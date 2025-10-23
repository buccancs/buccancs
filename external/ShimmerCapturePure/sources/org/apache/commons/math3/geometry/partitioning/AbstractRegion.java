package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes5.dex */
public abstract class AbstractRegion<S extends Space, T extends Space> implements Region<S> {
    private final double tolerance;
    private Point<S> barycenter;
    private double size;
    private BSPTree<S> tree;

    protected AbstractRegion(double d) {
        this.tree = new BSPTree<>(Boolean.TRUE);
        this.tolerance = d;
    }

    protected AbstractRegion(BSPTree<S> bSPTree, double d) {
        this.tree = bSPTree;
        this.tolerance = d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected AbstractRegion(Collection<SubHyperplane<S>> collection, double d) {
        this.tolerance = d;
        if (collection.size() == 0) {
            this.tree = new BSPTree<>(Boolean.TRUE);
            return;
        }
        TreeSet treeSet = new TreeSet(new Comparator<SubHyperplane<S>>() { // from class: org.apache.commons.math3.geometry.partitioning.AbstractRegion.1
            @Override // java.util.Comparator
            public int compare(SubHyperplane<S> subHyperplane, SubHyperplane<S> subHyperplane2) {
                if (subHyperplane2.getSize() < subHyperplane.getSize()) {
                    return -1;
                }
                return subHyperplane == subHyperplane2 ? 0 : 1;
            }
        });
        treeSet.addAll(collection);
        BSPTree<S> bSPTree = new BSPTree<>();
        this.tree = bSPTree;
        insertCuts(bSPTree, treeSet);
        this.tree.visit(new BSPTreeVisitor<S>() { // from class: org.apache.commons.math3.geometry.partitioning.AbstractRegion.2
            @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
            public void visitInternalNode(BSPTree<S> bSPTree2) {
            }

            @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
            public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree2) {
                return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
            }

            @Override // org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor
            public void visitLeafNode(BSPTree<S> bSPTree2) {
                if (bSPTree2.getParent() == null || bSPTree2 == bSPTree2.getParent().getMinus()) {
                    bSPTree2.setAttribute(Boolean.TRUE);
                } else {
                    bSPTree2.setAttribute(Boolean.FALSE);
                }
            }
        });
    }

    public AbstractRegion(Hyperplane<S>[] hyperplaneArr, double d) {
        this.tolerance = d;
        if (hyperplaneArr == null || hyperplaneArr.length == 0) {
            this.tree = new BSPTree<>(Boolean.FALSE);
            return;
        }
        BSPTree<S> tree = hyperplaneArr[0].wholeSpace().getTree(false);
        this.tree = tree;
        tree.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> hyperplane : hyperplaneArr) {
            if (tree.insertCut(hyperplane)) {
                tree.setAttribute(null);
                tree.getPlus().setAttribute(Boolean.FALSE);
                tree = tree.getMinus();
                tree.setAttribute(Boolean.TRUE);
            }
        }
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public abstract AbstractRegion<S, T> buildNew(BSPTree<S> bSPTree);

    protected abstract void computeGeometricalProperties();

    public double getTolerance() {
        return this.tolerance;
    }

