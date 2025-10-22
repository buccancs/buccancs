package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Stack;

import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;

/* loaded from: classes5.dex */
class BDSTreeHash implements Serializable {
    private static final long serialVersionUID = 1;
    private final int initialHeight;
    private int height;
    private int nextIndex;
    private XMSSNode tailNode;
    private boolean initialized = false;
    private boolean finished = false;

    BDSTreeHash(int i) {
        this.initialHeight = i;
    }

    int getHeight() {
        if (!this.initialized || this.finished) {
            return Integer.MAX_VALUE;
        }
        return this.height;
    }

    int getIndexLeaf() {
        return this.nextIndex;
    }

    public XMSSNode getTailNode() {
        return this.tailNode.clone();
    }

    void initialize(int i) {
        this.tailNode = null;
        this.height = this.initialHeight;
        this.nextIndex = i;
        this.initialized = true;
        this.finished = false;
    }

    boolean isFinished() {
        return this.finished;
    }

    boolean isInitialized() {
        return this.initialized;
    }

    void setNode(XMSSNode xMSSNode) {
        this.tailNode = xMSSNode;
        int height = xMSSNode.getHeight();
        this.height = height;
        if (height == this.initialHeight) {
            this.finished = true;
        }
    }

    void update(Stack<XMSSNode> stack, WOTSPlus wOTSPlus, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        if (this.finished || !this.initialized) {
            throw new IllegalStateException("finished or not initialized");
        }
        OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(this.nextIndex).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
        LTreeAddress lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withLTreeAddress(this.nextIndex).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withTreeIndex(this.nextIndex).build();
        wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(bArr2, oTSHashAddress2), bArr);
        XMSSNode xMSSNodeLTree = XMSSNodeUtil.lTree(wOTSPlus, wOTSPlus.getPublicKey(oTSHashAddress2), lTreeAddress);
        while (!stack.isEmpty() && stack.peek().getHeight() == xMSSNodeLTree.getHeight() && stack.peek().getHeight() != this.initialHeight) {
            HashTreeAddress hashTreeAddress2 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            XMSSNode xMSSNodeRandomizeHash = XMSSNodeUtil.randomizeHash(wOTSPlus, stack.pop(), xMSSNodeLTree, hashTreeAddress2);
            XMSSNode xMSSNode = new XMSSNode(xMSSNodeRandomizeHash.getHeight() + 1, xMSSNodeRandomizeHash.getValue());
            hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.getTreeHeight() + 1).withTreeIndex(hashTreeAddress2.getTreeIndex()).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
            xMSSNodeLTree = xMSSNode;
        }
        XMSSNode xMSSNode2 = this.tailNode;
        if (xMSSNode2 == null) {
            this.tailNode = xMSSNodeLTree;
        } else if (xMSSNode2.getHeight() == xMSSNodeLTree.getHeight()) {
            HashTreeAddress hashTreeAddress3 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            xMSSNodeLTree = new XMSSNode(this.tailNode.getHeight() + 1, XMSSNodeUtil.randomizeHash(wOTSPlus, this.tailNode, xMSSNodeLTree, hashTreeAddress3).getValue());
            this.tailNode = xMSSNodeLTree;
        } else {
            stack.push(xMSSNodeLTree);
        }
        if (this.tailNode.getHeight() == this.initialHeight) {
            this.finished = true;
        } else {
            this.height = xMSSNodeLTree.getHeight();
            this.nextIndex++;
        }
    }
}
