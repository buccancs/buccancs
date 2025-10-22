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
import pl.edu.icm.jlargearrays.LargeArrayUtils;

/* loaded from: classes2.dex */
public class DoubleFFT_3D {
    private int columns;
    private long columnsl;
    private DoubleFFT_1D fftColumns;
    private DoubleFFT_1D fftRows;
    private DoubleFFT_1D fftSlices;
    private boolean isPowerOfTwo;
    private int rowStride;
    private long rowStridel;
    private int rows;
    private long rowsl;
    private int sliceStride;
    private long sliceStridel;
    private int slices;
    private long slicesl;
    private boolean useThreads;

    public DoubleFFT_3D(long j, long j2, long j3) {
        this.isPowerOfTwo = false;
        this.useThreads = false;
        if (j <= 1 || j2 <= 1 || j3 <= 1) {
            throw new IllegalArgumentException("slices, rows and columns must be greater than 1");
        }
        this.slices = (int) j;
        this.rows = (int) j2;
        int i = (int) j3;
        this.columns = i;
        this.slicesl = j;
        this.rowsl = j2;
        this.columnsl = j3;
        long j4 = j2 * j3;
        this.sliceStride = (int) j4;
        this.rowStride = i;
        this.sliceStridel = j4;
        this.rowStridel = j3;
        if (j * j2 * j3 >= CommonUtils.getThreadsBeginN_3D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2) && CommonUtils.isPowerOf2(j3)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays(((2 * j) * j2) * j3 > ((long) LargeArray.getMaxSizeOf32bitArray()));
        DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(j);
        this.fftSlices = doubleFFT_1D;
        if (j == j2) {
            this.fftRows = doubleFFT_1D;
        } else {
            this.fftRows = new DoubleFFT_1D(j2);
        }
        if (j == j3) {
            this.fftColumns = this.fftSlices;
        } else if (j2 == j3) {
            this.fftColumns = this.fftRows;
        } else {
            this.fftColumns = new DoubleFFT_1D(j3);
        }
    }

