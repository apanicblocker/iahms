package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * 房型对象 hotel_room_type
 *
 * @author apkr
 * @date 2024-12-16
 */
@Schema(title = "房型")
@Alias("HotelRoomType")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class HotelRoomType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 房型ID */
    @Schema(title = "房型ID")
    private Long roomTypeId;

    /** 房型名称 */
    @Schema(title = "房型名称")
    private String roomName;

    /** 房型别名 */
    @Schema(title = "房型别名")
    private String nickName;

    /** 房型标价 */
    @Schema(title = "房型标价")
    private BigDecimal price;

    /** 房型数量 */
    @Schema(title = "房型数量")
    private Integer quantity;

    /** 房型描述 */
    @Schema(title = "房型描述")
    private String description;

}