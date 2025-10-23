package org.apache.commons.math3.optimization.univariate;

import java.io.Serializable;

@Deprecated
/* loaded from: classes5.dex */
public class UnivariatePointValuePair implements Serializable {
    private static final long serialVersionUID = 1003888396256744753L;
    private final double point;
    private final double value;

    public UnivariatePointValuePair(double d, double d2) {
        this.point = d;
        this.value = d2;
    }

    public double getPoint() {
        return this.point;
    }

    public double getValue() {
        return this.value;
    }
}
