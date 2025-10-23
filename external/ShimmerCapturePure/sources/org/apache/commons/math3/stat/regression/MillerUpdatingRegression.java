package org.apache.commons.math3.stat.regression;

import java.util.Arrays;

import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class MillerUpdatingRegression implements UpdatingMultipleLinearRegression {
    private final double[] d;
    private final double epsilon;
    private final boolean[] lindep;
    private final int nvars;
    private final double[] r;
    private final double[] rhs;
    private final double[] rss;
    private final double[] tol;
    private final int[] vorder;
    private final double[] work_sing;
    private final double[] work_tolset;
    private final double[] x_sing;
    private boolean hasIntercept;
    private long nobs;
    private boolean rss_set;
    private double sserr;
    private double sumsqy;
    private double sumy;
    private boolean tol_set;

    private MillerUpdatingRegression() {
        this(-1, false, Double.NaN);
    }

    public MillerUpdatingRegression(int i, boolean z, double d) throws ModelSpecificationException {
        this.nobs = 0L;
        this.sserr = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        if (i < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        if (z) {
            this.nvars = i + 1;
        } else {
            this.nvars = i;
        }
        this.hasIntercept = z;
        this.nobs = 0L;
        int i2 = this.nvars;
        this.d = new double[i2];
        this.rhs = new double[i2];
        this.r = new double[((i2 - 1) * i2) / 2];
        this.tol = new double[i2];
        this.rss = new double[i2];
        this.vorder = new int[i2];
        this.x_sing = new double[i2];
        this.work_sing = new double[i2];
        this.work_tolset = new double[i2];
        this.lindep = new boolean[i2];
        for (int i3 = 0; i3 < this.nvars; i3++) {
            this.vorder[i3] = i3;
        }
        if (d > 0.0d) {
            this.epsilon = d;
        } else {
            this.epsilon = -d;
        }
    }

    public MillerUpdatingRegression(int i, boolean z) throws ModelSpecificationException {
        this(i, z, Precision.EPSILON);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public long getN() {
        return this.nobs;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void addObservation(double[] dArr, double d) throws ModelSpecificationException {
        boolean z = this.hasIntercept;
        if ((!z && dArr.length != this.nvars) || (z && dArr.length + 1 != this.nvars)) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(dArr.length), Integer.valueOf(this.nvars));
        }
        if (!z) {
            include(MathArrays.copyOf(dArr, dArr.length), 1.0d, d);
        } else {
            double[] dArr2 = new double[dArr.length + 1];
            System.arraycopy(dArr, 0, dArr2, 1, dArr.length);
            dArr2[0] = 1.0d;
            include(dArr2, 1.0d, d);
        }
        this.nobs++;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void addObservations(double[][] dArr, double[] dArr2) throws ModelSpecificationException {
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            LocalizedFormats localizedFormats = LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(dArr == null ? 0 : dArr.length);
            objArr[1] = Integer.valueOf(dArr2 != null ? dArr2.length : 0);
            throw new ModelSpecificationException(localizedFormats, objArr);
        }
        if (dArr.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.NO_DATA, new Object[0]);
        }
        if (dArr[0].length + 1 > dArr.length) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(dArr.length), Integer.valueOf(dArr[0].length));
        }
        while (i < dArr.length) {
            addObservation(dArr[i], dArr2[i]);
            i++;
        }
    }

    private void include(double[] dArr, double d, double d2) {
        double dSmartAdd;
        this.rss_set = false;
        this.sumy = smartAdd(d2, this.sumy);
        this.sumsqy = smartAdd(this.sumsqy, d2 * d2);
        double d3 = d2;
        int i = 0;
        double d4 = d;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            double d5 = 0.0d;
            if (d4 == 0.0d) {
                return;
            }
            double d6 = dArr[i2];
            if (d6 == 0.0d) {
                i += (this.nvars - i2) - 1;
            } else {
                double d7 = this.d[i2];
                double d8 = d4 * d6;
                if (d7 != 0.0d) {
                    double d9 = d8 * d6;
                    dSmartAdd = smartAdd(d7, d9);
                    if (FastMath.abs(d9 / d7) > Precision.EPSILON) {
                        d4 = (d4 * d7) / dSmartAdd;
                    }
                    d5 = d4;
                } else {
                    dSmartAdd = d8 * d6;
                }
                this.d[i2] = dSmartAdd;
                int i3 = i2 + 1;
                while (i3 < this.nvars) {
                    double d10 = d5;
                    double d11 = dArr[i3];
                    double d12 = d3;
                    dArr[i3] = smartAdd(d11, (-d6) * this.r[i]);
                    if (d7 != 0.0d) {
                        double[] dArr2 = this.r;
                        dArr2[i] = smartAdd(dArr2[i] * d7, d11 * d8) / dSmartAdd;
                    } else {
                        this.r[i] = d11 / d6;
                    }
                    i++;
                    i3++;
                    d5 = d10;
                    d3 = d12;
                }
                double d13 = d3;
                double d14 = d5;
                double dSmartAdd2 = smartAdd(d13, (-d6) * this.rhs[i2]);
                if (d7 != 0.0d) {
                    double[] dArr3 = this.rhs;
                    dArr3[i2] = smartAdd(d7 * dArr3[i2], d13 * d8) / dSmartAdd;
                } else {
                    this.rhs[i2] = d13 / d6;
                }
                d3 = dSmartAdd2;
                d4 = d14;
            }
        }
        this.sserr = smartAdd(this.sserr, d4 * d3 * d3);
    }

    private double smartAdd(double d, double d2) {
        double dAbs = FastMath.abs(d);
        double dAbs2 = FastMath.abs(d2);
        return dAbs > dAbs2 ? dAbs2 > dAbs * Precision.EPSILON ? d + d2 : d : dAbs > dAbs2 * Precision.EPSILON ? d + d2 : d2;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void clear() {
        Arrays.fill(this.d, 0.0d);
        Arrays.fill(this.rhs, 0.0d);
        Arrays.fill(this.r, 0.0d);
        Arrays.fill(this.tol, 0.0d);
        Arrays.fill(this.rss, 0.0d);
        Arrays.fill(this.work_tolset, 0.0d);
        Arrays.fill(this.work_sing, 0.0d);
        Arrays.fill(this.x_sing, 0.0d);
        Arrays.fill(this.lindep, false);
        for (int i = 0; i < this.nvars; i++) {
            this.vorder[i] = i;
        }
        this.nobs = 0L;
        this.sserr = 0.0d;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
    }

    private void tolset() {
        double d = this.epsilon;
        for (int i = 0; i < this.nvars; i++) {
            this.work_tolset[i] = FastMath.sqrt(this.d[i]);
        }
        this.tol[0] = this.work_tolset[0] * d;
        for (int i2 = 1; i2 < this.nvars; i2++) {
            int i3 = i2 - 1;
            double dAbs = this.work_tolset[i2];
            for (int i4 = 0; i4 < i2; i4++) {
                dAbs += FastMath.abs(this.r[i3]) * this.work_tolset[i4];
                i3 += (this.nvars - i4) - 2;
            }
            this.tol[i2] = dAbs * d;
        }
        this.tol_set = true;
    }

    private double[] regcf(int i) throws ModelSpecificationException {
        if (i < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        if (i > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(i), Integer.valueOf(this.nvars));
        }
        if (!this.tol_set) {
            tolset();
        }
        double[] dArr = new double[i];
        boolean z = false;
        for (int i2 = i - 1; i2 > -1; i2--) {
            if (FastMath.sqrt(this.d[i2]) < this.tol[i2]) {
                dArr[i2] = 0.0d;
                this.d[i2] = 0.0d;
                z = true;
            } else {
                dArr[i2] = this.rhs[i2];
                int i3 = this.nvars;
                int i4 = ((((i3 + i3) - i2) - 1) * i2) / 2;
                for (int i5 = i2 + 1; i5 < i; i5++) {
                    dArr[i2] = smartAdd(dArr[i2], (-this.r[i4]) * dArr[i5]);
                    i4++;
                }
            }
        }
        if (z) {
            for (int i6 = 0; i6 < i; i6++) {
                if (this.lindep[i6]) {
                    dArr[i6] = Double.NaN;
                }
            }
        }
        return dArr;
    }

    private void singcheck() {
        for (int i = 0; i < this.nvars; i++) {
            this.work_sing[i] = FastMath.sqrt(this.d[i]);
        }
        for (int i2 = 0; i2 < this.nvars; i2++) {
            double d = this.tol[i2];
            int i3 = i2 - 1;
            int i4 = i3;
            for (int i5 = 0; i5 < i3; i5++) {
                if (FastMath.abs(this.r[i4]) * this.work_sing[i5] < d) {
                    this.r[i4] = 0.0d;
                }
                i4 += (this.nvars - i5) - 2;
            }
            boolean[] zArr = this.lindep;
            zArr[i2] = false;
            if (this.work_sing[i2] < d) {
                zArr[i2] = true;
                if (i2 < this.nvars - 1) {
                    Arrays.fill(this.x_sing, 0.0d);
                    int i6 = this.nvars;
                    int i7 = ((((i6 + i6) - i2) - 1) * i2) / 2;
                    int i8 = i2 + 1;
                    while (i8 < this.nvars) {
                        double[] dArr = this.x_sing;
                        double[] dArr2 = this.r;
                        dArr[i8] = dArr2[i7];
                        dArr2[i7] = 0.0d;
                        i8++;
                        i7++;
                    }
                    double[] dArr3 = this.rhs;
                    double d2 = dArr3[i2];
                    double[] dArr4 = this.d;
                    double d3 = dArr4[i2];
                    dArr4[i2] = 0.0d;
                    dArr3[i2] = 0.0d;
                    include(this.x_sing, d3, d2);
                } else {
                    double d4 = this.sserr;
                    double d5 = this.d[i2];
                    double d6 = this.rhs[i2];
                    this.sserr = d4 + (d5 * d6 * d6);
                }
            }
        }
    }

    private void ss() {
        double d = this.sserr;
        double[] dArr = this.rss;
        int i = this.nvars;
        dArr[i - 1] = d;
        for (int i2 = i - 1; i2 > 0; i2--) {
            double d2 = this.d[i2];
            double d3 = this.rhs[i2];
            d += d2 * d3 * d3;
            this.rss[i2 - 1] = d;
        }
        this.rss_set = true;
    }

    private double[] cov(int i) {
        double d;
        double d2;
        if (this.nobs <= i) {
            return null;
        }
        int i2 = 0;
        double d3 = 0.0d;
        int i3 = 0;
        while (true) {
            d = 1.0d;
            if (i3 >= i) {
                break;
            }
            if (!this.lindep[i3]) {
                d3 += 1.0d;
            }
            i3++;
        }
        int i4 = i - 1;
        double d4 = this.rss[i4] / (this.nobs - d3);
        double[] dArr = new double[(i * i4) / 2];
        inverse(dArr, i);
        double[] dArr2 = new double[((i + 1) * i) / 2];
        Arrays.fill(dArr2, Double.NaN);
        int i5 = 0;
        while (i2 < i) {
            if (!this.lindep[i2]) {
                int i6 = i2;
                int i7 = i5;
                while (i6 < i) {
                    if (this.lindep[i6]) {
                        i7 += (i - i6) - 1;
                    } else {
                        int i8 = (i5 + i6) - i2;
                        if (i2 == i6) {
                            d2 = d / this.d[i6];
                        } else {
                            d2 = dArr[i8 - 1] / this.d[i6];
                        }
                        int i9 = i6 + 1;
                        for (int i10 = i9; i10 < i; i10++) {
                            if (!this.lindep[i10]) {
                                d2 += (dArr[i8] * dArr[i7]) / this.d[i10];
                            }
                            i8++;
                            i7++;
                        }
                        dArr2[((i9 * i6) / 2) + i2] = d2 * d4;
                    }
                    i6++;
                    d = 1.0d;
                }
            }
            i5 += (i - i2) - 1;
            i2++;
            d = 1.0d;
        }
        return dArr2;
    }

    private void inverse(double[] dArr, int i) {
        int i2 = i - 1;
        int i3 = ((i * i2) / 2) - 1;
        Arrays.fill(dArr, Double.NaN);
        while (i2 > 0) {
            if (this.lindep[i2]) {
                i3 -= i - i2;
            } else {
                int i4 = this.nvars;
                int i5 = ((i2 - 1) * ((i4 + i4) - i2)) / 2;
                for (int i6 = i; i6 > i2; i6--) {
                    double d = 0.0d;
                    int i7 = i3;
                    int i8 = i5;
                    for (int i9 = i2; i9 < i6 - 1; i9++) {
                        i7 += (i - i9) - 1;
                        if (!this.lindep[i9]) {
                            d += (-this.r[i8]) * dArr[i7];
                        }
                        i8++;
                    }
                    dArr[i3] = d - this.r[i8];
                    i3--;
                }
            }
            i2--;
        }
    }

    public double[] getPartialCorrelations(int i) {
        int i2 = this.nvars;
        double[] dArr = new double[(((i2 - i) + 1) * (i2 - i)) / 2];
        int i3 = -i;
        int i4 = i + 1;
        int i5 = -i4;
        double[] dArr2 = new double[i2 - i];
        double[] dArr3 = new double[(i2 - i) - 1];
        int i6 = ((i2 - i) * ((i2 - i) - 1)) / 2;
        if (i < -1 || i >= i2) {
            return null;
        }
        int i7 = (i2 - 1) - i;
        int length = this.r.length - ((i7 * (i7 + 1)) / 2);
        double d = this.d[i];
        double d2 = 0.0d;
        if (d > 0.0d) {
            dArr2[i + i3] = 1.0d / FastMath.sqrt(d);
        }
        while (i4 < this.nvars) {
            int i8 = ((length + i4) - 1) - i;
            double d3 = this.d[i4];
            for (int i9 = i; i9 < i4; i9++) {
                double d4 = this.d[i9];
                double d5 = this.r[i8];
                d3 += d4 * d5 * d5;
                i8 += (this.nvars - i9) - 2;
            }
            if (d3 > 0.0d) {
                dArr2[i4 + i3] = 1.0d / FastMath.sqrt(d3);
            } else {
                dArr2[i4 + i3] = 0.0d;
            }
            i4++;
        }
        double dSqrt = this.sserr;
        for (int i10 = i; i10 < this.nvars; i10++) {
            double d6 = this.d[i10];
            double d7 = this.rhs[i10];
            dSqrt += d6 * d7 * d7;
        }
        if (dSqrt > 0.0d) {
            dSqrt = 1.0d / FastMath.sqrt(dSqrt);
        }
        int i11 = i;
        while (i11 < this.nvars) {
            Arrays.fill(dArr3, d2);
            int i12 = ((length + i11) - i) - 1;
            int i13 = i;
            double d8 = d2;
            while (i13 < i11) {
                int i14 = i12 + 1;
                int i15 = i11 + 1;
                while (true) {
                    if (i15 < this.nvars) {
                        int i16 = i15 + i5;
                        double d9 = dArr3[i16];
                        int i17 = length;
                        double d10 = this.d[i13];
                        double[] dArr4 = this.r;
                        dArr3[i16] = d9 + (d10 * dArr4[i12] * dArr4[i14]);
                        i14++;
                        i15++;
                        length = i17;
                    }
                }
                d8 += this.d[i13] * this.r[i12] * this.rhs[i13];
                i12 += (r14 - i13) - 2;
                i13++;
                length = length;
            }
            int i18 = length;
            int i19 = i12 + 1;
            int i20 = i11 + 1;
            for (int i21 = i20; i21 < this.nvars; i21++) {
                int i22 = i21 + i5;
                double d11 = dArr3[i22] + (this.d[i11] * this.r[i19]);
                dArr3[i22] = d11;
                i19++;
                dArr[(((((i21 - 1) - i) * (i21 - i)) / 2) + i11) - i] = d11 * dArr2[i11 + i3] * dArr2[i21 + i3];
            }
            double d12 = d8 + (this.d[i11] * this.rhs[i11]);
            int i23 = i11 + i3;
            dArr[i23 + i6] = d12 * dArr2[i23] * dSqrt;
            i11 = i20;
            length = i18;
            d2 = 0.0d;
        }
        return dArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void vmove(int r25, int r26) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove(int, int):void");
    }

    private int reorderRegressors(int[] iArr, int i) {
        if (iArr.length < 1 || iArr.length > (this.nvars + 1) - i) {
            return -1;
        }
        int i2 = i;
        int i3 = i2;
        while (i2 < this.nvars) {
            int i4 = this.vorder[i2];
            int i5 = 0;
            while (true) {
                if (i5 >= iArr.length) {
                    break;
                }
                if (i4 != iArr[i5] || i2 <= i3) {
                    i5++;
                } else {
                    vmove(i2, i3);
                    i3++;
                    if (i3 >= iArr.length + i) {
                        return 0;
                    }
                }
            }
            i2++;
        }
        return 0;
    }

    public double getDiagonalOfHatMatrix(double[] dArr) {
        double[] dArr2 = dArr;
        int i = this.nvars;
        double[] dArr3 = new double[i];
        if (dArr2.length > i) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            double[] dArr4 = new double[dArr2.length + 1];
            dArr4[0] = 1.0d;
            System.arraycopy(dArr2, 0, dArr4, 1, dArr2.length);
            dArr2 = dArr4;
        }
        double dSmartAdd = 0.0d;
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            if (FastMath.sqrt(this.d[i2]) < this.tol[i2]) {
                dArr3[i2] = 0.0d;
            } else {
                int i3 = i2 - 1;
                double dSmartAdd2 = dArr2[i2];
                for (int i4 = 0; i4 < i2; i4++) {
                    dSmartAdd2 = smartAdd(dSmartAdd2, (-dArr3[i4]) * this.r[i3]);
                    i3 += (this.nvars - i4) - 2;
                }
                dArr3[i2] = dSmartAdd2;
                dSmartAdd = smartAdd(dSmartAdd, (dSmartAdd2 * dSmartAdd2) / this.d[i2]);
            }
        }
        return dSmartAdd;
    }

    public int[] getOrderOfRegressors() {
        return MathArrays.copyOf(this.vorder);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public RegressionResults regress() throws ModelSpecificationException {
        return regress(this.nvars);
    }

    public RegressionResults regress(int i) throws ModelSpecificationException {
        int i2;
        if (this.nobs <= i) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(i));
        }
        if (i > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(i), Integer.valueOf(this.nvars));
        }
        tolset();
        singcheck();
        double[] dArrRegcf = regcf(i);
        ss();
        double[] dArrCov = cov(i);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean[] zArr = this.lindep;
            if (i3 >= zArr.length) {
                break;
            }
            if (!zArr[i3]) {
                i4++;
            }
            i3++;
        }
        for (int i5 = 0; i5 < i; i5++) {
            if (this.vorder[i5] != i5) {
                double[] dArr = new double[dArrRegcf.length];
                double[] dArr2 = new double[dArrCov.length];
                int[] iArr = new int[dArrRegcf.length];
                for (int i6 = 0; i6 < this.nvars; i6++) {
                    for (int i7 = 0; i7 < i; i7++) {
                        if (this.vorder[i7] == i6) {
                            dArr[i6] = dArrRegcf[i7];
                            iArr[i6] = i7;
                        }
                    }
                }
                int i8 = 0;
                for (int i9 = 0; i9 < dArrRegcf.length; i9++) {
                    int i10 = iArr[i9];
                    int i11 = 0;
                    while (i11 <= i9) {
                        int i12 = iArr[i11];
                        if (i10 > i12) {
                            i2 = (((i10 + 1) * i10) / 2) + i12;
                        } else {
                            i2 = ((i12 * (i12 + 1)) / 2) + i10;
                        }
                        dArr2[i8] = dArrCov[i2];
                        i11++;
                        i8++;
                    }
                }
                return new RegressionResults(dArr, new double[][]{dArr2}, true, this.nobs, i4, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
            }
        }
        return new RegressionResults(dArrRegcf, new double[][]{dArrCov}, true, this.nobs, i4, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public RegressionResults regress(int[] iArr) throws ModelSpecificationException {
        int i;
        int[] iArr2 = iArr;
        int length = iArr2.length;
        int i2 = this.nvars;
        if (length > i2) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(iArr2.length), Integer.valueOf(this.nvars));
        }
        if (this.nobs <= i2) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(this.nvars));
        }
        Arrays.sort(iArr);
        int i3 = 0;
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            if (i4 >= this.nvars) {
                throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, Integer.valueOf(i4), Integer.valueOf(this.nvars));
            }
            if (i4 > 0 && iArr2[i4] == iArr2[i4 - 1]) {
                iArr2[i4] = -1;
                i3++;
            }
        }
        if (i3 > 0) {
            int[] iArr3 = new int[iArr2.length - i3];
            int i5 = 0;
            for (int i6 : iArr2) {
                if (i6 > -1) {
                    iArr3[i5] = i6;
                    i5++;
                }
            }
            iArr2 = iArr3;
        }
        reorderRegressors(iArr2, 0);
        tolset();
        singcheck();
        double[] dArrRegcf = regcf(iArr2.length);
        ss();
        double[] dArrCov = cov(iArr2.length);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            boolean[] zArr = this.lindep;
            if (i7 >= zArr.length) {
                break;
            }
            if (!zArr[i7]) {
                i8++;
            }
            i7++;
        }
        for (int i9 = 0; i9 < this.nvars; i9++) {
            if (this.vorder[i9] != iArr2[i9]) {
                double[] dArr = new double[dArrRegcf.length];
                int[] iArr4 = new int[dArrRegcf.length];
                for (int i10 = 0; i10 < iArr2.length; i10++) {
                    int i11 = 0;
                    while (true) {
                        int[] iArr5 = this.vorder;
                        if (i11 < iArr5.length) {
                            if (iArr5[i11] == iArr2[i10]) {
                                dArr[i10] = dArrRegcf[i11];
                                iArr4[i10] = i11;
                            }
                            i11++;
                        }
                    }
                }
                double[] dArr2 = new double[dArrCov.length];
                int i12 = 0;
                for (int i13 = 0; i13 < dArrRegcf.length; i13++) {
                    int i14 = iArr4[i13];
                    int i15 = 0;
                    while (i15 <= i13) {
                        int i16 = iArr4[i15];
                        if (i14 > i16) {
                            i = (((i14 + 1) * i14) / 2) + i16;
                        } else {
                            i = ((i16 * (i16 + 1)) / 2) + i14;
                        }
                        dArr2[i12] = dArrCov[i];
                        i15++;
                        i12++;
                    }
                }
                return new RegressionResults(dArr, new double[][]{dArr2}, true, this.nobs, i8, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
            }
        }
        return new RegressionResults(dArrRegcf, new double[][]{dArrCov}, true, this.nobs, i8, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
    }
}
