package org.apache.commons.math.random;

import org.apache.commons.math.DimensionMismatchException;
import org.apache.commons.math.linear.MatrixIndexException;
import org.apache.commons.math.linear.NotPositiveDefiniteMatrixException;
import org.apache.commons.math.linear.RealMatrix;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/CorrelatedRandomVectorGenerator.class */
public class CorrelatedRandomVectorGenerator implements RandomVectorGenerator {
    private final double[] mean;
    private final NormalizedRandomGenerator generator;
    private final double[] normalized;
    private RealMatrix root;
    private int rank;

    public CorrelatedRandomVectorGenerator(double[] mean, RealMatrix covariance, double small, NormalizedRandomGenerator generator) throws MatrixIndexException, DimensionMismatchException, NotPositiveDefiniteMatrixException {
        int order = covariance.getRowDimension();
        if (mean.length != order) {
            throw new DimensionMismatchException(mean.length, order);
        }
        this.mean = (double[]) mean.clone();
        decompose(covariance, small);
        this.generator = generator;
        this.normalized = new double[this.rank];
    }

    public CorrelatedRandomVectorGenerator(RealMatrix covariance, double small, NormalizedRandomGenerator generator) throws MatrixIndexException, NotPositiveDefiniteMatrixException {
        int order = covariance.getRowDimension();
        this.mean = new double[order];
        for (int i = 0; i < order; i++) {
            this.mean[i] = 0.0d;
        }
        decompose(covariance, small);
        this.generator = generator;
        this.normalized = new double[this.rank];
    }

    public NormalizedRandomGenerator getGenerator() {
        return this.generator;
    }

    public RealMatrix getRootMatrix() {
        return this.root;
    }

    public int getRank() {
        return this.rank;
    }

    /* JADX WARN: Incorrect condition in loop: B:8:0x0047 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void decompose(org.apache.commons.math.linear.RealMatrix r10, double r11) throws org.apache.commons.math.linear.MatrixIndexException, org.apache.commons.math.linear.NotPositiveDefiniteMatrixException {
        /*
            Method dump skipped, instructions count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.random.CorrelatedRandomVectorGenerator.decompose(org.apache.commons.math.linear.RealMatrix, double):void");
    }

    @Override // org.apache.commons.math.random.RandomVectorGenerator
    public double[] nextVector() {
        for (int i = 0; i < this.rank; i++) {
            this.normalized[i] = this.generator.nextNormalizedDouble();
        }
        double[] correlated = new double[this.mean.length];
        for (int i2 = 0; i2 < correlated.length; i2++) {
            correlated[i2] = this.mean[i2];
            for (int j = 0; j < this.rank; j++) {
                int i3 = i2;
                correlated[i3] = correlated[i3] + (this.root.getEntry(i2, j) * this.normalized[j]);
            }
        }
        return correlated;
    }
}
