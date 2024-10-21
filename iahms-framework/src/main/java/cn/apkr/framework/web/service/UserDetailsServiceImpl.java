package cn.apkr.framework.web.service;

import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.enums.UserStatus;
import cn.apkr.common.exception.ServiceException;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private SysUserService userService;

	@Autowired
	private SysPermissionService permissionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("已进入Load方法"); // temp
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
		Set<String> permissions = new HashSet<>();
		permissions.add("*:*:*");
		return new LoginUser(user.getId(), user, permissions);
	}
}
