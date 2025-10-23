package org.apache.commons.math3.linear;

import java.io.Serializable;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
    public static final int BLOCK_SIZE = 36;
    private static final long serialVersionUID = -4602336630143123183L;
    private final int blockColumns;
    private final int blockRows;
    private final T[][] blocks;
    private final int columns;
    private final int rows;

    public BlockFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        super(field, i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = (i + 35) / 36;
        this.blockColumns = (i2 + 35) / 36;
        this.blocks = (T[][]) createBlocksLayout(field, i, i2);
    }

    public BlockFieldMatrix(T[][] tArr) throws DimensionMismatchException {
        this(tArr.length, tArr[0].length, toBlocksLayout(tArr), false);
    }

    public BlockFieldMatrix(int i, int i2, T[][] tArr, boolean z) throws NotStrictlyPositiveException, DimensionMismatchException {
        super(extractField(tArr), i, i2);
        this.rows = i;
        this.columns = i2;
        int i3 = (i + 35) / 36;
        this.blockRows = i3;
        int i4 = (i2 + 35) / 36;
        this.blockColumns = i4;
        if (z) {
            this.blocks = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), i3 * i4, -1));
        } else {
            this.blocks = tArr;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockRows; i6++) {
            int iBlockHeight = blockHeight(i6);
            int i7 = 0;
            while (i7 < this.blockColumns) {
                if (tArr[i5].length != blockWidth(i7) * iBlockHeight) {
                    throw new DimensionMismatchException(tArr[i5].length, iBlockHeight * blockWidth(i7));
                }
                if (z) {
                    ((T[][]) this.blocks)[i5] = (FieldElement[]) tArr[i5].clone();
                }
                i7++;
                i5++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends FieldElement<T>> T[][] toBlocksLayout(T[][] tArr) throws DimensionMismatchException {
        int length = tArr.length;
        int length2 = tArr[0].length;
        int i = (length + 35) / 36;
        int i2 = (length2 + 35) / 36;
        for (T[] tArr2 : tArr) {
            int length3 = tArr2.length;
            if (length3 != length2) {
                throw new DimensionMismatchException(length2, length3);
            }
        }
        Field fieldExtractField = extractField(tArr);
        T[][] tArr3 = (T[][]) ((FieldElement[][]) MathArrays.buildArray(fieldExtractField, i * i2, -1));
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i4 * 36;
            int iMin = FastMath.min(i5 + 36, length);
            int i6 = iMin - i5;
            int i7 = 0;
            while (i7 < i2) {
                int i8 = i7 * 36;
                int iMin2 = FastMath.min(i8 + 36, length2) - i8;
                FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(fieldExtractField, i6 * iMin2);
                tArr3[i3] = fieldElementArr;
                int i9 = length;
                int i10 = length2;
                int i11 = i5;
                int i12 = 0;
                while (i11 < iMin) {
                    System.arraycopy(tArr[i11], i8, fieldElementArr, i12, iMin2);
                    i12 += iMin2;
                    i11++;
                    i = i;
                }
                i3++;
                i7++;
                length = i9;
                length2 = i10;
            }
        }
        return tArr3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends FieldElement<T>> T[][] createBlocksLayout(Field<T> field, int i, int i2) {
        int i3 = (i + 35) / 36;
        int i4 = (i2 + 35) / 36;
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(field, i3 * i4, -1));
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i6 * 36;
            int iMin = FastMath.min(i7 + 36, i) - i7;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = i8 * 36;
                tArr[i5] = (FieldElement[]) MathArrays.buildArray(field, (FastMath.min(i9 + 36, i2) - i9) * iMin);
                i5++;
            }
        }
        return tArr;
    }

    private int blockHeight(int i) {
        if (i == this.blockRows - 1) {
            return this.rows - (i * 36);
        }
        return 36;
    }

    private int blockWidth(int i) {
        if (i == this.blockColumns - 1) {
            return this.columns - (i * 36);
        }
        return 36;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getColumnDimension() {
        return this.columns;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getRowDimension() {
        return this.rows;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new BlockFieldMatrix(getField(), i, i2);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> copy() {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            T[][] tArr = this.blocks;
            if (i >= tArr.length) {
                return blockFieldMatrix;
            }
            T[] tArr2 = tArr[i];
            System.arraycopy(tArr2, 0, blockFieldMatrix.blocks[i], 0, tArr2.length);
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> add(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        try {
            return add((BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            checkAdditionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
            int i = 0;
            for (int i2 = 0; i2 < blockFieldMatrix.blockRows; i2++) {
                for (int i3 = 0; i3 < blockFieldMatrix.blockColumns; i3++) {
                    FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix.blocks)[i];
                    T[] tArr = this.blocks[i];
                    int i4 = i2 * 36;
                    int iMin = FastMath.min(i4 + 36, this.rows);
                    int i5 = i3 * 36;
                    int iMin2 = FastMath.min(i5 + 36, this.columns);
                    int i6 = 0;
                    while (i4 < iMin) {
                        for (int i7 = i5; i7 < iMin2; i7++) {
                            fieldElementArr[i6] = (FieldElement) tArr[i6].add(fieldMatrix.getEntry(i4, i7));
                            i6++;
                        }
                        i4++;
                    }
                    i++;
                }
            }
            return blockFieldMatrix;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BlockFieldMatrix<T> add(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            FieldElement[][] fieldElementArr = (T[][]) blockFieldMatrix2.blocks;
            if (i >= fieldElementArr.length) {
                return blockFieldMatrix2;
            }
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            T[] tArr = this.blocks[i];
            T[] tArr2 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr2.length; i2++) {
                fieldElementArr2[i2] = (FieldElement) tArr[i2].add(tArr2[i2]);
            }
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> subtract(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        try {
            return subtract((BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            checkSubtractionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
            int i = 0;
            for (int i2 = 0; i2 < blockFieldMatrix.blockRows; i2++) {
                for (int i3 = 0; i3 < blockFieldMatrix.blockColumns; i3++) {
                    FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix.blocks)[i];
                    T[] tArr = this.blocks[i];
                    int i4 = i2 * 36;
                    int iMin = FastMath.min(i4 + 36, this.rows);
                    int i5 = i3 * 36;
                    int iMin2 = FastMath.min(i5 + 36, this.columns);
                    int i6 = 0;
                    while (i4 < iMin) {
                        for (int i7 = i5; i7 < iMin2; i7++) {
                            fieldElementArr[i6] = (FieldElement) tArr[i6].subtract(fieldMatrix.getEntry(i4, i7));
                            i6++;
                        }
                        i4++;
                    }
                    i++;
                }
            }
            return blockFieldMatrix;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BlockFieldMatrix<T> subtract(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            FieldElement[][] fieldElementArr = (T[][]) blockFieldMatrix2.blocks;
            if (i >= fieldElementArr.length) {
                return blockFieldMatrix2;
            }
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            T[] tArr = this.blocks[i];
            T[] tArr2 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr2.length; i2++) {
                fieldElementArr2[i2] = (FieldElement) tArr[i2].subtract(tArr2[i2]);
            }
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> scalarAdd(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            FieldElement[][] fieldElementArr = (T[][]) blockFieldMatrix.blocks;
            if (i >= fieldElementArr.length) {
                return blockFieldMatrix;
            }
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            T[] tArr = this.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr2.length; i2++) {
                fieldElementArr2[i2] = (FieldElement) tArr[i2].add(t);
            }
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> scalarMultiply(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        int i = 0;
        while (true) {
            FieldElement[][] fieldElementArr = (T[][]) blockFieldMatrix.blocks;
            if (i >= fieldElementArr.length) {
                return blockFieldMatrix;
            }
            FieldElement[] fieldElementArr2 = fieldElementArr[i];
            T[] tArr = this.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr2.length; i2++) {
                fieldElementArr2[i2] = (FieldElement) tArr[i2].multiply(t);
            }
            i++;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> multiply(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        try {
            return blockFieldMatrix.multiply((BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            checkMultiplicationCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix2 = new BlockFieldMatrix(getField(), blockFieldMatrix.rows, fieldMatrix.getColumnDimension());
            T zero = getField().getZero();
            int i = 0;
            int i2 = 0;
            while (i < blockFieldMatrix2.blockRows) {
                int i3 = i * 36;
                int iMin = FastMath.min(i3 + 36, blockFieldMatrix.rows);
                int i4 = 0;
                while (i4 < blockFieldMatrix2.blockColumns) {
                    int i5 = i4 * 36;
                    int iMin2 = FastMath.min(i5 + 36, fieldMatrix.getColumnDimension());
                    FieldElement[] fieldElementArr = blockFieldMatrix2.blocks[i2];
                    int i6 = 0;
                    while (i6 < blockFieldMatrix.blockColumns) {
                        int iBlockWidth = blockFieldMatrix.blockWidth(i6);
                        T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i) + i6];
                        int i7 = i6 * 36;
                        int i8 = i3;
                        int i9 = 0;
                        while (i8 < iMin) {
                            int i10 = (i8 - i3) * iBlockWidth;
                            T t = zero;
                            int i11 = i10 + iBlockWidth;
                            int i12 = i3;
                            int i13 = i5;
                            while (i13 < iMin2) {
                                int i14 = iMin;
                                int i15 = i5;
                                int i16 = iMin2;
                                int i17 = i7;
                                int i18 = i10;
                                FieldElement fieldElement = t;
                                while (i18 < i11) {
                                    fieldElement = (FieldElement) fieldElement.add(tArr[i18].multiply(fieldMatrix.getEntry(i17, i13)));
                                    i17++;
                                    i18++;
                                    i11 = i11;
                                    tArr = tArr;
                                }
                                fieldElementArr[i9] = (FieldElement) fieldElementArr[i9].add(fieldElement);
                                i9++;
                                i13++;
                                iMin = i14;
                                i5 = i15;
                                iMin2 = i16;
                                i11 = i11;
                            }
                            i8++;
                            zero = t;
                            i3 = i12;
                        }
                        i6++;
                        blockFieldMatrix = this;
                    }
                    i2++;
                    i4++;
                    blockFieldMatrix = this;
                }
                i++;
                blockFieldMatrix = this;
            }
            return blockFieldMatrix2;
        }
    }

    public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> blockFieldMatrix) throws DimensionMismatchException {
        int i;
        BlockFieldMatrix<T> blockFieldMatrix2 = this;
        BlockFieldMatrix<T> blockFieldMatrix3 = blockFieldMatrix;
        checkMultiplicationCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix4 = new BlockFieldMatrix<>(getField(), blockFieldMatrix2.rows, blockFieldMatrix3.columns);
        T zero = getField().getZero();
        int i2 = 0;
        int i3 = 0;
        while (i2 < blockFieldMatrix4.blockRows) {
            int i4 = i2 * 36;
            int iMin = FastMath.min(i4 + 36, blockFieldMatrix2.rows);
            int i5 = 0;
            while (i5 < blockFieldMatrix4.blockColumns) {
                int iBlockWidth = blockFieldMatrix4.blockWidth(i5);
                int i6 = iBlockWidth + iBlockWidth;
                int i7 = i6 + iBlockWidth;
                int i8 = i7 + iBlockWidth;
                FieldElement[] fieldElementArr = blockFieldMatrix4.blocks[i3];
                int i9 = 0;
                while (i9 < blockFieldMatrix2.blockColumns) {
                    int iBlockWidth2 = blockFieldMatrix2.blockWidth(i9);
                    T t = zero;
                    BlockFieldMatrix<T> blockFieldMatrix5 = blockFieldMatrix4;
                    T[] tArr = blockFieldMatrix2.blocks[(blockFieldMatrix2.blockColumns * i2) + i9];
                    T[] tArr2 = blockFieldMatrix3.blocks[(blockFieldMatrix3.blockColumns * i9) + i5];
                    int i10 = i4;
                    int i11 = 0;
                    while (i10 < iMin) {
                        int i12 = (i10 - i4) * iBlockWidth2;
                        int i13 = i12 + iBlockWidth2;
                        int i14 = iBlockWidth2;
                        int i15 = 0;
                        while (i15 < iBlockWidth) {
                            int i16 = i15;
                            int i17 = i4;
                            int i18 = iMin;
                            FieldElement fieldElement = t;
                            int i19 = i12;
                            while (true) {
                                i = i2;
                                if (i19 >= i13 - 3) {
                                    break;
                                }
                                fieldElement = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement.add(tArr[i19].multiply(tArr2[i16]))).add(tArr[i19 + 1].multiply(tArr2[i16 + iBlockWidth]))).add(tArr[i19 + 2].multiply(tArr2[i16 + i6]))).add(tArr[i19 + 3].multiply(tArr2[i16 + i7]));
                                i19 += 4;
                                i16 += i8;
                                i2 = i;
                                i5 = i5;
                            }
                            int i20 = i5;
                            while (i19 < i13) {
                                fieldElement = (FieldElement) fieldElement.add(tArr[i19].multiply(tArr2[i16]));
                                i16 += iBlockWidth;
                                i19++;
                            }
                            fieldElementArr[i11] = (FieldElement) fieldElementArr[i11].add(fieldElement);
                            i11++;
                            i15++;
                            i4 = i17;
                            iMin = i18;
                            i2 = i;
                            i5 = i20;
                        }
                        i10++;
                        iBlockWidth2 = i14;
                    }
                    i9++;
                    blockFieldMatrix2 = this;
                    blockFieldMatrix3 = blockFieldMatrix;
                    zero = t;
                    blockFieldMatrix4 = blockFieldMatrix5;
                }
                i3++;
                i5++;
                blockFieldMatrix2 = this;
                blockFieldMatrix3 = blockFieldMatrix;
            }
            i2++;
            blockFieldMatrix2 = this;
            blockFieldMatrix3 = blockFieldMatrix;
        }
        return blockFieldMatrix4;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[][] getData() {
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), getRowDimension(), getColumnDimension()));
        int i = this.columns - ((this.blockColumns - 1) * 36);
        for (int i2 = 0; i2 < this.blockRows; i2++) {
            int i3 = i2 * 36;
            int iMin = FastMath.min(i3 + 36, this.rows);
            int i4 = 0;
            int i5 = 0;
            while (i3 < iMin) {
                T[] tArr2 = tArr[i3];
                int i6 = this.blockColumns * i2;
                int i7 = 0;
                int i8 = 0;
                while (i7 < this.blockColumns - 1) {
                    System.arraycopy(this.blocks[i6], i4, tArr2, i8, 36);
                    i8 += 36;
                    i7++;
                    i6++;
                }
                System.arraycopy(this.blocks[i6], i5, tArr2, i8, i);
                i4 += 36;
                i5 += i;
                i3++;
            }
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getSubMatrix(int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        int i5;
        int i6;
        int i7;
        checkSubMatrixIndex(i, i2, i3, i4);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), (i2 - i) + 1, (i4 - i3) + 1);
        int i8 = i % 36;
        int i9 = i3 / 36;
        int i10 = i3 % 36;
        int i11 = i / 36;
        int i12 = 0;
        while (i12 < blockFieldMatrix.blockRows) {
            int iBlockHeight = blockFieldMatrix.blockHeight(i12);
            int i13 = i9;
            int i14 = 0;
            while (i14 < blockFieldMatrix.blockColumns) {
                int iBlockWidth = blockFieldMatrix.blockWidth(i14);
                T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i12) + i14];
                int i15 = (this.blockColumns * i11) + i13;
                int iBlockWidth2 = blockWidth(i13);
                int i16 = iBlockHeight + i8;
                int i17 = i16 - 36;
                int i18 = iBlockWidth + i10;
                int i19 = i18 - 36;
                if (i17 <= 0) {
                    i5 = i13;
                    i6 = i14;
                    i7 = i12;
                    if (i19 > 0) {
                        int iBlockWidth3 = blockWidth(i5 + 1);
                        copyBlockPart(this.blocks[i15], iBlockWidth2, i8, i16, i10, 36, tArr, iBlockWidth, 0, 0);
                        copyBlockPart(this.blocks[i15 + 1], iBlockWidth3, i8, i16, 0, i19, tArr, iBlockWidth, 0, iBlockWidth - i19);
                    } else {
                        copyBlockPart(this.blocks[i15], iBlockWidth2, i8, i16, i10, i18, tArr, iBlockWidth, 0, 0);
                    }
                } else if (i19 > 0) {
                    int iBlockWidth4 = blockWidth(i13 + 1);
                    i5 = i13;
                    i6 = i14;
                    i7 = i12;
                    copyBlockPart(this.blocks[i15], iBlockWidth2, i8, 36, i10, 36, tArr, iBlockWidth, 0, 0);
                    int i20 = iBlockWidth - i19;
                    copyBlockPart(this.blocks[i15 + 1], iBlockWidth4, i8, 36, 0, i19, tArr, iBlockWidth, 0, i20);
                    int i21 = iBlockHeight - i17;
                    copyBlockPart(this.blocks[i15 + this.blockColumns], iBlockWidth2, 0, i17, i10, 36, tArr, iBlockWidth, i21, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns + 1], iBlockWidth4, 0, i17, 0, i19, tArr, iBlockWidth, i21, i20);
                } else {
                    i5 = i13;
                    i6 = i14;
                    i7 = i12;
                    copyBlockPart(this.blocks[i15], iBlockWidth2, i8, 36, i10, i18, tArr, iBlockWidth, 0, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns], iBlockWidth2, 0, i17, i10, i18, tArr, iBlockWidth, iBlockHeight - i17, 0);
                }
                i13 = i5 + 1;
                i14 = i6 + 1;
                i12 = i7;
            }
            i11++;
            i12++;
        }
        return blockFieldMatrix;
    }

    private void copyBlockPart(T[] tArr, int i, int i2, int i3, int i4, int i5, T[] tArr2, int i6, int i7, int i8) {
        int i9 = i5 - i4;
        int i10 = (i2 * i) + i4;
        int i11 = (i7 * i6) + i8;
        while (i2 < i3) {
            System.arraycopy(tArr, i10, tArr2, i11, i9);
            i10 += i;
            i11 += i6;
            i2++;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setSubMatrix(T[][] tArr, int i, int i2) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        T[][] tArr2 = tArr;
        int i3 = i;
        MathUtils.checkNotNull(tArr);
        int length = tArr2[0].length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int length2 = tArr2.length + i3;
        int i4 = i2 + length;
        blockFieldMatrix.checkSubMatrixIndex(i3, length2 - 1, i2, i4 - 1);
        for (T[] tArr3 : tArr2) {
            if (tArr3.length != length) {
                throw new DimensionMismatchException(length, tArr3.length);
            }
        }
        int i5 = i3 / 36;
        int i6 = (length2 + 35) / 36;
        int i7 = i2 / 36;
        int i8 = (i4 + 35) / 36;
        while (i5 < i6) {
            int iBlockHeight = blockFieldMatrix.blockHeight(i5);
            int i9 = i5 * 36;
            int iMax = FastMath.max(i3, i9);
            int iMin = FastMath.min(length2, iBlockHeight + i9);
            int i10 = i7;
            while (i10 < i8) {
                int iBlockWidth = blockFieldMatrix.blockWidth(i10);
                int i11 = i10 * 36;
                int iMax2 = FastMath.max(i2, i11);
                int i12 = i6;
                int iMin2 = FastMath.min(i4, i11 + iBlockWidth) - iMax2;
                int i13 = length2;
                int i14 = i7;
                T[] tArr4 = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i5) + i10];
                int i15 = iMax;
                while (i15 < iMin) {
                    System.arraycopy(tArr2[i15 - i3], iMax2 - i2, tArr4, ((i15 - i9) * iBlockWidth) + (iMax2 - i11), iMin2);
                    i15++;
                    tArr2 = tArr;
                    i3 = i;
                }
                i10++;
                blockFieldMatrix = this;
                tArr2 = tArr;
                i3 = i;
                i6 = i12;
                length2 = i13;
                i7 = i14;
            }
            i5++;
            blockFieldMatrix = this;
            tArr2 = tArr;
            i3 = i;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getRowMatrix(int i) throws OutOfRangeException {
        checkRowIndex(i);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), 1, this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockColumns; i6++) {
            int iBlockWidth = blockWidth(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i2) + i6];
            int length = tArr.length - i4;
            if (iBlockWidth > length) {
                int i7 = i3 * iBlockWidth;
                System.arraycopy(tArr2, i7, tArr, i4, length);
                i5++;
                tArr = blockFieldMatrix.blocks[i5];
                int i8 = iBlockWidth - length;
                System.arraycopy(tArr2, i7, tArr, 0, i8);
                i4 = i8;
            } else {
                System.arraycopy(tArr2, i3 * iBlockWidth, tArr, i4, iBlockWidth);
                i4 += iBlockWidth;
            }
        }
        return blockFieldMatrix;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setRowMatrix(int i, FieldMatrix<T> fieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRowMatrix(i, (BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            super.setRowMatrix(i, fieldMatrix);
        }
    }

    public void setRowMatrix(int i, BlockFieldMatrix<T> blockFieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (blockFieldMatrix.getRowDimension() != 1 || blockFieldMatrix.getColumnDimension() != columnDimension) {
            throw new MatrixDimensionMismatchException(blockFieldMatrix.getRowDimension(), blockFieldMatrix.getColumnDimension(), 1, columnDimension);
        }
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockColumns; i6++) {
            int iBlockWidth = blockWidth(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i2) + i6];
            int length = tArr.length - i4;
            if (iBlockWidth > length) {
                int i7 = i3 * iBlockWidth;
                System.arraycopy(tArr, i4, tArr2, i7, length);
                i5++;
                tArr = blockFieldMatrix.blocks[i5];
                int i8 = iBlockWidth - length;
                System.arraycopy(tArr, 0, tArr2, i7, i8);
                i4 = i8;
            } else {
                System.arraycopy(tArr, i4, tArr2, i3 * iBlockWidth, iBlockWidth);
                i4 += iBlockWidth;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getColumnMatrix(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, 1);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int iBlockWidth = blockWidth(i2);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockRows; i6++) {
            int iBlockHeight = blockHeight(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i6) + i2];
            int i7 = 0;
            while (i7 < iBlockHeight) {
                if (i4 >= tArr.length) {
                    i5++;
                    tArr = blockFieldMatrix.blocks[i5];
                    i4 = 0;
                }
                tArr[i4] = tArr2[(i7 * iBlockWidth) + i3];
                i7++;
                i4++;
            }
        }
        return blockFieldMatrix;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setColumnMatrix(int i, FieldMatrix<T> fieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumnMatrix(i, (BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            super.setColumnMatrix(i, fieldMatrix);
        }
    }

    void setColumnMatrix(int i, BlockFieldMatrix<T> blockFieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (blockFieldMatrix.getRowDimension() != rowDimension || blockFieldMatrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(blockFieldMatrix.getRowDimension(), blockFieldMatrix.getColumnDimension(), rowDimension, 1);
        }
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int iBlockWidth = blockWidth(i2);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockRows; i6++) {
            int iBlockHeight = blockHeight(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i6) + i2];
            int i7 = 0;
            while (i7 < iBlockHeight) {
                if (i4 >= tArr.length) {
                    i5++;
                    tArr = blockFieldMatrix.blocks[i5];
                    i4 = 0;
                }
                tArr2[(i7 * iBlockWidth) + i3] = tArr[i4];
                i7++;
                i4++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> getRowVector(int i) throws OutOfRangeException {
        checkRowIndex(i);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int iBlockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * iBlockWidth, fieldElementArr, i4, iBlockWidth);
            i4 += iBlockWidth;
        }
        return new ArrayFieldVector((Field) getField(), fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setRowVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRow(i, ((ArrayFieldVector) fieldVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setRowVector(i, fieldVector);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> getColumnVector(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.rows);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int iBlockWidth = blockWidth(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockRows; i5++) {
            int iBlockHeight = blockHeight(i5);
            T[] tArr = this.blocks[(this.blockColumns * i5) + i2];
            int i6 = 0;
            while (i6 < iBlockHeight) {
                fieldElementArr[i4] = tArr[(i6 * iBlockWidth) + i3];
                i6++;
                i4++;
            }
        }
        return new ArrayFieldVector((Field) getField(), fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setColumnVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(i, ((ArrayFieldVector) fieldVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setColumnVector(i, fieldVector);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] getRow(int i) throws OutOfRangeException {
        checkRowIndex(i);
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), this.columns));
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int iBlockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * iBlockWidth, tArr, i4, iBlockWidth);
            i4 += iBlockWidth;
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setRow(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new MatrixDimensionMismatchException(1, tArr.length, 1, columnDimension);
        }
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int iBlockWidth = blockWidth(i5);
            System.arraycopy(tArr, i4, this.blocks[(this.blockColumns * i2) + i5], i3 * iBlockWidth, iBlockWidth);
            i4 += iBlockWidth;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] getColumn(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), this.rows));
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int iBlockWidth = blockWidth(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockRows; i5++) {
            int iBlockHeight = blockHeight(i5);
            T[] tArr2 = this.blocks[(this.blockColumns * i5) + i2];
            int i6 = 0;
            while (i6 < iBlockHeight) {
                tArr[i4] = tArr2[(i6 * iBlockWidth) + i3];
                i6++;
                i4++;
            }
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setColumn(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (tArr.length != rowDimension) {
            throw new MatrixDimensionMismatchException(tArr.length, 1, rowDimension, 1);
        }
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int iBlockWidth = blockWidth(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockRows; i5++) {
            int iBlockHeight = blockHeight(i5);
            T[] tArr2 = this.blocks[(this.blockColumns * i5) + i2];
            int i6 = 0;
            while (i6 < iBlockHeight) {
                tArr2[(i6 * iBlockWidth) + i3] = tArr[i4];
                i6++;
                i4++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T getEntry(int i, int i2) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        return this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36))];
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36))] = t;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void addToEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        int iBlockWidth = ((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36));
        FieldElement[] fieldElementArr = this.blocks[(i3 * this.blockColumns) + i4];
        fieldElementArr[iBlockWidth] = (FieldElement) fieldElementArr[iBlockWidth].add(t);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void multiplyEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        int iBlockWidth = ((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36));
        FieldElement[] fieldElementArr = this.blocks[(i3 * this.blockColumns) + i4];
        fieldElementArr[iBlockWidth] = (FieldElement) fieldElementArr[iBlockWidth].multiply(t);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> transpose() {
        int rowDimension = getRowDimension();
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), getColumnDimension(), rowDimension);
        int i = 0;
        for (int i2 = 0; i2 < this.blockColumns; i2++) {
            for (int i3 = 0; i3 < this.blockRows; i3++) {
                T[] tArr = blockFieldMatrix.blocks[i];
                T[] tArr2 = this.blocks[(this.blockColumns * i3) + i2];
                int i4 = i2 * 36;
                int iMin = FastMath.min(i4 + 36, this.columns);
                int i5 = i3 * 36;
                int iMin2 = FastMath.min(i5 + 36, this.rows);
                int i6 = 0;
                for (int i7 = i4; i7 < iMin; i7++) {
                    int i8 = iMin - i4;
                    int i9 = i7 - i4;
                    for (int i10 = i5; i10 < iMin2; i10++) {
                        tArr[i6] = tArr2[i9];
                        i6++;
                        i9 += i8;
                    }
                }
                i++;
            }
        }
        return blockFieldMatrix;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] operate(T[] tArr) throws DimensionMismatchException {
        if (tArr.length != this.columns) {
            throw new DimensionMismatchException(tArr.length, this.columns);
        }
        T[] tArr2 = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), this.rows));
        T zero = getField().getZero();
        for (int i = 0; i < this.blockRows; i++) {
            int i2 = i * 36;
            int iMin = FastMath.min(i2 + 36, this.rows);
            int i3 = 0;
            while (true) {
                int i4 = this.blockColumns;
                if (i3 < i4) {
                    T[] tArr3 = this.blocks[(i4 * i) + i3];
                    int i5 = i3 * 36;
                    int iMin2 = FastMath.min(i5 + 36, this.columns);
                    int i6 = i2;
                    int i7 = 0;
                    while (i6 < iMin) {
                        FieldElement fieldElement = zero;
                        int i8 = i5;
                        while (i8 < iMin2 - 3) {
                            fieldElement = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement.add(tArr3[i7].multiply(tArr[i8]))).add(tArr3[i7 + 1].multiply(tArr[i8 + 1]))).add(tArr3[i7 + 2].multiply(tArr[i8 + 2]))).add(tArr3[i7 + 3].multiply(tArr[i8 + 3]));
                            i7 += 4;
                            i8 += 4;
                            zero = zero;
                        }
                        T t = zero;
                        while (i8 < iMin2) {
                            fieldElement = (FieldElement) fieldElement.add(tArr3[i7].multiply(tArr[i8]));
                            i8++;
                            i7++;
                        }
                        tArr2[i6] = (FieldElement) tArr2[i6].add((Decimal64) fieldElement);
                        i6++;
                        zero = t;
                    }
                    i3++;
                }
            }
        }
        return tArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int i;
        if (tArr.length != this.rows) {
            throw new DimensionMismatchException(tArr.length, this.rows);
        }
        T[] tArr2 = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), this.columns));
        T zero = getField().getZero();
        for (int i2 = 0; i2 < this.blockColumns; i2++) {
            int iBlockWidth = blockWidth(i2);
            int i3 = iBlockWidth + iBlockWidth;
            int i4 = i3 + iBlockWidth;
            int i5 = i4 + iBlockWidth;
            int i6 = i2 * 36;
            int iMin = FastMath.min(i6 + 36, this.columns);
            for (int i7 = 0; i7 < this.blockRows; i7++) {
                T[] tArr3 = this.blocks[(this.blockColumns * i7) + i2];
                int i8 = i7 * 36;
                int iMin2 = FastMath.min(i8 + 36, this.rows);
                int i9 = i6;
                while (i9 < iMin) {
                    int i10 = i9 - i6;
                    T t = zero;
                    int i11 = i6;
                    FieldElement fieldElement = t;
                    int i12 = i8;
                    while (true) {
                        i = iMin;
                        if (i12 >= iMin2 - 3) {
                            break;
                        }
                        fieldElement = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement.add(tArr3[i10].multiply(tArr[i12]))).add(tArr3[i10 + iBlockWidth].multiply(tArr[i12 + 1]))).add(tArr3[i10 + i3].multiply(tArr[i12 + 2]))).add(tArr3[i10 + i4].multiply(tArr[i12 + 3]));
                        i10 += i5;
                        i12 += 4;
                        iMin = i;
                        i8 = i8;
                    }
                    int i13 = i8;
                    while (i12 < iMin2) {
                        fieldElement = (FieldElement) fieldElement.add(tArr3[i10].multiply(tArr[i12]));
                        i10 += iBlockWidth;
                        i12++;
                    }
                    tArr2[i9] = (FieldElement) tArr2[i9].add((Decimal64) fieldElement);
                    i9++;
                    zero = t;
                    i6 = i11;
                    iMin = i;
                    i8 = i13;
                }
            }
        }
        return tArr2;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixChangingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        for (int i3 = 0; i3 < this.blockRows; i3++) {
            int i4 = i3 * 36;
            int iMin = FastMath.min(i4 + 36, this.rows);
            for (int i5 = i4; i5 < iMin; i5++) {
                for (int i6 = 0; i6 < this.blockColumns; i6++) {
                    int iBlockWidth = blockWidth(i6);
                    int i7 = i6 * 36;
                    int iMin2 = FastMath.min(i7 + 36, this.columns);
                    Object[] objArr = this.blocks[(this.blockColumns * i3) + i6];
                    int i8 = (i5 - i4) * iBlockWidth;
                    while (i7 < iMin2) {
                        objArr[i8] = fieldMatrixChangingVisitor.visit(i5, i7, objArr[i8]);
                        i8++;
                        i7++;
                    }
                }
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixPreservingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        for (int i3 = 0; i3 < this.blockRows; i3++) {
            int i4 = i3 * 36;
            int iMin = FastMath.min(i4 + 36, this.rows);
            for (int i5 = i4; i5 < iMin; i5++) {
                for (int i6 = 0; i6 < this.blockColumns; i6++) {
                    int iBlockWidth = blockWidth(i6);
                    int i7 = i6 * 36;
                    int iMin2 = FastMath.min(i7 + 36, this.columns);
                    T[] tArr = this.blocks[(this.blockColumns * i3) + i6];
                    int i8 = (i5 - i4) * iBlockWidth;
                    while (i7 < iMin2) {
                        fieldMatrixPreservingVisitor.visit(i5, i7, tArr[i8]);
                        i8++;
                        i7++;
                    }
                }
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        int i5;
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(this.rows, this.columns, i, i2, i3, i4);
        for (int i6 = i / 36; i6 < (i2 / 36) + 1; i6 = i5) {
            int i7 = i6 * 36;
            i5 = i6 + 1;
            int iMin = FastMath.min(i5 * 36, i2 + 1);
            for (int iMax = FastMath.max(i, i7); iMax < iMin; iMax++) {
                int i8 = i3 / 36;
                while (i8 < (i4 / 36) + 1) {
                    int iBlockWidth = blockWidth(i8);
                    int i9 = i8 * 36;
                    int iMax2 = FastMath.max(i3, i9);
                    int i10 = i8 + 1;
                    int i11 = i5;
                    int iMin2 = FastMath.min(i10 * 36, i4 + 1);
                    int i12 = iMin;
                    Object[] objArr = this.blocks[(this.blockColumns * i6) + i8];
                    int i13 = (((iMax - i7) * iBlockWidth) + iMax2) - i9;
                    while (iMax2 < iMin2) {
                        objArr[i13] = fieldMatrixChangingVisitor.visit(iMax, iMax2, objArr[i13]);
                        i13++;
                        iMax2++;
                    }
                    i8 = i10;
                    i5 = i11;
                    iMin = i12;
                }
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        int i5;
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(this.rows, this.columns, i, i2, i3, i4);
        for (int i6 = i / 36; i6 < (i2 / 36) + 1; i6 = i5) {
            int i7 = i6 * 36;
            i5 = i6 + 1;
            int iMin = FastMath.min(i5 * 36, i2 + 1);
            for (int iMax = FastMath.max(i, i7); iMax < iMin; iMax++) {
                int i8 = i3 / 36;
                while (i8 < (i4 / 36) + 1) {
                    int iBlockWidth = blockWidth(i8);
                    int i9 = i8 * 36;
                    int iMax2 = FastMath.max(i3, i9);
                    int i10 = i8 + 1;
                    int i11 = i5;
                    int iMin2 = FastMath.min(i10 * 36, i4 + 1);
                    int i12 = iMin;
                    T[] tArr = this.blocks[(this.blockColumns * i6) + i8];
                    int i13 = (((iMax - i7) * iBlockWidth) + iMax2) - i9;
                    while (iMax2 < iMin2) {
                        fieldMatrixPreservingVisitor.visit(iMax, iMax2, tArr[i13]);
                        i13++;
                        iMax2++;
                    }
                    i8 = i10;
                    i5 = i11;
                    iMin = i12;
                }
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixChangingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        int i3 = 0;
        for (int i4 = 0; i4 < this.blockRows; i4++) {
            int i5 = i4 * 36;
            int iMin = FastMath.min(i5 + 36, this.rows);
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int i7 = i6 * 36;
                int iMin2 = FastMath.min(i7 + 36, this.columns);
                Object[] objArr = this.blocks[i3];
                int i8 = 0;
                for (int i9 = i5; i9 < iMin; i9++) {
                    for (int i10 = i7; i10 < iMin2; i10++) {
                        objArr[i8] = fieldMatrixChangingVisitor.visit(i9, i10, objArr[i8]);
                        i8++;
                    }
                }
                i3++;
            }
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int i = this.rows;
        int i2 = this.columns;
        fieldMatrixPreservingVisitor.start(i, i2, 0, i - 1, 0, i2 - 1);
        int i3 = 0;
        for (int i4 = 0; i4 < this.blockRows; i4++) {
            int i5 = i4 * 36;
            int iMin = FastMath.min(i5 + 36, this.rows);
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int i7 = i6 * 36;
                int iMin2 = FastMath.min(i7 + 36, this.columns);
                T[] tArr = this.blocks[i3];
                int i8 = 0;
                for (int i9 = i5; i9 < iMin; i9++) {
                    for (int i10 = i7; i10 < iMin2; i10++) {
                        fieldMatrixPreservingVisitor.visit(i9, i10, tArr[i8]);
                        i8++;
                    }
                }
                i3++;
            }
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        blockFieldMatrix.checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, i, i2, i3, i4);
        int i5 = i / 36;
        while (i5 < (i2 / 36) + 1) {
            int i6 = i5 * 36;
            int iMax = FastMath.max(i, i6);
            int i7 = i5 + 1;
            int iMin = FastMath.min(i7 * 36, i2 + 1);
            int i8 = i3 / 36;
            while (i8 < (i4 / 36) + 1) {
                int iBlockWidth = blockFieldMatrix.blockWidth(i8);
                int i9 = i8 * 36;
                int iMax2 = FastMath.max(i3, i9);
                int i10 = i8 + 1;
                int i11 = iMax;
                int iMin2 = FastMath.min(i10 * 36, i4 + 1);
                int i12 = i7;
                Object[] objArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i5) + i8];
                int i13 = i11;
                while (i13 < iMin) {
                    int i14 = (((i13 - i6) * iBlockWidth) + iMax2) - i9;
                    int i15 = iMax2;
                    while (i15 < iMin2) {
                        objArr[i14] = fieldMatrixChangingVisitor.visit(i13, i15, objArr[i14]);
                        i14++;
                        i15++;
                        i5 = i5;
                        i6 = i6;
                    }
                    i13++;
                    i6 = i6;
                }
                blockFieldMatrix = this;
                i8 = i10;
                iMax = i11;
                i7 = i12;
                i6 = i6;
            }
            blockFieldMatrix = this;
            i5 = i7;
        }
        return (T) fieldMatrixChangingVisitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        blockFieldMatrix.checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, i, i2, i3, i4);
        int i5 = i / 36;
        while (i5 < (i2 / 36) + 1) {
            int i6 = i5 * 36;
            int iMax = FastMath.max(i, i6);
            int i7 = i5 + 1;
            int iMin = FastMath.min(i7 * 36, i2 + 1);
            int i8 = i3 / 36;
            while (i8 < (i4 / 36) + 1) {
                int iBlockWidth = blockFieldMatrix.blockWidth(i8);
                int i9 = i8 * 36;
                int iMax2 = FastMath.max(i3, i9);
                int i10 = i8 + 1;
                int i11 = iMax;
                int iMin2 = FastMath.min(i10 * 36, i4 + 1);
                int i12 = i7;
                T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i5) + i8];
                int i13 = i11;
                while (i13 < iMin) {
                    int i14 = (((i13 - i6) * iBlockWidth) + iMax2) - i9;
                    int i15 = iMax2;
                    while (i15 < iMin2) {
                        fieldMatrixPreservingVisitor.visit(i13, i15, tArr[i14]);
                        i14++;
                        i15++;
                        i5 = i5;
                        i6 = i6;
                    }
                    i13++;
                    i6 = i6;
                }
                blockFieldMatrix = this;
                i8 = i10;
                iMax = i11;
                i7 = i12;
                i6 = i6;
            }
            blockFieldMatrix = this;
            i5 = i7;
        }
        return (T) fieldMatrixPreservingVisitor.end();
    }
}
