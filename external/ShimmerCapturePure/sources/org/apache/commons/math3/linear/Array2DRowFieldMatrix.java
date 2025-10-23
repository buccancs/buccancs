package org.apache.commons.math3.linear;

import java.io.Serializable;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class Array2DRowFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
    private static final long serialVersionUID = 7260756672015356458L;
    private T[][] data;

    public Array2DRowFieldMatrix(Field<T> field) {
        super(field);
    }

    public Array2DRowFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        super(field, i, i2);
        this.data = (T[][]) ((FieldElement[][]) MathArrays.buildArray(field, i, i2));
    }

    public Array2DRowFieldMatrix(T[][] tArr) throws NullArgumentException, NoDataException, DimensionMismatchException {
        this(extractField(tArr), tArr);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[][] tArr) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        super(field);
        copyIn(tArr);
    }

    public Array2DRowFieldMatrix(T[][] tArr, boolean z) throws NullArgumentException, NoDataException, DimensionMismatchException {
        this(extractField(tArr), tArr, z);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[][] tArr, boolean z) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        super(field);
        if (z) {
            copyIn(tArr);
            return;
        }
        MathUtils.checkNotNull(tArr);
        int length = tArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int length2 = tArr[0].length;
        if (length2 == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int i = 1; i < length; i++) {
            if (tArr[i].length != length2) {
                throw new DimensionMismatchException(length2, tArr[i].length);
            }
        }
        this.data = tArr;
    }

    public Array2DRowFieldMatrix(T[] tArr) throws NoDataException {
        this(extractField(tArr), tArr);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[] tArr) {
        super(field);
        int length = tArr.length;
        this.data = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), length, 1));
        for (int i = 0; i < length; i++) {
            this.data[i][0] = tArr[i];
        }
    }

    public T[][] getDataRef() {
        return this.data;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new Array2DRowFieldMatrix(getField(), i, i2);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> copy() {
        return new Array2DRowFieldMatrix((Field) getField(), copyOut(), false);
    }

    public Array2DRowFieldMatrix<T> add(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(array2DRowFieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldElement[][] fieldElementArr = (FieldElement[][]) MathArrays.buildArray(getField(), rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            T[] tArr2 = array2DRowFieldMatrix.data[i];
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldElementArr2[i2] = (FieldElement) tArr[i2].add(tArr2[i2]);
            }
        }
        return new Array2DRowFieldMatrix<>((Field) getField(), fieldElementArr, false);
    }

    public Array2DRowFieldMatrix<T> subtract(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(array2DRowFieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldElement[][] fieldElementArr = (FieldElement[][]) MathArrays.buildArray(getField(), rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            T[] tArr2 = array2DRowFieldMatrix.data[i];
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldElementArr2[i2] = (FieldElement) tArr[i2].subtract(tArr2[i2]);
            }
        }
        return new Array2DRowFieldMatrix<>((Field) getField(), fieldElementArr, false);
    }

    public Array2DRowFieldMatrix<T> multiply(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) throws DimensionMismatchException {
        checkMultiplicationCompatible(array2DRowFieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = array2DRowFieldMatrix.getColumnDimension();
        int columnDimension2 = getColumnDimension();
        FieldElement[][] fieldElementArr = (FieldElement[][]) MathArrays.buildArray(getField(), rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                T zero = getField().getZero();
                for (int i3 = 0; i3 < columnDimension2; i3++) {
                    zero = (FieldElement) zero.add(tArr[i3].multiply(array2DRowFieldMatrix.data[i3][i2]));
                }
                fieldElementArr2[i2] = zero;
            }
        }
        return new Array2DRowFieldMatrix<>((Field) getField(), fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[][] getData() {
        return (T[][]) copyOut();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setSubMatrix(T[][] tArr, int i, int i2) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        if (this.data != null) {
            super.setSubMatrix(tArr, i, i2);
            return;
        }
        if (i > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, Integer.valueOf(i));
        }
        if (i2 > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, Integer.valueOf(i2));
        }
        if (tArr.length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int length = tArr[0].length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        this.data = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), tArr.length, length));
        int i3 = 0;
        while (true) {
            T[][] tArr2 = this.data;
            if (i3 >= tArr2.length) {
                return;
            }
            T[] tArr3 = tArr[i3];
            if (tArr3.length != length) {
                throw new DimensionMismatchException(length, tArr[i3].length);
            }
            System.arraycopy(tArr3, 0, tArr2[i3 + i], i2, length);
            i3++;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T getEntry(int i, int i2) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        return this.data[i][i2];
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        this.data[i][i2] = t;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void addToEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        FieldElement[] fieldElementArr = this.data[i];
        fieldElementArr[i2] = (FieldElement) fieldElementArr[i2].add(t);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void multiplyEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        FieldElement[] fieldElementArr = this.data[i];
        fieldElementArr[i2] = (FieldElement) fieldElementArr[i2].multiply(t);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getRowDimension() {
        T[][] tArr = this.data;
        if (tArr == null) {
            return 0;
        }
        return tArr.length;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getColumnDimension() {
        T[] tArr;
        T[][] tArr2 = this.data;
        if (tArr2 == null || (tArr = tArr2[0]) == null) {
            return 0;
        }
        return tArr.length;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] operate(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new DimensionMismatchException(tArr.length, columnDimension);
        }
        T[] tArr2 = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), rowDimension));
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr3 = this.data[i];
            T zero = getField().getZero();
            for (int i2 = 0; i2 < columnDimension; i2++) {
                zero = (T) zero.add(tArr3[i2].multiply(tArr[i2]));
            }
            tArr2[i] = zero;
        }
        return tArr2;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != rowDimension) {
            throw new DimensionMismatchException(tArr.length, rowDimension);
        }
        T[] tArr2 = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), columnDimension));
        for (int i = 0; i < columnDimension; i++) {
            T zero = getField().getZero();
            for (int i2 = 0; i2 < rowDimension; i2++) {
                zero = (T) zero.add(this.data[i2][i].multiply(tArr[i2]));
            }
            tArr2[i] = zero;
        }
        return tArr2;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            Object[] objArr = this.data[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                objArr[i2] = fieldMatrixChangingVisitor.visit(i, i2, objArr[i2]);
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i, i2, tArr[i2]);
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            Object[] objArr = this.data[i];
            for (int i5 = i3; i5 <= i4; i5++) {
                objArr[i5] = fieldMatrixChangingVisitor.visit(i, i5, objArr[i5]);
            }
            i++;
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            T[] tArr = this.data[i];
            for (int i5 = i3; i5 <= i4; i5++) {
                fieldMatrixPreservingVisitor.visit(i, i5, tArr[i5]);
            }
            i++;
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                Object[] objArr = this.data[i2];
                objArr[i] = fieldMatrixChangingVisitor.visit(i2, i, objArr[i]);
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i2, i, this.data[i2][i]);
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                Object[] objArr = this.data[i5];
                objArr[i3] = fieldMatrixChangingVisitor.visit(i5, i3, objArr[i3]);
            }
            i3++;
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                fieldMatrixPreservingVisitor.visit(i5, i3, this.data[i5][i3]);
            }
            i3++;
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    private T[][] copyOut() {
        int rowDimension = getRowDimension();
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), rowDimension, getColumnDimension()));
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr2 = this.data[i];
            System.arraycopy(tArr2, 0, tArr[i], 0, tArr2.length);
        }
        return tArr;
    }

    private void copyIn(T[][] tArr) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        setSubMatrix(tArr, 0, 0);
    }
}
