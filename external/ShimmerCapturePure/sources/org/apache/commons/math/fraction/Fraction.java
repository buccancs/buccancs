package org.apache.commons.math.fraction;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.NullArgumentException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/fraction/Fraction.class */
public class Fraction extends Number implements FieldElement<Fraction>, Comparable<Fraction>, Serializable {
    public static final Fraction TWO = new Fraction(2, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction MINUS_ONE = new Fraction(-1, 1);
    private static final long serialVersionUID = 3698073679419233275L;
    private final int denominator;
    private final int numerator;

    public Fraction(double value) throws FractionConversionException {
        this(value, 1.0E-5d, 100);
    }

    public Fraction(double value, double epsilon, int maxIterations) throws FractionConversionException {
        this(value, epsilon, Integer.MAX_VALUE, maxIterations);
    }

    public Fraction(double value, int maxDenominator) throws FractionConversionException {
        this(value, 0.0d, maxDenominator, 100);
    }

    private Fraction(double value, double epsilon, int maxDenominator, int maxIterations) throws FractionConversionException {
        long p2;
        long q2;
        double r0 = value;
        long a0 = (long) FastMath.floor(r0);
        if (a0 > 2147483647L) {
            throw new FractionConversionException(value, a0, 1L);
        }
        if (FastMath.abs(a0 - value) < epsilon) {
            this.numerator = (int) a0;
            this.denominator = 1;
            return;
        }
        long p0 = 1;
        long q0 = 0;
        long p1 = a0;
        long q1 = 1;
        int n = 0;
        boolean stop = false;
        do {
            n++;
            double r1 = 1.0d / (r0 - a0);
            long a1 = (long) FastMath.floor(r1);
            p2 = (a1 * p1) + p0;
            q2 = (a1 * q1) + q0;
            if (p2 > 2147483647L || q2 > 2147483647L) {
                throw new FractionConversionException(value, p2, q2);
            }
            double convergent = p2 / q2;
            if (n < maxIterations && FastMath.abs(convergent - value) > epsilon && q2 < maxDenominator) {
                p0 = p1;
                p1 = p2;
                q0 = q1;
                q1 = q2;
                a0 = a1;
                r0 = r1;
            } else {
                stop = true;
            }
        } while (!stop);
        if (n >= maxIterations) {
            throw new FractionConversionException(value, maxIterations);
        }
        if (q2 < maxDenominator) {
            this.numerator = (int) p2;
            this.denominator = (int) q2;
        } else {
            this.numerator = (int) p1;
            this.denominator = (int) q1;
        }
    }

    public Fraction(int num) {
        this(num, 1);
    }

    public Fraction(int num, int den) {
        if (den == 0) {
            throw MathRuntimeException.createArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(num), Integer.valueOf(den));
        }
        if (den < 0) {
            if (num == Integer.MIN_VALUE || den == Integer.MIN_VALUE) {
                throw MathRuntimeException.createArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(num), Integer.valueOf(den));
            }
            num = -num;
            den = -den;
        }
        int d = MathUtils.gcd(num, den);
        if (d > 1) {
            num /= d;
            den /= d;
        }
        if (den < 0) {
            num = -num;
            den = -den;
        }
        this.numerator = num;
        this.denominator = den;
    }

    public static Fraction getReducedFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw MathRuntimeException.createArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(numerator), Integer.valueOf(denominator));
        }
        if (numerator == 0) {
            return ZERO;
        }
        if (denominator == Integer.MIN_VALUE && (numerator & 1) == 0) {
            numerator /= 2;
            denominator /= 2;
        }
        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE) {
                throw MathRuntimeException.createArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(numerator), Integer.valueOf(denominator));
            }
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = MathUtils.gcd(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    public Fraction abs() {
        Fraction ret;
        if (this.numerator >= 0) {
            ret = this;
        } else {
            ret = negate();
        }
        return ret;
    }

    @Override // java.lang.Comparable
    public int compareTo(Fraction object) {
        long nOd = this.numerator * object.denominator;
        long dOn = this.denominator * object.numerator;
        if (nOd < dOn) {
            return -1;
        }
        return nOd > dOn ? 1 : 0;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.denominator;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Fraction) {
            Fraction rhs = (Fraction) other;
            return this.numerator == rhs.numerator && this.denominator == rhs.denominator;
        }
        return false;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) doubleValue();
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int hashCode() {
        return (37 * (629 + this.numerator)) + this.denominator;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) doubleValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) doubleValue();
    }

    public Fraction negate() {
        if (this.numerator == Integer.MIN_VALUE) {
            throw MathRuntimeException.createArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(this.numerator), Integer.valueOf(this.denominator));
        }
        return new Fraction(-this.numerator, this.denominator);
    }

    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    @Override // org.apache.commons.math.FieldElement
    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public Fraction add(int i) {
        return new Fraction(this.numerator + (i * this.denominator), this.denominator);
    }

    @Override // org.apache.commons.math.FieldElement
    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public Fraction subtract(int i) {
        return new Fraction(this.numerator - (i * this.denominator), this.denominator);
    }

    private Fraction addSub(Fraction fraction, boolean isAdd) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (this.numerator == 0) {
            return isAdd ? fraction : fraction.negate();
        }
        if (fraction.numerator == 0) {
            return this;
        }
        int d1 = MathUtils.gcd(this.denominator, fraction.denominator);
        if (d1 == 1) {
            int uvp = MathUtils.mulAndCheck(this.numerator, fraction.denominator);
            int upv = MathUtils.mulAndCheck(fraction.numerator, this.denominator);
            return new Fraction(isAdd ? MathUtils.addAndCheck(uvp, upv) : MathUtils.subAndCheck(uvp, upv), MathUtils.mulAndCheck(this.denominator, fraction.denominator));
        }
        BigInteger uvp2 = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
        BigInteger upv2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / d1));
        BigInteger t = isAdd ? uvp2.add(upv2) : uvp2.subtract(upv2);
        int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
        int d2 = tmodd1 == 0 ? d1 : MathUtils.gcd(tmodd1, d1);
        BigInteger w = t.divide(BigInteger.valueOf(d2));
        if (w.bitLength() > 31) {
            throw MathRuntimeException.createArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY, w);
        }
        return new Fraction(w.intValue(), MathUtils.mulAndCheck(this.denominator / d1, fraction.denominator / d2));
    }

    @Override // org.apache.commons.math.FieldElement
    public Fraction multiply(Fraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (this.numerator == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int d1 = MathUtils.gcd(this.numerator, fraction.denominator);
        int d2 = MathUtils.gcd(fraction.numerator, this.denominator);
        return getReducedFraction(MathUtils.mulAndCheck(this.numerator / d1, fraction.numerator / d2), MathUtils.mulAndCheck(this.denominator / d2, fraction.denominator / d1));
    }

    public Fraction multiply(int i) {
        return new Fraction(this.numerator * i, this.denominator);
    }

    @Override // org.apache.commons.math.FieldElement
    public Fraction divide(Fraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (fraction.numerator == 0) {
            throw MathRuntimeException.createArithmeticException(LocalizedFormats.ZERO_FRACTION_TO_DIVIDE_BY, Integer.valueOf(fraction.numerator), Integer.valueOf(fraction.denominator));
        }
        return multiply(fraction.reciprocal());
    }

    public Fraction divide(int i) {
        return new Fraction(this.numerator, this.denominator * i);
    }

    public String toString() {
        String str;
        if (this.denominator == 1) {
            str = Integer.toString(this.numerator);
        } else if (this.numerator == 0) {
            str = "0";
        } else {
            str = this.numerator + " / " + this.denominator;
        }
        return str;
    }

    @Override // org.apache.commons.math.FieldElement
    /* renamed from: getField */
    public Field<Fraction> getField2() {
        return FractionField.getInstance();
    }
}
