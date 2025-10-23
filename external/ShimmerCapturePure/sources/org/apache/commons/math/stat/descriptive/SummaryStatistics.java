package org.apache.commons.math.stat.descriptive;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math.stat.descriptive.moment.Variance;
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
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/SummaryStatistics.class */
public class SummaryStatistics implements StatisticalSummary, Serializable {
    private static final long serialVersionUID = -2021321786743555871L;
    protected long n = 0;
    protected SecondMoment secondMoment = new SecondMoment();
    protected Sum sum = new Sum();
    protected SumOfSquares sumsq = new SumOfSquares();
    protected Min min = new Min();
    protected Max max = new Max();
    protected SumOfLogs sumLog = new SumOfLogs();
    protected GeometricMean geoMean = new GeometricMean(this.sumLog);
    private StorelessUnivariateStatistic geoMeanImpl = this.geoMean;
    protected Mean mean = new Mean();
    protected Variance variance = new Variance();
    private StorelessUnivariateStatistic sumImpl = this.sum;
    private StorelessUnivariateStatistic sumsqImpl = this.sumsq;
    private StorelessUnivariateStatistic minImpl = this.min;
    private StorelessUnivariateStatistic maxImpl = this.max;
    private StorelessUnivariateStatistic sumLogImpl = this.sumLog;
    private StorelessUnivariateStatistic meanImpl = this.mean;
    private StorelessUnivariateStatistic varianceImpl = this.variance;

    public SummaryStatistics() {
    }

    public SummaryStatistics(SummaryStatistics original) {
        copy(original, this);
    }

    public static void copy(SummaryStatistics source, SummaryStatistics dest) {
        dest.maxImpl = source.maxImpl.copy();
        dest.meanImpl = source.meanImpl.copy();
        dest.minImpl = source.minImpl.copy();
        dest.sumImpl = source.sumImpl.copy();
        dest.varianceImpl = source.varianceImpl.copy();
        dest.sumLogImpl = source.sumLogImpl.copy();
        dest.sumsqImpl = source.sumsqImpl.copy();
        if (source.getGeoMeanImpl() instanceof GeometricMean) {
            dest.geoMeanImpl = new GeometricMean((SumOfLogs) dest.sumLogImpl);
        } else {
            dest.geoMeanImpl = source.geoMeanImpl.copy();
        }
        SecondMoment.copy(source.secondMoment, dest.secondMoment);
        dest.n = source.n;
        if (source.geoMean == source.geoMeanImpl) {
            dest.geoMean = (GeometricMean) dest.geoMeanImpl;
        } else {
            GeometricMean.copy(source.geoMean, dest.geoMean);
        }
        if (source.max == source.maxImpl) {
            dest.max = (Max) dest.maxImpl;
        } else {
            Max.copy(source.max, dest.max);
        }
        if (source.mean == source.meanImpl) {
            dest.mean = (Mean) dest.meanImpl;
        } else {
            Mean.copy(source.mean, dest.mean);
        }
        if (source.min == source.minImpl) {
            dest.min = (Min) dest.minImpl;
        } else {
            Min.copy(source.min, dest.min);
        }
        if (source.sum == source.sumImpl) {
            dest.sum = (Sum) dest.sumImpl;
        } else {
            Sum.copy(source.sum, dest.sum);
        }
        if (source.variance == source.varianceImpl) {
            dest.variance = (Variance) dest.varianceImpl;
        } else {
            Variance.copy(source.variance, dest.variance);
        }
        if (source.sumLog == source.sumLogImpl) {
            dest.sumLog = (SumOfLogs) dest.sumLogImpl;
        } else {
            SumOfLogs.copy(source.sumLog, dest.sumLog);
        }
        if (source.sumsq == source.sumsqImpl) {
            dest.sumsq = (SumOfSquares) dest.sumsqImpl;
        } else {
            SumOfSquares.copy(source.sumsq, dest.sumsq);
        }
    }

    public StatisticalSummary getSummary() {
        return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
    }

