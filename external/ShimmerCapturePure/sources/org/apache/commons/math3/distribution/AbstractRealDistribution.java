package org.apache.commons.math3.distribution;

import java.io.Serializable;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public abstract class AbstractRealDistribution implements RealDistribution, Serializable {
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final long serialVersionUID = -38038050983108802L;
    protected final RandomGenerator random;

    @Deprecated
    protected RandomDataImpl randomData;
    private double solverAbsoluteAccuracy;

    @Deprecated
    protected AbstractRealDistribution() {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = null;
    }

    protected AbstractRealDistribution(RandomGenerator randomGenerator) {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = randomGenerator;
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double probability(double d) {
        return 0.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    @Deprecated
    public double cumulativeProbability(double d, double d2) throws NumberIsTooLargeException {
        return probability(d, d2);
    }

    public double probability(double d, double d2) {
        if (d > d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(d), Double.valueOf(d2), true);
        }
        return cumulativeProbability(d2) - cumulativeProbability(d);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(final double d) throws OutOfRangeException, NullArgumentException, NoBracketingException {
        double d2;
        if (d >= 0.0d) {
            double d3 = 1.0d;
            if (d <= 1.0d) {
                double supportLowerBound = getSupportLowerBound();
                if (d == 0.0d) {
                    return supportLowerBound;
                }
                double supportUpperBound = getSupportUpperBound();
                if (d == 1.0d) {
                    return supportUpperBound;
                }
                double numericalMean = getNumericalMean();
                double dSqrt = FastMath.sqrt(getNumericalVariance());
                boolean z = (Double.isInfinite(numericalMean) || Double.isNaN(numericalMean) || Double.isInfinite(dSqrt) || Double.isNaN(dSqrt)) ? false : true;
                if (supportLowerBound == Double.NEGATIVE_INFINITY) {
                    if (z) {
                        supportLowerBound = numericalMean - (FastMath.sqrt((1.0d - d) / d) * dSqrt);
                    } else {
                        supportLowerBound = -1.0d;
                        while (cumulativeProbability(supportLowerBound) >= d) {
                            supportLowerBound *= 2.0d;
                        }
                    }
                }
                if (supportUpperBound != Double.POSITIVE_INFINITY) {
                    d2 = supportUpperBound;
                } else if (z) {
                    supportUpperBound = numericalMean + (dSqrt * FastMath.sqrt(d / (1.0d - d)));
                    d2 = supportUpperBound;
                } else {
                    while (cumulativeProbability(d3) < d) {
                        d3 *= 2.0d;
                    }
                    d2 = d3;
                }
                double dSolve = UnivariateSolverUtils.solve(new UnivariateFunction() { // from class: org.apache.commons.math3.distribution.AbstractRealDistribution.1
                    @Override // org.apache.commons.math3.analysis.UnivariateFunction
                    public double value(double d4) {
                        return AbstractRealDistribution.this.cumulativeProbability(d4) - d;
                    }
                }, supportLowerBound, d2, getSolverAbsoluteAccuracy());
                if (!isSupportConnected()) {
                    double solverAbsoluteAccuracy = getSolverAbsoluteAccuracy();
                    double d4 = dSolve - solverAbsoluteAccuracy;
                    if (d4 >= getSupportLowerBound()) {
                        double dCumulativeProbability = cumulativeProbability(dSolve);
                        if (cumulativeProbability(d4) == dCumulativeProbability) {
                            while (dSolve - supportLowerBound > solverAbsoluteAccuracy) {
                                double d5 = (supportLowerBound + dSolve) * 0.5d;
                                if (cumulativeProbability(d5) < dCumulativeProbability) {
                                    supportLowerBound = d5;
                                } else {
                                    dSolve = d5;
                                }
                            }
                        }
                    }
                }
                return dSolve;
            }
        }
        throw new OutOfRangeException(Double.valueOf(d), 0, 1);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public void reseedRandomGenerator(long j) {
        this.random.setSeed(j);
        this.randomData.reSeed(j);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        return inverseCumulativeProbability(this.random.nextDouble());
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double[] sample(int i) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
        }
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = sample();
        }
        return dArr;
    }

    public double logDensity(double d) {
        return FastMath.log(density(d));
    }
}
