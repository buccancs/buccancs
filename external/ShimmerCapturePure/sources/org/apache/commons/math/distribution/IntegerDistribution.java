package org.apache.commons.math.distribution;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/IntegerDistribution.class */
public interface IntegerDistribution extends DiscreteDistribution {
    double probability(int i);

    double cumulativeProbability(int i) throws MathException;

    double cumulativeProbability(int i, int i2) throws MathException;

    int inverseCumulativeProbability(double d) throws MathException;
}
