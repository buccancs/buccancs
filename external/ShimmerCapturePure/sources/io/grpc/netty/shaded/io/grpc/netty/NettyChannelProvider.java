package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ManagedChannelProvider;

/* loaded from: classes2.dex */
public final class NettyChannelProvider extends ManagedChannelProvider {
    @Override // io.grpc.ManagedChannelProvider
    public boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.ManagedChannelProvider
    public int priority() {
        return 5;
    }

    @Override // io.grpc.ManagedChannelProvider
    public NettyChannelBuilder builderForAddress(String str, int i) {
        return NettyChannelBuilder.forAddress(str, i);
    }

    @Override // io.grpc.ManagedChannelProvider
    public NettyChannelBuilder builderForTarget(String str) {
        return NettyChannelBuilder.forTarget(str);
    }
}
