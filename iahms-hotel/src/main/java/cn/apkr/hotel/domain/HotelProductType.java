package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.util.List;

@Schema(title = "商品类型表")
@Alias("HotelProductType")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelProductType extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 商品类型主键 */
	@Schema(title = "商品类型主键")
	private Long productTypeId;

	/** 商品类型名称 */
	@Schema(title = "商品类型名称")
	private String name;

	/** 商品类型描述 */
	@Schema(title = "商品类型描述")
	private String description;

	// 属于该类型的商品信息
	@Schema(title = "属于该商品类型的商品信息")
	private List<HotelProduct> products;
}
