package io.grpc.xds.internal.certprovider;

import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.xds.internal.sds.Closeable;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class CertificateProvider implements Closeable {
    private final boolean notifyCertUpdates;
    private final DistributorWatcher watcher;

    protected CertificateProvider(DistributorWatcher distributorWatcher, boolean z) {
        this.watcher = distributorWatcher;
        this.notifyCertUpdates = z;
    }

    @Override // io.grpc.xds.internal.sds.Closeable, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public DistributorWatcher getWatcher() {
        return this.watcher;
    }

    public boolean isNotifyCertUpdates() {
        return this.notifyCertUpdates;
    }

    public abstract void start();

    public interface Watcher {
        void onError(Status status);

        void updateCertificate(PrivateKey privateKey, List<X509Certificate> list);

        void updateTrustedRoots(List<X509Certificate> list);
    }

    public static final class DistributorWatcher implements Watcher {
        final Set<Watcher> downstreamWatchers = new HashSet();
        private List<X509Certificate> certChain;
        private PrivateKey privateKey;
        private List<X509Certificate> trustedRoots;

        void clearValues() {
            this.privateKey = null;
            this.certChain = null;
            this.trustedRoots = null;
        }

        synchronized void addWatcher(Watcher watcher) {
            this.downstreamWatchers.add(watcher);
            if (this.privateKey != null && this.certChain != null) {
                sendLastCertificateUpdate(watcher);
            }
            if (this.trustedRoots != null) {
                sendLastTrustedRootsUpdate(watcher);
            }
        }

        synchronized void removeWatcher(Watcher watcher) {
            this.downstreamWatchers.remove(watcher);
        }

        public Set<Watcher> getDownstreamWatchers() {
            return Collections.unmodifiableSet(this.downstreamWatchers);
        }

        private void sendLastCertificateUpdate(Watcher watcher) {
            watcher.updateCertificate(this.privateKey, this.certChain);
        }

        private void sendLastTrustedRootsUpdate(Watcher watcher) {
            watcher.updateTrustedRoots(this.trustedRoots);
        }

        @Override // io.grpc.xds.internal.certprovider.CertificateProvider.Watcher
        public synchronized void updateCertificate(PrivateKey privateKey, List<X509Certificate> list) {
            Preconditions.checkNotNull(privateKey, "key");
            Preconditions.checkNotNull(list, "certChain");
            this.privateKey = privateKey;
            this.certChain = list;
            Iterator<Watcher> it2 = this.downstreamWatchers.iterator();
            while (it2.hasNext()) {
                sendLastCertificateUpdate(it2.next());
            }
        }

        @Override // io.grpc.xds.internal.certprovider.CertificateProvider.Watcher
        public synchronized void updateTrustedRoots(List<X509Certificate> list) {
            Preconditions.checkNotNull(list, "trustedRoots");
            this.trustedRoots = list;
            Iterator<Watcher> it2 = this.downstreamWatchers.iterator();
            while (it2.hasNext()) {
                sendLastTrustedRootsUpdate(it2.next());
            }
        }

        @Override // io.grpc.xds.internal.certprovider.CertificateProvider.Watcher
        public synchronized void onError(Status status) {
            Iterator<Watcher> it2 = this.downstreamWatchers.iterator();
            while (it2.hasNext()) {
                it2.next().onError(status);
            }
        }

        X509Certificate getLastIdentityCert() {
            List<X509Certificate> list = this.certChain;
            if (list == null || list.isEmpty()) {
                return null;
            }
            return this.certChain.get(0);
        }

        void close() {
            this.downstreamWatchers.clear();
            clearValues();
        }
    }
}
