/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.base;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Nonnull;

import org.junit.jupiter.api.Test;

import xyz.zhouxy.plusone.commons.util.AssertTools;

class IWithCodeTests {

    @Test
    void equalsCode_SameCode_ReturnsTrue() {
        assertTrue(WithCode.INSTANCE.equalsCode("testCode"));
        Integer intCode = 0;
        Long longCode = 0L;
        assertTrue(WithIntCode.INSTANCE.equalsCode(intCode));
        assertTrue(WithLongCode.INSTANCE.equalsCode(intCode));
        assertTrue(WithLongCode.INSTANCE.equalsCode(longCode));

        assertTrue(WithCode.INSTANCE.equalsCode(WithCode.SAME_CODE_INSTANCE));
        assertTrue(WithIntCode.INSTANCE.equalsCode(WithIntCode.SAME_CODE_INSTANCE));
        assertTrue(WithIntCode.INSTANCE.equalsCode(WithLongCode.SAME_CODE_INSTANCE));
        assertTrue(WithLongCode.INSTANCE.equalsCode(WithLongCode.SAME_CODE_INSTANCE));
        assertTrue(WithLongCode.INSTANCE.equalsCode(WithIntCode.SAME_CODE_INSTANCE));
    }

    @Test
    void equalsCode_DifferentCode_ReturnsFalse() {
        assertFalse(WithCode.INSTANCE.equalsCode("wrongCode"));
        Integer intCode = 108;
        Long longCode = 108L;
        assertFalse(WithIntCode.INSTANCE.equalsCode(intCode));
        assertFalse(WithLongCode.INSTANCE.equalsCode(intCode));
        assertFalse(WithLongCode.INSTANCE.equalsCode(longCode));

        assertFalse(WithCode.INSTANCE.equalsCode(WithCode.WRONG_CODE_INSTANCE));
        assertFalse(WithIntCode.INSTANCE.equalsCode(WithIntCode.WRONG_CODE_INSTANCE));
        assertFalse(WithIntCode.INSTANCE.equalsCode(WithLongCode.WRONG_CODE_INSTANCE));
        assertFalse(WithLongCode.INSTANCE.equalsCode(WithLongCode.WRONG_CODE_INSTANCE));
        assertFalse(WithLongCode.INSTANCE.equalsCode(WithIntCode.WRONG_CODE_INSTANCE));
    }

    @Test
    @SuppressWarnings("null")
    void equalsCode_NullCode_ReturnsFalse() {
        assertFalse(WithCode.INSTANCE.equalsCode((WithCode) null));
        assertFalse(WithCode.INSTANCE.equalsCode((WithIntCode) null));
        assertFalse(WithCode.INSTANCE.equalsCode((WithLongCode) null));

        assertFalse(WithIntCode.INSTANCE.equalsCode((WithCode) null));
        assertFalse(WithIntCode.INSTANCE.equalsCode((WithIntCode) null));
        assertFalse(WithIntCode.INSTANCE.equalsCode((WithLongCode) null));

        assertFalse(WithLongCode.INSTANCE.equalsCode((WithCode) null));
        assertFalse(WithLongCode.INSTANCE.equalsCode((WithIntCode) null));
        assertFalse(WithLongCode.INSTANCE.equalsCode((WithLongCode) null));

        assertFalse(WithCode.INSTANCE.equalsCode((String) null));
        Integer intCode = null;
        Long longCode = null;
        assertThrows(NullPointerException.class, () -> WithIntCode.INSTANCE.equalsCode(intCode));
        assertThrows(NullPointerException.class, () -> WithLongCode.INSTANCE.equalsCode(intCode));
        assertThrows(NullPointerException.class, () -> WithLongCode.INSTANCE.equalsCode(longCode));
    }

    private static enum WithCode implements IWithCode<String> {
        INSTANCE("testCode"),
        SAME_CODE_INSTANCE("testCode"),
        WRONG_CODE_INSTANCE("wrongCode"),
        ;

        @Nonnull
        private final String code;

        WithCode(String code) {
            AssertTools.checkNotNull(code);
            this.code = code;
        }

        @Override
        @Nonnull
        public String getCode() {
            return code;
        }
    }

    private static enum WithIntCode implements IWithIntCode {
        INSTANCE(0),
        SAME_CODE_INSTANCE(0),
        WRONG_CODE_INSTANCE(1),
        ;

        private final int code;

        WithIntCode(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return code;
        }
    }

    private static enum WithLongCode implements IWithLongCode {
        INSTANCE(0L),
        SAME_CODE_INSTANCE(0L),
        WRONG_CODE_INSTANCE(108L),
        ;

        private final long code;

        WithLongCode(long code) {
            this.code = code;
        }

        @Override
        public long getCode() {
            return code;
        }
    }
}
