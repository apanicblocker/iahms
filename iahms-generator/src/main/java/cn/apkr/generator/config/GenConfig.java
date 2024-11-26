package cn.apkr.generator.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gen")
@PropertySource(value = { "classpath:generator.yml" })
public class GenConfig {

	// 作者
//	@Value("${author")
	@Getter
	@Setter
	public static String author;

	// 生成包路径
//	@Value("${packageName}")
	@Getter
	@Setter
	public static String packageName;

	// 自动去除表前缀，默认false
//	@Value("${autoRemovePre}")
	@Getter
	@Setter
	public static boolean autoRemovePre;

	// 表前缀（类名不包含表前缀）
//	@Value("${tablePrefix")
	@Getter
	@Setter
	public static String tablePrefix;

}
