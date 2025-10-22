package io.grpc.alts;

import com.google.common.collect.ImmutableList;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public final class AltsChannelBuilder extends ForwardingChannelBuilder<AltsChannelBuilder> {
    private static final Logger logger = Logger.getLogger(AltsChannelBuilder.class.getName());
    private final NettyChannelBuilder delegate;
    private final ImmutableList.Builder<String> targetServiceAccountsBuilder = ImmutableList.builder();
    private boolean enableUntrustedAlts;
    private ObjectPool<Channel> handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL);

    private AltsChannelBuilder(String str) {
        this.delegate = NettyChannelBuilder.forTarget(str);
    }

    public static final AltsChannelBuilder forTarget(String str) {
        return new AltsChannelBuilder(str);
    }

    public static AltsChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ForwardingChannelBuilder
    public NettyChannelBuilder delegate() {
        return this.delegate;
    }

    public AltsChannelBuilder enableUntrustedAltsForTesting() {
        this.enableUntrustedAlts = true;
        return this;
    }

    public AltsChannelBuilder addTargetServiceAccount(String str) {
        this.targetServiceAccountsBuilder.add((ImmutableList.Builder<String>) str);
        return this;
    }

    public AltsChannelBuilder setHandshakerAddressForTesting(String str) {
        this.handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.getHandshakerChannelForTesting(str));
        return this;
    }

    @Override // io.grpc.ForwardingChannelBuilder, io.grpc.ManagedChannelBuilder
    public ManagedChannel build() {
        if (!CheckGcpEnvironment.isOnGcp()) {
            if (this.enableUntrustedAlts) {
                logger.log(Level.WARNING, "Untrusted ALTS mode is enabled and we cannot guarantee the trustworthiness of the ALTS handshaker service");
            } else {
                delegate().intercept(new ClientInterceptor[]{new FailingClientInterceptor(Status.INTERNAL.withDescription("ALTS is only allowed to run on Google Cloud Platform"))});
            }
        }
        InternalNettyChannelBuilder.setProtocolNegotiatorFactory(delegate(), new AltsProtocolNegotiator.ClientAltsProtocolNegotiatorFactory(this.targetServiceAccountsBuilder.build(), this.handshakerChannelPool));
        return delegate().build();
    }

    @Nullable
    InternalProtocolNegotiator.ProtocolNegotiator getProtocolNegotiatorForTest() {
        return new AltsProtocolNegotiator.ClientAltsProtocolNegotiatorFactory(this.targetServiceAccountsBuilder.build(), this.handshakerChannelPool).buildProtocolNegotiator();
    }

    static final class FailingClientInterceptor implements ClientInterceptor {
        private final Status status;

        public FailingClientInterceptor(Status status) {
            this.status = status;
        }

        @Override // io.grpc.ClientInterceptor
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            return new FailingClientCall(this.status);
        }
    }
}
