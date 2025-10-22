package io.grpc.xds.internal.certprovider;

import io.grpc.xds.Bootstrapper;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.internal.certprovider.CertificateProvider;
import io.grpc.xds.internal.certprovider.CertificateProviderStore;
import io.grpc.xds.internal.sds.DynamicSslContextProvider;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
abstract class CertProviderSslContextProvider extends DynamicSslContextProvider implements CertificateProvider.Watcher {

    @Nullable
    private final CertificateProviderStore.Handle certHandle;

    @Nullable
    private final CommonTlsContext.CertificateProviderInstance certInstance;

    @Nullable
    private final CertificateProviderStore.Handle rootCertHandle;

    @Nullable
    private final CommonTlsContext.CertificateProviderInstance rootCertInstance;

    @Nullable
    protected List<X509Certificate> savedCertChain;

    @Nullable
    protected PrivateKey savedKey;

    @Nullable
    protected List<X509Certificate> savedTrustedRoots;

    protected CertProviderSslContextProvider(Node node, Map<String, Bootstrapper.CertificateProviderInfo> map, CommonTlsContext.CertificateProviderInstance certificateProviderInstance, CommonTlsContext.CertificateProviderInstance certificateProviderInstance2, CertificateValidationContext certificateValidationContext, EnvoyServerProtoData.BaseTlsContext baseTlsContext, CertificateProviderStore certificateProviderStore) {
        String instanceName;
        super(baseTlsContext, certificateValidationContext);
        this.certInstance = certificateProviderInstance;
        this.rootCertInstance = certificateProviderInstance2;
        if (certificateProviderInstance == null || !certificateProviderInstance.isInitialized()) {
            this.certHandle = null;
            instanceName = null;
        } else {
            instanceName = certificateProviderInstance.getInstanceName();
            Bootstrapper.CertificateProviderInfo certProviderConfig = getCertProviderConfig(map, instanceName);
            this.certHandle = certificateProviderStore.createOrGetProvider(certificateProviderInstance.getCertificateName(), certProviderConfig.getPluginName(), certProviderConfig.getConfig(), this, true);
        }
        if (certificateProviderInstance2 == null || !certificateProviderInstance2.isInitialized() || certificateProviderInstance2.getInstanceName().equals(instanceName)) {
            this.rootCertHandle = null;
        } else {
            Bootstrapper.CertificateProviderInfo certProviderConfig2 = getCertProviderConfig(map, certificateProviderInstance2.getInstanceName());
            this.rootCertHandle = certificateProviderStore.createOrGetProvider(certificateProviderInstance2.getCertificateName(), certProviderConfig2.getPluginName(), certProviderConfig2.getConfig(), this, true);
        }
    }

    private void clearKeysAndCerts() {
        this.savedKey = null;
        this.savedTrustedRoots = null;
        this.savedCertChain = null;
    }

    protected final boolean isClientSideTls() {
        return this.rootCertInstance != null && this.certInstance == null;
    }

    protected final boolean isMtls() {
        return (this.certInstance == null || this.rootCertInstance == null) ? false : true;
    }

    protected final boolean isServerSideTls() {
        return this.certInstance != null && this.rootCertInstance == null;
    }

    private Bootstrapper.CertificateProviderInfo getCertProviderConfig(Map<String, Bootstrapper.CertificateProviderInfo> map, String str) {
        return map.get(str);
    }

    @Override // io.grpc.xds.internal.certprovider.CertificateProvider.Watcher
    public final void updateCertificate(PrivateKey privateKey, List<X509Certificate> list) {
        this.savedKey = privateKey;
        this.savedCertChain = list;
        updateSslContextWhenReady();
    }

    @Override // io.grpc.xds.internal.certprovider.CertificateProvider.Watcher
    public final void updateTrustedRoots(List<X509Certificate> list) {
        this.savedTrustedRoots = list;
        updateSslContextWhenReady();
    }

    private void updateSslContextWhenReady() {
        if (isMtls()) {
            if (this.savedKey == null || this.savedTrustedRoots == null) {
                return;
            }
            updateSslContext();
            clearKeysAndCerts();
            return;
        }
        if (isClientSideTls()) {
            if (this.savedTrustedRoots != null) {
                updateSslContext();
                clearKeysAndCerts();
                return;
            }
            return;
        }
        if (!isServerSideTls() || this.savedKey == null) {
            return;
        }
        updateSslContext();
        clearKeysAndCerts();
    }

    @Override // io.grpc.xds.internal.sds.DynamicSslContextProvider
    protected final CertificateValidationContext generateCertificateValidationContext() {
        return this.staticCertificateValidationContext;
    }

    @Override
    // io.grpc.xds.internal.sds.SslContextProvider, io.grpc.xds.internal.sds.Closeable, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        CertificateProviderStore.Handle handle = this.certHandle;
        if (handle != null) {
            handle.close();
        }
        CertificateProviderStore.Handle handle2 = this.rootCertHandle;
        if (handle2 != null) {
            handle2.close();
        }
    }
}
