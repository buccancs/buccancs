package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

/* loaded from: classes5.dex */
public class DefaultFieldMatrixPreservingVisitor<T extends FieldElement<T>> implements FieldMatrixPreservingVisitor<T> {
    private final T zero;

    public DefaultFieldMatrixPreservingVisitor(T t) {
        this.zero = t;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
    public T end() {
        return this.zero;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
    public void start(int i, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
    public void visit(int i, int i2, T t) {
    }
}
