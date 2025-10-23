package org.apache.commons.math.stat.inference;

import org.apache.commons.math.MathException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.distribution.ChiSquaredDistribution;
import org.apache.commons.math.distribution.ChiSquaredDistributionImpl;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/inference/ChiSquareTestImpl.class */
public class ChiSquareTestImpl implements UnknownDistributionChiSquareTest {
    private ChiSquaredDistribution distribution;

    public ChiSquareTestImpl() {
        this(new ChiSquaredDistributionImpl(1.0d));
    }

    public ChiSquareTestImpl(ChiSquaredDistribution x) {
        setDistribution(x);
    }

    @Override // org.apache.commons.math.stat.inference.ChiSquareTest
    public double chiSquare(double[] expected, long[] observed) throws IllegalArgumentException {
        double d;
        double d2;
        double d3;
        if (expected.length < 2) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(expected.length), 2);
        }
        if (expected.length != observed.length) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(expected.length), Integer.valueOf(observed.length));
        }
        checkPositive(expected);
        checkNonNegative(observed);
        double sumExpected = 0.0d;
        double sumObserved = 0.0d;
        for (int i = 0; i < observed.length; i++) {
            sumExpected += expected[i];
            sumObserved += observed[i];
        }
        double ratio = 1.0d;
        boolean rescale = false;
        if (FastMath.abs(sumExpected - sumObserved) > 1.0E-5d) {
            ratio = sumObserved / sumExpected;
            rescale = true;
        }
        double sumSq = 0.0d;
        for (int i2 = 0; i2 < observed.length; i2++) {
            if (rescale) {
                double dev = observed[i2] - (ratio * expected[i2]);
                d = sumSq;
                d2 = dev * dev;
                d3 = ratio * expected[i2];
            } else {
                double dev2 = observed[i2] - expected[i2];
                d = sumSq;
                d2 = dev2 * dev2;
                d3 = expected[i2];
            }
            sumSq = d + (d2 / d3);
        }
        return sumSq;
    }

    @Override // org.apache.commons.math.stat.inference.ChiSquareTest
    public double chiSquareTest(double[] expected, long[] observed) throws MathException, IllegalArgumentException {
        this.distribution.setDegreesOfFreedom(expected.length - 1.0d);
        return 1.0d - this.distribution.cumulativeProbability(chiSquare(expected, observed));
    }

    @Override // org.apache.commons.math.stat.inference.ChiSquareTest
    public boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws MathException, IllegalArgumentException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return chiSquareTest(expected, observed) < alpha;
    }

    @Override // org.apache.commons.math.stat.inference.ChiSquareTest
    public double chiSquare(long[][] counts) throws IllegalArgumentException {
        checkArray(counts);
        int nRows = counts.length;
        int nCols = counts[0].length;
        double[] rowSum = new double[nRows];
        double[] colSum = new double[nCols];
        double total = 0.0d;
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                int i = row;
                rowSum[i] = rowSum[i] + counts[row][col];
                int i2 = col;
                colSum[i2] = colSum[i2] + counts[row][col];
                total += counts[row][col];
            }
        }
        double sumSq = 0.0d;
        for (int row2 = 0; row2 < nRows; row2++) {
            for (int col2 = 0; col2 < nCols; col2++) {
                double expected = (rowSum[row2] * colSum[col2]) / total;
                sumSq += ((counts[row2][col2] - expected) * (counts[row2][col2] - expected)) / expected;
            }
        }
        return sumSq;
    }

    @Override // org.apache.commons.math.stat.inference.ChiSquareTest
    public double chiSquareTest(long[][] counts) throws MathException, IllegalArgumentException {
        checkArray(counts);
        double df = (counts.length - 1.0d) * (counts[0].length - 1.0d);
        this.distribution.setDegreesOfFreedom(df);
        return 1.0d - this.distribution.cumulativeProbability(chiSquare(counts));
    }

    @Override // org.apache.commons.math.stat.inference.ChiSquareTest
    public boolean chiSquareTest(long[][] counts, double alpha) throws MathException, IllegalArgumentException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Double.valueOf(0.0d), Double.valueOf(0.5d));
        }
        return chiSquareTest(counts) < alpha;
    }

    @Override // org.apache.commons.math.stat.inference.UnknownDistributionChiSquareTest
    public double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws IllegalArgumentException {
        double d;
        double d2;
        if (observed1.length < 2) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(observed1.length), 2);
        }
        if (observed1.length != observed2.length) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(observed1.length), Integer.valueOf(observed2.length));
        }
        checkNonNegative(observed1);
        checkNonNegative(observed2);
        long countSum1 = 0;
        long countSum2 = 0;
        double weight = 0.0d;
        for (int i = 0; i < observed1.length; i++) {
            countSum1 += observed1[i];
            countSum2 += observed2[i];
        }
        if (countSum1 == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OBSERVED_COUNTS_ALL_ZERO, 1);
        }
        if (countSum2 == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OBSERVED_COUNTS_ALL_ZERO, 2);
        }
        boolean unequalCounts = countSum1 != countSum2;
        if (unequalCounts) {
            weight = FastMath.sqrt(countSum1 / countSum2);
        }
        double sumSq = 0.0d;
        for (int i2 = 0; i2 < observed1.length; i2++) {
            if (observed1[i2] == 0 && observed2[i2] == 0) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, Integer.valueOf(i2));
            }
            double obs1 = observed1[i2];
            double obs2 = observed2[i2];
            if (unequalCounts) {
                d = obs1 / weight;
                d2 = obs2 * weight;
            } else {
                d = obs1;
                d2 = obs2;
            }
            double dev = d - d2;
            sumSq += (dev * dev) / (obs1 + obs2);
        }
        return sumSq;
    }

    @Override // org.apache.commons.math.stat.inference.UnknownDistributionChiSquareTest
    public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws MathException, IllegalArgumentException {
        this.distribution.setDegreesOfFreedom(observed1.length - 1.0d);
        return 1.0d - this.distribution.cumulativeProbability(chiSquareDataSetsComparison(observed1, observed2));
    }

    @Override // org.apache.commons.math.stat.inference.UnknownDistributionChiSquareTest
    public boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws MathException, IllegalArgumentException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Double.valueOf(0.0d), Double.valueOf(0.5d));
        }
        return chiSquareTestDataSetsComparison(observed1, observed2) < alpha;
    }

    private void checkArray(long[][] in) throws IllegalArgumentException {
        if (in.length < 2) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(in.length), 2);
        }
        if (in[0].length < 2) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(in[0].length), 2);
        }
        checkRectangular(in);
        checkNonNegative(in);
    }

    private void checkRectangular(long[][] in) {
        for (int i = 1; i < in.length; i++) {
            if (in[i].length != in[0].length) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, Integer.valueOf(in[i].length), Integer.valueOf(in[0].length));
            }
        }
    }

    private void checkPositive(double[] in) throws IllegalArgumentException {
        for (int i = 0; i < in.length; i++) {
            if (in[i] <= 0.0d) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_POSITIVE_ELEMENT_AT_INDEX, Integer.valueOf(i), Double.valueOf(in[i]));
            }
        }
    }

    private void checkNonNegative(long[] in) throws IllegalArgumentException {
        for (int i = 0; i < in.length; i++) {
            if (in[i] < 0) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, Integer.valueOf(i), Long.valueOf(in[i]));
            }
        }
    }

    private void checkNonNegative(long[][] in) throws IllegalArgumentException {
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length; j++) {
                if (in[i][j] < 0) {
                    throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_2D_INDEX, Integer.valueOf(i), Integer.valueOf(j), Long.valueOf(in[i][j]));
                }
            }
        }
    }

    public void setDistribution(ChiSquaredDistribution value) {
        this.distribution = value;
    }
}
