package xyz.zhouxy.plusone.commons.util;

/**
 * 自定义结果
 *
 * @author zhouxy
 */
final class CustomResult extends UnifiedResponse {

    CustomResult(Object status, String message) {
        super(status, message);
    }

    CustomResult(Object status, String message, Object data) {
        super(status, message, data);
    }

    private static final long serialVersionUID = -5794887914598566589L;
}
