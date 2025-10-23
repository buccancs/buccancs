package org.apache.commons.math.complex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/complex/Complex.class */
public class Complex implements FieldElement<Complex>, Serializable {
    public static final Complex I = new Complex(0.0d, 1.0d);
    public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
    public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Complex ONE = new Complex(1.0d, 0.0d);
    public static final Complex ZERO = new Complex(0.0d, 0.0d);
    private static final long serialVersionUID = -6195664516687396620L;
    private final double imaginary;
    private final double real;
    private final transient boolean isNaN;
    private final transient boolean isInfinite;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        this.isNaN = Double.isNaN(real) || Double.isNaN(imaginary);
        this.isInfinite = !this.isNaN && (Double.isInfinite(real) || Double.isInfinite(imaginary));
    }

    public double abs() {
        if (isNaN()) {
            return Double.NaN;
        }
        if (isInfinite()) {
            return Double.POSITIVE_INFINITY;
        }
        if (FastMath.abs(this.real) < FastMath.abs(this.imaginary)) {
            if (this.imaginary == 0.0d) {
                return FastMath.abs(this.real);
            }
            double q = this.real / this.imaginary;
            return FastMath.abs(this.imaginary) * FastMath.sqrt(1.0d + (q * q));
        }
        if (this.real == 0.0d) {
            return FastMath.abs(this.imaginary);
        }
        double q2 = this.imaginary / this.real;
        return FastMath.abs(this.real) * FastMath.sqrt(1.0d + (q2 * q2));
    }

    @Override // org.apache.commons.math.FieldElement
    public Complex add(Complex rhs) {
        return createComplex(this.real + rhs.getReal(), this.imaginary + rhs.getImaginary());
    }

    public Complex conjugate() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(this.real, -this.imaginary);
    }

    @Override // org.apache.commons.math.FieldElement
    public Complex divide(Complex rhs) {
        if (isNaN() || rhs.isNaN()) {
            return NaN;
        }
        double c = rhs.getReal();
        double d = rhs.getImaginary();
        if (c == 0.0d && d == 0.0d) {
            return NaN;
        }
        if (rhs.isInfinite() && !isInfinite()) {
            return ZERO;
        }
        if (FastMath.abs(c) < FastMath.abs(d)) {
            double q = c / d;
            double denominator = (c * q) + d;
            return createComplex(((this.real * q) + this.imaginary) / denominator, ((this.imaginary * q) - this.real) / denominator);
        }
        double q2 = d / c;
        double denominator2 = (d * q2) + c;
        return createComplex(((this.imaginary * q2) + this.real) / denominator2, (this.imaginary - (this.real * q2)) / denominator2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Complex) {
            Complex rhs = (Complex) other;
            if (rhs.isNaN()) {
                return isNaN();
            }
            return this.real == rhs.real && this.imaginary == rhs.imaginary;
        }
        return false;
    }

    public int hashCode() {
        if (isNaN()) {
            return 7;
        }
        return 37 * ((17 * MathUtils.hash(this.imaginary)) + MathUtils.hash(this.real));
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public boolean isNaN() {
        return this.isNaN;
    }

    public boolean isInfinite() {
        return this.isInfinite;
    }

    @Override // org.apache.commons.math.FieldElement
    public Complex multiply(Complex rhs) {
        if (isNaN() || rhs.isNaN()) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(rhs.real) || Double.isInfinite(rhs.imaginary)) {
            return INF;
        }
        return createComplex((this.real * rhs.real) - (this.imaginary * rhs.imaginary), (this.real * rhs.imaginary) + (this.imaginary * rhs.real));
    }

    public Complex multiply(double rhs) {
        if (isNaN() || Double.isNaN(rhs)) {
            return NaN;
        }
        if (Double.isInfinite(this.real) || Double.isInfinite(this.imaginary) || Double.isInfinite(rhs)) {
            return INF;
        }
        return createComplex(this.real * rhs, this.imaginary * rhs);
    }

    public Complex negate() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(-this.real, -this.imaginary);
    }

    @Override // org.apache.commons.math.FieldElement
    public Complex subtract(Complex rhs) {
        if (isNaN() || rhs.isNaN()) {
            return NaN;
        }
        return createComplex(this.real - rhs.getReal(), this.imaginary - rhs.getImaginary());
    }

    public Complex acos() {
        if (isNaN()) {
            return NaN;
        }
        return add(sqrt1z().multiply(I)).log().multiply(I.negate());
    }

    public Complex asin() {
        if (isNaN()) {
            return NaN;
        }
        return sqrt1z().add(multiply(I)).log().multiply(I.negate());
    }

    public Complex atan() {
        if (isNaN()) {
            return NaN;
        }
        return add(I).divide(I.subtract(this)).log().multiply(I.divide(createComplex(2.0d, 0.0d)));
    }

    public Complex cos() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(FastMath.cos(this.real) * MathUtils.cosh(this.imaginary), (-FastMath.sin(this.real)) * MathUtils.sinh(this.imaginary));
    }

    public Complex cosh() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(MathUtils.cosh(this.real) * FastMath.cos(this.imaginary), MathUtils.sinh(this.real) * FastMath.sin(this.imaginary));
    }

    public Complex exp() {
        if (isNaN()) {
            return NaN;
        }
        double expReal = FastMath.exp(this.real);
        return createComplex(expReal * FastMath.cos(this.imaginary), expReal * FastMath.sin(this.imaginary));
    }

    public Complex log() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(FastMath.log(abs()), FastMath.atan2(this.imaginary, this.real));
    }

    public Complex pow(Complex x) {
        if (x == null) {
            throw new NullPointerException();
        }
        return log().multiply(x).exp();
    }

    public Complex sin() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(FastMath.sin(this.real) * MathUtils.cosh(this.imaginary), FastMath.cos(this.real) * MathUtils.sinh(this.imaginary));
    }

    public Complex sinh() {
        if (isNaN()) {
            return NaN;
        }
        return createComplex(MathUtils.sinh(this.real) * FastMath.cos(this.imaginary), MathUtils.cosh(this.real) * FastMath.sin(this.imaginary));
    }

    public Complex sqrt() {
        if (isNaN()) {
            return NaN;
        }
        if (this.real == 0.0d && this.imaginary == 0.0d) {
            return createComplex(0.0d, 0.0d);
        }
        double t = FastMath.sqrt((FastMath.abs(this.real) + abs()) / 2.0d);
        if (this.real >= 0.0d) {
            return createComplex(t, this.imaginary / (2.0d * t));
        }
        return createComplex(FastMath.abs(this.imaginary) / (2.0d * t), MathUtils.indicator(this.imaginary) * t);
    }

    public Complex sqrt1z() {
        return createComplex(1.0d, 0.0d).subtract(multiply(this)).sqrt();
    }

    public Complex tan() {
        if (isNaN()) {
            return NaN;
        }
        double real2 = 2.0d * this.real;
        double imaginary2 = 2.0d * this.imaginary;
        double d = FastMath.cos(real2) + MathUtils.cosh(imaginary2);
        return createComplex(FastMath.sin(real2) / d, MathUtils.sinh(imaginary2) / d);
    }

    public Complex tanh() {
        if (isNaN()) {
            return NaN;
        }
        double real2 = 2.0d * this.real;
        double imaginary2 = 2.0d * this.imaginary;
        double d = MathUtils.cosh(real2) + FastMath.cos(imaginary2);
        return createComplex(MathUtils.sinh(real2) / d, FastMath.sin(imaginary2) / d);
    }

    public double getArgument() {
        return FastMath.atan2(getImaginary(), getReal());
    }

    public List<Complex> nthRoot(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.CANNOT_COMPUTE_NTH_ROOT_FOR_NEGATIVE_N, Integer.valueOf(n));
        }
        List<Complex> result = new ArrayList<>();
        if (isNaN()) {
            result.add(NaN);
            return result;
        }
        if (isInfinite()) {
            result.add(INF);
            return result;
        }
        double nthRootOfAbs = FastMath.pow(abs(), 1.0d / n);
        double nthPhi = getArgument() / n;
        double slice = 6.283185307179586d / n;
        double innerPart = nthPhi;
        for (int k = 0; k < n; k++) {
            double realPart = nthRootOfAbs * FastMath.cos(innerPart);
            double imaginaryPart = nthRootOfAbs * FastMath.sin(innerPart);
            result.add(createComplex(realPart, imaginaryPart));
            innerPart += slice;
        }
        return result;
    }

    protected Complex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    protected final Object readResolve() {
        return createComplex(this.real, this.imaginary);
    }

    @Override // org.apache.commons.math.FieldElement
    /* renamed from: getField */
    public Field<Complex> getField2() {
        return ComplexField.getInstance();
    }
}
