package cn.apkr.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "iahms")
public class IahmsConfig {

    // 项目名称
    private String name;

    // 版本名
    private String version;

    // 版权年份
    private String copyrightYear;

    // 上传路径
    private static String profile;

    // 获取地址开关
    private static boolean addressEnabled;

    // 验证码类型
    private static String captchaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getProfile() {
        return profile;
    }

    public static void setProfile(String profile) {
        IahmsConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static void setAddressEnabled(boolean addressEnabled) {
        IahmsConfig.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public static void setCaptchaType(String captchaType) {
        IahmsConfig.captchaType = captchaType;
    }
}
