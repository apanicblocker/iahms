package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 商品对象 hotel_product
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "商品")
@Alias("HotelProduct")
@EqualsAndHashCode(callSuper = true)
@Data
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

    /** 价格 */
    @Schema(title = "价格")
    private BigDecimal price;

    /** 库存 */
    @Schema(title = "库存")
    private Integer inventory;

    /** 商品描述 */
    @Schema(title = "商品描述")
    private String description;

    /** 删除标识 */
    @Schema(title = "删除标识")
    private Integer delFlag;

}