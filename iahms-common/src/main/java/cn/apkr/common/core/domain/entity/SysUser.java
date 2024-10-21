package cn.apkr.common.core.domain.entity;

import cn.apkr.common.core.domain.BaseEntity;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.*;

import java.io.Serial;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private long id;

    /** 用户名;登录用 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 密码 */
    @JSONField(serialize = false)
    private String password;

    /** 性别 */
    private Short gender;

    /** 头像 */
    private String avatar;

    /** 电话号码 */
    private String phoneNumber;

    /** 启用状态 */
    private Boolean status;

    /** 删除标识 */
    private Boolean delFlag;

    /** 租户号 */
    private long tenantId;

    /** 乐观锁 */
    private Integer revision;

    /** 创建人 */
    private long createdBy;

    /** 创建时间 */
    private Timestamp createdTime;

    /** 更新人 */
    private long updatedBy;

    /** 更新时间 */
    private Timestamp updatedTime;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    /**
     * 默认UserId为1的为管理员
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}