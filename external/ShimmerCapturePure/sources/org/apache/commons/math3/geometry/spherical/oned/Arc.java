package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class Arc {
    private final double lower;
    private final double middle;
    private final double tolerance;
    private final double upper;

    public Arc(double d, double d2, double d3) throws NumberIsTooLargeException {
        this.tolerance = d3;
        if (!Precision.equals(d, d2, 0)) {
            double d4 = d2 - d;
            if (d4 < 6.283185307179586d) {
                if (d <= d2) {
                    double dNormalizeAngle = MathUtils.normalizeAngle(d, 3.141592653589793d);
                    this.lower = dNormalizeAngle;
                    double d5 = d4 + dNormalizeAngle;
                    this.upper = d5;
                    this.middle = (dNormalizeAngle + d5) * 0.5d;
                    return;
                }
                throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d), Double.valueOf(d2), true);
            }
        }
        this.lower = 0.0d;
        this.upper = 6.283185307179586d;
        this.middle = 3.141592653589793d;
    }

    public double getBarycenter() {
        return this.middle;
    }

    public double getInf() {
        return this.lower;
    }

    public double getSize() {
        return this.upper - this.lower;
    }

    public double getSup() {
        return this.upper;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public Region.Location checkPoint(double d) {
        double dNormalizeAngle = MathUtils.normalizeAngle(d, this.middle);
        double d2 = this.lower;
        double d3 = this.tolerance;
        if (dNormalizeAngle >= d2 - d3) {
            double d4 = this.upper;
            if (dNormalizeAngle <= d4 + d3) {
                if (dNormalizeAngle <= d2 + d3 || dNormalizeAngle >= d4 - d3) {
                    return getSize() >= 6.283185307179586d - this.tolerance ? Region.Location.INSIDE : Region.Location.BOUNDARY;
                }
                return Region.Location.INSIDE;
            }
        }
        return Region.Location.OUTSIDE;
    }
}
