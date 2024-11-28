package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;

@Schema(title = "支付记录")
@Alias("HotelPayRecord")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelPayRecord extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 支付记录ID */
	@Schema(title = "支付记录ID")
	private Long payRecordId;

	/** 客户ID */
	@Schema(title = "客户ID")
	private Long customerId;

	/** 订单ID */
	@Schema(title = "订单ID")
	private Long orderId;

	/** 支付类型 */
	@Schema(title = "支付类型")
	private Short payType;

	/** 支付方式 */
	@Schema(title = "支付方式/支付渠道")
	private Short payMethod;

	/** 支付金额 */
	@Schema(title = "支付金额")
	private BigDecimal amount;

	// 支付的客户信息
	@Schema(title = "支付的客户信息")
	private HotelCustomer customer;
}
