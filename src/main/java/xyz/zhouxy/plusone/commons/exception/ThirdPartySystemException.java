package xyz.zhouxy.plusone.commons.exception;

public class ThirdPartySystemException extends BaseRuntimeException {
    private static final long serialVersionUID = 20240827113826L;

    protected ThirdPartySystemException(String type, String msg) {
        super(type, msg);
    }

    protected ThirdPartySystemException(String type, Throwable cause) {
        super(type, cause);
    }

    protected ThirdPartySystemException(String type, String msg, Throwable cause) {
        super(type, msg, cause);
    }

    private static final String DEFAULT = "0";

    public static ThirdPartySystemException of(String msg) {
        return new ThirdPartySystemException(DEFAULT, msg);
    }

    public static ThirdPartySystemException of(Throwable cause) {
        return new ThirdPartySystemException(DEFAULT, cause);
    }

    public static ThirdPartySystemException of(String msg, Throwable cause) {
        return new ThirdPartySystemException(DEFAULT, msg, cause);
    }
}
