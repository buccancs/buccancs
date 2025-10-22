package org.apache.commons.math.analysis.solvers;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/solvers/UnivariateRealSolverFactoryImpl.class */
public class UnivariateRealSolverFactoryImpl extends UnivariateRealSolverFactory {
    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverFactory
    public UnivariateRealSolver newDefaultSolver() {
        return newBrentSolver();
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverFactory
    public UnivariateRealSolver newBisectionSolver() {
        return new BisectionSolver();
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverFactory
    public UnivariateRealSolver newBrentSolver() {
        return new BrentSolver();
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverFactory
    public UnivariateRealSolver newNewtonSolver() {
        return new NewtonSolver();
    }

    @Override // org.apache.commons.math.analysis.solvers.UnivariateRealSolverFactory
    public UnivariateRealSolver newSecantSolver() {
        return new SecantSolver();
    }
}
