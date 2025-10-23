package org.apache.commons.math3.linear;

import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public abstract class AbstractFieldMatrix<T extends FieldElement<T>> implements FieldMatrix<T> {
    private final Field<T> field;

    protected AbstractFieldMatrix() {
        this.field = null;
    }

    protected AbstractFieldMatrix(Field<T> field) {
        this.field = field;
    }

    protected AbstractFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(i));
        }
        if (i2 <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(i2));
        }
        this.field = field;
    }

    protected static <T extends FieldElement<T>> Field<T> extractField(T[][] tArr) throws NullArgumentException, NoDataException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        if (tArr.length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        T[] tArr2 = tArr[0];
        if (tArr2.length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        return tArr2[0].getField();
    }

    protected static <T extends FieldElement<T>> Field<T> extractField(T[] tArr) throws NoDataException {
        if (tArr.length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        return tArr[0].getField();
    }

    @Deprecated
    protected static <T extends FieldElement<T>> T[][] buildArray(Field<T> field, int i, int i2) {
        return (T[][]) ((FieldElement[][]) MathArrays.buildArray(field, i, i2));
    }

    @Deprecated
    protected static <T extends FieldElement<T>> T[] buildArray(Field<T> field, int i) {
        return (T[]) ((FieldElement[]) MathArrays.buildArray(field, i));
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public abstract void addToEntry(int i, int i2, T t) throws OutOfRangeException;

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public abstract FieldMatrix<T> copy();

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public abstract FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException;

    @Override // org.apache.commons.math3.linear.AnyMatrix
    public abstract int getColumnDimension();

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public abstract T getEntry(int i, int i2) throws OutOfRangeException;

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public Field<T> getField() {
        return this.field;
    }

    @Override // org.apache.commons.math3.linear.AnyMatrix
    public abstract int getRowDimension();

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public abstract void multiplyEntry(int i, int i2, T t) throws OutOfRangeException;

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public abstract void setEntry(int i, int i2, T t) throws OutOfRangeException;

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> add(FieldMatrix<T> fieldMatrix) throws OutOfRangeException, NotStrictlyPositiveException, MatrixDimensionMismatchException {
        checkAdditionCompatible(fieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixCreateMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).add(fieldMatrix.getEntry(i, i2)));
            }
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> subtract(FieldMatrix<T> fieldMatrix) throws OutOfRangeException, NotStrictlyPositiveException, MatrixDimensionMismatchException {
        checkSubtractionCompatible(fieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixCreateMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).subtract(fieldMatrix.getEntry(i, i2)));
            }
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> scalarAdd(T t) throws OutOfRangeException, NotStrictlyPositiveException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixCreateMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).add(t));
            }
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> scalarMultiply(T t) throws OutOfRangeException, NotStrictlyPositiveException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixCreateMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).multiply(t));
            }
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> multiply(FieldMatrix<T> fieldMatrix) throws OutOfRangeException, NotStrictlyPositiveException, DimensionMismatchException {
        checkMultiplicationCompatible(fieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = fieldMatrix.getColumnDimension();
        int columnDimension2 = getColumnDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                T zero = this.field.getZero();
                for (int i3 = 0; i3 < columnDimension2; i3++) {
                    zero = (FieldElement) zero.add(getEntry(i, i3).multiply(fieldMatrix.getEntry(i3, i2)));
                }
                fieldMatrixCreateMatrix.setEntry(i, i2, zero);
            }
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> preMultiply(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        return fieldMatrix.multiply(this);
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> power(int i) throws NotPositiveException, DimensionMismatchException {
        if (i < 0) {
            throw new NotPositiveException(Integer.valueOf(i));
        }
        if (!isSquare()) {
            throw new NonSquareMatrixException(getRowDimension(), getColumnDimension());
        }
        if (i == 0) {
            return MatrixUtils.createFieldIdentityMatrix(getField(), getRowDimension());
        }
        if (i == 1) {
            return copy();
        }
        char[] charArray = Integer.toBinaryString(i - 1).toCharArray();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (charArray[i2] == '1') {
                arrayList.add(Integer.valueOf((charArray.length - i2) - 1));
            }
        }
        ArrayList arrayList2 = new ArrayList(charArray.length);
        arrayList2.add(0, copy());
        for (int i3 = 1; i3 < charArray.length; i3++) {
            FieldMatrix<T> fieldMatrix = (FieldMatrix) arrayList2.get(i3 - 1);
            arrayList2.add(i3, fieldMatrix.multiply(fieldMatrix));
        }
        FieldMatrix<T> fieldMatrixCopy = copy();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            fieldMatrixCopy = fieldMatrixCopy.multiply((FieldMatrix) arrayList2.get(((Integer) it2.next()).intValue()));
        }
        return fieldMatrixCopy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T[][] getData() {
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(this.field, getRowDimension(), getColumnDimension()));
        for (int i = 0; i < tArr.length; i++) {
            FieldElement[] fieldElementArr = tArr[i];
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = getEntry(i, i2);
            }
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getSubMatrix(int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix((i2 - i) + 1, (i4 - i3) + 1);
        for (int i5 = i; i5 <= i2; i5++) {
            for (int i6 = i3; i6 <= i4; i6++) {
                fieldMatrixCreateMatrix.setEntry(i5 - i, i6 - i3, getEntry(i5, i6));
            }
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getSubMatrix(final int[] iArr, final int[] iArr2) throws OutOfRangeException, NotStrictlyPositiveException, NullArgumentException, NoDataException {
        checkSubMatrixIndex(iArr, iArr2);
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(iArr.length, iArr2.length);
        fieldMatrixCreateMatrix.walkInOptimizedOrder(new DefaultFieldMatrixChangingVisitor<T>(this.field.getZero()) { // from class: org.apache.commons.math3.linear.AbstractFieldMatrix.1
            @Override
            // org.apache.commons.math3.linear.DefaultFieldMatrixChangingVisitor, org.apache.commons.math3.linear.FieldMatrixChangingVisitor
            public T visit(int i, int i2, T t) {
                return (T) AbstractFieldMatrix.this.getEntry(iArr[i], iArr2[i2]);
            }
        });
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void copySubMatrix(int i, int i2, int i3, int i4, final T[][] tArr) throws NumberIsTooSmallException, OutOfRangeException, MatrixDimensionMismatchException {
        checkSubMatrixIndex(i, i2, i3, i4);
        int i5 = (i2 + 1) - i;
        int i6 = (i4 + 1) - i3;
        if (tArr.length < i5 || tArr[0].length < i6) {
            throw new MatrixDimensionMismatchException(tArr.length, tArr[0].length, i5, i6);
        }
        walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>(this.field.getZero()) { // from class: org.apache.commons.math3.linear.AbstractFieldMatrix.2
            private int startColumn;
            private int startRow;

            @Override
            // org.apache.commons.math3.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
            public void start(int i7, int i8, int i9, int i10, int i11, int i12) {
                this.startRow = i9;
                this.startColumn = i11;
            }

            @Override
            // org.apache.commons.math3.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
            public void visit(int i7, int i8, T t) {
                tArr[i7 - this.startRow][i8 - this.startColumn] = t;
            }
        }, i, i2, i3, i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void copySubMatrix(int[] iArr, int[] iArr2, T[][] tArr) throws OutOfRangeException, MatrixDimensionMismatchException, NullArgumentException, NoDataException {
        checkSubMatrixIndex(iArr, iArr2);
        if (tArr.length < iArr.length || tArr[0].length < iArr2.length) {
            throw new MatrixDimensionMismatchException(tArr.length, tArr[0].length, iArr.length, iArr2.length);
        }
        for (int i = 0; i < iArr.length; i++) {
            FieldElement[] fieldElementArr = tArr[i];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                fieldElementArr[i2] = getEntry(iArr[i], iArr2[i2]);
            }
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setSubMatrix(T[][] tArr, int i, int i2) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        int length = tArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int length2 = tArr[0].length;
        if (length2 == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int i3 = 1; i3 < length; i3++) {
            if (tArr[i3].length != length2) {
                throw new DimensionMismatchException(length2, tArr[i3].length);
            }
        }
        checkRowIndex(i);
        checkColumnIndex(i2);
        checkRowIndex((length + i) - 1);
        checkColumnIndex((length2 + i2) - 1);
        for (int i4 = 0; i4 < length; i4++) {
            for (int i5 = 0; i5 < length2; i5++) {
                setEntry(i + i4, i2 + i5, tArr[i4][i5]);
            }
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getRowMatrix(int i) throws OutOfRangeException, NotStrictlyPositiveException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(1, columnDimension);
        for (int i2 = 0; i2 < columnDimension; i2++) {
            fieldMatrixCreateMatrix.setEntry(0, i2, getEntry(i, i2));
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setRowMatrix(int i, FieldMatrix<T> fieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (fieldMatrix.getRowDimension() != 1 || fieldMatrix.getColumnDimension() != columnDimension) {
            throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, fieldMatrix.getEntry(0, i2));
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getColumnMatrix(int i) throws OutOfRangeException, NotStrictlyPositiveException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(rowDimension, 1);
        for (int i2 = 0; i2 < rowDimension; i2++) {
            fieldMatrixCreateMatrix.setEntry(i2, 0, getEntry(i2, i));
        }
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setColumnMatrix(int i, FieldMatrix<T> fieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (fieldMatrix.getRowDimension() != rowDimension || fieldMatrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, fieldMatrix.getEntry(i2, 0));
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> getRowVector(int i) throws OutOfRangeException {
        return new ArrayFieldVector((Field) this.field, getRow(i), false);
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setRowVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (fieldVector.getDimension() != columnDimension) {
            throw new MatrixDimensionMismatchException(1, fieldVector.getDimension(), 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, fieldVector.getEntry(i2));
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> getColumnVector(int i) throws OutOfRangeException {
        return new ArrayFieldVector((Field) this.field, getColumn(i), false);
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setColumnVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (fieldVector.getDimension() != rowDimension) {
            throw new MatrixDimensionMismatchException(fieldVector.getDimension(), 1, rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, fieldVector.getEntry(i2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T[] getRow(int i) throws OutOfRangeException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, columnDimension));
        for (int i2 = 0; i2 < columnDimension; i2++) {
            tArr[i2] = getEntry(i, i2);
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setRow(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new MatrixDimensionMismatchException(1, tArr.length, 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, tArr[i2]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T[] getColumn(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, rowDimension));
        for (int i2 = 0; i2 < rowDimension; i2++) {
            tArr[i2] = getEntry(i2, i);
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public void setColumn(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (tArr.length != rowDimension) {
            throw new MatrixDimensionMismatchException(tArr.length, 1, rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, tArr[i2]);
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> transpose() throws NotStrictlyPositiveException {
        final FieldMatrix<T> fieldMatrixCreateMatrix = createMatrix(getColumnDimension(), getRowDimension());
        walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>(this.field.getZero()) { // from class: org.apache.commons.math3.linear.AbstractFieldMatrix.3
            @Override
            // org.apache.commons.math3.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
            public void visit(int i, int i2, T t) throws OutOfRangeException {
                fieldMatrixCreateMatrix.setEntry(i2, i, t);
            }
        });
        return fieldMatrixCreateMatrix;
    }

    @Override // org.apache.commons.math3.linear.AnyMatrix
    public boolean isSquare() {
        return getColumnDimension() == getRowDimension();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T getTrace() throws NonSquareMatrixException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (rowDimension != columnDimension) {
            throw new NonSquareMatrixException(rowDimension, columnDimension);
        }
        T zero = this.field.getZero();
        for (int i = 0; i < rowDimension; i++) {
            zero = (T) zero.add(getEntry(i, i));
        }
        return zero;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T[] operate(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new DimensionMismatchException(tArr.length, columnDimension);
        }
        T[] tArr2 = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, rowDimension));
        for (int i = 0; i < rowDimension; i++) {
            T zero = this.field.getZero();
            for (int i2 = 0; i2 < columnDimension; i2++) {
                zero = (T) zero.add(getEntry(i, i2).multiply(tArr[i2]));
            }
            tArr2[i] = zero;
        }
        return tArr2;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> operate(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return new ArrayFieldVector((Field) this.field, operate(((ArrayFieldVector) fieldVector).getDataRef()), false);
        } catch (ClassCastException unused) {
            int rowDimension = getRowDimension();
            int columnDimension = getColumnDimension();
            if (fieldVector.getDimension() != columnDimension) {
                throw new DimensionMismatchException(fieldVector.getDimension(), columnDimension);
            }
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, rowDimension);
            for (int i = 0; i < rowDimension; i++) {
                T zero = this.field.getZero();
                for (int i2 = 0; i2 < columnDimension; i2++) {
                    zero = (FieldElement) zero.add(getEntry(i, i2).multiply(fieldVector.getEntry(i2)));
                }
                fieldElementArr[i] = zero;
            }
            return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != rowDimension) {
            throw new DimensionMismatchException(tArr.length, rowDimension);
        }
        T[] tArr2 = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, columnDimension));
        for (int i = 0; i < columnDimension; i++) {
            T zero = this.field.getZero();
            for (int i2 = 0; i2 < rowDimension; i2++) {
                zero = (T) zero.add(getEntry(i2, i).multiply(tArr[i2]));
            }
            tArr2[i] = zero;
        }
        return tArr2;
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> preMultiply(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return new ArrayFieldVector((Field) this.field, preMultiply(((ArrayFieldVector) fieldVector).getDataRef()), false);
        } catch (ClassCastException unused) {
            int rowDimension = getRowDimension();
            int columnDimension = getColumnDimension();
            if (fieldVector.getDimension() != rowDimension) {
                throw new DimensionMismatchException(fieldVector.getDimension(), rowDimension);
            }
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, columnDimension);
            for (int i = 0; i < columnDimension; i++) {
                T zero = this.field.getZero();
                for (int i2 = 0; i2 < rowDimension; i2++) {
                    zero = (FieldElement) zero.add(getEntry(i2, i).multiply(fieldVector.getEntry(i2)));
                }
                fieldElementArr[i] = zero;
            }
            return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
        }
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) throws OutOfRangeException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                setEntry(i, i2, fieldMatrixChangingVisitor.visit(i, i2, getEntry(i, i2)));
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i, i2, getEntry(i, i2));
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            for (int i5 = i3; i5 <= i4; i5++) {
                setEntry(i, i5, fieldMatrixChangingVisitor.visit(i, i5, getEntry(i, i5)));
            }
            i++;
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            for (int i5 = i3; i5 <= i4; i5++) {
                fieldMatrixPreservingVisitor.visit(i, i5, getEntry(i, i5));
            }
            i++;
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) throws OutOfRangeException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                setEntry(i2, i, fieldMatrixChangingVisitor.visit(i2, i, getEntry(i2, i)));
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i2, i, getEntry(i2, i));
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                setEntry(i5, i3, fieldMatrixChangingVisitor.visit(i5, i3, getEntry(i5, i3)));
            }
            i3++;
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                fieldMatrixPreservingVisitor.visit(i5, i3, getEntry(i5, i3));
            }
            i3++;
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        return (T) walkInRowOrder(fieldMatrixChangingVisitor);
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        return (T) walkInRowOrder(fieldMatrixPreservingVisitor);
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        return (T) walkInRowOrder(fieldMatrixChangingVisitor, i, i2, i3, i4);
    }

    @Override // org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        return (T) walkInRowOrder(fieldMatrixPreservingVisitor, i, i2, i3, i4);
    }

    public String toString() {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        StringBuffer stringBuffer = new StringBuffer();
        String name = getClass().getName();
        stringBuffer.append(name.substring(name.lastIndexOf(46) + 1)).append(VectorFormat.DEFAULT_PREFIX);
        for (int i = 0; i < rowDimension; i++) {
            if (i > 0) {
                stringBuffer.append(UtilVerisenseDriver.CSV_DELIMITER);
            }
            stringBuffer.append(VectorFormat.DEFAULT_PREFIX);
            for (int i2 = 0; i2 < columnDimension; i2++) {
                if (i2 > 0) {
                    stringBuffer.append(UtilVerisenseDriver.CSV_DELIMITER);
                }
                stringBuffer.append(getEntry(i, i2));
            }
            stringBuffer.append(VectorFormat.DEFAULT_SUFFIX);
        }
        stringBuffer.append(VectorFormat.DEFAULT_SUFFIX);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldMatrix)) {
            return false;
        }
        FieldMatrix fieldMatrix = (FieldMatrix) obj;
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (fieldMatrix.getColumnDimension() != columnDimension || fieldMatrix.getRowDimension() != rowDimension) {
            return false;
        }
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                if (!getEntry(i, i2).equals(fieldMatrix.getEntry(i, i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        int iHashCode = ((9999422 + rowDimension) * 31) + columnDimension;
        for (int i = 0; i < rowDimension; i++) {
            int i2 = 0;
            while (i2 < columnDimension) {
                int i3 = i2 + 1;
                iHashCode = (iHashCode * 31) + ((((i + 1) * 11) + (i3 * 17)) * getEntry(i, i2).hashCode());
                i2 = i3;
            }
        }
        return iHashCode;
    }

    protected void checkRowIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getRowDimension()) {
            throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, Integer.valueOf(i), 0, Integer.valueOf(getRowDimension() - 1));
        }
    }

    protected void checkColumnIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getColumnDimension()) {
            throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, Integer.valueOf(i), 0, Integer.valueOf(getColumnDimension() - 1));
        }
    }

    protected void checkSubMatrixIndex(int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkRowIndex(i);
        checkRowIndex(i2);
        if (i2 < i) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(i2), Integer.valueOf(i), true);
        }
        checkColumnIndex(i3);
        checkColumnIndex(i4);
        if (i4 < i3) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(i4), Integer.valueOf(i3), true);
        }
    }

    protected void checkSubMatrixIndex(int[] iArr, int[] iArr2) throws OutOfRangeException, NullArgumentException, NoDataException {
        if (iArr == null || iArr2 == null) {
            throw new NullArgumentException();
        }
        if (iArr.length == 0 || iArr2.length == 0) {
            throw new NoDataException();
        }
        for (int i : iArr) {
            checkRowIndex(i);
        }
        for (int i2 : iArr2) {
            checkColumnIndex(i2);
        }
    }

    protected void checkAdditionCompatible(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        if (getRowDimension() != fieldMatrix.getRowDimension() || getColumnDimension() != fieldMatrix.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), getRowDimension(), getColumnDimension());
        }
    }

    protected void checkSubtractionCompatible(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        if (getRowDimension() != fieldMatrix.getRowDimension() || getColumnDimension() != fieldMatrix.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), getRowDimension(), getColumnDimension());
        }
    }

    protected void checkMultiplicationCompatible(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        if (getColumnDimension() != fieldMatrix.getRowDimension()) {
            throw new DimensionMismatchException(fieldMatrix.getRowDimension(), getColumnDimension());
        }
    }
}
