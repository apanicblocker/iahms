package cn.apkr.hotel.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Schema(title = "客户标签关联表")
@Alias("HotelTagCustomer")
@Data
public class HotelTagCustomer {

	/** 标签ID */
	@Schema(title = "标签ID")
	private Long tagId;

	/** 客户ID */
	@Schema(title = "客户ID")
	private Long customerId;

}

