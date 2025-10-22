package io.grpc.xds.internal.sds;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiationEvent;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiators;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiationEvent;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.XdsAttributes;
import io.grpc.xds.XdsClientWrapperForServerSds;
import io.grpc.xds.internal.sds.SslContextProvider;

import java.security.cert.CertStoreException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

import org.apache.http.HttpHost;

/* loaded from: classes3.dex */
public final class SdsProtocolNegotiators {
    private static final Logger logger = Logger.getLogger(SdsProtocolNegotiators.class.getName());
    private static final AsciiString SCHEME = AsciiString.of(HttpHost.DEFAULT_SCHEME_NAME);

    private SdsProtocolNegotiators() {
    }

    public static InternalNettyChannelBuilder.ProtocolNegotiatorFactory clientProtocolNegotiatorFactory() {
        return new ClientSdsProtocolNegotiatorFactory();
    }

    public static ServerSdsProtocolNegotiator serverProtocolNegotiator(int i, @Nullable InternalProtocolNegotiator.ProtocolNegotiator protocolNegotiator) {
        return new ServerSdsProtocolNegotiator(new XdsClientWrapperForServerSds(i), protocolNegotiator);
    }

    private static final class ClientSdsProtocolNegotiatorFactory implements InternalNettyChannelBuilder.ProtocolNegotiatorFactory {
        private ClientSdsProtocolNegotiatorFactory() {
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.ProtocolNegotiatorFactory
        public InternalProtocolNegotiator.ProtocolNegotiator buildProtocolNegotiator() {
            final ClientSdsProtocolNegotiator clientSdsProtocolNegotiator = new ClientSdsProtocolNegotiator();
            return new InternalProtocolNegotiator.ProtocolNegotiator() { // from class: io.grpc.xds.internal.sds.SdsProtocolNegotiators.ClientSdsProtocolNegotiatorFactory.1LocalSdsNegotiator
                @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
                public AsciiString scheme() {
                    return clientSdsProtocolNegotiator.scheme();
                }

                @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
                public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
                    return clientSdsProtocolNegotiator.newHandler(grpcHttp2ConnectionHandler);
                }

                @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
                public void close() {
                    clientSdsProtocolNegotiator.close();
                }
            };
        }
    }

    static final class ClientSdsProtocolNegotiator implements InternalProtocolNegotiator.ProtocolNegotiator {
        ClientSdsProtocolNegotiator() {
        }

