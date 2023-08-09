package xyz.zhouxy.plusone.commons.util;

/**
 * 普通结果
 *
 * @author zhouxy
 */
final class OrdinaryResult extends UnifiedResponse {

    OrdinaryResult(Object status, String message) {
        super(status, message);
    }

    OrdinaryResult(Object status, String message, Object data) {
        super(status, message, data);
    }

    private static final long serialVersionUID = -5794887914598566589L;
}
