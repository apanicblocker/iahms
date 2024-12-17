package cn.apkr.generator.domain;

import cn.apkr.common.constant.GenConstants;
import cn.apkr.common.core.domain.BaseEntity;
import cn.apkr.common.utils.StringUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.util.Arrays;

@Alias("GenTableColumn")
@Data
@EqualsAndHashCode(callSuper = true)
public class GenTableColumn extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long columnId;

	private Long tableId;

	private String columnName;

	private String columnComment;

	private String columnType;

	private String javaType;

	@NotBlank(message = "Java属性不能为空")
	private String javaField;

	private Boolean isPk;

	private Boolean isIncrement;

	private Boolean isRequired;

	private Boolean isInsert;

	private Boolean isEdit;

	private Boolean isList;

	private Boolean isQuery;

	private String queryType;

	private String htmlType;

	private String dictType;

	private Integer sort;

	public String getCapJavaField() {
		return StringUtils.capitalize(javaField);
	}

	public boolean isSuperColumn() {
		return isSuperColumn(this.javaField);
	}

	public static boolean isSuperColumn(String javaField) {
		return StringUtils.equalsAnyIgnoreCase(javaField,
				ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
	}

	public boolean isUsableColumn() {
		return isUsableColumn(javaField);
	}

	public static boolean isUsableColumn(String javaField) {
		return StringUtils.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
	}

	public String readConverterExp() {
		String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(remarks)) {
			for (String value : remarks.split(" ")) {
				if (StringUtils.isNotEmpty(value)) {
					CharSequence startStr = value.subSequence(0, 1);
					String endStr = value.substring(1);
					sb.append(startStr).append("=").append(endStr).append(",");
				}
			}
			return sb.deleteCharAt(sb.length() - 1).toString();
		} else {
			return this.columnComment;
		}
	}

}
