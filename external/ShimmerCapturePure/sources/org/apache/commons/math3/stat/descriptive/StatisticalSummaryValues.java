package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class StatisticalSummaryValues implements Serializable, StatisticalSummary {
    private static final long serialVersionUID = -5108854841843722536L;
    private final double max;
    private final double mean;
    private final double min;
    private final long n;
    private final double sum;
    private final double variance;

    public StatisticalSummaryValues(double d, double d2, long j, double d3, double d4, double d5) {
        this.mean = d;
        this.variance = d2;
        this.n = j;
        this.max = d3;
        this.min = d4;
        this.sum = d5;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMax() {
        return this.max;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMean() {
        return this.mean;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMin() {
        return this.min;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public long getN() {
        return this.n;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getSum() {
        return this.sum;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getVariance() {
        return this.variance;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getStandardDeviation() {
        return FastMath.sqrt(this.variance);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StatisticalSummaryValues)) {
            return false;
        }
        StatisticalSummaryValues statisticalSummaryValues = (StatisticalSummaryValues) obj;
        return Precision.equalsIncludingNaN(statisticalSummaryValues.getMax(), getMax()) && Precision.equalsIncludingNaN(statisticalSummaryValues.getMean(), getMean()) && Precision.equalsIncludingNaN(statisticalSummaryValues.getMin(), getMin()) && Precision.equalsIncludingNaN((float) statisticalSummaryValues.getN(), (float) getN()) && Precision.equalsIncludingNaN(statisticalSummaryValues.getSum(), getSum()) && Precision.equalsIncludingNaN(statisticalSummaryValues.getVariance(), getVariance());
    }

    public int hashCode() {
        return ((((((((((MathUtils.hash(getMax()) + 31) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash(getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getVariance());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("StatisticalSummaryValues:\nn: ");
        stringBuffer.append(getN()).append("\nmin: ");
        stringBuffer.append(getMin()).append("\nmax: ");
        stringBuffer.append(getMax()).append("\nmean: ");
        stringBuffer.append(getMean()).append("\nstd dev: ");
        stringBuffer.append(getStandardDeviation()).append("\nvariance: ");
        stringBuffer.append(getVariance()).append("\nsum: ");
        stringBuffer.append(getSum()).append(StringUtils.LF);
        return stringBuffer.toString();
    }
}
