package cn.apkr.common.exception.base;

import cn.apkr.common.utils.MessageUtils;
import cn.apkr.common.utils.StringUtils;
import lombok.Getter;

import java.io.Serial;

@Getter
public class BaseException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	private final String module;

	private final String code;

	private final Object[] args;

	private final String defaultMessage;

	public BaseException(String module, String code, Object[] args, String defaultMessage) {
		this.module = module;
		this.code = code;
		this.args = args;
		this.defaultMessage = defaultMessage;
	}

	public BaseException(String module, String code, Object[] args) {
		this(module, code, args, null);
	}

	public BaseException(String module, String defaultMessage) {
		this(module, null, null, defaultMessage);
	}

	public BaseException(String code, Object[] args) {
		this(null, code, args, null);
	}

	public BaseException(String defaultMessage) {
		this(null, null, null, defaultMessage);
	}

	public String getMessage() {
		String message = null;
		if (!StringUtils.isEmpty(code)) {
			message = MessageUtils.message(code, args);
		}
		if (message == null) {
			message = defaultMessage;
		}
		return message;
	}

}
