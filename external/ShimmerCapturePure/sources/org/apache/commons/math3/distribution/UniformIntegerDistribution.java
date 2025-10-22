package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

/* loaded from: classes5.dex */
public class UniformIntegerDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20120109;
    private final int lower;
    private final int upper;

    public UniformIntegerDistribution(int i, int i2) throws NumberIsTooLargeException {
        this(new Well19937c(), i, i2);
    }

    public UniformIntegerDistribution(RandomGenerator randomGenerator, int i, int i2) throws NumberIsTooLargeException {
        super(randomGenerator);
        if (i > i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(i), Integer.valueOf(i2), true);
        }
        this.lower = i;
        this.upper = i2;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double cumulativeProbability(int i) {
        if (i < this.lower) {
            return 0.0d;
        }
        if (i > this.upper) {
            return 1.0d;
        }
        return ((i - r0) + 1.0d) / ((r1 - r0) + 1.0d);
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double getNumericalMean() {
        return (this.lower + this.upper) * 0.5d;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double getNumericalVariance() {
        double d = (this.upper - this.lower) + 1;
        return ((d * d) - 1.0d) / 12.0d;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public int getSupportLowerBound() {
        return this.lower;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public int getSupportUpperBound() {
        return this.upper;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public boolean isSupportConnected() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double probability(int i) {
        int i2;
        if (i < this.lower || i > (i2 = this.upper)) {
            return 0.0d;
        }
        return 1.0d / ((i2 - r0) + 1);
    }

    @Override
    // org.apache.commons.math3.distribution.AbstractIntegerDistribution, org.apache.commons.math3.distribution.IntegerDistribution
    public int sample() {
        int i = this.upper;
        int i2 = this.lower;
        int i3 = (i - i2) + 1;
        if (i3 > 0) {
            return i2 + this.random.nextInt(i3);
        }
        while (true) {
            int iNextInt = this.random.nextInt();
            if (iNextInt >= this.lower && iNextInt <= this.upper) {
                return iNextInt;
            }
        }
    }
}
