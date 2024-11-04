package cn.apkr.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Deprecated
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T date;

    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <E> Result<E> success() {
        return new Result<>(0, "操作成功", null);
    }

    public static <E> Result<E> error(String message) {
        return new Result<>(1, message, null);
    }
}
