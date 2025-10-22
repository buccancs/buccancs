package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/DefaultRealMatrixPreservingVisitor.class */
public class DefaultRealMatrixPreservingVisitor implements RealMatrixPreservingVisitor {
    @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
    public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
    }

    @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
    public void visit(int row, int column, double value) throws MatrixVisitorException {
    }

    @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
    public double end() {
        return 0.0d;
    }
}
