package io.grpc.netty.shaded.io.netty.handler.codec.http2;

/* loaded from: classes3.dex */
public interface StreamByteDistributor {

    boolean distribute(int i, Writer writer) throws Http2Exception;

    void updateDependencyTree(int i, int i2, short s, boolean z);

    void updateStreamableBytes(StreamState streamState);

    public interface StreamState {
        boolean hasFrame();

        long pendingBytes();

        Http2Stream stream();

        int windowSize();
    }

    public interface Writer {
        void write(Http2Stream http2Stream, int i);
    }
}
