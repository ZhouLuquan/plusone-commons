/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package xyz.zhouxy.plusone.commons.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdWorker from Seata.
 *
 * @author funkye
 * @author selfishlover
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
     */
    public IdWorker(long workerId) {
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
    private void initWorkerId(long workerId) {
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
            } catch (InterruptedException ignore) {
                // don't care
            }
        }
    }

    /**
     * get newest timestamp relative to twepoch
     */
    private long getNewestTimestamp() {
        return System.currentTimeMillis() - TWEPOCH;
    }
}