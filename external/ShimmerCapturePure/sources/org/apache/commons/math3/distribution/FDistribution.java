package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class FDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -8516354193418641566L;
    private final double denominatorDegreesOfFreedom;
    private final double numeratorDegreesOfFreedom;
    private final double solverAbsoluteAccuracy;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;

    public FDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public FDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public FDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        this(randomGenerator, d, d2, 1.0E-9d);
    }

    public FDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(d));
        }
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(d2));
        }
        this.numeratorDegreesOfFreedom = d;
        this.denominatorDegreesOfFreedom = d2;
        this.solverAbsoluteAccuracy = d3;
    }

    public double getDenominatorDegreesOfFreedom() {
        return this.denominatorDegreesOfFreedom;
    }

    public double getNumeratorDegreesOfFreedom() {
        return this.numeratorDegreesOfFreedom;
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
        return false;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        return FastMath.exp(logDensity(d));
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double d) {
        double d2 = this.numeratorDegreesOfFreedom / 2.0d;
        double d3 = this.denominatorDegreesOfFreedom / 2.0d;
        double dLog = FastMath.log(d);
        double dLog2 = FastMath.log(this.numeratorDegreesOfFreedom);
        double dLog3 = FastMath.log(this.denominatorDegreesOfFreedom);
        double dLog4 = FastMath.log((this.numeratorDegreesOfFreedom * d) + this.denominatorDegreesOfFreedom);
        return ((((((dLog2 * d2) + (d2 * dLog)) - dLog) + (dLog3 * d3)) - (d2 * dLog4)) - (dLog4 * d3)) - Beta.logBeta(d2, d3);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double d2 = this.numeratorDegreesOfFreedom;
        double d3 = this.denominatorDegreesOfFreedom;
        double d4 = d * d2;
        return Beta.regularizedBeta(d4 / (d3 + d4), d2 * 0.5d, d3 * 0.5d);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        double denominatorDegreesOfFreedom = getDenominatorDegreesOfFreedom();
        if (denominatorDegreesOfFreedom > 2.0d) {
            return denominatorDegreesOfFreedom / (denominatorDegreesOfFreedom - 2.0d);
        }
        return Double.NaN;
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
        double denominatorDegreesOfFreedom = getDenominatorDegreesOfFreedom();
        if (denominatorDegreesOfFreedom <= 4.0d) {
            return Double.NaN;
        }
        double numeratorDegreesOfFreedom = getNumeratorDegreesOfFreedom();
        double d = denominatorDegreesOfFreedom - 2.0d;
        return (((denominatorDegreesOfFreedom * denominatorDegreesOfFreedom) * 2.0d) * ((numeratorDegreesOfFreedom + denominatorDegreesOfFreedom) - 2.0d)) / ((numeratorDegreesOfFreedom * (d * d)) * (denominatorDegreesOfFreedom - 4.0d));
    }
}