    private void insertCuts(BSPTree<S> bSPTree, Collection<SubHyperplane<S>> collection) {
        Hyperplane<S> hyperplane;
        Iterator<SubHyperplane<S>> it2 = collection.iterator();
        loop0:
        while (true) {
            hyperplane = null;
            while (hyperplane == null && it2.hasNext()) {
                hyperplane = it2.next().getHyperplane();
                if (!bSPTree.insertCut(hyperplane.copySelf())) {
                    break;
                }
            }
        }
        if (it2.hasNext()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (it2.hasNext()) {
                SubHyperplane<S> next = it2.next();
                SubHyperplane.SplitSubHyperplane<S> splitSubHyperplaneSplit = next.split(hyperplane);
                int i = AnonymousClass3.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[splitSubHyperplaneSplit.getSide().ordinal()];
                if (i == 1) {
                    arrayList.add(next);
                } else if (i == 2) {
                    arrayList2.add(next);
                } else if (i == 3) {
                    arrayList.add(splitSubHyperplaneSplit.getPlus());
                    arrayList2.add(splitSubHyperplaneSplit.getMinus());
                }
            }
            insertCuts(bSPTree.getPlus(), arrayList);
            insertCuts(bSPTree.getMinus(), arrayList2);
        }
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public AbstractRegion<S, T> copySelf() {
        return buildNew((BSPTree) this.tree.copySelf());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public boolean isEmpty() {
        return isEmpty(this.tree);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public boolean isEmpty(BSPTree<S> bSPTree) {
        if (bSPTree.getCut() == null) {
            return !((Boolean) bSPTree.getAttribute()).booleanValue();
        }
        return isEmpty(bSPTree.getMinus()) && isEmpty(bSPTree.getPlus());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public boolean isFull() {
        return isFull(this.tree);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public boolean isFull(BSPTree<S> bSPTree) {
        if (bSPTree.getCut() == null) {
            return ((Boolean) bSPTree.getAttribute()).booleanValue();
        }
        return isFull(bSPTree.getMinus()) && isFull(bSPTree.getPlus());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public boolean contains(Region<S> region) {
        return new RegionFactory().difference(region, this).isEmpty();
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public BoundaryProjection<S> projectToBoundary(Point<S> point) {
        BoundaryProjector boundaryProjector = new BoundaryProjector(point);
        getTree(true).visit(boundaryProjector);
        return boundaryProjector.getProjection();
    }

    public Region.Location checkPoint(Vector<S> vector) {
        return checkPoint((Point) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public Region.Location checkPoint(Point<S> point) {
        return checkPoint(this.tree, point);
    }

    protected Region.Location checkPoint(BSPTree<S> bSPTree, Vector<S> vector) {
        return checkPoint((BSPTree) bSPTree, (Point) vector);
    }

    protected Region.Location checkPoint(BSPTree<S> bSPTree, Point<S> point) {
        BSPTree<S> cell = bSPTree.getCell(point, this.tolerance);
        if (cell.getCut() == null) {
            return ((Boolean) cell.getAttribute()).booleanValue() ? Region.Location.INSIDE : Region.Location.OUTSIDE;
        }
        Region.Location locationCheckPoint = checkPoint(cell.getMinus(), point);
        return locationCheckPoint == checkPoint(cell.getPlus(), point) ? locationCheckPoint : Region.Location.BOUNDARY;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public BSPTree<S> getTree(boolean z) {
        if (z && this.tree.getCut() != null && this.tree.getAttribute() == null) {
            this.tree.visit(new BoundaryBuilder());
        }
        return this.tree;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public double getBoundarySize() {
        BoundarySizeVisitor boundarySizeVisitor = new BoundarySizeVisitor();
        getTree(true).visit(boundarySizeVisitor);
        return boundarySizeVisitor.getSize();
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public double getSize() {
        if (this.barycenter == null) {
            computeGeometricalProperties();
        }
        return this.size;
    }

    protected void setSize(double d) {
        this.size = d;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public Point<S> getBarycenter() {
        if (this.barycenter == null) {
            computeGeometricalProperties();
        }
        return this.barycenter;
    }

    protected void setBarycenter(Point<S> point) {
        this.barycenter = point;
    }

    protected void setBarycenter(Vector<S> vector) {
        setBarycenter((Point) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    @Deprecated
    public Side side(Hyperplane<S> hyperplane) {
        InsideFinder insideFinder = new InsideFinder(this);
        insideFinder.recurseSides(this.tree, hyperplane.wholeHyperplane());
        return insideFinder.plusFound() ? insideFinder.minusFound() ? Side.BOTH : Side.PLUS : insideFinder.minusFound() ? Side.MINUS : Side.HYPER;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Region
    public SubHyperplane<S> intersection(SubHyperplane<S> subHyperplane) {
        return recurseIntersection(this.tree, subHyperplane);
    }

    private SubHyperplane<S> recurseIntersection(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane) {
        if (bSPTree.getCut() == null) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                return subHyperplane.copySelf();
            }
            return null;
        }
        SubHyperplane.SplitSubHyperplane<S> splitSubHyperplaneSplit = subHyperplane.split(bSPTree.getCut().getHyperplane());
        if (splitSubHyperplaneSplit.getPlus() != null) {
            if (splitSubHyperplaneSplit.getMinus() != null) {
                SubHyperplane<S> subHyperplaneRecurseIntersection = recurseIntersection(bSPTree.getPlus(), splitSubHyperplaneSplit.getPlus());
                SubHyperplane<S> subHyperplaneRecurseIntersection2 = recurseIntersection(bSPTree.getMinus(), splitSubHyperplaneSplit.getMinus());
                return subHyperplaneRecurseIntersection == null ? subHyperplaneRecurseIntersection2 : subHyperplaneRecurseIntersection2 == null ? subHyperplaneRecurseIntersection : subHyperplaneRecurseIntersection.reunite(subHyperplaneRecurseIntersection2);
            }
            return recurseIntersection(bSPTree.getPlus(), subHyperplane);
        }
        if (splitSubHyperplaneSplit.getMinus() != null) {
            return recurseIntersection(bSPTree.getMinus(), subHyperplane);
        }
        return recurseIntersection(bSPTree.getPlus(), recurseIntersection(bSPTree.getMinus(), subHyperplane));
    }

    public AbstractRegion<S, T> applyTransform(Transform<S, T> transform) {
        BoundaryAttribute boundaryAttribute;
        HashMap map = new HashMap();
        BSPTree<S> bSPTreeRecurseTransform = recurseTransform(getTree(false), transform, map);
        for (Map.Entry<BSPTree<S>, BSPTree<S>> entry : map.entrySet()) {
            if (entry.getKey().getCut() != null && (boundaryAttribute = (BoundaryAttribute) entry.getKey().getAttribute()) != null) {
                BoundaryAttribute boundaryAttribute2 = (BoundaryAttribute) entry.getValue().getAttribute();
                Iterator<BSPTree<S>> it2 = boundaryAttribute.getSplitters().iterator();
                while (it2.hasNext()) {
                    boundaryAttribute2.getSplitters().add(map.get(it2.next()));
                }
            }
        }
        return buildNew((BSPTree) bSPTreeRecurseTransform);
    }

    private BSPTree<S> recurseTransform(BSPTree<S> bSPTree, Transform<S, T> transform, Map<BSPTree<S>, BSPTree<S>> map) {
        BSPTree<S> bSPTree2;
        if (bSPTree.getCut() == null) {
            bSPTree2 = new BSPTree<>(bSPTree.getAttribute());
        } else {
            AbstractSubHyperplane<S, T> abstractSubHyperplaneApplyTransform = ((AbstractSubHyperplane) bSPTree.getCut()).applyTransform(transform);
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute != null) {
                boundaryAttribute = new BoundaryAttribute(boundaryAttribute.getPlusOutside() == null ? null : ((AbstractSubHyperplane) boundaryAttribute.getPlusOutside()).applyTransform(transform), boundaryAttribute.getPlusInside() != null ? ((AbstractSubHyperplane) boundaryAttribute.getPlusInside()).applyTransform(transform) : null, new NodesSet());
            }
            bSPTree2 = new BSPTree<>(abstractSubHyperplaneApplyTransform, recurseTransform(bSPTree.getPlus(), transform, map), recurseTransform(bSPTree.getMinus(), transform, map), boundaryAttribute);
        }
        map.put(bSPTree, bSPTree2);
        return bSPTree2;
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.AbstractRegion$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        static {
            int[] iArr = new int[Side.values().length];
            $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side = iArr;
            try {
                iArr[Side.PLUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[Side.MINUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[Side.BOTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
