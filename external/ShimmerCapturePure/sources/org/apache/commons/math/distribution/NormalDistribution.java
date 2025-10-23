package org.apache.commons.math.distribution;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/NormalDistribution.class */
public interface NormalDistribution extends ContinuousDistribution, HasDensity<Double> {
    double getMean();

    @Deprecated
    void setMean(double d);

    double getStandardDeviation();

    @Deprecated
    void setStandardDeviation(double d);

    double density(Double d);
}
