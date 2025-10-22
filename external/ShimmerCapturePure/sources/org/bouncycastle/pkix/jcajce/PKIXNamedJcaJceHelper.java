package org.bouncycastle.pkix.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertPathBuilder;

import org.bouncycastle.jcajce.util.NamedJcaJceHelper;

/* loaded from: classes5.dex */
class PKIXNamedJcaJceHelper extends NamedJcaJceHelper implements PKIXJcaJceHelper {
    public PKIXNamedJcaJceHelper(String str) {
        super(str);
    }

    @Override // org.bouncycastle.pkix.jcajce.PKIXJcaJceHelper
    public CertPathBuilder createCertPathBuilder(String str) throws NoSuchAlgorithmException, NoSuchProviderException {
        return CertPathBuilder.getInstance(str, this.providerName);
    }
}
