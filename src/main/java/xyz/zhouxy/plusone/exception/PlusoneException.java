package xyz.zhouxy.plusone.exception;

/**
 * 项目的基础异常，默认错误码为 9999999
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public class PlusoneException extends RuntimeException implements IWithCode {

    private static final long serialVersionUID = -2546365325001947203L;

    private final int code;

    public PlusoneException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public PlusoneException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public PlusoneException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
