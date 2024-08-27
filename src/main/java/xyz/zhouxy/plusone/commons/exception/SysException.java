package xyz.zhouxy.plusone.commons.exception;

public class SysException extends BaseRuntimeException {
    private static final long serialVersionUID = 8821240827443168118L;

    protected SysException(String type, String msg) {
        super(type, msg);
    }

    protected SysException(String type, Throwable cause) {
        super(type, cause);
    }

    protected SysException(String type, String msg, Throwable cause) {
        super(type, msg, cause);
    }

    private static final String DEFAULT = "0";

    public static SysException of(String msg) {
        return new SysException(DEFAULT, msg);
    }

    public static SysException of(Throwable cause) {
        return new SysException(DEFAULT, cause);
    }

    public static SysException of(String msg, Throwable cause) {
        return new SysException(DEFAULT, msg, cause);
    }
}
