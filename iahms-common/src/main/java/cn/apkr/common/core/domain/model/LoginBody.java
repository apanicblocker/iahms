package cn.apkr.common.core.domain.model;

import cn.apkr.common.constant.UserConstants;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class LoginBody {

    /** 用户名 */
//    @NotBlank
    @Pattern(regexp = "^\\S{" + UserConstants.USERNAME_MIN_LENGTH + "," + UserConstants.USERNAME_MAX_LENGTH + "}$")
    private String username;

    /** 用户密码 */
    private String password;

    /** 验证码 */
    private String code;

    /** 唯一标识 */
    private String uuid;

}
