package org.apache.commons.math.linear;

import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.VectorFormat;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/AbstractFieldMatrix.class */
public abstract class AbstractFieldMatrix<T extends FieldElement<T>> implements FieldMatrix<T> {
    private final Field<T> field;

    protected AbstractFieldMatrix() {
        this.field = null;
    }

    protected AbstractFieldMatrix(Field<T> field) {
        this.field = field;
    }

    protected AbstractFieldMatrix(Field<T> field, int rowDimension, int columnDimension) throws IllegalArgumentException {
        if (rowDimension < 1) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(rowDimension), 1);
        }
        if (columnDimension < 1) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(columnDimension), 1);
        }
        this.field = field;
    }

    protected static <T extends FieldElement<T>> Field<T> extractField(T[][] d) throws IllegalArgumentException {
        if (d.length == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_ROW, new Object[0]);
        }
        if (d[0].length == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_COLUMN, new Object[0]);
        }
        return d[0][0].getField2();
    }

    protected static <T extends FieldElement<T>> Field<T> extractField(T[] d) throws IllegalArgumentException {
        if (d.length == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_ROW, new Object[0]);
        }
        return d[0].getField2();
    }

    protected static <T extends FieldElement<T>> T[][] buildArray(Field<T> field, int i, int i2) {
        if (i2 < 0) {
            return (T[][]) ((FieldElement[][]) Array.newInstance(((FieldElement[]) Array.newInstance(field.getZero().getClass(), 0)).getClass(), i));
        }
        T[][] tArr = (T[][]) ((FieldElement[][]) Array.newInstance(field.getZero().getClass(), i, i2));
        for (T[] tArr2 : tArr) {
            Arrays.fill(tArr2, field.getZero());
        }
        return tArr;
    }

    protected static <T extends FieldElement<T>> T[] buildArray(Field<T> field, int i) {
        T[] tArr = (T[]) ((FieldElement[]) Array.newInstance(field.getZero().getClass(), i));
        Arrays.fill(tArr, field.getZero());
        return tArr;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public abstract FieldMatrix<T> createMatrix(int i, int i2) throws IllegalArgumentException;

    @Override // org.apache.commons.math.linear.FieldMatrix
    public abstract FieldMatrix<T> copy();

    @Override // org.apache.commons.math.linear.FieldMatrix
    public abstract T getEntry(int i, int i2) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.FieldMatrix
    public abstract void setEntry(int i, int i2, T t) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.FieldMatrix
    public abstract void addToEntry(int i, int i2, T t) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.FieldMatrix
    public abstract void multiplyEntry(int i, int i2, T t) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.AnyMatrix
    public abstract int getRowDimension();

    @Override // org.apache.commons.math.linear.AnyMatrix
    public abstract int getColumnDimension();

    @Override // org.apache.commons.math.linear.FieldMatrix
    public Field<T> getField() {
        return this.field;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> add(FieldMatrix<T> m) throws MatrixIndexException, IllegalArgumentException {
        checkAdditionCompatible(m);
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, (FieldElement) getEntry(row, col).add(m.getEntry(row, col)));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> subtract(FieldMatrix<T> m) throws MatrixIndexException, IllegalArgumentException {
        checkSubtractionCompatible(m);
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, (FieldElement) getEntry(row, col).subtract(m.getEntry(row, col)));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> scalarAdd(T d) throws MatrixIndexException, IllegalArgumentException {
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, (FieldElement) getEntry(row, col).add(d));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> scalarMultiply(T d) throws MatrixIndexException, IllegalArgumentException {
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, (FieldElement) getEntry(row, col).multiply(d));
            }
        }
        return out;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23, types: [org.apache.commons.math.FieldElement] */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> multiply(FieldMatrix<T> m) throws MatrixIndexException, IllegalArgumentException {
        checkMultiplicationCompatible(m);
        int nRows = getRowDimension();
        int nCols = m.getColumnDimension();
        int nSum = getColumnDimension();
        FieldMatrix<T> out = createMatrix(nRows, nCols);
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                T sum = this.field.getZero();
                for (int i = 0; i < nSum; i++) {
                    sum = (FieldElement) sum.add(getEntry(row, i).multiply(m.getEntry(i, col)));
                }
                out.setEntry(row, col, sum);
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> preMultiply(FieldMatrix<T> m) throws IllegalArgumentException {
        return m.multiply(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public T[][] getData() {
        T[][] tArr = (T[][]) buildArray(this.field, getRowDimension(), getColumnDimension());
        for (int i = 0; i < tArr.length; i++) {
            FieldElement[] fieldElementArr = tArr[i];
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = getEntry(i, i2);
            }
        }
        return tArr;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) throws MatrixIndexException, IllegalArgumentException {
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        FieldMatrix<T> subMatrix = createMatrix((endRow - startRow) + 1, (endColumn - startColumn) + 1);
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startColumn; j <= endColumn; j++) {
                subMatrix.setEntry(i - startRow, j - startColumn, getEntry(i, j));
            }
        }
        return subMatrix;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws MatrixVisitorException, MatrixIndexException, IllegalArgumentException {
        checkSubMatrixIndex(selectedRows, selectedColumns);
        FieldMatrix<T> subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
        subMatrix.walkInOptimizedOrder(new DefaultFieldMatrixChangingVisitor<T>(this.field.getZero()) { // from class: org.apache.commons.math.linear.AbstractFieldMatrix.1
            @Override
            // org.apache.commons.math.linear.DefaultFieldMatrixChangingVisitor, org.apache.commons.math.linear.FieldMatrixChangingVisitor
            public T visit(int i, int i2, T t) {
                return (T) AbstractFieldMatrix.this.getEntry(selectedRows[i], selectedColumns[i2]);
            }
        });
        return subMatrix;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void copySubMatrix(int startRow, int endRow, int startColumn, int endColumn, final T[][] destination) throws MatrixVisitorException, MatrixIndexException, IllegalArgumentException {
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        int rowsCount = (endRow + 1) - startRow;
        int columnsCount = (endColumn + 1) - startColumn;
        if (destination.length < rowsCount || destination[0].length < columnsCount) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(destination.length), Integer.valueOf(destination[0].length), Integer.valueOf(rowsCount), Integer.valueOf(columnsCount));
        }
        walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>(this.field.getZero()) { // from class: org.apache.commons.math.linear.AbstractFieldMatrix.2
            private int startRow;
            private int startColumn;

            @Override
            // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
            public void start(int rows, int columns, int startRow2, int endRow2, int startColumn2, int endColumn2) {
                this.startRow = startRow2;
                this.startColumn = startColumn2;
            }

            @Override
            // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
            public void visit(int row, int column, T value) {
                destination[row - this.startRow][column - this.startColumn] = value;
            }
        }, startRow, endRow, startColumn, endColumn);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, T[][] tArr) throws MatrixIndexException, IllegalArgumentException {
        checkSubMatrixIndex(selectedRows, selectedColumns);
        if (tArr.length < selectedRows.length || tArr[0].length < selectedColumns.length) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(tArr.length), Integer.valueOf(tArr[0].length), Integer.valueOf(selectedRows.length), Integer.valueOf(selectedColumns.length));
        }
        for (int i = 0; i < selectedRows.length; i++) {
            FieldElement[] fieldElementArr = tArr[i];
            for (int j = 0; j < selectedColumns.length; j++) {
                fieldElementArr[j] = getEntry(selectedRows[i], selectedColumns[j]);
            }
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setSubMatrix(T[][] subMatrix, int row, int column) throws MatrixIndexException {
        int nRows = subMatrix.length;
        if (nRows == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_ROW, new Object[0]);
        }
        int nCols = subMatrix[0].length;
        if (nCols == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_COLUMN, new Object[0]);
        }
        for (int r = 1; r < nRows; r++) {
            if (subMatrix[r].length != nCols) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, Integer.valueOf(nCols), Integer.valueOf(subMatrix[r].length));
            }
        }
        checkRowIndex(row);
        checkColumnIndex(column);
        checkRowIndex((nRows + row) - 1);
        checkColumnIndex((nCols + column) - 1);
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                setEntry(row + i, column + j, subMatrix[i][j]);
            }
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> getRowMatrix(int row) throws MatrixIndexException, IllegalArgumentException {
        checkRowIndex(row);
        int nCols = getColumnDimension();
        FieldMatrix<T> out = createMatrix(1, nCols);
        for (int i = 0; i < nCols; i++) {
            out.setEntry(0, i, getEntry(row, i));
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setRowMatrix(int row, FieldMatrix<T> matrix) throws MatrixIndexException, InvalidMatrixException {
        checkRowIndex(row);
        int nCols = getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(matrix.getRowDimension()), Integer.valueOf(matrix.getColumnDimension()), 1, Integer.valueOf(nCols));
        }
        for (int i = 0; i < nCols; i++) {
            setEntry(row, i, matrix.getEntry(0, i));
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> getColumnMatrix(int column) throws MatrixIndexException, IllegalArgumentException {
        checkColumnIndex(column);
        int nRows = getRowDimension();
        FieldMatrix<T> out = createMatrix(nRows, 1);
        for (int i = 0; i < nRows; i++) {
            out.setEntry(i, 0, getEntry(i, column));
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setColumnMatrix(int column, FieldMatrix<T> matrix) throws MatrixIndexException, InvalidMatrixException {
        checkColumnIndex(column);
        int nRows = getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(matrix.getRowDimension()), Integer.valueOf(matrix.getColumnDimension()), Integer.valueOf(nRows), 1);
        }
        for (int i = 0; i < nRows; i++) {
            setEntry(i, column, matrix.getEntry(i, 0));
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldVector<T> getRowVector(int row) throws MatrixIndexException {
        return new ArrayFieldVector(getRow(row), false);
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setRowVector(int row, FieldVector<T> vector) throws MatrixIndexException, InvalidMatrixException {
        checkRowIndex(row);
        int nCols = getColumnDimension();
        if (vector.getDimension() != nCols) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, 1, Integer.valueOf(vector.getDimension()), 1, Integer.valueOf(nCols));
        }
        for (int i = 0; i < nCols; i++) {
            setEntry(row, i, vector.getEntry(i));
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldVector<T> getColumnVector(int column) throws MatrixIndexException {
        return new ArrayFieldVector(getColumn(column), false);
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setColumnVector(int column, FieldVector<T> vector) throws MatrixIndexException, InvalidMatrixException {
        checkColumnIndex(column);
        int nRows = getRowDimension();
        if (vector.getDimension() != nRows) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(vector.getDimension()), 1, Integer.valueOf(nRows), 1);
        }
        for (int i = 0; i < nRows; i++) {
            setEntry(i, column, vector.getEntry(i));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public T[] getRow(int i) throws MatrixIndexException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        T[] tArr = (T[]) buildArray(this.field, columnDimension);
        for (int i2 = 0; i2 < columnDimension; i2++) {
            tArr[i2] = getEntry(i, i2);
        }
        return tArr;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setRow(int row, T[] array) throws MatrixIndexException, InvalidMatrixException {
        checkRowIndex(row);
        int nCols = getColumnDimension();
        if (array.length != nCols) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, 1, Integer.valueOf(array.length), 1, Integer.valueOf(nCols));
        }
        for (int i = 0; i < nCols; i++) {
            setEntry(row, i, array[i]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public T[] getColumn(int i) throws MatrixIndexException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        T[] tArr = (T[]) buildArray(this.field, rowDimension);
        for (int i2 = 0; i2 < rowDimension; i2++) {
            tArr[i2] = getEntry(i2, i);
        }
        return tArr;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public void setColumn(int column, T[] array) throws MatrixIndexException, InvalidMatrixException {
        checkColumnIndex(column);
        int nRows = getRowDimension();
        if (array.length != nRows) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(array.length), 1, Integer.valueOf(nRows), 1);
        }
        for (int i = 0; i < nRows; i++) {
            setEntry(i, column, array[i]);
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldMatrix<T> transpose() throws MatrixVisitorException, IllegalArgumentException {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(nCols, nRows);
        walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>(this.field.getZero()) { // from class: org.apache.commons.math.linear.AbstractFieldMatrix.3
            @Override
            // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
            public void visit(int row, int column, T value) throws MatrixIndexException {
                out.setEntry(column, row, value);
            }
        });
        return out;
    }

    @Override // org.apache.commons.math.linear.AnyMatrix
    public boolean isSquare() {
        return getColumnDimension() == getRowDimension();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [org.apache.commons.math.FieldElement] */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public T getTrace() throws NonSquareMatrixException {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        if (nRows != nCols) {
            throw new NonSquareMatrixException(nRows, nCols);
        }
        T trace = this.field.getZero();
        for (int i = 0; i < nRows; i++) {
            trace = (FieldElement) trace.add(getEntry(i, i));
        }
        return trace;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21, types: [org.apache.commons.math.FieldElement] */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public T[] operate(T[] tArr) throws IllegalArgumentException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(tArr.length), Integer.valueOf(columnDimension));
        }
        T[] tArr2 = (T[]) buildArray(this.field, rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            T zero = this.field.getZero();
            for (int i2 = 0; i2 < columnDimension; i2++) {
                zero = (FieldElement) zero.add(getEntry(i, i2).multiply(tArr[i2]));
            }
            tArr2[i] = zero;
        }
        return tArr2;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldVector<T> operate(FieldVector<T> v) throws IllegalArgumentException {
        try {
            return new ArrayFieldVector(operate(((ArrayFieldVector) v).getDataRef()), false);
        } catch (ClassCastException e) {
            int nRows = getRowDimension();
            int nCols = getColumnDimension();
            if (v.getDimension() != nCols) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(v.getDimension()), Integer.valueOf(nCols));
            }
            FieldElement[] fieldElementArrBuildArray = buildArray(this.field, nRows);
            for (int row = 0; row < nRows; row++) {
                FieldElement zero = this.field.getZero();
                for (int i = 0; i < nCols; i++) {
                    zero = (FieldElement) zero.add(getEntry(row, i).multiply(v.getEntry(i)));
                }
                fieldElementArrBuildArray[row] = zero;
            }
            return new ArrayFieldVector(fieldElementArrBuildArray, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21, types: [org.apache.commons.math.FieldElement] */
    @Override // org.apache.commons.math.linear.FieldMatrix
    public T[] preMultiply(T[] tArr) throws IllegalArgumentException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != rowDimension) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(tArr.length), Integer.valueOf(rowDimension));
        }
        T[] tArr2 = (T[]) buildArray(this.field, columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            T zero = this.field.getZero();
            for (int i2 = 0; i2 < rowDimension; i2++) {
                zero = (FieldElement) zero.add(getEntry(i2, i).multiply(tArr[i2]));
            }
            tArr2[i] = zero;
        }
        return tArr2;
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public FieldVector<T> preMultiply(FieldVector<T> v) throws IllegalArgumentException {
        try {
            return new ArrayFieldVector(preMultiply(((ArrayFieldVector) v).getDataRef()), false);
        } catch (ClassCastException e) {
            int nRows = getRowDimension();
            int nCols = getColumnDimension();
            if (v.getDimension() != nRows) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(v.getDimension()), Integer.valueOf(nRows));
            }
            FieldElement[] fieldElementArrBuildArray = buildArray(this.field, nCols);
            for (int col = 0; col < nCols; col++) {
                FieldElement zero = this.field.getZero();
                for (int i = 0; i < nRows; i++) {
                    zero = (FieldElement) zero.add(getEntry(i, col).multiply(v.getEntry(i)));
                }
                fieldElementArrBuildArray[col] = zero;
            }
            return new ArrayFieldVector(fieldElementArrBuildArray);
        }
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) throws MatrixVisitorException, MatrixIndexException {
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

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) throws MatrixVisitorException {
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

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        for (int i5 = i; i5 <= i2; i5++) {
            for (int i6 = i3; i6 <= i4; i6++) {
                setEntry(i5, i6, fieldMatrixChangingVisitor.visit(i5, i6, getEntry(i5, i6)));
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        for (int i5 = i; i5 <= i2; i5++) {
            for (int i6 = i3; i6 <= i4; i6++) {
                fieldMatrixPreservingVisitor.visit(i5, i6, getEntry(i5, i6));
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) throws MatrixVisitorException, MatrixIndexException {
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

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) throws MatrixVisitorException {
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

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        for (int i5 = i3; i5 <= i4; i5++) {
            for (int i6 = i; i6 <= i2; i6++) {
                setEntry(i6, i5, fieldMatrixChangingVisitor.visit(i6, i5, getEntry(i6, i5)));
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        for (int i5 = i3; i5 <= i4; i5++) {
            for (int i6 = i; i6 <= i2; i6++) {
                fieldMatrixPreservingVisitor.visit(i6, i5, getEntry(i6, i5));
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) throws MatrixVisitorException {
        return (T) walkInRowOrder(fieldMatrixChangingVisitor);
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) throws MatrixVisitorException {
        return (T) walkInRowOrder(fieldMatrixPreservingVisitor);
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException {
        return (T) walkInRowOrder(fieldMatrixChangingVisitor, i, i2, i3, i4);
    }

    @Override // org.apache.commons.math.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException {
        return (T) walkInRowOrder(fieldMatrixPreservingVisitor, i, i2, i3, i4);
    }

    public String toString() {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        StringBuilder res = new StringBuilder();
        String fullClassName = getClass().getName();
        String shortClassName = fullClassName.substring(fullClassName.lastIndexOf(46) + 1);
        res.append(shortClassName).append(VectorFormat.DEFAULT_PREFIX);
        for (int i = 0; i < nRows; i++) {
            if (i > 0) {
                res.append(UtilVerisenseDriver.CSV_DELIMITER);
            }
            res.append(VectorFormat.DEFAULT_PREFIX);
            for (int j = 0; j < nCols; j++) {
                if (j > 0) {
                    res.append(UtilVerisenseDriver.CSV_DELIMITER);
                }
                res.append(getEntry(i, j));
            }
            res.append(VectorFormat.DEFAULT_SUFFIX);
        }
        res.append(VectorFormat.DEFAULT_SUFFIX);
        return res.toString();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof FieldMatrix)) {
            return false;
        }
        FieldMatrix<?> m = (FieldMatrix) object;
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        if (m.getColumnDimension() != nCols || m.getRowDimension() != nRows) {
            return false;
        }
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                if (!getEntry(row, col).equals(m.getEntry(row, col))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        int ret = (322562 * 31) + nRows;
        int ret2 = (ret * 31) + nCols;
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                ret2 = (ret2 * 31) + (((11 * (row + 1)) + (17 * (col + 1))) * getEntry(row, col).hashCode());
            }
        }
        return ret2;
    }

    protected void checkRowIndex(int row) {
        if (row < 0 || row >= getRowDimension()) {
            throw new MatrixIndexException(LocalizedFormats.ROW_INDEX_OUT_OF_RANGE, Integer.valueOf(row), 0, Integer.valueOf(getRowDimension() - 1));
        }
    }

    protected void checkColumnIndex(int column) throws MatrixIndexException {
        if (column < 0 || column >= getColumnDimension()) {
            throw new MatrixIndexException(LocalizedFormats.COLUMN_INDEX_OUT_OF_RANGE, Integer.valueOf(column), 0, Integer.valueOf(getColumnDimension() - 1));
        }
    }

    protected void checkSubMatrixIndex(int startRow, int endRow, int startColumn, int endColumn) throws MatrixIndexException {
        checkRowIndex(startRow);
        checkRowIndex(endRow);
        if (startRow > endRow) {
            throw new MatrixIndexException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(startRow), Integer.valueOf(endRow));
        }
        checkColumnIndex(startColumn);
        checkColumnIndex(endColumn);
        if (startColumn > endColumn) {
            throw new MatrixIndexException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(startColumn), Integer.valueOf(endColumn));
        }
    }

    protected void checkSubMatrixIndex(int[] selectedRows, int[] selectedColumns) throws MatrixIndexException {
        if (selectedRows.length * selectedColumns.length == 0) {
            if (selectedRows.length == 0) {
                throw new MatrixIndexException(LocalizedFormats.EMPTY_SELECTED_ROW_INDEX_ARRAY, new Object[0]);
            }
            throw new MatrixIndexException(LocalizedFormats.EMPTY_SELECTED_COLUMN_INDEX_ARRAY, new Object[0]);
        }
        for (int row : selectedRows) {
            checkRowIndex(row);
        }
        for (int column : selectedColumns) {
            checkColumnIndex(column);
        }
    }

    protected void checkAdditionCompatible(FieldMatrix<T> m) {
        if (getRowDimension() != m.getRowDimension() || getColumnDimension() != m.getColumnDimension()) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_ADDITION_COMPATIBLE_MATRICES, Integer.valueOf(getRowDimension()), Integer.valueOf(getColumnDimension()), Integer.valueOf(m.getRowDimension()), Integer.valueOf(m.getColumnDimension()));
        }
    }

    protected void checkSubtractionCompatible(FieldMatrix<T> m) {
        if (getRowDimension() != m.getRowDimension() || getColumnDimension() != m.getColumnDimension()) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_SUBTRACTION_COMPATIBLE_MATRICES, Integer.valueOf(getRowDimension()), Integer.valueOf(getColumnDimension()), Integer.valueOf(m.getRowDimension()), Integer.valueOf(m.getColumnDimension()));
        }
    }

    protected void checkMultiplicationCompatible(FieldMatrix<T> m) {
        if (getColumnDimension() != m.getRowDimension()) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_MULTIPLICATION_COMPATIBLE_MATRICES, Integer.valueOf(getRowDimension()), Integer.valueOf(getColumnDimension()), Integer.valueOf(m.getRowDimension()), Integer.valueOf(m.getColumnDimension()));
        }
    }
}
