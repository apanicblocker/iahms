package cn.apkr.web.controller.system;

import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class SysUserController {

	@Autowired
	private ISysUserService userService;



}
