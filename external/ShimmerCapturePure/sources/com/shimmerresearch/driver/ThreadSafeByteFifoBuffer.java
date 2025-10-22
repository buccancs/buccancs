package com.shimmerresearch.driver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes2.dex */
public class ThreadSafeByteFifoBuffer {
    private BlockingQueue<Byte> buffer;

    public ThreadSafeByteFifoBuffer(int i) {
        this.buffer = new ArrayBlockingQueue(i);
    }

    public void write(byte[] bArr) throws InterruptedException {
        for (byte b : bArr) {
            this.buffer.put(Byte.valueOf(b));
        }
    }

    public void write(byte b) throws InterruptedException {
        this.buffer.put(Byte.valueOf(b));
    }

    public byte[] read(int i) throws InterruptedException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = this.buffer.take().byteValue();
        }
        return bArr;
    }

    public byte read() throws InterruptedException {
        return this.buffer.take().byteValue();
    }

    public int size() {
        return this.buffer.size();
    }
}
