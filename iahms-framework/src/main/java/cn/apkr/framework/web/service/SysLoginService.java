package cn.apkr.framework.web.service;

import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.exception.user.UserPasswordNotMatchException;
import cn.apkr.common.utils.ip.IpUtils;
import cn.apkr.framework.security.context.AuthenticationContextHolder;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SysLoginService {

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private SysPasswordService passwordService;

	public String login(String username, String password, String code, String uuid) {
		// 验证码校验
		// TODO: 验证码校验方法
		// 登录前置校验
		// TODO: 登录前置校验方法
		// 验证IP是否被封锁
		String ip = IpUtils.getIpAddr();
		// TODO：IP封锁校验Service
		// 用户验证
		Authentication authentication = null;
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			AuthenticationContextHolder.setContext(authenticationToken);
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager.authenticate(authenticationToken);
		} catch(Exception e) {
			if (e instanceof BadCredentialsException) {
				// TODO: 登录日志记录
				throw new UserPasswordNotMatchException();
			} else {
				passwordService.incrementIpFailCount(ip);
				// TODO: 登录日志记录
				throw new ServiceException(e.getMessage());
			}
		} finally {
			AuthenticationContextHolder.clearContext();
		}
		// 异步记录登录成功信息
		// TODO: 异步任务管理器
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		// 生成 Token并返回
		return tokenService.createToken(loginUser);
	}

}
