package io.grpc.netty.shaded.io.netty.channel;

/* loaded from: classes3.dex */
public interface MessageSizeEstimator {

    Handle newHandle();

    public interface Handle {
        int size(Object obj);
    }
}
