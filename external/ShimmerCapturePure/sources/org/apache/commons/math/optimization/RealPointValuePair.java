package org.apache.commons.math.optimization;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/RealPointValuePair.class */
public class RealPointValuePair implements Serializable {
    private static final long serialVersionUID = 1003888396256744753L;
    private final double[] point;
    private final double value;

    public RealPointValuePair(double[] point, double value) {
        this.point = point == null ? null : (double[]) point.clone();
        this.value = value;
    }

    public RealPointValuePair(double[] point, double value, boolean copyArray) {
        this.point = copyArray ? point == null ? null : (double[]) point.clone() : point;
        this.value = value;
    }

    public double[] getPoint() {
        if (this.point == null) {
            return null;
        }
        return (double[]) this.point.clone();
    }

    public double[] getPointRef() {
        return this.point;
    }

    public double getValue() {
        return this.value;
    }
}
