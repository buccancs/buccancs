package org.apache.commons.math3.geometry.partitioning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes5.dex */
public class RegionFactory<S extends Space> {
    private final RegionFactory<S>.NodesCleaner nodeCleaner = new NodesCleaner(this, null);

    public Region<S> buildConvex(Hyperplane<S>... hyperplaneArr) {
        if (hyperplaneArr == null || hyperplaneArr.length == 0) {
            return null;
        }
        Region<S> regionWholeSpace = hyperplaneArr[0].wholeSpace();
        BSPTree<S> tree = regionWholeSpace.getTree(false);
        tree.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> hyperplane : hyperplaneArr) {
            if (tree.insertCut(hyperplane)) {
                tree.setAttribute(null);
                tree.getPlus().setAttribute(Boolean.FALSE);
                tree = tree.getMinus();
                tree.setAttribute(Boolean.TRUE);
            } else {
                SubHyperplane subHyperplaneWholeHyperplane = hyperplane.wholeHyperplane();
                for (BSPTree<S> parent = tree; parent.getParent() != null && subHyperplaneWholeHyperplane != null; parent = parent.getParent()) {
                    Hyperplane<S> hyperplane2 = parent.getParent().getCut().getHyperplane();
                    SubHyperplane.SplitSubHyperplane<S> splitSubHyperplaneSplit = subHyperplaneWholeHyperplane.split(hyperplane2);
                    int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[splitSubHyperplaneSplit.getSide().ordinal()];
                    if (i == 1) {
                        if (!hyperplane.sameOrientationAs(hyperplane2)) {
                            return getComplement(hyperplaneArr[0].wholeSpace());
                        }
                    } else {
                        if (i == 2) {
                            throw new MathIllegalArgumentException(LocalizedFormats.NOT_CONVEX_HYPERPLANES, new Object[0]);
                        }
                        subHyperplaneWholeHyperplane = splitSubHyperplaneSplit.getMinus();
                    }
                }
            }
        }
        return regionWholeSpace;
    }

    public Region<S> union(Region<S> region, Region<S> region2) {
        BSPTree<S> bSPTreeMerge = region.getTree(false).merge(region2.getTree(false), new UnionMerger(this, null));
        bSPTreeMerge.visit(this.nodeCleaner);
        return region.buildNew(bSPTreeMerge);
    }

    public Region<S> intersection(Region<S> region, Region<S> region2) {
        BSPTree<S> bSPTreeMerge = region.getTree(false).merge(region2.getTree(false), new IntersectionMerger(this, null));
        bSPTreeMerge.visit(this.nodeCleaner);
        return region.buildNew(bSPTreeMerge);
    }

    public Region<S> xor(Region<S> region, Region<S> region2) {
        BSPTree<S> bSPTreeMerge = region.getTree(false).merge(region2.getTree(false), new XorMerger(this, null));
        bSPTreeMerge.visit(this.nodeCleaner);
        return region.buildNew(bSPTreeMerge);
    }

    public Region<S> difference(Region<S> region, Region<S> region2) {
        BSPTree<S> bSPTreeMerge = region.getTree(false).merge(region2.getTree(false), new DifferenceMerger(region, region2));
        bSPTreeMerge.visit(this.nodeCleaner);
        return region.buildNew(bSPTreeMerge);
    }

    public Region<S> getComplement(Region<S> region) {
        return region.buildNew(recurseComplement(region.getTree(false)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<S> recurseComplement(BSPTree<S> bSPTree) {
        BoundaryAttribute boundaryAttribute;
        HashMap map = new HashMap();
        BSPTree<S> bSPTreeRecurseComplement = recurseComplement(bSPTree, map);
        for (Map.Entry<BSPTree<S>, BSPTree<S>> entry : map.entrySet()) {
            if (entry.getKey().getCut() != null && (boundaryAttribute = (BoundaryAttribute) entry.getKey().getAttribute()) != null) {
                BoundaryAttribute boundaryAttribute2 = (BoundaryAttribute) entry.getValue().getAttribute();
                Iterator<BSPTree<S>> it2 = boundaryAttribute.getSplitters().iterator();
                while (it2.hasNext()) {
                    boundaryAttribute2.getSplitters().add(map.get(it2.next()));
                }
            }
        }
        return bSPTreeRecurseComplement;
    }

    private BSPTree<S> recurseComplement(BSPTree<S> bSPTree, Map<BSPTree<S>, BSPTree<S>> map) {
        BSPTree<S> bSPTree2;
        if (bSPTree.getCut() == null) {
            bSPTree2 = new BSPTree<>(((Boolean) bSPTree.getAttribute()).booleanValue() ? Boolean.FALSE : Boolean.TRUE);
        } else {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute != null) {
                boundaryAttribute = new BoundaryAttribute(boundaryAttribute.getPlusInside() == null ? null : boundaryAttribute.getPlusInside().copySelf(), boundaryAttribute.getPlusOutside() != null ? boundaryAttribute.getPlusOutside().copySelf() : null, new NodesSet());
            }
            bSPTree2 = new BSPTree<>(bSPTree.getCut().copySelf(), recurseComplement(bSPTree.getPlus(), map), recurseComplement(bSPTree.getMinus(), map), boundaryAttribute);
        }
        map.put(bSPTree, bSPTree2);
        return bSPTree2;
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.RegionFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        static {
            int[] iArr = new int[Side.values().length];
            $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side = iArr;
            try {
                iArr[Side.HYPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[Side.PLUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private class UnionMerger implements BSPTree.LeafMerger<S> {
        private UnionMerger() {
        }

        /* synthetic */ UnionMerger(RegionFactory regionFactory, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger
        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree.insertInTree(bSPTree3, z, new VanishingToLeaf(true));
                return bSPTree;
            }
            bSPTree2.insertInTree(bSPTree3, z, new VanishingToLeaf(false));
            return bSPTree2;
        }
    }

    private class IntersectionMerger implements BSPTree.LeafMerger<S> {
        private IntersectionMerger() {
        }

        /* synthetic */ IntersectionMerger(RegionFactory regionFactory, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger
        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree2.insertInTree(bSPTree3, z, new VanishingToLeaf(true));
                return bSPTree2;
            }
            bSPTree.insertInTree(bSPTree3, z, new VanishingToLeaf(false));
            return bSPTree;
        }
    }

    private class XorMerger implements BSPTree.LeafMerger<S> {
        private XorMerger() {
        }

        /* synthetic */ XorMerger(RegionFactory regionFactory, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger
        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree2 = RegionFactory.this.recurseComplement(bSPTree2);
            }
            bSPTree2.insertInTree(bSPTree3, z, new VanishingToLeaf(true));
            return bSPTree2;
        }
    }

    private class DifferenceMerger implements BSPTree.LeafMerger<S>, BSPTree.VanishingCutHandler<S> {
        private final Region<S> region1;
        private final Region<S> region2;

        DifferenceMerger(Region<S> region, Region<S> region2) {
            this.region1 = region.copySelf();
            this.region2 = region2.copySelf();
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger
        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                RegionFactory regionFactory = RegionFactory.this;
                if (z2) {
                    bSPTree = bSPTree2;
                }
                BSPTree<S> bSPTreeRecurseComplement = regionFactory.recurseComplement(bSPTree);
                bSPTreeRecurseComplement.insertInTree(bSPTree3, z, this);
                return bSPTreeRecurseComplement;
            }
            if (!z2) {
                bSPTree = bSPTree2;
            }
            bSPTree.insertInTree(bSPTree3, z, this);
            return bSPTree;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTree.VanishingCutHandler
        public BSPTree<S> fixNode(BSPTree<S> bSPTree) {
            Point<S> barycenter = this.region1.buildNew(bSPTree.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, null)).getBarycenter();
            return new BSPTree<>(Boolean.valueOf(this.region1.checkPoint(barycenter) == Region.Location.INSIDE && this.region2.checkPoint(barycenter) == Region.Location.OUTSIDE));
        }
    }

    private class NodesCleaner implements BSPTreeVisitor<S> {
        private NodesCleaner() {
        }

        /* synthetic */ NodesCleaner(RegionFactory regionFactory, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitLeafNode(BSPTree<S> bSPTree) {
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
            return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
        public void visitInternalNode(BSPTree<S> bSPTree) {
            bSPTree.setAttribute(null);
        }
    }

    private class VanishingToLeaf implements BSPTree.VanishingCutHandler<S> {
        private final boolean inside;

        VanishingToLeaf(boolean z) {
            this.inside = z;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.BSPTree.VanishingCutHandler
        public BSPTree<S> fixNode(BSPTree<S> bSPTree) {
            if (bSPTree.getPlus().getAttribute().equals(bSPTree.getMinus().getAttribute())) {
                return new BSPTree<>(bSPTree.getPlus().getAttribute());
            }
            return new BSPTree<>(Boolean.valueOf(this.inside));
        }
    }
}
