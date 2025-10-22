package org.apache.commons.math.optimization;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/MultivariateRealOptimizer.class */
public interface MultivariateRealOptimizer {
    int getMaxIterations();

    void setMaxIterations(int i);

    int getMaxEvaluations();

    void setMaxEvaluations(int i);

    int getIterations();

    int getEvaluations();

    RealConvergenceChecker getConvergenceChecker();

    void setConvergenceChecker(RealConvergenceChecker realConvergenceChecker);

    RealPointValuePair optimize(MultivariateRealFunction multivariateRealFunction, GoalType goalType, double[] dArr) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException;
}
