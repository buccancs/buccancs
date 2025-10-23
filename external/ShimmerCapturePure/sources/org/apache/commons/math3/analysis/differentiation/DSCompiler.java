package org.apache.commons.math3.analysis.differentiation;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class DSCompiler {
    private static AtomicReference<DSCompiler[][]> compilers = new AtomicReference<>(null);
    private final int[][][] compIndirection;
    private final int[][] derivativesIndirection;
    private final int[] lowerIndirection;
    private final int[][][] multIndirection;
    private final int order;
    private final int parameters;
    private final int[][] sizes;

    private DSCompiler(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) throws NumberIsTooLargeException {
        this.parameters = i;
        this.order = i2;
        int[][] iArrCompileSizes = compileSizes(i, i2, dSCompiler);
        this.sizes = iArrCompileSizes;
        int[][] iArrCompileDerivativesIndirection = compileDerivativesIndirection(i, i2, dSCompiler, dSCompiler2);
        this.derivativesIndirection = iArrCompileDerivativesIndirection;
        int[] iArrCompileLowerIndirection = compileLowerIndirection(i, i2, dSCompiler, dSCompiler2);
        this.lowerIndirection = iArrCompileLowerIndirection;
        this.multIndirection = compileMultiplicationIndirection(i, i2, dSCompiler, dSCompiler2, iArrCompileLowerIndirection);
        this.compIndirection = compileCompositionIndirection(i, i2, dSCompiler, dSCompiler2, iArrCompileSizes, iArrCompileDerivativesIndirection);
    }

    public static DSCompiler getCompiler(int i, int i2) throws NumberIsTooLargeException {
        DSCompiler dSCompiler;
        DSCompiler[][] dSCompilerArr = compilers.get();
        if (dSCompilerArr != null && dSCompilerArr.length > i) {
            DSCompiler[] dSCompilerArr2 = dSCompilerArr[i];
            if (dSCompilerArr2.length > i2 && (dSCompiler = dSCompilerArr2[i2]) != null) {
                return dSCompiler;
            }
        }
        DSCompiler[][] dSCompilerArr3 = (DSCompiler[][]) Array.newInstance((Class<?>) DSCompiler.class, FastMath.max(i, dSCompilerArr == null ? 0 : dSCompilerArr.length) + 1, FastMath.max(i2, dSCompilerArr == null ? 0 : dSCompilerArr[0].length) + 1);
        if (dSCompilerArr != null) {
            for (int i3 = 0; i3 < dSCompilerArr.length; i3++) {
                DSCompiler[] dSCompilerArr4 = dSCompilerArr[i3];
                System.arraycopy(dSCompilerArr4, 0, dSCompilerArr3[i3], 0, dSCompilerArr4.length);
            }
        }
        for (int i4 = 0; i4 <= i + i2; i4++) {
            int iMax = FastMath.max(0, i4 - i);
            while (iMax <= FastMath.min(i2, i4)) {
                int i5 = i4 - iMax;
                DSCompiler[] dSCompilerArr5 = dSCompilerArr3[i5];
                if (dSCompilerArr5[iMax] == null) {
                    dSCompilerArr5[iMax] = new DSCompiler(i5, iMax, i5 == 0 ? null : dSCompilerArr3[i5 - 1][iMax], iMax != 0 ? dSCompilerArr5[iMax - 1] : null);
                }
                iMax++;
            }
        }
        LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(compilers, dSCompilerArr, dSCompilerArr3);
        return dSCompilerArr3[i][i2];
    }

    private static int[][] compileSizes(int i, int i2, DSCompiler dSCompiler) {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i + 1, i2 + 1);
        int i3 = 0;
        if (i == 0) {
            Arrays.fill(iArr[0], 1);
        } else {
            System.arraycopy(dSCompiler.sizes, 0, iArr, 0, i);
            iArr[i][0] = 1;
            while (i3 < i2) {
                int[] iArr2 = iArr[i];
                int i4 = i3 + 1;
                iArr2[i4] = iArr2[i3] + iArr[i - 1][i4];
                i3 = i4;
            }
        }
        return iArr;
    }

    private static int[][] compileDerivativesIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        if (i == 0 || i2 == 0) {
            return (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 1, i);
        }
        int length = dSCompiler.derivativesIndirection.length;
        int length2 = dSCompiler2.derivativesIndirection.length;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, length + length2, i);
        for (int i3 = 0; i3 < length; i3++) {
            System.arraycopy(dSCompiler.derivativesIndirection[i3], 0, iArr[i3], 0, i - 1);
        }
        for (int i4 = 0; i4 < length2; i4++) {
            int i5 = length + i4;
            System.arraycopy(dSCompiler2.derivativesIndirection[i4], 0, iArr[i5], 0, i);
            int[] iArr2 = iArr[i5];
            int i6 = i - 1;
            iArr2[i6] = iArr2[i6] + 1;
        }
        return iArr;
    }

    private static int[] compileLowerIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        if (i == 0 || i2 <= 1) {
            return new int[]{0};
        }
        int[] iArr = dSCompiler.lowerIndirection;
        int length = iArr.length;
        int length2 = dSCompiler2.lowerIndirection.length;
        int[] iArr2 = new int[length + length2];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        for (int i3 = 0; i3 < length2; i3++) {
            iArr2[length + i3] = dSCompiler.getSize() + dSCompiler2.lowerIndirection[i3];
        }
        return iArr2;
    }

    private static int[][][] compileMultiplicationIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2, int[] iArr) {
        if (i == 0 || i2 == 0) {
            return new int[][][]{new int[][]{new int[]{1, 0, 0}}};
        }
        int[][][] iArr2 = dSCompiler.multIndirection;
        int length = iArr2.length;
        int length2 = dSCompiler2.multIndirection.length;
        int[][][] iArr3 = new int[length + length2][][];
        System.arraycopy(iArr2, 0, iArr3, 0, length);
        for (int i3 = 0; i3 < length2; i3++) {
            int[][] iArr4 = dSCompiler2.multIndirection[i3];
            ArrayList arrayList = new ArrayList(iArr4.length * 2);
            for (int i4 = 0; i4 < iArr4.length; i4++) {
                int[] iArr5 = iArr4[i4];
                arrayList.add(new int[]{iArr5[0], iArr[iArr5[1]], iArr5[2] + length});
                int[] iArr6 = iArr4[i4];
                arrayList.add(new int[]{iArr6[0], iArr6[1] + length, iArr[iArr6[2]]});
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                int[] iArr7 = (int[]) arrayList.get(i5);
                if (iArr7[0] > 0) {
                    for (int i6 = i5 + 1; i6 < arrayList.size(); i6++) {
                        int[] iArr8 = (int[]) arrayList.get(i6);
                        if (iArr7[1] == iArr8[1] && iArr7[2] == iArr8[2]) {
                            iArr7[0] = iArr7[0] + iArr8[0];
                            iArr8[0] = 0;
                        }
                    }
                    arrayList2.add(iArr7);
                }
            }
            iArr3[length + i3] = (int[][]) arrayList2.toArray(new int[arrayList2.size()][]);
        }
        return iArr3;
    }

    private static int[][][] compileCompositionIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2, int[][] iArr, int[][] iArr2) throws NumberIsTooLargeException {
        DSCompiler dSCompiler3 = dSCompiler2;
        char c = 0;
        int i3 = 1;
        if (i == 0 || i2 == 0) {
            return new int[][][]{new int[][]{new int[]{1, 0}}};
        }
        int[][][] iArr3 = dSCompiler.compIndirection;
        int length = iArr3.length;
        int length2 = dSCompiler3.compIndirection.length;
        int[][][] iArr4 = new int[length + length2][][];
        System.arraycopy(iArr3, 0, iArr4, 0, length);
        int i4 = 0;
        while (i4 < length2) {
            ArrayList arrayList = new ArrayList();
            int[][] iArr5 = dSCompiler3.compIndirection[i4];
            int length3 = iArr5.length;
            int i5 = 0;
            while (i5 < length3) {
                int[] iArr6 = iArr5[i5];
                int length4 = iArr6.length + i3;
                int[] iArr7 = new int[length4];
                iArr7[c] = iArr6[c];
                iArr7[1] = iArr6[1] + 1;
                int[] iArr8 = new int[i];
                int i6 = i - 1;
                iArr8[i6] = 1;
                iArr7[iArr6.length] = getPartialDerivativeIndex(i, i2, iArr, iArr8);
                int i7 = length2;
                int i8 = 2;
                while (i8 < iArr6.length) {
                    iArr7[i8] = convertIndex(iArr6[i8], i, dSCompiler3.derivativesIndirection, i, i2, iArr);
                    i8++;
                    length = length;
                    i5 = i5;
                    iArr4 = iArr4;
                    iArr6 = iArr6;
                    length3 = length3;
                    iArr5 = iArr5;
                    arrayList = arrayList;
                }
                int i9 = i5;
                int i10 = length3;
                int[][] iArr9 = iArr5;
                ArrayList arrayList2 = arrayList;
                int i11 = length;
                int[][][] iArr10 = iArr4;
                Arrays.sort(iArr7, 2, length4);
                arrayList2.add(iArr7);
                int[] iArr11 = iArr6;
                int i12 = 2;
                while (i12 < iArr11.length) {
                    int length5 = iArr11.length;
                    int[] iArr12 = new int[length5];
                    iArr12[0] = iArr11[0];
                    iArr12[1] = iArr11[1];
                    int i13 = 2;
                    while (i13 < iArr11.length) {
                        int i14 = iArr11[i13];
                        int[][] iArr13 = dSCompiler3.derivativesIndirection;
                        int i15 = i13;
                        int[] iArr14 = iArr12;
                        int[] iArr15 = iArr11;
                        int i16 = length5;
                        int iConvertIndex = convertIndex(i14, i, iArr13, i, i2, iArr);
                        iArr14[i15] = iConvertIndex;
                        if (i15 == i12) {
                            System.arraycopy(iArr2[iConvertIndex], 0, iArr8, 0, i);
                            iArr8[i6] = iArr8[i6] + 1;
                            iArr14[i15] = getPartialDerivativeIndex(i, i2, iArr, iArr8);
                        }
                        i13 = i15 + 1;
                        iArr12 = iArr14;
                        length5 = i16;
                        iArr11 = iArr15;
                        dSCompiler3 = dSCompiler2;
                    }
                    int[] iArr16 = iArr12;
                    Arrays.sort(iArr16, 2, length5);
                    arrayList2.add(iArr16);
                    i12++;
                    dSCompiler3 = dSCompiler2;
                    iArr11 = iArr11;
                }
                i5 = i9 + 1;
                dSCompiler3 = dSCompiler2;
                arrayList = arrayList2;
                length2 = i7;
                length = i11;
                iArr4 = iArr10;
                length3 = i10;
                iArr5 = iArr9;
                c = 0;
                i3 = 1;
            }
            int i17 = length;
            int i18 = length2;
            int[][][] iArr17 = iArr4;
            ArrayList arrayList3 = arrayList;
            ArrayList arrayList4 = new ArrayList(arrayList3.size());
            for (int i19 = 0; i19 < arrayList3.size(); i19++) {
                int[] iArr18 = (int[]) arrayList3.get(i19);
                if (iArr18[0] > 0) {
                    for (int i20 = i19 + 1; i20 < arrayList3.size(); i20++) {
                        int[] iArr19 = (int[]) arrayList3.get(i20);
                        boolean z = iArr18.length == iArr19.length;
                        for (int i21 = 1; z && i21 < iArr18.length; i21++) {
                            z &= iArr18[i21] == iArr19[i21];
                        }
                        if (z) {
                            iArr18[0] = iArr18[0] + iArr19[0];
                            iArr19[0] = 0;
                        }
                    }
                    arrayList4.add(iArr18);
                }
            }
            iArr17[i17 + i4] = (int[][]) arrayList4.toArray(new int[arrayList4.size()][]);
            i4++;
            dSCompiler3 = dSCompiler2;
            length2 = i18;
            length = i17;
            iArr4 = iArr17;
            c = 0;
            i3 = 1;
        }
        return iArr4;
    }

    private static int getPartialDerivativeIndex(int i, int i2, int[][] iArr, int... iArr2) throws NumberIsTooLargeException {
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        for (int i6 = i - 1; i6 >= 0; i6--) {
            int i7 = iArr2[i6];
            i5 += i7;
            if (i5 > i2) {
                throw new NumberIsTooLargeException(Integer.valueOf(i5), Integer.valueOf(i2), true);
            }
            while (true) {
                int i8 = i7 - 1;
                if (i7 > 0) {
                    i3 += iArr[i6][i4];
                    i7 = i8;
                    i4--;
                }
            }
        }
        return i3;
    }

    private static int convertIndex(int i, int i2, int[][] iArr, int i3, int i4, int[][] iArr2) throws NumberIsTooLargeException {
        int[] iArr3 = new int[i3];
        System.arraycopy(iArr[i], 0, iArr3, 0, FastMath.min(i2, i3));
        return getPartialDerivativeIndex(i3, i4, iArr2, iArr3);
    }

    public int getFreeParameters() {
        return this.parameters;
    }

    public int getOrder() {
        return this.order;
    }

    public int getPartialDerivativeIndex(int... iArr) throws DimensionMismatchException, NumberIsTooLargeException {
        if (iArr.length != getFreeParameters()) {
            throw new DimensionMismatchException(iArr.length, getFreeParameters());
        }
        return getPartialDerivativeIndex(this.parameters, this.order, this.sizes, iArr);
    }

    public int[] getPartialDerivativeOrders(int i) {
        return this.derivativesIndirection[i];
    }

    public int getSize() {
        return this.sizes[this.parameters][this.order];
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = MathArrays.linearCombination(d, dArr[i + i4], d2, dArr2[i2 + i4]);
        }
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double d3, double[] dArr3, int i3, double[] dArr4, int i4) {
        for (int i5 = 0; i5 < getSize(); i5++) {
            dArr4[i4 + i5] = MathArrays.linearCombination(d, dArr[i + i5], d2, dArr2[i2 + i5], d3, dArr3[i3 + i5]);
        }
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double d3, double[] dArr3, int i3, double d4, double[] dArr4, int i4, double[] dArr5, int i5) {
        for (int i6 = 0; i6 < getSize(); i6++) {
            dArr5[i5 + i6] = MathArrays.linearCombination(d, dArr[i + i6], d2, dArr2[i2 + i6], d3, dArr3[i3 + i6], d4, dArr4[i4 + i6]);
        }
    }

    public void add(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] + dArr2[i2 + i4];
        }
    }

    public void subtract(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] - dArr2[i2 + i4];
        }
    }

    public void multiply(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        int i4 = 0;
        while (true) {
            int[][][] iArr = this.multIndirection;
            if (i4 >= iArr.length) {
                return;
            }
            double d = 0.0d;
            for (int[] iArr2 : iArr[i4]) {
                d += iArr2[0] * dArr[iArr2[1] + i] * dArr2[i2 + iArr2[2]];
            }
            dArr3[i3 + i4] = d;
            i4++;
        }
    }

    public void divide(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        pow(dArr2, i, -1, dArr4, 0);
        multiply(dArr, i, dArr4, 0, dArr3, i3);
    }

    public void remainder(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double dIEEEremainder = FastMath.IEEEremainder(dArr[i], dArr2[i2]);
        double dRint = FastMath.rint((dArr[i] - dIEEEremainder) / dArr2[i2]);
        dArr3[i3] = dIEEEremainder;
        for (int i4 = 1; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] - (dArr2[i2 + i4] * dRint);
        }
    }

    public void pow(double d, double[] dArr, int i, double[] dArr2, int i2) {
        int i3 = 1;
        int i4 = this.order + 1;
        double[] dArr3 = new double[i4];
        if (d == 0.0d) {
            double d2 = dArr[i];
            if (d2 == 0.0d) {
                dArr3[0] = 1.0d;
                double d3 = Double.POSITIVE_INFINITY;
                while (i3 < i4) {
                    d3 = -d3;
                    dArr3[i3] = d3;
                    i3++;
                }
            } else if (d2 < 0.0d) {
                Arrays.fill(dArr3, Double.NaN);
            }
        } else {
            dArr3[0] = FastMath.pow(d, dArr[i]);
            double dLog = FastMath.log(d);
            while (i3 < i4) {
                dArr3[i3] = dArr3[i3 - 1] * dLog;
                i3++;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void pow(double[] dArr, int i, double d, double[] dArr2, int i2) {
        int i3 = this.order;
        double[] dArr3 = new double[i3 + 1];
        double dPow = FastMath.pow(dArr[i], d - i3);
        for (int i4 = this.order; i4 > 0; i4--) {
            dArr3[i4] = dPow;
            dPow *= dArr[i];
        }
        dArr3[0] = dPow;
        double d2 = d;
        for (int i5 = 1; i5 <= this.order; i5++) {
            dArr3[i5] = dArr3[i5] * d2;
            d2 *= d - i5;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void pow(double[] dArr, int i, int i2, double[] dArr2, int i3) {
        if (i2 == 0) {
            dArr2[i3] = 1.0d;
            Arrays.fill(dArr2, i3 + 1, i3 + getSize(), 0.0d);
            return;
        }
        int i4 = this.order;
        double[] dArr3 = new double[i4 + 1];
        if (i2 > 0) {
            int iMin = FastMath.min(i4, i2);
            double dPow = FastMath.pow(dArr[i], i2 - iMin);
            while (iMin > 0) {
                dArr3[iMin] = dPow;
                dPow *= dArr[i];
                iMin--;
            }
            dArr3[0] = dPow;
        } else {
            double d = 1.0d / dArr[i];
            double dPow2 = FastMath.pow(d, -i2);
            for (int i5 = 0; i5 <= this.order; i5++) {
                dArr3[i5] = dPow2;
                dPow2 *= d;
            }
        }
        double d2 = i2;
        for (int i6 = 1; i6 <= this.order; i6++) {
            dArr3[i6] = dArr3[i6] * d2;
            d2 *= i2 - i6;
        }
        compose(dArr, i, dArr3, dArr2, i3);
    }

    public void pow(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        log(dArr, i, dArr4, 0);
        double[] dArr5 = new double[getSize()];
        multiply(dArr4, 0, dArr2, i2, dArr5, 0);
        exp(dArr5, 0, dArr3, i3);
    }

    public void rootN(double[] dArr, int i, int i2, double[] dArr2, int i3) {
        double dPow;
        double[] dArr3 = new double[this.order + 1];
        if (i2 == 2) {
            double dSqrt = FastMath.sqrt(dArr[i]);
            dArr3[0] = dSqrt;
            dPow = 0.5d / dSqrt;
        } else if (i2 == 3) {
            double dCbrt = FastMath.cbrt(dArr[i]);
            dArr3[0] = dCbrt;
            dPow = 1.0d / ((3.0d * dCbrt) * dCbrt);
        } else {
            double d = i2;
            double dPow2 = FastMath.pow(dArr[i], 1.0d / d);
            dArr3[0] = dPow2;
            dPow = 1.0d / (d * FastMath.pow(dPow2, i2 - 1));
        }
        double d2 = 1.0d / i2;
        double d3 = 1.0d / dArr[i];
        for (int i4 = 1; i4 <= this.order; i4++) {
            dArr3[i4] = dPow;
            dPow *= (d2 - i4) * d3;
        }
        compose(dArr, i, dArr3, dArr2, i3);
    }

    public void exp(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        Arrays.fill(dArr3, FastMath.exp(dArr[i]));
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void expm1(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.expm1(dArr[i]);
        Arrays.fill(dArr3, 1, this.order + 1, FastMath.exp(dArr[i]));
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.log(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / dArr[i];
            double d2 = d;
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = d2;
                d2 *= (-i3) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log1p(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.log1p(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / (dArr[i] + 1.0d);
            double d2 = d;
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = d2;
                d2 *= (-i3) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log10(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.log10(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / dArr[i];
            double dLog = d / FastMath.log(10.0d);
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = dLog;
                dLog *= (-i3) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void cos(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.cos(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = -FastMath.sin(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = -dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void sin(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.sin(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.cos(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = -dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void tan(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        int i3;
        double d2;
        double[] dArr3 = new double[this.order + 1];
        double dTan = FastMath.tan(dArr[i]);
        dArr3[0] = dTan;
        int i4 = this.order;
        if (i4 > 0) {
            int i5 = 2;
            double[] dArr4 = new double[i4 + 2];
            dArr4[1] = 1.0d;
            double d3 = dTan * dTan;
            int i6 = 1;
            while (i6 <= this.order) {
                int i7 = i6 + 1;
                dArr4[i7] = i6 * dArr4[i6];
                double d4 = 0.0d;
                int i8 = i7;
                while (i8 >= 0) {
                    double d5 = (d4 * d3) + dArr4[i8];
                    if (i8 > i5) {
                        int i9 = i8 - 1;
                        d = d3;
                        double d6 = i9 * dArr4[i9];
                        int i10 = i8 - 3;
                        i3 = i7;
                        d2 = d5;
                        dArr4[i8 - 2] = d6 + (i10 * dArr4[i10]);
                    } else {
                        d = d3;
                        i3 = i7;
                        d2 = d5;
                        if (i8 == 2) {
                            dArr4[0] = dArr4[1];
                        }
                        i8 -= 2;
                        i7 = i3;
                        d3 = d;
                        d4 = d2;
                        i5 = 2;
                    }
                    i8 -= 2;
                    i7 = i3;
                    d3 = d;
                    d4 = d2;
                    i5 = 2;
                }
                double d7 = d3;
                int i11 = i7;
                if ((i6 & 1) == 0) {
                    d4 *= dTan;
                }
                dArr3[i6] = d4;
                i6 = i11;
                d3 = d7;
                i5 = 2;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void acos(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[dSCompiler.order + 1];
        double d3 = dArr[i];
        dArr3[0] = FastMath.acos(d3);
        int i3 = dSCompiler.order;
        if (i3 > 0) {
            double[] dArr4 = new double[i3];
            dArr4[0] = -1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (1.0d - d4);
            double dSqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * dSqrt;
            int i4 = 2;
            int i5 = 2;
            while (i5 <= dSCompiler.order) {
                int i6 = i5 - 1;
                dArr4[i6] = i6 * dArr4[i5 - 2];
                double d6 = 0.0d;
                while (i6 >= 0) {
                    double d7 = (d6 * d4) + dArr4[i6];
                    if (i6 > i4) {
                        int i7 = i6 - 1;
                        d = d7;
                        d2 = d4;
                        dArr4[i6 - 2] = (i7 * dArr4[i7]) + (((i5 * 2) - i6) * dArr4[i6 - 3]);
                    } else {
                        d = d7;
                        d2 = d4;
                        if (i6 == 2) {
                            dArr4[0] = dArr4[1];
                        }
                        i6 -= 2;
                        i4 = 2;
                        d6 = d;
                        d4 = d2;
                    }
                    i6 -= 2;
                    i4 = 2;
                    d6 = d;
                    d4 = d2;
                }
                double d8 = d4;
                if ((i5 & 1) == 0) {
                    d6 *= d3;
                }
                dSqrt *= d5;
                dArr3[i5] = d6 * dSqrt;
                i5++;
                d4 = d8;
                i4 = 2;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asin(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        double[] dArr3 = new double[this.order + 1];
        double d3 = dArr[i];
        dArr3[0] = FastMath.asin(d3);
        int i3 = this.order;
        if (i3 > 0) {
            double[] dArr4 = new double[i3];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (1.0d - d4);
            double dSqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * dSqrt;
            int i4 = 2;
            int i5 = 2;
            while (i5 <= this.order) {
                int i6 = i5 - 1;
                dArr4[i6] = i6 * dArr4[i5 - 2];
                double d6 = 0.0d;
                while (i6 >= 0) {
                    double d7 = (d6 * d4) + dArr4[i6];
                    if (i6 > i4) {
                        int i7 = i6 - 1;
                        d = d7;
                        d2 = d4;
                        dArr4[i6 - 2] = (i7 * dArr4[i7]) + (((i5 * 2) - i6) * dArr4[i6 - 3]);
                    } else {
                        d = d7;
                        d2 = d4;
                        if (i6 == 2) {
                            dArr4[0] = dArr4[1];
                        }
                        i6 -= 2;
                        d6 = d;
                        d4 = d2;
                        i4 = 2;
                    }
                    i6 -= 2;
                    d6 = d;
                    d4 = d2;
                    i4 = 2;
                }
                double d8 = d4;
                if ((i5 & 1) == 0) {
                    d6 *= d3;
                }
                dSqrt *= d5;
                dArr3[i5] = d6 * dSqrt;
                i5++;
                d4 = d8;
                i4 = 2;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[dSCompiler.order + 1];
        double d2 = dArr[i];
        dArr3[0] = FastMath.atan(d2);
        int i3 = dSCompiler.order;
        if (i3 > 0) {
            double[] dArr4 = new double[i3];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (d3 + 1.0d);
            dArr3[1] = 1.0d * d4;
            int i4 = 2;
            double d5 = d4;
            int i5 = 2;
            while (i5 <= dSCompiler.order) {
                int i6 = i5 - 1;
                double[] dArr5 = dArr4;
                dArr5[i6] = (-i5) * dArr5[i5 - 2];
                double d6 = 0.0d;
                while (i6 >= 0) {
                    double d7 = (d6 * d3) + dArr5[i6];
                    if (i6 > i4) {
                        int i7 = i6 - 1;
                        d = d7;
                        dArr5[i6 - 2] = (i7 * dArr5[i7]) + ((i7 - (i5 * 2)) * dArr5[i6 - 3]);
                    } else {
                        d = d7;
                        if (i6 == 2) {
                            dArr5[0] = dArr5[1];
                        }
                        i6 -= 2;
                        i4 = 2;
                        d6 = d;
                    }
                    i6 -= 2;
                    i4 = 2;
                    d6 = d;
                }
                if ((i5 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i5] = d6 * d5;
                i5++;
                i4 = 2;
                dSCompiler = this;
                dArr4 = dArr5;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan2(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        multiply(dArr2, i2, dArr2, i2, dArr4, 0);
        int size = getSize();
        double[] dArr5 = new double[size];
        multiply(dArr, i, dArr, i, dArr5, 0);
        add(dArr4, 0, dArr5, 0, dArr5, 0);
        rootN(dArr5, 0, 2, dArr4, 0);
        if (dArr2[i2] >= 0.0d) {
            add(dArr4, 0, dArr2, i2, dArr5, 0);
            divide(dArr, i, dArr5, 0, dArr4, 0);
            atan(dArr4, 0, dArr5, 0);
            for (int i4 = 0; i4 < size; i4++) {
                dArr3[i3 + i4] = dArr5[i4] * 2.0d;
            }
        } else {
            subtract(dArr4, 0, dArr2, i2, dArr5, 0);
            divide(dArr, i, dArr5, 0, dArr4, 0);
            atan(dArr4, 0, dArr5, 0);
            double d = dArr5[0];
            dArr3[i3] = (d <= 0.0d ? -3.141592653589793d : 3.141592653589793d) - (d * 2.0d);
            for (int i5 = 1; i5 < size; i5++) {
                dArr3[i3 + i5] = dArr5[i5] * (-2.0d);
            }
        }
        dArr3[i3] = FastMath.atan2(dArr[i], dArr2[i2]);
    }

    public void cosh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.cosh(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.sinh(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void sinh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[this.order + 1];
        dArr3[0] = FastMath.sinh(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.cosh(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void tanh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        int i3;
        double d2;
        double[] dArr3 = new double[this.order + 1];
        double dTanh = FastMath.tanh(dArr[i]);
        dArr3[0] = dTanh;
        int i4 = this.order;
        if (i4 > 0) {
            int i5 = 2;
            double[] dArr4 = new double[i4 + 2];
            dArr4[1] = 1.0d;
            double d3 = dTanh * dTanh;
            int i6 = 1;
            while (i6 <= this.order) {
                int i7 = i6 + 1;
                dArr4[i7] = (-i6) * dArr4[i6];
                double d4 = 0.0d;
                int i8 = i7;
                while (i8 >= 0) {
                    double d5 = (d4 * d3) + dArr4[i8];
                    if (i8 > i5) {
                        int i9 = i8 - 1;
                        d = d3;
                        double d6 = i9 * dArr4[i9];
                        int i10 = i8 - 3;
                        i3 = i7;
                        d2 = d5;
                        dArr4[i8 - 2] = d6 - (i10 * dArr4[i10]);
                    } else {
                        d = d3;
                        i3 = i7;
                        d2 = d5;
                        if (i8 == 2) {
                            dArr4[0] = dArr4[1];
                        }
                        i8 -= 2;
                        i7 = i3;
                        d3 = d;
                        d4 = d2;
                        i5 = 2;
                    }
                    i8 -= 2;
                    i7 = i3;
                    d3 = d;
                    d4 = d2;
                    i5 = 2;
                }
                double d7 = d3;
                int i11 = i7;
                if ((i6 & 1) == 0) {
                    d4 *= dTanh;
                }
                dArr3[i6] = d4;
                i6 = i11;
                d3 = d7;
                i5 = 2;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void acosh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        double[] dArr3 = new double[this.order + 1];
        double d3 = dArr[i];
        dArr3[0] = FastMath.acosh(d3);
        int i3 = this.order;
        if (i3 > 0) {
            double[] dArr4 = new double[i3];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (d4 - 1.0d);
            double dSqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * dSqrt;
            int i4 = 2;
            int i5 = 2;
            while (i5 <= this.order) {
                int i6 = i5 - 1;
                double[] dArr5 = dArr4;
                dArr5[i6] = (1 - i5) * dArr5[i5 - 2];
                double d6 = 0.0d;
                while (i6 >= 0) {
                    double d7 = (d6 * d4) + dArr5[i6];
                    if (i6 > i4) {
                        d = d7;
                        d2 = d4;
                        dArr5[i6 - 2] = ((1 - i6) * dArr5[i6 - 1]) + ((i6 - (i5 * 2)) * dArr5[i6 - 3]);
                    } else {
                        d = d7;
                        d2 = d4;
                        if (i6 == 2) {
                            dArr5[0] = -dArr5[1];
                        }
                        i6 -= 2;
                        d6 = d;
                        d4 = d2;
                        i4 = 2;
                    }
                    i6 -= 2;
                    d6 = d;
                    d4 = d2;
                    i4 = 2;
                }
                double d8 = d4;
                if ((i5 & 1) == 0) {
                    d6 *= d3;
                }
                dSqrt *= d5;
                dArr3[i5] = d6 * dSqrt;
                i5++;
                dArr4 = dArr5;
                d4 = d8;
                i4 = 2;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asinh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double d2;
        double[] dArr3 = new double[this.order + 1];
        double d3 = dArr[i];
        dArr3[0] = FastMath.asinh(d3);
        int i3 = this.order;
        if (i3 > 0) {
            double[] dArr4 = new double[i3];
            dArr4[0] = 1.0d;
            double d4 = d3 * d3;
            double d5 = 1.0d / (d4 + 1.0d);
            double dSqrt = FastMath.sqrt(d5);
            dArr3[1] = dArr4[0] * dSqrt;
            int i4 = 2;
            int i5 = 2;
            while (i5 <= this.order) {
                int i6 = i5 - 1;
                double[] dArr5 = dArr4;
                dArr5[i6] = (1 - i5) * dArr5[i5 - 2];
                double d6 = 0.0d;
                while (i6 >= 0) {
                    double d7 = (d6 * d4) + dArr5[i6];
                    if (i6 > i4) {
                        int i7 = i6 - 1;
                        d = d7;
                        d2 = d4;
                        dArr5[i6 - 2] = (i7 * dArr5[i7]) + ((i6 - (i5 * 2)) * dArr5[i6 - 3]);
                    } else {
                        d = d7;
                        d2 = d4;
                        if (i6 == 2) {
                            dArr5[0] = dArr5[1];
                        }
                        i6 -= 2;
                        d6 = d;
                        d4 = d2;
                        i4 = 2;
                    }
                    i6 -= 2;
                    d6 = d;
                    d4 = d2;
                    i4 = 2;
                }
                double d8 = d4;
                if ((i5 & 1) == 0) {
                    d6 *= d3;
                }
                dSqrt *= d5;
                dArr3[i5] = d6 * dSqrt;
                i5++;
                dArr4 = dArr5;
                d4 = d8;
                i4 = 2;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atanh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        DSCompiler dSCompiler = this;
        double[] dArr3 = new double[dSCompiler.order + 1];
        double d2 = dArr[i];
        dArr3[0] = FastMath.atanh(d2);
        int i3 = dSCompiler.order;
        if (i3 > 0) {
            double[] dArr4 = new double[i3];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d - d3);
            dArr3[1] = 1.0d * d4;
            int i4 = 2;
            double d5 = d4;
            int i5 = 2;
            while (i5 <= dSCompiler.order) {
                int i6 = i5 - 1;
                dArr4[i6] = i5 * dArr4[i5 - 2];
                double d6 = 0.0d;
                while (i6 >= 0) {
                    double d7 = (d6 * d3) + dArr4[i6];
                    if (i6 > i4) {
                        int i7 = i6 - 1;
                        d = d7;
                        dArr4[i6 - 2] = (i7 * dArr4[i7]) + ((((i5 * 2) - i6) + 1) * dArr4[i6 - 3]);
                    } else {
                        d = d7;
                        if (i6 == 2) {
                            dArr4[0] = dArr4[1];
                        }
                        i6 -= 2;
                        i4 = 2;
                        d6 = d;
                    }
                    i6 -= 2;
                    i4 = 2;
                    d6 = d;
                }
                if ((i5 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i5] = d6 * d5;
                i5++;
                i4 = 2;
                dSCompiler = this;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void compose(double[] dArr, int i, double[] dArr2, double[] dArr3, int i2) {
        int i3 = 0;
        while (true) {
            int[][][] iArr = this.compIndirection;
            if (i3 >= iArr.length) {
                return;
            }
            double d = 0.0d;
            for (int[] iArr2 : iArr[i3]) {
                double d2 = iArr2[0] * dArr2[iArr2[1]];
                for (int i4 = 2; i4 < iArr2.length; i4++) {
                    d2 *= dArr[iArr2[i4] + i];
                }
                d += d2;
            }
            dArr3[i2 + i3] = d;
            i3++;
        }
    }

    public double taylor(double[] dArr, int i, double... dArr2) throws MathArithmeticException {
        double d = 0.0d;
        for (int size = getSize() - 1; size >= 0; size--) {
            int[] partialDerivativeOrders = getPartialDerivativeOrders(size);
            double dPow = dArr[i + size];
            for (int i2 = 0; i2 < partialDerivativeOrders.length; i2++) {
                int i3 = partialDerivativeOrders[i2];
                if (i3 > 0) {
                    try {
                        dPow *= FastMath.pow(dArr2[i2], i3) / CombinatoricsUtils.factorial(partialDerivativeOrders[i2]);
                    } catch (NotPositiveException e) {
                        throw new MathInternalError(e);
                    }
                }
            }
            d += dPow;
        }
        return d;
    }

    public void checkCompatibility(DSCompiler dSCompiler) throws DimensionMismatchException {
        if (this.parameters != dSCompiler.parameters) {
            throw new DimensionMismatchException(this.parameters, dSCompiler.parameters);
        }
        if (this.order != dSCompiler.order) {
            throw new DimensionMismatchException(this.order, dSCompiler.order);
        }
    }
}
