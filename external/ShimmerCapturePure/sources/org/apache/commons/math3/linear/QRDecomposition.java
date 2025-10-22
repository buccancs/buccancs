package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class QRDecomposition {
    private final double threshold;
    private RealMatrix cachedH;
    private RealMatrix cachedQ;
    private RealMatrix cachedQT;
    private RealMatrix cachedR;
    private double[][] qrt;
    private double[] rDiag;

    public QRDecomposition(RealMatrix realMatrix) {
        this(realMatrix, 0.0d);
    }

    public QRDecomposition(RealMatrix realMatrix, double d) {
        this.threshold = d;
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        this.qrt = realMatrix.transpose().getData();
        this.rDiag = new double[FastMath.min(rowDimension, columnDimension)];
        this.cachedQ = null;
        this.cachedQT = null;
        this.cachedR = null;
        this.cachedH = null;
        decompose(this.qrt);
    }

    protected void decompose(double[][] dArr) {
        for (int i = 0; i < FastMath.min(dArr.length, dArr[0].length); i++) {
            performHouseholderReflection(i, dArr);
        }
    }

    protected void performHouseholderReflection(int i, double[][] dArr) {
        double[] dArr2 = dArr[i];
        double d = 0.0d;
        for (int i2 = i; i2 < dArr2.length; i2++) {
            double d2 = dArr2[i2];
            d += d2 * d2;
        }
        double dSqrt = dArr2[i] > 0.0d ? -FastMath.sqrt(d) : FastMath.sqrt(d);
        this.rDiag[i] = dSqrt;
        if (dSqrt != 0.0d) {
            dArr2[i] = dArr2[i] - dSqrt;
            for (int i3 = i + 1; i3 < dArr.length; i3++) {
                double[] dArr3 = dArr[i3];
                double d3 = 0.0d;
                for (int i4 = i; i4 < dArr3.length; i4++) {
                    d3 -= dArr3[i4] * dArr2[i4];
                }
                double d4 = d3 / (dArr2[i] * dSqrt);
                for (int i5 = i; i5 < dArr3.length; i5++) {
                    dArr3[i5] = dArr3[i5] - (dArr2[i5] * d4);
                }
            }
        }
    }

    public RealMatrix getR() {
        if (this.cachedR == null) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length2, length);
            for (int iMin = FastMath.min(length2, length) - 1; iMin >= 0; iMin--) {
                dArr2[iMin][iMin] = this.rDiag[iMin];
                for (int i = iMin + 1; i < length; i++) {
                    dArr2[iMin][i] = this.qrt[i][iMin];
                }
            }
            this.cachedR = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedR;
    }

    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = getQT().transpose();
        }
        return this.cachedQ;
    }

    public RealMatrix getQT() {
        double d;
        if (this.cachedQT == null) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length2, length2);
            int i = length2 - 1;
            while (true) {
                d = 1.0d;
                if (i < FastMath.min(length2, length)) {
                    break;
                }
                dArr2[i][i] = 1.0d;
                i--;
            }
            int iMin = FastMath.min(length2, length) - 1;
            while (iMin >= 0) {
                double[] dArr3 = this.qrt[iMin];
                dArr2[iMin][iMin] = d;
                if (dArr3[iMin] != 0.0d) {
                    for (int i2 = iMin; i2 < length2; i2++) {
                        double d2 = 0.0d;
                        for (int i3 = iMin; i3 < length2; i3++) {
                            d2 -= dArr2[i2][i3] * dArr3[i3];
                        }
                        double d3 = d2 / (this.rDiag[iMin] * dArr3[iMin]);
                        for (int i4 = iMin; i4 < length2; i4++) {
                            double[] dArr4 = dArr2[i2];
                            dArr4[i4] = dArr4[i4] + ((-d3) * dArr3[i4]);
                        }
                    }
                }
                iMin--;
                d = 1.0d;
            }
            this.cachedQT = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedQT;
    }

    public RealMatrix getH() {
        int i;
        if (this.cachedH == null) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length2, length);
            int i2 = 0;
            while (i2 < length2) {
                int i3 = 0;
                while (true) {
                    i = i2 + 1;
                    if (i3 < FastMath.min(i, length)) {
                        dArr2[i2][i3] = this.qrt[i3][i2] / (-this.rDiag[i3]);
                        i3++;
                    }
                }
                i2 = i;
            }
            this.cachedH = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedH;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.qrt, this.rDiag, this.threshold);
    }

    private static class Solver implements DecompositionSolver {
        private final double[][] qrt;
        private final double[] rDiag;
        private final double threshold;

        private Solver(double[][] dArr, double[] dArr2, double d) {
            this.qrt = dArr;
            this.rDiag = dArr2;
            this.threshold = d;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            for (double d : this.rDiag) {
                if (FastMath.abs(d) <= this.threshold) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector realVector) {
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int length2 = dArr[0].length;
            if (realVector.getDimension() != length2) {
                throw new DimensionMismatchException(realVector.getDimension(), length2);
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            double[] dArr2 = new double[length];
            double[] array = realVector.toArray();
            for (int i = 0; i < FastMath.min(length2, length); i++) {
                double[] dArr3 = this.qrt[i];
                double d = 0.0d;
                for (int i2 = i; i2 < length2; i2++) {
                    d += array[i2] * dArr3[i2];
                }
                double d2 = d / (this.rDiag[i] * dArr3[i]);
                for (int i3 = i; i3 < length2; i3++) {
                    array[i3] = array[i3] + (dArr3[i3] * d2);
                }
            }
            for (int length3 = this.rDiag.length - 1; length3 >= 0; length3--) {
                double d3 = array[length3] / this.rDiag[length3];
                array[length3] = d3;
                double[] dArr4 = this.qrt[length3];
                dArr2[length3] = d3;
                for (int i4 = 0; i4 < length3; i4++) {
                    array[i4] = array[i4] - (dArr4[i4] * d3);
                }
            }
            return new ArrayRealVector(dArr2, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix realMatrix) throws NumberIsTooSmallException, OutOfRangeException, MatrixDimensionMismatchException {
            double d;
            double[][] dArr = this.qrt;
            int length = dArr.length;
            int i = 0;
            int length2 = dArr[0].length;
            if (realMatrix.getRowDimension() != length2) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length2);
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int columnDimension = realMatrix.getColumnDimension();
            int i2 = (columnDimension + 51) / 52;
            double[][] dArrCreateBlocksLayout = BlockRealMatrix.createBlocksLayout(length, columnDimension);
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, realMatrix.getRowDimension(), 52);
            double[] dArr3 = new double[52];
            int i3 = 0;
            while (i3 < i2) {
                int i4 = i3 * 52;
                int iMin = FastMath.min(i4 + 52, columnDimension);
                int i5 = iMin - i4;
                int i6 = columnDimension;
                realMatrix.copySubMatrix(0, length2 - 1, i4, iMin - 1, dArr2);
                int i7 = 0;
                while (true) {
                    d = 1.0d;
                    if (i7 >= FastMath.min(length2, length)) {
                        break;
                    }
                    double[] dArr4 = this.qrt[i7];
                    double d2 = 1.0d / (this.rDiag[i7] * dArr4[i7]);
                    Arrays.fill(dArr3, i, i5, 0.0d);
                    int i8 = i7;
                    while (i8 < length2) {
                        double d3 = dArr4[i8];
                        double[] dArr5 = dArr2[i8];
                        while (i < i5) {
                            dArr3[i] = dArr3[i] + (dArr5[i] * d3);
                            i++;
                        }
                        i8++;
                        i = 0;
                    }
                    for (int i9 = 0; i9 < i5; i9++) {
                        dArr3[i9] = dArr3[i9] * d2;
                    }
                    for (int i10 = i7; i10 < length2; i10++) {
                        double d4 = dArr4[i10];
                        double[] dArr6 = dArr2[i10];
                        for (int i11 = 0; i11 < i5; i11++) {
                            dArr6[i11] = dArr6[i11] + (dArr3[i11] * d4);
                        }
                    }
                    i7++;
                    i = 0;
                }
                int length3 = this.rDiag.length - 1;
                while (length3 >= 0) {
                    int i12 = length3 / 52;
                    int i13 = i12 * 52;
                    double d5 = d / this.rDiag[length3];
                    double[] dArr7 = dArr2[length3];
                    double[] dArr8 = dArrCreateBlocksLayout[(i12 * i2) + i3];
                    int i14 = (length3 - i13) * i5;
                    int i15 = 0;
                    while (i15 < i5) {
                        double d6 = dArr7[i15] * d5;
                        dArr7[i15] = d6;
                        dArr8[i14] = d6;
                        i15++;
                        i14++;
                    }
                    double[] dArr9 = this.qrt[length3];
                    for (int i16 = 0; i16 < length3; i16++) {
                        double d7 = dArr9[i16];
                        double[] dArr10 = dArr2[i16];
                        for (int i17 = 0; i17 < i5; i17++) {
                            dArr10[i17] = dArr10[i17] - (dArr7[i17] * d7);
                        }
                    }
                    length3--;
                    d = 1.0d;
                }
                i3++;
                columnDimension = i6;
                i = 0;
            }
            return new BlockRealMatrix(length, columnDimension, dArrCreateBlocksLayout, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.qrt[0].length));
        }
    }
}
