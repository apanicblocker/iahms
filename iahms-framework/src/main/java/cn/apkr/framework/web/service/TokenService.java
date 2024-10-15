package cn.apkr.framework.web.service;

import cn.apkr.common.constant.CacheConstants;
import cn.apkr.common.constant.Constants;
import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.core.redis.RedisCache;
import cn.apkr.common.utils.ServletUtils;
import cn.apkr.common.utils.ip.AddressUtils;
import cn.apkr.common.utils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * 从HTTP请求获取用户身份信息
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求中携带的令牌
        String token = getValidTokenFromHeader(request);
        if (StringUtils.hasLength(token)) {
            try {
                // 获取JWT负载内容
                Claims claims = parseClaims(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String userKey = generateLoginTokenCacheKey(uuid);
                return redisCache.getCacheObject(userKey);
            } catch (Exception e) {
                log.error("获取用户信息异常'{}'", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 从HTTP请求头中获取令牌
     * @param request
     * @return 令牌
     */
    private String getValidTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.hasLength(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 把登录信息存入Redis
     * @param loginUser 登录信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (Objects.nonNull(loginUser) && StringUtils.hasLength(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 把登录信息从Redis中删除
     * @param uuid UUID
     */
    public void delLoginUser(String uuid) {
        if (StringUtils.hasLength(uuid)) {
            String userKey = generateLoginTokenCacheKey(uuid);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     * @param loginUser 登录信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {

        String uuid = UUID.randomUUID().toString();
        loginUser.setToken(uuid);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, uuid);

        // 创建秘钥实例
        String tokenId = UUID.randomUUID().toString();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                // 设置头部信息
                .header()
                .add("type", "JWT")
                .add("alg", "HS512")
                .and()
                // 设置自定义负载信息payload
                .claims(claims)
                // 令牌ID
                .id(tokenId)
                // 过期时间
                .expiration(new Date(loginUser.getExpireTime()))
                // 签发时间
                .issuedAt(new Date(loginUser.getLoginTime()))
                // 主题（用户名）
                .subject(loginUser.getUsername())
                // 签名
                .signWith(key, Jwts.SIG.HS512)
                .compact();
    }

    /**
     * 从令牌中获取用户名
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    /**
     * 解析令牌头部
     * @param token 令牌
     * @return 令牌头部
     */
    private JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    /**
     * 解析令牌载荷
     * @param token 令牌
     * @return 令牌载荷
     */
    private Claims parseClaims(String token) {
        return parseClaim(token).getPayload();
    }

    /**
     * 验证令牌并解析
     * @param token 令牌
     * @return Jws
     */
    private Jws<Claims> parseClaim(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }

    /**
     * 设置用户代理信息
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新混存
     * @param loginUser 登录信息
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getExpireTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = generateLoginTokenCacheKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String generateLoginTokenCacheKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }

}
