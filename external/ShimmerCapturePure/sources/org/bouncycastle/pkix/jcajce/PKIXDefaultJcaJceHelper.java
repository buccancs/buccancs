package org.bouncycastle.pkix.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathBuilder;

import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;

/* loaded from: classes5.dex */
class PKIXDefaultJcaJceHelper extends DefaultJcaJceHelper implements PKIXJcaJceHelper {
    @Override // org.bouncycastle.pkix.jcajce.PKIXJcaJceHelper
    public CertPathBuilder createCertPathBuilder(String str) throws NoSuchAlgorithmException {
        return CertPathBuilder.getInstance(str);
    }
}
