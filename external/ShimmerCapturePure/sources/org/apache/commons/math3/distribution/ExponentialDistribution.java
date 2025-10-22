package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.ResizableDoubleArray;

/* loaded from: classes5.dex */
public class ExponentialDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final double[] EXPONENTIAL_SA_QI;
    private static final long serialVersionUID = 2401296428283614780L;

    static {
        double dLog = FastMath.log(2.0d);
        ResizableDoubleArray resizableDoubleArray = new ResizableDoubleArray(20);
        double dPow = 0.0d;
        int i = 1;
        while (dPow < 1.0d) {
            dPow += FastMath.pow(dLog, i) / CombinatoricsUtils.factorial(i);
            resizableDoubleArray.addElement(dPow);
            i++;
        }
        EXPONENTIAL_SA_QI = resizableDoubleArray.getElements();
    }

    private final double logMean;
    private final double mean;
    private final double solverAbsoluteAccuracy;

    public ExponentialDistribution(double d) {
        this(d, 1.0E-9d);
    }

    public ExponentialDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public ExponentialDistribution(RandomGenerator randomGenerator, double d) throws NotStrictlyPositiveException {
        this(randomGenerator, d, 1.0E-9d);
    }

    public ExponentialDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(d));
        }
        this.mean = d;
        this.logMean = FastMath.log(d);
        this.solverAbsoluteAccuracy = d2;
    }

    public double getMean() {
        return this.mean;
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

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double d) {
        if (d < 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        return ((-d) / this.mean) - this.logMean;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double d) {
        double dLogDensity = logDensity(d);
        if (dLogDensity == Double.NEGATIVE_INFINITY) {
            return 0.0d;
        }
        return FastMath.exp(dLogDensity);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        return 1.0d - FastMath.exp((-d) / this.mean);
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        }
        if (d == 1.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return FastMath.log(1.0d - d) * (-this.mean);
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        double[] dArr;
        double dNextDouble = this.random.nextDouble();
        double d = 0.0d;
        while (dNextDouble < 0.5d) {
            d += EXPONENTIAL_SA_QI[0];
            dNextDouble *= 2.0d;
        }
        double d2 = dNextDouble + (dNextDouble - 1.0d);
        if (d2 <= EXPONENTIAL_SA_QI[0]) {
            return this.mean * (d + d2);
        }
        double dNextDouble2 = this.random.nextDouble();
        int i = 0;
        do {
            i++;
            double dNextDouble3 = this.random.nextDouble();
            if (dNextDouble3 < dNextDouble2) {
                dNextDouble2 = dNextDouble3;
            }
            dArr = EXPONENTIAL_SA_QI;
        } while (d2 > dArr[i]);
        return this.mean * (d + (dNextDouble2 * dArr[0]));
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return getMean();
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double mean = getMean();
        return mean * mean;
    }
}
