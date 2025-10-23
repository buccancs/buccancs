package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.NullArgumentException;

/* loaded from: classes5.dex */
public abstract class AbstractPolynomialSolver extends BaseAbstractUnivariateSolver<PolynomialFunction> implements PolynomialSolver {
    private PolynomialFunction polynomialFunction;

    protected AbstractPolynomialSolver(double d) {
        super(d);
    }

    protected AbstractPolynomialSolver(double d, double d2) {
        super(d, d2);
    }

    protected AbstractPolynomialSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    public void setup(int i, PolynomialFunction polynomialFunction, double d, double d2, double d3) throws NullArgumentException {
        super.setup(i, (int) polynomialFunction, d, d2, d3);
        this.polynomialFunction = polynomialFunction;
    }

    protected double[] getCoefficients() {
        return this.polynomialFunction.getCoefficients();
    }
}
