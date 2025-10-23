package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/SingularValueDecomposition.class */
public interface SingularValueDecomposition {
    RealMatrix getU();

    RealMatrix getUT();

    RealMatrix getS();

    double[] getSingularValues();

    RealMatrix getV();

    RealMatrix getVT();

    RealMatrix getCovariance(double d) throws IllegalArgumentException;

    double getNorm();

    double getConditionNumber();

    int getRank();

    DecompositionSolver getSolver();
}