    public void complexForward(final double[] dArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft3da_subth2(0, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
            } else {
                xdft3da_sub2(0, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
            return;
        }
        int i6 = this.rows;
        int i7 = this.columns;
        this.sliceStride = i6 * 2 * i7;
        this.rowStride = i7 * 2;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && i6 >= numberOfThreads && i7 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + (-1) ? this.slices : i10 + i8;
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            int i13 = DoubleFFT_3D.this.sliceStride * i12;
                            for (int i14 = 0; i14 < DoubleFFT_3D.this.rows; i14++) {
                                DoubleFFT_3D.this.fftColumns.complexForward(dArr, (DoubleFFT_3D.this.rowStride * i14) + i13);
                            }
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            int i16 = DoubleFFT_3D.this.sliceStride * i15;
                            for (int i17 = 0; i17 < DoubleFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < DoubleFFT_3D.this.rows; i19++) {
                                    int i20 = i16 + i18 + (DoubleFFT_3D.this.rowStride * i19);
                                    int i21 = i19 * 2;
                                    double[] dArr3 = dArr;
                                    dArr2[i21] = dArr3[i20];
                                    dArr2[i21 + 1] = dArr3[i20 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2);
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.rows; i22++) {
                                    int i23 = i16 + i18 + (DoubleFFT_3D.this.rowStride * i22);
                                    int i24 = i22 * 2;
                                    double[] dArr4 = dArr;
                                    dArr4[i23] = dArr2[i24];
                                    dArr4[i23 + 1] = dArr2[i24 + 1];
                                }
                            }
                        }
                    }
                });
                i12++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i15 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i16 = i3 * i15;
                final int i17 = i3 == numberOfThreads + (-1) ? this.rows : i16 + i15;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.3
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i18 = i16; i18 < i17; i18++) {
                            int i19 = DoubleFFT_3D.this.rowStride * i18;
                            for (int i20 = 0; i20 < DoubleFFT_3D.this.columns; i20++) {
                                int i21 = i20 * 2;
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.slices; i22++) {
                                    int i23 = (DoubleFFT_3D.this.sliceStride * i22) + i19 + i21;
                                    int i24 = i22 * 2;
                                    double[] dArr3 = dArr;
                                    dArr2[i24] = dArr3[i23];
                                    dArr2[i24 + 1] = dArr3[i23 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2);
                                for (int i25 = 0; i25 < DoubleFFT_3D.this.slices; i25++) {
                                    int i26 = (DoubleFFT_3D.this.sliceStride * i25) + i19 + i21;
                                    int i27 = i25 * 2;
                                    double[] dArr4 = dArr;
                                    dArr4[i26] = dArr2[i27];
                                    dArr4[i26 + 1] = dArr2[i27 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i18 = 0; i18 < this.slices; i18++) {
                int i19 = this.sliceStride * i18;
                for (int i20 = 0; i20 < this.rows; i20++) {
                    this.fftColumns.complexForward(dArr, (this.rowStride * i20) + i19);
                }
            }
            double[] dArr2 = new double[this.rows * 2];
            int i21 = 0;
            while (true) {
                i = this.slices;
                if (i21 >= i) {
                    break;
                }
                int i22 = this.sliceStride * i21;
                for (int i23 = 0; i23 < this.columns; i23++) {
                    int i24 = i23 * 2;
                    for (int i25 = 0; i25 < this.rows; i25++) {
                        int i26 = i22 + i24 + (this.rowStride * i25);
                        int i27 = i25 * 2;
                        dArr2[i27] = dArr[i26];
                        dArr2[i27 + 1] = dArr[i26 + 1];
                    }
                    this.fftRows.complexForward(dArr2);
                    for (int i28 = 0; i28 < this.rows; i28++) {
                        int i29 = i22 + i24 + (this.rowStride * i28);
                        int i30 = i28 * 2;
                        dArr[i29] = dArr2[i30];
                        dArr[i29 + 1] = dArr2[i30 + 1];
                    }
                }
                i21++;
            }
            double[] dArr3 = new double[i * 2];
            for (int i31 = 0; i31 < this.rows; i31++) {
                int i32 = this.rowStride * i31;
                for (int i33 = 0; i33 < this.columns; i33++) {
                    int i34 = i33 * 2;
                    for (int i35 = 0; i35 < this.slices; i35++) {
                        int i36 = (this.sliceStride * i35) + i32 + i34;
                        int i37 = i35 * 2;
                        dArr3[i37] = dArr[i36];
                        dArr3[i37 + 1] = dArr[i36 + 1];
                    }
                    this.fftSlices.complexForward(dArr3);
                    for (int i38 = 0; i38 < this.slices; i38++) {
                        int i39 = (this.sliceStride * i38) + i32 + i34;
                        int i40 = i38 * 2;
                        dArr[i39] = dArr3[i40];
                        dArr[i39 + 1] = dArr3[i40 + 1];
                    }
                }
            }
        }
        int i41 = this.rows;
        int i42 = this.columns;
        this.sliceStride = i41 * i42;
        this.rowStride = i42;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void complexForward(final pl.edu.icm.jlargearrays.DoubleLargeArray r24) {
        /*
            Method dump skipped, instructions count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.complexForward(pl.edu.icm.jlargearrays.DoubleLargeArray):void");
    }

    public void complexForward(final double[][][] dArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft3da_subth2(0, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
            } else {
                xdft3da_sub2(0, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i6 = i2 / numberOfThreads;
            int i7 = 0;
            while (i7 < numberOfThreads) {
                final int i8 = i7 * i6;
                final int i9 = i7 == numberOfThreads + (-1) ? this.slices : i8 + i6;
                futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i8; i10 < i9; i10++) {
                            for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                DoubleFFT_3D.this.fftColumns.complexForward(dArr[i10][i11]);
                            }
                        }
                    }
                });
                i7++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i10 = 0;
            while (i10 < numberOfThreads) {
                final int i11 = i10 * i6;
                final int i12 = i10 == numberOfThreads + (-1) ? this.slices : i11 + i6;
                futureArr[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i13 = i11; i13 < i12; i13++) {
                            for (int i14 = 0; i14 < DoubleFFT_3D.this.columns; i14++) {
                                int i15 = i14 * 2;
                                for (int i16 = 0; i16 < DoubleFFT_3D.this.rows; i16++) {
                                    int i17 = i16 * 2;
                                    double[] dArr3 = dArr[i13][i16];
                                    dArr2[i17] = dArr3[i15];
                                    dArr2[i17 + 1] = dArr3[i15 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2);
                                for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    double[] dArr4 = dArr[i13][i18];
                                    dArr4[i15] = dArr2[i19];
                                    dArr4[i15 + 1] = dArr2[i19 + 1];
                                }
                            }
                        }
                    }
                });
                i10++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i13 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i14 = i3 * i13;
                final int i15 = i3 == numberOfThreads + (-1) ? this.rows : i14 + i13;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.9
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i16 = i14; i16 < i15; i16++) {
                            for (int i17 = 0; i17 < DoubleFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < DoubleFFT_3D.this.slices; i19++) {
                                    int i20 = i19 * 2;
                                    double[] dArr3 = dArr[i19][i16];
                                    dArr2[i20] = dArr3[i18];
                                    dArr2[i20 + 1] = dArr3[i18 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2);
                                for (int i21 = 0; i21 < DoubleFFT_3D.this.slices; i21++) {
                                    int i22 = i21 * 2;
                                    double[] dArr4 = dArr[i21][i16];
                                    dArr4[i18] = dArr2[i22];
                                    dArr4[i18 + 1] = dArr2[i22 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i16 = 0; i16 < this.slices; i16++) {
            for (int i17 = 0; i17 < this.rows; i17++) {
                this.fftColumns.complexForward(dArr[i16][i17]);
            }
        }
        double[] dArr2 = new double[this.rows * 2];
        int i18 = 0;
        while (true) {
            i = this.slices;
            if (i18 >= i) {
                break;
            }
            for (int i19 = 0; i19 < this.columns; i19++) {
                int i20 = i19 * 2;
                for (int i21 = 0; i21 < this.rows; i21++) {
                    int i22 = i21 * 2;
                    double[] dArr3 = dArr[i18][i21];
                    dArr2[i22] = dArr3[i20];
                    dArr2[i22 + 1] = dArr3[i20 + 1];
                }
                this.fftRows.complexForward(dArr2);
                for (int i23 = 0; i23 < this.rows; i23++) {
                    int i24 = i23 * 2;
                    double[] dArr4 = dArr[i18][i23];
                    dArr4[i20] = dArr2[i24];
                    dArr4[i20 + 1] = dArr2[i24 + 1];
                }
            }
            i18++;
        }
        double[] dArr5 = new double[i * 2];
        for (int i25 = 0; i25 < this.rows; i25++) {
            for (int i26 = 0; i26 < this.columns; i26++) {
                int i27 = i26 * 2;
                for (int i28 = 0; i28 < this.slices; i28++) {
                    int i29 = i28 * 2;
                    double[] dArr6 = dArr[i28][i25];
                    dArr5[i29] = dArr6[i27];
                    dArr5[i29 + 1] = dArr6[i27 + 1];
                }
                this.fftSlices.complexForward(dArr5);
                for (int i30 = 0; i30 < this.slices; i30++) {
                    int i31 = i30 * 2;
                    double[] dArr7 = dArr[i30][i25];
                    dArr7[i27] = dArr5[i31];
                    dArr7[i27 + 1] = dArr5[i31 + 1];
                }
            }
        }
    }

    public void complexInverse(final double[] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft3da_subth2(0, 1, dArr, z);
                cdft3db_subth(1, dArr, z);
            } else {
                xdft3da_sub2(0, 1, dArr, z);
                cdft3db_sub(1, dArr, z);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
            return;
        }
        int i6 = this.rows;
        int i7 = this.columns;
        this.sliceStride = i6 * 2 * i7;
        this.rowStride = i7 * 2;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && i6 >= numberOfThreads && i7 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i2 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + (-1) ? this.slices : i10 + i8;
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.10
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            int i13 = DoubleFFT_3D.this.sliceStride * i12;
                            for (int i14 = 0; i14 < DoubleFFT_3D.this.rows; i14++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr, (DoubleFFT_3D.this.rowStride * i14) + i13, z);
                            }
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.11
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            int i16 = DoubleFFT_3D.this.sliceStride * i15;
                            for (int i17 = 0; i17 < DoubleFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < DoubleFFT_3D.this.rows; i19++) {
                                    int i20 = i16 + i18 + (DoubleFFT_3D.this.rowStride * i19);
                                    int i21 = i19 * 2;
                                    double[] dArr3 = dArr;
                                    dArr2[i21] = dArr3[i20];
                                    dArr2[i21 + 1] = dArr3[i20 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, z);
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.rows; i22++) {
                                    int i23 = i16 + i18 + (DoubleFFT_3D.this.rowStride * i22);
                                    int i24 = i22 * 2;
                                    double[] dArr4 = dArr;
                                    dArr4[i23] = dArr2[i24];
                                    dArr4[i23 + 1] = dArr2[i24 + 1];
                                }
                            }
                        }
                    }
                });
                i12++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i15 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i16 = i3 * i15;
                final int i17 = i3 == numberOfThreads + (-1) ? this.rows : i16 + i15;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.12
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i18 = i16; i18 < i17; i18++) {
                            int i19 = DoubleFFT_3D.this.rowStride * i18;
                            for (int i20 = 0; i20 < DoubleFFT_3D.this.columns; i20++) {
                                int i21 = i20 * 2;
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.slices; i22++) {
                                    int i23 = (DoubleFFT_3D.this.sliceStride * i22) + i19 + i21;
                                    int i24 = i22 * 2;
                                    double[] dArr3 = dArr;
                                    dArr2[i24] = dArr3[i23];
                                    dArr2[i24 + 1] = dArr3[i23 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, z);
                                for (int i25 = 0; i25 < DoubleFFT_3D.this.slices; i25++) {
                                    int i26 = (DoubleFFT_3D.this.sliceStride * i25) + i19 + i21;
                                    int i27 = i25 * 2;
                                    double[] dArr4 = dArr;
                                    dArr4[i26] = dArr2[i27];
                                    dArr4[i26 + 1] = dArr2[i27 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i18 = 0; i18 < this.slices; i18++) {
                int i19 = this.sliceStride * i18;
                for (int i20 = 0; i20 < this.rows; i20++) {
                    this.fftColumns.complexInverse(dArr, (this.rowStride * i20) + i19, z);
                }
            }
            double[] dArr2 = new double[this.rows * 2];
            int i21 = 0;
            while (true) {
                i = this.slices;
                if (i21 >= i) {
                    break;
                }
                int i22 = this.sliceStride * i21;
                for (int i23 = 0; i23 < this.columns; i23++) {
                    int i24 = i23 * 2;
                    for (int i25 = 0; i25 < this.rows; i25++) {
                        int i26 = i22 + i24 + (this.rowStride * i25);
                        int i27 = i25 * 2;
                        dArr2[i27] = dArr[i26];
                        dArr2[i27 + 1] = dArr[i26 + 1];
                    }
                    this.fftRows.complexInverse(dArr2, z);
                    for (int i28 = 0; i28 < this.rows; i28++) {
                        int i29 = i22 + i24 + (this.rowStride * i28);
                        int i30 = i28 * 2;
                        dArr[i29] = dArr2[i30];
                        dArr[i29 + 1] = dArr2[i30 + 1];
                    }
                }
                i21++;
            }
            double[] dArr3 = new double[i * 2];
            for (int i31 = 0; i31 < this.rows; i31++) {
                int i32 = this.rowStride * i31;
                for (int i33 = 0; i33 < this.columns; i33++) {
                    int i34 = i33 * 2;
                    for (int i35 = 0; i35 < this.slices; i35++) {
                        int i36 = (this.sliceStride * i35) + i32 + i34;
                        int i37 = i35 * 2;
                        dArr3[i37] = dArr[i36];
                        dArr3[i37 + 1] = dArr[i36 + 1];
                    }
                    this.fftSlices.complexInverse(dArr3, z);
                    for (int i38 = 0; i38 < this.slices; i38++) {
                        int i39 = (this.sliceStride * i38) + i32 + i34;
                        int i40 = i38 * 2;
                        dArr[i39] = dArr3[i40];
                        dArr[i39 + 1] = dArr3[i40 + 1];
                    }
                }
            }
        }
        int i41 = this.rows;
        int i42 = this.columns;
        this.sliceStride = i41 * i42;
        this.rowStride = i42;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0166  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void complexInverse(final pl.edu.icm.jlargearrays.DoubleLargeArray r25, final boolean r26) {
        /*
            Method dump skipped, instructions count: 706
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.complexInverse(pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    public void complexInverse(final double[][][] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            int i4 = this.columns;
            int i5 = i4 * 2;
            this.columns = i5;
            this.sliceStride = this.rows * i5;
            this.rowStride = i5;
            if (numberOfThreads > 1 && this.useThreads) {
                xdft3da_subth2(0, 1, dArr, z);
                cdft3db_subth(1, dArr, z);
            } else {
                xdft3da_sub2(0, 1, dArr, z);
                cdft3db_sub(1, dArr, z);
            }
            this.columns = i4;
            this.sliceStride = this.rows * i4;
            this.rowStride = i4;
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i6 = i2 / numberOfThreads;
            int i7 = 0;
            while (i7 < numberOfThreads) {
                final int i8 = i7 * i6;
                final int i9 = i7 == numberOfThreads + (-1) ? this.slices : i8 + i6;
                futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.16
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i8; i10 < i9; i10++) {
                            for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr[i10][i11], z);
                            }
                        }
                    }
                });
                i7++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i10 = 0;
            while (i10 < numberOfThreads) {
                final int i11 = i10 * i6;
                final int i12 = i10 == numberOfThreads + (-1) ? this.slices : i11 + i6;
                futureArr[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.17
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i13 = i11; i13 < i12; i13++) {
                            for (int i14 = 0; i14 < DoubleFFT_3D.this.columns; i14++) {
                                int i15 = i14 * 2;
                                for (int i16 = 0; i16 < DoubleFFT_3D.this.rows; i16++) {
                                    int i17 = i16 * 2;
                                    double[] dArr3 = dArr[i13][i16];
                                    dArr2[i17] = dArr3[i15];
                                    dArr2[i17 + 1] = dArr3[i15 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, z);
                                for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    double[] dArr4 = dArr[i13][i18];
                                    dArr4[i15] = dArr2[i19];
                                    dArr4[i15 + 1] = dArr2[i19 + 1];
                                }
                            }
                        }
                    }
                });
                i10++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i13 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i14 = i3 * i13;
                final int i15 = i3 == numberOfThreads + (-1) ? this.rows : i14 + i13;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.18
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i16 = i14; i16 < i15; i16++) {
                            for (int i17 = 0; i17 < DoubleFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < DoubleFFT_3D.this.slices; i19++) {
                                    int i20 = i19 * 2;
                                    double[] dArr3 = dArr[i19][i16];
                                    dArr2[i20] = dArr3[i18];
                                    dArr2[i20 + 1] = dArr3[i18 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, z);
                                for (int i21 = 0; i21 < DoubleFFT_3D.this.slices; i21++) {
                                    int i22 = i21 * 2;
                                    double[] dArr4 = dArr[i21][i16];
                                    dArr4[i18] = dArr2[i22];
                                    dArr4[i18 + 1] = dArr2[i22 + 1];
                                }
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i16 = 0; i16 < this.slices; i16++) {
            for (int i17 = 0; i17 < this.rows; i17++) {
                this.fftColumns.complexInverse(dArr[i16][i17], z);
            }
        }
        double[] dArr2 = new double[this.rows * 2];
        int i18 = 0;
        while (true) {
            i = this.slices;
            if (i18 >= i) {
                break;
            }
            for (int i19 = 0; i19 < this.columns; i19++) {
                int i20 = i19 * 2;
                for (int i21 = 0; i21 < this.rows; i21++) {
                    int i22 = i21 * 2;
                    double[] dArr3 = dArr[i18][i21];
                    dArr2[i22] = dArr3[i20];
                    dArr2[i22 + 1] = dArr3[i20 + 1];
                }
                this.fftRows.complexInverse(dArr2, z);
                for (int i23 = 0; i23 < this.rows; i23++) {
                    int i24 = i23 * 2;
                    double[] dArr4 = dArr[i18][i23];
                    dArr4[i20] = dArr2[i24];
                    dArr4[i20 + 1] = dArr2[i24 + 1];
                }
            }
            i18++;
        }
        double[] dArr5 = new double[i * 2];
        for (int i25 = 0; i25 < this.rows; i25++) {
            for (int i26 = 0; i26 < this.columns; i26++) {
                int i27 = i26 * 2;
                for (int i28 = 0; i28 < this.slices; i28++) {
                    int i29 = i28 * 2;
                    double[] dArr6 = dArr[i28][i25];
                    dArr5[i29] = dArr6[i27];
                    dArr5[i29 + 1] = dArr6[i27 + 1];
                }
                this.fftSlices.complexInverse(dArr5, z);
                for (int i30 = 0; i30 < this.slices; i30++) {
                    int i31 = i30 * 2;
                    double[] dArr7 = dArr[i30][i25];
                    dArr7[i27] = dArr5[i31];
                    dArr7[i27 + 1] = dArr5[i31 + 1];
                }
            }
        }
    }

    public void realForward(double[] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft3da_subth1(1, -1, dArr, true);
            cdft3db_subth(-1, dArr, true);
            rdft3d_sub(1, dArr);
        } else {
            xdft3da_sub1(1, -1, dArr, true);
            cdft3db_sub(-1, dArr, true);
            rdft3d_sub(1, dArr);
        }
    }

    public void realForward(DoubleLargeArray doubleLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft3da_subth1(1L, -1, doubleLargeArray, true);
            cdft3db_subth(-1, doubleLargeArray, true);
            rdft3d_sub(1, doubleLargeArray);
        } else {
            xdft3da_sub1(1L, -1, doubleLargeArray, true);
            cdft3db_sub(-1, doubleLargeArray, true);
            rdft3d_sub(1, doubleLargeArray);
        }
    }

    public void realForward(double[][][] dArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft3da_subth1(1, -1, dArr, true);
            cdft3db_subth(-1, dArr, true);
            rdft3d_sub(1, dArr);
        } else {
            xdft3da_sub1(1, -1, dArr, true);
            cdft3db_sub(-1, dArr, true);
            rdft3d_sub(1, dArr);
        }
    }

    public void realForwardFull(double[] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_sub2(1, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realForwardFull(DoubleLargeArray doubleLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1L, -1, doubleLargeArray, true);
                cdft3db_subth(-1, doubleLargeArray, true);
                rdft3d_sub(1, doubleLargeArray);
            } else {
                xdft3da_sub2(1L, -1, doubleLargeArray, true);
                cdft3db_sub(-1, doubleLargeArray, true);
                rdft3d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealForwardFull(doubleLargeArray);
    }

    public void realForwardFull(double[][][] dArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, -1, dArr, true);
                cdft3db_subth(-1, dArr, true);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_sub2(1, -1, dArr, true);
                cdft3db_sub(-1, dArr, true);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealForwardFull(dArr);
    }

    public void realInverse(double[] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft3d_sub(-1, dArr);
            cdft3db_subth(1, dArr, z);
            xdft3da_subth1(1, 1, dArr, z);
        } else {
            rdft3d_sub(-1, dArr);
            cdft3db_sub(1, dArr, z);
            xdft3da_sub1(1, 1, dArr, z);
        }
    }

    public void realInverse(DoubleLargeArray doubleLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft3d_sub(-1, doubleLargeArray);
            cdft3db_subth(1, doubleLargeArray, z);
            xdft3da_subth1(1L, 1, doubleLargeArray, z);
        } else {
            rdft3d_sub(-1, doubleLargeArray);
            cdft3db_sub(1, doubleLargeArray, z);
            xdft3da_sub1(1L, 1, doubleLargeArray, z);
        }
    }

    public void realInverse(double[][][] dArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft3d_sub(-1, dArr);
            cdft3db_subth(1, dArr, z);
            xdft3da_subth1(1, 1, dArr, z);
        } else {
            rdft3d_sub(-1, dArr);
            cdft3db_sub(1, dArr, z);
            xdft3da_sub1(1, 1, dArr, z);
        }
    }

    public void realInverseFull(double[] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, 1, dArr, z);
                cdft3db_subth(1, dArr, z);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_sub2(1, 1, dArr, z);
                cdft3db_sub(1, dArr, z);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    public void realInverseFull(DoubleLargeArray doubleLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1L, 1, doubleLargeArray, z);
                cdft3db_subth(1, doubleLargeArray, z);
                rdft3d_sub(1, doubleLargeArray);
            } else {
                xdft3da_sub2(1L, 1, doubleLargeArray, z);
                cdft3db_sub(1, doubleLargeArray, z);
                rdft3d_sub(1, doubleLargeArray);
            }
            fillSymmetric(doubleLargeArray);
            return;
        }
        mixedRadixRealInverseFull(doubleLargeArray, z);
    }

    public void realInverseFull(double[][][] dArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, 1, dArr, z);
                cdft3db_subth(1, dArr, z);
                rdft3d_sub(1, dArr);
            } else {
                xdft3da_sub2(1, 1, dArr, z);
                cdft3db_sub(1, dArr, z);
                rdft3d_sub(1, dArr);
            }
            fillSymmetric(dArr);
            return;
        }
        mixedRadixRealInverseFull(dArr, z);
    }

    private void mixedRadixRealForwardFull(final double[][][] dArr) {
        int i;
        int i2;
        int i3;
        int i4 = this.rows;
        double[] dArr2 = new double[i4 * 2];
        int i5 = (i4 / 2) + 1;
        final int i6 = this.columns * 2;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        final int i7 = i;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && (i3 = this.slices) >= numberOfThreads && this.columns >= numberOfThreads && i5 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i3 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + (-1) ? this.slices : i10 + i8;
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.19
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                DoubleFFT_3D.this.fftColumns.realForwardFull(dArr[i12][i13]);
                            }
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.20
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr3 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            for (int i16 = 0; i16 < DoubleFFT_3D.this.columns; i16++) {
                                int i17 = i16 * 2;
                                for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    double[] dArr4 = dArr[i15][i18];
                                    dArr3[i19] = dArr4[i17];
                                    dArr3[i19 + 1] = dArr4[i17 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr3);
                                for (int i20 = 0; i20 < DoubleFFT_3D.this.rows; i20++) {
                                    int i21 = i20 * 2;
                                    double[] dArr5 = dArr[i15][i20];
                                    dArr5[i17] = dArr3[i21];
                                    dArr5[i17 + 1] = dArr3[i21 + 1];
                                }
                            }
                        }
                    }
                });
                i12++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i15 = i5 / numberOfThreads;
            int i16 = 0;
            while (i16 < numberOfThreads) {
                final int i17 = i16 * i15;
                final int i18 = i16 == numberOfThreads + (-1) ? i5 : i17 + i15;
                futureArr[i16] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.21
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr3 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i19 = i17; i19 < i18; i19++) {
                            for (int i20 = 0; i20 < DoubleFFT_3D.this.columns; i20++) {
                                int i21 = i20 * 2;
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.slices; i22++) {
                                    int i23 = i22 * 2;
                                    double[] dArr4 = dArr[i22][i19];
                                    dArr3[i23] = dArr4[i21];
                                    dArr3[i23 + 1] = dArr4[i21 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr3);
                                for (int i24 = 0; i24 < DoubleFFT_3D.this.slices; i24++) {
                                    int i25 = i24 * 2;
                                    double[] dArr5 = dArr[i24][i19];
                                    dArr5[i21] = dArr3[i25];
                                    dArr5[i21 + 1] = dArr3[i25 + 1];
                                }
                            }
                        }
                    }
                });
                i16++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
            int i19 = this.slices / numberOfThreads;
            int i20 = 0;
            while (i20 < numberOfThreads) {
                final int i21 = i20 * i19;
                final int i22 = i20 == numberOfThreads + (-1) ? this.slices : i21 + i19;
                int i23 = i20;
                futureArr[i23] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.22
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i24 = i21; i24 < i22; i24++) {
                            int i25 = (DoubleFFT_3D.this.slices - i24) % DoubleFFT_3D.this.slices;
                            for (int i26 = 1; i26 < i7; i26++) {
                                int i27 = DoubleFFT_3D.this.rows - i26;
                                for (int i28 = 0; i28 < DoubleFFT_3D.this.columns; i28++) {
                                    int i29 = i28 * 2;
                                    int i30 = i6;
                                    int i31 = i30 - i29;
                                    double[][][] dArr3 = dArr;
                                    double[] dArr4 = dArr3[i25][i27];
                                    double[] dArr5 = dArr3[i24][i26];
                                    dArr4[i31 % i30] = dArr5[i29];
                                    dArr4[(i31 + 1) % i30] = -dArr5[i29 + 1];
                                }
                            }
                        }
                    }
                });
                i20 = i23 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e8);
                return;
            }
        }
        for (int i24 = 0; i24 < this.slices; i24++) {
            for (int i25 = 0; i25 < this.rows; i25++) {
                this.fftColumns.realForwardFull(dArr[i24][i25]);
            }
        }
        int i26 = 0;
        while (true) {
            i2 = this.slices;
            if (i26 >= i2) {
                break;
            }
            for (int i27 = 0; i27 < this.columns; i27++) {
                int i28 = i27 * 2;
                for (int i29 = 0; i29 < this.rows; i29++) {
                    int i30 = i29 * 2;
                    double[] dArr3 = dArr[i26][i29];
                    dArr2[i30] = dArr3[i28];
                    dArr2[i30 + 1] = dArr3[i28 + 1];
                }
                this.fftRows.complexForward(dArr2);
                for (int i31 = 0; i31 < this.rows; i31++) {
                    int i32 = i31 * 2;
                    double[] dArr4 = dArr[i26][i31];
                    dArr4[i28] = dArr2[i32];
                    dArr4[i28 + 1] = dArr2[i32 + 1];
                }
            }
            i26++;
        }
        double[] dArr5 = new double[i2 * 2];
        for (int i33 = 0; i33 < i5; i33++) {
            for (int i34 = 0; i34 < this.columns; i34++) {
                int i35 = i34 * 2;
                for (int i36 = 0; i36 < this.slices; i36++) {
                    int i37 = i36 * 2;
                    double[] dArr6 = dArr[i36][i33];
                    dArr5[i37] = dArr6[i35];
                    dArr5[i37 + 1] = dArr6[i35 + 1];
                }
                this.fftSlices.complexForward(dArr5);
                for (int i38 = 0; i38 < this.slices; i38++) {
                    int i39 = i38 * 2;
                    double[] dArr7 = dArr[i38][i33];
                    dArr7[i35] = dArr5[i39];
                    dArr7[i35 + 1] = dArr5[i39 + 1];
                }
            }
        }
        int i40 = 0;
        while (true) {
            int i41 = this.slices;
            if (i40 >= i41) {
                return;
            }
            int i42 = (i41 - i40) % i41;
            for (int i43 = 1; i43 < i7; i43++) {
                int i44 = this.rows - i43;
                for (int i45 = 0; i45 < this.columns; i45++) {
                    int i46 = i45 * 2;
                    int i47 = i6 - i46;
                    double[] dArr8 = dArr[i42][i44];
                    double[] dArr9 = dArr[i40][i43];
                    dArr8[i47 % i6] = dArr9[i46];
                    dArr8[(i47 + 1) % i6] = -dArr9[i46 + 1];
                }
            }
            i40++;
        }
    }

    private void mixedRadixRealInverseFull(final double[][][] dArr, final boolean z) {
        int i;
        int i2;
        int i3;
        int i4 = this.rows;
        double[] dArr2 = new double[i4 * 2];
        int i5 = (i4 / 2) + 1;
        final int i6 = this.columns * 2;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        final int i7 = i;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && (i3 = this.slices) >= numberOfThreads && this.columns >= numberOfThreads && i5 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i8 = i3 / numberOfThreads;
            int i9 = 0;
            while (i9 < numberOfThreads) {
                final int i10 = i9 * i8;
                final int i11 = i9 == numberOfThreads + (-1) ? this.slices : i10 + i8;
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.23
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                DoubleFFT_3D.this.fftColumns.realInverseFull(dArr[i12][i13], z);
                            }
                        }
                    }
                });
                i9++;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.24
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr3 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            for (int i16 = 0; i16 < DoubleFFT_3D.this.columns; i16++) {
                                int i17 = i16 * 2;
                                for (int i18 = 0; i18 < DoubleFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    double[] dArr4 = dArr[i15][i18];
                                    dArr3[i19] = dArr4[i17];
                                    dArr3[i19 + 1] = dArr4[i17 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr3, z);
                                for (int i20 = 0; i20 < DoubleFFT_3D.this.rows; i20++) {
                                    int i21 = i20 * 2;
                                    double[] dArr5 = dArr[i15][i20];
                                    dArr5[i17] = dArr3[i21];
                                    dArr5[i17 + 1] = dArr3[i21 + 1];
                                }
                            }
                        }
                    }
                });
                i12++;
                str = str;
            }
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            int i15 = i5 / numberOfThreads;
            int i16 = 0;
            while (i16 < numberOfThreads) {
                final int i17 = i16 * i15;
                final int i18 = i16 == numberOfThreads + (-1) ? i5 : i17 + i15;
                int i19 = i16;
                futureArr[i19] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.25
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr3 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i20 = i17; i20 < i18; i20++) {
                            for (int i21 = 0; i21 < DoubleFFT_3D.this.columns; i21++) {
                                int i22 = i21 * 2;
                                for (int i23 = 0; i23 < DoubleFFT_3D.this.slices; i23++) {
                                    int i24 = i23 * 2;
                                    double[] dArr4 = dArr[i23][i20];
                                    dArr3[i24] = dArr4[i22];
                                    dArr3[i24 + 1] = dArr4[i22 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr3, z);
                                for (int i25 = 0; i25 < DoubleFFT_3D.this.slices; i25++) {
                                    int i26 = i25 * 2;
                                    double[] dArr5 = dArr[i25][i20];
                                    dArr5[i22] = dArr3[i26];
                                    dArr5[i22 + 1] = dArr3[i26 + 1];
                                }
                            }
                        }
                    }
                });
                i16 = i19 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i20 = this.slices / numberOfThreads;
            int i21 = 0;
            while (i21 < numberOfThreads) {
                final int i22 = i21 * i20;
                final int i23 = i21 == numberOfThreads + (-1) ? this.slices : i22 + i20;
                futureArr[i21] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.26
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i24 = i22; i24 < i23; i24++) {
                            int i25 = (DoubleFFT_3D.this.slices - i24) % DoubleFFT_3D.this.slices;
                            for (int i26 = 1; i26 < i7; i26++) {
                                int i27 = DoubleFFT_3D.this.rows - i26;
                                for (int i28 = 0; i28 < DoubleFFT_3D.this.columns; i28++) {
                                    int i29 = i28 * 2;
                                    int i30 = i6;
                                    int i31 = i30 - i29;
                                    double[][][] dArr3 = dArr;
                                    double[] dArr4 = dArr3[i25][i27];
                                    double[] dArr5 = dArr3[i24][i26];
                                    dArr4[i31 % i30] = dArr5[i29];
                                    dArr4[(i31 + 1) % i30] = -dArr5[i29 + 1];
                                }
                            }
                        }
                    }
                });
                i21++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                return;
            }
        }
        for (int i24 = 0; i24 < this.slices; i24++) {
            for (int i25 = 0; i25 < this.rows; i25++) {
                this.fftColumns.realInverseFull(dArr[i24][i25], z);
            }
        }
        int i26 = 0;
        while (true) {
            i2 = this.slices;
            if (i26 >= i2) {
                break;
            }
            for (int i27 = 0; i27 < this.columns; i27++) {
                int i28 = i27 * 2;
                for (int i29 = 0; i29 < this.rows; i29++) {
                    int i30 = i29 * 2;
                    double[] dArr3 = dArr[i26][i29];
                    dArr2[i30] = dArr3[i28];
                    dArr2[i30 + 1] = dArr3[i28 + 1];
                }
                this.fftRows.complexInverse(dArr2, z);
                for (int i31 = 0; i31 < this.rows; i31++) {
                    int i32 = i31 * 2;
                    double[] dArr4 = dArr[i26][i31];
                    dArr4[i28] = dArr2[i32];
                    dArr4[i28 + 1] = dArr2[i32 + 1];
                }
            }
            i26++;
        }
        double[] dArr5 = new double[i2 * 2];
        for (int i33 = 0; i33 < i5; i33++) {
            for (int i34 = 0; i34 < this.columns; i34++) {
                int i35 = i34 * 2;
                for (int i36 = 0; i36 < this.slices; i36++) {
                    int i37 = i36 * 2;
                    double[] dArr6 = dArr[i36][i33];
                    dArr5[i37] = dArr6[i35];
                    dArr5[i37 + 1] = dArr6[i35 + 1];
                }
                this.fftSlices.complexInverse(dArr5, z);
                for (int i38 = 0; i38 < this.slices; i38++) {
                    int i39 = i38 * 2;
                    double[] dArr7 = dArr[i38][i33];
                    dArr7[i35] = dArr5[i39];
                    dArr7[i35 + 1] = dArr5[i39 + 1];
                }
            }
        }
        int i40 = 0;
        while (true) {
            int i41 = this.slices;
            if (i40 >= i41) {
                return;
            }
            int i42 = (i41 - i40) % i41;
            for (int i43 = 1; i43 < i7; i43++) {
                int i44 = this.rows - i43;
                for (int i45 = 0; i45 < this.columns; i45++) {
                    int i46 = i45 * 2;
                    int i47 = i6 - i46;
                    double[] dArr8 = dArr[i42][i44];
                    double[] dArr9 = dArr[i40][i43];
                    dArr8[i47 % i6] = dArr9[i46];
                    dArr8[(i47 + 1) % i6] = -dArr9[i46 + 1];
                }
            }
            i40++;
        }
    }

    private void mixedRadixRealForwardFull(final double[] dArr) {
        int i;
        int i2;
        final int i3 = this.columns * 2;
        double[] dArr2 = new double[i3];
        int i4 = this.rows;
        int i5 = 1;
        int i6 = (i4 / 2) + 1;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        int i7 = i;
        final int i8 = this.sliceStride * 2;
        final int i9 = this.rowStride * 2;
        int i10 = this.slices / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && i10 >= numberOfThreads && this.columns >= numberOfThreads && i6 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i11 = i10 / numberOfThreads;
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = (this.slices - 1) - (i12 * i11);
                final int i14 = i12 == numberOfThreads + (-1) ? i10 + 1 : i13 - i11;
                Future[] futureArr2 = futureArr;
                futureArr2[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.27
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr3 = new double[i3];
                        for (int i15 = i13; i15 >= i14; i15--) {
                            int i16 = DoubleFFT_3D.this.sliceStride * i15;
                            int i17 = i8 * i15;
                            for (int i18 = DoubleFFT_3D.this.rows - 1; i18 >= 0; i18--) {
                                System.arraycopy(dArr, (DoubleFFT_3D.this.rowStride * i18) + i16, dArr3, 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realForwardFull(dArr3);
                                System.arraycopy(dArr3, 0, dArr, (i9 * i18) + i17, i3);
                            }
                        }
                    }
                });
                i12++;
                i10 = i10;
                futureArr = futureArr2;
                numberOfThreads = numberOfThreads;
            }
            Future[] futureArr3 = futureArr;
            int i15 = numberOfThreads;
            int i16 = i10;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i17 = i16 + 1;
            final double[][][] dArr3 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, i17, this.rows, i3);
            int i18 = i15;
            int i19 = 0;
            while (i19 < i18) {
                final int i20 = i19 * i11;
                final int i21 = i19 == i18 + (-1) ? i17 : i20 + i11;
                futureArr3[i19] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.28
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i22 = i20; i22 < i21; i22++) {
                            int i23 = DoubleFFT_3D.this.sliceStride * i22;
                            for (int i24 = 0; i24 < DoubleFFT_3D.this.rows; i24++) {
                                System.arraycopy(dArr, (DoubleFFT_3D.this.rowStride * i24) + i23, dArr3[i22][i24], 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realForwardFull(dArr3[i22][i24]);
                            }
                        }
                    }
                });
                i19++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i22 = 0;
            while (i22 < i18) {
                final int i23 = i22 * i11;
                final int i24 = i22 == i18 + (-1) ? i17 : i23 + i11;
                futureArr3[i22] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.29
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i25 = i23; i25 < i24; i25++) {
                            int i26 = i8 * i25;
                            for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                System.arraycopy(dArr3[i25][i27], 0, dArr, (i9 * i27) + i26, i3);
                            }
                        }
                    }
                });
                i22++;
                i18 = i18;
                str = str;
                i7 = i7;
                i17 = i17;
            }
            int i25 = i18;
            final int i26 = i7;
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i27 = this.slices / i25;
            int i28 = 0;
            while (i28 < i25) {
                final int i29 = i28 * i27;
                final int i30 = i28 == i25 + (-1) ? this.slices : i29 + i27;
                futureArr3[i28] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.30
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr4 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i31 = i29; i31 < i30; i31++) {
                            int i32 = i8 * i31;
                            for (int i33 = 0; i33 < DoubleFFT_3D.this.columns; i33++) {
                                int i34 = i33 * 2;
                                for (int i35 = 0; i35 < DoubleFFT_3D.this.rows; i35++) {
                                    int i36 = (i9 * i35) + i32 + i34;
                                    int i37 = i35 * 2;
                                    double[] dArr5 = dArr;
                                    dArr4[i37] = dArr5[i36];
                                    dArr4[i37 + 1] = dArr5[i36 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr4);
                                for (int i38 = 0; i38 < DoubleFFT_3D.this.rows; i38++) {
                                    int i39 = (i9 * i38) + i32 + i34;
                                    int i40 = i38 * 2;
                                    double[] dArr6 = dArr;
                                    dArr6[i39] = dArr4[i40];
                                    dArr6[i39 + 1] = dArr4[i40 + 1];
                                }
                            }
                        }
                    }
                });
                i28++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
            }
            int i31 = i6 / i25;
            int i32 = 0;
            while (i32 < i25) {
                final int i33 = i32 * i31;
                final int i34 = i32 == i25 + (-1) ? i6 : i33 + i31;
                futureArr3[i32] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.31
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr4 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i35 = i33; i35 < i34; i35++) {
                            int i36 = i9 * i35;
                            for (int i37 = 0; i37 < DoubleFFT_3D.this.columns; i37++) {
                                int i38 = i37 * 2;
                                for (int i39 = 0; i39 < DoubleFFT_3D.this.slices; i39++) {
                                    int i40 = i39 * 2;
                                    int i41 = (i8 * i39) + i36 + i38;
                                    double[] dArr5 = dArr;
                                    dArr4[i40] = dArr5[i41];
                                    dArr4[i40 + 1] = dArr5[i41 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr4);
                                for (int i42 = 0; i42 < DoubleFFT_3D.this.slices; i42++) {
                                    int i43 = i42 * 2;
                                    int i44 = (i8 * i42) + i36 + i38;
                                    double[] dArr6 = dArr;
                                    dArr6[i44] = dArr4[i43];
                                    dArr6[i44 + 1] = dArr4[i43 + 1];
                                }
                            }
                        }
                    }
                });
                i32++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e9) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
            }
            int i35 = this.slices / i25;
            int i36 = 0;
            while (i36 < i25) {
                final int i37 = i36 * i35;
                final int i38 = i36 == i25 + (-1) ? this.slices : i37 + i35;
                futureArr3[i36] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.32
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i39 = i37; i39 < i38; i39++) {
                            int i40 = (DoubleFFT_3D.this.slices - i39) % DoubleFFT_3D.this.slices;
                            int i41 = i8;
                            int i42 = i40 * i41;
                            int i43 = i41 * i39;
                            for (int i44 = 1; i44 < i26; i44++) {
                                int i45 = DoubleFFT_3D.this.rows - i44;
                                int i46 = i9;
                                int i47 = i45 * i46;
                                int i48 = i46 * i44;
                                int i49 = i47 + i42;
                                for (int i50 = 0; i50 < DoubleFFT_3D.this.columns; i50++) {
                                    int i51 = i50 * 2;
                                    int i52 = i3;
                                    int i53 = i52 - i51;
                                    int i54 = i43 + i48 + i51;
                                    double[] dArr4 = dArr;
                                    dArr4[(i53 % i52) + i49] = dArr4[i54];
                                    dArr4[((i53 + 1) % i52) + i49] = -dArr4[i54 + 1];
                                }
                            }
                        }
                    }
                });
                i36++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e11) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e11);
                return;
            } catch (ExecutionException e12) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e12);
                return;
            }
        }
        int i39 = i7;
        for (int i40 = this.slices - 1; i40 >= 0; i40--) {
            int i41 = this.sliceStride * i40;
            int i42 = i40 * i8;
            for (int i43 = this.rows - 1; i43 >= 0; i43--) {
                System.arraycopy(dArr, (this.rowStride * i43) + i41, dArr2, 0, this.columns);
                this.fftColumns.realForwardFull(dArr2);
                System.arraycopy(dArr2, 0, dArr, (i43 * i9) + i42, i3);
            }
        }
        double[] dArr4 = new double[this.rows * 2];
        int i44 = 0;
        while (true) {
            i2 = this.slices;
            if (i44 >= i2) {
                break;
            }
            int i45 = i44 * i8;
            for (int i46 = 0; i46 < this.columns; i46++) {
                int i47 = i46 * 2;
                for (int i48 = 0; i48 < this.rows; i48++) {
                    int i49 = i48 * 2;
                    int i50 = (i48 * i9) + i45 + i47;
                    dArr4[i49] = dArr[i50];
                    dArr4[i49 + 1] = dArr[i50 + 1];
                }
                this.fftRows.complexForward(dArr4);
                for (int i51 = 0; i51 < this.rows; i51++) {
                    int i52 = i51 * 2;
                    int i53 = (i51 * i9) + i45 + i47;
                    dArr[i53] = dArr4[i52];
                    dArr[i53 + 1] = dArr4[i52 + 1];
                }
            }
            i44++;
        }
        double[] dArr5 = new double[i2 * 2];
        for (int i54 = 0; i54 < i6; i54++) {
            int i55 = i54 * i9;
            for (int i56 = 0; i56 < this.columns; i56++) {
                int i57 = i56 * 2;
                for (int i58 = 0; i58 < this.slices; i58++) {
                    int i59 = i58 * 2;
                    int i60 = (i58 * i8) + i55 + i57;
                    dArr5[i59] = dArr[i60];
                    dArr5[i59 + 1] = dArr[i60 + 1];
                }
                this.fftSlices.complexForward(dArr5);
                for (int i61 = 0; i61 < this.slices; i61++) {
                    int i62 = i61 * 2;
                    int i63 = (i61 * i8) + i55 + i57;
                    dArr[i63] = dArr5[i62];
                    dArr[i63 + 1] = dArr5[i62 + 1];
                }
            }
        }
        int i64 = 0;
        while (true) {
            int i65 = this.slices;
            if (i64 >= i65) {
                return;
            }
            int i66 = ((i65 - i64) % i65) * i8;
            int i67 = i64 * i8;
            int i68 = i39;
            int i69 = 1;
            while (i69 < i68) {
                int i70 = i69 * i9;
                int i71 = ((this.rows - i69) * i9) + i66;
                int i72 = 0;
                while (i72 < this.columns) {
                    int i73 = i72 * 2;
                    int i74 = i3 - i73;
                    int i75 = i67 + i70 + i73;
                    dArr[(i74 % i3) + i71] = dArr[i75];
                    dArr[((i74 + i5) % i3) + i71] = -dArr[i75 + i5];
                    i72++;
                    i5 = 1;
                }
                i69++;
                i5 = 1;
            }
            i64++;
            i39 = i68;
            i5 = 1;
        }
    }

    private void mixedRadixRealForwardFull(final DoubleLargeArray doubleLargeArray) {
        long j;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j3);
        long j4 = this.rowsl;
        long j5 = (j4 / 2) + 1;
        if (j4 % 2 == 0) {
            j = j4 / 2;
        } else {
            j = (j4 + 1) / 2;
        }
        final long j6 = j;
        final long j7 = this.sliceStridel * 2;
        final long j8 = this.rowStridel * 2;
        long j9 = this.slicesl / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads) {
            long j10 = numberOfThreads;
            if (j9 >= j10 && this.columnsl >= j10 && j5 >= j10) {
                Future[] futureArr = new Future[numberOfThreads];
                long j11 = j9 / j10;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j12 = (this.slicesl - 1) - (i * j11);
                    final long j13 = i == numberOfThreads + (-1) ? j9 + 1 : j12 - j11;
                    final long j14 = j3;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.33
                        @Override // java.lang.Runnable
                        public void run() {
                            DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j14);
                            for (long j15 = j12; j15 >= j13; j15--) {
                                long j16 = j15 * DoubleFFT_3D.this.sliceStridel;
                                long j17 = j15 * j7;
                                for (long j18 = DoubleFFT_3D.this.rowsl - 1; j18 >= 0; j18--) {
                                    LargeArrayUtils.arraycopy(doubleLargeArray, (DoubleFFT_3D.this.rowStridel * j18) + j16, doubleLargeArray3, 0L, DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_3D.this.fftColumns.realForwardFull(doubleLargeArray3);
                                    LargeArrayUtils.arraycopy(doubleLargeArray3, 0L, doubleLargeArray, (j8 * j18) + j17, j14);
                                }
                            }
                        }
                    });
                    i++;
                    numberOfThreads = numberOfThreads;
                    futureArr = futureArr2;
                    j3 = j3;
                    j10 = j10;
                }
                long j15 = j10;
                Future[] futureArr3 = futureArr;
                int i2 = numberOfThreads;
                final long j16 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                long j17 = j9 + 1;
                final DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.rowsl * j17 * j16);
                int i3 = 0;
                while (i3 < i2) {
                    final long j18 = i3 * j11;
                    final long j19 = i3 == i2 + (-1) ? j17 : j18 + j11;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.34
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j20 = j18; j20 < j19; j20++) {
                                long j21 = DoubleFFT_3D.this.sliceStridel * j20;
                                for (long j22 = 0; j22 < DoubleFFT_3D.this.rowsl; j22++) {
                                    DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                                    long j23 = j21 + (DoubleFFT_3D.this.rowStridel * j22);
                                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray3;
                                    long j24 = DoubleFFT_3D.this.rowsl * j20;
                                    long j25 = j16;
                                    LargeArrayUtils.arraycopy(doubleLargeArray4, j23, doubleLargeArray5, (j24 * j25) + (j25 * j22), DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_1D doubleFFT_1D = DoubleFFT_3D.this.fftColumns;
                                    DoubleLargeArray doubleLargeArray6 = doubleLargeArray3;
                                    long j26 = DoubleFFT_3D.this.rowsl * j20;
                                    long j27 = j16;
                                    doubleFFT_1D.realForwardFull(doubleLargeArray6, (j26 * j27) + (j27 * j22));
                                }
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e3) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                }
                int i4 = 0;
                while (i4 < i2) {
                    final long j20 = i4 * j11;
                    final long j21 = i4 == i2 + (-1) ? j17 : j20 + j11;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.35
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j22 = j20; j22 < j21; j22++) {
                                long j23 = j7 * j22;
                                for (long j24 = 0; j24 < DoubleFFT_3D.this.rowsl; j24++) {
                                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
                                    long j25 = DoubleFFT_3D.this.rowsl * j22;
                                    long j26 = j16;
                                    LargeArrayUtils.arraycopy(doubleLargeArray5, (j25 * j26) + (j24 * j26), doubleLargeArray, j23 + (j8 * j24), j26);
                                }
                            }
                        }
                    });
                    i4++;
                    str = str;
                    doubleLargeArray3 = doubleLargeArray3;
                    i2 = i2;
                }
                String str2 = str;
                int i5 = i2;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e5) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                }
                DoubleFFT_3D doubleFFT_3D = this;
                long j22 = doubleFFT_3D.slicesl / j15;
                int i6 = i5;
                int i7 = 0;
                while (i7 < i6) {
                    final long j23 = i7 * j22;
                    final long j24 = i7 == i6 + (-1) ? doubleFFT_3D.slicesl : j23 + j22;
                    futureArr3[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.36
                        @Override // java.lang.Runnable
                        public void run() {
                            long j25 = 2;
                            DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(DoubleFFT_3D.this.rowsl * 2, false);
                            long j26 = j23;
                            while (j26 < j24) {
                                long j27 = j7 * j26;
                                long j28 = 0;
                                while (j28 < DoubleFFT_3D.this.columnsl) {
                                    long j29 = j28 * j25;
                                    long j30 = 0;
                                    while (j30 < DoubleFFT_3D.this.rowsl) {
                                        long j31 = (j8 * j30) + j27 + j29;
                                        long j32 = j30 * j25;
                                        doubleLargeArray5.setDouble(j32, doubleLargeArray.getDouble(j31));
                                        doubleLargeArray5.setDouble(j32 + 1, doubleLargeArray.getDouble(j31 + 1));
                                        j30++;
                                        j26 = j26;
                                        j25 = 2;
                                    }
                                    long j33 = j26;
                                    DoubleFFT_3D.this.fftRows.complexForward(doubleLargeArray5);
                                    long j34 = 0;
                                    while (j34 < DoubleFFT_3D.this.rowsl) {
                                        long j35 = (j8 * j34) + j27 + j29;
                                        long j36 = j34 * 2;
                                        doubleLargeArray.setDouble(j35, doubleLargeArray5.getDouble(j36));
                                        doubleLargeArray.setDouble(j35 + 1, doubleLargeArray5.getDouble(j36 + 1));
                                        j34++;
                                        j27 = j27;
                                    }
                                    j28++;
                                    j27 = j27;
                                    j26 = j33;
                                    j25 = 2;
                                }
                                j26++;
                                j25 = 2;
                            }
                        }
                    });
                    i7++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e7) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                } catch (ExecutionException e8) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                }
                long j25 = j5 / j15;
                int i8 = 0;
                while (i8 < i6) {
                    final long j26 = i8 * j25;
                    final long j27 = i8 == i6 + (-1) ? j5 : j26 + j25;
                    futureArr3[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.37
                        @Override // java.lang.Runnable
                        public void run() {
                            long j28 = 2;
                            DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(DoubleFFT_3D.this.slicesl * 2, false);
                            long j29 = j26;
                            while (j29 < j27) {
                                long j30 = j8 * j29;
                                long j31 = 0;
                                while (j31 < DoubleFFT_3D.this.columnsl) {
                                    long j32 = j31 * j28;
                                    long j33 = 0;
                                    while (j33 < DoubleFFT_3D.this.slicesl) {
                                        long j34 = j33 * j28;
                                        long j35 = (j7 * j33) + j30 + j32;
                                        doubleLargeArray5.setDouble(j34, doubleLargeArray.getDouble(j35));
                                        doubleLargeArray5.setDouble(j34 + 1, doubleLargeArray.getDouble(j35 + 1));
                                        j33++;
                                        j29 = j29;
                                        j28 = 2;
                                    }
                                    long j36 = j29;
                                    DoubleFFT_3D.this.fftSlices.complexForward(doubleLargeArray5);
                                    long j37 = 0;
                                    while (j37 < DoubleFFT_3D.this.slicesl) {
                                        long j38 = j37 * 2;
                                        long j39 = (j7 * j37) + j30 + j32;
                                        doubleLargeArray.setDouble(j39, doubleLargeArray5.getDouble(j38));
                                        doubleLargeArray.setDouble(j39 + 1, doubleLargeArray5.getDouble(j38 + 1));
                                        j37++;
                                        j30 = j30;
                                    }
                                    j31++;
                                    j30 = j30;
                                    j29 = j36;
                                    j28 = 2;
                                }
                                j29++;
                                j28 = 2;
                            }
                        }
                    });
                    i8++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e9) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
                } catch (ExecutionException e10) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
                }
                long j28 = doubleFFT_3D.slicesl / j15;
                int i9 = 0;
                while (i9 < i6) {
                    final long j29 = i9 * j28;
                    final long j30 = i9 == i6 + (-1) ? doubleFFT_3D.slicesl : j29 + j28;
                    futureArr3[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.38
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j31 = j29; j31 < j30; j31++) {
                                long j32 = (DoubleFFT_3D.this.slicesl - j31) % DoubleFFT_3D.this.slicesl;
                                long j33 = j7;
                                long j34 = j32 * j33;
                                long j35 = j33 * j31;
                                long j36 = 1;
                                while (j36 < j6) {
                                    long j37 = DoubleFFT_3D.this.rowsl - j36;
                                    long j38 = j8;
                                    long j39 = j37 * j38;
                                    long j40 = j38 * j36;
                                    long j41 = j39 + j34;
                                    long j42 = 0;
                                    while (j42 < DoubleFFT_3D.this.columnsl) {
                                        long j43 = 2 * j42;
                                        long j44 = j34;
                                        long j45 = j16;
                                        long j46 = j45 - j43;
                                        long j47 = j35 + j40 + j43;
                                        long j48 = j35;
                                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                                        doubleLargeArray5.setDouble((j46 % j45) + j41, doubleLargeArray5.getDouble(j47));
                                        DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                                        doubleLargeArray6.setDouble(j41 + ((j46 + 1) % j16), -doubleLargeArray6.getDouble(j47 + 1));
                                        j42++;
                                        j34 = j44;
                                        j35 = j48;
                                        j40 = j40;
                                    }
                                    j36++;
                                    j34 = j34;
                                    j35 = j35;
                                }
                            }
                        }
                    });
                    i9++;
                    doubleFFT_3D = this;
                    i6 = i6;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e11) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e11);
                    return;
                } catch (ExecutionException e12) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e12);
                    return;
                }
            }
        }
        DoubleFFT_3D doubleFFT_3D2 = this;
        for (long j31 = doubleFFT_3D2.slicesl - 1; j31 >= 0; j31--) {
            long j32 = j31 * doubleFFT_3D2.sliceStridel;
            long j33 = j31 * j7;
            for (long j34 = doubleFFT_3D2.rowsl - 1; j34 >= 0; j34--) {
                LargeArrayUtils.arraycopy(doubleLargeArray, j32 + (doubleFFT_3D2.rowStridel * j34), doubleLargeArray2, 0L, doubleFFT_3D2.columnsl);
                doubleFFT_3D2.fftColumns.realForwardFull(doubleLargeArray2);
                LargeArrayUtils.arraycopy(doubleLargeArray2, 0L, doubleLargeArray, j33 + (j34 * j8), j3);
            }
        }
        DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(doubleFFT_3D2.rowsl * 2, false);
        long j35 = 0;
        while (j35 < doubleFFT_3D2.slicesl) {
            long j36 = j35 * j7;
            long j37 = 0;
            while (j37 < doubleFFT_3D2.columnsl) {
                long j38 = j37 * j2;
                long j39 = j36;
                long j40 = 0;
                while (j40 < doubleFFT_3D2.rowsl) {
                    long j41 = j40 * j2;
                    long j42 = j39 + (j40 * j8) + j38;
                    doubleLargeArray5.setDouble(j41, doubleLargeArray.getDouble(j42));
                    doubleLargeArray5.setDouble(j41 + 1, doubleLargeArray.getDouble(j42 + 1));
                    j40++;
                    j35 = j35;
                    j2 = 2;
                }
                long j43 = j35;
                doubleFFT_3D2.fftRows.complexForward(doubleLargeArray5);
                for (long j44 = 0; j44 < doubleFFT_3D2.rowsl; j44++) {
                    long j45 = j44 * 2;
                    long j46 = j39 + (j44 * j8) + j38;
                    doubleLargeArray.setDouble(j46, doubleLargeArray5.getDouble(j45));
                    doubleLargeArray.setDouble(j46 + 1, doubleLargeArray5.getDouble(j45 + 1));
                }
                j37++;
                j36 = j39;
                j35 = j43;
                j2 = 2;
            }
            j35++;
            j2 = 2;
        }
        long j47 = 2;
        DoubleLargeArray doubleLargeArray6 = new DoubleLargeArray(doubleFFT_3D2.slicesl * 2, false);
        long j48 = 0;
        while (j48 < j5) {
            long j49 = j48 * j8;
            long j50 = 0;
            while (j50 < doubleFFT_3D2.columnsl) {
                long j51 = j50 * j47;
                long j52 = 0;
                while (j52 < doubleFFT_3D2.slicesl) {
                    long j53 = j48;
                    long j54 = j52 * 2;
                    long j55 = (j52 * j7) + j49 + j51;
                    doubleLargeArray6.setDouble(j54, doubleLargeArray.getDouble(j55));
                    doubleLargeArray6.setDouble(j54 + 1, doubleLargeArray.getDouble(j55 + 1));
                    j52++;
                    j48 = j53;
                    j50 = j50;
                }
                long j56 = j48;
                long j57 = j50;
                doubleFFT_3D2.fftSlices.complexForward(doubleLargeArray6);
                for (long j58 = 0; j58 < doubleFFT_3D2.slicesl; j58++) {
                    long j59 = j58 * 2;
                    long j60 = (j58 * j7) + j49 + j51;
                    doubleLargeArray.setDouble(j60, doubleLargeArray6.getDouble(j59));
                    doubleLargeArray.setDouble(j60 + 1, doubleLargeArray6.getDouble(j59 + 1));
                }
                j50 = j57 + 1;
                j48 = j56;
                j47 = 2;
            }
            j48++;
            j47 = 2;
        }
        long j61 = 0;
        while (true) {
            long j62 = doubleFFT_3D2.slicesl;
            if (j61 >= j62) {
                return;
            }
            long j63 = ((j62 - j61) % j62) * j7;
            long j64 = j61 * j7;
            long j65 = 1;
            while (j65 < j6) {
                long j66 = j65 * j8;
                long j67 = ((doubleFFT_3D2.rowsl - j65) * j8) + j63;
                long j68 = j63;
                long j69 = 0;
                while (j69 < doubleFFT_3D2.columnsl) {
                    long j70 = j69 * 2;
                    long j71 = j3 - j70;
                    long j72 = j64 + j66 + j70;
                    doubleLargeArray.setDouble(j67 + (j71 % j3), doubleLargeArray.getDouble(j72));
                    doubleLargeArray.setDouble(j67 + ((j71 + 1) % j3), -doubleLargeArray.getDouble(j72 + 1));
                    j69++;
                    doubleFFT_3D2 = this;
                    j64 = j64;
                }
                j65++;
                doubleFFT_3D2 = this;
                j63 = j68;
            }
            j61++;
            doubleFFT_3D2 = this;
        }
    }

    private void mixedRadixRealInverseFull(final double[] dArr, final boolean z) {
        int i;
        int i2;
        final int i3 = this.columns * 2;
        double[] dArr2 = new double[i3];
        int i4 = this.rows;
        int i5 = (i4 / 2) + 1;
        if (i4 % 2 == 0) {
            i = i4 / 2;
        } else {
            i = (i4 + 1) / 2;
        }
        int i6 = i;
        final int i7 = this.sliceStride * 2;
        final int i8 = this.rowStride * 2;
        int i9 = this.slices / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads && i9 >= numberOfThreads && this.columns >= numberOfThreads && i5 >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i10 = i9 / numberOfThreads;
            int i11 = 0;
            while (i11 < numberOfThreads) {
                final int i12 = (this.slices - 1) - (i11 * i10);
                final int i13 = i11 == numberOfThreads + (-1) ? i9 + 1 : i12 - i10;
                Future[] futureArr2 = futureArr;
                futureArr2[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.39
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr3 = new double[i3];
                        for (int i14 = i12; i14 >= i13; i14--) {
                            int i15 = DoubleFFT_3D.this.sliceStride * i14;
                            int i16 = i7 * i14;
                            for (int i17 = DoubleFFT_3D.this.rows - 1; i17 >= 0; i17--) {
                                System.arraycopy(dArr, (DoubleFFT_3D.this.rowStride * i17) + i15, dArr3, 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realInverseFull(dArr3, z);
                                System.arraycopy(dArr3, 0, dArr, (i8 * i17) + i16, i3);
                            }
                        }
                    }
                });
                i11++;
                i9 = i9;
                i6 = i6;
                futureArr = futureArr2;
                numberOfThreads = numberOfThreads;
                i5 = i5;
            }
            Future[] futureArr3 = futureArr;
            int i14 = numberOfThreads;
            int i15 = i9;
            final int i16 = i6;
            int i17 = i5;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i18 = i15 + 1;
            final double[][][] dArr3 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, i18, this.rows, i3);
            int i19 = i14;
            int i20 = 0;
            while (i20 < i19) {
                final int i21 = i20 * i10;
                final int i22 = i20 == i19 + (-1) ? i18 : i21 + i10;
                futureArr3[i20] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.40
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i23 = i21; i23 < i22; i23++) {
                            int i24 = DoubleFFT_3D.this.sliceStride * i23;
                            for (int i25 = 0; i25 < DoubleFFT_3D.this.rows; i25++) {
                                System.arraycopy(dArr, (DoubleFFT_3D.this.rowStride * i25) + i24, dArr3[i23][i25], 0, DoubleFFT_3D.this.columns);
                                DoubleFFT_3D.this.fftColumns.realInverseFull(dArr3[i23][i25], z);
                            }
                        }
                    }
                });
                i20++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i23 = 0;
            while (i23 < i19) {
                final int i24 = i23 * i10;
                final int i25 = i23 == i19 + (-1) ? i18 : i24 + i10;
                futureArr3[i23] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.41
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i26 = i24; i26 < i25; i26++) {
                            int i27 = i7 * i26;
                            for (int i28 = 0; i28 < DoubleFFT_3D.this.rows; i28++) {
                                System.arraycopy(dArr3[i26][i28], 0, dArr, (i8 * i28) + i27, i3);
                            }
                        }
                    }
                });
                i23++;
                str = str;
                i19 = i19;
                i18 = i18;
            }
            int i26 = i19;
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i27 = this.slices / i26;
            int i28 = 0;
            while (i28 < i26) {
                final int i29 = i28 * i27;
                final int i30 = i28 == i26 + (-1) ? this.slices : i29 + i27;
                futureArr3[i28] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.42
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr4 = new double[DoubleFFT_3D.this.rows * 2];
                        for (int i31 = i29; i31 < i30; i31++) {
                            int i32 = i7 * i31;
                            for (int i33 = 0; i33 < DoubleFFT_3D.this.columns; i33++) {
                                int i34 = i33 * 2;
                                for (int i35 = 0; i35 < DoubleFFT_3D.this.rows; i35++) {
                                    int i36 = (i8 * i35) + i32 + i34;
                                    int i37 = i35 * 2;
                                    double[] dArr5 = dArr;
                                    dArr4[i37] = dArr5[i36];
                                    dArr4[i37 + 1] = dArr5[i36 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr4, z);
                                for (int i38 = 0; i38 < DoubleFFT_3D.this.rows; i38++) {
                                    int i39 = (i8 * i38) + i32 + i34;
                                    int i40 = i38 * 2;
                                    double[] dArr6 = dArr;
                                    dArr6[i39] = dArr4[i40];
                                    dArr6[i39 + 1] = dArr4[i40 + 1];
                                }
                            }
                        }
                    }
                });
                i28++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e7) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
            }
            int i31 = i17 / i26;
            int i32 = 0;
            while (i32 < i26) {
                final int i33 = i32 * i31;
                final int i34 = i32 == i26 + (-1) ? i17 : i33 + i31;
                int i35 = i32;
                futureArr3[i35] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.43
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr4 = new double[DoubleFFT_3D.this.slices * 2];
                        for (int i36 = i33; i36 < i34; i36++) {
                            int i37 = i8 * i36;
                            for (int i38 = 0; i38 < DoubleFFT_3D.this.columns; i38++) {
                                int i39 = i38 * 2;
                                for (int i40 = 0; i40 < DoubleFFT_3D.this.slices; i40++) {
                                    int i41 = i40 * 2;
                                    int i42 = (i7 * i40) + i37 + i39;
                                    double[] dArr5 = dArr;
                                    dArr4[i41] = dArr5[i42];
                                    dArr4[i41 + 1] = dArr5[i42 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr4, z);
                                for (int i43 = 0; i43 < DoubleFFT_3D.this.slices; i43++) {
                                    int i44 = i43 * 2;
                                    int i45 = (i7 * i43) + i37 + i39;
                                    double[] dArr6 = dArr;
                                    dArr6[i45] = dArr4[i44];
                                    dArr6[i45 + 1] = dArr4[i44 + 1];
                                }
                            }
                        }
                    }
                });
                i32 = i35 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e9) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
            }
            int i36 = this.slices / i26;
            int i37 = 0;
            while (i37 < i26) {
                final int i38 = i37 * i36;
                final int i39 = i37 == i26 + (-1) ? this.slices : i38 + i36;
                int i40 = i37;
                futureArr3[i40] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.44
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i41 = i38; i41 < i39; i41++) {
                            int i42 = (DoubleFFT_3D.this.slices - i41) % DoubleFFT_3D.this.slices;
                            int i43 = i7;
                            int i44 = i42 * i43;
                            int i45 = i43 * i41;
                            for (int i46 = 1; i46 < i16; i46++) {
                                int i47 = DoubleFFT_3D.this.rows - i46;
                                int i48 = i8;
                                int i49 = i47 * i48;
                                int i50 = i48 * i46;
                                int i51 = i49 + i44;
                                for (int i52 = 0; i52 < DoubleFFT_3D.this.columns; i52++) {
                                    int i53 = i52 * 2;
                                    int i54 = i3;
                                    int i55 = i54 - i53;
                                    int i56 = i45 + i50 + i53;
                                    double[] dArr4 = dArr;
                                    dArr4[(i55 % i54) + i51] = dArr4[i56];
                                    dArr4[((i55 + 1) % i54) + i51] = -dArr4[i56 + 1];
                                }
                            }
                        }
                    }
                });
                i37 = i40 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e11) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e11);
                return;
            } catch (ExecutionException e12) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e12);
                return;
            }
        }
        int i41 = i6;
        for (int i42 = this.slices - 1; i42 >= 0; i42--) {
            int i43 = this.sliceStride * i42;
            int i44 = i42 * i7;
            for (int i45 = this.rows - 1; i45 >= 0; i45--) {
                System.arraycopy(dArr, (this.rowStride * i45) + i43, dArr2, 0, this.columns);
                this.fftColumns.realInverseFull(dArr2, z);
                System.arraycopy(dArr2, 0, dArr, (i45 * i8) + i44, i3);
            }
        }
        double[] dArr4 = new double[this.rows * 2];
        int i46 = 0;
        while (true) {
            i2 = this.slices;
            if (i46 >= i2) {
                break;
            }
            int i47 = i46 * i7;
            for (int i48 = 0; i48 < this.columns; i48++) {
                int i49 = i48 * 2;
                for (int i50 = 0; i50 < this.rows; i50++) {
                    int i51 = i50 * 2;
                    int i52 = (i50 * i8) + i47 + i49;
                    dArr4[i51] = dArr[i52];
                    dArr4[i51 + 1] = dArr[i52 + 1];
                }
                this.fftRows.complexInverse(dArr4, z);
                for (int i53 = 0; i53 < this.rows; i53++) {
                    int i54 = i53 * 2;
                    int i55 = (i53 * i8) + i47 + i49;
                    dArr[i55] = dArr4[i54];
                    dArr[i55 + 1] = dArr4[i54 + 1];
                }
            }
            i46++;
        }
        double[] dArr5 = new double[i2 * 2];
        for (int i56 = 0; i56 < i5; i56++) {
            int i57 = i56 * i8;
            for (int i58 = 0; i58 < this.columns; i58++) {
                int i59 = i58 * 2;
                for (int i60 = 0; i60 < this.slices; i60++) {
                    int i61 = i60 * 2;
                    int i62 = (i60 * i7) + i57 + i59;
                    dArr5[i61] = dArr[i62];
                    dArr5[i61 + 1] = dArr[i62 + 1];
                }
                this.fftSlices.complexInverse(dArr5, z);
                for (int i63 = 0; i63 < this.slices; i63++) {
                    int i64 = i63 * 2;
                    int i65 = (i63 * i7) + i57 + i59;
                    dArr[i65] = dArr5[i64];
                    dArr[i65 + 1] = dArr5[i64 + 1];
                }
            }
        }
        int i66 = 0;
        while (true) {
            int i67 = this.slices;
            if (i66 >= i67) {
                return;
            }
            int i68 = ((i67 - i66) % i67) * i7;
            int i69 = i66 * i7;
            int i70 = i41;
            for (int i71 = 1; i71 < i70; i71++) {
                int i72 = i71 * i8;
                int i73 = ((this.rows - i71) * i8) + i68;
                for (int i74 = 0; i74 < this.columns; i74++) {
                    int i75 = i74 * 2;
                    int i76 = i3 - i75;
                    int i77 = i69 + i72 + i75;
                    dArr[(i76 % i3) + i73] = dArr[i77];
                    dArr[((i76 + 1) % i3) + i73] = -dArr[i77 + 1];
                }
            }
            i66++;
            i41 = i70;
        }
    }

    private void mixedRadixRealInverseFull(final DoubleLargeArray doubleLargeArray, final boolean z) {
        long j;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j3);
        long j4 = this.rowsl;
        long j5 = (j4 / 2) + 1;
        if (j4 % 2 == 0) {
            j = j4 / 2;
        } else {
            j = (j4 + 1) / 2;
        }
        final long j6 = j;
        final long j7 = this.sliceStridel * 2;
        final long j8 = this.rowStridel * 2;
        long j9 = this.slicesl / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.useThreads) {
            long j10 = numberOfThreads;
            if (j9 >= j10 && this.columnsl >= j10 && j5 >= j10) {
                Future[] futureArr = new Future[numberOfThreads];
                long j11 = j9 / j10;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j12 = (this.slicesl - 1) - (i * j11);
                    final long j13 = i == numberOfThreads + (-1) ? j9 + 1 : j12 - j11;
                    final long j14 = j3;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.45
                        @Override // java.lang.Runnable
                        public void run() {
                            DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j14);
                            for (long j15 = j12; j15 >= j13; j15--) {
                                long j16 = j15 * DoubleFFT_3D.this.sliceStridel;
                                long j17 = j15 * j7;
                                for (long j18 = DoubleFFT_3D.this.rowsl - 1; j18 >= 0; j18--) {
                                    LargeArrayUtils.arraycopy(doubleLargeArray, (DoubleFFT_3D.this.rowStridel * j18) + j16, doubleLargeArray3, 0L, DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_3D.this.fftColumns.realInverseFull(doubleLargeArray3, z);
                                    LargeArrayUtils.arraycopy(doubleLargeArray3, 0L, doubleLargeArray, (j8 * j18) + j17, j14);
                                }
                            }
                        }
                    });
                    i++;
                    futureArr = futureArr2;
                    j3 = j3;
                    j10 = j10;
                    numberOfThreads = numberOfThreads;
                }
                long j15 = j10;
                int i2 = numberOfThreads;
                Future[] futureArr3 = futureArr;
                final long j16 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                long j17 = j9 + 1;
                final DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.rowsl * j17 * j16);
                int i3 = i2;
                int i4 = 0;
                while (i4 < i3) {
                    final long j18 = i4 * j11;
                    final long j19 = i4 == i3 + (-1) ? j17 : j18 + j11;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.46
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j20 = j18; j20 < j19; j20++) {
                                long j21 = DoubleFFT_3D.this.sliceStridel * j20;
                                for (long j22 = 0; j22 < DoubleFFT_3D.this.rowsl; j22++) {
                                    DoubleLargeArray doubleLargeArray4 = doubleLargeArray;
                                    long j23 = j21 + (DoubleFFT_3D.this.rowStridel * j22);
                                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray3;
                                    long j24 = DoubleFFT_3D.this.rowsl * j20;
                                    long j25 = j16;
                                    LargeArrayUtils.arraycopy(doubleLargeArray4, j23, doubleLargeArray5, (j24 * j25) + (j25 * j22), DoubleFFT_3D.this.columnsl);
                                    DoubleFFT_1D doubleFFT_1D = DoubleFFT_3D.this.fftColumns;
                                    DoubleLargeArray doubleLargeArray6 = doubleLargeArray3;
                                    long j26 = DoubleFFT_3D.this.rowsl * j20;
                                    long j27 = j16;
                                    doubleFFT_1D.realInverseFull(doubleLargeArray6, (j26 * j27) + (j27 * j22), z);
                                }
                            }
                        }
                    });
                    i4++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e3) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                }
                int i5 = 0;
                while (i5 < i3) {
                    final long j20 = i5 * j11;
                    final long j21 = i5 == i3 + (-1) ? j17 : j20 + j11;
                    final DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
                    futureArr3[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.47
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j22 = j20; j22 < j21; j22++) {
                                long j23 = j7 * j22;
                                for (long j24 = 0; j24 < DoubleFFT_3D.this.rowsl; j24++) {
                                    DoubleLargeArray doubleLargeArray5 = doubleLargeArray4;
                                    long j25 = DoubleFFT_3D.this.rowsl * j22;
                                    long j26 = j16;
                                    LargeArrayUtils.arraycopy(doubleLargeArray5, (j25 * j26) + (j24 * j26), doubleLargeArray, j23 + (j8 * j24), j26);
                                }
                            }
                        }
                    });
                    i5++;
                    str = str;
                    doubleLargeArray3 = doubleLargeArray3;
                    i3 = i3;
                }
                int i6 = i3;
                String str2 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e5) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                }
                DoubleFFT_3D doubleFFT_3D = this;
                long j22 = doubleFFT_3D.slicesl / j15;
                int i7 = i6;
                int i8 = 0;
                while (i8 < i7) {
                    final long j23 = i8 * j22;
                    final long j24 = i8 == i7 + (-1) ? doubleFFT_3D.slicesl : j23 + j22;
                    futureArr3[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.48
                        @Override // java.lang.Runnable
                        public void run() {
                            long j25 = 2;
                            DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(DoubleFFT_3D.this.rowsl * 2, false);
                            long j26 = j23;
                            while (j26 < j24) {
                                long j27 = j7 * j26;
                                long j28 = 0;
                                while (j28 < DoubleFFT_3D.this.columnsl) {
                                    long j29 = j28 * j25;
                                    long j30 = 0;
                                    while (j30 < DoubleFFT_3D.this.rowsl) {
                                        long j31 = (j8 * j30) + j27 + j29;
                                        long j32 = j30 * j25;
                                        doubleLargeArray5.setDouble(j32, doubleLargeArray.getDouble(j31));
                                        doubleLargeArray5.setDouble(j32 + 1, doubleLargeArray.getDouble(j31 + 1));
                                        j30++;
                                        j26 = j26;
                                        j25 = 2;
                                    }
                                    long j33 = j26;
                                    DoubleFFT_3D.this.fftRows.complexInverse(doubleLargeArray5, z);
                                    long j34 = 0;
                                    while (j34 < DoubleFFT_3D.this.rowsl) {
                                        long j35 = (j8 * j34) + j27 + j29;
                                        long j36 = j34 * 2;
                                        doubleLargeArray.setDouble(j35, doubleLargeArray5.getDouble(j36));
                                        doubleLargeArray.setDouble(j35 + 1, doubleLargeArray5.getDouble(j36 + 1));
                                        j34++;
                                        j27 = j27;
                                    }
                                    j28++;
                                    j27 = j27;
                                    j26 = j33;
                                    j25 = 2;
                                }
                                j26++;
                                j25 = 2;
                            }
                        }
                    });
                    i8++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e7) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                } catch (ExecutionException e8) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                }
                long j25 = j5 / j15;
                int i9 = 0;
                while (i9 < i7) {
                    final long j26 = i9 * j25;
                    final long j27 = i9 == i7 + (-1) ? j5 : j26 + j25;
                    futureArr3[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.49
                        @Override // java.lang.Runnable
                        public void run() {
                            long j28 = 2;
                            DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(DoubleFFT_3D.this.slicesl * 2, false);
                            long j29 = j26;
                            while (j29 < j27) {
                                long j30 = j8 * j29;
                                long j31 = 0;
                                while (j31 < DoubleFFT_3D.this.columnsl) {
                                    long j32 = j31 * j28;
                                    long j33 = 0;
                                    while (j33 < DoubleFFT_3D.this.slicesl) {
                                        long j34 = j33 * j28;
                                        long j35 = (j7 * j33) + j30 + j32;
                                        doubleLargeArray5.setDouble(j34, doubleLargeArray.getDouble(j35));
                                        doubleLargeArray5.setDouble(j34 + 1, doubleLargeArray.getDouble(j35 + 1));
                                        j33++;
                                        j29 = j29;
                                        j28 = 2;
                                    }
                                    long j36 = j29;
                                    DoubleFFT_3D.this.fftSlices.complexInverse(doubleLargeArray5, z);
                                    long j37 = 0;
                                    while (j37 < DoubleFFT_3D.this.slicesl) {
                                        long j38 = j37 * 2;
                                        long j39 = (j7 * j37) + j30 + j32;
                                        doubleLargeArray.setDouble(j39, doubleLargeArray5.getDouble(j38));
                                        doubleLargeArray.setDouble(j39 + 1, doubleLargeArray5.getDouble(j38 + 1));
                                        j37++;
                                        j30 = j30;
                                    }
                                    j31++;
                                    j30 = j30;
                                    j29 = j36;
                                    j28 = 2;
                                }
                                j29++;
                                j28 = 2;
                            }
                        }
                    });
                    i9++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e9) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
                } catch (ExecutionException e10) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
                }
                long j28 = doubleFFT_3D.slicesl / j15;
                int i10 = 0;
                while (i10 < i7) {
                    final long j29 = i10 * j28;
                    final long j30 = i10 == i7 + (-1) ? doubleFFT_3D.slicesl : j29 + j28;
                    futureArr3[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.50
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j31 = j29; j31 < j30; j31++) {
                                long j32 = (DoubleFFT_3D.this.slicesl - j31) % DoubleFFT_3D.this.slicesl;
                                long j33 = j7;
                                long j34 = j32 * j33;
                                long j35 = j33 * j31;
                                long j36 = 1;
                                while (j36 < j6) {
                                    long j37 = DoubleFFT_3D.this.rowsl - j36;
                                    long j38 = j8;
                                    long j39 = j37 * j38;
                                    long j40 = j38 * j36;
                                    long j41 = j39 + j34;
                                    long j42 = 0;
                                    while (j42 < DoubleFFT_3D.this.columnsl) {
                                        long j43 = 2 * j42;
                                        long j44 = j34;
                                        long j45 = j16;
                                        long j46 = j45 - j43;
                                        long j47 = j35 + j40 + j43;
                                        long j48 = j35;
                                        DoubleLargeArray doubleLargeArray5 = doubleLargeArray;
                                        doubleLargeArray5.setDouble((j46 % j45) + j41, doubleLargeArray5.getDouble(j47));
                                        DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                                        doubleLargeArray6.setDouble(j41 + ((j46 + 1) % j16), -doubleLargeArray6.getDouble(j47 + 1));
                                        j42++;
                                        j34 = j44;
                                        j35 = j48;
                                        j40 = j40;
                                    }
                                    j36++;
                                    j34 = j34;
                                    j35 = j35;
                                }
                            }
                        }
                    });
                    i10++;
                    doubleFFT_3D = this;
                    i7 = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                    return;
                } catch (InterruptedException e11) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e11);
                    return;
                } catch (ExecutionException e12) {
                    Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e12);
                    return;
                }
            }
        }
        DoubleFFT_3D doubleFFT_3D2 = this;
        for (long j31 = doubleFFT_3D2.slicesl - 1; j31 >= 0; j31--) {
            long j32 = j31 * doubleFFT_3D2.sliceStridel;
            long j33 = j31 * j7;
            for (long j34 = doubleFFT_3D2.rowsl - 1; j34 >= 0; j34--) {
                LargeArrayUtils.arraycopy(doubleLargeArray, j32 + (doubleFFT_3D2.rowStridel * j34), doubleLargeArray2, 0L, doubleFFT_3D2.columnsl);
                doubleFFT_3D2.fftColumns.realInverseFull(doubleLargeArray2, z);
                LargeArrayUtils.arraycopy(doubleLargeArray2, 0L, doubleLargeArray, j33 + (j34 * j8), j3);
            }
        }
        DoubleLargeArray doubleLargeArray5 = new DoubleLargeArray(doubleFFT_3D2.rowsl * 2, false);
        long j35 = 0;
        while (j35 < doubleFFT_3D2.slicesl) {
            long j36 = j35 * j7;
            long j37 = 0;
            while (j37 < doubleFFT_3D2.columnsl) {
                long j38 = j37 * j2;
                long j39 = j36;
                long j40 = 0;
                while (j40 < doubleFFT_3D2.rowsl) {
                    long j41 = j40 * j2;
                    long j42 = j39 + (j40 * j8) + j38;
                    doubleLargeArray5.setDouble(j41, doubleLargeArray.getDouble(j42));
                    doubleLargeArray5.setDouble(j41 + 1, doubleLargeArray.getDouble(j42 + 1));
                    j40++;
                    j35 = j35;
                    j2 = 2;
                }
                long j43 = j35;
                doubleFFT_3D2.fftRows.complexInverse(doubleLargeArray5, z);
                long j44 = 0;
                while (j44 < doubleFFT_3D2.rowsl) {
                    long j45 = j44 * 2;
                    long j46 = j39 + (j44 * j8) + j38;
                    doubleLargeArray.setDouble(j46, doubleLargeArray5.getDouble(j45));
                    doubleLargeArray.setDouble(j46 + 1, doubleLargeArray5.getDouble(j45 + 1));
                    j44++;
                    j38 = j38;
                }
                j37++;
                j36 = j39;
                j35 = j43;
                j2 = 2;
            }
            j35++;
            j2 = 2;
        }
        boolean z2 = z;
        long j47 = 2;
        DoubleLargeArray doubleLargeArray6 = new DoubleLargeArray(doubleFFT_3D2.slicesl * 2, false);
        long j48 = 0;
        while (j48 < j5) {
            long j49 = j48 * j8;
            long j50 = 0;
            while (j50 < doubleFFT_3D2.columnsl) {
                long j51 = j50 * j47;
                long j52 = 0;
                while (j52 < doubleFFT_3D2.slicesl) {
                    long j53 = j48;
                    long j54 = j52 * 2;
                    long j55 = (j52 * j7) + j49 + j51;
                    doubleLargeArray6.setDouble(j54, doubleLargeArray.getDouble(j55));
                    doubleLargeArray6.setDouble(j54 + 1, doubleLargeArray.getDouble(j55 + 1));
                    j52++;
                    j48 = j53;
                    j50 = j50;
                }
                long j56 = j48;
                long j57 = j50;
                doubleFFT_3D2.fftSlices.complexInverse(doubleLargeArray6, z2);
                for (long j58 = 0; j58 < doubleFFT_3D2.slicesl; j58++) {
                    long j59 = j58 * 2;
                    long j60 = (j58 * j7) + j49 + j51;
                    doubleLargeArray.setDouble(j60, doubleLargeArray6.getDouble(j59));
                    doubleLargeArray.setDouble(j60 + 1, doubleLargeArray6.getDouble(j59 + 1));
                }
                j50 = j57 + 1;
                z2 = z;
                j48 = j56;
                j47 = 2;
            }
            j48++;
            z2 = z;
            j47 = 2;
        }
        long j61 = 0;
        while (true) {
            long j62 = doubleFFT_3D2.slicesl;
            if (j61 >= j62) {
                return;
            }
            long j63 = ((j62 - j61) % j62) * j7;
            long j64 = j61 * j7;
            long j65 = 1;
            while (j65 < j6) {
                long j66 = j65 * j8;
                long j67 = ((doubleFFT_3D2.rowsl - j65) * j8) + j63;
                long j68 = j63;
                long j69 = 0;
                while (j69 < doubleFFT_3D2.columnsl) {
                    long j70 = j69 * 2;
                    long j71 = j3 - j70;
                    long j72 = j64 + j66 + j70;
                    doubleLargeArray.setDouble(j67 + (j71 % j3), doubleLargeArray.getDouble(j72));
                    doubleLargeArray.setDouble(j67 + ((j71 + 1) % j3), -doubleLargeArray.getDouble(j72 + 1));
                    j69++;
                    doubleFFT_3D2 = this;
                    j64 = j64;
                }
                j65++;
                doubleFFT_3D2 = this;
                j63 = j68;
            }
            j61++;
            doubleFFT_3D2 = this;
        }
    }

    private void xdft3da_sub1(int i, int i2, double[] dArr, boolean z) {
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr2 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(dArr, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(dArr, (this.rowStride * i10) + i8);
                    }
                }
                int i11 = this.columns;
                if (i11 > 4) {
                    for (int i12 = 0; i12 < this.columns; i12 += 8) {
                        int i13 = 0;
                        while (true) {
                            int i14 = this.rows;
                            if (i13 >= i14) {
                                break;
                            }
                            int i15 = (this.rowStride * i13) + i8 + i12;
                            int i16 = i13 * 2;
                            int i17 = (i14 * 2) + i16;
                            int i18 = (i14 * 2) + i17;
                            int i19 = (i14 * 2) + i18;
                            dArr2[i16] = dArr[i15];
                            dArr2[i16 + 1] = dArr[i15 + 1];
                            dArr2[i17] = dArr[i15 + 2];
                            dArr2[i17 + 1] = dArr[i15 + 3];
                            dArr2[i18] = dArr[i15 + 4];
                            dArr2[i18 + 1] = dArr[i15 + 5];
                            dArr2[i19] = dArr[i15 + 6];
                            dArr2[i19 + 1] = dArr[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(dArr2, 0);
                        this.fftRows.complexForward(dArr2, this.rows * 2);
                        this.fftRows.complexForward(dArr2, this.rows * 4);
                        this.fftRows.complexForward(dArr2, this.rows * 6);
                        int i20 = 0;
                        while (true) {
                            int i21 = this.rows;
                            if (i20 < i21) {
                                int i22 = (this.rowStride * i20) + i8 + i12;
                                int i23 = i20 * 2;
                                int i24 = (i21 * 2) + i23;
                                int i25 = (i21 * 2) + i24;
                                int i26 = (i21 * 2) + i25;
                                dArr[i22] = dArr2[i23];
                                dArr[i22 + 1] = dArr2[i23 + 1];
                                dArr[i22 + 2] = dArr2[i24];
                                dArr[i22 + 3] = dArr2[i24 + 1];
                                dArr[i22 + 4] = dArr2[i25];
                                dArr[i22 + 5] = dArr2[i25 + 1];
                                dArr[i22 + 6] = dArr2[i26];
                                dArr[i22 + 7] = dArr2[i26 + 1];
                                i20++;
                            }
                        }
                    }
                } else if (i11 == 4) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.rows;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = (this.rowStride * i27) + i8;
                        int i30 = i27 * 2;
                        int i31 = (i28 * 2) + i30;
                        dArr2[i30] = dArr[i29];
                        dArr2[i30 + 1] = dArr[i29 + 1];
                        dArr2[i31] = dArr[i29 + 2];
                        dArr2[i31 + 1] = dArr[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 < i33) {
                            int i34 = (this.rowStride * i32) + i8;
                            int i35 = i32 * 2;
                            int i36 = (i33 * 2) + i35;
                            dArr[i34] = dArr2[i35];
                            dArr[i34 + 1] = dArr2[i35 + 1];
                            dArr[i34 + 2] = dArr2[i36];
                            dArr[i34 + 3] = dArr2[i36 + 1];
                            i32++;
                        }
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        dArr2[i39] = dArr[i38];
                        dArr2[i39 + 1] = dArr[i38 + 1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        dArr[i41] = dArr2[i42];
                        dArr[i41 + 1] = dArr2[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(dArr, (this.rowStride * i45) + i44, z);
                }
            }
            int i46 = this.columns;
            if (i46 > 4) {
                for (int i47 = 0; i47 < this.columns; i47 += 8) {
                    int i48 = 0;
                    while (true) {
                        int i49 = this.rows;
                        if (i48 >= i49) {
                            break;
                        }
                        int i50 = (this.rowStride * i48) + i44 + i47;
                        int i51 = i48 * 2;
                        int i52 = (i49 * 2) + i51;
                        int i53 = (i49 * 2) + i52;
                        int i54 = (i49 * 2) + i53;
                        dArr2[i51] = dArr[i50];
                        dArr2[i51 + 1] = dArr[i50 + 1];
                        dArr2[i52] = dArr[i50 + 2];
                        dArr2[i52 + 1] = dArr[i50 + 3];
                        dArr2[i53] = dArr[i50 + 4];
                        dArr2[i53 + 1] = dArr[i50 + 5];
                        dArr2[i54] = dArr[i50 + 6];
                        dArr2[i54 + 1] = dArr[i50 + 7];
                        i48++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z);
                    int i55 = 0;
                    while (true) {
                        int i56 = this.rows;
                        if (i55 < i56) {
                            int i57 = (this.rowStride * i55) + i44 + i47;
                            int i58 = i55 * 2;
                            int i59 = (i56 * 2) + i58;
                            int i60 = (i56 * 2) + i59;
                            int i61 = (i56 * 2) + i60;
                            dArr[i57] = dArr2[i58];
                            dArr[i57 + 1] = dArr2[i58 + 1];
                            dArr[i57 + 2] = dArr2[i59];
                            dArr[i57 + 3] = dArr2[i59 + 1];
                            dArr[i57 + 4] = dArr2[i60];
                            dArr[i57 + 5] = dArr2[i60 + 1];
                            dArr[i57 + 6] = dArr2[i61];
                            dArr[i57 + 7] = dArr2[i61 + 1];
                            i55++;
                        }
                    }
                }
            } else if (i46 == 4) {
                int i62 = 0;
                while (true) {
                    int i63 = this.rows;
                    if (i62 >= i63) {
                        break;
                    }
                    int i64 = (this.rowStride * i62) + i44;
                    int i65 = i62 * 2;
                    int i66 = (i63 * 2) + i65;
                    dArr2[i65] = dArr[i64];
                    dArr2[i65 + 1] = dArr[i64 + 1];
                    dArr2[i66] = dArr[i64 + 2];
                    dArr2[i66 + 1] = dArr[i64 + 3];
                    i62++;
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                int i67 = 0;
                while (true) {
                    int i68 = this.rows;
                    if (i67 >= i68) {
                        break;
                    }
                    int i69 = (this.rowStride * i67) + i44;
                    int i70 = i67 * 2;
                    int i71 = (i68 * 2) + i70;
                    dArr[i69] = dArr2[i70];
                    dArr[i69 + 1] = dArr2[i70 + 1];
                    dArr[i69 + 2] = dArr2[i71];
                    dArr[i69 + 3] = dArr2[i71 + 1];
                    i67++;
                }
            } else if (i46 == 2) {
                for (int i72 = 0; i72 < this.rows; i72++) {
                    int i73 = (this.rowStride * i72) + i44;
                    int i74 = i72 * 2;
                    dArr2[i74] = dArr[i73];
                    dArr2[i74 + 1] = dArr[i73 + 1];
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                for (int i75 = 0; i75 < this.rows; i75++) {
                    int i76 = (this.rowStride * i75) + i44;
                    int i77 = i75 * 2;
                    dArr[i76] = dArr2[i77];
                    dArr[i76 + 1] = dArr2[i77 + 1];
                }
            }
            if (i != 0) {
                for (int i78 = 0; i78 < this.rows; i78++) {
                    this.fftColumns.realInverse(dArr, (this.rowStride * i78) + i44, z);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0538 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void xdft3da_sub1(long r36, int r38, pl.edu.icm.jlargearrays.DoubleLargeArray r39, boolean r40) {
        /*
            Method dump skipped, instructions count: 1345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_sub1(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_sub2(int i, int i2, double[] dArr, boolean z) {
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr2 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(dArr, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(dArr, (this.rowStride * i10) + i8);
                    }
                }
                int i11 = this.columns;
                if (i11 > 4) {
                    for (int i12 = 0; i12 < this.columns; i12 += 8) {
                        int i13 = 0;
                        while (true) {
                            int i14 = this.rows;
                            if (i13 >= i14) {
                                break;
                            }
                            int i15 = (this.rowStride * i13) + i8 + i12;
                            int i16 = i13 * 2;
                            int i17 = (i14 * 2) + i16;
                            int i18 = (i14 * 2) + i17;
                            int i19 = (i14 * 2) + i18;
                            dArr2[i16] = dArr[i15];
                            dArr2[i16 + 1] = dArr[i15 + 1];
                            dArr2[i17] = dArr[i15 + 2];
                            dArr2[i17 + 1] = dArr[i15 + 3];
                            dArr2[i18] = dArr[i15 + 4];
                            dArr2[i18 + 1] = dArr[i15 + 5];
                            dArr2[i19] = dArr[i15 + 6];
                            dArr2[i19 + 1] = dArr[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(dArr2, 0);
                        this.fftRows.complexForward(dArr2, this.rows * 2);
                        this.fftRows.complexForward(dArr2, this.rows * 4);
                        this.fftRows.complexForward(dArr2, this.rows * 6);
                        int i20 = 0;
                        while (true) {
                            int i21 = this.rows;
                            if (i20 < i21) {
                                int i22 = (this.rowStride * i20) + i8 + i12;
                                int i23 = i20 * 2;
                                int i24 = (i21 * 2) + i23;
                                int i25 = (i21 * 2) + i24;
                                int i26 = (i21 * 2) + i25;
                                dArr[i22] = dArr2[i23];
                                dArr[i22 + 1] = dArr2[i23 + 1];
                                dArr[i22 + 2] = dArr2[i24];
                                dArr[i22 + 3] = dArr2[i24 + 1];
                                dArr[i22 + 4] = dArr2[i25];
                                dArr[i22 + 5] = dArr2[i25 + 1];
                                dArr[i22 + 6] = dArr2[i26];
                                dArr[i22 + 7] = dArr2[i26 + 1];
                                i20++;
                            }
                        }
                    }
                } else if (i11 == 4) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.rows;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = (this.rowStride * i27) + i8;
                        int i30 = i27 * 2;
                        int i31 = (i28 * 2) + i30;
                        dArr2[i30] = dArr[i29];
                        dArr2[i30 + 1] = dArr[i29 + 1];
                        dArr2[i31] = dArr[i29 + 2];
                        dArr2[i31 + 1] = dArr[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 < i33) {
                            int i34 = (this.rowStride * i32) + i8;
                            int i35 = i32 * 2;
                            int i36 = (i33 * 2) + i35;
                            dArr[i34] = dArr2[i35];
                            dArr[i34 + 1] = dArr2[i35 + 1];
                            dArr[i34 + 2] = dArr2[i36];
                            dArr[i34 + 3] = dArr2[i36 + 1];
                            i32++;
                        }
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        dArr2[i39] = dArr[i38];
                        dArr2[i39 + 1] = dArr[i38 + 1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        dArr[i41] = dArr2[i42];
                        dArr[i41 + 1] = dArr2[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(dArr, (this.rowStride * i45) + i44, z);
                }
            } else {
                for (int i46 = 0; i46 < this.rows; i46++) {
                    this.fftColumns.realInverse2(dArr, (this.rowStride * i46) + i44, z);
                }
            }
            int i47 = this.columns;
            if (i47 > 4) {
                for (int i48 = 0; i48 < this.columns; i48 += 8) {
                    int i49 = 0;
                    while (true) {
                        int i50 = this.rows;
                        if (i49 >= i50) {
                            break;
                        }
                        int i51 = (this.rowStride * i49) + i44 + i48;
                        int i52 = i49 * 2;
                        int i53 = (i50 * 2) + i52;
                        int i54 = (i50 * 2) + i53;
                        int i55 = (i50 * 2) + i54;
                        dArr2[i52] = dArr[i51];
                        dArr2[i52 + 1] = dArr[i51 + 1];
                        dArr2[i53] = dArr[i51 + 2];
                        dArr2[i53 + 1] = dArr[i51 + 3];
                        dArr2[i54] = dArr[i51 + 4];
                        dArr2[i54 + 1] = dArr[i51 + 5];
                        dArr2[i55] = dArr[i51 + 6];
                        dArr2[i55 + 1] = dArr[i51 + 7];
                        i49++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z);
                    int i56 = 0;
                    while (true) {
                        int i57 = this.rows;
                        if (i56 < i57) {
                            int i58 = (this.rowStride * i56) + i44 + i48;
                            int i59 = i56 * 2;
                            int i60 = (i57 * 2) + i59;
                            int i61 = (i57 * 2) + i60;
                            int i62 = (i57 * 2) + i61;
                            dArr[i58] = dArr2[i59];
                            dArr[i58 + 1] = dArr2[i59 + 1];
                            dArr[i58 + 2] = dArr2[i60];
                            dArr[i58 + 3] = dArr2[i60 + 1];
                            dArr[i58 + 4] = dArr2[i61];
                            dArr[i58 + 5] = dArr2[i61 + 1];
                            dArr[i58 + 6] = dArr2[i62];
                            dArr[i58 + 7] = dArr2[i62 + 1];
                            i56++;
                        }
                    }
                }
            } else if (i47 == 4) {
                int i63 = 0;
                while (true) {
                    int i64 = this.rows;
                    if (i63 >= i64) {
                        break;
                    }
                    int i65 = (this.rowStride * i63) + i44;
                    int i66 = i63 * 2;
                    int i67 = (i64 * 2) + i66;
                    dArr2[i66] = dArr[i65];
                    dArr2[i66 + 1] = dArr[i65 + 1];
                    dArr2[i67] = dArr[i65 + 2];
                    dArr2[i67 + 1] = dArr[i65 + 3];
                    i63++;
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                int i68 = 0;
                while (true) {
                    int i69 = this.rows;
                    if (i68 < i69) {
                        int i70 = (this.rowStride * i68) + i44;
                        int i71 = i68 * 2;
                        int i72 = (i69 * 2) + i71;
                        dArr[i70] = dArr2[i71];
                        dArr[i70 + 1] = dArr2[i71 + 1];
                        dArr[i70 + 2] = dArr2[i72];
                        dArr[i70 + 3] = dArr2[i72 + 1];
                        i68++;
                    }
                }
            } else if (i47 == 2) {
                for (int i73 = 0; i73 < this.rows; i73++) {
                    int i74 = (this.rowStride * i73) + i44;
                    int i75 = i73 * 2;
                    dArr2[i75] = dArr[i74];
                    dArr2[i75 + 1] = dArr[i74 + 1];
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                for (int i76 = 0; i76 < this.rows; i76++) {
                    int i77 = (this.rowStride * i76) + i44;
                    int i78 = i76 * 2;
                    dArr[i77] = dArr2[i78];
                    dArr[i77 + 1] = dArr2[i78 + 1];
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void xdft3da_sub2(long r36, int r38, pl.edu.icm.jlargearrays.DoubleLargeArray r39, boolean r40) {
        /*
            Method dump skipped, instructions count: 1329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_sub2(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_sub1(int i, int i2, double[][][] dArr, boolean z) {
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr2 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(dArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(dArr[i7][i9], 0);
                    }
                }
                int i10 = this.columns;
                if (i10 > 4) {
                    for (int i11 = 0; i11 < this.columns; i11 += 8) {
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 >= i13) {
                                break;
                            }
                            int i14 = i12 * 2;
                            int i15 = (i13 * 2) + i14;
                            int i16 = (i13 * 2) + i15;
                            int i17 = (i13 * 2) + i16;
                            double[] dArr3 = dArr[i7][i12];
                            dArr2[i14] = dArr3[i11];
                            dArr2[i14 + 1] = dArr3[i11 + 1];
                            dArr2[i15] = dArr3[i11 + 2];
                            dArr2[i15 + 1] = dArr3[i11 + 3];
                            dArr2[i16] = dArr3[i11 + 4];
                            dArr2[i16 + 1] = dArr3[i11 + 5];
                            dArr2[i17] = dArr3[i11 + 6];
                            dArr2[i17 + 1] = dArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(dArr2, 0);
                        this.fftRows.complexForward(dArr2, this.rows * 2);
                        this.fftRows.complexForward(dArr2, this.rows * 4);
                        this.fftRows.complexForward(dArr2, this.rows * 6);
                        int i18 = 0;
                        while (true) {
                            int i19 = this.rows;
                            if (i18 < i19) {
                                int i20 = i18 * 2;
                                int i21 = (i19 * 2) + i20;
                                int i22 = (i19 * 2) + i21;
                                int i23 = (i19 * 2) + i22;
                                double[] dArr4 = dArr[i7][i18];
                                dArr4[i11] = dArr2[i20];
                                dArr4[i11 + 1] = dArr2[i20 + 1];
                                dArr4[i11 + 2] = dArr2[i21];
                                dArr4[i11 + 3] = dArr2[i21 + 1];
                                dArr4[i11 + 4] = dArr2[i22];
                                dArr4[i11 + 5] = dArr2[i22 + 1];
                                dArr4[i11 + 6] = dArr2[i23];
                                dArr4[i11 + 7] = dArr2[i23 + 1];
                                i18++;
                            }
                        }
                    }
                } else if (i10 == 4) {
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 >= i25) {
                            break;
                        }
                        int i26 = i24 * 2;
                        int i27 = (i25 * 2) + i26;
                        double[] dArr5 = dArr[i7][i24];
                        dArr2[i26] = dArr5[0];
                        dArr2[i26 + 1] = dArr5[1];
                        dArr2[i27] = dArr5[2];
                        dArr2[i27 + 1] = dArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 < i29) {
                            int i30 = i28 * 2;
                            int i31 = (i29 * 2) + i30;
                            double[] dArr6 = dArr[i7][i28];
                            dArr6[0] = dArr2[i30];
                            dArr6[1] = dArr2[i30 + 1];
                            dArr6[2] = dArr2[i31];
                            dArr6[3] = dArr2[i31 + 1];
                            i28++;
                        }
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        double[] dArr7 = dArr[i7][i32];
                        dArr2[i33] = dArr7[0];
                        dArr2[i33 + 1] = dArr7[1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        double[] dArr8 = dArr[i7][i34];
                        dArr8[0] = dArr2[i35];
                        dArr8[1] = dArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(dArr[i36][i37], z);
                }
            }
            int i38 = this.columns;
            if (i38 > 4) {
                for (int i39 = 0; i39 < this.columns; i39 += 8) {
                    int i40 = 0;
                    while (true) {
                        int i41 = this.rows;
                        if (i40 >= i41) {
                            break;
                        }
                        int i42 = i40 * 2;
                        int i43 = (i41 * 2) + i42;
                        int i44 = (i41 * 2) + i43;
                        int i45 = (i41 * 2) + i44;
                        double[] dArr9 = dArr[i36][i40];
                        dArr2[i42] = dArr9[i39];
                        dArr2[i42 + 1] = dArr9[i39 + 1];
                        dArr2[i43] = dArr9[i39 + 2];
                        dArr2[i43 + 1] = dArr9[i39 + 3];
                        dArr2[i44] = dArr9[i39 + 4];
                        dArr2[i44 + 1] = dArr9[i39 + 5];
                        dArr2[i45] = dArr9[i39 + 6];
                        dArr2[i45 + 1] = dArr9[i39 + 7];
                        i40++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z);
                    int i46 = 0;
                    while (true) {
                        int i47 = this.rows;
                        if (i46 < i47) {
                            int i48 = i46 * 2;
                            int i49 = (i47 * 2) + i48;
                            int i50 = (i47 * 2) + i49;
                            int i51 = (i47 * 2) + i50;
                            double[] dArr10 = dArr[i36][i46];
                            dArr10[i39] = dArr2[i48];
                            dArr10[i39 + 1] = dArr2[i48 + 1];
                            dArr10[i39 + 2] = dArr2[i49];
                            dArr10[i39 + 3] = dArr2[i49 + 1];
                            dArr10[i39 + 4] = dArr2[i50];
                            dArr10[i39 + 5] = dArr2[i50 + 1];
                            dArr10[i39 + 6] = dArr2[i51];
                            dArr10[i39 + 7] = dArr2[i51 + 1];
                            i46++;
                        }
                    }
                }
            } else if (i38 == 4) {
                int i52 = 0;
                while (true) {
                    int i53 = this.rows;
                    if (i52 >= i53) {
                        break;
                    }
                    int i54 = i52 * 2;
                    int i55 = (i53 * 2) + i54;
                    double[] dArr11 = dArr[i36][i52];
                    dArr2[i54] = dArr11[0];
                    dArr2[i54 + 1] = dArr11[1];
                    dArr2[i55] = dArr11[2];
                    dArr2[i55 + 1] = dArr11[3];
                    i52++;
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                int i56 = 0;
                while (true) {
                    int i57 = this.rows;
                    if (i56 >= i57) {
                        break;
                    }
                    int i58 = i56 * 2;
                    int i59 = (i57 * 2) + i58;
                    double[] dArr12 = dArr[i36][i56];
                    dArr12[0] = dArr2[i58];
                    dArr12[1] = dArr2[i58 + 1];
                    dArr12[2] = dArr2[i59];
                    dArr12[3] = dArr2[i59 + 1];
                    i56++;
                }
            } else if (i38 == 2) {
                for (int i60 = 0; i60 < this.rows; i60++) {
                    int i61 = i60 * 2;
                    double[] dArr13 = dArr[i36][i60];
                    dArr2[i61] = dArr13[0];
                    dArr2[i61 + 1] = dArr13[1];
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                for (int i62 = 0; i62 < this.rows; i62++) {
                    int i63 = i62 * 2;
                    double[] dArr14 = dArr[i36][i62];
                    dArr14[0] = dArr2[i63];
                    dArr14[1] = dArr2[i63 + 1];
                }
            }
            if (i != 0) {
                for (int i64 = 0; i64 < this.rows; i64++) {
                    this.fftColumns.realInverse(dArr[i36][i64], z);
                }
            }
        }
    }

    private void xdft3da_sub2(int i, int i2, double[][][] dArr, boolean z) {
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        double[] dArr2 = new double[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(dArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(dArr[i7][i9]);
                    }
                }
                int i10 = this.columns;
                if (i10 > 4) {
                    for (int i11 = 0; i11 < this.columns; i11 += 8) {
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 >= i13) {
                                break;
                            }
                            int i14 = i12 * 2;
                            int i15 = (i13 * 2) + i14;
                            int i16 = (i13 * 2) + i15;
                            int i17 = (i13 * 2) + i16;
                            double[] dArr3 = dArr[i7][i12];
                            dArr2[i14] = dArr3[i11];
                            dArr2[i14 + 1] = dArr3[i11 + 1];
                            dArr2[i15] = dArr3[i11 + 2];
                            dArr2[i15 + 1] = dArr3[i11 + 3];
                            dArr2[i16] = dArr3[i11 + 4];
                            dArr2[i16 + 1] = dArr3[i11 + 5];
                            dArr2[i17] = dArr3[i11 + 6];
                            dArr2[i17 + 1] = dArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(dArr2, 0);
                        this.fftRows.complexForward(dArr2, this.rows * 2);
                        this.fftRows.complexForward(dArr2, this.rows * 4);
                        this.fftRows.complexForward(dArr2, this.rows * 6);
                        int i18 = 0;
                        while (true) {
                            int i19 = this.rows;
                            if (i18 < i19) {
                                int i20 = i18 * 2;
                                int i21 = (i19 * 2) + i20;
                                int i22 = (i19 * 2) + i21;
                                int i23 = (i19 * 2) + i22;
                                double[] dArr4 = dArr[i7][i18];
                                dArr4[i11] = dArr2[i20];
                                dArr4[i11 + 1] = dArr2[i20 + 1];
                                dArr4[i11 + 2] = dArr2[i21];
                                dArr4[i11 + 3] = dArr2[i21 + 1];
                                dArr4[i11 + 4] = dArr2[i22];
                                dArr4[i11 + 5] = dArr2[i22 + 1];
                                dArr4[i11 + 6] = dArr2[i23];
                                dArr4[i11 + 7] = dArr2[i23 + 1];
                                i18++;
                            }
                        }
                    }
                } else if (i10 == 4) {
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 >= i25) {
                            break;
                        }
                        int i26 = i24 * 2;
                        int i27 = (i25 * 2) + i26;
                        double[] dArr5 = dArr[i7][i24];
                        dArr2[i26] = dArr5[0];
                        dArr2[i26 + 1] = dArr5[1];
                        dArr2[i27] = dArr5[2];
                        dArr2[i27 + 1] = dArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    this.fftRows.complexForward(dArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 < i29) {
                            int i30 = i28 * 2;
                            int i31 = (i29 * 2) + i30;
                            double[] dArr6 = dArr[i7][i28];
                            dArr6[0] = dArr2[i30];
                            dArr6[1] = dArr2[i30 + 1];
                            dArr6[2] = dArr2[i31];
                            dArr6[3] = dArr2[i31 + 1];
                            i28++;
                        }
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        double[] dArr7 = dArr[i7][i32];
                        dArr2[i33] = dArr7[0];
                        dArr2[i33 + 1] = dArr7[1];
                    }
                    this.fftRows.complexForward(dArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        double[] dArr8 = dArr[i7][i34];
                        dArr8[0] = dArr2[i35];
                        dArr8[1] = dArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(dArr[i36][i37], z);
                }
            } else {
                for (int i38 = 0; i38 < this.rows; i38++) {
                    this.fftColumns.realInverse2(dArr[i36][i38], 0, z);
                }
            }
            int i39 = this.columns;
            if (i39 > 4) {
                for (int i40 = 0; i40 < this.columns; i40 += 8) {
                    int i41 = 0;
                    while (true) {
                        int i42 = this.rows;
                        if (i41 >= i42) {
                            break;
                        }
                        int i43 = i41 * 2;
                        int i44 = (i42 * 2) + i43;
                        int i45 = (i42 * 2) + i44;
                        int i46 = (i42 * 2) + i45;
                        double[] dArr9 = dArr[i36][i41];
                        dArr2[i43] = dArr9[i40];
                        dArr2[i43 + 1] = dArr9[i40 + 1];
                        dArr2[i44] = dArr9[i40 + 2];
                        dArr2[i44 + 1] = dArr9[i40 + 3];
                        dArr2[i45] = dArr9[i40 + 4];
                        dArr2[i45 + 1] = dArr9[i40 + 5];
                        dArr2[i46] = dArr9[i40 + 6];
                        dArr2[i46 + 1] = dArr9[i40 + 7];
                        i41++;
                    }
                    this.fftRows.complexInverse(dArr2, 0, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(dArr2, this.rows * 6, z);
                    int i47 = 0;
                    while (true) {
                        int i48 = this.rows;
                        if (i47 < i48) {
                            int i49 = i47 * 2;
                            int i50 = (i48 * 2) + i49;
                            int i51 = (i48 * 2) + i50;
                            int i52 = (i48 * 2) + i51;
                            double[] dArr10 = dArr[i36][i47];
                            dArr10[i40] = dArr2[i49];
                            dArr10[i40 + 1] = dArr2[i49 + 1];
                            dArr10[i40 + 2] = dArr2[i50];
                            dArr10[i40 + 3] = dArr2[i50 + 1];
                            dArr10[i40 + 4] = dArr2[i51];
                            dArr10[i40 + 5] = dArr2[i51 + 1];
                            dArr10[i40 + 6] = dArr2[i52];
                            dArr10[i40 + 7] = dArr2[i52 + 1];
                            i47++;
                        }
                    }
                }
            } else if (i39 == 4) {
                int i53 = 0;
                while (true) {
                    int i54 = this.rows;
                    if (i53 >= i54) {
                        break;
                    }
                    int i55 = i53 * 2;
                    int i56 = (i54 * 2) + i55;
                    double[] dArr11 = dArr[i36][i53];
                    dArr2[i55] = dArr11[0];
                    dArr2[i55 + 1] = dArr11[1];
                    dArr2[i56] = dArr11[2];
                    dArr2[i56 + 1] = dArr11[3];
                    i53++;
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                this.fftRows.complexInverse(dArr2, this.rows * 2, z);
                int i57 = 0;
                while (true) {
                    int i58 = this.rows;
                    if (i57 < i58) {
                        int i59 = i57 * 2;
                        int i60 = (i58 * 2) + i59;
                        double[] dArr12 = dArr[i36][i57];
                        dArr12[0] = dArr2[i59];
                        dArr12[1] = dArr2[i59 + 1];
                        dArr12[2] = dArr2[i60];
                        dArr12[3] = dArr2[i60 + 1];
                        i57++;
                    }
                }
            } else if (i39 == 2) {
                for (int i61 = 0; i61 < this.rows; i61++) {
                    int i62 = i61 * 2;
                    double[] dArr13 = dArr[i36][i61];
                    dArr2[i62] = dArr13[0];
                    dArr2[i62 + 1] = dArr13[1];
                }
                this.fftRows.complexInverse(dArr2, 0, z);
                for (int i63 = 0; i63 < this.rows; i63++) {
                    int i64 = i63 * 2;
                    double[] dArr14 = dArr[i36][i63];
                    dArr14[0] = dArr2[i64];
                    dArr14[1] = dArr2[i64 + 1];
                }
            }
        }
    }

    private void cdft3db_sub(int i, double[] dArr, boolean z) {
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        double[] dArr2 = new double[i4];
        if (i == -1) {
            if (i5 > 4) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    int i7 = this.rowStride * i6;
                    for (int i8 = 0; i8 < this.columns; i8 += 8) {
                        int i9 = 0;
                        while (true) {
                            int i10 = this.slices;
                            if (i9 >= i10) {
                                break;
                            }
                            int i11 = (this.sliceStride * i9) + i7 + i8;
                            int i12 = i9 * 2;
                            int i13 = (i10 * 2) + i12;
                            int i14 = (i10 * 2) + i13;
                            int i15 = (i10 * 2) + i14;
                            dArr2[i12] = dArr[i11];
                            dArr2[i12 + 1] = dArr[i11 + 1];
                            dArr2[i13] = dArr[i11 + 2];
                            dArr2[i13 + 1] = dArr[i11 + 3];
                            dArr2[i14] = dArr[i11 + 4];
                            dArr2[i14 + 1] = dArr[i11 + 5];
                            dArr2[i15] = dArr[i11 + 6];
                            dArr2[i15 + 1] = dArr[i11 + 7];
                            i9++;
                        }
                        this.fftSlices.complexForward(dArr2, 0);
                        this.fftSlices.complexForward(dArr2, this.slices * 2);
                        this.fftSlices.complexForward(dArr2, this.slices * 4);
                        this.fftSlices.complexForward(dArr2, this.slices * 6);
                        int i16 = 0;
                        while (true) {
                            int i17 = this.slices;
                            if (i16 < i17) {
                                int i18 = (this.sliceStride * i16) + i7 + i8;
                                int i19 = i16 * 2;
                                int i20 = (i17 * 2) + i19;
                                int i21 = (i17 * 2) + i20;
                                int i22 = (i17 * 2) + i21;
                                dArr[i18] = dArr2[i19];
                                dArr[i18 + 1] = dArr2[i19 + 1];
                                dArr[i18 + 2] = dArr2[i20];
                                dArr[i18 + 3] = dArr2[i20 + 1];
                                dArr[i18 + 4] = dArr2[i21];
                                dArr[i18 + 5] = dArr2[i21 + 1];
                                dArr[i18 + 6] = dArr2[i22];
                                dArr[i18 + 7] = dArr2[i22 + 1];
                                i16++;
                            }
                        }
                    }
                }
                return;
            }
            if (i5 != 4) {
                if (i5 == 2) {
                    for (int i23 = 0; i23 < this.rows; i23++) {
                        int i24 = this.rowStride * i23;
                        for (int i25 = 0; i25 < this.slices; i25++) {
                            int i26 = (this.sliceStride * i25) + i24;
                            int i27 = i25 * 2;
                            dArr2[i27] = dArr[i26];
                            dArr2[i27 + 1] = dArr[i26 + 1];
                        }
                        this.fftSlices.complexForward(dArr2, 0);
                        for (int i28 = 0; i28 < this.slices; i28++) {
                            int i29 = (this.sliceStride * i28) + i24;
                            int i30 = i28 * 2;
                            dArr[i29] = dArr2[i30];
                            dArr[i29 + 1] = dArr2[i30 + 1];
                        }
                    }
                    return;
                }
                return;
            }
            for (int i31 = 0; i31 < this.rows; i31++) {
                int i32 = this.rowStride * i31;
                int i33 = 0;
                while (true) {
                    int i34 = this.slices;
                    if (i33 >= i34) {
                        break;
                    }
                    int i35 = (this.sliceStride * i33) + i32;
                    int i36 = i33 * 2;
                    int i37 = (i34 * 2) + i36;
                    dArr2[i36] = dArr[i35];
                    dArr2[i36 + 1] = dArr[i35 + 1];
                    dArr2[i37] = dArr[i35 + 2];
                    dArr2[i37 + 1] = dArr[i35 + 3];
                    i33++;
                }
                this.fftSlices.complexForward(dArr2, 0);
                this.fftSlices.complexForward(dArr2, this.slices * 2);
                int i38 = 0;
                while (true) {
                    int i39 = this.slices;
                    if (i38 < i39) {
                        int i40 = (this.sliceStride * i38) + i32;
                        int i41 = i38 * 2;
                        int i42 = (i39 * 2) + i41;
                        dArr[i40] = dArr2[i41];
                        dArr[i40 + 1] = dArr2[i41 + 1];
                        dArr[i40 + 2] = dArr2[i42];
                        dArr[i40 + 3] = dArr2[i42 + 1];
                        i38++;
                    }
                }
            }
            return;
        }
        if (i5 > 4) {
            for (int i43 = 0; i43 < this.rows; i43++) {
                int i44 = this.rowStride * i43;
                for (int i45 = 0; i45 < this.columns; i45 += 8) {
                    int i46 = 0;
                    while (true) {
                        int i47 = this.slices;
                        if (i46 >= i47) {
                            break;
                        }
                        int i48 = (this.sliceStride * i46) + i44 + i45;
                        int i49 = i46 * 2;
                        int i50 = (i47 * 2) + i49;
                        int i51 = (i47 * 2) + i50;
                        int i52 = (i47 * 2) + i51;
                        dArr2[i49] = dArr[i48];
                        dArr2[i49 + 1] = dArr[i48 + 1];
                        dArr2[i50] = dArr[i48 + 2];
                        dArr2[i50 + 1] = dArr[i48 + 3];
                        dArr2[i51] = dArr[i48 + 4];
                        dArr2[i51 + 1] = dArr[i48 + 5];
                        dArr2[i52] = dArr[i48 + 6];
                        dArr2[i52 + 1] = dArr[i48 + 7];
                        i46++;
                    }
                    this.fftSlices.complexInverse(dArr2, 0, z);
                    this.fftSlices.complexInverse(dArr2, this.slices * 2, z);
                    this.fftSlices.complexInverse(dArr2, this.slices * 4, z);
                    this.fftSlices.complexInverse(dArr2, this.slices * 6, z);
                    int i53 = 0;
                    while (true) {
                        int i54 = this.slices;
                        if (i53 < i54) {
                            int i55 = (this.sliceStride * i53) + i44 + i45;
                            int i56 = i53 * 2;
                            int i57 = (i54 * 2) + i56;
                            int i58 = (i54 * 2) + i57;
                            int i59 = (i54 * 2) + i58;
                            dArr[i55] = dArr2[i56];
                            dArr[i55 + 1] = dArr2[i56 + 1];
                            dArr[i55 + 2] = dArr2[i57];
                            dArr[i55 + 3] = dArr2[i57 + 1];
                            dArr[i55 + 4] = dArr2[i58];
                            dArr[i55 + 5] = dArr2[i58 + 1];
                            dArr[i55 + 6] = dArr2[i59];
                            dArr[i55 + 7] = dArr2[i59 + 1];
                            i53++;
                        }
                    }
                }
            }
            return;
        }
        if (i5 != 4) {
            if (i5 == 2) {
                for (int i60 = 0; i60 < this.rows; i60++) {
                    int i61 = this.rowStride * i60;
                    for (int i62 = 0; i62 < this.slices; i62++) {
                        int i63 = (this.sliceStride * i62) + i61;
                        int i64 = i62 * 2;
                        dArr2[i64] = dArr[i63];
                        dArr2[i64 + 1] = dArr[i63 + 1];
                    }
                    this.fftSlices.complexInverse(dArr2, 0, z);
                    for (int i65 = 0; i65 < this.slices; i65++) {
                        int i66 = (this.sliceStride * i65) + i61;
                        int i67 = i65 * 2;
                        dArr[i66] = dArr2[i67];
                        dArr[i66 + 1] = dArr2[i67 + 1];
                    }
                }
                return;
            }
            return;
        }
        for (int i68 = 0; i68 < this.rows; i68++) {
            int i69 = this.rowStride * i68;
            int i70 = 0;
            while (true) {
                int i71 = this.slices;
                if (i70 >= i71) {
                    break;
                }
                int i72 = (this.sliceStride * i70) + i69;
                int i73 = i70 * 2;
                int i74 = (i71 * 2) + i73;
                dArr2[i73] = dArr[i72];
                dArr2[i73 + 1] = dArr[i72 + 1];
                dArr2[i74] = dArr[i72 + 2];
                dArr2[i74 + 1] = dArr[i72 + 3];
                i70++;
            }
            this.fftSlices.complexInverse(dArr2, 0, z);
            this.fftSlices.complexInverse(dArr2, this.slices * 2, z);
            int i75 = 0;
            while (true) {
                int i76 = this.slices;
                if (i75 < i76) {
                    int i77 = (this.sliceStride * i75) + i69;
                    int i78 = i75 * 2;
                    int i79 = (i76 * 2) + i78;
                    dArr[i77] = dArr2[i78];
                    dArr[i77 + 1] = dArr2[i78 + 1];
                    dArr[i77 + 2] = dArr2[i79];
                    dArr[i77 + 3] = dArr2[i79 + 1];
                    i75++;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0289  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cdft3db_sub(int r36, pl.edu.icm.jlargearrays.DoubleLargeArray r37, boolean r38) {
        /*
            Method dump skipped, instructions count: 1277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.cdft3db_sub(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft3db_sub(int i, double[][][] dArr, boolean z) {
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        double[] dArr2 = new double[i4];
        if (i == -1) {
            if (i5 > 4) {
                for (int i6 = 0; i6 < this.rows; i6++) {
                    for (int i7 = 0; i7 < this.columns; i7 += 8) {
                        int i8 = 0;
                        while (true) {
                            int i9 = this.slices;
                            if (i8 >= i9) {
                                break;
                            }
                            int i10 = i8 * 2;
                            int i11 = (i9 * 2) + i10;
                            int i12 = (i9 * 2) + i11;
                            int i13 = (i9 * 2) + i12;
                            double[] dArr3 = dArr[i8][i6];
                            dArr2[i10] = dArr3[i7];
                            dArr2[i10 + 1] = dArr3[i7 + 1];
                            dArr2[i11] = dArr3[i7 + 2];
                            dArr2[i11 + 1] = dArr3[i7 + 3];
                            dArr2[i12] = dArr3[i7 + 4];
                            dArr2[i12 + 1] = dArr3[i7 + 5];
                            dArr2[i13] = dArr3[i7 + 6];
                            dArr2[i13 + 1] = dArr3[i7 + 7];
                            i8++;
                        }
                        this.fftSlices.complexForward(dArr2, 0);
                        this.fftSlices.complexForward(dArr2, this.slices * 2);
                        this.fftSlices.complexForward(dArr2, this.slices * 4);
                        this.fftSlices.complexForward(dArr2, this.slices * 6);
                        int i14 = 0;
                        while (true) {
                            int i15 = this.slices;
                            if (i14 < i15) {
                                int i16 = i14 * 2;
                                int i17 = (i15 * 2) + i16;
                                int i18 = (i15 * 2) + i17;
                                int i19 = (i15 * 2) + i18;
                                double[] dArr4 = dArr[i14][i6];
                                dArr4[i7] = dArr2[i16];
                                dArr4[i7 + 1] = dArr2[i16 + 1];
                                dArr4[i7 + 2] = dArr2[i17];
                                dArr4[i7 + 3] = dArr2[i17 + 1];
                                dArr4[i7 + 4] = dArr2[i18];
                                dArr4[i7 + 5] = dArr2[i18 + 1];
                                dArr4[i7 + 6] = dArr2[i19];
                                dArr4[i7 + 7] = dArr2[i19 + 1];
                                i14++;
                            }
                        }
                    }
                }
                return;
            }
            if (i5 != 4) {
                if (i5 == 2) {
                    for (int i20 = 0; i20 < this.rows; i20++) {
                        for (int i21 = 0; i21 < this.slices; i21++) {
                            int i22 = i21 * 2;
                            double[] dArr5 = dArr[i21][i20];
                            dArr2[i22] = dArr5[0];
                            dArr2[i22 + 1] = dArr5[1];
                        }
                        this.fftSlices.complexForward(dArr2, 0);
                        for (int i23 = 0; i23 < this.slices; i23++) {
                            int i24 = i23 * 2;
                            double[] dArr6 = dArr[i23][i20];
                            dArr6[0] = dArr2[i24];
                            dArr6[1] = dArr2[i24 + 1];
                        }
                    }
                    return;
                }
                return;
            }
            for (int i25 = 0; i25 < this.rows; i25++) {
                int i26 = 0;
                while (true) {
                    int i27 = this.slices;
                    if (i26 >= i27) {
                        break;
                    }
                    int i28 = i26 * 2;
                    int i29 = (i27 * 2) + i28;
                    double[] dArr7 = dArr[i26][i25];
                    dArr2[i28] = dArr7[0];
                    dArr2[i28 + 1] = dArr7[1];
                    dArr2[i29] = dArr7[2];
                    dArr2[i29 + 1] = dArr7[3];
                    i26++;
                }
                this.fftSlices.complexForward(dArr2, 0);
                this.fftSlices.complexForward(dArr2, this.slices * 2);
                int i30 = 0;
                while (true) {
                    int i31 = this.slices;
                    if (i30 < i31) {
                        int i32 = i30 * 2;
                        int i33 = (i31 * 2) + i32;
                        double[] dArr8 = dArr[i30][i25];
                        dArr8[0] = dArr2[i32];
                        dArr8[1] = dArr2[i32 + 1];
                        dArr8[2] = dArr2[i33];
                        dArr8[3] = dArr2[i33 + 1];
                        i30++;
                    }
                }
            }
            return;
        }
        if (i5 > 4) {
            for (int i34 = 0; i34 < this.rows; i34++) {
                for (int i35 = 0; i35 < this.columns; i35 += 8) {
                    int i36 = 0;
                    while (true) {
                        int i37 = this.slices;
                        if (i36 >= i37) {
                            break;
                        }
                        int i38 = i36 * 2;
                        int i39 = (i37 * 2) + i38;
                        int i40 = (i37 * 2) + i39;
                        int i41 = (i37 * 2) + i40;
                        double[] dArr9 = dArr[i36][i34];
                        dArr2[i38] = dArr9[i35];
                        dArr2[i38 + 1] = dArr9[i35 + 1];
                        dArr2[i39] = dArr9[i35 + 2];
                        dArr2[i39 + 1] = dArr9[i35 + 3];
                        dArr2[i40] = dArr9[i35 + 4];
                        dArr2[i40 + 1] = dArr9[i35 + 5];
                        dArr2[i41] = dArr9[i35 + 6];
                        dArr2[i41 + 1] = dArr9[i35 + 7];
                        i36++;
                    }
                    this.fftSlices.complexInverse(dArr2, 0, z);
                    this.fftSlices.complexInverse(dArr2, this.slices * 2, z);
                    this.fftSlices.complexInverse(dArr2, this.slices * 4, z);
                    this.fftSlices.complexInverse(dArr2, this.slices * 6, z);
                    int i42 = 0;
                    while (true) {
                        int i43 = this.slices;
                        if (i42 < i43) {
                            int i44 = i42 * 2;
                            int i45 = (i43 * 2) + i44;
                            int i46 = (i43 * 2) + i45;
                            int i47 = (i43 * 2) + i46;
                            double[] dArr10 = dArr[i42][i34];
                            dArr10[i35] = dArr2[i44];
                            dArr10[i35 + 1] = dArr2[i44 + 1];
                            dArr10[i35 + 2] = dArr2[i45];
                            dArr10[i35 + 3] = dArr2[i45 + 1];
                            dArr10[i35 + 4] = dArr2[i46];
                            dArr10[i35 + 5] = dArr2[i46 + 1];
                            dArr10[i35 + 6] = dArr2[i47];
                            dArr10[i35 + 7] = dArr2[i47 + 1];
                            i42++;
                        }
                    }
                }
            }
            return;
        }
        if (i5 != 4) {
            if (i5 == 2) {
                for (int i48 = 0; i48 < this.rows; i48++) {
                    for (int i49 = 0; i49 < this.slices; i49++) {
                        int i50 = i49 * 2;
                        double[] dArr11 = dArr[i49][i48];
                        dArr2[i50] = dArr11[0];
                        dArr2[i50 + 1] = dArr11[1];
                    }
                    this.fftSlices.complexInverse(dArr2, 0, z);
                    for (int i51 = 0; i51 < this.slices; i51++) {
                        int i52 = i51 * 2;
                        double[] dArr12 = dArr[i51][i48];
                        dArr12[0] = dArr2[i52];
                        dArr12[1] = dArr2[i52 + 1];
                    }
                }
                return;
            }
            return;
        }
        for (int i53 = 0; i53 < this.rows; i53++) {
            int i54 = 0;
            while (true) {
                int i55 = this.slices;
                if (i54 >= i55) {
                    break;
                }
                int i56 = i54 * 2;
                int i57 = (i55 * 2) + i56;
                double[] dArr13 = dArr[i54][i53];
                dArr2[i56] = dArr13[0];
                dArr2[i56 + 1] = dArr13[1];
                dArr2[i57] = dArr13[2];
                dArr2[i57 + 1] = dArr13[3];
                i54++;
            }
            this.fftSlices.complexInverse(dArr2, 0, z);
            this.fftSlices.complexInverse(dArr2, this.slices * 2, z);
            int i58 = 0;
            while (true) {
                int i59 = this.slices;
                if (i58 < i59) {
                    int i60 = i58 * 2;
                    int i61 = (i59 * 2) + i60;
                    double[] dArr14 = dArr[i58][i53];
                    dArr14[0] = dArr2[i60];
                    dArr14[1] = dArr2[i60 + 1];
                    dArr14[2] = dArr2[i61];
                    dArr14[3] = dArr2[i61 + 1];
                    i58++;
                }
            }
        }
    }

    private void xdft3da_subth1(final int i, final int i2, final double[] dArr, final boolean z) {
        final int iMin = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        final int i7 = i5;
        Future[] futureArr = new Future[iMin];
        for (int i8 = 0; i8 < iMin; i8++) {
            final int i9 = i8;
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.51
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < DoubleFFT_3D.this.slices) {
                            int i11 = DoubleFFT_3D.this.sliceStride * i10;
                            if (i == 0) {
                                for (int i12 = 0; i12 < DoubleFFT_3D.this.rows; i12++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr, (DoubleFFT_3D.this.rowStride * i12) + i11);
                                }
                            } else {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr, (DoubleFFT_3D.this.rowStride * i13) + i11);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i14 = 0; i14 < DoubleFFT_3D.this.columns; i14 += 8) {
                                    for (int i15 = 0; i15 < DoubleFFT_3D.this.rows; i15++) {
                                        int i16 = (DoubleFFT_3D.this.rowStride * i15) + i11 + i14;
                                        int i17 = i15 * 2;
                                        int i18 = (DoubleFFT_3D.this.rows * 2) + i17;
                                        int i19 = (DoubleFFT_3D.this.rows * 2) + i18;
                                        int i20 = (DoubleFFT_3D.this.rows * 2) + i19;
                                        double[] dArr3 = dArr;
                                        dArr2[i17] = dArr3[i16];
                                        dArr2[i17 + 1] = dArr3[i16 + 1];
                                        dArr2[i18] = dArr3[i16 + 2];
                                        dArr2[i18 + 1] = dArr3[i16 + 3];
                                        dArr2[i19] = dArr3[i16 + 4];
                                        dArr2[i19 + 1] = dArr3[i16 + 5];
                                        dArr2[i20] = dArr3[i16 + 6];
                                        dArr2[i20 + 1] = dArr3[i16 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 6);
                                    for (int i21 = 0; i21 < DoubleFFT_3D.this.rows; i21++) {
                                        int i22 = (DoubleFFT_3D.this.rowStride * i21) + i11 + i14;
                                        int i23 = i21 * 2;
                                        int i24 = (DoubleFFT_3D.this.rows * 2) + i23;
                                        int i25 = (DoubleFFT_3D.this.rows * 2) + i24;
                                        int i26 = (DoubleFFT_3D.this.rows * 2) + i25;
                                        double[] dArr4 = dArr;
                                        dArr4[i22] = dArr2[i23];
                                        dArr4[i22 + 1] = dArr2[i23 + 1];
                                        dArr4[i22 + 2] = dArr2[i24];
                                        dArr4[i22 + 3] = dArr2[i24 + 1];
                                        dArr4[i22 + 4] = dArr2[i25];
                                        dArr4[i22 + 5] = dArr2[i25 + 1];
                                        dArr4[i22 + 6] = dArr2[i26];
                                        dArr4[i22 + 7] = dArr2[i26 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                    int i28 = (DoubleFFT_3D.this.rowStride * i27) + i11;
                                    int i29 = i27 * 2;
                                    int i30 = (DoubleFFT_3D.this.rows * 2) + i29;
                                    double[] dArr5 = dArr;
                                    dArr2[i29] = dArr5[i28];
                                    dArr2[i29 + 1] = dArr5[i28 + 1];
                                    dArr2[i30] = dArr5[i28 + 2];
                                    dArr2[i30 + 1] = dArr5[i28 + 3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                for (int i31 = 0; i31 < DoubleFFT_3D.this.rows; i31++) {
                                    int i32 = (DoubleFFT_3D.this.rowStride * i31) + i11;
                                    int i33 = i31 * 2;
                                    int i34 = (DoubleFFT_3D.this.rows * 2) + i33;
                                    double[] dArr6 = dArr;
                                    dArr6[i32] = dArr2[i33];
                                    dArr6[i32 + 1] = dArr2[i33 + 1];
                                    dArr6[i32 + 2] = dArr2[i34];
                                    dArr6[i32 + 3] = dArr2[i34 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i35 = 0; i35 < DoubleFFT_3D.this.rows; i35++) {
                                    int i36 = (DoubleFFT_3D.this.rowStride * i35) + i11;
                                    int i37 = i35 * 2;
                                    double[] dArr7 = dArr;
                                    dArr2[i37] = dArr7[i36];
                                    dArr2[i37 + 1] = dArr7[i36 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                for (int i38 = 0; i38 < DoubleFFT_3D.this.rows; i38++) {
                                    int i39 = (DoubleFFT_3D.this.rowStride * i38) + i11;
                                    int i40 = i38 * 2;
                                    double[] dArr8 = dArr;
                                    dArr8[i39] = dArr2[i40];
                                    dArr8[i39 + 1] = dArr2[i40 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i41 = i9;
                    while (i41 < DoubleFFT_3D.this.slices) {
                        int i42 = DoubleFFT_3D.this.sliceStride * i41;
                        if (i == 0) {
                            for (int i43 = 0; i43 < DoubleFFT_3D.this.rows; i43++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr, (DoubleFFT_3D.this.rowStride * i43) + i42, z);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i44 = 0; i44 < DoubleFFT_3D.this.columns; i44 += 8) {
                                for (int i45 = 0; i45 < DoubleFFT_3D.this.rows; i45++) {
                                    int i46 = (DoubleFFT_3D.this.rowStride * i45) + i42 + i44;
                                    int i47 = i45 * 2;
                                    int i48 = (DoubleFFT_3D.this.rows * 2) + i47;
                                    int i49 = (DoubleFFT_3D.this.rows * 2) + i48;
                                    int i50 = (DoubleFFT_3D.this.rows * 2) + i49;
                                    double[] dArr9 = dArr;
                                    dArr2[i47] = dArr9[i46];
                                    dArr2[i47 + 1] = dArr9[i46 + 1];
                                    dArr2[i48] = dArr9[i46 + 2];
                                    dArr2[i48 + 1] = dArr9[i46 + 3];
                                    dArr2[i49] = dArr9[i46 + 4];
                                    dArr2[i49 + 1] = dArr9[i46 + 5];
                                    dArr2[i50] = dArr9[i46 + 6];
                                    dArr2[i50 + 1] = dArr9[i46 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 4, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 6, z);
                                for (int i51 = 0; i51 < DoubleFFT_3D.this.rows; i51++) {
                                    int i52 = (DoubleFFT_3D.this.rowStride * i51) + i42 + i44;
                                    int i53 = i51 * 2;
                                    int i54 = (DoubleFFT_3D.this.rows * 2) + i53;
                                    int i55 = (DoubleFFT_3D.this.rows * 2) + i54;
                                    int i56 = (DoubleFFT_3D.this.rows * 2) + i55;
                                    double[] dArr10 = dArr;
                                    dArr10[i52] = dArr2[i53];
                                    dArr10[i52 + 1] = dArr2[i53 + 1];
                                    dArr10[i52 + 2] = dArr2[i54];
                                    dArr10[i52 + 3] = dArr2[i54 + 1];
                                    dArr10[i52 + 4] = dArr2[i55];
                                    dArr10[i52 + 5] = dArr2[i55 + 1];
                                    dArr10[i52 + 6] = dArr2[i56];
                                    dArr10[i52 + 7] = dArr2[i56 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i57 = 0; i57 < DoubleFFT_3D.this.rows; i57++) {
                                int i58 = (DoubleFFT_3D.this.rowStride * i57) + i42;
                                int i59 = i57 * 2;
                                int i60 = (DoubleFFT_3D.this.rows * 2) + i59;
                                double[] dArr11 = dArr;
                                dArr2[i59] = dArr11[i58];
                                dArr2[i59 + 1] = dArr11[i58 + 1];
                                dArr2[i60] = dArr11[i58 + 2];
                                dArr2[i60 + 1] = dArr11[i58 + 3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                            for (int i61 = 0; i61 < DoubleFFT_3D.this.rows; i61++) {
                                int i62 = (DoubleFFT_3D.this.rowStride * i61) + i42;
                                int i63 = i61 * 2;
                                int i64 = (DoubleFFT_3D.this.rows * 2) + i63;
                                double[] dArr12 = dArr;
                                dArr12[i62] = dArr2[i63];
                                dArr12[i62 + 1] = dArr2[i63 + 1];
                                dArr12[i62 + 2] = dArr2[i64];
                                dArr12[i62 + 3] = dArr2[i64 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i65 = 0; i65 < DoubleFFT_3D.this.rows; i65++) {
                                int i66 = (DoubleFFT_3D.this.rowStride * i65) + i42;
                                int i67 = i65 * 2;
                                double[] dArr13 = dArr;
                                dArr2[i67] = dArr13[i66];
                                dArr2[i67 + 1] = dArr13[i66 + 1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            for (int i68 = 0; i68 < DoubleFFT_3D.this.rows; i68++) {
                                int i69 = (DoubleFFT_3D.this.rowStride * i68) + i42;
                                int i70 = i68 * 2;
                                double[] dArr14 = dArr;
                                dArr14[i69] = dArr2[i70];
                                dArr14[i69 + 1] = dArr2[i70 + 1];
                            }
                        }
                        if (i != 0) {
                            for (int i71 = 0; i71 < DoubleFFT_3D.this.rows; i71++) {
                                DoubleFFT_3D.this.fftColumns.realInverse(dArr, (DoubleFFT_3D.this.rowStride * i71) + i42, z);
                            }
                        }
                        i41 += iMin;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[LOOP:0: B:13:0x0034->B:14:0x0036, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void xdft3da_subth1(final long r21, final int r23, final pl.edu.icm.jlargearrays.DoubleLargeArray r24, final boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r14 = org.jtransforms.fft.DoubleFFT_3D.class
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r2 = r13.slicesl
            long r0 = org.apache.commons.math3.util.FastMath.min(r0, r2)
            int r15 = (int) r0
            long r0 = r13.slicesl
            long r2 = r13.rowsl
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L19
            r0 = r2
        L19:
            r2 = 8
            long r0 = r0 * r2
            long r2 = r13.columnsl
            r4 = 4
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L28
            r2 = 1
        L26:
            long r0 = r0 >> r2
            goto L2e
        L28:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L2e
            r2 = 2
            goto L26
        L2e:
            r16 = r0
            java.util.concurrent.Future[] r0 = new java.util.concurrent.Future[r15]
            r1 = 0
            r12 = 0
        L34:
            if (r12 >= r15) goto L56
            long r6 = (long) r12
            org.jtransforms.fft.DoubleFFT_3D$52 r18 = new org.jtransforms.fft.DoubleFFT_3D$52
            r1 = r18
            r2 = r20
            r3 = r16
            r5 = r23
            r8 = r15
            r9 = r21
            r11 = r24
            r19 = r12
            r12 = r25
            r1.<init>()
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r18)
            r0[r19] = r1
            int r12 = r19 + 1
            goto L34
        L56:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r0)     // Catch: java.util.concurrent.ExecutionException -> L5b java.lang.InterruptedException -> L6b
            goto L7a
        L5b:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L7a
        L6b:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_subth1(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_subth2(final int i, final int i2, final double[] dArr, final boolean z) {
        final int iMin = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        final int i7 = i5;
        Future[] futureArr = new Future[iMin];
        for (int i8 = 0; i8 < iMin; i8++) {
            final int i9 = i8;
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.53
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < DoubleFFT_3D.this.slices) {
                            int i11 = DoubleFFT_3D.this.sliceStride * i10;
                            if (i == 0) {
                                for (int i12 = 0; i12 < DoubleFFT_3D.this.rows; i12++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr, (DoubleFFT_3D.this.rowStride * i12) + i11);
                                }
                            } else {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.rows; i13++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr, (DoubleFFT_3D.this.rowStride * i13) + i11);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i14 = 0; i14 < DoubleFFT_3D.this.columns; i14 += 8) {
                                    for (int i15 = 0; i15 < DoubleFFT_3D.this.rows; i15++) {
                                        int i16 = (DoubleFFT_3D.this.rowStride * i15) + i11 + i14;
                                        int i17 = i15 * 2;
                                        int i18 = (DoubleFFT_3D.this.rows * 2) + i17;
                                        int i19 = (DoubleFFT_3D.this.rows * 2) + i18;
                                        int i20 = (DoubleFFT_3D.this.rows * 2) + i19;
                                        double[] dArr3 = dArr;
                                        dArr2[i17] = dArr3[i16];
                                        dArr2[i17 + 1] = dArr3[i16 + 1];
                                        dArr2[i18] = dArr3[i16 + 2];
                                        dArr2[i18 + 1] = dArr3[i16 + 3];
                                        dArr2[i19] = dArr3[i16 + 4];
                                        dArr2[i19 + 1] = dArr3[i16 + 5];
                                        dArr2[i20] = dArr3[i16 + 6];
                                        dArr2[i20 + 1] = dArr3[i16 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 6);
                                    for (int i21 = 0; i21 < DoubleFFT_3D.this.rows; i21++) {
                                        int i22 = (DoubleFFT_3D.this.rowStride * i21) + i11 + i14;
                                        int i23 = i21 * 2;
                                        int i24 = (DoubleFFT_3D.this.rows * 2) + i23;
                                        int i25 = (DoubleFFT_3D.this.rows * 2) + i24;
                                        int i26 = (DoubleFFT_3D.this.rows * 2) + i25;
                                        double[] dArr4 = dArr;
                                        dArr4[i22] = dArr2[i23];
                                        dArr4[i22 + 1] = dArr2[i23 + 1];
                                        dArr4[i22 + 2] = dArr2[i24];
                                        dArr4[i22 + 3] = dArr2[i24 + 1];
                                        dArr4[i22 + 4] = dArr2[i25];
                                        dArr4[i22 + 5] = dArr2[i25 + 1];
                                        dArr4[i22 + 6] = dArr2[i26];
                                        dArr4[i22 + 7] = dArr2[i26 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                    int i28 = (DoubleFFT_3D.this.rowStride * i27) + i11;
                                    int i29 = i27 * 2;
                                    int i30 = (DoubleFFT_3D.this.rows * 2) + i29;
                                    double[] dArr5 = dArr;
                                    dArr2[i29] = dArr5[i28];
                                    dArr2[i29 + 1] = dArr5[i28 + 1];
                                    dArr2[i30] = dArr5[i28 + 2];
                                    dArr2[i30 + 1] = dArr5[i28 + 3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                for (int i31 = 0; i31 < DoubleFFT_3D.this.rows; i31++) {
                                    int i32 = (DoubleFFT_3D.this.rowStride * i31) + i11;
                                    int i33 = i31 * 2;
                                    int i34 = (DoubleFFT_3D.this.rows * 2) + i33;
                                    double[] dArr6 = dArr;
                                    dArr6[i32] = dArr2[i33];
                                    dArr6[i32 + 1] = dArr2[i33 + 1];
                                    dArr6[i32 + 2] = dArr2[i34];
                                    dArr6[i32 + 3] = dArr2[i34 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i35 = 0; i35 < DoubleFFT_3D.this.rows; i35++) {
                                    int i36 = (DoubleFFT_3D.this.rowStride * i35) + i11;
                                    int i37 = i35 * 2;
                                    double[] dArr7 = dArr;
                                    dArr2[i37] = dArr7[i36];
                                    dArr2[i37 + 1] = dArr7[i36 + 1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                for (int i38 = 0; i38 < DoubleFFT_3D.this.rows; i38++) {
                                    int i39 = (DoubleFFT_3D.this.rowStride * i38) + i11;
                                    int i40 = i38 * 2;
                                    double[] dArr8 = dArr;
                                    dArr8[i39] = dArr2[i40];
                                    dArr8[i39 + 1] = dArr2[i40 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i41 = i9;
                    while (i41 < DoubleFFT_3D.this.slices) {
                        int i42 = DoubleFFT_3D.this.sliceStride * i41;
                        if (i == 0) {
                            for (int i43 = 0; i43 < DoubleFFT_3D.this.rows; i43++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr, (DoubleFFT_3D.this.rowStride * i43) + i42, z);
                            }
                        } else {
                            for (int i44 = 0; i44 < DoubleFFT_3D.this.rows; i44++) {
                                DoubleFFT_3D.this.fftColumns.realInverse2(dArr, (DoubleFFT_3D.this.rowStride * i44) + i42, z);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i45 = 0; i45 < DoubleFFT_3D.this.columns; i45 += 8) {
                                for (int i46 = 0; i46 < DoubleFFT_3D.this.rows; i46++) {
                                    int i47 = (DoubleFFT_3D.this.rowStride * i46) + i42 + i45;
                                    int i48 = i46 * 2;
                                    int i49 = (DoubleFFT_3D.this.rows * 2) + i48;
                                    int i50 = (DoubleFFT_3D.this.rows * 2) + i49;
                                    int i51 = (DoubleFFT_3D.this.rows * 2) + i50;
                                    double[] dArr9 = dArr;
                                    dArr2[i48] = dArr9[i47];
                                    dArr2[i48 + 1] = dArr9[i47 + 1];
                                    dArr2[i49] = dArr9[i47 + 2];
                                    dArr2[i49 + 1] = dArr9[i47 + 3];
                                    dArr2[i50] = dArr9[i47 + 4];
                                    dArr2[i50 + 1] = dArr9[i47 + 5];
                                    dArr2[i51] = dArr9[i47 + 6];
                                    dArr2[i51 + 1] = dArr9[i47 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 4, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 6, z);
                                for (int i52 = 0; i52 < DoubleFFT_3D.this.rows; i52++) {
                                    int i53 = (DoubleFFT_3D.this.rowStride * i52) + i42 + i45;
                                    int i54 = i52 * 2;
                                    int i55 = (DoubleFFT_3D.this.rows * 2) + i54;
                                    int i56 = (DoubleFFT_3D.this.rows * 2) + i55;
                                    int i57 = (DoubleFFT_3D.this.rows * 2) + i56;
                                    double[] dArr10 = dArr;
                                    dArr10[i53] = dArr2[i54];
                                    dArr10[i53 + 1] = dArr2[i54 + 1];
                                    dArr10[i53 + 2] = dArr2[i55];
                                    dArr10[i53 + 3] = dArr2[i55 + 1];
                                    dArr10[i53 + 4] = dArr2[i56];
                                    dArr10[i53 + 5] = dArr2[i56 + 1];
                                    dArr10[i53 + 6] = dArr2[i57];
                                    dArr10[i53 + 7] = dArr2[i57 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i58 = 0; i58 < DoubleFFT_3D.this.rows; i58++) {
                                int i59 = (DoubleFFT_3D.this.rowStride * i58) + i42;
                                int i60 = i58 * 2;
                                int i61 = (DoubleFFT_3D.this.rows * 2) + i60;
                                double[] dArr11 = dArr;
                                dArr2[i60] = dArr11[i59];
                                dArr2[i60 + 1] = dArr11[i59 + 1];
                                dArr2[i61] = dArr11[i59 + 2];
                                dArr2[i61 + 1] = dArr11[i59 + 3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                            for (int i62 = 0; i62 < DoubleFFT_3D.this.rows; i62++) {
                                int i63 = (DoubleFFT_3D.this.rowStride * i62) + i42;
                                int i64 = i62 * 2;
                                int i65 = (DoubleFFT_3D.this.rows * 2) + i64;
                                double[] dArr12 = dArr;
                                dArr12[i63] = dArr2[i64];
                                dArr12[i63 + 1] = dArr2[i64 + 1];
                                dArr12[i63 + 2] = dArr2[i65];
                                dArr12[i63 + 3] = dArr2[i65 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i66 = 0; i66 < DoubleFFT_3D.this.rows; i66++) {
                                int i67 = (DoubleFFT_3D.this.rowStride * i66) + i42;
                                int i68 = i66 * 2;
                                double[] dArr13 = dArr;
                                dArr2[i68] = dArr13[i67];
                                dArr2[i68 + 1] = dArr13[i67 + 1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            for (int i69 = 0; i69 < DoubleFFT_3D.this.rows; i69++) {
                                int i70 = (DoubleFFT_3D.this.rowStride * i69) + i42;
                                int i71 = i69 * 2;
                                double[] dArr14 = dArr;
                                dArr14[i70] = dArr2[i71];
                                dArr14[i70 + 1] = dArr2[i71 + 1];
                            }
                        }
                        i41 += iMin;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[LOOP:0: B:13:0x0034->B:14:0x0036, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void xdft3da_subth2(final long r21, final int r23, final pl.edu.icm.jlargearrays.DoubleLargeArray r24, final boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r14 = org.jtransforms.fft.DoubleFFT_3D.class
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r2 = r13.slicesl
            long r0 = org.apache.commons.math3.util.FastMath.min(r0, r2)
            int r15 = (int) r0
            long r0 = r13.slicesl
            long r2 = r13.rowsl
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L19
            r0 = r2
        L19:
            r2 = 8
            long r0 = r0 * r2
            long r2 = r13.columnsl
            r4 = 4
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L28
            r2 = 1
        L26:
            long r0 = r0 >> r2
            goto L2e
        L28:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L2e
            r2 = 2
            goto L26
        L2e:
            r16 = r0
            java.util.concurrent.Future[] r0 = new java.util.concurrent.Future[r15]
            r1 = 0
            r12 = 0
        L34:
            if (r12 >= r15) goto L56
            long r6 = (long) r12
            org.jtransforms.fft.DoubleFFT_3D$54 r18 = new org.jtransforms.fft.DoubleFFT_3D$54
            r1 = r18
            r2 = r20
            r3 = r16
            r5 = r23
            r8 = r15
            r9 = r21
            r11 = r24
            r19 = r12
            r12 = r25
            r1.<init>()
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r18)
            r0[r19] = r1
            int r12 = r19 + 1
            goto L34
        L56:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r0)     // Catch: java.util.concurrent.ExecutionException -> L5b java.lang.InterruptedException -> L6b
            goto L7a
        L5b:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L7a
        L6b:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r14.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.xdft3da_subth2(long, int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void xdft3da_subth1(final int i, final int i2, final double[][][] dArr, final boolean z) {
        final int iMin = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        final int i7 = i5;
        Future[] futureArr = new Future[iMin];
        for (int i8 = 0; i8 < iMin; i8++) {
            final int i9 = i8;
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.55
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < DoubleFFT_3D.this.slices) {
                            if (i == 0) {
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr[i10][i11]);
                                }
                            } else {
                                for (int i12 = 0; i12 < DoubleFFT_3D.this.rows; i12++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr[i10][i12], 0);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.columns; i13 += 8) {
                                    for (int i14 = 0; i14 < DoubleFFT_3D.this.rows; i14++) {
                                        int i15 = i14 * 2;
                                        int i16 = (DoubleFFT_3D.this.rows * 2) + i15;
                                        int i17 = (DoubleFFT_3D.this.rows * 2) + i16;
                                        int i18 = (DoubleFFT_3D.this.rows * 2) + i17;
                                        double[] dArr3 = dArr[i10][i14];
                                        dArr2[i15] = dArr3[i13];
                                        dArr2[i15 + 1] = dArr3[i13 + 1];
                                        dArr2[i16] = dArr3[i13 + 2];
                                        dArr2[i16 + 1] = dArr3[i13 + 3];
                                        dArr2[i17] = dArr3[i13 + 4];
                                        dArr2[i17 + 1] = dArr3[i13 + 5];
                                        dArr2[i18] = dArr3[i13 + 6];
                                        dArr2[i18 + 1] = dArr3[i13 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 6);
                                    for (int i19 = 0; i19 < DoubleFFT_3D.this.rows; i19++) {
                                        int i20 = i19 * 2;
                                        int i21 = (DoubleFFT_3D.this.rows * 2) + i20;
                                        int i22 = (DoubleFFT_3D.this.rows * 2) + i21;
                                        int i23 = (DoubleFFT_3D.this.rows * 2) + i22;
                                        double[] dArr4 = dArr[i10][i19];
                                        dArr4[i13] = dArr2[i20];
                                        dArr4[i13 + 1] = dArr2[i20 + 1];
                                        dArr4[i13 + 2] = dArr2[i21];
                                        dArr4[i13 + 3] = dArr2[i21 + 1];
                                        dArr4[i13 + 4] = dArr2[i22];
                                        dArr4[i13 + 5] = dArr2[i22 + 1];
                                        dArr4[i13 + 6] = dArr2[i23];
                                        dArr4[i13 + 7] = dArr2[i23 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i24 = 0; i24 < DoubleFFT_3D.this.rows; i24++) {
                                    int i25 = i24 * 2;
                                    int i26 = (DoubleFFT_3D.this.rows * 2) + i25;
                                    double[] dArr5 = dArr[i10][i24];
                                    dArr2[i25] = dArr5[0];
                                    dArr2[i25 + 1] = dArr5[1];
                                    dArr2[i26] = dArr5[2];
                                    dArr2[i26 + 1] = dArr5[3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                    int i28 = i27 * 2;
                                    int i29 = (DoubleFFT_3D.this.rows * 2) + i28;
                                    double[] dArr6 = dArr[i10][i27];
                                    dArr6[0] = dArr2[i28];
                                    dArr6[1] = dArr2[i28 + 1];
                                    dArr6[2] = dArr2[i29];
                                    dArr6[3] = dArr2[i29 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i30 = 0; i30 < DoubleFFT_3D.this.rows; i30++) {
                                    int i31 = i30 * 2;
                                    double[] dArr7 = dArr[i10][i30];
                                    dArr2[i31] = dArr7[0];
                                    dArr2[i31 + 1] = dArr7[1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                for (int i32 = 0; i32 < DoubleFFT_3D.this.rows; i32++) {
                                    int i33 = i32 * 2;
                                    double[] dArr8 = dArr[i10][i32];
                                    dArr8[0] = dArr2[i33];
                                    dArr8[1] = dArr2[i33 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i34 = i9;
                    while (i34 < DoubleFFT_3D.this.slices) {
                        if (i == 0) {
                            for (int i35 = 0; i35 < DoubleFFT_3D.this.rows; i35++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr[i34][i35], z);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i36 = 0; i36 < DoubleFFT_3D.this.columns; i36 += 8) {
                                for (int i37 = 0; i37 < DoubleFFT_3D.this.rows; i37++) {
                                    int i38 = i37 * 2;
                                    int i39 = (DoubleFFT_3D.this.rows * 2) + i38;
                                    int i40 = (DoubleFFT_3D.this.rows * 2) + i39;
                                    int i41 = (DoubleFFT_3D.this.rows * 2) + i40;
                                    double[] dArr9 = dArr[i34][i37];
                                    dArr2[i38] = dArr9[i36];
                                    dArr2[i38 + 1] = dArr9[i36 + 1];
                                    dArr2[i39] = dArr9[i36 + 2];
                                    dArr2[i39 + 1] = dArr9[i36 + 3];
                                    dArr2[i40] = dArr9[i36 + 4];
                                    dArr2[i40 + 1] = dArr9[i36 + 5];
                                    dArr2[i41] = dArr9[i36 + 6];
                                    dArr2[i41 + 1] = dArr9[i36 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 4, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 6, z);
                                for (int i42 = 0; i42 < DoubleFFT_3D.this.rows; i42++) {
                                    int i43 = i42 * 2;
                                    int i44 = (DoubleFFT_3D.this.rows * 2) + i43;
                                    int i45 = (DoubleFFT_3D.this.rows * 2) + i44;
                                    int i46 = (DoubleFFT_3D.this.rows * 2) + i45;
                                    double[] dArr10 = dArr[i34][i42];
                                    dArr10[i36] = dArr2[i43];
                                    dArr10[i36 + 1] = dArr2[i43 + 1];
                                    dArr10[i36 + 2] = dArr2[i44];
                                    dArr10[i36 + 3] = dArr2[i44 + 1];
                                    dArr10[i36 + 4] = dArr2[i45];
                                    dArr10[i36 + 5] = dArr2[i45 + 1];
                                    dArr10[i36 + 6] = dArr2[i46];
                                    dArr10[i36 + 7] = dArr2[i46 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i47 = 0; i47 < DoubleFFT_3D.this.rows; i47++) {
                                int i48 = i47 * 2;
                                int i49 = (DoubleFFT_3D.this.rows * 2) + i48;
                                double[] dArr11 = dArr[i34][i47];
                                dArr2[i48] = dArr11[0];
                                dArr2[i48 + 1] = dArr11[1];
                                dArr2[i49] = dArr11[2];
                                dArr2[i49 + 1] = dArr11[3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                            for (int i50 = 0; i50 < DoubleFFT_3D.this.rows; i50++) {
                                int i51 = i50 * 2;
                                int i52 = (DoubleFFT_3D.this.rows * 2) + i51;
                                double[] dArr12 = dArr[i34][i50];
                                dArr12[0] = dArr2[i51];
                                dArr12[1] = dArr2[i51 + 1];
                                dArr12[2] = dArr2[i52];
                                dArr12[3] = dArr2[i52 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i53 = 0; i53 < DoubleFFT_3D.this.rows; i53++) {
                                int i54 = i53 * 2;
                                double[] dArr13 = dArr[i34][i53];
                                dArr2[i54] = dArr13[0];
                                dArr2[i54 + 1] = dArr13[1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            for (int i55 = 0; i55 < DoubleFFT_3D.this.rows; i55++) {
                                int i56 = i55 * 2;
                                double[] dArr14 = dArr[i34][i55];
                                dArr14[0] = dArr2[i56];
                                dArr14[1] = dArr2[i56 + 1];
                            }
                        }
                        if (i != 0) {
                            for (int i57 = 0; i57 < DoubleFFT_3D.this.rows; i57++) {
                                DoubleFFT_3D.this.fftColumns.realInverse(dArr[i34][i57], z);
                            }
                        }
                        i34 += iMin;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft3da_subth2(final int i, final int i2, final double[][][] dArr, final boolean z) {
        final int iMin = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
        int i3 = this.slices;
        int i4 = this.rows;
        if (i3 < i4) {
            i3 = i4;
        }
        int i5 = i3 * 8;
        int i6 = this.columns;
        if (i6 == 4) {
            i5 >>= 1;
        } else if (i6 < 4) {
            i5 >>= 2;
        }
        final int i7 = i5;
        Future[] futureArr = new Future[iMin];
        for (int i8 = 0; i8 < iMin; i8++) {
            final int i9 = i8;
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.56
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < DoubleFFT_3D.this.slices) {
                            if (i == 0) {
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.rows; i11++) {
                                    DoubleFFT_3D.this.fftColumns.complexForward(dArr[i10][i11]);
                                }
                            } else {
                                for (int i12 = 0; i12 < DoubleFFT_3D.this.rows; i12++) {
                                    DoubleFFT_3D.this.fftColumns.realForward(dArr[i10][i12]);
                                }
                            }
                            if (DoubleFFT_3D.this.columns > 4) {
                                for (int i13 = 0; i13 < DoubleFFT_3D.this.columns; i13 += 8) {
                                    for (int i14 = 0; i14 < DoubleFFT_3D.this.rows; i14++) {
                                        int i15 = i14 * 2;
                                        int i16 = (DoubleFFT_3D.this.rows * 2) + i15;
                                        int i17 = (DoubleFFT_3D.this.rows * 2) + i16;
                                        int i18 = (DoubleFFT_3D.this.rows * 2) + i17;
                                        double[] dArr3 = dArr[i10][i14];
                                        dArr2[i15] = dArr3[i13];
                                        dArr2[i15 + 1] = dArr3[i13 + 1];
                                        dArr2[i16] = dArr3[i13 + 2];
                                        dArr2[i16 + 1] = dArr3[i13 + 3];
                                        dArr2[i17] = dArr3[i13 + 4];
                                        dArr2[i17 + 1] = dArr3[i13 + 5];
                                        dArr2[i18] = dArr3[i13 + 6];
                                        dArr2[i18 + 1] = dArr3[i13 + 7];
                                    }
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 4);
                                    DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 6);
                                    for (int i19 = 0; i19 < DoubleFFT_3D.this.rows; i19++) {
                                        int i20 = i19 * 2;
                                        int i21 = (DoubleFFT_3D.this.rows * 2) + i20;
                                        int i22 = (DoubleFFT_3D.this.rows * 2) + i21;
                                        int i23 = (DoubleFFT_3D.this.rows * 2) + i22;
                                        double[] dArr4 = dArr[i10][i19];
                                        dArr4[i13] = dArr2[i20];
                                        dArr4[i13 + 1] = dArr2[i20 + 1];
                                        dArr4[i13 + 2] = dArr2[i21];
                                        dArr4[i13 + 3] = dArr2[i21 + 1];
                                        dArr4[i13 + 4] = dArr2[i22];
                                        dArr4[i13 + 5] = dArr2[i22 + 1];
                                        dArr4[i13 + 6] = dArr2[i23];
                                        dArr4[i13 + 7] = dArr2[i23 + 1];
                                    }
                                }
                            } else if (DoubleFFT_3D.this.columns == 4) {
                                for (int i24 = 0; i24 < DoubleFFT_3D.this.rows; i24++) {
                                    int i25 = i24 * 2;
                                    int i26 = (DoubleFFT_3D.this.rows * 2) + i25;
                                    double[] dArr5 = dArr[i10][i24];
                                    dArr2[i25] = dArr5[0];
                                    dArr2[i25 + 1] = dArr5[1];
                                    dArr2[i26] = dArr5[2];
                                    dArr2[i26 + 1] = dArr5[3];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, DoubleFFT_3D.this.rows * 2);
                                for (int i27 = 0; i27 < DoubleFFT_3D.this.rows; i27++) {
                                    int i28 = i27 * 2;
                                    int i29 = (DoubleFFT_3D.this.rows * 2) + i28;
                                    double[] dArr6 = dArr[i10][i27];
                                    dArr6[0] = dArr2[i28];
                                    dArr6[1] = dArr2[i28 + 1];
                                    dArr6[2] = dArr2[i29];
                                    dArr6[3] = dArr2[i29 + 1];
                                }
                            } else if (DoubleFFT_3D.this.columns == 2) {
                                for (int i30 = 0; i30 < DoubleFFT_3D.this.rows; i30++) {
                                    int i31 = i30 * 2;
                                    double[] dArr7 = dArr[i10][i30];
                                    dArr2[i31] = dArr7[0];
                                    dArr2[i31 + 1] = dArr7[1];
                                }
                                DoubleFFT_3D.this.fftRows.complexForward(dArr2, 0);
                                for (int i32 = 0; i32 < DoubleFFT_3D.this.rows; i32++) {
                                    int i33 = i32 * 2;
                                    double[] dArr8 = dArr[i10][i32];
                                    dArr8[0] = dArr2[i33];
                                    dArr8[1] = dArr2[i33 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i34 = i9;
                    while (i34 < DoubleFFT_3D.this.slices) {
                        if (i == 0) {
                            for (int i35 = 0; i35 < DoubleFFT_3D.this.rows; i35++) {
                                DoubleFFT_3D.this.fftColumns.complexInverse(dArr[i34][i35], z);
                            }
                        } else {
                            for (int i36 = 0; i36 < DoubleFFT_3D.this.rows; i36++) {
                                DoubleFFT_3D.this.fftColumns.realInverse2(dArr[i34][i36], 0, z);
                            }
                        }
                        if (DoubleFFT_3D.this.columns > 4) {
                            for (int i37 = 0; i37 < DoubleFFT_3D.this.columns; i37 += 8) {
                                for (int i38 = 0; i38 < DoubleFFT_3D.this.rows; i38++) {
                                    int i39 = i38 * 2;
                                    int i40 = (DoubleFFT_3D.this.rows * 2) + i39;
                                    int i41 = (DoubleFFT_3D.this.rows * 2) + i40;
                                    int i42 = (DoubleFFT_3D.this.rows * 2) + i41;
                                    double[] dArr9 = dArr[i34][i38];
                                    dArr2[i39] = dArr9[i37];
                                    dArr2[i39 + 1] = dArr9[i37 + 1];
                                    dArr2[i40] = dArr9[i37 + 2];
                                    dArr2[i40 + 1] = dArr9[i37 + 3];
                                    dArr2[i41] = dArr9[i37 + 4];
                                    dArr2[i41 + 1] = dArr9[i37 + 5];
                                    dArr2[i42] = dArr9[i37 + 6];
                                    dArr2[i42 + 1] = dArr9[i37 + 7];
                                }
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 4, z);
                                DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 6, z);
                                for (int i43 = 0; i43 < DoubleFFT_3D.this.rows; i43++) {
                                    int i44 = i43 * 2;
                                    int i45 = (DoubleFFT_3D.this.rows * 2) + i44;
                                    int i46 = (DoubleFFT_3D.this.rows * 2) + i45;
                                    int i47 = (DoubleFFT_3D.this.rows * 2) + i46;
                                    double[] dArr10 = dArr[i34][i43];
                                    dArr10[i37] = dArr2[i44];
                                    dArr10[i37 + 1] = dArr2[i44 + 1];
                                    dArr10[i37 + 2] = dArr2[i45];
                                    dArr10[i37 + 3] = dArr2[i45 + 1];
                                    dArr10[i37 + 4] = dArr2[i46];
                                    dArr10[i37 + 5] = dArr2[i46 + 1];
                                    dArr10[i37 + 6] = dArr2[i47];
                                    dArr10[i37 + 7] = dArr2[i47 + 1];
                                }
                            }
                        } else if (DoubleFFT_3D.this.columns == 4) {
                            for (int i48 = 0; i48 < DoubleFFT_3D.this.rows; i48++) {
                                int i49 = i48 * 2;
                                int i50 = (DoubleFFT_3D.this.rows * 2) + i49;
                                double[] dArr11 = dArr[i34][i48];
                                dArr2[i49] = dArr11[0];
                                dArr2[i49 + 1] = dArr11[1];
                                dArr2[i50] = dArr11[2];
                                dArr2[i50 + 1] = dArr11[3];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, DoubleFFT_3D.this.rows * 2, z);
                            for (int i51 = 0; i51 < DoubleFFT_3D.this.rows; i51++) {
                                int i52 = i51 * 2;
                                int i53 = (DoubleFFT_3D.this.rows * 2) + i52;
                                double[] dArr12 = dArr[i34][i51];
                                dArr12[0] = dArr2[i52];
                                dArr12[1] = dArr2[i52 + 1];
                                dArr12[2] = dArr2[i53];
                                dArr12[3] = dArr2[i53 + 1];
                            }
                        } else if (DoubleFFT_3D.this.columns == 2) {
                            for (int i54 = 0; i54 < DoubleFFT_3D.this.rows; i54++) {
                                int i55 = i54 * 2;
                                double[] dArr13 = dArr[i34][i54];
                                dArr2[i55] = dArr13[0];
                                dArr2[i55 + 1] = dArr13[1];
                            }
                            DoubleFFT_3D.this.fftRows.complexInverse(dArr2, 0, z);
                            for (int i56 = 0; i56 < DoubleFFT_3D.this.rows; i56++) {
                                int i57 = i56 * 2;
                                double[] dArr14 = dArr[i34][i56];
                                dArr14[0] = dArr2[i57];
                                dArr14[1] = dArr2[i57 + 1];
                            }
                        }
                        i34 += iMin;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void cdft3db_subth(final int i, final double[] dArr, final boolean z) {
        final int iMin = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.rows);
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        final int i6 = i4;
        Future[] futureArr = new Future[iMin];
        for (int i7 = 0; i7 < iMin; i7++) {
            final int i8 = i7;
            futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.57
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i6];
                    if (i == -1) {
                        if (DoubleFFT_3D.this.columns > 4) {
                            int i9 = i8;
                            while (i9 < DoubleFFT_3D.this.rows) {
                                int i10 = DoubleFFT_3D.this.rowStride * i9;
                                for (int i11 = 0; i11 < DoubleFFT_3D.this.columns; i11 += 8) {
                                    for (int i12 = 0; i12 < DoubleFFT_3D.this.slices; i12++) {
                                        int i13 = (DoubleFFT_3D.this.sliceStride * i12) + i10 + i11;
                                        int i14 = i12 * 2;
                                        int i15 = (DoubleFFT_3D.this.slices * 2) + i14;
                                        int i16 = (DoubleFFT_3D.this.slices * 2) + i15;
                                        int i17 = (DoubleFFT_3D.this.slices * 2) + i16;
                                        double[] dArr3 = dArr;
                                        dArr2[i14] = dArr3[i13];
                                        dArr2[i14 + 1] = dArr3[i13 + 1];
                                        dArr2[i15] = dArr3[i13 + 2];
                                        dArr2[i15 + 1] = dArr3[i13 + 3];
                                        dArr2[i16] = dArr3[i13 + 4];
                                        dArr2[i16 + 1] = dArr3[i13 + 5];
                                        dArr2[i17] = dArr3[i13 + 6];
                                        dArr2[i17 + 1] = dArr3[i13 + 7];
                                    }
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, 0);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 2);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 4);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 6);
                                    for (int i18 = 0; i18 < DoubleFFT_3D.this.slices; i18++) {
                                        int i19 = (DoubleFFT_3D.this.sliceStride * i18) + i10 + i11;
                                        int i20 = i18 * 2;
                                        int i21 = (DoubleFFT_3D.this.slices * 2) + i20;
                                        int i22 = (DoubleFFT_3D.this.slices * 2) + i21;
                                        int i23 = (DoubleFFT_3D.this.slices * 2) + i22;
                                        double[] dArr4 = dArr;
                                        dArr4[i19] = dArr2[i20];
                                        dArr4[i19 + 1] = dArr2[i20 + 1];
                                        dArr4[i19 + 2] = dArr2[i21];
                                        dArr4[i19 + 3] = dArr2[i21 + 1];
                                        dArr4[i19 + 4] = dArr2[i22];
                                        dArr4[i19 + 5] = dArr2[i22 + 1];
                                        dArr4[i19 + 6] = dArr2[i23];
                                        dArr4[i19 + 7] = dArr2[i23 + 1];
                                    }
                                }
                                i9 += iMin;
                            }
                            return;
                        }
                        if (DoubleFFT_3D.this.columns == 4) {
                            int i24 = i8;
                            while (i24 < DoubleFFT_3D.this.rows) {
                                int i25 = DoubleFFT_3D.this.rowStride * i24;
                                for (int i26 = 0; i26 < DoubleFFT_3D.this.slices; i26++) {
                                    int i27 = (DoubleFFT_3D.this.sliceStride * i26) + i25;
                                    int i28 = i26 * 2;
                                    int i29 = (DoubleFFT_3D.this.slices * 2) + i28;
                                    double[] dArr5 = dArr;
                                    dArr2[i28] = dArr5[i27];
                                    dArr2[i28 + 1] = dArr5[i27 + 1];
                                    dArr2[i29] = dArr5[i27 + 2];
                                    dArr2[i29 + 1] = dArr5[i27 + 3];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2, 0);
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 2);
                                for (int i30 = 0; i30 < DoubleFFT_3D.this.slices; i30++) {
                                    int i31 = (DoubleFFT_3D.this.sliceStride * i30) + i25;
                                    int i32 = i30 * 2;
                                    int i33 = (DoubleFFT_3D.this.slices * 2) + i32;
                                    double[] dArr6 = dArr;
                                    dArr6[i31] = dArr2[i32];
                                    dArr6[i31 + 1] = dArr2[i32 + 1];
                                    dArr6[i31 + 2] = dArr2[i33];
                                    dArr6[i31 + 3] = dArr2[i33 + 1];
                                }
                                i24 += iMin;
                            }
                            return;
                        }
                        if (DoubleFFT_3D.this.columns == 2) {
                            int i34 = i8;
                            while (i34 < DoubleFFT_3D.this.rows) {
                                int i35 = DoubleFFT_3D.this.rowStride * i34;
                                for (int i36 = 0; i36 < DoubleFFT_3D.this.slices; i36++) {
                                    int i37 = (DoubleFFT_3D.this.sliceStride * i36) + i35;
                                    int i38 = i36 * 2;
                                    double[] dArr7 = dArr;
                                    dArr2[i38] = dArr7[i37];
                                    dArr2[i38 + 1] = dArr7[i37 + 1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2, 0);
                                for (int i39 = 0; i39 < DoubleFFT_3D.this.slices; i39++) {
                                    int i40 = (DoubleFFT_3D.this.sliceStride * i39) + i35;
                                    int i41 = i39 * 2;
                                    double[] dArr8 = dArr;
                                    dArr8[i40] = dArr2[i41];
                                    dArr8[i40 + 1] = dArr2[i41 + 1];
                                }
                                i34 += iMin;
                            }
                            return;
                        }
                        return;
                    }
                    if (DoubleFFT_3D.this.columns > 4) {
                        int i42 = i8;
                        while (i42 < DoubleFFT_3D.this.rows) {
                            int i43 = DoubleFFT_3D.this.rowStride * i42;
                            for (int i44 = 0; i44 < DoubleFFT_3D.this.columns; i44 += 8) {
                                for (int i45 = 0; i45 < DoubleFFT_3D.this.slices; i45++) {
                                    int i46 = (DoubleFFT_3D.this.sliceStride * i45) + i43 + i44;
                                    int i47 = i45 * 2;
                                    int i48 = (DoubleFFT_3D.this.slices * 2) + i47;
                                    int i49 = (DoubleFFT_3D.this.slices * 2) + i48;
                                    int i50 = (DoubleFFT_3D.this.slices * 2) + i49;
                                    double[] dArr9 = dArr;
                                    dArr2[i47] = dArr9[i46];
                                    dArr2[i47 + 1] = dArr9[i46 + 1];
                                    dArr2[i48] = dArr9[i46 + 2];
                                    dArr2[i48 + 1] = dArr9[i46 + 3];
                                    dArr2[i49] = dArr9[i46 + 4];
                                    dArr2[i49 + 1] = dArr9[i46 + 5];
                                    dArr2[i50] = dArr9[i46 + 6];
                                    dArr2[i50 + 1] = dArr9[i46 + 7];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, 0, z);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 2, z);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 4, z);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 6, z);
                                for (int i51 = 0; i51 < DoubleFFT_3D.this.slices; i51++) {
                                    int i52 = (DoubleFFT_3D.this.sliceStride * i51) + i43 + i44;
                                    int i53 = i51 * 2;
                                    int i54 = (DoubleFFT_3D.this.slices * 2) + i53;
                                    int i55 = (DoubleFFT_3D.this.slices * 2) + i54;
                                    int i56 = (DoubleFFT_3D.this.slices * 2) + i55;
                                    double[] dArr10 = dArr;
                                    dArr10[i52] = dArr2[i53];
                                    dArr10[i52 + 1] = dArr2[i53 + 1];
                                    dArr10[i52 + 2] = dArr2[i54];
                                    dArr10[i52 + 3] = dArr2[i54 + 1];
                                    dArr10[i52 + 4] = dArr2[i55];
                                    dArr10[i52 + 5] = dArr2[i55 + 1];
                                    dArr10[i52 + 6] = dArr2[i56];
                                    dArr10[i52 + 7] = dArr2[i56 + 1];
                                }
                            }
                            i42 += iMin;
                        }
                        return;
                    }
                    if (DoubleFFT_3D.this.columns == 4) {
                        int i57 = i8;
                        while (i57 < DoubleFFT_3D.this.rows) {
                            int i58 = DoubleFFT_3D.this.rowStride * i57;
                            for (int i59 = 0; i59 < DoubleFFT_3D.this.slices; i59++) {
                                int i60 = (DoubleFFT_3D.this.sliceStride * i59) + i58;
                                int i61 = i59 * 2;
                                int i62 = (DoubleFFT_3D.this.slices * 2) + i61;
                                double[] dArr11 = dArr;
                                dArr2[i61] = dArr11[i60];
                                dArr2[i61 + 1] = dArr11[i60 + 1];
                                dArr2[i62] = dArr11[i60 + 2];
                                dArr2[i62 + 1] = dArr11[i60 + 3];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, 0, z);
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 2, z);
                            for (int i63 = 0; i63 < DoubleFFT_3D.this.slices; i63++) {
                                int i64 = (DoubleFFT_3D.this.sliceStride * i63) + i58;
                                int i65 = i63 * 2;
                                int i66 = (DoubleFFT_3D.this.slices * 2) + i65;
                                double[] dArr12 = dArr;
                                dArr12[i64] = dArr2[i65];
                                dArr12[i64 + 1] = dArr2[i65 + 1];
                                dArr12[i64 + 2] = dArr2[i66];
                                dArr12[i64 + 3] = dArr2[i66 + 1];
                            }
                            i57 += iMin;
                        }
                        return;
                    }
                    if (DoubleFFT_3D.this.columns == 2) {
                        int i67 = i8;
                        while (i67 < DoubleFFT_3D.this.rows) {
                            int i68 = DoubleFFT_3D.this.rowStride * i67;
                            for (int i69 = 0; i69 < DoubleFFT_3D.this.slices; i69++) {
                                int i70 = (DoubleFFT_3D.this.sliceStride * i69) + i68;
                                int i71 = i69 * 2;
                                double[] dArr13 = dArr;
                                dArr2[i71] = dArr13[i70];
                                dArr2[i71 + 1] = dArr13[i70 + 1];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, 0, z);
                            for (int i72 = 0; i72 < DoubleFFT_3D.this.slices; i72++) {
                                int i73 = (DoubleFFT_3D.this.sliceStride * i72) + i68;
                                int i74 = i72 * 2;
                                double[] dArr14 = dArr;
                                dArr14[i73] = dArr2[i74];
                                dArr14[i73 + 1] = dArr2[i74 + 1];
                            }
                            i67 += iMin;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035 A[LOOP:0: B:13:0x0033->B:14:0x0035, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cdft3db_subth(final int r19, final pl.edu.icm.jlargearrays.DoubleLargeArray r20, final boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.DoubleFFT_3D> r12 = org.jtransforms.fft.DoubleFFT_3D.class
            int r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r0 = (long) r0
            long r2 = r11.rowsl
            long r0 = org.apache.commons.math3.util.FastMath.min(r0, r2)
            int r13 = (int) r0
            long r0 = r11.slicesl
            long r2 = r11.rowsl
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L19
            r0 = r2
        L19:
            r2 = 8
            long r0 = r0 * r2
            long r2 = r11.columnsl
            r4 = 4
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L28
            r2 = 1
        L26:
            long r0 = r0 >> r2
            goto L2e
        L28:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L2e
            r2 = 2
            goto L26
        L2e:
            r14 = r0
            java.util.concurrent.Future[] r0 = new java.util.concurrent.Future[r13]
            r1 = 0
            r10 = 0
        L33:
            if (r10 >= r13) goto L52
            long r6 = (long) r10
            org.jtransforms.fft.DoubleFFT_3D$58 r16 = new org.jtransforms.fft.DoubleFFT_3D$58
            r1 = r16
            r2 = r18
            r3 = r14
            r5 = r19
            r8 = r13
            r9 = r20
            r17 = r10
            r10 = r21
            r1.<init>()
            java.util.concurrent.Future r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit(r16)
            r0[r17] = r1
            int r10 = r17 + 1
            goto L33
        L52:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r0)     // Catch: java.util.concurrent.ExecutionException -> L57 java.lang.InterruptedException -> L67
            goto L76
        L57:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L76
        L67:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.cdft3db_subth(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void cdft3db_subth(final int i, final double[][][] dArr, final boolean z) {
        final int iMin = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.rows);
        int i2 = this.slices;
        int i3 = this.rows;
        if (i2 < i3) {
            i2 = i3;
        }
        int i4 = i2 * 8;
        int i5 = this.columns;
        if (i5 == 4) {
            i4 >>= 1;
        } else if (i5 < 4) {
            i4 >>= 2;
        }
        final int i6 = i4;
        Future[] futureArr = new Future[iMin];
        for (int i7 = 0; i7 < iMin; i7++) {
            final int i8 = i7;
            futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.59
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i6];
                    if (i == -1) {
                        if (DoubleFFT_3D.this.columns > 4) {
                            int i9 = i8;
                            while (i9 < DoubleFFT_3D.this.rows) {
                                for (int i10 = 0; i10 < DoubleFFT_3D.this.columns; i10 += 8) {
                                    for (int i11 = 0; i11 < DoubleFFT_3D.this.slices; i11++) {
                                        int i12 = i11 * 2;
                                        int i13 = (DoubleFFT_3D.this.slices * 2) + i12;
                                        int i14 = (DoubleFFT_3D.this.slices * 2) + i13;
                                        int i15 = (DoubleFFT_3D.this.slices * 2) + i14;
                                        double[] dArr3 = dArr[i11][i9];
                                        dArr2[i12] = dArr3[i10];
                                        dArr2[i12 + 1] = dArr3[i10 + 1];
                                        dArr2[i13] = dArr3[i10 + 2];
                                        dArr2[i13 + 1] = dArr3[i10 + 3];
                                        dArr2[i14] = dArr3[i10 + 4];
                                        dArr2[i14 + 1] = dArr3[i10 + 5];
                                        dArr2[i15] = dArr3[i10 + 6];
                                        dArr2[i15 + 1] = dArr3[i10 + 7];
                                    }
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, 0);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 2);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 4);
                                    DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 6);
                                    for (int i16 = 0; i16 < DoubleFFT_3D.this.slices; i16++) {
                                        int i17 = i16 * 2;
                                        int i18 = (DoubleFFT_3D.this.slices * 2) + i17;
                                        int i19 = (DoubleFFT_3D.this.slices * 2) + i18;
                                        int i20 = (DoubleFFT_3D.this.slices * 2) + i19;
                                        double[] dArr4 = dArr[i16][i9];
                                        dArr4[i10] = dArr2[i17];
                                        dArr4[i10 + 1] = dArr2[i17 + 1];
                                        dArr4[i10 + 2] = dArr2[i18];
                                        dArr4[i10 + 3] = dArr2[i18 + 1];
                                        dArr4[i10 + 4] = dArr2[i19];
                                        dArr4[i10 + 5] = dArr2[i19 + 1];
                                        dArr4[i10 + 6] = dArr2[i20];
                                        dArr4[i10 + 7] = dArr2[i20 + 1];
                                    }
                                }
                                i9 += iMin;
                            }
                            return;
                        }
                        if (DoubleFFT_3D.this.columns == 4) {
                            int i21 = i8;
                            while (i21 < DoubleFFT_3D.this.rows) {
                                for (int i22 = 0; i22 < DoubleFFT_3D.this.slices; i22++) {
                                    int i23 = i22 * 2;
                                    int i24 = (DoubleFFT_3D.this.slices * 2) + i23;
                                    double[] dArr5 = dArr[i22][i21];
                                    dArr2[i23] = dArr5[0];
                                    dArr2[i23 + 1] = dArr5[1];
                                    dArr2[i24] = dArr5[2];
                                    dArr2[i24 + 1] = dArr5[3];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2, 0);
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2, DoubleFFT_3D.this.slices * 2);
                                for (int i25 = 0; i25 < DoubleFFT_3D.this.slices; i25++) {
                                    int i26 = i25 * 2;
                                    int i27 = (DoubleFFT_3D.this.slices * 2) + i26;
                                    double[] dArr6 = dArr[i25][i21];
                                    dArr6[0] = dArr2[i26];
                                    dArr6[1] = dArr2[i26 + 1];
                                    dArr6[2] = dArr2[i27];
                                    dArr6[3] = dArr2[i27 + 1];
                                }
                                i21 += iMin;
                            }
                            return;
                        }
                        if (DoubleFFT_3D.this.columns == 2) {
                            int i28 = i8;
                            while (i28 < DoubleFFT_3D.this.rows) {
                                for (int i29 = 0; i29 < DoubleFFT_3D.this.slices; i29++) {
                                    int i30 = i29 * 2;
                                    double[] dArr7 = dArr[i29][i28];
                                    dArr2[i30] = dArr7[0];
                                    dArr2[i30 + 1] = dArr7[1];
                                }
                                DoubleFFT_3D.this.fftSlices.complexForward(dArr2, 0);
                                for (int i31 = 0; i31 < DoubleFFT_3D.this.slices; i31++) {
                                    int i32 = i31 * 2;
                                    double[] dArr8 = dArr[i31][i28];
                                    dArr8[0] = dArr2[i32];
                                    dArr8[1] = dArr2[i32 + 1];
                                }
                                i28 += iMin;
                            }
                            return;
                        }
                        return;
                    }
                    if (DoubleFFT_3D.this.columns > 4) {
                        int i33 = i8;
                        while (i33 < DoubleFFT_3D.this.rows) {
                            for (int i34 = 0; i34 < DoubleFFT_3D.this.columns; i34 += 8) {
                                for (int i35 = 0; i35 < DoubleFFT_3D.this.slices; i35++) {
                                    int i36 = i35 * 2;
                                    int i37 = (DoubleFFT_3D.this.slices * 2) + i36;
                                    int i38 = (DoubleFFT_3D.this.slices * 2) + i37;
                                    int i39 = (DoubleFFT_3D.this.slices * 2) + i38;
                                    double[] dArr9 = dArr[i35][i33];
                                    dArr2[i36] = dArr9[i34];
                                    dArr2[i36 + 1] = dArr9[i34 + 1];
                                    dArr2[i37] = dArr9[i34 + 2];
                                    dArr2[i37 + 1] = dArr9[i34 + 3];
                                    dArr2[i38] = dArr9[i34 + 4];
                                    dArr2[i38 + 1] = dArr9[i34 + 5];
                                    dArr2[i39] = dArr9[i34 + 6];
                                    dArr2[i39 + 1] = dArr9[i34 + 7];
                                }
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, 0, z);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 2, z);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 4, z);
                                DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 6, z);
                                for (int i40 = 0; i40 < DoubleFFT_3D.this.slices; i40++) {
                                    int i41 = i40 * 2;
                                    int i42 = (DoubleFFT_3D.this.slices * 2) + i41;
                                    int i43 = (DoubleFFT_3D.this.slices * 2) + i42;
                                    int i44 = (DoubleFFT_3D.this.slices * 2) + i43;
                                    double[] dArr10 = dArr[i40][i33];
                                    dArr10[i34] = dArr2[i41];
                                    dArr10[i34 + 1] = dArr2[i41 + 1];
                                    dArr10[i34 + 2] = dArr2[i42];
                                    dArr10[i34 + 3] = dArr2[i42 + 1];
                                    dArr10[i34 + 4] = dArr2[i43];
                                    dArr10[i34 + 5] = dArr2[i43 + 1];
                                    dArr10[i34 + 6] = dArr2[i44];
                                    dArr10[i34 + 7] = dArr2[i44 + 1];
                                }
                            }
                            i33 += iMin;
                        }
                        return;
                    }
                    if (DoubleFFT_3D.this.columns == 4) {
                        int i45 = i8;
                        while (i45 < DoubleFFT_3D.this.rows) {
                            for (int i46 = 0; i46 < DoubleFFT_3D.this.slices; i46++) {
                                int i47 = i46 * 2;
                                int i48 = (DoubleFFT_3D.this.slices * 2) + i47;
                                double[] dArr11 = dArr[i46][i45];
                                dArr2[i47] = dArr11[0];
                                dArr2[i47 + 1] = dArr11[1];
                                dArr2[i48] = dArr11[2];
                                dArr2[i48 + 1] = dArr11[3];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, 0, z);
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, DoubleFFT_3D.this.slices * 2, z);
                            for (int i49 = 0; i49 < DoubleFFT_3D.this.slices; i49++) {
                                int i50 = i49 * 2;
                                int i51 = (DoubleFFT_3D.this.slices * 2) + i50;
                                double[] dArr12 = dArr[i49][i45];
                                dArr12[0] = dArr2[i50];
                                dArr12[1] = dArr2[i50 + 1];
                                dArr12[2] = dArr2[i51];
                                dArr12[3] = dArr2[i51 + 1];
                            }
                            i45 += iMin;
                        }
                        return;
                    }
                    if (DoubleFFT_3D.this.columns == 2) {
                        int i52 = i8;
                        while (i52 < DoubleFFT_3D.this.rows) {
                            for (int i53 = 0; i53 < DoubleFFT_3D.this.slices; i53++) {
                                int i54 = i53 * 2;
                                double[] dArr13 = dArr[i53][i52];
                                dArr2[i54] = dArr13[0];
                                dArr2[i54 + 1] = dArr13[1];
                            }
                            DoubleFFT_3D.this.fftSlices.complexInverse(dArr2, 0, z);
                            for (int i55 = 0; i55 < DoubleFFT_3D.this.slices; i55++) {
                                int i56 = i55 * 2;
                                double[] dArr14 = dArr[i55][i52];
                                dArr14[0] = dArr2[i56];
                                dArr14[1] = dArr2[i56 + 1];
                            }
                            i52 += iMin;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void rdft3d_sub(int i, double[] dArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i >= 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                int i6 = this.sliceStride;
                int i7 = i5 * i6;
                int i8 = i4 * i6;
                double d = (dArr[i8] - dArr[i7]) * 0.5d;
                dArr[i7] = d;
                dArr[i8] = dArr[i8] - d;
                int i9 = i7 + 1;
                int i10 = i8 + 1;
                double d2 = (dArr[i10] + dArr[i9]) * 0.5d;
                dArr[i9] = d2;
                dArr[i10] = dArr[i10] - d2;
                int i11 = this.rowStride;
                int i12 = (i5 * i6) + (i3 * i11);
                int i13 = (i6 * i4) + (i11 * i3);
                double d3 = (dArr[i13] - dArr[i12]) * 0.5d;
                dArr[i12] = d3;
                dArr[i13] = dArr[i13] - d3;
                int i14 = i12 + 1;
                int i15 = i13 + 1;
                double d4 = (dArr[i15] + dArr[i14]) * 0.5d;
                dArr[i14] = d4;
                dArr[i15] = dArr[i15] - d4;
                for (int i16 = 1; i16 < i3; i16++) {
                    int i17 = this.rows - i16;
                    int i18 = this.sliceStride;
                    int i19 = this.rowStride;
                    int i20 = (i5 * i18) + (i17 * i19);
                    int i21 = (i4 * i18) + (i16 * i19);
                    double d5 = (dArr[i21] - dArr[i20]) * 0.5d;
                    dArr[i20] = d5;
                    dArr[i21] = dArr[i21] - d5;
                    int i22 = i20 + 1;
                    int i23 = i21 + 1;
                    double d6 = (dArr[i23] + dArr[i22]) * 0.5d;
                    dArr[i22] = d6;
                    dArr[i23] = dArr[i23] - d6;
                    int i24 = (i4 * i18) + (i17 * i19);
                    int i25 = (i18 * i5) + (i19 * i16);
                    double d7 = (dArr[i25] - dArr[i24]) * 0.5d;
                    dArr[i24] = d7;
                    dArr[i25] = dArr[i25] - d7;
                    int i26 = i24 + 1;
                    int i27 = i25 + 1;
                    double d8 = (dArr[i27] + dArr[i26]) * 0.5d;
                    dArr[i26] = d8;
                    dArr[i27] = dArr[i27] - d8;
                }
            }
            for (int i28 = 1; i28 < i3; i28++) {
                int i29 = this.rows - i28;
                int i30 = this.rowStride;
                int i31 = i29 * i30;
                int i32 = i28 * i30;
                double d9 = (dArr[i32] - dArr[i31]) * 0.5d;
                dArr[i31] = d9;
                dArr[i32] = dArr[i32] - d9;
                int i33 = i31 + 1;
                int i34 = i32 + 1;
                double d10 = (dArr[i34] + dArr[i33]) * 0.5d;
                dArr[i33] = d10;
                dArr[i34] = dArr[i34] - d10;
                int i35 = this.sliceStride;
                int i36 = (i2 * i35) + (i29 * i30);
                int i37 = (i35 * i2) + (i30 * i28);
                double d11 = (dArr[i37] - dArr[i36]) * 0.5d;
                dArr[i36] = d11;
                dArr[i37] = dArr[i37] - d11;
                int i38 = i36 + 1;
                int i39 = i37 + 1;
                double d12 = (dArr[i39] + dArr[i38]) * 0.5d;
                dArr[i38] = d12;
                dArr[i39] = dArr[i39] - d12;
            }
            return;
        }
        for (int i40 = 1; i40 < i2; i40++) {
            int i41 = this.slices - i40;
            int i42 = this.sliceStride;
            int i43 = i40 * i42;
            int i44 = i41 * i42;
            int i45 = this.rowStride;
            int i46 = (i40 * i42) + (i3 * i45);
            int i47 = (i42 * i41) + (i45 * i3);
            double d13 = dArr[i43];
            double d14 = dArr[i44];
            dArr[i43] = d13 + d14;
            dArr[i44] = d13 - d14;
            int i48 = i44 + 1;
            double d15 = dArr[i48];
            int i49 = i43 + 1;
            double d16 = dArr[i49];
            dArr[i49] = d16 + d15;
            dArr[i48] = d15 - d16;
            double d17 = dArr[i46];
            double d18 = dArr[i47];
            dArr[i46] = d17 + d18;
            dArr[i47] = d17 - d18;
            int i50 = i47 + 1;
            double d19 = dArr[i50];
            int i51 = i46 + 1;
            double d20 = dArr[i51];
            dArr[i51] = d20 + d19;
            dArr[i50] = d19 - d20;
            for (int i52 = 1; i52 < i3; i52++) {
                int i53 = this.rows - i52;
                int i54 = this.sliceStride;
                int i55 = this.rowStride;
                int i56 = (i40 * i54) + (i52 * i55);
                int i57 = (i41 * i54) + (i53 * i55);
                double d21 = dArr[i56];
                double d22 = dArr[i57];
                dArr[i56] = d21 + d22;
                dArr[i57] = d21 - d22;
                int i58 = i57 + 1;
                double d23 = dArr[i58];
                int i59 = i56 + 1;
                double d24 = dArr[i59];
                dArr[i59] = d24 + d23;
                dArr[i58] = d23 - d24;
                int i60 = (i41 * i54) + (i52 * i55);
                int i61 = (i54 * i40) + (i53 * i55);
                double d25 = dArr[i60];
                double d26 = dArr[i61];
                dArr[i60] = d25 + d26;
                dArr[i61] = d25 - d26;
                int i62 = i61 + 1;
                double d27 = dArr[i62];
                int i63 = i60 + 1;
                double d28 = dArr[i63];
                dArr[i63] = d28 + d27;
                dArr[i62] = d27 - d28;
            }
        }
        for (int i64 = 1; i64 < i3; i64++) {
            int i65 = this.rows - i64;
            int i66 = this.rowStride;
            int i67 = i64 * i66;
            int i68 = i65 * i66;
            double d29 = dArr[i67];
            double d30 = dArr[i68];
            dArr[i67] = d29 + d30;
            dArr[i68] = d29 - d30;
            int i69 = i68 + 1;
            double d31 = dArr[i69];
            int i70 = i67 + 1;
            double d32 = dArr[i70];
            dArr[i70] = d32 + d31;
            dArr[i69] = d31 - d32;
            int i71 = this.sliceStride;
            int i72 = (i2 * i71) + (i64 * i66);
            int i73 = (i71 * i2) + (i65 * i66);
            double d33 = dArr[i72];
            double d34 = dArr[i73];
            dArr[i72] = d33 + d34;
            dArr[i73] = d33 - d34;
            int i74 = i73 + 1;
            double d35 = dArr[i74];
            int i75 = i72 + 1;
            double d36 = dArr[i75];
            dArr[i75] = d36 + d35;
            dArr[i74] = d35 - d36;
        }
    }

    private void rdft3d_sub(int i, DoubleLargeArray doubleLargeArray) {
        long j = this.slicesl >> 1;
        long j2 = this.rowsl >> 1;
        if (i >= 0) {
            long j3 = 1;
            while (j3 < j) {
                long j4 = this.slicesl - j3;
                long j5 = this.sliceStridel;
                long j6 = j4 * j5;
                long j7 = j5 * j3;
                doubleLargeArray.setDouble(j6, (doubleLargeArray.getDouble(j7) - doubleLargeArray.getDouble(j6)) * 0.5d);
                doubleLargeArray.setDouble(j7, doubleLargeArray.getDouble(j7) - doubleLargeArray.getDouble(j6));
                long j8 = j6 + 1;
                long j9 = j7 + 1;
                doubleLargeArray.setDouble(j8, (doubleLargeArray.getDouble(j9) + doubleLargeArray.getDouble(j8)) * 0.5d);
                doubleLargeArray.setDouble(j9, doubleLargeArray.getDouble(j9) - doubleLargeArray.getDouble(j8));
                long j10 = this.sliceStridel;
                long j11 = this.rowStridel;
                long j12 = (j4 * j10) + (j2 * j11);
                long j13 = (j10 * j3) + (j11 * j2);
                doubleLargeArray.setDouble(j12, (doubleLargeArray.getDouble(j13) - doubleLargeArray.getDouble(j12)) * 0.5d);
                doubleLargeArray.setDouble(j13, doubleLargeArray.getDouble(j13) - doubleLargeArray.getDouble(j12));
                long j14 = j12 + 1;
                long j15 = j13 + 1;
                doubleLargeArray.setDouble(j14, (doubleLargeArray.getDouble(j15) + doubleLargeArray.getDouble(j14)) * 0.5d);
                doubleLargeArray.setDouble(j15, doubleLargeArray.getDouble(j15) - doubleLargeArray.getDouble(j14));
                long j16 = 1;
                while (j16 < j2) {
                    long j17 = this.rowsl - j16;
                    long j18 = this.sliceStridel;
                    long j19 = this.rowStridel;
                    long j20 = j2;
                    long j21 = (j4 * j18) + (j17 * j19);
                    long j22 = (j18 * j3) + (j19 * j16);
                    doubleLargeArray.setDouble(j21, (doubleLargeArray.getDouble(j22) - doubleLargeArray.getDouble(j21)) * 0.5d);
                    doubleLargeArray.setDouble(j22, doubleLargeArray.getDouble(j22) - doubleLargeArray.getDouble(j21));
                    long j23 = j21 + 1;
                    long j24 = j22 + 1;
                    doubleLargeArray.setDouble(j23, (doubleLargeArray.getDouble(j24) + doubleLargeArray.getDouble(j23)) * 0.5d);
                    doubleLargeArray.setDouble(j24, doubleLargeArray.getDouble(j24) - doubleLargeArray.getDouble(j23));
                    long j25 = this.sliceStridel;
                    long j26 = this.rowStridel;
                    long j27 = (j3 * j25) + (j17 * j26);
                    long j28 = (j25 * j4) + (j26 * j16);
                    doubleLargeArray.setDouble(j27, (doubleLargeArray.getDouble(j28) - doubleLargeArray.getDouble(j27)) * 0.5d);
                    doubleLargeArray.setDouble(j28, doubleLargeArray.getDouble(j28) - doubleLargeArray.getDouble(j27));
                    long j29 = j27 + 1;
                    long j30 = j28 + 1;
                    doubleLargeArray.setDouble(j29, (doubleLargeArray.getDouble(j30) + doubleLargeArray.getDouble(j29)) * 0.5d);
                    doubleLargeArray.setDouble(j30, doubleLargeArray.getDouble(j30) - doubleLargeArray.getDouble(j29));
                    j16++;
                    j2 = j20;
                }
                j3++;
                j2 = j2;
            }
            long j31 = j2;
            for (long j32 = 1; j32 < j31; j32++) {
                long j33 = this.rowsl - j32;
                long j34 = this.rowStridel;
                long j35 = j33 * j34;
                long j36 = j34 * j32;
                doubleLargeArray.setDouble(j35, (doubleLargeArray.getDouble(j36) - doubleLargeArray.getDouble(j35)) * 0.5d);
                doubleLargeArray.setDouble(j36, doubleLargeArray.getDouble(j36) - doubleLargeArray.getDouble(j35));
                long j37 = j35 + 1;
                long j38 = j36 + 1;
                doubleLargeArray.setDouble(j37, (doubleLargeArray.getDouble(j38) + doubleLargeArray.getDouble(j37)) * 0.5d);
                doubleLargeArray.setDouble(j38, doubleLargeArray.getDouble(j38) - doubleLargeArray.getDouble(j37));
                long j39 = this.sliceStridel;
                long j40 = this.rowStridel;
                long j41 = (j * j39) + (j33 * j40);
                long j42 = (j * j39) + (j40 * j32);
                doubleLargeArray.setDouble(j41, (doubleLargeArray.getDouble(j42) - doubleLargeArray.getDouble(j41)) * 0.5d);
                doubleLargeArray.setDouble(j42, doubleLargeArray.getDouble(j42) - doubleLargeArray.getDouble(j41));
                long j43 = j41 + 1;
                long j44 = j42 + 1;
                doubleLargeArray.setDouble(j43, (doubleLargeArray.getDouble(j44) + doubleLargeArray.getDouble(j43)) * 0.5d);
                doubleLargeArray.setDouble(j44, doubleLargeArray.getDouble(j44) - doubleLargeArray.getDouble(j43));
            }
            return;
        }
        long j45 = 1;
        while (j45 < j) {
            long j46 = this.slicesl - j45;
            long j47 = this.sliceStridel;
            long j48 = j45 * j47;
            long j49 = j46 * j47;
            long j50 = j;
            long j51 = this.rowStridel;
            long j52 = j45;
            long j53 = (j45 * j47) + (j2 * j51);
            long j54 = (j47 * j46) + (j51 * j2);
            double d = doubleLargeArray.getDouble(j48) - doubleLargeArray.getDouble(j49);
            doubleLargeArray.setDouble(j48, doubleLargeArray.getDouble(j48) + doubleLargeArray.getDouble(j49));
            doubleLargeArray.setDouble(j49, d);
            long j55 = j49 + 1;
            long j56 = j48 + 1;
            double d2 = doubleLargeArray.getDouble(j55) - doubleLargeArray.getDouble(j56);
            doubleLargeArray.setDouble(j56, doubleLargeArray.getDouble(j56) + doubleLargeArray.getDouble(j55));
            doubleLargeArray.setDouble(j55, d2);
            double d3 = doubleLargeArray.getDouble(j53) - doubleLargeArray.getDouble(j54);
            doubleLargeArray.setDouble(j53, doubleLargeArray.getDouble(j53) + doubleLargeArray.getDouble(j54));
            doubleLargeArray.setDouble(j54, d3);
            long j57 = j54 + 1;
            long j58 = j53 + 1;
            double d4 = doubleLargeArray.getDouble(j57) - doubleLargeArray.getDouble(j58);
            doubleLargeArray.setDouble(j58, doubleLargeArray.getDouble(j58) + doubleLargeArray.getDouble(j57));
            doubleLargeArray.setDouble(j57, d4);
            for (long j59 = 1; j59 < j2; j59++) {
                long j60 = this.rowsl - j59;
                long j61 = this.sliceStridel;
                long j62 = this.rowStridel;
                long j63 = (j52 * j61) + (j59 * j62);
                long j64 = (j61 * j46) + (j62 * j60);
                double d5 = doubleLargeArray.getDouble(j63) - doubleLargeArray.getDouble(j64);
                doubleLargeArray.setDouble(j63, doubleLargeArray.getDouble(j63) + doubleLargeArray.getDouble(j64));
                doubleLargeArray.setDouble(j64, d5);
                long j65 = j64 + 1;
                long j66 = j63 + 1;
                double d6 = doubleLargeArray.getDouble(j65) - doubleLargeArray.getDouble(j66);
                doubleLargeArray.setDouble(j66, doubleLargeArray.getDouble(j66) + doubleLargeArray.getDouble(j65));
                doubleLargeArray.setDouble(j65, d6);
                long j67 = this.sliceStridel;
                long j68 = this.rowStridel;
                long j69 = (j46 * j67) + (j59 * j68);
                long j70 = (j67 * j52) + (j60 * j68);
                double d7 = doubleLargeArray.getDouble(j69) - doubleLargeArray.getDouble(j70);
                doubleLargeArray.setDouble(j69, doubleLargeArray.getDouble(j69) + doubleLargeArray.getDouble(j70));
                doubleLargeArray.setDouble(j70, d7);
                long j71 = j70 + 1;
                long j72 = j69 + 1;
                double d8 = doubleLargeArray.getDouble(j71) - doubleLargeArray.getDouble(j72);
                doubleLargeArray.setDouble(j72, doubleLargeArray.getDouble(j72) + doubleLargeArray.getDouble(j71));
                doubleLargeArray.setDouble(j71, d8);
            }
            j45 = j52 + 1;
            j = j50;
        }
        long j73 = j;
        for (long j74 = 1; j74 < j2; j74++) {
            long j75 = this.rowsl - j74;
            long j76 = this.rowStridel;
            long j77 = j74 * j76;
            long j78 = j76 * j75;
            double d9 = doubleLargeArray.getDouble(j77) - doubleLargeArray.getDouble(j78);
            doubleLargeArray.setDouble(j77, doubleLargeArray.getDouble(j77) + doubleLargeArray.getDouble(j78));
            doubleLargeArray.setDouble(j78, d9);
            long j79 = j78 + 1;
            long j80 = j77 + 1;
            double d10 = doubleLargeArray.getDouble(j79) - doubleLargeArray.getDouble(j80);
            doubleLargeArray.setDouble(j80, doubleLargeArray.getDouble(j80) + doubleLargeArray.getDouble(j79));
            doubleLargeArray.setDouble(j79, d10);
            long j81 = this.sliceStridel;
            long j82 = this.rowStridel;
            long j83 = (j73 * j81) + (j74 * j82);
            long j84 = (j81 * j73) + (j75 * j82);
            double d11 = doubleLargeArray.getDouble(j83) - doubleLargeArray.getDouble(j84);
            doubleLargeArray.setDouble(j83, doubleLargeArray.getDouble(j83) + doubleLargeArray.getDouble(j84));
            doubleLargeArray.setDouble(j84, d11);
            long j85 = j84 + 1;
            long j86 = j83 + 1;
            double d12 = doubleLargeArray.getDouble(j85) - doubleLargeArray.getDouble(j86);
            doubleLargeArray.setDouble(j86, doubleLargeArray.getDouble(j86) + doubleLargeArray.getDouble(j85));
            doubleLargeArray.setDouble(j85, d12);
        }
    }

    private void rdft3d_sub(int i, double[][][] dArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i >= 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                double[][] dArr2 = dArr[i5];
                double[] dArr3 = dArr2[0];
                double[][] dArr4 = dArr[i4];
                double[] dArr5 = dArr4[0];
                double d = (dArr5[0] - dArr3[0]) * 0.5d;
                dArr3[0] = d;
                dArr5[0] = dArr5[0] - d;
                double d2 = (dArr5[1] + dArr3[1]) * 0.5d;
                dArr3[1] = d2;
                dArr5[1] = dArr5[1] - d2;
                double[] dArr6 = dArr2[i3];
                double[] dArr7 = dArr4[i3];
                double d3 = (dArr7[0] - dArr6[0]) * 0.5d;
                dArr6[0] = d3;
                dArr7[0] = dArr7[0] - d3;
                double d4 = (dArr7[1] + dArr6[1]) * 0.5d;
                dArr6[1] = d4;
                dArr7[1] = dArr7[1] - d4;
                for (int i6 = 1; i6 < i3; i6++) {
                    int i7 = this.rows - i6;
                    double[][] dArr8 = dArr[i5];
                    double[] dArr9 = dArr8[i7];
                    double[][] dArr10 = dArr[i4];
                    double[] dArr11 = dArr10[i6];
                    double d5 = (dArr11[0] - dArr9[0]) * 0.5d;
                    dArr9[0] = d5;
                    dArr11[0] = dArr11[0] - d5;
                    double d6 = (dArr11[1] + dArr9[1]) * 0.5d;
                    dArr9[1] = d6;
                    dArr11[1] = dArr11[1] - d6;
                    double[] dArr12 = dArr10[i7];
                    double[] dArr13 = dArr8[i6];
                    double d7 = (dArr13[0] - dArr12[0]) * 0.5d;
                    dArr12[0] = d7;
                    dArr13[0] = dArr13[0] - d7;
                    double d8 = (dArr13[1] + dArr12[1]) * 0.5d;
                    dArr12[1] = d8;
                    dArr13[1] = dArr13[1] - d8;
                }
            }
            for (int i8 = 1; i8 < i3; i8++) {
                int i9 = this.rows - i8;
                double[][] dArr14 = dArr[0];
                double[] dArr15 = dArr14[i9];
                double[] dArr16 = dArr14[i8];
                double d9 = (dArr16[0] - dArr15[0]) * 0.5d;
                dArr15[0] = d9;
                dArr16[0] = dArr16[0] - d9;
                double d10 = (dArr16[1] + dArr15[1]) * 0.5d;
                dArr15[1] = d10;
                dArr16[1] = dArr16[1] - d10;
                double[][] dArr17 = dArr[i2];
                double[] dArr18 = dArr17[i9];
                double[] dArr19 = dArr17[i8];
                double d11 = (dArr19[0] - dArr18[0]) * 0.5d;
                dArr18[0] = d11;
                dArr19[0] = dArr19[0] - d11;
                double d12 = (dArr19[1] + dArr18[1]) * 0.5d;
                dArr18[1] = d12;
                dArr19[1] = dArr19[1] - d12;
            }
            return;
        }
        for (int i10 = 1; i10 < i2; i10++) {
            int i11 = this.slices - i10;
            double[][] dArr20 = dArr[i10];
            double[] dArr21 = dArr20[0];
            double d13 = dArr21[0];
            double[][] dArr22 = dArr[i11];
            double[] dArr23 = dArr22[0];
            double d14 = dArr23[0];
            dArr21[0] = d13 + d14;
            dArr23[0] = d13 - d14;
            double d15 = dArr23[1];
            double d16 = dArr21[1];
            dArr21[1] = d16 + d15;
            dArr23[1] = d15 - d16;
            double[] dArr24 = dArr20[i3];
            double d17 = dArr24[0];
            double[] dArr25 = dArr22[i3];
            double d18 = dArr25[0];
            dArr24[0] = d17 + d18;
            dArr25[0] = d17 - d18;
            double d19 = dArr25[1];
            double d20 = dArr24[1];
            dArr24[1] = d20 + d19;
            dArr25[1] = d19 - d20;
            for (int i12 = 1; i12 < i3; i12++) {
                int i13 = this.rows - i12;
                double[][] dArr26 = dArr[i10];
                double[] dArr27 = dArr26[i12];
                double d21 = dArr27[0];
                double[][] dArr28 = dArr[i11];
                double[] dArr29 = dArr28[i13];
                double d22 = dArr29[0];
                dArr27[0] = d21 + d22;
                dArr29[0] = d21 - d22;
                double d23 = dArr29[1];
                double d24 = dArr27[1];
                dArr27[1] = d24 + d23;
                dArr29[1] = d23 - d24;
                double[] dArr30 = dArr28[i12];
                double d25 = dArr30[0];
                double[] dArr31 = dArr26[i13];
                double d26 = dArr31[0];
                dArr30[0] = d25 + d26;
                dArr31[0] = d25 - d26;
                double d27 = dArr31[1];
                double d28 = dArr30[1];
                dArr30[1] = d28 + d27;
                dArr31[1] = d27 - d28;
            }
        }
        for (int i14 = 1; i14 < i3; i14++) {
            int i15 = this.rows - i14;
            double[][] dArr32 = dArr[0];
            double[] dArr33 = dArr32[i14];
            double d29 = dArr33[0];
            double[] dArr34 = dArr32[i15];
            double d30 = dArr34[0];
            dArr33[0] = d29 + d30;
            dArr34[0] = d29 - d30;
            double d31 = dArr34[1];
            double d32 = dArr33[1];
            dArr33[1] = d32 + d31;
            dArr34[1] = d31 - d32;
            double[][] dArr35 = dArr[i2];
            double[] dArr36 = dArr35[i14];
            double d33 = dArr36[0];
            double[] dArr37 = dArr35[i15];
            double d34 = dArr37[0];
            dArr36[0] = d33 + d34;
            dArr37[0] = d33 - d34;
            double d35 = dArr37[1];
            double d36 = dArr36[1];
            dArr36[1] = d36 + d35;
            dArr37[1] = d35 - d36;
        }
    }

    private void fillSymmetric(final double[][][] dArr) {
        int i;
        final int i2 = this.columns * 2;
        final int i3 = this.rows / 2;
        int i4 = this.slices / 2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || (i = this.slices) < numberOfThreads) {
            int i5 = 0;
            while (true) {
                int i6 = this.slices;
                if (i5 >= i6) {
                    break;
                }
                int i7 = (i6 - i5) % i6;
                int i8 = 0;
                while (true) {
                    int i9 = this.rows;
                    if (i8 < i9) {
                        int i10 = (i9 - i8) % i9;
                        int i11 = 1;
                        while (i11 < this.columns) {
                            int i12 = i2 - i11;
                            double[] dArr2 = dArr[i7][i10];
                            double[] dArr3 = dArr[i5][i8];
                            int i13 = i11 + 2;
                            dArr2[i12] = -dArr3[i13];
                            dArr2[i12 - 1] = dArr3[i11 + 1];
                            i11 = i13;
                        }
                        i8++;
                    }
                }
                i5++;
            }
            int i14 = 0;
            while (true) {
                int i15 = this.slices;
                if (i14 >= i15) {
                    break;
                }
                int i16 = (i15 - i14) % i15;
                for (int i17 = 1; i17 < i3; i17++) {
                    int i18 = this.rows - i17;
                    double[] dArr4 = dArr[i16][i17];
                    int i19 = this.columns;
                    double[] dArr5 = dArr[i14][i18];
                    dArr4[i19] = dArr5[1];
                    dArr5[i19] = dArr5[1];
                    dArr4[i19 + 1] = -dArr5[0];
                    dArr5[i19 + 1] = dArr5[0];
                }
                i14++;
            }
            int i20 = 0;
            while (true) {
                int i21 = this.slices;
                if (i20 >= i21) {
                    break;
                }
                int i22 = (i21 - i20) % i21;
                for (int i23 = 1; i23 < i3; i23++) {
                    double[] dArr6 = dArr[i22][this.rows - i23];
                    double[] dArr7 = dArr[i20][i23];
                    dArr6[0] = dArr7[0];
                    dArr6[1] = -dArr7[1];
                }
                i20++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i24 = i / numberOfThreads;
            int i25 = 0;
            while (i25 < numberOfThreads) {
                final int i26 = i25 * i24;
                final int i27 = i25 == numberOfThreads + (-1) ? this.slices : i26 + i24;
                int i28 = i25;
                futureArr[i28] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.60
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i29 = i26; i29 < i27; i29++) {
                            int i30 = (DoubleFFT_3D.this.slices - i29) % DoubleFFT_3D.this.slices;
                            for (int i31 = 0; i31 < DoubleFFT_3D.this.rows; i31++) {
                                int i32 = (DoubleFFT_3D.this.rows - i31) % DoubleFFT_3D.this.rows;
                                int i33 = 1;
                                while (i33 < DoubleFFT_3D.this.columns) {
                                    int i34 = i2 - i33;
                                    double[][][] dArr8 = dArr;
                                    double[] dArr9 = dArr8[i30][i32];
                                    double[] dArr10 = dArr8[i29][i31];
                                    int i35 = i33 + 2;
                                    dArr9[i34] = -dArr10[i35];
                                    dArr9[i34 - 1] = dArr10[i33 + 1];
                                    i33 = i35;
                                }
                            }
                        }
                    }
                });
                i25 = i28 + 1;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i29 = 0;
            while (i29 < numberOfThreads) {
                final int i30 = i29 * i24;
                final int i31 = i29 == numberOfThreads + (-1) ? this.slices : i30 + i24;
                futureArr[i29] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.61
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i32 = i30; i32 < i31; i32++) {
                            int i33 = (DoubleFFT_3D.this.slices - i32) % DoubleFFT_3D.this.slices;
                            for (int i34 = 1; i34 < i3; i34++) {
                                int i35 = DoubleFFT_3D.this.rows - i34;
                                double[] dArr8 = dArr[i33][i34];
                                int i36 = DoubleFFT_3D.this.columns;
                                double[] dArr9 = dArr[i32][i35];
                                dArr8[i36] = dArr9[1];
                                int i37 = DoubleFFT_3D.this.columns;
                                double[][][] dArr10 = dArr;
                                dArr9[i37] = dArr10[i32][i35][1];
                                double[] dArr11 = dArr10[i33][i34];
                                int i38 = DoubleFFT_3D.this.columns + 1;
                                double[] dArr12 = dArr[i32][i35];
                                dArr11[i38] = -dArr12[0];
                                dArr12[DoubleFFT_3D.this.columns + 1] = dArr[i32][i35][0];
                            }
                        }
                    }
                });
                i29++;
                str = str;
            }
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            int i32 = 0;
            while (i32 < numberOfThreads) {
                final int i33 = i32 * i24;
                final int i34 = i32 == numberOfThreads + (-1) ? this.slices : i33 + i24;
                futureArr[i32] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.62
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i35 = i33; i35 < i34; i35++) {
                            int i36 = (DoubleFFT_3D.this.slices - i35) % DoubleFFT_3D.this.slices;
                            for (int i37 = 1; i37 < i3; i37++) {
                                int i38 = DoubleFFT_3D.this.rows - i37;
                                double[][][] dArr8 = dArr;
                                double[] dArr9 = dArr8[i36][i38];
                                double[] dArr10 = dArr8[i35][i37];
                                dArr9[0] = dArr10[0];
                                dArr9[1] = -dArr10[1];
                            }
                        }
                    }
                });
                i32++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
        }
        for (int i35 = 1; i35 < i4; i35++) {
            int i36 = this.slices - i35;
            double[][] dArr8 = dArr[i35];
            double[] dArr9 = dArr8[0];
            int i37 = this.columns;
            double[][] dArr10 = dArr[i36];
            double[] dArr11 = dArr10[0];
            dArr9[i37] = dArr11[1];
            dArr11[i37] = dArr11[1];
            dArr9[i37 + 1] = -dArr11[0];
            dArr11[i37 + 1] = dArr11[0];
            double[] dArr12 = dArr8[i3];
            double[] dArr13 = dArr10[i3];
            dArr12[i37] = dArr13[1];
            dArr13[i37] = dArr13[1];
            dArr12[i37 + 1] = -dArr13[0];
            dArr13[i37 + 1] = dArr13[0];
            dArr11[0] = dArr9[0];
            dArr11[1] = -dArr9[1];
            dArr13[0] = dArr12[0];
            dArr13[1] = -dArr12[1];
        }
        double[][] dArr14 = dArr[0];
        double[] dArr15 = dArr14[0];
        int i38 = this.columns;
        dArr15[i38] = dArr15[1];
        dArr15[1] = 0.0d;
        double[] dArr16 = dArr14[i3];
        dArr16[i38] = dArr16[1];
        dArr16[1] = 0.0d;
        double[][] dArr17 = dArr[i4];
        double[] dArr18 = dArr17[0];
        dArr18[i38] = dArr18[1];
        dArr18[1] = 0.0d;
        double[] dArr19 = dArr17[i3];
        dArr19[i38] = dArr19[1];
        dArr19[1] = 0.0d;
        dArr18[i38 + 1] = 0.0d;
        dArr19[i38 + 1] = 0.0d;
    }

    private void fillSymmetric(final double[] dArr) {
        int i;
        int i2;
        final int i3 = this.columns * 2;
        int i4 = this.rows;
        final int i5 = i4 / 2;
        int i6 = this.slices;
        int i7 = i6 / 2;
        final int i8 = i4 * i3;
        for (int i9 = i6 - 1; i9 >= 1; i9--) {
            int i10 = this.sliceStride * i9;
            int i11 = i10 * 2;
            for (int i12 = 0; i12 < this.rows; i12++) {
                int i13 = this.rowStride * i12;
                int i14 = i13 * 2;
                for (int i15 = 0; i15 < this.columns; i15 += 2) {
                    int i16 = i10 + i13 + i15;
                    int i17 = i11 + i14 + i15;
                    dArr[i17] = dArr[i16];
                    dArr[i16] = 0.0d;
                    int i18 = i16 + 1;
                    dArr[i17 + 1] = dArr[i18];
                    dArr[i18] = 0.0d;
                }
            }
        }
        int i19 = 1;
        while (true) {
            int i20 = this.rows;
            if (i19 >= i20) {
                break;
            }
            int i21 = (i20 - i19) * this.rowStride;
            int i22 = (i20 - i19) * i3;
            for (int i23 = 0; i23 < this.columns; i23 += 2) {
                int i24 = i21 + i23;
                int i25 = i22 + i23;
                dArr[i25] = dArr[i24];
                dArr[i24] = 0.0d;
                int i26 = i24 + 1;
                dArr[i25 + 1] = dArr[i26];
                dArr[i26] = 0.0d;
            }
            i19++;
        }
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads <= 1 || !this.useThreads || (i2 = this.slices) < numberOfThreads) {
            i = i7;
            int i27 = 0;
            while (true) {
                int i28 = this.slices;
                if (i27 >= i28) {
                    break;
                }
                int i29 = ((i28 - i27) % i28) * i8;
                int i30 = i27 * i8;
                int i31 = 0;
                while (true) {
                    int i32 = this.rows;
                    if (i31 < i32) {
                        int i33 = ((i32 - i31) % i32) * i3;
                        int i34 = i31 * i3;
                        int i35 = 1;
                        while (i35 < this.columns) {
                            int i36 = ((i29 + i33) + i3) - i35;
                            int i37 = i30 + i34 + i35;
                            dArr[i36] = -dArr[i37 + 2];
                            dArr[i36 - 1] = dArr[i37 + 1];
                            i35 += 2;
                            i29 = i29;
                            i30 = i30;
                        }
                        i31++;
                    }
                }
                i27++;
            }
            int i38 = 0;
            while (true) {
                int i39 = this.slices;
                if (i38 >= i39) {
                    break;
                }
                int i40 = ((i39 - i38) % i39) * i8;
                int i41 = i38 * i8;
                for (int i42 = 1; i42 < i5; i42++) {
                    int i43 = ((this.rows - i42) * i3) + i41;
                    int i44 = this.columns;
                    int i45 = (i42 * i3) + i40 + i44;
                    int i46 = i44 + i43;
                    int i47 = i43 + 1;
                    dArr[i45] = dArr[i47];
                    dArr[i46] = dArr[i47];
                    dArr[i45 + 1] = -dArr[i43];
                    dArr[i46 + 1] = dArr[i43];
                }
                i38++;
            }
            int i48 = 0;
            while (true) {
                int i49 = this.slices;
                if (i48 >= i49) {
                    break;
                }
                int i50 = ((i49 - i48) % i49) * i8;
                int i51 = i48 * i8;
                for (int i52 = 1; i52 < i5; i52++) {
                    int i53 = ((this.rows - i52) * i3) + i50;
                    int i54 = (i52 * i3) + i51;
                    dArr[i53] = dArr[i54];
                    dArr[i53 + 1] = -dArr[i54 + 1];
                }
                i48++;
            }
        } else {
            Future[] futureArr = new Future[numberOfThreads];
            int i55 = i2 / numberOfThreads;
            int i56 = 0;
            while (i56 < numberOfThreads) {
                final int i57 = i56 * i55;
                final int i58 = i56 == numberOfThreads + (-1) ? this.slices : i57 + i55;
                Future[] futureArr2 = futureArr;
                futureArr2[i56] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.63
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i59 = i57; i59 < i58; i59++) {
                            int i60 = (DoubleFFT_3D.this.slices - i59) % DoubleFFT_3D.this.slices;
                            int i61 = i8;
                            int i62 = i60 * i61;
                            int i63 = i61 * i59;
                            for (int i64 = 0; i64 < DoubleFFT_3D.this.rows; i64++) {
                                int i65 = (DoubleFFT_3D.this.rows - i64) % DoubleFFT_3D.this.rows;
                                int i66 = i3;
                                int i67 = i65 * i66;
                                int i68 = i66 * i64;
                                for (int i69 = 1; i69 < DoubleFFT_3D.this.columns; i69 += 2) {
                                    int i70 = ((i62 + i67) + i3) - i69;
                                    int i71 = i63 + i68 + i69;
                                    double[] dArr2 = dArr;
                                    dArr2[i70] = -dArr2[i71 + 2];
                                    dArr2[i70 - 1] = dArr2[i71 + 1];
                                }
                            }
                        }
                    }
                });
                i56++;
                numberOfThreads = numberOfThreads;
                futureArr = futureArr2;
            }
            Future[] futureArr3 = futureArr;
            int i59 = numberOfThreads;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i60 = 0;
            while (i60 < i59) {
                final int i61 = i60 * i55;
                final int i62 = i60 == i59 + (-1) ? this.slices : i61 + i55;
                futureArr3[i60] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.64
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i63 = i61; i63 < i62; i63++) {
                            int i64 = (DoubleFFT_3D.this.slices - i63) % DoubleFFT_3D.this.slices;
                            int i65 = i8;
                            int i66 = i64 * i65;
                            int i67 = i65 * i63;
                            for (int i68 = 1; i68 < i5; i68++) {
                                int i69 = DoubleFFT_3D.this.rows - i68;
                                int i70 = i3;
                                int i71 = (i69 * i70) + i67;
                                int i72 = (i70 * i68) + i66 + DoubleFFT_3D.this.columns;
                                int i73 = DoubleFFT_3D.this.columns + i71;
                                int i74 = i71 + 1;
                                double[] dArr2 = dArr;
                                dArr2[i72] = dArr2[i74];
                                dArr2[i73] = dArr2[i74];
                                dArr2[i72 + 1] = -dArr2[i71];
                                dArr2[i73 + 1] = dArr2[i71];
                            }
                        }
                    }
                });
                i60++;
                str = str;
                i7 = i7;
            }
            i = i7;
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            int i63 = 0;
            while (i63 < i59) {
                final int i64 = i63 * i55;
                final int i65 = i63 == i59 + (-1) ? this.slices : i64 + i55;
                futureArr3[i63] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_3D.65
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i66 = i64; i66 < i65; i66++) {
                            int i67 = (DoubleFFT_3D.this.slices - i66) % DoubleFFT_3D.this.slices;
                            int i68 = i8;
                            int i69 = i67 * i68;
                            int i70 = i68 * i66;
                            for (int i71 = 1; i71 < i5; i71++) {
                                int i72 = DoubleFFT_3D.this.rows - i71;
                                int i73 = i3;
                                int i74 = (i72 * i73) + i69;
                                int i75 = (i73 * i71) + i70;
                                double[] dArr2 = dArr;
                                dArr2[i74] = dArr2[i75];
                                dArr2[i74 + 1] = -dArr2[i75 + 1];
                            }
                        }
                    }
                });
                i63++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
        }
        int i66 = i;
        int i67 = 1;
        while (i67 < i66) {
            int i68 = i67 * i8;
            int i69 = (this.slices - i67) * i8;
            int i70 = i5 * i3;
            int i71 = i68 + i70;
            int i72 = i70 + i69;
            int i73 = this.columns;
            int i74 = i69 + 1;
            dArr[i68 + i73] = dArr[i74];
            dArr[i69 + i73] = dArr[i74];
            dArr[i68 + i73 + 1] = -dArr[i69];
            dArr[i69 + i73 + 1] = dArr[i69];
            int i75 = i72 + 1;
            dArr[i71 + i73] = dArr[i75];
            dArr[i72 + i73] = dArr[i75];
            dArr[i71 + i73 + 1] = -dArr[i72];
            dArr[i73 + i72 + 1] = dArr[i72];
            dArr[i69] = dArr[i68];
            dArr[i74] = -dArr[i68 + 1];
            dArr[i72] = dArr[i71];
            dArr[i75] = -dArr[i71 + 1];
            i67++;
            i3 = i3;
            i5 = i5;
        }
        int i76 = this.columns;
        dArr[i76] = dArr[1];
        dArr[1] = 0.0d;
        int i77 = i5 * i3;
        int i78 = i66 * i8;
        int i79 = i77 + i78;
        int i80 = i77 + i76;
        int i81 = i77 + 1;
        dArr[i80] = dArr[i81];
        dArr[i81] = 0.0d;
        int i82 = i78 + 1;
        dArr[i78 + i76] = dArr[i82];
        dArr[i82] = 0.0d;
        int i83 = i79 + 1;
        dArr[i79 + i76] = dArr[i83];
        dArr[i83] = 0.0d;
        dArr[i78 + i76 + 1] = 0.0d;
        dArr[i79 + i76 + 1] = 0.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void fillSymmetric(final pl.edu.icm.jlargearrays.DoubleLargeArray r42) {
        /*
            Method dump skipped, instructions count: 1021
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.DoubleFFT_3D.fillSymmetric(pl.edu.icm.jlargearrays.DoubleLargeArray):void");
    }
}
