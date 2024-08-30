package xyz.zhouxy.plusone.commons.exception;

public class NoAvailableMacFoundException extends Exception {
    private static final long serialVersionUID = 152827098461071551L;

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
