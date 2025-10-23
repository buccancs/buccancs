package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.util.AsciiString;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface ProtocolNegotiator {
    void close();

    ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler);

    AsciiString scheme();
}
