package org.apache.commons.math3.stat.inference;

import java.lang.reflect.Array;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class GTest {
    public double g(double[] dArr, long[] jArr) throws NotStrictlyPositiveException, NotPositiveException, DimensionMismatchException {
        double d;
        boolean z;
        double d2;
        double d3;
        if (dArr.length < 2) {
            throw new DimensionMismatchException(dArr.length, 2);
        }
        if (dArr.length != jArr.length) {
            throw new DimensionMismatchException(dArr.length, jArr.length);
        }
        MathArrays.checkPositive(dArr);
        MathArrays.checkNonNegative(jArr);
        double dLog = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        for (int i = 0; i < jArr.length; i++) {
            d4 += dArr[i];
            d5 += jArr[i];
        }
        if (FastMath.abs(d4 - d5) > 1.0E-5d) {
            d = d5 / d4;
            z = true;
        } else {
            d = 1.0d;
            z = false;
        }
        for (int i2 = 0; i2 < jArr.length; i2++) {
            if (z) {
                d2 = jArr[i2];
                d3 = dArr[i2] * d;
            } else {
                d2 = jArr[i2];
                d3 = dArr[i2];
            }
            dLog += jArr[i2] * FastMath.log(d2 / d3);
        }
        return dLog * 2.0d;
    }

    public double gTest(double[] dArr, long[] jArr) throws NotStrictlyPositiveException, NotPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, dArr.length - 1.0d).cumulativeProbability(g(dArr, jArr));
    }

    public double gTestIntrinsic(double[] dArr, long[] jArr) throws NotStrictlyPositiveException, NotPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, dArr.length - 2.0d).cumulativeProbability(g(dArr, jArr));
    }

    public boolean gTest(double[] dArr, long[] jArr, double d) throws OutOfRangeException, NotStrictlyPositiveException, NotPositiveException, DimensionMismatchException, MaxCountExceededException {
        if (d <= 0.0d || d > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
        }
        return gTest(dArr, jArr) < d;
    }

    private double entropy(long[][] jArr) {
        double dLog = 0.0d;
        double d = 0.0d;
        for (long[] jArr2 : jArr) {
            int i = 0;
            while (true) {
                if (i < jArr2.length) {
                    d += r7[i];
                    i++;
                }
            }
        }
        for (long[] jArr3 : jArr) {
            int i2 = 0;
            while (true) {
                if (i2 < jArr3.length) {
                    long j = jArr3[i2];
                    if (j != 0) {
                        double d2 = j / d;
                        dLog += d2 * FastMath.log(d2);
                    }
                    i2++;
                }
            }
        }
        return -dLog;
    }

    private double entropy(long[] jArr) {
        double dLog = 0.0d;
        double d = 0.0d;
        for (long j : jArr) {
            d += j;
        }
        for (long j2 : jArr) {
            if (j2 != 0) {
                double d2 = j2 / d;
                dLog += d2 * FastMath.log(d2);
            }
        }
        return -dLog;
    }

    public double gDataSetsComparison(long[] jArr, long[] jArr2) throws NotPositiveException, ZeroException, DimensionMismatchException {
        if (jArr.length < 2) {
            throw new DimensionMismatchException(jArr.length, 2);
        }
        if (jArr.length != jArr2.length) {
            throw new DimensionMismatchException(jArr.length, jArr2.length);
        }
        MathArrays.checkNonNegative(jArr);
        MathArrays.checkNonNegative(jArr2);
        long[] jArr3 = new long[jArr.length];
        long[][] jArr4 = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 2, jArr.length);
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < jArr.length; i++) {
            long j3 = jArr[i];
            if (j3 == 0 && jArr2[i] == 0) {
                throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, Integer.valueOf(i));
            }
            j += j3;
            long j4 = jArr2[i];
            j2 += j4;
            jArr3[i] = j3 + j4;
            jArr4[0][i] = jArr[i];
            jArr4[1][i] = jArr2[i];
        }
        if (j == 0 || j2 == 0) {
            throw new ZeroException();
        }
        return (j + j2) * 2.0d * ((entropy(new long[]{j, j2}) + entropy(jArr3)) - entropy(jArr4));
    }

    public double rootLogLikelihoodRatio(long j, long j2, long j3, long j4) {
        double dSqrt = FastMath.sqrt(gDataSetsComparison(new long[]{j, j2}, new long[]{j3, j4}));
        return ((double) j) / ((double) (j + j2)) < ((double) j3) / ((double) (j3 + j4)) ? -dSqrt : dSqrt;
    }

    public double gTestDataSetsComparison(long[] jArr, long[] jArr2) throws NotPositiveException, ZeroException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, jArr.length - 1.0d).cumulativeProbability(gDataSetsComparison(jArr, jArr2));
    }

    public boolean gTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws OutOfRangeException, NotPositiveException, ZeroException, DimensionMismatchException, MaxCountExceededException {
        if (d <= 0.0d || d > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
        }
        return gTestDataSetsComparison(jArr, jArr2) < d;
    }
}