    public void addValue(double value) {
        this.sumImpl.increment(value);
        this.sumsqImpl.increment(value);
        this.minImpl.increment(value);
        this.maxImpl.increment(value);
        this.sumLogImpl.increment(value);
        this.secondMoment.increment(value);
        if (!(this.meanImpl instanceof Mean)) {
            this.meanImpl.increment(value);
        }
        if (!(this.varianceImpl instanceof Variance)) {
            this.varianceImpl.increment(value);
        }
        if (!(this.geoMeanImpl instanceof GeometricMean)) {
            this.geoMeanImpl.increment(value);
        }
        this.n++;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public long getN() {
        return this.n;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public double getSum() {
        return this.sumImpl.getResult();
    }

    public double getSumsq() {
        return this.sumsqImpl.getResult();
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public double getMean() {
        if (this.mean == this.meanImpl) {
            return new Mean(this.secondMoment).getResult();
        }
        return this.meanImpl.getResult();
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public double getStandardDeviation() {
        double stdDev = Double.NaN;
        if (getN() > 0) {
            if (getN() > 1) {
                stdDev = FastMath.sqrt(getVariance());
            } else {
                stdDev = 0.0d;
            }
        }
        return stdDev;
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public double getVariance() {
        if (this.varianceImpl == this.variance) {
            return new Variance(this.secondMoment).getResult();
        }
        return this.varianceImpl.getResult();
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public double getMax() {
        return this.maxImpl.getResult();
    }

    @Override // org.apache.commons.math.stat.descriptive.StatisticalSummary
    public double getMin() {
        return this.minImpl.getResult();
    }

    public double getGeometricMean() {
        return this.geoMeanImpl.getResult();
    }

    public double getSumOfLogs() {
        return this.sumLogImpl.getResult();
    }

    public double getSecondMoment() {
        return this.secondMoment.getResult();
    }

    public String toString() {
        StringBuilder outBuffer = new StringBuilder();
        outBuffer.append("SummaryStatistics:").append(StringUtils.LF);
        outBuffer.append("n: ").append(getN()).append(StringUtils.LF);
        outBuffer.append("min: ").append(getMin()).append(StringUtils.LF);
        outBuffer.append("max: ").append(getMax()).append(StringUtils.LF);
        outBuffer.append("mean: ").append(getMean()).append(StringUtils.LF);
        outBuffer.append("geometric mean: ").append(getGeometricMean()).append(StringUtils.LF);
        outBuffer.append("variance: ").append(getVariance()).append(StringUtils.LF);
        outBuffer.append("sum of squares: ").append(getSumsq()).append(StringUtils.LF);
        outBuffer.append("standard deviation: ").append(getStandardDeviation()).append(StringUtils.LF);
        outBuffer.append("sum of logs: ").append(getSumOfLogs()).append(StringUtils.LF);
        return outBuffer.toString();
    }

    public void clear() {
        this.n = 0L;
        this.minImpl.clear();
        this.maxImpl.clear();
        this.sumImpl.clear();
        this.sumLogImpl.clear();
        this.sumsqImpl.clear();
        this.geoMeanImpl.clear();
        this.secondMoment.clear();
        if (this.meanImpl != this.mean) {
            this.meanImpl.clear();
        }
        if (this.varianceImpl != this.variance) {
            this.varianceImpl.clear();
        }
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SummaryStatistics)) {
            return false;
        }
        SummaryStatistics stat = (SummaryStatistics) object;
        return MathUtils.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathUtils.equalsIncludingNaN(stat.getMax(), getMax()) && MathUtils.equalsIncludingNaN(stat.getMean(), getMean()) && MathUtils.equalsIncludingNaN(stat.getMin(), getMin()) && MathUtils.equalsIncludingNaN((float) stat.getN(), (float) getN()) && MathUtils.equalsIncludingNaN(stat.getSum(), getSum()) && MathUtils.equalsIncludingNaN(stat.getSumsq(), getSumsq()) && MathUtils.equalsIncludingNaN(stat.getVariance(), getVariance());
    }

    public int hashCode() {
        int result = 31 + MathUtils.hash(getGeometricMean());
        return (((((((((((((((result * 31) + MathUtils.hash(getGeometricMean())) * 31) + MathUtils.hash(getMax())) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash(getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getSumsq())) * 31) + MathUtils.hash(getVariance());
    }

    public StorelessUnivariateStatistic getSumImpl() {
        return this.sumImpl;
    }

    public void setSumImpl(StorelessUnivariateStatistic sumImpl) {
        checkEmpty();
        this.sumImpl = sumImpl;
    }

    public StorelessUnivariateStatistic getSumsqImpl() {
        return this.sumsqImpl;
    }

    public void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) {
        checkEmpty();
        this.sumsqImpl = sumsqImpl;
    }

    public StorelessUnivariateStatistic getMinImpl() {
        return this.minImpl;
    }

    public void setMinImpl(StorelessUnivariateStatistic minImpl) {
        checkEmpty();
        this.minImpl = minImpl;
    }

    public StorelessUnivariateStatistic getMaxImpl() {
        return this.maxImpl;
    }

    public void setMaxImpl(StorelessUnivariateStatistic maxImpl) {
        checkEmpty();
        this.maxImpl = maxImpl;
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumLogImpl;
    }

    public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) {
        checkEmpty();
        this.sumLogImpl = sumLogImpl;
        this.geoMean.setSumLogImpl(sumLogImpl);
    }

    public StorelessUnivariateStatistic getGeoMeanImpl() {
        return this.geoMeanImpl;
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) {
        checkEmpty();
        this.geoMeanImpl = geoMeanImpl;
    }

    public StorelessUnivariateStatistic getMeanImpl() {
        return this.meanImpl;
    }

    public void setMeanImpl(StorelessUnivariateStatistic meanImpl) {
        checkEmpty();
        this.meanImpl = meanImpl;
    }

    public StorelessUnivariateStatistic getVarianceImpl() {
        return this.varianceImpl;
    }

    public void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) {
        checkEmpty();
        this.varianceImpl = varianceImpl;
    }

    private void checkEmpty() {
        if (this.n > 0) {
            throw MathRuntimeException.createIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(this.n));
        }
    }

    public SummaryStatistics copy() {
        SummaryStatistics result = new SummaryStatistics();
        copy(this, result);
        return result;
    }
}
