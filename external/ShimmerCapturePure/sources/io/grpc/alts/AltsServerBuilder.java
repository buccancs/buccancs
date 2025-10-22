package io.grpc.alts;

import io.grpc.BindableService;
import io.grpc.Channel;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.Status;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public final class AltsServerBuilder extends ServerBuilder<AltsServerBuilder> {
    private static final Logger logger = Logger.getLogger(AltsServerBuilder.class.getName());
    private final NettyServerBuilder delegate;
    private boolean enableUntrustedAlts;
    private ObjectPool<Channel> handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL);

    private AltsServerBuilder(NettyServerBuilder nettyServerBuilder) {
        this.delegate = nettyServerBuilder;
    }

    public static AltsServerBuilder forPort(int i) {
        return new AltsServerBuilder(NettyServerBuilder.forAddress(new InetSocketAddress(i)));
    }

    public AltsServerBuilder enableUntrustedAltsForTesting() {
        this.enableUntrustedAlts = true;
        return this;
    }

    public AltsServerBuilder setHandshakerAddressForTesting(String str) {
        this.handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.getHandshakerChannelForTesting(str));
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder handshakeTimeout(long j, TimeUnit timeUnit) {
        this.delegate.handshakeTimeout(j, timeUnit);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder directExecutor() {
        this.delegate.directExecutor();
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.delegate.addStreamTracerFactory(factory);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.delegate.addTransportFilter(serverTransportFilter);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder executor(Executor executor) {
        this.delegate.executor(executor);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder addService(ServerServiceDefinition serverServiceDefinition) {
        this.delegate.addService(serverServiceDefinition);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder addService(BindableService bindableService) {
        this.delegate.addService(bindableService);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder fallbackHandlerRegistry(HandlerRegistry handlerRegistry) {
        this.delegate.fallbackHandlerRegistry(handlerRegistry);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("Can't set TLS settings for ALTS");
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder decompressorRegistry(DecompressorRegistry decompressorRegistry) {
        this.delegate.decompressorRegistry(decompressorRegistry);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder compressorRegistry(CompressorRegistry compressorRegistry) {
        this.delegate.compressorRegistry(compressorRegistry);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public AltsServerBuilder intercept(ServerInterceptor serverInterceptor) {
        this.delegate.intercept(serverInterceptor);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public Server build() {
        if (!CheckGcpEnvironment.isOnGcp()) {
            if (this.enableUntrustedAlts) {
                logger.log(Level.WARNING, "Untrusted ALTS mode is enabled and we cannot guarantee the trustworthiness of the ALTS handshaker service");
            } else {
                this.delegate.intercept((ServerInterceptor) new FailingServerInterceptor(Status.INTERNAL.withDescription("ALTS is only allowed to run on Google Cloud Platform")));
            }
        }
        this.delegate.protocolNegotiator(AltsProtocolNegotiator.serverAltsProtocolNegotiator(this.handshakerChannelPool));
        return this.delegate.build();
    }

    static final class FailingServerInterceptor implements ServerInterceptor {
        private final Status status;

        public FailingServerInterceptor(Status status) {
            this.status = status;
        }

        @Override // io.grpc.ServerInterceptor
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
            serverCall.close(this.status, new Metadata());
            return new ServerCall.Listener<ReqT>() { // from class: io.grpc.alts.AltsServerBuilder.FailingServerInterceptor.1
            };
        }
    }
}
