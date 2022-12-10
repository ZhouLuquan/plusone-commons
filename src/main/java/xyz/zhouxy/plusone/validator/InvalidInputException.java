package xyz.zhouxy.plusone.validator;

import xyz.zhouxy.plusone.exception.PlusoneException;

/**
 * 4040200 - 无效的用户输入
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public class InvalidInputException extends PlusoneException {

    private static final long serialVersionUID = 7956661913360059670L;

    public static final int ERROR_CODE = 4040200;

    private InvalidInputException(int code, String msg) {
        super(code, msg);
    }

    private InvalidInputException(int code, Throwable cause) {
        super(code, cause);
    }

    private InvalidInputException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public InvalidInputException(String msg) {
        this(ERROR_CODE, msg);
    }

    public InvalidInputException(Throwable cause) {
        this(ERROR_CODE, cause);
    }

    public InvalidInputException(String msg, Throwable cause) {
        this(ERROR_CODE, msg, cause);
    }

    /**
     * 不支持的 Principal 类型出现时抛出的异常
     */
    public static InvalidInputException unsupportedPrincipalTypeException() {
        return unsupportedPrincipalTypeException("不支持的 PrincipalType");
    }

    /**
     * 不支持的 Principal 类型出现时抛出的异常
     */
    public static InvalidInputException unsupportedPrincipalTypeException(String message) {
        return new InvalidInputException(4040201, message);
    }
}
