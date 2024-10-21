package cn.apkr.common.constant;

import io.jsonwebtoken.Claims;

public class Constants {

    // UTF-8 字符集
    public static final String UTF8 = "UTF-8";

    // 令牌
    public static final String TOKEN = "token";

    // 令牌键值
    public static final String LOGIN_USER_KEY = "login_user_key";

    // 令牌前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 用户ID
    public static final String JWT_USERID = "userid";

    // 用户名称
    public static final String JWT_USERNAME = Claims.SUBJECT;

    // 用户头像
    public static final String JWT_AVATAR = "avatar";

    // 创建时间
    public static final String JWT_CREATED = "created";

    // 用户权限
    public static final String JWT_AUTHORITIES = "authorities";

    // 资源映射路径 前缀
    public static final String RESOURCE_PREFIX = "/profile";

    // 自动识别json对象白名单配置（仅允许解析的包名，范围越小越安全）
    public static final String[] JSON_WHITELIST_STR = { "org.springframework", "cn.apkr" };
}
