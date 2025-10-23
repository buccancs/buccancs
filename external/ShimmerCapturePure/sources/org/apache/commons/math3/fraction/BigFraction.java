package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class BigFraction extends Number implements FieldElement<BigFraction>, Comparable<BigFraction>, Serializable {
    public static final BigFraction TWO = new BigFraction(2);
    public static final BigFraction ONE = new BigFraction(1);
    public static final BigFraction ZERO = new BigFraction(0);
    public static final BigFraction MINUS_ONE = new BigFraction(-1);
    public static final BigFraction FOUR_FIFTHS = new BigFraction(4, 5);
    public static final BigFraction ONE_FIFTH = new BigFraction(1, 5);
    public static final BigFraction ONE_HALF = new BigFraction(1, 2);
    public static final BigFraction ONE_QUARTER = new BigFraction(1, 4);
    public static final BigFraction ONE_THIRD = new BigFraction(1, 3);
    public static final BigFraction THREE_FIFTHS = new BigFraction(3, 5);
    public static final BigFraction THREE_QUARTERS = new BigFraction(3, 4);
    public static final BigFraction TWO_FIFTHS = new BigFraction(2, 5);
    public static final BigFraction TWO_QUARTERS = new BigFraction(2, 4);
    public static final BigFraction TWO_THIRDS = new BigFraction(2, 3);
    private static final long serialVersionUID = -5630213147331578515L;
    private static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100);
    private final BigInteger denominator;
    private final BigInteger numerator;

    public BigFraction(BigInteger bigInteger) {
        this(bigInteger, BigInteger.ONE);
    }

    public BigFraction(BigInteger bigInteger, BigInteger bigInteger2) throws NullArgumentException {
        MathUtils.checkNotNull(bigInteger, LocalizedFormats.NUMERATOR, new Object[0]);
        MathUtils.checkNotNull(bigInteger2, LocalizedFormats.DENOMINATOR, new Object[0]);
        if (bigInteger2.signum() == 0) {
            throw new ZeroException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (bigInteger.signum() == 0) {
            this.numerator = BigInteger.ZERO;
            this.denominator = BigInteger.ONE;
            return;
        }
        BigInteger bigIntegerGcd = bigInteger.gcd(bigInteger2);
        if (BigInteger.ONE.compareTo(bigIntegerGcd) < 0) {
            bigInteger = bigInteger.divide(bigIntegerGcd);
            bigInteger2 = bigInteger2.divide(bigIntegerGcd);
        }
        if (bigInteger2.signum() == -1) {
            bigInteger = bigInteger.negate();
            bigInteger2 = bigInteger2.negate();
        }
        this.numerator = bigInteger;
        this.denominator = bigInteger2;
    }

    public BigFraction(double d) throws MathIllegalArgumentException {
        if (Double.isNaN(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION, new Object[0]);
        }
        if (Double.isInfinite(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION, new Object[0]);
        }
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        long j = Long.MIN_VALUE & jDoubleToLongBits;
        long j2 = 9218868437227405312L & jDoubleToLongBits;
        long j3 = jDoubleToLongBits & 4503599627370495L;
        j3 = j2 != 0 ? j3 | 4503599627370496L : j3;
        j3 = j != 0 ? -j3 : j3;
        int i = ((int) (j2 >> 52)) - 1075;
        while ((9007199254740990L & j3) != 0 && (1 & j3) == 0) {
            j3 >>= 1;
            i++;
        }
        if (i < 0) {
            this.numerator = BigInteger.valueOf(j3);
            this.denominator = BigInteger.ZERO.flipBit(-i);
        } else {
            this.numerator = BigInteger.valueOf(j3).multiply(BigInteger.ZERO.flipBit(i));
            this.denominator = BigInteger.ONE;
        }
    }

    public BigFraction(double d, double d2, int i) throws FractionConversionException {
        this(d, d2, Integer.MAX_VALUE, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x009e, code lost:

        if (r38 != 0.0d) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a7, code lost:

        if (org.apache.commons.math3.util.FastMath.abs(r15) >= r40) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00da, code lost:

        throw new org.apache.commons.math3.fraction.FractionConversionException(r36, r5, r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private BigFraction(double r36, double r38, int r40, int r41) throws org.apache.commons.math3.fraction.FractionConversionException {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.fraction.BigFraction.<init>(double, double, int, int):void");
    }

    public BigFraction(double d, int i) throws FractionConversionException {
        this(d, 0.0d, i, 100);
    }

    public BigFraction(int i) {
        this(BigInteger.valueOf(i), BigInteger.ONE);
    }

    public BigFraction(int i, int i2) {
        this(BigInteger.valueOf(i), BigInteger.valueOf(i2));
    }

    public BigFraction(long j) {
        this(BigInteger.valueOf(j), BigInteger.ONE);
    }

    public BigFraction(long j, long j2) {
        this(BigInteger.valueOf(j), BigInteger.valueOf(j2));
    }

    public static BigFraction getReducedFraction(int i, int i2) {
        return i == 0 ? ZERO : new BigFraction(i, i2);
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }

    public BigInteger getNumerator() {
        return this.numerator;
    }

    public BigFraction abs() {
        return this.numerator.signum() == 1 ? this : negate();
    }

    public BigFraction add(BigInteger bigInteger) throws NullArgumentException {
        MathUtils.checkNotNull(bigInteger);
        if (this.numerator.signum() == 0) {
            return new BigFraction(bigInteger);
        }
        return bigInteger.signum() == 0 ? this : new BigFraction(this.numerator.add(this.denominator.multiply(bigInteger)), this.denominator);
    }

    public BigFraction add(int i) {
        return add(BigInteger.valueOf(i));
    }

    public BigFraction add(long j) {
        return add(BigInteger.valueOf(j));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction add(BigFraction bigFraction) {
        BigInteger bigIntegerMultiply;
        BigInteger bigIntegerAdd;
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (bigFraction.numerator.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return bigFraction;
        }
        if (this.denominator.equals(bigFraction.denominator)) {
            bigIntegerAdd = this.numerator.add(bigFraction.numerator);
            bigIntegerMultiply = this.denominator;
        } else {
            BigInteger bigIntegerAdd2 = this.numerator.multiply(bigFraction.denominator).add(bigFraction.numerator.multiply(this.denominator));
            bigIntegerMultiply = this.denominator.multiply(bigFraction.denominator);
            bigIntegerAdd = bigIntegerAdd2;
        }
        return bigIntegerAdd.signum() == 0 ? ZERO : new BigFraction(bigIntegerAdd, bigIntegerMultiply);
    }

    public BigDecimal bigDecimalValue() {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator));
    }

    public BigDecimal bigDecimalValue(int i) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), i);
    }

    public BigDecimal bigDecimalValue(int i, int i2) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), i, i2);
    }

    @Override // java.lang.Comparable
    public int compareTo(BigFraction bigFraction) {
        int iSignum = this.numerator.signum();
        int iSignum2 = bigFraction.numerator.signum();
        if (iSignum != iSignum2) {
            return iSignum > iSignum2 ? 1 : -1;
        }
        if (iSignum == 0) {
            return 0;
        }
        return this.numerator.multiply(bigFraction.denominator).compareTo(this.denominator.multiply(bigFraction.numerator));
    }

    public BigFraction divide(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (bigInteger.signum() != 0) {
            return this.numerator.signum() == 0 ? ZERO : new BigFraction(this.numerator, this.denominator.multiply(bigInteger));
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
    }

    public BigFraction divide(int i) {
        return divide(BigInteger.valueOf(i));
    }

    public BigFraction divide(long j) {
        return divide(BigInteger.valueOf(j));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction divide(BigFraction bigFraction) {
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (bigFraction.numerator.signum() != 0) {
            return this.numerator.signum() == 0 ? ZERO : multiply(bigFraction.reciprocal());
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        double dDoubleValue = this.numerator.doubleValue() / this.denominator.doubleValue();
        if (!Double.isNaN(dDoubleValue)) {
            return dDoubleValue;
        }
        int iMax = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Double.MAX_VALUE);
        return this.numerator.shiftRight(iMax).doubleValue() / this.denominator.shiftRight(iMax).doubleValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BigFraction) {
            BigFraction bigFractionReduce = ((BigFraction) obj).reduce();
            BigFraction bigFractionReduce2 = reduce();
            if (bigFractionReduce2.numerator.equals(bigFractionReduce.numerator) && bigFractionReduce2.denominator.equals(bigFractionReduce.denominator)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Number
    public float floatValue() {
        float fFloatValue = this.numerator.floatValue() / this.denominator.floatValue();
        if (!Double.isNaN(fFloatValue)) {
            return fFloatValue;
        }
        int iMax = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Float.MAX_VALUE);
        return this.numerator.shiftRight(iMax).floatValue() / this.denominator.shiftRight(iMax).floatValue();
    }

    public int getDenominatorAsInt() {
        return this.denominator.intValue();
    }

    public long getDenominatorAsLong() {
        return this.denominator.longValue();
    }

    public int getNumeratorAsInt() {
        return this.numerator.intValue();
    }

    public long getNumeratorAsLong() {
        return this.numerator.longValue();
    }

    public int hashCode() {
        return ((this.numerator.hashCode() + 629) * 37) + this.denominator.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator.divide(this.denominator).intValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator.divide(this.denominator).longValue();
    }

    public BigFraction multiply(BigInteger bigInteger) {
        if (bigInteger != null) {
            return (this.numerator.signum() == 0 || bigInteger.signum() == 0) ? ZERO : new BigFraction(bigInteger.multiply(this.numerator), this.denominator);
        }
        throw new NullArgumentException();
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction multiply(int i) {
        return (i == 0 || this.numerator.signum() == 0) ? ZERO : multiply(BigInteger.valueOf(i));
    }

    public BigFraction multiply(long j) {
        return (j == 0 || this.numerator.signum() == 0) ? ZERO : multiply(BigInteger.valueOf(j));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction multiply(BigFraction bigFraction) {
        if (bigFraction != null) {
            return (this.numerator.signum() == 0 || bigFraction.numerator.signum() == 0) ? ZERO : new BigFraction(this.numerator.multiply(bigFraction.numerator), this.denominator.multiply(bigFraction.denominator));
        }
        throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction negate() {
        return new BigFraction(this.numerator.negate(), this.denominator);
    }

    public double percentageValue() {
        return multiply(ONE_HUNDRED).doubleValue();
    }

    public BigFraction pow(int i) {
        if (i == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (i < 0) {
            int i2 = -i;
            return new BigFraction(this.denominator.pow(i2), this.numerator.pow(i2));
        }
        return new BigFraction(this.numerator.pow(i), this.denominator.pow(i));
    }

    public BigFraction pow(long j) {
        if (j == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (j < 0) {
            long j2 = -j;
            return new BigFraction(ArithmeticUtils.pow(this.denominator, j2), ArithmeticUtils.pow(this.numerator, j2));
        }
        return new BigFraction(ArithmeticUtils.pow(this.numerator, j), ArithmeticUtils.pow(this.denominator, j));
    }

    public BigFraction pow(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (bigInteger.signum() == -1) {
            BigInteger bigIntegerNegate = bigInteger.negate();
            return new BigFraction(ArithmeticUtils.pow(this.denominator, bigIntegerNegate), ArithmeticUtils.pow(this.numerator, bigIntegerNegate));
        }
        return new BigFraction(ArithmeticUtils.pow(this.numerator, bigInteger), ArithmeticUtils.pow(this.denominator, bigInteger));
    }

    public double pow(double d) {
        return FastMath.pow(this.numerator.doubleValue(), d) / FastMath.pow(this.denominator.doubleValue(), d);
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction reciprocal() {
        return new BigFraction(this.denominator, this.numerator);
    }

    public BigFraction reduce() {
        BigInteger bigIntegerGcd = this.numerator.gcd(this.denominator);
        return BigInteger.ONE.compareTo(bigIntegerGcd) < 0 ? new BigFraction(this.numerator.divide(bigIntegerGcd), this.denominator.divide(bigIntegerGcd)) : this;
    }

    public BigFraction subtract(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullArgumentException();
        }
        if (bigInteger.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return new BigFraction(bigInteger.negate());
        }
        return new BigFraction(this.numerator.subtract(this.denominator.multiply(bigInteger)), this.denominator);
    }

    public BigFraction subtract(int i) {
        return subtract(BigInteger.valueOf(i));
    }

    public BigFraction subtract(long j) {
        return subtract(BigInteger.valueOf(j));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction subtract(BigFraction bigFraction) {
        BigInteger bigIntegerMultiply;
        BigInteger bigIntegerSubtract;
        if (bigFraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (bigFraction.numerator.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return bigFraction.negate();
        }
        if (this.denominator.equals(bigFraction.denominator)) {
            bigIntegerSubtract = this.numerator.subtract(bigFraction.numerator);
            bigIntegerMultiply = this.denominator;
        } else {
            BigInteger bigIntegerSubtract2 = this.numerator.multiply(bigFraction.denominator).subtract(bigFraction.numerator.multiply(this.denominator));
            bigIntegerMultiply = this.denominator.multiply(bigFraction.denominator);
            bigIntegerSubtract = bigIntegerSubtract2;
        }
        return new BigFraction(bigIntegerSubtract, bigIntegerMultiply);
    }

    public String toString() {
        if (BigInteger.ONE.equals(this.denominator)) {
            return this.numerator.toString();
        }
        if (BigInteger.ZERO.equals(this.numerator)) {
            return "0";
        }
        return this.numerator + " / " + this.denominator;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Field<BigFraction> getField() {
        return BigFractionField.getInstance();
    }
}
