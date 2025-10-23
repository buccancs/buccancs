package org.apache.commons.math3.linear;

/* loaded from: classes5.dex */
public interface DecompositionSolver {
    RealMatrix getInverse() throws SingularMatrixException;

    boolean isNonSingular();

    RealMatrix solve(RealMatrix realMatrix) throws SingularMatrixException;

    RealVector solve(RealVector realVector) throws SingularMatrixException;
}
