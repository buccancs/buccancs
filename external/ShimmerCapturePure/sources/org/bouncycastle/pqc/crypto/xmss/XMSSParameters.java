package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes5.dex */
public final class XMSSParameters {
    private final int height;
    private final int k;
    private final XMSSOid oid;
    private final WOTSPlus wotsPlus;

    public XMSSParameters(int i, Digest digest) {
        if (i < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        WOTSPlus wOTSPlus = new WOTSPlus(new WOTSPlusParameters(digest));
        this.wotsPlus = wOTSPlus;
        this.height = i;
        this.k = determineMinK();
        this.oid = DefaultXMSSOid.lookup(getDigest().getAlgorithmName(), getDigestSize(), getWinternitzParameter(), wOTSPlus.getParams().getLen(), i);
    }

    private int determineMinK() {
        int i = 2;
        while (true) {
            int i2 = this.height;
            if (i > i2) {
                throw new IllegalStateException("should never happen...");
            }
            if ((i2 - i) % 2 == 0) {
                return i;
            }
            i++;
        }
    }

    protected Digest getDigest() {
        return this.wotsPlus.getParams().getDigest();
    }

    public int getDigestSize() {
        return this.wotsPlus.getParams().getDigestSize();
    }

    public int getHeight() {
        return this.height;
    }

    int getK() {
        return this.k;
    }

    WOTSPlus getWOTSPlus() {
        return this.wotsPlus;
    }

    public int getWinternitzParameter() {
        return this.wotsPlus.getParams().getWinternitzParameter();
    }
}
