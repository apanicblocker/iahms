package cn.apkr.framework.web.service;

import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.exception.ServiceException;
import cn.apkr.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private ISysUserService userService;

	@Autowired
	private SysPermissionService permissionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userService.selectUserByUserName(username);
		if (user == null) {
			log.info("登录用户：{} 不存在.", username);
			throw new ServiceException("登录用户：" + username + " 不存在");
		} else if (user.getDelFlag()) {
			log.info("登录用户：{} 已被删除", username);
			throw new ServiceException("对不起，您的帐号：" + username + " 已被删除");
		} else if (!user.getStatus()) {
			log.info("登录用户：{} 已被停用.", username);
			throw new ServiceException("对不起，您的帐号：" + username + " 已停用");
		}
		return createLoginUser(user);
	}

	public UserDetails createLoginUser(SysUser user) {
		return new LoginUser(user.getUserId(), user);
	}
}
