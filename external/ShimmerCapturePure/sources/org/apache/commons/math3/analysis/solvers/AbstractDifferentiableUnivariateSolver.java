package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

@Deprecated
/* loaded from: classes5.dex */
public abstract class AbstractDifferentiableUnivariateSolver extends BaseAbstractUnivariateSolver<DifferentiableUnivariateFunction> implements DifferentiableUnivariateSolver {
    private UnivariateFunction functionDerivative;

    protected AbstractDifferentiableUnivariateSolver(double d) {
        super(d);
    }

    protected AbstractDifferentiableUnivariateSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    protected double computeDerivativeObjectiveValue(double d) throws TooManyEvaluationsException {
        incrementEvaluationCount();
        return this.functionDerivative.value(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    public void setup(int i, DifferentiableUnivariateFunction differentiableUnivariateFunction, double d, double d2, double d3) throws NullArgumentException {
        super.setup(i, (int) differentiableUnivariateFunction, d, d2, d3);
        this.functionDerivative = differentiableUnivariateFunction.derivative();
    }
}
