package cn.apkr.common.core.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Schema(title = "基类")
public class BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	// 不想做微服务了
//	/** 租户号 */
//	@Schema(title = "租户号")
//	@JsonIgnore
//	private Long tenantId;

	/** 乐观锁 */
	@Schema(title = "乐观锁")
	@JsonIgnore
	private Integer revision;

	/** 创建人 */
	@Schema(title = "创建者")
	private Long createdBy;

	/** 创建时间 */
	@Schema(title = "创建时间")
//	@JSONField(serialize = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;

	/** 更新人 */
	@Schema(title = "更新者")
	private Long updatedBy;

	/** 更新时间 */
	@Schema(title = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp updatedTime;

	/** 备注 */
	@Schema(title = "备注")
	private String remark;
}

