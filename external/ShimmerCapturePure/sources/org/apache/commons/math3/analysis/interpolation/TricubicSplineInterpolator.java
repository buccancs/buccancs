package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes5.dex */
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
    private int nextIndex(int i, int i2) {
        int i3 = i + 1;
        return i3 < i2 ? i3 : i;
    }

    private int previousIndex(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    @Override // org.apache.commons.math3.analysis.interpolation.TrivariateGridInterpolator
    public TricubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NumberIsTooSmallException, NoDataException, NonMonotonicSequenceException, DimensionMismatchException {
        if (dArr.length == 0 || dArr2.length == 0 || dArr3.length == 0 || dArr4.length == 0) {
            throw new NoDataException();
        }
        if (dArr.length != dArr4.length) {
            throw new DimensionMismatchException(dArr.length, dArr4.length);
        }
        MathArrays.checkOrder(dArr);
        MathArrays.checkOrder(dArr2);
        MathArrays.checkOrder(dArr3);
        int length = dArr.length;
        int length2 = dArr2.length;
        int length3 = dArr3.length;
        double[][][] dArr5 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length3, length, length2);
        double[][][] dArr6 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length2, length3, length);
        for (int i = 0; i < length; i++) {
            if (dArr4[i].length != length2) {
                throw new DimensionMismatchException(dArr4[i].length, length2);
            }
            for (int i2 = 0; i2 < length2; i2++) {
                if (dArr4[i][i2].length != length3) {
                    throw new DimensionMismatchException(dArr4[i][i2].length, length3);
                }
                for (int i3 = 0; i3 < length3; i3++) {
                    double d = dArr4[i][i2][i3];
                    dArr5[i3][i][i2] = d;
                    dArr6[i2][i3][i] = d;
                }
            }
        }
        BicubicSplineInterpolator bicubicSplineInterpolator = new BicubicSplineInterpolator(true);
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr = new BicubicSplineInterpolatingFunction[length];
        for (int i4 = 0; i4 < length; i4++) {
            bicubicSplineInterpolatingFunctionArr[i4] = bicubicSplineInterpolator.interpolate(dArr2, dArr3, dArr4[i4]);
        }
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr2 = new BicubicSplineInterpolatingFunction[length2];
        for (int i5 = 0; i5 < length2; i5++) {
            bicubicSplineInterpolatingFunctionArr2[i5] = bicubicSplineInterpolator.interpolate(dArr3, dArr, dArr6[i5]);
        }
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr3 = new BicubicSplineInterpolatingFunction[length3];
        for (int i6 = 0; i6 < length3; i6++) {
            bicubicSplineInterpolatingFunctionArr3[i6] = bicubicSplineInterpolator.interpolate(dArr, dArr2, dArr5[i6]);
        }
        double[][][] dArr7 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length, length2, length3);
        double[][][] dArr8 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length, length2, length3);
        double[][][] dArr9 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length, length2, length3);
        int i7 = 0;
        while (i7 < length3) {
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction = bicubicSplineInterpolatingFunctionArr3[i7];
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr4 = bicubicSplineInterpolatingFunctionArr3;
            for (int i8 = 0; i8 < length; i8++) {
                double d2 = dArr[i8];
                int i9 = 0;
                while (i9 < length2) {
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr5 = bicubicSplineInterpolatingFunctionArr;
                    double d3 = dArr2[i9];
                    dArr7[i8][i9][i7] = bicubicSplineInterpolatingFunction.partialDerivativeX(d2, d3);
                    dArr8[i8][i9][i7] = bicubicSplineInterpolatingFunction.partialDerivativeY(d2, d3);
                    dArr9[i8][i9][i7] = bicubicSplineInterpolatingFunction.partialDerivativeXY(d2, d3);
                    i9++;
                    bicubicSplineInterpolatingFunctionArr = bicubicSplineInterpolatingFunctionArr5;
                    bicubicSplineInterpolatingFunctionArr2 = bicubicSplineInterpolatingFunctionArr2;
                }
            }
            i7++;
            bicubicSplineInterpolatingFunctionArr3 = bicubicSplineInterpolatingFunctionArr4;
        }
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr6 = bicubicSplineInterpolatingFunctionArr;
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr7 = bicubicSplineInterpolatingFunctionArr2;
        double[][][] dArr10 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length, length2, length3);
        double[][][] dArr11 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length, length2, length3);
        for (int i10 = 0; i10 < length; i10++) {
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction2 = bicubicSplineInterpolatingFunctionArr6[i10];
            int i11 = 0;
            while (i11 < length2) {
                double[][][] dArr12 = dArr9;
                double d4 = dArr2[i11];
                int i12 = 0;
                while (i12 < length3) {
                    double[][][] dArr13 = dArr12;
                    double d5 = dArr3[i12];
                    dArr10[i10][i11][i12] = bicubicSplineInterpolatingFunction2.partialDerivativeY(d4, d5);
                    dArr11[i10][i11][i12] = bicubicSplineInterpolatingFunction2.partialDerivativeXY(d4, d5);
                    i12++;
                    dArr8 = dArr8;
                    dArr12 = dArr13;
                }
                i11++;
                dArr9 = dArr12;
            }
        }
        double[][][] dArr14 = dArr8;
        double[][][] dArr15 = dArr9;
        double[][][] dArr16 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length, length2, length3);
        for (int i13 = 0; i13 < length2; i13++) {
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction3 = bicubicSplineInterpolatingFunctionArr7[i13];
            for (int i14 = 0; i14 < length3; i14++) {
                double d6 = dArr3[i14];
                int i15 = 0;
                while (i15 < length) {
                    double[][][] dArr17 = dArr10;
                    dArr16[i15][i13][i14] = bicubicSplineInterpolatingFunction3.partialDerivativeXY(d6, dArr[i15]);
                    i15++;
                    length = length;
                    dArr10 = dArr17;
                }
            }
        }
        double[][][] dArr18 = dArr10;
        int i16 = length;
        double[][][] dArr19 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, i16, length2, length3);
        int i17 = 0;
        while (i17 < i16) {
            int iNextIndex = nextIndex(i17, i16);
            int iPreviousIndex = previousIndex(i17);
            int i18 = 0;
            while (i18 < length2) {
                int iNextIndex2 = nextIndex(i18, length2);
                int iPreviousIndex2 = previousIndex(i18);
                int i19 = i16;
                int i20 = 0;
                while (i20 < length3) {
                    int iNextIndex3 = nextIndex(i20, length3);
                    int iPreviousIndex3 = previousIndex(i20);
                    double[] dArr20 = dArr19[i17][i18];
                    double[][] dArr21 = dArr4[iNextIndex];
                    double[] dArr22 = dArr21[iNextIndex2];
                    double d7 = dArr22[iNextIndex3];
                    double[] dArr23 = dArr21[iPreviousIndex2];
                    double d8 = d7 - dArr23[iNextIndex3];
                    double[][] dArr24 = dArr4[iPreviousIndex];
                    double[] dArr25 = dArr24[iNextIndex2];
                    double d9 = d8 - dArr25[iNextIndex3];
                    double[] dArr26 = dArr24[iPreviousIndex2];
                    dArr20[i20] = (((((d9 + dArr26[iNextIndex3]) - dArr22[iPreviousIndex3]) + dArr23[iPreviousIndex3]) + dArr25[iPreviousIndex3]) - dArr26[iPreviousIndex3]) / (((dArr[iNextIndex] - dArr[iPreviousIndex]) * (dArr2[iNextIndex2] - dArr2[iPreviousIndex2])) * (dArr3[iNextIndex3] - dArr3[iPreviousIndex3]));
                    i20++;
                    length2 = length2;
                }
                i18++;
                i16 = i19;
                length2 = length2;
            }
            i17++;
            length2 = length2;
        }
        return new TricubicSplineInterpolatingFunction(dArr, dArr2, dArr3, dArr4, dArr7, dArr14, dArr18, dArr15, dArr16, dArr11, dArr19);
    }
}
