package cn.apkr.generator.utils;

import cn.apkr.common.constant.GenConstants;
import cn.apkr.common.utils.DateUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.generator.domain.GenTable;
import cn.apkr.generator.domain.GenTableColumn;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VelocityUtils {

	private static final Logger log = LoggerFactory.getLogger(VelocityUtils.class);

	// 项目空间路径
	private static final String PROJECT_PATH = "main/java";

	// mybatis空间路径
	private static final String MYBATIS_PATH = "main/resources/mapper";

	// 默认上级菜单，系统工具（非必要）
	private static final String DEFAULT_PARENT_MENU_ID = "3";

//	// test
//	public static void initMapperContext(VelocityContext context, GenTable table) {
//		String packageName = table.getPackageName();
//		context.put("packageName", packageName);
//		context.put("className", StringUtils.uncapitalize(table.getClassName()));
//		context.put("ClassName", table.getClassName());
//		context.put("columns", table.getColumns());
//	}

	public static VelocityContext prepareContext(GenTable genTable) {
		String moduleName = genTable.getModuleName();
		String businessName = genTable.getBusinessName();
		String packageName = genTable.getPackageName();
		String tplCategory = genTable.getTplCategory();
		String functionName = genTable.getFunctionName();

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("tplCategory", genTable.getTplCategory());
		velocityContext.put("tableName", genTable.getTableName());
		velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
		velocityContext.put("ClassName", genTable.getClassName());
		velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
		velocityContext.put("moduleName", genTable.getModuleName());
		velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
		velocityContext.put("businessName", genTable.getBusinessName());
		velocityContext.put("basePackage", getPackagePrefix(packageName));
		velocityContext.put("packageName", packageName);
		velocityContext.put("author", genTable.getFunctionAuthor());
		velocityContext.put("datetime", DateUtils.getDate());
		velocityContext.put("pkColumn", genTable.getPkColumn());
		velocityContext.put("importList", getImportList(genTable));
		velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
		velocityContext.put("columns", genTable.getColumns());
		velocityContext.put("table", genTable);
		velocityContext.put("dicts", getDicts(genTable));
		setMenuVelocityContext(velocityContext, genTable);
		if (GenConstants.TPL_TREE.equals(tplCategory)) {
			setTreeVelocityContext(velocityContext, genTable);
		}
		if (GenConstants.TPL_SUB.equals(tplCategory)) {
			setSubVelocityContext(velocityContext, genTable);
		}
		return velocityContext;
	}

	public static void setMenuVelocityContext(VelocityContext context, GenTable genTable) {
		String options = genTable.getOptions();
		if (StringUtils.isEmpty(options)) {
			log.warn("业务字段为空：{}", genTable.getTableName());
			return;
		}
		JSONObject paramsObj = JSON.parseObject(options);
		String treeCode = getTreeCode(paramsObj);
		String treeParentCode = getTreeCode(paramsObj);
		String treeName = getTreeName(paramsObj);

		context.put("treeCode", treeCode);
		context.put("treeParentCode", treeParentCode);
		context.put("treeName", treeName);
		context.put("expandColumn", getExpandColumn(genTable));
		if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
		{
			context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
		}
		if (paramsObj.containsKey(GenConstants.TREE_NAME))
		{
			context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
		}
	}

	public static void setSubVelocityContext(VelocityContext context, GenTable genTable) {
		GenTable subTable = genTable.getSubTable();
		String subTableName = genTable.getSubTableName();
		String subTableFkName = genTable.getSubTableFkName();
		String subClassName = genTable.getSubTable().getClassName();
		String subTableFkClassName = StringUtils.convertToCamelCase(subTableFkName);

		context.put("subTable", subTable);
		context.put("subTableName", subTableName);
		context.put("subTableFkName", subTableFkName);
		context.put("subTableFkClassName", subTableFkClassName);
		context.put("subTableFkclassName", StringUtils.uncapitalize(subTableFkClassName));
		context.put("subClassName", subClassName);
		context.put("subclassName", StringUtils.uncapitalize(subClassName));
		context.put("subImportList", getImportList(genTable.getSubTable()));
	}

	public static void setTreeVelocityContext(VelocityContext context, GenTable genTable)
	{
		String options = genTable.getOptions();
		JSONObject paramsObj = JSON.parseObject(options);
		String treeCode = getTreeCode(paramsObj);
		String treeParentCode = getTreeParentCode(paramsObj);
		String treeName = getTreeName(paramsObj);

		context.put("treeCode", treeCode);
		context.put("treeParentCode", treeParentCode);
		context.put("treeName", treeName);
		context.put("expandColumn", getExpandColumn(genTable));
		if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
		{
			context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
		}
		if (paramsObj.containsKey(GenConstants.TREE_NAME))
		{
			context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
		}
	}

	/**
	 * 获取模板信息
	 *
	 * @return 模板列表
	 */
	public static List<String> getTemplateList(String tplCategory) {
		List<String> templates = new ArrayList<String>();
		templates.add("vm/java/domain.java.vm");
		templates.add("vm/java/mapper.java.vm");
		templates.add("vm/java/service.java.vm");
		templates.add("vm/java/serviceImpl.java.vm");
		templates.add("vm/java/controller.java.vm");
		templates.add("vm/xml/mapper.xml.vm");
		templates.add("vm/sql/sql.vm");
		templates.add("vm/js/api.js.vm");
		if (GenConstants.TPL_CRUD.equals(tplCategory)) {
			templates.add("vm/vue/index.vue.vm");
		}
		else if (GenConstants.TPL_TREE.equals(tplCategory)) {
			templates.add("vm/vue/index-tree.vue.vm");
		}
		else if (GenConstants.TPL_SUB.equals(tplCategory)) {
			templates.add("vm/vue/index.vue.vm");
			templates.add("vm/java/sub-domain.java.vm");
		}
		return templates;
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, GenTable genTable)
	{
		// 文件名称
		String fileName = "";
		// 包路径
		String packageName = genTable.getPackageName();
		// 模块名
		String moduleName = genTable.getModuleName();
		// 大写类名
		String className = genTable.getClassName();
		// 业务名称
		String businessName = genTable.getBusinessName();

		String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
		String mybatisPath = MYBATIS_PATH + "/" + moduleName;
		String vuePath = "vue";

		if (template.contains("domain.java.vm")) {
			fileName = StringUtils.format("{}/domain/{}.java", javaPath, className);
		}
		if (template.contains("sub-domain.java.vm") && StringUtils.equals(GenConstants.TPL_SUB, genTable.getTplCategory())) {
			fileName = StringUtils.format("{}/domain/{}.java", javaPath, genTable.getSubTable().getClassName());
		}
		else if (template.contains("mapper.java.vm")) {
			fileName = StringUtils.format("{}/mapper/{}Mapper.java", javaPath, className);
		}
		else if (template.contains("service.java.vm")) {
			fileName = StringUtils.format("{}/service/I{}Service.java", javaPath, className);
		}
		else if (template.contains("serviceImpl.java.vm")) {
			fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
		}
		else if (template.contains("controller.java.vm")) {
			fileName = StringUtils.format("{}/controller/{}Controller.java", javaPath, className);
		}
		else if (template.contains("mapper.xml.vm")) {
			fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
		}
		else if (template.contains("sql.vm")) {
			fileName = businessName + "Menu.sql";
		}
		else if (template.contains("api.js.vm")) {
			fileName = StringUtils.format("{}/api/{}/{}.js", vuePath, moduleName, businessName);
		}
		else if (template.contains("index.vue.vm")) {
			fileName = StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
		}
		else if (template.contains("index-tree.vue.vm")) {
			fileName = StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
		}
		return fileName;
	}

	/**
	 * 获取包前缀
	 * @param packageName 包名称
	 * @return 包前缀名称
	 */
	public static String getPackagePrefix(String packageName) {
		int lastIndex = packageName.lastIndexOf(".");
		return StringUtils.substring(packageName, 0, lastIndex);
	}

	/**
	 * 根据列类型获取导入包
	 * @param genTable 业务表对象
	 * @return 返回需要导入的包列表
	 */
	public static HashSet<String> getImportList(GenTable genTable) {
		List<GenTableColumn> columns = genTable.getColumns();
		GenTable subGenTable = genTable.getSubTable();
		HashSet<String> importList = new HashSet<String>();
		if (StringUtils.isNotNull(subGenTable)) {
			importList.add("java.util.List");
		}
		for (GenTableColumn column : columns) {
			if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
				importList.add("java.util.Date");
				importList.add("com.fasterxml.jackson.annotation.JsonFormat");
			}
			else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
				importList.add("java.math.BigDecimal");
			}
		}
		return importList;
	}

	/**
	 * 根据列类型获取字典组
	 * @param genTable 业务表对象
	 * @return 返回字典组
	 */
	public static String getDicts(GenTable genTable) {
		List<GenTableColumn> columns = genTable.getColumns();
		Set<String> dicts = new HashSet<String>();
		addDicts(dicts, columns);
		if (StringUtils.isNotNull(genTable.getSubTable())) {
			List<GenTableColumn> subColumns = genTable.getSubTable().getColumns();
			addDicts(dicts, subColumns);
		}
		return StringUtils.join(dicts, ", ");
	}

	/**
	 * 添加字典列表
	 * @param dicts 字典列表
	 * @param columns 列集合
	 */
	public static void addDicts(Set<String> dicts, List<GenTableColumn> columns) {
		for (GenTableColumn column : columns) {
			if (!column.isSuperColumn() && StringUtils.isNotEmpty(column.getDictType()) && StringUtils.equalsAny(
					column.getHtmlType(),
					new String[] { GenConstants.HTML_SELECT, GenConstants.HTML_RADIO, GenConstants.HTML_CHECKBOX })) {
				dicts.add("'" + column.getDictType() + "'");
			}
		}
	}

	/**
	 * 获取权限前缀
	 * @param moduleName 模块名称
	 * @param businessName 业务名称
	 * @return 返回权限前缀
	 */
	public static String getPermissionPrefix(String moduleName, String businessName) {
		return StringUtils.format("{}:{}", moduleName, businessName);
	}

	/**
	 * 获取上级菜单ID字段
	 * @param paramsObj 生成其他选项
	 * @return 上级菜单ID字段
	 */
	public static String getParentMenuId(JSONObject paramsObj) {
		if (StringUtils.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.PARENT_MENU_ID)
				&& StringUtils.isNotEmpty(paramsObj.getString(GenConstants.PARENT_MENU_ID))) {
			return paramsObj.getString(GenConstants.PARENT_MENU_ID);
		}
		return DEFAULT_PARENT_MENU_ID;
	}

	/**
	 * 获取树编码
	 * @param paramsObj 生成其他选项
	 * @return 树编码
	 */
	public static String getTreeCode(JSONObject paramsObj) {
		if (paramsObj.containsKey(GenConstants.TREE_CODE)) {
			return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE));
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 获取树父编码
	 * @param paramsObj 生成其他选项
	 * @return 树父编码
	 */
	public static String getTreeParentCode(JSONObject paramsObj) {
		if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
			return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE));
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 获取树名称
	 * @param paramsObj 生成其他选项
	 * @return 树名称
	 */
	public static String getTreeName(JSONObject paramsObj) {
		if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
			return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME));
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 获取需要在哪一列上面显示展开按钮
	 * @param genTable 业务表对象
	 * @return 展开按钮列序号
	 */
	public static int getExpandColumn(GenTable genTable) {
		String options = genTable.getOptions();
		JSONObject paramsObj = JSON.parseObject(options);
		String treeName = paramsObj.getString(GenConstants.TREE_NAME);
		int num = 0;
		for (GenTableColumn column : genTable.getColumns()) {
			if (StringUtils.isNotNull(column.getIsList())) {
				num ++;
				String columnName = column.getColumnName();
				if (columnName.equals(treeName)) {
					break;
				}
			}
		}
		return num;
	}

}
