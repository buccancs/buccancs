package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/DecompositionSolver.class */
public interface DecompositionSolver {
    double[] solve(double[] dArr) throws InvalidMatrixException, IllegalArgumentException;

    RealVector solve(RealVector realVector) throws InvalidMatrixException, IllegalArgumentException;

    RealMatrix solve(RealMatrix realMatrix) throws InvalidMatrixException, IllegalArgumentException;

    boolean isNonSingular();

    RealMatrix getInverse() throws InvalidMatrixException;
}
