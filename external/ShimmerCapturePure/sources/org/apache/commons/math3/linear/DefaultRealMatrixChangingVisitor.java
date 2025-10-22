package org.apache.commons.math3.linear;

/* loaded from: classes5.dex */
public class DefaultRealMatrixChangingVisitor implements RealMatrixChangingVisitor {
    @Override // org.apache.commons.math3.linear.RealMatrixChangingVisitor
    public double end() {
        return 0.0d;
    }

    @Override // org.apache.commons.math3.linear.RealMatrixChangingVisitor
    public void start(int i, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // org.apache.commons.math3.linear.RealMatrixChangingVisitor
    public double visit(int i, int i2, double d) {
        return d;
    }
}
