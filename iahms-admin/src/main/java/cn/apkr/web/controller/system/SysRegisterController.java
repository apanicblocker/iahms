package cn.apkr.web.controller.system;

import cn.apkr.common.core.controller.BaseController;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.model.RegisterBody;
import cn.apkr.framework.web.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SysRegisterController extends BaseController {

    @Autowired
    private SysRegisterService registerService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        boolean registerSwitch = true;  // TODO: 注册功能开关
        if (!registerSwitch) {
            return AjaxResult.error("当前系统没有开启注册功能");
        }

        String msg = registerService.register(user);
        return Objects.isNull(msg) ? success() : error(msg);
    }
}
