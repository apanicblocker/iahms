package cn.apkr.hotel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 房态记录对象 hotel_room_status_record
 *
 * @author apkr
 * @date 2024-12-06
 */
@Schema(title = "房态记录")
@Alias("HotelRoomStatusRecord")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelRoomStatusRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 房态记录ID */
    @Schema(title = "房态记录ID")
    private Long roomStatusRecordId;

    /** 房间ID */
    @Schema(title = "房间ID")
    private Long roomId;

    /** 房间状态 */
    @Schema(title = "房间状态")
    private Integer roomStatus;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "开始时间")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "结束时间")
    private Date endTime;

}