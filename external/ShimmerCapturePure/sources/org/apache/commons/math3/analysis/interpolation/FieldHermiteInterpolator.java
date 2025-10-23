package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class FieldHermiteInterpolator<T extends FieldElement<T>> {
    private final List<T> abscissae = new ArrayList();
    private final List<T[]> topDiagonal = new ArrayList();
    private final List<T[]> bottomDiagonal = new ArrayList();

    public void addSamplePoint(T t, T[]... tArr) throws NullArgumentException, ZeroException, DimensionMismatchException, MathArithmeticException {
        MathUtils.checkNotNull(t);
        FieldElement fieldElement = (FieldElement) t.getField().getOne();
        for (int i = 0; i < tArr.length; i++) {
            FieldElement[] fieldElementArr = (FieldElement[]) tArr[i].clone();
            if (i > 1) {
                fieldElement = (FieldElement) fieldElement.multiply(i);
                FieldElement fieldElement2 = (FieldElement) fieldElement.reciprocal();
                for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                    fieldElementArr[i2] = (FieldElement) fieldElementArr[i2].multiply(fieldElement2);
                }
            }
            int size = this.abscissae.size();
            this.bottomDiagonal.add(size - i, fieldElementArr);
            int i3 = i;
            FieldElement[] fieldElementArr2 = fieldElementArr;
            while (i3 < size) {
                i3++;
                int i4 = size - i3;
                T[] tArr2 = this.bottomDiagonal.get(i4);
                if (t.equals(this.abscissae.get(i4))) {
                    throw new ZeroException(LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, t);
                }
                FieldElement fieldElement3 = (FieldElement) ((FieldElement) t.subtract(this.abscissae.get(i4))).reciprocal();
                for (int i5 = 0; i5 < fieldElementArr.length; i5++) {
                    tArr2[i5] = (FieldElement) fieldElement3.multiply((FieldElement) fieldElementArr2[i5].subtract(tArr2[i5]));
                }
                fieldElementArr2 = tArr2;
            }
            this.topDiagonal.add(fieldElementArr2.clone());
            this.abscissae.add(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] value(T t) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(t);
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(t.getField(), this.topDiagonal.get(0).length));
        FieldElement fieldElement = (FieldElement) t.getField().getOne();
        for (int i = 0; i < this.topDiagonal.size(); i++) {
            T[] tArr2 = this.topDiagonal.get(i);
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = (FieldElement) tArr[i2].add((Decimal64) tArr2[i2].multiply(fieldElement));
            }
            fieldElement = (FieldElement) fieldElement.multiply((FieldElement) t.subtract(this.abscissae.get(i)));
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[][] derivatives(T t, int i) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(t);
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        FieldElement fieldElement = (FieldElement) t.getField().getZero();
        FieldElement fieldElement2 = (FieldElement) t.getField().getOne();
        int i2 = i + 1;
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(t.getField(), i2);
        fieldElementArr[0] = fieldElement;
        int i3 = 0;
        while (i3 < i) {
            int i4 = i3 + 1;
            fieldElementArr[i4] = (FieldElement) fieldElementArr[i3].add(fieldElement2);
            i3 = i4;
        }
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(t.getField(), i2, this.topDiagonal.get(0).length));
        FieldElement[] fieldElementArr2 = (FieldElement[]) MathArrays.buildArray(t.getField(), i2);
        fieldElementArr2[0] = (FieldElement) t.getField().getOne();
        for (int i5 = 0; i5 < this.topDiagonal.size(); i5++) {
            T[] tArr2 = this.topDiagonal.get(i5);
            FieldElement fieldElement3 = (FieldElement) t.subtract(this.abscissae.get(i5));
            for (int i6 = i; i6 >= 0; i6--) {
                int i7 = 0;
                while (true) {
                    FieldElement[] fieldElementArr3 = tArr[i6];
                    if (i7 >= fieldElementArr3.length) {
                        break;
                    }
                    fieldElementArr3[i7] = (FieldElement) fieldElementArr3[i7].add((Decimal64) tArr2[i7].multiply(fieldElementArr2[i6]));
                    i7++;
                }
                FieldElement fieldElement4 = (FieldElement) fieldElementArr2[i6].multiply(fieldElement3);
                fieldElementArr2[i6] = fieldElement4;
                if (i6 > 0) {
                    fieldElementArr2[i6] = (FieldElement) fieldElement4.add(fieldElementArr[i6].multiply(fieldElementArr2[i6 - 1]));
                }
            }
        }
        return tArr;
    }
}
