package org.apache.commons.math3.linear;

import com.google.common.base.Ascii;

import java.lang.reflect.Array;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class EigenDecomposition {
    private static final double EPSILON = 1.0E-12d;
    private final boolean isSymmetric;
    private RealMatrix cachedD;
    private RealMatrix cachedV;
    private RealMatrix cachedVt;
    private ArrayRealVector[] eigenvectors;
    private double[] imagEigenvalues;
    private double[] main;
    private byte maxIter;
    private double[] realEigenvalues;
    private double[] secondary;
    private TriDiagonalTransformer transformer;

    public EigenDecomposition(RealMatrix realMatrix) throws MathArithmeticException {
        this.maxIter = Ascii.RS;
        boolean zIsSymmetric = MatrixUtils.isSymmetric(realMatrix, realMatrix.getRowDimension() * 10 * realMatrix.getColumnDimension() * Precision.EPSILON);
        this.isSymmetric = zIsSymmetric;
        if (zIsSymmetric) {
            transformToTridiagonal(realMatrix);
            findEigenVectors(this.transformer.getQ().getData());
        } else {
            findEigenVectorsFromSchur(transformToSchur(realMatrix));
        }
    }

    @Deprecated
    public EigenDecomposition(RealMatrix realMatrix, double d) throws MathArithmeticException {
        this(realMatrix);
    }

    public EigenDecomposition(double[] dArr, double[] dArr2) {
        this.maxIter = Ascii.RS;
        this.isSymmetric = true;
        this.main = (double[]) dArr.clone();
        this.secondary = (double[]) dArr2.clone();
        this.transformer = null;
        int length = dArr.length;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length);
        for (int i = 0; i < length; i++) {
            dArr3[i][i] = 1.0d;
        }
        findEigenVectors(dArr3);
    }

    @Deprecated
    public EigenDecomposition(double[] dArr, double[] dArr2, double d) {
        this(dArr, dArr2);
    }

    public RealMatrix getV() throws OutOfRangeException, MatrixDimensionMismatchException {
        if (this.cachedV == null) {
            int length = this.eigenvectors.length;
            this.cachedV = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedV.setColumnVector(i, this.eigenvectors[i]);
            }
        }
        return this.cachedV;
    }

    public RealMatrix getD() throws OutOfRangeException {
        if (this.cachedD == null) {
            this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);
            int i = 0;
            while (true) {
                double[] dArr = this.imagEigenvalues;
                if (i >= dArr.length) {
                    break;
                }
                if (Precision.compareTo(dArr[i], 0.0d, 1.0E-12d) > 0) {
                    this.cachedD.setEntry(i, i + 1, this.imagEigenvalues[i]);
                } else if (Precision.compareTo(this.imagEigenvalues[i], 0.0d, 1.0E-12d) < 0) {
                    this.cachedD.setEntry(i, i - 1, this.imagEigenvalues[i]);
                }
                i++;
            }
        }
        return this.cachedD;
    }

    public RealMatrix getVT() throws OutOfRangeException, MatrixDimensionMismatchException {
        if (this.cachedVt == null) {
            int length = this.eigenvectors.length;
            this.cachedVt = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedVt.setRowVector(i, this.eigenvectors[i]);
            }
        }
        return this.cachedVt;
    }

    public boolean hasComplexEigenvalues() {
        int i = 0;
        while (true) {
            double[] dArr = this.imagEigenvalues;
            if (i >= dArr.length) {
                return false;
            }
            if (!Precision.equals(dArr[i], 0.0d, 1.0E-12d)) {
                return true;
            }
            i++;
        }
    }

    public double[] getRealEigenvalues() {
        return (double[]) this.realEigenvalues.clone();
    }

    public double getRealEigenvalue(int i) {
        return this.realEigenvalues[i];
    }

    public double[] getImagEigenvalues() {
        return (double[]) this.imagEigenvalues.clone();
    }

    public double getImagEigenvalue(int i) {
        return this.imagEigenvalues[i];
    }

    public RealVector getEigenvector(int i) {
        return this.eigenvectors[i].copy();
    }

    public double getDeterminant() {
        double d = 1.0d;
        for (double d2 : this.realEigenvalues) {
            d *= d2;
        }
        return d;
    }

    public RealMatrix getSquareRoot() throws OutOfRangeException, MatrixDimensionMismatchException {
        if (!this.isSymmetric) {
            throw new MathUnsupportedOperationException();
        }
        double[] dArr = new double[this.realEigenvalues.length];
        int i = 0;
        while (true) {
            double[] dArr2 = this.realEigenvalues;
            if (i < dArr2.length) {
                double d = dArr2[i];
                if (d <= 0.0d) {
                    throw new MathUnsupportedOperationException();
                }
                dArr[i] = FastMath.sqrt(d);
                i++;
            } else {
                RealMatrix realMatrixCreateRealDiagonalMatrix = MatrixUtils.createRealDiagonalMatrix(dArr);
                RealMatrix v = getV();
                return v.multiply(realMatrixCreateRealDiagonalMatrix).multiply(getVT());
            }
        }
    }

    public DecompositionSolver getSolver() {
        if (hasComplexEigenvalues()) {
            throw new MathUnsupportedOperationException();
        }
        return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
    }

    private void transformToTridiagonal(RealMatrix realMatrix) {
        TriDiagonalTransformer triDiagonalTransformer = new TriDiagonalTransformer(realMatrix);
        this.transformer = triDiagonalTransformer;
        this.main = triDiagonalTransformer.getMainDiagonalRef();
        this.secondary = this.transformer.getSecondaryDiagonalRef();
    }

    private void findEigenVectors(double[][] dArr) {
        int i;
        int i2;
        double d;
        double d2;
        double d3;
        double d4;
        double[][] dArr2 = (double[][]) dArr.clone();
        int length = this.main.length;
        this.realEigenvalues = new double[length];
        this.imagEigenvalues = new double[length];
        double[] dArr3 = new double[length];
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            this.realEigenvalues[i3] = this.main[i3];
            dArr3[i3] = this.secondary[i3];
            i3++;
        }
        this.realEigenvalues[i] = this.main[i];
        dArr3[i] = 0.0d;
        double dAbs = 0.0d;
        for (int i4 = 0; i4 < length; i4++) {
            if (FastMath.abs(this.realEigenvalues[i4]) > dAbs) {
                dAbs = FastMath.abs(this.realEigenvalues[i4]);
            }
            if (FastMath.abs(dArr3[i4]) > dAbs) {
                dAbs = FastMath.abs(dArr3[i4]);
            }
        }
        if (dAbs != 0.0d) {
            for (int i5 = 0; i5 < length; i5++) {
                if (FastMath.abs(this.realEigenvalues[i5]) <= Precision.EPSILON * dAbs) {
                    this.realEigenvalues[i5] = 0.0d;
                }
                if (FastMath.abs(dArr3[i5]) <= Precision.EPSILON * dAbs) {
                    dArr3[i5] = 0.0d;
                }
            }
        }
        for (int i6 = 0; i6 < length; i6++) {
            int i7 = 0;
            do {
                i2 = i6;
                while (i2 < i) {
                    int i8 = i2 + 1;
                    double dAbs2 = FastMath.abs(this.realEigenvalues[i2]) + FastMath.abs(this.realEigenvalues[i8]);
                    if (FastMath.abs(dArr3[i2]) + dAbs2 == dAbs2) {
                        break;
                    } else {
                        i2 = i8;
                    }
                }
                if (i2 != i6) {
                    if (i7 == this.maxIter) {
                        throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, Byte.valueOf(this.maxIter), new Object[0]);
                    }
                    i7++;
                    double[] dArr4 = this.realEigenvalues;
                    double d5 = (dArr4[i6 + 1] - dArr4[i6]) / (dArr3[i6] * 2.0d);
                    double dSqrt = FastMath.sqrt((d5 * d5) + 1.0d);
                    if (d5 < 0.0d) {
                        double[] dArr5 = this.realEigenvalues;
                        d = dArr5[i2] - dArr5[i6];
                        d2 = dArr3[i6];
                        d3 = d5 - dSqrt;
                    } else {
                        double[] dArr6 = this.realEigenvalues;
                        d = dArr6[i2] - dArr6[i6];
                        d2 = dArr3[i6];
                        d3 = d5 + dSqrt;
                    }
                    double d6 = d + (d2 / d3);
                    int i9 = i2 - 1;
                    double d7 = 0.0d;
                    double d8 = 1.0d;
                    double d9 = 1.0d;
                    while (true) {
                        if (i9 < i6) {
                            break;
                        }
                        double d10 = dArr3[i9];
                        double d11 = d8 * d10;
                        double d12 = d9 * d10;
                        if (FastMath.abs(d11) >= FastMath.abs(d6)) {
                            double d13 = d6 / d11;
                            double dSqrt2 = FastMath.sqrt((d13 * d13) + 1.0d);
                            dArr3[i9 + 1] = d11 * dSqrt2;
                            d8 = 1.0d / dSqrt2;
                            double d14 = d13 * d8;
                            dSqrt = dSqrt2;
                            d4 = d14;
                        } else {
                            double d15 = d11 / d6;
                            dSqrt = FastMath.sqrt((d15 * d15) + 1.0d);
                            dArr3[i9 + 1] = d6 * dSqrt;
                            d4 = 1.0d / dSqrt;
                            d8 = d15 * d4;
                        }
                        int i10 = i9 + 1;
                        if (dArr3[i10] == 0.0d) {
                            double[] dArr7 = this.realEigenvalues;
                            dArr7[i10] = dArr7[i10] - d7;
                            dArr3[i2] = 0.0d;
                            break;
                        }
                        double[] dArr8 = this.realEigenvalues;
                        double d16 = dArr8[i10] - d7;
                        double d17 = ((dArr8[i9] - d16) * d8) + (d4 * 2.0d * d12);
                        double d18 = d8 * d17;
                        dArr8[i10] = d16 + d18;
                        d6 = (d4 * d17) - d12;
                        for (int i11 = 0; i11 < length; i11++) {
                            double[] dArr9 = dArr2[i11];
                            double d19 = dArr9[i10];
                            dArr9[i10] = (dArr9[i9] * d8) + (d4 * d19);
                            dArr9[i9] = (dArr9[i9] * d4) - (d19 * d8);
                        }
                        i9--;
                        dSqrt = d17;
                        d9 = d4;
                        d7 = d18;
                    }
                    if (dSqrt != 0.0d || i9 < i6) {
                        double[] dArr10 = this.realEigenvalues;
                        dArr10[i6] = dArr10[i6] - d7;
                        dArr3[i6] = d6;
                        dArr3[i2] = 0.0d;
                    }
                }
            } while (i2 != i6);
        }
        int i12 = 0;
        while (i12 < length) {
            double d20 = this.realEigenvalues[i12];
            int i13 = i12 + 1;
            int i14 = i12;
            for (int i15 = i13; i15 < length; i15++) {
                double d21 = this.realEigenvalues[i15];
                if (d21 > d20) {
                    i14 = i15;
                    d20 = d21;
                }
            }
            if (i14 != i12) {
                double[] dArr11 = this.realEigenvalues;
                dArr11[i14] = dArr11[i12];
                dArr11[i12] = d20;
                for (int i16 = 0; i16 < length; i16++) {
                    double[] dArr12 = dArr2[i16];
                    double d22 = dArr12[i12];
                    dArr12[i12] = dArr12[i14];
                    dArr12[i14] = d22;
                }
            }
            i12 = i13;
        }
        double dAbs3 = 0.0d;
        for (int i17 = 0; i17 < length; i17++) {
            if (FastMath.abs(this.realEigenvalues[i17]) > dAbs3) {
                dAbs3 = FastMath.abs(this.realEigenvalues[i17]);
            }
        }
        if (dAbs3 != 0.0d) {
            for (int i18 = 0; i18 < length; i18++) {
                if (FastMath.abs(this.realEigenvalues[i18]) < Precision.EPSILON * dAbs3) {
                    this.realEigenvalues[i18] = 0.0d;
                }
            }
        }
        this.eigenvectors = new ArrayRealVector[length];
        double[] dArr13 = new double[length];
        for (int i19 = 0; i19 < length; i19++) {
            for (int i20 = 0; i20 < length; i20++) {
                dArr13[i20] = dArr2[i20][i19];
            }
            this.eigenvectors[i19] = new ArrayRealVector(dArr13);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.commons.math3.linear.SchurTransformer transformToSchur(org.apache.commons.math3.linear.RealMatrix r18) {
        /*
            r17 = this;
            r0 = r17
            org.apache.commons.math3.linear.SchurTransformer r1 = new org.apache.commons.math3.linear.SchurTransformer
            r2 = r18
            r1.<init>(r2)
            org.apache.commons.math3.linear.RealMatrix r2 = r1.getT()
            double[][] r2 = r2.getData()
            int r3 = r2.length
            double[] r3 = new double[r3]
            r0.realEigenvalues = r3
            int r3 = r2.length
            double[] r3 = new double[r3]
            r0.imagEigenvalues = r3
            r3 = 0
        L1c:
            double[] r4 = r0.realEigenvalues
            int r5 = r4.length
            if (r3 >= r5) goto L73
            int r4 = r4.length
            int r4 = r4 + (-1)
            if (r3 == r4) goto L68
            int r4 = r3 + 1
            r5 = r2[r4]
            r6 = r5[r3]
            r8 = 0
            r10 = 4427486594234968593(0x3d719799812dea11, double:1.0E-12)
            boolean r5 = org.apache.commons.math3.util.Precision.equals(r6, r8, r10)
            if (r5 == 0) goto L3a
            goto L68
        L3a:
            r5 = r2[r4]
            r6 = r5[r4]
            r8 = r2[r3]
            r9 = r8[r3]
            double r9 = r9 - r6
            r11 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r9 = r9 * r11
            double r11 = r9 * r9
            r13 = r5[r3]
            r15 = r8[r4]
            double r13 = r13 * r15
            double r11 = r11 + r13
            double r11 = org.apache.commons.math3.util.FastMath.abs(r11)
            double r11 = org.apache.commons.math3.util.FastMath.sqrt(r11)
            double[] r5 = r0.realEigenvalues
            double r6 = r6 + r9
            r5[r3] = r6
            double[] r8 = r0.imagEigenvalues
            r8[r3] = r11
            r5[r4] = r6
            double r5 = -r11
            r8[r4] = r5
            r3 = r4
            goto L70
        L68:
            double[] r4 = r0.realEigenvalues
            r5 = r2[r3]
            r6 = r5[r3]
            r4[r3] = r6
        L70:
            int r3 = r3 + 1
            goto L1c
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.EigenDecomposition.transformToSchur(org.apache.commons.math3.linear.RealMatrix):org.apache.commons.math3.linear.SchurTransformer");
    }

    private Complex cdiv(double d, double d2, double d3, double d4) {
        return new Complex(d, d2).divide(new Complex(d3, d4));
    }

    private void findEigenVectorsFromSchur(SchurTransformer schurTransformer) throws MathArithmeticException {
        double[][] dArr;
        int i;
        double d;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        double d2;
        double d3;
        int i8;
        double[][] dArr2;
        int i9;
        int i10;
        double[][] data = schurTransformer.getT().getData();
        double[][] data2 = schurTransformer.getP().getData();
        int length = data.length;
        double d4 = 0.0d;
        double dAbs = 0.0d;
        for (int i11 = 0; i11 < length; i11++) {
            for (int iMax = FastMath.max(i11 - 1, 0); iMax < length; iMax++) {
                dAbs += FastMath.abs(data[i11][iMax]);
            }
        }
        if (Precision.equals(dAbs, 0.0d, 1.0E-12d)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        int i12 = length - 1;
        int i13 = i12;
        double d5 = 0.0d;
        double d6 = 0.0d;
        double d7 = 0.0d;
        while (i13 >= 0) {
            double d8 = this.realEigenvalues[i13];
            double d9 = this.imagEigenvalues[i13];
            if (Precision.equals(d9, d4)) {
                data[i13][i13] = 1.0d;
                int i14 = i13 - 1;
                int i15 = i13;
                double d10 = d5;
                while (i14 >= 0) {
                    double d11 = data[i14][i14] - d8;
                    for (int i16 = i15; i16 <= i13; i16++) {
                        d4 += data[i14][i16] * data[i16][i13];
                    }
                    if (Precision.compareTo(this.imagEigenvalues[i14], 0.0d, 1.0E-12d) < 0) {
                        i10 = i14;
                        d6 = d11;
                        dArr2 = data2;
                        i9 = length;
                        d10 = d4;
                    } else {
                        dArr2 = data2;
                        i9 = length;
                        if (Precision.equals(this.imagEigenvalues[i14], 0.0d)) {
                            if (d11 != 0.0d) {
                                data[i14][i13] = (-d4) / d11;
                            } else {
                                data[i14][i13] = (-d4) / (Precision.EPSILON * dAbs);
                            }
                            i10 = i14;
                        } else {
                            double[] dArr3 = data[i14];
                            int i17 = i14 + 1;
                            double d12 = dArr3[i17];
                            double d13 = data[i17][i14];
                            double d14 = this.realEigenvalues[i14];
                            double d15 = (d14 - d8) * (d14 - d8);
                            double d16 = this.imagEigenvalues[i14];
                            double d17 = ((d12 * d10) - (d6 * d4)) / (d15 + (d16 * d16));
                            dArr3[i13] = d17;
                            if (FastMath.abs(d12) > FastMath.abs(d6)) {
                                i10 = i14;
                                data[i17][i13] = ((-d4) - (d11 * d17)) / d12;
                                d10 = d10;
                            } else {
                                i10 = i14;
                                data[i17][i13] = ((-d10) - (d13 * d17)) / d6;
                            }
                        }
                        double dAbs2 = FastMath.abs(data[i10][i13]);
                        if (Precision.EPSILON * dAbs2 * dAbs2 > 1.0d) {
                            for (int i18 = i10; i18 <= i13; i18++) {
                                double[] dArr4 = data[i18];
                                dArr4[i13] = dArr4[i13] / dAbs2;
                            }
                        }
                        i15 = i10;
                    }
                    i14 = i10 - 1;
                    data2 = dArr2;
                    d7 = d4;
                    length = i9;
                    d4 = 0.0d;
                }
                dArr = data2;
                i = length;
                d5 = d10;
            } else {
                dArr = data2;
                i = length;
                if (d9 < d4) {
                    int i19 = i13 - 1;
                    if (FastMath.abs(data[i13][i19]) > FastMath.abs(data[i19][i13])) {
                        double[] dArr5 = data[i19];
                        double[] dArr6 = data[i13];
                        dArr5[i19] = d9 / dArr6[i19];
                        dArr5[i13] = (-(dArr6[i13] - d8)) / dArr6[i19];
                        d = d9;
                        i2 = i12;
                        i3 = i13;
                    } else {
                        double[] dArr7 = data[i19];
                        d = d9;
                        i2 = i12;
                        i3 = i13;
                        Complex complexCdiv = cdiv(0.0d, -dArr7[i13], dArr7[i19] - d8, d);
                        data[i19][i19] = complexCdiv.getReal();
                        data[i19][i3] = complexCdiv.getImaginary();
                    }
                    double[] dArr8 = data[i3];
                    dArr8[i19] = 0.0d;
                    dArr8[i3] = 1.0d;
                    int i20 = i19;
                    double d18 = d7;
                    double d19 = d5;
                    int i21 = i3 - 2;
                    double d20 = d19;
                    while (i21 >= 0) {
                        int i22 = i20;
                        double d21 = 0.0d;
                        double d22 = 0.0d;
                        while (i20 <= i3) {
                            double d23 = data[i21][i20];
                            double[] dArr9 = data[i20];
                            d22 += dArr9[i19] * d23;
                            d21 += d23 * dArr9[i3];
                            i20++;
                        }
                        double d24 = data[i21][i21] - d8;
                        if (Precision.compareTo(this.imagEigenvalues[i21], 0.0d, 1.0E-12d) < 0) {
                            d18 = d22;
                            i6 = i2;
                            i8 = i3;
                            i20 = i22;
                            d6 = d24;
                        } else {
                            double d25 = d18;
                            double d26 = d20;
                            if (Precision.equals(this.imagEigenvalues[i21], 0.0d)) {
                                Complex complexCdiv2 = cdiv(-d22, -d21, d24, d);
                                data[i21][i19] = complexCdiv2.getReal();
                                data[i21][i3] = complexCdiv2.getImaginary();
                                i6 = i2;
                                i7 = i3;
                                d3 = d26;
                                d2 = d25;
                            } else {
                                int i23 = i21 + 1;
                                double d27 = data[i21][i23];
                                double d28 = data[i23][i21];
                                double d29 = this.realEigenvalues[i21];
                                double d30 = this.imagEigenvalues[i21];
                                double dAbs3 = (((d29 - d8) * (d29 - d8)) + (d30 * d30)) - (d * d);
                                double d31 = (d29 - d8) * 2.0d * d;
                                i6 = i2;
                                i7 = i3;
                                if (Precision.equals(dAbs3, 0.0d) && Precision.equals(d31, 0.0d)) {
                                    dAbs3 = Precision.EPSILON * dAbs * (FastMath.abs(d24) + FastMath.abs(d) + FastMath.abs(d27) + FastMath.abs(d28) + FastMath.abs(d6));
                                }
                                double d32 = d21;
                                double d33 = d22;
                                Complex complexCdiv3 = cdiv(((d27 * d25) - (d6 * d22)) + (d * d21), ((d27 * d26) - (d6 * d21)) - (d * d22), dAbs3, d31);
                                data[i21][i19] = complexCdiv3.getReal();
                                data[i21][i7] = complexCdiv3.getImaginary();
                                if (FastMath.abs(d27) > FastMath.abs(d6) + FastMath.abs(d)) {
                                    double[] dArr10 = data[i23];
                                    double[] dArr11 = data[i21];
                                    dArr10[i19] = (((-d33) - (dArr11[i19] * d24)) + (d * dArr11[i7])) / d27;
                                    dArr10[i7] = (((-d32) - (d24 * dArr11[i7])) - (d * dArr11[i19])) / d27;
                                    d2 = d25;
                                    d3 = d26;
                                } else {
                                    d2 = d25;
                                    double[] dArr12 = data[i21];
                                    d3 = d26;
                                    Complex complexCdiv4 = cdiv((-d2) - (dArr12[i19] * d28), (-d26) - (d28 * dArr12[i7]), d6, d);
                                    data[i23][i19] = complexCdiv4.getReal();
                                    data[i23][i7] = complexCdiv4.getImaginary();
                                }
                            }
                            double dMax = FastMath.max(FastMath.abs(data[i21][i19]), FastMath.abs(data[i21][i7]));
                            if (Precision.EPSILON * dMax * dMax > 1.0d) {
                                i8 = i7;
                                for (int i24 = i21; i24 <= i8; i24++) {
                                    double[] dArr13 = data[i24];
                                    dArr13[i19] = dArr13[i19] / dMax;
                                    dArr13[i8] = dArr13[i8] / dMax;
                                }
                            } else {
                                i8 = i7;
                            }
                            d18 = d2;
                            i20 = i21;
                            d21 = d3;
                        }
                        i21--;
                        i3 = i8;
                        i2 = i6;
                        d20 = d21;
                    }
                    double d34 = d20;
                    i4 = i2;
                    i5 = i3;
                    d5 = d34;
                    d7 = d18;
                }
                i13 = i5 - 1;
                data2 = dArr;
                length = i;
                i12 = i4;
                d4 = 0.0d;
            }
            i4 = i12;
            i5 = i13;
            i13 = i5 - 1;
            data2 = dArr;
            length = i;
            i12 = i4;
            d4 = 0.0d;
        }
        int i25 = i12;
        double[][] dArr14 = data2;
        int i26 = length;
        while (i12 >= 0) {
            int i27 = i25;
            for (int i28 = 0; i28 <= i27; i28++) {
                double d35 = 0.0d;
                for (int i29 = 0; i29 <= FastMath.min(i12, i27); i29++) {
                    d35 += dArr14[i28][i29] * data[i29][i12];
                }
                dArr14[i28][i12] = d35;
            }
            i12--;
            i25 = i27;
        }
        this.eigenvectors = new ArrayRealVector[i26];
        double[] dArr15 = new double[i26];
        for (int i30 = 0; i30 < i26; i30++) {
            for (int i31 = 0; i31 < i26; i31++) {
                dArr15[i31] = dArr14[i31][i30];
            }
            this.eigenvectors[i30] = new ArrayRealVector(dArr15);
        }
    }

    private static class Solver implements DecompositionSolver {
        private final ArrayRealVector[] eigenvectors;
        private double[] imagEigenvalues;
        private double[] realEigenvalues;

        private Solver(double[] dArr, double[] dArr2, ArrayRealVector[] arrayRealVectorArr) {
            this.realEigenvalues = dArr;
            this.imagEigenvalues = dArr2;
            this.eigenvectors = arrayRealVectorArr;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector realVector) {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int length = this.realEigenvalues.length;
            if (realVector.getDimension() != length) {
                throw new DimensionMismatchException(realVector.getDimension(), length);
            }
            double[] dArr = new double[length];
            for (int i = 0; i < length; i++) {
                ArrayRealVector arrayRealVector = this.eigenvectors[i];
                double[] dataRef = arrayRealVector.getDataRef();
                double dDotProduct = arrayRealVector.dotProduct(realVector) / this.realEigenvalues[i];
                for (int i2 = 0; i2 < length; i2++) {
                    dArr[i2] = dArr[i2] + (dataRef[i2] * dDotProduct);
                }
            }
            return new ArrayRealVector(dArr, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix realMatrix) {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int length = this.realEigenvalues.length;
            if (realMatrix.getRowDimension() != length) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length);
            }
            int columnDimension = realMatrix.getColumnDimension();
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, columnDimension);
            double[] dArr2 = new double[length];
            for (int i = 0; i < columnDimension; i++) {
                for (int i2 = 0; i2 < length; i2++) {
                    dArr2[i2] = realMatrix.getEntry(i2, i);
                    dArr[i2][i] = 0.0d;
                }
                for (int i3 = 0; i3 < length; i3++) {
                    ArrayRealVector arrayRealVector = this.eigenvectors[i3];
                    double[] dataRef = arrayRealVector.getDataRef();
                    double entry = 0.0d;
                    for (int i4 = 0; i4 < length; i4++) {
                        entry += arrayRealVector.getEntry(i4) * dArr2[i4];
                    }
                    double d = entry / this.realEigenvalues[i3];
                    for (int i5 = 0; i5 < length; i5++) {
                        double[] dArr3 = dArr[i5];
                        dArr3[i] = dArr3[i] + (dataRef[i5] * d);
                    }
                }
            }
            return new Array2DRowRealMatrix(dArr, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            double dMax = 0.0d;
            for (int i = 0; i < this.realEigenvalues.length; i++) {
                dMax = FastMath.max(dMax, eigenvalueNorm(i));
            }
            if (dMax == 0.0d) {
                return false;
            }
            for (int i2 = 0; i2 < this.realEigenvalues.length; i2++) {
                if (Precision.equals(eigenvalueNorm(i2) / dMax, 0.0d, 1.0E-12d)) {
                    return false;
                }
            }
            return true;
        }

        private double eigenvalueNorm(int i) {
            double d = this.realEigenvalues[i];
            double d2 = this.imagEigenvalues[i];
            return FastMath.sqrt((d * d) + (d2 * d2));
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int length = this.realEigenvalues.length;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length);
            for (int i = 0; i < length; i++) {
                double[] dArr2 = dArr[i];
                for (int i2 = 0; i2 < length; i2++) {
                    double d = 0.0d;
                    for (int i3 = 0; i3 < length; i3++) {
                        double[] dataRef = this.eigenvectors[i3].getDataRef();
                        d += (dataRef[i] * dataRef[i2]) / this.realEigenvalues[i3];
                    }
                    dArr2[i2] = d;
                }
            }
            return MatrixUtils.createRealMatrix(dArr);
        }
    }
}
