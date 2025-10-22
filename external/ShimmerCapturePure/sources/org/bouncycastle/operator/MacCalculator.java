package org.bouncycastle.operator;

import java.io.OutputStream;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes5.dex */
public interface MacCalculator {
    AlgorithmIdentifier getAlgorithmIdentifier();

    GenericKey getKey();

    byte[] getMac();

    OutputStream getOutputStream();
}
