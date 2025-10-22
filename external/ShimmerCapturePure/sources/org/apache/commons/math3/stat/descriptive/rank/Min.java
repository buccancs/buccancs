package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class Min extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -2941995784909003131L;
    private long n;
    private double value;

    public Min() {
        this.n = 0L;
        this.value = Double.NaN;
    }

    public Min(Min min) throws NullArgumentException {
        copy(min, this);
    }

    public static void copy(Min min, Min min2) throws NullArgumentException {
        MathUtils.checkNotNull(min);
        MathUtils.checkNotNull(min2);
        min2.setData(min.getDataRef());
        min2.n = min.n;
        min2.value = min.value;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.value = Double.NaN;
        this.n = 0L;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.n;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.value;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        double d2 = this.value;
        if (d < d2 || Double.isNaN(d2)) {
            this.value = d;
        }
        this.n++;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2)) {
            return Double.NaN;
        }
        double d = dArr[i];
        for (int i3 = i; i3 < i + i2; i3++) {
            if (!Double.isNaN(dArr[i3])) {
                double d2 = dArr[i3];
                if (d >= d2) {
                    d = d2;
                }
            }
        }
        return d;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public Min copy() throws NullArgumentException {
        Min min = new Min();
        copy(this, min);
        return min;
    }
}
