package cn.apkr.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
public final class ServiceException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private Integer code;

	/**
	 * 错误提示
	 */
	@Setter
	private String message;

	/**
	 * 错误明细，内部调试错误
	 */
	@Setter
	private String detailMessage;

	public ServiceException() {
	}

	public ServiceException(String message) {
		this.message = message;
	}

	public ServiceException(String message, Integer code) {
		this.message = message;
		this.code = code;
	}
}
