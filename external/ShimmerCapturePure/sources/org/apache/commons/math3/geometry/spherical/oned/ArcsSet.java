package org.apache.commons.math3.geometry.spherical.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class ArcsSet extends AbstractRegion<Sphere1D, Sphere1D> implements Iterable<double[]> {
    public ArcsSet(double d) {
        super(d);
    }

    public ArcsSet(double d, double d2, double d3) throws NumberIsTooLargeException {
        super(buildTree(d, d2, d3), d3);
    }

    public ArcsSet(BSPTree<Sphere1D> bSPTree, double d) throws InconsistentStateAt2PiWrapping {
        super(bSPTree, d);
        check2PiConsistency();
    }

    public ArcsSet(Collection<SubHyperplane<Sphere1D>> collection, double d) throws InconsistentStateAt2PiWrapping {
        super(collection, d);
        check2PiConsistency();
    }

    private static BSPTree<Sphere1D> buildTree(double d, double d2, double d3) throws NumberIsTooLargeException {
        if (!Precision.equals(d, d2, 0)) {
            double d4 = d2 - d;
            if (d4 < 6.283185307179586d) {
                if (d > d2) {
                    throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d), Double.valueOf(d2), true);
                }
                double dNormalizeAngle = MathUtils.normalizeAngle(d, 3.141592653589793d);
                double d5 = d4 + dNormalizeAngle;
                SubLimitAngle subLimitAngleWholeHyperplane = new LimitAngle(new S1Point(dNormalizeAngle), false, d3).wholeHyperplane();
                if (d5 <= 6.283185307179586d) {
                    return new BSPTree<>(subLimitAngleWholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(new LimitAngle(new S1Point(d5), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
                }
                return new BSPTree<>(subLimitAngleWholeHyperplane, new BSPTree(new LimitAngle(new S1Point(d5 - 6.283185307179586d), true, d3).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), new BSPTree(Boolean.TRUE), null);
            }
        }
        return new BSPTree<>(Boolean.TRUE);
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ AbstractRegion buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Sphere1D>) bSPTree);
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ Region buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Sphere1D>) bSPTree);
    }

    private void check2PiConsistency() throws InconsistentStateAt2PiWrapping {
        BSPTree<Sphere1D> tree = getTree(false);
        if (tree.getCut() == null) {
            return;
        }
        Boolean bool = (Boolean) getFirstLeaf(tree).getAttribute();
        Boolean bool2 = (Boolean) getLastLeaf(tree).getAttribute();
        if (bool2.booleanValue() ^ bool.booleanValue()) {
            throw new InconsistentStateAt2PiWrapping();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> getFirstLeaf(BSPTree<Sphere1D> bSPTree) {
        if (bSPTree.getCut() == null) {
            return bSPTree;
        }
        BSPTree<Sphere1D> bSPTree2 = null;
        while (bSPTree != null) {
            bSPTree2 = bSPTree;
            bSPTree = previousInternalNode(bSPTree);
        }
        return leafBefore(bSPTree2);
    }

    private BSPTree<Sphere1D> getLastLeaf(BSPTree<Sphere1D> bSPTree) {
        if (bSPTree.getCut() == null) {
            return bSPTree;
        }
        BSPTree<Sphere1D> bSPTree2 = null;
        while (bSPTree != null) {
            bSPTree2 = bSPTree;
            bSPTree = nextInternalNode(bSPTree);
        }
        return leafAfter(bSPTree2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public BSPTree<Sphere1D> getFirstArcStart() {
        BSPTree<Sphere1D> tree = getTree(false);
        if (tree.getCut() == null) {
            return null;
        }
        BSPTree parent = getFirstLeaf(tree).getParent();
        while (parent != null && !isArcStart(parent)) {
            parent = nextInternalNode(parent);
        }
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isArcStart(BSPTree<Sphere1D> bSPTree) {
        return !((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && ((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isArcEnd(BSPTree<Sphere1D> bSPTree) {
        return ((Boolean) leafBefore(bSPTree).getAttribute()).booleanValue() && !((Boolean) leafAfter(bSPTree).getAttribute()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> nextInternalNode(BSPTree<Sphere1D> bSPTree) {
        if (childAfter(bSPTree).getCut() != null) {
            return leafAfter(bSPTree).getParent();
        }
        while (isAfterParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> previousInternalNode(BSPTree<Sphere1D> bSPTree) {
        if (childBefore(bSPTree).getCut() != null) {
            return leafBefore(bSPTree).getParent();
        }
        while (isBeforeParent(bSPTree)) {
            bSPTree = bSPTree.getParent();
        }
        return bSPTree.getParent();
    }

    private BSPTree<Sphere1D> leafBefore(BSPTree<Sphere1D> bSPTree) {
        BSPTree<Sphere1D> bSPTreeChildBefore = childBefore(bSPTree);
        while (bSPTreeChildBefore.getCut() != null) {
            bSPTreeChildBefore = childAfter(bSPTreeChildBefore);
        }
        return bSPTreeChildBefore;
    }

    private BSPTree<Sphere1D> leafAfter(BSPTree<Sphere1D> bSPTree) {
        BSPTree<Sphere1D> bSPTreeChildAfter = childAfter(bSPTree);
        while (bSPTreeChildAfter.getCut() != null) {
            bSPTreeChildAfter = childBefore(bSPTreeChildAfter);
        }
        return bSPTreeChildAfter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isBeforeParent(BSPTree<Sphere1D> bSPTree) {
        BSPTree<S> parent = bSPTree.getParent();
        return parent != 0 && bSPTree == childBefore(parent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isAfterParent(BSPTree<Sphere1D> bSPTree) {
        BSPTree<S> parent = bSPTree.getParent();
        return parent != 0 && bSPTree == childAfter(parent);
    }

    private BSPTree<Sphere1D> childBefore(BSPTree<Sphere1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getMinus();
        }
        return bSPTree.getPlus();
    }

    private BSPTree<Sphere1D> childAfter(BSPTree<Sphere1D> bSPTree) {
        if (isDirect(bSPTree)) {
            return bSPTree.getPlus();
        }
        return bSPTree.getMinus();
    }

    private boolean isDirect(BSPTree<Sphere1D> bSPTree) {
        return ((LimitAngle) bSPTree.getCut().getHyperplane()).isDirect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getAngle(BSPTree<Sphere1D> bSPTree) {
        return ((LimitAngle) bSPTree.getCut().getHyperplane()).getLocation().getAlpha();
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public ArcsSet buildNew(BSPTree<Sphere1D> bSPTree) {
        return new ArcsSet(bSPTree, getTolerance());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion
    protected void computeGeometricalProperties() {
        double d = 0.0d;
        if (getTree(false).getCut() == null) {
            setBarycenter(S1Point.NaN);
            setSize(((Boolean) getTree(false).getAttribute()).booleanValue() ? 6.283185307179586d : 0.0d);
            return;
        }
        Iterator<double[]> it2 = iterator();
        double d2 = 0.0d;
        while (it2.hasNext()) {
            double[] next = it2.next();
            double d3 = next[1];
            double d4 = next[0];
            double d5 = d3 - d4;
            d += d5;
            d2 += d5 * (d4 + d3);
        }
        setSize(d);
        if (Precision.equals(d, 6.283185307179586d, 0)) {
            setBarycenter(S1Point.NaN);
        } else if (d >= Precision.SAFE_MIN) {
            setBarycenter(new S1Point(d2 / (d * 2.0d)));
        } else {
            setBarycenter(((LimitAngle) getTree(false).getCut().getHyperplane()).getLocation());
        }
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public BoundaryProjection<Sphere1D> projectToBoundary(Point<Sphere1D> point) {
        double alpha = ((S1Point) point).getAlpha();
        Iterator<double[]> it2 = iterator();
        double d = Double.NaN;
        double d2 = Double.NaN;
        boolean z = false;
        while (it2.hasNext()) {
            double[] next = it2.next();
            if (Double.isNaN(d2)) {
                d2 = next[0];
            }
            if (!z) {
                double d3 = next[0];
                if (alpha < d3) {
                    if (!Double.isNaN(d)) {
                        double d4 = alpha - d;
                        double d5 = next[0] - alpha;
                        if (d4 < d5) {
                            return new BoundaryProjection<>(point, new S1Point(d), d4);
                        }
                        return new BoundaryProjection<>(point, new S1Point(next[0]), d5);
                    }
                    z = true;
                } else {
                    double d6 = next[1];
                    if (alpha <= d6) {
                        double d7 = d3 - alpha;
                        double d8 = alpha - d6;
                        if (d7 < d8) {
                            return new BoundaryProjection<>(point, new S1Point(next[1]), d8);
                        }
                        return new BoundaryProjection<>(point, new S1Point(next[0]), d7);
                    }
                }
            }
            d = next[1];
        }
        if (Double.isNaN(d)) {
            return new BoundaryProjection<>(point, null, 6.283185307179586d);
        }
        if (z) {
            double d9 = alpha - (d - 6.283185307179586d);
            double d10 = d2 - alpha;
            if (d9 < d10) {
                return new BoundaryProjection<>(point, new S1Point(d), d9);
            }
            return new BoundaryProjection<>(point, new S1Point(d2), d10);
        }
        double d11 = alpha - d;
        double d12 = (6.283185307179586d + d2) - alpha;
        if (d11 < d12) {
            return new BoundaryProjection<>(point, new S1Point(d), d11);
        }
        return new BoundaryProjection<>(point, new S1Point(d2), d12);
    }

    public List<Arc> asList() {
        ArrayList arrayList = new ArrayList();
        Iterator<double[]> it2 = iterator();
        while (it2.hasNext()) {
            double[] next = it2.next();
            arrayList.add(new Arc(next[0], next[1], getTolerance()));
        }
        return arrayList;
    }

    @Override // java.lang.Iterable
    public Iterator<double[]> iterator() {
        return new SubArcsIterator();
    }

    @Deprecated
    public Side side(Arc arc) {
        return split(arc).getSide();
    }

    public Split split(Arc arc) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double inf = arc.getInf() + 3.141592653589793d;
        double sup = arc.getSup() - arc.getInf();
        Iterator<double[]> it2 = iterator();
        while (it2.hasNext()) {
            double[] next = it2.next();
            double dNormalizeAngle = MathUtils.normalizeAngle(next[0], inf) - arc.getInf();
            double d = next[0];
            double d2 = d - dNormalizeAngle;
            double d3 = next[1] - d2;
            if (dNormalizeAngle < sup) {
                arrayList.add(Double.valueOf(d));
                if (d3 > sup) {
                    double d4 = sup + d2;
                    arrayList.add(Double.valueOf(d4));
                    arrayList2.add(Double.valueOf(d4));
                    if (d3 > 6.283185307179586d) {
                        double d5 = d2 + 6.283185307179586d;
                        arrayList2.add(Double.valueOf(d5));
                        arrayList.add(Double.valueOf(d5));
                        arrayList.add(Double.valueOf(next[1]));
                    } else {
                        arrayList2.add(Double.valueOf(next[1]));
                    }
                } else {
                    arrayList.add(Double.valueOf(next[1]));
                }
            } else {
                arrayList2.add(Double.valueOf(d));
                if (d3 > 6.283185307179586d) {
                    double d6 = d2 + 6.283185307179586d;
                    arrayList2.add(Double.valueOf(d6));
                    arrayList.add(Double.valueOf(d6));
                    double d7 = sup + 6.283185307179586d;
                    if (d3 > d7) {
                        double d8 = d7 + d2;
                        arrayList.add(Double.valueOf(d8));
                        arrayList2.add(Double.valueOf(d8));
                        arrayList2.add(Double.valueOf(next[1]));
                    } else {
                        arrayList.add(Double.valueOf(next[1]));
                    }
                } else {
                    arrayList2.add(Double.valueOf(next[1]));
                }
            }
        }
        return new Split(createSplitPart(arrayList));
    }

    private void addArcLimit(BSPTree<Sphere1D> bSPTree, double d, boolean z) {
        LimitAngle limitAngle = new LimitAngle(new S1Point(d), !z, getTolerance());
        BSPTree<S> cell = bSPTree.getCell(limitAngle.getLocation(), getTolerance());
        if (cell.getCut() != null) {
            throw new MathInternalError();
        }
        cell.insertCut(limitAngle);
        cell.setAttribute(null);
        cell.getPlus().setAttribute(Boolean.FALSE);
        cell.getMinus().setAttribute(Boolean.TRUE);
    }

    private ArcsSet createSplitPart(List<Double> list) {
        if (list.isEmpty()) {
            return null;
        }
        int i = 0;
        while (i < list.size()) {
            int size = (i + 1) % list.size();
            double dDoubleValue = list.get(i).doubleValue();
            if (FastMath.abs(MathUtils.normalizeAngle(list.get(size).doubleValue(), dDoubleValue) - dDoubleValue) <= getTolerance()) {
                if (size > 0) {
                    list.remove(size);
                    list.remove(i);
                    i--;
                } else {
                    double dDoubleValue2 = list.remove(list.size() - 1).doubleValue();
                    double dDoubleValue3 = list.remove(0).doubleValue();
                    if (list.isEmpty()) {
                        if (dDoubleValue2 - dDoubleValue3 > 3.141592653589793d) {
                            return new ArcsSet((BSPTree<Sphere1D>) new BSPTree(Boolean.TRUE), getTolerance());
                        }
                        return null;
                    }
                    list.add(Double.valueOf(list.remove(0).doubleValue() + 6.283185307179586d));
                }
            }
            i++;
        }
        BSPTree<Sphere1D> bSPTree = new BSPTree<>(Boolean.FALSE);
        for (int i2 = 0; i2 < list.size() - 1; i2 += 2) {
            addArcLimit(bSPTree, list.get(i2).doubleValue(), true);
            addArcLimit(bSPTree, list.get(i2 + 1).doubleValue(), false);
        }
        if (bSPTree.getCut() == null) {
            return null;
        }
        return new ArcsSet(bSPTree, getTolerance());
    }

    public static class Split {
        private final ArcsSet minus;
        private final ArcsSet plus;

        private Split(ArcsSet arcsSet, ArcsSet arcsSet2) {
            this.plus = arcsSet;
            this.minus = arcsSet2;
        }

        public ArcsSet getMinus() {
            return this.minus;
        }

        public ArcsSet getPlus() {
            return this.plus;
        }

        public Side getSide() {
            if (this.plus != null) {
                if (this.minus != null) {
                    return Side.BOTH;
                }
                return Side.PLUS;
            }
            if (this.minus != null) {
                return Side.MINUS;
            }
            return Side.HYPER;
        }
    }

    public static class InconsistentStateAt2PiWrapping extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20140107;

        public InconsistentStateAt2PiWrapping() {
            super(LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING, new Object[0]);
        }
    }

    private class SubArcsIterator implements Iterator<double[]> {
        private final BSPTree<Sphere1D> firstStart;
        private BSPTree<Sphere1D> current;
        private double[] pending;

        SubArcsIterator() {
            BSPTree<Sphere1D> firstArcStart = ArcsSet.this.getFirstArcStart();
            this.firstStart = firstArcStart;
            this.current = firstArcStart;
            if (firstArcStart == null) {
                if (((Boolean) ArcsSet.this.getFirstLeaf(ArcsSet.this.getTree(false)).getAttribute()).booleanValue()) {
                    this.pending = new double[]{0.0d, 6.283185307179586d};
                    return;
                } else {
                    this.pending = null;
                    return;
                }
            }
            selectPending();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pending != null;
        }

        private void selectPending() {
            BSPTree<Sphere1D> bSPTreeNextInternalNode = this.current;
            while (bSPTreeNextInternalNode != null && !ArcsSet.this.isArcStart(bSPTreeNextInternalNode)) {
                bSPTreeNextInternalNode = ArcsSet.this.nextInternalNode(bSPTreeNextInternalNode);
            }
            if (bSPTreeNextInternalNode == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree<Sphere1D> bSPTreeNextInternalNode2 = bSPTreeNextInternalNode;
            while (bSPTreeNextInternalNode2 != null && !ArcsSet.this.isArcEnd(bSPTreeNextInternalNode2)) {
                bSPTreeNextInternalNode2 = ArcsSet.this.nextInternalNode(bSPTreeNextInternalNode2);
            }
            if (bSPTreeNextInternalNode2 != null) {
                this.pending = new double[]{ArcsSet.this.getAngle(bSPTreeNextInternalNode), ArcsSet.this.getAngle(bSPTreeNextInternalNode2)};
                this.current = bSPTreeNextInternalNode2;
                return;
            }
            BSPTree<Sphere1D> bSPTreePreviousInternalNode = this.firstStart;
            while (bSPTreePreviousInternalNode != null && !ArcsSet.this.isArcEnd(bSPTreePreviousInternalNode)) {
                bSPTreePreviousInternalNode = ArcsSet.this.previousInternalNode(bSPTreePreviousInternalNode);
            }
            if (bSPTreePreviousInternalNode == null) {
                throw new MathInternalError();
            }
            this.pending = new double[]{ArcsSet.this.getAngle(bSPTreeNextInternalNode), ArcsSet.this.getAngle(bSPTreePreviousInternalNode) + 6.283185307179586d};
            this.current = null;
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
