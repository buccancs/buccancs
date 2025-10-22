package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/* loaded from: classes5.dex */
interface EACHelper {
    KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException, NoSuchProviderException;
}
