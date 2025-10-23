package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;

/* loaded from: classes5.dex */
public abstract class AbstractUnivariateSolver extends BaseAbstractUnivariateSolver<UnivariateFunction> implements UnivariateSolver {
    protected AbstractUnivariateSolver(double d) {
        super(d);
    }

    protected AbstractUnivariateSolver(double d, double d2) {
        super(d, d2);
    }

    protected AbstractUnivariateSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }
}
