package org.apache.commons.math.distribution;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/FDistribution.class */
public interface FDistribution extends ContinuousDistribution {
    double getNumeratorDegreesOfFreedom();

    @Deprecated
    void setNumeratorDegreesOfFreedom(double d);

    double getDenominatorDegreesOfFreedom();

    @Deprecated
    void setDenominatorDegreesOfFreedom(double d);
}
