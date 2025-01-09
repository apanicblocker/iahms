package cn.apkr.common.exception.user;

import java.io.Serial;

public class UserPasswordRetryLimitExceedException extends UserException {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserPasswordRetryLimitExceedException(int retryLimitCount, int lockTime) {
		super("user.password.retry.limit.exceed", new Object[] { retryLimitCount, lockTime });
	}
}
