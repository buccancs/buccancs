package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class FieldLUDecomposition<T extends FieldElement<T>> {
    private final Field<T> field;
    private FieldMatrix<T> cachedL;
    private FieldMatrix<T> cachedP;
    private FieldMatrix<T> cachedU;
    private boolean even;
    private T[][] lu;
    private int[] pivot;
    private boolean singular;

    public FieldLUDecomposition(FieldMatrix<T> fieldMatrix) {
        if (!fieldMatrix.isSquare()) {
            throw new NonSquareMatrixException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension());
        }
        int columnDimension = fieldMatrix.getColumnDimension();
        this.field = fieldMatrix.getField();
        this.lu = (T[][]) fieldMatrix.getData();
        this.pivot = new int[columnDimension];
        this.cachedL = null;
        this.cachedU = null;
        this.cachedP = null;
        for (int i = 0; i < columnDimension; i++) {
            this.pivot[i] = i;
        }
        this.even = true;
        this.singular = false;
        int i2 = 0;
        while (i2 < columnDimension) {
            this.field.getZero();
            for (int i3 = 0; i3 < i2; i3++) {
                FieldElement[] fieldElementArr = this.lu[i3];
                FieldElement fieldElement = fieldElementArr[i2];
                for (int i4 = 0; i4 < i3; i4++) {
                    fieldElement = (FieldElement) fieldElement.subtract(fieldElementArr[i4].multiply(this.lu[i4][i2]));
                }
                fieldElementArr[i2] = fieldElement;
            }
            int i5 = i2;
            int i6 = i5;
            while (i5 < columnDimension) {
                FieldElement[] fieldElementArr2 = this.lu[i5];
                FieldElement fieldElement2 = fieldElementArr2[i2];
                for (int i7 = 0; i7 < i2; i7++) {
                    fieldElement2 = (FieldElement) fieldElement2.subtract(fieldElementArr2[i7].multiply(this.lu[i7][i2]));
                }
                fieldElementArr2[i2] = fieldElement2;
                if (this.lu[i6][i2].equals(this.field.getZero())) {
                    i6++;
                }
                i5++;
            }
            if (i6 >= columnDimension) {
                this.singular = true;
                return;
            }
            if (i6 != i2) {
                this.field.getZero();
                for (int i8 = 0; i8 < columnDimension; i8++) {
                    T[][] tArr = this.lu;
                    T[] tArr2 = tArr[i6];
                    T t = tArr2[i8];
                    tArr2[i8] = tArr[i2][i8];
                    tArr[i2][i8] = t;
                }
                int[] iArr = this.pivot;
                int i9 = iArr[i6];
                iArr[i6] = iArr[i2];
                iArr[i2] = i9;
                this.even = !this.even;
            }
            T t2 = this.lu[i2][i2];
            int i10 = i2 + 1;
            for (int i11 = i10; i11 < columnDimension; i11++) {
                FieldElement[] fieldElementArr3 = this.lu[i11];
                fieldElementArr3[i2] = (FieldElement) fieldElementArr3[i2].divide(t2);
            }
            i2 = i10;
        }
    }

    public FieldMatrix<T> getL() throws OutOfRangeException {
        if (this.cachedL == null && !this.singular) {
            int length = this.pivot.length;
            this.cachedL = new Array2DRowFieldMatrix(this.field, length, length);
            for (int i = 0; i < length; i++) {
                T[] tArr = this.lu[i];
                for (int i2 = 0; i2 < i; i2++) {
                    this.cachedL.setEntry(i, i2, tArr[i2]);
                }
                this.cachedL.setEntry(i, i, this.field.getOne());
            }
        }
        return this.cachedL;
    }

    public FieldMatrix<T> getU() throws OutOfRangeException {
        if (this.cachedU == null && !this.singular) {
            int length = this.pivot.length;
            this.cachedU = new Array2DRowFieldMatrix(this.field, length, length);
            for (int i = 0; i < length; i++) {
                T[] tArr = this.lu[i];
                for (int i2 = i; i2 < length; i2++) {
                    this.cachedU.setEntry(i, i2, tArr[i2]);
                }
            }
        }
        return this.cachedU;
    }

    public FieldMatrix<T> getP() throws OutOfRangeException {
        if (this.cachedP == null && !this.singular) {
            int length = this.pivot.length;
            this.cachedP = new Array2DRowFieldMatrix(this.field, length, length);
            for (int i = 0; i < length; i++) {
                this.cachedP.setEntry(i, this.pivot[i], this.field.getOne());
            }
        }
        return this.cachedP;
    }

    public int[] getPivot() {
        return (int[]) this.pivot.clone();
    }

    public T getDeterminant() {
        if (this.singular) {
            return this.field.getZero();
        }
        int length = this.pivot.length;
        T t = (T) (this.even ? this.field.getOne() : this.field.getZero().subtract(this.field.getOne()));
        for (int i = 0; i < length; i++) {
            t = (T) t.multiply(this.lu[i][i]);
        }
        return t;
    }

    public FieldDecompositionSolver<T> getSolver() {
        return new Solver(this.field, this.lu, this.pivot, this.singular);
    }

    private static class Solver<T extends FieldElement<T>> implements FieldDecompositionSolver<T> {
        private final Field<T> field;
        private final T[][] lu;
        private final int[] pivot;
        private final boolean singular;

        private Solver(Field<T> field, T[][] tArr, int[] iArr, boolean z) {
            this.field = field;
            this.lu = tArr;
            this.pivot = iArr;
            this.singular = z;
        }

        @Override // org.apache.commons.math3.linear.FieldDecompositionSolver
        public boolean isNonSingular() {
            return !this.singular;
        }

        @Override // org.apache.commons.math3.linear.FieldDecompositionSolver
        public FieldVector<T> solve(FieldVector<T> fieldVector) {
            try {
                return solve((ArrayFieldVector) fieldVector);
            } catch (ClassCastException unused) {
                int length = this.pivot.length;
                if (fieldVector.getDimension() != length) {
                    throw new DimensionMismatchException(fieldVector.getDimension(), length);
                }
                if (this.singular) {
                    throw new SingularMatrixException();
                }
                FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, length);
                for (int i = 0; i < length; i++) {
                    fieldElementArr[i] = fieldVector.getEntry(this.pivot[i]);
                }
                int i2 = 0;
                while (i2 < length) {
                    FieldElement fieldElement = fieldElementArr[i2];
                    int i3 = i2 + 1;
                    for (int i4 = i3; i4 < length; i4++) {
                        fieldElementArr[i4] = (FieldElement) fieldElementArr[i4].subtract(fieldElement.multiply(this.lu[i4][i2]));
                    }
                    i2 = i3;
                }
                for (int i5 = length - 1; i5 >= 0; i5--) {
                    FieldElement fieldElement2 = (FieldElement) fieldElementArr[i5].divide(this.lu[i5][i5]);
                    fieldElementArr[i5] = fieldElement2;
                    for (int i6 = 0; i6 < i5; i6++) {
                        fieldElementArr[i6] = (FieldElement) fieldElementArr[i6].subtract(fieldElement2.multiply(this.lu[i6][i5]));
                    }
                }
                return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
            }
        }

        public ArrayFieldVector<T> solve(ArrayFieldVector<T> arrayFieldVector) {
            int length = this.pivot.length;
            int dimension = arrayFieldVector.getDimension();
            if (dimension != length) {
                throw new DimensionMismatchException(dimension, length);
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, length);
            for (int i = 0; i < length; i++) {
                fieldElementArr[i] = arrayFieldVector.getEntry(this.pivot[i]);
            }
            int i2 = 0;
            while (i2 < length) {
                FieldElement fieldElement = fieldElementArr[i2];
                int i3 = i2 + 1;
                for (int i4 = i3; i4 < length; i4++) {
                    fieldElementArr[i4] = (FieldElement) fieldElementArr[i4].subtract(fieldElement.multiply(this.lu[i4][i2]));
                }
                i2 = i3;
            }
            for (int i5 = length - 1; i5 >= 0; i5--) {
                FieldElement fieldElement2 = (FieldElement) fieldElementArr[i5].divide(this.lu[i5][i5]);
                fieldElementArr[i5] = fieldElement2;
                for (int i6 = 0; i6 < i5; i6++) {
                    fieldElementArr[i6] = (FieldElement) fieldElementArr[i6].subtract(fieldElement2.multiply(this.lu[i6][i5]));
                }
            }
            return new ArrayFieldVector<>(fieldElementArr, false);
        }

        @Override // org.apache.commons.math3.linear.FieldDecompositionSolver
        public FieldMatrix<T> solve(FieldMatrix<T> fieldMatrix) {
            int length = this.pivot.length;
            if (fieldMatrix.getRowDimension() != length) {
                throw new DimensionMismatchException(fieldMatrix.getRowDimension(), length);
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            int columnDimension = fieldMatrix.getColumnDimension();
            FieldElement[][] fieldElementArr = (FieldElement[][]) MathArrays.buildArray(this.field, length, columnDimension);
            for (int i = 0; i < length; i++) {
                FieldElement[] fieldElementArr2 = fieldElementArr[i];
                int i2 = this.pivot[i];
                for (int i3 = 0; i3 < columnDimension; i3++) {
                    fieldElementArr2[i3] = fieldMatrix.getEntry(i2, i3);
                }
            }
            int i4 = 0;
            while (i4 < length) {
                FieldElement[] fieldElementArr3 = fieldElementArr[i4];
                int i5 = i4 + 1;
                for (int i6 = i5; i6 < length; i6++) {
                    FieldElement[] fieldElementArr4 = fieldElementArr[i6];
                    T t = this.lu[i6][i4];
                    for (int i7 = 0; i7 < columnDimension; i7++) {
                        fieldElementArr4[i7] = (FieldElement) fieldElementArr4[i7].subtract(fieldElementArr3[i7].multiply(t));
                    }
                }
                i4 = i5;
            }
            for (int i8 = length - 1; i8 >= 0; i8--) {
                FieldElement[] fieldElementArr5 = fieldElementArr[i8];
                T t2 = this.lu[i8][i8];
                for (int i9 = 0; i9 < columnDimension; i9++) {
                    fieldElementArr5[i9] = (FieldElement) fieldElementArr5[i9].divide(t2);
                }
                for (int i10 = 0; i10 < i8; i10++) {
                    FieldElement[] fieldElementArr6 = fieldElementArr[i10];
                    T t3 = this.lu[i10][i8];
                    for (int i11 = 0; i11 < columnDimension; i11++) {
                        fieldElementArr6[i11] = (FieldElement) fieldElementArr6[i11].subtract(fieldElementArr5[i11].multiply(t3));
                    }
                }
            }
            return new Array2DRowFieldMatrix((Field) this.field, fieldElementArr, false);
        }

        @Override // org.apache.commons.math3.linear.FieldDecompositionSolver
        public FieldMatrix<T> getInverse() throws OutOfRangeException {
            int length = this.pivot.length;
            T one = this.field.getOne();
            Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, length);
            for (int i = 0; i < length; i++) {
                array2DRowFieldMatrix.setEntry(i, i, one);
            }
            return solve(array2DRowFieldMatrix);
        }
    }
}
