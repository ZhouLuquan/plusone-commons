package xyz.zhouxy.plusone.exception;

/**
 * 带错误码的异常。
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public abstract class BaseException extends RuntimeException implements IWithCode {

    private static final long serialVersionUID = -2546365325001947203L;

    private final int code;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
