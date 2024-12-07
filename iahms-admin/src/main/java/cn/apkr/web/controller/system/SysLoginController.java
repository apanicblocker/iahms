package cn.apkr.web.controller.system;

import cn.apkr.common.constant.Constants;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.core.domain.model.LoginBody;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.framework.web.service.SysLoginService;
import cn.apkr.framework.web.service.SysPermissionService;
import cn.apkr.framework.web.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Tag(name = "登录验证")
@RestController
public class SysLoginController {

	@Autowired
	private SysLoginService loginService;

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private TokenService tokenService;

	@Operation(summary = "登录方法")
	@PostMapping("/login")
	public AjaxResult login(@RequestBody LoginBody loginBody) {
		AjaxResult ajax = AjaxResult.success();
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
		ajax.put(Constants.TOKEN, token);
		return ajax;
	}

	@Operation(summary = "获取用户信息")
	@GetMapping("/getInfo")
	public AjaxResult getInfo() {
		SysUser user = SecurityUtils.getLoginUser().getUser();
		// 角色集合
		Set<String> roles = permissionService.getRolePermission(user);
		AjaxResult ajax = AjaxResult.success();
		ajax.put("user", user);
		ajax.put("roles", roles);
		return ajax;
	}

}
