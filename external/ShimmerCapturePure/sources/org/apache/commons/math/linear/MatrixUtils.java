package org.apache.commons.math.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.math.Field;
import org.apache.commons.math.FieldElement;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.fraction.BigFraction;
import org.apache.commons.math.fraction.Fraction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/MatrixUtils.class */
public class MatrixUtils {
    private MatrixUtils() {
    }

    public static RealMatrix createRealMatrix(int rows, int columns) {
        return rows * columns <= 4096 ? new Array2DRowRealMatrix(rows, columns) : new BlockRealMatrix(rows, columns);
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(Field<T> field, int rows, int columns) {
        return rows * columns <= 4096 ? new Array2DRowFieldMatrix(field, rows, columns) : new BlockFieldMatrix(field, rows, columns);
    }

    public static RealMatrix createRealMatrix(double[][] data) {
        return data.length * data[0].length <= 4096 ? new Array2DRowRealMatrix(data) : new BlockRealMatrix(data);
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(T[][] data) {
        return data.length * data[0].length <= 4096 ? new Array2DRowFieldMatrix(data) : new BlockFieldMatrix(data);
    }

    public static RealMatrix createRealIdentityMatrix(int dimension) throws MatrixIndexException {
        RealMatrix m = createRealMatrix(dimension, dimension);
        for (int i = 0; i < dimension; i++) {
            m.setEntry(i, i, 1.0d);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldIdentityMatrix(Field<T> field, int dimension) {
        T zero = field.getZero();
        T one = field.getOne();
        FieldElement[][] fieldElementArr = (FieldElement[][]) Array.newInstance(zero.getClass(), dimension, dimension);
        for (int row = 0; row < dimension; row++) {
            FieldElement[] fieldElementArr2 = fieldElementArr[row];
            Arrays.fill(fieldElementArr2, zero);
            fieldElementArr2[row] = one;
        }
        return new Array2DRowFieldMatrix(fieldElementArr, false);
    }

    @Deprecated
    public static BigMatrix createBigIdentityMatrix(int dimension) {
        BigDecimal[][] d = new BigDecimal[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            BigDecimal[] dRow = d[row];
            Arrays.fill(dRow, BigMatrixImpl.ZERO);
            dRow[row] = BigMatrixImpl.ONE;
        }
        return new BigMatrixImpl(d, false);
    }

    public static RealMatrix createRealDiagonalMatrix(double[] diagonal) throws MatrixIndexException {
        RealMatrix m = createRealMatrix(diagonal.length, diagonal.length);
        for (int i = 0; i < diagonal.length; i++) {
            m.setEntry(i, i, diagonal[i]);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldDiagonalMatrix(T[] diagonal) throws MatrixIndexException {
        FieldMatrix<T> m = createFieldMatrix(diagonal[0].getField2(), diagonal.length, diagonal.length);
        for (int i = 0; i < diagonal.length; i++) {
            m.setEntry(i, i, diagonal[i]);
        }
        return m;
    }

    @Deprecated
    public static BigMatrix createBigMatrix(double[][] data) {
        return new BigMatrixImpl(data);
    }

    @Deprecated
    public static BigMatrix createBigMatrix(BigDecimal[][] data) {
        return new BigMatrixImpl(data);
    }

    @Deprecated
    public static BigMatrix createBigMatrix(BigDecimal[][] data, boolean copyArray) {
        return new BigMatrixImpl(data, copyArray);
    }

    @Deprecated
    public static BigMatrix createBigMatrix(String[][] data) {
        return new BigMatrixImpl(data);
    }

    public static RealVector createRealVector(double[] data) {
        return new ArrayRealVector(data, true);
    }

    public static <T extends FieldElement<T>> FieldVector<T> createFieldVector(T[] data) {
        return new ArrayFieldVector((FieldElement[]) data, true);
    }

    public static RealMatrix createRowRealMatrix(double[] rowData) throws MatrixIndexException {
        int nCols = rowData.length;
        RealMatrix m = createRealMatrix(1, nCols);
        for (int i = 0; i < nCols; i++) {
            m.setEntry(0, i, rowData[i]);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createRowFieldMatrix(T[] rowData) throws MatrixIndexException {
        int nCols = rowData.length;
        if (nCols == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_COLUMN, new Object[0]);
        }
        FieldMatrix<T> m = createFieldMatrix(rowData[0].getField2(), 1, nCols);
        for (int i = 0; i < nCols; i++) {
            m.setEntry(0, i, rowData[i]);
        }
        return m;
    }

    @Deprecated
    public static BigMatrix createRowBigMatrix(double[] rowData) {
        int nCols = rowData.length;
        BigDecimal[][] data = new BigDecimal[1][nCols];
        for (int i = 0; i < nCols; i++) {
            data[0][i] = new BigDecimal(rowData[i]);
        }
        return new BigMatrixImpl(data, false);
    }

    @Deprecated
    public static BigMatrix createRowBigMatrix(BigDecimal[] rowData) {
        int nCols = rowData.length;
        BigDecimal[][] data = new BigDecimal[1][nCols];
        System.arraycopy(rowData, 0, data[0], 0, nCols);
        return new BigMatrixImpl(data, false);
    }

    @Deprecated
    public static BigMatrix createRowBigMatrix(String[] rowData) {
        int nCols = rowData.length;
        BigDecimal[][] data = new BigDecimal[1][nCols];
        for (int i = 0; i < nCols; i++) {
            data[0][i] = new BigDecimal(rowData[i]);
        }
        return new BigMatrixImpl(data, false);
    }

    public static RealMatrix createColumnRealMatrix(double[] columnData) throws MatrixIndexException {
        int nRows = columnData.length;
        RealMatrix m = createRealMatrix(nRows, 1);
        for (int i = 0; i < nRows; i++) {
            m.setEntry(i, 0, columnData[i]);
        }
        return m;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createColumnFieldMatrix(T[] columnData) throws MatrixIndexException {
        int nRows = columnData.length;
        if (nRows == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.AT_LEAST_ONE_ROW, new Object[0]);
        }
        FieldMatrix<T> m = createFieldMatrix(columnData[0].getField2(), nRows, 1);
        for (int i = 0; i < nRows; i++) {
            m.setEntry(i, 0, columnData[i]);
        }
        return m;
    }

    @Deprecated
    public static BigMatrix createColumnBigMatrix(double[] columnData) {
        int nRows = columnData.length;
        BigDecimal[][] data = new BigDecimal[nRows][1];
        for (int row = 0; row < nRows; row++) {
            data[row][0] = new BigDecimal(columnData[row]);
        }
        return new BigMatrixImpl(data, false);
    }

    @Deprecated
    public static BigMatrix createColumnBigMatrix(BigDecimal[] columnData) {
        int nRows = columnData.length;
        BigDecimal[][] data = new BigDecimal[nRows][1];
        for (int row = 0; row < nRows; row++) {
            data[row][0] = columnData[row];
        }
        return new BigMatrixImpl(data, false);
    }

    @Deprecated
    public static BigMatrix createColumnBigMatrix(String[] columnData) {
        int nRows = columnData.length;
        BigDecimal[][] data = new BigDecimal[nRows][1];
        for (int row = 0; row < nRows; row++) {
            data[row][0] = new BigDecimal(columnData[row]);
        }
        return new BigMatrixImpl(data, false);
    }

    public static void checkRowIndex(AnyMatrix m, int row) {
        if (row < 0 || row >= m.getRowDimension()) {
            throw new MatrixIndexException(LocalizedFormats.ROW_INDEX_OUT_OF_RANGE, Integer.valueOf(row), 0, Integer.valueOf(m.getRowDimension() - 1));
        }
    }

    public static void checkColumnIndex(AnyMatrix m, int column) throws MatrixIndexException {
        if (column < 0 || column >= m.getColumnDimension()) {
            throw new MatrixIndexException(LocalizedFormats.COLUMN_INDEX_OUT_OF_RANGE, Integer.valueOf(column), 0, Integer.valueOf(m.getColumnDimension() - 1));
        }
    }

    public static void checkSubMatrixIndex(AnyMatrix m, int startRow, int endRow, int startColumn, int endColumn) throws MatrixIndexException {
        checkRowIndex(m, startRow);
        checkRowIndex(m, endRow);
        if (startRow > endRow) {
            throw new MatrixIndexException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(startRow), Integer.valueOf(endRow));
        }
        checkColumnIndex(m, startColumn);
        checkColumnIndex(m, endColumn);
        if (startColumn > endColumn) {
            throw new MatrixIndexException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(startColumn), Integer.valueOf(endColumn));
        }
    }

    public static void checkSubMatrixIndex(AnyMatrix m, int[] selectedRows, int[] selectedColumns) throws MatrixIndexException {
        if (selectedRows.length * selectedColumns.length == 0) {
            if (selectedRows.length == 0) {
                throw new MatrixIndexException(LocalizedFormats.EMPTY_SELECTED_ROW_INDEX_ARRAY, new Object[0]);
            }
            throw new MatrixIndexException(LocalizedFormats.EMPTY_SELECTED_COLUMN_INDEX_ARRAY, new Object[0]);
        }
        for (int row : selectedRows) {
            checkRowIndex(m, row);
        }
        for (int column : selectedColumns) {
            checkColumnIndex(m, column);
        }
    }

    public static void checkAdditionCompatible(AnyMatrix left, AnyMatrix right) throws IllegalArgumentException {
        if (left.getRowDimension() != right.getRowDimension() || left.getColumnDimension() != right.getColumnDimension()) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_ADDITION_COMPATIBLE_MATRICES, Integer.valueOf(left.getRowDimension()), Integer.valueOf(left.getColumnDimension()), Integer.valueOf(right.getRowDimension()), Integer.valueOf(right.getColumnDimension()));
        }
    }

    public static void checkSubtractionCompatible(AnyMatrix left, AnyMatrix right) throws IllegalArgumentException {
        if (left.getRowDimension() != right.getRowDimension() || left.getColumnDimension() != right.getColumnDimension()) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_SUBTRACTION_COMPATIBLE_MATRICES, Integer.valueOf(left.getRowDimension()), Integer.valueOf(left.getColumnDimension()), Integer.valueOf(right.getRowDimension()), Integer.valueOf(right.getColumnDimension()));
        }
    }

    public static void checkMultiplicationCompatible(AnyMatrix left, AnyMatrix right) throws IllegalArgumentException {
        if (left.getColumnDimension() != right.getRowDimension()) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_MULTIPLICATION_COMPATIBLE_MATRICES, Integer.valueOf(left.getRowDimension()), Integer.valueOf(left.getColumnDimension()), Integer.valueOf(right.getRowDimension()), Integer.valueOf(right.getColumnDimension()));
        }
    }

    public static Array2DRowRealMatrix fractionMatrixToRealMatrix(FieldMatrix<Fraction> m) throws MatrixVisitorException {
        FractionMatrixConverter converter = new FractionMatrixConverter();
        m.walkInOptimizedOrder(converter);
        return converter.getConvertedMatrix();
    }

    public static Array2DRowRealMatrix bigFractionMatrixToRealMatrix(FieldMatrix<BigFraction> m) throws MatrixVisitorException {
        BigFractionMatrixConverter converter = new BigFractionMatrixConverter();
        m.walkInOptimizedOrder(converter);
        return converter.getConvertedMatrix();
    }

    public static void serializeRealVector(RealVector vector, ObjectOutputStream oos) throws IOException {
        int n = vector.getDimension();
        oos.writeInt(n);
        for (int i = 0; i < n; i++) {
            oos.writeDouble(vector.getEntry(i));
        }
    }

    public static void deserializeRealVector(Object instance, String fieldName, ObjectInputStream ois) throws IllegalAccessException, NoSuchFieldException, IOException, ClassNotFoundException, IllegalArgumentException {
        try {
            int n = ois.readInt();
            double[] data = new double[n];
            for (int i = 0; i < n; i++) {
                data[i] = ois.readDouble();
            }
            RealVector vector = new ArrayRealVector(data, false);
            java.lang.reflect.Field f = instance.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, vector);
        } catch (IllegalAccessException iae) {
            IOException ioe = new IOException();
            ioe.initCause(iae);
            throw ioe;
        } catch (NoSuchFieldException nsfe) {
            IOException ioe2 = new IOException();
            ioe2.initCause(nsfe);
            throw ioe2;
        }
    }

    public static void serializeRealMatrix(RealMatrix matrix, ObjectOutputStream oos) throws IOException {
        int n = matrix.getRowDimension();
        int m = matrix.getColumnDimension();
        oos.writeInt(n);
        oos.writeInt(m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                oos.writeDouble(matrix.getEntry(i, j));
            }
        }
    }

    public static void deserializeRealMatrix(Object instance, String fieldName, ObjectInputStream ois) throws IllegalAccessException, NoSuchFieldException, IOException, ClassNotFoundException, IllegalArgumentException {
        try {
            int n = ois.readInt();
            int m = ois.readInt();
            double[][] data = new double[n][m];
            for (int i = 0; i < n; i++) {
                double[] dataI = data[i];
                for (int j = 0; j < m; j++) {
                    dataI[j] = ois.readDouble();
                }
            }
            RealMatrix matrix = new Array2DRowRealMatrix(data, false);
            java.lang.reflect.Field f = instance.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, matrix);
        } catch (IllegalAccessException iae) {
            IOException ioe = new IOException();
            ioe.initCause(iae);
            throw ioe;
        } catch (NoSuchFieldException nsfe) {
            IOException ioe2 = new IOException();
            ioe2.initCause(nsfe);
            throw ioe2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/MatrixUtils$FractionMatrixConverter.class */
    private static class FractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<Fraction> {
        private double[][] data;

        public FractionMatrixConverter() {
            super(Fraction.ZERO);
        }

        @Override
        // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            this.data = new double[rows][columns];
        }

        @Override
        // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
        public void visit(int row, int column, Fraction value) {
            this.data[row][column] = value.doubleValue();
        }

        Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(this.data, false);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/MatrixUtils$BigFractionMatrixConverter.class */
    private static class BigFractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<BigFraction> {
        private double[][] data;

        public BigFractionMatrixConverter() {
            super(BigFraction.ZERO);
        }

        @Override
        // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            this.data = new double[rows][columns];
        }

        @Override
        // org.apache.commons.math.linear.DefaultFieldMatrixPreservingVisitor, org.apache.commons.math.linear.FieldMatrixPreservingVisitor
        public void visit(int row, int column, BigFraction value) {
            this.data[row][column] = value.doubleValue();
        }

        Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(this.data, false);
        }
    }
}
