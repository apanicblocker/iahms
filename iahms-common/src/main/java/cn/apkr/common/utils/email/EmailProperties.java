package cn.apkr.common.utils.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    // 发件人邮箱
//    @Value("${email.user}")
    public String user;

    // 发件人邮箱授权码
//    @Value("${email.code}")
    public String code;

    // 发件人邮箱对应的服务器域名
//    @Value("${email.host}")
    public String host;

    // 身份验证开关
//    @Value("${email.auth}")
    private boolean auth;
}
