package cn.apkr.generator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gen")
@PropertySource(value = { "classpath:generator.yml" })
public class GenConfig {

	// 作者
	@Getter
	public static String author;

	// 生成包路径
	@Getter
	public static String packageName;

	// 自动去除表前缀，默认false
	@Getter
	public static boolean autoRemovePre;

	// 表前缀（类名不包含表前缀）
	@Getter
	public static String tablePrefix;

	@Value("${author}")
	public void setAuthor(String author) {
		GenConfig.author = author;
	}

	@Value("${packageName}")
	public void setPackageName(String packageName) {
		GenConfig.packageName = packageName;
	}

	@Value("${autoRemovePre}")
	public void setAutoRemovePre(boolean autoRemovePre) {
		GenConfig.autoRemovePre = autoRemovePre;
	}

	@Value("${tablePrefix}")
	public void setTablePrefix(String tablePrefix) {
		GenConfig.tablePrefix = tablePrefix;
	}
}
