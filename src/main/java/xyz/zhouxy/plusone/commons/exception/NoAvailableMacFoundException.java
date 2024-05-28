package xyz.zhouxy.plusone.commons.exception;

public class NoAvailableMacFoundException extends Exception {
    public NoAvailableMacFoundException() {
        super();
    }

    public NoAvailableMacFoundException(String msg) {
        super(msg);
    }

    public NoAvailableMacFoundException(Throwable e) {
        super(e);
    }

    public NoAvailableMacFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}
