package com.ythd.ower.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionIdUtil {
    private static final Logger log = LoggerFactory.getLogger(TransactionIdUtil.class);
    private static final String TRANSACTIONID = "transactionId";
    private final long workerId;
    private final long twepoch = 1361753741828L;
    private long sequence = 0L;
    private final long workerIdBits = 4L;
    public final long maxWorkerId = 15L;
    private final long sequenceBits = 10L;
    private final long workerIdShift = 10L;
    private final long timestampLeftShift = 14L;
    public final long sequenceMask = 1023L;
    private long lastTimestamp = -1L;

    public TransactionIdUtil(long workerId) {
        this.getClass();
        if (workerId <= 15L && workerId >= 0L) {
            this.workerId = workerId;
        } else {
            Object[] var10003 = new Object[1];
            this.getClass();
            var10003[0] = 15L;
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", var10003));
        }
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        long var10001;
        if (this.lastTimestamp == timestamp) {
            var10001 = this.sequence + 1L;
            this.getClass();
            this.sequence = var10001 & 1023L;
            if (this.sequence == 0L) {
                System.out.println("###########1023");
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            } catch (Exception var5) {
                log.error("TransactionIdUtils has exception:", var5);
            }
        }

        this.lastTimestamp = timestamp;
        long var10000 = timestamp - 1361753741828L << 14;
        var10001 = this.workerId;
        this.getClass();
        long nextId = var10000 | var10001 << 10 | this.sequence;
        return nextId;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
            ;
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        TransactionIdUtil worker = new TransactionIdUtil(2L);
        System.out.println(worker.getClass().getName());
        System.out.println(worker.getClass().getSimpleName());
        System.out.println(worker.getClass().getCanonicalName());
        System.out.println(worker.nextId());
        System.out.println((new TransactionIdUtil(2L)).nextId());
    }
}
