package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {
    int lowerSigmaBound;
    private BigInteger g;
    private BigInteger n;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(z);
        this.g = bigInteger;
        this.n = bigInteger2;
        this.lowerSigmaBound = i;
    }

    public BigInteger getG() {
        return this.g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.n;
    }
}
