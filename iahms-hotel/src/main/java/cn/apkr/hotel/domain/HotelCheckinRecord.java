package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * 入住记录对象 hotel_checkin_record
 *
 * @author apkr
 * @date 2024-12-16
 */
@Schema(title = "入住记录")
@Alias("HotelCheckinRecord")
@ToString(callSuper = true)
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

    /** 客户ID;主要的开房客人（出问题先找他） */
    @Schema(title = "客户ID;主要的开房客人（出问题先找他）")
    private Long customerId;

    /** 房费 */
    @Schema(title = "房费")
    private BigDecimal roomAmount;

    /** 入住类型 */
    @Schema(title = "入住类型")
    private String checkinType;

    /** 入住状态 */
    @Schema(title = "入住状态")
    private String checkinStatus;

    /** 预计入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "预计入住时间")
    private Date preCheckinTime;

    /** 实际入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "实际入住时间")
    private Date actCheckinTime;

    /** 预计退房时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "预计退房时间")
    private Date preCheckoutTime;

    /** 实际退房时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "实际退房时间")
    private Date actCheckoutTime;

}