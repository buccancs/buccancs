package org.bouncycastle.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* loaded from: classes5.dex */
abstract class CertHelper {
    CertHelper() {
    }

    protected abstract CertificateFactory createCertificateFactory(String str) throws CertificateException, NoSuchProviderException;

    public CertificateFactory getCertificateFactory(String str) throws CertificateException, NoSuchProviderException {
        return createCertificateFactory(str);
    }
}
