package cn.apkr.common.exception.user;

import java.io.Serial;

public class UserPasswordNotMatchException extends UserException{

	@Serial
	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException() {
		super("user.password.not.match", null);
	}
}
