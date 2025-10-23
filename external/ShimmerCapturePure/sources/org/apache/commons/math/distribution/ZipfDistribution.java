package org.apache.commons.math.distribution;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/ZipfDistribution.class */
public interface ZipfDistribution extends IntegerDistribution {
    int getNumberOfElements();

    @Deprecated
    void setNumberOfElements(int i);

    double getExponent();

    @Deprecated
    void setExponent(double d);
}
