/*
 * Copyright 2023-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons;

import java.io.ObjectStreamClass;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.exception.NoAvailableMacFoundException;

@Slf4j
class SerialTests {

    @Test
    void testSerialVersionUID() {
        long uid = getSerialVersionUID(NoAvailableMacFoundException.class);
        log.info("\n    private static final long serialVersionUID = {}L;", uid);
    }

    private long getSerialVersionUID(Class<?> cl) {
        ObjectStreamClass c = ObjectStreamClass.lookup(cl);
        return c.getSerialVersionUID();
    }
}
