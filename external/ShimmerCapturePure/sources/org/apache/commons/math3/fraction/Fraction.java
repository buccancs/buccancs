package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;

/* loaded from: classes5.dex */
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
    private static final double DEFAULT_EPSILON = 1.0E-5d;
    private static final long serialVersionUID = 3698073679419233275L;
    private final int denominator;
    private final int numerator;

    public Fraction(double d) throws FractionConversionException {
        this(d, 1.0E-5d, 100);
    }

    public Fraction(double d, double d2, int i) throws FractionConversionException {
        this(d, d2, Integer.MAX_VALUE, i);
    }

    public Fraction(double d, int i) throws FractionConversionException {
        this(d, 0.0d, i, 100);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a2, code lost:

        r8 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a8, code lost:

        if (r38 != 0.0d) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b1, code lost:

        if (org.apache.commons.math3.util.FastMath.abs(r16) >= r40) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00db, code lost:

        throw new org.apache.commons.math3.fraction.FractionConversionException(r36, r9, r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private Fraction(double r36, double r38, int r40, int r41) throws org.apache.commons.math3.fraction.FractionConversionException {
        /*
            Method dump skipped, instructions count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.fraction.Fraction.<init>(double, double, int, int):void");
    }

    public Fraction(int i) {
        this(i, 1);
    }

    public Fraction(int i, int i2) throws MathArithmeticException {
        if (i2 == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (i2 < 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
            }
            i = -i;
            i2 = -i2;
        }
        int iGcd = ArithmeticUtils.gcd(i, i2);
        if (iGcd > 1) {
            i /= iGcd;
            i2 /= iGcd;
        }
        if (i2 < 0) {
            i = -i;
            i2 = -i2;
        }
        this.numerator = i;
        this.denominator = i2;
    }

    public static Fraction getReducedFraction(int i, int i2) throws MathArithmeticException {
        if (i2 == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (i == 0) {
            return ZERO;
        }
        if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
            i /= 2;
            i2 /= 2;
        }
        if (i2 < 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
            }
            i = -i;
            i2 = -i2;
        }
        int iGcd = ArithmeticUtils.gcd(i, i2);
        return new Fraction(i / iGcd, i2 / iGcd);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.denominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int hashCode() {
        return ((this.numerator + 629) * 37) + this.denominator;
    }

    public Fraction abs() {
        return this.numerator >= 0 ? this : negate();
    }

    @Override // java.lang.Comparable
    public int compareTo(Fraction fraction) {
        long j = this.numerator * fraction.denominator;
        long j2 = this.denominator * fraction.numerator;
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        return this.numerator == fraction.numerator && this.denominator == fraction.denominator;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) doubleValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) doubleValue();
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction negate() {
        int i = this.numerator;
        if (i == Integer.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(this.numerator), Integer.valueOf(this.denominator));
        }
        return new Fraction(-i, this.denominator);
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public Fraction add(int i) {
        int i2 = this.numerator;
        int i3 = this.denominator;
        return new Fraction(i2 + (i * i3), i3);
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public Fraction subtract(int i) {
        int i2 = this.numerator;
        int i3 = this.denominator;
        return new Fraction(i2 - (i * i3), i3);
    }

    private Fraction addSub(Fraction fraction, boolean z) throws MathArithmeticException {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (this.numerator == 0) {
            return z ? fraction : fraction.negate();
        }
        if (fraction.numerator == 0) {
            return this;
        }
        int iGcd = ArithmeticUtils.gcd(this.denominator, fraction.denominator);
        if (iGcd == 1) {
            int iMulAndCheck = ArithmeticUtils.mulAndCheck(this.numerator, fraction.denominator);
            int iMulAndCheck2 = ArithmeticUtils.mulAndCheck(fraction.numerator, this.denominator);
            return new Fraction(z ? ArithmeticUtils.addAndCheck(iMulAndCheck, iMulAndCheck2) : ArithmeticUtils.subAndCheck(iMulAndCheck, iMulAndCheck2), ArithmeticUtils.mulAndCheck(this.denominator, fraction.denominator));
        }
        BigInteger bigIntegerMultiply = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / iGcd));
        BigInteger bigIntegerMultiply2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / iGcd));
        BigInteger bigIntegerAdd = z ? bigIntegerMultiply.add(bigIntegerMultiply2) : bigIntegerMultiply.subtract(bigIntegerMultiply2);
        int iIntValue = bigIntegerAdd.mod(BigInteger.valueOf(iGcd)).intValue();
        int iGcd2 = iIntValue == 0 ? iGcd : ArithmeticUtils.gcd(iIntValue, iGcd);
        BigInteger bigIntegerDivide = bigIntegerAdd.divide(BigInteger.valueOf(iGcd2));
        if (bigIntegerDivide.bitLength() > 31) {
            throw new MathArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY, bigIntegerDivide);
        }
        return new Fraction(bigIntegerDivide.intValue(), ArithmeticUtils.mulAndCheck(this.denominator / iGcd, fraction.denominator / iGcd2));
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction multiply(Fraction fraction) throws MathArithmeticException {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        int i = this.numerator;
        if (i == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int iGcd = ArithmeticUtils.gcd(i, fraction.denominator);
        int iGcd2 = ArithmeticUtils.gcd(fraction.numerator, this.denominator);
        return getReducedFraction(ArithmeticUtils.mulAndCheck(this.numerator / iGcd, fraction.numerator / iGcd2), ArithmeticUtils.mulAndCheck(this.denominator / iGcd2, fraction.denominator / iGcd));
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction multiply(int i) {
        return multiply(new Fraction(i));
    }

    @Override // org.apache.commons.math3.FieldElement
    public Fraction divide(Fraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_FRACTION_TO_DIVIDE_BY, Integer.valueOf(fraction.numerator), Integer.valueOf(fraction.denominator));
        }
        return multiply(fraction.reciprocal());
    }

    public Fraction divide(int i) {
        return divide(new Fraction(i));
    }

    public double percentageValue() {
        return doubleValue() * 100.0d;
    }

    public String toString() {
        if (this.denominator == 1) {
            return Integer.toString(this.numerator);
        }
        if (this.numerator == 0) {
            return "0";
        }
        return this.numerator + " / " + this.denominator;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Field<Fraction> getField() {
        return FractionField.getInstance();
    }
}
