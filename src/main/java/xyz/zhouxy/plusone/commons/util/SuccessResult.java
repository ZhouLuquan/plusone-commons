package xyz.zhouxy.plusone.commons.util;

import javax.annotation.Nullable;

/**
 * 成功结果
 *
 * @author zhouxy
 */
final class SuccessResult extends UnifiedResponse {
    private static final String SUCCESS_STATUS = "000000";
    private static final String DEFAULT_SUCCESS_MSG = "SUCCESS";

    SuccessResult() {
        super(SUCCESS_STATUS, DEFAULT_SUCCESS_MSG);
    }

    SuccessResult(@Nullable String message) {
        super(SUCCESS_STATUS, message);
    }

    SuccessResult(@Nullable String message, @Nullable Object data) {
        super(SUCCESS_STATUS, message, data);
    }

    private static final long serialVersionUID = -7509096647748429661L;
}
