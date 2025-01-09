package cn.apkr.common.core.domain.model;

import cn.apkr.common.constant.UserConstants;
import cn.apkr.common.utils.MessageUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@Schema(title = "用户登录对象")
public class LoginBody {

    /** 用户名 */
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\S{" + UserConstants.USERNAME_MIN_LENGTH + "," + UserConstants.USERNAME_MAX_LENGTH + "}$",
            message = "用户不存在/密码错误")
    @Schema(title = "用户名")
    private String username;

    /** 用户密码 */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^\\S{" + UserConstants.PASSWORD_MIN_LENGTH + "," + UserConstants.PASSWORD_MAX_LENGTH + "}$",
            message = "用户不存在/密码错误")
    @Schema(title = "用户密码")
    private String password;

    /** 验证码 */
    @Schema(title = "验证码")
    private String code;

    /** 唯一标识 */
    @Schema(title = "唯一标识")
    private String uuid;

}
