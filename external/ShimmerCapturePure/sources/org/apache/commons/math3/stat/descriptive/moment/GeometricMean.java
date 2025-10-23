package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class GeometricMean extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -8178734905303459453L;
    private StorelessUnivariateStatistic sumOfLogs;

    public GeometricMean() {
        this.sumOfLogs = new SumOfLogs();
    }

    public GeometricMean(GeometricMean geometricMean) throws NullArgumentException {
        copy(geometricMean, this);
    }

    public GeometricMean(SumOfLogs sumOfLogs) {
        this.sumOfLogs = sumOfLogs;
    }

    public static void copy(GeometricMean geometricMean, GeometricMean geometricMean2) throws NullArgumentException {
        MathUtils.checkNotNull(geometricMean);
        MathUtils.checkNotNull(geometricMean2);
        geometricMean2.setData(geometricMean.getDataRef());
        geometricMean2.sumOfLogs = geometricMean.sumOfLogs.copy();
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumOfLogs;
    }

    public void setSumLogImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumOfLogs = storelessUnivariateStatistic;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public GeometricMean copy() throws NullArgumentException {
        GeometricMean geometricMean = new GeometricMean();
        copy(this, geometricMean);
        return geometricMean;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        this.sumOfLogs.increment(d);
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        if (this.sumOfLogs.getN() > 0) {
            return FastMath.exp(this.sumOfLogs.getResult() / this.sumOfLogs.getN());
        }
        return Double.NaN;
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.sumOfLogs.clear();
    }

    @Override
    // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return FastMath.exp(this.sumOfLogs.evaluate(dArr, i, i2) / i2);
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.sumOfLogs.getN();
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (getN() > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(getN()));
        }
    }
}
