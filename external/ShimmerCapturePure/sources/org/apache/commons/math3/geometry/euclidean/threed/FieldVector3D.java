package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class FieldVector3D<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20130224;
    private final T x;
    private final T y;
    private final T z;

    public FieldVector3D(T t, T t2, T t3) {
        this.x = t;
        this.y = t2;
        this.z = t3;
    }

    public FieldVector3D(T[] tArr) throws DimensionMismatchException {
        if (tArr.length != 3) {
            throw new DimensionMismatchException(tArr.length, 3);
        }
        this.x = tArr[0];
        this.y = tArr[1];
        this.z = tArr[2];
    }

    public FieldVector3D(T t, T t2) {
        RealFieldElement realFieldElement = (RealFieldElement) t2.cos();
        this.x = (T) ((RealFieldElement) t.cos()).multiply(realFieldElement);
        this.y = (T) ((RealFieldElement) t.sin()).multiply(realFieldElement);
        this.z = (T) t2.sin();
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D) {
        this.x = (T) t.multiply(fieldVector3D.x);
        this.y = (T) t.multiply(fieldVector3D.y);
        this.z = (T) t.multiply(fieldVector3D.z);
    }

    public FieldVector3D(T t, Vector3D vector3D) {
        this.x = (T) t.multiply(vector3D.getX());
        this.y = (T) t.multiply(vector3D.getY());
        this.z = (T) t.multiply(vector3D.getZ());
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D) {
        this.x = (T) fieldVector3D.x.multiply(d);
        this.y = (T) fieldVector3D.y.multiply(d);
        this.z = (T) fieldVector3D.z.multiply(d);
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D, T t2, FieldVector3D<T> fieldVector3D2) {
        this.x = (T) t.linearCombination(t, fieldVector3D.getX(), t2, fieldVector3D2.getX());
        this.y = (T) t.linearCombination(t, fieldVector3D.getY(), t2, fieldVector3D2.getY());
        this.z = (T) t.linearCombination(t, fieldVector3D.getZ(), t2, fieldVector3D2.getZ());
    }

    public FieldVector3D(T t, Vector3D vector3D, T t2, Vector3D vector3D2) {
        this.x = (T) t.linearCombination(vector3D.getX(), t, vector3D2.getX(), t2);
        this.y = (T) t.linearCombination(vector3D.getY(), t, vector3D2.getY(), t2);
        this.z = (T) t.linearCombination(vector3D.getZ(), t, vector3D2.getZ(), t2);
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D, double d2, FieldVector3D<T> fieldVector3D2) {
        RealFieldElement x = fieldVector3D.getX();
        this.x = (T) x.linearCombination(d, (double) fieldVector3D.getX(), d2, (double) fieldVector3D2.getX());
        this.y = (T) x.linearCombination(d, (double) fieldVector3D.getY(), d2, (double) fieldVector3D2.getY());
        this.z = (T) x.linearCombination(d, (double) fieldVector3D.getZ(), d2, (double) fieldVector3D2.getZ());
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D, T t2, FieldVector3D<T> fieldVector3D2, T t3, FieldVector3D<T> fieldVector3D3) {
        this.x = (T) t.linearCombination(t, fieldVector3D.getX(), t2, fieldVector3D2.getX(), t3, fieldVector3D3.getX());
        this.y = (T) t.linearCombination(t, fieldVector3D.getY(), t2, fieldVector3D2.getY(), t3, fieldVector3D3.getY());
        this.z = (T) t.linearCombination(t, fieldVector3D.getZ(), t2, fieldVector3D2.getZ(), t3, fieldVector3D3.getZ());
    }

    public FieldVector3D(T t, Vector3D vector3D, T t2, Vector3D vector3D2, T t3, Vector3D vector3D3) {
        this.x = (T) t.linearCombination(vector3D.getX(), t, vector3D2.getX(), t2, vector3D3.getX(), t3);
        this.y = (T) t.linearCombination(vector3D.getY(), t, vector3D2.getY(), t2, vector3D3.getY(), t3);
        this.z = (T) t.linearCombination(vector3D.getZ(), t, vector3D2.getZ(), t2, vector3D3.getZ(), t3);
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D, double d2, FieldVector3D<T> fieldVector3D2, double d3, FieldVector3D<T> fieldVector3D3) {
        RealFieldElement x = fieldVector3D.getX();
        this.x = (T) x.linearCombination(d, (double) fieldVector3D.getX(), d2, (double) fieldVector3D2.getX(), d3, (double) fieldVector3D3.getX());
        this.y = (T) x.linearCombination(d, (double) fieldVector3D.getY(), d2, (double) fieldVector3D2.getY(), d3, (double) fieldVector3D3.getY());
        this.z = (T) x.linearCombination(d, (double) fieldVector3D.getZ(), d2, (double) fieldVector3D2.getZ(), d3, (double) fieldVector3D3.getZ());
    }

    public FieldVector3D(T t, FieldVector3D<T> fieldVector3D, T t2, FieldVector3D<T> fieldVector3D2, T t3, FieldVector3D<T> fieldVector3D3, T t4, FieldVector3D<T> fieldVector3D4) {
        this.x = (T) t.linearCombination(t, fieldVector3D.getX(), t2, fieldVector3D2.getX(), t3, fieldVector3D3.getX(), t4, fieldVector3D4.getX());
        this.y = (T) t.linearCombination(t, fieldVector3D.getY(), t2, fieldVector3D2.getY(), t3, fieldVector3D3.getY(), t4, fieldVector3D4.getY());
        this.z = (T) t.linearCombination(t, fieldVector3D.getZ(), t2, fieldVector3D2.getZ(), t3, fieldVector3D3.getZ(), t4, fieldVector3D4.getZ());
    }

    public FieldVector3D(T t, Vector3D vector3D, T t2, Vector3D vector3D2, T t3, Vector3D vector3D3, T t4, Vector3D vector3D4) {
        this.x = (T) t.linearCombination(vector3D.getX(), t, vector3D2.getX(), t2, vector3D3.getX(), t3, vector3D4.getX(), t4);
        this.y = (T) t.linearCombination(vector3D.getY(), t, vector3D2.getY(), t2, vector3D3.getY(), t3, vector3D4.getY(), t4);
        this.z = (T) t.linearCombination(vector3D.getZ(), t, vector3D2.getZ(), t2, vector3D3.getZ(), t3, vector3D4.getZ(), t4);
    }

    public FieldVector3D(double d, FieldVector3D<T> fieldVector3D, double d2, FieldVector3D<T> fieldVector3D2, double d3, FieldVector3D<T> fieldVector3D3, double d4, FieldVector3D<T> fieldVector3D4) {
        RealFieldElement x = fieldVector3D.getX();
        this.x = (T) x.linearCombination(d, (double) fieldVector3D.getX(), d2, (double) fieldVector3D2.getX(), d3, (double) fieldVector3D3.getX(), d4, (double) fieldVector3D4.getX());
        this.y = (T) x.linearCombination(d, (double) fieldVector3D.getY(), d2, (double) fieldVector3D2.getY(), d3, (double) fieldVector3D3.getY(), d4, (double) fieldVector3D4.getY());
        this.z = (T) x.linearCombination(d, (double) fieldVector3D.getZ(), d2, (double) fieldVector3D2.getZ(), d3, (double) fieldVector3D3.getZ(), d4, (double) fieldVector3D4.getZ());
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.getNorm().multiply(fieldVector3D2.getNorm());
        if (realFieldElement.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        RealFieldElement realFieldElementDotProduct = dotProduct(fieldVector3D, fieldVector3D2);
        double real = realFieldElement.getReal() * 0.9999d;
        if (realFieldElementDotProduct.getReal() < (-real) || realFieldElementDotProduct.getReal() > real) {
            FieldVector3D fieldVector3DCrossProduct = crossProduct(fieldVector3D, fieldVector3D2);
            if (realFieldElementDotProduct.getReal() >= 0.0d) {
                return (T) ((RealFieldElement) fieldVector3DCrossProduct.getNorm().divide(realFieldElement)).asin();
            }
            return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DCrossProduct.getNorm().divide(realFieldElement)).asin()).subtract(3.141592653589793d)).negate();
        }
        return (T) ((RealFieldElement) realFieldElementDotProduct.divide(realFieldElement)).acos();
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> fieldVector3D, Vector3D vector3D) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.getNorm().multiply(vector3D.getNorm());
        if (realFieldElement.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        RealFieldElement realFieldElementDotProduct = dotProduct(fieldVector3D, vector3D);
        double real = realFieldElement.getReal() * 0.9999d;
        if (realFieldElementDotProduct.getReal() < (-real) || realFieldElementDotProduct.getReal() > real) {
            FieldVector3D fieldVector3DCrossProduct = crossProduct(fieldVector3D, vector3D);
            if (realFieldElementDotProduct.getReal() >= 0.0d) {
                return (T) ((RealFieldElement) fieldVector3DCrossProduct.getNorm().divide(realFieldElement)).asin();
            }
            return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DCrossProduct.getNorm().divide(realFieldElement)).asin()).subtract(3.141592653589793d)).negate();
        }
        return (T) ((RealFieldElement) realFieldElementDotProduct.divide(realFieldElement)).acos();
    }

    public static <T extends RealFieldElement<T>> T angle(Vector3D vector3D, FieldVector3D<T> fieldVector3D) throws MathArithmeticException {
        return (T) angle(fieldVector3D, vector3D);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return (T) fieldVector3D.dotProduct(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return (T) fieldVector3D.dotProduct(vector3D);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return (T) fieldVector3D.dotProduct(vector3D);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return fieldVector3D.crossProduct(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return fieldVector3D.crossProduct(vector3D);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) ((FieldVector3D) fieldVector3D).x.linearCombination(vector3D.getY(), ((FieldVector3D) fieldVector3D).z, -vector3D.getZ(), ((FieldVector3D) fieldVector3D).y), (RealFieldElement) ((FieldVector3D) fieldVector3D).y.linearCombination(vector3D.getZ(), ((FieldVector3D) fieldVector3D).x, -vector3D.getX(), ((FieldVector3D) fieldVector3D).z), (RealFieldElement) ((FieldVector3D) fieldVector3D).z.linearCombination(vector3D.getX(), ((FieldVector3D) fieldVector3D).y, -vector3D.getY(), ((FieldVector3D) fieldVector3D).x));
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return (T) fieldVector3D.distance1(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return (T) fieldVector3D.distance1(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distance1(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return (T) fieldVector3D.distance1(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return (T) fieldVector3D.distance(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return (T) fieldVector3D.distance(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distance(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return (T) fieldVector3D.distance(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return (T) fieldVector3D.distanceInf(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return (T) fieldVector3D.distanceInf(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return (T) fieldVector3D.distanceInf(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) {
        return (T) fieldVector3D.distanceSq(fieldVector3D2);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> fieldVector3D, Vector3D vector3D) {
        return (T) fieldVector3D.distanceSq(vector3D);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(Vector3D vector3D, FieldVector3D<T> fieldVector3D) {
        return (T) fieldVector3D.distanceSq(vector3D);
    }

    public T getX() {
        return this.x;
    }

    public T getY() {
        return this.y;
    }

    public T getZ() {
        return this.z;
    }

    public T[] toArray() {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(this.x.getField(), 3));
        tArr[0] = this.x;
        tArr[1] = this.y;
        tArr[2] = this.z;
        return tArr;
    }

    public Vector3D toVector3D() {
        return new Vector3D(this.x.getReal(), this.y.getReal(), this.z.getReal());
    }

    public T getNorm1() {
        return (T) ((RealFieldElement) ((RealFieldElement) this.x.abs()).add((RealFieldElement) this.y.abs())).add((RealFieldElement) this.z.abs());
    }

    public T getNorm() {
        T t = this.x;
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
        T t2 = this.y;
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.add((RealFieldElement) t2.multiply(t2));
        T t3 = this.z;
        return (T) ((RealFieldElement) realFieldElement2.add((RealFieldElement) t3.multiply(t3))).sqrt();
    }

    public T getNormSq() {
        T t = this.x;
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
        T t2 = this.y;
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.add((RealFieldElement) t2.multiply(t2));
        T t3 = this.z;
        return (T) realFieldElement2.add((RealFieldElement) t3.multiply(t3));
    }

    public T getNormInf() {
        T t = (T) this.x.abs();
        T t2 = (T) this.y.abs();
        T t3 = (T) this.z.abs();
        return t.getReal() <= t2.getReal() ? t2.getReal() <= t3.getReal() ? t3 : t2 : t.getReal() <= t3.getReal() ? t3 : t;
    }

    public T getAlpha() {
        return (T) this.y.atan2(this.x);
    }

    public T getDelta() {
        return (T) ((RealFieldElement) this.z.divide(getNorm())).asin();
    }

    public FieldVector3D<T> add(FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(fieldVector3D.x), (RealFieldElement) this.y.add(fieldVector3D.y), (RealFieldElement) this.z.add(fieldVector3D.z));
    }

    public FieldVector3D<T> add(Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(vector3D.getX()), (RealFieldElement) this.y.add(vector3D.getY()), (RealFieldElement) this.z.add(vector3D.getZ()));
    }

    public FieldVector3D<T> add(T t, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.getField().getOne(), this, t, fieldVector3D);
    }

    public FieldVector3D<T> add(T t, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(t.multiply(vector3D.getX())), (RealFieldElement) this.y.add(t.multiply(vector3D.getY())), (RealFieldElement) this.z.add(t.multiply(vector3D.getZ())));
    }

    public FieldVector3D<T> add(double d, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>(1.0d, this, d, fieldVector3D);
    }

    public FieldVector3D<T> add(double d, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(vector3D.getX() * d), (RealFieldElement) this.y.add(vector3D.getY() * d), (RealFieldElement) this.z.add(d * vector3D.getZ()));
    }

    public FieldVector3D<T> subtract(FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(fieldVector3D.x), (RealFieldElement) this.y.subtract(fieldVector3D.y), (RealFieldElement) this.z.subtract(fieldVector3D.z));
    }

    public FieldVector3D<T> subtract(Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(vector3D.getX()), (RealFieldElement) this.y.subtract(vector3D.getY()), (RealFieldElement) this.z.subtract(vector3D.getZ()));
    }

    public FieldVector3D<T> subtract(T t, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.getField().getOne(), this, (RealFieldElement) t.negate(), fieldVector3D);
    }

    public FieldVector3D<T> subtract(T t, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(t.multiply(vector3D.getX())), (RealFieldElement) this.y.subtract(t.multiply(vector3D.getY())), (RealFieldElement) this.z.subtract(t.multiply(vector3D.getZ())));
    }

    public FieldVector3D<T> subtract(double d, FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>(1.0d, this, -d, fieldVector3D);
    }

    public FieldVector3D<T> subtract(double d, Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(vector3D.getX() * d), (RealFieldElement) this.y.subtract(vector3D.getY() * d), (RealFieldElement) this.z.subtract(d * vector3D.getZ()));
    }

    public FieldVector3D<T> normalize() throws MathArithmeticException {
        RealFieldElement norm = getNorm();
        if (norm.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return scalarMultiply((FieldVector3D<T>) norm.reciprocal());
    }

    public FieldVector3D<T> orthogonal() throws MathArithmeticException {
        double real = getNorm().getReal() * 0.6d;
        if (real == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        if (FastMath.abs(this.x.getReal()) <= real) {
            T t = this.y;
            RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
            T t2 = this.z;
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add((RealFieldElement) t2.multiply(t2))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) realFieldElement2.getField().getZero(), (RealFieldElement) realFieldElement2.multiply(this.z), (RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(this.y)).negate());
        }
        if (FastMath.abs(this.y.getReal()) <= real) {
            T t3 = this.x;
            RealFieldElement realFieldElement3 = (RealFieldElement) t3.multiply(t3);
            T t4 = this.z;
            RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement3.add((RealFieldElement) t4.multiply(t4))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) realFieldElement4.multiply(this.z)).negate(), (RealFieldElement) realFieldElement4.getField().getZero(), (RealFieldElement) realFieldElement4.multiply(this.x));
        }
        T t5 = this.x;
        RealFieldElement realFieldElement5 = (RealFieldElement) t5.multiply(t5);
        T t6 = this.y;
        RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement5.add((RealFieldElement) t6.multiply(t6))).sqrt()).reciprocal();
        return new FieldVector3D<>((RealFieldElement) realFieldElement6.multiply(this.y), (RealFieldElement) ((RealFieldElement) realFieldElement6.multiply(this.x)).negate(), (RealFieldElement) realFieldElement6.getField().getZero());
    }

    public FieldVector3D<T> negate() {
        return new FieldVector3D<>((RealFieldElement) this.x.negate(), (RealFieldElement) this.y.negate(), (RealFieldElement) this.z.negate());
    }

    public FieldVector3D<T> scalarMultiply(T t) {
        return new FieldVector3D<>((RealFieldElement) this.x.multiply(t), (RealFieldElement) this.y.multiply(t), (RealFieldElement) this.z.multiply(t));
    }

    public FieldVector3D<T> scalarMultiply(double d) {
        return new FieldVector3D<>((RealFieldElement) this.x.multiply(d), (RealFieldElement) this.y.multiply(d), (RealFieldElement) this.z.multiply(d));
    }

    public boolean isNaN() {
        return Double.isNaN(this.x.getReal()) || Double.isNaN(this.y.getReal()) || Double.isNaN(this.z.getReal());
    }

    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x.getReal()) || Double.isInfinite(this.y.getReal()) || Double.isInfinite(this.z.getReal()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldVector3D)) {
            return false;
        }
        FieldVector3D fieldVector3D = (FieldVector3D) obj;
        if (fieldVector3D.isNaN()) {
            return isNaN();
        }
        return this.x.equals(fieldVector3D.x) && this.y.equals(fieldVector3D.y) && this.z.equals(fieldVector3D.z);
    }

    public int hashCode() {
        if (isNaN()) {
            return 409;
        }
        return ((this.x.hashCode() * 107) + (this.y.hashCode() * 83) + this.z.hashCode()) * 311;
    }

    public T dotProduct(FieldVector3D<T> fieldVector3D) {
        T t = this.x;
        return (T) t.linearCombination(t, fieldVector3D.x, this.y, fieldVector3D.y, this.z, fieldVector3D.z);
    }

    public T dotProduct(Vector3D vector3D) {
        return (T) this.x.linearCombination(vector3D.getX(), this.x, vector3D.getY(), this.y, vector3D.getZ(), this.z);
    }

    public FieldVector3D<T> crossProduct(FieldVector3D<T> fieldVector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.linearCombination(this.y, fieldVector3D.z, this.z.negate(), fieldVector3D.y), (RealFieldElement) this.y.linearCombination(this.z, fieldVector3D.x, this.x.negate(), fieldVector3D.z), (RealFieldElement) this.z.linearCombination(this.x, fieldVector3D.y, this.y.negate(), fieldVector3D.x));
    }

    public FieldVector3D<T> crossProduct(Vector3D vector3D) {
        return new FieldVector3D<>((RealFieldElement) this.x.linearCombination(vector3D.getZ(), this.y, -vector3D.getY(), this.z), (RealFieldElement) this.y.linearCombination(vector3D.getX(), this.z, -vector3D.getZ(), this.x), (RealFieldElement) this.z.linearCombination(vector3D.getY(), this.x, -vector3D.getX(), this.y));
    }

    public T distance1(FieldVector3D<T> fieldVector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) fieldVector3D.x.subtract(this.x)).abs();
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) fieldVector3D.y.subtract(this.y)).abs();
        return (T) ((RealFieldElement) realFieldElement.add(realFieldElement2)).add((RealFieldElement) ((RealFieldElement) fieldVector3D.z.subtract(this.z)).abs());
    }

    public T distance1(Vector3D vector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) this.x.subtract(vector3D.getX())).abs();
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) this.y.subtract(vector3D.getY())).abs();
        return (T) ((RealFieldElement) realFieldElement.add(realFieldElement2)).add((RealFieldElement) ((RealFieldElement) this.z.subtract(vector3D.getZ())).abs());
    }

    public T distance(FieldVector3D<T> fieldVector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.x.subtract(this.x);
        RealFieldElement realFieldElement2 = (RealFieldElement) fieldVector3D.y.subtract(this.y);
        RealFieldElement realFieldElement3 = (RealFieldElement) fieldVector3D.z.subtract(this.z);
        return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3))).sqrt();
    }

    public T distance(Vector3D vector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) this.x.subtract(vector3D.getX());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.y.subtract(vector3D.getY());
        RealFieldElement realFieldElement3 = (RealFieldElement) this.z.subtract(vector3D.getZ());
        return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3))).sqrt();
    }

    public T distanceInf(FieldVector3D<T> fieldVector3D) {
        T t = (T) ((RealFieldElement) fieldVector3D.x.subtract(this.x)).abs();
        T t2 = (T) ((RealFieldElement) fieldVector3D.y.subtract(this.y)).abs();
        T t3 = (T) ((RealFieldElement) fieldVector3D.z.subtract(this.z)).abs();
        return t.getReal() <= t2.getReal() ? t2.getReal() <= t3.getReal() ? t3 : t2 : t.getReal() <= t3.getReal() ? t3 : t;
    }

    public T distanceInf(Vector3D vector3D) {
        T t = (T) ((RealFieldElement) this.x.subtract(vector3D.getX())).abs();
        T t2 = (T) ((RealFieldElement) this.y.subtract(vector3D.getY())).abs();
        T t3 = (T) ((RealFieldElement) this.z.subtract(vector3D.getZ())).abs();
        return t.getReal() <= t2.getReal() ? t2.getReal() <= t3.getReal() ? t3 : t2 : t.getReal() <= t3.getReal() ? t3 : t;
    }

    public T distanceSq(FieldVector3D<T> fieldVector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.x.subtract(this.x);
        RealFieldElement realFieldElement2 = (RealFieldElement) fieldVector3D.y.subtract(this.y);
        RealFieldElement realFieldElement3 = (RealFieldElement) fieldVector3D.z.subtract(this.z);
        return (T) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3));
    }

    public T distanceSq(Vector3D vector3D) {
        RealFieldElement realFieldElement = (RealFieldElement) this.x.subtract(vector3D.getX());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.y.subtract(vector3D.getY());
        RealFieldElement realFieldElement3 = (RealFieldElement) this.z.subtract(vector3D.getZ());
        return (T) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3));
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(toVector3D());
    }

    public String toString(NumberFormat numberFormat) {
        return new Vector3DFormat(numberFormat).format(toVector3D());
    }
}
