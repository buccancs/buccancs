package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class Vector3D implements Serializable, Vector<Euclidean3D> {
    public static final Vector3D ZERO = new Vector3D(0.0d, 0.0d, 0.0d);
    public static final Vector3D PLUS_I = new Vector3D(1.0d, 0.0d, 0.0d);
    public static final Vector3D MINUS_I = new Vector3D(-1.0d, 0.0d, 0.0d);
    public static final Vector3D PLUS_J = new Vector3D(0.0d, 1.0d, 0.0d);
    public static final Vector3D MINUS_J = new Vector3D(0.0d, -1.0d, 0.0d);
    public static final Vector3D PLUS_K = new Vector3D(0.0d, 0.0d, 1.0d);
    public static final Vector3D MINUS_K = new Vector3D(0.0d, 0.0d, -1.0d);
    public static final Vector3D NaN = new Vector3D(Double.NaN, Double.NaN, Double.NaN);
    public static final Vector3D POSITIVE_INFINITY = new Vector3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector3D NEGATIVE_INFINITY = new Vector3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    private static final long serialVersionUID = 1313493323784566947L;
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
    }

    public Vector3D(double[] dArr) throws DimensionMismatchException {
        if (dArr.length != 3) {
            throw new DimensionMismatchException(dArr.length, 3);
        }
        this.x = dArr[0];
        this.y = dArr[1];
        this.z = dArr[2];
    }

    public Vector3D(double d, double d2) {
        double dCos = FastMath.cos(d2);
        this.x = FastMath.cos(d) * dCos;
        this.y = FastMath.sin(d) * dCos;
        this.z = FastMath.sin(d2);
    }

    public Vector3D(double d, Vector3D vector3D) {
        this.x = vector3D.x * d;
        this.y = vector3D.y * d;
        this.z = d * vector3D.z;
    }

    public Vector3D(double d, Vector3D vector3D, double d2, Vector3D vector3D2) {
        this.x = MathArrays.linearCombination(d, vector3D.x, d2, vector3D2.x);
        this.y = MathArrays.linearCombination(d, vector3D.y, d2, vector3D2.y);
        this.z = MathArrays.linearCombination(d, vector3D.z, d2, vector3D2.z);
    }

    public Vector3D(double d, Vector3D vector3D, double d2, Vector3D vector3D2, double d3, Vector3D vector3D3) {
        this.x = MathArrays.linearCombination(d, vector3D.x, d2, vector3D2.x, d3, vector3D3.x);
        this.y = MathArrays.linearCombination(d, vector3D.y, d2, vector3D2.y, d3, vector3D3.y);
        this.z = MathArrays.linearCombination(d, vector3D.z, d2, vector3D2.z, d3, vector3D3.z);
    }

    public Vector3D(double d, Vector3D vector3D, double d2, Vector3D vector3D2, double d3, Vector3D vector3D3, double d4, Vector3D vector3D4) {
        this.x = MathArrays.linearCombination(d, vector3D.x, d2, vector3D2.x, d3, vector3D3.x, d4, vector3D4.x);
        this.y = MathArrays.linearCombination(d, vector3D.y, d2, vector3D2.y, d3, vector3D3.y, d4, vector3D4.y);
        this.z = MathArrays.linearCombination(d, vector3D.z, d2, vector3D2.z, d3, vector3D3.z, d4, vector3D4.z);
    }

    public static double angle(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        double norm = vector3D.getNorm() * vector3D2.getNorm();
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double dDotProduct = vector3D.dotProduct(vector3D2);
        double d = 0.9999d * norm;
        if (dDotProduct < (-d) || dDotProduct > d) {
            Vector3D vector3DCrossProduct = crossProduct(vector3D, vector3D2);
            if (dDotProduct >= 0.0d) {
                return FastMath.asin(vector3DCrossProduct.getNorm() / norm);
            }
            return 3.141592653589793d - FastMath.asin(vector3DCrossProduct.getNorm() / norm);
        }
        return FastMath.acos(dDotProduct / norm);
    }

    public static double dotProduct(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.dotProduct(vector3D2);
    }

    public static Vector3D crossProduct(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.crossProduct(vector3D2);
    }

    public static double distance1(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distance1(vector3D2);
    }

    public static double distance(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distance((Vector<Euclidean3D>) vector3D2);
    }

    public static double distanceInf(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distanceInf(vector3D2);
    }

    public static double distanceSq(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distanceSq(vector3D2);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormSq() {
        double d = this.x;
        double d2 = this.y;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.z;
        return d3 + (d4 * d4);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D getZero() {
        return ZERO;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y, this.z};
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public /* bridge */ /* synthetic */ Vector add(double d, Vector vector) {
        return add(d, (Vector<Euclidean3D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public /* bridge */ /* synthetic */ Vector add(Vector vector) {
        return add((Vector<Euclidean3D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public /* bridge */ /* synthetic */ Vector subtract(double d, Vector vector) {
        return subtract(d, (Vector<Euclidean3D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public /* bridge */ /* synthetic */ Vector subtract(Vector vector) {
        return subtract((Vector<Euclidean3D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public Space getSpace() {
        return Euclidean3D.getInstance();
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y) + FastMath.abs(this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm() {
        double d = this.x;
        double d2 = this.y;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.z;
        return FastMath.sqrt(d3 + (d4 * d4));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormInf() {
        return FastMath.max(FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y)), FastMath.abs(this.z));
    }

    public double getAlpha() {
        return FastMath.atan2(this.y, this.x);
    }

    public double getDelta() {
        return FastMath.asin(this.z / getNorm());
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D add(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return new Vector3D(this.x + vector3D.x, this.y + vector3D.y, this.z + vector3D.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D add(double d, Vector<Euclidean3D> vector) {
        return new Vector3D(1.0d, this, d, (Vector3D) vector);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D subtract(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return new Vector3D(this.x - vector3D.x, this.y - vector3D.y, this.z - vector3D.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D subtract(double d, Vector<Euclidean3D> vector) {
        return new Vector3D(1.0d, this, -d, (Vector3D) vector);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D normalize() throws MathArithmeticException {
        double norm = getNorm();
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return scalarMultiply(1.0d / norm);
    }

    public Vector3D orthogonal() throws MathArithmeticException {
        double norm = getNorm() * 0.6d;
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        if (FastMath.abs(this.x) <= norm) {
            double d = this.y;
            double d2 = this.z;
            double dSqrt = 1.0d / FastMath.sqrt((d * d) + (d2 * d2));
            return new Vector3D(0.0d, dSqrt * this.z, (-dSqrt) * this.y);
        }
        if (FastMath.abs(this.y) <= norm) {
            double d3 = this.x;
            double d4 = this.z;
            double dSqrt2 = 1.0d / FastMath.sqrt((d3 * d3) + (d4 * d4));
            return new Vector3D((-dSqrt2) * this.z, 0.0d, dSqrt2 * this.x);
        }
        double d5 = this.x;
        double d6 = this.y;
        double dSqrt3 = 1.0d / FastMath.sqrt((d5 * d5) + (d6 * d6));
        return new Vector3D(dSqrt3 * this.y, (-dSqrt3) * this.x, 0.0d);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D negate() {
        return new Vector3D(-this.x, -this.y, -this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector3D scalarMultiply(double d) {
        return new Vector3D(d * this.x, this.y * d, this.z * d);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y) || Double.isInfinite(this.z));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vector3D)) {
            return false;
        }
        Vector3D vector3D = (Vector3D) obj;
        if (vector3D.isNaN()) {
            return isNaN();
        }
        return this.x == vector3D.x && this.y == vector3D.y && this.z == vector3D.z;
    }

    public int hashCode() {
        if (isNaN()) {
            return 642;
        }
        return ((MathUtils.hash(this.x) * CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256) + (MathUtils.hash(this.y) * 3) + MathUtils.hash(this.z)) * 643;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double dotProduct(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return MathArrays.linearCombination(this.x, vector3D.x, this.y, vector3D.y, this.z, vector3D.z);
    }

    public Vector3D crossProduct(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return new Vector3D(MathArrays.linearCombination(this.y, vector3D.z, -this.z, vector3D.y), MathArrays.linearCombination(this.z, vector3D.x, -this.x, vector3D.z), MathArrays.linearCombination(this.x, vector3D.y, -this.y, vector3D.x));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance1(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return FastMath.abs(vector3D.x - this.x) + FastMath.abs(vector3D.y - this.y) + FastMath.abs(vector3D.z - this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance(Vector<Euclidean3D> vector) {
        return distance((Point<Euclidean3D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public double distance(Point<Euclidean3D> point) {
        Vector3D vector3D = (Vector3D) point;
        double d = vector3D.x - this.x;
        double d2 = vector3D.y - this.y;
        double d3 = vector3D.z - this.z;
        return FastMath.sqrt((d * d) + (d2 * d2) + (d3 * d3));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceInf(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        double dAbs = FastMath.abs(vector3D.x - this.x);
        double dAbs2 = FastMath.abs(vector3D.y - this.y);
        return FastMath.max(FastMath.max(dAbs, dAbs2), FastMath.abs(vector3D.z - this.z));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceSq(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        double d = vector3D.x - this.x;
        double d2 = vector3D.y - this.y;
        double d3 = vector3D.z - this.z;
        return (d * d) + (d2 * d2) + (d3 * d3);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(this);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public String toString(NumberFormat numberFormat) {
        return new Vector3DFormat(numberFormat).format(this);
    }
}
