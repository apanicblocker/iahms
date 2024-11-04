package cn.apkr.framework.aspectj;

import cn.apkr.common.annotation.Log;
import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.enums.BusinessStatus;
import cn.apkr.common.filter.PropertyPreExcludeFilter;
import cn.apkr.common.utils.SecurityUtils;
import cn.apkr.common.utils.ServletUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.common.utils.ip.IpUtils;
import cn.apkr.system.domain.SysOperLog;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	/** 排除敏感属性字段 */
	public static final String[] EXCLUDE_PROPERTIES = { "password", "oldPassword", "newPassword", "confirmPassword" };

	/** 计算操作消耗时间 */
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

	@Before(value = "@annotation(controllerLog)")
	public void boBefore(JoinPoint joinPoint, Log controllerLog) {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	/**
	 * 处理完请求后执行
	 * @param joinPoint
	 * @param controllerLog
	 * @param jsonResult
	 */
	@AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
	public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
		handleLog(joinPoint, controllerLog, null, jsonResult);
	}

	/**
	 * 拦截异常操作
	 *
	 * @param joinPoint 切点
	 * @param e 异常
	 */
	@AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
		handleLog(joinPoint, controllerLog, e, null);
	}

	protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
		try {
			// 获取当前的用户
			LoginUser loginUser = SecurityUtils.getLoginUser();

			// *========数据库日志=========*//
			SysOperLog operLog = new SysOperLog();
			operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
			// 请求的地址
			String ip = IpUtils.getIpAddr();
			operLog.setOperIp(ip);
			operLog.setOperUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
			if (loginUser != null) {
				operLog.setOperName(loginUser.getUsername());
			}

			if (e != null) {
				operLog.setStatus(BusinessStatus.FAIL.ordinal());
				operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
			}
			// 设置方法名称
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			operLog.setMethod(className + "." + methodName + "()");
			// 设置请求方法
			operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
			// 处理设置注解上的参数
			getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
			// 设置消耗时间
			operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
			// TODO: 保存数据库
		} catch (Exception ex) {
			// 记录本地异常日志
			log.error("异常信息:{}", ex.getMessage());
			ex.printStackTrace();
		} finally {
			TIME_THREADLOCAL.remove();
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 该方法主要用于解析Controller层方法上的注解信息，并将这些信息用于构建操作日志对象
	 *
	 * @param joinPoint 切入点对象，包含被拦截方法的信息
	 * @param log 方法上的注解对象，包含操作日志的相关信息
	 * @param operLog 操作日志对象，用于记录操作类型、标题、操作者类型等信息
	 * @param jsonResult 方法的返回结果，用于记录响应数据
	 * @throws Exception 可能抛出的异常
	 */
	public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog, Object jsonResult) throws Exception {
		// 设置action动作
		operLog.setBusinessType(log.businessType().ordinal());
		operLog.setTitle(log.title());
		operLog.setOperatorType(log.operatorType().ordinal());
		// 是否需要保存request，参数和值
		if (log.isSaveRequestData()) {
			// 获取参数的信息，传入到数据库中
			setRequestValue(joinPoint, operLog, log.excludeParamNames());
		}
		if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult)) {
			operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
		}
	}


	/**
	 * 获取请求的参数放到 log中
	 * @param joinPoint 切入点，用于获取方法参数
	 * @param operLog 操作日志对象，用于记录操作信息
	 * @param excludeParamNames 需要排除的参数名称数组，以避免记录敏感信息
	 * @throws Exception 异常
	 */
	private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames) {
		Map<?, ?> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
		String requestMethod = operLog.getRequestMethod();
		if (StringUtils.isEmpty(paramsMap)
				&& (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))) {
			String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
			operLog.setOperParam(StringUtils.substring(params, 0, 2000));
		} else {
			operLog.setOperParam(StringUtils.substring(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)), 0, 2000));
		}
	}

	/**
	 * 将参数数组转换为字符串并排除指定参数名的参数
	 * 用于日志记录或调试时输出参数信息
	 *
	 * @param paramsArray 要转换的参数数组
	 * @param excludeParamNames 需要排除的参数名数组，在转换过程中将忽略这些参数
	 * @return 转换后的参数字符串
	 */
	private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
		StringBuilder params = new StringBuilder();
		if (paramsArray != null && paramsArray.length > 0) {
			for (Object o : paramsArray) {
				if (StringUtils.isNotNull(o) && !isFilterObject(o)) {
					try {
						String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
						params.append(jsonObj).append(" ");
					} catch (Exception e) {
						log.debug("又是一个不处理的异常，这是为什么呢");
					}
				}
			}
		}
		return params.toString().trim();
	}

	/**
	 * 忽略敏感属性
	 */
	public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
		return new PropertyPreExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
	}

	/**
	 * 判断是否需要过滤的对象
	 * @param o 对象信息
	 * @return 如果是需要过滤的对象，则返回 true；否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public boolean isFilterObject(final Object o) {
		Class<?> clazz = o.getClass();
		if (clazz.isArray()) {
			return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
		} else if (Collection.class.isAssignableFrom(clazz)) {
			Collection collection = (Collection) o;
			for (Object value : collection) {
				return value instanceof MultipartFile;
			}
		} else if (Map.class.isAssignableFrom(clazz)) {
			Map map = (Map) o;
			for (Object value: map.entrySet()) {
				Map.Entry entry = (Map.Entry) value;
				return entry.getValue() instanceof MultipartFile;
			}
		}
		return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
				|| o instanceof BindingResult;
	}

}
