package cn.apkr.common.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

	OK("0", "正常"), LOCK("1", "停用"), DELETED("2", "删除");

	private final String code;
	private final String info;

	UserStatus(String code, String info)
	{
		this.code = code;
		this.info = info;
	}
}
