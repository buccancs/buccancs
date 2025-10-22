package io.grpc.netty.shaded.io.netty.handler.stream;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.io.InputStream;
import java.io.PushbackInputStream;

/* loaded from: classes3.dex */
public class ChunkedStream implements ChunkedInput<ByteBuf> {
    static final int DEFAULT_CHUNK_SIZE = 8192;
    private final int chunkSize;
    private final PushbackInputStream in;
    private boolean closed;
    private long offset;

    public ChunkedStream(InputStream inputStream) {
        this(inputStream, 8192);
    }

    public ChunkedStream(InputStream inputStream, int i) {
        ObjectUtil.checkNotNull(inputStream, "in");
        ObjectUtil.checkPositive(i, "chunkSize");
        if (inputStream instanceof PushbackInputStream) {
            this.in = (PushbackInputStream) inputStream;
        } else {
            this.in = new PushbackInputStream(inputStream);
        }
        this.chunkSize = i;
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
        if (this.closed || (i = this.in.read()) < 0) {
            return true;
        }
        this.in.unread(i);
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.closed = true;
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
        ByteBuf byteBufBuffer = byteBufAllocator.buffer(this.in.available() <= 0 ? this.chunkSize : Math.min(this.chunkSize, this.in.available()));
        try {
            this.offset += byteBufBuffer.writeBytes(this.in, r0);
            return byteBufBuffer;
        } catch (Throwable th) {
            byteBufBuffer.release();
            throw th;
        }
    }
}
