package io.grpc.xds.internal.sds;

import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.internal.sds.trust.SdsTrustManagerFactory;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig;

import java.io.IOException;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
final class SdsClientSslContextProvider extends SdsSslContextProvider {
    private SdsClientSslContextProvider(Node node, SdsSecretConfig sdsSecretConfig, SdsSecretConfig sdsSecretConfig2, CertificateValidationContext certificateValidationContext, Executor executor, Executor executor2, EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
        super(node, sdsSecretConfig, sdsSecretConfig2, certificateValidationContext, executor, executor2, upstreamTlsContext);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static io.grpc.xds.internal.sds.SdsClientSslContextProvider getProvider(io.grpc.xds.EnvoyServerProtoData.UpstreamTlsContext r11, io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node r12, java.util.concurrent.Executor r13, java.util.concurrent.Executor r14) {
        /*
            java.lang.String r0 = "upstreamTlsContext"
            com.google.common.base.Preconditions.checkNotNull(r11, r0)
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext r0 = r11.getCommonTlsContext()
            boolean r1 = r0.hasCombinedValidationContext()
            r2 = 0
            if (r1 == 0) goto L2e
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext$CombinedCertificateValidationContext r1 = r0.getCombinedValidationContext()
            boolean r3 = r1.hasValidationContextSdsSecretConfig()
            if (r3 == 0) goto L1f
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig r3 = r1.getValidationContextSdsSecretConfig()
            goto L20
        L1f:
            r3 = r2
        L20:
            boolean r4 = r1.hasDefaultValidationContext()
            if (r4 == 0) goto L2b
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext r1 = r1.getDefaultValidationContext()
            goto L2c
        L2b:
            r1 = r2
        L2c:
            r7 = r1
            goto L39
        L2e:
            boolean r1 = r0.hasValidationContextSdsSecretConfig()
            if (r1 == 0) goto L3b
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig r3 = r0.getValidationContextSdsSecretConfig()
            r7 = r2
        L39:
            r6 = r3
            goto L4a
        L3b:
            boolean r1 = r0.hasValidationContext()
            if (r1 == 0) goto L48
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext r1 = r0.getValidationContext()
            r7 = r1
            r6 = r2
            goto L4a
        L48:
            r6 = r2
            r7 = r6
        L4a:
            int r1 = r0.getTlsCertificateSdsSecretConfigsCount()
            if (r1 <= 0) goto L55
            r1 = 0
            io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfig r2 = r0.getTlsCertificateSdsSecretConfigs(r1)
        L55:
            r5 = r2
            io.grpc.xds.internal.sds.SdsClientSslContextProvider r0 = new io.grpc.xds.internal.sds.SdsClientSslContextProvider
            r3 = r0
            r4 = r12
            r8 = r13
            r9 = r14
            r10 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.internal.sds.SdsClientSslContextProvider.getProvider(io.grpc.xds.EnvoyServerProtoData$UpstreamTlsContext, io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node, java.util.concurrent.Executor, java.util.concurrent.Executor):io.grpc.xds.internal.sds.SdsClientSslContextProvider");
    }

    @Override // io.grpc.xds.internal.sds.DynamicSslContextProvider
    protected final SslContextBuilder getSslContextBuilder(CertificateValidationContext certificateValidationContext) throws IOException, CertificateException, CertStoreException {
        SslContextBuilder sslContextBuilderTrustManager = GrpcSslContexts.forClient().trustManager(new SdsTrustManagerFactory(certificateValidationContext));
        if (this.tlsCertificate != null) {
            sslContextBuilderTrustManager.keyManager(this.tlsCertificate.getCertificateChain().getInlineBytes().newInput(), this.tlsCertificate.getPrivateKey().getInlineBytes().newInput(), this.tlsCertificate.hasPassword() ? this.tlsCertificate.getPassword().getInlineString() : null);
        }
        return sslContextBuilderTrustManager;
    }
}
