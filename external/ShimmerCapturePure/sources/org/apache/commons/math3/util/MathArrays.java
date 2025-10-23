package org.apache.commons.math3.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

/* loaded from: classes5.dex */
public class MathArrays {

    private MathArrays() {
    }

    public static double[] scale(double d, double[] dArr) {
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = dArr[i] * d;
        }
        return dArr2;
    }

    public static void scaleInPlace(double d, double[] dArr) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] * d;
        }
    }

    public static double[] ebeAdd(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] + dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeSubtract(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] - dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeMultiply(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] * dArr2[i];
        }
        return dArr3;
    }

    public static double[] ebeDivide(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double[] dArr3 = (double[]) dArr.clone();
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr3[i] / dArr2[i];
        }
        return dArr3;
    }

    public static double distance1(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double dAbs = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            dAbs += FastMath.abs(dArr[i] - dArr2[i]);
        }
        return dAbs;
    }

    public static int distance1(int[] iArr, int[] iArr2) throws DimensionMismatchException {
        checkEqualLength(iArr, iArr2);
        int iAbs = 0;
        for (int i = 0; i < iArr.length; i++) {
            iAbs += FastMath.abs(iArr[i] - iArr2[i]);
        }
        return iAbs;
    }

    public static double distance(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i] - dArr2[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public static double cosAngle(double[] dArr, double[] dArr2) {
        return linearCombination(dArr, dArr2) / (safeNorm(dArr) * safeNorm(dArr2));
    }

    public static double distance(int[] iArr, int[] iArr2) throws DimensionMismatchException {
        checkEqualLength(iArr, iArr2);
        double d = 0.0d;
        for (int i = 0; i < iArr.length; i++) {
            double d2 = iArr[i] - iArr2[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public static double distanceInf(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        double dMax = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            dMax = FastMath.max(dMax, FastMath.abs(dArr[i] - dArr2[i]));
        }
        return dMax;
    }

    public static int distanceInf(int[] iArr, int[] iArr2) throws DimensionMismatchException {
        checkEqualLength(iArr, iArr2);
        int iMax = 0;
        for (int i = 0; i < iArr.length; i++) {
            iMax = FastMath.max(iMax, FastMath.abs(iArr[i] - iArr2[i]));
        }
        return iMax;
    }

    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] tArr, OrderDirection orderDirection, boolean z) {
        T t = tArr[0];
        int length = tArr.length;
        for (int i = 1; i < length; i++) {
            int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection[orderDirection.ordinal()];
            if (i2 == 1) {
                int iCompareTo = t.compareTo(tArr[i]);
                if (z) {
                    if (iCompareTo >= 0) {
                        return false;
                    }
                } else if (iCompareTo > 0) {
                    return false;
                }
            } else if (i2 == 2) {
                int iCompareTo2 = tArr[i].compareTo(t);
                if (z) {
                    if (iCompareTo2 >= 0) {
                        return false;
                    }
                } else if (iCompareTo2 > 0) {
                    return false;
                }
            } else {
                throw new MathInternalError();
            }
            t = tArr[i];
        }
        return true;
    }

    public static boolean isMonotonic(double[] dArr, OrderDirection orderDirection, boolean z) {
        return checkOrder(dArr, orderDirection, z, false);
    }

    public static boolean checkEqualLength(double[] dArr, double[] dArr2, boolean z) {
        if (dArr.length == dArr2.length) {
            return true;
        }
        if (z) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        return false;
    }

    public static void checkEqualLength(double[] dArr, double[] dArr2) {
        checkEqualLength(dArr, dArr2, true);
    }

    public static boolean checkEqualLength(int[] iArr, int[] iArr2, boolean z) {
        if (iArr.length == iArr2.length) {
            return true;
        }
        if (z) {
            throw new DimensionMismatchException(iArr.length, iArr2.length);
        }
        return false;
    }

    public static void checkEqualLength(int[] iArr, int[] iArr2) {
        checkEqualLength(iArr, iArr2, true);
    }

    public static boolean checkOrder(double[] dArr, OrderDirection orderDirection, boolean z, boolean z2) throws NonMonotonicSequenceException {
        double d = dArr[0];
        int length = dArr.length;
        int i = 1;
        while (i < length) {
            int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection[orderDirection.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new MathInternalError();
                }
                if (z) {
                    if (dArr[i] >= d) {
                        break;
                    }
                    d = dArr[i];
                    i++;
                } else {
                    if (dArr[i] > d) {
                        break;
                    }
                    d = dArr[i];
                    i++;
                }
            } else if (z) {
                if (dArr[i] <= d) {
                    break;
                }
                d = dArr[i];
                i++;
            } else {
                if (dArr[i] < d) {
                    break;
                }
                d = dArr[i];
                i++;
            }
        }
        if (i == length) {
            return true;
        }
        if (z2) {
            throw new NonMonotonicSequenceException(Double.valueOf(dArr[i]), Double.valueOf(d), i, orderDirection, z);
        }
        return false;
    }

    public static void checkOrder(double[] dArr, OrderDirection orderDirection, boolean z) throws NonMonotonicSequenceException {
        checkOrder(dArr, orderDirection, z, true);
    }

    public static void checkOrder(double[] dArr) throws NonMonotonicSequenceException {
        checkOrder(dArr, OrderDirection.INCREASING, true);
    }

    public static void checkRectangular(long[][] jArr) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(jArr);
        for (int i = 1; i < jArr.length; i++) {
            if (jArr[i].length != jArr[0].length) {
                throw new DimensionMismatchException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, jArr[i].length, jArr[0].length);
            }
        }
    }

    public static void checkPositive(double[] dArr) throws NotStrictlyPositiveException {
        for (int i = 0; i < dArr.length; i++) {
            if (dArr[i] <= 0.0d) {
                throw new NotStrictlyPositiveException(Double.valueOf(dArr[i]));
            }
        }
    }

    public static void checkNotNaN(double[] dArr) throws NotANumberException {
        for (double d : dArr) {
            if (Double.isNaN(d)) {
                throw new NotANumberException();
            }
        }
    }

    public static void checkNonNegative(long[] jArr) throws NotPositiveException {
        for (int i = 0; i < jArr.length; i++) {
            if (jArr[i] < 0) {
                throw new NotPositiveException(Long.valueOf(jArr[i]));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:

        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void checkNonNegative(long[][] r8) throws org.apache.commons.math3.exception.NotPositiveException {
        /*
            r0 = 0
            r1 = 0
        L2:
            int r2 = r8.length
            if (r1 >= r2) goto L27
            r2 = 0
        L6:
            r3 = r8[r1]
            int r4 = r3.length
            if (r2 >= r4) goto L24
            r4 = r3[r2]
            r6 = 0
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 < 0) goto L16
            int r2 = r2 + 1
            goto L6
        L16:
            org.apache.commons.math3.exception.NotPositiveException r0 = new org.apache.commons.math3.exception.NotPositiveException
            r8 = r8[r1]
            r1 = r8[r2]
            java.lang.Long r8 = java.lang.Long.valueOf(r1)
            r0.<init>(r8)
            throw r0
        L24:
            int r1 = r1 + 1
            goto L2
        L27:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.MathArrays.checkNonNegative(long[][]):void");
    }

    public static double safeNorm(double[] dArr) {
        double length = 1.304E19d / dArr.length;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        for (double d6 : dArr) {
            double dAbs = FastMath.abs(d6);
            if (dAbs >= 3.834E-20d && dAbs <= length) {
                d2 += dAbs * dAbs;
            } else if (dAbs > 3.834E-20d) {
                if (dAbs > d3) {
                    double d7 = d3 / dAbs;
                    d = (d * d7 * d7) + 1.0d;
                    d3 = dAbs;
                } else {
                    double d8 = dAbs / d3;
                    d += d8 * d8;
                }
            } else if (dAbs > d4) {
                double d9 = d4 / dAbs;
                d5 = (d5 * d9 * d9) + 1.0d;
                d4 = dAbs;
            } else {
                if (dAbs != 0.0d) {
                    double d10 = dAbs / d4;
                    d5 += d10 * d10;
                }
            }
        }
        if (d != 0.0d) {
            return d3 * Math.sqrt(d + ((d2 / d3) / d3));
        }
        if (d2 == 0.0d) {
            return d4 * Math.sqrt(d5);
        }
        if (d2 >= d4) {
            return Math.sqrt(d2 * (((d4 / d2) * d4 * d5) + 1.0d));
        }
        return Math.sqrt(d4 * ((d2 / d4) + (d5 * d4)));
    }

    public static void sortInPlace(double[] dArr, double[]... dArr2) throws NullArgumentException, DimensionMismatchException {
        sortInPlace(dArr, OrderDirection.INCREASING, dArr2);
    }

    public static void sortInPlace(double[] dArr, OrderDirection orderDirection, double[]... dArr2) throws NullArgumentException, DimensionMismatchException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int length = dArr.length;
        for (double[] dArr3 : dArr2) {
            if (dArr3 == null) {
                throw new NullArgumentException();
            }
            if (dArr3.length != length) {
                throw new DimensionMismatchException(dArr3.length, length);
            }
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(new PairDoubleInteger(dArr[i], i));
        }
        Collections.sort(arrayList, orderDirection == OrderDirection.INCREASING ? new Comparator<PairDoubleInteger>() { // from class: org.apache.commons.math3.util.MathArrays.1
            @Override // java.util.Comparator
            public int compare(PairDoubleInteger pairDoubleInteger, PairDoubleInteger pairDoubleInteger2) {
                return Double.compare(pairDoubleInteger.getKey(), pairDoubleInteger2.getKey());
            }
        } : new Comparator<PairDoubleInteger>() { // from class: org.apache.commons.math3.util.MathArrays.2
            @Override // java.util.Comparator
            public int compare(PairDoubleInteger pairDoubleInteger, PairDoubleInteger pairDoubleInteger2) {
                return Double.compare(pairDoubleInteger2.getKey(), pairDoubleInteger.getKey());
            }
        });
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            PairDoubleInteger pairDoubleInteger = (PairDoubleInteger) arrayList.get(i2);
            dArr[i2] = pairDoubleInteger.getKey();
            iArr[i2] = pairDoubleInteger.getValue();
        }
        for (double[] dArr4 : dArr2) {
            double[] dArr5 = (double[]) dArr4.clone();
            for (int i3 = 0; i3 < length; i3++) {
                dArr4[i3] = dArr5[iArr[i3]];
            }
        }
    }

    public static int[] copyOf(int[] iArr) {
        return copyOf(iArr, iArr.length);
    }

    public static double[] copyOf(double[] dArr) {
        return copyOf(dArr, dArr.length);
    }

    public static int[] copyOf(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, FastMath.min(i, iArr.length));
        return iArr2;
    }

    public static double[] copyOf(double[] dArr, int i) {
        double[] dArr2 = new double[i];
        System.arraycopy(dArr, 0, dArr2, 0, FastMath.min(i, dArr.length));
        return dArr2;
    }

    public static double[] copyOfRange(double[] dArr, int i, int i2) {
        int i3 = i2 - i;
        double[] dArr2 = new double[i3];
        System.arraycopy(dArr, i, dArr2, 0, FastMath.min(i3, dArr.length - i));
        return dArr2;
    }

    public static double linearCombination(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        checkEqualLength(dArr, dArr2);
        int length = dArr.length;
        int i = 1;
        if (length == 1) {
            return dArr[0] * dArr2[0];
        }
        double[] dArr3 = new double[length];
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            double d3 = dArr[i2];
            double dLongBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & (-134217728));
            double d4 = d3 - dLongBitsToDouble;
            double d5 = dArr2[i2];
            double dLongBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d5) & (-134217728));
            double d6 = d5 - dLongBitsToDouble2;
            double d7 = d3 * d5;
            dArr3[i2] = d7;
            d2 += (d4 * d6) - (((d7 - (dLongBitsToDouble * dLongBitsToDouble2)) - (d4 * dLongBitsToDouble2)) - (dLongBitsToDouble * d6));
        }
        double d8 = dArr3[0];
        double d9 = dArr3[1];
        double d10 = d8 + d9;
        double d11 = d10 - d9;
        double d12 = (d9 - (d10 - d11)) + (d8 - d11);
        int i3 = length - 1;
        while (i < i3) {
            i++;
            double d13 = dArr3[i];
            double d14 = d10 + d13;
            double d15 = d14 - d13;
            d12 += (d13 - (d14 - d15)) + (d10 - d15);
            d10 = d14;
        }
        double d16 = d10 + d2 + d12;
        if (!Double.isNaN(d16)) {
            return d16;
        }
        for (int i4 = 0; i4 < length; i4++) {
            d += dArr[i4] * dArr2[i4];
        }
        return d;
    }

    public static double linearCombination(double d, double d2, double d3, double d4) {
        double dLongBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d) & (-134217728));
        double d5 = d - dLongBitsToDouble;
        double dLongBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d2) & (-134217728));
        double d6 = d2 - dLongBitsToDouble2;
        double d7 = d * d2;
        double d8 = (d5 * d6) - (((d7 - (dLongBitsToDouble * dLongBitsToDouble2)) - (d5 * dLongBitsToDouble2)) - (dLongBitsToDouble * d6));
        double dLongBitsToDouble3 = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & (-134217728));
        double d9 = d3 - dLongBitsToDouble3;
        double dLongBitsToDouble4 = Double.longBitsToDouble((-134217728) & Double.doubleToRawLongBits(d4));
        double d10 = d4 - dLongBitsToDouble4;
        double d11 = d3 * d4;
        double d12 = (d9 * d10) - (((d11 - (dLongBitsToDouble3 * dLongBitsToDouble4)) - (d9 * dLongBitsToDouble4)) - (dLongBitsToDouble3 * d10));
        double d13 = d7 + d11;
        double d14 = d13 - d11;
        double d15 = d8 + d12 + (d11 - (d13 - d14)) + (d7 - d14) + d13;
        return Double.isNaN(d15) ? d13 : d15;
    }

    public static double linearCombination(double d, double d2, double d3, double d4, double d5, double d6) {
        double dLongBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d) & (-134217728));
        double d7 = d - dLongBitsToDouble;
        double dLongBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d2) & (-134217728));
        double d8 = d2 - dLongBitsToDouble2;
        double d9 = d * d2;
        double d10 = (d7 * d8) - (((d9 - (dLongBitsToDouble * dLongBitsToDouble2)) - (d7 * dLongBitsToDouble2)) - (dLongBitsToDouble * d8));
        double dLongBitsToDouble3 = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & (-134217728));
        double d11 = d3 - dLongBitsToDouble3;
        double dLongBitsToDouble4 = Double.longBitsToDouble(Double.doubleToRawLongBits(d4) & (-134217728));
        double d12 = d4 - dLongBitsToDouble4;
        double d13 = d3 * d4;
        double d14 = (d11 * d12) - (((d13 - (dLongBitsToDouble3 * dLongBitsToDouble4)) - (d11 * dLongBitsToDouble4)) - (dLongBitsToDouble3 * d12));
        double dLongBitsToDouble5 = Double.longBitsToDouble(Double.doubleToRawLongBits(d5) & (-134217728));
        double d15 = d5 - dLongBitsToDouble5;
        double dLongBitsToDouble6 = Double.longBitsToDouble((-134217728) & Double.doubleToRawLongBits(d6));
        double d16 = d6 - dLongBitsToDouble6;
        double d17 = d5 * d6;
        double d18 = (d15 * d16) - (((d17 - (dLongBitsToDouble5 * dLongBitsToDouble6)) - (d15 * dLongBitsToDouble6)) - (dLongBitsToDouble5 * d16));
        double d19 = d9 + d13;
        double d20 = d19 - d13;
        double d21 = (d13 - (d19 - d20)) + (d9 - d20);
        double d22 = d19 + d17;
        double d23 = d22 - d17;
        double d24 = d10 + d14 + d18 + d21 + (d17 - (d22 - d23)) + (d19 - d23) + d22;
        return Double.isNaN(d24) ? d22 : d24;
    }

    public static double linearCombination(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double dLongBitsToDouble = Double.longBitsToDouble(Double.doubleToRawLongBits(d) & (-134217728));
        double d9 = d - dLongBitsToDouble;
        double dLongBitsToDouble2 = Double.longBitsToDouble(Double.doubleToRawLongBits(d2) & (-134217728));
        double d10 = d2 - dLongBitsToDouble2;
        double d11 = d * d2;
        double d12 = (d9 * d10) - (((d11 - (dLongBitsToDouble * dLongBitsToDouble2)) - (d9 * dLongBitsToDouble2)) - (dLongBitsToDouble * d10));
        double dLongBitsToDouble3 = Double.longBitsToDouble(Double.doubleToRawLongBits(d3) & (-134217728));
        double d13 = d3 - dLongBitsToDouble3;
        double dLongBitsToDouble4 = Double.longBitsToDouble(Double.doubleToRawLongBits(d4) & (-134217728));
        double d14 = d4 - dLongBitsToDouble4;
        double d15 = d3 * d4;
        double d16 = (d13 * d14) - (((d15 - (dLongBitsToDouble3 * dLongBitsToDouble4)) - (d13 * dLongBitsToDouble4)) - (dLongBitsToDouble3 * d14));
        double dLongBitsToDouble5 = Double.longBitsToDouble(Double.doubleToRawLongBits(d5) & (-134217728));
        double d17 = d5 - dLongBitsToDouble5;
        double dLongBitsToDouble6 = Double.longBitsToDouble(Double.doubleToRawLongBits(d6) & (-134217728));
        double d18 = d6 - dLongBitsToDouble6;
        double d19 = d5 * d6;
        double d20 = (d17 * d18) - (((d19 - (dLongBitsToDouble5 * dLongBitsToDouble6)) - (d17 * dLongBitsToDouble6)) - (dLongBitsToDouble5 * d18));
        double dLongBitsToDouble7 = Double.longBitsToDouble(Double.doubleToRawLongBits(d7) & (-134217728));
        double d21 = d7 - dLongBitsToDouble7;
        double dLongBitsToDouble8 = Double.longBitsToDouble((-134217728) & Double.doubleToRawLongBits(d8));
        double d22 = d8 - dLongBitsToDouble8;
        double d23 = d7 * d8;
        double d24 = (d21 * d22) - (((d23 - (dLongBitsToDouble7 * dLongBitsToDouble8)) - (d21 * dLongBitsToDouble8)) - (dLongBitsToDouble7 * d22));
        double d25 = d11 + d15;
        double d26 = d25 - d15;
        double d27 = (d15 - (d25 - d26)) + (d11 - d26);
        double d28 = d25 + d19;
        double d29 = d28 - d19;
        double d30 = (d19 - (d28 - d29)) + (d25 - d29);
        double d31 = d28 + d23;
        double d32 = d31 - d23;
        double d33 = d12 + d16 + d20 + d24 + d27 + d30 + (d23 - (d31 - d32)) + (d28 - d32) + d31;
        return Double.isNaN(d33) ? d31 : d33;
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null) {
            return !((fArr == null) ^ (fArr2 == null));
        }
        if (fArr.length != fArr2.length) {
            return false;
        }
        for (int i = 0; i < fArr.length; i++) {
            if (!Precision.equals(fArr[i], fArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIncludingNaN(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null) {
            return !((fArr == null) ^ (fArr2 == null));
        }
        if (fArr.length != fArr2.length) {
            return false;
        }
        for (int i = 0; i < fArr.length; i++) {
            if (!Precision.equalsIncludingNaN(fArr[i], fArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(double[] dArr, double[] dArr2) {
        if (dArr == null || dArr2 == null) {
            return !((dArr == null) ^ (dArr2 == null));
        }
        if (dArr.length != dArr2.length) {
            return false;
        }
        for (int i = 0; i < dArr.length; i++) {
            if (!Precision.equals(dArr[i], dArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIncludingNaN(double[] dArr, double[] dArr2) {
        if (dArr == null || dArr2 == null) {
            return !((dArr == null) ^ (dArr2 == null));
        }
        if (dArr.length != dArr2.length) {
            return false;
        }
        for (int i = 0; i < dArr.length; i++) {
            if (!Precision.equalsIncludingNaN(dArr[i], dArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static double[] normalizeArray(double[] dArr, double d) throws MathIllegalArgumentException, MathArithmeticException {
        if (Double.isInfinite(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_INFINITE, new Object[0]);
        }
        if (Double.isNaN(d)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_NAN, new Object[0]);
        }
        int length = dArr.length;
        double[] dArr2 = new double[length];
        double d2 = 0.0d;
        for (int i = 0; i < length; i++) {
            if (Double.isInfinite(dArr[i])) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(dArr[i]), Integer.valueOf(i));
            }
            if (!Double.isNaN(dArr[i])) {
                d2 += dArr[i];
            }
        }
        if (d2 == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ARRAY_SUMS_TO_ZERO, new Object[0]);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (Double.isNaN(dArr[i2])) {
                dArr2[i2] = Double.NaN;
            } else {
                dArr2[i2] = (dArr[i2] * d) / d2;
            }
        }
        return dArr2;
    }

    public static <T> T[] buildArray(Field<T> field, int i) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance(field.getRuntimeClass(), i));
        Arrays.fill(tArr, field.getZero());
        return tArr;
    }

    public static <T> T[][] buildArray(Field<T> field, int i, int i2) {
        if (i2 < 0) {
            return (T[][]) ((Object[][]) Array.newInstance(buildArray(field, 0).getClass(), i));
        }
        T[][] tArr = (T[][]) ((Object[][]) Array.newInstance(field.getRuntimeClass(), i, i2));
        for (int i3 = 0; i3 < i; i3++) {
            Arrays.fill(tArr[i3], field.getZero());
        }
        return tArr;
    }

    public static double[] convolve(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        int length = dArr.length;
        int length2 = dArr2.length;
        if (length == 0 || length2 == 0) {
            throw new NoDataException();
        }
        int i = (length + length2) - 1;
        double[] dArr3 = new double[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int iMax = FastMath.max(0, i3 - length);
            double d = 0.0d;
            for (int i4 = i2 - iMax; iMax < length2 && i4 >= 0; i4--) {
                d += dArr[i4] * dArr2[iMax];
                iMax++;
            }
            dArr3[i2] = d;
            i2 = i3;
        }
        return dArr3;
    }

    public static void shuffle(int[] iArr, int i, Position position) {
        shuffle(iArr, i, position, new Well19937c());
    }

    public static void shuffle(int[] iArr, int i, Position position, RandomGenerator randomGenerator) {
        int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$util$MathArrays$Position[position.ordinal()];
        if (i2 == 1) {
            int length = iArr.length - 1;
            while (length >= i) {
                int iSample = length == i ? i : new UniformIntegerDistribution(randomGenerator, i, length).sample();
                int i3 = iArr[iSample];
                iArr[iSample] = iArr[length];
                iArr[length] = i3;
                length--;
            }
            return;
        }
        if (i2 != 2) {
            throw new MathInternalError();
        }
        int i4 = 0;
        while (i4 <= i) {
            int iSample2 = i4 == i ? i : new UniformIntegerDistribution(randomGenerator, i4, i).sample();
            int i5 = iArr[iSample2];
            iArr[iSample2] = iArr[i4];
            iArr[i4] = i5;
            i4++;
        }
    }

    public static void shuffle(int[] iArr, RandomGenerator randomGenerator) {
        shuffle(iArr, 0, Position.TAIL, randomGenerator);
    }

    public static void shuffle(int[] iArr) {
        shuffle(iArr, new Well19937c());
    }

    public static int[] natural(int i) {
        return sequence(i, 0, 1);
    }

    public static int[] sequence(int i, int i2, int i3) {
        int[] iArr = new int[i];
        for (int i4 = 0; i4 < i; i4++) {
            iArr[i4] = (i4 * i3) + i2;
        }
        return iArr;
    }

    public static boolean verifyValues(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return verifyValues(dArr, i, i2, false);
    }

    public static boolean verifyValues(double[] dArr, int i, int i2, boolean z) throws MathIllegalArgumentException {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i2));
        }
        int i3 = i + i2;
        if (i3 <= dArr.length) {
            return i2 != 0 || z;
        }
        throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
    }

    public static boolean verifyValues(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        return verifyValues(dArr, dArr2, i, i2, false);
    }

    public static boolean verifyValues(double[] dArr, double[] dArr2, int i, int i2, boolean z) throws MathIllegalArgumentException {
        if (dArr2 == null || dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        checkEqualLength(dArr2, dArr);
        boolean z2 = false;
        for (int i3 = i; i3 < i + i2; i3++) {
            double d = dArr2[i3];
            if (Double.isNaN(d)) {
                throw new MathIllegalArgumentException(LocalizedFormats.NAN_ELEMENT_AT_INDEX, Integer.valueOf(i3));
            }
            if (Double.isInfinite(d)) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(d), Integer.valueOf(i3));
            }
            if (d < 0.0d) {
                throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, Integer.valueOf(i3), Double.valueOf(d));
            }
            if (!z2 && d > 0.0d) {
                z2 = true;
            }
        }
        if (!z2) {
            throw new MathIllegalArgumentException(LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO, new Object[0]);
        }
        return verifyValues(dArr, i, i2, z);
    }

    public static double[] concatenate(double[]... dArr) {
        int length = 0;
        for (double[] dArr2 : dArr) {
            length += dArr2.length;
        }
        double[] dArr3 = new double[length];
        int i = 0;
        for (double[] dArr4 : dArr) {
            int length2 = dArr4.length;
            System.arraycopy(dArr4, 0, dArr3, i, length2);
            i += length2;
        }
        return dArr3;
    }

    public static double[] unique(double[] dArr) {
        TreeSet treeSet = new TreeSet();
        int i = 0;
        for (double d : dArr) {
            treeSet.add(Double.valueOf(d));
        }
        int size = treeSet.size();
        double[] dArr2 = new double[size];
        Iterator it2 = treeSet.iterator();
        while (it2.hasNext()) {
            i++;
            dArr2[size - i] = ((Double) it2.next()).doubleValue();
        }
        return dArr2;
    }

    public enum OrderDirection {
        INCREASING,
        DECREASING
    }

    public enum Position {
        HEAD,
        TAIL
    }

    public interface Function {
        double evaluate(double[] dArr);

        double evaluate(double[] dArr, int i, int i2);
    }

    private static class PairDoubleInteger {
        private final double key;
        private final int value;

        PairDoubleInteger(double d, int i) {
            this.key = d;
            this.value = i;
        }

        public double getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* renamed from: org.apache.commons.math3.util.MathArrays$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$MathArrays$Position;

        static {
            int[] iArr = new int[Position.values().length];
            $SwitchMap$org$apache$commons$math3$util$MathArrays$Position = iArr;
            try {
                iArr[Position.TAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$util$MathArrays$Position[Position.HEAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[OrderDirection.values().length];
            $SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection = iArr2;
            try {
                iArr2[OrderDirection.INCREASING.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection[OrderDirection.DECREASING.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
