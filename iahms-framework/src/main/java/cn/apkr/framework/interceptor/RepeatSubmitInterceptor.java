package cn.apkr.framework.interceptor;

import cn.apkr.common.annotation.RepeatSubmit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 防止重复提交拦截器
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	/**
	 * 验证是否重复提交由子类实现具体的防重复提交的规则
	 * @param request
	 * @param annotation
	 * @return
	 */
	public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
