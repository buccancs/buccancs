package org.apache.commons.math3.linear;

/* loaded from: classes5.dex */
public interface RealVectorChangingVisitor {
    double end();

    void start(int i, int i2, int i3);

    double visit(int i, double d);
}
