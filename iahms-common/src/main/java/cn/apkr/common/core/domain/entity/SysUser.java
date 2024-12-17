package cn.apkr.common.core.domain.entity;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.util.List;

@Alias("SysUser")
@Schema(title = "用户")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 用户名;登录用 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 密码 */
//    @JSONField(serialize = false)
    private String password;

    /** 性别 */
    private Short gender;

    /** 头像 */
    private String avatar;

    /** 电话号码 */
    private String phoneNumber;

    /** 启用状态 */
    private Boolean status;

    /** 角色组 */
    private List<SysRole> roles;

    /** 角色对戏 */
    private Long[] roleIds;

    public boolean isAdmin() {
        return isAdmin(this.userId);
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