package io.grpc.xds.internal.sds;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.internal.sds.SslContextProvider;
import io.grpc.xds.internal.sds.trust.SdsTrustManagerFactory;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsCertificate;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
final class SecretVolumeServerSslContextProvider extends SslContextProvider {

    @Nullable
    private final CertificateValidationContext certContext;

    @Nullable
    private final String certificateChain;

    @Nullable
    private final String privateKey;

    @Nullable
    private final String privateKeyPassword;

    private SecretVolumeServerSslContextProvider(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable CertificateValidationContext certificateValidationContext, EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext) {
        super(downstreamTlsContext);
        this.privateKey = str;
        this.privateKeyPassword = str2;
        this.certificateChain = str3;
        this.certContext = certificateValidationContext;
    }

    static SecretVolumeServerSslContextProvider getProvider(EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext) {
        Preconditions.checkNotNull(downstreamTlsContext, "downstreamTlsContext");
        CommonTlsContext commonTlsContext = downstreamTlsContext.getCommonTlsContext();
        Preconditions.checkArgument(commonTlsContext.getTlsCertificateSdsSecretConfigsCount() == 0, "unexpected TlsCertificateSdsSecretConfigs");
        TlsCertificate tlsCertificates = commonTlsContext.getTlsCertificatesCount() > 0 ? commonTlsContext.getTlsCertificates(0) : null;
        CommonTlsContextUtil.validateTlsCertificate(tlsCertificates, false);
        CertificateValidationContext certificateValidationContext = CommonTlsContextUtil.getCertificateValidationContext(commonTlsContext);
        if (certificateValidationContext != null) {
            certificateValidationContext = CommonTlsContextUtil.validateCertificateContext(certificateValidationContext, true);
        }
        CertificateValidationContext certificateValidationContext2 = certificateValidationContext;
        return new SecretVolumeServerSslContextProvider(tlsCertificates.getPrivateKey().getFilename(), tlsCertificates.hasPassword() ? tlsCertificates.getPassword().getInlineString() : null, tlsCertificates.getCertificateChain().getFilename(), certificateValidationContext2, downstreamTlsContext);
    }

    @Override
    // io.grpc.xds.internal.sds.SslContextProvider, io.grpc.xds.internal.sds.Closeable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // io.grpc.xds.internal.sds.SslContextProvider
    public void addCallback(SslContextProvider.Callback callback) {
        Preconditions.checkNotNull(callback, "callback");
        performCallback(new SslContextProvider.SslContextGetter() { // from class: io.grpc.xds.internal.sds.SecretVolumeServerSslContextProvider.1
            @Override // io.grpc.xds.internal.sds.SslContextProvider.SslContextGetter
            public SslContext get() throws IOException, CertificateException, CertStoreException {
                return SecretVolumeServerSslContextProvider.this.buildSslContextFromSecrets();
            }
        }, callback);
    }

    SslContext buildSslContextFromSecrets() throws IOException, CertificateException, CertStoreException {
        SslContextBuilder sslContextBuilderForServer = GrpcSslContexts.forServer(new File(this.certificateChain), new File(this.privateKey), this.privateKeyPassword);
        setClientAuthValues(sslContextBuilderForServer, this.certContext != null ? new SdsTrustManagerFactory(this.certContext) : null);
        return sslContextBuilderForServer.build();
    }
}
