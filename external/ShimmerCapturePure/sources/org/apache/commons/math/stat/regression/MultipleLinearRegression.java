package org.apache.commons.math.stat.regression;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/regression/MultipleLinearRegression.class */
public interface MultipleLinearRegression {
    double[] estimateRegressionParameters();

    double[][] estimateRegressionParametersVariance();

    double[] estimateResiduals();

    double estimateRegressandVariance();

    double[] estimateRegressionParametersStandardErrors();
}
