package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class LoessInterpolator implements UnivariateInterpolator, Serializable {
    public static final double DEFAULT_ACCURACY = 1.0E-12d;
    public static final double DEFAULT_BANDWIDTH = 0.3d;
    public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
    private static final long serialVersionUID = 5204927143605193821L;
    private final double accuracy;
    private final double bandwidth;
    private final int robustnessIters;

    public LoessInterpolator() {
        this.bandwidth = 0.3d;
        this.robustnessIters = 2;
        this.accuracy = 1.0E-12d;
    }

    public LoessInterpolator(double d, int i) {
        this(d, i, 1.0E-12d);
    }

    public LoessInterpolator(double d, int i, double d2) throws OutOfRangeException, NotPositiveException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, Double.valueOf(d), 0, 1);
        }
        this.bandwidth = d;
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, Integer.valueOf(i));
        }
        this.robustnessIters = i;
        this.accuracy = d2;
    }

    private static void updateBandwidthInterval(double[] dArr, double[] dArr2, int i, int[] iArr) {
        int i2 = iArr[0];
        int iNextNonzero = nextNonzero(dArr2, iArr[1]);
        if (iNextNonzero < dArr.length) {
            double d = dArr[iNextNonzero];
            double d2 = dArr[i];
            if (d - d2 < d2 - dArr[i2]) {
                iArr[0] = nextNonzero(dArr2, iArr[0]);
                iArr[1] = iNextNonzero;
            }
        }
    }

    private static int nextNonzero(double[] dArr, int i) {
        do {
            i++;
            if (i >= dArr.length) {
                break;
            }
        } while (dArr[i] == 0.0d);
        return i;
    }

    private static double tricube(double d) {
        double dAbs = FastMath.abs(d);
        if (dAbs >= 1.0d) {
            return 0.0d;
        }
        double d2 = 1.0d - ((dAbs * dAbs) * dAbs);
        return d2 * d2 * d2;
    }

    private static void checkAllFiniteReal(double[] dArr) throws NotFiniteNumberException {
        for (double d : dArr) {
            MathUtils.checkFinite(d);
        }
    }

    @Override // org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator
    public final PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws NumberIsTooSmallException, NoDataException, NotFiniteNumberException, NonMonotonicSequenceException, DimensionMismatchException {
        return new SplineInterpolator().interpolate(dArr, smooth(dArr, dArr2));
    }

    public final double[] smooth(double[] dArr, double[] dArr2, double[] dArr3) throws NumberIsTooSmallException, NoDataException, NotFiniteNumberException, NonMonotonicSequenceException, DimensionMismatchException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException();
        }
        checkAllFiniteReal(dArr);
        checkAllFiniteReal(dArr2);
        checkAllFiniteReal(dArr3);
        MathArrays.checkOrder(dArr);
        int i = 0;
        char c = 1;
        if (length == 1) {
            return new double[]{dArr2[0]};
        }
        if (length == 2) {
            return new double[]{dArr2[0], dArr2[1]};
        }
        int i2 = (int) (this.bandwidth * length);
        if (i2 < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, Integer.valueOf(i2), 2, true);
        }
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        double[] dArr6 = new double[length];
        double[] dArr7 = new double[length];
        Arrays.fill(dArr7, 1.0d);
        int i3 = 0;
        while (i3 <= this.robustnessIters) {
            int[] iArr = {i, i2 - 1};
            int i4 = 0;
            while (true) {
                double d = 0.0d;
                if (i4 >= length) {
                    break;
                }
                double d2 = dArr[i4];
                if (i4 > 0) {
                    updateBandwidthInterval(dArr, dArr3, i4, iArr);
                }
                int i5 = iArr[i];
                int i6 = iArr[c];
                double d3 = dArr[i4];
                double dAbs = FastMath.abs(1.0d / (dArr[d3 - dArr[i5] > dArr[i6] - d3 ? i5 : i6] - d2));
                double d4 = 0.0d;
                double d5 = 0.0d;
                double d6 = 0.0d;
                double d7 = 0.0d;
                double d8 = 0.0d;
                while (i5 <= i6) {
                    double d9 = dArr[i5];
                    double d10 = dArr2[i5];
                    double dTricube = tricube((i5 < i4 ? d2 - d9 : d9 - d2) * dAbs) * dArr7[i5] * dArr3[i5];
                    double d11 = d9 * dTricube;
                    d5 += dTricube;
                    d4 += d11;
                    d8 += d9 * d11;
                    d6 += dTricube * d10;
                    d7 += d10 * d11;
                    i5++;
                }
                double d12 = d4 / d5;
                double d13 = d6 / d5;
                double d14 = d7 / d5;
                double d15 = (d8 / d5) - (d12 * d12);
                double[] dArr8 = dArr4;
                if (FastMath.sqrt(FastMath.abs(d15)) >= this.accuracy) {
                    d = (d14 - (d12 * d13)) / d15;
                }
                double d16 = (d * d2) + (d13 - (d12 * d));
                dArr8[i4] = d16;
                dArr5[i4] = FastMath.abs(dArr2[i4] - d16);
                i4++;
                dArr4 = dArr8;
                i = 0;
                c = 1;
            }
            double[] dArr9 = dArr4;
            if (i3 == this.robustnessIters) {
                return dArr9;
            }
            System.arraycopy(dArr5, 0, dArr6, 0, length);
            Arrays.sort(dArr6);
            double d17 = dArr6[length / 2];
            if (FastMath.abs(d17) < this.accuracy) {
                return dArr9;
            }
            for (int i7 = 0; i7 < length; i7++) {
                double d18 = dArr5[i7] / (6.0d * d17);
                if (d18 >= 1.0d) {
                    dArr7[i7] = 0.0d;
                } else {
                    double d19 = 1.0d - (d18 * d18);
                    dArr7[i7] = d19 * d19;
                }
            }
            i3++;
            dArr4 = dArr9;
            i = 0;
            c = 1;
        }
        return dArr4;
    }

    public final double[] smooth(double[] dArr, double[] dArr2) throws NumberIsTooSmallException, NoDataException, NotFiniteNumberException, NonMonotonicSequenceException, DimensionMismatchException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        double[] dArr3 = new double[dArr.length];
        Arrays.fill(dArr3, 1.0d);
        return smooth(dArr, dArr2, dArr3);
    }
}
