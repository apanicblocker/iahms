package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 订单对象 hotel_order
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "订单")
@Alias("HotelOrder")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelOrder extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    @Schema(title = "订单ID")
    private Long orderId;

    /** 订单号 */
    @Schema(title = "订单号")
    private String number;

    /** 客户ID */
    @Schema(title = "客户ID")
    private Long customerId;

    /** 订单总金额 */
    @Schema(title = "订单总金额")
    private BigDecimal totalAmount;

    /** 订单状态 */
    @Schema(title = "订单状态")
    private Integer orderStatus;

    /** 删除标识 */
    @Schema(title = "删除标识")
    private Integer delFlag;

}