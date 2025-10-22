package io.grpc.netty.shaded.io.netty.util;

@Deprecated
/* loaded from: classes3.dex */
public interface ResourceLeak {
    boolean close();

    void record();

    void record(Object obj);
}
