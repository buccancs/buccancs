package org.apache.commons.math.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/moment/StandardDeviation.class */
public class StandardDeviation extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 5728716329662425188L;
    private Variance variance;

    public StandardDeviation() {
        this.variance = null;
        this.variance = new Variance();
    }

    public StandardDeviation(SecondMoment m2) {
        this.variance = null;
        this.variance = new Variance(m2);
    }

    public StandardDeviation(StandardDeviation original) {
        this.variance = null;
        copy(original, this);
    }

    public StandardDeviation(boolean isBiasCorrected) {
        this.variance = null;
        this.variance = new Variance(isBiasCorrected);
    }

    public StandardDeviation(boolean isBiasCorrected, SecondMoment m2) {
        this.variance = null;
        this.variance = new Variance(isBiasCorrected, m2);
    }

    public static void copy(StandardDeviation source, StandardDeviation dest) {
        dest.setData(source.getDataRef());
        dest.variance = source.variance.copy();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        this.variance.increment(d);
    }

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.variance.getN();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return FastMath.sqrt(this.variance.getResult());
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.variance.clear();
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic
    public double evaluate(double[] values) {
        return FastMath.sqrt(this.variance.evaluate(values));
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic
    public double evaluate(double[] values, int begin, int length) {
        return FastMath.sqrt(this.variance.evaluate(values, begin, length));
    }

    public double evaluate(double[] values, double mean, int begin, int length) {
        return FastMath.sqrt(this.variance.evaluate(values, mean, begin, length));
    }

    public double evaluate(double[] values, double mean) {
        return FastMath.sqrt(this.variance.evaluate(values, mean));
    }

    public boolean isBiasCorrected() {
        return this.variance.isBiasCorrected();
    }

    public void setBiasCorrected(boolean isBiasCorrected) {
        this.variance.setBiasCorrected(isBiasCorrected);
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public StandardDeviation copy() {
        StandardDeviation result = new StandardDeviation();
        copy(this, result);
        return result;
    }
}
