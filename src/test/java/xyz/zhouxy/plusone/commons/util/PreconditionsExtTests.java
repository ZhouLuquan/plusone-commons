package xyz.zhouxy.plusone.commons.util;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PreconditionsExtTests {

    @Test
    void testCheck() {
        assertThrows(TestException.class, () -> {
            PreconditionsExt.checkCondition(false, () -> new TestException("Test error message."));
        });
    }

    @Test
    void testCheckAllNotNull() {
        assertThrows(NullPointerException.class, () -> {
            Object[] array = null;
            PreconditionsExt.checkAllNotNull(array);
        });
        Object obj = new Object();
        assertNotNull(obj);
        assertThrows(NullPointerException.class,
                () -> PreconditionsExt.checkAllNotNull(new Object[] { obj, null }));
        assertThrows(NullPointerException.class,
                () -> PreconditionsExt.checkAllNotNull(obj, null));
        List<Object> list = Arrays.asList(obj, null);
        assertNotNull(list);
        assertThrows(NullPointerException.class,
                () -> PreconditionsExt.checkAllNotNull(list));

        PreconditionsExt.checkAllNotNull(new Object[] { obj, "Test" });
        PreconditionsExt.checkAllNotNull(obj, "Test");
        PreconditionsExt.checkAllNotNull(Arrays.asList(obj, "Test"));
    }
}

class TestException extends Exception {
    private static final long serialVersionUID = -8808661764734834820L;

    protected TestException(String msg) {
        super(msg);
    }

    protected TestException(Throwable cause) {
        super(cause);
    }

    protected TestException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
