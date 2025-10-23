package org.apache.commons.math3.geometry.euclidean.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class IntervalsSet extends AbstractRegion<Euclidean1D, Euclidean1D> implements Iterable<double[]> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;

    public IntervalsSet(double d) {
        super(d);
    }

    public IntervalsSet(double d, double d2, double d3) {
        super(buildTree(d, d2, d3), d3);
    }

    public IntervalsSet(BSPTree<Euclidean1D> bSPTree, double d) {
        super(bSPTree, d);
    }

    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> collection, double d) {
        super(collection, d);
    }

    @Deprecated
    public IntervalsSet() {
        this(1.0E-10d);
    }

    @Deprecated
    public IntervalsSet(double d, double d2) {
        this(d, d2, 1.0E-10d);
    }

    @Deprecated
    public IntervalsSet(BSPTree<Euclidean1D> bSPTree) {
        this(bSPTree, 1.0E-10d);
    }

    @Deprecated
    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> collection) {
        this(collection, 1.0E-10d);
    }

    private static BSPTree<Euclidean1D> buildTree(double d, double d2, double d3) {
        if (Double.isInfinite(d) && d < 0.0d) {
            if (Double.isInfinite(d2) && d2 > 0.0d) {
                return new BSPTree<>(Boolean.TRUE);
            }
            return new BSPTree<>(new OrientedPoint(new Vector1D(d2), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
        }
        SubOrientedPoint subOrientedPointWholeHyperplane = new OrientedPoint(new Vector1D(d), false, d3).wholeHyperplane();
        if (Double.isInfinite(d2) && d2 > 0.0d) {
            return new BSPTree<>(subOrientedPointWholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
        }
        return new BSPTree<>(subOrientedPointWholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(new OrientedPoint(new Vector1D(d2), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ AbstractRegion buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Euclidean1D>) bSPTree);
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ Region buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Euclidean1D>) bSPTree);
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public IntervalsSet buildNew(BSPTree<Euclidean1D> bSPTree) {
        return new IntervalsSet(bSPTree, getTolerance());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion
    protected void computeGeometricalProperties() {
        if (getTree(false).getCut() == null) {
            setBarycenter((Point) Vector1D.NaN);
            setSize(((Boolean) getTree(false).getAttribute()).booleanValue() ? Double.POSITIVE_INFINITY : 0.0d);
            return;
        }
        double size = 0.0d;
        for (Interval interval : asList()) {
            size += interval.getSize();
            size += interval.getSize() * interval.getBarycenter();
        }
        setSize(size);
        if (Double.isInfinite(size)) {
            setBarycenter((Point) Vector1D.NaN);
        } else if (size >= Precision.SAFE_MIN) {
            setBarycenter((Point) new Vector1D(size / size));
        } else {
            setBarycenter((Point) ((OrientedPoint) getTree(false).getCut().getHyperplane()).getLocation());
        }
    }

    public double getInf() {
        BSPTree<Euclidean1D> tree = getTree(false);
        double d = Double.POSITIVE_INFINITY;
        while (tree.getCut() != null) {
            OrientedPoint orientedPoint = (OrientedPoint) tree.getCut().getHyperplane();
            double x = orientedPoint.getLocation().getX();
            tree = orientedPoint.isDirect() ? tree.getMinus() : tree.getPlus();
            d = x;
        }
        if (((Boolean) tree.getAttribute()).booleanValue()) {
            return Double.NEGATIVE_INFINITY;
        }
        return d;
    }

    public double getSup() {
        BSPTree<Euclidean1D> tree = getTree(false);
        double d = Double.NEGATIVE_INFINITY;
        while (tree.getCut() != null) {
            OrientedPoint orientedPoint = (OrientedPoint) tree.getCut().getHyperplane();
            double x = orientedPoint.getLocation().getX();
            tree = orientedPoint.isDirect() ? tree.getPlus() : tree.getMinus();
            d = x;
        }
        if (((Boolean) tree.getAttribute()).booleanValue()) {
            return Double.POSITIVE_INFINITY;
        }
        return d;
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public BoundaryProjection<Euclidean1D> projectToBoundary(Point<Euclidean1D> point) {
        double x = ((Vector1D) point).getX();
        Iterator<double[]> it2 = iterator();
        double d = Double.NEGATIVE_INFINITY;
        while (it2.hasNext()) {
            double[] next = it2.next();
            double d2 = next[0];
            if (x < d2) {
                double d3 = x - d;
                double d4 = d2 - x;
                if (d3 < d4) {
                    return new BoundaryProjection<>(point, finiteOrNullPoint(d), d3);
                }
                return new BoundaryProjection<>(point, finiteOrNullPoint(d2), d4);
            }
            d = next[1];
            if (x <= d) {
                double d5 = d2 - x;
                double d6 = x - d;
                if (d5 < d6) {
                    return new BoundaryProjection<>(point, finiteOrNullPoint(d), d6);
                }
                return new BoundaryProjection<>(point, finiteOrNullPoint(d2), d5);
            }
        }
        return new BoundaryProjection<>(point, finiteOrNullPoint(d), x - d);
    }

    private Vector1D finiteOrNullPoint(double d) {
        if (Double.isInfinite(d)) {
            return null;
        }
        return new Vector1D(d);
    }

    public List<Interval> asList() {
        ArrayList arrayList = new ArrayList();
        Iterator<double[]> it2 = iterator();
        while (it2.hasNext()) {
            double[] next = it2.next();
            arrayList.add(new Interval(next[0], next[1]));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Euclidean1D> getFirstLeaf(BSPTree<Euclidean1D> bSPTree) {
        if (bSPTree.getCut() == null) {
            return bSPTree;
        }
        BSPTree<Euclidean1D> bSPTree2 = null;
        while (bSPTree != null) {
            bSPTree2 = bSPTree;
            bSPTree = previousInternalNode(bSPTree);
        }
        return leafBefore(bSPTree2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public BSPTree<Euclidean1D> getFirstIntervalBoundary() {
        BSPTree<Euclidean1D> tree = getTree(false);
        if (tree.getCut() == null) {
            return null;
        }
        BSPTree parent = getFirstLeaf(tree).getParent();
        while (parent != null && !isIntervalStart(parent) && !isIntervalEnd(parent)) {
            parent = nextInternalNode(parent);
        }
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isIntervalStart(BSPTree<Euclidean1D> bSPTree) {
        return !((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && ((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isIntervalEnd(BSPTree<Euclidean1D> bSPTree) {
        return ((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && !((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Euclidean1D> nextInternalNode(BSPTree<Euclidean1D> bSPTree) {
        if (childAfter(bSPTree).getCut() != null) {
            return leafAfter(bSPTree).getParent();
        }
        while (isAfterParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    private BSPTree<Euclidean1D> previousInternalNode(BSPTree<Euclidean1D> bSPTree) {
        if (childBefore(bSPTree).getCut() != null) {
            return leafBefore(bSPTree).getParent();
        }
        while (isBeforeParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    private BSPTree<Euclidean1D> leafBefore(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<Euclidean1D> bSPTreeChildBefore = childBefore(bSPTree);
        while (bSPTreeChildBefore.getCut() != null) {
            bSPTreeChildBefore = childAfter(bSPTreeChildBefore);
        }
        return bSPTreeChildBefore;
    }

    private BSPTree<Euclidean1D> leafAfter(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<Euclidean1D> bSPTreeChildAfter = childAfter(bSPTree);
        while (bSPTreeChildAfter.getCut() != null) {
            bSPTreeChildAfter = childBefore(bSPTreeChildAfter);
        }
        return bSPTreeChildAfter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isBeforeParent(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<S> parent = bSPTree.getParent();
        return parent != 0 && bSPTree == childBefore(parent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isAfterParent(BSPTree<Euclidean1D> bSPTree) {
        BSPTree<S> parent = bSPTree.getParent();
        return parent != 0 && bSPTree == childAfter(parent);
    }

    private BSPTree<Euclidean1D> childBefore(BSPTree<Euclidean1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getMinus();
        }
        return bSPTree.getPlus();
    }

    private BSPTree<Euclidean1D> childAfter(BSPTree<Euclidean1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getPlus();
        }
        return bSPTree.getMinus();
    }

    private boolean isDirect(BSPTree<Euclidean1D> bSPTree) {
        return ((OrientedPoint) bSPTree.getCut().getHyperplane()).isDirect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getAngle(BSPTree<Euclidean1D> bSPTree) {
        return ((OrientedPoint) bSPTree.getCut().getHyperplane()).getLocation().getX();
    }

    @Override // java.lang.Iterable
    public Iterator<double[]> iterator() {
        return new SubIntervalsIterator();
    }

    private class SubIntervalsIterator implements Iterator<double[]> {
        private BSPTree<Euclidean1D> current;
        private double[] pending;

        SubIntervalsIterator() {
            BSPTree<Euclidean1D> firstIntervalBoundary = IntervalsSet.this.getFirstIntervalBoundary();
            this.current = firstIntervalBoundary;
            if (firstIntervalBoundary == null) {
                if (((Boolean) IntervalsSet.this.getFirstLeaf(IntervalsSet.this.getTree(false)).getAttribute()).booleanValue()) {
                    this.pending = new double[]{Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY};
                    return;
                } else {
                    this.pending = null;
                    return;
                }
            }
            if (IntervalsSet.this.isIntervalEnd(firstIntervalBoundary)) {
                this.pending = new double[]{Double.NEGATIVE_INFINITY, IntervalsSet.this.getAngle(this.current)};
            } else {
                selectPending();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pending != null;
        }

        private void selectPending() {
            BSPTree<Euclidean1D> bSPTreeNextInternalNode = this.current;
            while (bSPTreeNextInternalNode != null && !IntervalsSet.this.isIntervalStart(bSPTreeNextInternalNode)) {
                bSPTreeNextInternalNode = IntervalsSet.this.nextInternalNode(bSPTreeNextInternalNode);
            }
            if (bSPTreeNextInternalNode == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree<Euclidean1D> bSPTreeNextInternalNode2 = bSPTreeNextInternalNode;
            while (bSPTreeNextInternalNode2 != null && !IntervalsSet.this.isIntervalEnd(bSPTreeNextInternalNode2)) {
                bSPTreeNextInternalNode2 = IntervalsSet.this.nextInternalNode(bSPTreeNextInternalNode2);
            }
            if (bSPTreeNextInternalNode2 != null) {
                this.pending = new double[]{IntervalsSet.this.getAngle(bSPTreeNextInternalNode), IntervalsSet.this.getAngle(bSPTreeNextInternalNode2)};
                this.current = bSPTreeNextInternalNode2;
            } else {
                this.pending = new double[]{IntervalsSet.this.getAngle(bSPTreeNextInternalNode), Double.POSITIVE_INFINITY};
                this.current = null;
            }
        }

        @Override // java.util.Iterator
        public double[] next() {
            double[] dArr = this.pending;
            if (dArr == null) {
                throw new NoSuchElementException();
            }
            selectPending();
            return dArr;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
