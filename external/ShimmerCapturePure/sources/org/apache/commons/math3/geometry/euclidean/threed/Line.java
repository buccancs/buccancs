package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class Line implements Embedding<Euclidean3D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private final double tolerance;
    private Vector3D direction;
    private Vector3D zero;

    public Line(Vector3D vector3D, Vector3D vector3D2, double d) throws MathIllegalArgumentException {
        reset(vector3D, vector3D2);
        this.tolerance = d;
    }

    public Line(Line line) {
        this.direction = line.direction;
        this.zero = line.zero;
        this.tolerance = line.tolerance;
    }

    @Deprecated
    public Line(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        this(vector3D, vector3D2, 1.0E-10d);
    }

    public Vector3D getDirection() {
        return this.direction;
    }

    public Vector3D getOrigin() {
        return this.zero;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public /* bridge */ /* synthetic */ Point toSpace(Point point) {
        return toSpace((Point<Euclidean1D>) point);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public /* bridge */ /* synthetic */ Point toSubSpace(Point point) {
        return toSubSpace((Point<Euclidean3D>) point);
    }

    public void reset(Vector3D vector3D, Vector3D vector3D2) throws MathIllegalArgumentException {
        Vector3D vector3DSubtract = vector3D2.subtract((Vector<Euclidean3D>) vector3D);
        double normSq = vector3DSubtract.getNormSq();
        if (normSq == 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        this.direction = new Vector3D(1.0d / FastMath.sqrt(normSq), vector3DSubtract);
        this.zero = new Vector3D(1.0d, vector3D, (-vector3D.dotProduct(vector3DSubtract)) / normSq, vector3DSubtract);
    }

    public Line revert() {
        Line line = new Line(this);
        line.direction = line.direction.negate();
        return line;
    }

    public double getAbscissa(Vector3D vector3D) {
        return vector3D.subtract((Vector<Euclidean3D>) this.zero).dotProduct(this.direction);
    }

    public Vector3D pointAt(double d) {
        return new Vector3D(1.0d, this.zero, d, this.direction);
    }

    public Vector1D toSubSpace(Vector<Euclidean3D> vector) {
        return toSubSpace((Point<Euclidean3D>) vector);
    }

    public Vector3D toSpace(Vector<Euclidean1D> vector) {
        return toSpace((Point<Euclidean1D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Vector1D toSubSpace(Point<Euclidean3D> point) {
        return new Vector1D(getAbscissa((Vector3D) point));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Vector3D toSpace(Point<Euclidean1D> point) {
        return pointAt(((Vector1D) point).getX());
    }

    public boolean isSimilarTo(Line line) throws MathArithmeticException {
        double dAngle = Vector3D.angle(this.direction, line.direction);
        double d = this.tolerance;
        return (dAngle < d || dAngle > 3.141592653589793d - d) && contains(line.zero);
    }

    public boolean contains(Vector3D vector3D) {
        return distance(vector3D) < this.tolerance;
    }

    public double distance(Vector3D vector3D) {
        Vector3D vector3DSubtract = vector3D.subtract((Vector<Euclidean3D>) this.zero);
        return new Vector3D(1.0d, vector3DSubtract, -vector3DSubtract.dotProduct(this.direction), this.direction).getNorm();
    }

    public double distance(Line line) {
        Vector3D vector3DCrossProduct = Vector3D.crossProduct(this.direction, line.direction);
        double norm = vector3DCrossProduct.getNorm();
        if (norm < Precision.SAFE_MIN) {
            return distance(line.zero);
        }
        return FastMath.abs(line.zero.subtract((Vector<Euclidean3D>) this.zero).dotProduct(vector3DCrossProduct) / norm);
    }

    public Vector3D closestPoint(Line line) {
        double dDotProduct = this.direction.dotProduct(line.direction);
        double d = 1.0d - (dDotProduct * dDotProduct);
        if (d < Precision.EPSILON) {
            return this.zero;
        }
        Vector3D vector3DSubtract = line.zero.subtract((Vector<Euclidean3D>) this.zero);
        return new Vector3D(1.0d, this.zero, (vector3DSubtract.dotProduct(this.direction) - (vector3DSubtract.dotProduct(line.direction) * dDotProduct)) / d, this.direction);
    }

    public Vector3D intersection(Line line) {
        Vector3D vector3DClosestPoint = closestPoint(line);
        if (line.contains(vector3DClosestPoint)) {
            return vector3DClosestPoint;
        }
        return null;
    }

    public SubLine wholeLine() {
        return new SubLine(this, new IntervalsSet(this.tolerance));
    }
}
