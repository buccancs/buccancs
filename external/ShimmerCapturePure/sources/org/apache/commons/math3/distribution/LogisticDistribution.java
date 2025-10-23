package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class LogisticDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20141003;
    private final double mu;
    private final double s;

    public LogisticDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public LogisticDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, Double.valueOf(d2));
        }
        this.mu = d;
        this.s = d2;
    }

    public double getLocation() {
        return this.mu;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return this.mu;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double d = this.s;
        return (1.0d / (d * d)) * 3.289868133696453d;
    }

    public double getScale() {
        return this.s;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
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
        return false;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        double dExp = FastMath.exp(-((d - this.mu) / this.s));
        double d2 = (1.0d / this.s) * dExp;
        double d3 = dExp + 1.0d;
        return d2 / (d3 * d3);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        return 1.0d / (FastMath.exp(-((1.0d / this.s) * (d - this.mu))) + 1.0d);
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        }
        if (d == 0.0d) {
            return 0.0d;
        }
        if (d == 1.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.s * Math.log(d / (1.0d - d))) + this.mu;
    }
}
