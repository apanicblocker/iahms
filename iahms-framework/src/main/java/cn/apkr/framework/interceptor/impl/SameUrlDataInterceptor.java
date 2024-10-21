package cn.apkr.framework.interceptor.impl;

import cn.apkr.common.annotation.RepeatSubmit;
import cn.apkr.framework.interceptor.RepeatSubmitInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 判断请求url和数据是否和上一次相同，
 * 如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor
{
	public final String REPEAT_PARAMS = "repeatParams";

	public final String REPEAT_TIME = "repeatTime";

	// 令牌自定义标识
	@Value("${token.header}")
	private String header;

	@SuppressWarnings("unchecked")
	@Override
	public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
		return false;
	}

	/**
	 * 判断参数是否相同
	 */
	private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
		String nowParams = (String) nowMap.get(REPEAT_PARAMS);
		String preParams = (String) preMap.get(REPEAT_PARAMS);
		return nowParams.equals(preParams);
	}

	/**
	 * 判断两次间隔时间
	 */
	private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap, int interval) {
		long time1 = (Long) nowMap.get(REPEAT_TIME);
		long time2 = (Long) preMap.get(REPEAT_TIME);
		return (time1 - time2) < interval;
	}
}
