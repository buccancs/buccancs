package org.apache.commons.math3.distribution;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

/* loaded from: classes5.dex */
public class ChiSquaredDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -8352658048349159782L;
    private final GammaDistribution gamma;
    private final double solverAbsoluteAccuracy;

    public ChiSquaredDistribution(double d) {
        this(d, 1.0E-9d);
    }

    public ChiSquaredDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public ChiSquaredDistribution(RandomGenerator randomGenerator, double d) {
        this(randomGenerator, d, 1.0E-9d);
    }

    public ChiSquaredDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        this.gamma = new GammaDistribution(d / 2.0d, 2.0d);
        this.solverAbsoluteAccuracy = d2;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return 0.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportConnected() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public double getDegreesOfFreedom() {
        return this.gamma.getShape() * 2.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        return this.gamma.density(d);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double d) {
        return this.gamma.logDensity(d);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        return this.gamma.cumulativeProbability(d);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return getDegreesOfFreedom();
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        return getDegreesOfFreedom() * 2.0d;
    }
}
