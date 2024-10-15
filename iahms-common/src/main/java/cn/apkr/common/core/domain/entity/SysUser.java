package cn.apkr.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户",description = "")
public class SysUser {
    /** 用户ID */
    @ApiModelProperty(name = "用户ID",notes = "")
    private long id;
    /** 用户名;登录用 */
    @ApiModelProperty(name = "用户名",notes = "登录用")
    private String username;
    /** 昵称 */
    @ApiModelProperty(name = "昵称",notes = "")
    private String nickname;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    private String password;
    /** 性别 */
    @ApiModelProperty(name = "性别",notes = "")
    private Short gender;
    /** 头像 */
    @ApiModelProperty(name = "头像",notes = "")
    private String avatar;
    /** 电话号码 */
    @ApiModelProperty(name = "电话号码",notes = "")
    private String phoneNumber ;
    /** 启用状态 */
    @ApiModelProperty(name = "启用状态",notes = "")
    private Boolean status;
    /** 删除标识 */
    @ApiModelProperty(name = "删除标识",notes = "")
    private Boolean delFlag;
    /** 租户号 */
    @ApiModelProperty(name = "租户号",notes = "")
    private long tenantId;
    /** 乐观锁 */
    @ApiModelProperty(name = "乐观锁",notes = "")
    private Integer revision;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    private long createdBy;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Timestamp createdTime;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    private long updatedBy;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    private Timestamp updatedTime;
}