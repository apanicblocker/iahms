package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 房间对象 hotel_room
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "房间")
@Alias("HotelRoom")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelRoom extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 房间ID */
    @Schema(title = "房间ID")
    private Long roomId;

    /** 房型ID */
    @Schema(title = "房型ID")
    private Long roomTypeId;

    /** 标准房费 */
    @Schema(title = "标准房费")
    private BigDecimal price;

    /** 房间编号 */
    @Schema(title = "房间编号")
    private String number;

    /** 是否脏房 */
    @Schema(title = "是否脏房")
    private Integer dirtyFlag;

    /** 删除标识 */
    @Schema(title = "删除标识")
    private Integer delFlag;

}