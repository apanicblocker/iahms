package cn.apkr.hotel.domain;

import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 入住记录&客户对象 hotel_checkin_customer_link
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "入住记录&客户")
@Alias("HotelCheckinCustomerLink")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelCheckinCustomerLink extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    @Schema(title = "客户ID")
    private Long customerId;

    /** 入住记录ID */
    @Schema(title = "入住记录ID")
    private Long checkinRecordId;

}