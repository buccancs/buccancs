package org.apache.commons.math3.util;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public final class CombinatoricsUtils {
    static final long[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final AtomicReference<long[][]> STIRLING_S2 = new AtomicReference<>(null);

    private CombinatoricsUtils() {
    }

    public static long binomialCoefficient(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        long jMulAndCheck = 1;
        if (i == i2 || i2 == 0) {
            return 1L;
        }
        if (i2 == 1 || i2 == i - 1) {
            return i;
        }
        if (i2 > i / 2) {
            return binomialCoefficient(i, i - i2);
        }
        if (i <= 61) {
            int i3 = (i - i2) + 1;
            for (int i4 = 1; i4 <= i2; i4++) {
                jMulAndCheck = (jMulAndCheck * i3) / i4;
                i3++;
            }
        } else if (i <= 66) {
            int i5 = (i - i2) + 1;
            for (int i6 = 1; i6 <= i2; i6++) {
                long jGcd = ArithmeticUtils.gcd(i5, i6);
                jMulAndCheck = (jMulAndCheck / (i6 / jGcd)) * (i5 / jGcd);
                i5++;
            }
        } else {
            int i7 = (i - i2) + 1;
            for (int i8 = 1; i8 <= i2; i8++) {
                long jGcd2 = ArithmeticUtils.gcd(i7, i8);
                jMulAndCheck = ArithmeticUtils.mulAndCheck(jMulAndCheck / (i8 / jGcd2), i7 / jGcd2);
                i7++;
            }
        }
        return jMulAndCheck;
    }

    public static double binomialCoefficientDouble(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        double d = 1.0d;
        if (i == i2 || i2 == 0) {
            return 1.0d;
        }
        if (i2 == 1 || i2 == i - 1) {
            return i;
        }
        if (i2 > i / 2) {
            return binomialCoefficientDouble(i, i - i2);
        }
        if (i < 67) {
            return binomialCoefficient(i, i2);
        }
        for (int i3 = 1; i3 <= i2; i3++) {
            d *= ((i - i2) + i3) / i3;
        }
        return FastMath.floor(d + 0.5d);
    }

    public static double binomialCoefficientLog(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        double dLog = 0.0d;
        if (i == i2 || i2 == 0) {
            return 0.0d;
        }
        if (i2 == 1 || i2 == i - 1) {
            return FastMath.log(i);
        }
        if (i < 67) {
            return FastMath.log(binomialCoefficient(i, i2));
        }
        if (i < 1030) {
            return FastMath.log(binomialCoefficientDouble(i, i2));
        }
        if (i2 > i / 2) {
            return binomialCoefficientLog(i, i - i2);
        }
        for (int i3 = (i - i2) + 1; i3 <= i; i3++) {
            dLog += FastMath.log(i3);
        }
        for (int i4 = 2; i4 <= i2; i4++) {
            dLog -= FastMath.log(i4);
        }
        return dLog;
    }

    public static long factorial(int i) throws NotPositiveException, MathArithmeticException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        }
        if (i > 20) {
            throw new MathArithmeticException();
        }
        return FACTORIALS[i];
    }

    public static double factorialDouble(int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        }
        if (i < 21) {
            return FACTORIALS[i];
        }
        return FastMath.floor(FastMath.exp(factorialLog(i)) + 0.5d);
    }

    public static double factorialLog(int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        }
        if (i < 21) {
            return FastMath.log(FACTORIALS[i]);
        }
        double dLog = 0.0d;
        for (int i2 = 2; i2 <= i; i2++) {
            dLog += FastMath.log(i2);
        }
        return dLog;
    }

    public static long stirlingS2(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        if (i2 < 0) {
            throw new NotPositiveException(Integer.valueOf(i2));
        }
        if (i2 > i) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(i), true);
        }
        long[][] jArr = STIRLING_S2.get();
        char c = 0;
        long j = 1;
        if (jArr == null) {
            long[][] jArr2 = new long[26][];
            jArr2[0] = new long[]{1};
            int i3 = 1;
            while (i3 < 26) {
                int i4 = i3 + 1;
                long[] jArr3 = new long[i4];
                jArr2[i3] = jArr3;
                jArr3[c] = 0;
                jArr3[1] = j;
                jArr3[i3] = j;
                int i5 = 2;
                while (i5 < i3) {
                    long[] jArr4 = jArr2[i3];
                    long[] jArr5 = jArr2[i3 - 1];
                    jArr4[i5] = (i5 * jArr5[i5]) + jArr5[i5 - 1];
                    i5++;
                    c = 0;
                    j = 1;
                }
                i3 = i4;
            }
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(STIRLING_S2, null, jArr2);
            jArr = jArr2;
        }
        if (i < jArr.length) {
            return jArr[i][i2];
        }
        if (i2 == 0) {
            return 0L;
        }
        if (i2 == 1 || i2 == i) {
            return 1L;
        }
        if (i2 == 2) {
            return (1 << (i - 1)) - 1;
        }
        if (i2 == i - 1) {
            return binomialCoefficient(i, 2);
        }
        long j2 = (i2 & 1) == 0 ? 1L : -1L;
        long jBinomialCoefficient = 0;
        int i6 = 1;
        while (i6 <= i2) {
            j2 = -j2;
            long[][] jArr6 = jArr;
            jBinomialCoefficient += binomialCoefficient(i2, i6) * j2 * ArithmeticUtils.pow(i6, i);
            if (jBinomialCoefficient < 0) {
                throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, Integer.valueOf(i), 0, Integer.valueOf(jArr6.length - 1));
            }
            i6++;
            jArr = jArr6;
        }
        return jBinomialCoefficient / factorial(i2);
    }

    public static Iterator<int[]> combinationsIterator(int i, int i2) {
        return new Combinations(i, i2).iterator();
    }

    public static void checkBinomial(int i, int i2) throws NotPositiveException, NumberIsTooLargeException {
        if (i < i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, Integer.valueOf(i2), Integer.valueOf(i), true);
        }
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        }
    }
}
