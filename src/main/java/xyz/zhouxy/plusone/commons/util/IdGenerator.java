package xyz.zhouxy.plusone.commons.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.annotations.Beta;

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
