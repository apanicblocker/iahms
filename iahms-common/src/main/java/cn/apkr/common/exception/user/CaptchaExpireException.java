package cn.apkr.common.exception.user;

import java.io.Serial;

public class CaptchaExpireException extends UserException {

	@Serial
	private static final long serialVersionUID = 1L;

	public CaptchaExpireException() {
		super("user.jcaptcha.expire", null);
	}
}
