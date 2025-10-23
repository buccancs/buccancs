package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class CholeskyDecomposition {
    public static final double DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD = 1.0E-10d;
    public static final double DEFAULT_RELATIVE_SYMMETRY_THRESHOLD = 1.0E-15d;
    private RealMatrix cachedL;
    private RealMatrix cachedLT;
    private double[][] lTData;

    public CholeskyDecomposition(RealMatrix realMatrix) {
        this(realMatrix, 1.0E-15d, 1.0E-10d);
    }

    public CholeskyDecomposition(RealMatrix realMatrix, double d, double d2) {
        if (!realMatrix.isSquare()) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        }
        int rowDimension = realMatrix.getRowDimension();
        this.lTData = realMatrix.getData();
        this.cachedL = null;
        this.cachedLT = null;
        int i = 0;
        while (i < rowDimension) {
            double[] dArr = this.lTData[i];
            int i2 = i + 1;
            int i3 = i2;
            while (i3 < rowDimension) {
                double[] dArr2 = this.lTData[i3];
                double d3 = dArr[i3];
                double d4 = dArr2[i];
                int i4 = i;
                if (FastMath.abs(d3 - d4) > FastMath.max(FastMath.abs(d3), FastMath.abs(d4)) * d) {
                    throw new NonSymmetricMatrixException(i4, i3, d);
                }
                dArr2[i4] = 0.0d;
                i3++;
                i = i4;
            }
            i = i2;
        }
        for (int i5 = 0; i5 < rowDimension; i5++) {
            double[] dArr3 = this.lTData[i5];
            double d5 = dArr3[i5];
            if (d5 <= d2) {
                throw new NonPositiveDefiniteMatrixException(dArr3[i5], i5, d2);
            }
            double dSqrt = FastMath.sqrt(d5);
            dArr3[i5] = dSqrt;
            double d6 = 1.0d / dSqrt;
            for (int i6 = rowDimension - 1; i6 > i5; i6--) {
                dArr3[i6] = dArr3[i6] * d6;
                double[] dArr4 = this.lTData[i6];
                for (int i7 = i6; i7 < rowDimension; i7++) {
                    dArr4[i7] = dArr4[i7] - (dArr3[i6] * dArr3[i7]);
                }
            }
        }
    }

    public RealMatrix getL() {
        if (this.cachedL == null) {
            this.cachedL = getLT().transpose();
        }
        return this.cachedL;
    }

    public RealMatrix getLT() {
        if (this.cachedLT == null) {
            this.cachedLT = MatrixUtils.createRealMatrix(this.lTData);
        }
        return this.cachedLT;
    }

    public double getDeterminant() {
        double d = 1.0d;
        int i = 0;
        while (true) {
            double[][] dArr = this.lTData;
            if (i >= dArr.length) {
                return d;
            }
            double d2 = dArr[i][i];
            d *= d2 * d2;
            i++;
        }
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.lTData);
    }

    private static class Solver implements DecompositionSolver {
        private final double[][] lTData;

        private Solver(double[][] dArr) {
            this.lTData = dArr;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            return true;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector realVector) {
            int length = this.lTData.length;
            if (realVector.getDimension() != length) {
                throw new DimensionMismatchException(realVector.getDimension(), length);
            }
            double[] array = realVector.toArray();
            int i = 0;
            while (i < length) {
                double[] dArr = this.lTData[i];
                double d = array[i] / dArr[i];
                array[i] = d;
                i++;
                for (int i2 = i; i2 < length; i2++) {
                    array[i2] = array[i2] - (dArr[i2] * d);
                }
            }
            for (int i3 = length - 1; i3 >= 0; i3--) {
                double d2 = array[i3] / this.lTData[i3][i3];
                array[i3] = d2;
                for (int i4 = 0; i4 < i3; i4++) {
                    array[i4] = array[i4] - (this.lTData[i4][i3] * d2);
                }
            }
            return new ArrayRealVector(array, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix realMatrix) {
            int length = this.lTData.length;
            if (realMatrix.getRowDimension() != length) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length);
            }
            int columnDimension = realMatrix.getColumnDimension();
            double[][] data = realMatrix.getData();
            int i = 0;
            while (i < length) {
                double[] dArr = this.lTData[i];
                double d = dArr[i];
                double[] dArr2 = data[i];
                for (int i2 = 0; i2 < columnDimension; i2++) {
                    dArr2[i2] = dArr2[i2] / d;
                }
                i++;
                for (int i3 = i; i3 < length; i3++) {
                    double[] dArr3 = data[i3];
                    double d2 = dArr[i3];
                    for (int i4 = 0; i4 < columnDimension; i4++) {
                        dArr3[i4] = dArr3[i4] - (dArr2[i4] * d2);
                    }
                }
            }
            for (int i5 = length - 1; i5 >= 0; i5--) {
                double d3 = this.lTData[i5][i5];
                double[] dArr4 = data[i5];
                for (int i6 = 0; i6 < columnDimension; i6++) {
                    dArr4[i6] = dArr4[i6] / d3;
                }
                for (int i7 = 0; i7 < i5; i7++) {
                    double[] dArr5 = data[i7];
                    double d4 = this.lTData[i7][i5];
                    for (int i8 = 0; i8 < columnDimension; i8++) {
                        dArr5[i8] = dArr5[i8] - (dArr4[i8] * d4);
                    }
                }
            }
            return new Array2DRowRealMatrix(data);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.lTData.length));
        }
    }
}
