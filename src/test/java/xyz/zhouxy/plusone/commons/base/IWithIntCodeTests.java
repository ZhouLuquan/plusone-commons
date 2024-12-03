package xyz.zhouxy.plusone.commons.base;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IWithIntCodeTests {
    @Test
    void test() {
        IWithIntCode instance1 = new WithIntCodeImpl(10);
        IWithIntCode instance2 = new WithIntCodeImpl(20);
        IWithIntCode instance3 = new WithIntCodeImpl(10);

        // Test for equalsCode method
        assertTrue(instance1.equalsCode(10));
        assertFalse(instance1.equalsCode(20));
        assertTrue(instance2.equalsCode(20));
        assertTrue(instance3.equalsCode(10));

        // Test for distinct instances with same code
        assertTrue(instance1.equalsCode(instance3.getCode())); // because they have the same code
        assertFalse(instance1.equalsCode(instance2.getCode())); // because they have different codes
    }
}

class WithIntCodeImpl implements IWithIntCode {
    private int code;

    public WithIntCodeImpl(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
