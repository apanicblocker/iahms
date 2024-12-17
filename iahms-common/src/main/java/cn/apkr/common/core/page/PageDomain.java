package cn.apkr.common.core.page;

import cn.apkr.common.utils.StringUtils;
import lombok.Data;

@Data
public class PageDomain {

	private Integer pageNum;

	private Integer pageSize;

	private String orderByColumn;

	private String isAsc = "asc";

	private Boolean reasonable = true;

	public String getOrderBy() {
		if (StringUtils.isEmpty(orderByColumn)) {
			return "";
		}
		return StringUtils.camelCase2lowUnderscore(orderByColumn) + " " + isAsc;
	}

	public void setIsAsc(String isAsc)
	{
		if (StringUtils.isNotEmpty(isAsc))
		{
			// 兼容前端排序类型
			if ("ascending".equals(isAsc))
			{
				isAsc = "asc";
			}
			else if ("descending".equals(isAsc))
			{
				isAsc = "desc";
			}
			this.isAsc = isAsc;
		}
	}

	public Boolean getReasonable()
	{
		if (StringUtils.isNull(reasonable))
		{
			return Boolean.TRUE;
		}
		return reasonable;
	}

}
