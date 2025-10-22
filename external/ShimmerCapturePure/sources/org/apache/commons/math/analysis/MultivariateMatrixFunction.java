package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/MultivariateMatrixFunction.class */
public interface MultivariateMatrixFunction {
    double[][] value(double[] dArr) throws FunctionEvaluationException, IllegalArgumentException;
}
