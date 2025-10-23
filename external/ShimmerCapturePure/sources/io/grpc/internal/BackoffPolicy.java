package io.grpc.internal;

/* loaded from: classes2.dex */
public interface BackoffPolicy {

    long nextBackoffNanos();

    public interface Provider {
        BackoffPolicy get();
    }
}
