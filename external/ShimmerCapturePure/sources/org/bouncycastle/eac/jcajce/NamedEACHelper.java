package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/* loaded from: classes5.dex */
class NamedEACHelper implements EACHelper {
    private final String providerName;

    NamedEACHelper(String str) {
        this.providerName = str;
    }

    @Override // org.bouncycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException, NoSuchProviderException {
        return KeyFactory.getInstance(str, this.providerName);
    }
}
