package cn.apkr.common.core.domain.entity;

import cn.apkr.common.core.domain.BaseEntity;
import lombok.*;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	// 角色ID
	private Long id;

	// 角色名称
	private String roleName;

	// 角色权限字符串
	private String roleKey;

	// 显示顺序
	private Integer sortNumber;

	// 数据范围
	private Short dataScope;

	// 角色状态
	private Boolean status;

	// 删除标志
	private Boolean delFlag;

	// 备注
	private String remark;

}
