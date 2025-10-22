package org.apache.commons.math.analysis.solvers;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.ConvergingAlgorithm;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/solvers/UnivariateRealSolver.class */
public interface UnivariateRealSolver extends ConvergingAlgorithm {
    double getFunctionValueAccuracy();

    void setFunctionValueAccuracy(double d);

    void resetFunctionValueAccuracy();

    @Deprecated
    double solve(double d, double d2) throws FunctionEvaluationException, ConvergenceException;

    @Deprecated
    double solve(UnivariateRealFunction univariateRealFunction, double d, double d2) throws FunctionEvaluationException, ConvergenceException;

    @Deprecated
    double solve(double d, double d2, double d3) throws FunctionEvaluationException, IllegalArgumentException, ConvergenceException;

    @Deprecated
    double solve(UnivariateRealFunction univariateRealFunction, double d, double d2, double d3) throws FunctionEvaluationException, IllegalArgumentException, ConvergenceException;

    double getResult();

    double getFunctionValue();
}
