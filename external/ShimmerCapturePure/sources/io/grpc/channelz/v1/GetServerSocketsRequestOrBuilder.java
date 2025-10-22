package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface GetServerSocketsRequestOrBuilder extends MessageOrBuilder {
    long getMaxResults();

    long getServerId();

    long getStartSocketId();
}
