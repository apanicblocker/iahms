package cn.apkr.system.domain;

import cn.apkr.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

@Schema(title = "操作日志记录表")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOperLog extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 日志主键 */
	@Schema(title = "操作序号")
	private Long operId;

	/** 操作模块 */
	@Schema(title = "操作模块")
	private String title;

	/** 业务类型（0其它 1新增 2修改 3删除） */
	@Schema(title = "业务类型")
	private Integer businessType;

	/** 业务类型数组 */
	@Schema(title = "业务类型数组")
	private Integer[] businessTypes;

	/** 请求方法 */
	@Schema(title = "请求方法")
	private String method;

	/** 请求方式 */
	@Schema(title = "请求方式")
	private String requestMethod;

	/** 操作类别（0其它 1后台用户 2手机端用户） */
	@Schema(title = "操作类别")
	private Integer operatorType;

	/** 操作人员 */
	@Schema(title = "操作人员")
	private String operName;

	/** 请求url */
	@Schema(title = "请求地址")
	private String operUrl;

	/** 操作地址 */
	@Schema(title = "操作地址")
	private String operIp;

	/** 操作地点 */
	@Schema(title = "操作地点")
	private String operLocation;

	/** 请求参数 */
	@Schema(title = "请求参数")
	private String operParam;

	/** 返回参数 */
	@Schema(title = "返回参数")
	private String jsonResult;

	/** 操作状态（0正常 1异常） */
	@Schema(title = "操作状态")
	private Integer status;

	/** 错误消息 */
	@Schema(title = "错误消息")
	private String errorMsg;

	/** 操作时间 */
	@Schema(title = "操作时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operTime;

	/** 消耗时间 */
	@Schema(title = "消耗时间")
	private Long costTime;

}
