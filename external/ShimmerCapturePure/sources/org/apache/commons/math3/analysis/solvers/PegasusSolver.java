package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.BaseSecantSolver;

/* loaded from: classes5.dex */
public class PegasusSolver extends BaseSecantSolver {
    public PegasusSolver() {
        super(1.0E-6d, BaseSecantSolver.Method.PEGASUS);
    }

    public PegasusSolver(double d) {
        super(d, BaseSecantSolver.Method.PEGASUS);
    }

    public PegasusSolver(double d, double d2) {
        super(d, d2, BaseSecantSolver.Method.PEGASUS);
    }

    public PegasusSolver(double d, double d2, double d3) {
        super(d, d2, d3, BaseSecantSolver.Method.PEGASUS);
    }
}
