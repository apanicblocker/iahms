package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Alias("HotelTag")
@Schema(title = "用户")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelTag extends BaseEntity {

	/** 标签ID */
	private Long tagId;

	/** 标签名 */
	private String label;

	/** 标签颜色 */
	private String color;
}
