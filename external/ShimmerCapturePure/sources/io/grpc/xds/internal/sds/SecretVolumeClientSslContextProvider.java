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
final class SecretVolumeClientSslContextProvider extends SslContextProvider {

    @Nullable
    private final CertificateValidationContext certContext;

    @Nullable
    private final String certificateChain;

    @Nullable
    private final String privateKey;

    @Nullable
    private final String privateKeyPassword;

    private SecretVolumeClientSslContextProvider(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable CertificateValidationContext certificateValidationContext, EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
        super(upstreamTlsContext);
        this.privateKey = str;
        this.privateKeyPassword = str2;
        this.certificateChain = str3;
        this.certContext = certificateValidationContext;
    }

    static SecretVolumeClientSslContextProvider getProvider(EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext) {
        String str;
        String str2;
        String filename;
        Preconditions.checkNotNull(upstreamTlsContext, "upstreamTlsContext");
        CommonTlsContext commonTlsContext = upstreamTlsContext.getCommonTlsContext();
        Preconditions.checkArgument(commonTlsContext.getTlsCertificateSdsSecretConfigsCount() == 0, "unexpected TlsCertificateSdsSecretConfigs");
        CertificateValidationContext certificateValidationContext = CommonTlsContextUtil.getCertificateValidationContext(commonTlsContext);
        CommonTlsContextUtil.validateCertificateContext(certificateValidationContext, false);
        TlsCertificate tlsCertificates = commonTlsContext.getTlsCertificatesCount() > 0 ? commonTlsContext.getTlsCertificates(0) : null;
        if (tlsCertificates != null) {
            tlsCertificates = CommonTlsContextUtil.validateTlsCertificate(tlsCertificates, true);
        }
        if (tlsCertificates != null) {
            String filename2 = tlsCertificates.getPrivateKey().getFilename();
            String inlineString = tlsCertificates.hasPassword() ? tlsCertificates.getPassword().getInlineString() : null;
            filename = tlsCertificates.getCertificateChain().getFilename();
            str = filename2;
            str2 = inlineString;
        } else {
            str = null;
            str2 = null;
            filename = null;
        }
        return new SecretVolumeClientSslContextProvider(str, str2, filename, certificateValidationContext, upstreamTlsContext);
    }

    @Override
    // io.grpc.xds.internal.sds.SslContextProvider, io.grpc.xds.internal.sds.Closeable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // io.grpc.xds.internal.sds.SslContextProvider
    public void addCallback(SslContextProvider.Callback callback) {
        Preconditions.checkNotNull(callback, "callback");
        performCallback(new SslContextProvider.SslContextGetter() { // from class: io.grpc.xds.internal.sds.SecretVolumeClientSslContextProvider.1
            @Override // io.grpc.xds.internal.sds.SslContextProvider.SslContextGetter
            public SslContext get() throws IOException, CertificateException, CertStoreException {
                return SecretVolumeClientSslContextProvider.this.buildSslContextFromSecrets();
            }
        }, callback);
    }

    SslContext buildSslContextFromSecrets() throws IOException, CertificateException, CertStoreException {
        SslContextBuilder sslContextBuilderTrustManager = GrpcSslContexts.forClient().trustManager(new SdsTrustManagerFactory(this.certContext));
        if (this.privateKey != null && this.certificateChain != null) {
            sslContextBuilderTrustManager.keyManager(new File(this.certificateChain), new File(this.privateKey), this.privateKeyPassword);
        }
        return sslContextBuilderTrustManager.build();
    }
}
