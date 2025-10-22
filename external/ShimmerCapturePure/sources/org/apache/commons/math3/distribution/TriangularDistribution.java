package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class TriangularDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20120112;
    private final double a;
    private final double b;
    private final double c;
    private final double solverAbsoluteAccuracy;

    public TriangularDistribution(double d, double d2, double d3) throws NumberIsTooSmallException, NumberIsTooLargeException {
        this(new Well19937c(), d, d2, d3);
    }

    public TriangularDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NumberIsTooSmallException, NumberIsTooLargeException {
        super(randomGenerator);
        if (d >= d3) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d3), false);
        }
        if (d2 < d) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Double.valueOf(d2), Double.valueOf(d), true);
        }
        if (d2 > d3) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, Double.valueOf(d2), Double.valueOf(d3), true);
        }
        this.a = d;
        this.c = d2;
        this.b = d3;
        this.solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(d), FastMath.ulp(d3));
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        double d2 = this.a;
        if (d < d2) {
            return 0.0d;
        }
        if (d2 <= d) {
            double d3 = this.c;
            if (d < d3) {
                return ((d - d2) * (d - d2)) / ((this.b - d2) * (d3 - d2));
            }
        }
        double d4 = this.c;
        if (d == d4) {
            return (d4 - d2) / (this.b - d2);
        }
        if (d4 >= d) {
            return 1.0d;
        }
        double d5 = this.b;
        if (d <= d5) {
            return 1.0d - (((d5 - d) * (d5 - d)) / ((d5 - d2) * (d5 - d4)));
        }
        return 1.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        double d2 = this.a;
        if (d < d2) {
            return 0.0d;
        }
        if (d2 <= d) {
            double d3 = this.c;
            if (d < d3) {
                return ((d - d2) * 2.0d) / ((this.b - d2) * (d3 - d2));
            }
        }
        double d4 = this.c;
        if (d == d4) {
            return 2.0d / (this.b - d2);
        }
        if (d4 < d) {
            double d5 = this.b;
            if (d <= d5) {
                return ((d5 - d) * 2.0d) / ((d5 - d2) * (d5 - d4));
            }
        }
        return 0.0d;
    }

    public double getMode() {
        return this.c;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return ((this.a + this.b) + this.c) / 3.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double d = this.a;
        double d2 = this.b;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.c;
        return ((((d3 + (d4 * d4)) - (d * d2)) - (d * d4)) - (d2 * d4)) / 18.0d;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return this.a;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return this.b;
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
        if (d == 0.0d) {
            return this.a;
        }
        if (d == 1.0d) {
            return this.b;
        }
        double d2 = this.c;
        double d3 = this.a;
        double d4 = this.b;
        if (d < (d2 - d3) / (d4 - d3)) {
            return d3 + FastMath.sqrt(d * (d4 - d3) * (d2 - d3));
        }
        return d4 - FastMath.sqrt(((1.0d - d) * (d4 - d3)) * (d4 - d2));
    }
}
