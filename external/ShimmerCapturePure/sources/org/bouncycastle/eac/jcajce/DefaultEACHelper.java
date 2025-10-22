package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
class DefaultEACHelper implements EACHelper {
    DefaultEACHelper() {
    }

    @Override // org.bouncycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(str);
    }
}
