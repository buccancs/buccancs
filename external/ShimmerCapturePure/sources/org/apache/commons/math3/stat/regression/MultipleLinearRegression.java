package org.apache.commons.math3.stat.regression;

/* loaded from: classes5.dex */
public interface MultipleLinearRegression {
    double estimateRegressandVariance();

    double[] estimateRegressionParameters();

    double[] estimateRegressionParametersStandardErrors();

    double[][] estimateRegressionParametersVariance();

    double[] estimateResiduals();
}
