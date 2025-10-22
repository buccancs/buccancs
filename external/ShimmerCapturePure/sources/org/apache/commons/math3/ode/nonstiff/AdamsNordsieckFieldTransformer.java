package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class AdamsNordsieckFieldTransformer<T extends RealFieldElement<T>> {
    private static final Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> CACHE = new HashMap();
    private final T[] c1;
    private final Field<T> field;
    private final Array2DRowFieldMatrix<T> update;

    private AdamsNordsieckFieldTransformer(Field<T> field, int i) {
        this.field = field;
        int i2 = i - 1;
        FieldMatrix<T> fieldMatrixBuildP = buildP(i2);
        FieldDecompositionSolver solver = new FieldLUDecomposition(fieldMatrixBuildP).getSolver();
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(field, i2);
        Arrays.fill(realFieldElementArr, field.getOne());
        this.c1 = (T[]) ((RealFieldElement[]) solver.solve(new ArrayFieldVector((FieldElement[]) realFieldElementArr, false)).toArray());
        RealFieldElement[][] realFieldElementArr2 = (RealFieldElement[][]) fieldMatrixBuildP.getData();
        for (int length = realFieldElementArr2.length - 1; length > 0; length--) {
            realFieldElementArr2[length] = realFieldElementArr2[length - 1];
        }
        RealFieldElement[] realFieldElementArr3 = (RealFieldElement[]) MathArrays.buildArray(field, i2);
        realFieldElementArr2[0] = realFieldElementArr3;
        Arrays.fill(realFieldElementArr3, field.getZero());
        this.update = new Array2DRowFieldMatrix<>(solver.solve(new Array2DRowFieldMatrix((FieldElement[][]) realFieldElementArr2, false)).getData());
    }

    public static <T extends RealFieldElement<T>> AdamsNordsieckFieldTransformer<T> getInstance(Field<T> field, int i) {
        AdamsNordsieckFieldTransformer<T> adamsNordsieckFieldTransformer;
        Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> map = CACHE;
        synchronized (map) {
            Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>> map2 = map.get(Integer.valueOf(i));
            if (map2 == null) {
                map2 = new HashMap<>();
                map.put(Integer.valueOf(i), map2);
            }
            adamsNordsieckFieldTransformer = (AdamsNordsieckFieldTransformer) map2.get(field);
            if (adamsNordsieckFieldTransformer == null) {
                adamsNordsieckFieldTransformer = new AdamsNordsieckFieldTransformer<>(field, i);
                map2.put(field, adamsNordsieckFieldTransformer);
            }
        }
        return adamsNordsieckFieldTransformer;
    }

    private FieldMatrix<T> buildP(int i) {
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(this.field, i, i);
        for (int i2 = 1; i2 <= realFieldElementArr.length; i2++) {
            RealFieldElement[] realFieldElementArr2 = realFieldElementArr[i2 - 1];
            int i3 = -i2;
            RealFieldElement realFieldElement = (RealFieldElement) this.field.getZero().add(i3);
            int i4 = 1;
            while (i4 <= realFieldElementArr2.length) {
                int i5 = i4 - 1;
                i4++;
                realFieldElementArr2[i5] = (RealFieldElement) realFieldElement.multiply(i4);
                realFieldElement = (RealFieldElement) realFieldElement.multiply(i3);
            }
        }
        return new Array2DRowFieldMatrix((FieldElement[][]) realFieldElementArr, false);
    }

    public Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T t, T[] tArr, T[][] tArr2, T[][] tArr3) throws OutOfRangeException {
        Field<T> field = this.field;
        T[] tArr4 = this.c1;
        int i = 1;
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(field, tArr4.length + 1, tArr4.length + 1);
        int i2 = 0;
        RealFieldElement[][] realFieldElementArr2 = (RealFieldElement[][]) MathArrays.buildArray(this.field, this.c1.length + 1, tArr2[0].length);
        T[] tArr5 = tArr2[0];
        T[] tArr6 = tArr3[0];
        int i3 = 1;
        while (i3 < tArr2.length) {
            RealFieldElement realFieldElement = (RealFieldElement) tArr[i3].subtract(tArr[i2]);
            RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.divide(t);
            RealFieldElement realFieldElement3 = (RealFieldElement) t.reciprocal();
            int i4 = i3 * 2;
            int i5 = i4 - 2;
            RealFieldElement[] realFieldElementArr3 = realFieldElementArr[i5];
            int i6 = i4 - i;
            RealFieldElement[] realFieldElementArr4 = i6 < realFieldElementArr.length ? realFieldElementArr[i6] : null;
            while (i2 < realFieldElementArr3.length) {
                realFieldElement3 = (RealFieldElement) realFieldElement3.multiply(realFieldElement2);
                realFieldElementArr3[i2] = (RealFieldElement) realFieldElement.multiply(realFieldElement3);
                if (realFieldElementArr4 != null) {
                    realFieldElementArr4[i2] = (RealFieldElement) realFieldElement3.multiply(i2 + 2);
                }
                i2++;
            }
            T[] tArr7 = tArr2[i3];
            T[] tArr8 = tArr3[i3];
            RealFieldElement[] realFieldElementArr5 = realFieldElementArr2[i5];
            RealFieldElement[] realFieldElementArr6 = i6 < realFieldElementArr2.length ? realFieldElementArr2[i6] : null;
            for (int i7 = 0; i7 < tArr7.length; i7++) {
                realFieldElementArr5[i7] = (RealFieldElement) ((RealFieldElement) tArr7[i7].subtract(tArr5[i7])).subtract((RealFieldElement) realFieldElement.multiply(tArr6[i7]));
                if (realFieldElementArr6 != null) {
                    realFieldElementArr6[i7] = (RealFieldElement) tArr8[i7].subtract(tArr6[i7]);
                }
            }
            i3++;
            i = 1;
            i2 = 0;
        }
        FieldMatrix fieldMatrixSolve = new FieldLUDecomposition(new Array2DRowFieldMatrix((FieldElement[][]) realFieldElementArr, false)).getSolver().solve(new Array2DRowFieldMatrix((FieldElement[][]) realFieldElementArr2, false));
        Array2DRowFieldMatrix<T> array2DRowFieldMatrix = new Array2DRowFieldMatrix<>(this.field, fieldMatrixSolve.getRowDimension() - 1, fieldMatrixSolve.getColumnDimension());
        for (int i8 = 0; i8 < array2DRowFieldMatrix.getRowDimension(); i8++) {
            for (int i9 = 0; i9 < array2DRowFieldMatrix.getColumnDimension(); i9++) {
                array2DRowFieldMatrix.setEntry(i8, i9, fieldMatrixSolve.getEntry(i8, i9));
            }
        }
        return array2DRowFieldMatrix;
    }

    public Array2DRowFieldMatrix<T> updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) {
        return (Array2DRowFieldMatrix<T>) this.update.multiply((Array2DRowFieldMatrix) array2DRowFieldMatrix);
    }

    public void updateHighOrderDerivativesPhase2(T[] tArr, T[] tArr2, Array2DRowFieldMatrix<T> array2DRowFieldMatrix) {
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) array2DRowFieldMatrix.getDataRef();
        for (int i = 0; i < realFieldElementArr.length; i++) {
            RealFieldElement[] realFieldElementArr2 = realFieldElementArr[i];
            T t = this.c1[i];
            for (int i2 = 0; i2 < realFieldElementArr2.length; i2++) {
                realFieldElementArr2[i2] = (RealFieldElement) realFieldElementArr2[i2].add((RealFieldElement) t.multiply(tArr[i2].subtract(tArr2[i2])));
            }
        }
    }
}
