package io.grpc.xds.internal.certprovider;

import io.grpc.xds.internal.certprovider.CertificateProvider;
import io.grpc.xds.internal.sds.ReferenceCountingMap;

import java.io.Closeable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public final class CertificateProviderStore {
    private static final Logger logger = Logger.getLogger(CertificateProviderStore.class.getName());
    private static CertificateProviderStore instance;
    private final ReferenceCountingMap<CertProviderKey, CertificateProvider> certProviderMap = new ReferenceCountingMap<>(new CertProviderFactory());
    private final CertificateProviderRegistry certificateProviderRegistry;

    public CertificateProviderStore(CertificateProviderRegistry certificateProviderRegistry) {
        this.certificateProviderRegistry = certificateProviderRegistry;
    }

    public static synchronized CertificateProviderStore getInstance() {
        if (instance == null) {
            instance = new CertificateProviderStore(CertificateProviderRegistry.getInstance());
        }
        return instance;
    }

    public synchronized Handle createOrGetProvider(String str, String str2, Object obj, CertificateProvider.Watcher watcher, boolean z) {
        if (!z) {
            try {
                return createProviderHelper(str, str2, obj, watcher, true);
            } catch (UnsupportedOperationException e) {
                logger.log(Level.FINE, "Trying to get provider for notifyCertUpdates==true", (Throwable) e);
            }
        } else {
            return createProviderHelper(str, str2, obj, watcher, z);
        }
    }

    private synchronized Handle createProviderHelper(String str, String str2, Object obj, CertificateProvider.Watcher watcher, boolean z) {
        CertProviderKey certProviderKey;
        CertificateProvider certificateProvider;
        certProviderKey = new CertProviderKey(str, str2, z, obj);
        certificateProvider = (CertificateProvider) this.certProviderMap.get(certProviderKey);
        certificateProvider.getWatcher().addWatcher(watcher);
        return new Handle(certProviderKey, watcher, certificateProvider);
    }

    private static final class CertProviderKey {
        private final String certName;
        private final Object config;
        private final boolean notifyCertUpdates;
        private final String pluginName;

        private CertProviderKey(String str, String str2, boolean z, Object obj) {
            this.certName = str;
            this.pluginName = str2;
            this.notifyCertUpdates = z;
            this.config = obj;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CertProviderKey)) {
                return false;
            }
            CertProviderKey certProviderKey = (CertProviderKey) obj;
            return this.notifyCertUpdates == certProviderKey.notifyCertUpdates && Objects.equals(this.certName, certProviderKey.certName) && Objects.equals(this.pluginName, certProviderKey.pluginName) && Objects.equals(this.config, certProviderKey.config);
        }

        public int hashCode() {
            return Objects.hash(this.certName, this.pluginName, Boolean.valueOf(this.notifyCertUpdates), this.config);
        }

        public String toString() {
            return "CertProviderKey{certName='" + this.certName + "', pluginName='" + this.pluginName + "', notifyCertUpdates=" + this.notifyCertUpdates + ", config=" + this.config + '}';
        }
    }

    final class Handle implements Closeable {
        final CertificateProvider certProvider;
        private final CertProviderKey key;
        private final CertificateProvider.Watcher watcher;

        private Handle(CertProviderKey certProviderKey, CertificateProvider.Watcher watcher, CertificateProvider certificateProvider) {
            this.key = certProviderKey;
            this.watcher = watcher;
            this.certProvider = certificateProvider;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            this.certProvider.getWatcher().removeWatcher(this.watcher);
            CertificateProviderStore.this.certProviderMap.release(this.key, this.certProvider);
        }
    }

    private final class CertProviderFactory implements ReferenceCountingMap.ValueFactory<CertProviderKey, CertificateProvider> {
        private CertProviderFactory() {
        }

        @Override // io.grpc.xds.internal.sds.ReferenceCountingMap.ValueFactory
        public CertificateProvider create(CertProviderKey certProviderKey) {
            CertificateProviderProvider provider = CertificateProviderStore.this.certificateProviderRegistry.getProvider(certProviderKey.pluginName);
            if (provider == null) {
                throw new IllegalArgumentException("Provider not found for " + certProviderKey.pluginName);
            }
            CertificateProvider certificateProviderCreateCertificateProvider = provider.createCertificateProvider(certProviderKey.config, new CertificateProvider.DistributorWatcher(), certProviderKey.notifyCertUpdates);
            certificateProviderCreateCertificateProvider.start();
            return certificateProviderCreateCertificateProvider;
        }
    }
}
