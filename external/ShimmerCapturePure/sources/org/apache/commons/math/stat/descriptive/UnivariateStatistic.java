package org.apache.commons.math.stat.descriptive;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/UnivariateStatistic.class */
public interface UnivariateStatistic {
    double evaluate(double[] dArr);

    double evaluate(double[] dArr, int i, int i2);

    UnivariateStatistic copy();
}
