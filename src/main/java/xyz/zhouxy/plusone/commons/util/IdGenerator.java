package xyz.zhouxy.plusone.commons.util;

import java.util.UUID;

import com.google.common.annotations.Beta;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

@Beta
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
        return (digits(uuid.getMostSignificantBits() >> 32, 8) +
                digits(uuid.getMostSignificantBits() >> 16, 4) +
                digits(uuid.getMostSignificantBits(), 4) +
                digits(uuid.getLeastSignificantBits() >> 48, 4) +
                digits(uuid.getLeastSignificantBits(), 12));
    }

    /** Returns val represented by the specified number of hex digits. */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

    // ===== SnowflakeId =====

    private static final Table<Long, Long, SnowflakeIdGenerator> snowflakePool = HashBasedTable.create();

    public static long nextSnowflakeId(long workerId, long datacenterId) {
        SnowflakeIdGenerator generator = getSnowflakeIdGenerator(workerId, datacenterId);
        return generator.nextId();
    }

    public static SnowflakeIdGenerator getSnowflakeIdGenerator(long workerId, long datacenterId) {
        SnowflakeIdGenerator generator = snowflakePool.get(workerId, datacenterId);
        if (generator == null) {
            synchronized (IdGenerator.class) {
                generator = snowflakePool.get(workerId, datacenterId);
                if (generator == null) {
                    generator = new SnowflakeIdGenerator(workerId, datacenterId);
                    snowflakePool.put(workerId, datacenterId, generator);
                }
            }
        }
        return generator;
    }

    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }
}
