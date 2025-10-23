package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;

/* loaded from: classes5.dex */
class BoundarySizeVisitor<S extends Space> implements BSPTreeVisitor<S> {
    private double boundarySize = 0.0d;

    BoundarySizeVisitor() {
    }

    public double getSize() {
        return this.boundarySize;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
    public void visitLeafNode(BSPTree<S> bSPTree) {
    }

    @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
    public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
        return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
    public void visitInternalNode(BSPTree<S> bSPTree) {
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute.getPlusOutside() != null) {
            this.boundarySize += boundaryAttribute.getPlusOutside().getSize();
        }
        if (boundaryAttribute.getPlusInside() != null) {
            this.boundarySize += boundaryAttribute.getPlusInside().getSize();
        }
    }
}
