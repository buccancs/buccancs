package io.grpc.netty.shaded.io.netty.util;

/* loaded from: classes3.dex */
public interface Timeout {
    boolean cancel();

    boolean isCancelled();

    boolean isExpired();

    TimerTask task();

    Timer timer();
}
