package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class SplineInterpolator implements UnivariateInterpolator {
    @Override // org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator
    public PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws NumberIsTooSmallException, NonMonotonicSequenceException, DimensionMismatchException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        if (dArr.length < 3) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(dArr.length), 3, true);
        }
        int length = dArr.length;
        int i = length - 1;
        MathArrays.checkOrder(dArr);
        double[] dArr3 = new double[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            dArr3[i2] = dArr[i3] - dArr[i2];
            i2 = i3;
        }
        double[] dArr4 = new double[i];
        double[] dArr5 = new double[length];
        dArr4[0] = 0.0d;
        dArr5[0] = 0.0d;
        int i4 = 1;
        while (i4 < i) {
            int i5 = i4 + 1;
            int i6 = i4 - 1;
            double d = ((dArr[i5] - dArr[i6]) * 2.0d) - (dArr3[i6] * dArr4[i6]);
            dArr4[i4] = dArr3[i4] / d;
            double d2 = dArr2[i5];
            double d3 = dArr3[i6];
            double d4 = (d2 * d3) - (dArr2[i4] * (dArr[i5] - dArr[i6]));
            double d5 = dArr2[i6];
            double d6 = dArr3[i4];
            dArr5[i4] = ((((d4 + (d5 * d6)) * 3.0d) / (d6 * d3)) - (d3 * dArr5[i6])) / d;
            i4 = i5;
        }
        double[] dArr6 = new double[i];
        double[] dArr7 = new double[length];
        double[] dArr8 = new double[i];
        dArr5[i] = 0.0d;
        dArr7[i] = 0.0d;
        for (int i7 = length - 2; i7 >= 0; i7--) {
            int i8 = i7 + 1;
            double d7 = dArr5[i7] - (dArr4[i7] * dArr7[i8]);
            dArr7[i7] = d7;
            double d8 = dArr2[i8] - dArr2[i7];
            double d9 = dArr3[i7];
            dArr6[i7] = (d8 / d9) - ((d9 * (dArr7[i8] + (d7 * 2.0d))) / 3.0d);
            dArr8[i7] = (dArr7[i8] - dArr7[i7]) / (dArr3[i7] * 3.0d);
        }
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[i];
        double[] dArr9 = new double[4];
        for (int i9 = 0; i9 < i; i9++) {
            dArr9[0] = dArr2[i9];
            dArr9[1] = dArr6[i9];
            dArr9[2] = dArr7[i9];
            dArr9[3] = dArr8[i9];
            polynomialFunctionArr[i9] = new PolynomialFunction(dArr9);
        }
        return new PolynomialSplineFunction(dArr, polynomialFunctionArr);
    }
}
