package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;

/* loaded from: classes3.dex */
abstract class SpdyHeaderBlockDecoder {
    SpdyHeaderBlockDecoder() {
    }

    static SpdyHeaderBlockDecoder newInstance(SpdyVersion spdyVersion, int i) {
        return new SpdyHeaderBlockZlibDecoder(spdyVersion, i);
    }

    abstract void decode(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception;

    abstract void end();

    abstract void endHeaderBlock(SpdyHeadersFrame spdyHeadersFrame) throws Exception;
}
