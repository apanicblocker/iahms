package cn.apkr.hotel.domain;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.List;

@Alias("HotelCustomer")
@Schema(title = "客户")
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelCustomer extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 客户ID */
	@Schema(title = "客户ID")
	private Long customerId;

	/** 客源 */
	@Schema(title = "客源")
	private Short origin;

	/** 姓名;真实姓名 */
	@Schema(title = "姓名")
	private String name;

	/** 身份证号 */
	@Schema(title = "身份证号")
	private String idNumber;

	/** 电话号码 */
	@Schema(title = "电话号码")
	private String phoneNumber;

	/** 性别 */
	@Schema(title = "性别")
	private Short gender;

	/** 生日 */
	@Schema(title = "生日")
	private Timestamp birthday;

	/** 地区 */
	@Schema(title = "地区")
	private String region;

	/** 邮箱 */
	@Schema(title = "邮箱")
	private String email;

	/** 入住次数 */
	@Schema(title = "入住次数")
	private Integer checkinTimes;

	/** 删除标志 */
	@Schema(title = "删除标志")
	private Boolean delFlag;

	/** 标签对象 */
	@Schema(title = "标签对象")
	private List<HotelTag> tags;

	/** 标签组 */
	@Schema(title = "标签组")
	private Long[] tagIds;

}