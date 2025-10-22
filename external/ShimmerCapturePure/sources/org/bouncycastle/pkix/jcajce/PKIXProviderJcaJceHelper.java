package org.bouncycastle.pkix.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.CertPathBuilder;

import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;

/* loaded from: classes5.dex */
class PKIXProviderJcaJceHelper extends ProviderJcaJceHelper implements PKIXJcaJceHelper {
    public PKIXProviderJcaJceHelper(Provider provider) {
        super(provider);
    }

    @Override // org.bouncycastle.pkix.jcajce.PKIXJcaJceHelper
    public CertPathBuilder createCertPathBuilder(String str) throws NoSuchAlgorithmException {
        return CertPathBuilder.getInstance(str, this.provider);
    }
}
