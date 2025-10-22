package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class NakagamiDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20141003;
    private final double inverseAbsoluteAccuracy;
    private final double mu;
    private final double omega;

    public NakagamiDistribution(double d, double d2) {
        this(d, d2, 1.0E-9d);
    }

    public NakagamiDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public NakagamiDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) {
        super(randomGenerator);
        if (d < 0.5d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(0.5d), true);
        }
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, Double.valueOf(d2));
        }
        this.mu = d;
        this.omega = d2;
        this.inverseAbsoluteAccuracy = d3;
    }

    public double getScale() {
        return this.omega;
    }

    public double getShape() {
        return this.mu;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.inverseAbsoluteAccuracy;
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

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double d2 = this.mu;
        return ((FastMath.pow(d2, d2) * 2.0d) / (Gamma.gamma(this.mu) * FastMath.pow(this.omega, this.mu))) * FastMath.pow(d, (this.mu * 2.0d) - 1.0d) * FastMath.exp((((-this.mu) * d) * d) / this.omega);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        double d2 = this.mu;
        return Gamma.regularizedGammaP(d2, ((d2 * d) * d) / this.omega);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return (Gamma.gamma(this.mu + 0.5d) / Gamma.gamma(this.mu)) * FastMath.sqrt(this.omega / this.mu);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double dGamma = Gamma.gamma(this.mu + 0.5d) / Gamma.gamma(this.mu);
        return this.omega * (1.0d - (((1.0d / this.mu) * dGamma) * dGamma));
    }
}
