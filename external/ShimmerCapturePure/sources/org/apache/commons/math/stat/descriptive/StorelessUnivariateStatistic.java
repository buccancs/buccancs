package org.apache.commons.math.stat.descriptive;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/StorelessUnivariateStatistic.class */
public interface StorelessUnivariateStatistic extends UnivariateStatistic {
    void increment(double d);

    void incrementAll(double[] dArr);

    void incrementAll(double[] dArr, int i, int i2);

    double getResult();

    long getN();

    void clear();

    StorelessUnivariateStatistic copy();
}
