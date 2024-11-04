package cn.apkr.common.core.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Schema(title = "表格分页数据对象")
@Data
public class TableDataInfo implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 总记录数 */
	@Schema(title = "总记录数")
	private long total;

	/** 列表数据 */
	@Schema(title = "列表数据")
	private List<?> rows;

	/** 消息状态码 */
	@Schema(title = "消息状态码")
	private int code;

	/** 消息内容 */
	@Schema(title = "消息内容")
	private String msg;

	/**
	 * 表格数据对象
	 */
	public TableDataInfo() {
	}

	/**
	 * 分页
	 * @param list 列表数据
	 * @param total 总记录数
	 */
	public TableDataInfo(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}
}
