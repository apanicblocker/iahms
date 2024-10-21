package cn.apkr.web.controller.system;

import cn.apkr.common.constant.Constants;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.model.LoginBody;
import cn.apkr.framework.web.service.SysLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "登录验证")
@RestController
public class SysLoginController {

	@Autowired
	private SysLoginService loginService;

	@Operation(summary = "登录方法")
	@PostMapping("/login")
	public AjaxResult login(@RequestBody LoginBody loginBody) {
		AjaxResult ajax = AjaxResult.success();
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
				loginBody.getUuid());
		ajax.put(Constants.TOKEN, token);
		return ajax;
	}
}
