package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;

/* loaded from: classes5.dex */
public class OrientedPoint implements Hyperplane<Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private final double tolerance;
    private boolean direct;
    private Vector1D location;

    public OrientedPoint(Vector1D vector1D, boolean z, double d) {
        this.location = vector1D;
        this.direct = z;
        this.tolerance = d;
    }

    @Deprecated
    public OrientedPoint(Vector1D vector1D, boolean z) {
        this(vector1D, z, 1.0E-10d);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public OrientedPoint copySelf() {
        return this;
    }

    public Vector1D getLocation() {
        return this.location;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getTolerance() {
        return this.tolerance;
    }

    public boolean isDirect() {
        return this.direct;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Point<Euclidean1D> project(Point<Euclidean1D> point) {
        return this.location;
    }

    public void revertSelf() {
        this.direct = !this.direct;
    }

    public double getOffset(Vector<Euclidean1D> vector) {
        return getOffset((Point<Euclidean1D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getOffset(Point<Euclidean1D> point) {
        double x = ((Vector1D) point).getX() - this.location.getX();
        return this.direct ? x : -x;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public SubOrientedPoint wholeHyperplane() {
        return new SubOrientedPoint(this, null);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public IntervalsSet wholeSpace() {
        return new IntervalsSet(this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public boolean sameOrientationAs(Hyperplane<Euclidean1D> hyperplane) {
        return !(((OrientedPoint) hyperplane).direct ^ this.direct);
    }
}
