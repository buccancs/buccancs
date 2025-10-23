package org.apache.commons.math.linear;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/FieldVector.class */
public interface FieldVector<T extends FieldElement<T>> {
    Field<T> getField();

    FieldVector<T> copy();

    FieldVector<T> add(FieldVector<T> fieldVector) throws IllegalArgumentException;

    FieldVector<T> add(T[] tArr) throws IllegalArgumentException;

    FieldVector<T> subtract(FieldVector<T> fieldVector) throws IllegalArgumentException;

    FieldVector<T> subtract(T[] tArr) throws IllegalArgumentException;

    FieldVector<T> mapAdd(T t);

    FieldVector<T> mapAddToSelf(T t);

    FieldVector<T> mapSubtract(T t);

    FieldVector<T> mapSubtractToSelf(T t);

    FieldVector<T> mapMultiply(T t);

    FieldVector<T> mapMultiplyToSelf(T t);

    FieldVector<T> mapDivide(T t);

    FieldVector<T> mapDivideToSelf(T t);

    FieldVector<T> mapInv();

    FieldVector<T> mapInvToSelf();

    FieldVector<T> ebeMultiply(FieldVector<T> fieldVector) throws IllegalArgumentException;

    FieldVector<T> ebeMultiply(T[] tArr) throws IllegalArgumentException;

    FieldVector<T> ebeDivide(FieldVector<T> fieldVector) throws IllegalArgumentException;

    FieldVector<T> ebeDivide(T[] tArr) throws IllegalArgumentException;

    T[] getData();

    T dotProduct(FieldVector<T> fieldVector) throws IllegalArgumentException;

    T dotProduct(T[] tArr) throws IllegalArgumentException;

    FieldVector<T> projection(FieldVector<T> fieldVector) throws IllegalArgumentException;

    FieldVector<T> projection(T[] tArr) throws IllegalArgumentException;

    FieldMatrix<T> outerProduct(FieldVector<T> fieldVector) throws IllegalArgumentException;

    FieldMatrix<T> outerProduct(T[] tArr) throws IllegalArgumentException;

    T getEntry(int i) throws MatrixIndexException;

    void setEntry(int i, T t) throws MatrixIndexException;

    int getDimension();

    FieldVector<T> append(FieldVector<T> fieldVector);

    FieldVector<T> append(T t);

    FieldVector<T> append(T[] tArr);

    FieldVector<T> getSubVector(int i, int i2) throws MatrixIndexException;

    void setSubVector(int i, FieldVector<T> fieldVector) throws MatrixIndexException;

    void setSubVector(int i, T[] tArr) throws MatrixIndexException;

    void set(T t);

    T[] toArray();
}
