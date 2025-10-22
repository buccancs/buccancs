package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.shimmerresearch.driver.ShimmerObject;
import io.grpc.alts.CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateCallback;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SniHostNameMatcher;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
public final class ReferenceCountedOpenSslServerContext extends ReferenceCountedOpenSslContext {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslServerContext.class);
    private static final byte[] ID = {ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.SET_SHIMMERNAME_COMMAND};
    private static final boolean ENABLE_SESSION_TICKET = SystemPropertyUtil.getBoolean("jdk.tls.server.enableSessionTicketExtension", false);
    private final OpenSslServerSessionContext sessionContext;

    ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2) throws SSLException {
        this(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2, clientAuth, strArr, z, z2, str2);
    }

    ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2) throws SSLException {
        super(iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, j, j2, 1, (Certificate[]) x509CertificateArr2, clientAuth, strArr, z, z2, true);
        try {
            OpenSslServerSessionContext openSslServerSessionContextNewSessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, str2);
            this.sessionContext = openSslServerSessionContextNewSessionContext;
            if (ENABLE_SESSION_TICKET) {
                openSslServerSessionContextNewSessionContext.setTicketKeys(new OpenSslSessionTicketKey[0]);
            }
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    static OpenSslServerSessionContext newSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, long j, OpenSslEngineMap openSslEngineMap, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, String str2) throws Throwable {
        OpenSslKeyMaterialProvider openSslKeyMaterialProviderProviderFor;
        KeyManagerFactory openSslCachingX509KeyManagerFactory;
        OpenSslKeyMaterialProvider openSslKeyMaterialProvider = null;
        try {
            try {
                SSLContext.setVerify(j, 0, 10);
                if (OpenSsl.useKeyManagerFactory()) {
                    if (keyManagerFactory == null) {
                        char[] cArrKeyStorePassword = keyStorePassword(str);
                        KeyStore keyStoreBuildKeyStore = buildKeyStore(x509CertificateArr2, privateKey, cArrKeyStorePassword, str2);
                        if (keyStoreBuildKeyStore.aliases().hasMoreElements()) {
                            openSslCachingX509KeyManagerFactory = new OpenSslX509KeyManagerFactory();
                        } else {
                            openSslCachingX509KeyManagerFactory = new OpenSslCachingX509KeyManagerFactory(KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()));
                        }
                        openSslCachingX509KeyManagerFactory.init(keyStoreBuildKeyStore, cArrKeyStorePassword);
                        keyManagerFactory = openSslCachingX509KeyManagerFactory;
                    }
                    openSslKeyMaterialProviderProviderFor = providerFor(keyManagerFactory, str);
                    try {
                        try {
                            SSLContext.setCertificateCallback(j, new OpenSslServerCertificateCallback(openSslEngineMap, new OpenSslKeyMaterialManager(openSslKeyMaterialProviderProviderFor)));
                        } catch (Exception e) {
                            e = e;
                            throw new SSLException("failed to set certificate and key", e);
                        }
                    } catch (Throwable th) {
                        th = th;
                        openSslKeyMaterialProvider = openSslKeyMaterialProviderProviderFor;
                        if (openSslKeyMaterialProvider != null) {
                            openSslKeyMaterialProvider.destroy();
                        }
                        throw th;
                    }
                } else {
                    if (keyManagerFactory != null) {
                        throw new IllegalArgumentException("KeyManagerFactory not supported");
                    }
                    ObjectUtil.checkNotNull(x509CertificateArr2, "keyCertChain");
                    setKeyMaterial(j, x509CertificateArr2, privateKey, str);
                    openSslKeyMaterialProviderProviderFor = null;
                }
                try {
                    if (x509CertificateArr != null) {
                        trustManagerFactory = buildTrustManagerFactory(x509CertificateArr, trustManagerFactory, str2);
                    } else if (trustManagerFactory == null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init((KeyStore) null);
                    }
                    X509TrustManager x509TrustManagerChooseTrustManager = chooseTrustManager(trustManagerFactory.getTrustManagers());
                    setVerifyCallback(j, openSslEngineMap, x509TrustManagerChooseTrustManager);
                    X509Certificate[] acceptedIssuers = x509TrustManagerChooseTrustManager.getAcceptedIssuers();
                    if (acceptedIssuers != null && acceptedIssuers.length > 0) {
                        long bio = 0;
                        try {
                            bio = toBIO(ByteBufAllocator.DEFAULT, acceptedIssuers);
                            if (!SSLContext.setCACertificateBio(j, bio)) {
                                throw new SSLException("unable to setup accepted issuers for trustmanager " + x509TrustManagerChooseTrustManager);
                            }
                        } finally {
                            freeBio(bio);
                        }
                    }
                    if (PlatformDependent.javaVersion() >= 8) {
                        SSLContext.setSniHostnameMatcher(j, new OpenSslSniHostnameMatcher(openSslEngineMap));
                    }
                    OpenSslServerSessionContext openSslServerSessionContext = new OpenSslServerSessionContext(referenceCountedOpenSslContext, openSslKeyMaterialProviderProviderFor);
                    openSslServerSessionContext.setSessionIdContext(ID);
                    return openSslServerSessionContext;
                } catch (SSLException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new SSLException("unable to setup trustmanager", e3);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    private static void setVerifyCallback(long j, OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
        if (useExtendedTrustManager(x509TrustManager)) {
            SSLContext.setCertVerifyCallback(j, new ExtendedTrustManagerVerifyCallback(openSslEngineMap, CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6314m((Object) x509TrustManager)));
        } else {
            SSLContext.setCertVerifyCallback(j, new TrustManagerVerifyCallback(openSslEngineMap, x509TrustManager));
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext, io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public OpenSslServerSessionContext sessionContext() {
        return this.sessionContext;
    }

    private static final class OpenSslServerCertificateCallback implements CertificateCallback {
        private final OpenSslEngineMap engineMap;
        private final OpenSslKeyMaterialManager keyManagerHolder;

        OpenSslServerCertificateCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

        @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateCallback
        public void handle(long j, byte[] bArr, byte[][] bArr2) throws Exception {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine == null) {
                return;
            }
            try {
                this.keyManagerHolder.setKeyMaterialServerSide(referenceCountedOpenSslEngine);
            } catch (Throwable th) {
                ReferenceCountedOpenSslServerContext.logger.debug("Failed to set the server-side key material", th);
                referenceCountedOpenSslEngine.initHandshakeException(th);
            }
        }
    }

    private static final class TrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509TrustManager manager;

        TrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
            super(openSslEngineMap);
            this.manager = x509TrustManager;
        }

        @Override
            // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext.AbstractCertificateVerifier
        void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str);
        }
    }

    private static final class ExtendedTrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509ExtendedTrustManager manager;

        ExtendedTrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509ExtendedTrustManager x509ExtendedTrustManager) {
            super(openSslEngineMap);
            this.manager = OpenSslTlsv13X509ExtendedTrustManager.wrap(x509ExtendedTrustManager);
        }

        @Override
            // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext.AbstractCertificateVerifier
        void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    private static final class OpenSslSniHostnameMatcher implements SniHostNameMatcher {
        private final OpenSslEngineMap engineMap;

        OpenSslSniHostnameMatcher(OpenSslEngineMap openSslEngineMap) {
            this.engineMap = openSslEngineMap;
        }

        @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.SniHostNameMatcher
        public boolean match(long j, String str) {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine != null) {
                return referenceCountedOpenSslEngine.checkSniHostnameMatch(str.getBytes(CharsetUtil.UTF_8));
            }
            ReferenceCountedOpenSslServerContext.logger.warn("No ReferenceCountedOpenSslEngine found for SSL pointer: {}", Long.valueOf(j));
            return false;
        }
    }
}
