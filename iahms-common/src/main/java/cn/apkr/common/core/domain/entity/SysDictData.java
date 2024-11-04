package cn.apkr.common.core.domain.entity;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;

@Alias("SysDictData")
@Schema(title = "字典数据")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictData extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 字典编码 */
	@Schema(title = "字典编码")
	private Long dictCode;

	/** 字典排序 */
	@Schema(title = "字典排序")
	private String dictSort;

	/** 字典标签 */
	@Schema(title = "字典标签")
	@NotBlank(message = "字典标签不能为空")
	@Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
	private String dictLabel;

	/** 字典键值 */
	@Schema(title = "字典键值")
	@NotBlank(message = "字典键值不能为空")
	@Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
	private String dictValue;

	/** 字典类型 */
	@Schema(title = "字典类型")
	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
	private String dictType;

	/** 是否默认 */
	@Schema(title = "是否默认")
	private Boolean isDefault;

	/** 状态 */
	@Schema(title = "状态")
	private Short status;

}
