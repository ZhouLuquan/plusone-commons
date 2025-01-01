/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.zhouxy.plusone.commons.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import xyz.zhouxy.plusone.commons.exception.system.NoAvailableMacFoundException;

/**
 * Seata 提供的修改版雪花ID。
 * <p>
 * 大体思路为：
 * <ol>
 * <li>每个机器线程安全地生成序列，前面加上机器的id，这样就不会与其它机器的id相冲突。</li>
 * <li>时间戳作为序列的“预留位”，它更像是应用启动时最开始的序列的一部分，在一个时间戳里生成 4096 个 id 之后，直接生成下一个时间戳的 id。</li>
 * </ol>
 * </p>
 * <p>
 * 详情见以下介绍：
 * <ul>
 * <li><a href="https://seata.apache.org/zh-cn/blog/seata-analysis-UUID-generator/">Seata基于改良版雪花算法的分布式UUID生成器分析</a></li>
 * <li><a href="https://seata.apache.org/zh-cn/blog/seata-snowflake-explain">关于新版雪花算法的答疑</a></li>
 * <li><a href="https://juejin.cn/post/7264387737276203065">在开源项目中看到一个改良版的雪花算法，现在它是你的了。</a></li>
 * <li><a href="https://juejin.cn/post/7265516484029743138">关于若干读者，阅读“改良版雪花算法”后提出的几个共性问题的回复。</a></li>
 * </ul>
 * </p>
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108}">ZhouXY</a>
 */
public class IdWorker {

    /**
     * Start time cut (2020-05-03)
     */
    private static final long TWEPOCH = 1588435200000L;

    /**
     * The number of bits occupied by workerId
     */
    private static final int WORKER_ID_BITS = 10;

    /**
     * The number of bits occupied by timestamp
     */
    private static final int TIMESTAMP_BITS = 41;

    /**
     * The number of bits occupied by sequence
     */
    private static final int SEQUENCE_BITS = 12;

    /**
     * Maximum supported machine id, the result is 1023
     */
    private static final int MAX_WORKER_ID = ~(-1 << WORKER_ID_BITS);

    /**
     * business meaning: machine ID (0 ~ 1023)
     * actual layout in memory:
     * highest 1 bit: 0
     * middle 10 bit: workerId
     * lowest 53 bit: all 0
     */
    private long workerId;

    /**
     * timestamp and sequence mix in one Long
     * highest 11 bit: not used
     * middle  41 bit: timestamp
     * lowest  12 bit: sequence
     */
    private AtomicLong timestampAndSequence;

    /**
     * mask that help to extract timestamp and sequence from a long
     */
    private static final long TIMESTAMP_AND_SEQUENCE_MASK = ~(-1L << (TIMESTAMP_BITS + SEQUENCE_BITS));

    /**
     * instantiate an IdWorker using given workerId
     * @param workerId if null, then will auto assign one
     */
    public IdWorker(Long workerId) {
        initTimestampAndSequence();
        initWorkerId(workerId);
    }

    /**
     * init first timestamp and sequence immediately
     */
    private void initTimestampAndSequence() {
        long timestamp = getNewestTimestamp();
        long timestampWithSequence = timestamp << SEQUENCE_BITS;
        this.timestampAndSequence = new AtomicLong(timestampWithSequence);
    }

    /**
     * init workerId
     * @param workerId if null, then auto generate one
     */
    private void initWorkerId(Long workerId) {
        if (workerId == null) {
            workerId = generateWorkerId();
        }
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            String message = String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID);
            throw new IllegalArgumentException(message);
        }
        this.workerId = workerId << (TIMESTAMP_BITS + SEQUENCE_BITS);
    }

    /**
     * get next UUID(base on snowflake algorithm), which look like:
     * highest 1 bit: always 0
     * next   10 bit: workerId
     * next   41 bit: timestamp
     * lowest 12 bit: sequence
     * @return UUID
     */
    public long nextId() {
        waitIfNecessary();
        long next = timestampAndSequence.incrementAndGet();
        long timestampWithSequence = next & TIMESTAMP_AND_SEQUENCE_MASK;
        return workerId | timestampWithSequence;
    }

    /**
     * block current thread if the QPS of acquiring UUID is too high
     * that current sequence space is exhausted
     */
    private void waitIfNecessary() {
        long currentWithSequence = timestampAndSequence.get();
        long current = currentWithSequence >>> SEQUENCE_BITS;
        long newest = getNewestTimestamp();
        if (current >= newest) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignore) { // NOSONAR don't care
            }
        }
    }

    /**
     * get newest timestamp relative to twepoch
     */
    private long getNewestTimestamp() {
        return System.currentTimeMillis() - TWEPOCH;
    }

    /**
     * auto generate workerId, try using mac first, if failed, then randomly generate one
     * @return workerId
     */
    private static long generateWorkerId() {
        try {
            return generateWorkerIdBaseOnMac();
        } catch (Exception e) {
            return generateRandomWorkerId();
        }
    }

    /**
     * use lowest 10 bit of available MAC as workerId
     * @return workerId
     * @throws SocketException
     * @throws NoAvailableMacFoundException when there is no available mac found
     */
    private static long generateWorkerIdBaseOnMac() throws SocketException, NoAvailableMacFoundException {
        Enumeration<NetworkInterface> all = NetworkInterface.getNetworkInterfaces();
        while (all.hasMoreElements()) {
            NetworkInterface networkInterface = all.nextElement();
            boolean isLoopback = networkInterface.isLoopback();
            boolean isVirtual = networkInterface.isVirtual();
            byte[] mac = networkInterface.getHardwareAddress();
            if (isLoopback || isVirtual || mac == null) {
                continue;
            }
            return ((mac[4] & 0B11) << 8) | (mac[5] & 0xFF);
        }
        throw new NoAvailableMacFoundException();
    }

    /**
     * randomly generate one as workerId
     * @return workerId
     */
    private static long generateRandomWorkerId() {
        return ThreadLocalRandom.current().nextInt(MAX_WORKER_ID + 1);
    }
}
