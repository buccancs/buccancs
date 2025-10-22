package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class XMSSMTSigner implements StateAwareMessageSigner {
    private boolean hasGenerated;
    private boolean initSign;
    private XMSSMTPrivateKeyParameters nextKeyGenerator;
    private XMSSMTParameters params;
    private XMSSMTPrivateKeyParameters privateKey;
    private XMSSMTPublicKeyParameters publicKey;
    private WOTSPlus wotsPlus;
    private XMSSParameters xmssParams;

    private WOTSPlusSignature wotsSign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        WOTSPlus wOTSPlus = this.wotsPlus;
        wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.privateKey.getPublicSeed());
        return this.wotsPlus.sign(bArr, oTSHashAddress);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("message == null");
        }
        if (!this.initSign) {
            throw new IllegalStateException("signer not initialized for signature generation");
        }
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.privateKey;
        if (xMSSMTPrivateKeyParameters == null) {
            throw new IllegalStateException("signing key no longer usable");
        }
        if (xMSSMTPrivateKeyParameters.getBDSState().isEmpty()) {
            throw new IllegalStateException("not initialized");
        }
        BDSStateMap bDSState = this.privateKey.getBDSState();
        long index = this.privateKey.getIndex();
        int height = this.params.getHeight();
        int height2 = this.xmssParams.getHeight();
        if (!XMSSUtil.isIndexValid(height, index)) {
            throw new IllegalStateException("index out of bounds");
        }
        byte[] bArrPRF = this.wotsPlus.getKhf().PRF(this.privateKey.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(index, 32));
        byte[] bArrHMsg = this.wotsPlus.getKhf().HMsg(Arrays.concatenate(bArrPRF, this.privateKey.getRoot(), XMSSUtil.toBytesBigEndian(index, this.params.getDigestSize())), bArr);
        XMSSMTSignature xMSSMTSignatureBuild = new XMSSMTSignature.Builder(this.params).withIndex(index).withRandom(bArrPRF).build();
        long treeIndex = XMSSUtil.getTreeIndex(index, height2);
        int leafIndex = XMSSUtil.getLeafIndex(index, height2);
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
        OTSHashAddress oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withTreeAddress(treeIndex).withOTSAddress(leafIndex).build();
        if (bDSState.get(0) == null || leafIndex == 0) {
            bDSState.put(0, new BDS(this.xmssParams, this.privateKey.getPublicSeed(), this.privateKey.getSecretKeySeed(), oTSHashAddress));
        }
        xMSSMTSignatureBuild.getReducedSignatures().add(new XMSSReducedSignature.Builder(this.xmssParams).withWOTSPlusSignature(wotsSign(bArrHMsg, oTSHashAddress)).withAuthPath(bDSState.get(0).getAuthenticationPath()).build());
        for (int i = 1; i < this.params.getLayers(); i++) {
            XMSSNode root = bDSState.get(i - 1).getRoot();
            int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height2);
            treeIndex = XMSSUtil.getTreeIndex(treeIndex, height2);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(i).withTreeAddress(treeIndex).withOTSAddress(leafIndex2).build();
            WOTSPlusSignature wOTSPlusSignatureWotsSign = wotsSign(root.getValue(), oTSHashAddress2);
            if (bDSState.get(i) == null || XMSSUtil.isNewBDSInitNeeded(index, height2, i)) {
                bDSState.put(i, new BDS(this.xmssParams, this.privateKey.getPublicSeed(), this.privateKey.getSecretKeySeed(), oTSHashAddress2));
            }
            xMSSMTSignatureBuild.getReducedSignatures().add(new XMSSReducedSignature.Builder(this.xmssParams).withWOTSPlusSignature(wOTSPlusSignatureWotsSign).withAuthPath(bDSState.get(i).getAuthenticationPath()).build());
        }
        this.hasGenerated = true;
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters2 = this.nextKeyGenerator;
        if (xMSSMTPrivateKeyParameters2 != null) {
            XMSSMTPrivateKeyParameters nextKey = xMSSMTPrivateKeyParameters2.getNextKey();
            this.privateKey = nextKey;
            this.nextKeyGenerator = nextKey;
        } else {
            this.privateKey = null;
        }
        return xMSSMTSignatureBuild.toByteArray();
    }

    @Override // org.bouncycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        XMSSMTPrivateKeyParameters nextKey;
        if (this.hasGenerated) {
            nextKey = this.privateKey;
            this.privateKey = null;
        } else {
            nextKey = this.nextKeyGenerator.getNextKey();
        }
        this.nextKeyGenerator = null;
        return nextKey;
    }

    public long getUsagesRemaining() {
        return this.privateKey.getUsagesRemaining();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        XMSSMTParameters parameters;
        if (z) {
            this.initSign = true;
            this.hasGenerated = false;
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = (XMSSMTPrivateKeyParameters) cipherParameters;
            this.privateKey = xMSSMTPrivateKeyParameters;
            this.nextKeyGenerator = xMSSMTPrivateKeyParameters;
            parameters = xMSSMTPrivateKeyParameters.getParameters();
        } else {
            this.initSign = false;
            XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters = (XMSSMTPublicKeyParameters) cipherParameters;
            this.publicKey = xMSSMTPublicKeyParameters;
            parameters = xMSSMTPublicKeyParameters.getParameters();
        }
        this.params = parameters;
        this.xmssParams = parameters.getXMSSParameters();
        this.wotsPlus = new WOTSPlus(new WOTSPlusParameters(this.params.getDigest()));
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new NullPointerException("message == null");
        }
        if (bArr2 == null) {
            throw new NullPointerException("signature == null");
        }
        if (this.publicKey == null) {
            throw new NullPointerException("publicKey == null");
        }
        XMSSMTSignature xMSSMTSignatureBuild = new XMSSMTSignature.Builder(this.params).withSignature(bArr2).build();
        byte[] bArrHMsg = this.wotsPlus.getKhf().HMsg(Arrays.concatenate(xMSSMTSignatureBuild.getRandom(), this.publicKey.getRoot(), XMSSUtil.toBytesBigEndian(xMSSMTSignatureBuild.getIndex(), this.params.getDigestSize())), bArr);
        long index = xMSSMTSignatureBuild.getIndex();
        int height = this.xmssParams.getHeight();
        long treeIndex = XMSSUtil.getTreeIndex(index, height);
        int leafIndex = XMSSUtil.getLeafIndex(index, height);
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.publicKey.getPublicSeed());
        OTSHashAddress oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withTreeAddress(treeIndex).withOTSAddress(leafIndex).build();
        XMSSNode rootNodeFromSignature = XMSSVerifierUtil.getRootNodeFromSignature(this.wotsPlus, height, bArrHMsg, xMSSMTSignatureBuild.getReducedSignatures().get(0), oTSHashAddress, leafIndex);
        int i = 1;
        while (i < this.params.getLayers()) {
            XMSSReducedSignature xMSSReducedSignature = xMSSMTSignatureBuild.getReducedSignatures().get(i);
            int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height);
            long treeIndex2 = XMSSUtil.getTreeIndex(treeIndex, height);
            rootNodeFromSignature = XMSSVerifierUtil.getRootNodeFromSignature(this.wotsPlus, height, rootNodeFromSignature.getValue(), xMSSReducedSignature, (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(i).withTreeAddress(treeIndex2).withOTSAddress(leafIndex2).build(), leafIndex2);
            i++;
            treeIndex = treeIndex2;
        }
        return Arrays.constantTimeAreEqual(rootNodeFromSignature.getValue(), this.publicKey.getRoot());
    }
}
