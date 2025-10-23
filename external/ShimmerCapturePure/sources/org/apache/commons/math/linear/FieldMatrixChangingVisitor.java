package org.apache.commons.math.linear;

import org.apache.commons.math.FieldElement;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/FieldMatrixChangingVisitor.class */
public interface FieldMatrixChangingVisitor<T extends FieldElement<?>> {
    void start(int i, int i2, int i3, int i4, int i5, int i6);

    T visit(int i, int i2, T t) throws MatrixVisitorException;

    T end();
}
