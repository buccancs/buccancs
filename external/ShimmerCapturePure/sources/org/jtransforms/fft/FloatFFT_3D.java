package org.jtransforms.fft;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;

/* loaded from: classes2.dex */
public class FloatFFT_3D {
    private int columns;
    private long columnsl;
    private FloatFFT_1D fftColumns;
    private FloatFFT_1D fftRows;
    private FloatFFT_1D fftSlices;
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

    public FloatFFT_3D(long j, long j2, long j3) {
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
        FloatFFT_1D floatFFT_1D = new FloatFFT_1D(j);
        this.fftSlices = floatFFT_1D;
        if (j == j2) {
            this.fftRows = floatFFT_1D;
        } else {
            this.fftRows = new FloatFFT_1D(j2);
        }
        if (j == j3) {
            this.fftColumns = this.fftSlices;
        } else if (j2 == j3) {
            this.fftColumns = this.fftRows;
        } else {
            this.fftColumns = new FloatFFT_1D(j3);
        }
    }

    public void complexForward(final float[] fArr) {
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
                xdft3da_subth2(0, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
            } else {
                xdft3da_sub2(0, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
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
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            int i13 = FloatFFT_3D.this.sliceStride * i12;
                            for (int i14 = 0; i14 < FloatFFT_3D.this.rows; i14++) {
                                FloatFFT_3D.this.fftColumns.complexForward(fArr, (FloatFFT_3D.this.rowStride * i14) + i13);
                            }
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            int i16 = FloatFFT_3D.this.sliceStride * i15;
                            for (int i17 = 0; i17 < FloatFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < FloatFFT_3D.this.rows; i19++) {
                                    int i20 = i16 + i18 + (FloatFFT_3D.this.rowStride * i19);
                                    int i21 = i19 * 2;
                                    float[] fArr3 = fArr;
                                    fArr2[i21] = fArr3[i20];
                                    fArr2[i21 + 1] = fArr3[i20 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2);
                                for (int i22 = 0; i22 < FloatFFT_3D.this.rows; i22++) {
                                    int i23 = i16 + i18 + (FloatFFT_3D.this.rowStride * i22);
                                    int i24 = i22 * 2;
                                    float[] fArr4 = fArr;
                                    fArr4[i23] = fArr2[i24];
                                    fArr4[i23 + 1] = fArr2[i24 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i15 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i16 = i3 * i15;
                final int i17 = i3 == numberOfThreads + (-1) ? this.rows : i16 + i15;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.3
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i18 = i16; i18 < i17; i18++) {
                            int i19 = FloatFFT_3D.this.rowStride * i18;
                            for (int i20 = 0; i20 < FloatFFT_3D.this.columns; i20++) {
                                int i21 = i20 * 2;
                                for (int i22 = 0; i22 < FloatFFT_3D.this.slices; i22++) {
                                    int i23 = (FloatFFT_3D.this.sliceStride * i22) + i19 + i21;
                                    int i24 = i22 * 2;
                                    float[] fArr3 = fArr;
                                    fArr2[i24] = fArr3[i23];
                                    fArr2[i24 + 1] = fArr3[i23 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2);
                                for (int i25 = 0; i25 < FloatFFT_3D.this.slices; i25++) {
                                    int i26 = (FloatFFT_3D.this.sliceStride * i25) + i19 + i21;
                                    int i27 = i25 * 2;
                                    float[] fArr4 = fArr;
                                    fArr4[i26] = fArr2[i27];
                                    fArr4[i26 + 1] = fArr2[i27 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i18 = 0; i18 < this.slices; i18++) {
                int i19 = this.sliceStride * i18;
                for (int i20 = 0; i20 < this.rows; i20++) {
                    this.fftColumns.complexForward(fArr, (this.rowStride * i20) + i19);
                }
            }
            float[] fArr2 = new float[this.rows * 2];
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
                        fArr2[i27] = fArr[i26];
                        fArr2[i27 + 1] = fArr[i26 + 1];
                    }
                    this.fftRows.complexForward(fArr2);
                    for (int i28 = 0; i28 < this.rows; i28++) {
                        int i29 = i22 + i24 + (this.rowStride * i28);
                        int i30 = i28 * 2;
                        fArr[i29] = fArr2[i30];
                        fArr[i29 + 1] = fArr2[i30 + 1];
                    }
                }
                i21++;
            }
            float[] fArr3 = new float[i * 2];
            for (int i31 = 0; i31 < this.rows; i31++) {
                int i32 = this.rowStride * i31;
                for (int i33 = 0; i33 < this.columns; i33++) {
                    int i34 = i33 * 2;
                    for (int i35 = 0; i35 < this.slices; i35++) {
                        int i36 = (this.sliceStride * i35) + i32 + i34;
                        int i37 = i35 * 2;
                        fArr3[i37] = fArr[i36];
                        fArr3[i37 + 1] = fArr[i36 + 1];
                    }
                    this.fftSlices.complexForward(fArr3);
                    for (int i38 = 0; i38 < this.slices; i38++) {
                        int i39 = (this.sliceStride * i38) + i32 + i34;
                        int i40 = i38 * 2;
                        fArr[i39] = fArr3[i40];
                        fArr[i39 + 1] = fArr3[i40 + 1];
                    }
                }
            }
        }
        int i41 = this.rows;
        int i42 = this.columns;
        this.sliceStride = i41 * i42;
        this.rowStride = i42;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void complexForward(final pl.edu.icm.jlargearrays.FloatLargeArray r26) {
        /*
            Method dump skipped, instructions count: 707
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.complexForward(pl.edu.icm.jlargearrays.FloatLargeArray):void");
    }

    public void complexForward(final float[][][] fArr) {
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
                xdft3da_subth2(0, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
            } else {
                xdft3da_sub2(0, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
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
                futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i8; i10 < i9; i10++) {
                            for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                FloatFFT_3D.this.fftColumns.complexForward(fArr[i10][i11]);
                            }
                        }
                    }
                });
                i7++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i10 = 0;
            while (i10 < numberOfThreads) {
                final int i11 = i10 * i6;
                final int i12 = i10 == numberOfThreads + (-1) ? this.slices : i11 + i6;
                futureArr[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i13 = i11; i13 < i12; i13++) {
                            for (int i14 = 0; i14 < FloatFFT_3D.this.columns; i14++) {
                                int i15 = i14 * 2;
                                for (int i16 = 0; i16 < FloatFFT_3D.this.rows; i16++) {
                                    int i17 = i16 * 2;
                                    float[] fArr3 = fArr[i13][i16];
                                    fArr2[i17] = fArr3[i15];
                                    fArr2[i17 + 1] = fArr3[i15 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2);
                                for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    float[] fArr4 = fArr[i13][i18];
                                    fArr4[i15] = fArr2[i19];
                                    fArr4[i15 + 1] = fArr2[i19 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i13 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i14 = i3 * i13;
                final int i15 = i3 == numberOfThreads + (-1) ? this.rows : i14 + i13;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.9
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i16 = i14; i16 < i15; i16++) {
                            for (int i17 = 0; i17 < FloatFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < FloatFFT_3D.this.slices; i19++) {
                                    int i20 = i19 * 2;
                                    float[] fArr3 = fArr[i19][i16];
                                    fArr2[i20] = fArr3[i18];
                                    fArr2[i20 + 1] = fArr3[i18 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2);
                                for (int i21 = 0; i21 < FloatFFT_3D.this.slices; i21++) {
                                    int i22 = i21 * 2;
                                    float[] fArr4 = fArr[i21][i16];
                                    fArr4[i18] = fArr2[i22];
                                    fArr4[i18 + 1] = fArr2[i22 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i16 = 0; i16 < this.slices; i16++) {
            for (int i17 = 0; i17 < this.rows; i17++) {
                this.fftColumns.complexForward(fArr[i16][i17]);
            }
        }
        float[] fArr2 = new float[this.rows * 2];
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
                    float[] fArr3 = fArr[i18][i21];
                    fArr2[i22] = fArr3[i20];
                    fArr2[i22 + 1] = fArr3[i20 + 1];
                }
                this.fftRows.complexForward(fArr2);
                for (int i23 = 0; i23 < this.rows; i23++) {
                    int i24 = i23 * 2;
                    float[] fArr4 = fArr[i18][i23];
                    fArr4[i20] = fArr2[i24];
                    fArr4[i20 + 1] = fArr2[i24 + 1];
                }
            }
            i18++;
        }
        float[] fArr5 = new float[i * 2];
        for (int i25 = 0; i25 < this.rows; i25++) {
            for (int i26 = 0; i26 < this.columns; i26++) {
                int i27 = i26 * 2;
                for (int i28 = 0; i28 < this.slices; i28++) {
                    int i29 = i28 * 2;
                    float[] fArr6 = fArr[i28][i25];
                    fArr5[i29] = fArr6[i27];
                    fArr5[i29 + 1] = fArr6[i27 + 1];
                }
                this.fftSlices.complexForward(fArr5);
                for (int i30 = 0; i30 < this.slices; i30++) {
                    int i31 = i30 * 2;
                    float[] fArr7 = fArr[i30][i25];
                    fArr7[i27] = fArr5[i31];
                    fArr7[i27 + 1] = fArr5[i31 + 1];
                }
            }
        }
    }

    public void complexInverse(final float[] fArr, final boolean z) {
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
                xdft3da_subth2(0, 1, fArr, z);
                cdft3db_subth(1, fArr, z);
            } else {
                xdft3da_sub2(0, 1, fArr, z);
                cdft3db_sub(1, fArr, z);
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
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.10
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            int i13 = FloatFFT_3D.this.sliceStride * i12;
                            for (int i14 = 0; i14 < FloatFFT_3D.this.rows; i14++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr, (FloatFFT_3D.this.rowStride * i14) + i13, z);
                            }
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.11
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            int i16 = FloatFFT_3D.this.sliceStride * i15;
                            for (int i17 = 0; i17 < FloatFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < FloatFFT_3D.this.rows; i19++) {
                                    int i20 = i16 + i18 + (FloatFFT_3D.this.rowStride * i19);
                                    int i21 = i19 * 2;
                                    float[] fArr3 = fArr;
                                    fArr2[i21] = fArr3[i20];
                                    fArr2[i21 + 1] = fArr3[i20 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, z);
                                for (int i22 = 0; i22 < FloatFFT_3D.this.rows; i22++) {
                                    int i23 = i16 + i18 + (FloatFFT_3D.this.rowStride * i22);
                                    int i24 = i22 * 2;
                                    float[] fArr4 = fArr;
                                    fArr4[i23] = fArr2[i24];
                                    fArr4[i23 + 1] = fArr2[i24 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i15 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i16 = i3 * i15;
                final int i17 = i3 == numberOfThreads + (-1) ? this.rows : i16 + i15;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.12
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i18 = i16; i18 < i17; i18++) {
                            int i19 = FloatFFT_3D.this.rowStride * i18;
                            for (int i20 = 0; i20 < FloatFFT_3D.this.columns; i20++) {
                                int i21 = i20 * 2;
                                for (int i22 = 0; i22 < FloatFFT_3D.this.slices; i22++) {
                                    int i23 = (FloatFFT_3D.this.sliceStride * i22) + i19 + i21;
                                    int i24 = i22 * 2;
                                    float[] fArr3 = fArr;
                                    fArr2[i24] = fArr3[i23];
                                    fArr2[i24 + 1] = fArr3[i23 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, z);
                                for (int i25 = 0; i25 < FloatFFT_3D.this.slices; i25++) {
                                    int i26 = (FloatFFT_3D.this.sliceStride * i25) + i19 + i21;
                                    int i27 = i25 * 2;
                                    float[] fArr4 = fArr;
                                    fArr4[i26] = fArr2[i27];
                                    fArr4[i26 + 1] = fArr2[i27 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i18 = 0; i18 < this.slices; i18++) {
                int i19 = this.sliceStride * i18;
                for (int i20 = 0; i20 < this.rows; i20++) {
                    this.fftColumns.complexInverse(fArr, (this.rowStride * i20) + i19, z);
                }
            }
            float[] fArr2 = new float[this.rows * 2];
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
                        fArr2[i27] = fArr[i26];
                        fArr2[i27 + 1] = fArr[i26 + 1];
                    }
                    this.fftRows.complexInverse(fArr2, z);
                    for (int i28 = 0; i28 < this.rows; i28++) {
                        int i29 = i22 + i24 + (this.rowStride * i28);
                        int i30 = i28 * 2;
                        fArr[i29] = fArr2[i30];
                        fArr[i29 + 1] = fArr2[i30 + 1];
                    }
                }
                i21++;
            }
            float[] fArr3 = new float[i * 2];
            for (int i31 = 0; i31 < this.rows; i31++) {
                int i32 = this.rowStride * i31;
                for (int i33 = 0; i33 < this.columns; i33++) {
                    int i34 = i33 * 2;
                    for (int i35 = 0; i35 < this.slices; i35++) {
                        int i36 = (this.sliceStride * i35) + i32 + i34;
                        int i37 = i35 * 2;
                        fArr3[i37] = fArr[i36];
                        fArr3[i37 + 1] = fArr[i36 + 1];
                    }
                    this.fftSlices.complexInverse(fArr3, z);
                    for (int i38 = 0; i38 < this.slices; i38++) {
                        int i39 = (this.sliceStride * i38) + i32 + i34;
                        int i40 = i38 * 2;
                        fArr[i39] = fArr3[i40];
                        fArr[i39 + 1] = fArr3[i40 + 1];
                    }
                }
            }
        }
        int i41 = this.rows;
        int i42 = this.columns;
        this.sliceStride = i41 * i42;
        this.rowStride = i42;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void complexInverse(final pl.edu.icm.jlargearrays.FloatLargeArray r25, final boolean r26) {
        /*
            Method dump skipped, instructions count: 727
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.complexInverse(pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    public void complexInverse(final float[][][] fArr, final boolean z) {
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
                xdft3da_subth2(0, 1, fArr, z);
                cdft3db_subth(1, fArr, z);
            } else {
                xdft3da_sub2(0, 1, fArr, z);
                cdft3db_sub(1, fArr, z);
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
                futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.16
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i10 = i8; i10 < i9; i10++) {
                            for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr[i10][i11], z);
                            }
                        }
                    }
                });
                i7++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i10 = 0;
            while (i10 < numberOfThreads) {
                final int i11 = i10 * i6;
                final int i12 = i10 == numberOfThreads + (-1) ? this.slices : i11 + i6;
                futureArr[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.17
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i13 = i11; i13 < i12; i13++) {
                            for (int i14 = 0; i14 < FloatFFT_3D.this.columns; i14++) {
                                int i15 = i14 * 2;
                                for (int i16 = 0; i16 < FloatFFT_3D.this.rows; i16++) {
                                    int i17 = i16 * 2;
                                    float[] fArr3 = fArr[i13][i16];
                                    fArr2[i17] = fArr3[i15];
                                    fArr2[i17 + 1] = fArr3[i15 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, z);
                                for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    float[] fArr4 = fArr[i13][i18];
                                    fArr4[i15] = fArr2[i19];
                                    fArr4[i15 + 1] = fArr2[i19 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i13 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i14 = i3 * i13;
                final int i15 = i3 == numberOfThreads + (-1) ? this.rows : i14 + i13;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.18
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i16 = i14; i16 < i15; i16++) {
                            for (int i17 = 0; i17 < FloatFFT_3D.this.columns; i17++) {
                                int i18 = i17 * 2;
                                for (int i19 = 0; i19 < FloatFFT_3D.this.slices; i19++) {
                                    int i20 = i19 * 2;
                                    float[] fArr3 = fArr[i19][i16];
                                    fArr2[i20] = fArr3[i18];
                                    fArr2[i20 + 1] = fArr3[i18 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, z);
                                for (int i21 = 0; i21 < FloatFFT_3D.this.slices; i21++) {
                                    int i22 = i21 * 2;
                                    float[] fArr4 = fArr[i21][i16];
                                    fArr4[i18] = fArr2[i22];
                                    fArr4[i18 + 1] = fArr2[i22 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i16 = 0; i16 < this.slices; i16++) {
            for (int i17 = 0; i17 < this.rows; i17++) {
                this.fftColumns.complexInverse(fArr[i16][i17], z);
            }
        }
        float[] fArr2 = new float[this.rows * 2];
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
                    float[] fArr3 = fArr[i18][i21];
                    fArr2[i22] = fArr3[i20];
                    fArr2[i22 + 1] = fArr3[i20 + 1];
                }
                this.fftRows.complexInverse(fArr2, z);
                for (int i23 = 0; i23 < this.rows; i23++) {
                    int i24 = i23 * 2;
                    float[] fArr4 = fArr[i18][i23];
                    fArr4[i20] = fArr2[i24];
                    fArr4[i20 + 1] = fArr2[i24 + 1];
                }
            }
            i18++;
        }
        float[] fArr5 = new float[i * 2];
        for (int i25 = 0; i25 < this.rows; i25++) {
            for (int i26 = 0; i26 < this.columns; i26++) {
                int i27 = i26 * 2;
                for (int i28 = 0; i28 < this.slices; i28++) {
                    int i29 = i28 * 2;
                    float[] fArr6 = fArr[i28][i25];
                    fArr5[i29] = fArr6[i27];
                    fArr5[i29 + 1] = fArr6[i27 + 1];
                }
                this.fftSlices.complexInverse(fArr5, z);
                for (int i30 = 0; i30 < this.slices; i30++) {
                    int i31 = i30 * 2;
                    float[] fArr7 = fArr[i30][i25];
                    fArr7[i27] = fArr5[i31];
                    fArr7[i27 + 1] = fArr5[i31 + 1];
                }
            }
        }
    }

    public void realForward(float[] fArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft3da_subth1(1, -1, fArr, true);
            cdft3db_subth(-1, fArr, true);
            rdft3d_sub(1, fArr);
        } else {
            xdft3da_sub1(1, -1, fArr, true);
            cdft3db_sub(-1, fArr, true);
            rdft3d_sub(1, fArr);
        }
    }

    public void realForward(FloatLargeArray floatLargeArray) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft3da_subth1(1L, -1, floatLargeArray, true);
            cdft3db_subth(-1, floatLargeArray, true);
            rdft3d_sub(1, floatLargeArray);
        } else {
            xdft3da_sub1(1L, -1, floatLargeArray, true);
            cdft3db_sub(-1, floatLargeArray, true);
            rdft3d_sub(1, floatLargeArray);
        }
    }

    public void realForward(float[][][] fArr) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            xdft3da_subth1(1, -1, fArr, true);
            cdft3db_subth(-1, fArr, true);
            rdft3d_sub(1, fArr);
        } else {
            xdft3da_sub1(1, -1, fArr, true);
            cdft3db_sub(-1, fArr, true);
            rdft3d_sub(1, fArr);
        }
    }

    public void realForwardFull(float[] fArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_sub2(1, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealForwardFull(fArr);
    }

    public void realForwardFull(FloatLargeArray floatLargeArray) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1L, -1, floatLargeArray, true);
                cdft3db_subth(-1, floatLargeArray, true);
                rdft3d_sub(1, floatLargeArray);
            } else {
                xdft3da_sub2(1L, -1, floatLargeArray, true);
                cdft3db_sub(-1, floatLargeArray, true);
                rdft3d_sub(1, floatLargeArray);
            }
            fillSymmetric(floatLargeArray);
            return;
        }
        mixedRadixRealForwardFull(floatLargeArray);
    }

    public void realForwardFull(float[][][] fArr) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, -1, fArr, true);
                cdft3db_subth(-1, fArr, true);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_sub2(1, -1, fArr, true);
                cdft3db_sub(-1, fArr, true);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealForwardFull(fArr);
    }

    public void realInverse(float[] fArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft3d_sub(-1, fArr);
            cdft3db_subth(1, fArr, z);
            xdft3da_subth1(1, 1, fArr, z);
        } else {
            rdft3d_sub(-1, fArr);
            cdft3db_sub(1, fArr, z);
            xdft3da_sub1(1, 1, fArr, z);
        }
    }

    public void realInverse(FloatLargeArray floatLargeArray, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft3d_sub(-1, floatLargeArray);
            cdft3db_subth(1, floatLargeArray, z);
            xdft3da_subth1(1L, 1, floatLargeArray, z);
        } else {
            rdft3d_sub(-1, floatLargeArray);
            cdft3db_sub(1, floatLargeArray, z);
            xdft3da_sub1(1L, 1, floatLargeArray, z);
        }
    }

    public void realInverse(float[][][] fArr, boolean z) {
        if (!this.isPowerOfTwo) {
            throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
        }
        if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
            rdft3d_sub(-1, fArr);
            cdft3db_subth(1, fArr, z);
            xdft3da_subth1(1, 1, fArr, z);
        } else {
            rdft3d_sub(-1, fArr);
            cdft3db_sub(1, fArr, z);
            xdft3da_sub1(1, 1, fArr, z);
        }
    }

    public void realInverseFull(float[] fArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, 1, fArr, z);
                cdft3db_subth(1, fArr, z);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_sub2(1, 1, fArr, z);
                cdft3db_sub(1, fArr, z);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealInverseFull(fArr, z);
    }

    public void realInverseFull(FloatLargeArray floatLargeArray, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1L, 1, floatLargeArray, z);
                cdft3db_subth(1, floatLargeArray, z);
                rdft3d_sub(1, floatLargeArray);
            } else {
                xdft3da_sub2(1L, 1, floatLargeArray, z);
                cdft3db_sub(1, floatLargeArray, z);
                rdft3d_sub(1, floatLargeArray);
            }
            fillSymmetric(floatLargeArray);
            return;
        }
        mixedRadixRealInverseFull(floatLargeArray, z);
    }

    public void realInverseFull(float[][][] fArr, boolean z) {
        if (this.isPowerOfTwo) {
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && this.useThreads) {
                xdft3da_subth2(1, 1, fArr, z);
                cdft3db_subth(1, fArr, z);
                rdft3d_sub(1, fArr);
            } else {
                xdft3da_sub2(1, 1, fArr, z);
                cdft3db_sub(1, fArr, z);
                rdft3d_sub(1, fArr);
            }
            fillSymmetric(fArr);
            return;
        }
        mixedRadixRealInverseFull(fArr, z);
    }

    private void mixedRadixRealForwardFull(final float[][][] fArr) {
        int i;
        int i2;
        int i3;
        int i4 = this.rows;
        float[] fArr2 = new float[i4 * 2];
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
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.19
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                FloatFFT_3D.this.fftColumns.realForwardFull(fArr[i12][i13]);
                            }
                        }
                    }
                });
                i9++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.20
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr3 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            for (int i16 = 0; i16 < FloatFFT_3D.this.columns; i16++) {
                                int i17 = i16 * 2;
                                for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    float[] fArr4 = fArr[i15][i18];
                                    fArr3[i19] = fArr4[i17];
                                    fArr3[i19 + 1] = fArr4[i17 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr3);
                                for (int i20 = 0; i20 < FloatFFT_3D.this.rows; i20++) {
                                    int i21 = i20 * 2;
                                    float[] fArr5 = fArr[i15][i20];
                                    fArr5[i17] = fArr3[i21];
                                    fArr5[i17 + 1] = fArr3[i21 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i15 = i5 / numberOfThreads;
            int i16 = 0;
            while (i16 < numberOfThreads) {
                final int i17 = i16 * i15;
                final int i18 = i16 == numberOfThreads + (-1) ? i5 : i17 + i15;
                futureArr[i16] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.21
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr3 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i19 = i17; i19 < i18; i19++) {
                            for (int i20 = 0; i20 < FloatFFT_3D.this.columns; i20++) {
                                int i21 = i20 * 2;
                                for (int i22 = 0; i22 < FloatFFT_3D.this.slices; i22++) {
                                    int i23 = i22 * 2;
                                    float[] fArr4 = fArr[i22][i19];
                                    fArr3[i23] = fArr4[i21];
                                    fArr3[i23 + 1] = fArr4[i21 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr3);
                                for (int i24 = 0; i24 < FloatFFT_3D.this.slices; i24++) {
                                    int i25 = i24 * 2;
                                    float[] fArr5 = fArr[i24][i19];
                                    fArr5[i21] = fArr3[i25];
                                    fArr5[i21 + 1] = fArr3[i25 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
            int i19 = this.slices / numberOfThreads;
            int i20 = 0;
            while (i20 < numberOfThreads) {
                final int i21 = i20 * i19;
                final int i22 = i20 == numberOfThreads + (-1) ? this.slices : i21 + i19;
                int i23 = i20;
                futureArr[i23] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.22
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i24 = i21; i24 < i22; i24++) {
                            int i25 = (FloatFFT_3D.this.slices - i24) % FloatFFT_3D.this.slices;
                            for (int i26 = 1; i26 < i7; i26++) {
                                int i27 = FloatFFT_3D.this.rows - i26;
                                for (int i28 = 0; i28 < FloatFFT_3D.this.columns; i28++) {
                                    int i29 = i28 * 2;
                                    int i30 = i6;
                                    int i31 = i30 - i29;
                                    float[][][] fArr3 = fArr;
                                    float[] fArr4 = fArr3[i25][i27];
                                    float[] fArr5 = fArr3[i24][i26];
                                    fArr4[i31 % i30] = fArr5[i29];
                                    fArr4[(i31 + 1) % i30] = -fArr5[i29 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e8);
                return;
            }
        }
        for (int i24 = 0; i24 < this.slices; i24++) {
            for (int i25 = 0; i25 < this.rows; i25++) {
                this.fftColumns.realForwardFull(fArr[i24][i25]);
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
                    float[] fArr3 = fArr[i26][i29];
                    fArr2[i30] = fArr3[i28];
                    fArr2[i30 + 1] = fArr3[i28 + 1];
                }
                this.fftRows.complexForward(fArr2);
                for (int i31 = 0; i31 < this.rows; i31++) {
                    int i32 = i31 * 2;
                    float[] fArr4 = fArr[i26][i31];
                    fArr4[i28] = fArr2[i32];
                    fArr4[i28 + 1] = fArr2[i32 + 1];
                }
            }
            i26++;
        }
        float[] fArr5 = new float[i2 * 2];
        for (int i33 = 0; i33 < i5; i33++) {
            for (int i34 = 0; i34 < this.columns; i34++) {
                int i35 = i34 * 2;
                for (int i36 = 0; i36 < this.slices; i36++) {
                    int i37 = i36 * 2;
                    float[] fArr6 = fArr[i36][i33];
                    fArr5[i37] = fArr6[i35];
                    fArr5[i37 + 1] = fArr6[i35 + 1];
                }
                this.fftSlices.complexForward(fArr5);
                for (int i38 = 0; i38 < this.slices; i38++) {
                    int i39 = i38 * 2;
                    float[] fArr7 = fArr[i38][i33];
                    fArr7[i35] = fArr5[i39];
                    fArr7[i35 + 1] = fArr5[i39 + 1];
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
                    float[] fArr8 = fArr[i42][i44];
                    float[] fArr9 = fArr[i40][i43];
                    fArr8[i47 % i6] = fArr9[i46];
                    fArr8[(i47 + 1) % i6] = -fArr9[i46 + 1];
                }
            }
            i40++;
        }
    }

    private void mixedRadixRealInverseFull(final float[][][] fArr, final boolean z) {
        int i;
        int i2;
        int i3;
        int i4 = this.rows;
        float[] fArr2 = new float[i4 * 2];
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
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.23
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i10; i12 < i11; i12++) {
                            for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                FloatFFT_3D.this.fftColumns.realInverseFull(fArr[i12][i13], z);
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i12 = 0;
            while (i12 < numberOfThreads) {
                final int i13 = i12 * i8;
                final int i14 = i12 == numberOfThreads + (-1) ? this.slices : i13 + i8;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.24
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr3 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i15 = i13; i15 < i14; i15++) {
                            for (int i16 = 0; i16 < FloatFFT_3D.this.columns; i16++) {
                                int i17 = i16 * 2;
                                for (int i18 = 0; i18 < FloatFFT_3D.this.rows; i18++) {
                                    int i19 = i18 * 2;
                                    float[] fArr4 = fArr[i15][i18];
                                    fArr3[i19] = fArr4[i17];
                                    fArr3[i19 + 1] = fArr4[i17 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr3, z);
                                for (int i20 = 0; i20 < FloatFFT_3D.this.rows; i20++) {
                                    int i21 = i20 * 2;
                                    float[] fArr5 = fArr[i15][i20];
                                    fArr5[i17] = fArr3[i21];
                                    fArr5[i17 + 1] = fArr3[i21 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            int i15 = i5 / numberOfThreads;
            int i16 = 0;
            while (i16 < numberOfThreads) {
                final int i17 = i16 * i15;
                final int i18 = i16 == numberOfThreads + (-1) ? i5 : i17 + i15;
                int i19 = i16;
                futureArr[i19] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.25
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr3 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i20 = i17; i20 < i18; i20++) {
                            for (int i21 = 0; i21 < FloatFFT_3D.this.columns; i21++) {
                                int i22 = i21 * 2;
                                for (int i23 = 0; i23 < FloatFFT_3D.this.slices; i23++) {
                                    int i24 = i23 * 2;
                                    float[] fArr4 = fArr[i23][i20];
                                    fArr3[i24] = fArr4[i22];
                                    fArr3[i24 + 1] = fArr4[i22 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr3, z);
                                for (int i25 = 0; i25 < FloatFFT_3D.this.slices; i25++) {
                                    int i26 = i25 * 2;
                                    float[] fArr5 = fArr[i25][i20];
                                    fArr5[i22] = fArr3[i26];
                                    fArr5[i22 + 1] = fArr3[i26 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i20 = this.slices / numberOfThreads;
            int i21 = 0;
            while (i21 < numberOfThreads) {
                final int i22 = i21 * i20;
                final int i23 = i21 == numberOfThreads + (-1) ? this.slices : i22 + i20;
                futureArr[i21] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.26
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i24 = i22; i24 < i23; i24++) {
                            int i25 = (FloatFFT_3D.this.slices - i24) % FloatFFT_3D.this.slices;
                            for (int i26 = 1; i26 < i7; i26++) {
                                int i27 = FloatFFT_3D.this.rows - i26;
                                for (int i28 = 0; i28 < FloatFFT_3D.this.columns; i28++) {
                                    int i29 = i28 * 2;
                                    int i30 = i6;
                                    int i31 = i30 - i29;
                                    float[][][] fArr3 = fArr;
                                    float[] fArr4 = fArr3[i25][i27];
                                    float[] fArr5 = fArr3[i24][i26];
                                    fArr4[i31 % i30] = fArr5[i29];
                                    fArr4[(i31 + 1) % i30] = -fArr5[i29 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                return;
            } catch (ExecutionException e8) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                return;
            }
        }
        for (int i24 = 0; i24 < this.slices; i24++) {
            for (int i25 = 0; i25 < this.rows; i25++) {
                this.fftColumns.realInverseFull(fArr[i24][i25], z);
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
                    float[] fArr3 = fArr[i26][i29];
                    fArr2[i30] = fArr3[i28];
                    fArr2[i30 + 1] = fArr3[i28 + 1];
                }
                this.fftRows.complexInverse(fArr2, z);
                for (int i31 = 0; i31 < this.rows; i31++) {
                    int i32 = i31 * 2;
                    float[] fArr4 = fArr[i26][i31];
                    fArr4[i28] = fArr2[i32];
                    fArr4[i28 + 1] = fArr2[i32 + 1];
                }
            }
            i26++;
        }
        float[] fArr5 = new float[i2 * 2];
        for (int i33 = 0; i33 < i5; i33++) {
            for (int i34 = 0; i34 < this.columns; i34++) {
                int i35 = i34 * 2;
                for (int i36 = 0; i36 < this.slices; i36++) {
                    int i37 = i36 * 2;
                    float[] fArr6 = fArr[i36][i33];
                    fArr5[i37] = fArr6[i35];
                    fArr5[i37 + 1] = fArr6[i35 + 1];
                }
                this.fftSlices.complexInverse(fArr5, z);
                for (int i38 = 0; i38 < this.slices; i38++) {
                    int i39 = i38 * 2;
                    float[] fArr7 = fArr[i38][i33];
                    fArr7[i35] = fArr5[i39];
                    fArr7[i35 + 1] = fArr5[i39 + 1];
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
                    float[] fArr8 = fArr[i42][i44];
                    float[] fArr9 = fArr[i40][i43];
                    fArr8[i47 % i6] = fArr9[i46];
                    fArr8[(i47 + 1) % i6] = -fArr9[i46 + 1];
                }
            }
            i40++;
        }
    }

    private void mixedRadixRealForwardFull(final float[] fArr) {
        int i;
        int i2;
        final int i3 = this.columns * 2;
        float[] fArr2 = new float[i3];
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
                futureArr2[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.27
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr3 = new float[i3];
                        for (int i14 = i12; i14 >= i13; i14--) {
                            int i15 = FloatFFT_3D.this.sliceStride * i14;
                            int i16 = i7 * i14;
                            for (int i17 = FloatFFT_3D.this.rows - 1; i17 >= 0; i17--) {
                                System.arraycopy(fArr, (FloatFFT_3D.this.rowStride * i17) + i15, fArr3, 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realForwardFull(fArr3);
                                System.arraycopy(fArr3, 0, fArr, (i8 * i17) + i16, i3);
                            }
                        }
                    }
                });
                i11++;
                i9 = i9;
                futureArr = futureArr2;
                numberOfThreads = numberOfThreads;
            }
            Future[] futureArr3 = futureArr;
            int i14 = numberOfThreads;
            int i15 = i9;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i16 = i15 + 1;
            final float[][][] fArr3 = (float[][][]) Array.newInstance((Class<?>) Float.TYPE, i16, this.rows, i3);
            int i17 = i14;
            int i18 = 0;
            while (i18 < i17) {
                final int i19 = i18 * i10;
                final int i20 = i18 == i17 + (-1) ? i16 : i19 + i10;
                futureArr3[i18] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.28
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i21 = i19; i21 < i20; i21++) {
                            int i22 = FloatFFT_3D.this.sliceStride * i21;
                            for (int i23 = 0; i23 < FloatFFT_3D.this.rows; i23++) {
                                System.arraycopy(fArr, (FloatFFT_3D.this.rowStride * i23) + i22, fArr3[i21][i23], 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realForwardFull(fArr3[i21][i23]);
                            }
                        }
                    }
                });
                i18++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i21 = 0;
            while (i21 < i17) {
                final int i22 = i21 * i10;
                final int i23 = i21 == i17 + (-1) ? i16 : i22 + i10;
                futureArr3[i21] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.29
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i24 = i22; i24 < i23; i24++) {
                            int i25 = i7 * i24;
                            for (int i26 = 0; i26 < FloatFFT_3D.this.rows; i26++) {
                                System.arraycopy(fArr3[i24][i26], 0, fArr, (i8 * i26) + i25, i3);
                            }
                        }
                    }
                });
                i21++;
                i17 = i17;
                str = str;
                i6 = i6;
                i16 = i16;
            }
            int i24 = i17;
            final int i25 = i6;
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i26 = this.slices / i24;
            int i27 = 0;
            while (i27 < i24) {
                final int i28 = i27 * i26;
                final int i29 = i27 == i24 + (-1) ? this.slices : i28 + i26;
                futureArr3[i27] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.30
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr4 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i30 = i28; i30 < i29; i30++) {
                            int i31 = i7 * i30;
                            for (int i32 = 0; i32 < FloatFFT_3D.this.columns; i32++) {
                                int i33 = i32 * 2;
                                for (int i34 = 0; i34 < FloatFFT_3D.this.rows; i34++) {
                                    int i35 = (i8 * i34) + i31 + i33;
                                    int i36 = i34 * 2;
                                    float[] fArr5 = fArr;
                                    fArr4[i36] = fArr5[i35];
                                    fArr4[i36 + 1] = fArr5[i35 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr4);
                                for (int i37 = 0; i37 < FloatFFT_3D.this.rows; i37++) {
                                    int i38 = (i8 * i37) + i31 + i33;
                                    int i39 = i37 * 2;
                                    float[] fArr6 = fArr;
                                    fArr6[i38] = fArr4[i39];
                                    fArr6[i38 + 1] = fArr4[i39 + 1];
                                }
                            }
                        }
                    }
                });
                i27++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e7) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
            }
            int i30 = i5 / i24;
            int i31 = 0;
            while (i31 < i24) {
                final int i32 = i31 * i30;
                final int i33 = i31 == i24 + (-1) ? i5 : i32 + i30;
                futureArr3[i31] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.31
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr4 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i34 = i32; i34 < i33; i34++) {
                            int i35 = i8 * i34;
                            for (int i36 = 0; i36 < FloatFFT_3D.this.columns; i36++) {
                                int i37 = i36 * 2;
                                for (int i38 = 0; i38 < FloatFFT_3D.this.slices; i38++) {
                                    int i39 = i38 * 2;
                                    int i40 = (i7 * i38) + i35 + i37;
                                    float[] fArr5 = fArr;
                                    fArr4[i39] = fArr5[i40];
                                    fArr4[i39 + 1] = fArr5[i40 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr4);
                                for (int i41 = 0; i41 < FloatFFT_3D.this.slices; i41++) {
                                    int i42 = i41 * 2;
                                    int i43 = (i7 * i41) + i35 + i37;
                                    float[] fArr6 = fArr;
                                    fArr6[i43] = fArr4[i42];
                                    fArr6[i43 + 1] = fArr4[i42 + 1];
                                }
                            }
                        }
                    }
                });
                i31++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e9) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
            }
            int i34 = this.slices / i24;
            int i35 = 0;
            while (i35 < i24) {
                final int i36 = i35 * i34;
                final int i37 = i35 == i24 + (-1) ? this.slices : i36 + i34;
                futureArr3[i35] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.32
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i38 = i36; i38 < i37; i38++) {
                            int i39 = (FloatFFT_3D.this.slices - i38) % FloatFFT_3D.this.slices;
                            int i40 = i7;
                            int i41 = i39 * i40;
                            int i42 = i40 * i38;
                            for (int i43 = 1; i43 < i25; i43++) {
                                int i44 = FloatFFT_3D.this.rows - i43;
                                int i45 = i8;
                                int i46 = i44 * i45;
                                int i47 = i45 * i43;
                                int i48 = i46 + i41;
                                for (int i49 = 0; i49 < FloatFFT_3D.this.columns; i49++) {
                                    int i50 = i49 * 2;
                                    int i51 = i3;
                                    int i52 = i51 - i50;
                                    int i53 = i42 + i47 + i50;
                                    float[] fArr4 = fArr;
                                    fArr4[(i52 % i51) + i48] = fArr4[i53];
                                    fArr4[((i52 + 1) % i51) + i48] = -fArr4[i53 + 1];
                                }
                            }
                        }
                    }
                });
                i35++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e11) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e11);
                return;
            } catch (ExecutionException e12) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e12);
                return;
            }
        }
        int i38 = i6;
        for (int i39 = this.slices - 1; i39 >= 0; i39--) {
            int i40 = this.sliceStride * i39;
            int i41 = i39 * i7;
            for (int i42 = this.rows - 1; i42 >= 0; i42--) {
                System.arraycopy(fArr, (this.rowStride * i42) + i40, fArr2, 0, this.columns);
                this.fftColumns.realForwardFull(fArr2);
                System.arraycopy(fArr2, 0, fArr, (i42 * i8) + i41, i3);
            }
        }
        float[] fArr4 = new float[this.rows * 2];
        int i43 = 0;
        while (true) {
            i2 = this.slices;
            if (i43 >= i2) {
                break;
            }
            int i44 = i43 * i7;
            for (int i45 = 0; i45 < this.columns; i45++) {
                int i46 = i45 * 2;
                for (int i47 = 0; i47 < this.rows; i47++) {
                    int i48 = i47 * 2;
                    int i49 = (i47 * i8) + i44 + i46;
                    fArr4[i48] = fArr[i49];
                    fArr4[i48 + 1] = fArr[i49 + 1];
                }
                this.fftRows.complexForward(fArr4);
                for (int i50 = 0; i50 < this.rows; i50++) {
                    int i51 = i50 * 2;
                    int i52 = (i50 * i8) + i44 + i46;
                    fArr[i52] = fArr4[i51];
                    fArr[i52 + 1] = fArr4[i51 + 1];
                }
            }
            i43++;
        }
        float[] fArr5 = new float[i2 * 2];
        for (int i53 = 0; i53 < i5; i53++) {
            int i54 = i53 * i8;
            for (int i55 = 0; i55 < this.columns; i55++) {
                int i56 = i55 * 2;
                for (int i57 = 0; i57 < this.slices; i57++) {
                    int i58 = i57 * 2;
                    int i59 = (i57 * i7) + i54 + i56;
                    fArr5[i58] = fArr[i59];
                    fArr5[i58 + 1] = fArr[i59 + 1];
                }
                this.fftSlices.complexForward(fArr5);
                for (int i60 = 0; i60 < this.slices; i60++) {
                    int i61 = i60 * 2;
                    int i62 = (i60 * i7) + i54 + i56;
                    fArr[i62] = fArr5[i61];
                    fArr[i62 + 1] = fArr5[i61 + 1];
                }
            }
        }
        int i63 = 0;
        while (true) {
            int i64 = this.slices;
            if (i63 >= i64) {
                return;
            }
            int i65 = ((i64 - i63) % i64) * i7;
            int i66 = i63 * i7;
            int i67 = i38;
            for (int i68 = 1; i68 < i67; i68++) {
                int i69 = i68 * i8;
                int i70 = ((this.rows - i68) * i8) + i65;
                for (int i71 = 0; i71 < this.columns; i71++) {
                    int i72 = i71 * 2;
                    int i73 = i3 - i72;
                    int i74 = i66 + i69 + i72;
                    fArr[(i73 % i3) + i70] = fArr[i74];
                    fArr[((i73 + 1) % i3) + i70] = -fArr[i74 + 1];
                }
            }
            i63++;
            i38 = i67;
        }
    }

    private void mixedRadixRealForwardFull(final FloatLargeArray floatLargeArray) {
        long j;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        FloatLargeArray floatLargeArray2 = new FloatLargeArray(j3);
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
                    futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.33
                        @Override // java.lang.Runnable
                        public void run() {
                            FloatLargeArray floatLargeArray3 = new FloatLargeArray(j14);
                            for (long j15 = j12; j15 >= j13; j15--) {
                                long j16 = j15 * FloatFFT_3D.this.sliceStridel;
                                long j17 = j15 * j7;
                                for (long j18 = FloatFFT_3D.this.rowsl - 1; j18 >= 0; j18--) {
                                    LargeArrayUtils.arraycopy(floatLargeArray, (FloatFFT_3D.this.rowStridel * j18) + j16, floatLargeArray3, 0L, FloatFFT_3D.this.columnsl);
                                    FloatFFT_3D.this.fftColumns.realForwardFull(floatLargeArray3);
                                    LargeArrayUtils.arraycopy(floatLargeArray3, 0L, floatLargeArray, (j8 * j18) + j17, j14);
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
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                long j17 = j9 + 1;
                final FloatLargeArray floatLargeArray3 = new FloatLargeArray(this.rowsl * j17 * j16);
                int i3 = 0;
                while (i3 < i2) {
                    final long j18 = i3 * j11;
                    final long j19 = i3 == i2 + (-1) ? j17 : j18 + j11;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.34
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j20 = j18; j20 < j19; j20++) {
                                long j21 = FloatFFT_3D.this.sliceStridel * j20;
                                for (long j22 = 0; j22 < FloatFFT_3D.this.rowsl; j22++) {
                                    FloatLargeArray floatLargeArray4 = floatLargeArray;
                                    long j23 = j21 + (FloatFFT_3D.this.rowStridel * j22);
                                    FloatLargeArray floatLargeArray5 = floatLargeArray3;
                                    long j24 = FloatFFT_3D.this.rowsl * j20;
                                    long j25 = j16;
                                    LargeArrayUtils.arraycopy(floatLargeArray4, j23, floatLargeArray5, (j24 * j25) + (j25 * j22), FloatFFT_3D.this.columnsl);
                                    FloatFFT_1D floatFFT_1D = FloatFFT_3D.this.fftColumns;
                                    FloatLargeArray floatLargeArray6 = floatLargeArray3;
                                    long j26 = FloatFFT_3D.this.rowsl * j20;
                                    long j27 = j16;
                                    floatFFT_1D.realForwardFull(floatLargeArray6, (j26 * j27) + (j27 * j22));
                                }
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e3) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                }
                int i4 = 0;
                while (i4 < i2) {
                    final long j20 = i4 * j11;
                    final long j21 = i4 == i2 + (-1) ? j17 : j20 + j11;
                    final FloatLargeArray floatLargeArray4 = floatLargeArray3;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.35
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j22 = j20; j22 < j21; j22++) {
                                long j23 = j7 * j22;
                                for (long j24 = 0; j24 < FloatFFT_3D.this.rowsl; j24++) {
                                    FloatLargeArray floatLargeArray5 = floatLargeArray4;
                                    long j25 = FloatFFT_3D.this.rowsl * j22;
                                    long j26 = j16;
                                    LargeArrayUtils.arraycopy(floatLargeArray5, (j25 * j26) + (j24 * j26), floatLargeArray, j23 + (j8 * j24), j26);
                                }
                            }
                        }
                    });
                    i4++;
                    str = str;
                    floatLargeArray3 = floatLargeArray3;
                    i2 = i2;
                }
                String str2 = str;
                int i5 = i2;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e5) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                }
                FloatFFT_3D floatFFT_3D = this;
                long j22 = floatFFT_3D.slicesl / j15;
                int i6 = i5;
                int i7 = 0;
                while (i7 < i6) {
                    final long j23 = i7 * j22;
                    final long j24 = i7 == i6 + (-1) ? floatFFT_3D.slicesl : j23 + j22;
                    futureArr3[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.36
                        @Override // java.lang.Runnable
                        public void run() {
                            long j25 = 2;
                            FloatLargeArray floatLargeArray5 = new FloatLargeArray(FloatFFT_3D.this.rowsl * 2, false);
                            long j26 = j23;
                            while (j26 < j24) {
                                long j27 = j7 * j26;
                                long j28 = 0;
                                while (j28 < FloatFFT_3D.this.columnsl) {
                                    long j29 = j28 * j25;
                                    long j30 = 0;
                                    while (j30 < FloatFFT_3D.this.rowsl) {
                                        long j31 = (j8 * j30) + j27 + j29;
                                        long j32 = j30 * j25;
                                        floatLargeArray5.setFloat(j32, floatLargeArray.getFloat(j31));
                                        floatLargeArray5.setFloat(j32 + 1, floatLargeArray.getFloat(j31 + 1));
                                        j30++;
                                        j26 = j26;
                                        j25 = 2;
                                    }
                                    long j33 = j26;
                                    FloatFFT_3D.this.fftRows.complexForward(floatLargeArray5);
                                    for (long j34 = 0; j34 < FloatFFT_3D.this.rowsl; j34++) {
                                        long j35 = (j8 * j34) + j27 + j29;
                                        long j36 = j34 * 2;
                                        floatLargeArray.setFloat(j35, floatLargeArray5.getFloat(j36));
                                        floatLargeArray.setFloat(j35 + 1, floatLargeArray5.getFloat(j36 + 1));
                                    }
                                    j28++;
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
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                } catch (ExecutionException e8) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                }
                long j25 = j5 / j15;
                int i8 = 0;
                while (i8 < i6) {
                    final long j26 = i8 * j25;
                    final long j27 = i8 == i6 + (-1) ? j5 : j26 + j25;
                    futureArr3[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.37
                        @Override // java.lang.Runnable
                        public void run() {
                            long j28 = 2;
                            FloatLargeArray floatLargeArray5 = new FloatLargeArray(FloatFFT_3D.this.slicesl * 2, false);
                            long j29 = j26;
                            while (j29 < j27) {
                                long j30 = j8 * j29;
                                long j31 = 0;
                                while (j31 < FloatFFT_3D.this.columnsl) {
                                    long j32 = j31 * j28;
                                    long j33 = 0;
                                    while (j33 < FloatFFT_3D.this.slicesl) {
                                        long j34 = j33 * j28;
                                        long j35 = (j7 * j33) + j30 + j32;
                                        floatLargeArray5.setFloat(j34, floatLargeArray.getFloat(j35));
                                        floatLargeArray5.setFloat(j34 + 1, floatLargeArray.getFloat(j35 + 1));
                                        j33++;
                                        j29 = j29;
                                        j28 = 2;
                                    }
                                    long j36 = j29;
                                    FloatFFT_3D.this.fftSlices.complexForward(floatLargeArray5);
                                    for (long j37 = 0; j37 < FloatFFT_3D.this.slicesl; j37++) {
                                        long j38 = j37 * 2;
                                        long j39 = (j7 * j37) + j30 + j32;
                                        floatLargeArray.setFloat(j39, floatLargeArray5.getFloat(j38));
                                        floatLargeArray.setFloat(j39 + 1, floatLargeArray5.getFloat(j38 + 1));
                                    }
                                    j31++;
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
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
                } catch (ExecutionException e10) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
                }
                long j28 = floatFFT_3D.slicesl / j15;
                int i9 = 0;
                while (i9 < i6) {
                    final long j29 = i9 * j28;
                    final long j30 = i9 == i6 + (-1) ? floatFFT_3D.slicesl : j29 + j28;
                    futureArr3[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.38
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j31 = j29; j31 < j30; j31++) {
                                long j32 = (FloatFFT_3D.this.slicesl - j31) % FloatFFT_3D.this.slicesl;
                                long j33 = j7;
                                long j34 = j32 * j33;
                                long j35 = j33 * j31;
                                for (long j36 = 1; j36 < j6; j36++) {
                                    long j37 = FloatFFT_3D.this.rowsl - j36;
                                    long j38 = j8;
                                    long j39 = j37 * j38;
                                    long j40 = j38 * j36;
                                    long j41 = j39 + j34;
                                    long j42 = 0;
                                    while (j42 < FloatFFT_3D.this.columnsl) {
                                        long j43 = 2 * j42;
                                        long j44 = j34;
                                        long j45 = j16;
                                        long j46 = j45 - j43;
                                        long j47 = j35 + j40 + j43;
                                        long j48 = j35;
                                        FloatLargeArray floatLargeArray5 = floatLargeArray;
                                        floatLargeArray5.setFloat((j46 % j45) + j41, floatLargeArray5.getFloat(j47));
                                        FloatLargeArray floatLargeArray6 = floatLargeArray;
                                        floatLargeArray6.setFloat(j41 + ((j46 + 1) % j16), -floatLargeArray6.getFloat(j47 + 1));
                                        j42++;
                                        j34 = j44;
                                        j35 = j48;
                                    }
                                }
                            }
                        }
                    });
                    i9++;
                    floatFFT_3D = this;
                    i6 = i6;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e11) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e11);
                } catch (ExecutionException e12) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e12);
                }
                return;
            }
        }
        for (long j31 = this.slicesl - 1; j31 >= 0; j31--) {
            long j32 = j31 * this.sliceStridel;
            long j33 = j31 * j7;
            for (long j34 = this.rowsl - 1; j34 >= 0; j34--) {
                LargeArrayUtils.arraycopy(floatLargeArray, j32 + (this.rowStridel * j34), floatLargeArray2, 0L, this.columnsl);
                this.fftColumns.realForwardFull(floatLargeArray2);
                LargeArrayUtils.arraycopy(floatLargeArray2, 0L, floatLargeArray, j33 + (j34 * j8), j3);
            }
        }
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.rowsl * 2, false);
        long j35 = 0;
        while (j35 < this.slicesl) {
            long j36 = j35 * j7;
            long j37 = 0;
            while (j37 < this.columnsl) {
                long j38 = j37 * j2;
                long j39 = j36;
                long j40 = 0;
                while (j40 < this.rowsl) {
                    long j41 = j40 * j2;
                    long j42 = j39 + (j40 * j8) + j38;
                    floatLargeArray5.setFloat(j41, floatLargeArray.getFloat(j42));
                    floatLargeArray5.setFloat(j41 + 1, floatLargeArray.getFloat(j42 + 1));
                    j40++;
                    j2 = 2;
                }
                this.fftRows.complexForward(floatLargeArray5);
                for (long j43 = 0; j43 < this.rowsl; j43++) {
                    long j44 = j43 * 2;
                    long j45 = j39 + (j43 * j8) + j38;
                    floatLargeArray.setFloat(j45, floatLargeArray5.getFloat(j44));
                    floatLargeArray.setFloat(j45 + 1, floatLargeArray5.getFloat(j44 + 1));
                }
                j37++;
                j36 = j39;
                j2 = 2;
            }
            j35++;
            j2 = 2;
        }
        long j46 = 2;
        FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.slicesl * 2, false);
        long j47 = 0;
        while (j47 < j5) {
            long j48 = j47 * j8;
            long j49 = 0;
            while (j49 < this.columnsl) {
                long j50 = j49 * j46;
                long j51 = 0;
                while (j51 < this.slicesl) {
                    long j52 = j47;
                    long j53 = j51 * 2;
                    long j54 = (j51 * j7) + j48 + j50;
                    floatLargeArray6.setFloat(j53, floatLargeArray.getFloat(j54));
                    floatLargeArray6.setFloat(j53 + 1, floatLargeArray.getFloat(j54 + 1));
                    j51++;
                    j47 = j52;
                }
                long j55 = j47;
                this.fftSlices.complexForward(floatLargeArray6);
                for (long j56 = 0; j56 < this.slicesl; j56++) {
                    long j57 = j56 * 2;
                    long j58 = (j56 * j7) + j48 + j50;
                    floatLargeArray.setFloat(j58, floatLargeArray6.getFloat(j57));
                    floatLargeArray.setFloat(j58 + 1, floatLargeArray6.getFloat(j57 + 1));
                }
                j49++;
                j47 = j55;
                j46 = 2;
            }
            j47++;
            j46 = 2;
        }
        long j59 = 0;
        while (true) {
            long j60 = this.slicesl;
            if (j59 >= j60) {
                return;
            }
            long j61 = ((j60 - j59) % j60) * j7;
            long j62 = j59 * j7;
            long j63 = 1;
            while (j63 < j6) {
                long j64 = j63 * j8;
                long j65 = ((this.rowsl - j63) * j8) + j61;
                long j66 = j61;
                long j67 = 0;
                while (j67 < this.columnsl) {
                    long j68 = j67 * 2;
                    long j69 = j3 - j68;
                    long j70 = j62 + j64 + j68;
                    floatLargeArray.setFloat(j65 + (j69 % j3), floatLargeArray.getFloat(j70));
                    floatLargeArray.setFloat(j65 + ((j69 + 1) % j3), -floatLargeArray.getFloat(j70 + 1));
                    j67++;
                    j62 = j62;
                }
                j63++;
                j61 = j66;
            }
            j59++;
        }
    }

    private void mixedRadixRealInverseFull(final float[] fArr, final boolean z) {
        int i;
        int i2;
        final int i3 = this.columns * 2;
        float[] fArr2 = new float[i3];
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
                futureArr2[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.39
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr3 = new float[i3];
                        for (int i14 = i12; i14 >= i13; i14--) {
                            int i15 = FloatFFT_3D.this.sliceStride * i14;
                            int i16 = i7 * i14;
                            for (int i17 = FloatFFT_3D.this.rows - 1; i17 >= 0; i17--) {
                                System.arraycopy(fArr, (FloatFFT_3D.this.rowStride * i17) + i15, fArr3, 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realInverseFull(fArr3, z);
                                System.arraycopy(fArr3, 0, fArr, (i8 * i17) + i16, i3);
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i18 = i15 + 1;
            final float[][][] fArr3 = (float[][][]) Array.newInstance((Class<?>) Float.TYPE, i18, this.rows, i3);
            int i19 = i14;
            int i20 = 0;
            while (i20 < i19) {
                final int i21 = i20 * i10;
                final int i22 = i20 == i19 + (-1) ? i18 : i21 + i10;
                futureArr3[i20] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.40
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i23 = i21; i23 < i22; i23++) {
                            int i24 = FloatFFT_3D.this.sliceStride * i23;
                            for (int i25 = 0; i25 < FloatFFT_3D.this.rows; i25++) {
                                System.arraycopy(fArr, (FloatFFT_3D.this.rowStride * i25) + i24, fArr3[i23][i25], 0, FloatFFT_3D.this.columns);
                                FloatFFT_3D.this.fftColumns.realInverseFull(fArr3[i23][i25], z);
                            }
                        }
                    }
                });
                i20++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i23 = 0;
            while (i23 < i19) {
                final int i24 = i23 * i10;
                final int i25 = i23 == i19 + (-1) ? i18 : i24 + i10;
                futureArr3[i23] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.41
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i26 = i24; i26 < i25; i26++) {
                            int i27 = i7 * i26;
                            for (int i28 = 0; i28 < FloatFFT_3D.this.rows; i28++) {
                                System.arraycopy(fArr3[i26][i28], 0, fArr, (i8 * i28) + i27, i3);
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
            int i27 = this.slices / i26;
            int i28 = 0;
            while (i28 < i26) {
                final int i29 = i28 * i27;
                final int i30 = i28 == i26 + (-1) ? this.slices : i29 + i27;
                futureArr3[i28] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.42
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr4 = new float[FloatFFT_3D.this.rows * 2];
                        for (int i31 = i29; i31 < i30; i31++) {
                            int i32 = i7 * i31;
                            for (int i33 = 0; i33 < FloatFFT_3D.this.columns; i33++) {
                                int i34 = i33 * 2;
                                for (int i35 = 0; i35 < FloatFFT_3D.this.rows; i35++) {
                                    int i36 = (i8 * i35) + i32 + i34;
                                    int i37 = i35 * 2;
                                    float[] fArr5 = fArr;
                                    fArr4[i37] = fArr5[i36];
                                    fArr4[i37 + 1] = fArr5[i36 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr4, z);
                                for (int i38 = 0; i38 < FloatFFT_3D.this.rows; i38++) {
                                    int i39 = (i8 * i38) + i32 + i34;
                                    int i40 = i38 * 2;
                                    float[] fArr6 = fArr;
                                    fArr6[i39] = fArr4[i40];
                                    fArr6[i39 + 1] = fArr4[i40 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
            } catch (ExecutionException e8) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
            }
            int i31 = i17 / i26;
            int i32 = 0;
            while (i32 < i26) {
                final int i33 = i32 * i31;
                final int i34 = i32 == i26 + (-1) ? i17 : i33 + i31;
                int i35 = i32;
                futureArr3[i35] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.43
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr4 = new float[FloatFFT_3D.this.slices * 2];
                        for (int i36 = i33; i36 < i34; i36++) {
                            int i37 = i8 * i36;
                            for (int i38 = 0; i38 < FloatFFT_3D.this.columns; i38++) {
                                int i39 = i38 * 2;
                                for (int i40 = 0; i40 < FloatFFT_3D.this.slices; i40++) {
                                    int i41 = i40 * 2;
                                    int i42 = (i7 * i40) + i37 + i39;
                                    float[] fArr5 = fArr;
                                    fArr4[i41] = fArr5[i42];
                                    fArr4[i41 + 1] = fArr5[i42 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr4, z);
                                for (int i43 = 0; i43 < FloatFFT_3D.this.slices; i43++) {
                                    int i44 = i43 * 2;
                                    int i45 = (i7 * i43) + i37 + i39;
                                    float[] fArr6 = fArr;
                                    fArr6[i45] = fArr4[i44];
                                    fArr6[i45 + 1] = fArr4[i44 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
            } catch (ExecutionException e10) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
            }
            int i36 = this.slices / i26;
            int i37 = 0;
            while (i37 < i26) {
                final int i38 = i37 * i36;
                final int i39 = i37 == i26 + (-1) ? this.slices : i38 + i36;
                int i40 = i37;
                futureArr3[i40] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.44
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i41 = i38; i41 < i39; i41++) {
                            int i42 = (FloatFFT_3D.this.slices - i41) % FloatFFT_3D.this.slices;
                            int i43 = i7;
                            int i44 = i42 * i43;
                            int i45 = i43 * i41;
                            for (int i46 = 1; i46 < i16; i46++) {
                                int i47 = FloatFFT_3D.this.rows - i46;
                                int i48 = i8;
                                int i49 = i47 * i48;
                                int i50 = i48 * i46;
                                int i51 = i49 + i44;
                                for (int i52 = 0; i52 < FloatFFT_3D.this.columns; i52++) {
                                    int i53 = i52 * 2;
                                    int i54 = i3;
                                    int i55 = i54 - i53;
                                    int i56 = i45 + i50 + i53;
                                    float[] fArr4 = fArr;
                                    fArr4[(i55 % i54) + i51] = fArr4[i56];
                                    fArr4[((i55 + 1) % i54) + i51] = -fArr4[i56 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e11);
                return;
            } catch (ExecutionException e12) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e12);
                return;
            }
        }
        int i41 = i6;
        for (int i42 = this.slices - 1; i42 >= 0; i42--) {
            int i43 = this.sliceStride * i42;
            int i44 = i42 * i7;
            for (int i45 = this.rows - 1; i45 >= 0; i45--) {
                System.arraycopy(fArr, (this.rowStride * i45) + i43, fArr2, 0, this.columns);
                this.fftColumns.realInverseFull(fArr2, z);
                System.arraycopy(fArr2, 0, fArr, (i45 * i8) + i44, i3);
            }
        }
        float[] fArr4 = new float[this.rows * 2];
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
                    fArr4[i51] = fArr[i52];
                    fArr4[i51 + 1] = fArr[i52 + 1];
                }
                this.fftRows.complexInverse(fArr4, z);
                for (int i53 = 0; i53 < this.rows; i53++) {
                    int i54 = i53 * 2;
                    int i55 = (i53 * i8) + i47 + i49;
                    fArr[i55] = fArr4[i54];
                    fArr[i55 + 1] = fArr4[i54 + 1];
                }
            }
            i46++;
        }
        float[] fArr5 = new float[i2 * 2];
        for (int i56 = 0; i56 < i5; i56++) {
            int i57 = i56 * i8;
            for (int i58 = 0; i58 < this.columns; i58++) {
                int i59 = i58 * 2;
                for (int i60 = 0; i60 < this.slices; i60++) {
                    int i61 = i60 * 2;
                    int i62 = (i60 * i7) + i57 + i59;
                    fArr5[i61] = fArr[i62];
                    fArr5[i61 + 1] = fArr[i62 + 1];
                }
                this.fftSlices.complexInverse(fArr5, z);
                for (int i63 = 0; i63 < this.slices; i63++) {
                    int i64 = i63 * 2;
                    int i65 = (i63 * i7) + i57 + i59;
                    fArr[i65] = fArr5[i64];
                    fArr[i65 + 1] = fArr5[i64 + 1];
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
                    fArr[(i76 % i3) + i73] = fArr[i77];
                    fArr[((i76 + 1) % i3) + i73] = -fArr[i77 + 1];
                }
            }
            i66++;
            i41 = i70;
        }
    }

    private void mixedRadixRealInverseFull(final FloatLargeArray floatLargeArray, final boolean z) {
        long j;
        long j2 = 2;
        long j3 = this.columnsl * 2;
        FloatLargeArray floatLargeArray2 = new FloatLargeArray(j3);
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
                    futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.45
                        @Override // java.lang.Runnable
                        public void run() {
                            FloatLargeArray floatLargeArray3 = new FloatLargeArray(j14);
                            for (long j15 = j12; j15 >= j13; j15--) {
                                long j16 = j15 * FloatFFT_3D.this.sliceStridel;
                                long j17 = j15 * j7;
                                for (long j18 = FloatFFT_3D.this.rowsl - 1; j18 >= 0; j18--) {
                                    LargeArrayUtils.arraycopy(floatLargeArray, (FloatFFT_3D.this.rowStridel * j18) + j16, floatLargeArray3, 0L, FloatFFT_3D.this.columnsl);
                                    FloatFFT_3D.this.fftColumns.realInverseFull(floatLargeArray3, z);
                                    LargeArrayUtils.arraycopy(floatLargeArray3, 0L, floatLargeArray, (j8 * j18) + j17, j14);
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
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                long j17 = j9 + 1;
                final FloatLargeArray floatLargeArray3 = new FloatLargeArray(this.rowsl * j17 * j16);
                int i3 = i2;
                int i4 = 0;
                while (i4 < i3) {
                    final long j18 = i4 * j11;
                    final long j19 = i4 == i3 + (-1) ? j17 : j18 + j11;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.46
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j20 = j18; j20 < j19; j20++) {
                                long j21 = FloatFFT_3D.this.sliceStridel * j20;
                                for (long j22 = 0; j22 < FloatFFT_3D.this.rowsl; j22++) {
                                    FloatLargeArray floatLargeArray4 = floatLargeArray;
                                    long j23 = j21 + (FloatFFT_3D.this.rowStridel * j22);
                                    FloatLargeArray floatLargeArray5 = floatLargeArray3;
                                    long j24 = FloatFFT_3D.this.rowsl * j20;
                                    long j25 = j16;
                                    LargeArrayUtils.arraycopy(floatLargeArray4, j23, floatLargeArray5, (j24 * j25) + (j25 * j22), FloatFFT_3D.this.columnsl);
                                    FloatFFT_1D floatFFT_1D = FloatFFT_3D.this.fftColumns;
                                    FloatLargeArray floatLargeArray6 = floatLargeArray3;
                                    long j26 = FloatFFT_3D.this.rowsl * j20;
                                    long j27 = j16;
                                    floatFFT_1D.realInverseFull(floatLargeArray6, (j26 * j27) + (j27 * j22), z);
                                }
                            }
                        }
                    });
                    i4++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e3) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
                }
                int i5 = 0;
                while (i5 < i3) {
                    final long j20 = i5 * j11;
                    final long j21 = i5 == i3 + (-1) ? j17 : j20 + j11;
                    final FloatLargeArray floatLargeArray4 = floatLargeArray3;
                    futureArr3[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.47
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j22 = j20; j22 < j21; j22++) {
                                long j23 = j7 * j22;
                                for (long j24 = 0; j24 < FloatFFT_3D.this.rowsl; j24++) {
                                    FloatLargeArray floatLargeArray5 = floatLargeArray4;
                                    long j25 = FloatFFT_3D.this.rowsl * j22;
                                    long j26 = j16;
                                    LargeArrayUtils.arraycopy(floatLargeArray5, (j25 * j26) + (j24 * j26), floatLargeArray, j23 + (j8 * j24), j26);
                                }
                            }
                        }
                    });
                    i5++;
                    str = str;
                    floatLargeArray3 = floatLargeArray3;
                    i3 = i3;
                }
                int i6 = i3;
                String str2 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e5) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                } catch (ExecutionException e6) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                }
                FloatFFT_3D floatFFT_3D = this;
                long j22 = floatFFT_3D.slicesl / j15;
                int i7 = i6;
                int i8 = 0;
                while (i8 < i7) {
                    final long j23 = i8 * j22;
                    final long j24 = i8 == i7 + (-1) ? floatFFT_3D.slicesl : j23 + j22;
                    futureArr3[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.48
                        @Override // java.lang.Runnable
                        public void run() {
                            long j25 = 2;
                            FloatLargeArray floatLargeArray5 = new FloatLargeArray(FloatFFT_3D.this.rowsl * 2, false);
                            long j26 = j23;
                            while (j26 < j24) {
                                long j27 = j7 * j26;
                                long j28 = 0;
                                while (j28 < FloatFFT_3D.this.columnsl) {
                                    long j29 = j28 * j25;
                                    long j30 = 0;
                                    while (j30 < FloatFFT_3D.this.rowsl) {
                                        long j31 = (j8 * j30) + j27 + j29;
                                        long j32 = j30 * j25;
                                        floatLargeArray5.setFloat(j32, floatLargeArray.getFloat(j31));
                                        floatLargeArray5.setFloat(j32 + 1, floatLargeArray.getFloat(j31 + 1));
                                        j30++;
                                        j26 = j26;
                                        j25 = 2;
                                    }
                                    long j33 = j26;
                                    FloatFFT_3D.this.fftRows.complexInverse(floatLargeArray5, z);
                                    for (long j34 = 0; j34 < FloatFFT_3D.this.rowsl; j34++) {
                                        long j35 = (j8 * j34) + j27 + j29;
                                        long j36 = j34 * 2;
                                        floatLargeArray.setFloat(j35, floatLargeArray5.getFloat(j36));
                                        floatLargeArray.setFloat(j35 + 1, floatLargeArray5.getFloat(j36 + 1));
                                    }
                                    j28++;
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
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e7);
                } catch (ExecutionException e8) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e8);
                }
                long j25 = j5 / j15;
                int i9 = 0;
                while (i9 < i7) {
                    final long j26 = i9 * j25;
                    final long j27 = i9 == i7 + (-1) ? j5 : j26 + j25;
                    futureArr3[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.49
                        @Override // java.lang.Runnable
                        public void run() {
                            long j28 = 2;
                            FloatLargeArray floatLargeArray5 = new FloatLargeArray(FloatFFT_3D.this.slicesl * 2, false);
                            long j29 = j26;
                            while (j29 < j27) {
                                long j30 = j8 * j29;
                                long j31 = 0;
                                while (j31 < FloatFFT_3D.this.columnsl) {
                                    long j32 = j31 * j28;
                                    long j33 = 0;
                                    while (j33 < FloatFFT_3D.this.slicesl) {
                                        long j34 = j33 * j28;
                                        long j35 = (j7 * j33) + j30 + j32;
                                        floatLargeArray5.setFloat(j34, floatLargeArray.getFloat(j35));
                                        floatLargeArray5.setFloat(j34 + 1, floatLargeArray.getFloat(j35 + 1));
                                        j33++;
                                        j29 = j29;
                                        j28 = 2;
                                    }
                                    long j36 = j29;
                                    FloatFFT_3D.this.fftSlices.complexInverse(floatLargeArray5, z);
                                    for (long j37 = 0; j37 < FloatFFT_3D.this.slicesl; j37++) {
                                        long j38 = j37 * 2;
                                        long j39 = (j7 * j37) + j30 + j32;
                                        floatLargeArray.setFloat(j39, floatLargeArray5.getFloat(j38));
                                        floatLargeArray.setFloat(j39 + 1, floatLargeArray5.getFloat(j38 + 1));
                                    }
                                    j31++;
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
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e9);
                } catch (ExecutionException e10) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e10);
                }
                long j28 = floatFFT_3D.slicesl / j15;
                int i10 = 0;
                while (i10 < i7) {
                    final long j29 = i10 * j28;
                    final long j30 = i10 == i7 + (-1) ? floatFFT_3D.slicesl : j29 + j28;
                    futureArr3[i10] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.50
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j31 = j29; j31 < j30; j31++) {
                                long j32 = (FloatFFT_3D.this.slicesl - j31) % FloatFFT_3D.this.slicesl;
                                long j33 = j7;
                                long j34 = j32 * j33;
                                long j35 = j33 * j31;
                                for (long j36 = 1; j36 < j6; j36++) {
                                    long j37 = FloatFFT_3D.this.rowsl - j36;
                                    long j38 = j8;
                                    long j39 = j37 * j38;
                                    long j40 = j38 * j36;
                                    long j41 = j39 + j34;
                                    long j42 = 0;
                                    while (j42 < FloatFFT_3D.this.columnsl) {
                                        long j43 = 2 * j42;
                                        long j44 = j34;
                                        long j45 = j16;
                                        long j46 = j45 - j43;
                                        long j47 = j35 + j40 + j43;
                                        long j48 = j35;
                                        FloatLargeArray floatLargeArray5 = floatLargeArray;
                                        floatLargeArray5.setFloat((j46 % j45) + j41, floatLargeArray5.getFloat(j47));
                                        FloatLargeArray floatLargeArray6 = floatLargeArray;
                                        floatLargeArray6.setFloat(j41 + ((j46 + 1) % j16), -floatLargeArray6.getFloat(j47 + 1));
                                        j42++;
                                        j34 = j44;
                                        j35 = j48;
                                    }
                                }
                            }
                        }
                    });
                    i10++;
                    floatFFT_3D = this;
                    i7 = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException e11) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e11);
                } catch (ExecutionException e12) {
                    Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e12);
                }
                return;
            }
        }
        for (long j31 = this.slicesl - 1; j31 >= 0; j31--) {
            long j32 = j31 * this.sliceStridel;
            long j33 = j31 * j7;
            for (long j34 = this.rowsl - 1; j34 >= 0; j34--) {
                LargeArrayUtils.arraycopy(floatLargeArray, j32 + (this.rowStridel * j34), floatLargeArray2, 0L, this.columnsl);
                this.fftColumns.realInverseFull(floatLargeArray2, z);
                LargeArrayUtils.arraycopy(floatLargeArray2, 0L, floatLargeArray, j33 + (j34 * j8), j3);
            }
        }
        FloatLargeArray floatLargeArray5 = new FloatLargeArray(this.rowsl * 2, false);
        long j35 = 0;
        while (j35 < this.slicesl) {
            long j36 = j35 * j7;
            long j37 = 0;
            while (j37 < this.columnsl) {
                long j38 = j37 * j2;
                long j39 = j36;
                long j40 = 0;
                while (j40 < this.rowsl) {
                    long j41 = j40 * j2;
                    long j42 = j39 + (j40 * j8) + j38;
                    floatLargeArray5.setFloat(j41, floatLargeArray.getFloat(j42));
                    floatLargeArray5.setFloat(j41 + 1, floatLargeArray.getFloat(j42 + 1));
                    j40++;
                    j2 = 2;
                }
                this.fftRows.complexInverse(floatLargeArray5, z);
                for (long j43 = 0; j43 < this.rowsl; j43++) {
                    long j44 = j43 * 2;
                    long j45 = j39 + (j43 * j8) + j38;
                    floatLargeArray.setFloat(j45, floatLargeArray5.getFloat(j44));
                    floatLargeArray.setFloat(j45 + 1, floatLargeArray5.getFloat(j44 + 1));
                }
                j37++;
                j36 = j39;
                j2 = 2;
            }
            j35++;
            j2 = 2;
        }
        long j46 = 2;
        FloatLargeArray floatLargeArray6 = new FloatLargeArray(this.slicesl * 2, false);
        long j47 = 0;
        while (j47 < j5) {
            long j48 = j47 * j8;
            long j49 = 0;
            while (j49 < this.columnsl) {
                long j50 = j49 * j46;
                long j51 = 0;
                while (j51 < this.slicesl) {
                    long j52 = j47;
                    long j53 = j51 * 2;
                    long j54 = (j51 * j7) + j48 + j50;
                    floatLargeArray6.setFloat(j53, floatLargeArray.getFloat(j54));
                    floatLargeArray6.setFloat(j53 + 1, floatLargeArray.getFloat(j54 + 1));
                    j51++;
                    j47 = j52;
                }
                long j55 = j47;
                this.fftSlices.complexInverse(floatLargeArray6, z);
                for (long j56 = 0; j56 < this.slicesl; j56++) {
                    long j57 = j56 * 2;
                    long j58 = (j56 * j7) + j48 + j50;
                    floatLargeArray.setFloat(j58, floatLargeArray6.getFloat(j57));
                    floatLargeArray.setFloat(j58 + 1, floatLargeArray6.getFloat(j57 + 1));
                }
                j49++;
                j47 = j55;
                j46 = 2;
            }
            j47++;
            j46 = 2;
        }
        long j59 = 0;
        while (true) {
            long j60 = this.slicesl;
            if (j59 >= j60) {
                return;
            }
            long j61 = ((j60 - j59) % j60) * j7;
            long j62 = j59 * j7;
            long j63 = 1;
            while (j63 < j6) {
                long j64 = j63 * j8;
                long j65 = ((this.rowsl - j63) * j8) + j61;
                long j66 = j61;
                long j67 = 0;
                while (j67 < this.columnsl) {
                    long j68 = j67 * 2;
                    long j69 = j3 - j68;
                    long j70 = j62 + j64 + j68;
                    floatLargeArray.setFloat(j65 + (j69 % j3), floatLargeArray.getFloat(j70));
                    floatLargeArray.setFloat(j65 + ((j69 + 1) % j3), -floatLargeArray.getFloat(j70 + 1));
                    j67++;
                    j62 = j62;
                }
                j63++;
                j61 = j66;
            }
            j59++;
        }
    }

    private void xdft3da_sub1(int i, int i2, float[] fArr, boolean z) {
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
        float[] fArr2 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(fArr, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(fArr, (this.rowStride * i10) + i8);
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
                            fArr2[i16] = fArr[i15];
                            fArr2[i16 + 1] = fArr[i15 + 1];
                            fArr2[i17] = fArr[i15 + 2];
                            fArr2[i17 + 1] = fArr[i15 + 3];
                            fArr2[i18] = fArr[i15 + 4];
                            fArr2[i18 + 1] = fArr[i15 + 5];
                            fArr2[i19] = fArr[i15 + 6];
                            fArr2[i19 + 1] = fArr[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(fArr2, 0);
                        this.fftRows.complexForward(fArr2, this.rows * 2);
                        this.fftRows.complexForward(fArr2, this.rows * 4);
                        this.fftRows.complexForward(fArr2, this.rows * 6);
                        int i20 = 0;
                        while (true) {
                            int i21 = this.rows;
                            if (i20 < i21) {
                                int i22 = (this.rowStride * i20) + i8 + i12;
                                int i23 = i20 * 2;
                                int i24 = (i21 * 2) + i23;
                                int i25 = (i21 * 2) + i24;
                                int i26 = (i21 * 2) + i25;
                                fArr[i22] = fArr2[i23];
                                fArr[i22 + 1] = fArr2[i23 + 1];
                                fArr[i22 + 2] = fArr2[i24];
                                fArr[i22 + 3] = fArr2[i24 + 1];
                                fArr[i22 + 4] = fArr2[i25];
                                fArr[i22 + 5] = fArr2[i25 + 1];
                                fArr[i22 + 6] = fArr2[i26];
                                fArr[i22 + 7] = fArr2[i26 + 1];
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
                        fArr2[i30] = fArr[i29];
                        fArr2[i30 + 1] = fArr[i29 + 1];
                        fArr2[i31] = fArr[i29 + 2];
                        fArr2[i31 + 1] = fArr[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 < i33) {
                            int i34 = (this.rowStride * i32) + i8;
                            int i35 = i32 * 2;
                            int i36 = (i33 * 2) + i35;
                            fArr[i34] = fArr2[i35];
                            fArr[i34 + 1] = fArr2[i35 + 1];
                            fArr[i34 + 2] = fArr2[i36];
                            fArr[i34 + 3] = fArr2[i36 + 1];
                            i32++;
                        }
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        fArr2[i39] = fArr[i38];
                        fArr2[i39 + 1] = fArr[i38 + 1];
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        fArr[i41] = fArr2[i42];
                        fArr[i41 + 1] = fArr2[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(fArr, (this.rowStride * i45) + i44, z);
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
                        fArr2[i51] = fArr[i50];
                        fArr2[i51 + 1] = fArr[i50 + 1];
                        fArr2[i52] = fArr[i50 + 2];
                        fArr2[i52 + 1] = fArr[i50 + 3];
                        fArr2[i53] = fArr[i50 + 4];
                        fArr2[i53 + 1] = fArr[i50 + 5];
                        fArr2[i54] = fArr[i50 + 6];
                        fArr2[i54 + 1] = fArr[i50 + 7];
                        i48++;
                    }
                    this.fftRows.complexInverse(fArr2, 0, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 6, z);
                    int i55 = 0;
                    while (true) {
                        int i56 = this.rows;
                        if (i55 < i56) {
                            int i57 = (this.rowStride * i55) + i44 + i47;
                            int i58 = i55 * 2;
                            int i59 = (i56 * 2) + i58;
                            int i60 = (i56 * 2) + i59;
                            int i61 = (i56 * 2) + i60;
                            fArr[i57] = fArr2[i58];
                            fArr[i57 + 1] = fArr2[i58 + 1];
                            fArr[i57 + 2] = fArr2[i59];
                            fArr[i57 + 3] = fArr2[i59 + 1];
                            fArr[i57 + 4] = fArr2[i60];
                            fArr[i57 + 5] = fArr2[i60 + 1];
                            fArr[i57 + 6] = fArr2[i61];
                            fArr[i57 + 7] = fArr2[i61 + 1];
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
                    fArr2[i65] = fArr[i64];
                    fArr2[i65 + 1] = fArr[i64 + 1];
                    fArr2[i66] = fArr[i64 + 2];
                    fArr2[i66 + 1] = fArr[i64 + 3];
                    i62++;
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                int i67 = 0;
                while (true) {
                    int i68 = this.rows;
                    if (i67 >= i68) {
                        break;
                    }
                    int i69 = (this.rowStride * i67) + i44;
                    int i70 = i67 * 2;
                    int i71 = (i68 * 2) + i70;
                    fArr[i69] = fArr2[i70];
                    fArr[i69 + 1] = fArr2[i70 + 1];
                    fArr[i69 + 2] = fArr2[i71];
                    fArr[i69 + 3] = fArr2[i71 + 1];
                    i67++;
                }
            } else if (i46 == 2) {
                for (int i72 = 0; i72 < this.rows; i72++) {
                    int i73 = (this.rowStride * i72) + i44;
                    int i74 = i72 * 2;
                    fArr2[i74] = fArr[i73];
                    fArr2[i74 + 1] = fArr[i73 + 1];
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                for (int i75 = 0; i75 < this.rows; i75++) {
                    int i76 = (this.rowStride * i75) + i44;
                    int i77 = i75 * 2;
                    fArr[i76] = fArr2[i77];
                    fArr[i76 + 1] = fArr2[i77 + 1];
                }
            }
            if (i != 0) {
                for (int i78 = 0; i78 < this.rows; i78++) {
                    this.fftColumns.realInverse(fArr, (this.rowStride * i78) + i44, z);
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
    private void xdft3da_sub1(long r36, int r38, pl.edu.icm.jlargearrays.FloatLargeArray r39, boolean r40) {
        /*
            Method dump skipped, instructions count: 1345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_sub1(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_sub2(int i, int i2, float[] fArr, boolean z) {
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
        float[] fArr2 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                int i8 = this.sliceStride * i7;
                if (i == 0) {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.complexForward(fArr, (this.rowStride * i9) + i8);
                    }
                } else {
                    for (int i10 = 0; i10 < this.rows; i10++) {
                        this.fftColumns.realForward(fArr, (this.rowStride * i10) + i8);
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
                            fArr2[i16] = fArr[i15];
                            fArr2[i16 + 1] = fArr[i15 + 1];
                            fArr2[i17] = fArr[i15 + 2];
                            fArr2[i17 + 1] = fArr[i15 + 3];
                            fArr2[i18] = fArr[i15 + 4];
                            fArr2[i18 + 1] = fArr[i15 + 5];
                            fArr2[i19] = fArr[i15 + 6];
                            fArr2[i19 + 1] = fArr[i15 + 7];
                            i13++;
                        }
                        this.fftRows.complexForward(fArr2, 0);
                        this.fftRows.complexForward(fArr2, this.rows * 2);
                        this.fftRows.complexForward(fArr2, this.rows * 4);
                        this.fftRows.complexForward(fArr2, this.rows * 6);
                        int i20 = 0;
                        while (true) {
                            int i21 = this.rows;
                            if (i20 < i21) {
                                int i22 = (this.rowStride * i20) + i8 + i12;
                                int i23 = i20 * 2;
                                int i24 = (i21 * 2) + i23;
                                int i25 = (i21 * 2) + i24;
                                int i26 = (i21 * 2) + i25;
                                fArr[i22] = fArr2[i23];
                                fArr[i22 + 1] = fArr2[i23 + 1];
                                fArr[i22 + 2] = fArr2[i24];
                                fArr[i22 + 3] = fArr2[i24 + 1];
                                fArr[i22 + 4] = fArr2[i25];
                                fArr[i22 + 5] = fArr2[i25 + 1];
                                fArr[i22 + 6] = fArr2[i26];
                                fArr[i22 + 7] = fArr2[i26 + 1];
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
                        fArr2[i30] = fArr[i29];
                        fArr2[i30 + 1] = fArr[i29 + 1];
                        fArr2[i31] = fArr[i29 + 2];
                        fArr2[i31 + 1] = fArr[i29 + 3];
                        i27++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    int i32 = 0;
                    while (true) {
                        int i33 = this.rows;
                        if (i32 < i33) {
                            int i34 = (this.rowStride * i32) + i8;
                            int i35 = i32 * 2;
                            int i36 = (i33 * 2) + i35;
                            fArr[i34] = fArr2[i35];
                            fArr[i34 + 1] = fArr2[i35 + 1];
                            fArr[i34 + 2] = fArr2[i36];
                            fArr[i34 + 3] = fArr2[i36 + 1];
                            i32++;
                        }
                    }
                } else if (i11 == 2) {
                    for (int i37 = 0; i37 < this.rows; i37++) {
                        int i38 = (this.rowStride * i37) + i8;
                        int i39 = i37 * 2;
                        fArr2[i39] = fArr[i38];
                        fArr2[i39 + 1] = fArr[i38 + 1];
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    for (int i40 = 0; i40 < this.rows; i40++) {
                        int i41 = (this.rowStride * i40) + i8;
                        int i42 = i40 * 2;
                        fArr[i41] = fArr2[i42];
                        fArr[i41 + 1] = fArr2[i42 + 1];
                    }
                }
            }
            return;
        }
        for (int i43 = 0; i43 < this.slices; i43++) {
            int i44 = this.sliceStride * i43;
            if (i == 0) {
                for (int i45 = 0; i45 < this.rows; i45++) {
                    this.fftColumns.complexInverse(fArr, (this.rowStride * i45) + i44, z);
                }
            } else {
                for (int i46 = 0; i46 < this.rows; i46++) {
                    this.fftColumns.realInverse2(fArr, (this.rowStride * i46) + i44, z);
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
                        fArr2[i52] = fArr[i51];
                        fArr2[i52 + 1] = fArr[i51 + 1];
                        fArr2[i53] = fArr[i51 + 2];
                        fArr2[i53 + 1] = fArr[i51 + 3];
                        fArr2[i54] = fArr[i51 + 4];
                        fArr2[i54 + 1] = fArr[i51 + 5];
                        fArr2[i55] = fArr[i51 + 6];
                        fArr2[i55 + 1] = fArr[i51 + 7];
                        i49++;
                    }
                    this.fftRows.complexInverse(fArr2, 0, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 6, z);
                    int i56 = 0;
                    while (true) {
                        int i57 = this.rows;
                        if (i56 < i57) {
                            int i58 = (this.rowStride * i56) + i44 + i48;
                            int i59 = i56 * 2;
                            int i60 = (i57 * 2) + i59;
                            int i61 = (i57 * 2) + i60;
                            int i62 = (i57 * 2) + i61;
                            fArr[i58] = fArr2[i59];
                            fArr[i58 + 1] = fArr2[i59 + 1];
                            fArr[i58 + 2] = fArr2[i60];
                            fArr[i58 + 3] = fArr2[i60 + 1];
                            fArr[i58 + 4] = fArr2[i61];
                            fArr[i58 + 5] = fArr2[i61 + 1];
                            fArr[i58 + 6] = fArr2[i62];
                            fArr[i58 + 7] = fArr2[i62 + 1];
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
                    fArr2[i66] = fArr[i65];
                    fArr2[i66 + 1] = fArr[i65 + 1];
                    fArr2[i67] = fArr[i65 + 2];
                    fArr2[i67 + 1] = fArr[i65 + 3];
                    i63++;
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                int i68 = 0;
                while (true) {
                    int i69 = this.rows;
                    if (i68 < i69) {
                        int i70 = (this.rowStride * i68) + i44;
                        int i71 = i68 * 2;
                        int i72 = (i69 * 2) + i71;
                        fArr[i70] = fArr2[i71];
                        fArr[i70 + 1] = fArr2[i71 + 1];
                        fArr[i70 + 2] = fArr2[i72];
                        fArr[i70 + 3] = fArr2[i72 + 1];
                        i68++;
                    }
                }
            } else if (i47 == 2) {
                for (int i73 = 0; i73 < this.rows; i73++) {
                    int i74 = (this.rowStride * i73) + i44;
                    int i75 = i73 * 2;
                    fArr2[i75] = fArr[i74];
                    fArr2[i75 + 1] = fArr[i74 + 1];
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                for (int i76 = 0; i76 < this.rows; i76++) {
                    int i77 = (this.rowStride * i76) + i44;
                    int i78 = i76 * 2;
                    fArr[i77] = fArr2[i78];
                    fArr[i77 + 1] = fArr2[i78 + 1];
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
    private void xdft3da_sub2(long r36, int r38, pl.edu.icm.jlargearrays.FloatLargeArray r39, boolean r40) {
        /*
            Method dump skipped, instructions count: 1329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_sub2(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_sub1(int i, int i2, float[][][] fArr, boolean z) {
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
        float[] fArr2 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(fArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(fArr[i7][i9], 0);
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
                            float[] fArr3 = fArr[i7][i12];
                            fArr2[i14] = fArr3[i11];
                            fArr2[i14 + 1] = fArr3[i11 + 1];
                            fArr2[i15] = fArr3[i11 + 2];
                            fArr2[i15 + 1] = fArr3[i11 + 3];
                            fArr2[i16] = fArr3[i11 + 4];
                            fArr2[i16 + 1] = fArr3[i11 + 5];
                            fArr2[i17] = fArr3[i11 + 6];
                            fArr2[i17 + 1] = fArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(fArr2, 0);
                        this.fftRows.complexForward(fArr2, this.rows * 2);
                        this.fftRows.complexForward(fArr2, this.rows * 4);
                        this.fftRows.complexForward(fArr2, this.rows * 6);
                        int i18 = 0;
                        while (true) {
                            int i19 = this.rows;
                            if (i18 < i19) {
                                int i20 = i18 * 2;
                                int i21 = (i19 * 2) + i20;
                                int i22 = (i19 * 2) + i21;
                                int i23 = (i19 * 2) + i22;
                                float[] fArr4 = fArr[i7][i18];
                                fArr4[i11] = fArr2[i20];
                                fArr4[i11 + 1] = fArr2[i20 + 1];
                                fArr4[i11 + 2] = fArr2[i21];
                                fArr4[i11 + 3] = fArr2[i21 + 1];
                                fArr4[i11 + 4] = fArr2[i22];
                                fArr4[i11 + 5] = fArr2[i22 + 1];
                                fArr4[i11 + 6] = fArr2[i23];
                                fArr4[i11 + 7] = fArr2[i23 + 1];
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
                        float[] fArr5 = fArr[i7][i24];
                        fArr2[i26] = fArr5[0];
                        fArr2[i26 + 1] = fArr5[1];
                        fArr2[i27] = fArr5[2];
                        fArr2[i27 + 1] = fArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 < i29) {
                            int i30 = i28 * 2;
                            int i31 = (i29 * 2) + i30;
                            float[] fArr6 = fArr[i7][i28];
                            fArr6[0] = fArr2[i30];
                            fArr6[1] = fArr2[i30 + 1];
                            fArr6[2] = fArr2[i31];
                            fArr6[3] = fArr2[i31 + 1];
                            i28++;
                        }
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        float[] fArr7 = fArr[i7][i32];
                        fArr2[i33] = fArr7[0];
                        fArr2[i33 + 1] = fArr7[1];
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        float[] fArr8 = fArr[i7][i34];
                        fArr8[0] = fArr2[i35];
                        fArr8[1] = fArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(fArr[i36][i37], z);
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
                        float[] fArr9 = fArr[i36][i40];
                        fArr2[i42] = fArr9[i39];
                        fArr2[i42 + 1] = fArr9[i39 + 1];
                        fArr2[i43] = fArr9[i39 + 2];
                        fArr2[i43 + 1] = fArr9[i39 + 3];
                        fArr2[i44] = fArr9[i39 + 4];
                        fArr2[i44 + 1] = fArr9[i39 + 5];
                        fArr2[i45] = fArr9[i39 + 6];
                        fArr2[i45 + 1] = fArr9[i39 + 7];
                        i40++;
                    }
                    this.fftRows.complexInverse(fArr2, 0, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 6, z);
                    int i46 = 0;
                    while (true) {
                        int i47 = this.rows;
                        if (i46 < i47) {
                            int i48 = i46 * 2;
                            int i49 = (i47 * 2) + i48;
                            int i50 = (i47 * 2) + i49;
                            int i51 = (i47 * 2) + i50;
                            float[] fArr10 = fArr[i36][i46];
                            fArr10[i39] = fArr2[i48];
                            fArr10[i39 + 1] = fArr2[i48 + 1];
                            fArr10[i39 + 2] = fArr2[i49];
                            fArr10[i39 + 3] = fArr2[i49 + 1];
                            fArr10[i39 + 4] = fArr2[i50];
                            fArr10[i39 + 5] = fArr2[i50 + 1];
                            fArr10[i39 + 6] = fArr2[i51];
                            fArr10[i39 + 7] = fArr2[i51 + 1];
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
                    float[] fArr11 = fArr[i36][i52];
                    fArr2[i54] = fArr11[0];
                    fArr2[i54 + 1] = fArr11[1];
                    fArr2[i55] = fArr11[2];
                    fArr2[i55 + 1] = fArr11[3];
                    i52++;
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                int i56 = 0;
                while (true) {
                    int i57 = this.rows;
                    if (i56 >= i57) {
                        break;
                    }
                    int i58 = i56 * 2;
                    int i59 = (i57 * 2) + i58;
                    float[] fArr12 = fArr[i36][i56];
                    fArr12[0] = fArr2[i58];
                    fArr12[1] = fArr2[i58 + 1];
                    fArr12[2] = fArr2[i59];
                    fArr12[3] = fArr2[i59 + 1];
                    i56++;
                }
            } else if (i38 == 2) {
                for (int i60 = 0; i60 < this.rows; i60++) {
                    int i61 = i60 * 2;
                    float[] fArr13 = fArr[i36][i60];
                    fArr2[i61] = fArr13[0];
                    fArr2[i61 + 1] = fArr13[1];
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                for (int i62 = 0; i62 < this.rows; i62++) {
                    int i63 = i62 * 2;
                    float[] fArr14 = fArr[i36][i62];
                    fArr14[0] = fArr2[i63];
                    fArr14[1] = fArr2[i63 + 1];
                }
            }
            if (i != 0) {
                for (int i64 = 0; i64 < this.rows; i64++) {
                    this.fftColumns.realInverse(fArr[i36][i64], z);
                }
            }
        }
    }

    private void xdft3da_sub2(int i, int i2, float[][][] fArr, boolean z) {
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
        float[] fArr2 = new float[i5];
        if (i2 == -1) {
            for (int i7 = 0; i7 < this.slices; i7++) {
                if (i == 0) {
                    for (int i8 = 0; i8 < this.rows; i8++) {
                        this.fftColumns.complexForward(fArr[i7][i8]);
                    }
                } else {
                    for (int i9 = 0; i9 < this.rows; i9++) {
                        this.fftColumns.realForward(fArr[i7][i9]);
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
                            float[] fArr3 = fArr[i7][i12];
                            fArr2[i14] = fArr3[i11];
                            fArr2[i14 + 1] = fArr3[i11 + 1];
                            fArr2[i15] = fArr3[i11 + 2];
                            fArr2[i15 + 1] = fArr3[i11 + 3];
                            fArr2[i16] = fArr3[i11 + 4];
                            fArr2[i16 + 1] = fArr3[i11 + 5];
                            fArr2[i17] = fArr3[i11 + 6];
                            fArr2[i17 + 1] = fArr3[i11 + 7];
                            i12++;
                        }
                        this.fftRows.complexForward(fArr2, 0);
                        this.fftRows.complexForward(fArr2, this.rows * 2);
                        this.fftRows.complexForward(fArr2, this.rows * 4);
                        this.fftRows.complexForward(fArr2, this.rows * 6);
                        int i18 = 0;
                        while (true) {
                            int i19 = this.rows;
                            if (i18 < i19) {
                                int i20 = i18 * 2;
                                int i21 = (i19 * 2) + i20;
                                int i22 = (i19 * 2) + i21;
                                int i23 = (i19 * 2) + i22;
                                float[] fArr4 = fArr[i7][i18];
                                fArr4[i11] = fArr2[i20];
                                fArr4[i11 + 1] = fArr2[i20 + 1];
                                fArr4[i11 + 2] = fArr2[i21];
                                fArr4[i11 + 3] = fArr2[i21 + 1];
                                fArr4[i11 + 4] = fArr2[i22];
                                fArr4[i11 + 5] = fArr2[i22 + 1];
                                fArr4[i11 + 6] = fArr2[i23];
                                fArr4[i11 + 7] = fArr2[i23 + 1];
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
                        float[] fArr5 = fArr[i7][i24];
                        fArr2[i26] = fArr5[0];
                        fArr2[i26 + 1] = fArr5[1];
                        fArr2[i27] = fArr5[2];
                        fArr2[i27 + 1] = fArr5[3];
                        i24++;
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    this.fftRows.complexForward(fArr2, this.rows * 2);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.rows;
                        if (i28 < i29) {
                            int i30 = i28 * 2;
                            int i31 = (i29 * 2) + i30;
                            float[] fArr6 = fArr[i7][i28];
                            fArr6[0] = fArr2[i30];
                            fArr6[1] = fArr2[i30 + 1];
                            fArr6[2] = fArr2[i31];
                            fArr6[3] = fArr2[i31 + 1];
                            i28++;
                        }
                    }
                } else if (i10 == 2) {
                    for (int i32 = 0; i32 < this.rows; i32++) {
                        int i33 = i32 * 2;
                        float[] fArr7 = fArr[i7][i32];
                        fArr2[i33] = fArr7[0];
                        fArr2[i33 + 1] = fArr7[1];
                    }
                    this.fftRows.complexForward(fArr2, 0);
                    for (int i34 = 0; i34 < this.rows; i34++) {
                        int i35 = i34 * 2;
                        float[] fArr8 = fArr[i7][i34];
                        fArr8[0] = fArr2[i35];
                        fArr8[1] = fArr2[i35 + 1];
                    }
                }
            }
            return;
        }
        for (int i36 = 0; i36 < this.slices; i36++) {
            if (i == 0) {
                for (int i37 = 0; i37 < this.rows; i37++) {
                    this.fftColumns.complexInverse(fArr[i36][i37], z);
                }
            } else {
                for (int i38 = 0; i38 < this.rows; i38++) {
                    this.fftColumns.realInverse2(fArr[i36][i38], 0, z);
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
                        float[] fArr9 = fArr[i36][i41];
                        fArr2[i43] = fArr9[i40];
                        fArr2[i43 + 1] = fArr9[i40 + 1];
                        fArr2[i44] = fArr9[i40 + 2];
                        fArr2[i44 + 1] = fArr9[i40 + 3];
                        fArr2[i45] = fArr9[i40 + 4];
                        fArr2[i45 + 1] = fArr9[i40 + 5];
                        fArr2[i46] = fArr9[i40 + 6];
                        fArr2[i46 + 1] = fArr9[i40 + 7];
                        i41++;
                    }
                    this.fftRows.complexInverse(fArr2, 0, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 4, z);
                    this.fftRows.complexInverse(fArr2, this.rows * 6, z);
                    int i47 = 0;
                    while (true) {
                        int i48 = this.rows;
                        if (i47 < i48) {
                            int i49 = i47 * 2;
                            int i50 = (i48 * 2) + i49;
                            int i51 = (i48 * 2) + i50;
                            int i52 = (i48 * 2) + i51;
                            float[] fArr10 = fArr[i36][i47];
                            fArr10[i40] = fArr2[i49];
                            fArr10[i40 + 1] = fArr2[i49 + 1];
                            fArr10[i40 + 2] = fArr2[i50];
                            fArr10[i40 + 3] = fArr2[i50 + 1];
                            fArr10[i40 + 4] = fArr2[i51];
                            fArr10[i40 + 5] = fArr2[i51 + 1];
                            fArr10[i40 + 6] = fArr2[i52];
                            fArr10[i40 + 7] = fArr2[i52 + 1];
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
                    float[] fArr11 = fArr[i36][i53];
                    fArr2[i55] = fArr11[0];
                    fArr2[i55 + 1] = fArr11[1];
                    fArr2[i56] = fArr11[2];
                    fArr2[i56 + 1] = fArr11[3];
                    i53++;
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                this.fftRows.complexInverse(fArr2, this.rows * 2, z);
                int i57 = 0;
                while (true) {
                    int i58 = this.rows;
                    if (i57 < i58) {
                        int i59 = i57 * 2;
                        int i60 = (i58 * 2) + i59;
                        float[] fArr12 = fArr[i36][i57];
                        fArr12[0] = fArr2[i59];
                        fArr12[1] = fArr2[i59 + 1];
                        fArr12[2] = fArr2[i60];
                        fArr12[3] = fArr2[i60 + 1];
                        i57++;
                    }
                }
            } else if (i39 == 2) {
                for (int i61 = 0; i61 < this.rows; i61++) {
                    int i62 = i61 * 2;
                    float[] fArr13 = fArr[i36][i61];
                    fArr2[i62] = fArr13[0];
                    fArr2[i62 + 1] = fArr13[1];
                }
                this.fftRows.complexInverse(fArr2, 0, z);
                for (int i63 = 0; i63 < this.rows; i63++) {
                    int i64 = i63 * 2;
                    float[] fArr14 = fArr[i36][i63];
                    fArr14[0] = fArr2[i64];
                    fArr14[1] = fArr2[i64 + 1];
                }
            }
        }
    }

    private void cdft3db_sub(int i, float[] fArr, boolean z) {
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
        float[] fArr2 = new float[i4];
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
                            fArr2[i12] = fArr[i11];
                            fArr2[i12 + 1] = fArr[i11 + 1];
                            fArr2[i13] = fArr[i11 + 2];
                            fArr2[i13 + 1] = fArr[i11 + 3];
                            fArr2[i14] = fArr[i11 + 4];
                            fArr2[i14 + 1] = fArr[i11 + 5];
                            fArr2[i15] = fArr[i11 + 6];
                            fArr2[i15 + 1] = fArr[i11 + 7];
                            i9++;
                        }
                        this.fftSlices.complexForward(fArr2, 0);
                        this.fftSlices.complexForward(fArr2, this.slices * 2);
                        this.fftSlices.complexForward(fArr2, this.slices * 4);
                        this.fftSlices.complexForward(fArr2, this.slices * 6);
                        int i16 = 0;
                        while (true) {
                            int i17 = this.slices;
                            if (i16 < i17) {
                                int i18 = (this.sliceStride * i16) + i7 + i8;
                                int i19 = i16 * 2;
                                int i20 = (i17 * 2) + i19;
                                int i21 = (i17 * 2) + i20;
                                int i22 = (i17 * 2) + i21;
                                fArr[i18] = fArr2[i19];
                                fArr[i18 + 1] = fArr2[i19 + 1];
                                fArr[i18 + 2] = fArr2[i20];
                                fArr[i18 + 3] = fArr2[i20 + 1];
                                fArr[i18 + 4] = fArr2[i21];
                                fArr[i18 + 5] = fArr2[i21 + 1];
                                fArr[i18 + 6] = fArr2[i22];
                                fArr[i18 + 7] = fArr2[i22 + 1];
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
                            fArr2[i27] = fArr[i26];
                            fArr2[i27 + 1] = fArr[i26 + 1];
                        }
                        this.fftSlices.complexForward(fArr2, 0);
                        for (int i28 = 0; i28 < this.slices; i28++) {
                            int i29 = (this.sliceStride * i28) + i24;
                            int i30 = i28 * 2;
                            fArr[i29] = fArr2[i30];
                            fArr[i29 + 1] = fArr2[i30 + 1];
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
                    fArr2[i36] = fArr[i35];
                    fArr2[i36 + 1] = fArr[i35 + 1];
                    fArr2[i37] = fArr[i35 + 2];
                    fArr2[i37 + 1] = fArr[i35 + 3];
                    i33++;
                }
                this.fftSlices.complexForward(fArr2, 0);
                this.fftSlices.complexForward(fArr2, this.slices * 2);
                int i38 = 0;
                while (true) {
                    int i39 = this.slices;
                    if (i38 < i39) {
                        int i40 = (this.sliceStride * i38) + i32;
                        int i41 = i38 * 2;
                        int i42 = (i39 * 2) + i41;
                        fArr[i40] = fArr2[i41];
                        fArr[i40 + 1] = fArr2[i41 + 1];
                        fArr[i40 + 2] = fArr2[i42];
                        fArr[i40 + 3] = fArr2[i42 + 1];
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
                        fArr2[i49] = fArr[i48];
                        fArr2[i49 + 1] = fArr[i48 + 1];
                        fArr2[i50] = fArr[i48 + 2];
                        fArr2[i50 + 1] = fArr[i48 + 3];
                        fArr2[i51] = fArr[i48 + 4];
                        fArr2[i51 + 1] = fArr[i48 + 5];
                        fArr2[i52] = fArr[i48 + 6];
                        fArr2[i52 + 1] = fArr[i48 + 7];
                        i46++;
                    }
                    this.fftSlices.complexInverse(fArr2, 0, z);
                    this.fftSlices.complexInverse(fArr2, this.slices * 2, z);
                    this.fftSlices.complexInverse(fArr2, this.slices * 4, z);
                    this.fftSlices.complexInverse(fArr2, this.slices * 6, z);
                    int i53 = 0;
                    while (true) {
                        int i54 = this.slices;
                        if (i53 < i54) {
                            int i55 = (this.sliceStride * i53) + i44 + i45;
                            int i56 = i53 * 2;
                            int i57 = (i54 * 2) + i56;
                            int i58 = (i54 * 2) + i57;
                            int i59 = (i54 * 2) + i58;
                            fArr[i55] = fArr2[i56];
                            fArr[i55 + 1] = fArr2[i56 + 1];
                            fArr[i55 + 2] = fArr2[i57];
                            fArr[i55 + 3] = fArr2[i57 + 1];
                            fArr[i55 + 4] = fArr2[i58];
                            fArr[i55 + 5] = fArr2[i58 + 1];
                            fArr[i55 + 6] = fArr2[i59];
                            fArr[i55 + 7] = fArr2[i59 + 1];
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
                        fArr2[i64] = fArr[i63];
                        fArr2[i64 + 1] = fArr[i63 + 1];
                    }
                    this.fftSlices.complexInverse(fArr2, 0, z);
                    for (int i65 = 0; i65 < this.slices; i65++) {
                        int i66 = (this.sliceStride * i65) + i61;
                        int i67 = i65 * 2;
                        fArr[i66] = fArr2[i67];
                        fArr[i66 + 1] = fArr2[i67 + 1];
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
                fArr2[i73] = fArr[i72];
                fArr2[i73 + 1] = fArr[i72 + 1];
                fArr2[i74] = fArr[i72 + 2];
                fArr2[i74 + 1] = fArr[i72 + 3];
                i70++;
            }
            this.fftSlices.complexInverse(fArr2, 0, z);
            this.fftSlices.complexInverse(fArr2, this.slices * 2, z);
            int i75 = 0;
            while (true) {
                int i76 = this.slices;
                if (i75 < i76) {
                    int i77 = (this.sliceStride * i75) + i69;
                    int i78 = i75 * 2;
                    int i79 = (i76 * 2) + i78;
                    fArr[i77] = fArr2[i78];
                    fArr[i77 + 1] = fArr2[i78 + 1];
                    fArr[i77 + 2] = fArr2[i79];
                    fArr[i77 + 3] = fArr2[i79 + 1];
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
    private void cdft3db_sub(int r36, pl.edu.icm.jlargearrays.FloatLargeArray r37, boolean r38) {
        /*
            Method dump skipped, instructions count: 1274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.cdft3db_sub(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void cdft3db_sub(int i, float[][][] fArr, boolean z) {
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
        float[] fArr2 = new float[i4];
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
                            float[] fArr3 = fArr[i8][i6];
                            fArr2[i10] = fArr3[i7];
                            fArr2[i10 + 1] = fArr3[i7 + 1];
                            fArr2[i11] = fArr3[i7 + 2];
                            fArr2[i11 + 1] = fArr3[i7 + 3];
                            fArr2[i12] = fArr3[i7 + 4];
                            fArr2[i12 + 1] = fArr3[i7 + 5];
                            fArr2[i13] = fArr3[i7 + 6];
                            fArr2[i13 + 1] = fArr3[i7 + 7];
                            i8++;
                        }
                        this.fftSlices.complexForward(fArr2, 0);
                        this.fftSlices.complexForward(fArr2, this.slices * 2);
                        this.fftSlices.complexForward(fArr2, this.slices * 4);
                        this.fftSlices.complexForward(fArr2, this.slices * 6);
                        int i14 = 0;
                        while (true) {
                            int i15 = this.slices;
                            if (i14 < i15) {
                                int i16 = i14 * 2;
                                int i17 = (i15 * 2) + i16;
                                int i18 = (i15 * 2) + i17;
                                int i19 = (i15 * 2) + i18;
                                float[] fArr4 = fArr[i14][i6];
                                fArr4[i7] = fArr2[i16];
                                fArr4[i7 + 1] = fArr2[i16 + 1];
                                fArr4[i7 + 2] = fArr2[i17];
                                fArr4[i7 + 3] = fArr2[i17 + 1];
                                fArr4[i7 + 4] = fArr2[i18];
                                fArr4[i7 + 5] = fArr2[i18 + 1];
                                fArr4[i7 + 6] = fArr2[i19];
                                fArr4[i7 + 7] = fArr2[i19 + 1];
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
                            float[] fArr5 = fArr[i21][i20];
                            fArr2[i22] = fArr5[0];
                            fArr2[i22 + 1] = fArr5[1];
                        }
                        this.fftSlices.complexForward(fArr2, 0);
                        for (int i23 = 0; i23 < this.slices; i23++) {
                            int i24 = i23 * 2;
                            float[] fArr6 = fArr[i23][i20];
                            fArr6[0] = fArr2[i24];
                            fArr6[1] = fArr2[i24 + 1];
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
                    float[] fArr7 = fArr[i26][i25];
                    fArr2[i28] = fArr7[0];
                    fArr2[i28 + 1] = fArr7[1];
                    fArr2[i29] = fArr7[2];
                    fArr2[i29 + 1] = fArr7[3];
                    i26++;
                }
                this.fftSlices.complexForward(fArr2, 0);
                this.fftSlices.complexForward(fArr2, this.slices * 2);
                int i30 = 0;
                while (true) {
                    int i31 = this.slices;
                    if (i30 < i31) {
                        int i32 = i30 * 2;
                        int i33 = (i31 * 2) + i32;
                        float[] fArr8 = fArr[i30][i25];
                        fArr8[0] = fArr2[i32];
                        fArr8[1] = fArr2[i32 + 1];
                        fArr8[2] = fArr2[i33];
                        fArr8[3] = fArr2[i33 + 1];
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
                        float[] fArr9 = fArr[i36][i34];
                        fArr2[i38] = fArr9[i35];
                        fArr2[i38 + 1] = fArr9[i35 + 1];
                        fArr2[i39] = fArr9[i35 + 2];
                        fArr2[i39 + 1] = fArr9[i35 + 3];
                        fArr2[i40] = fArr9[i35 + 4];
                        fArr2[i40 + 1] = fArr9[i35 + 5];
                        fArr2[i41] = fArr9[i35 + 6];
                        fArr2[i41 + 1] = fArr9[i35 + 7];
                        i36++;
                    }
                    this.fftSlices.complexInverse(fArr2, 0, z);
                    this.fftSlices.complexInverse(fArr2, this.slices * 2, z);
                    this.fftSlices.complexInverse(fArr2, this.slices * 4, z);
                    this.fftSlices.complexInverse(fArr2, this.slices * 6, z);
                    int i42 = 0;
                    while (true) {
                        int i43 = this.slices;
                        if (i42 < i43) {
                            int i44 = i42 * 2;
                            int i45 = (i43 * 2) + i44;
                            int i46 = (i43 * 2) + i45;
                            int i47 = (i43 * 2) + i46;
                            float[] fArr10 = fArr[i42][i34];
                            fArr10[i35] = fArr2[i44];
                            fArr10[i35 + 1] = fArr2[i44 + 1];
                            fArr10[i35 + 2] = fArr2[i45];
                            fArr10[i35 + 3] = fArr2[i45 + 1];
                            fArr10[i35 + 4] = fArr2[i46];
                            fArr10[i35 + 5] = fArr2[i46 + 1];
                            fArr10[i35 + 6] = fArr2[i47];
                            fArr10[i35 + 7] = fArr2[i47 + 1];
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
                        float[] fArr11 = fArr[i49][i48];
                        fArr2[i50] = fArr11[0];
                        fArr2[i50 + 1] = fArr11[1];
                    }
                    this.fftSlices.complexInverse(fArr2, 0, z);
                    for (int i51 = 0; i51 < this.slices; i51++) {
                        int i52 = i51 * 2;
                        float[] fArr12 = fArr[i51][i48];
                        fArr12[0] = fArr2[i52];
                        fArr12[1] = fArr2[i52 + 1];
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
                float[] fArr13 = fArr[i54][i53];
                fArr2[i56] = fArr13[0];
                fArr2[i56 + 1] = fArr13[1];
                fArr2[i57] = fArr13[2];
                fArr2[i57 + 1] = fArr13[3];
                i54++;
            }
            this.fftSlices.complexInverse(fArr2, 0, z);
            this.fftSlices.complexInverse(fArr2, this.slices * 2, z);
            int i58 = 0;
            while (true) {
                int i59 = this.slices;
                if (i58 < i59) {
                    int i60 = i58 * 2;
                    int i61 = (i59 * 2) + i60;
                    float[] fArr14 = fArr[i58][i53];
                    fArr14[0] = fArr2[i60];
                    fArr14[1] = fArr2[i60 + 1];
                    fArr14[2] = fArr2[i61];
                    fArr14[3] = fArr2[i61 + 1];
                    i58++;
                }
            }
        }
    }

    private void xdft3da_subth1(final int i, final int i2, final float[] fArr, final boolean z) {
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
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.51
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < FloatFFT_3D.this.slices) {
                            int i11 = FloatFFT_3D.this.sliceStride * i10;
                            if (i == 0) {
                                for (int i12 = 0; i12 < FloatFFT_3D.this.rows; i12++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr, (FloatFFT_3D.this.rowStride * i12) + i11);
                                }
                            } else {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr, (FloatFFT_3D.this.rowStride * i13) + i11);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i14 = 0; i14 < FloatFFT_3D.this.columns; i14 += 8) {
                                    for (int i15 = 0; i15 < FloatFFT_3D.this.rows; i15++) {
                                        int i16 = (FloatFFT_3D.this.rowStride * i15) + i11 + i14;
                                        int i17 = i15 * 2;
                                        int i18 = (FloatFFT_3D.this.rows * 2) + i17;
                                        int i19 = (FloatFFT_3D.this.rows * 2) + i18;
                                        int i20 = (FloatFFT_3D.this.rows * 2) + i19;
                                        float[] fArr3 = fArr;
                                        fArr2[i17] = fArr3[i16];
                                        fArr2[i17 + 1] = fArr3[i16 + 1];
                                        fArr2[i18] = fArr3[i16 + 2];
                                        fArr2[i18 + 1] = fArr3[i16 + 3];
                                        fArr2[i19] = fArr3[i16 + 4];
                                        fArr2[i19 + 1] = fArr3[i16 + 5];
                                        fArr2[i20] = fArr3[i16 + 6];
                                        fArr2[i20 + 1] = fArr3[i16 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 6);
                                    for (int i21 = 0; i21 < FloatFFT_3D.this.rows; i21++) {
                                        int i22 = (FloatFFT_3D.this.rowStride * i21) + i11 + i14;
                                        int i23 = i21 * 2;
                                        int i24 = (FloatFFT_3D.this.rows * 2) + i23;
                                        int i25 = (FloatFFT_3D.this.rows * 2) + i24;
                                        int i26 = (FloatFFT_3D.this.rows * 2) + i25;
                                        float[] fArr4 = fArr;
                                        fArr4[i22] = fArr2[i23];
                                        fArr4[i22 + 1] = fArr2[i23 + 1];
                                        fArr4[i22 + 2] = fArr2[i24];
                                        fArr4[i22 + 3] = fArr2[i24 + 1];
                                        fArr4[i22 + 4] = fArr2[i25];
                                        fArr4[i22 + 5] = fArr2[i25 + 1];
                                        fArr4[i22 + 6] = fArr2[i26];
                                        fArr4[i22 + 7] = fArr2[i26 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i27 = 0; i27 < FloatFFT_3D.this.rows; i27++) {
                                    int i28 = (FloatFFT_3D.this.rowStride * i27) + i11;
                                    int i29 = i27 * 2;
                                    int i30 = (FloatFFT_3D.this.rows * 2) + i29;
                                    float[] fArr5 = fArr;
                                    fArr2[i29] = fArr5[i28];
                                    fArr2[i29 + 1] = fArr5[i28 + 1];
                                    fArr2[i30] = fArr5[i28 + 2];
                                    fArr2[i30 + 1] = fArr5[i28 + 3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                for (int i31 = 0; i31 < FloatFFT_3D.this.rows; i31++) {
                                    int i32 = (FloatFFT_3D.this.rowStride * i31) + i11;
                                    int i33 = i31 * 2;
                                    int i34 = (FloatFFT_3D.this.rows * 2) + i33;
                                    float[] fArr6 = fArr;
                                    fArr6[i32] = fArr2[i33];
                                    fArr6[i32 + 1] = fArr2[i33 + 1];
                                    fArr6[i32 + 2] = fArr2[i34];
                                    fArr6[i32 + 3] = fArr2[i34 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i35 = 0; i35 < FloatFFT_3D.this.rows; i35++) {
                                    int i36 = (FloatFFT_3D.this.rowStride * i35) + i11;
                                    int i37 = i35 * 2;
                                    float[] fArr7 = fArr;
                                    fArr2[i37] = fArr7[i36];
                                    fArr2[i37 + 1] = fArr7[i36 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                for (int i38 = 0; i38 < FloatFFT_3D.this.rows; i38++) {
                                    int i39 = (FloatFFT_3D.this.rowStride * i38) + i11;
                                    int i40 = i38 * 2;
                                    float[] fArr8 = fArr;
                                    fArr8[i39] = fArr2[i40];
                                    fArr8[i39 + 1] = fArr2[i40 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i41 = i9;
                    while (i41 < FloatFFT_3D.this.slices) {
                        int i42 = FloatFFT_3D.this.sliceStride * i41;
                        if (i == 0) {
                            for (int i43 = 0; i43 < FloatFFT_3D.this.rows; i43++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr, (FloatFFT_3D.this.rowStride * i43) + i42, z);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i44 = 0; i44 < FloatFFT_3D.this.columns; i44 += 8) {
                                for (int i45 = 0; i45 < FloatFFT_3D.this.rows; i45++) {
                                    int i46 = (FloatFFT_3D.this.rowStride * i45) + i42 + i44;
                                    int i47 = i45 * 2;
                                    int i48 = (FloatFFT_3D.this.rows * 2) + i47;
                                    int i49 = (FloatFFT_3D.this.rows * 2) + i48;
                                    int i50 = (FloatFFT_3D.this.rows * 2) + i49;
                                    float[] fArr9 = fArr;
                                    fArr2[i47] = fArr9[i46];
                                    fArr2[i47 + 1] = fArr9[i46 + 1];
                                    fArr2[i48] = fArr9[i46 + 2];
                                    fArr2[i48 + 1] = fArr9[i46 + 3];
                                    fArr2[i49] = fArr9[i46 + 4];
                                    fArr2[i49 + 1] = fArr9[i46 + 5];
                                    fArr2[i50] = fArr9[i46 + 6];
                                    fArr2[i50 + 1] = fArr9[i46 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 4, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 6, z);
                                for (int i51 = 0; i51 < FloatFFT_3D.this.rows; i51++) {
                                    int i52 = (FloatFFT_3D.this.rowStride * i51) + i42 + i44;
                                    int i53 = i51 * 2;
                                    int i54 = (FloatFFT_3D.this.rows * 2) + i53;
                                    int i55 = (FloatFFT_3D.this.rows * 2) + i54;
                                    int i56 = (FloatFFT_3D.this.rows * 2) + i55;
                                    float[] fArr10 = fArr;
                                    fArr10[i52] = fArr2[i53];
                                    fArr10[i52 + 1] = fArr2[i53 + 1];
                                    fArr10[i52 + 2] = fArr2[i54];
                                    fArr10[i52 + 3] = fArr2[i54 + 1];
                                    fArr10[i52 + 4] = fArr2[i55];
                                    fArr10[i52 + 5] = fArr2[i55 + 1];
                                    fArr10[i52 + 6] = fArr2[i56];
                                    fArr10[i52 + 7] = fArr2[i56 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i57 = 0; i57 < FloatFFT_3D.this.rows; i57++) {
                                int i58 = (FloatFFT_3D.this.rowStride * i57) + i42;
                                int i59 = i57 * 2;
                                int i60 = (FloatFFT_3D.this.rows * 2) + i59;
                                float[] fArr11 = fArr;
                                fArr2[i59] = fArr11[i58];
                                fArr2[i59 + 1] = fArr11[i58 + 1];
                                fArr2[i60] = fArr11[i58 + 2];
                                fArr2[i60 + 1] = fArr11[i58 + 3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                            for (int i61 = 0; i61 < FloatFFT_3D.this.rows; i61++) {
                                int i62 = (FloatFFT_3D.this.rowStride * i61) + i42;
                                int i63 = i61 * 2;
                                int i64 = (FloatFFT_3D.this.rows * 2) + i63;
                                float[] fArr12 = fArr;
                                fArr12[i62] = fArr2[i63];
                                fArr12[i62 + 1] = fArr2[i63 + 1];
                                fArr12[i62 + 2] = fArr2[i64];
                                fArr12[i62 + 3] = fArr2[i64 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i65 = 0; i65 < FloatFFT_3D.this.rows; i65++) {
                                int i66 = (FloatFFT_3D.this.rowStride * i65) + i42;
                                int i67 = i65 * 2;
                                float[] fArr13 = fArr;
                                fArr2[i67] = fArr13[i66];
                                fArr2[i67 + 1] = fArr13[i66 + 1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            for (int i68 = 0; i68 < FloatFFT_3D.this.rows; i68++) {
                                int i69 = (FloatFFT_3D.this.rowStride * i68) + i42;
                                int i70 = i68 * 2;
                                float[] fArr14 = fArr;
                                fArr14[i69] = fArr2[i70];
                                fArr14[i69 + 1] = fArr2[i70 + 1];
                            }
                        }
                        if (i != 0) {
                            for (int i71 = 0; i71 < FloatFFT_3D.this.rows; i71++) {
                                FloatFFT_3D.this.fftColumns.realInverse(fArr, (FloatFFT_3D.this.rowStride * i71) + i42, z);
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
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[LOOP:0: B:13:0x0034->B:14:0x0036, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void xdft3da_subth1(final long r21, final int r23, final pl.edu.icm.jlargearrays.FloatLargeArray r24, final boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r14 = org.jtransforms.fft.FloatFFT_3D.class
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
            org.jtransforms.fft.FloatFFT_3D$52 r18 = new org.jtransforms.fft.FloatFFT_3D$52
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_subth1(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_subth2(final int i, final int i2, final float[] fArr, final boolean z) {
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
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.53
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < FloatFFT_3D.this.slices) {
                            int i11 = FloatFFT_3D.this.sliceStride * i10;
                            if (i == 0) {
                                for (int i12 = 0; i12 < FloatFFT_3D.this.rows; i12++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr, (FloatFFT_3D.this.rowStride * i12) + i11);
                                }
                            } else {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.rows; i13++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr, (FloatFFT_3D.this.rowStride * i13) + i11);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i14 = 0; i14 < FloatFFT_3D.this.columns; i14 += 8) {
                                    for (int i15 = 0; i15 < FloatFFT_3D.this.rows; i15++) {
                                        int i16 = (FloatFFT_3D.this.rowStride * i15) + i11 + i14;
                                        int i17 = i15 * 2;
                                        int i18 = (FloatFFT_3D.this.rows * 2) + i17;
                                        int i19 = (FloatFFT_3D.this.rows * 2) + i18;
                                        int i20 = (FloatFFT_3D.this.rows * 2) + i19;
                                        float[] fArr3 = fArr;
                                        fArr2[i17] = fArr3[i16];
                                        fArr2[i17 + 1] = fArr3[i16 + 1];
                                        fArr2[i18] = fArr3[i16 + 2];
                                        fArr2[i18 + 1] = fArr3[i16 + 3];
                                        fArr2[i19] = fArr3[i16 + 4];
                                        fArr2[i19 + 1] = fArr3[i16 + 5];
                                        fArr2[i20] = fArr3[i16 + 6];
                                        fArr2[i20 + 1] = fArr3[i16 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 6);
                                    for (int i21 = 0; i21 < FloatFFT_3D.this.rows; i21++) {
                                        int i22 = (FloatFFT_3D.this.rowStride * i21) + i11 + i14;
                                        int i23 = i21 * 2;
                                        int i24 = (FloatFFT_3D.this.rows * 2) + i23;
                                        int i25 = (FloatFFT_3D.this.rows * 2) + i24;
                                        int i26 = (FloatFFT_3D.this.rows * 2) + i25;
                                        float[] fArr4 = fArr;
                                        fArr4[i22] = fArr2[i23];
                                        fArr4[i22 + 1] = fArr2[i23 + 1];
                                        fArr4[i22 + 2] = fArr2[i24];
                                        fArr4[i22 + 3] = fArr2[i24 + 1];
                                        fArr4[i22 + 4] = fArr2[i25];
                                        fArr4[i22 + 5] = fArr2[i25 + 1];
                                        fArr4[i22 + 6] = fArr2[i26];
                                        fArr4[i22 + 7] = fArr2[i26 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i27 = 0; i27 < FloatFFT_3D.this.rows; i27++) {
                                    int i28 = (FloatFFT_3D.this.rowStride * i27) + i11;
                                    int i29 = i27 * 2;
                                    int i30 = (FloatFFT_3D.this.rows * 2) + i29;
                                    float[] fArr5 = fArr;
                                    fArr2[i29] = fArr5[i28];
                                    fArr2[i29 + 1] = fArr5[i28 + 1];
                                    fArr2[i30] = fArr5[i28 + 2];
                                    fArr2[i30 + 1] = fArr5[i28 + 3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                for (int i31 = 0; i31 < FloatFFT_3D.this.rows; i31++) {
                                    int i32 = (FloatFFT_3D.this.rowStride * i31) + i11;
                                    int i33 = i31 * 2;
                                    int i34 = (FloatFFT_3D.this.rows * 2) + i33;
                                    float[] fArr6 = fArr;
                                    fArr6[i32] = fArr2[i33];
                                    fArr6[i32 + 1] = fArr2[i33 + 1];
                                    fArr6[i32 + 2] = fArr2[i34];
                                    fArr6[i32 + 3] = fArr2[i34 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i35 = 0; i35 < FloatFFT_3D.this.rows; i35++) {
                                    int i36 = (FloatFFT_3D.this.rowStride * i35) + i11;
                                    int i37 = i35 * 2;
                                    float[] fArr7 = fArr;
                                    fArr2[i37] = fArr7[i36];
                                    fArr2[i37 + 1] = fArr7[i36 + 1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                for (int i38 = 0; i38 < FloatFFT_3D.this.rows; i38++) {
                                    int i39 = (FloatFFT_3D.this.rowStride * i38) + i11;
                                    int i40 = i38 * 2;
                                    float[] fArr8 = fArr;
                                    fArr8[i39] = fArr2[i40];
                                    fArr8[i39 + 1] = fArr2[i40 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i41 = i9;
                    while (i41 < FloatFFT_3D.this.slices) {
                        int i42 = FloatFFT_3D.this.sliceStride * i41;
                        if (i == 0) {
                            for (int i43 = 0; i43 < FloatFFT_3D.this.rows; i43++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr, (FloatFFT_3D.this.rowStride * i43) + i42, z);
                            }
                        } else {
                            for (int i44 = 0; i44 < FloatFFT_3D.this.rows; i44++) {
                                FloatFFT_3D.this.fftColumns.realInverse2(fArr, (FloatFFT_3D.this.rowStride * i44) + i42, z);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i45 = 0; i45 < FloatFFT_3D.this.columns; i45 += 8) {
                                for (int i46 = 0; i46 < FloatFFT_3D.this.rows; i46++) {
                                    int i47 = (FloatFFT_3D.this.rowStride * i46) + i42 + i45;
                                    int i48 = i46 * 2;
                                    int i49 = (FloatFFT_3D.this.rows * 2) + i48;
                                    int i50 = (FloatFFT_3D.this.rows * 2) + i49;
                                    int i51 = (FloatFFT_3D.this.rows * 2) + i50;
                                    float[] fArr9 = fArr;
                                    fArr2[i48] = fArr9[i47];
                                    fArr2[i48 + 1] = fArr9[i47 + 1];
                                    fArr2[i49] = fArr9[i47 + 2];
                                    fArr2[i49 + 1] = fArr9[i47 + 3];
                                    fArr2[i50] = fArr9[i47 + 4];
                                    fArr2[i50 + 1] = fArr9[i47 + 5];
                                    fArr2[i51] = fArr9[i47 + 6];
                                    fArr2[i51 + 1] = fArr9[i47 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 4, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 6, z);
                                for (int i52 = 0; i52 < FloatFFT_3D.this.rows; i52++) {
                                    int i53 = (FloatFFT_3D.this.rowStride * i52) + i42 + i45;
                                    int i54 = i52 * 2;
                                    int i55 = (FloatFFT_3D.this.rows * 2) + i54;
                                    int i56 = (FloatFFT_3D.this.rows * 2) + i55;
                                    int i57 = (FloatFFT_3D.this.rows * 2) + i56;
                                    float[] fArr10 = fArr;
                                    fArr10[i53] = fArr2[i54];
                                    fArr10[i53 + 1] = fArr2[i54 + 1];
                                    fArr10[i53 + 2] = fArr2[i55];
                                    fArr10[i53 + 3] = fArr2[i55 + 1];
                                    fArr10[i53 + 4] = fArr2[i56];
                                    fArr10[i53 + 5] = fArr2[i56 + 1];
                                    fArr10[i53 + 6] = fArr2[i57];
                                    fArr10[i53 + 7] = fArr2[i57 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i58 = 0; i58 < FloatFFT_3D.this.rows; i58++) {
                                int i59 = (FloatFFT_3D.this.rowStride * i58) + i42;
                                int i60 = i58 * 2;
                                int i61 = (FloatFFT_3D.this.rows * 2) + i60;
                                float[] fArr11 = fArr;
                                fArr2[i60] = fArr11[i59];
                                fArr2[i60 + 1] = fArr11[i59 + 1];
                                fArr2[i61] = fArr11[i59 + 2];
                                fArr2[i61 + 1] = fArr11[i59 + 3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                            for (int i62 = 0; i62 < FloatFFT_3D.this.rows; i62++) {
                                int i63 = (FloatFFT_3D.this.rowStride * i62) + i42;
                                int i64 = i62 * 2;
                                int i65 = (FloatFFT_3D.this.rows * 2) + i64;
                                float[] fArr12 = fArr;
                                fArr12[i63] = fArr2[i64];
                                fArr12[i63 + 1] = fArr2[i64 + 1];
                                fArr12[i63 + 2] = fArr2[i65];
                                fArr12[i63 + 3] = fArr2[i65 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i66 = 0; i66 < FloatFFT_3D.this.rows; i66++) {
                                int i67 = (FloatFFT_3D.this.rowStride * i66) + i42;
                                int i68 = i66 * 2;
                                float[] fArr13 = fArr;
                                fArr2[i68] = fArr13[i67];
                                fArr2[i68 + 1] = fArr13[i67 + 1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            for (int i69 = 0; i69 < FloatFFT_3D.this.rows; i69++) {
                                int i70 = (FloatFFT_3D.this.rowStride * i69) + i42;
                                int i71 = i69 * 2;
                                float[] fArr14 = fArr;
                                fArr14[i70] = fArr2[i71];
                                fArr14[i70 + 1] = fArr2[i71 + 1];
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
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[LOOP:0: B:13:0x0034->B:14:0x0036, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void xdft3da_subth2(final long r21, final int r23, final pl.edu.icm.jlargearrays.FloatLargeArray r24, final boolean r25) {
        /*
            r20 = this;
            r13 = r20
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r14 = org.jtransforms.fft.FloatFFT_3D.class
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
            org.jtransforms.fft.FloatFFT_3D$54 r18 = new org.jtransforms.fft.FloatFFT_3D$54
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.xdft3da_subth2(long, int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void xdft3da_subth1(final int i, final int i2, final float[][][] fArr, final boolean z) {
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
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.55
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < FloatFFT_3D.this.slices) {
                            if (i == 0) {
                                for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr[i10][i11]);
                                }
                            } else {
                                for (int i12 = 0; i12 < FloatFFT_3D.this.rows; i12++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr[i10][i12], 0);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.columns; i13 += 8) {
                                    for (int i14 = 0; i14 < FloatFFT_3D.this.rows; i14++) {
                                        int i15 = i14 * 2;
                                        int i16 = (FloatFFT_3D.this.rows * 2) + i15;
                                        int i17 = (FloatFFT_3D.this.rows * 2) + i16;
                                        int i18 = (FloatFFT_3D.this.rows * 2) + i17;
                                        float[] fArr3 = fArr[i10][i14];
                                        fArr2[i15] = fArr3[i13];
                                        fArr2[i15 + 1] = fArr3[i13 + 1];
                                        fArr2[i16] = fArr3[i13 + 2];
                                        fArr2[i16 + 1] = fArr3[i13 + 3];
                                        fArr2[i17] = fArr3[i13 + 4];
                                        fArr2[i17 + 1] = fArr3[i13 + 5];
                                        fArr2[i18] = fArr3[i13 + 6];
                                        fArr2[i18 + 1] = fArr3[i13 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 6);
                                    for (int i19 = 0; i19 < FloatFFT_3D.this.rows; i19++) {
                                        int i20 = i19 * 2;
                                        int i21 = (FloatFFT_3D.this.rows * 2) + i20;
                                        int i22 = (FloatFFT_3D.this.rows * 2) + i21;
                                        int i23 = (FloatFFT_3D.this.rows * 2) + i22;
                                        float[] fArr4 = fArr[i10][i19];
                                        fArr4[i13] = fArr2[i20];
                                        fArr4[i13 + 1] = fArr2[i20 + 1];
                                        fArr4[i13 + 2] = fArr2[i21];
                                        fArr4[i13 + 3] = fArr2[i21 + 1];
                                        fArr4[i13 + 4] = fArr2[i22];
                                        fArr4[i13 + 5] = fArr2[i22 + 1];
                                        fArr4[i13 + 6] = fArr2[i23];
                                        fArr4[i13 + 7] = fArr2[i23 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i24 = 0; i24 < FloatFFT_3D.this.rows; i24++) {
                                    int i25 = i24 * 2;
                                    int i26 = (FloatFFT_3D.this.rows * 2) + i25;
                                    float[] fArr5 = fArr[i10][i24];
                                    fArr2[i25] = fArr5[0];
                                    fArr2[i25 + 1] = fArr5[1];
                                    fArr2[i26] = fArr5[2];
                                    fArr2[i26 + 1] = fArr5[3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                for (int i27 = 0; i27 < FloatFFT_3D.this.rows; i27++) {
                                    int i28 = i27 * 2;
                                    int i29 = (FloatFFT_3D.this.rows * 2) + i28;
                                    float[] fArr6 = fArr[i10][i27];
                                    fArr6[0] = fArr2[i28];
                                    fArr6[1] = fArr2[i28 + 1];
                                    fArr6[2] = fArr2[i29];
                                    fArr6[3] = fArr2[i29 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i30 = 0; i30 < FloatFFT_3D.this.rows; i30++) {
                                    int i31 = i30 * 2;
                                    float[] fArr7 = fArr[i10][i30];
                                    fArr2[i31] = fArr7[0];
                                    fArr2[i31 + 1] = fArr7[1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                for (int i32 = 0; i32 < FloatFFT_3D.this.rows; i32++) {
                                    int i33 = i32 * 2;
                                    float[] fArr8 = fArr[i10][i32];
                                    fArr8[0] = fArr2[i33];
                                    fArr8[1] = fArr2[i33 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i34 = i9;
                    while (i34 < FloatFFT_3D.this.slices) {
                        if (i == 0) {
                            for (int i35 = 0; i35 < FloatFFT_3D.this.rows; i35++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr[i34][i35], z);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i36 = 0; i36 < FloatFFT_3D.this.columns; i36 += 8) {
                                for (int i37 = 0; i37 < FloatFFT_3D.this.rows; i37++) {
                                    int i38 = i37 * 2;
                                    int i39 = (FloatFFT_3D.this.rows * 2) + i38;
                                    int i40 = (FloatFFT_3D.this.rows * 2) + i39;
                                    int i41 = (FloatFFT_3D.this.rows * 2) + i40;
                                    float[] fArr9 = fArr[i34][i37];
                                    fArr2[i38] = fArr9[i36];
                                    fArr2[i38 + 1] = fArr9[i36 + 1];
                                    fArr2[i39] = fArr9[i36 + 2];
                                    fArr2[i39 + 1] = fArr9[i36 + 3];
                                    fArr2[i40] = fArr9[i36 + 4];
                                    fArr2[i40 + 1] = fArr9[i36 + 5];
                                    fArr2[i41] = fArr9[i36 + 6];
                                    fArr2[i41 + 1] = fArr9[i36 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 4, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 6, z);
                                for (int i42 = 0; i42 < FloatFFT_3D.this.rows; i42++) {
                                    int i43 = i42 * 2;
                                    int i44 = (FloatFFT_3D.this.rows * 2) + i43;
                                    int i45 = (FloatFFT_3D.this.rows * 2) + i44;
                                    int i46 = (FloatFFT_3D.this.rows * 2) + i45;
                                    float[] fArr10 = fArr[i34][i42];
                                    fArr10[i36] = fArr2[i43];
                                    fArr10[i36 + 1] = fArr2[i43 + 1];
                                    fArr10[i36 + 2] = fArr2[i44];
                                    fArr10[i36 + 3] = fArr2[i44 + 1];
                                    fArr10[i36 + 4] = fArr2[i45];
                                    fArr10[i36 + 5] = fArr2[i45 + 1];
                                    fArr10[i36 + 6] = fArr2[i46];
                                    fArr10[i36 + 7] = fArr2[i46 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i47 = 0; i47 < FloatFFT_3D.this.rows; i47++) {
                                int i48 = i47 * 2;
                                int i49 = (FloatFFT_3D.this.rows * 2) + i48;
                                float[] fArr11 = fArr[i34][i47];
                                fArr2[i48] = fArr11[0];
                                fArr2[i48 + 1] = fArr11[1];
                                fArr2[i49] = fArr11[2];
                                fArr2[i49 + 1] = fArr11[3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                            for (int i50 = 0; i50 < FloatFFT_3D.this.rows; i50++) {
                                int i51 = i50 * 2;
                                int i52 = (FloatFFT_3D.this.rows * 2) + i51;
                                float[] fArr12 = fArr[i34][i50];
                                fArr12[0] = fArr2[i51];
                                fArr12[1] = fArr2[i51 + 1];
                                fArr12[2] = fArr2[i52];
                                fArr12[3] = fArr2[i52 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i53 = 0; i53 < FloatFFT_3D.this.rows; i53++) {
                                int i54 = i53 * 2;
                                float[] fArr13 = fArr[i34][i53];
                                fArr2[i54] = fArr13[0];
                                fArr2[i54 + 1] = fArr13[1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            for (int i55 = 0; i55 < FloatFFT_3D.this.rows; i55++) {
                                int i56 = i55 * 2;
                                float[] fArr14 = fArr[i34][i55];
                                fArr14[0] = fArr2[i56];
                                fArr14[1] = fArr2[i56 + 1];
                            }
                        }
                        if (i != 0) {
                            for (int i57 = 0; i57 < FloatFFT_3D.this.rows; i57++) {
                                FloatFFT_3D.this.fftColumns.realInverse(fArr[i34][i57], z);
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
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void xdft3da_subth2(final int i, final int i2, final float[][][] fArr, final boolean z) {
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
            futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.56
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i7];
                    if (i2 == -1) {
                        int i10 = i9;
                        while (i10 < FloatFFT_3D.this.slices) {
                            if (i == 0) {
                                for (int i11 = 0; i11 < FloatFFT_3D.this.rows; i11++) {
                                    FloatFFT_3D.this.fftColumns.complexForward(fArr[i10][i11]);
                                }
                            } else {
                                for (int i12 = 0; i12 < FloatFFT_3D.this.rows; i12++) {
                                    FloatFFT_3D.this.fftColumns.realForward(fArr[i10][i12]);
                                }
                            }
                            if (FloatFFT_3D.this.columns > 4) {
                                for (int i13 = 0; i13 < FloatFFT_3D.this.columns; i13 += 8) {
                                    for (int i14 = 0; i14 < FloatFFT_3D.this.rows; i14++) {
                                        int i15 = i14 * 2;
                                        int i16 = (FloatFFT_3D.this.rows * 2) + i15;
                                        int i17 = (FloatFFT_3D.this.rows * 2) + i16;
                                        int i18 = (FloatFFT_3D.this.rows * 2) + i17;
                                        float[] fArr3 = fArr[i10][i14];
                                        fArr2[i15] = fArr3[i13];
                                        fArr2[i15 + 1] = fArr3[i13 + 1];
                                        fArr2[i16] = fArr3[i13 + 2];
                                        fArr2[i16 + 1] = fArr3[i13 + 3];
                                        fArr2[i17] = fArr3[i13 + 4];
                                        fArr2[i17 + 1] = fArr3[i13 + 5];
                                        fArr2[i18] = fArr3[i13 + 6];
                                        fArr2[i18 + 1] = fArr3[i13 + 7];
                                    }
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 4);
                                    FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 6);
                                    for (int i19 = 0; i19 < FloatFFT_3D.this.rows; i19++) {
                                        int i20 = i19 * 2;
                                        int i21 = (FloatFFT_3D.this.rows * 2) + i20;
                                        int i22 = (FloatFFT_3D.this.rows * 2) + i21;
                                        int i23 = (FloatFFT_3D.this.rows * 2) + i22;
                                        float[] fArr4 = fArr[i10][i19];
                                        fArr4[i13] = fArr2[i20];
                                        fArr4[i13 + 1] = fArr2[i20 + 1];
                                        fArr4[i13 + 2] = fArr2[i21];
                                        fArr4[i13 + 3] = fArr2[i21 + 1];
                                        fArr4[i13 + 4] = fArr2[i22];
                                        fArr4[i13 + 5] = fArr2[i22 + 1];
                                        fArr4[i13 + 6] = fArr2[i23];
                                        fArr4[i13 + 7] = fArr2[i23 + 1];
                                    }
                                }
                            } else if (FloatFFT_3D.this.columns == 4) {
                                for (int i24 = 0; i24 < FloatFFT_3D.this.rows; i24++) {
                                    int i25 = i24 * 2;
                                    int i26 = (FloatFFT_3D.this.rows * 2) + i25;
                                    float[] fArr5 = fArr[i10][i24];
                                    fArr2[i25] = fArr5[0];
                                    fArr2[i25 + 1] = fArr5[1];
                                    fArr2[i26] = fArr5[2];
                                    fArr2[i26 + 1] = fArr5[3];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, FloatFFT_3D.this.rows * 2);
                                for (int i27 = 0; i27 < FloatFFT_3D.this.rows; i27++) {
                                    int i28 = i27 * 2;
                                    int i29 = (FloatFFT_3D.this.rows * 2) + i28;
                                    float[] fArr6 = fArr[i10][i27];
                                    fArr6[0] = fArr2[i28];
                                    fArr6[1] = fArr2[i28 + 1];
                                    fArr6[2] = fArr2[i29];
                                    fArr6[3] = fArr2[i29 + 1];
                                }
                            } else if (FloatFFT_3D.this.columns == 2) {
                                for (int i30 = 0; i30 < FloatFFT_3D.this.rows; i30++) {
                                    int i31 = i30 * 2;
                                    float[] fArr7 = fArr[i10][i30];
                                    fArr2[i31] = fArr7[0];
                                    fArr2[i31 + 1] = fArr7[1];
                                }
                                FloatFFT_3D.this.fftRows.complexForward(fArr2, 0);
                                for (int i32 = 0; i32 < FloatFFT_3D.this.rows; i32++) {
                                    int i33 = i32 * 2;
                                    float[] fArr8 = fArr[i10][i32];
                                    fArr8[0] = fArr2[i33];
                                    fArr8[1] = fArr2[i33 + 1];
                                }
                            }
                            i10 += iMin;
                        }
                        return;
                    }
                    int i34 = i9;
                    while (i34 < FloatFFT_3D.this.slices) {
                        if (i == 0) {
                            for (int i35 = 0; i35 < FloatFFT_3D.this.rows; i35++) {
                                FloatFFT_3D.this.fftColumns.complexInverse(fArr[i34][i35], z);
                            }
                        } else {
                            for (int i36 = 0; i36 < FloatFFT_3D.this.rows; i36++) {
                                FloatFFT_3D.this.fftColumns.realInverse2(fArr[i34][i36], 0, z);
                            }
                        }
                        if (FloatFFT_3D.this.columns > 4) {
                            for (int i37 = 0; i37 < FloatFFT_3D.this.columns; i37 += 8) {
                                for (int i38 = 0; i38 < FloatFFT_3D.this.rows; i38++) {
                                    int i39 = i38 * 2;
                                    int i40 = (FloatFFT_3D.this.rows * 2) + i39;
                                    int i41 = (FloatFFT_3D.this.rows * 2) + i40;
                                    int i42 = (FloatFFT_3D.this.rows * 2) + i41;
                                    float[] fArr9 = fArr[i34][i38];
                                    fArr2[i39] = fArr9[i37];
                                    fArr2[i39 + 1] = fArr9[i37 + 1];
                                    fArr2[i40] = fArr9[i37 + 2];
                                    fArr2[i40 + 1] = fArr9[i37 + 3];
                                    fArr2[i41] = fArr9[i37 + 4];
                                    fArr2[i41 + 1] = fArr9[i37 + 5];
                                    fArr2[i42] = fArr9[i37 + 6];
                                    fArr2[i42 + 1] = fArr9[i37 + 7];
                                }
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 4, z);
                                FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 6, z);
                                for (int i43 = 0; i43 < FloatFFT_3D.this.rows; i43++) {
                                    int i44 = i43 * 2;
                                    int i45 = (FloatFFT_3D.this.rows * 2) + i44;
                                    int i46 = (FloatFFT_3D.this.rows * 2) + i45;
                                    int i47 = (FloatFFT_3D.this.rows * 2) + i46;
                                    float[] fArr10 = fArr[i34][i43];
                                    fArr10[i37] = fArr2[i44];
                                    fArr10[i37 + 1] = fArr2[i44 + 1];
                                    fArr10[i37 + 2] = fArr2[i45];
                                    fArr10[i37 + 3] = fArr2[i45 + 1];
                                    fArr10[i37 + 4] = fArr2[i46];
                                    fArr10[i37 + 5] = fArr2[i46 + 1];
                                    fArr10[i37 + 6] = fArr2[i47];
                                    fArr10[i37 + 7] = fArr2[i47 + 1];
                                }
                            }
                        } else if (FloatFFT_3D.this.columns == 4) {
                            for (int i48 = 0; i48 < FloatFFT_3D.this.rows; i48++) {
                                int i49 = i48 * 2;
                                int i50 = (FloatFFT_3D.this.rows * 2) + i49;
                                float[] fArr11 = fArr[i34][i48];
                                fArr2[i49] = fArr11[0];
                                fArr2[i49 + 1] = fArr11[1];
                                fArr2[i50] = fArr11[2];
                                fArr2[i50 + 1] = fArr11[3];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, FloatFFT_3D.this.rows * 2, z);
                            for (int i51 = 0; i51 < FloatFFT_3D.this.rows; i51++) {
                                int i52 = i51 * 2;
                                int i53 = (FloatFFT_3D.this.rows * 2) + i52;
                                float[] fArr12 = fArr[i34][i51];
                                fArr12[0] = fArr2[i52];
                                fArr12[1] = fArr2[i52 + 1];
                                fArr12[2] = fArr2[i53];
                                fArr12[3] = fArr2[i53 + 1];
                            }
                        } else if (FloatFFT_3D.this.columns == 2) {
                            for (int i54 = 0; i54 < FloatFFT_3D.this.rows; i54++) {
                                int i55 = i54 * 2;
                                float[] fArr13 = fArr[i34][i54];
                                fArr2[i55] = fArr13[0];
                                fArr2[i55 + 1] = fArr13[1];
                            }
                            FloatFFT_3D.this.fftRows.complexInverse(fArr2, 0, z);
                            for (int i56 = 0; i56 < FloatFFT_3D.this.rows; i56++) {
                                int i57 = i56 * 2;
                                float[] fArr14 = fArr[i34][i56];
                                fArr14[0] = fArr2[i57];
                                fArr14[1] = fArr2[i57 + 1];
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
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void cdft3db_subth(final int i, final float[] fArr, final boolean z) {
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
            futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.57
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i6];
                    if (i == -1) {
                        if (FloatFFT_3D.this.columns > 4) {
                            int i9 = i8;
                            while (i9 < FloatFFT_3D.this.rows) {
                                int i10 = FloatFFT_3D.this.rowStride * i9;
                                for (int i11 = 0; i11 < FloatFFT_3D.this.columns; i11 += 8) {
                                    for (int i12 = 0; i12 < FloatFFT_3D.this.slices; i12++) {
                                        int i13 = (FloatFFT_3D.this.sliceStride * i12) + i10 + i11;
                                        int i14 = i12 * 2;
                                        int i15 = (FloatFFT_3D.this.slices * 2) + i14;
                                        int i16 = (FloatFFT_3D.this.slices * 2) + i15;
                                        int i17 = (FloatFFT_3D.this.slices * 2) + i16;
                                        float[] fArr3 = fArr;
                                        fArr2[i14] = fArr3[i13];
                                        fArr2[i14 + 1] = fArr3[i13 + 1];
                                        fArr2[i15] = fArr3[i13 + 2];
                                        fArr2[i15 + 1] = fArr3[i13 + 3];
                                        fArr2[i16] = fArr3[i13 + 4];
                                        fArr2[i16 + 1] = fArr3[i13 + 5];
                                        fArr2[i17] = fArr3[i13 + 6];
                                        fArr2[i17 + 1] = fArr3[i13 + 7];
                                    }
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, 0);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 2);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 4);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 6);
                                    for (int i18 = 0; i18 < FloatFFT_3D.this.slices; i18++) {
                                        int i19 = (FloatFFT_3D.this.sliceStride * i18) + i10 + i11;
                                        int i20 = i18 * 2;
                                        int i21 = (FloatFFT_3D.this.slices * 2) + i20;
                                        int i22 = (FloatFFT_3D.this.slices * 2) + i21;
                                        int i23 = (FloatFFT_3D.this.slices * 2) + i22;
                                        float[] fArr4 = fArr;
                                        fArr4[i19] = fArr2[i20];
                                        fArr4[i19 + 1] = fArr2[i20 + 1];
                                        fArr4[i19 + 2] = fArr2[i21];
                                        fArr4[i19 + 3] = fArr2[i21 + 1];
                                        fArr4[i19 + 4] = fArr2[i22];
                                        fArr4[i19 + 5] = fArr2[i22 + 1];
                                        fArr4[i19 + 6] = fArr2[i23];
                                        fArr4[i19 + 7] = fArr2[i23 + 1];
                                    }
                                }
                                i9 += iMin;
                            }
                            return;
                        }
                        if (FloatFFT_3D.this.columns == 4) {
                            int i24 = i8;
                            while (i24 < FloatFFT_3D.this.rows) {
                                int i25 = FloatFFT_3D.this.rowStride * i24;
                                for (int i26 = 0; i26 < FloatFFT_3D.this.slices; i26++) {
                                    int i27 = (FloatFFT_3D.this.sliceStride * i26) + i25;
                                    int i28 = i26 * 2;
                                    int i29 = (FloatFFT_3D.this.slices * 2) + i28;
                                    float[] fArr5 = fArr;
                                    fArr2[i28] = fArr5[i27];
                                    fArr2[i28 + 1] = fArr5[i27 + 1];
                                    fArr2[i29] = fArr5[i27 + 2];
                                    fArr2[i29 + 1] = fArr5[i27 + 3];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2, 0);
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 2);
                                for (int i30 = 0; i30 < FloatFFT_3D.this.slices; i30++) {
                                    int i31 = (FloatFFT_3D.this.sliceStride * i30) + i25;
                                    int i32 = i30 * 2;
                                    int i33 = (FloatFFT_3D.this.slices * 2) + i32;
                                    float[] fArr6 = fArr;
                                    fArr6[i31] = fArr2[i32];
                                    fArr6[i31 + 1] = fArr2[i32 + 1];
                                    fArr6[i31 + 2] = fArr2[i33];
                                    fArr6[i31 + 3] = fArr2[i33 + 1];
                                }
                                i24 += iMin;
                            }
                            return;
                        }
                        if (FloatFFT_3D.this.columns == 2) {
                            int i34 = i8;
                            while (i34 < FloatFFT_3D.this.rows) {
                                int i35 = FloatFFT_3D.this.rowStride * i34;
                                for (int i36 = 0; i36 < FloatFFT_3D.this.slices; i36++) {
                                    int i37 = (FloatFFT_3D.this.sliceStride * i36) + i35;
                                    int i38 = i36 * 2;
                                    float[] fArr7 = fArr;
                                    fArr2[i38] = fArr7[i37];
                                    fArr2[i38 + 1] = fArr7[i37 + 1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2, 0);
                                for (int i39 = 0; i39 < FloatFFT_3D.this.slices; i39++) {
                                    int i40 = (FloatFFT_3D.this.sliceStride * i39) + i35;
                                    int i41 = i39 * 2;
                                    float[] fArr8 = fArr;
                                    fArr8[i40] = fArr2[i41];
                                    fArr8[i40 + 1] = fArr2[i41 + 1];
                                }
                                i34 += iMin;
                            }
                            return;
                        }
                        return;
                    }
                    if (FloatFFT_3D.this.columns > 4) {
                        int i42 = i8;
                        while (i42 < FloatFFT_3D.this.rows) {
                            int i43 = FloatFFT_3D.this.rowStride * i42;
                            for (int i44 = 0; i44 < FloatFFT_3D.this.columns; i44 += 8) {
                                for (int i45 = 0; i45 < FloatFFT_3D.this.slices; i45++) {
                                    int i46 = (FloatFFT_3D.this.sliceStride * i45) + i43 + i44;
                                    int i47 = i45 * 2;
                                    int i48 = (FloatFFT_3D.this.slices * 2) + i47;
                                    int i49 = (FloatFFT_3D.this.slices * 2) + i48;
                                    int i50 = (FloatFFT_3D.this.slices * 2) + i49;
                                    float[] fArr9 = fArr;
                                    fArr2[i47] = fArr9[i46];
                                    fArr2[i47 + 1] = fArr9[i46 + 1];
                                    fArr2[i48] = fArr9[i46 + 2];
                                    fArr2[i48 + 1] = fArr9[i46 + 3];
                                    fArr2[i49] = fArr9[i46 + 4];
                                    fArr2[i49 + 1] = fArr9[i46 + 5];
                                    fArr2[i50] = fArr9[i46 + 6];
                                    fArr2[i50 + 1] = fArr9[i46 + 7];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, 0, z);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 2, z);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 4, z);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 6, z);
                                for (int i51 = 0; i51 < FloatFFT_3D.this.slices; i51++) {
                                    int i52 = (FloatFFT_3D.this.sliceStride * i51) + i43 + i44;
                                    int i53 = i51 * 2;
                                    int i54 = (FloatFFT_3D.this.slices * 2) + i53;
                                    int i55 = (FloatFFT_3D.this.slices * 2) + i54;
                                    int i56 = (FloatFFT_3D.this.slices * 2) + i55;
                                    float[] fArr10 = fArr;
                                    fArr10[i52] = fArr2[i53];
                                    fArr10[i52 + 1] = fArr2[i53 + 1];
                                    fArr10[i52 + 2] = fArr2[i54];
                                    fArr10[i52 + 3] = fArr2[i54 + 1];
                                    fArr10[i52 + 4] = fArr2[i55];
                                    fArr10[i52 + 5] = fArr2[i55 + 1];
                                    fArr10[i52 + 6] = fArr2[i56];
                                    fArr10[i52 + 7] = fArr2[i56 + 1];
                                }
                            }
                            i42 += iMin;
                        }
                        return;
                    }
                    if (FloatFFT_3D.this.columns == 4) {
                        int i57 = i8;
                        while (i57 < FloatFFT_3D.this.rows) {
                            int i58 = FloatFFT_3D.this.rowStride * i57;
                            for (int i59 = 0; i59 < FloatFFT_3D.this.slices; i59++) {
                                int i60 = (FloatFFT_3D.this.sliceStride * i59) + i58;
                                int i61 = i59 * 2;
                                int i62 = (FloatFFT_3D.this.slices * 2) + i61;
                                float[] fArr11 = fArr;
                                fArr2[i61] = fArr11[i60];
                                fArr2[i61 + 1] = fArr11[i60 + 1];
                                fArr2[i62] = fArr11[i60 + 2];
                                fArr2[i62 + 1] = fArr11[i60 + 3];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr2, 0, z);
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 2, z);
                            for (int i63 = 0; i63 < FloatFFT_3D.this.slices; i63++) {
                                int i64 = (FloatFFT_3D.this.sliceStride * i63) + i58;
                                int i65 = i63 * 2;
                                int i66 = (FloatFFT_3D.this.slices * 2) + i65;
                                float[] fArr12 = fArr;
                                fArr12[i64] = fArr2[i65];
                                fArr12[i64 + 1] = fArr2[i65 + 1];
                                fArr12[i64 + 2] = fArr2[i66];
                                fArr12[i64 + 3] = fArr2[i66 + 1];
                            }
                            i57 += iMin;
                        }
                        return;
                    }
                    if (FloatFFT_3D.this.columns == 2) {
                        int i67 = i8;
                        while (i67 < FloatFFT_3D.this.rows) {
                            int i68 = FloatFFT_3D.this.rowStride * i67;
                            for (int i69 = 0; i69 < FloatFFT_3D.this.slices; i69++) {
                                int i70 = (FloatFFT_3D.this.sliceStride * i69) + i68;
                                int i71 = i69 * 2;
                                float[] fArr13 = fArr;
                                fArr2[i71] = fArr13[i70];
                                fArr2[i71 + 1] = fArr13[i70 + 1];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr2, 0, z);
                            for (int i72 = 0; i72 < FloatFFT_3D.this.slices; i72++) {
                                int i73 = (FloatFFT_3D.this.sliceStride * i72) + i68;
                                int i74 = i72 * 2;
                                float[] fArr14 = fArr;
                                fArr14[i73] = fArr2[i74];
                                fArr14[i73 + 1] = fArr2[i74 + 1];
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
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035 A[LOOP:0: B:13:0x0033->B:14:0x0035, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cdft3db_subth(final int r19, final pl.edu.icm.jlargearrays.FloatLargeArray r20, final boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.fft.FloatFFT_3D> r12 = org.jtransforms.fft.FloatFFT_3D.class
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
            org.jtransforms.fft.FloatFFT_3D$58 r16 = new org.jtransforms.fft.FloatFFT_3D$58
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
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.cdft3db_subth(int, pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    private void cdft3db_subth(final int i, final float[][][] fArr, final boolean z) {
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
            futureArr[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.59
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i6];
                    if (i == -1) {
                        if (FloatFFT_3D.this.columns > 4) {
                            int i9 = i8;
                            while (i9 < FloatFFT_3D.this.rows) {
                                for (int i10 = 0; i10 < FloatFFT_3D.this.columns; i10 += 8) {
                                    for (int i11 = 0; i11 < FloatFFT_3D.this.slices; i11++) {
                                        int i12 = i11 * 2;
                                        int i13 = (FloatFFT_3D.this.slices * 2) + i12;
                                        int i14 = (FloatFFT_3D.this.slices * 2) + i13;
                                        int i15 = (FloatFFT_3D.this.slices * 2) + i14;
                                        float[] fArr3 = fArr[i11][i9];
                                        fArr2[i12] = fArr3[i10];
                                        fArr2[i12 + 1] = fArr3[i10 + 1];
                                        fArr2[i13] = fArr3[i10 + 2];
                                        fArr2[i13 + 1] = fArr3[i10 + 3];
                                        fArr2[i14] = fArr3[i10 + 4];
                                        fArr2[i14 + 1] = fArr3[i10 + 5];
                                        fArr2[i15] = fArr3[i10 + 6];
                                        fArr2[i15 + 1] = fArr3[i10 + 7];
                                    }
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, 0);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 2);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 4);
                                    FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 6);
                                    for (int i16 = 0; i16 < FloatFFT_3D.this.slices; i16++) {
                                        int i17 = i16 * 2;
                                        int i18 = (FloatFFT_3D.this.slices * 2) + i17;
                                        int i19 = (FloatFFT_3D.this.slices * 2) + i18;
                                        int i20 = (FloatFFT_3D.this.slices * 2) + i19;
                                        float[] fArr4 = fArr[i16][i9];
                                        fArr4[i10] = fArr2[i17];
                                        fArr4[i10 + 1] = fArr2[i17 + 1];
                                        fArr4[i10 + 2] = fArr2[i18];
                                        fArr4[i10 + 3] = fArr2[i18 + 1];
                                        fArr4[i10 + 4] = fArr2[i19];
                                        fArr4[i10 + 5] = fArr2[i19 + 1];
                                        fArr4[i10 + 6] = fArr2[i20];
                                        fArr4[i10 + 7] = fArr2[i20 + 1];
                                    }
                                }
                                i9 += iMin;
                            }
                            return;
                        }
                        if (FloatFFT_3D.this.columns == 4) {
                            int i21 = i8;
                            while (i21 < FloatFFT_3D.this.rows) {
                                for (int i22 = 0; i22 < FloatFFT_3D.this.slices; i22++) {
                                    int i23 = i22 * 2;
                                    int i24 = (FloatFFT_3D.this.slices * 2) + i23;
                                    float[] fArr5 = fArr[i22][i21];
                                    fArr2[i23] = fArr5[0];
                                    fArr2[i23 + 1] = fArr5[1];
                                    fArr2[i24] = fArr5[2];
                                    fArr2[i24 + 1] = fArr5[3];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2, 0);
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2, FloatFFT_3D.this.slices * 2);
                                for (int i25 = 0; i25 < FloatFFT_3D.this.slices; i25++) {
                                    int i26 = i25 * 2;
                                    int i27 = (FloatFFT_3D.this.slices * 2) + i26;
                                    float[] fArr6 = fArr[i25][i21];
                                    fArr6[0] = fArr2[i26];
                                    fArr6[1] = fArr2[i26 + 1];
                                    fArr6[2] = fArr2[i27];
                                    fArr6[3] = fArr2[i27 + 1];
                                }
                                i21 += iMin;
                            }
                            return;
                        }
                        if (FloatFFT_3D.this.columns == 2) {
                            int i28 = i8;
                            while (i28 < FloatFFT_3D.this.rows) {
                                for (int i29 = 0; i29 < FloatFFT_3D.this.slices; i29++) {
                                    int i30 = i29 * 2;
                                    float[] fArr7 = fArr[i29][i28];
                                    fArr2[i30] = fArr7[0];
                                    fArr2[i30 + 1] = fArr7[1];
                                }
                                FloatFFT_3D.this.fftSlices.complexForward(fArr2, 0);
                                for (int i31 = 0; i31 < FloatFFT_3D.this.slices; i31++) {
                                    int i32 = i31 * 2;
                                    float[] fArr8 = fArr[i31][i28];
                                    fArr8[0] = fArr2[i32];
                                    fArr8[1] = fArr2[i32 + 1];
                                }
                                i28 += iMin;
                            }
                            return;
                        }
                        return;
                    }
                    if (FloatFFT_3D.this.columns > 4) {
                        int i33 = i8;
                        while (i33 < FloatFFT_3D.this.rows) {
                            for (int i34 = 0; i34 < FloatFFT_3D.this.columns; i34 += 8) {
                                for (int i35 = 0; i35 < FloatFFT_3D.this.slices; i35++) {
                                    int i36 = i35 * 2;
                                    int i37 = (FloatFFT_3D.this.slices * 2) + i36;
                                    int i38 = (FloatFFT_3D.this.slices * 2) + i37;
                                    int i39 = (FloatFFT_3D.this.slices * 2) + i38;
                                    float[] fArr9 = fArr[i35][i33];
                                    fArr2[i36] = fArr9[i34];
                                    fArr2[i36 + 1] = fArr9[i34 + 1];
                                    fArr2[i37] = fArr9[i34 + 2];
                                    fArr2[i37 + 1] = fArr9[i34 + 3];
                                    fArr2[i38] = fArr9[i34 + 4];
                                    fArr2[i38 + 1] = fArr9[i34 + 5];
                                    fArr2[i39] = fArr9[i34 + 6];
                                    fArr2[i39 + 1] = fArr9[i34 + 7];
                                }
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, 0, z);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 2, z);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 4, z);
                                FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 6, z);
                                for (int i40 = 0; i40 < FloatFFT_3D.this.slices; i40++) {
                                    int i41 = i40 * 2;
                                    int i42 = (FloatFFT_3D.this.slices * 2) + i41;
                                    int i43 = (FloatFFT_3D.this.slices * 2) + i42;
                                    int i44 = (FloatFFT_3D.this.slices * 2) + i43;
                                    float[] fArr10 = fArr[i40][i33];
                                    fArr10[i34] = fArr2[i41];
                                    fArr10[i34 + 1] = fArr2[i41 + 1];
                                    fArr10[i34 + 2] = fArr2[i42];
                                    fArr10[i34 + 3] = fArr2[i42 + 1];
                                    fArr10[i34 + 4] = fArr2[i43];
                                    fArr10[i34 + 5] = fArr2[i43 + 1];
                                    fArr10[i34 + 6] = fArr2[i44];
                                    fArr10[i34 + 7] = fArr2[i44 + 1];
                                }
                            }
                            i33 += iMin;
                        }
                        return;
                    }
                    if (FloatFFT_3D.this.columns == 4) {
                        int i45 = i8;
                        while (i45 < FloatFFT_3D.this.rows) {
                            for (int i46 = 0; i46 < FloatFFT_3D.this.slices; i46++) {
                                int i47 = i46 * 2;
                                int i48 = (FloatFFT_3D.this.slices * 2) + i47;
                                float[] fArr11 = fArr[i46][i45];
                                fArr2[i47] = fArr11[0];
                                fArr2[i47 + 1] = fArr11[1];
                                fArr2[i48] = fArr11[2];
                                fArr2[i48 + 1] = fArr11[3];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr2, 0, z);
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr2, FloatFFT_3D.this.slices * 2, z);
                            for (int i49 = 0; i49 < FloatFFT_3D.this.slices; i49++) {
                                int i50 = i49 * 2;
                                int i51 = (FloatFFT_3D.this.slices * 2) + i50;
                                float[] fArr12 = fArr[i49][i45];
                                fArr12[0] = fArr2[i50];
                                fArr12[1] = fArr2[i50 + 1];
                                fArr12[2] = fArr2[i51];
                                fArr12[3] = fArr2[i51 + 1];
                            }
                            i45 += iMin;
                        }
                        return;
                    }
                    if (FloatFFT_3D.this.columns == 2) {
                        int i52 = i8;
                        while (i52 < FloatFFT_3D.this.rows) {
                            for (int i53 = 0; i53 < FloatFFT_3D.this.slices; i53++) {
                                int i54 = i53 * 2;
                                float[] fArr13 = fArr[i53][i52];
                                fArr2[i54] = fArr13[0];
                                fArr2[i54 + 1] = fArr13[1];
                            }
                            FloatFFT_3D.this.fftSlices.complexInverse(fArr2, 0, z);
                            for (int i55 = 0; i55 < FloatFFT_3D.this.slices; i55++) {
                                int i56 = i55 * 2;
                                float[] fArr14 = fArr[i55][i52];
                                fArr14[0] = fArr2[i56];
                                fArr14[1] = fArr2[i56 + 1];
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
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void rdft3d_sub(int i, float[] fArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i >= 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                int i6 = this.sliceStride;
                int i7 = i5 * i6;
                int i8 = i4 * i6;
                float f = (fArr[i8] - fArr[i7]) * 0.5f;
                fArr[i7] = f;
                fArr[i8] = fArr[i8] - f;
                int i9 = i7 + 1;
                int i10 = i8 + 1;
                float f2 = (fArr[i10] + fArr[i9]) * 0.5f;
                fArr[i9] = f2;
                fArr[i10] = fArr[i10] - f2;
                int i11 = this.rowStride;
                int i12 = (i5 * i6) + (i3 * i11);
                int i13 = (i6 * i4) + (i11 * i3);
                float f3 = (fArr[i13] - fArr[i12]) * 0.5f;
                fArr[i12] = f3;
                fArr[i13] = fArr[i13] - f3;
                int i14 = i12 + 1;
                int i15 = i13 + 1;
                float f4 = (fArr[i15] + fArr[i14]) * 0.5f;
                fArr[i14] = f4;
                fArr[i15] = fArr[i15] - f4;
                for (int i16 = 1; i16 < i3; i16++) {
                    int i17 = this.rows - i16;
                    int i18 = this.sliceStride;
                    int i19 = this.rowStride;
                    int i20 = (i5 * i18) + (i17 * i19);
                    int i21 = (i4 * i18) + (i16 * i19);
                    float f5 = (fArr[i21] - fArr[i20]) * 0.5f;
                    fArr[i20] = f5;
                    fArr[i21] = fArr[i21] - f5;
                    int i22 = i20 + 1;
                    int i23 = i21 + 1;
                    float f6 = (fArr[i23] + fArr[i22]) * 0.5f;
                    fArr[i22] = f6;
                    fArr[i23] = fArr[i23] - f6;
                    int i24 = (i4 * i18) + (i17 * i19);
                    int i25 = (i18 * i5) + (i19 * i16);
                    float f7 = (fArr[i25] - fArr[i24]) * 0.5f;
                    fArr[i24] = f7;
                    fArr[i25] = fArr[i25] - f7;
                    int i26 = i24 + 1;
                    int i27 = i25 + 1;
                    float f8 = (fArr[i27] + fArr[i26]) * 0.5f;
                    fArr[i26] = f8;
                    fArr[i27] = fArr[i27] - f8;
                }
            }
            for (int i28 = 1; i28 < i3; i28++) {
                int i29 = this.rows - i28;
                int i30 = this.rowStride;
                int i31 = i29 * i30;
                int i32 = i28 * i30;
                float f9 = (fArr[i32] - fArr[i31]) * 0.5f;
                fArr[i31] = f9;
                fArr[i32] = fArr[i32] - f9;
                int i33 = i31 + 1;
                int i34 = i32 + 1;
                float f10 = (fArr[i34] + fArr[i33]) * 0.5f;
                fArr[i33] = f10;
                fArr[i34] = fArr[i34] - f10;
                int i35 = this.sliceStride;
                int i36 = (i2 * i35) + (i29 * i30);
                int i37 = (i35 * i2) + (i30 * i28);
                float f11 = (fArr[i37] - fArr[i36]) * 0.5f;
                fArr[i36] = f11;
                fArr[i37] = fArr[i37] - f11;
                int i38 = i36 + 1;
                int i39 = i37 + 1;
                float f12 = (fArr[i39] + fArr[i38]) * 0.5f;
                fArr[i38] = f12;
                fArr[i39] = fArr[i39] - f12;
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
            float f13 = fArr[i43];
            float f14 = fArr[i44];
            fArr[i43] = f13 + f14;
            fArr[i44] = f13 - f14;
            int i48 = i44 + 1;
            float f15 = fArr[i48];
            int i49 = i43 + 1;
            float f16 = fArr[i49];
            fArr[i49] = f16 + f15;
            fArr[i48] = f15 - f16;
            float f17 = fArr[i46];
            float f18 = fArr[i47];
            fArr[i46] = f17 + f18;
            fArr[i47] = f17 - f18;
            int i50 = i47 + 1;
            float f19 = fArr[i50];
            int i51 = i46 + 1;
            float f20 = fArr[i51];
            fArr[i51] = f20 + f19;
            fArr[i50] = f19 - f20;
            for (int i52 = 1; i52 < i3; i52++) {
                int i53 = this.rows - i52;
                int i54 = this.sliceStride;
                int i55 = this.rowStride;
                int i56 = (i40 * i54) + (i52 * i55);
                int i57 = (i41 * i54) + (i53 * i55);
                float f21 = fArr[i56];
                float f22 = fArr[i57];
                fArr[i56] = f21 + f22;
                fArr[i57] = f21 - f22;
                int i58 = i57 + 1;
                float f23 = fArr[i58];
                int i59 = i56 + 1;
                float f24 = fArr[i59];
                fArr[i59] = f24 + f23;
                fArr[i58] = f23 - f24;
                int i60 = (i41 * i54) + (i52 * i55);
                int i61 = (i54 * i40) + (i53 * i55);
                float f25 = fArr[i60];
                float f26 = fArr[i61];
                fArr[i60] = f25 + f26;
                fArr[i61] = f25 - f26;
                int i62 = i61 + 1;
                float f27 = fArr[i62];
                int i63 = i60 + 1;
                float f28 = fArr[i63];
                fArr[i63] = f28 + f27;
                fArr[i62] = f27 - f28;
            }
        }
        for (int i64 = 1; i64 < i3; i64++) {
            int i65 = this.rows - i64;
            int i66 = this.rowStride;
            int i67 = i64 * i66;
            int i68 = i65 * i66;
            float f29 = fArr[i67];
            float f30 = fArr[i68];
            fArr[i67] = f29 + f30;
            fArr[i68] = f29 - f30;
            int i69 = i68 + 1;
            float f31 = fArr[i69];
            int i70 = i67 + 1;
            float f32 = fArr[i70];
            fArr[i70] = f32 + f31;
            fArr[i69] = f31 - f32;
            int i71 = this.sliceStride;
            int i72 = (i2 * i71) + (i64 * i66);
            int i73 = (i71 * i2) + (i65 * i66);
            float f33 = fArr[i72];
            float f34 = fArr[i73];
            fArr[i72] = f33 + f34;
            fArr[i73] = f33 - f34;
            int i74 = i73 + 1;
            float f35 = fArr[i74];
            int i75 = i72 + 1;
            float f36 = fArr[i75];
            fArr[i75] = f36 + f35;
            fArr[i74] = f35 - f36;
        }
    }

    private void rdft3d_sub(int i, FloatLargeArray floatLargeArray) {
        long j = this.slicesl >> 1;
        long j2 = this.rowsl >> 1;
        if (i >= 0) {
            for (long j3 = 1; j3 < j; j3++) {
                long j4 = this.slicesl - j3;
                long j5 = this.sliceStridel;
                long j6 = j4 * j5;
                long j7 = j5 * j3;
                floatLargeArray.setFloat(j6, (floatLargeArray.getFloat(j7) - floatLargeArray.getFloat(j6)) * 0.5f);
                floatLargeArray.setFloat(j7, floatLargeArray.getFloat(j7) - floatLargeArray.getFloat(j6));
                long j8 = j6 + 1;
                long j9 = j7 + 1;
                floatLargeArray.setFloat(j8, (floatLargeArray.getFloat(j9) + floatLargeArray.getFloat(j8)) * 0.5f);
                floatLargeArray.setFloat(j9, floatLargeArray.getFloat(j9) - floatLargeArray.getFloat(j8));
                long j10 = this.sliceStridel;
                long j11 = this.rowStridel;
                long j12 = (j4 * j10) + (j2 * j11);
                long j13 = (j10 * j3) + (j11 * j2);
                floatLargeArray.setFloat(j12, (floatLargeArray.getFloat(j13) - floatLargeArray.getFloat(j12)) * 0.5f);
                floatLargeArray.setFloat(j13, floatLargeArray.getFloat(j13) - floatLargeArray.getFloat(j12));
                long j14 = j12 + 1;
                long j15 = j13 + 1;
                floatLargeArray.setFloat(j14, (floatLargeArray.getFloat(j15) + floatLargeArray.getFloat(j14)) * 0.5f);
                floatLargeArray.setFloat(j15, floatLargeArray.getFloat(j15) - floatLargeArray.getFloat(j14));
                long j16 = 1;
                while (j16 < j2) {
                    long j17 = this.rowsl - j16;
                    long j18 = this.sliceStridel;
                    long j19 = j4;
                    long j20 = this.rowStridel;
                    long j21 = j2;
                    long j22 = (j4 * j18) + (j17 * j20);
                    long j23 = (j18 * j3) + (j20 * j16);
                    floatLargeArray.setFloat(j22, (floatLargeArray.getFloat(j23) - floatLargeArray.getFloat(j22)) * 0.5f);
                    floatLargeArray.setFloat(j23, floatLargeArray.getFloat(j23) - floatLargeArray.getFloat(j22));
                    long j24 = j22 + 1;
                    long j25 = j23 + 1;
                    floatLargeArray.setFloat(j24, (floatLargeArray.getFloat(j25) + floatLargeArray.getFloat(j24)) * 0.5f);
                    floatLargeArray.setFloat(j25, floatLargeArray.getFloat(j25) - floatLargeArray.getFloat(j24));
                    long j26 = this.sliceStridel;
                    long j27 = this.rowStridel;
                    long j28 = (j3 * j26) + (j17 * j27);
                    long j29 = (j26 * j19) + (j27 * j16);
                    floatLargeArray.setFloat(j28, (floatLargeArray.getFloat(j29) - floatLargeArray.getFloat(j28)) * 0.5f);
                    floatLargeArray.setFloat(j29, floatLargeArray.getFloat(j29) - floatLargeArray.getFloat(j28));
                    long j30 = j28 + 1;
                    long j31 = j29 + 1;
                    floatLargeArray.setFloat(j30, (floatLargeArray.getFloat(j31) + floatLargeArray.getFloat(j30)) * 0.5f);
                    floatLargeArray.setFloat(j31, floatLargeArray.getFloat(j31) - floatLargeArray.getFloat(j30));
                    j16++;
                    j4 = j19;
                    j2 = j21;
                }
            }
            long j32 = j2;
            for (long j33 = 1; j33 < j32; j33++) {
                long j34 = this.rowsl - j33;
                long j35 = this.rowStridel;
                long j36 = j34 * j35;
                long j37 = j35 * j33;
                floatLargeArray.setFloat(j36, (floatLargeArray.getFloat(j37) - floatLargeArray.getFloat(j36)) * 0.5f);
                floatLargeArray.setFloat(j37, floatLargeArray.getFloat(j37) - floatLargeArray.getFloat(j36));
                long j38 = j36 + 1;
                long j39 = j37 + 1;
                floatLargeArray.setFloat(j38, (floatLargeArray.getFloat(j39) + floatLargeArray.getFloat(j38)) * 0.5f);
                floatLargeArray.setFloat(j39, floatLargeArray.getFloat(j39) - floatLargeArray.getFloat(j38));
                long j40 = this.sliceStridel;
                long j41 = this.rowStridel;
                long j42 = (j * j40) + (j34 * j41);
                long j43 = (j * j40) + (j41 * j33);
                floatLargeArray.setFloat(j42, (floatLargeArray.getFloat(j43) - floatLargeArray.getFloat(j42)) * 0.5f);
                floatLargeArray.setFloat(j43, floatLargeArray.getFloat(j43) - floatLargeArray.getFloat(j42));
                long j44 = j42 + 1;
                long j45 = j43 + 1;
                floatLargeArray.setFloat(j44, (floatLargeArray.getFloat(j45) + floatLargeArray.getFloat(j44)) * 0.5f);
                floatLargeArray.setFloat(j45, floatLargeArray.getFloat(j45) - floatLargeArray.getFloat(j44));
            }
            return;
        }
        long j46 = 1;
        while (j46 < j) {
            long j47 = this.slicesl - j46;
            long j48 = this.sliceStridel;
            long j49 = j46 * j48;
            long j50 = j47 * j48;
            long j51 = j;
            long j52 = this.rowStridel;
            long j53 = j46;
            long j54 = (j46 * j48) + (j2 * j52);
            long j55 = (j48 * j47) + (j52 * j2);
            float f = floatLargeArray.getFloat(j49) - floatLargeArray.getFloat(j50);
            floatLargeArray.setFloat(j49, floatLargeArray.getFloat(j49) + floatLargeArray.getFloat(j50));
            floatLargeArray.setFloat(j50, f);
            long j56 = j50 + 1;
            long j57 = j49 + 1;
            float f2 = floatLargeArray.getFloat(j56) - floatLargeArray.getFloat(j57);
            floatLargeArray.setFloat(j57, floatLargeArray.getFloat(j57) + floatLargeArray.getFloat(j56));
            floatLargeArray.setFloat(j56, f2);
            float f3 = floatLargeArray.getFloat(j54) - floatLargeArray.getFloat(j55);
            floatLargeArray.setFloat(j54, floatLargeArray.getFloat(j54) + floatLargeArray.getFloat(j55));
            floatLargeArray.setFloat(j55, f3);
            long j58 = j55 + 1;
            long j59 = j54 + 1;
            float f4 = floatLargeArray.getFloat(j58) - floatLargeArray.getFloat(j59);
            floatLargeArray.setFloat(j59, floatLargeArray.getFloat(j59) + floatLargeArray.getFloat(j58));
            floatLargeArray.setFloat(j58, f4);
            for (long j60 = 1; j60 < j2; j60++) {
                long j61 = this.rowsl - j60;
                long j62 = this.sliceStridel;
                long j63 = this.rowStridel;
                long j64 = (j53 * j62) + (j60 * j63);
                long j65 = (j62 * j47) + (j63 * j61);
                float f5 = floatLargeArray.getFloat(j64) - floatLargeArray.getFloat(j65);
                floatLargeArray.setFloat(j64, floatLargeArray.getFloat(j64) + floatLargeArray.getFloat(j65));
                floatLargeArray.setFloat(j65, f5);
                long j66 = j65 + 1;
                long j67 = j64 + 1;
                float f6 = floatLargeArray.getFloat(j66) - floatLargeArray.getFloat(j67);
                floatLargeArray.setFloat(j67, floatLargeArray.getFloat(j67) + floatLargeArray.getFloat(j66));
                floatLargeArray.setFloat(j66, f6);
                long j68 = this.sliceStridel;
                long j69 = this.rowStridel;
                long j70 = (j47 * j68) + (j60 * j69);
                long j71 = (j68 * j53) + (j61 * j69);
                float f7 = floatLargeArray.getFloat(j70) - floatLargeArray.getFloat(j71);
                floatLargeArray.setFloat(j70, floatLargeArray.getFloat(j70) + floatLargeArray.getFloat(j71));
                floatLargeArray.setFloat(j71, f7);
                long j72 = j71 + 1;
                long j73 = j70 + 1;
                float f8 = floatLargeArray.getFloat(j72) - floatLargeArray.getFloat(j73);
                floatLargeArray.setFloat(j73, floatLargeArray.getFloat(j73) + floatLargeArray.getFloat(j72));
                floatLargeArray.setFloat(j72, f8);
            }
            j46 = j53 + 1;
            j = j51;
        }
        long j74 = j;
        for (long j75 = 1; j75 < j2; j75++) {
            long j76 = this.rowsl - j75;
            long j77 = this.rowStridel;
            long j78 = j75 * j77;
            long j79 = j77 * j76;
            float f9 = floatLargeArray.getFloat(j78) - floatLargeArray.getFloat(j79);
            floatLargeArray.setFloat(j78, floatLargeArray.getFloat(j78) + floatLargeArray.getFloat(j79));
            floatLargeArray.setFloat(j79, f9);
            long j80 = j79 + 1;
            long j81 = j78 + 1;
            float f10 = floatLargeArray.getFloat(j80) - floatLargeArray.getFloat(j81);
            floatLargeArray.setFloat(j81, floatLargeArray.getFloat(j81) + floatLargeArray.getFloat(j80));
            floatLargeArray.setFloat(j80, f10);
            long j82 = this.sliceStridel;
            long j83 = this.rowStridel;
            long j84 = (j74 * j82) + (j75 * j83);
            long j85 = (j82 * j74) + (j76 * j83);
            float f11 = floatLargeArray.getFloat(j84) - floatLargeArray.getFloat(j85);
            floatLargeArray.setFloat(j84, floatLargeArray.getFloat(j84) + floatLargeArray.getFloat(j85));
            floatLargeArray.setFloat(j85, f11);
            long j86 = j85 + 1;
            long j87 = j84 + 1;
            float f12 = floatLargeArray.getFloat(j86) - floatLargeArray.getFloat(j87);
            floatLargeArray.setFloat(j87, floatLargeArray.getFloat(j87) + floatLargeArray.getFloat(j86));
            floatLargeArray.setFloat(j86, f12);
        }
    }

    private void rdft3d_sub(int i, float[][][] fArr) {
        int i2 = this.slices >> 1;
        int i3 = this.rows >> 1;
        if (i >= 0) {
            for (int i4 = 1; i4 < i2; i4++) {
                int i5 = this.slices - i4;
                float[][] fArr2 = fArr[i5];
                float[] fArr3 = fArr2[0];
                float[][] fArr4 = fArr[i4];
                float[] fArr5 = fArr4[0];
                float f = (fArr5[0] - fArr3[0]) * 0.5f;
                fArr3[0] = f;
                fArr5[0] = fArr5[0] - f;
                float f2 = (fArr5[1] + fArr3[1]) * 0.5f;
                fArr3[1] = f2;
                fArr5[1] = fArr5[1] - f2;
                float[] fArr6 = fArr2[i3];
                float[] fArr7 = fArr4[i3];
                float f3 = (fArr7[0] - fArr6[0]) * 0.5f;
                fArr6[0] = f3;
                fArr7[0] = fArr7[0] - f3;
                float f4 = (fArr7[1] + fArr6[1]) * 0.5f;
                fArr6[1] = f4;
                fArr7[1] = fArr7[1] - f4;
                for (int i6 = 1; i6 < i3; i6++) {
                    int i7 = this.rows - i6;
                    float[][] fArr8 = fArr[i5];
                    float[] fArr9 = fArr8[i7];
                    float[][] fArr10 = fArr[i4];
                    float[] fArr11 = fArr10[i6];
                    float f5 = (fArr11[0] - fArr9[0]) * 0.5f;
                    fArr9[0] = f5;
                    fArr11[0] = fArr11[0] - f5;
                    float f6 = (fArr11[1] + fArr9[1]) * 0.5f;
                    fArr9[1] = f6;
                    fArr11[1] = fArr11[1] - f6;
                    float[] fArr12 = fArr10[i7];
                    float[] fArr13 = fArr8[i6];
                    float f7 = (fArr13[0] - fArr12[0]) * 0.5f;
                    fArr12[0] = f7;
                    fArr13[0] = fArr13[0] - f7;
                    float f8 = (fArr13[1] + fArr12[1]) * 0.5f;
                    fArr12[1] = f8;
                    fArr13[1] = fArr13[1] - f8;
                }
            }
            for (int i8 = 1; i8 < i3; i8++) {
                int i9 = this.rows - i8;
                float[][] fArr14 = fArr[0];
                float[] fArr15 = fArr14[i9];
                float[] fArr16 = fArr14[i8];
                float f9 = (fArr16[0] - fArr15[0]) * 0.5f;
                fArr15[0] = f9;
                fArr16[0] = fArr16[0] - f9;
                float f10 = (fArr16[1] + fArr15[1]) * 0.5f;
                fArr15[1] = f10;
                fArr16[1] = fArr16[1] - f10;
                float[][] fArr17 = fArr[i2];
                float[] fArr18 = fArr17[i9];
                float[] fArr19 = fArr17[i8];
                float f11 = (fArr19[0] - fArr18[0]) * 0.5f;
                fArr18[0] = f11;
                fArr19[0] = fArr19[0] - f11;
                float f12 = (fArr19[1] + fArr18[1]) * 0.5f;
                fArr18[1] = f12;
                fArr19[1] = fArr19[1] - f12;
            }
            return;
        }
        for (int i10 = 1; i10 < i2; i10++) {
            int i11 = this.slices - i10;
            float[][] fArr20 = fArr[i10];
            float[] fArr21 = fArr20[0];
            float f13 = fArr21[0];
            float[][] fArr22 = fArr[i11];
            float[] fArr23 = fArr22[0];
            float f14 = fArr23[0];
            fArr21[0] = f13 + f14;
            fArr23[0] = f13 - f14;
            float f15 = fArr23[1];
            float f16 = fArr21[1];
            fArr21[1] = f16 + f15;
            fArr23[1] = f15 - f16;
            float[] fArr24 = fArr20[i3];
            float f17 = fArr24[0];
            float[] fArr25 = fArr22[i3];
            float f18 = fArr25[0];
            fArr24[0] = f17 + f18;
            fArr25[0] = f17 - f18;
            float f19 = fArr25[1];
            float f20 = fArr24[1];
            fArr24[1] = f20 + f19;
            fArr25[1] = f19 - f20;
            for (int i12 = 1; i12 < i3; i12++) {
                int i13 = this.rows - i12;
                float[][] fArr26 = fArr[i10];
                float[] fArr27 = fArr26[i12];
                float f21 = fArr27[0];
                float[][] fArr28 = fArr[i11];
                float[] fArr29 = fArr28[i13];
                float f22 = fArr29[0];
                fArr27[0] = f21 + f22;
                fArr29[0] = f21 - f22;
                float f23 = fArr29[1];
                float f24 = fArr27[1];
                fArr27[1] = f24 + f23;
                fArr29[1] = f23 - f24;
                float[] fArr30 = fArr28[i12];
                float f25 = fArr30[0];
                float[] fArr31 = fArr26[i13];
                float f26 = fArr31[0];
                fArr30[0] = f25 + f26;
                fArr31[0] = f25 - f26;
                float f27 = fArr31[1];
                float f28 = fArr30[1];
                fArr30[1] = f28 + f27;
                fArr31[1] = f27 - f28;
            }
        }
        for (int i14 = 1; i14 < i3; i14++) {
            int i15 = this.rows - i14;
            float[][] fArr32 = fArr[0];
            float[] fArr33 = fArr32[i14];
            float f29 = fArr33[0];
            float[] fArr34 = fArr32[i15];
            float f30 = fArr34[0];
            fArr33[0] = f29 + f30;
            fArr34[0] = f29 - f30;
            float f31 = fArr34[1];
            float f32 = fArr33[1];
            fArr33[1] = f32 + f31;
            fArr34[1] = f31 - f32;
            float[][] fArr35 = fArr[i2];
            float[] fArr36 = fArr35[i14];
            float f33 = fArr36[0];
            float[] fArr37 = fArr35[i15];
            float f34 = fArr37[0];
            fArr36[0] = f33 + f34;
            fArr37[0] = f33 - f34;
            float f35 = fArr37[1];
            float f36 = fArr36[1];
            fArr36[1] = f36 + f35;
            fArr37[1] = f35 - f36;
        }
    }

    private void fillSymmetric(final float[][][] fArr) {
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
                            float[] fArr2 = fArr[i7][i10];
                            float[] fArr3 = fArr[i5][i8];
                            int i13 = i11 + 2;
                            fArr2[i12] = -fArr3[i13];
                            fArr2[i12 - 1] = fArr3[i11 + 1];
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
                    float[] fArr4 = fArr[i16][i17];
                    int i19 = this.columns;
                    float[] fArr5 = fArr[i14][i18];
                    fArr4[i19] = fArr5[1];
                    fArr5[i19] = fArr5[1];
                    fArr4[i19 + 1] = -fArr5[0];
                    fArr5[i19 + 1] = fArr5[0];
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
                    float[] fArr6 = fArr[i22][this.rows - i23];
                    float[] fArr7 = fArr[i20][i23];
                    fArr6[0] = fArr7[0];
                    fArr6[1] = -fArr7[1];
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
                futureArr[i28] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.60
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i29 = i26; i29 < i27; i29++) {
                            int i30 = (FloatFFT_3D.this.slices - i29) % FloatFFT_3D.this.slices;
                            for (int i31 = 0; i31 < FloatFFT_3D.this.rows; i31++) {
                                int i32 = (FloatFFT_3D.this.rows - i31) % FloatFFT_3D.this.rows;
                                int i33 = 1;
                                while (i33 < FloatFFT_3D.this.columns) {
                                    int i34 = i2 - i33;
                                    float[][][] fArr8 = fArr;
                                    float[] fArr9 = fArr8[i30][i32];
                                    float[] fArr10 = fArr8[i29][i31];
                                    int i35 = i33 + 2;
                                    fArr9[i34] = -fArr10[i35];
                                    fArr9[i34 - 1] = fArr10[i33 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i29 = 0;
            while (i29 < numberOfThreads) {
                final int i30 = i29 * i24;
                final int i31 = i29 == numberOfThreads + (-1) ? this.slices : i30 + i24;
                futureArr[i29] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.61
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i32 = i30; i32 < i31; i32++) {
                            int i33 = (FloatFFT_3D.this.slices - i32) % FloatFFT_3D.this.slices;
                            for (int i34 = 1; i34 < i3; i34++) {
                                int i35 = FloatFFT_3D.this.rows - i34;
                                float[] fArr8 = fArr[i33][i34];
                                int i36 = FloatFFT_3D.this.columns;
                                float[] fArr9 = fArr[i32][i35];
                                fArr8[i36] = fArr9[1];
                                int i37 = FloatFFT_3D.this.columns;
                                float[][][] fArr10 = fArr;
                                fArr9[i37] = fArr10[i32][i35][1];
                                float[] fArr11 = fArr10[i33][i34];
                                int i38 = FloatFFT_3D.this.columns + 1;
                                float[] fArr12 = fArr[i32][i35];
                                fArr11[i38] = -fArr12[0];
                                fArr12[FloatFFT_3D.this.columns + 1] = fArr[i32][i35][0];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            int i32 = 0;
            while (i32 < numberOfThreads) {
                final int i33 = i32 * i24;
                final int i34 = i32 == numberOfThreads + (-1) ? this.slices : i33 + i24;
                futureArr[i32] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.62
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i35 = i33; i35 < i34; i35++) {
                            int i36 = (FloatFFT_3D.this.slices - i35) % FloatFFT_3D.this.slices;
                            for (int i37 = 1; i37 < i3; i37++) {
                                int i38 = FloatFFT_3D.this.rows - i37;
                                float[][][] fArr8 = fArr;
                                float[] fArr9 = fArr8[i36][i38];
                                float[] fArr10 = fArr8[i35][i37];
                                fArr9[0] = fArr10[0];
                                fArr9[1] = -fArr10[1];
                            }
                        }
                    }
                });
                i32++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e5) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
        }
        for (int i35 = 1; i35 < i4; i35++) {
            int i36 = this.slices - i35;
            float[][] fArr8 = fArr[i35];
            float[] fArr9 = fArr8[0];
            int i37 = this.columns;
            float[][] fArr10 = fArr[i36];
            float[] fArr11 = fArr10[0];
            fArr9[i37] = fArr11[1];
            fArr11[i37] = fArr11[1];
            fArr9[i37 + 1] = -fArr11[0];
            fArr11[i37 + 1] = fArr11[0];
            float[] fArr12 = fArr8[i3];
            float[] fArr13 = fArr10[i3];
            fArr12[i37] = fArr13[1];
            fArr13[i37] = fArr13[1];
            fArr12[i37 + 1] = -fArr13[0];
            fArr13[i37 + 1] = fArr13[0];
            fArr11[0] = fArr9[0];
            fArr11[1] = -fArr9[1];
            fArr13[0] = fArr12[0];
            fArr13[1] = -fArr12[1];
        }
        float[][] fArr14 = fArr[0];
        float[] fArr15 = fArr14[0];
        int i38 = this.columns;
        fArr15[i38] = fArr15[1];
        fArr15[1] = 0.0f;
        float[] fArr16 = fArr14[i3];
        fArr16[i38] = fArr16[1];
        fArr16[1] = 0.0f;
        float[][] fArr17 = fArr[i4];
        float[] fArr18 = fArr17[0];
        fArr18[i38] = fArr18[1];
        fArr18[1] = 0.0f;
        float[] fArr19 = fArr17[i3];
        fArr19[i38] = fArr19[1];
        fArr19[1] = 0.0f;
        fArr18[i38 + 1] = 0.0f;
        fArr19[i38 + 1] = 0.0f;
    }

    private void fillSymmetric(final float[] fArr) {
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
                    fArr[i17] = fArr[i16];
                    fArr[i16] = 0.0f;
                    int i18 = i16 + 1;
                    fArr[i17 + 1] = fArr[i18];
                    fArr[i18] = 0.0f;
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
                fArr[i25] = fArr[i24];
                fArr[i24] = 0.0f;
                int i26 = i24 + 1;
                fArr[i25 + 1] = fArr[i26];
                fArr[i26] = 0.0f;
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
                        for (int i35 = 1; i35 < this.columns; i35 += 2) {
                            int i36 = ((i29 + i33) + i3) - i35;
                            int i37 = i30 + i34 + i35;
                            fArr[i36] = -fArr[i37 + 2];
                            fArr[i36 - 1] = fArr[i37 + 1];
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
                    fArr[i45] = fArr[i47];
                    fArr[i46] = fArr[i47];
                    fArr[i45 + 1] = -fArr[i43];
                    fArr[i46 + 1] = fArr[i43];
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
                    fArr[i53] = fArr[i54];
                    fArr[i53 + 1] = -fArr[i54 + 1];
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
                futureArr2[i56] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.63
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i59 = i57; i59 < i58; i59++) {
                            int i60 = (FloatFFT_3D.this.slices - i59) % FloatFFT_3D.this.slices;
                            int i61 = i8;
                            int i62 = i60 * i61;
                            int i63 = i61 * i59;
                            for (int i64 = 0; i64 < FloatFFT_3D.this.rows; i64++) {
                                int i65 = (FloatFFT_3D.this.rows - i64) % FloatFFT_3D.this.rows;
                                int i66 = i3;
                                int i67 = i65 * i66;
                                int i68 = i66 * i64;
                                for (int i69 = 1; i69 < FloatFFT_3D.this.columns; i69 += 2) {
                                    int i70 = ((i62 + i67) + i3) - i69;
                                    int i71 = i63 + i68 + i69;
                                    float[] fArr2 = fArr;
                                    fArr2[i70] = -fArr2[i71 + 2];
                                    fArr2[i70 - 1] = fArr2[i71 + 1];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i60 = 0;
            while (i60 < i59) {
                final int i61 = i60 * i55;
                final int i62 = i60 == i59 + (-1) ? this.slices : i61 + i55;
                futureArr3[i60] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.64
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i63 = i61; i63 < i62; i63++) {
                            int i64 = (FloatFFT_3D.this.slices - i63) % FloatFFT_3D.this.slices;
                            int i65 = i8;
                            int i66 = i64 * i65;
                            int i67 = i65 * i63;
                            for (int i68 = 1; i68 < i5; i68++) {
                                int i69 = FloatFFT_3D.this.rows - i68;
                                int i70 = i3;
                                int i71 = (i69 * i70) + i67;
                                int i72 = (i70 * i68) + i66 + FloatFFT_3D.this.columns;
                                int i73 = FloatFFT_3D.this.columns + i71;
                                int i74 = i71 + 1;
                                float[] fArr2 = fArr;
                                fArr2[i72] = fArr2[i74];
                                fArr2[i73] = fArr2[i74];
                                fArr2[i72 + 1] = -fArr2[i71];
                                fArr2[i73 + 1] = fArr2[i71];
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
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
            }
            int i63 = 0;
            while (i63 < i59) {
                final int i64 = i63 * i55;
                final int i65 = i63 == i59 + (-1) ? this.slices : i64 + i55;
                futureArr3[i63] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.FloatFFT_3D.65
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i66 = i64; i66 < i65; i66++) {
                            int i67 = (FloatFFT_3D.this.slices - i66) % FloatFFT_3D.this.slices;
                            int i68 = i8;
                            int i69 = i67 * i68;
                            int i70 = i68 * i66;
                            for (int i71 = 1; i71 < i5; i71++) {
                                int i72 = FloatFFT_3D.this.rows - i71;
                                int i73 = i3;
                                int i74 = (i72 * i73) + i69;
                                int i75 = (i73 * i71) + i70;
                                float[] fArr2 = fArr;
                                fArr2[i74] = fArr2[i75];
                                fArr2[i74 + 1] = -fArr2[i75 + 1];
                            }
                        }
                    }
                });
                i63++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e5) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatFFT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
            }
        }
        int i66 = i;
        for (int i67 = 1; i67 < i66; i67++) {
            int i68 = i67 * i8;
            int i69 = (this.slices - i67) * i8;
            int i70 = i5 * i3;
            int i71 = i68 + i70;
            int i72 = i70 + i69;
            int i73 = this.columns;
            int i74 = i69 + 1;
            fArr[i68 + i73] = fArr[i74];
            fArr[i69 + i73] = fArr[i74];
            fArr[i68 + i73 + 1] = -fArr[i69];
            fArr[i69 + i73 + 1] = fArr[i69];
            int i75 = i72 + 1;
            fArr[i71 + i73] = fArr[i75];
            fArr[i72 + i73] = fArr[i75];
            fArr[i71 + i73 + 1] = -fArr[i72];
            fArr[i73 + i72 + 1] = fArr[i72];
            fArr[i69] = fArr[i68];
            fArr[i74] = -fArr[i68 + 1];
            fArr[i72] = fArr[i71];
            fArr[i75] = -fArr[i71 + 1];
        }
        int i76 = this.columns;
        fArr[i76] = fArr[1];
        fArr[1] = 0.0f;
        int i77 = i5 * i3;
        int i78 = i66 * i8;
        int i79 = i77 + i78;
        int i80 = i77 + i76;
        int i81 = i77 + 1;
        fArr[i80] = fArr[i81];
        fArr[i81] = 0.0f;
        int i82 = i78 + 1;
        fArr[i78 + i76] = fArr[i82];
        fArr[i82] = 0.0f;
        int i83 = i79 + 1;
        fArr[i79 + i76] = fArr[i83];
        fArr[i83] = 0.0f;
        fArr[i78 + i76 + 1] = 0.0f;
        fArr[i79 + i76 + 1] = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void fillSymmetric(final pl.edu.icm.jlargearrays.FloatLargeArray r38) {
        /*
            Method dump skipped, instructions count: 975
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.fft.FloatFFT_3D.fillSymmetric(pl.edu.icm.jlargearrays.FloatLargeArray):void");
    }
}
