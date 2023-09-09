package xyz.zhouxy.plusone.commons.util;

import org.junit.jupiter.api.Test;
import xyz.zhouxy.plusone.commons.exception.BaseException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PreconditionsExtTests {

    @Test
    void testCheck() {
        assertThrows(TestException.class, () -> {
            PreconditionsExt.check(false, () -> new TestException("Test error message."));
        });
    }

    @Test
    void testCheckAllNotNull() {
        assertThrows(NullPointerException.class, () -> {
            Object[] array = null;
            PreconditionsExt.checkAllNotNull(array);
        });
        assertThrows(NullPointerException.class,
                () -> PreconditionsExt.checkAllNotNull(new Object[]{new Object(), null}));
        assertThrows(NullPointerException.class,
                () -> PreconditionsExt.checkAllNotNull(new Object(), null));
        assertThrows(NullPointerException.class,
                () -> PreconditionsExt.checkAllNotNull(Arrays.asList(new Object(), null)));

        PreconditionsExt.checkAllNotNull(new Object[]{new Object(), "Test"});
        PreconditionsExt.checkAllNotNull(new Object(), "Test");
        PreconditionsExt.checkAllNotNull(Arrays.asList(new Object(), "Test"));
    }
}

class TestException extends BaseException {
    private static final long serialVersionUID = -8808661764734834820L;

    private static final String ERR_CODE = "TEST";

    protected TestException(String msg) {
        super(ERR_CODE, msg);
    }

    protected TestException(Throwable cause) {
        super(ERR_CODE, cause);
    }

    protected TestException(String msg, Throwable cause) {
        super(ERR_CODE, msg, cause);
    }
}
