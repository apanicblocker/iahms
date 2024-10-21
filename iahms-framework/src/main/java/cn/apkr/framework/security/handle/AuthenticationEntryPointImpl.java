package cn.apkr.framework.security.handle;

import cn.apkr.common.constant.HttpStatus;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.utils.ServletUtils;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 */
@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

	@Serial
	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		int code = HttpStatus.UNAUTHORIZED;
		String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
		ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
	}
}
