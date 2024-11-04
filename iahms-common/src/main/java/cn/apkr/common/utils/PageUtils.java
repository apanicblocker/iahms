package cn.apkr.common.utils;

import cn.apkr.common.core.page.PageDomain;
import cn.apkr.common.core.page.TableSupport;
import cn.apkr.common.utils.sql.SqlUtils;
import com.github.pagehelper.PageHelper;

public class PageUtils extends PageHelper {

	public static void startPage() {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
		Boolean reasonable = pageDomain.getReasonable();

	}

}
