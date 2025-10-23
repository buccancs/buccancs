package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class PolynomialFunctionLagrangeForm implements UnivariateFunction {
    private final double[] x;
    private final double[] y;
    private double[] coefficients;
    private boolean coefficientsComputed;

    public PolynomialFunctionLagrangeForm(double[] dArr, double[] dArr2) throws NumberIsTooSmallException, NullArgumentException, DimensionMismatchException, NonMonotonicSequenceException {
        double[] dArr3 = new double[dArr.length];
        this.x = dArr3;
        double[] dArr4 = new double[dArr2.length];
        this.y = dArr4;
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr4, 0, dArr2.length);
        this.coefficientsComputed = false;
        if (verifyInterpolationArray(dArr, dArr2, false)) {
            return;
        }
        MathArrays.sortInPlace(dArr3, dArr4);
        verifyInterpolationArray(dArr3, dArr4, true);
    }

    public static double evaluate(double[] dArr, double[] dArr2, double d) throws NumberIsTooSmallException, NullArgumentException, DimensionMismatchException, NonMonotonicSequenceException {
        if (verifyInterpolationArray(dArr, dArr2, false)) {
            return evaluateInternal(dArr, dArr2, d);
        }
        double[] dArr3 = new double[dArr.length];
        double[] dArr4 = new double[dArr2.length];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr4, 0, dArr2.length);
        MathArrays.sortInPlace(dArr3, dArr4);
        verifyInterpolationArray(dArr3, dArr4, true);
        return evaluateInternal(dArr3, dArr4, d);
    }

    private static double evaluateInternal(double[] dArr, double[] dArr2, double d) {
        double d2;
        int length = dArr.length;
        double[] dArr3 = new double[length];
        double[] dArr4 = new double[length];
        double d3 = Double.POSITIVE_INFINITY;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            dArr3[i2] = dArr2[i2];
            dArr4[i2] = dArr2[i2];
            double dAbs = FastMath.abs(d - dArr[i2]);
            if (dAbs < d3) {
                i = i2;
                d3 = dAbs;
            }
        }
        double d4 = dArr2[i];
        for (int i3 = 1; i3 < length; i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= length - i3) {
                    break;
                }
                double d5 = dArr[i4];
                double d6 = d5 - d;
                double d7 = dArr[i3 + i4];
                double d8 = d7 - d;
                double d9 = d5 - d7;
                int i5 = i4 + 1;
                double d10 = (dArr3[i5] - dArr4[i4]) / d9;
                dArr3[i4] = d6 * d10;
                dArr4[i4] = d8 * d10;
                i4 = i5;
            }
            if (i < (r10 + 1) * 0.5d) {
                d2 = dArr3[i];
            } else {
                i--;
                d2 = dArr4[i];
            }
            d4 += d2;
        }
        return d4;
    }

    public static boolean verifyInterpolationArray(double[] dArr, double[] dArr2, boolean z) throws NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        if (dArr.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 2, Integer.valueOf(dArr.length), true);
        }
        return MathArrays.checkOrder(dArr, MathArrays.OrderDirection.INCREASING, true, z);
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double d) {
        return evaluateInternal(this.x, this.y, d);
    }

    public int degree() {
        return this.x.length - 1;
    }

    public double[] getInterpolatingPoints() {
        double[] dArr = this.x;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public double[] getInterpolatingValues() {
        double[] dArr = this.y;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public double[] getCoefficients() {
        if (!this.coefficientsComputed) {
            computeCoefficients();
        }
        double[] dArr = this.coefficients;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    protected void computeCoefficients() {
        int iDegree = degree();
        int i = iDegree + 1;
        this.coefficients = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.coefficients[i2] = 0.0d;
        }
        double[] dArr = new double[iDegree + 2];
        dArr[0] = 1.0d;
        int i3 = 0;
        while (i3 < i) {
            for (int i4 = i3; i4 > 0; i4--) {
                dArr[i4] = dArr[i4 - 1] - (dArr[i4] * this.x[i3]);
            }
            dArr[0] = dArr[0] * (-this.x[i3]);
            i3++;
            dArr[i3] = 1.0d;
        }
        double[] dArr2 = new double[i];
        for (int i5 = 0; i5 < i; i5++) {
            double d = 1.0d;
            for (int i6 = 0; i6 < i; i6++) {
                if (i5 != i6) {
                    double[] dArr3 = this.x;
                    d *= dArr3[i5] - dArr3[i6];
                }
            }
            double d2 = this.y[i5] / d;
            double d3 = dArr[i];
            dArr2[iDegree] = d3;
            double[] dArr4 = this.coefficients;
            dArr4[iDegree] = dArr4[iDegree] + (d3 * d2);
            for (int i7 = iDegree - 1; i7 >= 0; i7--) {
                int i8 = i7 + 1;
                double d4 = dArr[i8] + (dArr2[i8] * this.x[i5]);
                dArr2[i7] = d4;
                double[] dArr5 = this.coefficients;
                dArr5[i7] = dArr5[i7] + (d4 * d2);
            }
        }
        this.coefficientsComputed = true;
    }
}
