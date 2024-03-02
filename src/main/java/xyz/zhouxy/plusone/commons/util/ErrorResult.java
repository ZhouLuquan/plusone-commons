package xyz.zhouxy.plusone.commons.util;

import java.util.Objects;

import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.base.IWithCode;

/**
 * 错误结果
 *
 * @author zhouxy
 */
final class ErrorResult extends UnifiedResponse {
    private static final String DEFAULT_ERR_STATUS = "9999999";

    ErrorResult(@Nullable String message) {
        super(DEFAULT_ERR_STATUS, message);
    }

    ErrorResult(@Nullable String message, @Nullable Object data) {
        super(DEFAULT_ERR_STATUS, message, data);
    }

    ErrorResult(Object status, @Nullable String message) {
        super(status, message);
    }

    ErrorResult(Object status, @Nullable String message, @Nullable Object data) {
        super(status, message, data);
    }

    ErrorResult(Object status, Throwable e) {
        super(status, Objects.requireNonNull(e).getMessage());
    }

    <E extends Throwable & IWithCode<?>> ErrorResult(E e) {
        super(Objects.requireNonNull(e).getCode(), Objects.requireNonNull(e).getMessage());
    }

    private static final long serialVersionUID = -1680792957826923092L;
}
