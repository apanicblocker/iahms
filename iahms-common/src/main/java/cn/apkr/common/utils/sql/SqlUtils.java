package cn.apkr.common.utils.sql;

import cn.apkr.common.exception.UtilException;
import cn.apkr.common.utils.StringUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;

import java.io.StringReader;

public class SqlUtils {

	private static final CCJSqlParserManager parserManager = new CCJSqlParserManager();

	/**
	 * 定义常用的 sql关键字
	 */
	public static String SQL_REGEX = "and |extractvalue|updatexml|sleep|exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |or |union |like |+|/*|user()";

	/**
	 * 仅支持字母、数字、下划线、空格、逗号、小数点（支持多个字段排序）
	 */
	public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

	/**
	 * 检查字符，防止注入绕过
	 */
	public static String escapeOrderBySql(String value) {
		if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
			throw new UtilException("参数不符合规范，不能进行查询");
		}
		return value;
	}

	/**
	 * 验证 order by 语法是否符合规范
	 */
	public static boolean isValidOrderBySql(String value) {
		return value.matches(SQL_PATTERN);
	}

	/**
	 * SQL关键字检查
	 */
	public static void filterKeyword(String value) {
		if (StringUtils.isEmpty(value)) {
			return;
		}
		String[] sqlKeywords = StringUtils.split(SQL_REGEX, "\\|");
		for (String sqlKeyword : sqlKeywords) {
			if (StringUtils.indexOfIgnoreCase(value, sqlKeyword) > -1) {
				throw new UtilException("参数存在SQL注入风险");
			}
		}
	}

	public static Statement parseSql(String sql) throws JSQLParserException {
		return parserManager.parse(new StringReader(sql));
	}

}
