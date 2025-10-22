package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RectangularCholeskyDecomposition;

/* loaded from: classes5.dex */
public class CorrelatedRandomVectorGenerator implements RandomVectorGenerator {
    private final NormalizedRandomGenerator generator;
    private final double[] mean;
    private final double[] normalized;
    private final RealMatrix root;

    public CorrelatedRandomVectorGenerator(double[] dArr, RealMatrix realMatrix, double d, NormalizedRandomGenerator normalizedRandomGenerator) {
        int rowDimension = realMatrix.getRowDimension();
        if (dArr.length != rowDimension) {
            throw new DimensionMismatchException(dArr.length, rowDimension);
        }
        this.mean = (double[]) dArr.clone();
        RectangularCholeskyDecomposition rectangularCholeskyDecomposition = new RectangularCholeskyDecomposition(realMatrix, d);
        this.root = rectangularCholeskyDecomposition.getRootMatrix();
        this.generator = normalizedRandomGenerator;
        this.normalized = new double[rectangularCholeskyDecomposition.getRank()];
    }

    public CorrelatedRandomVectorGenerator(RealMatrix realMatrix, double d, NormalizedRandomGenerator normalizedRandomGenerator) {
        int rowDimension = realMatrix.getRowDimension();
        this.mean = new double[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            this.mean[i] = 0.0d;
        }
        RectangularCholeskyDecomposition rectangularCholeskyDecomposition = new RectangularCholeskyDecomposition(realMatrix, d);
        this.root = rectangularCholeskyDecomposition.getRootMatrix();
        this.generator = normalizedRandomGenerator;
        this.normalized = new double[rectangularCholeskyDecomposition.getRank()];
    }

    public NormalizedRandomGenerator getGenerator() {
        return this.generator;
    }

    public RealMatrix getRootMatrix() {
        return this.root;
    }

    public int getRank() {
        return this.normalized.length;
    }

    @Override // org.apache.commons.math3.random.RandomVectorGenerator
    public double[] nextVector() {
        int i = 0;
        while (true) {
            double[] dArr = this.normalized;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = this.generator.nextNormalizedDouble();
            i++;
        }
        int length = this.mean.length;
        double[] dArr2 = new double[length];
        for (int i2 = 0; i2 < length; i2++) {
            dArr2[i2] = this.mean[i2];
            for (int i3 = 0; i3 < this.root.getColumnDimension(); i3++) {
                dArr2[i2] = dArr2[i2] + (this.root.getEntry(i2, i3) * this.normalized[i3]);
            }
        }
        return dArr2;
    }
}
