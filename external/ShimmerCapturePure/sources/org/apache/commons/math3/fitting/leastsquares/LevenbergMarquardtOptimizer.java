package org.apache.commons.math3.fitting.leastsquares;

import java.util.Arrays;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class LevenbergMarquardtOptimizer implements LeastSquaresOptimizer {
    private static final double TWO_EPS = Precision.EPSILON * 2.0d;
    private final double costRelativeTolerance;
    private final double initialStepBoundFactor;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private final double qrRankingThreshold;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3, double d4, double d5) {
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    public double getCostRelativeTolerance() {
        return this.costRelativeTolerance;
    }

    public double getInitialStepBoundFactor() {
        return this.initialStepBoundFactor;
    }

    public double getOrthoTolerance() {
        return this.orthoTolerance;
    }

    public double getParameterRelativeTolerance() {
        return this.parRelativeTolerance;
    }

    public double getRankingThreshold() {
        return this.qrRankingThreshold;
    }

    public LevenbergMarquardtOptimizer withInitialStepBoundFactor(double d) {
        return new LevenbergMarquardtOptimizer(d, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withCostRelativeTolerance(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, d, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withParameterRelativeTolerance(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, d, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withOrthoTolerance(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, d, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withRankingThreshold(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x02f2, code lost:
    
        return new org.apache.commons.math3.fitting.leastsquares.OptimumImpl(r0, r17.getCount(), r16.getCount());
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0353 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x027d  */
    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem r65) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.MaxCountExceededException {
        /*
            Method dump skipped, instructions count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem):org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer$Optimum");
    }

    private double determineLMParameter(double[] dArr, double d, double[] dArr2, InternalData internalData, int i, double[] dArr3, double[] dArr4, double[] dArr5, double[] dArr6, double d2) {
        double d3;
        double d4;
        double dMin;
        double[][] dArr7 = internalData.weightedJacobian;
        int[] iArr = internalData.permutation;
        int i2 = internalData.rank;
        double[] dArr8 = internalData.diagR;
        int i3 = 0;
        int length = dArr7[0].length;
        for (int i4 = 0; i4 < i2; i4++) {
            dArr6[iArr[i4]] = dArr[i4];
        }
        for (int i5 = i2; i5 < length; i5++) {
            dArr6[iArr[i5]] = 0.0d;
        }
        for (int i6 = i2 - 1; i6 >= 0; i6--) {
            int i7 = iArr[i6];
            double d5 = dArr6[i7] / dArr8[i7];
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = iArr[i8];
                dArr6[i9] = dArr6[i9] - (dArr7[i8][i7] * d5);
            }
            dArr6[i7] = d5;
        }
        double d6 = 0.0d;
        for (int i10 = 0; i10 < i; i10++) {
            int i11 = iArr[i10];
            double d7 = dArr2[i11] * dArr6[i11];
            dArr3[i11] = d7;
            d6 += d7 * d7;
        }
        double dSqrt = FastMath.sqrt(d6);
        double d8 = dSqrt - d;
        double d9 = d * 0.1d;
        if (d8 <= d9) {
            return 0.0d;
        }
        if (i2 == i) {
            for (int i12 = 0; i12 < i; i12++) {
                int i13 = iArr[i12];
                dArr3[i13] = dArr3[i13] * (dArr2[i13] / dSqrt);
            }
            double d10 = 0.0d;
            int i14 = 0;
            while (i14 < i) {
                int i15 = iArr[i14];
                double d11 = 0.0d;
                while (i3 < i14) {
                    d11 += dArr7[i3][i15] * dArr3[iArr[i3]];
                    i3++;
                }
                double d12 = (dArr3[i15] - d11) / dArr8[i15];
                dArr3[i15] = d12;
                d10 += d12 * d12;
                i14++;
                i3 = 0;
            }
            d3 = d8 / (d * d10);
        } else {
            d3 = 0.0d;
        }
        double d13 = 0.0d;
        for (int i16 = 0; i16 < i; i16++) {
            int i17 = iArr[i16];
            double d14 = 0.0d;
            for (int i18 = 0; i18 <= i16; i18++) {
                d14 += dArr7[i18][i17] * dArr[i18];
            }
            double d15 = d14 / dArr2[i17];
            d13 += d15 * d15;
        }
        double dSqrt2 = FastMath.sqrt(d13);
        double dMin2 = dSqrt2 / d;
        if (dMin2 == 0.0d) {
            dMin2 = Precision.SAFE_MIN / FastMath.min(d, 0.1d);
        }
        double d16 = dMin2;
        double d17 = d8;
        double dMin3 = FastMath.min(d16, FastMath.max(d2, d3));
        double d18 = 0.0d;
        if (dMin3 == 0.0d) {
            dMin3 = dSqrt2 / dSqrt;
        }
        double d19 = d16;
        int i19 = 10;
        double dMax = d3;
        while (i19 >= 0) {
            if (dMin3 == d18) {
                dMin3 = FastMath.max(Precision.SAFE_MIN, 0.001d * d19);
            }
            double dSqrt3 = FastMath.sqrt(dMin3);
            for (int i20 = 0; i20 < i; i20++) {
                int i21 = iArr[i20];
                dArr3[i21] = dArr2[i21] * dSqrt3;
            }
            double d20 = dMin3;
            double d21 = d19;
            determineLMDirection(dArr, dArr3, dArr4, internalData, i, dArr5, dArr6);
            double d22 = 0.0d;
            for (int i22 = 0; i22 < i; i22++) {
                int i23 = iArr[i22];
                double d23 = dArr2[i23] * dArr6[i23];
                dArr5[i23] = d23;
                d22 += d23 * d23;
            }
            double dSqrt4 = FastMath.sqrt(d22);
            double d24 = dSqrt4 - d;
            if (FastMath.abs(d24) <= d9 || (dMax == 0.0d && d24 <= d17 && d17 < 0.0d)) {
                return d20;
            }
            for (int i24 = 0; i24 < i; i24++) {
                int i25 = iArr[i24];
                dArr3[i25] = (dArr5[i25] * dArr2[i25]) / dSqrt4;
            }
            int i26 = 0;
            while (i26 < i) {
                int i27 = iArr[i26];
                double d25 = dArr3[i27] / dArr4[i26];
                dArr3[i27] = d25;
                i26++;
                for (int i28 = i26; i28 < i; i28++) {
                    int i29 = iArr[i28];
                    dArr3[i29] = dArr3[i29] - (dArr7[i28][i27] * d25);
                }
            }
            double d26 = 0.0d;
            for (int i30 = 0; i30 < i; i30++) {
                double d27 = dArr3[iArr[i30]];
                d26 += d27 * d27;
            }
            double d28 = d24 / (d * d26);
            if (d24 > 0.0d) {
                d4 = d20;
                dMax = FastMath.max(dMax, d4);
                dMin = d21;
            } else {
                d4 = d20;
                dMin = d21;
                if (d24 < 0.0d) {
                    dMin = FastMath.min(dMin, d4);
                }
            }
            double dMax2 = FastMath.max(dMax, d28 + d4);
            i19--;
            d19 = dMin;
            d17 = d24;
            d18 = 0.0d;
            dMin3 = dMax2;
        }
        return dMin3;
    }

    private void determineLMDirection(double[] dArr, double[] dArr2, double[] dArr3, InternalData internalData, int i, double[] dArr4, double[] dArr5) {
        int[] iArr;
        double dSqrt;
        double d;
        double[] dArr6 = dArr5;
        int[] iArr2 = internalData.permutation;
        double[][] dArr7 = internalData.weightedJacobian;
        double[] dArr8 = internalData.diagR;
        int i2 = 0;
        while (i2 < i) {
            int i3 = iArr2[i2];
            int i4 = i2 + 1;
            for (int i5 = i4; i5 < i; i5++) {
                dArr7[i5][i3] = dArr7[i2][iArr2[i5]];
            }
            dArr6[i2] = dArr8[i3];
            dArr4[i2] = dArr[i2];
            i2 = i4;
        }
        int i6 = 0;
        while (true) {
            double d2 = 0.0d;
            if (i6 >= i) {
                break;
            }
            double d3 = dArr2[iArr2[i6]];
            if (d3 != 0.0d) {
                Arrays.fill(dArr3, i6 + 1, dArr3.length, 0.0d);
            }
            dArr3[i6] = d3;
            int i7 = i6;
            double d4 = 0.0d;
            while (i7 < i) {
                int i8 = iArr2[i7];
                if (dArr3[i7] != d2) {
                    double d5 = dArr7[i7][i8];
                    if (FastMath.abs(d5) < FastMath.abs(dArr3[i7])) {
                        double d6 = d5 / dArr3[i7];
                        dSqrt = 1.0d / FastMath.sqrt((d6 * d6) + 1.0d);
                        d = d6 * dSqrt;
                    } else {
                        double d7 = dArr3[i7] / d5;
                        double dSqrt2 = 1.0d / FastMath.sqrt((d7 * d7) + 1.0d);
                        dSqrt = dSqrt2 * d7;
                        d = dSqrt2;
                    }
                    double d8 = dSqrt;
                    dArr7[i7][i8] = (d5 * d) + (dArr3[i7] * d8);
                    double d9 = dArr4[i7];
                    double d10 = (d * d9) + (d8 * d4);
                    iArr = iArr2;
                    double d11 = -d8;
                    d4 = (d4 * d) + (d9 * d11);
                    dArr4[i7] = d10;
                    for (int i9 = i7 + 1; i9 < i; i9++) {
                        double[] dArr9 = dArr7[i9];
                        double d12 = dArr9[i8];
                        double d13 = dArr3[i9];
                        dArr3[i9] = (d12 * d11) + (d13 * d);
                        dArr9[i8] = (d * d12) + (d8 * d13);
                    }
                } else {
                    iArr = iArr2;
                }
                i7++;
                iArr2 = iArr;
                d2 = 0.0d;
            }
            int[] iArr3 = iArr2;
            double[] dArr10 = dArr7[i6];
            int i10 = iArr3[i6];
            dArr3[i6] = dArr10[i10];
            dArr10[i10] = dArr5[i6];
            i6++;
            dArr6 = dArr5;
            iArr2 = iArr3;
        }
        double[] dArr11 = dArr6;
        int[] iArr4 = iArr2;
        int i11 = i;
        for (int i12 = 0; i12 < i; i12++) {
            if (dArr3[i12] == 0.0d && i11 == i) {
                i11 = i12;
            }
            if (i11 < i) {
                dArr4[i12] = 0.0d;
            }
        }
        if (i11 > 0) {
            for (int i13 = i11 - 1; i13 >= 0; i13--) {
                int i14 = iArr4[i13];
                double d14 = 0.0d;
                for (int i15 = i13 + 1; i15 < i11; i15++) {
                    d14 += dArr7[i15][i14] * dArr4[i15];
                }
                dArr4[i13] = (dArr4[i13] - d14) / dArr3[i13];
            }
        }
        for (int i16 = 0; i16 < dArr11.length; i16++) {
            dArr11[iArr4[i16]] = dArr4[i16];
        }
    }

    private InternalData qrDecomposition(RealMatrix realMatrix, int i) throws ConvergenceException {
        double[][] data = realMatrix.scalarMultiply(-1.0d).getData();
        int length = data.length;
        int length2 = data[0].length;
        int[] iArr = new int[length2];
        double[] dArr = new double[length2];
        double[] dArr2 = new double[length2];
        double[] dArr3 = new double[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[i2] = i2;
            double d = 0.0d;
            for (double[] dArr4 : data) {
                double d2 = dArr4[i2];
                d += d2 * d2;
            }
            dArr2[i2] = FastMath.sqrt(d);
        }
        for (int i3 = 0; i3 < length2; i3++) {
            int i4 = -1;
            double d3 = Double.NEGATIVE_INFINITY;
            for (int i5 = i3; i5 < length2; i5++) {
                double d4 = 0.0d;
                for (int i6 = i3; i6 < length; i6++) {
                    double d5 = data[i6][iArr[i5]];
                    d4 += d5 * d5;
                }
                if (Double.isInfinite(d4) || Double.isNaN(d4)) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, Integer.valueOf(length), Integer.valueOf(length2));
                }
                if (d4 > d3) {
                    i4 = i5;
                    d3 = d4;
                }
            }
            if (d3 <= this.qrRankingThreshold) {
                return new InternalData(data, iArr, i3, dArr, dArr2, dArr3);
            }
            int i7 = iArr[i4];
            iArr[i4] = iArr[i3];
            iArr[i3] = i7;
            double d6 = data[i3][i7];
            double dSqrt = FastMath.sqrt(d3);
            if (d6 > 0.0d) {
                dSqrt = -dSqrt;
            }
            double d7 = 1.0d / (d3 - (d6 * dSqrt));
            dArr3[i7] = d7;
            dArr[i7] = dSqrt;
            double[] dArr5 = data[i3];
            dArr5[i7] = dArr5[i7] - dSqrt;
            for (int i8 = (length2 - 1) - i3; i8 > 0; i8--) {
                double d8 = 0.0d;
                for (int i9 = i3; i9 < length; i9++) {
                    double[] dArr6 = data[i9];
                    d8 += dArr6[i7] * dArr6[iArr[i3 + i8]];
                }
                double d9 = d8 * d7;
                for (int i10 = i3; i10 < length; i10++) {
                    double[] dArr7 = data[i10];
                    int i11 = iArr[i3 + i8];
                    dArr7[i11] = dArr7[i11] - (dArr7[i7] * d9);
                }
            }
        }
        return new InternalData(data, iArr, i, dArr, dArr2, dArr3);
    }

    private void qTy(double[] dArr, InternalData internalData) {
        double[][] dArr2 = internalData.weightedJacobian;
        int[] iArr = internalData.permutation;
        double[] dArr3 = internalData.beta;
        int length = dArr2.length;
        int length2 = dArr2[0].length;
        for (int i = 0; i < length2; i++) {
            int i2 = iArr[i];
            double d = 0.0d;
            for (int i3 = i; i3 < length; i3++) {
                d += dArr2[i3][i2] * dArr[i3];
            }
            double d2 = d * dArr3[i2];
            for (int i4 = i; i4 < length; i4++) {
                dArr[i4] = dArr[i4] - (dArr2[i4][i2] * d2);
            }
        }
    }

    private static class InternalData {
        private final double[] beta;
        private final double[] diagR;
        private final double[] jacNorm;
        private final int[] permutation;
        private final int rank;
        private final double[][] weightedJacobian;

        InternalData(double[][] dArr, int[] iArr, int i, double[] dArr2, double[] dArr3, double[] dArr4) {
            this.weightedJacobian = dArr;
            this.permutation = iArr;
            this.rank = i;
            this.diagR = dArr2;
            this.jacNorm = dArr3;
            this.beta = dArr4;
        }
    }
}
