package cn.apkr.common.exception;

import java.io.Serial;

/**
 * 工具类异常
 */
public class UtilException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 8247610319171014183L;

	public UtilException(String message) {
		super(message);
	}

	public UtilException(Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}

	public UtilException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
