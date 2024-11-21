package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;

@Schema(title = "商品记录表")
@Alias("HotelProductRecord")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelProductRecord extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 商品记录ID */
	@Schema(title = "商品记录ID")
	private Long productRecordId;

	/** 订单ID */
	@Schema(title = "订单ID")
	private Long orderId;

	/** 商品ID */
	@Schema(title = "商品ID")
	private Long productId;

	/** 数量 */
	@Schema(title = "数量")
	private Integer quantity;

}