        private static boolean isTlsContextEmpty(EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
            return upstreamTlsContext == null || upstreamTlsContext.getCommonTlsContext() == null;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public void close() {
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public AsciiString scheme() {
            return SdsProtocolNegotiators.SCHEME;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
            EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext = (EnvoyServerProtoData.UpstreamTlsContext) grpcHttp2ConnectionHandler.getEagAttributes().get(XdsAttributes.ATTR_UPSTREAM_TLS_CONTEXT);
            if (isTlsContextEmpty(upstreamTlsContext)) {
                return InternalProtocolNegotiators.plaintext().newHandler(grpcHttp2ConnectionHandler);
            }
            return new ClientSdsHandler(grpcHttp2ConnectionHandler, upstreamTlsContext);
        }
    }

    private static class BufferReadsHandler extends ChannelInboundHandlerAdapter {
        private final List<Object> reads;
        private boolean readComplete;

        private BufferReadsHandler() {
            this.reads = new ArrayList();
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
            this.readComplete = true;
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
            this.reads.add(obj);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
            Iterator<Object> it2 = this.reads.iterator();
            while (it2.hasNext()) {
                super.channelRead(channelHandlerContext, it2.next());
            }
            if (this.readComplete) {
                super.channelReadComplete(channelHandlerContext);
            }
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
            SdsProtocolNegotiators.logger.log(Level.SEVERE, "exceptionCaught", th);
            channelHandlerContext.fireExceptionCaught(th);
        }
    }

    static final class ClientSdsHandler extends InternalProtocolNegotiators.ProtocolNegotiationHandler {
        private final GrpcHttp2ConnectionHandler grpcHandler;
        private final EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext;

        ClientSdsHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler, EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
            super(new ChannelHandlerAdapter() { // from class: io.grpc.xds.internal.sds.SdsProtocolNegotiators.ClientSdsHandler.1
                @Override
                // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
                public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                }
            });
            Preconditions.checkNotNull(grpcHttp2ConnectionHandler, "grpcHandler");
            this.grpcHandler = grpcHttp2ConnectionHandler;
            this.upstreamTlsContext = upstreamTlsContext;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators.ProtocolNegotiationHandler
        protected void handlerAdded0(final ChannelHandlerContext channelHandlerContext) {
            final BufferReadsHandler bufferReadsHandler = new BufferReadsHandler();
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), null, bufferReadsHandler);
            final SslContextProvider sslContextProviderFindOrCreateClientSslContextProvider = TlsContextManagerImpl.getInstance().findOrCreateClientSslContextProvider(this.upstreamTlsContext);
            sslContextProviderFindOrCreateClientSslContextProvider.addCallback(new SslContextProvider.Callback(channelHandlerContext.executor()) { // from class: io.grpc.xds.internal.sds.SdsProtocolNegotiators.ClientSdsHandler.2
                @Override // io.grpc.xds.internal.sds.SslContextProvider.Callback
                public void updateSecret(SslContext sslContext) {
                    SdsProtocolNegotiators.logger.log(Level.FINEST, "ClientSdsHandler.updateSecret authority={0}, ctx.name={1}", new Object[]{ClientSdsHandler.this.grpcHandler.getAuthority(), channelHandlerContext.name()});
                    channelHandlerContext.pipeline().addAfter(channelHandlerContext.name(), null, InternalProtocolNegotiators.tls(sslContext).newHandler(ClientSdsHandler.this.grpcHandler));
                    ClientSdsHandler.this.fireProtocolNegotiationEvent(channelHandlerContext);
                    channelHandlerContext.pipeline().remove(bufferReadsHandler);
                    TlsContextManagerImpl.getInstance().releaseClientSslContextProvider(sslContextProviderFindOrCreateClientSslContextProvider);
                }

                @Override // io.grpc.xds.internal.sds.SslContextProvider.Callback
                public void onException(Throwable th) {
                    channelHandlerContext.fireExceptionCaught(th);
                }
            });
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
            SdsProtocolNegotiators.logger.log(Level.SEVERE, "exceptionCaught", th);
            channelHandlerContext.fireExceptionCaught(th);
        }
    }

    public static final class ServerSdsProtocolNegotiator implements InternalProtocolNegotiator.ProtocolNegotiator {

        @Nullable
        private final InternalProtocolNegotiator.ProtocolNegotiator fallbackProtocolNegotiator;
        private final XdsClientWrapperForServerSds xdsClientWrapperForServerSds;

        public ServerSdsProtocolNegotiator(XdsClientWrapperForServerSds xdsClientWrapperForServerSds, @Nullable InternalProtocolNegotiator.ProtocolNegotiator protocolNegotiator) {
            this.xdsClientWrapperForServerSds = (XdsClientWrapperForServerSds) Preconditions.checkNotNull(xdsClientWrapperForServerSds, "xdsClientWrapperForServerSds");
            this.fallbackProtocolNegotiator = protocolNegotiator;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public void close() {
        }

        XdsClientWrapperForServerSds getXdsClientWrapperForServerSds() {
            return this.xdsClientWrapperForServerSds;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public AsciiString scheme() {
            return SdsProtocolNegotiators.SCHEME;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
            return new HandlerPickerHandler(grpcHttp2ConnectionHandler, this.xdsClientWrapperForServerSds, this.fallbackProtocolNegotiator);
        }
    }

    static final class HandlerPickerHandler extends ChannelInboundHandlerAdapter {

        @Nullable
        private final InternalProtocolNegotiator.ProtocolNegotiator fallbackProtocolNegotiator;
        private final GrpcHttp2ConnectionHandler grpcHandler;
        private final XdsClientWrapperForServerSds xdsClientWrapperForServerSds;

        HandlerPickerHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler, @Nullable XdsClientWrapperForServerSds xdsClientWrapperForServerSds, InternalProtocolNegotiator.ProtocolNegotiator protocolNegotiator) {
            this.grpcHandler = (GrpcHttp2ConnectionHandler) Preconditions.checkNotNull(grpcHttp2ConnectionHandler, "grpcHandler");
            this.xdsClientWrapperForServerSds = xdsClientWrapperForServerSds;
            this.fallbackProtocolNegotiator = protocolNegotiator;
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            if (obj instanceof ProtocolNegotiationEvent) {
                XdsClientWrapperForServerSds xdsClientWrapperForServerSds = this.xdsClientWrapperForServerSds;
                EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext = xdsClientWrapperForServerSds == null ? null : xdsClientWrapperForServerSds.getDownstreamTlsContext(channelHandlerContext.channel());
                if (downstreamTlsContext != null) {
                    channelHandlerContext.pipeline().replace(this, (String) null, new ServerSdsHandler(this.grpcHandler, downstreamTlsContext, this.fallbackProtocolNegotiator));
                    channelHandlerContext.fireUserEventTriggered((Object) InternalProtocolNegotiationEvent.getDefault());
                    return;
                } else {
                    if (this.fallbackProtocolNegotiator == null) {
                        channelHandlerContext.fireExceptionCaught((Throwable) new CertStoreException("No certificate source found!"));
                        return;
                    }
                    SdsProtocolNegotiators.logger.log(Level.INFO, "Using fallback for {0}", channelHandlerContext.channel().localAddress());
                    channelHandlerContext.pipeline().replace(this, (String) null, this.fallbackProtocolNegotiator.newHandler(this.grpcHandler));
                    channelHandlerContext.fireUserEventTriggered((Object) InternalProtocolNegotiationEvent.getDefault());
                    return;
                }
            }
            super.userEventTriggered(channelHandlerContext, obj);
        }
    }

    static final class ServerSdsHandler extends InternalProtocolNegotiators.ProtocolNegotiationHandler {
        private final EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext;

        @Nullable
        private final InternalProtocolNegotiator.ProtocolNegotiator fallbackProtocolNegotiator;
        private final GrpcHttp2ConnectionHandler grpcHandler;

        ServerSdsHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler, EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext, InternalProtocolNegotiator.ProtocolNegotiator protocolNegotiator) {
            super(new ChannelHandlerAdapter() { // from class: io.grpc.xds.internal.sds.SdsProtocolNegotiators.ServerSdsHandler.1
                @Override
                // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
                public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                }
            });
            Preconditions.checkNotNull(grpcHttp2ConnectionHandler, "grpcHandler");
            this.grpcHandler = grpcHttp2ConnectionHandler;
            this.downstreamTlsContext = downstreamTlsContext;
            this.fallbackProtocolNegotiator = protocolNegotiator;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators.ProtocolNegotiationHandler
        protected void handlerAdded0(final ChannelHandlerContext channelHandlerContext) {
            final BufferReadsHandler bufferReadsHandler = new BufferReadsHandler();
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), null, bufferReadsHandler);
            try {
                final SslContextProvider sslContextProviderFindOrCreateServerSslContextProvider = TlsContextManagerImpl.getInstance().findOrCreateServerSslContextProvider(this.downstreamTlsContext);
                sslContextProviderFindOrCreateServerSslContextProvider.addCallback(new SslContextProvider.Callback(channelHandlerContext.executor()) { // from class: io.grpc.xds.internal.sds.SdsProtocolNegotiators.ServerSdsHandler.2
                    @Override // io.grpc.xds.internal.sds.SslContextProvider.Callback
                    public void updateSecret(SslContext sslContext) {
                        channelHandlerContext.pipeline().addAfter(channelHandlerContext.name(), null, InternalProtocolNegotiators.serverTls(sslContext).newHandler(ServerSdsHandler.this.grpcHandler));
                        ServerSdsHandler.this.fireProtocolNegotiationEvent(channelHandlerContext);
                        channelHandlerContext.pipeline().remove(bufferReadsHandler);
                        TlsContextManagerImpl.getInstance().releaseServerSslContextProvider(sslContextProviderFindOrCreateServerSslContextProvider);
                    }

                    @Override // io.grpc.xds.internal.sds.SslContextProvider.Callback
                    public void onException(Throwable th) {
                        channelHandlerContext.fireExceptionCaught(th);
                    }
                });
            } catch (Exception e) {
                if (this.fallbackProtocolNegotiator == null) {
                    channelHandlerContext.fireExceptionCaught((Throwable) new CertStoreException("No certificate source found!", e));
                    return;
                }
                SdsProtocolNegotiators.logger.log(Level.INFO, "Using fallback for {0}", channelHandlerContext.channel().localAddress());
                channelHandlerContext.pipeline().replace(this, (String) null, this.fallbackProtocolNegotiator.newHandler(this.grpcHandler));
                channelHandlerContext.pipeline().remove(bufferReadsHandler);
            }
        }
    }
}
