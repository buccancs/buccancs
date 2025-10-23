package io.grpc.xds.internal.certprovider;

import io.grpc.xds.internal.certprovider.CertificateProvider;

/* loaded from: classes3.dex */
public interface CertificateProviderProvider {
    CertificateProvider createCertificateProvider(Object obj, CertificateProvider.DistributorWatcher distributorWatcher, boolean z);

    String getName();
}
