package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.internal.WritableBuffer;
import io.grpc.internal.WritableBufferAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;

/* loaded from: classes2.dex */
class NettyWritableBufferAllocator implements WritableBufferAllocator {
    private static final int MAX_BUFFER = 1048576;
    private static final int MIN_BUFFER = 4096;
    private final ByteBufAllocator allocator;

    NettyWritableBufferAllocator(ByteBufAllocator byteBufAllocator) {
        this.allocator = byteBufAllocator;
    }

    @Override // io.grpc.internal.WritableBufferAllocator
    public WritableBuffer allocate(int i) {
        int iMin = Math.min(1048576, Math.max(4096, i));
        return new NettyWritableBuffer(this.allocator.buffer(iMin, iMin));
    }
}
