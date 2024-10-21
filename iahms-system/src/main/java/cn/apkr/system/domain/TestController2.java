package cn.apkr.system.domain;

import cn.apkr.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

	@GetMapping("/test2")
	public AjaxResult test2() {
		return AjaxResult.success("测试成功");
	}

}
