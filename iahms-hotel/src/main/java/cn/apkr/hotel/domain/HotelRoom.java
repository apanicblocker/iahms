package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

@Schema(title = "房间信息表")
@Alias("HotelRoom")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoom extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 房间ID */
	@Schema(title = "房间ID")
	private Long roomId;

	/** 房间类型ID */
	@Schema(title = "房间类型ID")
	private Long roomTypeId;

	/** 房间编号 */
	@Schema(title = "房间编号")
	private String number;

	/** 标准价格 */
	@Schema(title = "标准价格")
	private BigDecimal price;

	/** 脏房标识 */
	@Schema(title = "脏房标识")
	private Boolean dirtyFlag;

	/** 删除标识 */
	@Schema(title = "删除标识")
	private Boolean delFlag;
}
