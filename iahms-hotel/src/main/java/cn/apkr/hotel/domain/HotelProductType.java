package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 商品类型对象 hotel_product_type
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "商品类型")
@Alias("HotelProductType")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelProductType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 商品类型ID */
    @Schema(title = "商品类型ID")
    private Long productTypeId;

    /** 类型名称 */
    @Schema(title = "类型名称")
    private String name;

    /** 类型描述 */
    @Schema(title = "类型描述")
    private String description;

}