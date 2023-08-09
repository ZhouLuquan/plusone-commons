package xyz.zhouxy.plusone.commons.util;

import com.google.common.base.Strings;

import xyz.zhouxy.plusone.commons.base.IWithCode;

/**
 * 错误结果
 *
 * @author zhouxy
 */
final class ErrorResult extends UnifiedResponse {
    private static final String DEFAULT_ERR_STATUS = "9999999";

    ErrorResult(String message) {
        super(DEFAULT_ERR_STATUS, message);
    }

    ErrorResult(String message, Object data) {
        super(DEFAULT_ERR_STATUS, message, data);
    }

    ErrorResult(Object status, String message) {
        super(status, message);
    }

    ErrorResult(Object status, String message, Object data) {
        super(status, message, data);
    }

    ErrorResult(Object status, Throwable e) {
        super(status, Strings.nullToEmpty(e.getMessage()));
    }

    <E extends Throwable & IWithCode<?>> ErrorResult(E e) {
        super(e.getCode(), Strings.nullToEmpty(e.getMessage()));
    }

    private static final long serialVersionUID = -1680792957826923092L;
}
