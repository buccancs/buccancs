package org.apache.commons.math.optimization;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/DifferentiableMultivariateVectorialOptimizer.class */
public interface DifferentiableMultivariateVectorialOptimizer {
    int getMaxIterations();

    void setMaxIterations(int i);

    int getIterations();

    int getMaxEvaluations();

    void setMaxEvaluations(int i);

    int getEvaluations();

    int getJacobianEvaluations();

    VectorialConvergenceChecker getConvergenceChecker();

    void setConvergenceChecker(VectorialConvergenceChecker vectorialConvergenceChecker);

    VectorialPointValuePair optimize(DifferentiableMultivariateVectorialFunction differentiableMultivariateVectorialFunction, double[] dArr, double[] dArr2, double[] dArr3) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException;
}
