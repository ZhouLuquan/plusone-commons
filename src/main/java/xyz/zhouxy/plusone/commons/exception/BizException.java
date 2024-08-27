package xyz.zhouxy.plusone.commons.exception;

/**
 * 业务异常
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public class BizException extends BaseRuntimeException {
    private static final long serialVersionUID = -5524759033245815405L;

    protected BizException(String type, String msg) {
        super(type, msg);
    }

    protected BizException(String type, Throwable cause) {
        super(type, cause);
    }

    protected BizException(String type, String msg, Throwable cause) {
        super(type, msg, cause);
    }

    private static final String DEFAULT = "0";

    public static BizException of(String msg) {
        return new BizException(DEFAULT, msg);
    }

    public static BizException of(Throwable cause) {
        return new BizException(DEFAULT, cause);
    }

    public static BizException of(String msg, Throwable cause) {
        return new BizException(DEFAULT, msg, cause);
    }
}
