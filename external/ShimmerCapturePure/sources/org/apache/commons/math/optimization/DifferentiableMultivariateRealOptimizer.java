package org.apache.commons.math.optimization;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.DifferentiableMultivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/DifferentiableMultivariateRealOptimizer.class */
public interface DifferentiableMultivariateRealOptimizer {
    int getMaxIterations();

    void setMaxIterations(int i);

    int getIterations();

    int getMaxEvaluations();

    void setMaxEvaluations(int i);

    int getEvaluations();

    int getGradientEvaluations();

    RealConvergenceChecker getConvergenceChecker();

    void setConvergenceChecker(RealConvergenceChecker realConvergenceChecker);

    RealPointValuePair optimize(DifferentiableMultivariateRealFunction differentiableMultivariateRealFunction, GoalType goalType, double[] dArr) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException;
}
