package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class SecondMoment extends FirstMoment implements Serializable {
    private static final long serialVersionUID = 3942403127395076445L;
    protected double m2;

    public SecondMoment() {
        this.m2 = Double.NaN;
    }

    public SecondMoment(SecondMoment secondMoment) throws NullArgumentException {
        super(secondMoment);
        this.m2 = secondMoment.m2;
    }

    public static void copy(SecondMoment secondMoment, SecondMoment secondMoment2) throws NullArgumentException {
        MathUtils.checkNotNull(secondMoment);
        MathUtils.checkNotNull(secondMoment2);
        FirstMoment.copy(secondMoment, secondMoment2);
        secondMoment2.m2 = secondMoment.m2;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.m2;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public /* bridge */ /* synthetic */ long getN() {
        return super.getN();
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.n < 1) {
            this.m2 = 0.0d;
            this.m1 = 0.0d;
        }
        super.increment(d);
        this.m2 += (this.n - 1.0d) * this.dev * this.nDev;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        super.clear();
        this.m2 = Double.NaN;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public SecondMoment copy() throws NullArgumentException {
        SecondMoment secondMoment = new SecondMoment();
        copy(this, secondMoment);
        return secondMoment;
    }
}
