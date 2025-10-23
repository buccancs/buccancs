package org.apache.commons.math.distribution;

import java.io.Serializable;

import org.apache.commons.math.MathException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.special.Beta;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/BinomialDistributionImpl.class */
public class BinomialDistributionImpl extends AbstractIntegerDistribution implements BinomialDistribution, Serializable {
    private static final long serialVersionUID = 6751309484392813623L;
    private int numberOfTrials;
    private double probabilityOfSuccess;

    public BinomialDistributionImpl(int trials, double p) {
        setNumberOfTrialsInternal(trials);
        setProbabilityOfSuccessInternal(p);
    }

    @Override // org.apache.commons.math.distribution.BinomialDistribution
    public int getNumberOfTrials() {
        return this.numberOfTrials;
    }

    @Override // org.apache.commons.math.distribution.BinomialDistribution
    @Deprecated
    public void setNumberOfTrials(int trials) {
        setNumberOfTrialsInternal(trials);
    }

    @Override // org.apache.commons.math.distribution.BinomialDistribution
    public double getProbabilityOfSuccess() {
        return this.probabilityOfSuccess;
    }

    @Override // org.apache.commons.math.distribution.BinomialDistribution
    @Deprecated
    public void setProbabilityOfSuccess(double p) {
        setProbabilityOfSuccessInternal(p);
    }

    private void setNumberOfTrialsInternal(int trials) {
        if (trials < 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NEGATIVE_NUMBER_OF_TRIALS, Integer.valueOf(trials));
        }
        this.numberOfTrials = trials;
    }

    private void setProbabilityOfSuccessInternal(double p) {
        if (p < 0.0d || p > 1.0d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(p), Double.valueOf(0.0d), Double.valueOf(1.0d));
        }
        this.probabilityOfSuccess = p;
    }

    @Override // org.apache.commons.math.distribution.AbstractIntegerDistribution
    protected int getDomainLowerBound(double p) {
        return -1;
    }

    @Override // org.apache.commons.math.distribution.AbstractIntegerDistribution
    protected int getDomainUpperBound(double p) {
        return this.numberOfTrials;
    }

    @Override
    // org.apache.commons.math.distribution.AbstractIntegerDistribution, org.apache.commons.math.distribution.IntegerDistribution
    public double cumulativeProbability(int x) throws MathException {
        double ret;
        if (x < 0) {
            ret = 0.0d;
        } else if (x >= this.numberOfTrials) {
            ret = 1.0d;
        } else {
            ret = 1.0d - Beta.regularizedBeta(getProbabilityOfSuccess(), x + 1.0d, this.numberOfTrials - x);
        }
        return ret;
    }

    @Override // org.apache.commons.math.distribution.IntegerDistribution
    public double probability(int x) {
        double ret;
        if (x < 0 || x > this.numberOfTrials) {
            ret = 0.0d;
        } else {
            ret = FastMath.exp(SaddlePointExpansion.logBinomialProbability(x, this.numberOfTrials, this.probabilityOfSuccess, 1.0d - this.probabilityOfSuccess));
        }
        return ret;
    }

    @Override
    // org.apache.commons.math.distribution.AbstractIntegerDistribution, org.apache.commons.math.distribution.IntegerDistribution
    public int inverseCumulativeProbability(double p) throws MathException {
        if (p == 0.0d) {
            return -1;
        }
        if (p == 1.0d) {
            return Integer.MAX_VALUE;
        }
        return super.inverseCumulativeProbability(p);
    }

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return getNumberOfTrials();
    }

    public double getNumericalMean() {
        return getNumberOfTrials() * getProbabilityOfSuccess();
    }

    public double getNumericalVariance() {
        double p = getProbabilityOfSuccess();
        return getNumberOfTrials() * p * (1.0d - p);
    }
}
