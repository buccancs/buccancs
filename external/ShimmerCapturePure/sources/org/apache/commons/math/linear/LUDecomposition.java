package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/LUDecomposition.class */
public interface LUDecomposition {
    RealMatrix getL();

    RealMatrix getU();

    RealMatrix getP();

    int[] getPivot();

    double getDeterminant();

    DecompositionSolver getSolver();
}
