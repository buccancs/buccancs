package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class WeibullDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 8589540077390120676L;
    private final double scale;
    private final double shape;
    private final double solverAbsoluteAccuracy;
    private double numericalMean;
    private boolean numericalMeanIsCalculated;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;

    public WeibullDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public WeibullDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public WeibullDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        this(randomGenerator, d, d2, 1.0E-9d);
    }

    public WeibullDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        this.numericalMean = Double.NaN;
        this.numericalMeanIsCalculated = false;
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(d));
        }
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d2));
        }
        this.scale = d2;
        this.shape = d;
        this.solverAbsoluteAccuracy = d3;
    }

    public double getScale() {
        return this.scale;
    }

    public double getShape() {
        return this.shape;
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

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        if (d < 0.0d) {
            return 0.0d;
        }
        double d2 = d / this.scale;
        double dPow = FastMath.pow(d2, this.shape - 1.0d);
        return (this.shape / this.scale) * dPow * FastMath.exp(-(d2 * dPow));
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double d) {
        if (d < 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        double d2 = d / this.scale;
        double dLog = FastMath.log(d2) * (this.shape - 1.0d);
        return (FastMath.log(this.shape / this.scale) + dLog) - (FastMath.exp(dLog) * d2);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        return 1.0d - FastMath.exp(-FastMath.pow(d / this.scale, this.shape));
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double d) {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        }
        if (d == 0.0d) {
            return 0.0d;
        }
        if (d == 1.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return this.scale * FastMath.pow(-FastMath.log1p(-d), 1.0d / this.shape);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        if (!this.numericalMeanIsCalculated) {
            this.numericalMean = calculateNumericalMean();
            this.numericalMeanIsCalculated = true;
        }
        return this.numericalMean;
    }

    protected double calculateNumericalMean() {
        return getScale() * FastMath.exp(Gamma.logGamma((1.0d / getShape()) + 1.0d));
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    protected double calculateNumericalVariance() {
        double shape = getShape();
        double scale = getScale();
        double numericalMean = getNumericalMean();
        return ((scale * scale) * FastMath.exp(Gamma.logGamma((2.0d / shape) + 1.0d))) - (numericalMean * numericalMean);
    }
}
