package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;

/* loaded from: classes5.dex */
public class LimitAngle implements Hyperplane<Sphere1D> {
    private final double tolerance;
    private boolean direct;
    private S1Point location;

    public LimitAngle(S1Point s1Point, boolean z, double d) {
        this.location = s1Point;
        this.direct = z;
        this.tolerance = d;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public LimitAngle copySelf() {
        return this;
    }

    public S1Point getLocation() {
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
    public Point<Sphere1D> project(Point<Sphere1D> point) {
        return this.location;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getOffset(Point<Sphere1D> point) {
        double alpha = ((S1Point) point).getAlpha() - this.location.getAlpha();
        return this.direct ? alpha : -alpha;
    }

    public LimitAngle getReverse() {
        return new LimitAngle(this.location, !this.direct, this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public SubLimitAngle wholeHyperplane() {
        return new SubLimitAngle(this, null);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public ArcsSet wholeSpace() {
        return new ArcsSet(this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public boolean sameOrientationAs(Hyperplane<Sphere1D> hyperplane) {
        return !(((LimitAngle) hyperplane).direct ^ this.direct);
    }
}
