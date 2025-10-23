package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* loaded from: classes3.dex */
abstract class SpdyHeaderBlockEncoder {
    SpdyHeaderBlockEncoder() {
    }

    static SpdyHeaderBlockEncoder newInstance(SpdyVersion spdyVersion, int i, int i2, int i3) {
        if (PlatformDependent.javaVersion() >= 7) {
            return new SpdyHeaderBlockZlibEncoder(spdyVersion, i);
        }
        return new SpdyHeaderBlockJZlibEncoder(spdyVersion, i, i2, i3);
    }

    abstract ByteBuf encode(ByteBufAllocator byteBufAllocator, SpdyHeadersFrame spdyHeadersFrame) throws Exception;

    abstract void end();
}
