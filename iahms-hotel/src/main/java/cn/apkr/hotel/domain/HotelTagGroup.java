package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * 标签组对象 hotel_tag_group
 *
 * @author apkr
 * @date 2024-12-21
 */
@Schema(title = "标签组")
@Alias("HotelTagGroup")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelTagGroup extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 标签组ID */
    @Schema(title = "标签组ID")
    private Long tagGroupId;

    /** 标签组名 */
    @NotBlank(message = "标签组名称不能为空")
    @Schema(title = "标签组名称")
    private String name;

    /** 创建人名称 */
    @Schema(title = "创建人名称")
    private String createName;

    /** 关联标签数量 */
    @NotNull(message = "关联标签数量不能为空")
    @Min(value = 0, message = "关联标签数量不能小于0")
    @Schema(title = "关联标签数量")
    private Integer relTagNum;

}