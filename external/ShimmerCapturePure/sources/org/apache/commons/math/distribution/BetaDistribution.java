package org.apache.commons.math.distribution;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/BetaDistribution.class */
public interface BetaDistribution extends ContinuousDistribution, HasDensity<Double> {
    double getAlpha();

    @Deprecated
    void setAlpha(double d);

    double getBeta();

    @Deprecated
    void setBeta(double d);

    double density(Double d) throws MathException;
}
