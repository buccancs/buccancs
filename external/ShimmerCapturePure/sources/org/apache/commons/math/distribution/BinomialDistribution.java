package org.apache.commons.math.distribution;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/BinomialDistribution.class */
public interface BinomialDistribution extends IntegerDistribution {
    int getNumberOfTrials();

    @Deprecated
    void setNumberOfTrials(int i);

    double getProbabilityOfSuccess();

    @Deprecated
    void setProbabilityOfSuccess(double d);
}
