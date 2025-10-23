package org.apache.commons.math.stat.descriptive;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/SynchronizedSummaryStatistics.class */
public class SynchronizedSummaryStatistics extends SummaryStatistics {
    private static final long serialVersionUID = 1909861009042253704L;

    public SynchronizedSummaryStatistics() {
    }

    public SynchronizedSummaryStatistics(SynchronizedSummaryStatistics original) {
        copy(original, this);
    }

    public static void copy(SynchronizedSummaryStatistics source, SynchronizedSummaryStatistics dest) {
        synchronized (source) {
            synchronized (dest) {
                SummaryStatistics.copy(source, dest);
            }
        }
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StatisticalSummary getSummary() {
        return super.getSummary();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void addValue(double value) {
        super.addValue(value);
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized long getN() {
        return super.getN();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized double getSum() {
        return super.getSum();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized double getSumsq() {
        return super.getSumsq();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized double getMean() {
        return super.getMean();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized double getStandardDeviation() {
        return super.getStandardDeviation();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized double getVariance() {
        return super.getVariance();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized double getMax() {
        return super.getMax();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.SummaryStatistics, org.apache.commons.math.stat.descriptive.StatisticalSummary
    public synchronized double getMin() {
        return super.getMin();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized double getGeometricMean() {
        return super.getGeometricMean();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized String toString() {
        return super.toString();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void clear() {
        super.clear();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized boolean equals(Object object) {
        return super.equals(object);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized int hashCode() {
        return super.hashCode();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getSumImpl() {
        return super.getSumImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setSumImpl(StorelessUnivariateStatistic sumImpl) {
        super.setSumImpl(sumImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getSumsqImpl() {
        return super.getSumsqImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) {
        super.setSumsqImpl(sumsqImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getMinImpl() {
        return super.getMinImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setMinImpl(StorelessUnivariateStatistic minImpl) {
        super.setMinImpl(minImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getMaxImpl() {
        return super.getMaxImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setMaxImpl(StorelessUnivariateStatistic maxImpl) {
        super.setMaxImpl(maxImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getSumLogImpl() {
        return super.getSumLogImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) {
        super.setSumLogImpl(sumLogImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getGeoMeanImpl() {
        return super.getGeoMeanImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) {
        super.setGeoMeanImpl(geoMeanImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getMeanImpl() {
        return super.getMeanImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setMeanImpl(StorelessUnivariateStatistic meanImpl) {
        super.setMeanImpl(meanImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized StorelessUnivariateStatistic getVarianceImpl() {
        return super.getVarianceImpl();
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) {
        super.setVarianceImpl(varianceImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.SummaryStatistics
    public synchronized SynchronizedSummaryStatistics copy() {
        SynchronizedSummaryStatistics result = new SynchronizedSummaryStatistics();
        copy(this, result);
        return result;
    }
}
