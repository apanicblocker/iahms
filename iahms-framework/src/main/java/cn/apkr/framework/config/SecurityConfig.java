package cn.apkr.framework.config;

import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.framework.config.properties.PermitAllUrlProperties;
import cn.apkr.framework.security.filter.JwtAuthenticationTokenFilter;
import cn.apkr.framework.security.handle.AuthenticationEntryPointImpl;
import cn.apkr.framework.security.handle.LogoutSuccessHandlerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig {

	/**
	 * 自定义用户认证逻辑
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 认证失败处理类
	 */
	@Autowired
	private AuthenticationEntryPointImpl unauthorizedHandler;

	/**
	 * 登出处理类
	 */
	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandler;

	/**
	 * token认证过滤器
	 */
	@Autowired
	private JwtAuthenticationTokenFilter authenticationTokenFilter;

	/**
	 * 跨域过滤器
	 */
	@Autowired
	private CorsFilter corsFilter;

	@Autowired
	private PermitAllUrlProperties permitAllUrl;

	/**
	 * 提供AuthenticationManager认证类
	 */
	@Bean
	AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		// 身份认证接口
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return new ProviderManager(daoAuthenticationProvider);
	}

	/**
	 * anyRequest | 匹配所有请求路径
	 * access | SpringEl表达式结果为true时可以访问
	 * anonymous | 匿名可以访问
	 * denyAll | 用户不能访问
	 * fullyAuthenticated | 用户完全认证可以访问（非remember-me下自动登录）
	 * hasAnyAuthority | 如果有参数，参数表示权限，则其中任何一个权限可以访问
	 * hasAnyRole | 如果有参数，参数表示角色，则其中任何一个角色可以访问
	 * hasAuthority | 如果有参数，参数表示权限，则其权限可以访问
	 * hasIpAddress | 如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
	 * hasRole | 如果有参数，参数表示角色，则其角色可以访问
	 * permitAll | 用户可以任意访问
	 * rememberMe | 允许通过remember-me登录的用户访问
	 * authenticated | 用户登录后可访问
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				// CSRF禁用，因为不使用session
				.csrf(AbstractHttpConfigurer::disable)
				// 禁用HTTP响应头
				.headers((headersCustomizer -> {
					headersCustomizer.cacheControl(HeadersConfigurer.CacheControlConfig::disable)
							.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
				}))
				// 认证失败处理类
				.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
				// 基于token，所以不需要session
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// 注解标记允许匿名访问的url
				.authorizeHttpRequests(requests -> {
					permitAllUrl.getUrls().forEach(url -> requests.requestMatchers(url).permitAll());
					// 对于登录login 注册register 验证码captchaImage 允许匿名访问
					requests.requestMatchers("/login", "/register").permitAll()
							// 静态资源，可匿名访问
							.requestMatchers(HttpMethod.GET, "/", "/*.html",
									"/**/*.html", "/**/*.css", "/**/*.js", "/profile/**")
							.permitAll()
							// 其他资源（外部文档、
							.requestMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**",
									"/*/api-docs", "/druid/**", "/*/api-docs/**")
							.permitAll()
							.requestMatchers("/hello").permitAll()
							// 除了上面外的所有请求都需要鉴权认证
							.anyRequest().authenticated();
				})
				// 添加 Logout过滤器
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler))
				// 添加 JWT过滤器
				.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
				// 添加 CORS过滤器
				.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class)
				.addFilterBefore(corsFilter, LogoutFilter.class)
				.build();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return SecurityUtils.PASSWORD_ENCODER;
	}
}
