package cn.apkr.hotel.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.util.Set;

import cn.apkr.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * 客户对象 hotel_customer
 *
 * @author apkr
 * @date 2024-12-09
 */
@Schema(title = "客户")
@Alias("HotelCustomer")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelCustomer extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    @Schema(title = "客户ID")
    private Long customerId;

    /** 客源 */
    @Schema(title = "客源")
    private Integer origin;

    /** 姓名;真实姓名 */
    @Schema(title = "姓名;真实姓名")
    private String name;

    /** 身份证号 */
    @Schema(title = "身份证号")
    private String idNumber;

    /** 电话号码 */
    @Schema(title = "电话号码")
    private String phoneNumber;

    /** 性别 */
    @Schema(title = "性别")
    private Integer gender;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "生日")
    private Date birthday;

    /** 地区 */
    @Schema(title = "地区")
    private String region;

    /** 邮箱 */
    @Schema(title = "邮箱")
    private String email;

    /** 入住次数 */
    @Schema(title = "入住次数")
    private Integer checkinTimes;

    /** 总消费金额 */
    @Schema(title = "总消费金额")
    private BigDecimal totalSpent;

    /** 最近消费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "最近消费时间")
    private Timestamp lastSpentTime;

    /** 黑名单标识 */
    @Schema(title = "黑名单标识")
    private Boolean blackFlag;

    // 标签ID数组
    @Schema(title = "标签ID数组")
    private Long[] tagIds;

    // 标签列表
    @Schema(title = "标签列表")
    private Set<HotelTag> tags;

}