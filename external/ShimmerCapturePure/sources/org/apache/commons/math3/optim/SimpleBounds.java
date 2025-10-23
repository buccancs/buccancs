package org.apache.commons.math3.optim;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class SimpleBounds implements OptimizationData {
    private final double[] lower;
    private final double[] upper;

    public SimpleBounds(double[] dArr, double[] dArr2) {
        this.lower = (double[]) dArr.clone();
        this.upper = (double[]) dArr2.clone();
    }

    public static SimpleBounds unbounded(int i) {
        double[] dArr = new double[i];
        Arrays.fill(dArr, Double.NEGATIVE_INFINITY);
        double[] dArr2 = new double[i];
        Arrays.fill(dArr2, Double.POSITIVE_INFINITY);
        return new SimpleBounds(dArr, dArr2);
    }

    public double[] getLower() {
        return (double[]) this.lower.clone();
    }

    public double[] getUpper() {
        return (double[]) this.upper.clone();
    }
}
