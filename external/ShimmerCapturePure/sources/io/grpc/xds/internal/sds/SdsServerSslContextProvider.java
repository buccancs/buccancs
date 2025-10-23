package io.grpc.xds.internal.sds;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.internal.sds.trust.SdsTrustManagerFactory;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig;

import java.io.IOException;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
final class SdsServerSslContextProvider extends SdsSslContextProvider {
    private SdsServerSslContextProvider(Node node, SdsSecretConfig sdsSecretConfig, SdsSecretConfig sdsSecretConfig2, Executor executor, Executor executor2, EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext) {
        super(node, sdsSecretConfig, sdsSecretConfig2, null, executor, executor2, downstreamTlsContext);
    }

    static SdsServerSslContextProvider getProvider(EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext, Node node, Executor executor, Executor executor2) {
        Preconditions.checkNotNull(downstreamTlsContext, "downstreamTlsContext");
        CommonTlsContext commonTlsContext = downstreamTlsContext.getCommonTlsContext();
        return new SdsServerSslContextProvider(node, commonTlsContext.getTlsCertificateSdsSecretConfigsCount() > 0 ? commonTlsContext.getTlsCertificateSdsSecretConfigs(0) : null, commonTlsContext.hasValidationContextSdsSecretConfig() ? commonTlsContext.getValidationContextSdsSecretConfig() : null, executor, executor2, downstreamTlsContext);
    }

    @Override // io.grpc.xds.internal.sds.DynamicSslContextProvider
    protected final SslContextBuilder getSslContextBuilder(CertificateValidationContext certificateValidationContext) throws IOException, CertificateException, CertStoreException {
        SslContextBuilder sslContextBuilderForServer = GrpcSslContexts.forServer(this.tlsCertificate.getCertificateChain().getInlineBytes().newInput(), this.tlsCertificate.getPrivateKey().getInlineBytes().newInput(), this.tlsCertificate.hasPassword() ? this.tlsCertificate.getPassword().getInlineString() : null);
        setClientAuthValues(sslContextBuilderForServer, certificateValidationContext != null ? new SdsTrustManagerFactory(certificateValidationContext) : null);
        return sslContextBuilderForServer;
    }
}
