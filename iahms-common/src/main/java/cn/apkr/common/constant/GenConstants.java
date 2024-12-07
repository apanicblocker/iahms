package cn.apkr.common.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenConstants {

	// 单表（增删改查）
	public static final String TPL_CRUD = "crud";

	// 树表（增删改查）
	public static final String TPL_TREE = "tree";

	// 主子表（增删改查）
	public static final String TPL_SUB = "sub";

	// 树编码字段
	public static final String TREE_CODE = "treeCode";

	// 树父编码字段
	public static final String TREE_PARENT_CODE = "treeParentCode";

	// 树名称字段
	public static final String TREE_NAME = "treeName";

	// 上级菜单ID字段
	public static final String PARENT_MENU_ID = "parentMenuId";

	// 上级菜单名称字段
	public static final String PARENT_MENU_NAME = "parentMenuName";

//	// 数据库数字类型
//	public static final String[] COLUMN_TYPE_NUM = {
//			"tinyint", "smallint", "mediumint", "int", "number", "integer",
//			"bit", "bigint", "float", "double", "decimal" };

	// 数据库数字类型映射
	public static final Map<String, String> COLUMN_TYPE_NUM_MAP = new HashMap<>();
	static {
		COLUMN_TYPE_NUM_MAP.put("bigint", "Long");
		COLUMN_TYPE_NUM_MAP.put("int", "Integer");
		COLUMN_TYPE_NUM_MAP.put("tinyint", "Integer");
		COLUMN_TYPE_NUM_MAP.put("float", "Float");
		COLUMN_TYPE_NUM_MAP.put("double", "Double");
		COLUMN_TYPE_NUM_MAP.put("decimal", "BigDecimal");
	}
	// 数据库数字类型
	public static final String[] COLUMN_TYPE_NUM = COLUMN_TYPE_NUM_MAP.keySet().toArray(new String[0]);

	// 数据库字符串类型
	public static final String[] COLUMN_TYPE_STR = {
			"char", "varchar", "nvarchar", "varchar2"};

	// 数据库文本类型
	public static final String[] COLUMN_TYPE_TEXT = {
			"tinytext", "text", "mediumtext", "longtext" };

	// 数据库时间类型
	public static final String[] COLUMN_TYPE_TIME = {
			"datetime", "time", "date", "timestamp" };

	// 页面不需要编辑的字段
	public static final String[] COLUMN_NAME_NOT_EDIT = {
			"id", "del_flag", "tenant_id", "revision", "create_time", "create_by" };

//	// 数据表通用字段（已经在BaseEntity中）
//	public static final String[] COLUMN_NAME_COMMON = {
//			"remark", "tenant_id", "revision", "create_time", "create_by", "update_time", "update_by" };

	// 页面不需要显示的列表字段
	public static final String[] COLUMN_NAME_NOT_LIST = {
			"id", "del_flag", "tenant_id", "revision", "create_time", "create_by", "update_time", "update_by" };

	// 页面不需要查询的字段
	public static final String[] COLUMN_NAME_NOT_QUERY = {
			"id", "del_flag", "remark", "tenant_id", "revision", "create_by", "create_time", "update_by", "update_time" };

	// Entity基类字段
	public static final String[] BASE_ENTITY = {
			"remark", "tenantId", "revision", "createTime", "createBy", "updateTime", "updateBy" };

	// Tree基类字段
	public static final String[] TREE_ENTITY = {
			"parentName", "parentId", "orderNum", "ancestors", "children" };

	// 文本框
	public static final String HTML_INPUT = "input";

	// 文本域
	public static final String HTML_TEXTAREA = "textarea";

	// 下拉框
	public static final String HTML_SELECT = "select";

	// 单选框
	public static final String HTML_RADIO = "radio";

	// 复选框
	public static final String HTML_CHECKBOX = "checkbox";

	// 日期控件
	public static final String HTML_DATETIME = "datetime";

	// 图片上传控件
	public static final String HTML_IMAGE_UPLOAD = "imageUpload";

	// 文件上传控件
	public static final String HTML_FILE_UPLOAD = "fileUpload";

	// 富文本类型
	public static final String HTML_EDITOR = "editor";

	// 字符串类型
	public static final String TYPE_STRING = "String";

	// 短整型
	public static final String TYPE_SHORT = "Short";

	// 整型
	public static final String TYPE_INTEGER = "Integer";

	// 长整型
	public static final String TYPE_LONG = "Long";

	// 浮点型
	public static final String TYPE_DOUBLE = "Double";

	// 高精度计算类型
	public static final String TYPE_BIGDECIMAL = "BigDecimal";

	// 布尔类型
	public static final String TYPE_BOOLEAN = "Boolean";

	// 时间类型
	public static final String TYPE_DATE = "Date";

	// 模糊查询
	public static final String QUERY_LIKE = "LIKE";

	// 相等查询
	public static final String QUERY_EQ = "EQ";

	// 需要
	public static final Boolean REQUIRE = true;

	// 不需要
	public static final Boolean NOT_REQUIRE = false;

}
