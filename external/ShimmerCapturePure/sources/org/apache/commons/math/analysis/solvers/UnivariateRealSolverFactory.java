package org.apache.commons.math.analysis.solvers;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/solvers/UnivariateRealSolverFactory.class */
public abstract class UnivariateRealSolverFactory {
    protected UnivariateRealSolverFactory() {
    }

    public static UnivariateRealSolverFactory newInstance() {
        return new UnivariateRealSolverFactoryImpl();
    }

    public abstract UnivariateRealSolver newDefaultSolver();

    public abstract UnivariateRealSolver newBisectionSolver();

    public abstract UnivariateRealSolver newBrentSolver();

    public abstract UnivariateRealSolver newNewtonSolver();

    public abstract UnivariateRealSolver newSecantSolver();
}
