package cn.apkr.common.exception.user;

import java.io.Serial;

public class IpRetryLimitExceedException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public IpRetryLimitExceedException(int retryLimitCount, int lockTime) {
		super("失败次数过多，你的ip暂时被限制登录.");
	}
}
