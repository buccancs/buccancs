package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
class FourthMoment extends ThirdMoment implements Serializable {
    private static final long serialVersionUID = 4763990447117157611L;
    private double m4;

    FourthMoment() {
        this.m4 = Double.NaN;
    }

    FourthMoment(FourthMoment fourthMoment) throws NullArgumentException {
        copy(fourthMoment, this);
    }

    public static void copy(FourthMoment fourthMoment, FourthMoment fourthMoment2) throws NullArgumentException {
        MathUtils.checkNotNull(fourthMoment);
        MathUtils.checkNotNull(fourthMoment2);
        ThirdMoment.copy((ThirdMoment) fourthMoment, (ThirdMoment) fourthMoment2);
        fourthMoment2.m4 = fourthMoment.m4;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.ThirdMoment, org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.m4;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.ThirdMoment, org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.n < 1) {
            this.m4 = 0.0d;
            this.m3 = 0.0d;
            this.m2 = 0.0d;
            this.m1 = 0.0d;
        }
        double d2 = this.m3;
        double d3 = this.m2;
        super.increment(d);
        double d4 = this.n;
        double d5 = (this.m4 - ((this.nDev * 4.0d) * d2)) + (this.nDevSq * 6.0d * d3);
        double d6 = d4 - 1.0d;
        this.m4 = d5 + (((d4 * d4) - (3.0d * d6)) * this.nDevSq * this.nDevSq * d6 * d4);
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.ThirdMoment, org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        super.clear();
        this.m4 = Double.NaN;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.ThirdMoment, org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public FourthMoment copy() throws NullArgumentException {
        FourthMoment fourthMoment = new FourthMoment();
        copy(this, fourthMoment);
        return fourthMoment;
    }
}
