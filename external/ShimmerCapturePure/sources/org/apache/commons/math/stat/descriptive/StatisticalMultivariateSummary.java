package org.apache.commons.math.stat.descriptive;

import org.apache.commons.math.linear.RealMatrix;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/StatisticalMultivariateSummary.class */
public interface StatisticalMultivariateSummary {
    int getDimension();

    double[] getMean();

    RealMatrix getCovariance();

    double[] getStandardDeviation();

    double[] getMax();

    double[] getMin();

    long getN();

    double[] getGeometricMean();

    double[] getSum();

    double[] getSumSq();

    double[] getSumLog();
}
