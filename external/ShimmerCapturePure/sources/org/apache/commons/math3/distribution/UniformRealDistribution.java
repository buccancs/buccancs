package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

/* loaded from: classes5.dex */
public class UniformRealDistribution extends AbstractRealDistribution {

    @Deprecated
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20120109;
    private final double lower;
    private final double upper;

    public UniformRealDistribution() {
        this(0.0d, 1.0d);
    }

    public UniformRealDistribution(double d, double d2) throws NumberIsTooLargeException {
        this(new Well19937c(), d, d2);
    }

    @Deprecated
    public UniformRealDistribution(double d, double d2, double d3) throws NumberIsTooLargeException {
        this(new Well19937c(), d, d2);
    }

    @Deprecated
    public UniformRealDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) {
        this(randomGenerator, d, d2);
    }

    public UniformRealDistribution(RandomGenerator randomGenerator, double d, double d2) throws NumberIsTooLargeException {
        super(randomGenerator);
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d2), false);
        }
        this.lower = d;
        this.upper = d2;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        double d2 = this.lower;
        if (d <= d2) {
            return 0.0d;
        }
        double d3 = this.upper;
        if (d >= d3) {
            return 1.0d;
        }
        return (d - d2) / (d3 - d2);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        double d2 = this.lower;
        if (d < d2) {
            return 0.0d;
        }
        double d3 = this.upper;
        if (d > d3) {
            return 0.0d;
        }
        return 1.0d / (d3 - d2);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return (this.lower + this.upper) * 0.5d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double d = this.upper - this.lower;
        return (d * d) / 12.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return this.lower;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return this.upper;
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
        return true;
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        }
        double d2 = this.upper;
        double d3 = this.lower;
        return (d * (d2 - d3)) + d3;
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        double dNextDouble = this.random.nextDouble();
        return (this.upper * dNextDouble) + ((1.0d - dNextDouble) * this.lower);
    }
}
