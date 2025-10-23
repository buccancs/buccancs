package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;

/* loaded from: classes5.dex */
public interface FieldVectorPreservingVisitor<T extends FieldElement<?>> {
    T end();

    void start(int i, int i2, int i3);

    void visit(int i, T t);
}
