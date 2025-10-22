package org.apache.commons.math.stat.inference;

import java.util.Collection;

import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.descriptive.StatisticalSummary;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/inference/TestUtils.class */
public class TestUtils {
    private static TTest tTest = new TTestImpl();
    private static ChiSquareTest chiSquareTest = new ChiSquareTestImpl();
    private static UnknownDistributionChiSquareTest unknownDistributionChiSquareTest = new ChiSquareTestImpl();
    private static OneWayAnova oneWayAnova = new OneWayAnovaImpl();

    protected TestUtils() {
    }

    @Deprecated
    public static TTest getTTest() {
        return tTest;
    }

    @Deprecated
    public static ChiSquareTest getChiSquareTest() {
        return chiSquareTest;
    }

    @Deprecated
    public static void setChiSquareTest(TTest chiSquareTest2) {
        tTest = chiSquareTest2;
    }

    @Deprecated
    public static void setChiSquareTest(ChiSquareTest chiSquareTest2) {
        chiSquareTest = chiSquareTest2;
    }

    @Deprecated
    public static UnknownDistributionChiSquareTest getUnknownDistributionChiSquareTest() {
        return unknownDistributionChiSquareTest;
    }

    @Deprecated
    public static void setUnknownDistributionChiSquareTest(UnknownDistributionChiSquareTest unknownDistributionChiSquareTest2) {
        unknownDistributionChiSquareTest = unknownDistributionChiSquareTest2;
    }

    @Deprecated
    public static OneWayAnova getOneWayAnova() {
        return oneWayAnova;
    }

    @Deprecated
    public static void setOneWayAnova(OneWayAnova oneWayAnova2) {
        oneWayAnova = oneWayAnova2;
    }

    public static double homoscedasticT(double[] sample1, double[] sample2) throws IllegalArgumentException {
        return tTest.homoscedasticT(sample1, sample2);
    }

    public static double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws IllegalArgumentException {
        return tTest.homoscedasticT(sampleStats1, sampleStats2);
    }

    public static boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha) throws MathException, IllegalArgumentException {
        return tTest.homoscedasticTTest(sample1, sample2, alpha);
    }

    public static double homoscedasticTTest(double[] sample1, double[] sample2) throws MathException, IllegalArgumentException {
        return tTest.homoscedasticTTest(sample1, sample2);
    }

    public static double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws MathException, IllegalArgumentException {
        return tTest.homoscedasticTTest(sampleStats1, sampleStats2);
    }

    public static double pairedT(double[] sample1, double[] sample2) throws MathException, IllegalArgumentException {
        return tTest.pairedT(sample1, sample2);
    }

    public static boolean pairedTTest(double[] sample1, double[] sample2, double alpha) throws MathException, IllegalArgumentException {
        return tTest.pairedTTest(sample1, sample2, alpha);
    }

    public static double pairedTTest(double[] sample1, double[] sample2) throws MathException, IllegalArgumentException {
        return tTest.pairedTTest(sample1, sample2);
    }

    public static double t(double mu, double[] observed) throws IllegalArgumentException {
        return tTest.t(mu, observed);
    }

    public static double t(double mu, StatisticalSummary sampleStats) throws IllegalArgumentException {
        return tTest.t(mu, sampleStats);
    }

    public static double t(double[] sample1, double[] sample2) throws IllegalArgumentException {
        return tTest.t(sample1, sample2);
    }

    public static double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws IllegalArgumentException {
        return tTest.t(sampleStats1, sampleStats2);
    }

    public static boolean tTest(double mu, double[] sample, double alpha) throws MathException, IllegalArgumentException {
        return tTest.tTest(mu, sample, alpha);
    }

    public static double tTest(double mu, double[] sample) throws MathException, IllegalArgumentException {
        return tTest.tTest(mu, sample);
    }

    public static boolean tTest(double mu, StatisticalSummary sampleStats, double alpha) throws MathException, IllegalArgumentException {
        return tTest.tTest(mu, sampleStats, alpha);
    }

    public static double tTest(double mu, StatisticalSummary sampleStats) throws MathException, IllegalArgumentException {
        return tTest.tTest(mu, sampleStats);
    }

    public static boolean tTest(double[] sample1, double[] sample2, double alpha) throws MathException, IllegalArgumentException {
        return tTest.tTest(sample1, sample2, alpha);
    }

    public static double tTest(double[] sample1, double[] sample2) throws MathException, IllegalArgumentException {
        return tTest.tTest(sample1, sample2);
    }

    public static boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha) throws MathException, IllegalArgumentException {
        return tTest.tTest(sampleStats1, sampleStats2, alpha);
    }

    public static double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws MathException, IllegalArgumentException {
        return tTest.tTest(sampleStats1, sampleStats2);
    }

    public static double chiSquare(double[] expected, long[] observed) throws IllegalArgumentException {
        return chiSquareTest.chiSquare(expected, observed);
    }

    public static double chiSquare(long[][] counts) throws IllegalArgumentException {
        return chiSquareTest.chiSquare(counts);
    }

    public static boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws MathException, IllegalArgumentException {
        return chiSquareTest.chiSquareTest(expected, observed, alpha);
    }

    public static double chiSquareTest(double[] expected, long[] observed) throws MathException, IllegalArgumentException {
        return chiSquareTest.chiSquareTest(expected, observed);
    }

    public static boolean chiSquareTest(long[][] counts, double alpha) throws MathException, IllegalArgumentException {
        return chiSquareTest.chiSquareTest(counts, alpha);
    }

    public static double chiSquareTest(long[][] counts) throws MathException, IllegalArgumentException {
        return chiSquareTest.chiSquareTest(counts);
    }

    public static double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws IllegalArgumentException {
        return unknownDistributionChiSquareTest.chiSquareDataSetsComparison(observed1, observed2);
    }

    public static double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws MathException, IllegalArgumentException {
        return unknownDistributionChiSquareTest.chiSquareTestDataSetsComparison(observed1, observed2);
    }

    public static boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws MathException, IllegalArgumentException {
        return unknownDistributionChiSquareTest.chiSquareTestDataSetsComparison(observed1, observed2, alpha);
    }

    public static double oneWayAnovaFValue(Collection<double[]> categoryData) throws MathException, IllegalArgumentException {
        return oneWayAnova.anovaFValue(categoryData);
    }

    public static double oneWayAnovaPValue(Collection<double[]> categoryData) throws MathException, IllegalArgumentException {
        return oneWayAnova.anovaPValue(categoryData);
    }

    public static boolean oneWayAnovaTest(Collection<double[]> categoryData, double alpha) throws MathException, IllegalArgumentException {
        return oneWayAnova.anovaTest(categoryData, alpha);
    }
}
