package org.apache.commons.math3.geometry.enclosing;

import java.io.Serializable;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

/* loaded from: classes5.dex */
public class EnclosingBall<S extends Space, P extends Point<S>> implements Serializable {
    private static final long serialVersionUID = 20140126;
    private final P center;
    private final double radius;
    private final P[] support;

    public EnclosingBall(P p, double d, P... pArr) {
        this.center = p;
        this.radius = d;
        this.support = (P[]) ((Point[]) pArr.clone());
    }

    public P getCenter() {
        return this.center;
    }

    public double getRadius() {
        return this.radius;
    }

    public P[] getSupport() {
        return (P[]) ((Point[]) this.support.clone());
    }

    public int getSupportSize() {
        return this.support.length;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean contains(P p) {
        return p.distance(this.center) <= this.radius;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean contains(P p, double d) {
        return p.distance(this.center) <= this.radius + d;
    }
}
