package cn.apkr.common.utils;

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
	 * * 判断一个Map是否为空
	 *
	 * @param map 要判断的Map
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Map<?, ?> map)
	{
		return isNull(map) || map.isEmpty();
	}

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
	public static String toUnderScoreCase(String str)
	{
		if (str == null)
		{
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (i > 0)
			{
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			}
			else
			{
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1))
			{
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase)
			{
				sb.append(SEPARATOR);
			}
			else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase)
			{
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

}
