package org.apache.commons.math3.random;

import java.util.Arrays;

import org.apache.commons.math3.exception.DimensionMismatchException;

/* loaded from: classes5.dex */
public class UncorrelatedRandomVectorGenerator implements RandomVectorGenerator {
    private final NormalizedRandomGenerator generator;
    private final double[] mean;
    private final double[] standardDeviation;

    public UncorrelatedRandomVectorGenerator(double[] dArr, double[] dArr2, NormalizedRandomGenerator normalizedRandomGenerator) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        this.mean = (double[]) dArr.clone();
        this.standardDeviation = (double[]) dArr2.clone();
        this.generator = normalizedRandomGenerator;
    }

    public UncorrelatedRandomVectorGenerator(int i, NormalizedRandomGenerator normalizedRandomGenerator) {
        this.mean = new double[i];
        double[] dArr = new double[i];
        this.standardDeviation = dArr;
        Arrays.fill(dArr, 1.0d);
        this.generator = normalizedRandomGenerator;
    }

    @Override // org.apache.commons.math3.random.RandomVectorGenerator
    public double[] nextVector() {
        int length = this.mean.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = this.mean[i] + (this.standardDeviation[i] * this.generator.nextNormalizedDouble());
        }
        return dArr;
    }
}
