package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 标签对象 hotel_tag
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "标签")
@Alias("HotelTag")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelTag extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    @Schema(title = "标签ID")
    private Long tagId;

    /** 标签文本 */
    @Schema(title = "标签文本")
    private String label;

    /** 标签颜色 */
    @Schema(title = "标签颜色")
    private String color;

}