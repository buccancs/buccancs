package io.grpc.alts;

import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.common.collect.ImmutableList;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.Status;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import io.grpc.auth.MoreCallCredentials;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import javax.net.ssl.SSLException;

/* loaded from: classes2.dex */
public final class ComputeEngineChannelBuilder extends ForwardingChannelBuilder<ComputeEngineChannelBuilder> {
    private final NettyChannelBuilder delegate;

    private ComputeEngineChannelBuilder(String str) {
        this.delegate = NettyChannelBuilder.forTarget(str);
        try {
            InternalNettyChannelBuilder.setProtocolNegotiatorFactory(delegate(), new AltsProtocolNegotiator.GoogleDefaultProtocolNegotiatorFactory(ImmutableList.of(), SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL), GrpcSslContexts.forClient().build()));
            delegate().intercept(new ClientInterceptor[]{new CallCredentialsInterceptor(MoreCallCredentials.from(ComputeEngineCredentials.create()), CheckGcpEnvironment.isOnGcp() ? Status.OK : Status.INTERNAL.withDescription("Compute Engine Credentials can only be used on Google Cloud Platform"))});
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final ComputeEngineChannelBuilder forTarget(String str) {
        return new ComputeEngineChannelBuilder(str);
    }

    public static ComputeEngineChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ForwardingChannelBuilder
    public NettyChannelBuilder delegate() {
        return this.delegate;
    }

    InternalProtocolNegotiator.ProtocolNegotiator getProtocolNegotiatorForTest() {
        try {
            return new AltsProtocolNegotiator.GoogleDefaultProtocolNegotiatorFactory(ImmutableList.of(), SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL), GrpcSslContexts.forClient().build()).buildProtocolNegotiator();
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }
}
