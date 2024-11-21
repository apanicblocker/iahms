package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;

@Alias("HotelTag")
@Schema(title = "用户")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelTag extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 标签ID */
	@Schema(title = "标签ID")
	private Long tagId;

	/** 标签名 */
	@Schema(title = "标签名")
	private String label;

	/** 标签颜色 */
	@Schema(title = "标签颜色")
	private String color;
}
