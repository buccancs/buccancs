package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;

/* loaded from: classes5.dex */
public class SubCircle extends AbstractSubHyperplane<Sphere2D, Sphere1D> {
    public SubCircle(Hyperplane<Sphere2D> hyperplane, Region<Sphere1D> region) {
        super(hyperplane, region);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane
    protected AbstractSubHyperplane<Sphere2D, Sphere1D> buildNew(Hyperplane<Sphere2D> hyperplane, Region<Sphere1D> region) {
        return new SubCircle(hyperplane, region);
    }

    @Override
    // org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane, org.apache.commons.math3.geometry.partitioning.SubHyperplane
    public SubHyperplane.SplitSubHyperplane<Sphere2D> split(Hyperplane<Sphere2D> hyperplane) throws MathArithmeticException {
        Circle circle = (Circle) getHyperplane();
        Circle circle2 = (Circle) hyperplane;
        double dAngle = Vector3D.angle(circle.getPole(), circle2.getPole());
        if (dAngle < circle.getTolerance() || dAngle > 3.141592653589793d - circle.getTolerance()) {
            return new SubHyperplane.SplitSubHyperplane<>(null, null);
        }
        ArcsSet.Split split = ((ArcsSet) getRemainingRegion()).split(circle.getInsideArc(circle2));
        ArcsSet plus = split.getPlus();
        ArcsSet minus = split.getMinus();
        return new SubHyperplane.SplitSubHyperplane<>(plus == null ? null : new SubCircle(circle.copySelf(), plus), minus != null ? new SubCircle(circle.copySelf(), minus) : null);
    }
}
