package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 支付记录对象 hotel_pay_record
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "支付记录")
@Alias("HotelPayRecord")
@EqualsAndHashCode(callSuper = true)
@Data
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
    private Integer payType;

    /** 支付方式 */
    @Schema(title = "支付方式")
    private Integer payMethod;

    /** 支付金额 */
    @Schema(title = "支付金额")
    private BigDecimal amount;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "支付时间")
    private Date payTime;

}