package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 购物记录对象 hotel_product_record
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "购物记录")
@Alias("HotelProductRecord")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelProductRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 购物记录ID */
    @Schema(title = "购物记录ID")
    private Long productRecordId;

    /** 订单ID */
    @Schema(title = "订单ID")
    private Long orderId;

    /** 商品ID */
    @Schema(title = "商品ID")
    private Long productId;

    /** 商品数量 */
    @Schema(title = "商品数量")
    private Integer quantity;

}