package org.apache.commons.math3.optimization.general;

import java.util.Arrays;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes5.dex */
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
    private final double costRelativeTolerance;
    private final double initialStepBoundFactor;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private final double qrRankingThreshold;
    private double[] beta;
    private double[] diagR;
    private double[] jacNorm;
    private double[] lmDir;
    private double lmPar;
    private int[] permutation;
    private int rank;
    private int solvedCols;
    private double[][] weightedJacobian;
    private double[] weightedResidual;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        this(100.0d, convergenceChecker, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, ConvergenceChecker<PointVectorValuePair> convergenceChecker, double d2, double d3, double d4, double d5) {
        super(convergenceChecker);
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3) {
        this(100.0d, d, d2, d3, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3, double d4, double d5) {
        super(null);
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x0329, code lost:
    
        setCost(r13);
        r62.point = r4.getPoint();
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0332, code lost:
    
        return r4;
     */
    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected org.apache.commons.math3.optimization.PointVectorValuePair doOptimize() throws org.apache.commons.math3.exception.ConvergenceException {
        /*
            Method dump skipped, instructions count: 967
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize():org.apache.commons.math3.optimization.PointVectorValuePair");
    }

    private void determineLMParameter(double[] dArr, double d, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5) {
        int i;
        double d2;
        double dMax;
        double d3;
        long j;
        double dMin;
        double[] dArr6 = dArr;
        double d4 = d;
        int length = this.weightedJacobian[0].length;
        int i2 = 0;
        while (true) {
            i = this.rank;
            if (i2 >= i) {
                break;
            }
            this.lmDir[this.permutation[i2]] = dArr6[i2];
            i2++;
        }
        while (i < length) {
            this.lmDir[this.permutation[i]] = 0.0d;
            i++;
        }
        for (int i3 = this.rank - 1; i3 >= 0; i3--) {
            int i4 = this.permutation[i3];
            double d5 = this.lmDir[i4] / this.diagR[i4];
            for (int i5 = 0; i5 < i3; i5++) {
                double[] dArr7 = this.lmDir;
                int i6 = this.permutation[i5];
                dArr7[i6] = dArr7[i6] - (this.weightedJacobian[i5][i4] * d5);
            }
            this.lmDir[i4] = d5;
        }
        double d6 = 0.0d;
        for (int i7 = 0; i7 < this.solvedCols; i7++) {
            int i8 = this.permutation[i7];
            double d7 = dArr2[i8] * this.lmDir[i8];
            dArr3[i8] = d7;
            d6 += d7 * d7;
        }
        double dSqrt = FastMath.sqrt(d6);
        double d8 = dSqrt - d4;
        double d9 = d4 * 0.1d;
        if (d8 <= d9) {
            this.lmPar = 0.0d;
            return;
        }
        if (this.rank == this.solvedCols) {
            for (int i9 = 0; i9 < this.solvedCols; i9++) {
                int i10 = this.permutation[i9];
                dArr3[i10] = dArr3[i10] * (dArr2[i10] / dSqrt);
            }
            d2 = d9;
            double d10 = 0.0d;
            for (int i11 = 0; i11 < this.solvedCols; i11++) {
                int i12 = this.permutation[i11];
                double d11 = 0.0d;
                for (int i13 = 0; i13 < i11; i13++) {
                    d11 += this.weightedJacobian[i13][i12] * dArr3[this.permutation[i13]];
                }
                double d12 = (dArr3[i12] - d11) / this.diagR[i12];
                dArr3[i12] = d12;
                d10 += d12 * d12;
            }
            dMax = d8 / (d10 * d4);
        } else {
            d2 = d9;
            dMax = 0.0d;
        }
        int i14 = 0;
        double d13 = 0.0d;
        while (i14 < this.solvedCols) {
            int i15 = this.permutation[i14];
            double d14 = d8;
            double d15 = 0.0d;
            for (int i16 = 0; i16 <= i14; i16++) {
                d15 += this.weightedJacobian[i16][i15] * dArr6[i16];
            }
            double d16 = d15 / dArr2[i15];
            d13 += d16 * d16;
            i14++;
            d8 = d14;
        }
        double d17 = d8;
        double dSqrt2 = FastMath.sqrt(d13);
        double dMin2 = dSqrt2 / d4;
        double d18 = 0.0d;
        if (dMin2 == 0.0d) {
            dMin2 = 2.2251E-308d / FastMath.min(d4, 0.1d);
        }
        double dMin3 = FastMath.min(dMin2, FastMath.max(this.lmPar, dMax));
        this.lmPar = dMin3;
        if (dMin3 == 0.0d) {
            this.lmPar = dSqrt2 / dSqrt;
        }
        int i17 = 10;
        double d19 = d17;
        while (i17 >= 0) {
            if (this.lmPar == d18) {
                double d20 = 0.001d * dMin2;
                d3 = dMin2;
                j = 4503652538340969L;
                this.lmPar = FastMath.max(2.2251E-308d, d20);
            } else {
                d3 = dMin2;
                j = 4503652538340969L;
            }
            double dSqrt3 = FastMath.sqrt(this.lmPar);
            for (int i18 = 0; i18 < this.solvedCols; i18++) {
                int i19 = this.permutation[i18];
                dArr3[i19] = dArr2[i19] * dSqrt3;
            }
            double[] dArr8 = dArr4;
            determineLMDirection(dArr6, dArr3, dArr8, dArr5);
            double d21 = 0.0d;
            for (int i20 = 0; i20 < this.solvedCols; i20++) {
                int i21 = this.permutation[i20];
                double d22 = dArr2[i21] * this.lmDir[i21];
                dArr5[i21] = d22;
                d21 += d22 * d22;
            }
            double dSqrt4 = FastMath.sqrt(d21);
            double d23 = dSqrt4 - d4;
            if (FastMath.abs(d23) <= d2) {
                return;
            }
            if (dMax == 0.0d && d23 <= d19 && d19 < 0.0d) {
                return;
            }
            for (int i22 = 0; i22 < this.solvedCols; i22++) {
                int i23 = this.permutation[i22];
                dArr3[i23] = (dArr5[i23] * dArr2[i23]) / dSqrt4;
            }
            int i24 = 0;
            while (i24 < this.solvedCols) {
                int i25 = this.permutation[i24];
                double d24 = dArr3[i25] / dArr8[i24];
                dArr3[i25] = d24;
                int i26 = i24 + 1;
                while (i26 < this.solvedCols) {
                    int i27 = this.permutation[i26];
                    dArr3[i27] = dArr3[i27] - (this.weightedJacobian[i26][i25] * d24);
                    i26++;
                }
                dArr8 = dArr4;
                i24 = i26;
            }
            double d25 = 0.0d;
            for (int i28 = 0; i28 < this.solvedCols; i28++) {
                double d26 = dArr3[this.permutation[i28]];
                d25 += d26 * d26;
            }
            double d27 = d23 / (d25 * d4);
            if (d23 > 0.0d) {
                dMax = FastMath.max(dMax, this.lmPar);
            } else {
                if (d23 < 0.0d) {
                    dMin = FastMath.min(d3, this.lmPar);
                }
                this.lmPar = FastMath.max(dMax, this.lmPar + d27);
                i17--;
                d19 = d23;
                d18 = 0.0d;
                dMin2 = dMin;
                dArr6 = dArr;
                d4 = d;
            }
            dMin = d3;
            this.lmPar = FastMath.max(dMax, this.lmPar + d27);
            i17--;
            d19 = d23;
            d18 = 0.0d;
            dMin2 = dMin;
            dArr6 = dArr;
            d4 = d;
        }
    }

    private void determineLMDirection(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) {
        int i;
        int i2;
        double dSqrt;
        double d;
        int i3 = 0;
        while (i3 < this.solvedCols) {
            int i4 = this.permutation[i3];
            int i5 = i3 + 1;
            for (int i6 = i5; i6 < this.solvedCols; i6++) {
                double[][] dArr5 = this.weightedJacobian;
                dArr5[i6][i4] = dArr5[i3][this.permutation[i6]];
            }
            this.lmDir[i3] = this.diagR[i4];
            dArr4[i3] = dArr[i3];
            i3 = i5;
        }
        int i7 = 0;
        while (true) {
            i = this.solvedCols;
            double d2 = 0.0d;
            if (i7 >= i) {
                break;
            }
            double d3 = dArr2[this.permutation[i7]];
            if (d3 != 0.0d) {
                Arrays.fill(dArr3, i7 + 1, dArr3.length, 0.0d);
            }
            dArr3[i7] = d3;
            int i8 = i7;
            double d4 = 0.0d;
            while (i8 < this.solvedCols) {
                int i9 = this.permutation[i8];
                if (dArr3[i8] != d2) {
                    double d5 = this.weightedJacobian[i8][i9];
                    if (FastMath.abs(d5) < FastMath.abs(dArr3[i8])) {
                        double d6 = d5 / dArr3[i8];
                        dSqrt = 1.0d / FastMath.sqrt((d6 * d6) + 1.0d);
                        d = d6 * dSqrt;
                    } else {
                        double d7 = dArr3[i8] / d5;
                        double dSqrt2 = 1.0d / FastMath.sqrt((d7 * d7) + 1.0d);
                        dSqrt = dSqrt2 * d7;
                        d = dSqrt2;
                    }
                    double d8 = dSqrt;
                    this.weightedJacobian[i8][i9] = (d5 * d) + (dArr3[i8] * d8);
                    double d9 = dArr4[i8];
                    double d10 = (d * d9) + (d8 * d4);
                    i2 = i7;
                    double d11 = -d8;
                    d4 = (d4 * d) + (d9 * d11);
                    dArr4[i8] = d10;
                    for (int i10 = i8 + 1; i10 < this.solvedCols; i10++) {
                        double[] dArr6 = this.weightedJacobian[i10];
                        double d12 = dArr6[i9];
                        double d13 = dArr3[i10];
                        dArr3[i10] = (d12 * d11) + (d13 * d);
                        dArr6[i9] = (d * d12) + (d8 * d13);
                    }
                } else {
                    i2 = i7;
                }
                i8++;
                i7 = i2;
                d2 = 0.0d;
            }
            int i11 = i7;
            double[] dArr7 = this.weightedJacobian[i11];
            int i12 = this.permutation[i11];
            dArr3[i11] = dArr7[i12];
            dArr7[i12] = this.lmDir[i11];
            i7 = i11 + 1;
        }
        int i13 = 0;
        while (true) {
            int i14 = this.solvedCols;
            if (i13 >= i14) {
                break;
            }
            if (dArr3[i13] == 0.0d && i == i14) {
                i = i13;
            }
            if (i < i14) {
                dArr4[i13] = 0.0d;
            }
            i13++;
        }
        if (i > 0) {
            for (int i15 = i - 1; i15 >= 0; i15--) {
                int i16 = this.permutation[i15];
                double d14 = 0.0d;
                for (int i17 = i15 + 1; i17 < i; i17++) {
                    d14 += this.weightedJacobian[i17][i16] * dArr4[i17];
                }
                dArr4[i15] = (dArr4[i15] - d14) / dArr3[i15];
            }
        }
        int i18 = 0;
        while (true) {
            double[] dArr8 = this.lmDir;
            if (i18 >= dArr8.length) {
                return;
            }
            dArr8[this.permutation[i18]] = dArr4[i18];
            i18++;
        }
    }

    private void qrDecomposition(RealMatrix realMatrix) throws ConvergenceException {
        double[][] data = realMatrix.scalarMultiply(-1.0d).getData();
        this.weightedJacobian = data;
        int length = data.length;
        char c = 0;
        int length2 = data[0].length;
        int i = 0;
        while (true) {
            double d = 0.0d;
            if (i >= length2) {
                break;
            }
            this.permutation[i] = i;
            for (int i2 = 0; i2 < length; i2++) {
                double d2 = this.weightedJacobian[i2][i];
                d += d2 * d2;
            }
            this.jacNorm[i] = FastMath.sqrt(d);
            i++;
        }
        int i3 = 0;
        while (i3 < length2) {
            int i4 = -1;
            double d3 = Double.NEGATIVE_INFINITY;
            for (int i5 = i3; i5 < length2; i5++) {
                double d4 = 0.0d;
                for (int i6 = i3; i6 < length; i6++) {
                    double d5 = this.weightedJacobian[i6][this.permutation[i5]];
                    d4 += d5 * d5;
                }
                if (Double.isInfinite(d4) || Double.isNaN(d4)) {
                    LocalizedFormats localizedFormats = LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN;
                    Object[] objArr = new Object[2];
                    objArr[c] = Integer.valueOf(length);
                    objArr[1] = Integer.valueOf(length2);
                    throw new ConvergenceException(localizedFormats, objArr);
                }
                if (d4 > d3) {
                    i4 = i5;
                    d3 = d4;
                }
            }
            if (d3 <= this.qrRankingThreshold) {
                this.rank = i3;
                return;
            }
            int[] iArr = this.permutation;
            int i7 = iArr[i4];
            iArr[i4] = iArr[i3];
            iArr[i3] = i7;
            double d6 = this.weightedJacobian[i3][i7];
            double dSqrt = FastMath.sqrt(d3);
            if (d6 > 0.0d) {
                dSqrt = -dSqrt;
            }
            double d7 = 1.0d / (d3 - (d6 * dSqrt));
            this.beta[i7] = d7;
            this.diagR[i7] = dSqrt;
            double[] dArr = this.weightedJacobian[i3];
            dArr[i7] = dArr[i7] - dSqrt;
            for (int i8 = (length2 - 1) - i3; i8 > 0; i8--) {
                double d8 = 0.0d;
                for (int i9 = i3; i9 < length; i9++) {
                    double[] dArr2 = this.weightedJacobian[i9];
                    d8 += dArr2[i7] * dArr2[this.permutation[i3 + i8]];
                }
                double d9 = d8 * d7;
                for (int i10 = i3; i10 < length; i10++) {
                    double[] dArr3 = this.weightedJacobian[i10];
                    int i11 = this.permutation[i3 + i8];
                    dArr3[i11] = dArr3[i11] - (dArr3[i7] * d9);
                }
            }
            i3++;
            c = 0;
        }
        this.rank = this.solvedCols;
    }

    private void qTy(double[] dArr) {
        double[][] dArr2 = this.weightedJacobian;
        int length = dArr2.length;
        int length2 = dArr2[0].length;
        for (int i = 0; i < length2; i++) {
            int i2 = this.permutation[i];
            double d = 0.0d;
            for (int i3 = i; i3 < length; i3++) {
                d += this.weightedJacobian[i3][i2] * dArr[i3];
            }
            double d2 = d * this.beta[i2];
            for (int i4 = i; i4 < length; i4++) {
                dArr[i4] = dArr[i4] - (this.weightedJacobian[i4][i2] * d2);
            }
        }
    }
}
