package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * 标签对象 hotel_tag
 *
 * @author apkr
 * @date 2024-12-21
 */
@Schema(title = "标签")
@Alias("HotelTag")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelTag extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    @Schema(title = "标签ID")
    private Long tagId;

    /** 标签组ID */
    @NotNull(message = "标签组ID不能为空")
    @Min(value = 0, message = "标签组ID不能小于0")
    @Schema(title = "标签组ID")
    private Long tagGroupId;

    /** 标签文本 */
    @NotBlank(message = "标签文本不能为空")
    @Schema(title = "标签文本")
    private String label;

    /** 标签颜色 */
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "标签颜色格式不正确")
    @Schema(title = "标签颜色")
    private String color;

    /** 创建人名称 */
    @Schema(title = "创建人名称")
    private String createName;

    /** 关联客户数量 */
    @NotNull(message = "关联客户数量不能为空")
    @Min(value = 0, message = "关联客户数量不能小于0")
    @Schema(title = "关联客户数量")
    private Integer relCusNum;

}