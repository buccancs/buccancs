package org.apache.commons.math.stat.descriptive;

import org.apache.commons.math.exception.NullArgumentException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/AbstractStorelessUnivariateStatistic.class */
public abstract class AbstractStorelessUnivariateStatistic extends AbstractUnivariateStatistic implements StorelessUnivariateStatistic {
    @Override
    // org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic, org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public abstract StorelessUnivariateStatistic copy();

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public abstract void clear();

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public abstract double getResult();

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public abstract void increment(double d);

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic
    public double evaluate(double[] values) {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        return evaluate(values, 0, values.length);
    }

    @Override
    // org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math.stat.descriptive.UnivariateStatistic
    public double evaluate(double[] values, int begin, int length) {
        if (test(values, begin, length)) {
            clear();
            incrementAll(values, begin, length);
        }
        return getResult();
    }

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void incrementAll(double[] values) {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        incrementAll(values, 0, values.length);
    }

    @Override // org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic
    public void incrementAll(double[] values, int begin, int length) {
        if (test(values, begin, length)) {
            int k = begin + length;
            for (int i = begin; i < k; i++) {
                increment(values[i]);
            }
        }
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof AbstractStorelessUnivariateStatistic)) {
            return false;
        }
        AbstractStorelessUnivariateStatistic stat = (AbstractStorelessUnivariateStatistic) object;
        return MathUtils.equalsIncludingNaN(stat.getResult(), getResult()) && MathUtils.equalsIncludingNaN((float) stat.getN(), (float) getN());
    }

    public int hashCode() {
        return (31 * (31 + MathUtils.hash(getResult()))) + MathUtils.hash(getN());
    }
}
