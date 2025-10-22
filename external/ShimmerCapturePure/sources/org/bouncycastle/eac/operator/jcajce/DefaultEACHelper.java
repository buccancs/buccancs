package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/* loaded from: classes5.dex */
class DefaultEACHelper extends EACHelper {
    DefaultEACHelper() {
    }

    @Override // org.bouncycastle.eac.operator.jcajce.EACHelper
    protected Signature createSignature(String str) throws NoSuchAlgorithmException {
        return Signature.getInstance(str);
    }
}
