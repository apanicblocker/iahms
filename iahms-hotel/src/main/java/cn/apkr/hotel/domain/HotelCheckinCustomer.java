package cn.apkr.hotel.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("HotelCheckinCustomer")
@Schema(title = "客户和入住记录关联")
@Data
public class HotelCheckinCustomer {

	/** 入住记录ID */
	@Schema(title = "入住记录ID")
	private Long checkinRecordId;

	/** 客户ID */
	@Schema(title = "客户ID")
	private Long customerId;

}
