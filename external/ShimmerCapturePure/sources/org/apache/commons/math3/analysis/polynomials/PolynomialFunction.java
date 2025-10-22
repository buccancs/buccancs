package org.apache.commons.math3.analysis.polynomials;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class PolynomialFunction implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction, Serializable {
    private static final long serialVersionUID = -7726511984200295583L;
    private final double[] coefficients;

    public PolynomialFunction(double[] dArr) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        while (length > 1 && dArr[length - 1] == 0.0d) {
            length--;
        }
        double[] dArr2 = new double[length];
        this.coefficients = dArr2;
        System.arraycopy(dArr, 0, dArr2, 0, length);
    }

    protected static double evaluate(double[] dArr, double d) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        double d2 = dArr[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            d2 = (d2 * d) + dArr[i];
        }
        return d2;
    }

    protected static double[] differentiate(double[] dArr) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        if (length == 1) {
            return new double[]{0.0d};
        }
        int i = length - 1;
        double[] dArr2 = new double[i];
        while (i > 0) {
            dArr2[i - 1] = i * dArr[i];
            i--;
        }
        return dArr2;
    }

    private static String toString(double d) {
        String string = Double.toString(d);
        return string.endsWith(".0") ? string.substring(0, string.length() - 2) : string;
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double d) {
        return evaluate(this.coefficients, d);
    }

    public int degree() {
        return this.coefficients.length - 1;
    }

    public double[] getCoefficients() {
        return (double[]) this.coefficients.clone();
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(this.coefficients);
        int length = this.coefficients.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(derivativeStructure.getFreeParameters(), derivativeStructure.getOrder(), this.coefficients[length - 1]);
        for (int i = length - 2; i >= 0; i--) {
            derivativeStructure2 = derivativeStructure2.multiply(derivativeStructure).add(this.coefficients[i]);
        }
        return derivativeStructure2;
    }

    public PolynomialFunction add(PolynomialFunction polynomialFunction) {
        int iMin = FastMath.min(this.coefficients.length, polynomialFunction.coefficients.length);
        int iMax = FastMath.max(this.coefficients.length, polynomialFunction.coefficients.length);
        double[] dArr = new double[iMax];
        for (int i = 0; i < iMin; i++) {
            dArr[i] = this.coefficients[i] + polynomialFunction.coefficients[i];
        }
        double[] dArr2 = this.coefficients;
        int length = dArr2.length;
        double[] dArr3 = polynomialFunction.coefficients;
        if (length < dArr3.length) {
            dArr2 = dArr3;
        }
        System.arraycopy(dArr2, iMin, dArr, iMin, iMax - iMin);
        return new PolynomialFunction(dArr);
    }

    public PolynomialFunction subtract(PolynomialFunction polynomialFunction) {
        int iMin = FastMath.min(this.coefficients.length, polynomialFunction.coefficients.length);
        int iMax = FastMath.max(this.coefficients.length, polynomialFunction.coefficients.length);
        double[] dArr = new double[iMax];
        for (int i = 0; i < iMin; i++) {
            dArr[i] = this.coefficients[i] - polynomialFunction.coefficients[i];
        }
        double[] dArr2 = this.coefficients;
        if (dArr2.length < polynomialFunction.coefficients.length) {
            while (iMin < iMax) {
                dArr[iMin] = -polynomialFunction.coefficients[iMin];
                iMin++;
            }
        } else {
            System.arraycopy(dArr2, iMin, dArr, iMin, iMax - iMin);
        }
        return new PolynomialFunction(dArr);
    }

    public PolynomialFunction negate() {
        double[] dArr = new double[this.coefficients.length];
        int i = 0;
        while (true) {
            double[] dArr2 = this.coefficients;
            if (i < dArr2.length) {
                dArr[i] = -dArr2[i];
                i++;
            } else {
                return new PolynomialFunction(dArr);
            }
        }
    }

    public PolynomialFunction multiply(PolynomialFunction polynomialFunction) {
        int length = (this.coefficients.length + polynomialFunction.coefficients.length) - 1;
        double[] dArr = new double[length];
        int i = 0;
        while (i < length) {
            dArr[i] = 0.0d;
            int i2 = i + 1;
            for (int iMax = FastMath.max(0, i2 - polynomialFunction.coefficients.length); iMax < FastMath.min(this.coefficients.length, i2); iMax++) {
                dArr[i] = dArr[i] + (this.coefficients[iMax] * polynomialFunction.coefficients[i - iMax]);
            }
            i = i2;
        }
        return new PolynomialFunction(dArr);
    }

    public PolynomialFunction polynomialDerivative() {
        return new PolynomialFunction(differentiate(this.coefficients));
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    public UnivariateFunction derivative() {
        return polynomialDerivative();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        double[] dArr = this.coefficients;
        double d = dArr[0];
        if (d == 0.0d) {
            if (dArr.length == 1) {
                return "0";
            }
        } else {
            sb.append(toString(d));
        }
        int i = 1;
        while (true) {
            double[] dArr2 = this.coefficients;
            if (i >= dArr2.length) {
                return sb.toString();
            }
            if (dArr2[i] != 0.0d) {
                if (sb.length() > 0) {
                    if (this.coefficients[i] < 0.0d) {
                        sb.append(" - ");
                    } else {
                        sb.append(" + ");
                    }
                } else if (this.coefficients[i] < 0.0d) {
                    sb.append("-");
                }
                double dAbs = FastMath.abs(this.coefficients[i]);
                if (dAbs - 1.0d != 0.0d) {
                    sb.append(toString(dAbs));
                    sb.append(' ');
                }
                sb.append("x");
                if (i > 1) {
                    sb.append('^');
                    sb.append(Integer.toString(i));
                }
            }
            i++;
        }
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.coefficients);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PolynomialFunction) && Arrays.equals(this.coefficients, ((PolynomialFunction) obj).coefficients);
    }

    public static class Parametric implements ParametricUnivariateFunction {
        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double[] gradient(double d, double... dArr) {
            double[] dArr2 = new double[dArr.length];
            double d2 = 1.0d;
            for (int i = 0; i < dArr.length; i++) {
                dArr2[i] = d2;
                d2 *= d;
            }
            return dArr2;
        }

        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double value(double d, double... dArr) throws NoDataException {
            return PolynomialFunction.evaluate(dArr, d);
        }
    }
}
