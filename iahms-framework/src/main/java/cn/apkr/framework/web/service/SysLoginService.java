package cn.apkr.framework.web.service;

import cn.apkr.common.constant.CacheConstants;
import cn.apkr.common.constant.HttpStatus;
import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.exception.user.CaptchaException;
import cn.apkr.common.exception.user.CaptchaExpireException;
import cn.apkr.common.exception.user.UserPasswordNotMatchException;
import cn.apkr.common.utils.CacheUtils;
import cn.apkr.common.utils.ip.IpUtils;
import cn.apkr.framework.security.context.AuthenticationContextHolder;
import cn.apkr.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SysLoginService {

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private SysPasswordService passwordService;

	@Autowired
	private ISysUserService userService;

	public String login(String username, String password, String code, String uuid) {
		// 验证码校验
		validateCaptcha(username, code, uuid);
		// 登录前置校验
		loginCheck(username, password);
		// 验证IP是否被封锁
		String ip = IpUtils.getIpAddress();
		// 验证ip是否被封锁
		passwordService.ipValidate(ip);
		// 用户验证
		Authentication authentication;
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			AuthenticationContextHolder.setContext(authenticationToken);
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager.authenticate(authenticationToken);
		} catch(BadCredentialsException e) {
			// TODO: 登录日志记录
			passwordService.incrementIpFailCount(ip);
			throw new ServiceException(e.getMessage(), HttpStatus.FORBIDDEN);
		} catch(Exception e) {
			// TODO: 登录日志记录
			throw new ServiceException(e.getMessage());
		} finally {
			AuthenticationContextHolder.clearContext();
		}
		// 异步记录登录成功信息
		// TODO: 登录日志记录
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		// 更新用户登录信息
		updateLoginInfo(loginUser.getUserId());
		// 生成 Token并返回
		return tokenService.createToken(loginUser);
	}


	/**
	 * 登录前置校验
	 * @param username 用户名
	 * @param password 用户密码
	 */
	public void loginCheck(String username, String password) {
		// TODO: 用户名和密码格式校验（本来是为了i18n的，但是现在感觉没必要先搁置了，所以直接用 @Validate 校验了这里就没写）
		// TODO: 登录黑名单校验（通过ConfigService
	}

	public void validateCaptcha(String username, String code, String uuid) {
		// TODO: 验证码校验开关判断（通过ConfigService
		boolean captchaEnabled = false;
		if (captchaEnabled) {
			String captcha = CacheUtils.get(CacheConstants.CAPTCHA_CODE_KEY, uuid != null ? uuid : "", String.class);
			CacheUtils.remove(CacheConstants.CAPTCHA_CODE_KEY, uuid != null ? uuid : "");
			if (captcha == null) {
				// TODO: 记录登录日志
				throw new CaptchaExpireException();
			}
			if (!code.equalsIgnoreCase(captcha)) {
				// TODO: 记录登录日志
				throw new CaptchaException();
			}
		}
	}

	public void updateLoginInfo(Long userId) {
		SysUser user = new SysUser();
		user.setUserId(userId);
		user.setLoginIp(IpUtils.getIpAddress());
		user.setLoginDate(new Date());
		userService.updateUserProfile(user);
	}

}
