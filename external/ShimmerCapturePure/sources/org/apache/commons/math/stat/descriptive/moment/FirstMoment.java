package org.apache.commons.math.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/moment/FirstMoment.class */
public class FirstMoment extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 6112755307178490473L;
    protected long n;
    protected double m1;
    protected double dev;
    protected double nDev;

    public FirstMoment() {
        this.n = 0L;
        this.m1 = Double.NaN;
        this.dev = Double.NaN;
        this.nDev = Double.NaN;
    }

    public FirstMoment(FirstMoment original) {
        copy(original, this);
    }

    public static void copy(FirstMoment source, FirstMoment dest) {
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.m1 = source.m1;
        dest.dev = source.dev;
        dest.nDev = source.nDev;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.n == 0) {
            this.m1 = 0.0d;
        }
        this.n++;
        double n0 = this.n;
        this.dev = d - this.m1;
        this.nDev = this.dev / n0;
        this.m1 += this.nDev;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.m1 = Double.NaN;
        this.n = 0L;
        this.dev = Double.NaN;
        this.nDev = Double.NaN;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.m1;
    }

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.n;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public FirstMoment copy() {
        FirstMoment result = new FirstMoment();
        copy(this, result);
        return result;
    }
}
