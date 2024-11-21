package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;

@Schema(title = "商品信息表")
@Alias("HotelProduct")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelProduct extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 商品ID */
	@Schema(title = "商品ID")
	private Long productId;

	/** 商品类型ID */
	@Schema(title = "商品类型ID")
	private Long productTypeId;

	/** 商品名称 */
	@Schema(title = "商品名称")
	private String name;

	/** 商品价格 */
	@Schema(title = "商品价格")
	private BigDecimal price;

	/** 库存 */
	@Schema(title = "库存")
	private Integer inventory;

	/** 商品描述 */
	@Schema(title = "商品描述")
	private String description;

	/** 删除标识 */
	@Schema(title = "删除标识")
	private Boolean delFlag;
}
