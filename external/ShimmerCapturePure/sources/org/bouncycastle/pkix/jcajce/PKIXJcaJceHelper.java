package org.bouncycastle.pkix.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertPathBuilder;

import org.bouncycastle.jcajce.util.JcaJceHelper;

/* loaded from: classes5.dex */
interface PKIXJcaJceHelper extends JcaJceHelper {
    CertPathBuilder createCertPathBuilder(String str) throws NoSuchAlgorithmException, NoSuchProviderException;
}
