package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ServerProvider;

/* loaded from: classes2.dex */
public final class NettyServerProvider extends ServerProvider {
    @Override // io.grpc.ServerProvider
    protected boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.ServerProvider
    protected int priority() {
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ServerProvider
    public NettyServerBuilder builderForPort(int i) {
        return NettyServerBuilder.forPort(i);
    }
}
