package org.apache.commons.math.analysis.solvers;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.DifferentiableUnivariateRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/solvers/NewtonSolver.class */
public class NewtonSolver extends UnivariateRealSolverImpl {
    @Deprecated
    public NewtonSolver(DifferentiableUnivariateRealFunction f) {
        super(f, 100, 1.0E-6d);
    }

    @Deprecated
    public NewtonSolver() {
        super(100, 1.0E-6d);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(this.f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(double min, double max, double startValue) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(this.f, min, max, startValue);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverImpl
    public double solve(int maxEval, UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        setMaximalIterationCount(maxEval);
        return solve(f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(f, min, max, UnivariateRealSolverUtils.midpoint(min, max));
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverImpl
    public double solve(int maxEval, UnivariateRealFunction f, double min, double max, double startValue) throws FunctionEvaluationException, MaxIterationsExceededException {
        setMaximalIterationCount(maxEval);
        return solve(f, min, max, startValue);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(UnivariateRealFunction f, double min, double max, double startValue) throws FunctionEvaluationException, MaxIterationsExceededException {
        try {
            UnivariateRealFunction derivative = ((DifferentiableUnivariateRealFunction) f).derivative();
            clearResult();
            verifySequence(min, startValue, max);
            double x0 = startValue;
            for (int i = 0; i < this.maximalIterationCount; i++) {
                double x1 = x0 - (f.value(x0) / derivative.value(x0));
                if (FastMath.abs(x1 - x0) <= this.absoluteAccuracy) {
                    setResult(x1, i);
                    return x1;
                }
                x0 = x1;
            }
            throw new MaxIterationsExceededException(this.maximalIterationCount);
        } catch (ClassCastException e) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.FUNCTION_NOT_DIFFERENTIABLE, new Object[0]);
        }
    }
}
