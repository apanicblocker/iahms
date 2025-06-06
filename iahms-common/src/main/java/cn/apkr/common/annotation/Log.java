package cn.apkr.common.annotation;

import cn.apkr.common.enums.BusinessType;
import cn.apkr.common.enums.OperatorType;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	/**
	 * 模块
	 */
	String title() default "";

	/**
	 * 功能
	 */
	BusinessType businessType() default BusinessType.OTHER;

	/**
	 * 操作人类型
	 */
	OperatorType operatorType() default OperatorType.MANAGE;

	/**
	 * 是否保存请求的参数
	 */
	boolean isSaveRequestData() default true;

	/**
	 * 是否保存响应的参数
	 */
	boolean isSaveResponseData() default true;

	/**
	 * 排除指定的请求参数
	 */
	String[] excludeParamNames() default {};

}
