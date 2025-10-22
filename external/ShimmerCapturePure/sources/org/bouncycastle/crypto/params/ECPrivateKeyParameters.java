package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class ECPrivateKeyParameters extends ECKeyParameters {
    BigInteger d;

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.d = bigInteger;
    }

    public BigInteger getD() {
        return this.d;
    }
}
