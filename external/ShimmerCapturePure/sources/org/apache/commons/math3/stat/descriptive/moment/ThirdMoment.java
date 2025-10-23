package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
class ThirdMoment extends SecondMoment implements Serializable {
    private static final long serialVersionUID = -7818711964045118679L;
    protected double m3;
    protected double nDevSq;

    ThirdMoment() {
        this.m3 = Double.NaN;
        this.nDevSq = Double.NaN;
    }

    ThirdMoment(ThirdMoment thirdMoment) throws NullArgumentException {
        copy(thirdMoment, this);
    }

    public static void copy(ThirdMoment thirdMoment, ThirdMoment thirdMoment2) throws NullArgumentException {
        MathUtils.checkNotNull(thirdMoment);
        MathUtils.checkNotNull(thirdMoment2);
        SecondMoment.copy((SecondMoment) thirdMoment, (SecondMoment) thirdMoment2);
        thirdMoment2.m3 = thirdMoment.m3;
        thirdMoment2.nDevSq = thirdMoment.nDevSq;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.m3;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.n < 1) {
            this.m1 = 0.0d;
            this.m2 = 0.0d;
            this.m3 = 0.0d;
        }
        double d2 = this.m2;
        super.increment(d);
        this.nDevSq = this.nDev * this.nDev;
        double d3 = this.n;
        this.m3 = (this.m3 - ((this.nDev * 3.0d) * d2)) + ((d3 - 1.0d) * (d3 - 2.0d) * this.nDevSq * this.dev);
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        super.clear();
        this.m3 = Double.NaN;
        this.nDevSq = Double.NaN;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public ThirdMoment copy() throws NullArgumentException {
        ThirdMoment thirdMoment = new ThirdMoment();
        copy(this, thirdMoment);
        return thirdMoment;
    }
}
