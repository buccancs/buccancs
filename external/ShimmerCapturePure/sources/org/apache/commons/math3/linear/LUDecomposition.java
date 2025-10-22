package org.apache.commons.math3.linear;

import java.lang.reflect.Array;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class LUDecomposition {
    private static final double DEFAULT_TOO_SMALL = 1.0E-11d;
    private final double[][] lu;
    private final int[] pivot;
    private RealMatrix cachedL;
    private RealMatrix cachedP;
    private RealMatrix cachedU;
    private boolean even;
    private boolean singular;

    public LUDecomposition(RealMatrix realMatrix) {
        this(realMatrix, 1.0E-11d);
    }

    public LUDecomposition(RealMatrix realMatrix, double d) {
        if (!realMatrix.isSquare()) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        }
        int columnDimension = realMatrix.getColumnDimension();
        this.lu = realMatrix.getData();
        this.pivot = new int[columnDimension];
        this.cachedL = null;
        this.cachedU = null;
        this.cachedP = null;
        for (int i = 0; i < columnDimension; i++) {
            this.pivot[i] = i;
        }
        this.even = true;
        this.singular = false;
        int i2 = 0;
        while (i2 < columnDimension) {
            for (int i3 = 0; i3 < i2; i3++) {
                double[] dArr = this.lu[i3];
                double d2 = dArr[i2];
                for (int i4 = 0; i4 < i3; i4++) {
                    d2 -= dArr[i4] * this.lu[i4][i2];
                }
                dArr[i2] = d2;
            }
            double dAbs = Double.NEGATIVE_INFINITY;
            int i5 = i2;
            int i6 = i5;
            while (i5 < columnDimension) {
                double[] dArr2 = this.lu[i5];
                double d3 = dArr2[i2];
                for (int i7 = 0; i7 < i2; i7++) {
                    d3 -= dArr2[i7] * this.lu[i7][i2];
                }
                dArr2[i2] = d3;
                if (FastMath.abs(d3) > dAbs) {
                    dAbs = FastMath.abs(d3);
                    i6 = i5;
                }
                i5++;
            }
            if (FastMath.abs(this.lu[i6][i2]) < d) {
                this.singular = true;
                return;
            }
            if (i6 != i2) {
                double[][] dArr3 = this.lu;
                double[] dArr4 = dArr3[i6];
                double[] dArr5 = dArr3[i2];
                for (int i8 = 0; i8 < columnDimension; i8++) {
                    double d4 = dArr4[i8];
                    dArr4[i8] = dArr5[i8];
                    dArr5[i8] = d4;
                }
                int[] iArr = this.pivot;
                int i9 = iArr[i6];
                iArr[i6] = iArr[i2];
                iArr[i2] = i9;
                this.even = !this.even;
            }
            double d5 = this.lu[i2][i2];
            int i10 = i2 + 1;
            for (int i11 = i10; i11 < columnDimension; i11++) {
                double[] dArr6 = this.lu[i11];
                dArr6[i2] = dArr6[i2] / d5;
            }
            i2 = i10;
        }
    }

    public RealMatrix getL() throws OutOfRangeException {
        if (this.cachedL == null && !this.singular) {
            int length = this.pivot.length;
            this.cachedL = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                double[] dArr = this.lu[i];
                for (int i2 = 0; i2 < i; i2++) {
                    this.cachedL.setEntry(i, i2, dArr[i2]);
                }
                this.cachedL.setEntry(i, i, 1.0d);
            }
        }
        return this.cachedL;
    }

    public RealMatrix getU() throws OutOfRangeException {
        if (this.cachedU == null && !this.singular) {
            int length = this.pivot.length;
            this.cachedU = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                double[] dArr = this.lu[i];
                for (int i2 = i; i2 < length; i2++) {
                    this.cachedU.setEntry(i, i2, dArr[i2]);
                }
            }
        }
        return this.cachedU;
    }

    public RealMatrix getP() throws OutOfRangeException {
        if (this.cachedP == null && !this.singular) {
            int length = this.pivot.length;
            this.cachedP = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedP.setEntry(i, this.pivot[i], 1.0d);
            }
        }
        return this.cachedP;
    }

    public int[] getPivot() {
        return (int[]) this.pivot.clone();
    }

    public double getDeterminant() {
        if (this.singular) {
            return 0.0d;
        }
        int length = this.pivot.length;
        double d = this.even ? 1.0d : -1.0d;
        for (int i = 0; i < length; i++) {
            d *= this.lu[i][i];
        }
        return d;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.lu, this.pivot, this.singular);
    }

    private static class Solver implements DecompositionSolver {
        private final double[][] lu;
        private final int[] pivot;
        private final boolean singular;

        private Solver(double[][] dArr, int[] iArr, boolean z) {
            this.lu = dArr;
            this.pivot = iArr;
            this.singular = z;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            return !this.singular;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector realVector) {
            int length = this.pivot.length;
            if (realVector.getDimension() != length) {
                throw new DimensionMismatchException(realVector.getDimension(), length);
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            double[] dArr = new double[length];
            for (int i = 0; i < length; i++) {
                dArr[i] = realVector.getEntry(this.pivot[i]);
            }
            int i2 = 0;
            while (i2 < length) {
                double d = dArr[i2];
                int i3 = i2 + 1;
                for (int i4 = i3; i4 < length; i4++) {
                    dArr[i4] = dArr[i4] - (this.lu[i4][i2] * d);
                }
                i2 = i3;
            }
            for (int i5 = length - 1; i5 >= 0; i5--) {
                double d2 = dArr[i5] / this.lu[i5][i5];
                dArr[i5] = d2;
                for (int i6 = 0; i6 < i5; i6++) {
                    dArr[i6] = dArr[i6] - (this.lu[i6][i5] * d2);
                }
            }
            return new ArrayRealVector(dArr, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix realMatrix) {
            int length = this.pivot.length;
            if (realMatrix.getRowDimension() != length) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length);
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            int columnDimension = realMatrix.getColumnDimension();
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, columnDimension);
            for (int i = 0; i < length; i++) {
                double[] dArr2 = dArr[i];
                int i2 = this.pivot[i];
                for (int i3 = 0; i3 < columnDimension; i3++) {
                    dArr2[i3] = realMatrix.getEntry(i2, i3);
                }
            }
            int i4 = 0;
            while (i4 < length) {
                double[] dArr3 = dArr[i4];
                int i5 = i4 + 1;
                for (int i6 = i5; i6 < length; i6++) {
                    double[] dArr4 = dArr[i6];
                    double d = this.lu[i6][i4];
                    for (int i7 = 0; i7 < columnDimension; i7++) {
                        dArr4[i7] = dArr4[i7] - (dArr3[i7] * d);
                    }
                }
                i4 = i5;
            }
            for (int i8 = length - 1; i8 >= 0; i8--) {
                double[] dArr5 = dArr[i8];
                double d2 = this.lu[i8][i8];
                for (int i9 = 0; i9 < columnDimension; i9++) {
                    dArr5[i9] = dArr5[i9] / d2;
                }
                for (int i10 = 0; i10 < i8; i10++) {
                    double[] dArr6 = dArr[i10];
                    double d3 = this.lu[i10][i8];
                    for (int i11 = 0; i11 < columnDimension; i11++) {
                        dArr6[i11] = dArr6[i11] - (dArr5[i11] * d3);
                    }
                }
            }
            return new Array2DRowRealMatrix(dArr, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.pivot.length));
        }
    }
}
