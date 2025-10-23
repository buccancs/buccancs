package org.apache.commons.math.linear;

import org.apache.commons.math.FieldElement;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/FieldDecompositionSolver.class */
public interface FieldDecompositionSolver<T extends FieldElement<T>> {
    T[] solve(T[] tArr) throws InvalidMatrixException, IllegalArgumentException;

    FieldVector<T> solve(FieldVector<T> fieldVector) throws InvalidMatrixException, IllegalArgumentException;

    FieldMatrix<T> solve(FieldMatrix<T> fieldMatrix) throws InvalidMatrixException, IllegalArgumentException;

    boolean isNonSingular();

    FieldMatrix<T> getInverse() throws InvalidMatrixException;
}
