package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes5.dex */
public class NewtonSolver extends AbstractDifferentiableUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public NewtonSolver() {
        this(1.0E-6d);
    }

    public NewtonSolver(double d) {
        super(d);
    }

    @Override
    // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver, org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double solve(int i, DifferentiableUnivariateFunction differentiableUnivariateFunction, double d, double d2) throws TooManyEvaluationsException {
        return super.solve(i, differentiableUnivariateFunction, UnivariateSolverUtils.midpoint(d, d2));
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected double doSolve() throws TooManyEvaluationsException {
        double startValue = getStartValue();
        double absoluteAccuracy = getAbsoluteAccuracy();
        while (true) {
            double dComputeObjectiveValue = startValue - (computeObjectiveValue(startValue) / computeDerivativeObjectiveValue(startValue));
            if (FastMath.abs(dComputeObjectiveValue - startValue) <= absoluteAccuracy) {
                return dComputeObjectiveValue;
            }
            startValue = dComputeObjectiveValue;
        }
    }
}
