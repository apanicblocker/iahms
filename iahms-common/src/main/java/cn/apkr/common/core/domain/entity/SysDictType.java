package cn.apkr.common.core.domain.entity;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;

@Alias("SysDictType")
@Schema(title = "字典类型")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictType extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 字典主键 */
	@Schema(title = "字典主键")
	private Long dictId;

	/** 字典名称 */
	@Schema(title = "字典名称")
	@NotBlank(message = "字典名称不能为空")
	@Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
	private String dictName;

	/** 字典类型 */
	@Schema(title = "字典类型")
	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
	@Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
	private String dictType;

	/** 状态 */
	@Schema(title = "状态")
	private String status;

}
