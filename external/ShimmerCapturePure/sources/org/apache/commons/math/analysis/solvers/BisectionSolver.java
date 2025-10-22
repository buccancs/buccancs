package org.apache.commons.math.analysis.solvers;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/solvers/BisectionSolver.class */
public class BisectionSolver extends UnivariateRealSolverImpl {
    @Deprecated
    public BisectionSolver(UnivariateRealFunction f) {
        super(f, 100, 1.0E-6d);
    }

    public BisectionSolver() {
        super(100, 1.0E-6d);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(double min, double max, double initial) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(this.f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(this.f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(UnivariateRealFunction f, double min, double max, double initial) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverImpl
    public double solve(int maxEval, UnivariateRealFunction f, double min, double max, double initial) throws FunctionEvaluationException, MaxIterationsExceededException {
        return solve(maxEval, f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverImpl
    public double solve(int maxEval, UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        setMaximalIterationCount(maxEval);
        return solve(f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        clearResult();
        verifyInterval(min, max);
        for (int i = 0; i < this.maximalIterationCount; i++) {
            double m = UnivariateRealSolverUtils.midpoint(min, max);
            double fmin = f.value(min);
            double fm = f.value(m);
            if (fm * fmin > 0.0d) {
                min = m;
            } else {
                max = m;
            }
            if (FastMath.abs(max - min) <= this.absoluteAccuracy) {
                double m2 = UnivariateRealSolverUtils.midpoint(min, max);
                setResult(m2, i);
                return m2;
            }
        }
        throw new MaxIterationsExceededException(this.maximalIterationCount);
    }
}
