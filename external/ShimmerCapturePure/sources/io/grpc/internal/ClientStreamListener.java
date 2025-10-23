package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

/* loaded from: classes2.dex */
public interface ClientStreamListener extends StreamListener {

    void closed(Status status, Metadata metadata);

    void closed(Status status, RpcProgress rpcProgress, Metadata metadata);

    void headersRead(Metadata metadata);

    public enum RpcProgress {
        PROCESSED,
        REFUSED,
        DROPPED
    }
}
