package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class NormalApproximationInterval implements BinomialConfidenceInterval {
    @Override // org.apache.commons.math3.stat.interval.BinomialConfidenceInterval
    public ConfidenceInterval createInterval(int i, int i2, double d) {
        IntervalUtils.checkParameters(i, i2, d);
        double d2 = i;
        double d3 = i2 / d2;
        double dInverseCumulativeProbability = new NormalDistribution().inverseCumulativeProbability(1.0d - ((1.0d - d) / 2.0d)) * FastMath.sqrt((1.0d / d2) * d3 * (1.0d - d3));
        return new ConfidenceInterval(d3 - dInverseCumulativeProbability, d3 + dInverseCumulativeProbability, d);
    }
}
