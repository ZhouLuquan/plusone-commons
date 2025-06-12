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

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;

import java.util.concurrent.TimeUnit;

/**
 * Twitter 版雪花算法
 */
public class SnowflakeIdGenerator {

    // ==============================Fields===========================================

    /** 开始时间截（北京时间 2008/08/08 20:00） */
    private static final long TWEPOCH = 1218196800000L;

    /** 机器 id 所占的位数 */
    private static final long WORKER_ID_BITS = 5L;

    /** 数据标识 id 所占的位数 */
    private static final long DATACENTER_ID_BITS = 5L;

    /** 支持的最大机器 id，结果是 31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /** 支持的最大数据标识 id，结果是 31 */
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    /** 序列在 id 中占的位数 */
    private static final long SEQUENCE_BITS = 12L;

    /** 机器 ID 向左移 12 位 */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /** 数据标识id向左移 17 位 (12+5) */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /** 时间截向左移 22 位 (5+5+12) */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /** 生成序列的掩码，这里为 4095 (0b111111111111=0xfff=4095) */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /** 数据中心 ID 和 工作机器 ID 偏移后的值 */
    private final long datacenterIdAndWorkerId;

    /** 毫秒内序列 (0~4095) */
    private long sequence = 0L;

    /** 上次生成 ID 的时间截 */
    private long lastTimestamp = -1L;

    // ==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdGenerator(final long workerId, final long datacenterId) {
        checkArgument((workerId <= MAX_WORKER_ID && workerId >= 0),
                "WorkerId can't be greater than %s or less than 0.", MAX_WORKER_ID);
        checkArgument((datacenterId <= MAX_DATACENTER_ID && datacenterId >= 0),
                "DatacenterId can't be greater than %s or less than 0.", MAX_DATACENTER_ID);
        this.datacenterIdAndWorkerId
                = (datacenterId << DATACENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT);
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 发生了回拨，此刻时间小于上次发号时间
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                // 时间偏差大小小于5ms，则等待两倍时间
                try {
                    TimeUnit.MILLISECONDS.sleep(offset << 1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException(e);
                }
                timestamp = timeGen();
                if (timestamp < lastTimestamp) {
                    // 还是小于，抛异常上报
                    throwClockBackwardsEx(lastTimestamp, timestamp);
                }
            } else {
                throwClockBackwardsEx(lastTimestamp, timestamp);
            }
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成 ID 的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | datacenterIdAndWorkerId | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间（毫秒）
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    protected void throwClockBackwardsEx(long lastTimestamp, long timestamp) {
        throw new IllegalStateException(
                String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                        lastTimestamp - timestamp));
    }
}
