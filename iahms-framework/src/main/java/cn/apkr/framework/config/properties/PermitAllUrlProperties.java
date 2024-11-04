package cn.apkr.framework.config.properties;

import cn.apkr.common.annotation.Anonymous;
import cn.apkr.common.utils.SpringUtils;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {
	private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

	private ApplicationContext applicationContext;

	private List<String> urls = new ArrayList<>();

	public String ASTERISK = "*";

	static class Pair {
		public RequestMappingInfo first;
		public Anonymous second;

		public Pair(RequestMappingInfo first, Anonymous second) {
			this.first = first;
			this.second = second;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
		String matching = SpringUtils.getRequiredProperty("spring.mvc.pathmatch.matching-strategy").toLowerCase();
		map.keySet()
				.stream()
				.flatMap(info -> {
					HandlerMethod handlerMethod = map.get(info);
					// 获取方法上边的注解 替代path variable 为 *
					Anonymous method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Anonymous.class);
					// 获取类上边的注解, 替代path variable 为 *
					Anonymous controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Anonymous.class);
					return Arrays.stream(new Pair[] { new Pair(info, method), new Pair(info, controller) });
				})
				.filter(pair -> pair.second != null)
				.flatMap(pair -> switch (matching) {
					case "ant_path_matcher" ->
							Objects.requireNonNull(pair.first.getPatternsCondition().getPatterns()).stream();
					case "path_pattern_parser" ->
							Objects.requireNonNull(pair.first.getPathPatternsCondition().getPatternValues())
									.stream();
					default -> Objects.requireNonNull(pair.first.getPatternsCondition().getPatterns()).stream();
				})
				.map(url -> RegExUtils.replaceAll(url, PATTERN, ASTERISK))
				.forEach(urls::add);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public List<String> getUrls() {
		return urls;
	}
}
