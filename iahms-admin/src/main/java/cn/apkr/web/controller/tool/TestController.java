package cn.apkr.web.controller.tool;

import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.utils.email.EmailProperties;
import cn.apkr.common.utils.email.EmailUtils;
import cn.apkr.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "测试模块")
@RestController
public class TestController {

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private ISysUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/allUser")
    public AjaxResult allUser(SysUser user) {
        List<SysUser> sysUsers = userService.selectUserList(user);
        return AjaxResult.success("操作成功", sysUsers);
    }

    @PostMapping("/send")
    public String send() {
        EmailUtils.sendMail(emailProperties, "2013690140@qq.com", "邮箱模块测试", "总有一天我会成为百万富翁！");
        return "send already";
    }
}
