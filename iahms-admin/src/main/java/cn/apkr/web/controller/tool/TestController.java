package cn.apkr.web.controller.tool;

import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.generator.mapper.GenTableMapper;
import cn.apkr.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "测试模块")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private GenTableMapper genTableMapper;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/allUser")
    public AjaxResult allUser(SysUser user) {
        List<SysUser> sysUsers = userService.selectUserList(user);
        return AjaxResult.success("操作成功", sysUsers);
    }

}
