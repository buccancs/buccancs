package io.grpc.netty.shaded.io.netty.handler.stream;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/* loaded from: classes3.dex */
public class ChunkedNioStream implements ChunkedInput<ByteBuf> {
    private final ByteBuffer byteBuffer;
    private final int chunkSize;
    private final ReadableByteChannel in;
    private long offset;

    public ChunkedNioStream(ReadableByteChannel readableByteChannel) {
        this(readableByteChannel, 8192);
    }

    public ChunkedNioStream(ReadableByteChannel readableByteChannel, int i) {
        ObjectUtil.checkNotNull(readableByteChannel, "in");
        if (i <= 0) {
            throw new IllegalArgumentException("chunkSize: " + i + " (expected: a positive integer)");
        }
        this.in = readableByteChannel;
        this.offset = 0L;
        this.chunkSize = i;
        this.byteBuffer = ByteBuffer.allocate(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long length() {
        return -1L;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long progress() {
        return this.offset;
    }

    public long transferredBytes() {
        return this.offset;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        int i;
        if (this.byteBuffer.position() > 0) {
            return false;
        }
        if (!this.in.isOpen() || (i = this.in.read(this.byteBuffer)) < 0) {
            return true;
        }
        this.offset += i;
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.in.close();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    @Deprecated
    public ByteBuf readChunk(ChannelHandlerContext channelHandlerContext) throws Exception {
        return readChunk(channelHandlerContext.alloc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public ByteBuf readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
        if (isEndOfInput()) {
            return null;
        }
        int iPosition = this.byteBuffer.position();
        do {
            int i = this.in.read(this.byteBuffer);
            if (i < 0) {
                break;
            }
            iPosition += i;
            this.offset += i;
        } while (iPosition != this.chunkSize);
        this.byteBuffer.flip();
        ByteBuf byteBufBuffer = byteBufAllocator.buffer(this.byteBuffer.remaining());
        try {
            byteBufBuffer.writeBytes(this.byteBuffer);
            this.byteBuffer.clear();
            return byteBufBuffer;
        } catch (Throwable th) {
            byteBufBuffer.release();
            throw th;
        }
    }
}
