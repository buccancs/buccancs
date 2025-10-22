package org.bouncycastle.pqc.crypto.xmss;

import java.io.IOException;

import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public final class XMSSPrivateKeyParameters extends XMSSKeyParameters implements XMSSStoreableObjectInterface {
    private final BDS bdsState;
    private final XMSSParameters params;
    private final byte[] publicSeed;
    private final byte[] root;
    private final byte[] secretKeyPRF;
    private final byte[] secretKeySeed;

    private XMSSPrivateKeyParameters(Builder builder) {
        super(true, builder.params.getDigest().getAlgorithmName());
        XMSSParameters xMSSParameters = builder.params;
        this.params = xMSSParameters;
        if (xMSSParameters == null) {
            throw new NullPointerException("params == null");
        }
        int digestSize = xMSSParameters.getDigestSize();
        byte[] bArr = builder.privateKey;
        if (bArr != null) {
            if (builder.xmss == null) {
                throw new NullPointerException("xmss == null");
            }
            int height = xMSSParameters.getHeight();
            int iBigEndianToInt = Pack.bigEndianToInt(bArr, 0);
            if (!XMSSUtil.isIndexValid(height, iBigEndianToInt)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            this.secretKeySeed = XMSSUtil.extractBytesAtOffset(bArr, 4, digestSize);
            int i = 4 + digestSize;
            this.secretKeyPRF = XMSSUtil.extractBytesAtOffset(bArr, i, digestSize);
            int i2 = i + digestSize;
            this.publicSeed = XMSSUtil.extractBytesAtOffset(bArr, i2, digestSize);
            int i3 = i2 + digestSize;
            this.root = XMSSUtil.extractBytesAtOffset(bArr, i3, digestSize);
            int i4 = i3 + digestSize;
            try {
                BDS bds = (BDS) XMSSUtil.deserialize(XMSSUtil.extractBytesAtOffset(bArr, i4, bArr.length - i4), BDS.class);
                if (bds.getIndex() != iBigEndianToInt) {
                    throw new IllegalStateException("serialized BDS has wrong index");
                }
                this.bdsState = bds.withWOTSDigest(DigestUtil.getDigestOID(builder.xmss.getDigest().getAlgorithmName()));
                return;
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            } catch (ClassNotFoundException e2) {
                throw new IllegalArgumentException(e2.getMessage(), e2);
            }
        }
        byte[] bArr2 = builder.secretKeySeed;
        if (bArr2 == null) {
            this.secretKeySeed = new byte[digestSize];
        } else {
            if (bArr2.length != digestSize) {
                throw new IllegalArgumentException("size of secretKeySeed needs to be equal size of digest");
            }
            this.secretKeySeed = bArr2;
        }
        byte[] bArr3 = builder.secretKeyPRF;
        if (bArr3 == null) {
            this.secretKeyPRF = new byte[digestSize];
        } else {
            if (bArr3.length != digestSize) {
                throw new IllegalArgumentException("size of secretKeyPRF needs to be equal size of digest");
            }
            this.secretKeyPRF = bArr3;
        }
        byte[] bArr4 = builder.publicSeed;
        if (bArr4 == null) {
            this.publicSeed = new byte[digestSize];
        } else {
            if (bArr4.length != digestSize) {
                throw new IllegalArgumentException("size of publicSeed needs to be equal size of digest");
            }
            this.publicSeed = bArr4;
        }
        byte[] bArr5 = builder.root;
        if (bArr5 == null) {
            this.root = new byte[digestSize];
        } else {
            if (bArr5.length != digestSize) {
                throw new IllegalArgumentException("size of root needs to be equal size of digest");
            }
            this.root = bArr5;
        }
        BDS bds2 = builder.bdsState;
        this.bdsState = bds2 == null ? (builder.index >= (1 << xMSSParameters.getHeight()) + (-2) || bArr4 == null || bArr2 == null) ? new BDS(xMSSParameters, builder.index) : new BDS(xMSSParameters, bArr4, bArr2, (OTSHashAddress) new OTSHashAddress.Builder().build(), builder.index) : bds2;
    }

    BDS getBDSState() {
        return this.bdsState;
    }

    public int getIndex() {
        return this.bdsState.getIndex();
    }

    public XMSSPrivateKeyParameters getNextKey() {
        Builder builderWithRoot;
        BDS bds;
        if (getIndex() < (1 << this.params.getHeight()) - 1) {
            builderWithRoot = new Builder(this.params).withSecretKeySeed(this.secretKeySeed).withSecretKeyPRF(this.secretKeyPRF).withPublicSeed(this.publicSeed).withRoot(this.root);
            bds = this.bdsState.getNextState(this.publicSeed, this.secretKeySeed, (OTSHashAddress) new OTSHashAddress.Builder().build());
        } else {
            builderWithRoot = new Builder(this.params).withSecretKeySeed(this.secretKeySeed).withSecretKeyPRF(this.secretKeyPRF).withPublicSeed(this.publicSeed).withRoot(this.root);
            bds = new BDS(this.params, getIndex() + 1);
        }
        return builderWithRoot.withBDSState(bds).build();
    }

    public XMSSParameters getParameters() {
        return this.params;
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.publicSeed);
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.root);
    }

    public byte[] getSecretKeyPRF() {
        return XMSSUtil.cloneArray(this.secretKeyPRF);
    }

    public byte[] getSecretKeySeed() {
        return XMSSUtil.cloneArray(this.secretKeySeed);
    }

    public long getUsagesRemaining() {
        return (1 << getParameters().getHeight()) - getIndex();
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.params.getDigestSize();
        byte[] bArr = new byte[digestSize + 4 + digestSize + digestSize + digestSize];
        Pack.intToBigEndian(this.bdsState.getIndex(), bArr, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.secretKeySeed, 4);
        int i = 4 + digestSize;
        XMSSUtil.copyBytesAtOffset(bArr, this.secretKeyPRF, i);
        int i2 = i + digestSize;
        XMSSUtil.copyBytesAtOffset(bArr, this.publicSeed, i2);
        XMSSUtil.copyBytesAtOffset(bArr, this.root, i2 + digestSize);
        try {
            return Arrays.concatenate(bArr, XMSSUtil.serialize(this.bdsState));
        } catch (IOException e) {
            throw new RuntimeException("error serializing bds state: " + e.getMessage());
        }
    }

    public static class Builder {
        private final XMSSParameters params;
        private int index = 0;
        private byte[] secretKeySeed = null;
        private byte[] secretKeyPRF = null;
        private byte[] publicSeed = null;
        private byte[] root = null;
        private BDS bdsState = null;
        private byte[] privateKey = null;
        private XMSSParameters xmss = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public XMSSPrivateKeyParameters build() {
            return new XMSSPrivateKeyParameters(this);
        }

        public Builder withBDSState(BDS bds) {
            this.bdsState = bds;
            return this;
        }

        public Builder withIndex(int i) {
            this.index = i;
            return this;
        }

        public Builder withPrivateKey(byte[] bArr, XMSSParameters xMSSParameters) {
            this.privateKey = XMSSUtil.cloneArray(bArr);
            this.xmss = xMSSParameters;
            return this;
        }

        public Builder withPublicSeed(byte[] bArr) {
            this.publicSeed = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withRoot(byte[] bArr) {
            this.root = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSecretKeyPRF(byte[] bArr) {
            this.secretKeyPRF = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSecretKeySeed(byte[] bArr) {
            this.secretKeySeed = XMSSUtil.cloneArray(bArr);
            return this;
        }
    }
}
