package org.apache.commons.math.optimization;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.ConvergingAlgorithm;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/UnivariateRealOptimizer.class */
public interface UnivariateRealOptimizer extends ConvergingAlgorithm {
    int getMaxEvaluations();

    void setMaxEvaluations(int i);

    int getEvaluations();

    double optimize(UnivariateRealFunction univariateRealFunction, GoalType goalType, double d, double d2) throws FunctionEvaluationException, ConvergenceException;

    double optimize(UnivariateRealFunction univariateRealFunction, GoalType goalType, double d, double d2, double d3) throws FunctionEvaluationException, ConvergenceException;

    double getResult();

    double getFunctionValue() throws FunctionEvaluationException;
}
