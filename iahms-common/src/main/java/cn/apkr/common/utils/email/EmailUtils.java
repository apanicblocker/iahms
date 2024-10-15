package cn.apkr.common.utils.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {

    public static boolean sendMail(EmailProperties emailProperties, String to, String title, String content) {
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮箱服务器
        properties.setProperty("mail.smtp.host", emailProperties.host);
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", emailProperties.host);
        properties.put("mail.smtp.auth", emailProperties.isAuth());
        // 创建邮件会话
        Session defaultInstance = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.user, emailProperties.code);
            }
        });

        try {
            // 创建默认 MimeMessage 对象
            MimeMessage message = new MimeMessage(defaultInstance);
            // 设置发送者
            message.setFrom(new InternetAddress(emailProperties.user));
            // 设置接受者
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            // 设置标题
            message.setSubject(title);
            // 设置内容
            message.setText(content);
            // 发送消息
            Transport.send(message);
            System.out.println("邮件发送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
            return false;
        }
    }

}
