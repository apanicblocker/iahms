package cn.apkr.framework.manager.factory;

import cn.apkr.common.constant.Constants;
import cn.apkr.common.utils.ServletUtils;
import cn.apkr.common.utils.SpringUtils;
import cn.apkr.common.utils.StringUtils;
import cn.apkr.common.utils.ip.AddressUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class AsyncFactory {

	private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

	public static TimerTask recordLoginInfo(final String username, final String status,
											final String message, final Object... args) {
		final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
		final String ip = ServletUtils.getRequest().getRemoteAddr();
		return new TimerTask() {
			@Override
			public void run() {
				String address = AddressUtils.getRealAddressByIP(ip);
				StringBuilder s = new StringBuilder();
				// TODO: 封装日志信息(LogUtils待完善)
				// 打印信息到日志
				sys_user_logger.info(s.toString(), args);
				// 获取客户端操作系统
				String os = userAgent.getOperatingSystem().getName();
				// 获取客户端浏览器
				String browser = userAgent.getBrowser().getName();
				// TODO: 封装对象(SysLoginInfo对象目前还没写）
				// TODO: 设置日志状态
				// TODO: 插入数据(到数据库）
			}
		};
	}
}
