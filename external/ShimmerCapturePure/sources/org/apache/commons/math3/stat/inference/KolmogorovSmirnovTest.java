package org.apache.commons.math3.stat.inference;

import com.shimmerresearch.verisense.communication.VerisenseMessage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class KolmogorovSmirnovTest {
    protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20d;
    protected static final int LARGE_SAMPLE_PRODUCT = 10000;
    protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;

    @Deprecated
    protected static final int MONTE_CARLO_ITERATIONS = 1000000;
    protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10d;

    @Deprecated
    protected static final int SMALL_SAMPLE_PRODUCT = 200;
    private final RandomGenerator rng;

    public KolmogorovSmirnovTest() {
        this.rng = new Well19937c();
    }

    @Deprecated
    public KolmogorovSmirnovTest(RandomGenerator randomGenerator) {
        this.rng = randomGenerator;
    }

    private static long calculateIntegralD(double d, int i, int i2, boolean z) {
        double d2 = i * i2;
        long jCeil = (long) FastMath.ceil((d - 1.0E-12d) * d2);
        return (z && ((long) FastMath.floor((d + 1.0E-12d) * d2)) == jCeil) ? jCeil + 1 : jCeil;
    }

    static void fillBooleanArrayRandomlyWithFixedNumberTrueValues(boolean[] zArr, int i, RandomGenerator randomGenerator) {
        Arrays.fill(zArr, true);
        while (i < zArr.length) {
            int i2 = i + 1;
            int iNextInt = randomGenerator.nextInt(i2);
            if (zArr[iNextInt]) {
                i = iNextInt;
            }
            zArr[i] = false;
            i = i2;
        }
    }

    private static void fixTies(double[] dArr, double[] dArr2) {
        boolean zHasTies;
        int i = 0;
        double[] dArrUnique = MathArrays.unique(MathArrays.concatenate(dArr, dArr2));
        if (dArrUnique.length == dArr.length + dArr2.length) {
            return;
        }
        double d = dArrUnique[0];
        double d2 = 1.0d;
        int i2 = 1;
        while (i2 < dArrUnique.length) {
            double d3 = dArrUnique[i2];
            double d4 = d - d3;
            if (d4 < d2) {
                d2 = d4;
            }
            i2++;
            d = d3;
        }
        double d5 = d2 / 2.0d;
        UniformRealDistribution uniformRealDistribution = new UniformRealDistribution(new JDKRandomGenerator(100), -d5, d5);
        do {
            jitter(dArr, uniformRealDistribution);
            jitter(dArr2, uniformRealDistribution);
            zHasTies = hasTies(dArr, dArr2);
            i++;
            if (!zHasTies) {
                break;
            }
        } while (i < 1000);
        if (zHasTies) {
            throw new MathInternalError();
        }
    }

    private static boolean hasTies(double[] dArr, double[] dArr2) {
        HashSet hashSet = new HashSet();
        for (double d : dArr) {
            if (!hashSet.add(Double.valueOf(d))) {
                return true;
            }
        }
        for (double d2 : dArr2) {
            if (!hashSet.add(Double.valueOf(d2))) {
                return true;
            }
        }
        return false;
    }

    private static void jitter(double[] dArr, RealDistribution realDistribution) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] + realDistribution.sample();
        }
    }

    private static int c(int i, int i2, int i3, int i4, long j, boolean z) {
        return z ? FastMath.abs((((long) i) * ((long) i4)) - (((long) i2) * ((long) i3))) <= j ? 1 : 0 : FastMath.abs((((long) i) * ((long) i4)) - (((long) i2) * ((long) i3))) < j ? 1 : 0;
    }

    private static double n(int i, int i2, int i3, int i4, long j, boolean z) {
        double[] dArr = new double[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            dArr[i5] = c(0, r10, i3, i4, j, z);
        }
        double d = 0.0d;
        int i6 = 1;
        while (i6 <= i) {
            double dC = c(i6, 0, i3, i4, j, z);
            for (int i7 = 1; i7 <= i2; i7++) {
                int i8 = i7 - 1;
                dC = (dC + dArr[i8]) * c(i6, i7, i3, i4, j, z);
                dArr[i8] = dC;
            }
            i6++;
            d = dC;
        }
        return d;
    }

    public double kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr, boolean z) {
        return 1.0d - cdf(kolmogorovSmirnovStatistic(realDistribution, dArr), dArr.length, z);
    }

    public double kolmogorovSmirnovStatistic(RealDistribution realDistribution, double[] dArr) {
        checkArray(dArr);
        int length = dArr.length;
        double d = length;
        double[] dArr2 = new double[length];
        System.arraycopy(dArr, 0, dArr2, 0, length);
        Arrays.sort(dArr2);
        double d2 = 0.0d;
        for (int i = 1; i <= length; i++) {
            int i2 = i - 1;
            double dCumulativeProbability = realDistribution.cumulativeProbability(dArr2[i2]);
            double dMax = FastMath.max(dCumulativeProbability - (i2 / d), (i / d) - dCumulativeProbability);
            if (dMax > d2) {
                d2 = dMax;
            }
        }
        return d2;
    }

    public double kolmogorovSmirnovTest(double[] dArr, double[] dArr2, boolean z) {
        double[] dArrCopyOf;
        double[] dArrCopyOf2;
        long length = dArr.length * dArr2.length;
        if (length >= VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER || !hasTies(dArr, dArr2)) {
            dArrCopyOf = dArr;
            dArrCopyOf2 = dArr2;
        } else {
            dArrCopyOf = MathArrays.copyOf(dArr);
            dArrCopyOf2 = MathArrays.copyOf(dArr2);
            fixTies(dArrCopyOf, dArrCopyOf2);
        }
        if (length < VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER) {
            return exactP(kolmogorovSmirnovStatistic(dArrCopyOf, dArrCopyOf2), dArr.length, dArr2.length, z);
        }
        return approximateP(kolmogorovSmirnovStatistic(dArr, dArr2), dArr.length, dArr2.length);
    }

    public double kolmogorovSmirnovTest(double[] dArr, double[] dArr2) {
        return kolmogorovSmirnovTest(dArr, dArr2, true);
    }

    public double kolmogorovSmirnovStatistic(double[] dArr, double[] dArr2) {
        return integralKolmogorovSmirnovStatistic(dArr, dArr2) / (dArr.length * dArr2.length);
    }

    private long integralKolmogorovSmirnovStatistic(double[] dArr, double[] dArr2) {
        checkArray(dArr);
        checkArray(dArr2);
        double[] dArrCopyOf = MathArrays.copyOf(dArr);
        double[] dArrCopyOf2 = MathArrays.copyOf(dArr2);
        Arrays.sort(dArrCopyOf);
        Arrays.sort(dArrCopyOf2);
        int length = dArrCopyOf.length;
        int length2 = dArrCopyOf2.length;
        int i = 0;
        long j = 0;
        long j2 = 0;
        int i2 = 0;
        do {
            double d = Double.compare(dArrCopyOf[i], dArrCopyOf2[i2]) <= 0 ? dArrCopyOf[i] : dArrCopyOf2[i2];
            while (i < length && Double.compare(dArrCopyOf[i], d) == 0) {
                i++;
                j2 += length2;
            }
            while (i2 < length2 && Double.compare(dArrCopyOf2[i2], d) == 0) {
                i2++;
                j2 -= length;
            }
            if (j2 > j) {
                j = j2;
            } else {
                long j3 = -j2;
                if (j3 > j) {
                    j = j3;
                }
            }
            if (i >= length) {
                break;
            }
        } while (i2 < length2);
        return j;
    }

    public double kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr) {
        return kolmogorovSmirnovTest(realDistribution, dArr, false);
    }

    public boolean kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr, double d) {
        if (d <= 0.0d || d > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
        }
        return kolmogorovSmirnovTest(realDistribution, dArr) < d;
    }

    public double bootstrap(double[] dArr, double[] dArr2, int i, boolean z) {
        int length = dArr.length;
        int length2 = dArr2.length;
        double[] dArr3 = new double[length + length2];
        System.arraycopy(dArr, 0, dArr3, 0, length);
        System.arraycopy(dArr2, 0, dArr3, length, length2);
        EnumeratedRealDistribution enumeratedRealDistribution = new EnumeratedRealDistribution(this.rng, dArr3);
        long jIntegralKolmogorovSmirnovStatistic = integralKolmogorovSmirnovStatistic(dArr, dArr2);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            long jIntegralKolmogorovSmirnovStatistic2 = integralKolmogorovSmirnovStatistic(enumeratedRealDistribution.sample(length), enumeratedRealDistribution.sample(length2));
            if (jIntegralKolmogorovSmirnovStatistic2 > jIntegralKolmogorovSmirnovStatistic) {
                i2++;
            } else if (jIntegralKolmogorovSmirnovStatistic2 == jIntegralKolmogorovSmirnovStatistic) {
                i3++;
            }
        }
        if (!z) {
            i2 += i3;
        }
        return i2 / i;
    }

    public double bootstrap(double[] dArr, double[] dArr2, int i) {
        return bootstrap(dArr, dArr2, i, true);
    }

    public double cdf(double d, int i) throws MathArithmeticException {
        return cdf(d, i, false);
    }

    public double cdfExact(double d, int i) throws MathArithmeticException {
        return cdf(d, i, true);
    }

    public double cdf(double d, int i, boolean z) throws MathArithmeticException {
        double d2 = i;
        double d3 = 1.0d;
        double d4 = 1.0d / d2;
        double d5 = 0.5d * d4;
        if (d <= d5) {
            return 0.0d;
        }
        if (d5 < d && d <= d4) {
            double d6 = (d * 2.0d) - d4;
            for (int i2 = 1; i2 <= i; i2++) {
                d3 *= i2 * d6;
            }
            return d3;
        }
        if (1.0d - d4 <= d && d < 1.0d) {
            return 1.0d - (Math.pow(1.0d - d, d2) * 2.0d);
        }
        if (1.0d <= d) {
            return 1.0d;
        }
        if (z) {
            return exactK(d, i);
        }
        if (i <= 140) {
            return roundedK(d, i);
        }
        return pelzGood(d, i);
    }

    private double exactK(double d, int i) throws MathArithmeticException {
        int iCeil = ((int) Math.ceil(i * d)) - 1;
        BigFraction bigFractionDivide = (BigFraction) createExactH(d, i).power(i).getEntry(iCeil, iCeil);
        for (int i2 = 1; i2 <= i; i2++) {
            bigFractionDivide = bigFractionDivide.multiply(i2).divide(i);
        }
        return bigFractionDivide.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d, int i) throws OutOfRangeException {
        double d2 = i;
        int iCeil = ((int) Math.ceil(d2 * d)) - 1;
        double entry = createRoundedH(d, i).power(i).getEntry(iCeil, iCeil);
        for (int i2 = 1; i2 <= i; i2++) {
            entry *= i2 / d2;
        }
        return entry;
    }

    public double pelzGood(double d, int i) {
        double d2;
        double d3;
        int i2;
        double d4;
        double d5 = i;
        double dSqrt = FastMath.sqrt(d5);
        double d6 = d * dSqrt;
        double d7 = d * d * d5;
        double d8 = d7 * d7;
        double d9 = d8 * d7;
        double d10 = 9.869604401089358d / (8.0d * d7);
        double d11 = 0.0d;
        double d12 = d8 * d8;
        double d13 = 0.0d;
        int i3 = 1;
        while (true) {
            if (i3 >= MAXIMUM_PARTIAL_SUM_COUNT) {
                d2 = d5;
                d3 = d9;
                break;
            }
            d2 = d5;
            double d14 = (i3 * 2) - 1;
            d3 = d9;
            double dExp = FastMath.exp((-d10) * d14 * d14);
            d13 += dExp;
            if (dExp <= d13 * 1.0E-10d) {
                break;
            }
            i3++;
            d5 = d2;
            d9 = d3;
        }
        int i4 = MAXIMUM_PARTIAL_SUM_COUNT;
        if (i3 == MAXIMUM_PARTIAL_SUM_COUNT) {
            throw new TooManyIterationsException(Integer.valueOf(MAXIMUM_PARTIAL_SUM_COUNT));
        }
        double dSqrt2 = (d13 * FastMath.sqrt(6.283185307179586d)) / d6;
        double d15 = d7 * 2.0d;
        double d16 = 0.0d;
        int i5 = 0;
        while (true) {
            if (i5 >= i4) {
                break;
            }
            double d17 = i5 + 0.5d;
            double d18 = d17 * d17;
            double dExp2 = ((d18 * 9.869604401089358d) - d7) * FastMath.exp((d18 * (-9.869604401089358d)) / d15);
            d16 += dExp2;
            if (FastMath.abs(dExp2) < FastMath.abs(d16) * 1.0E-10d) {
                i4 = MAXIMUM_PARTIAL_SUM_COUNT;
                break;
            }
            i5++;
            i4 = MAXIMUM_PARTIAL_SUM_COUNT;
        }
        if (i5 == i4) {
            throw new TooManyIterationsException(Integer.valueOf(MAXIMUM_PARTIAL_SUM_COUNT));
        }
        double dSqrt3 = FastMath.sqrt(1.5707963267948966d);
        double d19 = dSqrt2 + ((d16 * dSqrt3) / ((3.0d * d8) * dSqrt));
        double d20 = 2.0d * d8;
        double d21 = 6.0d * d3;
        double d22 = d7 * 5.0d;
        double d23 = 0.0d;
        int i6 = 0;
        while (true) {
            i2 = MAXIMUM_PARTIAL_SUM_COUNT;
            d4 = dSqrt;
            if (i6 >= MAXIMUM_PARTIAL_SUM_COUNT) {
                break;
            }
            double d24 = i6 + 0.5d;
            double d25 = d24 * d24;
            double dExp3 = (d21 + d20 + ((d20 - d22) * 9.869604401089358d * d25) + ((1.0d - d15) * 97.40909103400243d * d25 * d25)) * FastMath.exp((d25 * (-9.869604401089358d)) / d15);
            d23 += dExp3;
            if (FastMath.abs(dExp3) < FastMath.abs(d23) * 1.0E-10d) {
                i2 = MAXIMUM_PARTIAL_SUM_COUNT;
                break;
            }
            i6++;
            dSqrt = d4;
        }
        if (i6 == i2) {
            throw new TooManyIterationsException(Integer.valueOf(i2));
        }
        double d26 = 0.0d;
        int i7 = 1;
        while (true) {
            if (i7 >= i2) {
                break;
            }
            double d27 = i7 * i7;
            double dExp4 = d27 * 9.869604401089358d * FastMath.exp((d27 * (-9.869604401089358d)) / d15);
            d26 += dExp4;
            if (FastMath.abs(dExp4) < FastMath.abs(d26) * 1.0E-10d) {
                i2 = MAXIMUM_PARTIAL_SUM_COUNT;
                break;
            }
            i7++;
            i2 = MAXIMUM_PARTIAL_SUM_COUNT;
        }
        if (i7 == i2) {
            throw new TooManyIterationsException(Integer.valueOf(i2));
        }
        double d28 = d19 + ((dSqrt3 / d2) * ((d23 / ((((36.0d * d7) * d7) * d7) * d6)) - (d26 / ((18.0d * d7) * d6))));
        double d29 = 0.0d;
        int i8 = MAXIMUM_PARTIAL_SUM_COUNT;
        int i9 = 0;
        while (true) {
            if (i9 >= i8) {
                break;
            }
            double d30 = i9 + 0.5d;
            double d31 = d30 * d30;
            double d32 = d31 * d31;
            double d33 = d12;
            double dExp5 = (((((((d32 * d31) * 961.3891935753043d) * (5.0d - (d7 * 30.0d))) + ((d32 * 97.40909103400243d) * (((-60.0d) * d7) + (212.0d * d8)))) + ((d31 * 9.869604401089358d) * ((135.0d * d8) - (96.0d * d3)))) - (d3 * 30.0d)) - (90.0d * d33)) * FastMath.exp((d31 * (-9.869604401089358d)) / d15);
            d29 += dExp5;
            if (FastMath.abs(dExp5) < FastMath.abs(d29) * 1.0E-10d) {
                i8 = MAXIMUM_PARTIAL_SUM_COUNT;
                break;
            }
            i9++;
            d12 = d33;
            i8 = MAXIMUM_PARTIAL_SUM_COUNT;
        }
        if (i9 == i8) {
            throw new TooManyIterationsException(Integer.valueOf(MAXIMUM_PARTIAL_SUM_COUNT));
        }
        int i10 = 1;
        while (true) {
            if (i10 >= i8) {
                break;
            }
            double d34 = i10 * i10;
            double dExp6 = ((d34 * d34 * (-97.40909103400243d)) + (29.608813203268074d * d34 * d7)) * FastMath.exp((d34 * (-9.869604401089358d)) / d15);
            d11 += dExp6;
            if (FastMath.abs(dExp6) < FastMath.abs(d11) * 1.0E-10d) {
                i8 = MAXIMUM_PARTIAL_SUM_COUNT;
                break;
            }
            i10++;
            i8 = MAXIMUM_PARTIAL_SUM_COUNT;
        }
        if (i10 != i8) {
            return d28 + ((dSqrt3 / (d4 * d2)) * ((d29 / ((3240.0d * d3) * d8)) + (d11 / (108.0d * d3))));
        }
        throw new TooManyIterationsException(Integer.valueOf(MAXIMUM_PARTIAL_SUM_COUNT));
    }

    private FieldMatrix<BigFraction> createExactH(double d, int i) throws FractionConversionException, NumberIsTooLargeException {
        BigFraction bigFraction;
        int i2;
        double d2 = i * d;
        int iCeil = (int) Math.ceil(d2);
        int i3 = iCeil * 2;
        int i4 = i3 - 1;
        double d3 = iCeil - d2;
        if (d3 >= 1.0d) {
            throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(1.0d), false);
        }
        try {
            try {
                bigFraction = new BigFraction(d3, KS_SUM_CAUCHY_CRITERION, 10000);
            } catch (FractionConversionException unused) {
                bigFraction = new BigFraction(d3, 1.0E-5d, 10000);
            }
        } catch (FractionConversionException unused2) {
            bigFraction = new BigFraction(d3, 1.0E-10d, 10000);
        }
        BigFraction[][] bigFractionArr = (BigFraction[][]) Array.newInstance((Class<?>) BigFraction.class, i4, i4);
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                if ((i5 - i6) + 1 < 0) {
                    bigFractionArr[i5][i6] = BigFraction.ZERO;
                } else {
                    bigFractionArr[i5][i6] = BigFraction.ONE;
                }
            }
        }
        BigFraction[] bigFractionArr2 = new BigFraction[i4];
        bigFractionArr2[0] = bigFraction;
        for (int i7 = 1; i7 < i4; i7++) {
            bigFractionArr2[i7] = bigFraction.multiply(bigFractionArr2[i7 - 1]);
        }
        for (int i8 = 0; i8 < i4; i8++) {
            BigFraction[] bigFractionArr3 = bigFractionArr[i8];
            bigFractionArr3[0] = bigFractionArr3[0].subtract(bigFractionArr2[i8]);
            BigFraction[] bigFractionArr4 = bigFractionArr[i3 - 2];
            bigFractionArr4[i8] = bigFractionArr4[i8].subtract(bigFractionArr2[(i4 - i8) - 1]);
        }
        if (bigFraction.compareTo(BigFraction.ONE_HALF) == 1) {
            BigFraction[] bigFractionArr5 = bigFractionArr[i3 - 2];
            bigFractionArr5[0] = bigFractionArr5[0].add(bigFraction.multiply(2).subtract(1).pow(i4));
        }
        int i9 = 0;
        while (i9 < i4) {
            int i10 = 0;
            while (true) {
                i2 = i9 + 1;
                if (i10 < i2) {
                    int i11 = (i9 - i10) + 1;
                    if (i11 > 0) {
                        for (int i12 = 2; i12 <= i11; i12++) {
                            BigFraction[] bigFractionArr6 = bigFractionArr[i9];
                            bigFractionArr6[i10] = bigFractionArr6[i10].divide(i12);
                        }
                    }
                    i10++;
                }
            }
            i9 = i2;
        }
        return new Array2DRowFieldMatrix(BigFractionField.getInstance(), bigFractionArr);
    }

    private RealMatrix createRoundedH(double d, int i) throws NumberIsTooLargeException {
        int i2;
        double d2 = i * d;
        int iCeil = (int) Math.ceil(d2);
        int i3 = iCeil * 2;
        int i4 = i3 - 1;
        double d3 = iCeil - d2;
        if (d3 >= 1.0d) {
            throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(1.0d), false);
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i4, i4);
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                if ((i5 - i6) + 1 < 0) {
                    dArr[i5][i6] = 0.0d;
                } else {
                    dArr[i5][i6] = 1.0d;
                }
            }
        }
        double[] dArr2 = new double[i4];
        dArr2[0] = d3;
        for (int i7 = 1; i7 < i4; i7++) {
            dArr2[i7] = dArr2[i7 - 1] * d3;
        }
        for (int i8 = 0; i8 < i4; i8++) {
            double[] dArr3 = dArr[i8];
            dArr3[0] = dArr3[0] - dArr2[i8];
            double[] dArr4 = dArr[i3 - 2];
            dArr4[i8] = dArr4[i8] - dArr2[(i4 - i8) - 1];
        }
        if (Double.compare(d3, 0.5d) > 0) {
            double[] dArr5 = dArr[i3 - 2];
            dArr5[0] = dArr5[0] + FastMath.pow((d3 * 2.0d) - 1.0d, i4);
        }
        int i9 = 0;
        while (i9 < i4) {
            int i10 = 0;
            while (true) {
                i2 = i9 + 1;
                if (i10 < i2) {
                    int i11 = (i9 - i10) + 1;
                    if (i11 > 0) {
                        for (int i12 = 2; i12 <= i11; i12++) {
                            double[] dArr6 = dArr[i9];
                            dArr6[i10] = dArr6[i10] / i12;
                        }
                    }
                    i10++;
                }
            }
            i9 = i2;
        }
        return MatrixUtils.createRealMatrix(dArr);
    }

    private void checkArray(double[] dArr) {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        }
        if (dArr.length < 2) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(dArr.length), 2);
        }
    }

    public double ksSum(double d, double d2, int i) {
        if (d == 0.0d) {
            return 0.0d;
        }
        double d3 = (-2.0d) * d * d;
        double d4 = 0.5d;
        double dExp = 1.0d;
        long j = 1;
        int i2 = -1;
        while (dExp > d2 && j < i) {
            double d5 = j;
            dExp = FastMath.exp(d3 * d5 * d5);
            d4 += i2 * dExp;
            i2 *= -1;
            j++;
        }
        if (j != i) {
            return d4 * 2.0d;
        }
        throw new TooManyIterationsException(Integer.valueOf(i));
    }

    public double exactP(double d, int i, int i2, boolean z) {
        return 1.0d - (n(i2, i, i2, i, calculateIntegralD(d, i2, i, z), z) / CombinatoricsUtils.binomialCoefficientDouble(i + i2, i2));
    }

    public double approximateP(double d, int i, int i2) {
        double d2 = i2;
        double d3 = i;
        return 1.0d - ksSum(d * FastMath.sqrt((d2 * d3) / (d2 + d3)), KS_SUM_CAUCHY_CRITERION, MAXIMUM_PARTIAL_SUM_COUNT);
    }

    public double monteCarloP(double d, int i, int i2, boolean z, int i3) {
        return integralMonteCarloP(calculateIntegralD(d, i, i2, z), i, i2, i3);
    }

    private double integralMonteCarloP(long j, int i, int i2, int i3) {
        int iMax = FastMath.max(i, i2);
        int iMin = FastMath.min(i, i2);
        int i4 = iMax + iMin;
        boolean[] zArr = new boolean[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            fillBooleanArrayRandomlyWithFixedNumberTrueValues(zArr, iMax, this.rng);
            long j2 = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                if (zArr[i7]) {
                    j2 += iMin;
                    if (j2 >= j) {
                        i5++;
                        break;
                    }
                } else {
                    j2 -= iMax;
                    if (j2 <= (-j)) {
                        i5++;
                        break;
                        break;
                    }
                }
            }
        }
        return i5 / i3;
    }
}
