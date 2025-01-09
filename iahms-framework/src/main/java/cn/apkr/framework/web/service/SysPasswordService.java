package cn.apkr.framework.web.service;

import cn.apkr.common.constant.CacheConstants;
import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.exception.user.IpRetryLimitExceedException;
import cn.apkr.common.exception.user.UserPasswordNotMatchException;
import cn.apkr.common.exception.user.UserPasswordRetryLimitExceedException;
import cn.apkr.common.utils.CacheUtils;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.common.utils.ip.IpUtils;
import cn.apkr.framework.security.context.AuthenticationContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SysPasswordService {

	// 密码最大错误次数
	@Value(value = "${user.password.maxRetryCount}")
	private int maxRetryCount;

	// 密码错误锁定时间
	@Value(value = "${user.password.lockTime}")
	private int lockTime;

	// ip最大错误重试次数
	@Value(value = "${user.ip.maxRetryCount}")
	public int maxIpRetryCount;

	// 密码错误多次后ip的锁定时间
	@Value(value = "${user.ip.lockTime}")
	public int ipLockTime;

	/**
	 * 获取登录账户密码错误次数的缓存
	 * @return 缓存Cache
	 */
	private Cache getPwdCache() {
		return CacheUtils.getCache(CacheConstants.PWD_ERR_CNT_KEY);
	}

	/**
	 * 获取ip级的登录账户密码错误次数的缓存
	 * @return 缓存Cache
	 */
	private Cache getIpCache() {
		return CacheUtils.getCache(CacheConstants.IP_ERR_CNT_KEY);
	}

	/**
	 * 校验登录用户密码是否正确
	 * @param user 用户对象
	 */
	public void validate(SysUser user) {
		Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
		String username = usernamePasswordAuthenticationToken.getName();
		String password = usernamePasswordAuthenticationToken.getCredentials().toString();

		// 验证ip登录错误次数
		ipValidate(IpUtils.getIpAddress());
		// 根据username获取对应用户名的尝试登录次数
		Integer retryCount = getPwdCache().get(username, Integer.class);
		if (retryCount == null) {
			retryCount = 0;
		}
		if (retryCount >= maxRetryCount) {
			// TODO: 异步任务管理器（记录登录日志）
			throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
		}
		if (!matches(user, password)) {
			retryCount = retryCount + 1;
			// TODO: 异步任务管理器（记录登录日志）
			CacheUtils.put(CacheConstants.PWD_ERR_CNT_KEY, username, retryCount, lockTime, TimeUnit.MINUTES);
			throw new UserPasswordNotMatchException();
		} else {
			// 清除登录记录缓存
			getPwdCache().evictIfPresent(username);
		}

	}

	public void ipValidate(String ip) {
		Integer ipRetryCount = getIpCache().get(ip, Integer.class);
		if (ipRetryCount == null) {
			ipRetryCount = 0;
		}
		if (ipRetryCount >= maxIpRetryCount) {
			throw new IpRetryLimitExceedException(maxIpRetryCount, ipLockTime);
		}
	}

	public void incrementIpFailCount(String ip) {
		Integer ipRetryCount = getIpCache().get(ip, Integer.class);
		if (ipRetryCount == null) {
			ipRetryCount = 0;
		}
		ipRetryCount += 1;
		CacheUtils.put(CacheConstants.IP_ERR_CNT_KEY, ip, ipRetryCount, ipLockTime, TimeUnit.MINUTES);
	}

	/**
	 * 验证密码是否正确
	 * @param user 用户对象
	 * @param rawPassword 需要校验的密码
	 * @return 结果
	 */
	public boolean matches(SysUser user, String rawPassword) {
		return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
	}

}
