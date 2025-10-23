package org.apache.commons.math.linear;

import org.apache.commons.math.FieldElement;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/FieldLUDecomposition.class */
public interface FieldLUDecomposition<T extends FieldElement<T>> {
    FieldMatrix<T> getL();

    FieldMatrix<T> getU();

    FieldMatrix<T> getP();

    int[] getPivot();

    T getDeterminant();

    FieldDecompositionSolver<T> getSolver();
}
