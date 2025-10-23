package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.BaseSecantSolver;

/* loaded from: classes5.dex */
public class IllinoisSolver extends BaseSecantSolver {
    public IllinoisSolver() {
        super(1.0E-6d, BaseSecantSolver.Method.ILLINOIS);
    }

    public IllinoisSolver(double d) {
        super(d, BaseSecantSolver.Method.ILLINOIS);
    }

    public IllinoisSolver(double d, double d2) {
        super(d, d2, BaseSecantSolver.Method.ILLINOIS);
    }

    public IllinoisSolver(double d, double d2, double d3) {
        super(d, d2, d3, BaseSecantSolver.Method.PEGASUS);
    }
}
