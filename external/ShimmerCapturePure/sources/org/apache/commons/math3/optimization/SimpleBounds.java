package org.apache.commons.math3.optimization;

@Deprecated
/* loaded from: classes5.dex */
public class SimpleBounds implements OptimizationData {
    private final double[] lower;
    private final double[] upper;

    public SimpleBounds(double[] dArr, double[] dArr2) {
        this.lower = (double[]) dArr.clone();
        this.upper = (double[]) dArr2.clone();
    }

    public double[] getLower() {
        return (double[]) this.lower.clone();
    }

    public double[] getUpper() {
        return (double[]) this.upper.clone();
    }
}
