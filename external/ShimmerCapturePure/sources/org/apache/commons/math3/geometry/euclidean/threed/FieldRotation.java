package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class FieldRotation<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20130224;
    private final T q0;
    private final T q1;
    private final T q2;
    private final T q3;

    public FieldRotation(T t, T t2, T t3, T t4, boolean z) {
        if (!z) {
            this.q0 = t;
            this.q1 = t2;
            this.q2 = t3;
            this.q3 = t4;
            return;
        }
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(t)).add((RealFieldElement) t2.multiply(t2))).add((RealFieldElement) t3.multiply(t3))).add((RealFieldElement) t4.multiply(t4))).sqrt()).reciprocal();
        this.q0 = (T) realFieldElement.multiply(t);
        this.q1 = (T) realFieldElement.multiply(t2);
        this.q2 = (T) realFieldElement.multiply(t3);
        this.q3 = (T) realFieldElement.multiply(t4);
    }

    @Deprecated
    public FieldRotation(FieldVector3D<T> fieldVector3D, T t) throws MathIllegalArgumentException {
        this(fieldVector3D, t, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation(FieldVector3D<T> fieldVector3D, T t, RotationConvention rotationConvention) throws MathIllegalArgumentException {
        RealFieldElement norm = fieldVector3D.getNorm();
        if (norm.getReal() == 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
        }
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(rotationConvention == RotationConvention.VECTOR_OPERATOR ? -0.5d : 0.5d);
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) realFieldElement.sin()).divide(norm);
        this.q0 = (T) realFieldElement.cos();
        this.q1 = (T) realFieldElement2.multiply(fieldVector3D.getX());
        this.q2 = (T) realFieldElement2.multiply(fieldVector3D.getY());
        this.q3 = (T) realFieldElement2.multiply(fieldVector3D.getZ());
    }

    public FieldRotation(T[][] tArr, double d) throws NotARotationMatrixException {
        if (tArr.length != 3 || tArr[0].length != 3 || tArr[1].length != 3 || tArr[2].length != 3) {
            throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, Integer.valueOf(tArr.length), Integer.valueOf(tArr[0].length));
        }
        RealFieldElement[][] realFieldElementArrOrthogonalizeMatrix = orthogonalizeMatrix(tArr, d);
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[0][0].multiply((RealFieldElement) ((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[1][1].multiply(realFieldElementArrOrthogonalizeMatrix[2][2])).subtract((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[2][1].multiply(realFieldElementArrOrthogonalizeMatrix[1][2])))).subtract((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[1][0].multiply((RealFieldElement) ((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[0][1].multiply(realFieldElementArrOrthogonalizeMatrix[2][2])).subtract((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[2][1].multiply(realFieldElementArrOrthogonalizeMatrix[0][2]))))).add((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[2][0].multiply((RealFieldElement) ((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[0][1].multiply(realFieldElementArrOrthogonalizeMatrix[1][2])).subtract((RealFieldElement) realFieldElementArrOrthogonalizeMatrix[1][1].multiply(realFieldElementArrOrthogonalizeMatrix[0][2]))));
        if (realFieldElement.getReal() < 0.0d) {
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, realFieldElement);
        }
        RealFieldElement[] realFieldElementArrMat2quat = mat2quat(realFieldElementArrOrthogonalizeMatrix);
        this.q0 = (T) realFieldElementArrMat2quat[0];
        this.q1 = (T) realFieldElementArrMat2quat[1];
        this.q2 = (T) realFieldElementArrMat2quat[2];
        this.q3 = (T) realFieldElementArrMat2quat[3];
    }

    public FieldRotation(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2, FieldVector3D<T> fieldVector3D3, FieldVector3D<T> fieldVector3D4) throws MathArithmeticException {
        FieldVector3D<T> fieldVector3DNormalize = FieldVector3D.crossProduct(fieldVector3D, fieldVector3D2).normalize();
        FieldVector3D<T> fieldVector3DNormalize2 = FieldVector3D.crossProduct(fieldVector3DNormalize, fieldVector3D).normalize();
        FieldVector3D<T> fieldVector3DNormalize3 = fieldVector3D.normalize();
        FieldVector3D<T> fieldVector3DNormalize4 = FieldVector3D.crossProduct(fieldVector3D3, fieldVector3D4).normalize();
        FieldVector3D<T> fieldVector3DNormalize5 = FieldVector3D.crossProduct(fieldVector3DNormalize4, fieldVector3D3).normalize();
        FieldVector3D<T> fieldVector3DNormalize6 = fieldVector3D3.normalize();
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(fieldVector3DNormalize3.getX().getField(), 3, 3);
        realFieldElementArr[0][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getX().multiply(fieldVector3DNormalize6.getX())).add((RealFieldElement) fieldVector3DNormalize2.getX().multiply(fieldVector3DNormalize5.getX()))).add((RealFieldElement) fieldVector3DNormalize.getX().multiply(fieldVector3DNormalize4.getX()));
        realFieldElementArr[0][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getY().multiply(fieldVector3DNormalize6.getX())).add((RealFieldElement) fieldVector3DNormalize2.getY().multiply(fieldVector3DNormalize5.getX()))).add((RealFieldElement) fieldVector3DNormalize.getY().multiply(fieldVector3DNormalize4.getX()));
        realFieldElementArr[0][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getZ().multiply(fieldVector3DNormalize6.getX())).add((RealFieldElement) fieldVector3DNormalize2.getZ().multiply(fieldVector3DNormalize5.getX()))).add((RealFieldElement) fieldVector3DNormalize.getZ().multiply(fieldVector3DNormalize4.getX()));
        realFieldElementArr[1][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getX().multiply(fieldVector3DNormalize6.getY())).add((RealFieldElement) fieldVector3DNormalize2.getX().multiply(fieldVector3DNormalize5.getY()))).add((RealFieldElement) fieldVector3DNormalize.getX().multiply(fieldVector3DNormalize4.getY()));
        realFieldElementArr[1][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getY().multiply(fieldVector3DNormalize6.getY())).add((RealFieldElement) fieldVector3DNormalize2.getY().multiply(fieldVector3DNormalize5.getY()))).add((RealFieldElement) fieldVector3DNormalize.getY().multiply(fieldVector3DNormalize4.getY()));
        realFieldElementArr[1][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getZ().multiply(fieldVector3DNormalize6.getY())).add((RealFieldElement) fieldVector3DNormalize2.getZ().multiply(fieldVector3DNormalize5.getY()))).add((RealFieldElement) fieldVector3DNormalize.getZ().multiply(fieldVector3DNormalize4.getY()));
        realFieldElementArr[2][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getX().multiply(fieldVector3DNormalize6.getZ())).add((RealFieldElement) fieldVector3DNormalize2.getX().multiply(fieldVector3DNormalize5.getZ()))).add((RealFieldElement) fieldVector3DNormalize.getX().multiply(fieldVector3DNormalize4.getZ()));
        realFieldElementArr[2][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getY().multiply(fieldVector3DNormalize6.getZ())).add((RealFieldElement) fieldVector3DNormalize2.getY().multiply(fieldVector3DNormalize5.getZ()))).add((RealFieldElement) fieldVector3DNormalize.getY().multiply(fieldVector3DNormalize4.getZ()));
        realFieldElementArr[2][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldVector3DNormalize3.getZ().multiply(fieldVector3DNormalize6.getZ())).add((RealFieldElement) fieldVector3DNormalize2.getZ().multiply(fieldVector3DNormalize5.getZ()))).add((RealFieldElement) fieldVector3DNormalize.getZ().multiply(fieldVector3DNormalize4.getZ()));
        RealFieldElement[] realFieldElementArrMat2quat = mat2quat(realFieldElementArr);
        this.q0 = (T) realFieldElementArrMat2quat[0];
        this.q1 = (T) realFieldElementArrMat2quat[1];
        this.q2 = (T) realFieldElementArrMat2quat[2];
        this.q3 = (T) realFieldElementArrMat2quat[3];
    }

    public FieldRotation(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) fieldVector3D.getNorm().multiply(fieldVector3D2.getNorm());
        if (realFieldElement.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
        }
        RealFieldElement realFieldElementDotProduct = FieldVector3D.dotProduct(fieldVector3D, fieldVector3D2);
        if (realFieldElementDotProduct.getReal() < realFieldElement.getReal() * (-0.999999999999998d)) {
            FieldVector3D<T> fieldVector3DOrthogonal = fieldVector3D.orthogonal();
            this.q0 = (T) realFieldElement.getField().getZero();
            this.q1 = (T) fieldVector3DOrthogonal.getX().negate();
            this.q2 = (T) fieldVector3DOrthogonal.getY().negate();
            this.q3 = (T) fieldVector3DOrthogonal.getZ().negate();
            return;
        }
        T t = (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElementDotProduct.divide(realFieldElement)).add(1.0d)).multiply(0.5d)).sqrt();
        this.q0 = t;
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(realFieldElement)).multiply(2.0d)).reciprocal();
        FieldVector3D fieldVector3DCrossProduct = FieldVector3D.crossProduct(fieldVector3D2, fieldVector3D);
        this.q1 = (T) realFieldElement2.multiply(fieldVector3DCrossProduct.getX());
        this.q2 = (T) realFieldElement2.multiply(fieldVector3DCrossProduct.getY());
        this.q3 = (T) realFieldElement2.multiply(fieldVector3DCrossProduct.getZ());
    }

    @Deprecated
    public FieldRotation(RotationOrder rotationOrder, T t, T t2, T t3) {
        this(rotationOrder, RotationConvention.VECTOR_OPERATOR, t, t2, t3);
    }

    public FieldRotation(RotationOrder rotationOrder, RotationConvention rotationConvention, T t, T t2, T t3) {
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        FieldRotation<T> fieldRotationCompose = new FieldRotation(new FieldVector3D(realFieldElement, rotationOrder.getA1()), t, rotationConvention).compose(new FieldRotation(new FieldVector3D(realFieldElement, rotationOrder.getA2()), t2, rotationConvention).compose(new FieldRotation<>(new FieldVector3D(realFieldElement, rotationOrder.getA3()), t3, rotationConvention), rotationConvention), rotationConvention);
        this.q0 = fieldRotationCompose.q0;
        this.q1 = fieldRotationCompose.q1;
        this.q2 = fieldRotationCompose.q2;
        this.q3 = fieldRotationCompose.q3;
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyTo(Rotation rotation, FieldVector3D<T> fieldVector3D) {
        RealFieldElement x = fieldVector3D.getX();
        RealFieldElement y = fieldVector3D.getY();
        RealFieldElement z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ1())).add((RealFieldElement) y.multiply(rotation.getQ2()))).add((RealFieldElement) z.multiply(rotation.getQ3()));
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ0())).subtract((RealFieldElement) ((RealFieldElement) z.multiply(rotation.getQ2())).subtract((RealFieldElement) y.multiply(rotation.getQ3())))).multiply(rotation.getQ0())).add((RealFieldElement) realFieldElement.multiply(rotation.getQ1()))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) y.multiply(rotation.getQ0())).subtract((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ3())).subtract((RealFieldElement) z.multiply(rotation.getQ1())))).multiply(rotation.getQ0())).add((RealFieldElement) realFieldElement.multiply(rotation.getQ2()))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) z.multiply(rotation.getQ0())).subtract((RealFieldElement) ((RealFieldElement) y.multiply(rotation.getQ1())).subtract((RealFieldElement) x.multiply(rotation.getQ2())))).multiply(rotation.getQ0())).add((RealFieldElement) realFieldElement.multiply(rotation.getQ3()))).multiply(2)).subtract(z));
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyInverseTo(Rotation rotation, FieldVector3D<T> fieldVector3D) {
        RealFieldElement x = fieldVector3D.getX();
        RealFieldElement y = fieldVector3D.getY();
        RealFieldElement z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ1())).add((RealFieldElement) y.multiply(rotation.getQ2()))).add((RealFieldElement) z.multiply(rotation.getQ3()));
        double d = -rotation.getQ0();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(d)).subtract((RealFieldElement) ((RealFieldElement) z.multiply(rotation.getQ2())).subtract((RealFieldElement) y.multiply(rotation.getQ3())))).multiply(d)).add((RealFieldElement) realFieldElement.multiply(rotation.getQ1()))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) y.multiply(d)).subtract((RealFieldElement) ((RealFieldElement) x.multiply(rotation.getQ3())).subtract((RealFieldElement) z.multiply(rotation.getQ1())))).multiply(d)).add((RealFieldElement) realFieldElement.multiply(rotation.getQ2()))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) z.multiply(d)).subtract((RealFieldElement) ((RealFieldElement) y.multiply(rotation.getQ1())).subtract((RealFieldElement) x.multiply(rotation.getQ2())))).multiply(d)).add((RealFieldElement) realFieldElement.multiply(rotation.getQ3()))).multiply(2)).subtract(z));
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyTo(Rotation rotation, FieldRotation<T> fieldRotation) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ0())).subtract((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ1())).add((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ2()))).add((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ0())).add((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ1()))).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ3())).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ2()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ0())).add((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ2()))).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ1())).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ0())).add((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ3()))).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ2())).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ1()))), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyInverseTo(Rotation rotation, FieldRotation<T> fieldRotation) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ0())).add((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ1())).add((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ2()))).add((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ3())))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ1())).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ3())).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ2())))).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ0())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ2())).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ1())).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ3())))).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ0())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q0.multiply(rotation.getQ3())).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) fieldRotation).q1.multiply(rotation.getQ2())).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q2.multiply(rotation.getQ1())))).subtract((RealFieldElement) ((FieldRotation) fieldRotation).q3.multiply(rotation.getQ0())), false);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldRotation<T> fieldRotation, FieldRotation<T> fieldRotation2) {
        return (T) fieldRotation.composeInverseInternal(fieldRotation2).getAngle();
    }

    public T getQ0() {
        return this.q0;
    }

    public T getQ1() {
        return this.q1;
    }

    public T getQ2() {
        return this.q2;
    }

    public T getQ3() {
        return this.q3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T[] mat2quat(T[][] tArr) {
        T[] tArr2 = (T[]) ((RealFieldElement[]) MathArrays.buildArray(tArr[0][0].getField(), 4));
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) tArr[0][0].add(tArr[1][1])).add(tArr[2][2]);
        if (realFieldElement.getReal() > -0.19d) {
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(1.0d)).sqrt()).multiply(0.5d);
            tArr2[0] = realFieldElement2;
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) realFieldElement2.reciprocal()).multiply(0.25d);
            tArr2[1] = (RealFieldElement) realFieldElement3.multiply((RealFieldElement) tArr[1][2].subtract(tArr[2][1]));
            tArr2[2] = (RealFieldElement) realFieldElement3.multiply((RealFieldElement) tArr[2][0].subtract(tArr[0][2]));
            tArr2[3] = (RealFieldElement) realFieldElement3.multiply((RealFieldElement) tArr[0][1].subtract(tArr[1][0]));
        } else {
            RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) tArr[0][0].subtract(tArr[1][1])).subtract(tArr[2][2]);
            if (realFieldElement4.getReal() > -0.19d) {
                RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement4.add(1.0d)).sqrt()).multiply(0.5d);
                tArr2[1] = realFieldElement5;
                RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) realFieldElement5.reciprocal()).multiply(0.25d);
                tArr2[0] = (RealFieldElement) realFieldElement6.multiply((RealFieldElement) tArr[1][2].subtract(tArr[2][1]));
                tArr2[2] = (RealFieldElement) realFieldElement6.multiply((RealFieldElement) tArr[0][1].add(tArr[1][0]));
                tArr2[3] = (RealFieldElement) realFieldElement6.multiply((RealFieldElement) tArr[0][2].add(tArr[2][0]));
            } else {
                RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) tArr[1][1].subtract(tArr[0][0])).subtract(tArr[2][2]);
                if (realFieldElement7.getReal() > -0.19d) {
                    RealFieldElement realFieldElement8 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement7.add(1.0d)).sqrt()).multiply(0.5d);
                    tArr2[2] = realFieldElement8;
                    RealFieldElement realFieldElement9 = (RealFieldElement) ((RealFieldElement) realFieldElement8.reciprocal()).multiply(0.25d);
                    tArr2[0] = (RealFieldElement) realFieldElement9.multiply((RealFieldElement) tArr[2][0].subtract(tArr[0][2]));
                    tArr2[1] = (RealFieldElement) realFieldElement9.multiply((RealFieldElement) tArr[0][1].add(tArr[1][0]));
                    tArr2[3] = (RealFieldElement) realFieldElement9.multiply((RealFieldElement) tArr[2][1].add(tArr[1][2]));
                } else {
                    RealFieldElement realFieldElement10 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[2][2].subtract(tArr[0][0])).subtract(tArr[1][1])).add(1.0d)).sqrt()).multiply(0.5d);
                    tArr2[3] = realFieldElement10;
                    RealFieldElement realFieldElement11 = (RealFieldElement) ((RealFieldElement) realFieldElement10.reciprocal()).multiply(0.25d);
                    tArr2[0] = (RealFieldElement) realFieldElement11.multiply((RealFieldElement) tArr[0][1].subtract(tArr[1][0]));
                    tArr2[1] = (RealFieldElement) realFieldElement11.multiply((RealFieldElement) tArr[0][2].add(tArr[2][0]));
                    tArr2[2] = (RealFieldElement) realFieldElement11.multiply((RealFieldElement) tArr[2][1].add(tArr[1][2]));
                }
            }
        }
        return tArr2;
    }

    public FieldRotation<T> revert() {
        return new FieldRotation<>((RealFieldElement) this.q0.negate(), (RealFieldElement) this.q1, (RealFieldElement) this.q2, (RealFieldElement) this.q3, false);
    }

    @Deprecated
    public FieldVector3D<T> getAxis() {
        return getAxis(RotationConvention.VECTOR_OPERATOR);
    }

    public FieldVector3D<T> getAxis(RotationConvention rotationConvention) {
        T t = this.q1;
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
        T t2 = this.q2;
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.add((RealFieldElement) t2.multiply(t2));
        T t3 = this.q3;
        RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement2.add((RealFieldElement) t3.multiply(t3));
        if (realFieldElement3.getReal() == 0.0d) {
            Field<T> field = realFieldElement3.getField();
            return new FieldVector3D<>((RealFieldElement) (rotationConvention == RotationConvention.VECTOR_OPERATOR ? field.getOne() : ((RealFieldElement) field.getOne()).negate()), (RealFieldElement) field.getZero(), (RealFieldElement) field.getZero());
        }
        double d = rotationConvention == RotationConvention.VECTOR_OPERATOR ? 1.0d : -1.0d;
        if (this.q0.getReal() < 0.0d) {
            RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement3.sqrt()).reciprocal()).multiply(d);
            return new FieldVector3D<>((RealFieldElement) this.q1.multiply(realFieldElement4), (RealFieldElement) this.q2.multiply(realFieldElement4), (RealFieldElement) this.q3.multiply(realFieldElement4));
        }
        RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement3.sqrt()).reciprocal()).negate()).multiply(d);
        return new FieldVector3D<>((RealFieldElement) this.q1.multiply(realFieldElement5), (RealFieldElement) this.q2.multiply(realFieldElement5), (RealFieldElement) this.q3.multiply(realFieldElement5));
    }

    public T getAngle() {
        if (this.q0.getReal() >= -0.1d && this.q0.getReal() <= 0.1d) {
            if (this.q0.getReal() < 0.0d) {
                return (T) ((RealFieldElement) ((RealFieldElement) this.q0.negate()).acos()).multiply(2);
            }
            return (T) ((RealFieldElement) this.q0.acos()).multiply(2);
        }
        T t = this.q1;
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
        T t2 = this.q2;
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.add((RealFieldElement) t2.multiply(t2));
        T t3 = this.q3;
        return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.add((RealFieldElement) t3.multiply(t3))).sqrt()).asin()).multiply(2);
    }

    @Deprecated
    public T[] getAngles(RotationOrder rotationOrder) throws CardanEulerSingularityException {
        return (T[]) getAngles(rotationOrder, RotationConvention.VECTOR_OPERATOR);
    }

    public T[] getAngles(RotationOrder rotationOrder, RotationConvention rotationConvention) throws CardanEulerSingularityException {
        if (rotationConvention == RotationConvention.VECTOR_OPERATOR) {
            if (rotationOrder == RotationOrder.XYZ) {
                FieldVector3D<T> fieldVector3DApplyTo = applyTo(vector(0.0d, 0.0d, 1.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                if (fieldVector3DApplyInverseTo.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo.getZ().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) ((RealFieldElement) fieldVector3DApplyTo.getY().negate()).atan2(fieldVector3DApplyTo.getZ()), (RealFieldElement) fieldVector3DApplyInverseTo.getZ().asin(), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo.getY().negate()).atan2(fieldVector3DApplyInverseTo.getX()));
            }
            if (rotationOrder == RotationOrder.XZY) {
                FieldVector3D<T> fieldVector3DApplyTo2 = applyTo(vector(0.0d, 1.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo2 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                if (fieldVector3DApplyInverseTo2.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo2.getY().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo2.getZ().atan2(fieldVector3DApplyTo2.getY()), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo2.getY().asin()).negate(), (RealFieldElement) fieldVector3DApplyInverseTo2.getZ().atan2(fieldVector3DApplyInverseTo2.getX()));
            }
            if (rotationOrder == RotationOrder.YXZ) {
                FieldVector3D<T> fieldVector3DApplyTo3 = applyTo(vector(0.0d, 0.0d, 1.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo3 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                if (fieldVector3DApplyInverseTo3.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo3.getZ().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo3.getX().atan2(fieldVector3DApplyTo3.getZ()), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo3.getZ().asin()).negate(), (RealFieldElement) fieldVector3DApplyInverseTo3.getX().atan2(fieldVector3DApplyInverseTo3.getY()));
            }
            if (rotationOrder == RotationOrder.YZX) {
                FieldVector3D<T> fieldVector3DApplyTo4 = applyTo(vector(1.0d, 0.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo4 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                if (fieldVector3DApplyInverseTo4.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo4.getX().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) ((RealFieldElement) fieldVector3DApplyTo4.getZ().negate()).atan2(fieldVector3DApplyTo4.getX()), (RealFieldElement) fieldVector3DApplyInverseTo4.getX().asin(), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo4.getZ().negate()).atan2(fieldVector3DApplyInverseTo4.getY()));
            }
            if (rotationOrder == RotationOrder.ZXY) {
                FieldVector3D<T> fieldVector3DApplyTo5 = applyTo(vector(0.0d, 1.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo5 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                if (fieldVector3DApplyInverseTo5.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo5.getY().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) ((RealFieldElement) fieldVector3DApplyTo5.getX().negate()).atan2(fieldVector3DApplyTo5.getY()), (RealFieldElement) fieldVector3DApplyInverseTo5.getY().asin(), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo5.getX().negate()).atan2(fieldVector3DApplyInverseTo5.getZ()));
            }
            if (rotationOrder == RotationOrder.ZYX) {
                FieldVector3D<T> fieldVector3DApplyTo6 = applyTo(vector(1.0d, 0.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo6 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                if (fieldVector3DApplyInverseTo6.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo6.getX().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo6.getY().atan2(fieldVector3DApplyTo6.getX()), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo6.getX().asin()).negate(), (RealFieldElement) fieldVector3DApplyInverseTo6.getY().atan2(fieldVector3DApplyInverseTo6.getZ()));
            }
            if (rotationOrder == RotationOrder.XYX) {
                FieldVector3D<T> fieldVector3DApplyTo7 = applyTo(vector(1.0d, 0.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo7 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                if (fieldVector3DApplyInverseTo7.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo7.getX().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo7.getY().atan2(fieldVector3DApplyTo7.getZ().negate()), (RealFieldElement) fieldVector3DApplyInverseTo7.getX().acos(), (RealFieldElement) fieldVector3DApplyInverseTo7.getY().atan2(fieldVector3DApplyInverseTo7.getZ()));
            }
            if (rotationOrder == RotationOrder.XZX) {
                FieldVector3D<T> fieldVector3DApplyTo8 = applyTo(vector(1.0d, 0.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo8 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                if (fieldVector3DApplyInverseTo8.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo8.getX().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo8.getZ().atan2(fieldVector3DApplyTo8.getY()), (RealFieldElement) fieldVector3DApplyInverseTo8.getX().acos(), (RealFieldElement) fieldVector3DApplyInverseTo8.getZ().atan2(fieldVector3DApplyInverseTo8.getY().negate()));
            }
            if (rotationOrder == RotationOrder.YXY) {
                FieldVector3D<T> fieldVector3DApplyTo9 = applyTo(vector(0.0d, 1.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo9 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                if (fieldVector3DApplyInverseTo9.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo9.getY().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo9.getX().atan2(fieldVector3DApplyTo9.getZ()), (RealFieldElement) fieldVector3DApplyInverseTo9.getY().acos(), (RealFieldElement) fieldVector3DApplyInverseTo9.getX().atan2(fieldVector3DApplyInverseTo9.getZ().negate()));
            }
            if (rotationOrder == RotationOrder.YZY) {
                FieldVector3D<T> fieldVector3DApplyTo10 = applyTo(vector(0.0d, 1.0d, 0.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo10 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                if (fieldVector3DApplyInverseTo10.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo10.getY().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo10.getZ().atan2(fieldVector3DApplyTo10.getX().negate()), (RealFieldElement) fieldVector3DApplyInverseTo10.getY().acos(), (RealFieldElement) fieldVector3DApplyInverseTo10.getZ().atan2(fieldVector3DApplyInverseTo10.getX()));
            }
            if (rotationOrder == RotationOrder.ZXZ) {
                FieldVector3D<T> fieldVector3DApplyTo11 = applyTo(vector(0.0d, 0.0d, 1.0d));
                FieldVector3D<T> fieldVector3DApplyInverseTo11 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                if (fieldVector3DApplyInverseTo11.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo11.getZ().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo11.getX().atan2(fieldVector3DApplyTo11.getY().negate()), (RealFieldElement) fieldVector3DApplyInverseTo11.getZ().acos(), (RealFieldElement) fieldVector3DApplyInverseTo11.getX().atan2(fieldVector3DApplyInverseTo11.getY()));
            }
            FieldVector3D<T> fieldVector3DApplyTo12 = applyTo(vector(0.0d, 0.0d, 1.0d));
            FieldVector3D<T> fieldVector3DApplyInverseTo12 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
            if (fieldVector3DApplyInverseTo12.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo12.getZ().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyTo12.getY().atan2(fieldVector3DApplyTo12.getX()), (RealFieldElement) fieldVector3DApplyInverseTo12.getZ().acos(), (RealFieldElement) fieldVector3DApplyInverseTo12.getY().atan2(fieldVector3DApplyInverseTo12.getX().negate()));
        }
        if (rotationOrder == RotationOrder.XYZ) {
            FieldVector3D<T> fieldVector3DApplyTo13 = applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> fieldVector3DApplyInverseTo13 = applyInverseTo(Vector3D.PLUS_K);
            if (fieldVector3DApplyInverseTo13.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo13.getX().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo13.getY().negate()).atan2(fieldVector3DApplyInverseTo13.getZ()), (RealFieldElement) fieldVector3DApplyInverseTo13.getX().asin(), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyTo13.getY().negate()).atan2(fieldVector3DApplyTo13.getX()));
        }
        if (rotationOrder == RotationOrder.XZY) {
            FieldVector3D<T> fieldVector3DApplyTo14 = applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> fieldVector3DApplyInverseTo14 = applyInverseTo(Vector3D.PLUS_J);
            if (fieldVector3DApplyInverseTo14.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo14.getX().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo14.getZ().atan2(fieldVector3DApplyInverseTo14.getY()), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo14.getX().asin()).negate(), (RealFieldElement) fieldVector3DApplyTo14.getZ().atan2(fieldVector3DApplyTo14.getX()));
        }
        if (rotationOrder == RotationOrder.YXZ) {
            FieldVector3D<T> fieldVector3DApplyTo15 = applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> fieldVector3DApplyInverseTo15 = applyInverseTo(Vector3D.PLUS_K);
            if (fieldVector3DApplyInverseTo15.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo15.getY().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo15.getX().atan2(fieldVector3DApplyInverseTo15.getZ()), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo15.getY().asin()).negate(), (RealFieldElement) fieldVector3DApplyTo15.getX().atan2(fieldVector3DApplyTo15.getY()));
        }
        if (rotationOrder == RotationOrder.YZX) {
            FieldVector3D<T> fieldVector3DApplyTo16 = applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> fieldVector3DApplyInverseTo16 = applyInverseTo(Vector3D.PLUS_I);
            if (fieldVector3DApplyInverseTo16.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo16.getY().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo16.getZ().negate()).atan2(fieldVector3DApplyInverseTo16.getX()), (RealFieldElement) fieldVector3DApplyInverseTo16.getY().asin(), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyTo16.getZ().negate()).atan2(fieldVector3DApplyTo16.getY()));
        }
        if (rotationOrder == RotationOrder.ZXY) {
            FieldVector3D<T> fieldVector3DApplyTo17 = applyTo(Vector3D.PLUS_K);
            FieldVector3D<T> fieldVector3DApplyInverseTo17 = applyInverseTo(Vector3D.PLUS_J);
            if (fieldVector3DApplyInverseTo17.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo17.getZ().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo17.getX().negate()).atan2(fieldVector3DApplyInverseTo17.getY()), (RealFieldElement) fieldVector3DApplyInverseTo17.getZ().asin(), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyTo17.getX().negate()).atan2(fieldVector3DApplyTo17.getZ()));
        }
        if (rotationOrder == RotationOrder.ZYX) {
            FieldVector3D<T> fieldVector3DApplyTo18 = applyTo(Vector3D.PLUS_K);
            FieldVector3D<T> fieldVector3DApplyInverseTo18 = applyInverseTo(Vector3D.PLUS_I);
            if (fieldVector3DApplyInverseTo18.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo18.getZ().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo18.getY().atan2(fieldVector3DApplyInverseTo18.getX()), (RealFieldElement) ((RealFieldElement) fieldVector3DApplyInverseTo18.getZ().asin()).negate(), (RealFieldElement) fieldVector3DApplyTo18.getY().atan2(fieldVector3DApplyTo18.getZ()));
        }
        if (rotationOrder == RotationOrder.XYX) {
            FieldVector3D<T> fieldVector3DApplyTo19 = applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> fieldVector3DApplyInverseTo19 = applyInverseTo(Vector3D.PLUS_I);
            if (fieldVector3DApplyInverseTo19.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo19.getX().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo19.getY().atan2(fieldVector3DApplyInverseTo19.getZ().negate()), (RealFieldElement) fieldVector3DApplyInverseTo19.getX().acos(), (RealFieldElement) fieldVector3DApplyTo19.getY().atan2(fieldVector3DApplyTo19.getZ()));
        }
        if (rotationOrder == RotationOrder.XZX) {
            FieldVector3D<T> fieldVector3DApplyTo20 = applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> fieldVector3DApplyInverseTo20 = applyInverseTo(Vector3D.PLUS_I);
            if (fieldVector3DApplyInverseTo20.getX().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo20.getX().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo20.getZ().atan2(fieldVector3DApplyInverseTo20.getY()), (RealFieldElement) fieldVector3DApplyInverseTo20.getX().acos(), (RealFieldElement) fieldVector3DApplyTo20.getZ().atan2(fieldVector3DApplyTo20.getY().negate()));
        }
        if (rotationOrder == RotationOrder.YXY) {
            FieldVector3D<T> fieldVector3DApplyTo21 = applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> fieldVector3DApplyInverseTo21 = applyInverseTo(Vector3D.PLUS_J);
            if (fieldVector3DApplyInverseTo21.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo21.getY().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo21.getX().atan2(fieldVector3DApplyInverseTo21.getZ()), (RealFieldElement) fieldVector3DApplyInverseTo21.getY().acos(), (RealFieldElement) fieldVector3DApplyTo21.getX().atan2(fieldVector3DApplyTo21.getZ().negate()));
        }
        if (rotationOrder == RotationOrder.YZY) {
            FieldVector3D<T> fieldVector3DApplyTo22 = applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> fieldVector3DApplyInverseTo22 = applyInverseTo(Vector3D.PLUS_J);
            if (fieldVector3DApplyInverseTo22.getY().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo22.getY().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo22.getZ().atan2(fieldVector3DApplyInverseTo22.getX().negate()), (RealFieldElement) fieldVector3DApplyInverseTo22.getY().acos(), (RealFieldElement) fieldVector3DApplyTo22.getZ().atan2(fieldVector3DApplyTo22.getX()));
        }
        if (rotationOrder == RotationOrder.ZXZ) {
            FieldVector3D<T> fieldVector3DApplyTo23 = applyTo(Vector3D.PLUS_K);
            FieldVector3D<T> fieldVector3DApplyInverseTo23 = applyInverseTo(Vector3D.PLUS_K);
            if (fieldVector3DApplyInverseTo23.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo23.getZ().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo23.getX().atan2(fieldVector3DApplyInverseTo23.getY().negate()), (RealFieldElement) fieldVector3DApplyInverseTo23.getZ().acos(), (RealFieldElement) fieldVector3DApplyTo23.getX().atan2(fieldVector3DApplyTo23.getY()));
        }
        FieldVector3D<T> fieldVector3DApplyTo24 = applyTo(Vector3D.PLUS_K);
        FieldVector3D<T> fieldVector3DApplyInverseTo24 = applyInverseTo(Vector3D.PLUS_K);
        if (fieldVector3DApplyInverseTo24.getZ().getReal() < -0.9999999999d || fieldVector3DApplyInverseTo24.getZ().getReal() > 0.9999999999d) {
            throw new CardanEulerSingularityException(false);
        }
        return (T[]) buildArray((RealFieldElement) fieldVector3DApplyInverseTo24.getY().atan2(fieldVector3DApplyInverseTo24.getX()), (RealFieldElement) fieldVector3DApplyInverseTo24.getZ().acos(), (RealFieldElement) fieldVector3DApplyTo24.getY().atan2(fieldVector3DApplyTo24.getX().negate()));
    }

    private T[] buildArray(T t, T t2, T t3) {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(t.getField(), 3));
        tArr[0] = t;
        tArr[1] = t2;
        tArr[2] = t3;
        return tArr;
    }

    private FieldVector3D<T> vector(double d, double d2, double d3) {
        RealFieldElement realFieldElement = (RealFieldElement) this.q0.getField().getZero();
        return new FieldVector3D<>((RealFieldElement) realFieldElement.add(d), (RealFieldElement) realFieldElement.add(d2), (RealFieldElement) realFieldElement.add(d3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[][] getMatrix() {
        T t = this.q0;
        RealFieldElement realFieldElement = (RealFieldElement) t.multiply(t);
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.multiply(this.q1);
        RealFieldElement realFieldElement3 = (RealFieldElement) this.q0.multiply(this.q2);
        RealFieldElement realFieldElement4 = (RealFieldElement) this.q0.multiply(this.q3);
        T t2 = this.q1;
        RealFieldElement realFieldElement5 = (RealFieldElement) t2.multiply(t2);
        RealFieldElement realFieldElement6 = (RealFieldElement) this.q1.multiply(this.q2);
        RealFieldElement realFieldElement7 = (RealFieldElement) this.q1.multiply(this.q3);
        T t3 = this.q2;
        RealFieldElement realFieldElement8 = (RealFieldElement) t3.multiply(t3);
        RealFieldElement realFieldElement9 = (RealFieldElement) this.q2.multiply(this.q3);
        T t4 = this.q3;
        RealFieldElement realFieldElement10 = (RealFieldElement) t4.multiply(t4);
        T[][] tArr = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(this.q0.getField(), 3, 3));
        tArr[0][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(realFieldElement5)).multiply(2)).subtract(1.0d);
        tArr[1][0] = (RealFieldElement) ((RealFieldElement) realFieldElement6.subtract(realFieldElement4)).multiply(2);
        tArr[2][0] = (RealFieldElement) ((RealFieldElement) realFieldElement7.add(realFieldElement3)).multiply(2);
        tArr[0][1] = (RealFieldElement) ((RealFieldElement) realFieldElement6.add(realFieldElement4)).multiply(2);
        tArr[1][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(realFieldElement8)).multiply(2)).subtract(1.0d);
        tArr[2][1] = (RealFieldElement) ((RealFieldElement) realFieldElement9.subtract(realFieldElement2)).multiply(2);
        tArr[0][2] = (RealFieldElement) ((RealFieldElement) realFieldElement7.subtract(realFieldElement3)).multiply(2);
        tArr[1][2] = (RealFieldElement) ((RealFieldElement) realFieldElement9.add(realFieldElement2)).multiply(2);
        tArr[2][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(realFieldElement10)).multiply(2)).subtract(1.0d);
        return tArr;
    }

    public Rotation toRotation() {
        return new Rotation(this.q0.getReal(), this.q1.getReal(), this.q2.getReal(), this.q3.getReal(), false);
    }

    public FieldVector3D<T> applyTo(FieldVector3D<T> fieldVector3D) {
        RealFieldElement x = fieldVector3D.getX();
        RealFieldElement y = fieldVector3D.getY();
        RealFieldElement z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        T t = this.q0;
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(((RealFieldElement) x.multiply(t)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x);
        T t2 = this.q0;
        RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) y.multiply(t2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y);
        T t3 = this.q0;
        return new FieldVector3D<>(realFieldElement2, realFieldElement3, (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(((RealFieldElement) z.multiply(t3)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        T t = this.q0;
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(((RealFieldElement) t.multiply(x)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x);
        T t2 = this.q0;
        RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) t2.multiply(y)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y);
        T t3 = this.q0;
        return new FieldVector3D<>(realFieldElement2, realFieldElement3, (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(((RealFieldElement) t3.multiply(z)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyTo(T[] tArr, T[] tArr2) {
        T t = tArr[0];
        T t2 = tArr[1];
        T t3 = tArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(t)).add((RealFieldElement) this.q2.multiply(t2))).add((RealFieldElement) this.q3.multiply(t3));
        T t4 = this.q0;
        tArr2[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t4.multiply(((RealFieldElement) t.multiply(t4)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(t3)).subtract((RealFieldElement) this.q3.multiply(t2))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(t);
        T t5 = this.q0;
        tArr2[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t5.multiply(((RealFieldElement) t2.multiply(t5)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(t)).subtract((RealFieldElement) this.q1.multiply(t3))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(t2);
        T t6 = this.q0;
        tArr2[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(((RealFieldElement) t3.multiply(t6)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(t2)).subtract((RealFieldElement) this.q2.multiply(t))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(t3);
    }

    public void applyTo(double[] dArr, T[] tArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(d)).add((RealFieldElement) this.q2.multiply(d2))).add((RealFieldElement) this.q3.multiply(d3));
        T t = this.q0;
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t.multiply(((RealFieldElement) t.multiply(d)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(d3)).subtract((RealFieldElement) this.q3.multiply(d2))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(d);
        T t2 = this.q0;
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) t2.multiply(d2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(d)).subtract((RealFieldElement) this.q1.multiply(d3))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(d2);
        T t3 = this.q0;
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(((RealFieldElement) t3.multiply(d3)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(d2)).subtract((RealFieldElement) this.q2.multiply(d))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(d3);
    }

    public FieldVector3D<T> applyInverseTo(FieldVector3D<T> fieldVector3D) {
        RealFieldElement x = fieldVector3D.getX();
        RealFieldElement y = fieldVector3D.getY();
        RealFieldElement z = fieldVector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) x.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) y.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) z.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(x)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(y)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(z)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyInverseTo(T[] tArr, T[] tArr2) {
        T t = tArr[0];
        T t2 = tArr[1];
        T t3 = tArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(t)).add((RealFieldElement) this.q2.multiply(t2))).add((RealFieldElement) this.q3.multiply(t3));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        tArr2[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) t.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(t3)).subtract((RealFieldElement) this.q3.multiply(t2))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(t);
        tArr2[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(t)).subtract((RealFieldElement) this.q1.multiply(t3))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(t2);
        tArr2[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) t3.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(t2)).subtract((RealFieldElement) this.q2.multiply(t))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(t3);
    }

    public void applyInverseTo(double[] dArr, T[] tArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(d)).add((RealFieldElement) this.q2.multiply(d2))).add((RealFieldElement) this.q3.multiply(d3));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(d)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(d3)).subtract((RealFieldElement) this.q3.multiply(d2))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(d);
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(d2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(d)).subtract((RealFieldElement) this.q1.multiply(d3))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(d2);
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(d3)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(d2)).subtract((RealFieldElement) this.q2.multiply(d))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(d3);
    }

    public FieldRotation<T> applyTo(FieldRotation<T> fieldRotation) {
        return compose(fieldRotation, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> compose(FieldRotation<T> fieldRotation, RotationConvention rotationConvention) {
        return rotationConvention == RotationConvention.VECTOR_OPERATOR ? composeInternal(fieldRotation) : fieldRotation.composeInternal(this);
    }

    private FieldRotation<T> composeInternal(FieldRotation<T> fieldRotation) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q1)).add((RealFieldElement) fieldRotation.q2.multiply(this.q2))).add((RealFieldElement) fieldRotation.q3.multiply(this.q3))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q0)).add((RealFieldElement) fieldRotation.q0.multiply(this.q1))).add((RealFieldElement) ((RealFieldElement) fieldRotation.q2.multiply(this.q3)).subtract((RealFieldElement) fieldRotation.q3.multiply(this.q2))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q2.multiply(this.q0)).add((RealFieldElement) fieldRotation.q0.multiply(this.q2))).add((RealFieldElement) ((RealFieldElement) fieldRotation.q3.multiply(this.q1)).subtract((RealFieldElement) fieldRotation.q1.multiply(this.q3))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q3.multiply(this.q0)).add((RealFieldElement) fieldRotation.q0.multiply(this.q3))).add((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q2)).subtract((RealFieldElement) fieldRotation.q2.multiply(this.q1))), false);
    }

    public FieldRotation<T> applyTo(Rotation rotation) {
        return compose(rotation, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> compose(Rotation rotation, RotationConvention rotationConvention) {
        return rotationConvention == RotationConvention.VECTOR_OPERATOR ? composeInternal(rotation) : applyTo(rotation, this);
    }

    private FieldRotation<T> composeInternal(Rotation rotation) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ0())).subtract((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ1())).add((RealFieldElement) this.q2.multiply(rotation.getQ2()))).add((RealFieldElement) this.q3.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ1())).add((RealFieldElement) this.q1.multiply(rotation.getQ0()))).add((RealFieldElement) ((RealFieldElement) this.q3.multiply(rotation.getQ2())).subtract((RealFieldElement) this.q2.multiply(rotation.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ2())).add((RealFieldElement) this.q2.multiply(rotation.getQ0()))).add((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ3())).subtract((RealFieldElement) this.q3.multiply(rotation.getQ1()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ3())).add((RealFieldElement) this.q3.multiply(rotation.getQ0()))).add((RealFieldElement) ((RealFieldElement) this.q2.multiply(rotation.getQ1())).subtract((RealFieldElement) this.q1.multiply(rotation.getQ2()))), false);
    }

    public FieldRotation<T> applyInverseTo(FieldRotation<T> fieldRotation) {
        return composeInverse(fieldRotation, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> composeInverse(FieldRotation<T> fieldRotation, RotationConvention rotationConvention) {
        return rotationConvention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(fieldRotation) : fieldRotation.composeInternal(revert());
    }

    private FieldRotation<T> composeInverseInternal(FieldRotation<T> fieldRotation) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q0)).add((RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q1)).add((RealFieldElement) fieldRotation.q2.multiply(this.q2))).add((RealFieldElement) fieldRotation.q3.multiply(this.q3)))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q1)).add((RealFieldElement) ((RealFieldElement) fieldRotation.q2.multiply(this.q3)).subtract((RealFieldElement) fieldRotation.q3.multiply(this.q2)))).subtract((RealFieldElement) fieldRotation.q1.multiply(this.q0)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q2)).add((RealFieldElement) ((RealFieldElement) fieldRotation.q3.multiply(this.q1)).subtract((RealFieldElement) fieldRotation.q1.multiply(this.q3)))).subtract((RealFieldElement) fieldRotation.q2.multiply(this.q0)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) fieldRotation.q0.multiply(this.q3)).add((RealFieldElement) ((RealFieldElement) fieldRotation.q1.multiply(this.q2)).subtract((RealFieldElement) fieldRotation.q2.multiply(this.q1)))).subtract((RealFieldElement) fieldRotation.q3.multiply(this.q0)), false);
    }

    public FieldRotation<T> applyInverseTo(Rotation rotation) {
        return composeInverse(rotation, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> composeInverse(Rotation rotation, RotationConvention rotationConvention) {
        return rotationConvention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(rotation) : applyTo(rotation, revert());
    }

    private FieldRotation<T> composeInverseInternal(Rotation rotation) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(rotation.getQ0())).add((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ1())).add((RealFieldElement) this.q2.multiply(rotation.getQ2()))).add((RealFieldElement) this.q3.multiply(rotation.getQ3())))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ0())).add((RealFieldElement) ((RealFieldElement) this.q3.multiply(rotation.getQ2())).subtract((RealFieldElement) this.q2.multiply(rotation.getQ3())))).subtract((RealFieldElement) this.q0.multiply(rotation.getQ1())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q2.multiply(rotation.getQ0())).add((RealFieldElement) ((RealFieldElement) this.q1.multiply(rotation.getQ3())).subtract((RealFieldElement) this.q3.multiply(rotation.getQ1())))).subtract((RealFieldElement) this.q0.multiply(rotation.getQ2())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q3.multiply(rotation.getQ0())).add((RealFieldElement) ((RealFieldElement) this.q2.multiply(rotation.getQ1())).subtract((RealFieldElement) this.q1.multiply(rotation.getQ2())))).subtract((RealFieldElement) this.q0.multiply(rotation.getQ3())), false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v33, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r12v2, types: [T extends org.apache.commons.math3.RealFieldElement<T>[][], org.apache.commons.math3.RealFieldElement[][]] */
    /* JADX WARN: Type inference failed for: r15v48 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r3v45 */
    /* JADX WARN: Type inference failed for: r3v46, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    private T[][] orthogonalizeMatrix(T[][] tArr, double d) throws NotARotationMatrixException {
        char c = 0;
        T[] tArr2 = tArr[0];
        T t = tArr2[0];
        T t2 = tArr2[1];
        T t3 = tArr2[2];
        T[] tArr3 = tArr[1];
        T t4 = tArr3[0];
        T t5 = tArr3[1];
        T t6 = tArr3[2];
        T[] tArr4 = tArr[2];
        T t7 = tArr4[0];
        T t8 = tArr4[1];
        T t9 = tArr4[2];
        ??r12 = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(t.getField(), 3, 3));
        double d2 = 0.0d;
        int i = 0;
        T t10 = t3;
        T t11 = t;
        T t12 = t2;
        T t13 = t6;
        T t14 = t4;
        T t15 = t5;
        T t16 = t9;
        T t17 = t7;
        T t18 = t8;
        while (true) {
            int i2 = i + 1;
            if (i2 < 11) {
                RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[c][c].multiply(t11)).add((RealFieldElement) tArr[1][c].multiply(t14))).add((RealFieldElement) tArr[2][c].multiply(t17));
                RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[c][1].multiply(t11)).add((RealFieldElement) tArr[1][1].multiply(t14))).add((RealFieldElement) tArr[2][1].multiply(t17));
                double d3 = d2;
                RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][2].multiply(t11)).add((RealFieldElement) tArr[1][2].multiply(t14))).add((RealFieldElement) tArr[2][2].multiply(t17));
                RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][0].multiply(t12)).add((RealFieldElement) tArr[1][0].multiply(t15))).add((RealFieldElement) tArr[2][0].multiply(t18));
                T t19 = t17;
                RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][1].multiply(t12)).add((RealFieldElement) tArr[1][1].multiply(t15))).add((RealFieldElement) tArr[2][1].multiply(t18));
                T t20 = t14;
                RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][2].multiply(t12)).add((RealFieldElement) tArr[1][2].multiply(t15))).add((RealFieldElement) tArr[2][2].multiply(t18));
                T t21 = t18;
                RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][0].multiply(t10)).add((RealFieldElement) tArr[1][0].multiply(t13))).add((RealFieldElement) tArr[2][0].multiply(t16));
                RealFieldElement realFieldElement8 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][1].multiply(t10)).add((RealFieldElement) tArr[1][1].multiply(t13))).add((RealFieldElement) tArr[2][1].multiply(t16));
                T t22 = t15;
                RealFieldElement realFieldElement9 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][2].multiply(t10)).add((RealFieldElement) tArr[1][2].multiply(t13))).add((RealFieldElement) tArr[2][2].multiply(t16));
                T t23 = t16;
                r12[0][0] = (RealFieldElement) t11.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t11.multiply(realFieldElement)).add((RealFieldElement) t12.multiply(realFieldElement2))).add((RealFieldElement) t10.multiply(realFieldElement3))).subtract(tArr[0][0])).multiply(0.5d));
                r12[0][1] = (RealFieldElement) t12.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t11.multiply(realFieldElement4)).add((RealFieldElement) t12.multiply(realFieldElement5))).add((RealFieldElement) t10.multiply(realFieldElement6))).subtract(tArr[0][1])).multiply(0.5d));
                r12[0][2] = (RealFieldElement) t10.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t11.multiply(realFieldElement7)).add((RealFieldElement) t12.multiply(realFieldElement8))).add((RealFieldElement) t10.multiply(realFieldElement9))).subtract(tArr[0][2])).multiply(0.5d));
                r12[1][0] = (RealFieldElement) t20.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t20.multiply(realFieldElement)).add((RealFieldElement) t22.multiply(realFieldElement2))).add((RealFieldElement) t13.multiply(realFieldElement3))).subtract(tArr[1][0])).multiply(0.5d));
                ??r2 = r12[1];
                RealFieldElement realFieldElement10 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t20.multiply(realFieldElement4)).add((RealFieldElement) t22.multiply(realFieldElement5))).add((RealFieldElement) t13.multiply(realFieldElement6))).subtract(tArr[1][1]);
                T t24 = t13;
                r2[1] = (RealFieldElement) t22.subtract(realFieldElement10.multiply(0.5d));
                r12[1][2] = (RealFieldElement) t24.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t20.multiply(realFieldElement7)).add((RealFieldElement) t22.multiply(realFieldElement8))).add((RealFieldElement) t24.multiply(realFieldElement9))).subtract(tArr[1][2])).multiply(0.5d));
                r12[2][0] = (RealFieldElement) t19.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t19.multiply(realFieldElement)).add((RealFieldElement) t21.multiply(realFieldElement2))).add((RealFieldElement) t23.multiply(realFieldElement3))).subtract(tArr[2][0])).multiply(0.5d));
                r12[2][1] = (RealFieldElement) t21.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t19.multiply(realFieldElement4)).add((RealFieldElement) t21.multiply(realFieldElement5))).add((RealFieldElement) t23.multiply(realFieldElement6))).subtract(tArr[2][1])).multiply(0.5d));
                r12[2][2] = (RealFieldElement) t23.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t19.multiply(realFieldElement7)).add((RealFieldElement) t21.multiply(realFieldElement8))).add((RealFieldElement) t23.multiply(realFieldElement9))).subtract(tArr[2][2])).multiply(0.5d));
                double real = r12[0][0].getReal() - tArr[0][0].getReal();
                double real2 = r12[0][1].getReal() - tArr[0][1].getReal();
                double real3 = r12[0][2].getReal() - tArr[0][2].getReal();
                double real4 = r12[1][0].getReal() - tArr[1][0].getReal();
                double real5 = r12[1][1].getReal() - tArr[1][1].getReal();
                double real6 = r12[1][2].getReal() - tArr[1][2].getReal();
                double real7 = r12[2][0].getReal() - tArr[2][0].getReal();
                double real8 = r12[2][1].getReal() - tArr[2][1].getReal();
                double real9 = r12[2][2].getReal() - tArr[2][2].getReal();
                d2 = (real * real) + (real2 * real2) + (real3 * real3) + (real4 * real4) + (real5 * real5) + (real6 * real6) + (real7 * real7) + (real8 * real8) + (real9 * real9);
                if (FastMath.abs(d2 - d3) <= d) {
                    return r12;
                }
                c = 0;
                ??r1 = r12[0];
                ??r22 = r1[0];
                ??r4 = r1[1];
                ??r13 = r1[2];
                ??r6 = r12[1];
                ??r7 = r6[0];
                ??r8 = r6[1];
                ??r62 = r6[2];
                ??r9 = r12[2];
                ??r10 = r9[0];
                ??r11 = r9[1];
                i = i2;
                t10 = r13;
                t11 = r22;
                t12 = r4;
                t13 = r62;
                t14 = r7;
                t15 = r8;
                t16 = r9[2];
                t17 = r10;
                t18 = r11;
            } else {
                LocalizedFormats localizedFormats = LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX;
                Object[] objArr = new Object[1];
                objArr[c] = Integer.valueOf(i);
                throw new NotARotationMatrixException(localizedFormats, objArr);
            }
        }
    }
}
