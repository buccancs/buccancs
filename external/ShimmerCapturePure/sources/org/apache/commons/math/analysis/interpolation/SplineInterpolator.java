package org.apache.commons.math.analysis.interpolation;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math.exception.DimensionMismatchException;
import org.apache.commons.math.exception.NumberIsTooSmallException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/SplineInterpolator.class */
public class SplineInterpolator implements UnivariateRealInterpolator {
    @Override // org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator
    public PolynomialSplineFunction interpolate(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new DimensionMismatchException(x.length, y.length);
        }
        if (x.length < 3) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(x.length), 3, true);
        }
        int n = x.length - 1;
        MathUtils.checkOrder(x);
        double[] h = new double[n];
        for (int i = 0; i < n; i++) {
            h[i] = x[i + 1] - x[i];
        }
        double[] mu = new double[n];
        double[] z = new double[n + 1];
        mu[0] = 0.0d;
        z[0] = 0.0d;
        for (int i2 = 1; i2 < n; i2++) {
            double g = (2.0d * (x[i2 + 1] - x[i2 - 1])) - (h[i2 - 1] * mu[i2 - 1]);
            mu[i2] = h[i2] / g;
            z[i2] = (((3.0d * (((y[i2 + 1] * h[i2 - 1]) - (y[i2] * (x[i2 + 1] - x[i2 - 1]))) + (y[i2 - 1] * h[i2]))) / (h[i2 - 1] * h[i2])) - (h[i2 - 1] * z[i2 - 1])) / g;
        }
        double[] b = new double[n];
        double[] c = new double[n + 1];
        double[] d = new double[n];
        z[n] = 0.0d;
        c[n] = 0.0d;
        for (int j = n - 1; j >= 0; j--) {
            c[j] = z[j] - (mu[j] * c[j + 1]);
            b[j] = ((y[j + 1] - y[j]) / h[j]) - ((h[j] * (c[j + 1] + (2.0d * c[j]))) / 3.0d);
            d[j] = (c[j + 1] - c[j]) / (3.0d * h[j]);
        }
        PolynomialFunction[] polynomials = new PolynomialFunction[n];
        double[] coefficients = new double[4];
        for (int i3 = 0; i3 < n; i3++) {
            coefficients[0] = y[i3];
            coefficients[1] = b[i3];
            coefficients[2] = c[i3];
            coefficients[3] = d[i3];
            polynomials[i3] = new PolynomialFunction(coefficients);
        }
        return new PolynomialSplineFunction(x, polynomials);
    }
}
