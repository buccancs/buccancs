package org.apache.commons.math.analysis.polynomials;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/polynomials/PolynomialFunctionNewtonForm.class */
public class PolynomialFunctionNewtonForm implements UnivariateRealFunction {
    private final double[] c;
    private final double[] a;
    private double[] coefficients;
    private boolean coefficientsComputed;

    public PolynomialFunctionNewtonForm(double[] a, double[] c) throws IllegalArgumentException {
        verifyInputArray(a, c);
        this.a = new double[a.length];
        this.c = new double[c.length];
        System.arraycopy(a, 0, this.a, 0, a.length);
        System.arraycopy(c, 0, this.c, 0, c.length);
        this.coefficientsComputed = false;
    }

    public static double evaluate(double[] a, double[] c, double z) throws FunctionEvaluationException, IllegalArgumentException {
        verifyInputArray(a, c);
        int n = c.length;
        double value = a[n];
        for (int i = n - 1; i >= 0; i--) {
            value = a[i] + ((z - c[i]) * value);
        }
        return value;
    }

    protected static void verifyInputArray(double[] a, double[] c) throws IllegalArgumentException {
        if (a.length < 1 || c.length < 1) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY, new Object[0]);
        }
        if (a.length != c.length + 1) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.ARRAY_SIZES_SHOULD_HAVE_DIFFERENCE_1, Integer.valueOf(a.length), Integer.valueOf(c.length));
        }
    }

    @Override // org.apache.commons.math.analysis.UnivariateRealFunction
    public double value(double z) throws FunctionEvaluationException {
        return evaluate(this.a, this.c, z);
    }

    public int degree() {
        return this.c.length;
    }

    public double[] getNewtonCoefficients() {
        double[] out = new double[this.a.length];
        System.arraycopy(this.a, 0, out, 0, this.a.length);
        return out;
    }

    public double[] getCenters() {
        double[] out = new double[this.c.length];
        System.arraycopy(this.c, 0, out, 0, this.c.length);
        return out;
    }

    public double[] getCoefficients() {
        if (!this.coefficientsComputed) {
            computeCoefficients();
        }
        double[] out = new double[this.coefficients.length];
        System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
        return out;
    }

    protected void computeCoefficients() {
        int n = degree();
        this.coefficients = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            this.coefficients[i] = 0.0d;
        }
        this.coefficients[0] = this.a[n];
        for (int i2 = n - 1; i2 >= 0; i2--) {
            for (int j = n - i2; j > 0; j--) {
                this.coefficients[j] = this.coefficients[j - 1] - (this.c[i2] * this.coefficients[j]);
            }
            this.coefficients[0] = this.a[i2] - (this.c[i2] * this.coefficients[0]);
        }
        this.coefficientsComputed = true;
    }
}
