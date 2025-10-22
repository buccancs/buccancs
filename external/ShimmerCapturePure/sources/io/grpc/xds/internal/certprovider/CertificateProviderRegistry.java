package io.grpc.xds.internal.certprovider;

import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public final class CertificateProviderRegistry {
    private static CertificateProviderRegistry instance;
    private final LinkedHashMap<String, CertificateProviderProvider> providers = new LinkedHashMap<>();

    public static synchronized CertificateProviderRegistry getInstance() {
        if (instance == null) {
            instance = new CertificateProviderRegistry();
        }
        return instance;
    }

    public synchronized void register(CertificateProviderProvider certificateProviderProvider) {
        Preconditions.checkNotNull(certificateProviderProvider, "certificateProviderProvider");
        this.providers.put(certificateProviderProvider.getName(), certificateProviderProvider);
    }

    public synchronized void deregister(CertificateProviderProvider certificateProviderProvider) {
        Preconditions.checkNotNull(certificateProviderProvider, "certificateProviderProvider");
        this.providers.remove(certificateProviderProvider.getName());
    }

    @Nullable
    synchronized CertificateProviderProvider getProvider(String str) {
        return this.providers.get(Preconditions.checkNotNull(str, "name"));
    }
}
