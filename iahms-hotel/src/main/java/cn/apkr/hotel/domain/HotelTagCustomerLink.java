package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 标签&客户对象 hotel_tag_customer_link
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "标签&客户")
@Alias("HotelTagCustomerLink")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelTagCustomerLink extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    @Schema(title = "标签ID")
    private Long tagId;

    /** 客户ID */
    @Schema(title = "客户ID")
    private Long customerId;

}