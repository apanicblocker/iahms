package cn.apkr.common.core.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Schema(title = "基类")
public class BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 创建者 */
	@Schema(title = "创建者")
	private String createBy;

	/** 创建时间 */
	@Schema(title = "创建时间")
	@JSONField(serialize = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新者 */
	@Schema(title = "更新者")
	private String updateBy;

	/** 更新时间 */
	@Schema(title = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 备注 */
	@Schema(title = "备注")
	private String remark;
}
