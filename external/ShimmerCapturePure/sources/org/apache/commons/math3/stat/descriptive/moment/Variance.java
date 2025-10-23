package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class Variance extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
    private static final long serialVersionUID = -9111962718267217978L;
    protected boolean incMoment;
    protected SecondMoment moment;
    private boolean isBiasCorrected;

    public Variance() {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        this.moment = new SecondMoment();
    }

    public Variance(SecondMoment secondMoment) {
        this.isBiasCorrected = true;
        this.incMoment = false;
        this.moment = secondMoment;
    }

    public Variance(boolean z) {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        this.moment = new SecondMoment();
        this.isBiasCorrected = z;
    }

    public Variance(boolean z, SecondMoment secondMoment) {
        this.incMoment = false;
        this.moment = secondMoment;
        this.isBiasCorrected = z;
    }

    public Variance(Variance variance) throws NullArgumentException {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        copy(variance, this);
    }

    public static void copy(Variance variance, Variance variance2) throws NullArgumentException {
        MathUtils.checkNotNull(variance);
        MathUtils.checkNotNull(variance2);
        variance2.setData(variance.getDataRef());
        variance2.moment = variance.moment.copy();
        variance2.isBiasCorrected = variance.isBiasCorrected;
        variance2.incMoment = variance.incMoment;
    }

    public boolean isBiasCorrected() {
        return this.isBiasCorrected;
    }

    public void setBiasCorrected(boolean z) {
        this.isBiasCorrected = z;
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
        double d;
        double d2;
        if (this.moment.n == 0) {
            return Double.NaN;
        }
        if (this.moment.n == 1) {
            return 0.0d;
        }
        if (this.isBiasCorrected) {
            d = this.moment.m2;
            d2 = this.moment.n - 1.0d;
        } else {
            d = this.moment.m2;
            d2 = this.moment.n;
        }
        return d / d2;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.moment.getN();
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] dArr) throws MathIllegalArgumentException {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        return evaluate(dArr, 0, dArr.length);
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (test(dArr, i, i2)) {
            clear();
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                return evaluate(dArr, new Mean().evaluate(dArr, i, i2), i, i2);
            }
        }
        return Double.NaN;
    }

    @Override // org.apache.commons.math3.stat.descriptive.WeightedEvaluation
    public double evaluate(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        if (test(dArr, dArr2, i, i2)) {
            clear();
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                return evaluate(dArr, dArr2, new Mean().evaluate(dArr, dArr2, i, i2), i, i2);
            }
        }
        return Double.NaN;
    }

    @Override // org.apache.commons.math3.stat.descriptive.WeightedEvaluation
    public double evaluate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double d, int i, int i2) throws MathIllegalArgumentException {
        double d2;
        if (test(dArr, i, i2)) {
            double d3 = 0.0d;
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                double d4 = 0.0d;
                for (int i3 = i; i3 < i + i2; i3++) {
                    double d5 = dArr[i3] - d;
                    d3 += d5 * d5;
                    d4 += d5;
                }
                double d6 = i2;
                if (this.isBiasCorrected) {
                    d2 = d3 - ((d4 * d4) / d6);
                    d6 -= 1.0d;
                } else {
                    d2 = d3 - ((d4 * d4) / d6);
                }
                return d2 / d6;
            }
        }
        return Double.NaN;
    }

    public double evaluate(double[] dArr, double d) throws MathIllegalArgumentException {
        return evaluate(dArr, d, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double[] dArr2, double d, int i, int i2) throws MathIllegalArgumentException {
        int i3;
        double d2;
        int i4 = i;
        if (test(dArr, dArr2, i4, i2)) {
            double d3 = 0.0d;
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                int i5 = i4;
                double d4 = 0.0d;
                double d5 = 0.0d;
                while (true) {
                    i3 = i4 + i2;
                    if (i5 >= i3) {
                        break;
                    }
                    double d6 = dArr[i5] - d;
                    double d7 = dArr2[i5];
                    d4 += d6 * d6 * d7;
                    d5 += d7 * d6;
                    i5++;
                }
                while (i4 < i3) {
                    d3 += dArr2[i4];
                    i4++;
                }
                if (this.isBiasCorrected) {
                    d2 = d4 - ((d5 * d5) / d3);
                    d3 -= 1.0d;
                } else {
                    d2 = d4 - ((d5 * d5) / d3);
                }
                return d2 / d3;
            }
        }
        return Double.NaN;
    }

    public double evaluate(double[] dArr, double[] dArr2, double d) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, d, 0, dArr.length);
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public Variance copy() throws NullArgumentException {
        Variance variance = new Variance();
        copy(this, variance);
        return variance;
    }
}
