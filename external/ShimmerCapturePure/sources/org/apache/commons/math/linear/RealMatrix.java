package org.apache.commons.math.linear;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/RealMatrix.class */
public interface RealMatrix extends AnyMatrix {
    RealMatrix createMatrix(int i, int i2);

    RealMatrix copy();

    RealMatrix add(RealMatrix realMatrix) throws IllegalArgumentException;

    RealMatrix subtract(RealMatrix realMatrix) throws IllegalArgumentException;

    RealMatrix scalarAdd(double d);

    RealMatrix scalarMultiply(double d);

    RealMatrix multiply(RealMatrix realMatrix) throws IllegalArgumentException;

    RealMatrix preMultiply(RealMatrix realMatrix) throws IllegalArgumentException;

    double[][] getData();

    double getNorm();

    double getFrobeniusNorm();

    RealMatrix getSubMatrix(int i, int i2, int i3, int i4) throws MatrixIndexException;

    RealMatrix getSubMatrix(int[] iArr, int[] iArr2) throws MatrixIndexException;

    void copySubMatrix(int i, int i2, int i3, int i4, double[][] dArr) throws MatrixIndexException, IllegalArgumentException;

    void copySubMatrix(int[] iArr, int[] iArr2, double[][] dArr) throws MatrixIndexException, IllegalArgumentException;

    void setSubMatrix(double[][] dArr, int i, int i2) throws MatrixIndexException;

    RealMatrix getRowMatrix(int i) throws MatrixIndexException;

    void setRowMatrix(int i, RealMatrix realMatrix) throws MatrixIndexException, InvalidMatrixException;

    RealMatrix getColumnMatrix(int i) throws MatrixIndexException;

    void setColumnMatrix(int i, RealMatrix realMatrix) throws MatrixIndexException, InvalidMatrixException;

    RealVector getRowVector(int i) throws MatrixIndexException;

    void setRowVector(int i, RealVector realVector) throws MatrixIndexException, InvalidMatrixException;

    RealVector getColumnVector(int i) throws MatrixIndexException;

    void setColumnVector(int i, RealVector realVector) throws MatrixIndexException, InvalidMatrixException;

    double[] getRow(int i) throws MatrixIndexException;

    void setRow(int i, double[] dArr) throws MatrixIndexException, InvalidMatrixException;

    double[] getColumn(int i) throws MatrixIndexException;

    void setColumn(int i, double[] dArr) throws MatrixIndexException, InvalidMatrixException;

    double getEntry(int i, int i2) throws MatrixIndexException;

    void setEntry(int i, int i2, double d) throws MatrixIndexException;

    void addToEntry(int i, int i2, double d) throws MatrixIndexException;

    void multiplyEntry(int i, int i2, double d) throws MatrixIndexException;

    RealMatrix transpose();

    @Deprecated
    RealMatrix inverse() throws InvalidMatrixException;

    @Deprecated
    double getDeterminant();

    @Deprecated
    boolean isSingular();

    double getTrace() throws NonSquareMatrixException;

    double[] operate(double[] dArr) throws IllegalArgumentException;

    RealVector operate(RealVector realVector) throws IllegalArgumentException;

    double[] preMultiply(double[] dArr) throws IllegalArgumentException;

    RealVector preMultiply(RealVector realVector) throws IllegalArgumentException;

    double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) throws MatrixVisitorException;

    double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) throws MatrixVisitorException;

    double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException;

    double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException;

    double walkInColumnOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) throws MatrixVisitorException;

    double walkInColumnOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) throws MatrixVisitorException;

    double walkInColumnOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException;

    double walkInColumnOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException;

    double walkInOptimizedOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) throws MatrixVisitorException;

    double walkInOptimizedOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) throws MatrixVisitorException;

    double walkInOptimizedOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException;

    double walkInOptimizedOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws MatrixVisitorException, MatrixIndexException;

    @Deprecated
    double[] solve(double[] dArr) throws InvalidMatrixException, IllegalArgumentException;

    @Deprecated
    RealMatrix solve(RealMatrix realMatrix) throws InvalidMatrixException, IllegalArgumentException;
}
