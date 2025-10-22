package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class BicubicInterpolatingFunction implements BivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private static final int NUM_COEFF = 16;
    private final BicubicFunction[][] splines;
    private final double[] xval;
    private final double[] yval;

    public BicubicInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6) throws NoDataException, NonMonotonicSequenceException, DimensionMismatchException {
        int length = dArr.length;
        int length2 = dArr2.length;
        if (length != 0 && length2 != 0 && dArr3.length != 0) {
            char c = 0;
            if (dArr3[0].length != 0) {
                if (length != dArr3.length) {
                    throw new DimensionMismatchException(length, dArr3.length);
                }
                if (length != dArr4.length) {
                    throw new DimensionMismatchException(length, dArr4.length);
                }
                if (length != dArr5.length) {
                    throw new DimensionMismatchException(length, dArr5.length);
                }
                if (length != dArr6.length) {
                    throw new DimensionMismatchException(length, dArr6.length);
                }
                MathArrays.checkOrder(dArr);
                MathArrays.checkOrder(dArr2);
                this.xval = (double[]) dArr.clone();
                this.yval = (double[]) dArr2.clone();
                int i = length - 1;
                int i2 = length2 - 1;
                this.splines = (BicubicFunction[][]) Array.newInstance((Class<?>) BicubicFunction.class, i, i2);
                int i3 = 0;
                while (i3 < i) {
                    if (dArr3[i3].length != length2) {
                        throw new DimensionMismatchException(dArr3[i3].length, length2);
                    }
                    if (dArr4[i3].length != length2) {
                        throw new DimensionMismatchException(dArr4[i3].length, length2);
                    }
                    if (dArr5[i3].length != length2) {
                        throw new DimensionMismatchException(dArr5[i3].length, length2);
                    }
                    if (dArr6[i3].length != length2) {
                        throw new DimensionMismatchException(dArr6[i3].length, length2);
                    }
                    int i4 = i3 + 1;
                    double[] dArr7 = this.xval;
                    double d = dArr7[i4] - dArr7[i3];
                    int i5 = 0;
                    while (i5 < i2) {
                        int i6 = i5 + 1;
                        double[] dArr8 = this.yval;
                        double d2 = dArr8[i6] - dArr8[i5];
                        double d3 = d * d2;
                        double[] dArr9 = new double[16];
                        double[] dArr10 = dArr3[i3];
                        dArr9[c] = dArr10[i5];
                        double[] dArr11 = dArr3[i4];
                        dArr9[1] = dArr11[i5];
                        dArr9[2] = dArr10[i6];
                        dArr9[3] = dArr11[i6];
                        double[] dArr12 = dArr4[i3];
                        dArr9[4] = dArr12[i5] * d;
                        double[] dArr13 = dArr4[i4];
                        dArr9[5] = dArr13[i5] * d;
                        dArr9[6] = dArr12[i6] * d;
                        dArr9[7] = dArr13[i6] * d;
                        double[] dArr14 = dArr5[i3];
                        dArr9[8] = dArr14[i5] * d2;
                        double[] dArr15 = dArr5[i4];
                        dArr9[9] = dArr15[i5] * d2;
                        dArr9[10] = dArr14[i6] * d2;
                        dArr9[11] = dArr15[i6] * d2;
                        double[] dArr16 = dArr6[i3];
                        dArr9[12] = dArr16[i5] * d3;
                        double[] dArr17 = dArr6[i4];
                        dArr9[13] = dArr17[i5] * d3;
                        dArr9[14] = dArr16[i6] * d3;
                        dArr9[15] = dArr17[i6] * d3;
                        this.splines[i3][i5] = new BicubicFunction(computeSplineCoefficients(dArr9));
                        i = i;
                        i5 = i6;
                        c = 0;
                    }
                    i3 = i4;
                }
                return;
            }
        }
        throw new NoDataException();
    }

    @Override // org.apache.commons.math3.analysis.BivariateFunction
    public double value(double d, double d2) throws OutOfRangeException {
        int iSearchIndex = searchIndex(d, this.xval);
        int iSearchIndex2 = searchIndex(d2, this.yval);
        double[] dArr = this.xval;
        double d3 = dArr[iSearchIndex];
        double d4 = (d - d3) / (dArr[iSearchIndex + 1] - d3);
        double[] dArr2 = this.yval;
        double d5 = dArr2[iSearchIndex2];
        return this.splines[iSearchIndex][iSearchIndex2].value(d4, (d2 - d5) / (dArr2[iSearchIndex2 + 1] - d5));
    }

    public boolean isValidPoint(double d, double d2) {
        double[] dArr = this.xval;
        if (d >= dArr[0] && d <= dArr[dArr.length - 1]) {
            double[] dArr2 = this.yval;
            if (d2 >= dArr2[0] && d2 <= dArr2[dArr2.length - 1]) {
                return true;
            }
        }
        return false;
    }

    private int searchIndex(double d, double[] dArr) {
        int iBinarySearch = Arrays.binarySearch(dArr, d);
        if (iBinarySearch == -1 || iBinarySearch == (-dArr.length) - 1) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(dArr[0]), Double.valueOf(dArr[dArr.length - 1]));
        }
        if (iBinarySearch < 0) {
            return (-iBinarySearch) - 2;
        }
        int length = dArr.length;
        return iBinarySearch == length + (-1) ? length - 2 : iBinarySearch;
    }

    private double[] computeSplineCoefficients(double[] dArr) {
        double[] dArr2 = new double[16];
        for (int i = 0; i < 16; i++) {
            double[] dArr3 = AINV[i];
            double d = 0.0d;
            for (int i2 = 0; i2 < 16; i2++) {
                d += dArr3[i2] * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }
}
