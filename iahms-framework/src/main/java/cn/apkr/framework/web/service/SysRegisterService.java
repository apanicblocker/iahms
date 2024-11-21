package cn.apkr.framework.web.service;

import cn.apkr.common.constant.UserConstants;
import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.common.core.domain.model.RegisterBody;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.system.service.ISysConfigService;
import cn.apkr.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SysRegisterService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    public String register(RegisterBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            // TODO: 验证码
            try {
                // 等待2秒钟模拟验证码temp
                this.wait(1000 * 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 注册信息验证
        if (Objects.isNull(username)) {
            msg = "用户名不能为空";
        } else if (Objects.isNull(password)) {
            msg = "密码不能为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH 
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (!userService.checkUsernameUnique(sysUser)) {
            msg = "保存用户'" + username + "'失败，注册帐号已存在";
        } else {
            sysUser.setNickname(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag) {
                msg = "注册失败，请联系统管理人员";
            } else {
                // TODO: 异步记录注册成功的日志信息至数据库
            }
        }

        return msg;
    }

}
