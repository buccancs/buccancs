package io.grpc.netty.shaded.io.netty.util;

/* loaded from: classes3.dex */
public interface ResourceLeakTracker<T> {
    boolean close(T t);

    void record();

    void record(Object obj);
}
