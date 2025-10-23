package org.apache.commons.math.distribution;

import java.io.Serializable;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/ChiSquaredDistributionImpl.class */
public class ChiSquaredDistributionImpl extends AbstractContinuousDistribution implements ChiSquaredDistribution, Serializable {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -8352658048349159782L;
    private final double solverAbsoluteAccuracy;
    private GammaDistribution gamma;

    public ChiSquaredDistributionImpl(double df) {
        this(df, new GammaDistributionImpl(df / 2.0d, 2.0d));
    }

    @Deprecated
    public ChiSquaredDistributionImpl(double df, GammaDistribution g) {
        setGammaInternal(g);
        setDegreesOfFreedomInternal(df);
        this.solverAbsoluteAccuracy = 1.0E-9d;
    }

    public ChiSquaredDistributionImpl(double df, double inverseCumAccuracy) {
        this.gamma = new GammaDistributionImpl(df / 2.0d, 2.0d);
        setDegreesOfFreedomInternal(df);
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    private void setDegreesOfFreedomInternal(double degreesOfFreedom) {
        this.gamma.setAlpha(degreesOfFreedom / 2.0d);
    }

    @Override // org.apache.commons.math.distribution.ChiSquaredDistribution
    public double getDegreesOfFreedom() {
        return this.gamma.getAlpha() * 2.0d;
    }

    @Override // org.apache.commons.math.distribution.ChiSquaredDistribution
    @Deprecated
    public void setDegreesOfFreedom(double degreesOfFreedom) {
        setDegreesOfFreedomInternal(degreesOfFreedom);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.math.distribution.HasDensity
    @Deprecated
    public double density(Double x) {
        return density(x.doubleValue());
    }

    @Override // org.apache.commons.math.distribution.AbstractContinuousDistribution
    public double density(double x) {
        return this.gamma.density(Double.valueOf(x));
    }

    @Override // org.apache.commons.math.distribution.Distribution
    public double cumulativeProbability(double x) throws MathException {
        return this.gamma.cumulativeProbability(x);
    }

    @Override
    // org.apache.commons.math.distribution.AbstractContinuousDistribution, org.apache.commons.math.distribution.ContinuousDistribution
    public double inverseCumulativeProbability(double p) throws MathException {
        if (p == 0.0d) {
            return 0.0d;
        }
        if (p == 1.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return super.inverseCumulativeProbability(p);
    }

    @Override // org.apache.commons.math.distribution.AbstractContinuousDistribution
    protected double getDomainLowerBound(double p) {
        return Double.MIN_VALUE * this.gamma.getBeta();
    }

    @Override // org.apache.commons.math.distribution.AbstractContinuousDistribution
    protected double getDomainUpperBound(double p) {
        double ret;
        if (p < 0.5d) {
            ret = getDegreesOfFreedom();
        } else {
            ret = Double.MAX_VALUE;
        }
        return ret;
    }

    @Override // org.apache.commons.math.distribution.AbstractContinuousDistribution
    protected double getInitialDomain(double p) {
        double ret;
        if (p < 0.5d) {
            ret = getDegreesOfFreedom() * 0.5d;
        } else {
            ret = getDegreesOfFreedom();
        }
        return ret;
    }

    @Deprecated
    public void setGamma(GammaDistribution g) {
        setGammaInternal(g);
    }

    private void setGammaInternal(GammaDistribution g) {
        this.gamma = g;
    }

    @Override // org.apache.commons.math.distribution.AbstractContinuousDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getSupportLowerBound() {
        return 0.0d;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public double getNumericalMean() {
        return getDegreesOfFreedom();
    }

    public double getNumericalVariance() {
        return 2.0d * getDegreesOfFreedom();
    }
}
