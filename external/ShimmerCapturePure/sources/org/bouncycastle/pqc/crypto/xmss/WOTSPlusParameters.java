package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes5.dex */
final class WOTSPlusParameters {
    private final Digest digest;
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final int winternitzParameter;

    protected WOTSPlusParameters(Digest digest) {
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        this.digest = digest;
        int digestSize = XMSSUtil.getDigestSize(digest);
        this.digestSize = digestSize;
        this.winternitzParameter = 16;
        int iCeil = (int) Math.ceil((digestSize * 8) / XMSSUtil.log2(16));
        this.len1 = iCeil;
        int iFloor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * iCeil) / XMSSUtil.log2(16))) + 1;
        this.len2 = iFloor;
        int i = iCeil + iFloor;
        this.len = i;
        WOTSPlusOid wOTSPlusOidLookup = WOTSPlusOid.lookup(digest.getAlgorithmName(), digestSize, 16, i);
        this.oid = wOTSPlusOidLookup;
        if (wOTSPlusOidLookup != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + digest.getAlgorithmName());
    }

    protected Digest getDigest() {
        return this.digest;
    }

    protected int getDigestSize() {
        return this.digestSize;
    }

    protected int getLen() {
        return this.len;
    }

    protected int getLen1() {
        return this.len1;
    }

    protected int getLen2() {
        return this.len2;
    }

    protected XMSSOid getOid() {
        return this.oid;
    }

    protected int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
