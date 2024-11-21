package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

@Schema(title = "房间类型表")
@Alias("HotelRoomType")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomType extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 房间类型ID */
	@Schema(title = "房间类型ID")
	private Long roomTypeId;

	/** 房间类型名称 */
	@Schema(title = "房间类型名称")
	private String roomName;

	/** 房间类型昵称 */
	@Schema(title = "房间类型昵称")
	private String nickName;

	/** 房间类型数量 */
	@Schema(title = "房间类型数量")
	private Integer quantity;

	/** 房间类型描述 */
	@Schema(title = "房间类型描述")
	private String description;

	// 属于该房型的房间信息
	@Schema(title = "属于该房型的房间信息")
	private List<HotelRoom> rooms;
}
