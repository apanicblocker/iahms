package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

@Alias("HotelOrder")
@Schema(title = "订单信息表")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelOrder extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 订单ID */
	@Schema(title = "订单ID")
	private Long orderId;

	/** 订单编号;用于展示 */
	@Schema(title = "订单编号")
	private String number;

	/** 客户ID */
	@Schema(title = "客户ID")
	private String customerId;

	/** 总金额 */
	@Schema(title = "总金额")
	private BigDecimal totalAmount;

	/** 订单状态 */
	@Schema(title = "订单状态")
	private String orderStatus;

	/** 删除标识 */
	@Schema(title = "删除标识")
	private String delFlag;

	// 入住记录
	@Schema(title = "入住记录")
	private List<HotelCheckinRecord> checkinRecords;

	// 购物记录
	@Schema(title = "购物记录")
	private List<HotelProductRecord> productRecords;

}
