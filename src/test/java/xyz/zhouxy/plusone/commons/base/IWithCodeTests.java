package xyz.zhouxy.plusone.commons.base;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.annotation.Nonnull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xyz.zhouxy.plusone.commons.util.AssertTools;

class IWithCodeTests {

    private static class WithCodeImpl implements IWithCode<String> {
        @Nonnull
        private final String code;

        WithCodeImpl(String code) {
            AssertTools.checkNotNull(code);
            this.code = code;
        }

        @Override
        @Nonnull
        public String getCode() {
            return code;
        }
    }

    private WithCodeImpl instance;

    @BeforeEach
    void setUp() {
        instance = new WithCodeImpl("testCode");
    }

    @Test
    void equalsCode_SameCode_ReturnsTrue() {
        assertTrue(instance.equalsCode("testCode"));
    }

    @Test
    void equalsCode_DifferentCode_ReturnsFalse() {
        assertFalse(instance.equalsCode("wrongCode"));
    }

    @Test
    void equalsCode_NullCode_ReturnsFalse() {
        assertFalse(instance.equalsCode((String) null));
    }

    @Test
    void equalsCode_NullObject_ReturnsFalse() {
        assertFalse(instance.equalsCode((String) null));
    }
}
