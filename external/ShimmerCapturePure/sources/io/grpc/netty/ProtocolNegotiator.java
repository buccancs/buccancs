package io.grpc.netty;

import io.netty.channel.ChannelHandler;
import io.netty.util.AsciiString;

/* loaded from: classes2.dex */
interface ProtocolNegotiator {
    void close();

    ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler);

    AsciiString scheme();
}
