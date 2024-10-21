package cn.apkr.common.annotation;

public @interface RepeatSubmit {

	/**
	 * 间隔时间（ms），小于此时间视为重复提交
	 */
	int interval() default 5000;

	/**
	 * 提示消息
	 */
	String message() default "不允许重复提交，请稍后再试";
}
