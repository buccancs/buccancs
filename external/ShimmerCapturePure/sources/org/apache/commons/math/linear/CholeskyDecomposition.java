package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/CholeskyDecomposition.class */
public interface CholeskyDecomposition {
    RealMatrix getL();

    RealMatrix getLT();

    double getDeterminant();

    DecompositionSolver getSolver();
}
