package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.params.DHParameters;

/* loaded from: classes5.dex */
public interface TlsDHVerifier {
    boolean accept(DHParameters dHParameters);
}
