package org.apache.commons.math.linear;

import com.shimmerresearch.verisense.UtilVerisenseDriver;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/AbstractRealMatrix.class */
public abstract class AbstractRealMatrix implements RealMatrix {

    @Deprecated
    private DecompositionSolver lu;

    protected AbstractRealMatrix() {
        this.lu = null;
    }

    protected AbstractRealMatrix(int rowDimension, int columnDimension) throws IllegalArgumentException {
        if (rowDimension < 1) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(rowDimension), 1);
        }
        if (columnDimension <= 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(columnDimension), 1);
        }
        this.lu = null;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public abstract RealMatrix createMatrix(int i, int i2) throws IllegalArgumentException;

    @Override // org.apache.commons.math.linear.RealMatrix
    public abstract RealMatrix copy();

    @Override // org.apache.commons.math.linear.RealMatrix
    public abstract double getEntry(int i, int i2) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.RealMatrix
    public abstract void setEntry(int i, int i2, double d) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.RealMatrix
    public abstract void addToEntry(int i, int i2, double d) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.RealMatrix
    public abstract void multiplyEntry(int i, int i2, double d) throws MatrixIndexException;

    @Override // org.apache.commons.math.linear.AnyMatrix
    public abstract int getRowDimension();

    @Override // org.apache.commons.math.linear.AnyMatrix
    public abstract int getColumnDimension();

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix add(RealMatrix m) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkAdditionCompatible(this, m);
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, getEntry(row, col) + m.getEntry(row, col));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix subtract(RealMatrix m) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkSubtractionCompatible(this, m);
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, getEntry(row, col) - m.getEntry(row, col));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix scalarAdd(double d) throws MatrixIndexException, IllegalArgumentException {
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, getEntry(row, col) + d);
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix scalarMultiply(double d) throws MatrixIndexException, IllegalArgumentException {
        int rowCount = getRowDimension();
        int columnCount = getColumnDimension();
        RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                out.setEntry(row, col, getEntry(row, col) * d);
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix multiply(RealMatrix m) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkMultiplicationCompatible(this, m);
        int nRows = getRowDimension();
        int nCols = m.getColumnDimension();
        int nSum = getColumnDimension();
        RealMatrix out = createMatrix(nRows, nCols);
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                double sum = 0.0d;
                for (int i = 0; i < nSum; i++) {
                    sum += getEntry(row, i) * m.getEntry(i, col);
                }
                out.setEntry(row, col, sum);
            }
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix preMultiply(RealMatrix m) throws IllegalArgumentException {
        return m.multiply(this);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double[][] getData() {
        double[][] data = new double[getRowDimension()][getColumnDimension()];
        for (int i = 0; i < data.length; i++) {
            double[] dataI = data[i];
            for (int j = 0; j < dataI.length; j++) {
                dataI[j] = getEntry(i, j);
            }
        }
        return data;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double getNorm() {
        return walkInColumnOrder(new RealMatrixPreservingVisitor() { // from class: org.apache.commons.math.linear.AbstractRealMatrix.1
            private double endRow;
            private double columnSum;
            private double maxColSum;

            @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                this.endRow = endRow;
                this.columnSum = 0.0d;
                this.maxColSum = 0.0d;
            }

            @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void visit(int row, int column, double value) {
                this.columnSum += FastMath.abs(value);
                if (row == this.endRow) {
                    this.maxColSum = FastMath.max(this.maxColSum, this.columnSum);
                    this.columnSum = 0.0d;
                }
            }

            @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public double end() {
                return this.maxColSum;
            }
        });
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double getFrobeniusNorm() {
        return walkInOptimizedOrder(new RealMatrixPreservingVisitor() { // from class: org.apache.commons.math.linear.AbstractRealMatrix.2
            private double sum;

            @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                this.sum = 0.0d;
            }

            @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void visit(int row, int column, double value) {
                this.sum += value * value;
            }

            @Override // org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public double end() {
                return FastMath.sqrt(this.sum);
            }
        });
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        RealMatrix subMatrix = createMatrix((endRow - startRow) + 1, (endColumn - startColumn) + 1);
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startColumn; j <= endColumn; j++) {
                subMatrix.setEntry(i - startRow, j - startColumn, getEntry(i, j));
            }
        }
        return subMatrix;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws MatrixVisitorException, MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
        RealMatrix subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
        subMatrix.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() { // from class: org.apache.commons.math.linear.AbstractRealMatrix.3
            @Override
            // org.apache.commons.math.linear.DefaultRealMatrixChangingVisitor, org.apache.commons.math.linear.RealMatrixChangingVisitor
            public double visit(int row, int column, double value) {
                return AbstractRealMatrix.this.getEntry(selectedRows[row], selectedColumns[column]);
            }
        });
        return subMatrix;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void copySubMatrix(int startRow, int endRow, int startColumn, int endColumn, final double[][] destination) throws MatrixVisitorException, MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        int rowsCount = (endRow + 1) - startRow;
        int columnsCount = (endColumn + 1) - startColumn;
        if (destination.length < rowsCount || destination[0].length < columnsCount) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(destination.length), Integer.valueOf(destination[0].length), Integer.valueOf(rowsCount), Integer.valueOf(columnsCount));
        }
        walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() { // from class: org.apache.commons.math.linear.AbstractRealMatrix.4
            private int startRow;
            private int startColumn;

            @Override
            // org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor, org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void start(int rows, int columns, int startRow2, int endRow2, int startColumn2, int endColumn2) {
                this.startRow = startRow2;
                this.startColumn = startColumn2;
            }

            @Override
            // org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor, org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void visit(int row, int column, double value) {
                destination[row - this.startRow][column - this.startColumn] = value;
            }
        }, startRow, endRow, startColumn, endColumn);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, double[][] destination) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
        if (destination.length < selectedRows.length || destination[0].length < selectedColumns.length) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(destination.length), Integer.valueOf(destination[0].length), Integer.valueOf(selectedRows.length), Integer.valueOf(selectedColumns.length));
        }
        for (int i = 0; i < selectedRows.length; i++) {
            double[] destinationI = destination[i];
            for (int j = 0; j < selectedColumns.length; j++) {
                destinationI[j] = getEntry(selectedRows[i], selectedColumns[j]);
            }
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setSubMatrix(double[][] subMatrix, int row, int column) throws MatrixIndexException {
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
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        MatrixUtils.checkRowIndex(this, (nRows + row) - 1);
        MatrixUtils.checkColumnIndex(this, (nCols + column) - 1);
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                setEntry(row + i, column + j, subMatrix[i][j]);
            }
        }
        this.lu = null;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix getRowMatrix(int row) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        RealMatrix out = createMatrix(1, nCols);
        for (int i = 0; i < nCols; i++) {
            out.setEntry(0, i, getEntry(row, i));
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setRowMatrix(int row, RealMatrix matrix) throws MatrixIndexException, InvalidMatrixException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(matrix.getRowDimension()), Integer.valueOf(matrix.getColumnDimension()), 1, Integer.valueOf(nCols));
        }
        for (int i = 0; i < nCols; i++) {
            setEntry(row, i, matrix.getEntry(0, i));
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix getColumnMatrix(int column) throws MatrixIndexException, IllegalArgumentException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        RealMatrix out = createMatrix(nRows, 1);
        for (int i = 0; i < nRows; i++) {
            out.setEntry(i, 0, getEntry(i, column));
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setColumnMatrix(int column, RealMatrix matrix) throws MatrixIndexException, InvalidMatrixException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(matrix.getRowDimension()), Integer.valueOf(matrix.getColumnDimension()), Integer.valueOf(nRows), 1);
        }
        for (int i = 0; i < nRows; i++) {
            setEntry(i, column, matrix.getEntry(i, 0));
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealVector getRowVector(int row) throws MatrixIndexException {
        return new ArrayRealVector(getRow(row), false);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setRowVector(int row, RealVector vector) throws MatrixIndexException, InvalidMatrixException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        if (vector.getDimension() != nCols) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, 1, Integer.valueOf(vector.getDimension()), 1, Integer.valueOf(nCols));
        }
        for (int i = 0; i < nCols; i++) {
            setEntry(row, i, vector.getEntry(i));
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealVector getColumnVector(int column) throws MatrixIndexException {
        return new ArrayRealVector(getColumn(column), false);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setColumnVector(int column, RealVector vector) throws MatrixIndexException, InvalidMatrixException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        if (vector.getDimension() != nRows) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(vector.getDimension()), 1, Integer.valueOf(nRows), 1);
        }
        for (int i = 0; i < nRows; i++) {
            setEntry(i, column, vector.getEntry(i));
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double[] getRow(int row) throws MatrixIndexException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        double[] out = new double[nCols];
        for (int i = 0; i < nCols; i++) {
            out[i] = getEntry(row, i);
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setRow(int row, double[] array) throws MatrixIndexException, InvalidMatrixException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        if (array.length != nCols) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, 1, Integer.valueOf(array.length), 1, Integer.valueOf(nCols));
        }
        for (int i = 0; i < nCols; i++) {
            setEntry(row, i, array[i]);
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double[] getColumn(int column) throws MatrixIndexException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        double[] out = new double[nRows];
        for (int i = 0; i < nRows; i++) {
            out[i] = getEntry(i, column);
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public void setColumn(int column, double[] array) throws MatrixIndexException, InvalidMatrixException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        if (array.length != nRows) {
            throw new InvalidMatrixException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(array.length), 1, Integer.valueOf(nRows), 1);
        }
        for (int i = 0; i < nRows; i++) {
            setEntry(i, column, array[i]);
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealMatrix transpose() throws MatrixVisitorException, IllegalArgumentException {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        final RealMatrix out = createMatrix(nCols, nRows);
        walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() { // from class: org.apache.commons.math.linear.AbstractRealMatrix.5
            @Override
            // org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor, org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void visit(int row, int column, double value) throws MatrixIndexException {
                out.setEntry(column, row, value);
            }
        });
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    @Deprecated
    public RealMatrix inverse() throws InvalidMatrixException {
        if (this.lu == null) {
            this.lu = new LUDecompositionImpl(this, Double.MIN_NORMAL).getSolver();
        }
        return this.lu.getInverse();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    @Deprecated
    public double getDeterminant() throws InvalidMatrixException {
        return new LUDecompositionImpl(this, Double.MIN_NORMAL).getDeterminant();
    }

    @Override // org.apache.commons.math.linear.AnyMatrix
    public boolean isSquare() {
        return getColumnDimension() == getRowDimension();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    @Deprecated
    public boolean isSingular() {
        if (this.lu == null) {
            this.lu = new LUDecompositionImpl(this, Double.MIN_NORMAL).getSolver();
        }
        return !this.lu.isNonSingular();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double getTrace() throws NonSquareMatrixException {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        if (nRows != nCols) {
            throw new NonSquareMatrixException(nRows, nCols);
        }
        double trace = 0.0d;
        for (int i = 0; i < nRows; i++) {
            trace += getEntry(i, i);
        }
        return trace;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double[] operate(double[] v) throws IllegalArgumentException {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        if (v.length != nCols) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(v.length), Integer.valueOf(nCols));
        }
        double[] out = new double[nRows];
        for (int row = 0; row < nRows; row++) {
            double sum = 0.0d;
            for (int i = 0; i < nCols; i++) {
                sum += getEntry(row, i) * v[i];
            }
            out[row] = sum;
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealVector operate(RealVector v) throws IllegalArgumentException {
        try {
            return new ArrayRealVector(operate(((ArrayRealVector) v).getDataRef()), false);
        } catch (ClassCastException e) {
            int nRows = getRowDimension();
            int nCols = getColumnDimension();
            if (v.getDimension() != nCols) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(v.getDimension()), Integer.valueOf(nCols));
            }
            double[] out = new double[nRows];
            for (int row = 0; row < nRows; row++) {
                double sum = 0.0d;
                for (int i = 0; i < nCols; i++) {
                    sum += getEntry(row, i) * v.getEntry(i);
                }
                out[row] = sum;
            }
            return new ArrayRealVector(out, false);
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double[] preMultiply(double[] v) throws IllegalArgumentException {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        if (v.length != nRows) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(v.length), Integer.valueOf(nRows));
        }
        double[] out = new double[nCols];
        for (int col = 0; col < nCols; col++) {
            double sum = 0.0d;
            for (int i = 0; i < nRows; i++) {
                sum += getEntry(i, col) * v[i];
            }
            out[col] = sum;
        }
        return out;
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public RealVector preMultiply(RealVector v) throws IllegalArgumentException {
        try {
            return new ArrayRealVector(preMultiply(((ArrayRealVector) v).getDataRef()), false);
        } catch (ClassCastException e) {
            int nRows = getRowDimension();
            int nCols = getColumnDimension();
            if (v.getDimension() != nRows) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(v.getDimension()), Integer.valueOf(nRows));
            }
            double[] out = new double[nCols];
            for (int col = 0; col < nCols; col++) {
                double sum = 0.0d;
                for (int i = 0; i < nRows; i++) {
                    sum += getEntry(i, col) * v.getEntry(i);
                }
                out[col] = sum;
            }
            return new ArrayRealVector(out);
        }
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInRowOrder(RealMatrixChangingVisitor visitor) throws MatrixVisitorException, MatrixIndexException {
        int rows = getRowDimension();
        int columns = getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                double oldValue = getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        this.lu = null;
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInRowOrder(RealMatrixPreservingVisitor visitor) throws MatrixVisitorException {
        int rows = getRowDimension();
        int columns = getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws MatrixVisitorException, MatrixIndexException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                double oldValue = getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        this.lu = null;
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws MatrixVisitorException, MatrixIndexException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInColumnOrder(RealMatrixChangingVisitor visitor) throws MatrixVisitorException, MatrixIndexException {
        int rows = getRowDimension();
        int columns = getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                double oldValue = getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        this.lu = null;
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInColumnOrder(RealMatrixPreservingVisitor visitor) throws MatrixVisitorException {
        int rows = getRowDimension();
        int columns = getColumnDimension();
        visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInColumnOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws MatrixVisitorException, MatrixIndexException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; column <= endColumn; column++) {
            for (int row = startRow; row <= endRow; row++) {
                double oldValue = getEntry(row, column);
                double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        this.lu = null;
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInColumnOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws MatrixVisitorException, MatrixIndexException {
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; column <= endColumn; column++) {
            for (int row = startRow; row <= endRow; row++) {
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor) throws MatrixVisitorException {
        return walkInRowOrder(visitor);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor) throws MatrixVisitorException {
        return walkInRowOrder(visitor);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws MatrixVisitorException, MatrixIndexException {
        return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws MatrixVisitorException, MatrixIndexException {
        return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    @Deprecated
    public double[] solve(double[] b) throws InvalidMatrixException, IllegalArgumentException {
        if (this.lu == null) {
            this.lu = new LUDecompositionImpl(this, Double.MIN_NORMAL).getSolver();
        }
        return this.lu.solve(b);
    }

    @Override // org.apache.commons.math.linear.RealMatrix
    @Deprecated
    public RealMatrix solve(RealMatrix b) throws InvalidMatrixException, IllegalArgumentException {
        if (this.lu == null) {
            this.lu = new LUDecompositionImpl(this, Double.MIN_NORMAL).getSolver();
        }
        return this.lu.solve(b);
    }

    @Deprecated
    public void luDecompose() throws InvalidMatrixException {
        if (this.lu == null) {
            this.lu = new LUDecompositionImpl(this, Double.MIN_NORMAL).getSolver();
        }
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
        if (!(object instanceof RealMatrix)) {
            return false;
        }
        RealMatrix m = (RealMatrix) object;
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        if (m.getColumnDimension() != nCols || m.getRowDimension() != nRows) {
            return false;
        }
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                if (getEntry(row, col) != m.getEntry(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int nRows = getRowDimension();
        int nCols = getColumnDimension();
        int ret = (7 * 31) + nRows;
        int ret2 = (ret * 31) + nCols;
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                ret2 = (ret2 * 31) + (((11 * (row + 1)) + (17 * (col + 1))) * MathUtils.hash(getEntry(row, col)));
            }
        }
        return ret2;
    }
}
