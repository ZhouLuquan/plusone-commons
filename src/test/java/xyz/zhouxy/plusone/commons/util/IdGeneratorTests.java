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

package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import cn.hutool.core.collection.ConcurrentHashSet;

public class IdGeneratorTests {

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    @Test
    void testSnowflakeIdGenerator() { // NOSONAR
        final SnowflakeIdGenerator snowflake = new SnowflakeIdGenerator(0, 0);
        final Set<Long> ids = new ConcurrentHashSet<>();
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 50000; j++) {
                    if (false == ids.add(snowflake.nextId())) {
                        throw new RuntimeException("重复ID！");
                    }
                }
            });
        }
    }

    @Test
    void testIdWorker() { // NOSONAR
        final IdWorker idWorker = new IdWorker(0L);
        final Set<Long> ids = new ConcurrentHashSet<>();
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 50000; j++) {
                    if (false == ids.add(idWorker.nextId())) {
                        throw new RuntimeException("重复ID！");
                    }
                }
            });
            executor.execute(() -> {
                for (int j = 0; j < 50000; j++) {
                    if (false == ids.add(IdGenerator.nextSnowflakeId(0))) {
                        throw new RuntimeException("重复ID！");
                    }
                }
            });
        }
    }

    @Test
    void testToSimpleString() {
        UUID id = UUID.randomUUID();
        assertEquals(id.toString().replaceAll("-", ""),
                IdGenerator.toSimpleString(id));
    }

}
