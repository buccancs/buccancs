package org.apache.commons.math.analysis.solvers;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/solvers/RiddersSolver.class */
public class RiddersSolver extends UnivariateRealSolverImpl {
    @Deprecated
    public RiddersSolver(UnivariateRealFunction f) {
        super(f, 100, 1.0E-6d);
    }

    @Deprecated
    public RiddersSolver() {
        super(100, 1.0E-6d);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(double min, double max) throws FunctionEvaluationException, ConvergenceException {
        return solve(this.f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(double min, double max, double initial) throws FunctionEvaluationException, ConvergenceException {
        return solve(this.f, min, max, initial);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverImpl
    public double solve(int maxEval, UnivariateRealFunction f, double min, double max, double initial) throws FunctionEvaluationException, MaxIterationsExceededException {
        setMaximalIterationCount(maxEval);
        return solve(f, min, max, initial);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(UnivariateRealFunction f, double min, double max, double initial) throws FunctionEvaluationException, MaxIterationsExceededException {
        if (f.value(min) == 0.0d) {
            return min;
        }
        if (f.value(max) == 0.0d) {
            return max;
        }
        if (f.value(initial) == 0.0d) {
            return initial;
        }
        verifyBracketing(min, max, f);
        verifySequence(min, initial, max);
        if (isBracketing(min, initial, f)) {
            return solve(f, min, initial);
        }
        return solve(f, initial, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverImpl
    public double solve(int maxEval, UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        setMaximalIterationCount(maxEval);
        return solve(f, min, max);
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolver
    @Deprecated
    public double solve(UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        double x1 = min;
        double y1 = f.value(x1);
        double x2 = max;
        double y2 = f.value(x2);
        if (y1 == 0.0d) {
            return min;
        }
        if (y2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max, f);
        double oldx = Double.POSITIVE_INFINITY;
        for (int i = 1; i <= this.maximalIterationCount; i++) {
            double x3 = 0.5d * (x1 + x2);
            double y3 = f.value(x3);
            if (FastMath.abs(y3) <= this.functionValueAccuracy) {
                setResult(x3, i);
                return this.result;
            }
            double delta = 1.0d - ((y1 * y2) / (y3 * y3));
            double correction = ((MathUtils.sign(y2) * MathUtils.sign(y3)) * (x3 - x1)) / FastMath.sqrt(delta);
            double x = x3 - correction;
            double y = f.value(x);
            double tolerance = FastMath.max(this.relativeAccuracy * FastMath.abs(x), this.absoluteAccuracy);
            if (FastMath.abs(x - oldx) <= tolerance) {
                setResult(x, i);
                return this.result;
            }
            if (FastMath.abs(y) <= this.functionValueAccuracy) {
                setResult(x, i);
                return this.result;
            }
            if (correction > 0.0d) {
                if (MathUtils.sign(y1) + MathUtils.sign(y) == 0.0d) {
                    x2 = x;
                    y2 = y;
                } else {
                    x1 = x;
                    x2 = x3;
                    y1 = y;
                    y2 = y3;
                }
            } else if (MathUtils.sign(y2) + MathUtils.sign(y) == 0.0d) {
                x1 = x;
                y1 = y;
            } else {
                x1 = x3;
                x2 = x;
                y1 = y3;
                y2 = y;
            }
            oldx = x;
        }
        throw new MaxIterationsExceededException(this.maximalIterationCount);
    }
}
