package org.apache.commons.math.stat.descriptive.rank;

import java.io.Serializable;

import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/rank/Max.class */
public class Max extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -5593383832225844641L;
    private long n;
    private double value;

    public Max() {
        this.n = 0L;
        this.value = Double.NaN;
    }

    public Max(Max original) {
        copy(original, this);
    }

    public static void copy(Max source, Max dest) {
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (d > this.value || Double.isNaN(this.value)) {
            this.value = d;
        }
        this.n++;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.value = Double.NaN;
        this.n = 0L;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.value;
    }

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.n;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic
    public double evaluate(double[] values, int begin, int length) {
        double max = Double.NaN;
        if (test(values, begin, length)) {
            max = values[begin];
            for (int i = begin; i < begin + length; i++) {
                if (!Double.isNaN(values[i])) {
                    max = max > values[i] ? max : values[i];
                }
            }
        }
        return max;
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public Max copy() {
        Max result = new Max();
        copy(this, result);
        return result;
    }
}
