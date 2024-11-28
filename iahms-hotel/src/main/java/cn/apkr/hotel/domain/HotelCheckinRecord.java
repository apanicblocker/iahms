package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Schema(title = "入住记录表")
@Alias("HotelCheckinRecord")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelCheckinRecord extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 入住记录ID */
	@Schema(title = "入住记录ID")
	private Long checkinRecordId;

	/** 订单ID */
	@Schema(title = "订单ID")
	private Long orderId;

	/** 房间ID */
	@Schema(title = "房间ID")
	private Long roomId;

	/** 客户ID */
	@Schema(title = "客户ID")
	private Long customerId;

	/** 房费 */
	@Schema(title = "房费")
	private BigDecimal roomAmount;

	/** 入住类型 */
	@Schema(title = "入住类型")
	private Short checkinType;

	/** 入住状态 */
	@Schema(title = "入住状态")
	private Short checkinStatus;

	/** 预计入住时间 */
	@Schema(title = "预计入住时间")
	private Date preCheckinTime;

	/** 实际入住时间 */
	@Schema(title = "实际入住时间")
	private Date actCheckinTime;

	/** 预计离店时间 */
	@Schema(title = "预计离店时间")
	private Date preCheckoutTime;

	/** 实际离店时间 */
	@Schema(title = "实际离店时间")
	private Date actCheckoutTime;

	/** 删除标识 */
	@Schema(title = "删除标识")
	private Boolean delFlag;

	// 该次开房记录的入住房间信息
	@Schema(title = "入住房间信息")
	private HotelRoom room;

	// 该次开房记录居住的所有客户信息
	@Schema(title = "居住的客户信息列表")
	private List<HotelCustomer> customers;

}
