package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

/* loaded from: classes5.dex */
public class BoundaryProjection<S extends Space> {
    private final double offset;
    private final Point<S> original;
    private final Point<S> projected;

    public BoundaryProjection(Point<S> point, Point<S> point2, double d) {
        this.original = point;
        this.projected = point2;
        this.offset = d;
    }

    public double getOffset() {
        return this.offset;
    }

    public Point<S> getOriginal() {
        return this.original;
    }

    public Point<S> getProjected() {
        return this.projected;
    }
}
