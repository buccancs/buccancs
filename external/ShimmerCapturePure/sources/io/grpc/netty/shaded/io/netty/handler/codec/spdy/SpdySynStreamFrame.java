package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

/* loaded from: classes3.dex */
public interface SpdySynStreamFrame extends SpdyHeadersFrame {
    int associatedStreamId();

    boolean isUnidirectional();

    SpdySynStreamFrame setUnidirectional(boolean z);

    byte priority();

    SpdySynStreamFrame setAssociatedStreamId(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame
    SpdySynStreamFrame setInvalid();

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    SpdySynStreamFrame setLast(boolean z);

    SpdySynStreamFrame setPriority(byte b);

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    SpdySynStreamFrame setStreamId(int i);
}
