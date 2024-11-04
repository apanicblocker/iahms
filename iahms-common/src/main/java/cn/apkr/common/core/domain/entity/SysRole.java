package cn.apkr.common.core.domain.entity;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serial;

@Alias("SysRole")
@Schema(title = "角色")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	// 角色ID
	@Schema(title = "角色ID")
	private Long roleId;

	// 角色名称
	@Schema(title = "角色名称")
	private String roleName;

	// 角色权限字符串
	@Schema(title = "角色权限字符串")
	private String roleKey;

	// 显示顺序
	@Schema(title = "显示顺序")
	private Integer sortNumber;

	// 数据范围(准备遗弃，因为ruoyi是为了部门而设计这个变量，我们只做全局)
	@Schema(title = "数据范围")
	private Short dataScope;

	// 角色状态
	@Schema(title = "角色状态")
	private Boolean status;

}
