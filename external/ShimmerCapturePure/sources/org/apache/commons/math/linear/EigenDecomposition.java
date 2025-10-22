package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/EigenDecomposition.class */
public interface EigenDecomposition {
    RealMatrix getV();

    RealMatrix getD();

    RealMatrix getVT();

    double[] getRealEigenvalues();

    double getRealEigenvalue(int i);

    double[] getImagEigenvalues();

    double getImagEigenvalue(int i);

    RealVector getEigenvector(int i);

    double getDeterminant();

    DecompositionSolver getSolver();
}
