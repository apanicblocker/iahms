package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;

@Alias("HotelOrder")
@Schema(title = "订单信息表")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelOrder extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 订单ID */
	private Long orderId;

	/** 订单编号;用于展示 */
	private String number;

	/** 客户ID */
	private String customerId;

	/** 总金额 */
	private BigDecimal totalAmount;

	/** 订单状态 */
	private String orderStatus;

	/** 删除标志 */
	private String del_flag;
}
