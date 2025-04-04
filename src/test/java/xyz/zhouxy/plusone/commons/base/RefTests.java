/*
 * Copyright 2024-2025 the original author or authors.
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

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class RefTests {

    @Test
    void testRef() {
        Ref<String> strRef = Ref.of("ZhouXY");
        assertTrue(strRef.checkValue("ZhouXY"::equals));
        assertFalse(strRef.checkValue("ZhouXY1"::equals));
        apply(strRef);
        assertEquals("Hello ZhouXY", strRef.getValue());
        assertTrue(strRef.checkValue("Hello ZhouXY"::equals));
        log.info("strRef: {}", strRef);

        Ref<String> intStringRef = Ref.of("108");
        Ref<Integer> integerRef = intStringRef.transform(Integer::parseInt);
        assertEquals(108, integerRef.getValue());
    }

    void apply(Ref<String> strRef) {
        strRef.transformValue(str -> "Hello " + str);
    }
}
