package cn.apkr.system.service;

public interface ISysConfigService {

    /**
     * 获取验证码开关
     * @return
     */
    boolean selectCaptchaEnabled();
}
