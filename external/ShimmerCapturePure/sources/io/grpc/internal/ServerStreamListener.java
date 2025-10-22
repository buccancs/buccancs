package io.grpc.internal;

import io.grpc.Status;

/* loaded from: classes2.dex */
public interface ServerStreamListener extends StreamListener {
    void closed(Status status);

    void halfClosed();
}
