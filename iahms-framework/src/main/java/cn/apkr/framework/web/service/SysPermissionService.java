package cn.apkr.framework.web.service;

import cn.apkr.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class SysPermissionService {

	/**
	 * 获取角色数据权限
	 * @param user 用户信息
	 * @return 角色权限信息
	 */
	public Set<String> getRolePermission(SysUser user) {
		Set<String> roles = new HashSet<>();
		if (user.isAdmin()) {
			roles.add("admin");
		} else {
			roles.addAll(new ArrayList<>()); // temp
		}
		return roles;
	}

}
