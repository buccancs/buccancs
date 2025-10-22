package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public class MultiDimensionMismatchException extends MathIllegalArgumentException {
    private static final long serialVersionUID = -8415396756375798143L;
    private final Integer[] expected;
    private final Integer[] wrong;

    public MultiDimensionMismatchException(Integer[] numArr, Integer[] numArr2) {
        this(LocalizedFormats.DIMENSIONS_MISMATCH, numArr, numArr2);
    }

    public MultiDimensionMismatchException(Localizable localizable, Integer[] numArr, Integer[] numArr2) {
        super(localizable, numArr, numArr2);
        this.wrong = (Integer[]) numArr.clone();
        this.expected = (Integer[]) numArr2.clone();
    }

    public Integer[] getWrongDimensions() {
        return (Integer[]) this.wrong.clone();
    }

    public Integer[] getExpectedDimensions() {
        return (Integer[]) this.expected.clone();
    }

    public int getWrongDimension(int i) {
        return this.wrong[i].intValue();
    }

    public int getExpectedDimension(int i) {
        return this.expected[i].intValue();
    }
}
