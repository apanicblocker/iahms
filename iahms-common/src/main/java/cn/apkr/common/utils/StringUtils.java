package cn.apkr.common.utils;

import cn.apkr.common.core.text.StrFormatter;

import java.util.Collection;
import java.util.Map;

public final class StringUtils extends org.apache.commons.lang3.StringUtils {

	private StringUtils() {}

	/** 空字符串 */
	private static final String NULLSTR = "";

	/** 下划线 */
	private static final char SEPARATOR = '_';

	/** 星号 */
	private static final char ASTERISK = '*';

	/**
	 * * 判断一个对象是否为空
	 *
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * 判断一个 Collection是否为空，包含 List，Set，Queue
	 * @param collection 要判断的 Collection
	 * @return true: 为空，false: 非空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return isNull(collection) || collection.isEmpty();
	}

	/**
	 * 判断一个 Collection是否非空，包含 List，Set，Queue
	 * @param collection 要判断的 Collection
	 * @return true: 非空，false: 为空
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * * 判断一个对象数组是否为空
	 *
	 * @param objects 要判断的对象数组
	 ** @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects) {
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * * 判断一个对象数组是否非空
	 *
	 * @param objects 要判断的对象数组
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	/**
	 * * 判断一个Map是否为空
	 *
	 * @param map 要判断的Map
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	/**
	 * * 判断一个Map是否为空
	 *
	 * @param map 要判断的Map
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * * 判断一个对象是否非空
	 *
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * 驼峰转下划线命名
	 */
	public static String camelCase2lowUnderscore(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (i > 0) {
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			} else {
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1)) {
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
				sb.append(SEPARATOR);
			} else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 全大写+下划线转至驼峰式命名法
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String capUnderscore2CamelCase(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母大写
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String[] camels = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1).toLowerCase());
		}
		return result.toString();
	}

	/**
	 * 全小写+下划线转至驼峰式命名法
	 * 例如：user_name -> userName
	 */
	public static String lowUnderscore2CamelCase(String s) {
		if (s == null) {
			return null;
		}
		if (s.indexOf(SEPARATOR) == -1) {
			return s;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 *
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param params 参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params) {
		if (isEmpty(params) || isEmpty(template)) {
			return template;
		}
		return StrFormatter.format(template, params);
	}

}
