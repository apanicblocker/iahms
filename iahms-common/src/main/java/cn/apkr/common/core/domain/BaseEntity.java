package cn.apkr.common.core.domain;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Schema(title = "基类")
public class BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 删除标识 */
	@Schema(title = "删除标识")
	@JsonIgnore
	private Boolean delFlag;

	/** 备注 */
	@Schema(title = "备注")
	private String remark;

	// 不想做多租户了 T_T
	/** 租户号 */
	@Schema(title = "租户号")
	@JsonIgnore
	private Long tenantId;

	/** 乐观锁 */
	@Schema(title = "乐观锁")
	@JsonIgnore
	private Integer revision;

	/** 创建人 */
	@Schema(title = "创建者")
	@JsonIgnoreProperties
	private Long createBy;

	/** 创建时间 */
	@Schema(title = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新人 */
	@Schema(title = "更新者")
	private Long updateBy;

	/** 更新时间 */
	@Schema(title = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 请求参数 */
	@Schema(title = "请求参数")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Map<String, Object> params;

	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<>();
		}
		return params;
	}
}

