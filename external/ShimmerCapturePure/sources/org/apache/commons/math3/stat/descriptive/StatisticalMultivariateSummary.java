package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.linear.RealMatrix;

/* loaded from: classes5.dex */
public interface StatisticalMultivariateSummary {
    RealMatrix getCovariance();

    int getDimension();

    double[] getGeometricMean();

    double[] getMax();

    double[] getMean();

    double[] getMin();

    long getN();

    double[] getStandardDeviation();

    double[] getSum();

    double[] getSumLog();

    double[] getSumSq();
}
