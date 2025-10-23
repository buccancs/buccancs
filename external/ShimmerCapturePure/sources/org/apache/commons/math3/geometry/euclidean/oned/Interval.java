package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.partitioning.Region;

/* loaded from: classes5.dex */
public class Interval {
    private final double lower;
    private final double upper;

    public Interval(double d, double d2) {
        if (d2 < d) {
            throw new NumberIsTooSmallException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d2), Double.valueOf(d), true);
        }
        this.lower = d;
        this.upper = d2;
    }

    public double getBarycenter() {
        return (this.lower + this.upper) * 0.5d;
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

    @Deprecated
    public double getLower() {
        return getInf();
    }

    @Deprecated
    public double getUpper() {
        return getSup();
    }

    @Deprecated
    public double getLength() {
        return getSize();
    }

    @Deprecated
    public double getMidPoint() {
        return getBarycenter();
    }

    public Region.Location checkPoint(double d, double d2) {
        double d3 = this.lower;
        if (d >= d3 - d2) {
            double d4 = this.upper;
            if (d <= d4 + d2) {
                if (d > d3 + d2 && d < d4 - d2) {
                    return Region.Location.INSIDE;
                }
                return Region.Location.BOUNDARY;
            }
        }
        return Region.Location.OUTSIDE;
    }
}
