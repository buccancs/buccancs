package org.jtransforms.fft;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

/* loaded from: classes2.dex */
public class DoubleFFT_2D {
    private int columns;
    private long columnsl;
    private DoubleFFT_1D fftColumns;
    private DoubleFFT_1D fftRows;
    private boolean isPowerOfTwo;
    private int rows;
    private long rowsl;
    private boolean useThreads;

    public DoubleFFT_2D(long j, long j2) {
        this.isPowerOfTwo = false;
        this.useThreads = false;
        if (j <= 1 || j2 <= 1) {
            throw new IllegalArgumentException("rows and columns must be greater than 1");
        }
        this.rows = (int) j;
        this.columns = (int) j2;
        this.rowsl = j;
        this.columnsl = j2;
        if (j * j2 >= CommonUtils.getThreadsBeginN_2D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays((2 * j) * j2 > ((long) LargeArray.getMaxSizeOf32bitArray()));
        DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(j);
        this.fftRows = doubleFFT_1D;
        if (j == j2) {
            this.fftColumns = doubleFFT_1D;
        } else {
            this.fftColumns = new DoubleFFT_1D(j2);
        }
    }

    public void complexForward(final double[] dArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft2d0_subth1(0, -1, dArr, true);
                cdft2d_subth(-1, dArr, true);
            } else {
                while (i3 < this.rows) {
                    this.fftColumns.complexForward(dArr, this.columns * i3);
                    i3++;
                }
                cdft2d_sub(-1, dArr, true);
            }
            this.columns /= 2;
            return;
        }
        int i4 = this.columns;
        final int i5 = i4 * 2;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && i4 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i6 = i2 / numberOfThreads;
            int i7 = 0;
            while (i7 < numberOfThreads) {
                final int i8 = i7 * i6;
                final int i9 = i7 == numberOfThreads + (-1) ? this.rows : i8 + i6;
                futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i8; i10 < i9; i10++) {
                            DoubleFFT_2D.this.fftColumns.complexForward(dArr, i5 * i10);
                        }
                    }
                });
                i7++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i10 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i11 = i3 * i10;
                final int i12 = i3 == numberOfThreads + (-1) ? this.columns : i11 + i10;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_2D.this.rows * 2];
                        for (int i13 = i11; i13 < i12; i13++) {
                            int i14 = i13 * 2;
                            for (int i15 = 0; i15 < DoubleFFT_2D.this.rows; i15++) {
                                int i16 = i15 * 2;
                                int i17 = (i5 * i15) + i14;
                                double[] dArr3 = dArr;
                                dArr2[i16] = dArr3[i17];
                                dArr2[i16 + 1] = dArr3[i17 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2);
                            for (int i18 = 0; i18 < DoubleFFT_2D.this.rows; i18++) {
                                int i19 = i18 * 2;
                                int i20 = (i5 * i18) + i14;
                                double[] dArr4 = dArr;
                                dArr4[i20] = dArr2[i19];
                                dArr4[i20 + 1] = dArr2[i19 + 1];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                return;
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                return;
            }
        }
        int i13 = 0;
        while (true) {
            i = this.rows;
            if (i13 >= i) {
                break;
            }
            this.fftColumns.complexForward(dArr, i13 * i5);
            i13++;
        }
        double[] dArr2 = new double[i * 2];
        for (int i14 = 0; i14 < this.columns; i14++) {
            int i15 = i14 * 2;
            for (int i16 = 0; i16 < this.rows; i16++) {
                int i17 = i16 * 2;
                int i18 = (i16 * i5) + i15;
                dArr2[i17] = dArr[i18];
                dArr2[i17 + 1] = dArr[i18 + 1];
            }
            this.fftRows.complexForward(dArr2);
            for (int i19 = 0; i19 < this.rows; i19++) {
                int i20 = i19 * 2;
                int i21 = (i19 * i5) + i15;
                dArr[i21] = dArr2[i20];
                dArr[i21 + 1] = dArr2[i20 + 1];
            }
        }
    }

    public void complexForward(final DoubleLargeArray doubleLargeArray) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i = 0;
        long j = 2;
        if (this.isPowerOfTwo) {
            this.columnsl *= 2;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft2d0_subth1(0L, -1, doubleLargeArray, true);
                cdft2d_subth(-1, doubleLargeArray, true);
            } else {
                while (true) {
                    long j2 = i;
                    if (j2 >= this.rowsl) {
                        break;
                    }
                    this.fftColumns.complexForward(doubleLargeArray, j2 * this.columnsl);
                    i++;
                }
                cdft2d_sub(-1, doubleLargeArray, true);
            }
            this.columnsl /= 2;
            return;
        }
        long j3 = this.columnsl;
        final long j4 = j3 * 2;
        if (numberOfThreads > 1 && this.useThreads) {
            long j5 = this.rowsl;
            long j6 = numberOfThreads;
            if (j5 >= j6 && j3 >= j6) {
                Future[] futureArr = new Future[numberOfThreads];
                long j7 = j5 / j6;
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j8 = i2 * j7;
                    final long j9 = i2 == numberOfThreads + (-1) ? this.rowsl : j8 + j7;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.3
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j10 = j8; j10 < j9; j10++) {
                                DoubleFFT_2D.this.fftColumns.complexForward(doubleLargeArray, j4 * j10);
                            }
                        }
                    });
                    i2++;
                    futureArr = futureArr2;
                    j6 = j6;
                }
                long j10 = j6;
                Future[] futureArr3 = futureArr;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                long j11 = this.columnsl / j10;
                while (i < numberOfThreads) {
                    final long j12 = i * j11;
                    final long j13 = i == numberOfThreads + (-1) ? this.columnsl : j12 + j11;
                    futureArr3[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.4
                        @Override // java.lang.Runnable
                        public void run() {
                            long j14;
                            long j15 = 2;
                            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(DoubleFFT_2D.this.rowsl * 2, false);
                            long j16 = j12;
                            while (j16 < j13) {
                                long j17 = j16 * j15;
                                long j18 = 0;
                                while (true) {
                                    j14 = 1;
                                    if (j18 >= DoubleFFT_2D.this.rowsl) {
                                        break;
                                    }
                                    long j19 = j18 * j15;
                                    long j20 = (j4 * j18) + j17;
                                    doubleLargeArray2.setDouble(j19, doubleLargeArray.getDouble(j20));
                                    doubleLargeArray2.setDouble(j19 + 1, doubleLargeArray.getDouble(j20 + 1));
                                    j18++;
                                    j15 = 2;
                                }
                                DoubleFFT_2D.this.fftRows.complexForward(doubleLargeArray2);
                                for (long j21 = 0; j21 < DoubleFFT_2D.this.rowsl; j21++) {
                                    long j22 = j21 * 2;
                                    long j23 = (j4 * j21) + j17;
                                    doubleLargeArray.setDouble(j23, doubleLargeArray2.getDouble(j22));
                                    j14 = 1;
                                    doubleLargeArray.setDouble(j23 + 1, doubleLargeArray2.getDouble(j22 + 1));
                                }
                                j16 += j14;
                                j15 = 2;
                            }
                        }
                    });
                    i++;
                    str = str;
                    numberOfThreads = numberOfThreads;
                }
                String str2 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e3) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                    return;
                } catch (ExecutionException e4) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                    return;
                }
            }
        }
        for (long j14 = 0; j14 < this.rowsl; j14++) {
            this.fftColumns.complexForward(doubleLargeArray, j14 * j4);
        }
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.rowsl * 2, false);
        long j15 = 0;
        while (j15 < this.columnsl) {
            long j16 = j15 * j;
            long j17 = 0;
            while (j17 < this.rowsl) {
                long j18 = j17 * j;
                long j19 = (j17 * j4) + j16;
                doubleLargeArray2.setDouble(j18, doubleLargeArray.getDouble(j19));
                doubleLargeArray2.setDouble(j18 + 1, doubleLargeArray.getDouble(j19 + 1));
                j17++;
                j15 = j15;
                j = 2;
            }
            long j20 = j15;
            this.fftRows.complexForward(doubleLargeArray2);
            long j21 = 0;
            while (j21 < this.rowsl) {
                long j22 = j21 * 2;
                long j23 = (j21 * j4) + j16;
                doubleLargeArray.setDouble(j23, doubleLargeArray2.getDouble(j22));
                doubleLargeArray.setDouble(j23 + 1, doubleLargeArray2.getDouble(j22 + 1));
                j21++;
                j16 = j16;
            }
            j15 = j20 + 1;
            j = 2;
        }
    }

    public void complexForward(final double[][] dArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft2d0_subth1(0, -1, dArr, true);
                cdft2d_subth(-1, dArr, true);
            } else {
                while (i3 < this.rows) {
                    this.fftColumns.complexForward(dArr[i3]);
                    i3++;
                }
                cdft2d_sub(-1, dArr, true);
            }
            this.columns /= 2;
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.rows : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.5
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            DoubleFFT_2D.this.fftColumns.complexForward(dArr[i8]);
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i9 = i3 * i8;
                final int i10 = i3 == numberOfThreads + (-1) ? this.columns : i9 + i8;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.6
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_2D.this.rows * 2];
                        for (int i11 = i9; i11 < i10; i11++) {
                            int i12 = i11 * 2;
                            for (int i13 = 0; i13 < DoubleFFT_2D.this.rows; i13++) {
                                int i14 = i13 * 2;
                                double[] dArr3 = dArr[i13];
                                dArr2[i14] = dArr3[i12];
                                dArr2[i14 + 1] = dArr3[i12 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2);
                            for (int i15 = 0; i15 < DoubleFFT_2D.this.rows; i15++) {
                                int i16 = i15 * 2;
                                double[] dArr4 = dArr[i15];
                                dArr4[i12] = dArr2[i16];
                                dArr4[i12 + 1] = dArr2[i16 + 1];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                return;
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                return;
            }
        }
        int i11 = 0;
        while (true) {
            i = this.rows;
            if (i11 >= i) {
                break;
            }
            this.fftColumns.complexForward(dArr[i11]);
            i11++;
        }
        double[] dArr2 = new double[i * 2];
        for (int i12 = 0; i12 < this.columns; i12++) {
            int i13 = i12 * 2;
            for (int i14 = 0; i14 < this.rows; i14++) {
                int i15 = i14 * 2;
                double[] dArr3 = dArr[i14];
                dArr2[i15] = dArr3[i13];
                dArr2[i15 + 1] = dArr3[i13 + 1];
            }
            this.fftRows.complexForward(dArr2);
            for (int i16 = 0; i16 < this.rows; i16++) {
                int i17 = i16 * 2;
                double[] dArr4 = dArr[i16];
                dArr4[i13] = dArr2[i17];
                dArr4[i13 + 1] = dArr2[i17 + 1];
            }
        }
    }

    public void complexInverse(final double[] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft2d0_subth1(0, 1, dArr, z);
                cdft2d_subth(1, dArr, z);
            } else {
                while (i3 < this.rows) {
                    this.fftColumns.complexInverse(dArr, this.columns * i3, z);
                    i3++;
                }
                cdft2d_sub(1, dArr, z);
            }
            this.columns /= 2;
            return;
        }
        int i4 = this.columns;
        final int i5 = i4 * 2;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && i4 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i6 = i2 / numberOfThreads;
            int i7 = 0;
            while (i7 < numberOfThreads) {
                final int i8 = i7 * i6;
                final int i9 = i7 == numberOfThreads + (-1) ? this.rows : i8 + i6;
                int i10 = i7;
                futureArr[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i11 = i8; i11 < i9; i11++) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr, i5 * i11, z);
                        }
                    }
                });
                i7 = i10 + 1;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i11 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.columns : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_2D.this.rows * 2];
                        for (int i14 = i12; i14 < i13; i14++) {
                            int i15 = i14 * 2;
                            for (int i16 = 0; i16 < DoubleFFT_2D.this.rows; i16++) {
                                int i17 = i16 * 2;
                                int i18 = (i5 * i16) + i15;
                                double[] dArr3 = dArr;
                                dArr2[i17] = dArr3[i18];
                                dArr2[i17 + 1] = dArr3[i18 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, z);
                            for (int i19 = 0; i19 < DoubleFFT_2D.this.rows; i19++) {
                                int i20 = i19 * 2;
                                int i21 = (i5 * i19) + i15;
                                double[] dArr4 = dArr;
                                dArr4[i21] = dArr2[i20];
                                dArr4[i21 + 1] = dArr2[i20 + 1];
                            }
                        }
                    }
                });
                i3++;
                str = str;
                numberOfThreads = numberOfThreads;
            }
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                return;
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                return;
            }
        }
        int i14 = 0;
        while (true) {
            i = this.rows;
            if (i14 >= i) {
                break;
            }
            this.fftColumns.complexInverse(dArr, i14 * i5, z);
            i14++;
        }
        double[] dArr2 = new double[i * 2];
        for (int i15 = 0; i15 < this.columns; i15++) {
            int i16 = i15 * 2;
            for (int i17 = 0; i17 < this.rows; i17++) {
                int i18 = i17 * 2;
                int i19 = (i17 * i5) + i16;
                dArr2[i18] = dArr[i19];
                dArr2[i18 + 1] = dArr[i19 + 1];
            }
            this.fftRows.complexInverse(dArr2, z);
            for (int i20 = 0; i20 < this.rows; i20++) {
                int i21 = i20 * 2;
                int i22 = (i20 * i5) + i16;
                dArr[i22] = dArr2[i21];
                dArr[i22 + 1] = dArr2[i21 + 1];
            }
        }
    }

    public void complexInverse(final DoubleLargeArray doubleLargeArray, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j = 2;
        if (this.isPowerOfTwo) {
            this.columnsl *= 2;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft2d0_subth1(0L, 1, doubleLargeArray, z);
                cdft2d_subth(1, doubleLargeArray, z);
            } else {
                for (long j2 = 0; j2 < this.rowsl; j2++) {
                    this.fftColumns.complexInverse(doubleLargeArray, this.columnsl * j2, z);
                }
                cdft2d_sub(1, doubleLargeArray, z);
            }
            this.columnsl /= 2;
            return;
        }
        long j3 = this.columnsl;
        final long j4 = j3 * 2;
        if (numberOfThreads > 1 && this.useThreads) {
            long j5 = this.rowsl;
            long j6 = numberOfThreads;
            if (j5 >= j6 && j3 >= j6) {
                Future[] futureArr = new Future[numberOfThreads];
                long j7 = j5 / j6;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j8 = i * j7;
                    final long j9 = i == numberOfThreads + (-1) ? this.rowsl : j8 + j7;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.9
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j10 = j8; j10 < j9; j10++) {
                                DoubleFFT_2D.this.fftColumns.complexInverse(doubleLargeArray, j4 * j10, z);
                            }
                        }
                    });
                    i++;
                    futureArr = futureArr2;
                }
                Future[] futureArr3 = futureArr;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                long j10 = this.columnsl / j6;
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j11 = i2 * j10;
                    final long j12 = i2 == numberOfThreads + (-1) ? this.columnsl : j11 + j10;
                    futureArr3[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.10
                        @Override // java.lang.Runnable
                        public void run() {
                            long j13;
                            long j14 = 2;
                            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(DoubleFFT_2D.this.rowsl * 2, false);
                            long j15 = j11;
                            while (j15 < j12) {
                                long j16 = j15 * j14;
                                long j17 = 0;
                                while (true) {
                                    j13 = 1;
                                    if (j17 >= DoubleFFT_2D.this.rowsl) {
                                        break;
                                    }
                                    long j18 = j17 * j14;
                                    long j19 = (j4 * j17) + j16;
                                    doubleLargeArray2.setDouble(j18, doubleLargeArray.getDouble(j19));
                                    doubleLargeArray2.setDouble(j18 + 1, doubleLargeArray.getDouble(j19 + 1));
                                    j17++;
                                    j14 = 2;
                                }
                                DoubleFFT_2D.this.fftRows.complexInverse(doubleLargeArray2, z);
                                for (long j20 = 0; j20 < DoubleFFT_2D.this.rowsl; j20++) {
                                    long j21 = j20 * 2;
                                    long j22 = (j4 * j20) + j16;
                                    doubleLargeArray.setDouble(j22, doubleLargeArray2.getDouble(j21));
                                    j13 = 1;
                                    doubleLargeArray.setDouble(j22 + 1, doubleLargeArray2.getDouble(j21 + 1));
                                }
                                j15 += j13;
                                j14 = 2;
                            }
                        }
                    });
                    i2++;
                    str = str;
                    j10 = j10;
                }
                String str2 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e3) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                    return;
                } catch (ExecutionException e4) {
                    Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                    return;
                }
            }
        }
        for (long j13 = 0; j13 < this.rowsl; j13++) {
            this.fftColumns.complexInverse(doubleLargeArray, j13 * j4, z);
        }
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.rowsl * 2, false);
        long j14 = 0;
        while (j14 < this.columnsl) {
            long j15 = j14 * j;
            long j16 = 0;
            while (j16 < this.rowsl) {
                long j17 = j16 * j;
                long j18 = (j16 * j4) + j15;
                doubleLargeArray2.setDouble(j17, doubleLargeArray.getDouble(j18));
                doubleLargeArray2.setDouble(j17 + 1, doubleLargeArray.getDouble(j18 + 1));
                j16++;
                j14 = j14;
                j = 2;
            }
            long j19 = j14;
            this.fftRows.complexInverse(doubleLargeArray2, z);
            for (long j20 = 0; j20 < this.rowsl; j20++) {
                long j21 = j20 * 2;
                long j22 = (j20 * j4) + j15;
                doubleLargeArray.setDouble(j22, doubleLargeArray2.getDouble(j21));
                doubleLargeArray.setDouble(j22 + 1, doubleLargeArray2.getDouble(j21 + 1));
            }
            j14 = j19 + 1;
            j = 2;
        }
    }

    public void complexInverse(final double[][] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            this.columns *= 2;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft2d0_subth1(0, 1, dArr, z);
                cdft2d_subth(1, dArr, z);
            } else {
                while (i3 < this.rows) {
                    this.fftColumns.complexInverse(dArr[i3], z);
                    i3++;
                }
                cdft2d_sub(1, dArr, z);
            }
            this.columns /= 2;
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.rows : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.11
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr[i8], z);
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i9 = i3 * i8;
                final int i10 = i3 == numberOfThreads + (-1) ? this.columns : i9 + i8;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.12
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_2D.this.rows * 2];
                        for (int i11 = i9; i11 < i10; i11++) {
                            int i12 = i11 * 2;
                            for (int i13 = 0; i13 < DoubleFFT_2D.this.rows; i13++) {
                                int i14 = i13 * 2;
                                double[] dArr3 = dArr[i13];
                                dArr2[i14] = dArr3[i12];
                                dArr2[i14 + 1] = dArr3[i12 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, z);
                            for (int i15 = 0; i15 < DoubleFFT_2D.this.rows; i15++) {
                                int i16 = i15 * 2;
                                double[] dArr4 = dArr[i15];
                                dArr4[i12] = dArr2[i16];
                                dArr4[i12 + 1] = dArr2[i16 + 1];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                return;
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                return;
            }
        }
        int i11 = 0;
        while (true) {
            i = this.rows;
            if (i11 >= i) {
                break;
            }
            this.fftColumns.complexInverse(dArr[i11], z);
            i11++;
        }
        double[] dArr2 = new double[i * 2];
        for (int i12 = 0; i12 < this.columns; i12++) {
            int i13 = i12 * 2;
            for (int i14 = 0; i14 < this.rows; i14++) {
                int i15 = i14 * 2;
                double[] dArr3 = dArr[i14];
                dArr2[i15] = dArr3[i13];
                dArr2[i15 + 1] = dArr3[i13 + 1];
            }
            this.fftRows.complexInverse(dArr2, z);
            for (int i16 = 0; i16 < this.rows; i16++) {
                int i17 = i16 * 2;
                double[] dArr4 = dArr[i16];
                dArr4[i13] = dArr2[i17];
                dArr4[i13 + 1] = dArr2[i17 + 1];
            }
        }
    }

    public void realForward(double[] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft2d0_subth1(1, 1, dArr, true);
            cdft2d_subth(-1, dArr, true);
            rdft2d_sub(1, dArr);
        } else {
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realForward(dArr, this.columns * i);
            }
            cdft2d_sub(-1, dArr, true);
            rdft2d_sub(1, dArr);
        }
    }

    public void realForward(DoubleLargeArray doubleLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft2d0_subth1(1L, 1, doubleLargeArray, true);
            cdft2d_subth(-1, doubleLargeArray, true);
            rdft2d_sub(1, doubleLargeArray);
        } else {
            for (long j = 0; j < this.rowsl; j++) {
                this.fftColumns.realForward(doubleLargeArray, this.columnsl * j);
            }
            cdft2d_sub(-1, doubleLargeArray, true);
            rdft2d_sub(1, doubleLargeArray);
        }
    }

    public void realForward(double[][] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft2d0_subth1(1, 1, dArr, true);
            cdft2d_subth(-1, dArr, true);
            rdft2d_sub(1, dArr);
        } else {
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realForward(dArr[i]);
            }
            cdft2d_sub(-1, dArr, true);
            rdft2d_sub(1, dArr);
        }
    }

    public void realForwardFull(double[] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft2d0_subth1(1, 1, dArr, true);
                cdft2d_subth(-1, dArr, true);
                rdft2d_sub(1, dArr);
            } else {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realForward(dArr, this.columns * i);
                }
                cdft2d_sub(-1, dArr, true);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realForwardFull(DoubleLargeArray doubleLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft2d0_subth1(1L, 1, doubleLargeArray, true);
                cdft2d_subth(-1, doubleLargeArray, true);
                rdft2d_sub(1, doubleLargeArray);
            } else {
                for (long j = 0; j < this.rowsl; j++) {
                    this.fftColumns.realForward(doubleLargeArray, this.columnsl * j);
                }
                cdft2d_sub(-1, doubleLargeArray, true);
                rdft2d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealForwardFull(doubleLargeArray);
    }

    public void realForwardFull(double[][] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft2d0_subth1(1, 1, dArr, true);
                cdft2d_subth(-1, dArr, true);
                rdft2d_sub(1, dArr);
            } else {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realForward(dArr[i]);
                }
                cdft2d_sub(-1, dArr, true);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realInverse(double[] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft2d_sub(-1, dArr);
            cdft2d_subth(1, dArr, z);
            xdft2d0_subth1(1, -1, dArr, z);
        } else {
            rdft2d_sub(-1, dArr);
            cdft2d_sub(1, dArr, z);
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realInverse(dArr, this.columns * i, z);
            }
        }
    }

    public void realInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft2d_sub(-1, doubleLargeArray);
            cdft2d_subth(1, doubleLargeArray, z);
            xdft2d0_subth1(1L, -1, doubleLargeArray, z);
        } else {
            rdft2d_sub(-1, doubleLargeArray);
            cdft2d_sub(1, doubleLargeArray, z);
            for (long j = 0; j < this.rowsl; j++) {
                this.fftColumns.realInverse(doubleLargeArray, this.columnsl * j, z);
            }
        }
    }

    public void realInverse(double[][] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft2d_sub(-1, dArr);
            cdft2d_subth(1, dArr, z);
            xdft2d0_subth1(1, -1, dArr, z);
        } else {
            rdft2d_sub(-1, dArr);
            cdft2d_sub(1, dArr, z);
            for (int i = 0; i < this.rows; i++) {
                this.fftColumns.realInverse(dArr[i], z);
            }
        }
    }

    public void realInverseFull(double[] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft2d0_subth2(1, -1, dArr, z);
                cdft2d_subth(1, dArr, z);
                rdft2d_sub(1, dArr);
            } else {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realInverse2(dArr, this.columns * i, z);
                }
                cdft2d_sub(1, dArr, z);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    public void realInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft2d0_subth2(1L, -1, doubleLargeArray, z);
                cdft2d_subth(1, doubleLargeArray, z);
                rdft2d_sub(1, doubleLargeArray);
            } else {
                for (long j = 0; j < this.rowsl; j++) {
                    this.fftColumns.realInverse2(doubleLargeArray, this.columnsl * j, z);
                }
                cdft2d_sub(1, doubleLargeArray, z);
                rdft2d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealInverseFull(doubleLargeArray, z);
    }

    public void realInverseFull(double[][] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft2d0_subth2(1, -1, dArr, z);
                cdft2d_subth(1, dArr, z);
                rdft2d_sub(1, dArr);
            } else {
                for (int i = 0; i < this.rows; i++) {
                    this.fftColumns.realInverse2(dArr[i], 0, z);
                }
                cdft2d_sub(1, dArr, z);
                rdft2d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    private void mixedRadixRealForwardFull(final double[][] dArr) {
        int i;
        int i2;
        int i3 = this.columns / 2;
        final int i4 = i3 + 1;
        final double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i4, this.rows * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i5 = 1;
        if (numberOfThreads > 1 && this.useThreads && (i = this.rows) >= numberOfThreads && i3 - 1 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i6 = i / numberOfThreads;
            int i7 = 0;
            while (i7 < numberOfThreads) {
                final int i8 = i7 * i6;
                final int i9 = i7 == numberOfThreads + (-1) ? this.rows : i8 + i6;
                futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.13
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i8; i10 < i9; i10++) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr[i10]);
                        }
                    }
                });
                i7++;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            for (int i10 = 0; i10 < this.rows; i10++) {
                dArr2[0][i10] = dArr[i10][0];
            }
            this.fftRows.realForwardFull(dArr2[0]);
            int i11 = i2 / numberOfThreads;
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = (i12 * i11) + 1;
                int i14 = i12;
                final int i15 = i12 == numberOfThreads + (-1) ? i3 : i13 + i11;
                Future[] futureArr2 = futureArr;
                futureArr2[i14] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.14
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i16 = i13; i16 < i15; i16++) {
                            int i17 = i16 * 2;
                            for (int i18 = 0; i18 < DoubleFFT_2D.this.rows; i18++) {
                                int i19 = i18 * 2;
                                double[] dArr3 = dArr2[i16];
                                double[] dArr4 = dArr[i18];
                                dArr3[i19] = dArr4[i17];
                                dArr3[i19 + 1] = dArr4[i17 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2[i16]);
                        }
                    }
                });
                i12 = i14 + 1;
                str = str;
                futureArr = futureArr2;
            }
            String str2 = str;
            Future[] futureArr3 = futureArr;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            if (this.columns % 2 == 0) {
                for (int i16 = 0; i16 < this.rows; i16++) {
                    dArr2[i3][i16] = dArr[i16][1];
                }
                this.fftRows.realForwardFull(dArr2[i3]);
            } else {
                for (int i17 = 0; i17 < this.rows; i17++) {
                    int i18 = i17 * 2;
                    double[] dArr3 = dArr2[i3];
                    double[] dArr4 = dArr[i17];
                    dArr3[i18] = dArr4[i3 * 2];
                    dArr3[i18 + 1] = dArr4[1];
                }
                this.fftRows.complexForward(dArr2[i3]);
            }
            int i19 = this.rows / numberOfThreads;
            int i20 = 0;
            while (i20 < numberOfThreads) {
                final int i21 = i20 * i19;
                final int i22 = i20 == numberOfThreads + (-1) ? this.rows : i21 + i19;
                futureArr3[i20] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.15
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i23 = i21; i23 < i22; i23++) {
                            int i24 = i23 * 2;
                            for (int i25 = 0; i25 < i4; i25++) {
                                int i26 = i25 * 2;
                                double[] dArr5 = dArr[i23];
                                double[] dArr6 = dArr2[i25];
                                dArr5[i26] = dArr6[i24];
                                dArr5[i26 + 1] = dArr6[i24 + 1];
                            }
                        }
                    }
                });
                i20++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i23 = 0;
            while (i23 < numberOfThreads) {
                final int i24 = (i23 * i19) + 1;
                final int i25 = i23 == numberOfThreads + (-1) ? this.rows : i24 + i19;
                futureArr3[i23] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.16
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i26 = i24; i26 < i25; i26++) {
                            int i27 = DoubleFFT_2D.this.rows - i26;
                            for (int i28 = i4; i28 < DoubleFFT_2D.this.columns; i28++) {
                                int i29 = i28 * 2;
                                int i30 = (DoubleFFT_2D.this.columns - i28) * 2;
                                double[][] dArr5 = dArr;
                                double[] dArr6 = dArr5[0];
                                dArr6[i29] = dArr6[i30];
                                int i31 = i29 + 1;
                                int i32 = i30 + 1;
                                dArr6[i31] = -dArr6[i32];
                                double[] dArr7 = dArr5[i26];
                                double[] dArr8 = dArr5[i27];
                                dArr7[i29] = dArr8[i30];
                                dArr7[i31] = -dArr8[i32];
                            }
                        }
                    }
                });
                i23++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                return;
            }
        }
        for (int i26 = 0; i26 < this.rows; i26++) {
            this.fftColumns.realForward(dArr[i26]);
        }
        for (int i27 = 0; i27 < this.rows; i27++) {
            dArr2[0][i27] = dArr[i27][0];
        }
        this.fftRows.realForwardFull(dArr2[0]);
        for (int i28 = 1; i28 < i3; i28++) {
            int i29 = i28 * 2;
            for (int i30 = 0; i30 < this.rows; i30++) {
                int i31 = i30 * 2;
                double[] dArr5 = dArr2[i28];
                double[] dArr6 = dArr[i30];
                dArr5[i31] = dArr6[i29];
                dArr5[i31 + 1] = dArr6[i29 + 1];
            }
            this.fftRows.complexForward(dArr2[i28]);
        }
        if (this.columns % 2 == 0) {
            for (int i32 = 0; i32 < this.rows; i32++) {
                dArr2[i3][i32] = dArr[i32][1];
            }
            this.fftRows.realForwardFull(dArr2[i3]);
        } else {
            for (int i33 = 0; i33 < this.rows; i33++) {
                int i34 = i33 * 2;
                double[] dArr7 = dArr2[i3];
                double[] dArr8 = dArr[i33];
                dArr7[i34] = dArr8[i3 * 2];
                dArr7[i34 + 1] = dArr8[1];
            }
            this.fftRows.complexForward(dArr2[i3]);
        }
        for (int i35 = 0; i35 < this.rows; i35++) {
            int i36 = i35 * 2;
            for (int i37 = 0; i37 < i4; i37++) {
                int i38 = i37 * 2;
                double[] dArr9 = dArr[i35];
                double[] dArr10 = dArr2[i37];
                dArr9[i38] = dArr10[i36];
                dArr9[i38 + 1] = dArr10[i36 + 1];
            }
        }
        while (true) {
            int i39 = this.rows;
            if (i5 >= i39) {
                return;
            }
            int i40 = i39 - i5;
            int i41 = i4;
            while (true) {
                int i42 = this.columns;
                if (i41 < i42) {
                    int i43 = i41 * 2;
                    int i44 = (i42 - i41) * 2;
                    double[] dArr11 = dArr[0];
                    dArr11[i43] = dArr11[i44];
                    int i45 = i43 + 1;
                    int i46 = i44 + 1;
                    dArr11[i45] = -dArr11[i46];
                    double[] dArr12 = dArr[i5];
                    double[] dArr13 = dArr[i40];
                    dArr12[i43] = dArr13[i44];
                    dArr12[i45] = -dArr13[i46];
                    i41++;
                }
            }
            i5++;
        }
    }

    private void mixedRadixRealForwardFull(final double[] dArr) {
        int i;
        int i2;
        int i3 = this.columns;
        final int i4 = i3 * 2;
        int i5 = i3 / 2;
        final int i6 = i5 + 1;
        final double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i6, this.rows * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && (i = this.rows) >= numberOfThreads && i5 - 1 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i7 = i / numberOfThreads;
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i7;
                final int i10 = i8 == numberOfThreads + (-1) ? this.rows : i9 + i7;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.17
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i11 = i9; i11 < i10; i11++) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr, DoubleFFT_2D.this.columns * i11);
                        }
                    }
                });
                i8++;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            for (int i11 = 0; i11 < this.rows; i11++) {
                dArr2[0][i11] = dArr[this.columns * i11];
            }
            this.fftRows.realForwardFull(dArr2[0]);
            int i12 = i2 / numberOfThreads;
            int i13 = 0;
            while (i13 < numberOfThreads) {
                final int i14 = (i13 * i12) + 1;
                int i15 = i13;
                final int i16 = i13 == numberOfThreads + (-1) ? i5 : i14 + i12;
                Future[] futureArr2 = futureArr;
                futureArr2[i15] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.18
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i17 = i14; i17 < i16; i17++) {
                            int i18 = i17 * 2;
                            for (int i19 = 0; i19 < DoubleFFT_2D.this.rows; i19++) {
                                int i20 = i19 * 2;
                                int i21 = (DoubleFFT_2D.this.columns * i19) + i18;
                                double[] dArr3 = dArr2[i17];
                                double[] dArr4 = dArr;
                                dArr3[i20] = dArr4[i21];
                                dArr3[i20 + 1] = dArr4[i21 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2[i17]);
                        }
                    }
                });
                i13 = i15 + 1;
                str = str;
                futureArr = futureArr2;
            }
            String str2 = str;
            Future[] futureArr3 = futureArr;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            if (this.columns % 2 == 0) {
                for (int i17 = 0; i17 < this.rows; i17++) {
                    dArr2[i5][i17] = dArr[(this.columns * i17) + 1];
                }
                this.fftRows.realForwardFull(dArr2[i5]);
            } else {
                for (int i18 = 0; i18 < this.rows; i18++) {
                    int i19 = i18 * 2;
                    int i20 = this.columns * i18;
                    double[] dArr3 = dArr2[i5];
                    dArr3[i19] = dArr[(i5 * 2) + i20];
                    dArr3[i19 + 1] = dArr[i20 + 1];
                }
                this.fftRows.complexForward(dArr2[i5]);
            }
            int i21 = this.rows / numberOfThreads;
            int i22 = 0;
            while (i22 < numberOfThreads) {
                final int i23 = i22 * i21;
                final int i24 = i22 == numberOfThreads + (-1) ? this.rows : i23 + i21;
                futureArr3[i22] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.19
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i25 = i23; i25 < i24; i25++) {
                            int i26 = i25 * 2;
                            for (int i27 = 0; i27 < i6; i27++) {
                                int i28 = (i4 * i25) + (i27 * 2);
                                double[] dArr4 = dArr;
                                double[] dArr5 = dArr2[i27];
                                dArr4[i28] = dArr5[i26];
                                dArr4[i28 + 1] = dArr5[i26 + 1];
                            }
                        }
                    }
                });
                i22++;
                str2 = str2;
            }
            String str3 = str2;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str3, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str3, (Throwable) e6);
            }
            int i25 = 0;
            while (i25 < numberOfThreads) {
                final int i26 = (i25 * i21) + 1;
                final int i27 = i25 == numberOfThreads + (-1) ? this.rows : i26 + i21;
                futureArr3[i25] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.20
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i28 = i26; i28 < i27; i28++) {
                            int i29 = i4 * i28;
                            int i30 = ((DoubleFFT_2D.this.rows - i28) + 1) * i4;
                            for (int i31 = i6; i31 < DoubleFFT_2D.this.columns; i31++) {
                                int i32 = i31 * 2;
                                int i33 = (DoubleFFT_2D.this.columns - i31) * 2;
                                double[] dArr4 = dArr;
                                dArr4[i32] = dArr4[i33];
                                dArr4[i32 + 1] = -dArr4[i33 + 1];
                                int i34 = i29 + i32;
                                int i35 = i30 - i32;
                                dArr4[i34] = dArr4[i35];
                                dArr4[i34 + 1] = -dArr4[i35 + 1];
                            }
                        }
                    }
                });
                i25++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str3, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str3, (Throwable) e8);
                return;
            }
        }
        for (int i28 = 0; i28 < this.rows; i28++) {
            this.fftColumns.realForward(dArr, this.columns * i28);
        }
        for (int i29 = 0; i29 < this.rows; i29++) {
            dArr2[0][i29] = dArr[this.columns * i29];
        }
        this.fftRows.realForwardFull(dArr2[0]);
        for (int i30 = 1; i30 < i5; i30++) {
            int i31 = i30 * 2;
            for (int i32 = 0; i32 < this.rows; i32++) {
                int i33 = i32 * 2;
                int i34 = (this.columns * i32) + i31;
                double[] dArr4 = dArr2[i30];
                dArr4[i33] = dArr[i34];
                dArr4[i33 + 1] = dArr[i34 + 1];
            }
            this.fftRows.complexForward(dArr2[i30]);
        }
        if (this.columns % 2 == 0) {
            for (int i35 = 0; i35 < this.rows; i35++) {
                dArr2[i5][i35] = dArr[(this.columns * i35) + 1];
            }
            this.fftRows.realForwardFull(dArr2[i5]);
        } else {
            for (int i36 = 0; i36 < this.rows; i36++) {
                int i37 = i36 * 2;
                int i38 = this.columns * i36;
                double[] dArr5 = dArr2[i5];
                dArr5[i37] = dArr[(i5 * 2) + i38];
                dArr5[i37 + 1] = dArr[i38 + 1];
            }
            this.fftRows.complexForward(dArr2[i5]);
        }
        for (int i39 = 0; i39 < this.rows; i39++) {
            int i40 = i39 * 2;
            for (int i41 = 0; i41 < i6; i41++) {
                int i42 = (i39 * i4) + (i41 * 2);
                double[] dArr6 = dArr2[i41];
                dArr[i42] = dArr6[i40];
                dArr[i42 + 1] = dArr6[i40 + 1];
            }
        }
        int i43 = 1;
        while (true) {
            int i44 = this.rows;
            if (i43 >= i44) {
                return;
            }
            int i45 = i43 * i4;
            int i46 = ((i44 - i43) + 1) * i4;
            int i47 = i6;
            while (true) {
                int i48 = this.columns;
                if (i47 < i48) {
                    int i49 = i47 * 2;
                    int i50 = (i48 - i47) * 2;
                    dArr[i49] = dArr[i50];
                    dArr[i49 + 1] = -dArr[i50 + 1];
                    int i51 = i45 + i49;
                    int i52 = i46 - i49;
                    dArr[i51] = dArr[i52];
                    dArr[i51 + 1] = -dArr[i52 + 1];
                    i47++;
                }
            }
            i43++;
        }
    }

    private void mixedRadixRealForwardFull(final DoubleLargeArray doubleLargeArray) {
        DoubleFFT_2D doubleFFT_2D = this;
        long j = doubleFFT_2D.columnsl;
        final long j2 = j * 2;
        long j3 = j / 2;
        final long j4 = j3 + 1;
        final DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j4 * 2 * doubleFFT_2D.rowsl);
        final long j5 = doubleFFT_2D.rowsl * 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j6 = 0;
        if (numberOfThreads > 1 && doubleFFT_2D.useThreads) {
            long j7 = doubleFFT_2D.rowsl;
            long j8 = numberOfThreads;
            if (j7 >= j8) {
                long j9 = j3 - 1;
                if (j9 >= j8) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j10 = j7 / j8;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j11 = i * j10;
                        final long j12 = i == numberOfThreads + (-1) ? doubleFFT_2D.rowsl : j11 + j10;
                        Future[] futureArr2 = futureArr;
                        futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.21
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j13 = j11; j13 < j12; j13++) {
                                    DoubleFFT_2D.this.fftColumns.realForward(doubleLargeArray, DoubleFFT_2D.this.columnsl * j13);
                                }
                            }
                        });
                        i++;
                        futureArr = futureArr2;
                    }
                    Future[] futureArr3 = futureArr;
                    String str = null;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                    }
                    for (long j13 = 0; j13 < doubleFFT_2D.rowsl; j13++) {
                        doubleLargeArray2.setDouble(j13, doubleLargeArray.getDouble(doubleFFT_2D.columnsl * j13));
                    }
                    doubleFFT_2D.fftRows.realForwardFull(doubleLargeArray2);
                    long j14 = j9 / j8;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j15 = (i2 * j14) + 1;
                        final long j16 = i2 == numberOfThreads + (-1) ? j3 : j15 + j14;
                        futureArr3[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.22
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j17 = j15; j17 < j16; j17++) {
                                    long j18 = 2;
                                    long j19 = j17 * 2;
                                    long j20 = 0;
                                    while (j20 < DoubleFFT_2D.this.rowsl) {
                                        long j21 = j20 * j18;
                                        long j22 = (DoubleFFT_2D.this.columnsl * j20) + j19;
                                        doubleLargeArray2.setDouble((j5 * j17) + j21, doubleLargeArray.getDouble(j22));
                                        doubleLargeArray2.setDouble((j5 * j17) + j21 + 1, doubleLargeArray.getDouble(j22 + 1));
                                        j20++;
                                        j18 = 2;
                                    }
                                    DoubleFFT_2D.this.fftRows.complexForward(doubleLargeArray2, j5 * j17);
                                }
                            }
                        });
                        i2++;
                        str = str;
                        numberOfThreads = numberOfThreads;
                        j8 = j8;
                    }
                    String str2 = str;
                    long j17 = j8;
                    int i3 = numberOfThreads;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e3) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                    } catch (ExecutionException e4) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                    }
                    if (doubleFFT_2D.columnsl % 2 == 0) {
                        while (j6 < doubleFFT_2D.rowsl) {
                            doubleLargeArray2.setDouble((j3 * j5) + j6, doubleLargeArray.getDouble((doubleFFT_2D.columnsl * j6) + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.realForwardFull(doubleLargeArray2, j3 * j5);
                    } else {
                        while (j6 < doubleFFT_2D.rowsl) {
                            long j18 = doubleFFT_2D.columnsl * j6;
                            long j19 = (j3 * j5) + (j6 * 2);
                            doubleLargeArray2.setDouble(j19, doubleLargeArray.getDouble((j3 * 2) + j18));
                            doubleLargeArray2.setDouble(j19 + 1, doubleLargeArray.getDouble(j18 + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.complexForward(doubleLargeArray2, j3 * j5);
                    }
                    long j20 = doubleFFT_2D.rowsl / j17;
                    int i4 = 0;
                    while (i4 < i3) {
                        final long j21 = i4 * j20;
                        final long j22 = i4 == i3 + (-1) ? doubleFFT_2D.rowsl : j21 + j20;
                        int i5 = i3;
                        final DoubleLargeArray doubleLargeArray3 = doubleLargeArray2;
                        futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.23
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j23 = j21; j23 < j22; j23++) {
                                    long j24 = 2;
                                    long j25 = j23 * 2;
                                    long j26 = 0;
                                    while (j26 < j4) {
                                        long j27 = (j2 * j23) + (j26 * j24);
                                        doubleLargeArray.setDouble(j27, doubleLargeArray3.getDouble((j5 * j26) + j25));
                                        doubleLargeArray.setDouble(j27 + 1, doubleLargeArray3.getDouble((j5 * j26) + j25 + 1));
                                        j26++;
                                        j24 = 2;
                                    }
                                }
                            }
                        });
                        i4++;
                        str2 = str2;
                        i3 = i5;
                        doubleLargeArray2 = doubleLargeArray2;
                        doubleFFT_2D = this;
                    }
                    String str3 = str2;
                    int i6 = i3;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e5) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str3, (Throwable) e5);
                    } catch (ExecutionException e6) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str3, (Throwable) e6);
                    }
                    int i7 = 0;
                    while (i7 < i6) {
                        final long j23 = (i7 * j20) + 1;
                        String str4 = str3;
                        final long j24 = i7 == i6 + (-1) ? this.rowsl : j23 + j20;
                        futureArr3[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.24
                            @Override // java.lang.Runnable
                            public void run() {
                                long j25 = j23;
                                while (j25 < j24) {
                                    long j26 = j2 * j25;
                                    long j27 = 1;
                                    long j28 = ((DoubleFFT_2D.this.rowsl - j25) + 1) * j2;
                                    long j29 = j4;
                                    while (j29 < DoubleFFT_2D.this.columnsl) {
                                        long j30 = j29 * 2;
                                        long j31 = 2 * (DoubleFFT_2D.this.columnsl - j29);
                                        DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                                        doubleLargeArray4.setDouble(j30, doubleLargeArray4.getDouble(j31));
                                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                                        long j32 = j25;
                                        doubleLargeArray5.setDouble(j30 + 1, -doubleLargeArray5.getDouble(j31 + 1));
                                        long j33 = j26 + j30;
                                        long j34 = j28 - j30;
                                        DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                                        doubleLargeArray6.setDouble(j33, doubleLargeArray6.getDouble(j34));
                                        DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                                        doubleLargeArray7.setDouble(j33 + 1, -doubleLargeArray7.getDouble(j34 + 1));
                                        j29++;
                                        j27 = 1;
                                        j25 = j32;
                                    }
                                    j25 += j27;
                                }
                            }
                        });
                        i7++;
                        str3 = str4;
                    }
                    String str5 = str3;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                        return;
                    } catch (InterruptedException e7) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str5, (Throwable) e7);
                        return;
                    } catch (ExecutionException e8) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str5, (Throwable) e8);
                        return;
                    }
                }
            }
        }
        DoubleFFT_2D doubleFFT_2D2 = doubleFFT_2D;
        for (long j25 = 0; j25 < doubleFFT_2D2.rowsl; j25++) {
            doubleFFT_2D2.fftColumns.realForward(doubleLargeArray, doubleFFT_2D2.columnsl * j25);
        }
        for (long j26 = 0; j26 < doubleFFT_2D2.rowsl; j26++) {
            doubleLargeArray2.setDouble(j26, doubleLargeArray.getDouble(doubleFFT_2D2.columnsl * j26));
        }
        doubleFFT_2D2.fftRows.realForwardFull(doubleLargeArray2);
        for (long j27 = 1; j27 < j3; j27++) {
            long j28 = 2;
            long j29 = j27 * 2;
            long j30 = 0;
            while (j30 < doubleFFT_2D2.rowsl) {
                long j31 = j30 * j28;
                long j32 = (doubleFFT_2D2.columnsl * j30) + j29;
                long j33 = (j27 * j5) + j31;
                doubleLargeArray2.setDouble(j33, doubleLargeArray.getDouble(j32));
                doubleLargeArray2.setDouble(j33 + 1, doubleLargeArray.getDouble(j32 + 1));
                j30++;
                j28 = 2;
            }
            doubleFFT_2D2.fftRows.complexForward(doubleLargeArray2, j27 * j5);
        }
        if (doubleFFT_2D2.columnsl % 2 == 0) {
            for (long j34 = 0; j34 < doubleFFT_2D2.rowsl; j34++) {
                doubleLargeArray2.setDouble((j3 * j5) + j34, doubleLargeArray.getDouble((doubleFFT_2D2.columnsl * j34) + 1));
            }
            doubleFFT_2D2.fftRows.realForwardFull(doubleLargeArray2, j3 * j5);
        } else {
            for (long j35 = 0; j35 < doubleFFT_2D2.rowsl; j35++) {
                long j36 = doubleFFT_2D2.columnsl * j35;
                long j37 = (j3 * j5) + (j35 * 2);
                doubleLargeArray2.setDouble(j37, doubleLargeArray.getDouble((j3 * 2) + j36));
                doubleLargeArray2.setDouble(j37 + 1, doubleLargeArray.getDouble(j36 + 1));
            }
            doubleFFT_2D2.fftRows.complexForward(doubleLargeArray2, j3 * j5);
        }
        for (long j38 = 0; j38 < doubleFFT_2D2.rowsl; j38++) {
            long j39 = 2;
            long j40 = j38 * 2;
            long j41 = 0;
            while (j41 < j4) {
                long j42 = (j38 * j2) + (j41 * j39);
                long j43 = (j41 * j5) + j40;
                doubleLargeArray.setDouble(j42, doubleLargeArray2.getDouble(j43));
                doubleLargeArray.setDouble(j42 + 1, doubleLargeArray2.getDouble(j43 + 1));
                j41++;
                j39 = 2;
            }
        }
        long j44 = 1;
        while (true) {
            long j45 = doubleFFT_2D2.rowsl;
            if (j44 >= j45) {
                return;
            }
            long j46 = j44 * j2;
            long j47 = ((j45 - j44) + 1) * j2;
            long j48 = j4;
            while (true) {
                long j49 = doubleFFT_2D2.columnsl;
                if (j48 < j49) {
                    long j50 = j48 * 2;
                    long j51 = (j49 - j48) * 2;
                    doubleLargeArray.setDouble(j50, doubleLargeArray.getDouble(j51));
                    doubleLargeArray.setDouble(j50 + 1, -doubleLargeArray.getDouble(j51 + 1));
                    long j52 = j46 + j50;
                    long j53 = j47 - j50;
                    doubleLargeArray.setDouble(j52, doubleLargeArray.getDouble(j53));
                    doubleLargeArray.setDouble(j52 + 1, -doubleLargeArray.getDouble(j53 + 1));
                    j48++;
                    doubleFFT_2D2 = this;
                }
            }
            j44++;
            doubleFFT_2D2 = this;
        }
    }

    private void mixedRadixRealInverseFull(final double[][] dArr, final boolean z) {
        int i;
        int i2;
        int i3 = this.columns / 2;
        final int i4 = i3 + 1;
        final double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i4, this.rows * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && (i = this.rows) >= numberOfThreads && i3 - 1 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i5 = i / numberOfThreads;
            int i6 = 0;
            while (i6 < numberOfThreads) {
                final int i7 = i6 * i5;
                final int i8 = i6 == numberOfThreads + (-1) ? this.rows : i7 + i5;
                int i9 = i6;
                Future[] futureArr2 = futureArr;
                futureArr2[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.25
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i7; i10 < i8; i10++) {
                            DoubleFFT_2D.this.fftColumns.realInverse2(dArr[i10], 0, z);
                        }
                    }
                });
                i6 = i9 + 1;
                futureArr = futureArr2;
                i2 = i2;
            }
            Future[] futureArr3 = futureArr;
            int i10 = i2;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            for (int i11 = 0; i11 < this.rows; i11++) {
                dArr2[0][i11] = dArr[i11][0];
            }
            this.fftRows.realInverseFull(dArr2[0], z);
            int i12 = i10 / numberOfThreads;
            int i13 = 0;
            while (i13 < numberOfThreads) {
                final int i14 = (i13 * i12) + 1;
                final int i15 = i13 == numberOfThreads + (-1) ? i3 : i14 + i12;
                int i16 = i13;
                futureArr3[i16] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.26
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i17 = i14; i17 < i15; i17++) {
                            int i18 = i17 * 2;
                            for (int i19 = 0; i19 < DoubleFFT_2D.this.rows; i19++) {
                                int i20 = i19 * 2;
                                double[] dArr3 = dArr2[i17];
                                double[] dArr4 = dArr[i19];
                                dArr3[i20] = dArr4[i18];
                                dArr3[i20 + 1] = dArr4[i18 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2[i17], z);
                        }
                    }
                });
                i13 = i16 + 1;
                str = str;
            }
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            if (this.columns % 2 == 0) {
                for (int i17 = 0; i17 < this.rows; i17++) {
                    dArr2[i3][i17] = dArr[i17][1];
                }
                this.fftRows.realInverseFull(dArr2[i3], z);
            } else {
                for (int i18 = 0; i18 < this.rows; i18++) {
                    int i19 = i18 * 2;
                    double[] dArr3 = dArr2[i3];
                    double[] dArr4 = dArr[i18];
                    dArr3[i19] = dArr4[i3 * 2];
                    dArr3[i19 + 1] = dArr4[1];
                }
                this.fftRows.complexInverse(dArr2[i3], z);
            }
            int i20 = this.rows / numberOfThreads;
            int i21 = 0;
            while (i21 < numberOfThreads) {
                final int i22 = i21 * i20;
                final int i23 = i21 == numberOfThreads + (-1) ? this.rows : i22 + i20;
                futureArr3[i21] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.27
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i24 = i22; i24 < i23; i24++) {
                            int i25 = i24 * 2;
                            for (int i26 = 0; i26 < i4; i26++) {
                                int i27 = i26 * 2;
                                double[] dArr5 = dArr[i24];
                                double[] dArr6 = dArr2[i26];
                                dArr5[i27] = dArr6[i25];
                                dArr5[i27 + 1] = dArr6[i25 + 1];
                            }
                        }
                    }
                });
                i21++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i24 = 0;
            while (i24 < numberOfThreads) {
                final int i25 = (i24 * i20) + 1;
                final int i26 = i24 == numberOfThreads + (-1) ? this.rows : i25 + i20;
                futureArr3[i24] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.28
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i27 = i25; i27 < i26; i27++) {
                            int i28 = DoubleFFT_2D.this.rows - i27;
                            for (int i29 = i4; i29 < DoubleFFT_2D.this.columns; i29++) {
                                int i30 = i29 * 2;
                                int i31 = (DoubleFFT_2D.this.columns - i29) * 2;
                                double[][] dArr5 = dArr;
                                double[] dArr6 = dArr5[0];
                                dArr6[i30] = dArr6[i31];
                                int i32 = i30 + 1;
                                int i33 = i31 + 1;
                                dArr6[i32] = -dArr6[i33];
                                double[] dArr7 = dArr5[i27];
                                double[] dArr8 = dArr5[i28];
                                dArr7[i30] = dArr8[i31];
                                dArr7[i32] = -dArr8[i33];
                            }
                        }
                    }
                });
                i24++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                return;
            }
        }
        for (int i27 = 0; i27 < this.rows; i27++) {
            this.fftColumns.realInverse2(dArr[i27], 0, z);
        }
        for (int i28 = 0; i28 < this.rows; i28++) {
            dArr2[0][i28] = dArr[i28][0];
        }
        this.fftRows.realInverseFull(dArr2[0], z);
        for (int i29 = 1; i29 < i3; i29++) {
            int i30 = i29 * 2;
            for (int i31 = 0; i31 < this.rows; i31++) {
                int i32 = i31 * 2;
                double[] dArr5 = dArr2[i29];
                double[] dArr6 = dArr[i31];
                dArr5[i32] = dArr6[i30];
                dArr5[i32 + 1] = dArr6[i30 + 1];
            }
            this.fftRows.complexInverse(dArr2[i29], z);
        }
        if (this.columns % 2 == 0) {
            for (int i33 = 0; i33 < this.rows; i33++) {
                dArr2[i3][i33] = dArr[i33][1];
            }
            this.fftRows.realInverseFull(dArr2[i3], z);
        } else {
            for (int i34 = 0; i34 < this.rows; i34++) {
                int i35 = i34 * 2;
                double[] dArr7 = dArr2[i3];
                double[] dArr8 = dArr[i34];
                dArr7[i35] = dArr8[i3 * 2];
                dArr7[i35 + 1] = dArr8[1];
            }
            this.fftRows.complexInverse(dArr2[i3], z);
        }
        for (int i36 = 0; i36 < this.rows; i36++) {
            int i37 = i36 * 2;
            for (int i38 = 0; i38 < i4; i38++) {
                int i39 = i38 * 2;
                double[] dArr9 = dArr[i36];
                double[] dArr10 = dArr2[i38];
                dArr9[i39] = dArr10[i37];
                dArr9[i39 + 1] = dArr10[i37 + 1];
            }
        }
        int i40 = 1;
        while (true) {
            int i41 = this.rows;
            if (i40 >= i41) {
                return;
            }
            int i42 = i41 - i40;
            int i43 = i4;
            while (true) {
                int i44 = this.columns;
                if (i43 < i44) {
                    int i45 = i43 * 2;
                    int i46 = (i44 - i43) * 2;
                    double[] dArr11 = dArr[0];
                    dArr11[i45] = dArr11[i46];
                    int i47 = i45 + 1;
                    int i48 = i46 + 1;
                    dArr11[i47] = -dArr11[i48];
                    double[] dArr12 = dArr[i40];
                    double[] dArr13 = dArr[i42];
                    dArr12[i45] = dArr13[i46];
                    dArr12[i47] = -dArr13[i48];
                    i43++;
                }
            }
            i40++;
        }
    }

    private void mixedRadixRealInverseFull(final double[] dArr, final boolean z) {
        int i;
        int i2;
        int i3 = this.columns;
        int i4 = i3 * 2;
        int i5 = i3 / 2;
        final int i6 = i5 + 1;
        final double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i6, this.rows * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && (i = this.rows) >= numberOfThreads && i5 - 1 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i7 = i / numberOfThreads;
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i7;
                final int i10 = i8 == numberOfThreads + (-1) ? this.rows : i9 + i7;
                int i11 = i8;
                Future[] futureArr2 = futureArr;
                futureArr2[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.29
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i9; i12 < i10; i12++) {
                            DoubleFFT_2D.this.fftColumns.realInverse2(dArr, DoubleFFT_2D.this.columns * i12, z);
                        }
                    }
                });
                i8 = i11 + 1;
                futureArr = futureArr2;
                i2 = i2;
            }
            Future[] futureArr3 = futureArr;
            int i12 = i2;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            for (int i13 = 0; i13 < this.rows; i13++) {
                dArr2[0][i13] = dArr[this.columns * i13];
            }
            this.fftRows.realInverseFull(dArr2[0], z);
            int i14 = i12 / numberOfThreads;
            int i15 = 0;
            while (i15 < numberOfThreads) {
                final int i16 = (i15 * i14) + 1;
                final int i17 = i15 == numberOfThreads + (-1) ? i5 : i16 + i14;
                int i18 = i15;
                futureArr3[i18] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.30
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i19 = i16; i19 < i17; i19++) {
                            int i20 = i19 * 2;
                            for (int i21 = 0; i21 < DoubleFFT_2D.this.rows; i21++) {
                                int i22 = i21 * 2;
                                int i23 = (DoubleFFT_2D.this.columns * i21) + i20;
                                double[] dArr3 = dArr2[i19];
                                double[] dArr4 = dArr;
                                dArr3[i22] = dArr4[i23];
                                dArr3[i22 + 1] = dArr4[i23 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2[i19], z);
                        }
                    }
                });
                i15 = i18 + 1;
                str = str;
                i4 = i4;
                numberOfThreads = numberOfThreads;
            }
            int i19 = numberOfThreads;
            final int i20 = i4;
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            if (this.columns % 2 == 0) {
                for (int i21 = 0; i21 < this.rows; i21++) {
                    dArr2[i5][i21] = dArr[(this.columns * i21) + 1];
                }
                this.fftRows.realInverseFull(dArr2[i5], z);
            } else {
                for (int i22 = 0; i22 < this.rows; i22++) {
                    int i23 = i22 * 2;
                    int i24 = this.columns * i22;
                    double[] dArr3 = dArr2[i5];
                    dArr3[i23] = dArr[(i5 * 2) + i24];
                    dArr3[i23 + 1] = dArr[i24 + 1];
                }
                this.fftRows.complexInverse(dArr2[i5], z);
            }
            int i25 = this.rows / i19;
            int i26 = 0;
            while (i26 < i19) {
                final int i27 = i26 * i25;
                final int i28 = i26 == i19 + (-1) ? this.rows : i27 + i25;
                futureArr3[i26] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.31
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i29 = i27; i29 < i28; i29++) {
                            int i30 = i29 * 2;
                            for (int i31 = 0; i31 < i6; i31++) {
                                int i32 = (i20 * i29) + (i31 * 2);
                                double[] dArr4 = dArr;
                                double[] dArr5 = dArr2[i31];
                                dArr4[i32] = dArr5[i30];
                                dArr4[i32 + 1] = dArr5[i30 + 1];
                            }
                        }
                    }
                });
                i26++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i29 = 0;
            while (i29 < i19) {
                final int i30 = (i29 * i25) + 1;
                final int i31 = i29 == i19 + (-1) ? this.rows : i30 + i25;
                futureArr3[i29] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.32
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i32 = i30; i32 < i31; i32++) {
                            int i33 = i20 * i32;
                            int i34 = ((DoubleFFT_2D.this.rows - i32) + 1) * i20;
                            for (int i35 = i6; i35 < DoubleFFT_2D.this.columns; i35++) {
                                int i36 = i35 * 2;
                                int i37 = (DoubleFFT_2D.this.columns - i35) * 2;
                                double[] dArr4 = dArr;
                                dArr4[i36] = dArr4[i37];
                                dArr4[i36 + 1] = -dArr4[i37 + 1];
                                int i38 = i33 + i36;
                                int i39 = i34 - i36;
                                dArr4[i38] = dArr4[i39];
                                dArr4[i38 + 1] = -dArr4[i39 + 1];
                            }
                        }
                    }
                });
                i29++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                return;
            }
        }
        for (int i32 = 0; i32 < this.rows; i32++) {
            this.fftColumns.realInverse2(dArr, this.columns * i32, z);
        }
        for (int i33 = 0; i33 < this.rows; i33++) {
            dArr2[0][i33] = dArr[this.columns * i33];
        }
        this.fftRows.realInverseFull(dArr2[0], z);
        for (int i34 = 1; i34 < i5; i34++) {
            int i35 = i34 * 2;
            for (int i36 = 0; i36 < this.rows; i36++) {
                int i37 = i36 * 2;
                int i38 = (this.columns * i36) + i35;
                double[] dArr4 = dArr2[i34];
                dArr4[i37] = dArr[i38];
                dArr4[i37 + 1] = dArr[i38 + 1];
            }
            this.fftRows.complexInverse(dArr2[i34], z);
        }
        if (this.columns % 2 == 0) {
            for (int i39 = 0; i39 < this.rows; i39++) {
                dArr2[i5][i39] = dArr[(this.columns * i39) + 1];
            }
            this.fftRows.realInverseFull(dArr2[i5], z);
        } else {
            for (int i40 = 0; i40 < this.rows; i40++) {
                int i41 = i40 * 2;
                int i42 = this.columns * i40;
                double[] dArr5 = dArr2[i5];
                dArr5[i41] = dArr[(i5 * 2) + i42];
                dArr5[i41 + 1] = dArr[i42 + 1];
            }
            this.fftRows.complexInverse(dArr2[i5], z);
        }
        for (int i43 = 0; i43 < this.rows; i43++) {
            int i44 = i43 * 2;
            for (int i45 = 0; i45 < i6; i45++) {
                int i46 = (i43 * i4) + (i45 * 2);
                double[] dArr6 = dArr2[i45];
                dArr[i46] = dArr6[i44];
                dArr[i46 + 1] = dArr6[i44 + 1];
            }
        }
        int i47 = 1;
        while (true) {
            int i48 = this.rows;
            if (i47 >= i48) {
                return;
            }
            int i49 = i47 * i4;
            int i50 = ((i48 - i47) + 1) * i4;
            int i51 = i6;
            while (true) {
                int i52 = this.columns;
                if (i51 < i52) {
                    int i53 = i51 * 2;
                    int i54 = (i52 - i51) * 2;
                    dArr[i53] = dArr[i54];
                    dArr[i53 + 1] = -dArr[i54 + 1];
                    int i55 = i49 + i53;
                    int i56 = i50 - i53;
                    dArr[i55] = dArr[i56];
                    dArr[i55 + 1] = -dArr[i56 + 1];
                    i51++;
                }
            }
            i47++;
        }
    }

    private void mixedRadixRealInverseFull(final DoubleLargeArray doubleLargeArray, final boolean z) {
        DoubleFFT_2D doubleFFT_2D = this;
        long j = doubleFFT_2D.columnsl;
        final long j2 = j * 2;
        long j3 = j / 2;
        final long j4 = j3 + 1;
        final DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j4 * 2 * doubleFFT_2D.rowsl);
        final long j5 = doubleFFT_2D.rowsl * 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long j6 = 0;
        if (numberOfThreads > 1 && doubleFFT_2D.useThreads) {
            long j7 = doubleFFT_2D.rowsl;
            long j8 = numberOfThreads;
            if (j7 >= j8) {
                long j9 = j3 - 1;
                if (j9 >= j8) {
                    Future[] futureArr = new Future[numberOfThreads];
                    long j10 = j7 / j8;
                    int i = 0;
                    while (i < numberOfThreads) {
                        final long j11 = i * j10;
                        final long j12 = i == numberOfThreads + (-1) ? doubleFFT_2D.rowsl : j11 + j10;
                        Future[] futureArr2 = futureArr;
                        futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.33
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j13 = j11; j13 < j12; j13++) {
                                    DoubleFFT_2D.this.fftColumns.realInverse2(doubleLargeArray, DoubleFFT_2D.this.columnsl * j13, z);
                                }
                            }
                        });
                        i++;
                        futureArr = futureArr2;
                    }
                    Future[] futureArr3 = futureArr;
                    String str = null;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                    } catch (ExecutionException e2) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                    }
                    for (long j13 = 0; j13 < doubleFFT_2D.rowsl; j13++) {
                        doubleLargeArray2.setDouble(j13, doubleLargeArray.getDouble(doubleFFT_2D.columnsl * j13));
                    }
                    doubleFFT_2D.fftRows.realInverseFull(doubleLargeArray2, z);
                    long j14 = j9 / j8;
                    int i2 = 0;
                    while (i2 < numberOfThreads) {
                        final long j15 = (i2 * j14) + 1;
                        final long j16 = i2 == numberOfThreads + (-1) ? j3 : j15 + j14;
                        futureArr3[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.34
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j17 = j15; j17 < j16; j17++) {
                                    long j18 = 2;
                                    long j19 = j17 * 2;
                                    long j20 = 0;
                                    while (j20 < DoubleFFT_2D.this.rowsl) {
                                        long j21 = j20 * j18;
                                        long j22 = (DoubleFFT_2D.this.columnsl * j20) + j19;
                                        doubleLargeArray2.setDouble((j5 * j17) + j21, doubleLargeArray.getDouble(j22));
                                        doubleLargeArray2.setDouble((j5 * j17) + j21 + 1, doubleLargeArray.getDouble(j22 + 1));
                                        j20++;
                                        j18 = 2;
                                    }
                                    DoubleFFT_2D.this.fftRows.complexInverse(doubleLargeArray2, j5 * j17, z);
                                }
                            }
                        });
                        i2++;
                        str = str;
                        j8 = j8;
                        numberOfThreads = numberOfThreads;
                    }
                    String str2 = str;
                    long j17 = j8;
                    int i3 = numberOfThreads;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e3) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                    } catch (ExecutionException e4) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                    }
                    if (doubleFFT_2D.columnsl % 2 == 0) {
                        while (j6 < doubleFFT_2D.rowsl) {
                            doubleLargeArray2.setDouble((j3 * j5) + j6, doubleLargeArray.getDouble((doubleFFT_2D.columnsl * j6) + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.realInverseFull(doubleLargeArray2, j3 * j5, z);
                    } else {
                        while (j6 < doubleFFT_2D.rowsl) {
                            long j18 = doubleFFT_2D.columnsl * j6;
                            long j19 = (j3 * j5) + (j6 * 2);
                            doubleLargeArray2.setDouble(j19, doubleLargeArray.getDouble((j3 * 2) + j18));
                            doubleLargeArray2.setDouble(j19 + 1, doubleLargeArray.getDouble(j18 + 1));
                            j6++;
                        }
                        doubleFFT_2D.fftRows.complexInverse(doubleLargeArray2, j3 * j5, z);
                    }
                    long j20 = doubleFFT_2D.rowsl / j17;
                    int i4 = i3;
                    int i5 = 0;
                    while (i5 < i4) {
                        final long j21 = i5 * j20;
                        final long j22 = i5 == i4 + (-1) ? doubleFFT_2D.rowsl : j21 + j20;
                        String str3 = str2;
                        final DoubleLargeArray doubleLargeArray3 = doubleLargeArray2;
                        futureArr3[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.35
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j23 = j21; j23 < j22; j23++) {
                                    long j24 = 2;
                                    long j25 = j23 * 2;
                                    long j26 = 0;
                                    while (j26 < j4) {
                                        long j27 = (j2 * j23) + (j26 * j24);
                                        doubleLargeArray.setDouble(j27, doubleLargeArray3.getDouble((j5 * j26) + j25));
                                        doubleLargeArray.setDouble(j27 + 1, doubleLargeArray3.getDouble((j5 * j26) + j25 + 1));
                                        j26++;
                                        j24 = 2;
                                    }
                                }
                            }
                        });
                        i5++;
                        str2 = str3;
                        i4 = i4;
                        doubleLargeArray2 = doubleLargeArray2;
                        doubleFFT_2D = this;
                    }
                    int i6 = i4;
                    String str4 = str2;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                    } catch (InterruptedException e5) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str4, (Throwable) e5);
                    } catch (ExecutionException e6) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str4, (Throwable) e6);
                    }
                    int i7 = i6;
                    int i8 = 0;
                    while (i8 < i7) {
                        final long j23 = (i8 * j20) + 1;
                        int i9 = i7;
                        final long j24 = i8 == i7 + (-1) ? this.rowsl : j23 + j20;
                        futureArr3[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.36
                            @Override // java.lang.Runnable
                            public void run() {
                                long j25 = j23;
                                while (j25 < j24) {
                                    long j26 = j2 * j25;
                                    long j27 = 1;
                                    long j28 = ((DoubleFFT_2D.this.rowsl - j25) + 1) * j2;
                                    long j29 = j4;
                                    while (j29 < DoubleFFT_2D.this.columnsl) {
                                        long j30 = j29 * 2;
                                        long j31 = 2 * (DoubleFFT_2D.this.columnsl - j29);
                                        DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                                        doubleLargeArray4.setDouble(j30, doubleLargeArray4.getDouble(j31));
                                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                                        long j32 = j25;
                                        doubleLargeArray5.setDouble(j30 + 1, -doubleLargeArray5.getDouble(j31 + 1));
                                        long j33 = j26 + j30;
                                        long j34 = j28 - j30;
                                        DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                                        doubleLargeArray6.setDouble(j33, doubleLargeArray6.getDouble(j34));
                                        DoubleLargeArray doubleLargeArray7 = doubleLargeArray;
                                        doubleLargeArray7.setDouble(j33 + 1, -doubleLargeArray7.getDouble(j34 + 1));
                                        j29++;
                                        j27 = 1;
                                        j25 = j32;
                                    }
                                    j25 += j27;
                                }
                            }
                        });
                        i8++;
                        i7 = i9;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr3);
                        return;
                    } catch (InterruptedException e7) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str4, (Throwable) e7);
                        return;
                    } catch (ExecutionException e8) {
                        Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, str4, (Throwable) e8);
                        return;
                    }
                }
            }
        }
        DoubleFFT_2D doubleFFT_2D2 = doubleFFT_2D;
        for (long j25 = 0; j25 < doubleFFT_2D2.rowsl; j25++) {
            doubleFFT_2D2.fftColumns.realInverse2(doubleLargeArray, doubleFFT_2D2.columnsl * j25, z);
        }
        for (long j26 = 0; j26 < doubleFFT_2D2.rowsl; j26++) {
            doubleLargeArray2.setDouble(j26, doubleLargeArray.getDouble(doubleFFT_2D2.columnsl * j26));
        }
        doubleFFT_2D2.fftRows.realInverseFull(doubleLargeArray2, z);
        for (long j27 = 1; j27 < j3; j27++) {
            long j28 = j27 * 2;
            long j29 = 0;
            while (j29 < doubleFFT_2D2.rowsl) {
                long j30 = (doubleFFT_2D2.columnsl * j29) + j28;
                long j31 = (j29 * 2) + (j27 * j5);
                doubleLargeArray2.setDouble(j31, doubleLargeArray.getDouble(j30));
                doubleLargeArray2.setDouble(j31 + 1, doubleLargeArray.getDouble(j30 + 1));
                j29++;
                j28 = j28;
            }
            doubleFFT_2D2.fftRows.complexInverse(doubleLargeArray2, j27 * j5, z);
        }
        if (doubleFFT_2D2.columnsl % 2 == 0) {
            for (long j32 = 0; j32 < doubleFFT_2D2.rowsl; j32++) {
                doubleLargeArray2.setDouble((j3 * j5) + j32, doubleLargeArray.getDouble((doubleFFT_2D2.columnsl * j32) + 1));
            }
            doubleFFT_2D2.fftRows.realInverseFull(doubleLargeArray2, j3 * j5, z);
        } else {
            for (long j33 = 0; j33 < doubleFFT_2D2.rowsl; j33++) {
                long j34 = doubleFFT_2D2.columnsl * j33;
                long j35 = (j3 * j5) + (j33 * 2);
                doubleLargeArray2.setDouble(j35, doubleLargeArray.getDouble((j3 * 2) + j34));
                doubleLargeArray2.setDouble(j35 + 1, doubleLargeArray.getDouble(j34 + 1));
            }
            doubleFFT_2D2.fftRows.complexInverse(doubleLargeArray2, j3 * j5, z);
        }
        for (long j36 = 0; j36 < doubleFFT_2D2.rowsl; j36++) {
            long j37 = j36 * 2;
            long j38 = 0;
            while (j38 < j4) {
                long j39 = (j36 * j2) + (j38 * 2);
                long j40 = (j38 * j5) + j37;
                doubleLargeArray.setDouble(j39, doubleLargeArray2.getDouble(j40));
                doubleLargeArray.setDouble(j39 + 1, doubleLargeArray2.getDouble(j40 + 1));
                j38++;
                j37 = j37;
            }
        }
        long j41 = 1;
        while (true) {
            long j42 = doubleFFT_2D2.rowsl;
            if (j41 >= j42) {
                return;
            }
            long j43 = j41 * j2;
            long j44 = ((j42 - j41) + 1) * j2;
            long j45 = j4;
            while (true) {
                long j46 = doubleFFT_2D2.columnsl;
                if (j45 < j46) {
                    long j47 = j45 * 2;
                    long j48 = (j46 - j45) * 2;
                    doubleLargeArray.setDouble(j47, doubleLargeArray.getDouble(j48));
                    doubleLargeArray.setDouble(j47 + 1, -doubleLargeArray.getDouble(j48 + 1));
                    long j49 = j43 + j47;
                    long j50 = j44 - j47;
                    doubleLargeArray.setDouble(j49, doubleLargeArray.getDouble(j50));
                    doubleLargeArray.setDouble(j49 + 1, -doubleLargeArray.getDouble(j50 + 1));
                    j45++;
                    doubleFFT_2D2 = this;
                }
            }
            j41++;
            doubleFFT_2D2 = this;
        }
    }

    private void rdft2d_sub(int i, double[] dArr) {
        int i2 = this.rows >> 1;
        if (i >= 0) {
            for (int i3 = 1; i3 < i2; i3++) {
                int i4 = this.rows - i3;
                int i5 = this.columns;
                int i6 = i3 * i5;
                int i7 = i4 * i5;
                double d = (dArr[i6] - dArr[i7]) * 0.5d;
                dArr[i7] = d;
                dArr[i6] = dArr[i6] - d;
                int i8 = i7 + 1;
                int i9 = i6 + 1;
                double d2 = (dArr[i9] + dArr[i8]) * 0.5d;
                dArr[i8] = d2;
                dArr[i9] = dArr[i9] - d2;
            }
            return;
        }
        for (int i10 = 1; i10 < i2; i10++) {
            int i11 = this.rows - i10;
            int i12 = this.columns;
            int i13 = i10 * i12;
            int i14 = i11 * i12;
            double d3 = dArr[i13];
            double d4 = dArr[i14];
            dArr[i13] = d3 + d4;
            dArr[i14] = d3 - d4;
            int i15 = i14 + 1;
            double d5 = dArr[i15];
            int i16 = i13 + 1;
            double d6 = dArr[i16];
            dArr[i16] = d6 + d5;
            dArr[i15] = d5 - d6;
        }
    }

    private void rdft2d_sub(int i, DoubleLargeArray doubleLargeArray) {
        long j = this.rowsl >> 1;
        if (i >= 0) {
            for (long j2 = 1; j2 < j; j2++) {
                long j3 = this.rowsl - j2;
                long j4 = this.columnsl;
                long j5 = j2 * j4;
                long j6 = j3 * j4;
                doubleLargeArray.setDouble(j6, (doubleLargeArray.getDouble(j5) - doubleLargeArray.getDouble(j6)) * 0.5d);
                doubleLargeArray.setDouble(j5, doubleLargeArray.getDouble(j5) - doubleLargeArray.getDouble(j6));
                long j7 = j6 + 1;
                long j8 = j5 + 1;
                doubleLargeArray.setDouble(j7, (doubleLargeArray.getDouble(j8) + doubleLargeArray.getDouble(j7)) * 0.5d);
                doubleLargeArray.setDouble(j8, doubleLargeArray.getDouble(j8) - doubleLargeArray.getDouble(j7));
            }
            return;
        }
        for (long j9 = 1; j9 < j; j9++) {
            long j10 = this.rowsl - j9;
            long j11 = this.columnsl;
            long j12 = j9 * j11;
            long j13 = j10 * j11;
            double d = doubleLargeArray.getDouble(j12) - doubleLargeArray.getDouble(j13);
            doubleLargeArray.setDouble(j12, doubleLargeArray.getDouble(j12) + doubleLargeArray.getDouble(j13));
            doubleLargeArray.setDouble(j13, d);
            long j14 = j13 + 1;
            long j15 = j12 + 1;
            double d2 = doubleLargeArray.getDouble(j14) - doubleLargeArray.getDouble(j15);
            doubleLargeArray.setDouble(j15, doubleLargeArray.getDouble(j15) + doubleLargeArray.getDouble(j14));
            doubleLargeArray.setDouble(j14, d2);
        }
    }

    private void rdft2d_sub(int i, double[][] dArr) {
        int i2 = this.rows >> 1;
        if (i >= 0) {
            for (int i3 = 1; i3 < i2; i3++) {
                double[] dArr2 = dArr[this.rows - i3];
                double[] dArr3 = dArr[i3];
                double d = (dArr3[0] - dArr2[0]) * 0.5d;
                dArr2[0] = d;
                dArr3[0] = dArr3[0] - d;
                double d2 = (dArr3[1] + dArr2[1]) * 0.5d;
                dArr2[1] = d2;
                dArr3[1] = dArr3[1] - d2;
            }
            return;
        }
        for (int i4 = 1; i4 < i2; i4++) {
            int i5 = this.rows - i4;
            double[] dArr4 = dArr[i4];
            double d3 = dArr4[0];
            double[] dArr5 = dArr[i5];
            double d4 = dArr5[0];
            dArr4[0] = d3 + d4;
            dArr5[0] = d3 - d4;
            double d5 = dArr5[1];
            double d6 = dArr4[1];
            dArr4[1] = d6 + d5;
            dArr5[1] = d5 - d6;
        }
    }

    private void cdft2d_sub(int i, double[] dArr, boolean z) {
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        int i4 = 0;
        if (i == -1) {
            if (i3 > 4) {
                for (int i5 = 0; i5 < this.columns; i5 += 8) {
                    int i6 = 0;
                    while (true) {
                        int i7 = this.rows;
                        if (i6 >= i7) {
                            break;
                        }
                        int i8 = (this.columns * i6) + i5;
                        int i9 = i6 * 2;
                        int i10 = (i7 * 2) + i9;
                        int i11 = (i7 * 2) + i10;
                        int i12 = (i7 * 2) + i11;
                        dArr2[i9] = dArr[i8];
                        dArr2[i9 + 1] = dArr[i8 + 1];
                        dArr2[i10] = dArr[i8 + 2];
                        dArr2[i10 + 1] = dArr[i8 + 3];
                        dArr2[i11] = dArr[i8 + 4];
                        dArr2[i11 + 1] = dArr[i8 + 5];
                        dArr2[i12] = dArr[i8 + 6];
                        dArr2[i12 + 1] = dArr[i8 + 7];
                        i6++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    this.fftRows.complexForward(dArr2, this.rows * 4);
                    this.fftRows.complexForward(dArr2, this.rows * 6);
                    int i13 = 0;
                    while (true) {
                        int i14 = this.rows;
                        if (i13 < i14) {
                            int i15 = (this.columns * i13) + i5;
                            int i16 = i13 * 2;
                            int i17 = (i14 * 2) + i16;
                            int i18 = (i14 * 2) + i17;
                            int i19 = (i14 * 2) + i18;
                            dArr[i15] = dArr2[i16];
                            dArr[i15 + 1] = dArr2[i16 + 1];
                            dArr[i15 + 2] = dArr2[i17];
                            dArr[i15 + 3] = dArr2[i17 + 1];
                            dArr[i15 + 4] = dArr2[i18];
                            dArr[i15 + 5] = dArr2[i18 + 1];
                            dArr[i15 + 6] = dArr2[i19];
                            dArr[i15 + 7] = dArr2[i19 + 1];
                            i13++;
                        }
                    }
                }
                return;
            }
            if (i3 != 4) {
                if (i3 == 2) {
                    for (int i20 = 0; i20 < this.rows; i20++) {
                        int i21 = this.columns * i20;
                        int i22 = i20 * 2;
                        dArr2[i22] = dArr[i21];
                        dArr2[i22 + 1] = dArr[i21 + 1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    while (i4 < this.rows) {
                        int i23 = this.columns * i4;
                        int i24 = i4 * 2;
                        dArr[i23] = dArr2[i24];
                        dArr[i23 + 1] = dArr2[i24 + 1];
                        i4++;
                    }
                    return;
                }
                return;
            }
            int i25 = 0;
            while (true) {
                int i26 = this.rows;
                if (i25 >= i26) {
                    break;
                }
                int i27 = this.columns * i25;
                int i28 = i25 * 2;
                int i29 = (i26 * 2) + i28;
                dArr2[i28] = dArr[i27];
                dArr2[i28 + 1] = dArr[i27 + 1];
                dArr2[i29] = dArr[i27 + 2];
                dArr2[i29 + 1] = dArr[i27 + 3];
                i25++;
            }
            this.fftRows.complexForward(dArr2, 0);
            this.fftRows.complexForward(dArr2, this.rows * 2);
            while (true) {
                int i30 = this.rows;
                if (i4 >= i30) {
                    return;
                }
                int i31 = this.columns * i4;
                int i32 = i4 * 2;
                int i33 = (i30 * 2) + i32;
                dArr[i31] = dArr2[i32];
                dArr[i31 + 1] = dArr2[i32 + 1];
                dArr[i31 + 2] = dArr2[i33];
                dArr[i31 + 3] = dArr2[i33 + 1];
                i4++;
            }
        } else {
            if (i3 > 4) {
                for (int i34 = 0; i34 < this.columns; i34 += 8) {
                    int i35 = 0;
                    while (true) {
                        int i36 = this.rows;
                        if (i35 >= i36) {
                            break;
                        }
                        int i37 = (this.columns * i35) + i34;
                        int i38 = i35 * 2;
                        int i39 = (i36 * 2) + i38;
                        int i40 = (i36 * 2) + i39;
                        int i41 = (i36 * 2) + i40;
                        dArr2[i38] = dArr[i37];
                        dArr2[i38 + 1] = dArr[i37 + 1];
                        dArr2[i39] = dArr[i37 + 2];
                        dArr2[i39 + 1] = dArr[i37 + 3];
                        dArr2[i40] = dArr[i37 + 4];
                        dArr2[i40 + 1] = dArr[i37 + 5];
                        dArr2[i41] = dArr[i37 + 6];
                        dArr2[i41 + 1] = dArr[i37 + 7];
                        i35++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z);
                    int i42 = 0;
                    while (true) {
                        int i43 = this.rows;
                        if (i42 < i43) {
                            int i44 = (this.columns * i42) + i34;
                            int i45 = i42 * 2;
                            int i46 = (i43 * 2) + i45;
                            int i47 = (i43 * 2) + i46;
                            int i48 = (i43 * 2) + i47;
                            dArr[i44] = dArr2[i45];
                            dArr[i44 + 1] = dArr2[i45 + 1];
                            dArr[i44 + 2] = dArr2[i46];
                            dArr[i44 + 3] = dArr2[i46 + 1];
                            dArr[i44 + 4] = dArr2[i47];
                            dArr[i44 + 5] = dArr2[i47 + 1];
                            dArr[i44 + 6] = dArr2[i48];
                            dArr[i44 + 7] = dArr2[i48 + 1];
                            i42++;
                        }
                    }
                }
                return;
            }
            if (i3 != 4) {
                if (i3 == 2) {
                    for (int i49 = 0; i49 < this.rows; i49++) {
                        int i50 = this.columns * i49;
                        int i51 = i49 * 2;
                        dArr2[i51] = dArr[i50];
                        dArr2[i51 + 1] = dArr[i50 + 1];
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    while (i4 < this.rows) {
                        int i52 = this.columns * i4;
                        int i53 = i4 * 2;
                        dArr[i52] = dArr2[i53];
                        dArr[i52 + 1] = dArr2[i53 + 1];
                        i4++;
                    }
                    return;
                }
                return;
            }
            int i54 = 0;
            while (true) {
                int i55 = this.rows;
                if (i54 >= i55) {
                    break;
                }
                int i56 = this.columns * i54;
                int i57 = i54 * 2;
                int i58 = (i55 * 2) + i57;
                dArr2[i57] = dArr[i56];
                dArr2[i57 + 1] = dArr[i56 + 1];
                dArr2[i58] = dArr[i56 + 2];
                dArr2[i58 + 1] = dArr[i56 + 3];
                i54++;
            }
            this.fftRows.complexInverse(dArr2, 0, z);
            this.fftRows.complexInverse(dArr2, this.rows * 2, z);
            while (true) {
                int i59 = this.rows;
                if (i4 >= i59) {
                    return;
                }
                int i60 = this.columns * i4;
                int i61 = i4 * 2;
                int i62 = (i59 * 2) + i61;
                dArr[i60] = dArr2[i61];
                dArr[i60 + 1] = dArr2[i61 + 1];
                dArr[i60 + 2] = dArr2[i62];
                dArr[i60 + 3] = dArr2[i62 + 1];
                i4++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0238  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cdft2d_sub(int r30, pl.edu.icm.jlargearrays.DoubleLargeArray r31, boolean r32) {
        /*
            Method dump skipped, instructions count: 1101
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.cdft2d_sub(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft2d_sub(int i, double[][] dArr, boolean z) {
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        if (i == -1) {
            if (i3 > 4) {
                for (int i4 = 0; i4 < this.columns; i4 += 8) {
                    int i5 = 0;
                    while (true) {
                        int i6 = this.rows;
                        if (i5 >= i6) {
                            break;
                        }
                        int i7 = i5 * 2;
                        int i8 = (i6 * 2) + i7;
                        int i9 = (i6 * 2) + i8;
                        int i10 = (i6 * 2) + i9;
                        double[] dArr3 = dArr[i5];
                        dArr2[i7] = dArr3[i4];
                        dArr2[i7 + 1] = dArr3[i4 + 1];
                        dArr2[i8] = dArr3[i4 + 2];
                        dArr2[i8 + 1] = dArr3[i4 + 3];
                        dArr2[i9] = dArr3[i4 + 4];
                        dArr2[i9 + 1] = dArr3[i4 + 5];
                        dArr2[i10] = dArr3[i4 + 6];
                        dArr2[i10 + 1] = dArr3[i4 + 7];
                        i5++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    this.fftRows.complexForward(dArr2, this.rows * 4);
                    this.fftRows.complexForward(dArr2, this.rows * 6);
                    int i11 = 0;
                    while (true) {
                        int i12 = this.rows;
                        if (i11 < i12) {
                            int i13 = i11 * 2;
                            int i14 = (i12 * 2) + i13;
                            int i15 = (i12 * 2) + i14;
                            int i16 = (i12 * 2) + i15;
                            double[] dArr4 = dArr[i11];
                            dArr4[i4] = dArr2[i13];
                            dArr4[i4 + 1] = dArr2[i13 + 1];
                            dArr4[i4 + 2] = dArr2[i14];
                            dArr4[i4 + 3] = dArr2[i14 + 1];
                            dArr4[i4 + 4] = dArr2[i15];
                            dArr4[i4 + 5] = dArr2[i15 + 1];
                            dArr4[i4 + 6] = dArr2[i16];
                            dArr4[i4 + 7] = dArr2[i16 + 1];
                            i11++;
                        }
                    }
                }
                return;
            }
            if (i3 != 4) {
                if (i3 == 2) {
                    for (int i17 = 0; i17 < this.rows; i17++) {
                        int i18 = i17 * 2;
                        double[] dArr5 = dArr[i17];
                        dArr2[i18] = dArr5[0];
                        dArr2[i18 + 1] = dArr5[1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i19 = 0; i19 < this.rows; i19++) {
                        int i20 = i19 * 2;
                        double[] dArr6 = dArr[i19];
                        dArr6[0] = dArr2[i20];
                        dArr6[1] = dArr2[i20 + 1];
                    }
                    return;
                }
                return;
            }
            int i21 = 0;
            while (true) {
                int i22 = this.rows;
                if (i21 >= i22) {
                    break;
                }
                int i23 = i21 * 2;
                int i24 = (i22 * 2) + i23;
                double[] dArr7 = dArr[i21];
                dArr2[i23] = dArr7[0];
                dArr2[i23 + 1] = dArr7[1];
                dArr2[i24] = dArr7[2];
                dArr2[i24 + 1] = dArr7[3];
                i21++;
            }
            this.fftRows.complexForward(dArr2, 0);
            this.fftRows.complexForward(dArr2, this.rows * 2);
            int i25 = 0;
            while (true) {
                int i26 = this.rows;
                if (i25 >= i26) {
                    return;
                }
                int i27 = i25 * 2;
                int i28 = (i26 * 2) + i27;
                double[] dArr8 = dArr[i25];
                dArr8[0] = dArr2[i27];
                dArr8[1] = dArr2[i27 + 1];
                dArr8[2] = dArr2[i28];
                dArr8[3] = dArr2[i28 + 1];
                i25++;
            }
        } else {
            if (i3 > 4) {
                for (int i29 = 0; i29 < this.columns; i29 += 8) {
                    int i30 = 0;
                    while (true) {
                        int i31 = this.rows;
                        if (i30 >= i31) {
                            break;
                        }
                        int i32 = i30 * 2;
                        int i33 = (i31 * 2) + i32;
                        int i34 = (i31 * 2) + i33;
                        int i35 = (i31 * 2) + i34;
                        double[] dArr9 = dArr[i30];
                        dArr2[i32] = dArr9[i29];
                        dArr2[i32 + 1] = dArr9[i29 + 1];
                        dArr2[i33] = dArr9[i29 + 2];
                        dArr2[i33 + 1] = dArr9[i29 + 3];
                        dArr2[i34] = dArr9[i29 + 4];
                        dArr2[i34 + 1] = dArr9[i29 + 5];
                        dArr2[i35] = dArr9[i29 + 6];
                        dArr2[i35 + 1] = dArr9[i29 + 7];
                        i30++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z);
                    int i36 = 0;
                    while (true) {
                        int i37 = this.rows;
                        if (i36 < i37) {
                            int i38 = i36 * 2;
                            int i39 = (i37 * 2) + i38;
                            int i40 = (i37 * 2) + i39;
                            int i41 = (i37 * 2) + i40;
                            double[] dArr10 = dArr[i36];
                            dArr10[i29] = dArr2[i38];
                            dArr10[i29 + 1] = dArr2[i38 + 1];
                            dArr10[i29 + 2] = dArr2[i39];
                            dArr10[i29 + 3] = dArr2[i39 + 1];
                            dArr10[i29 + 4] = dArr2[i40];
                            dArr10[i29 + 5] = dArr2[i40 + 1];
                            dArr10[i29 + 6] = dArr2[i41];
                            dArr10[i29 + 7] = dArr2[i41 + 1];
                            i36++;
                        }
                    }
                }
                return;
            }
            if (i3 != 4) {
                if (i3 == 2) {
                    for (int i42 = 0; i42 < this.rows; i42++) {
                        int i43 = i42 * 2;
                        double[] dArr11 = dArr[i42];
                        dArr2[i43] = dArr11[0];
                        dArr2[i43 + 1] = dArr11[1];
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    for (int i44 = 0; i44 < this.rows; i44++) {
                        int i45 = i44 * 2;
                        double[] dArr12 = dArr[i44];
                        dArr12[0] = dArr2[i45];
                        dArr12[1] = dArr2[i45 + 1];
                    }
                    return;
                }
                return;
            }
            int i46 = 0;
            while (true) {
                int i47 = this.rows;
                if (i46 >= i47) {
                    break;
                }
                int i48 = i46 * 2;
                int i49 = (i47 * 2) + i48;
                double[] dArr13 = dArr[i46];
                dArr2[i48] = dArr13[0];
                dArr2[i48 + 1] = dArr13[1];
                dArr2[i49] = dArr13[2];
                dArr2[i49 + 1] = dArr13[3];
                i46++;
            }
            this.fftRows.complexInverse(dArr2, 0, z);
            this.fftRows.complexInverse(dArr2, this.rows * 2, z);
            int i50 = 0;
            while (true) {
                int i51 = this.rows;
                if (i50 >= i51) {
                    return;
                }
                int i52 = i50 * 2;
                int i53 = (i51 * 2) + i52;
                double[] dArr14 = dArr[i50];
                dArr14[0] = dArr2[i52];
                dArr14[1] = dArr2[i52 + 1];
                dArr14[2] = dArr2[i53];
                dArr14[3] = dArr2[i53 + 1];
                i50++;
            }
        }
    }

    private void xdft2d0_subth1(final int i, final int i2, final double[] dArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i3 = numberOfThreads2;
        Future[] futureArr = new Future[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            final int i5 = i4;
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.37
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 0) {
                        if (i2 == -1) {
                            int i6 = i5;
                            while (i6 < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr, DoubleFFT_2D.this.columns * i6);
                                i6 += i3;
                            }
                            return;
                        }
                        int i7 = i5;
                        while (i7 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr, DoubleFFT_2D.this.columns * i7, z);
                            i7 += i3;
                        }
                        return;
                    }
                    if (i2 == 1) {
                        int i8 = i5;
                        while (i8 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr, DoubleFFT_2D.this.columns * i8);
                            i8 += i3;
                        }
                        return;
                    }
                    int i9 = i5;
                    while (i9 < DoubleFFT_2D.this.rows) {
                        DoubleFFT_2D.this.fftColumns.realInverse(dArr, DoubleFFT_2D.this.columns * i9, z);
                        i9 += i3;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft2d0_subth1(final long j, final int i, final DoubleLargeArray doubleLargeArray, final boolean z) {
        long numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long numberOfThreads2 = this.rowsl;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = (int) numberOfThreads2;
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final int i4 = i3;
            futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.38
                @Override // java.lang.Runnable
                public void run() {
                    if (j == 0) {
                        if (i == -1) {
                            long j2 = i4;
                            while (j2 < DoubleFFT_2D.this.rowsl) {
                                DoubleFFT_2D.this.fftColumns.complexForward(doubleLargeArray, DoubleFFT_2D.this.columnsl * j2);
                                j2 += i2;
                            }
                            return;
                        }
                        long j3 = i4;
                        while (j3 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(doubleLargeArray, DoubleFFT_2D.this.columnsl * j3, z);
                            j3 += i2;
                        }
                        return;
                    }
                    if (i == 1) {
                        long j4 = i4;
                        while (j4 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.realForward(doubleLargeArray, DoubleFFT_2D.this.columnsl * j4);
                            j4 += i2;
                        }
                        return;
                    }
                    long j5 = i4;
                    while (j5 < DoubleFFT_2D.this.rowsl) {
                        DoubleFFT_2D.this.fftColumns.realInverse(doubleLargeArray, DoubleFFT_2D.this.columnsl * j5, z);
                        j5 += i2;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft2d0_subth2(final int i, final int i2, final double[] dArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i3 = numberOfThreads2;
        Future[] futureArr = new Future[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            final int i5 = i4;
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.39
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 0) {
                        if (i2 == -1) {
                            int i6 = i5;
                            while (i6 < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr, DoubleFFT_2D.this.columns * i6);
                                i6 += i3;
                            }
                            return;
                        }
                        int i7 = i5;
                        while (i7 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr, DoubleFFT_2D.this.columns * i7, z);
                            i7 += i3;
                        }
                        return;
                    }
                    if (i2 == 1) {
                        int i8 = i5;
                        while (i8 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr, DoubleFFT_2D.this.columns * i8);
                            i8 += i3;
                        }
                        return;
                    }
                    int i9 = i5;
                    while (i9 < DoubleFFT_2D.this.rows) {
                        DoubleFFT_2D.this.fftColumns.realInverse2(dArr, DoubleFFT_2D.this.columns * i9, z);
                        i9 += i3;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft2d0_subth2(final long j, final int i, final DoubleLargeArray doubleLargeArray, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = numberOfThreads2;
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final long j2 = i3;
            futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.40
                @Override // java.lang.Runnable
                public void run() {
                    if (j == 0) {
                        if (i == -1) {
                            long j3 = j2;
                            while (j3 < DoubleFFT_2D.this.rowsl) {
                                DoubleFFT_2D.this.fftColumns.complexForward(doubleLargeArray, DoubleFFT_2D.this.columnsl * j3);
                                j3 += i2;
                            }
                            return;
                        }
                        long j4 = j2;
                        while (j4 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(doubleLargeArray, DoubleFFT_2D.this.columnsl * j4, z);
                            j4 += i2;
                        }
                        return;
                    }
                    if (i == 1) {
                        long j5 = j2;
                        while (j5 < DoubleFFT_2D.this.rowsl) {
                            DoubleFFT_2D.this.fftColumns.realForward(doubleLargeArray, DoubleFFT_2D.this.columnsl * j5);
                            j5 += i2;
                        }
                        return;
                    }
                    long j6 = j2;
                    while (j6 < DoubleFFT_2D.this.rowsl) {
                        DoubleFFT_2D.this.fftColumns.realInverse2(doubleLargeArray, DoubleFFT_2D.this.columnsl * j6, z);
                        j6 += i2;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft2d0_subth1(final int i, final int i2, final double[][] dArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i3 = numberOfThreads2;
        Future[] futureArr = new Future[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            final int i5 = i4;
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.41
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 0) {
                        if (i2 == -1) {
                            int i6 = i5;
                            while (i6 < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr[i6]);
                                i6 += i3;
                            }
                            return;
                        }
                        int i7 = i5;
                        while (i7 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr[i7], z);
                            i7 += i3;
                        }
                        return;
                    }
                    if (i2 == 1) {
                        int i8 = i5;
                        while (i8 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr[i8]);
                            i8 += i3;
                        }
                        return;
                    }
                    int i9 = i5;
                    while (i9 < DoubleFFT_2D.this.rows) {
                        DoubleFFT_2D.this.fftColumns.realInverse(dArr[i9], z);
                        i9 += i3;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft2d0_subth2(final int i, final int i2, final double[][] dArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i3 = numberOfThreads2;
        Future[] futureArr = new Future[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            final int i5 = i4;
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.42
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 0) {
                        if (i2 == -1) {
                            int i6 = i5;
                            while (i6 < DoubleFFT_2D.this.rows) {
                                DoubleFFT_2D.this.fftColumns.complexForward(dArr[i6]);
                                i6 += i3;
                            }
                            return;
                        }
                        int i7 = i5;
                        while (i7 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.complexInverse(dArr[i7], z);
                            i7 += i3;
                        }
                        return;
                    }
                    if (i2 == 1) {
                        int i8 = i5;
                        while (i8 < DoubleFFT_2D.this.rows) {
                            DoubleFFT_2D.this.fftColumns.realForward(dArr[i8]);
                            i8 += i3;
                        }
                        return;
                    }
                    int i9 = i5;
                    while (i9 < DoubleFFT_2D.this.rows) {
                        DoubleFFT_2D.this.fftColumns.realInverse2(dArr[i9], 0, z);
                        i9 += i3;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void cdft2d_subth(final int i, final double[] dArr, final boolean z) {
        final int iMin = FastMath.min(this.columns / 2, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        final int i4 = i2;
        Future[] futureArr = new Future[iMin];
        for (int i5 = 0; i5 < iMin; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.43
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    int i7 = 0;
                    if (i == -1) {
                        if (DoubleFFT_2D.this.columns > iMin * 4) {
                            int i8 = i6 * 8;
                            while (i8 < DoubleFFT_2D.this.columns) {
                                for (int i9 = 0; i9 < DoubleFFT_2D.this.rows; i9++) {
                                    int i10 = (DoubleFFT_2D.this.columns * i9) + i8;
                                    int i11 = i9 * 2;
                                    int i12 = (DoubleFFT_2D.this.rows * 2) + i11;
                                    int i13 = (DoubleFFT_2D.this.rows * 2) + i12;
                                    int i14 = (DoubleFFT_2D.this.rows * 2) + i13;
                                    double[] dArr3 = dArr;
                                    dArr2[i11] = dArr3[i10];
                                    dArr2[i11 + 1] = dArr3[i10 + 1];
                                    dArr2[i12] = dArr3[i10 + 2];
                                    dArr2[i12 + 1] = dArr3[i10 + 3];
                                    dArr2[i13] = dArr3[i10 + 4];
                                    dArr2[i13 + 1] = dArr3[i10 + 5];
                                    dArr2[i14] = dArr3[i10 + 6];
                                    dArr2[i14 + 1] = dArr3[i10 + 7];
                                }
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, 0);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 2);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 4);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 6);
                                for (int i15 = 0; i15 < DoubleFFT_2D.this.rows; i15++) {
                                    int i16 = (DoubleFFT_2D.this.columns * i15) + i8;
                                    int i17 = i15 * 2;
                                    int i18 = (DoubleFFT_2D.this.rows * 2) + i17;
                                    int i19 = (DoubleFFT_2D.this.rows * 2) + i18;
                                    int i20 = (DoubleFFT_2D.this.rows * 2) + i19;
                                    double[] dArr4 = dArr;
                                    dArr4[i16] = dArr2[i17];
                                    dArr4[i16 + 1] = dArr2[i17 + 1];
                                    dArr4[i16 + 2] = dArr2[i18];
                                    dArr4[i16 + 3] = dArr2[i18 + 1];
                                    dArr4[i16 + 4] = dArr2[i19];
                                    dArr4[i16 + 5] = dArr2[i19 + 1];
                                    dArr4[i16 + 6] = dArr2[i20];
                                    dArr4[i16 + 7] = dArr2[i20 + 1];
                                }
                                i8 += iMin * 8;
                            }
                            return;
                        }
                        if (DoubleFFT_2D.this.columns == iMin * 4) {
                            for (int i21 = 0; i21 < DoubleFFT_2D.this.rows; i21++) {
                                int i22 = (DoubleFFT_2D.this.columns * i21) + (i6 * 4);
                                int i23 = i21 * 2;
                                int i24 = (DoubleFFT_2D.this.rows * 2) + i23;
                                double[] dArr5 = dArr;
                                dArr2[i23] = dArr5[i22];
                                dArr2[i23 + 1] = dArr5[i22 + 1];
                                dArr2[i24] = dArr5[i22 + 2];
                                dArr2[i24 + 1] = dArr5[i22 + 3];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2, 0);
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 2);
                            while (i7 < DoubleFFT_2D.this.rows) {
                                int i25 = (DoubleFFT_2D.this.columns * i7) + (i6 * 4);
                                int i26 = i7 * 2;
                                int i27 = (DoubleFFT_2D.this.rows * 2) + i26;
                                double[] dArr6 = dArr;
                                dArr6[i25] = dArr2[i26];
                                dArr6[i25 + 1] = dArr2[i26 + 1];
                                dArr6[i25 + 2] = dArr2[i27];
                                dArr6[i25 + 3] = dArr2[i27 + 1];
                                i7++;
                            }
                            return;
                        }
                        if (DoubleFFT_2D.this.columns == iMin * 2) {
                            for (int i28 = 0; i28 < DoubleFFT_2D.this.rows; i28++) {
                                int i29 = (DoubleFFT_2D.this.columns * i28) + (i6 * 2);
                                int i30 = i28 * 2;
                                double[] dArr7 = dArr;
                                dArr2[i30] = dArr7[i29];
                                dArr2[i30 + 1] = dArr7[i29 + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2, 0);
                            while (i7 < DoubleFFT_2D.this.rows) {
                                int i31 = (DoubleFFT_2D.this.columns * i7) + (i6 * 2);
                                int i32 = i7 * 2;
                                double[] dArr8 = dArr;
                                dArr8[i31] = dArr2[i32];
                                dArr8[i31 + 1] = dArr2[i32 + 1];
                                i7++;
                            }
                            return;
                        }
                        return;
                    }
                    if (DoubleFFT_2D.this.columns > iMin * 4) {
                        int i33 = i6 * 8;
                        while (i33 < DoubleFFT_2D.this.columns) {
                            for (int i34 = 0; i34 < DoubleFFT_2D.this.rows; i34++) {
                                int i35 = (DoubleFFT_2D.this.columns * i34) + i33;
                                int i36 = i34 * 2;
                                int i37 = (DoubleFFT_2D.this.rows * 2) + i36;
                                int i38 = (DoubleFFT_2D.this.rows * 2) + i37;
                                int i39 = (DoubleFFT_2D.this.rows * 2) + i38;
                                double[] dArr9 = dArr;
                                dArr2[i36] = dArr9[i35];
                                dArr2[i36 + 1] = dArr9[i35 + 1];
                                dArr2[i37] = dArr9[i35 + 2];
                                dArr2[i37 + 1] = dArr9[i35 + 3];
                                dArr2[i38] = dArr9[i35 + 4];
                                dArr2[i38 + 1] = dArr9[i35 + 5];
                                dArr2[i39] = dArr9[i35 + 6];
                                dArr2[i39 + 1] = dArr9[i35 + 7];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, 0, z);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 2, z);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 4, z);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 6, z);
                            for (int i40 = 0; i40 < DoubleFFT_2D.this.rows; i40++) {
                                int i41 = (DoubleFFT_2D.this.columns * i40) + i33;
                                int i42 = i40 * 2;
                                int i43 = (DoubleFFT_2D.this.rows * 2) + i42;
                                int i44 = (DoubleFFT_2D.this.rows * 2) + i43;
                                int i45 = (DoubleFFT_2D.this.rows * 2) + i44;
                                double[] dArr10 = dArr;
                                dArr10[i41] = dArr2[i42];
                                dArr10[i41 + 1] = dArr2[i42 + 1];
                                dArr10[i41 + 2] = dArr2[i43];
                                dArr10[i41 + 3] = dArr2[i43 + 1];
                                dArr10[i41 + 4] = dArr2[i44];
                                dArr10[i41 + 5] = dArr2[i44 + 1];
                                dArr10[i41 + 6] = dArr2[i45];
                                dArr10[i41 + 7] = dArr2[i45 + 1];
                            }
                            i33 += iMin * 8;
                        }
                        return;
                    }
                    if (DoubleFFT_2D.this.columns == iMin * 4) {
                        for (int i46 = 0; i46 < DoubleFFT_2D.this.rows; i46++) {
                            int i47 = (DoubleFFT_2D.this.columns * i46) + (i6 * 4);
                            int i48 = i46 * 2;
                            int i49 = (DoubleFFT_2D.this.rows * 2) + i48;
                            double[] dArr11 = dArr;
                            dArr2[i48] = dArr11[i47];
                            dArr2[i48 + 1] = dArr11[i47 + 1];
                            dArr2[i49] = dArr11[i47 + 2];
                            dArr2[i49 + 1] = dArr11[i47 + 3];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr2, 0, z);
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 2, z);
                        while (i7 < DoubleFFT_2D.this.rows) {
                            int i50 = (DoubleFFT_2D.this.columns * i7) + (i6 * 4);
                            int i51 = i7 * 2;
                            int i52 = (DoubleFFT_2D.this.rows * 2) + i51;
                            double[] dArr12 = dArr;
                            dArr12[i50] = dArr2[i51];
                            dArr12[i50 + 1] = dArr2[i51 + 1];
                            dArr12[i50 + 2] = dArr2[i52];
                            dArr12[i50 + 3] = dArr2[i52 + 1];
                            i7++;
                        }
                        return;
                    }
                    if (DoubleFFT_2D.this.columns == iMin * 2) {
                        for (int i53 = 0; i53 < DoubleFFT_2D.this.rows; i53++) {
                            int i54 = (DoubleFFT_2D.this.columns * i53) + (i6 * 2);
                            int i55 = i53 * 2;
                            double[] dArr13 = dArr;
                            dArr2[i55] = dArr13[i54];
                            dArr2[i55 + 1] = dArr13[i54 + 1];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr2, 0, z);
                        while (i7 < DoubleFFT_2D.this.rows) {
                            int i56 = (DoubleFFT_2D.this.columns * i7) + (i6 * 2);
                            int i57 = i7 * 2;
                            double[] dArr14 = dArr;
                            dArr14[i56] = dArr2[i57];
                            dArr14[i56 + 1] = dArr2[i57 + 1];
                            i7++;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0031 A[LOOP:0: B:10:0x002f->B:11:0x0031, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cdft2d_subth(final int r19, final pl.edu.icm.jlargearrays.DoubleLargeArray r20, final boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.DoubleFFT_2D> r12 = org.jtransforms.fft.DoubleFFT_2D.class
            long r0 = r11.columnsl
            r2 = 2
            long r0 = r0 / r2
            int r2 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r2 = (long) r2
            long r0 = org.apache.commons.math3.util.FastMath.min(r0, r2)
            int r13 = (int) r0
            r0 = 8
            long r2 = r11.rowsl
            long r2 = r2 * r0
            long r0 = r11.columnsl
            r4 = 4
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 != 0) goto L24
            r0 = 1
        L22:
            long r2 = r2 >> r0
            goto L2a
        L24:
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 >= 0) goto L2a
            r0 = 2
            goto L22
        L2a:
            r14 = r2
            java.util.concurrent.Future[] r0 = new java.util.concurrent.Future[r13]
            r1 = 0
            r10 = 0
        L2f:
            if (r10 >= r13) goto L4e
            long r7 = (long) r10
            org.jtransforms.fft.DoubleFFT_2D$44 r16 = new org.jtransforms.fft.DoubleFFT_2D$44
            r1 = r16
            r2 = r18
            r3 = r14
            r5 = r19
            r6 = r13
            r9 = r20
            r17 = r10
            r10 = r21
            r1.<init>()
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r16)
            r0[r17] = r1
            int r10 = r17 + 1
            goto L2f
        L4e:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r0)     // Catch: java.util.concurrent.ExecutionException -> L53 java.lang.InterruptedException -> L63
            goto L72
        L53:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L72
        L63:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.cdft2d_subth(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft2d_subth(final int i, final double[][] dArr, final boolean z) {
        final int iMin = FastMath.min(this.columns / 2, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 8;
        int i3 = this.columns;
        if (i3 == 4) {
            i2 >>= 1;
        } else if (i3 < 4) {
            i2 >>= 2;
        }
        final int i4 = i2;
        Future[] futureArr = new Future[iMin];
        for (int i5 = 0; i5 < iMin; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.45
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    int i7 = 0;
                    if (i == -1) {
                        if (DoubleFFT_2D.this.columns > iMin * 4) {
                            int i8 = i6 * 8;
                            while (i8 < DoubleFFT_2D.this.columns) {
                                for (int i9 = 0; i9 < DoubleFFT_2D.this.rows; i9++) {
                                    int i10 = i9 * 2;
                                    int i11 = (DoubleFFT_2D.this.rows * 2) + i10;
                                    int i12 = (DoubleFFT_2D.this.rows * 2) + i11;
                                    int i13 = (DoubleFFT_2D.this.rows * 2) + i12;
                                    double[] dArr3 = dArr[i9];
                                    dArr2[i10] = dArr3[i8];
                                    dArr2[i10 + 1] = dArr3[i8 + 1];
                                    dArr2[i11] = dArr3[i8 + 2];
                                    dArr2[i11 + 1] = dArr3[i8 + 3];
                                    dArr2[i12] = dArr3[i8 + 4];
                                    dArr2[i12 + 1] = dArr3[i8 + 5];
                                    dArr2[i13] = dArr3[i8 + 6];
                                    dArr2[i13 + 1] = dArr3[i8 + 7];
                                }
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, 0);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 2);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 4);
                                DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 6);
                                for (int i14 = 0; i14 < DoubleFFT_2D.this.rows; i14++) {
                                    int i15 = i14 * 2;
                                    int i16 = (DoubleFFT_2D.this.rows * 2) + i15;
                                    int i17 = (DoubleFFT_2D.this.rows * 2) + i16;
                                    int i18 = (DoubleFFT_2D.this.rows * 2) + i17;
                                    double[] dArr4 = dArr[i14];
                                    dArr4[i8] = dArr2[i15];
                                    dArr4[i8 + 1] = dArr2[i15 + 1];
                                    dArr4[i8 + 2] = dArr2[i16];
                                    dArr4[i8 + 3] = dArr2[i16 + 1];
                                    dArr4[i8 + 4] = dArr2[i17];
                                    dArr4[i8 + 5] = dArr2[i17 + 1];
                                    dArr4[i8 + 6] = dArr2[i18];
                                    dArr4[i8 + 7] = dArr2[i18 + 1];
                                }
                                i8 += iMin * 8;
                            }
                            return;
                        }
                        if (DoubleFFT_2D.this.columns == iMin * 4) {
                            for (int i19 = 0; i19 < DoubleFFT_2D.this.rows; i19++) {
                                int i20 = i19 * 2;
                                int i21 = (DoubleFFT_2D.this.rows * 2) + i20;
                                double[] dArr5 = dArr[i19];
                                int i22 = i6;
                                dArr2[i20] = dArr5[i22 * 4];
                                dArr2[i20 + 1] = dArr5[(i22 * 4) + 1];
                                dArr2[i21] = dArr5[(i22 * 4) + 2];
                                dArr2[i21 + 1] = dArr5[(i22 * 4) + 3];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2, 0);
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2, DoubleFFT_2D.this.rows * 2);
                            while (i7 < DoubleFFT_2D.this.rows) {
                                int i23 = i7 * 2;
                                int i24 = (DoubleFFT_2D.this.rows * 2) + i23;
                                double[] dArr6 = dArr[i7];
                                int i25 = i6;
                                dArr6[i25 * 4] = dArr2[i23];
                                dArr6[(i25 * 4) + 1] = dArr2[i23 + 1];
                                dArr6[(i25 * 4) + 2] = dArr2[i24];
                                dArr6[(i25 * 4) + 3] = dArr2[i24 + 1];
                                i7++;
                            }
                            return;
                        }
                        if (DoubleFFT_2D.this.columns == iMin * 2) {
                            for (int i26 = 0; i26 < DoubleFFT_2D.this.rows; i26++) {
                                int i27 = i26 * 2;
                                double[] dArr7 = dArr[i26];
                                int i28 = i6;
                                dArr2[i27] = dArr7[i28 * 2];
                                dArr2[i27 + 1] = dArr7[(i28 * 2) + 1];
                            }
                            DoubleFFT_2D.this.fftRows.complexForward(dArr2, 0);
                            while (i7 < DoubleFFT_2D.this.rows) {
                                int i29 = i7 * 2;
                                double[] dArr8 = dArr[i7];
                                int i30 = i6;
                                dArr8[i30 * 2] = dArr2[i29];
                                dArr8[(i30 * 2) + 1] = dArr2[i29 + 1];
                                i7++;
                            }
                            return;
                        }
                        return;
                    }
                    if (DoubleFFT_2D.this.columns > iMin * 4) {
                        int i31 = i6 * 8;
                        while (i31 < DoubleFFT_2D.this.columns) {
                            for (int i32 = 0; i32 < DoubleFFT_2D.this.rows; i32++) {
                                int i33 = i32 * 2;
                                int i34 = (DoubleFFT_2D.this.rows * 2) + i33;
                                int i35 = (DoubleFFT_2D.this.rows * 2) + i34;
                                int i36 = (DoubleFFT_2D.this.rows * 2) + i35;
                                double[] dArr9 = dArr[i32];
                                dArr2[i33] = dArr9[i31];
                                dArr2[i33 + 1] = dArr9[i31 + 1];
                                dArr2[i34] = dArr9[i31 + 2];
                                dArr2[i34 + 1] = dArr9[i31 + 3];
                                dArr2[i35] = dArr9[i31 + 4];
                                dArr2[i35 + 1] = dArr9[i31 + 5];
                                dArr2[i36] = dArr9[i31 + 6];
                                dArr2[i36 + 1] = dArr9[i31 + 7];
                            }
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, 0, z);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 2, z);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 4, z);
                            DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 6, z);
                            for (int i37 = 0; i37 < DoubleFFT_2D.this.rows; i37++) {
                                int i38 = i37 * 2;
                                int i39 = (DoubleFFT_2D.this.rows * 2) + i38;
                                int i40 = (DoubleFFT_2D.this.rows * 2) + i39;
                                int i41 = (DoubleFFT_2D.this.rows * 2) + i40;
                                double[] dArr10 = dArr[i37];
                                dArr10[i31] = dArr2[i38];
                                dArr10[i31 + 1] = dArr2[i38 + 1];
                                dArr10[i31 + 2] = dArr2[i39];
                                dArr10[i31 + 3] = dArr2[i39 + 1];
                                dArr10[i31 + 4] = dArr2[i40];
                                dArr10[i31 + 5] = dArr2[i40 + 1];
                                dArr10[i31 + 6] = dArr2[i41];
                                dArr10[i31 + 7] = dArr2[i41 + 1];
                            }
                            i31 += iMin * 8;
                        }
                        return;
                    }
                    if (DoubleFFT_2D.this.columns == iMin * 4) {
                        for (int i42 = 0; i42 < DoubleFFT_2D.this.rows; i42++) {
                            int i43 = i42 * 2;
                            int i44 = (DoubleFFT_2D.this.rows * 2) + i43;
                            double[] dArr11 = dArr[i42];
                            int i45 = i6;
                            dArr2[i43] = dArr11[i45 * 4];
                            dArr2[i43 + 1] = dArr11[(i45 * 4) + 1];
                            dArr2[i44] = dArr11[(i45 * 4) + 2];
                            dArr2[i44 + 1] = dArr11[(i45 * 4) + 3];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr2, 0, z);
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr2, DoubleFFT_2D.this.rows * 2, z);
                        while (i7 < DoubleFFT_2D.this.rows) {
                            int i46 = i7 * 2;
                            int i47 = (DoubleFFT_2D.this.rows * 2) + i46;
                            double[] dArr12 = dArr[i7];
                            int i48 = i6;
                            dArr12[i48 * 4] = dArr2[i46];
                            dArr12[(i48 * 4) + 1] = dArr2[i46 + 1];
                            dArr12[(i48 * 4) + 2] = dArr2[i47];
                            dArr12[(i48 * 4) + 3] = dArr2[i47 + 1];
                            i7++;
                        }
                        return;
                    }
                    if (DoubleFFT_2D.this.columns == iMin * 2) {
                        for (int i49 = 0; i49 < DoubleFFT_2D.this.rows; i49++) {
                            int i50 = i49 * 2;
                            double[] dArr13 = dArr[i49];
                            int i51 = i6;
                            dArr2[i50] = dArr13[i51 * 2];
                            dArr2[i50 + 1] = dArr13[(i51 * 2) + 1];
                        }
                        DoubleFFT_2D.this.fftRows.complexInverse(dArr2, 0, z);
                        while (i7 < DoubleFFT_2D.this.rows) {
                            int i52 = i7 * 2;
                            double[] dArr14 = dArr[i7];
                            int i53 = i6;
                            dArr14[i53 * 2] = dArr2[i52];
                            dArr14[(i53 * 2) + 1] = dArr2[i52 + 1];
                            i7++;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void fillSymmetric(final double[] dArr) {
        int i = this.columns * 2;
        int i2 = this.rows;
        int i3 = i2 / 2;
        int i4 = i2 - 1;
        while (true) {
            if (i4 < 1) {
                break;
            }
            int i5 = this.columns * i4;
            int i6 = i5 * 2;
            for (int i7 = 0; i7 < this.columns; i7 += 2) {
                int i8 = i6 + i7;
                int i9 = i5 + i7;
                dArr[i8] = dArr[i9];
                dArr[i9] = 0.0d;
                int i10 = i9 + 1;
                dArr[i8 + 1] = dArr[i10];
                dArr[i10] = 0.0d;
            }
            i4--;
        }
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || i3 < numberOfThreads) {
            for (int i11 = 1; i11 < i3; i11++) {
                int i12 = i11 * i;
                int i13 = (this.rows - i11) * i;
                int i14 = this.columns;
                dArr[i12 + i14] = dArr[i13 + 1];
                dArr[i12 + i14 + 1] = -dArr[i13];
            }
            for (int i15 = 1; i15 < i3; i15++) {
                int i16 = i15 * i;
                int i17 = ((this.rows - i15) + 1) * i;
                int i18 = this.columns;
                while (true) {
                    i18 += 2;
                    if (i18 < i) {
                        int i19 = i16 + i18;
                        int i20 = i17 - i18;
                        dArr[i19] = dArr[i20];
                        dArr[i19 + 1] = -dArr[i20 + 1];
                    }
                }
            }
            int i21 = 0;
            while (true) {
                int i22 = this.rows;
                if (i21 > i22 / 2) {
                    break;
                }
                int i23 = i21 * i;
                int i24 = ((i22 - i21) % i22) * i;
                for (int i25 = 0; i25 < i; i25 += 2) {
                    int i26 = i23 + i25;
                    int i27 = ((i - i25) % i) + i24;
                    dArr[i27] = dArr[i26];
                    dArr[i27 + 1] = -dArr[i26 + 1];
                }
                i21++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i28 = i3 / numberOfThreads;
            final int i29 = this.columns * 2;
            int i30 = 0;
            while (i30 < numberOfThreads) {
                final int i31 = i30 == 0 ? (i30 * i28) + 1 : i30 * i28;
                final int i32 = i30 * i28;
                final int i33 = i32 + i28;
                int i34 = i30;
                Future[] futureArr2 = futureArr;
                final int i35 = i30 == numberOfThreads + (-1) ? i33 + 1 : i33;
                futureArr2[i34] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.46
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i36 = i31; i36 < i33; i36++) {
                            int i37 = i29 * i36;
                            int i38 = (DoubleFFT_2D.this.rows - i36) * i29;
                            int i39 = i37 + DoubleFFT_2D.this.columns;
                            double[] dArr2 = dArr;
                            dArr2[i39] = dArr2[i38 + 1];
                            dArr2[i39 + 1] = -dArr2[i38];
                        }
                        for (int i40 = i31; i40 < i33; i40++) {
                            int i41 = i29 * i40;
                            int i42 = ((DoubleFFT_2D.this.rows - i40) + 1) * i29;
                            int i43 = DoubleFFT_2D.this.columns;
                            while (true) {
                                i43 += 2;
                                if (i43 < i29) {
                                    int i44 = i42 - i43;
                                    int i45 = i41 + i43;
                                    double[] dArr3 = dArr;
                                    dArr3[i45] = dArr3[i44];
                                    dArr3[i45 + 1] = -dArr3[i44 + 1];
                                }
                            }
                        }
                        for (int i46 = i32; i46 < i35; i46++) {
                            int i47 = (DoubleFFT_2D.this.rows - i46) % DoubleFFT_2D.this.rows;
                            int i48 = i29;
                            int i49 = i47 * i48;
                            int i50 = i48 * i46;
                            int i51 = 0;
                            while (true) {
                                int i52 = i29;
                                if (i51 < i52) {
                                    int i53 = ((i52 - i51) % i52) + i49;
                                    int i54 = i50 + i51;
                                    double[] dArr4 = dArr;
                                    dArr4[i53] = dArr4[i54];
                                    dArr4[i53 + 1] = -dArr4[i54 + 1];
                                    i51 += 2;
                                }
                            }
                        }
                    }
                });
                i30 = i34 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        int i36 = this.columns;
        dArr[i36] = -dArr[1];
        dArr[1] = 0.0d;
        int i37 = i3 * i;
        int i38 = i37 + 1;
        dArr[i37 + i36] = -dArr[i38];
        dArr[i38] = 0.0d;
        dArr[i37 + i36 + 1] = 0.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void fillSymmetric(final pl.edu.icm.jlargearrays.DoubleLargeArray r33) {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_2D.fillSymmetric(pl.edu.icm.jlargearrays.DoubleLargeArray):void");
    }

    private void fillSymmetric(final double[][] dArr) {
        final int i = this.columns * 2;
        int i2 = this.rows / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 1;
        if (numberOfThreads <= 1 || !this.useThreads || i2 < numberOfThreads) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.rows - i4;
                double[] dArr2 = dArr[i4];
                int i6 = this.columns;
                double[] dArr3 = dArr[i5];
                dArr2[i6] = dArr3[1];
                dArr2[i6 + 1] = -dArr3[0];
            }
            int i7 = 1;
            while (i7 < i2) {
                int i8 = this.rows - i7;
                int i9 = this.columns + 2;
                while (i9 < i) {
                    int i10 = i - i9;
                    double[] dArr4 = dArr[i7];
                    double[] dArr5 = dArr[i8];
                    dArr4[i9] = dArr5[i10];
                    dArr4[i9 + 1] = -dArr5[i10 + i3];
                    i9 += 2;
                    i3 = 1;
                }
                i7++;
                i3 = 1;
            }
            int i11 = 0;
            while (true) {
                int i12 = this.rows;
                if (i11 > i12 / 2) {
                    break;
                }
                int i13 = (i12 - i11) % i12;
                for (int i14 = 0; i14 < i; i14 += 2) {
                    int i15 = (i - i14) % i;
                    double[] dArr6 = dArr[i13];
                    double[] dArr7 = dArr[i11];
                    dArr6[i15] = dArr7[i14];
                    dArr6[i15 + 1] = -dArr7[i14 + 1];
                }
                i11++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i16 = i2 / numberOfThreads;
            int i17 = 0;
            while (i17 < numberOfThreads) {
                final int i18 = i17 == 0 ? (i17 * i16) + 1 : i17 * i16;
                final int i19 = i17 * i16;
                final int i20 = i19 + i16;
                int i21 = i17;
                final int i22 = i17 == numberOfThreads + (-1) ? i20 + 1 : i20;
                futureArr[i21] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_2D.48
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i23 = i18; i23 < i20; i23++) {
                            int i24 = DoubleFFT_2D.this.rows - i23;
                            double[] dArr8 = dArr[i23];
                            int i25 = DoubleFFT_2D.this.columns;
                            double[][] dArr9 = dArr;
                            dArr8[i25] = dArr9[i24][1];
                            dArr9[i23][DoubleFFT_2D.this.columns + 1] = -dArr[i24][0];
                        }
                        for (int i26 = i18; i26 < i20; i26++) {
                            int i27 = DoubleFFT_2D.this.rows - i26;
                            int i28 = DoubleFFT_2D.this.columns;
                            while (true) {
                                i28 += 2;
                                int i29 = i;
                                if (i28 < i29) {
                                    int i30 = i29 - i28;
                                    double[][] dArr10 = dArr;
                                    double[] dArr11 = dArr10[i26];
                                    double[] dArr12 = dArr10[i27];
                                    dArr11[i28] = dArr12[i30];
                                    dArr11[i28 + 1] = -dArr12[i30 + 1];
                                }
                            }
                        }
                        for (int i31 = i19; i31 < i22; i31++) {
                            int i32 = (DoubleFFT_2D.this.rows - i31) % DoubleFFT_2D.this.rows;
                            int i33 = 0;
                            while (true) {
                                int i34 = i;
                                if (i33 < i34) {
                                    int i35 = (i34 - i33) % i34;
                                    double[][] dArr13 = dArr;
                                    double[] dArr14 = dArr13[i32];
                                    double[] dArr15 = dArr13[i31];
                                    dArr14[i35] = dArr15[i33];
                                    dArr14[i35 + 1] = -dArr15[i33 + 1];
                                    i33 += 2;
                                }
                            }
                        }
                    }
                });
                i17 = i21 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        double[] dArr8 = dArr[0];
        int i23 = this.columns;
        dArr8[i23] = -dArr8[1];
        dArr8[1] = 0.0d;
        double[] dArr9 = dArr[i2];
        dArr9[i23] = -dArr9[1];
        dArr9[1] = 0.0d;
        dArr9[i23 + 1] = 0.0d;
    }
}
