/*
 * Copyright 2024-present ZhouXY
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
import static xyz.zhouxy.plusone.commons.util.AssertTools.checkNotNull;

import javax.annotation.Nonnull;

import org.junit.jupiter.api.Test;


class IWithCodeTests {

    @Test
    void equalsCode_SameCode_ReturnsTrue() {
        assertTrue(WithCode.INSTANCE.isCodeEquals("testCode"));
        Integer intCode = 0;
        Long longCode = 0L;
        assertTrue(WithIntCode.INSTANCE.isCodeEquals(intCode));
        assertTrue(WithLongCode.INSTANCE.isCodeEquals(intCode));
        assertTrue(WithLongCode.INSTANCE.isCodeEquals(longCode));

        assertTrue(WithCode.INSTANCE.isSameCodeAs(WithCode.SAME_CODE_INSTANCE));
        assertTrue(WithIntCode.INSTANCE.isSameCodeAs(WithIntCode.SAME_CODE_INSTANCE));
        assertTrue(WithIntCode.INSTANCE.isSameCodeAs(WithLongCode.SAME_CODE_INSTANCE));
        assertTrue(WithLongCode.INSTANCE.isSameCodeAs(WithLongCode.SAME_CODE_INSTANCE));
        assertTrue(WithLongCode.INSTANCE.isSameCodeAs(WithIntCode.SAME_CODE_INSTANCE));
    }

    @Test
    void equalsCode_DifferentCode_ReturnsFalse() {
        assertFalse(WithCode.INSTANCE.isCodeEquals("wrongCode"));
        Integer intCode = 108;
        Long longCode = 108L;
        assertFalse(WithIntCode.INSTANCE.isCodeEquals(intCode));
        assertFalse(WithLongCode.INSTANCE.isCodeEquals(intCode));
        assertFalse(WithLongCode.INSTANCE.isCodeEquals(longCode));

        assertFalse(WithCode.INSTANCE.isSameCodeAs(WithCode.WRONG_CODE_INSTANCE));
        assertFalse(WithIntCode.INSTANCE.isSameCodeAs(WithIntCode.WRONG_CODE_INSTANCE));
        assertFalse(WithIntCode.INSTANCE.isSameCodeAs(WithLongCode.WRONG_CODE_INSTANCE));
        assertFalse(WithLongCode.INSTANCE.isSameCodeAs(WithLongCode.WRONG_CODE_INSTANCE));
        assertFalse(WithLongCode.INSTANCE.isSameCodeAs(WithIntCode.WRONG_CODE_INSTANCE));
    }

    @Test
    @SuppressWarnings("null")
    void equalsCode_NullCode_ReturnsFalse() {
        assertFalse(WithCode.INSTANCE.isSameCodeAs((WithCode) null));
        assertFalse(WithCode.INSTANCE.isSameCodeAs((WithIntCode) null));
        assertFalse(WithCode.INSTANCE.isSameCodeAs((WithLongCode) null));

        assertFalse(WithIntCode.INSTANCE.isSameCodeAs((WithCode) null));
        assertFalse(WithIntCode.INSTANCE.isSameCodeAs((WithIntCode) null));
        assertFalse(WithIntCode.INSTANCE.isSameCodeAs((WithLongCode) null));

        assertFalse(WithLongCode.INSTANCE.isSameCodeAs((WithCode) null));
        assertFalse(WithLongCode.INSTANCE.isSameCodeAs((WithIntCode) null));
        assertFalse(WithLongCode.INSTANCE.isSameCodeAs((WithLongCode) null));

        assertFalse(WithCode.INSTANCE.isCodeEquals((String) null));
        Integer intCode = null;
        Long longCode = null;
        assertThrows(NullPointerException.class, () -> WithIntCode.INSTANCE.isCodeEquals(intCode));
        assertThrows(NullPointerException.class, () -> WithLongCode.INSTANCE.isCodeEquals(intCode));
        assertThrows(NullPointerException.class, () -> WithLongCode.INSTANCE.isCodeEquals(longCode));
    }

    private enum WithCode implements IWithCode<String> {
        INSTANCE("testCode"),
        SAME_CODE_INSTANCE("testCode"),
        WRONG_CODE_INSTANCE("wrongCode"),
        ;

        @Nonnull
        private final String code;

        WithCode(String code) {
            checkNotNull(code);
            this.code = code;
        }

        @Override
        @Nonnull
        public String getCode() {
            return code;
        }
    }

    private enum WithIntCode implements IWithIntCode {
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

    private enum WithLongCode implements IWithLongCode {
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
