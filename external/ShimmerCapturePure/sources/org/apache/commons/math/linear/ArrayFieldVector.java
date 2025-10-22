package org.apache.commons.math.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/ArrayFieldVector.class */
public class ArrayFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {
    private static final long serialVersionUID = 7648186910365927050L;
    private final Field<T> field;
    protected T[] data;

    public ArrayFieldVector(Field<T> field) {
        this(field, 0);
    }

    public ArrayFieldVector(Field<T> field, int i) {
        this.field = field;
        this.data = (T[]) buildArray(i);
        Arrays.fill(this.data, field.getZero());
    }

    public ArrayFieldVector(int size, T preset) {
        this(preset.getField2(), size);
        Arrays.fill(this.data, preset);
    }

    public ArrayFieldVector(T[] tArr) throws IllegalArgumentException {
        try {
            this.field = tArr[0].getField2();
            this.data = (T[]) ((FieldElement[]) tArr.clone());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
    }

    public ArrayFieldVector(Field<T> field, T[] tArr) {
        this.field = field;
        this.data = (T[]) ((FieldElement[]) tArr.clone());
    }

    public ArrayFieldVector(T[] tArr, boolean z) throws IllegalArgumentException, NullPointerException {
        if (tArr.length == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
        this.field = tArr[0].getField2();
        this.data = z ? (T[]) ((FieldElement[]) tArr.clone()) : tArr;
    }

    public ArrayFieldVector(Field<T> field, T[] tArr, boolean z) {
        this.field = field;
        this.data = z ? (T[]) ((FieldElement[]) tArr.clone()) : tArr;
    }

    public ArrayFieldVector(T[] tArr, int i, int i2) {
        if (tArr.length < i + i2) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.POSITION_SIZE_MISMATCH_INPUT_ARRAY, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(tArr.length));
        }
        this.field = tArr[0].getField2();
        this.data = (T[]) buildArray(i2);
        System.arraycopy(tArr, i, this.data, 0, i2);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector) {
        this.field = fieldVector.getField();
        this.data = (T[]) buildArray(fieldVector.getDimension());
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = fieldVector.getEntry(i);
        }
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector) {
        this.field = arrayFieldVector.getField();
        this.data = (T[]) ((FieldElement[]) arrayFieldVector.data.clone());
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, boolean z) {
        this.field = arrayFieldVector.getField();
        this.data = z ? (T[]) ((FieldElement[]) arrayFieldVector.data.clone()) : arrayFieldVector.data;
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, ArrayFieldVector<T> arrayFieldVector2) {
        this.field = arrayFieldVector.getField();
        this.data = (T[]) buildArray(arrayFieldVector.data.length + arrayFieldVector2.data.length);
        System.arraycopy(arrayFieldVector.data, 0, this.data, 0, arrayFieldVector.data.length);
        System.arraycopy(arrayFieldVector2.data, 0, this.data, arrayFieldVector.data.length, arrayFieldVector2.data.length);
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, T[] tArr) {
        this.field = arrayFieldVector.getField();
        this.data = (T[]) buildArray(arrayFieldVector.data.length + tArr.length);
        System.arraycopy(arrayFieldVector.data, 0, this.data, 0, arrayFieldVector.data.length);
        System.arraycopy(tArr, 0, this.data, arrayFieldVector.data.length, tArr.length);
    }

    public ArrayFieldVector(T[] tArr, ArrayFieldVector<T> arrayFieldVector) {
        this.field = arrayFieldVector.getField();
        this.data = (T[]) buildArray(tArr.length + arrayFieldVector.data.length);
        System.arraycopy(tArr, 0, this.data, 0, tArr.length);
        System.arraycopy(arrayFieldVector.data, 0, this.data, tArr.length, arrayFieldVector.data.length);
    }

    public ArrayFieldVector(T[] tArr, T[] tArr2) {
        try {
            this.data = (T[]) buildArray(tArr.length + tArr2.length);
            System.arraycopy(tArr, 0, this.data, 0, tArr.length);
            System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
            this.field = this.data[0].getField2();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
    }

    public ArrayFieldVector(Field<T> field, T[] tArr, T[] tArr2) {
        if (tArr.length + tArr2.length == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
        this.data = (T[]) buildArray(tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, this.data, 0, tArr.length);
        System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
        this.field = this.data[0].getField2();
    }

    private T[] buildArray(int i) {
        return (T[]) ((FieldElement[]) Array.newInstance(this.field.getZero().getClass(), i));
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public Field<T> getField() {
        return this.field;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> copy() {
        return new ArrayFieldVector((ArrayFieldVector) this, true);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> add(FieldVector<T> v) throws IllegalArgumentException {
        try {
            return add((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArrBuildArray[i] = (FieldElement) this.data[i].add(v.getEntry(i));
            }
            return new ArrayFieldVector(fieldElementArrBuildArray);
        }
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> add(T[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].add(v[i]);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    public ArrayFieldVector<T> add(ArrayFieldVector<T> v) throws IllegalArgumentException {
        return (ArrayFieldVector) add(v.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> subtract(FieldVector<T> v) throws IllegalArgumentException {
        try {
            return subtract((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArrBuildArray[i] = (FieldElement) this.data[i].subtract(v.getEntry(i));
            }
            return new ArrayFieldVector(fieldElementArrBuildArray);
        }
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> subtract(T[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].subtract(v[i]);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    public ArrayFieldVector<T> subtract(ArrayFieldVector<T> v) throws IllegalArgumentException {
        return (ArrayFieldVector) subtract(v.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapAdd(T d) {
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].add(d);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapAddToSelf(T t) {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].add(t);
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapSubtract(T d) {
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].subtract(d);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapSubtractToSelf(T t) {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].subtract(t);
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapMultiply(T d) {
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].multiply(d);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapMultiplyToSelf(T t) {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].multiply(t);
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapDivide(T d) {
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].divide(d);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapDivideToSelf(T t) {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].divide(t);
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapInv() {
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        T one = this.field.getOne();
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) one.divide(this.data[i]);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> mapInvToSelf() {
        T one = this.field.getOne();
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) one.divide(this.data[i]);
        }
        return this;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> ebeMultiply(FieldVector<T> v) throws IllegalArgumentException {
        try {
            return ebeMultiply((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArrBuildArray[i] = (FieldElement) this.data[i].multiply(v.getEntry(i));
            }
            return new ArrayFieldVector(fieldElementArrBuildArray);
        }
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> ebeMultiply(T[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].multiply(v[i]);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    public ArrayFieldVector<T> ebeMultiply(ArrayFieldVector<T> v) throws IllegalArgumentException {
        return (ArrayFieldVector) ebeMultiply(v.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> ebeDivide(FieldVector<T> v) throws IllegalArgumentException {
        try {
            return ebeDivide((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArrBuildArray[i] = (FieldElement) this.data[i].divide(v.getEntry(i));
            }
            return new ArrayFieldVector(fieldElementArrBuildArray);
        }
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> ebeDivide(T[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArrBuildArray[i] = (FieldElement) this.data[i].divide(v[i]);
        }
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    public ArrayFieldVector<T> ebeDivide(ArrayFieldVector<T> v) throws IllegalArgumentException {
        return (ArrayFieldVector) ebeDivide(v.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public T[] getData() {
        return (T[]) ((FieldElement[]) this.data.clone());
    }

    public T[] getDataRef() {
        return this.data;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [org.apache.commons.math.FieldElement] */
    @Override // org.apache.commons.math.linear.FieldVector
    public T dotProduct(FieldVector<T> fieldVector) throws IllegalArgumentException {
        try {
            return (T) dotProduct((ArrayFieldVector) fieldVector);
        } catch (ClassCastException e) {
            checkVectorDimensions(fieldVector);
            T zero = this.field.getZero();
            for (int i = 0; i < this.data.length; i++) {
                zero = (FieldElement) zero.add(this.data[i].multiply(fieldVector.getEntry(i)));
            }
            return zero;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [org.apache.commons.math.FieldElement] */
    @Override // org.apache.commons.math.linear.FieldVector
    public T dotProduct(T[] v) throws IllegalArgumentException {
        checkVectorDimensions(v.length);
        T dot = this.field.getZero();
        for (int i = 0; i < this.data.length; i++) {
            dot = (FieldElement) dot.add(this.data[i].multiply(v[i]));
        }
        return dot;
    }

    public T dotProduct(ArrayFieldVector<T> arrayFieldVector) throws IllegalArgumentException {
        return (T) dotProduct(arrayFieldVector.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> projection(FieldVector<T> v) {
        return v.mapMultiply((FieldElement) dotProduct(v).divide(v.dotProduct(v)));
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> projection(T[] v) {
        return projection((ArrayFieldVector) new ArrayFieldVector<>((FieldElement[]) v, false));
    }

    public ArrayFieldVector<T> projection(ArrayFieldVector<T> v) {
        return (ArrayFieldVector) v.mapMultiply((FieldElement) dotProduct((ArrayFieldVector) v).divide(v.dotProduct((ArrayFieldVector) v)));
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldMatrix<T> outerProduct(FieldVector<T> v) throws MatrixIndexException, IllegalArgumentException {
        try {
            return outerProduct((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            int m = this.data.length;
            FieldMatrix<T> out = new Array2DRowFieldMatrix<>(this.field, m, m);
            for (int i = 0; i < this.data.length; i++) {
                for (int j = 0; j < this.data.length; j++) {
                    out.setEntry(i, j, (FieldElement) this.data[i].multiply(v.getEntry(j)));
                }
            }
            return out;
        }
    }

    public FieldMatrix<T> outerProduct(ArrayFieldVector<T> v) throws IllegalArgumentException {
        return outerProduct(v.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldMatrix<T> outerProduct(T[] v) throws MatrixIndexException, IllegalArgumentException {
        checkVectorDimensions(v.length);
        int m = this.data.length;
        FieldMatrix<T> out = new Array2DRowFieldMatrix<>(this.field, m, m);
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                out.setEntry(i, j, (FieldElement) this.data[i].multiply(v[j]));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public T getEntry(int index) throws MatrixIndexException {
        return this.data[index];
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public int getDimension() {
        return this.data.length;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> append(FieldVector<T> v) {
        try {
            return append((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            return new ArrayFieldVector(this, new ArrayFieldVector(v));
        }
    }

    public ArrayFieldVector<T> append(ArrayFieldVector<T> v) {
        return new ArrayFieldVector<>(this, v);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> append(T in) {
        FieldElement[] fieldElementArrBuildArray = buildArray(this.data.length + 1);
        System.arraycopy(this.data, 0, fieldElementArrBuildArray, 0, this.data.length);
        fieldElementArrBuildArray[this.data.length] = in;
        return new ArrayFieldVector(fieldElementArrBuildArray);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> append(T[] in) {
        return new ArrayFieldVector(this, in);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public FieldVector<T> getSubVector(int index, int n) throws MatrixIndexException {
        ArrayFieldVector<T> out = new ArrayFieldVector<>(this.field, n);
        try {
            System.arraycopy(this.data, index, out.data, 0, n);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex((index + n) - 1);
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public void setEntry(int index, T value) throws MatrixIndexException {
        try {
            this.data[index] = value;
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
        }
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public void setSubVector(int i, FieldVector<T> fieldVector) throws MatrixIndexException {
        try {
            try {
                set(i, (ArrayFieldVector) fieldVector);
            } catch (ClassCastException e) {
                for (int i2 = i; i2 < i + fieldVector.getDimension(); i2++) {
                    ((T[]) this.data)[i2] = fieldVector.getEntry(i2 - i);
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            checkIndex(i);
            checkIndex((i + fieldVector.getDimension()) - 1);
        }
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public void setSubVector(int index, T[] v) throws MatrixIndexException {
        try {
            System.arraycopy(v, 0, this.data, index, v.length);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex((index + v.length) - 1);
        }
    }

    public void set(int index, ArrayFieldVector<T> v) throws MatrixIndexException {
        setSubVector(index, v.data);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public void set(T value) {
        Arrays.fill(this.data, value);
    }

    @Override // org.apache.commons.math.linear.FieldVector
    public T[] toArray() {
        return (T[]) ((FieldElement[]) this.data.clone());
    }

    protected void checkVectorDimensions(FieldVector<T> v) throws IllegalArgumentException {
        checkVectorDimensions(v.getDimension());
    }

    protected void checkVectorDimensions(int n) throws IllegalArgumentException {
        if (this.data.length != n) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(this.data.length), Integer.valueOf(n));
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        try {
            FieldVector<T> rhs = (FieldVector) other;
            if (this.data.length != rhs.getDimension()) {
                return false;
            }
            for (int i = 0; i < this.data.length; i++) {
                if (!this.data[i].equals(rhs.getEntry(i))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        int h = 3542;
        T[] arr$ = this.data;
        for (T a : arr$) {
            h ^= a.hashCode();
        }
        return h;
    }

    private void checkIndex(int index) throws MatrixIndexException {
        if (index < 0 || index >= getDimension()) {
            throw new MatrixIndexException(LocalizedFormats.INDEX_OUT_OF_RANGE, Integer.valueOf(index), 0, Integer.valueOf(getDimension() - 1));
        }
    }
}
