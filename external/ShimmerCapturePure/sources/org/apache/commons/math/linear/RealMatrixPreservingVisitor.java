package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/RealMatrixPreservingVisitor.class */
public interface RealMatrixPreservingVisitor {
    void start(int i, int i2, int i3, int i4, int i5, int i6);

    void visit(int i, int i2, double d) throws MatrixVisitorException;

    double end();
}
