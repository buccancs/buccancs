package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.unix.Buffer;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
final class EpollEventArray {
    private static final int EPOLL_EVENT_SIZE = Native.sizeofEpollEvent();
    private static final int EPOLL_DATA_OFFSET = Native.offsetofEpollData();
    private int length;
    private ByteBuffer memory;
    private long memoryAddress;

    EpollEventArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("length must be >= 1 but was " + i);
        }
        this.length = i;
        ByteBuffer byteBufferAllocateDirectWithNativeOrder = Buffer.allocateDirectWithNativeOrder(calculateBufferCapacity(i));
        this.memory = byteBufferAllocateDirectWithNativeOrder;
        this.memoryAddress = Buffer.memoryAddress(byteBufferAllocateDirectWithNativeOrder);
    }

    private static int calculateBufferCapacity(int i) {
        return i * EPOLL_EVENT_SIZE;
    }

    int length() {
        return this.length;
    }

    long memoryAddress() {
        return this.memoryAddress;
    }

    void increase() {
        int i = this.length << 1;
        this.length = i;
        ByteBuffer byteBufferAllocateDirectWithNativeOrder = Buffer.allocateDirectWithNativeOrder(calculateBufferCapacity(i));
        Buffer.free(this.memory);
        this.memory = byteBufferAllocateDirectWithNativeOrder;
        this.memoryAddress = Buffer.memoryAddress(byteBufferAllocateDirectWithNativeOrder);
    }

    void free() {
        Buffer.free(this.memory);
        this.memoryAddress = 0L;
    }

    int events(int i) {
        return getInt(i, 0);
    }

    int fd(int i) {
        return getInt(i, EPOLL_DATA_OFFSET);
    }

    private int getInt(int i, int i2) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.getInt(this.memoryAddress + (i * EPOLL_EVENT_SIZE) + i2);
        }
        return this.memory.getInt((i * EPOLL_EVENT_SIZE) + i2);
    }
}
