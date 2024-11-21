package cn.apkr.common.exception.user;

import cn.apkr.common.exception.base.BaseException;

import java.io.Serial;

public class UserException extends BaseException {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserException(String code, Object[] args) {
		super("user", code, args, null);
	}
}
