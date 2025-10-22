package org.apache.commons.math.stat.descriptive;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math.DimensionMismatchException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.MatrixIndexException;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.VectorialCovariance;
import org.apache.commons.math.stat.descriptive.rank.Max;
import org.apache.commons.math.stat.descriptive.rank.Min;
import org.apache.commons.math.stat.descriptive.summary.Sum;
import org.apache.commons.math.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/MultivariateSummaryStatistics.class */
public class MultivariateSummaryStatistics implements StatisticalMultivariateSummary, Serializable {
    private static final long serialVersionUID = 2271900808994826718L;
    private int k;
    private long n = 0;
    private StorelessUnivariateStatistic[] sumImpl;
    private StorelessUnivariateStatistic[] sumSqImpl;
    private StorelessUnivariateStatistic[] minImpl;
    private StorelessUnivariateStatistic[] maxImpl;
    private StorelessUnivariateStatistic[] sumLogImpl;
    private StorelessUnivariateStatistic[] geoMeanImpl;
    private StorelessUnivariateStatistic[] meanImpl;
    private VectorialCovariance covarianceImpl;

    public MultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
        this.k = k;
        this.sumImpl = new StorelessUnivariateStatistic[k];
        this.sumSqImpl = new StorelessUnivariateStatistic[k];
        this.minImpl = new StorelessUnivariateStatistic[k];
        this.maxImpl = new StorelessUnivariateStatistic[k];
        this.sumLogImpl = new StorelessUnivariateStatistic[k];
        this.geoMeanImpl = new StorelessUnivariateStatistic[k];
        this.meanImpl = new StorelessUnivariateStatistic[k];
        for (int i = 0; i < k; i++) {
            this.sumImpl[i] = new Sum();
            this.sumSqImpl[i] = new SumOfSquares();
            this.minImpl[i] = new Min();
            this.maxImpl[i] = new Max();
            this.sumLogImpl[i] = new SumOfLogs();
            this.geoMeanImpl[i] = new GeometricMean();
            this.meanImpl[i] = new Mean();
        }
        this.covarianceImpl = new VectorialCovariance(k, isCovarianceBiasCorrected);
    }

    public void addValue(double[] value) throws DimensionMismatchException {
        checkDimension(value.length);
        for (int i = 0; i < this.k; i++) {
            double v = value[i];
            this.sumImpl[i].increment(v);
            this.sumSqImpl[i].increment(v);
            this.minImpl[i].increment(v);
            this.maxImpl[i].increment(v);
            this.sumLogImpl[i].increment(v);
            this.geoMeanImpl[i].increment(v);
            this.meanImpl[i].increment(v);
        }
        this.covarianceImpl.increment(value);
        this.n++;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public int getDimension() {
        return this.k;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public long getN() {
        return this.n;
    }

    private double[] getResults(StorelessUnivariateStatistic[] stats) {
        double[] results = new double[stats.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = stats[i].getResult();
        }
        return results;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getSum() {
        return getResults(this.sumImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getSumSq() {
        return getResults(this.sumSqImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getSumLog() {
        return getResults(this.sumLogImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getMean() {
        return getResults(this.meanImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getStandardDeviation() throws MatrixIndexException {
        double[] stdDev = new double[this.k];
        if (getN() < 1) {
            Arrays.fill(stdDev, Double.NaN);
        } else if (getN() < 2) {
            Arrays.fill(stdDev, 0.0d);
        } else {
            RealMatrix matrix = this.covarianceImpl.getResult();
            for (int i = 0; i < this.k; i++) {
                stdDev[i] = FastMath.sqrt(matrix.getEntry(i, i));
            }
        }
        return stdDev;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public RealMatrix getCovariance() {
        return this.covarianceImpl.getResult();
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getMax() {
        return getResults(this.maxImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getMin() {
        return getResults(this.minImpl);
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalMultivariateSummary
    public double[] getGeometricMean() {
        return getResults(this.geoMeanImpl);
    }

    public String toString() {
        String suffix = System.getProperty("line.separator");
        StringBuilder outBuffer = new StringBuilder();
        outBuffer.append("MultivariateSummaryStatistics:" + suffix);
        outBuffer.append("n: " + getN() + suffix);
        append(outBuffer, getMin(), "min: ", ", ", suffix);
        append(outBuffer, getMax(), "max: ", ", ", suffix);
        append(outBuffer, getMean(), "mean: ", ", ", suffix);
        append(outBuffer, getGeometricMean(), "geometric mean: ", ", ", suffix);
        append(outBuffer, getSumSq(), "sum of squares: ", ", ", suffix);
        append(outBuffer, getSumLog(), "sum of logarithms: ", ", ", suffix);
        append(outBuffer, getStandardDeviation(), "standard deviation: ", ", ", suffix);
        outBuffer.append("covariance: " + getCovariance().toString() + suffix);
        return outBuffer.toString();
    }

    private void append(StringBuilder buffer, double[] data, String prefix, String separator, String suffix) {
        buffer.append(prefix);
        for (int i = 0; i < data.length; i++) {
            if (i > 0) {
                buffer.append(separator);
            }
            buffer.append(data[i]);
        }
        buffer.append(suffix);
    }

    public void clear() {
        this.n = 0L;
        for (int i = 0; i < this.k; i++) {
            this.minImpl[i].clear();
            this.maxImpl[i].clear();
            this.sumImpl[i].clear();
            this.sumLogImpl[i].clear();
            this.sumSqImpl[i].clear();
            this.geoMeanImpl[i].clear();
            this.meanImpl[i].clear();
        }
        this.covarianceImpl.clear();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof MultivariateSummaryStatistics)) {
            return false;
        }
        MultivariateSummaryStatistics stat = (MultivariateSummaryStatistics) object;
        return MathUtils.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathUtils.equalsIncludingNaN(stat.getMax(), getMax()) && MathUtils.equalsIncludingNaN(stat.getMean(), getMean()) && MathUtils.equalsIncludingNaN(stat.getMin(), getMin()) && MathUtils.equalsIncludingNaN((float) stat.getN(), (float) getN()) && MathUtils.equalsIncludingNaN(stat.getSum(), getSum()) && MathUtils.equalsIncludingNaN(stat.getSumSq(), getSumSq()) && MathUtils.equalsIncludingNaN(stat.getSumLog(), getSumLog()) && stat.getCovariance().equals(getCovariance());
    }

    public int hashCode() {
        int result = 31 + MathUtils.hash(getGeometricMean());
        return (((((((((((((((((result * 31) + MathUtils.hash(getGeometricMean())) * 31) + MathUtils.hash(getMax())) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash(getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getSumSq())) * 31) + MathUtils.hash(getSumLog())) * 31) + getCovariance().hashCode();
    }

    private void setImpl(StorelessUnivariateStatistic[] newImpl, StorelessUnivariateStatistic[] oldImpl) throws IllegalStateException, DimensionMismatchException {
        checkEmpty();
        checkDimension(newImpl.length);
        System.arraycopy(newImpl, 0, oldImpl, 0, newImpl.length);
    }

    public StorelessUnivariateStatistic[] getSumImpl() {
        return (StorelessUnivariateStatistic[]) this.sumImpl.clone();
    }

    public void setSumImpl(StorelessUnivariateStatistic[] sumImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(sumImpl, this.sumImpl);
    }

    public StorelessUnivariateStatistic[] getSumsqImpl() {
        return (StorelessUnivariateStatistic[]) this.sumSqImpl.clone();
    }

    public void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(sumsqImpl, this.sumSqImpl);
    }

    public StorelessUnivariateStatistic[] getMinImpl() {
        return (StorelessUnivariateStatistic[]) this.minImpl.clone();
    }

    public void setMinImpl(StorelessUnivariateStatistic[] minImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(minImpl, this.minImpl);
    }

    public StorelessUnivariateStatistic[] getMaxImpl() {
        return (StorelessUnivariateStatistic[]) this.maxImpl.clone();
    }

    public void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(maxImpl, this.maxImpl);
    }

    public StorelessUnivariateStatistic[] getSumLogImpl() {
        return (StorelessUnivariateStatistic[]) this.sumLogImpl.clone();
    }

    public void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(sumLogImpl, this.sumLogImpl);
    }

    public StorelessUnivariateStatistic[] getGeoMeanImpl() {
        return (StorelessUnivariateStatistic[]) this.geoMeanImpl.clone();
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(geoMeanImpl, this.geoMeanImpl);
    }

    public StorelessUnivariateStatistic[] getMeanImpl() {
        return (StorelessUnivariateStatistic[]) this.meanImpl.clone();
    }

    public void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) throws IllegalStateException, DimensionMismatchException {
        setImpl(meanImpl, this.meanImpl);
    }

    private void checkEmpty() {
        if (this.n > 0) {
            throw MathRuntimeException.createIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(this.n));
        }
    }

    private void checkDimension(int dimension) throws DimensionMismatchException {
        if (dimension != this.k) {
            throw new DimensionMismatchException(dimension, this.k);
        }
    }
}
