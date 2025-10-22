package io.grpc.xds.internal.sds;

import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.xds.internal.sds.SdsProtocolNegotiators;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public final class XdsServerBuilder extends ServerBuilder<XdsServerBuilder> {
    private final NettyServerBuilder delegate;
    private final int port;
    private InternalProtocolNegotiator.ProtocolNegotiator fallbackProtocolNegotiator;

    private XdsServerBuilder(NettyServerBuilder nettyServerBuilder, int i) {
        this.delegate = nettyServerBuilder;
        this.port = i;
    }

    public static XdsServerBuilder forPort(int i) {
        return new XdsServerBuilder(NettyServerBuilder.forAddress(new InetSocketAddress(i)), i);
    }

    public XdsServerBuilder fallbackProtocolNegotiator(InternalProtocolNegotiator.ProtocolNegotiator protocolNegotiator) {
        this.fallbackProtocolNegotiator = protocolNegotiator;
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder handshakeTimeout(long j, TimeUnit timeUnit) {
        this.delegate.handshakeTimeout(j, timeUnit);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder directExecutor() {
        this.delegate.directExecutor();
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.delegate.addStreamTracerFactory(factory);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.delegate.addTransportFilter(serverTransportFilter);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder executor(Executor executor) {
        this.delegate.executor(executor);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder addService(ServerServiceDefinition serverServiceDefinition) {
        this.delegate.addService(serverServiceDefinition);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder addService(BindableService bindableService) {
        this.delegate.addService(bindableService);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        this.delegate.fallbackHandlerRegistry(handlerRegistry);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("Cannot set security parameters on XdsServerBuilder");
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry) {
        this.delegate.decompressorRegistry(decompressorRegistry);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder compressorRegistry(@Nullable CompressorRegistry compressorRegistry) {
        this.delegate.compressorRegistry(compressorRegistry);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public XdsServerBuilder intercept(ServerInterceptor serverInterceptor) {
        this.delegate.intercept(serverInterceptor);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public Server build() {
        return buildServer(SdsProtocolNegotiators.serverProtocolNegotiator(this.port, this.fallbackProtocolNegotiator));
    }

    public ServerWrapperForXds buildServer(SdsProtocolNegotiators.ServerSdsProtocolNegotiator serverSdsProtocolNegotiator) {
        this.delegate.protocolNegotiator(serverSdsProtocolNegotiator);
        return new ServerWrapperForXds(this.delegate.build(), serverSdsProtocolNegotiator.getXdsClientWrapperForServerSds());
    }
}
