package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.util.Date;

@Schema(title = "房间状态表")
@Alias("HotelRoomState")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomStateRecord extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 房间状态记录ID */
	@Schema(title = "房间状态记录ID")
	private Long roomStateRecordId;

	/** 房间ID */
	@Schema(title = "房间ID")
	private Long roomId;

	/** 房间状态 */
	@Schema(title = "房间状态")
	private Short roomState;

	/** 状态开始时间 */
	@Schema(title = "状态开始时间")
	private Date startTime;

	/** 状态结束时间 */
	@Schema(title = "状态结束时间")
	private Date endTime;

}
