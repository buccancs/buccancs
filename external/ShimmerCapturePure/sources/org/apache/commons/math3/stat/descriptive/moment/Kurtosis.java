package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 2784465764798260919L;
    protected boolean incMoment;
    protected FourthMoment moment;

    public Kurtosis() {
        this.incMoment = true;
        this.moment = new FourthMoment();
    }

    public Kurtosis(FourthMoment fourthMoment) {
        this.incMoment = false;
        this.moment = fourthMoment;
    }

    public Kurtosis(Kurtosis kurtosis) throws NullArgumentException {
        copy(kurtosis, this);
    }

    public static void copy(Kurtosis kurtosis, Kurtosis kurtosis2) throws NullArgumentException {
        MathUtils.checkNotNull(kurtosis);
        MathUtils.checkNotNull(kurtosis2);
        kurtosis2.setData(kurtosis.getDataRef());
        kurtosis2.moment = kurtosis.moment.copy();
        kurtosis2.incMoment = kurtosis.incMoment;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        if (this.moment.getN() <= 3) {
            return Double.NaN;
        }
        double d = this.moment.m2 / (this.moment.n - 1);
        if (this.moment.n <= 3 || d < 1.0E-19d) {
            return 0.0d;
        }
        double d2 = this.moment.n;
        double d3 = d2 - 1.0d;
        return ((((d2 + 1.0d) * d2) * this.moment.getResult()) - (((this.moment.m2 * 3.0d) * this.moment.m2) * d3)) / ((((d3 * (d2 - 2.0d)) * (d2 - 3.0d)) * d) * d);
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.moment.getN();
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2) || i2 <= 3) {
            return Double.NaN;
        }
        Variance variance = new Variance();
        variance.incrementAll(dArr, i, i2);
        double d = variance.moment.m1;
        double dSqrt = FastMath.sqrt(variance.getResult());
        double dPow = 0.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            dPow += FastMath.pow(dArr[i3] - d, 4.0d);
        }
        double d2 = i2;
        double d3 = (d2 + 1.0d) * d2;
        double d4 = d2 - 1.0d;
        double d5 = d2 - 2.0d;
        double d6 = d2 - 3.0d;
        return ((d3 / ((d4 * d5) * d6)) * (dPow / FastMath.pow(dSqrt, 4.0d))) - ((FastMath.pow(d4, 2.0d) * 3.0d) / (d5 * d6));
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public Kurtosis copy() throws NullArgumentException {
        Kurtosis kurtosis = new Kurtosis();
        copy(this, kurtosis);
        return kurtosis;
    }
}
