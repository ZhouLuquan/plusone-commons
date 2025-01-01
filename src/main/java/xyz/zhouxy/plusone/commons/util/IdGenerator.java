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

package xyz.zhouxy.plusone.commons.util;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ID 生成器
 *
 * <p>
 * 生成 UUID 和 修改版雪花ID（Seata 版本）
 * </p>
 *
 * @see UUID
 * @see IdWorker
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108}">ZhouXY</a>
 */
public class IdGenerator {

    // ===== UUID =====

    public static UUID newUuid() {
        return UUID.randomUUID();
    }

    public static String uuidString() {
        return UUID.randomUUID().toString();
    }

    public static String simpleUuidString() {
        return toSimpleString(UUID.randomUUID());
    }

    public static String toSimpleString(UUID uuid) {
        AssertTools.checkArgument(Objects.nonNull(uuid));
        return (uuidDigits(uuid.getMostSignificantBits() >> 32, 8) +
                uuidDigits(uuid.getMostSignificantBits() >> 16, 4) +
                uuidDigits(uuid.getMostSignificantBits(), 4) +
                uuidDigits(uuid.getLeastSignificantBits() >> 48, 4) +
                uuidDigits(uuid.getLeastSignificantBits(), 12));
    }

    /** Returns val represented by the specified number of hex digits. */
    private static String uuidDigits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

    // ===== SnowflakeId =====

    private static final Map<Long, IdWorker> snowflakePool = new ConcurrentHashMap<>();

    public static long nextSnowflakeId(long workerId) {
        IdWorker generator = getSnowflakeIdGenerator(workerId);
        return generator.nextId();
    }

    public static IdWorker getSnowflakeIdGenerator(long workerId) {
        return snowflakePool.computeIfAbsent(workerId, wid -> new IdWorker(workerId));
    }

    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }
}
