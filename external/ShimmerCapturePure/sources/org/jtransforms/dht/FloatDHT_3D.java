package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

/* loaded from: classes2.dex */
public class FloatDHT_3D {
    private int columns;
    private long columnsl;
    private FloatDHT_1D dhtColumns;
    private FloatDHT_1D dhtRows;
    private FloatDHT_1D dhtSlices;
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

    public FloatDHT_3D(long j, long j2, long j3) {
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
        long j5 = j * j2 * j3;
        if (j5 >= CommonUtils.getThreadsBeginN_3D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2) && CommonUtils.isPowerOf2(j3)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays(j5 > ((long) LargeArray.getMaxSizeOf32bitArray()));
        FloatDHT_1D floatDHT_1D = new FloatDHT_1D(j);
        this.dhtSlices = floatDHT_1D;
        if (j == j2) {
            this.dhtRows = floatDHT_1D;
        } else {
            this.dhtRows = new FloatDHT_1D(j2);
        }
        if (j == j3) {
            this.dhtColumns = this.dhtSlices;
        } else if (j2 == j3) {
            this.dhtColumns = this.dhtRows;
        } else {
            this.dhtColumns = new FloatDHT_1D(j3);
        }
    }

    public void forward(final float[] fArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(-1, fArr, true);
                ddxt3db_subth(-1, fArr, true);
            } else {
                ddxt3da_sub(-1, fArr, true);
                ddxt3db_sub(-1, fArr, true);
            }
            yTransform(fArr);
            return;
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            int i9 = FloatDHT_3D.this.sliceStride * i8;
                            for (int i10 = 0; i10 < FloatDHT_3D.this.rows; i10++) {
                                FloatDHT_3D.this.dhtColumns.forward(fArr, (FloatDHT_3D.this.rowStride * i10) + i9);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            int i12 = FloatDHT_3D.this.sliceStride * i11;
                            for (int i13 = 0; i13 < FloatDHT_3D.this.columns; i13++) {
                                for (int i14 = 0; i14 < FloatDHT_3D.this.rows; i14++) {
                                    fArr2[i14] = fArr[(FloatDHT_3D.this.rowStride * i14) + i12 + i13];
                                }
                                FloatDHT_3D.this.dhtRows.forward(fArr2);
                                for (int i15 = 0; i15 < FloatDHT_3D.this.rows; i15++) {
                                    fArr[(FloatDHT_3D.this.rowStride * i15) + i12 + i13] = fArr2[i15];
                                }
                            }
                        }
                    }
                });
                i8++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.3
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            int i15 = FloatDHT_3D.this.rowStride * i14;
                            for (int i16 = 0; i16 < FloatDHT_3D.this.columns; i16++) {
                                for (int i17 = 0; i17 < FloatDHT_3D.this.slices; i17++) {
                                    fArr2[i17] = fArr[(FloatDHT_3D.this.sliceStride * i17) + i15 + i16];
                                }
                                FloatDHT_3D.this.dhtSlices.forward(fArr2);
                                for (int i18 = 0; i18 < FloatDHT_3D.this.slices; i18++) {
                                    fArr[(FloatDHT_3D.this.sliceStride * i18) + i15 + i16] = fArr2[i18];
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
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i14 = 0; i14 < this.slices; i14++) {
                int i15 = this.sliceStride * i14;
                for (int i16 = 0; i16 < this.rows; i16++) {
                    this.dhtColumns.forward(fArr, (this.rowStride * i16) + i15);
                }
            }
            float[] fArr2 = new float[this.rows];
            int i17 = 0;
            while (true) {
                i = this.slices;
                if (i17 >= i) {
                    break;
                }
                int i18 = this.sliceStride * i17;
                for (int i19 = 0; i19 < this.columns; i19++) {
                    for (int i20 = 0; i20 < this.rows; i20++) {
                        fArr2[i20] = fArr[(this.rowStride * i20) + i18 + i19];
                    }
                    this.dhtRows.forward(fArr2);
                    for (int i21 = 0; i21 < this.rows; i21++) {
                        fArr[(this.rowStride * i21) + i18 + i19] = fArr2[i21];
                    }
                }
                i17++;
            }
            float[] fArr3 = new float[i];
            for (int i22 = 0; i22 < this.rows; i22++) {
                int i23 = this.rowStride * i22;
                for (int i24 = 0; i24 < this.columns; i24++) {
                    for (int i25 = 0; i25 < this.slices; i25++) {
                        fArr3[i25] = fArr[(this.sliceStride * i25) + i23 + i24];
                    }
                    this.dhtSlices.forward(fArr3);
                    for (int i26 = 0; i26 < this.slices; i26++) {
                        fArr[(this.sliceStride * i26) + i23 + i24] = fArr3[i26];
                    }
                }
            }
        }
        yTransform(fArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void forward(final pl.edu.icm.jlargearrays.FloatLargeArray r20) {
        /*
            Method dump skipped, instructions count: 508
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.FloatDHT_3D.forward(pl.edu.icm.jlargearrays.FloatLargeArray):void");
    }

    public void forward(final float[][][] fArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(-1, fArr, true);
                ddxt3db_subth(-1, fArr, true);
            } else {
                ddxt3da_sub(-1, fArr, true);
                ddxt3db_sub(-1, fArr, true);
            }
            yTransform(fArr);
            return;
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            for (int i9 = 0; i9 < FloatDHT_3D.this.rows; i9++) {
                                FloatDHT_3D.this.dhtColumns.forward(fArr[i8][i9]);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < FloatDHT_3D.this.columns; i12++) {
                                for (int i13 = 0; i13 < FloatDHT_3D.this.rows; i13++) {
                                    fArr2[i13] = fArr[i11][i13][i12];
                                }
                                FloatDHT_3D.this.dhtRows.forward(fArr2);
                                for (int i14 = 0; i14 < FloatDHT_3D.this.rows; i14++) {
                                    fArr[i11][i14][i12] = fArr2[i14];
                                }
                            }
                        }
                    }
                });
                i8++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.9
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            for (int i15 = 0; i15 < FloatDHT_3D.this.columns; i15++) {
                                for (int i16 = 0; i16 < FloatDHT_3D.this.slices; i16++) {
                                    fArr2[i16] = fArr[i16][i14][i15];
                                }
                                FloatDHT_3D.this.dhtSlices.forward(fArr2);
                                for (int i17 = 0; i17 < FloatDHT_3D.this.slices; i17++) {
                                    fArr[i17][i14][i15] = fArr2[i17];
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
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i14 = 0; i14 < this.slices; i14++) {
                for (int i15 = 0; i15 < this.rows; i15++) {
                    this.dhtColumns.forward(fArr[i14][i15]);
                }
            }
            float[] fArr2 = new float[this.rows];
            int i16 = 0;
            while (true) {
                i = this.slices;
                if (i16 >= i) {
                    break;
                }
                for (int i17 = 0; i17 < this.columns; i17++) {
                    for (int i18 = 0; i18 < this.rows; i18++) {
                        fArr2[i18] = fArr[i16][i18][i17];
                    }
                    this.dhtRows.forward(fArr2);
                    for (int i19 = 0; i19 < this.rows; i19++) {
                        fArr[i16][i19][i17] = fArr2[i19];
                    }
                }
                i16++;
            }
            float[] fArr3 = new float[i];
            for (int i20 = 0; i20 < this.rows; i20++) {
                for (int i21 = 0; i21 < this.columns; i21++) {
                    for (int i22 = 0; i22 < this.slices; i22++) {
                        fArr3[i22] = fArr[i22][i20][i21];
                    }
                    this.dhtSlices.forward(fArr3);
                    for (int i23 = 0; i23 < this.slices; i23++) {
                        fArr[i23][i20][i21] = fArr3[i23];
                    }
                }
            }
        }
        yTransform(fArr);
    }

    public void inverse(final float[] fArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(1, fArr, z);
                ddxt3db_subth(1, fArr, z);
            } else {
                ddxt3da_sub(1, fArr, z);
                ddxt3db_sub(1, fArr, z);
            }
            yTransform(fArr);
            return;
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.10
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            int i9 = FloatDHT_3D.this.sliceStride * i8;
                            for (int i10 = 0; i10 < FloatDHT_3D.this.rows; i10++) {
                                FloatDHT_3D.this.dhtColumns.inverse(fArr, (FloatDHT_3D.this.rowStride * i10) + i9, z);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.11
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            int i12 = FloatDHT_3D.this.sliceStride * i11;
                            for (int i13 = 0; i13 < FloatDHT_3D.this.columns; i13++) {
                                for (int i14 = 0; i14 < FloatDHT_3D.this.rows; i14++) {
                                    fArr2[i14] = fArr[(FloatDHT_3D.this.rowStride * i14) + i12 + i13];
                                }
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, z);
                                for (int i15 = 0; i15 < FloatDHT_3D.this.rows; i15++) {
                                    fArr[(FloatDHT_3D.this.rowStride * i15) + i12 + i13] = fArr2[i15];
                                }
                            }
                        }
                    }
                });
                i8++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.12
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            int i15 = FloatDHT_3D.this.rowStride * i14;
                            for (int i16 = 0; i16 < FloatDHT_3D.this.columns; i16++) {
                                for (int i17 = 0; i17 < FloatDHT_3D.this.slices; i17++) {
                                    fArr2[i17] = fArr[(FloatDHT_3D.this.sliceStride * i17) + i15 + i16];
                                }
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, z);
                                for (int i18 = 0; i18 < FloatDHT_3D.this.slices; i18++) {
                                    fArr[(FloatDHT_3D.this.sliceStride * i18) + i15 + i16] = fArr2[i18];
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
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i14 = 0; i14 < this.slices; i14++) {
                int i15 = this.sliceStride * i14;
                for (int i16 = 0; i16 < this.rows; i16++) {
                    this.dhtColumns.inverse(fArr, (this.rowStride * i16) + i15, z);
                }
            }
            float[] fArr2 = new float[this.rows];
            int i17 = 0;
            while (true) {
                i = this.slices;
                if (i17 >= i) {
                    break;
                }
                int i18 = this.sliceStride * i17;
                for (int i19 = 0; i19 < this.columns; i19++) {
                    for (int i20 = 0; i20 < this.rows; i20++) {
                        fArr2[i20] = fArr[(this.rowStride * i20) + i18 + i19];
                    }
                    this.dhtRows.inverse(fArr2, z);
                    for (int i21 = 0; i21 < this.rows; i21++) {
                        fArr[(this.rowStride * i21) + i18 + i19] = fArr2[i21];
                    }
                }
                i17++;
            }
            float[] fArr3 = new float[i];
            for (int i22 = 0; i22 < this.rows; i22++) {
                int i23 = this.rowStride * i22;
                for (int i24 = 0; i24 < this.columns; i24++) {
                    for (int i25 = 0; i25 < this.slices; i25++) {
                        fArr3[i25] = fArr[(this.sliceStride * i25) + i23 + i24];
                    }
                    this.dhtSlices.inverse(fArr3, z);
                    for (int i26 = 0; i26 < this.slices; i26++) {
                        fArr[(this.sliceStride * i26) + i23 + i24] = fArr3[i26];
                    }
                }
            }
        }
        yTransform(fArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void inverse(final pl.edu.icm.jlargearrays.FloatLargeArray r22, final boolean r23) {
        /*
            Method dump skipped, instructions count: 530
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.FloatDHT_3D.inverse(pl.edu.icm.jlargearrays.FloatLargeArray, boolean):void");
    }

    public void inverse(final float[][][] fArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(1, fArr, z);
                ddxt3db_subth(1, fArr, z);
            } else {
                ddxt3da_sub(1, fArr, z);
                ddxt3db_sub(1, fArr, z);
            }
            yTransform(fArr);
            return;
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.16
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            for (int i9 = 0; i9 < FloatDHT_3D.this.rows; i9++) {
                                FloatDHT_3D.this.dhtColumns.inverse(fArr[i8][i9], z);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.17
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < FloatDHT_3D.this.columns; i12++) {
                                for (int i13 = 0; i13 < FloatDHT_3D.this.rows; i13++) {
                                    fArr2[i13] = fArr[i11][i13][i12];
                                }
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, z);
                                for (int i14 = 0; i14 < FloatDHT_3D.this.rows; i14++) {
                                    fArr[i11][i14][i12] = fArr2[i14];
                                }
                            }
                        }
                    }
                });
                i8++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.18
                    @Override // java.lang.Runnable
                    public void run() {
                        float[] fArr2 = new float[FloatDHT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            for (int i15 = 0; i15 < FloatDHT_3D.this.columns; i15++) {
                                for (int i16 = 0; i16 < FloatDHT_3D.this.slices; i16++) {
                                    fArr2[i16] = fArr[i16][i14][i15];
                                }
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, z);
                                for (int i17 = 0; i17 < FloatDHT_3D.this.slices; i17++) {
                                    fArr[i17][i14][i15] = fArr2[i17];
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
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
            } catch (ExecutionException e6) {
                Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
            }
        } else {
            for (int i14 = 0; i14 < this.slices; i14++) {
                for (int i15 = 0; i15 < this.rows; i15++) {
                    this.dhtColumns.inverse(fArr[i14][i15], z);
                }
            }
            float[] fArr2 = new float[this.rows];
            int i16 = 0;
            while (true) {
                i = this.slices;
                if (i16 >= i) {
                    break;
                }
                for (int i17 = 0; i17 < this.columns; i17++) {
                    for (int i18 = 0; i18 < this.rows; i18++) {
                        fArr2[i18] = fArr[i16][i18][i17];
                    }
                    this.dhtRows.inverse(fArr2, z);
                    for (int i19 = 0; i19 < this.rows; i19++) {
                        fArr[i16][i19][i17] = fArr2[i19];
                    }
                }
                i16++;
            }
            float[] fArr3 = new float[i];
            for (int i20 = 0; i20 < this.rows; i20++) {
                for (int i21 = 0; i21 < this.columns; i21++) {
                    for (int i22 = 0; i22 < this.slices; i22++) {
                        fArr3[i22] = fArr[i22][i20][i21];
                    }
                    this.dhtSlices.inverse(fArr3, z);
                    for (int i23 = 0; i23 < this.slices; i23++) {
                        fArr[i23][i20][i21] = fArr3[i23];
                    }
                }
            }
        }
        yTransform(fArr);
    }

    private void ddxt3da_sub(int i, float[] fArr, boolean z) {
        int i2 = this.rows * 4;
        if (this.columns == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            for (int i3 = 0; i3 < this.slices; i3++) {
                int i4 = this.sliceStride * i3;
                for (int i5 = 0; i5 < this.rows; i5++) {
                    this.dhtColumns.forward(fArr, (this.rowStride * i5) + i4);
                }
                int i6 = this.columns;
                if (i6 > 2) {
                    for (int i7 = 0; i7 < this.columns; i7 += 4) {
                        int i8 = 0;
                        while (true) {
                            int i9 = this.rows;
                            if (i8 >= i9) {
                                break;
                            }
                            int i10 = (this.rowStride * i8) + i4 + i7;
                            int i11 = i9 + i8;
                            fArr2[i8] = fArr[i10];
                            fArr2[i11] = fArr[i10 + 1];
                            fArr2[i11 + i9] = fArr[i10 + 2];
                            fArr2[i11 + (i9 * 2)] = fArr[i10 + 3];
                            i8++;
                        }
                        this.dhtRows.forward(fArr2, 0);
                        this.dhtRows.forward(fArr2, this.rows);
                        this.dhtRows.forward(fArr2, this.rows * 2);
                        this.dhtRows.forward(fArr2, this.rows * 3);
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 < i13) {
                                int i14 = (this.rowStride * i12) + i4 + i7;
                                int i15 = i13 + i12;
                                fArr[i14] = fArr2[i12];
                                fArr[i14 + 1] = fArr2[i15];
                                fArr[i14 + 2] = fArr2[i15 + i13];
                                fArr[i14 + 3] = fArr2[i15 + (i13 * 2)];
                                i12++;
                            }
                        }
                    }
                } else if (i6 == 2) {
                    int i16 = 0;
                    while (true) {
                        int i17 = this.rows;
                        if (i16 >= i17) {
                            break;
                        }
                        int i18 = (this.rowStride * i16) + i4;
                        fArr2[i16] = fArr[i18];
                        fArr2[i17 + i16] = fArr[i18 + 1];
                        i16++;
                    }
                    this.dhtRows.forward(fArr2, 0);
                    this.dhtRows.forward(fArr2, this.rows);
                    int i19 = 0;
                    while (true) {
                        int i20 = this.rows;
                        if (i19 < i20) {
                            int i21 = (this.rowStride * i19) + i4;
                            fArr[i21] = fArr2[i19];
                            fArr[i21 + 1] = fArr2[i20 + i19];
                            i19++;
                        }
                    }
                }
            }
            return;
        }
        for (int i22 = 0; i22 < this.slices; i22++) {
            int i23 = this.sliceStride * i22;
            for (int i24 = 0; i24 < this.rows; i24++) {
                this.dhtColumns.inverse(fArr, (this.rowStride * i24) + i23, z);
            }
            int i25 = this.columns;
            if (i25 > 2) {
                for (int i26 = 0; i26 < this.columns; i26 += 4) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.rows;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = (this.rowStride * i27) + i23 + i26;
                        int i30 = i28 + i27;
                        fArr2[i27] = fArr[i29];
                        fArr2[i30] = fArr[i29 + 1];
                        fArr2[i30 + i28] = fArr[i29 + 2];
                        fArr2[i30 + (i28 * 2)] = fArr[i29 + 3];
                        i27++;
                    }
                    this.dhtRows.inverse(fArr2, 0, z);
                    this.dhtRows.inverse(fArr2, this.rows, z);
                    this.dhtRows.inverse(fArr2, this.rows * 2, z);
                    this.dhtRows.inverse(fArr2, this.rows * 3, z);
                    int i31 = 0;
                    while (true) {
                        int i32 = this.rows;
                        if (i31 < i32) {
                            int i33 = (this.rowStride * i31) + i23 + i26;
                            int i34 = i32 + i31;
                            fArr[i33] = fArr2[i31];
                            fArr[i33 + 1] = fArr2[i34];
                            fArr[i33 + 2] = fArr2[i34 + i32];
                            fArr[i33 + 3] = fArr2[i34 + (i32 * 2)];
                            i31++;
                        }
                    }
                }
            } else if (i25 == 2) {
                int i35 = 0;
                while (true) {
                    int i36 = this.rows;
                    if (i35 >= i36) {
                        break;
                    }
                    int i37 = (this.rowStride * i35) + i23;
                    fArr2[i35] = fArr[i37];
                    fArr2[i36 + i35] = fArr[i37 + 1];
                    i35++;
                }
                this.dhtRows.inverse(fArr2, 0, z);
                this.dhtRows.inverse(fArr2, this.rows, z);
                int i38 = 0;
                while (true) {
                    int i39 = this.rows;
                    if (i38 < i39) {
                        int i40 = (this.rowStride * i38) + i23;
                        fArr[i40] = fArr2[i38];
                        fArr[i40 + 1] = fArr2[i39 + i38];
                        i38++;
                    }
                }
            }
        }
    }

    private void ddxt3da_sub(int i, FloatLargeArray floatLargeArray, boolean z) {
        long j;
        long j2;
        long j3 = this.rowsl * 4;
        long j4 = 2;
        if (this.columnsl == 2) {
            j3 >>= 1;
        }
        FloatLargeArray floatLargeArray2 = new FloatLargeArray(j3);
        if (i == -1) {
            long j5 = 0;
            while (j5 < this.slicesl) {
                long j6 = this.sliceStridel * j5;
                for (long j7 = 0; j7 < this.rowsl; j7++) {
                    this.dhtColumns.forward(floatLargeArray, (this.rowStridel * j7) + j6);
                }
                long j8 = this.columnsl;
                if (j8 > j4) {
                    long j9 = 0;
                    while (j9 < this.columnsl) {
                        long j10 = 0;
                        while (true) {
                            long j11 = this.rowsl;
                            if (j10 >= j11) {
                                break;
                            }
                            long j12 = j5;
                            long j13 = (this.rowStridel * j10) + j6 + j9;
                            long j14 = j11 + j10;
                            floatLargeArray2.setFloat(j10, floatLargeArray.getFloat(j13));
                            floatLargeArray2.setFloat(j14, floatLargeArray.getFloat(j13 + 1));
                            floatLargeArray2.setFloat(this.rowsl + j14, floatLargeArray.getFloat(j13 + 2));
                            floatLargeArray2.setFloat(j14 + (this.rowsl * 2), floatLargeArray.getFloat(j13 + 3));
                            j10++;
                            j5 = j12;
                            j9 = j9;
                            j6 = j6;
                        }
                        long j15 = j5;
                        long j16 = j6;
                        long j17 = j9;
                        this.dhtRows.forward(floatLargeArray2, 0L);
                        this.dhtRows.forward(floatLargeArray2, this.rowsl);
                        this.dhtRows.forward(floatLargeArray2, this.rowsl * 2);
                        this.dhtRows.forward(floatLargeArray2, this.rowsl * 3);
                        long j18 = 0;
                        while (true) {
                            long j19 = this.rowsl;
                            if (j18 < j19) {
                                long j20 = j16 + (this.rowStridel * j18) + j17;
                                long j21 = j19 + j18;
                                floatLargeArray.setFloat(j20, floatLargeArray2.getFloat(j18));
                                floatLargeArray.setFloat(j20 + 1, floatLargeArray2.getFloat(j21));
                                floatLargeArray.setFloat(j20 + 2, floatLargeArray2.getFloat(this.rowsl + j21));
                                floatLargeArray.setFloat(j20 + 3, floatLargeArray2.getFloat(j21 + (this.rowsl * 2)));
                                j18++;
                            }
                        }
                        j9 = j17 + 4;
                        j5 = j15;
                        j6 = j16;
                    }
                    j2 = j5;
                } else {
                    j2 = j5;
                    if (j8 == j4) {
                        for (long j22 = 0; j22 < this.rowsl; j22++) {
                            long j23 = j6 + (this.rowStridel * j22);
                            floatLargeArray2.setFloat(j22, floatLargeArray.getFloat(j23));
                            floatLargeArray2.setFloat(this.rowsl + j22, floatLargeArray.getFloat(j23 + 1));
                        }
                        this.dhtRows.forward(floatLargeArray2, 0L);
                        this.dhtRows.forward(floatLargeArray2, this.rowsl);
                        for (long j24 = 0; j24 < this.rowsl; j24++) {
                            long j25 = j6 + (this.rowStridel * j24);
                            floatLargeArray.setFloat(j25, floatLargeArray2.getFloat(j24));
                            floatLargeArray.setFloat(j25 + 1, floatLargeArray2.getFloat(this.rowsl + j24));
                        }
                    }
                }
                j5 = j2 + 1;
                j4 = 2;
            }
            return;
        }
        long j26 = 0;
        while (j26 < this.slicesl) {
            long j27 = this.sliceStridel * j26;
            for (long j28 = 0; j28 < this.rowsl; j28++) {
                this.dhtColumns.inverse(floatLargeArray, (this.rowStridel * j28) + j27, z);
            }
            long j29 = this.columnsl;
            if (j29 > 2) {
                long j30 = 0;
                while (j30 < this.columnsl) {
                    long j31 = 0;
                    while (true) {
                        long j32 = this.rowsl;
                        if (j31 >= j32) {
                            break;
                        }
                        long j33 = j26;
                        long j34 = (this.rowStridel * j31) + j27 + j30;
                        long j35 = j32 + j31;
                        floatLargeArray2.setFloat(j31, floatLargeArray.getFloat(j34));
                        floatLargeArray2.setFloat(j35, floatLargeArray.getFloat(j34 + 1));
                        floatLargeArray2.setFloat(this.rowsl + j35, floatLargeArray.getFloat(j34 + 2));
                        floatLargeArray2.setFloat(j35 + (this.rowsl * 2), floatLargeArray.getFloat(j34 + 3));
                        j31++;
                        j26 = j33;
                        j30 = j30;
                        j27 = j27;
                    }
                    long j36 = j26;
                    long j37 = j27;
                    long j38 = j30;
                    this.dhtRows.inverse(floatLargeArray2, 0L, z);
                    this.dhtRows.inverse(floatLargeArray2, this.rowsl, z);
                    this.dhtRows.inverse(floatLargeArray2, this.rowsl * 2, z);
                    this.dhtRows.inverse(floatLargeArray2, this.rowsl * 3, z);
                    long j39 = 0;
                    while (true) {
                        long j40 = this.rowsl;
                        if (j39 < j40) {
                            long j41 = j37 + (this.rowStridel * j39) + j38;
                            long j42 = j40 + j39;
                            floatLargeArray.setFloat(j41, floatLargeArray2.getFloat(j39));
                            floatLargeArray.setFloat(j41 + 1, floatLargeArray2.getFloat(j42));
                            floatLargeArray.setFloat(j41 + 2, floatLargeArray2.getFloat(this.rowsl + j42));
                            floatLargeArray.setFloat(j41 + 3, floatLargeArray2.getFloat(j42 + (this.rowsl * 2)));
                            j39++;
                        }
                    }
                    j30 = j38 + 4;
                    j26 = j36;
                    j27 = j37;
                }
                j = j26;
            } else {
                j = j26;
                if (j29 == 2) {
                    for (long j43 = 0; j43 < this.rowsl; j43++) {
                        long j44 = j27 + (this.rowStridel * j43);
                        floatLargeArray2.setFloat(j43, floatLargeArray.getFloat(j44));
                        floatLargeArray2.setFloat(this.rowsl + j43, floatLargeArray.getFloat(j44 + 1));
                    }
                    this.dhtRows.inverse(floatLargeArray2, 0L, z);
                    this.dhtRows.inverse(floatLargeArray2, this.rowsl, z);
                    for (long j45 = 0; j45 < this.rowsl; j45++) {
                        long j46 = j27 + (this.rowStridel * j45);
                        floatLargeArray.setFloat(j46, floatLargeArray2.getFloat(j45));
                        floatLargeArray.setFloat(j46 + 1, floatLargeArray2.getFloat(this.rowsl + j45));
                    }
                }
                j26 = j + 1;
            }
            j26 = j + 1;
        }
    }

    private void ddxt3da_sub(int i, float[][][] fArr, boolean z) {
        int i2 = this.rows * 4;
        if (this.columnsl == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            for (int i3 = 0; i3 < this.slices; i3++) {
                for (int i4 = 0; i4 < this.rows; i4++) {
                    this.dhtColumns.forward(fArr[i3][i4]);
                }
                int i5 = this.columns;
                if (i5 > 2) {
                    for (int i6 = 0; i6 < this.columns; i6 += 4) {
                        int i7 = 0;
                        while (true) {
                            int i8 = this.rows;
                            if (i7 >= i8) {
                                break;
                            }
                            int i9 = i8 + i7;
                            float[] fArr3 = fArr[i3][i7];
                            fArr2[i7] = fArr3[i6];
                            fArr2[i9] = fArr3[i6 + 1];
                            fArr2[i9 + i8] = fArr3[i6 + 2];
                            fArr2[i9 + (i8 * 2)] = fArr3[i6 + 3];
                            i7++;
                        }
                        this.dhtRows.forward(fArr2, 0);
                        this.dhtRows.forward(fArr2, this.rows);
                        this.dhtRows.forward(fArr2, this.rows * 2);
                        this.dhtRows.forward(fArr2, this.rows * 3);
                        int i10 = 0;
                        while (true) {
                            int i11 = this.rows;
                            if (i10 < i11) {
                                int i12 = i11 + i10;
                                float[] fArr4 = fArr[i3][i10];
                                fArr4[i6] = fArr2[i10];
                                fArr4[i6 + 1] = fArr2[i12];
                                fArr4[i6 + 2] = fArr2[i12 + i11];
                                fArr4[i6 + 3] = fArr2[i12 + (i11 * 2)];
                                i10++;
                            }
                        }
                    }
                } else if (i5 == 2) {
                    int i13 = 0;
                    while (true) {
                        int i14 = this.rows;
                        if (i13 >= i14) {
                            break;
                        }
                        float[] fArr5 = fArr[i3][i13];
                        fArr2[i13] = fArr5[0];
                        fArr2[i14 + i13] = fArr5[1];
                        i13++;
                    }
                    this.dhtRows.forward(fArr2, 0);
                    this.dhtRows.forward(fArr2, this.rows);
                    int i15 = 0;
                    while (true) {
                        int i16 = this.rows;
                        if (i15 < i16) {
                            float[] fArr6 = fArr[i3][i15];
                            fArr6[0] = fArr2[i15];
                            fArr6[1] = fArr2[i16 + i15];
                            i15++;
                        }
                    }
                }
            }
            return;
        }
        for (int i17 = 0; i17 < this.slices; i17++) {
            for (int i18 = 0; i18 < this.rows; i18++) {
                this.dhtColumns.inverse(fArr[i17][i18], z);
            }
            int i19 = this.columns;
            if (i19 > 2) {
                for (int i20 = 0; i20 < this.columns; i20 += 4) {
                    int i21 = 0;
                    while (true) {
                        int i22 = this.rows;
                        if (i21 >= i22) {
                            break;
                        }
                        int i23 = i22 + i21;
                        float[] fArr7 = fArr[i17][i21];
                        fArr2[i21] = fArr7[i20];
                        fArr2[i23] = fArr7[i20 + 1];
                        fArr2[i23 + i22] = fArr7[i20 + 2];
                        fArr2[i23 + (i22 * 2)] = fArr7[i20 + 3];
                        i21++;
                    }
                    this.dhtRows.inverse(fArr2, 0, z);
                    this.dhtRows.inverse(fArr2, this.rows, z);
                    this.dhtRows.inverse(fArr2, this.rows * 2, z);
                    this.dhtRows.inverse(fArr2, this.rows * 3, z);
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 < i25) {
                            int i26 = i25 + i24;
                            float[] fArr8 = fArr[i17][i24];
                            fArr8[i20] = fArr2[i24];
                            fArr8[i20 + 1] = fArr2[i26];
                            fArr8[i20 + 2] = fArr2[i26 + i25];
                            fArr8[i20 + 3] = fArr2[i26 + (i25 * 2)];
                            i24++;
                        }
                    }
                }
            } else if (i19 == 2) {
                int i27 = 0;
                while (true) {
                    int i28 = this.rows;
                    if (i27 >= i28) {
                        break;
                    }
                    float[] fArr9 = fArr[i17][i27];
                    fArr2[i27] = fArr9[0];
                    fArr2[i28 + i27] = fArr9[1];
                    i27++;
                }
                this.dhtRows.inverse(fArr2, 0, z);
                this.dhtRows.inverse(fArr2, this.rows, z);
                int i29 = 0;
                while (true) {
                    int i30 = this.rows;
                    if (i29 < i30) {
                        float[] fArr10 = fArr[i17][i29];
                        fArr10[0] = fArr2[i29];
                        fArr10[1] = fArr2[i30 + i29];
                        i29++;
                    }
                }
            }
        }
    }

    private void ddxt3db_sub(int i, float[] fArr, boolean z) {
        int i2 = this.slices * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            if (i3 <= 2) {
                if (i3 == 2) {
                    for (int i4 = 0; i4 < this.rows; i4++) {
                        int i5 = this.rowStride * i4;
                        int i6 = 0;
                        while (true) {
                            int i7 = this.slices;
                            if (i6 >= i7) {
                                break;
                            }
                            int i8 = (this.sliceStride * i6) + i5;
                            fArr2[i6] = fArr[i8];
                            fArr2[i7 + i6] = fArr[i8 + 1];
                            i6++;
                        }
                        this.dhtSlices.forward(fArr2, 0);
                        this.dhtSlices.forward(fArr2, this.slices);
                        int i9 = 0;
                        while (true) {
                            int i10 = this.slices;
                            if (i9 < i10) {
                                int i11 = (this.sliceStride * i9) + i5;
                                fArr[i11] = fArr2[i9];
                                fArr[i11 + 1] = fArr2[i10 + i9];
                                i9++;
                            }
                        }
                    }
                    return;
                }
                return;
            }
            for (int i12 = 0; i12 < this.rows; i12++) {
                int i13 = this.rowStride * i12;
                for (int i14 = 0; i14 < this.columns; i14 += 4) {
                    int i15 = 0;
                    while (true) {
                        int i16 = this.slices;
                        if (i15 >= i16) {
                            break;
                        }
                        int i17 = (this.sliceStride * i15) + i13 + i14;
                        int i18 = i16 + i15;
                        fArr2[i15] = fArr[i17];
                        fArr2[i18] = fArr[i17 + 1];
                        fArr2[i18 + i16] = fArr[i17 + 2];
                        fArr2[i18 + (i16 * 2)] = fArr[i17 + 3];
                        i15++;
                    }
                    this.dhtSlices.forward(fArr2, 0);
                    this.dhtSlices.forward(fArr2, this.slices);
                    this.dhtSlices.forward(fArr2, this.slices * 2);
                    this.dhtSlices.forward(fArr2, this.slices * 3);
                    int i19 = 0;
                    while (true) {
                        int i20 = this.slices;
                        if (i19 < i20) {
                            int i21 = (this.sliceStride * i19) + i13 + i14;
                            int i22 = i20 + i19;
                            fArr[i21] = fArr2[i19];
                            fArr[i21 + 1] = fArr2[i22];
                            fArr[i21 + 2] = fArr2[i22 + i20];
                            fArr[i21 + 3] = fArr2[i22 + (i20 * 2)];
                            i19++;
                        }
                    }
                }
            }
            return;
        }
        if (i3 <= 2) {
            if (i3 == 2) {
                for (int i23 = 0; i23 < this.rows; i23++) {
                    int i24 = this.rowStride * i23;
                    int i25 = 0;
                    while (true) {
                        int i26 = this.slices;
                        if (i25 >= i26) {
                            break;
                        }
                        int i27 = (this.sliceStride * i25) + i24;
                        fArr2[i25] = fArr[i27];
                        fArr2[i26 + i25] = fArr[i27 + 1];
                        i25++;
                    }
                    this.dhtSlices.inverse(fArr2, 0, z);
                    this.dhtSlices.inverse(fArr2, this.slices, z);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.slices;
                        if (i28 < i29) {
                            int i30 = (this.sliceStride * i28) + i24;
                            fArr[i30] = fArr2[i28];
                            fArr[i30 + 1] = fArr2[i29 + i28];
                            i28++;
                        }
                    }
                }
                return;
            }
            return;
        }
        for (int i31 = 0; i31 < this.rows; i31++) {
            int i32 = this.rowStride * i31;
            for (int i33 = 0; i33 < this.columns; i33 += 4) {
                int i34 = 0;
                while (true) {
                    int i35 = this.slices;
                    if (i34 >= i35) {
                        break;
                    }
                    int i36 = (this.sliceStride * i34) + i32 + i33;
                    int i37 = i35 + i34;
                    fArr2[i34] = fArr[i36];
                    fArr2[i37] = fArr[i36 + 1];
                    fArr2[i37 + i35] = fArr[i36 + 2];
                    fArr2[i37 + (i35 * 2)] = fArr[i36 + 3];
                    i34++;
                }
                this.dhtSlices.inverse(fArr2, 0, z);
                this.dhtSlices.inverse(fArr2, this.slices, z);
                this.dhtSlices.inverse(fArr2, this.slices * 2, z);
                this.dhtSlices.inverse(fArr2, this.slices * 3, z);
                int i38 = 0;
                while (true) {
                    int i39 = this.slices;
                    if (i38 < i39) {
                        int i40 = (this.sliceStride * i38) + i32 + i33;
                        int i41 = i39 + i38;
                        fArr[i40] = fArr2[i38];
                        fArr[i40 + 1] = fArr2[i41];
                        fArr[i40 + 2] = fArr2[i41 + i39];
                        fArr[i40 + 3] = fArr2[i41 + (i39 * 2)];
                        i38++;
                    }
                }
            }
        }
    }

    private void ddxt3db_sub(int i, FloatLargeArray floatLargeArray, boolean z) {
        long j = this.slicesl * 4;
        long j2 = 2;
        if (this.columnsl == 2) {
            j >>= 1;
        }
        FloatLargeArray floatLargeArray2 = new FloatLargeArray(j);
        if (i == -1) {
            long j3 = this.columnsl;
            if (j3 <= 2) {
                if (j3 == 2) {
                    for (long j4 = 0; j4 < this.rowsl; j4++) {
                        long j5 = this.rowStridel * j4;
                        for (long j6 = 0; j6 < this.slicesl; j6++) {
                            long j7 = (this.sliceStridel * j6) + j5;
                            floatLargeArray2.setFloat(j6, floatLargeArray.getFloat(j7));
                            floatLargeArray2.setFloat(this.slicesl + j6, floatLargeArray.getFloat(j7 + 1));
                        }
                        this.dhtSlices.forward(floatLargeArray2, 0L);
                        this.dhtSlices.forward(floatLargeArray2, this.slicesl);
                        for (long j8 = 0; j8 < this.slicesl; j8++) {
                            long j9 = (this.sliceStridel * j8) + j5;
                            floatLargeArray.setFloat(j9, floatLargeArray2.getFloat(j8));
                            floatLargeArray.setFloat(j9 + 1, floatLargeArray2.getFloat(this.slicesl + j8));
                        }
                    }
                    return;
                }
                return;
            }
            long j10 = 0;
            while (j10 < this.rowsl) {
                long j11 = this.rowStridel * j10;
                long j12 = 0;
                while (j12 < this.columnsl) {
                    long j13 = 0;
                    while (true) {
                        long j14 = this.slicesl;
                        if (j13 >= j14) {
                            break;
                        }
                        long j15 = (this.sliceStridel * j13) + j11 + j12;
                        long j16 = j14 + j13;
                        floatLargeArray2.setFloat(j13, floatLargeArray.getFloat(j15));
                        floatLargeArray2.setFloat(j16, floatLargeArray.getFloat(j15 + 1));
                        floatLargeArray2.setFloat(this.slicesl + j16, floatLargeArray.getFloat(j15 + 2));
                        floatLargeArray2.setFloat(j16 + (this.slicesl * 2), floatLargeArray.getFloat(j15 + 3));
                        j13++;
                        j10 = j10;
                        j11 = j11;
                    }
                    long j17 = j10;
                    long j18 = j11;
                    this.dhtSlices.forward(floatLargeArray2, 0L);
                    this.dhtSlices.forward(floatLargeArray2, this.slicesl);
                    this.dhtSlices.forward(floatLargeArray2, this.slicesl * 2);
                    this.dhtSlices.forward(floatLargeArray2, this.slicesl * 3);
                    long j19 = 0;
                    while (true) {
                        long j20 = this.slicesl;
                        if (j19 < j20) {
                            long j21 = (this.sliceStridel * j19) + j18 + j12;
                            long j22 = j20 + j19;
                            floatLargeArray.setFloat(j21, floatLargeArray2.getFloat(j19));
                            floatLargeArray.setFloat(j21 + 1, floatLargeArray2.getFloat(j22));
                            floatLargeArray.setFloat(j21 + 2, floatLargeArray2.getFloat(this.slicesl + j22));
                            floatLargeArray.setFloat(j21 + 3, floatLargeArray2.getFloat(j22 + (this.slicesl * 2)));
                            j19++;
                        }
                    }
                    j12 += 4;
                    j2 = 2;
                    j10 = j17;
                    j11 = j18;
                }
                j10++;
            }
            return;
        }
        long j23 = this.columnsl;
        if (j23 <= 2) {
            if (j23 == 2) {
                for (long j24 = 0; j24 < this.rowsl; j24++) {
                    long j25 = this.rowStridel * j24;
                    for (long j26 = 0; j26 < this.slicesl; j26++) {
                        long j27 = (this.sliceStridel * j26) + j25;
                        floatLargeArray2.setFloat(j26, floatLargeArray.getFloat(j27));
                        floatLargeArray2.setFloat(this.slicesl + j26, floatLargeArray.getFloat(j27 + 1));
                    }
                    this.dhtSlices.inverse(floatLargeArray2, 0L, z);
                    this.dhtSlices.inverse(floatLargeArray2, this.slicesl, z);
                    for (long j28 = 0; j28 < this.slicesl; j28++) {
                        long j29 = (this.sliceStridel * j28) + j25;
                        floatLargeArray.setFloat(j29, floatLargeArray2.getFloat(j28));
                        floatLargeArray.setFloat(j29 + 1, floatLargeArray2.getFloat(this.slicesl + j28));
                    }
                }
                return;
            }
            return;
        }
        long j30 = 0;
        while (j30 < this.rowsl) {
            long j31 = this.rowStridel * j30;
            long j32 = 0;
            while (j32 < this.columnsl) {
                long j33 = 0;
                while (true) {
                    long j34 = this.slicesl;
                    if (j33 >= j34) {
                        break;
                    }
                    long j35 = j30;
                    long j36 = (this.sliceStridel * j33) + j31 + j32;
                    long j37 = j34 + j33;
                    floatLargeArray2.setFloat(j33, floatLargeArray.getFloat(j36));
                    floatLargeArray2.setFloat(j37, floatLargeArray.getFloat(j36 + 1));
                    floatLargeArray2.setFloat(this.slicesl + j37, floatLargeArray.getFloat(j36 + 2));
                    floatLargeArray2.setFloat(j37 + (this.slicesl * 2), floatLargeArray.getFloat(j36 + 3));
                    j33++;
                    j30 = j35;
                    j32 = j32;
                    j31 = j31;
                }
                long j38 = j30;
                long j39 = j31;
                long j40 = j32;
                this.dhtSlices.inverse(floatLargeArray2, 0L, z);
                this.dhtSlices.inverse(floatLargeArray2, this.slicesl, z);
                this.dhtSlices.inverse(floatLargeArray2, this.slicesl * 2, z);
                this.dhtSlices.inverse(floatLargeArray2, this.slicesl * 3, z);
                long j41 = 0;
                while (true) {
                    long j42 = this.slicesl;
                    if (j41 < j42) {
                        long j43 = (this.sliceStridel * j41) + j39 + j40;
                        long j44 = j42 + j41;
                        floatLargeArray.setFloat(j43, floatLargeArray2.getFloat(j41));
                        floatLargeArray.setFloat(j43 + 1, floatLargeArray2.getFloat(j44));
                        floatLargeArray.setFloat(j43 + 2, floatLargeArray2.getFloat(this.slicesl + j44));
                        floatLargeArray.setFloat(j43 + 3, floatLargeArray2.getFloat(j44 + (this.slicesl * 2)));
                        j41++;
                    }
                }
                j32 = j40 + 4;
                j30 = j38;
                j31 = j39;
            }
            j30++;
        }
    }

    private void ddxt3db_sub(int i, float[][][] fArr, boolean z) {
        int i2 = this.slices * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        }
        float[] fArr2 = new float[i2];
        if (i == -1) {
            if (i3 <= 2) {
                if (i3 == 2) {
                    for (int i4 = 0; i4 < this.rows; i4++) {
                        int i5 = 0;
                        while (true) {
                            int i6 = this.slices;
                            if (i5 >= i6) {
                                break;
                            }
                            float[] fArr3 = fArr[i5][i4];
                            fArr2[i5] = fArr3[0];
                            fArr2[i6 + i5] = fArr3[1];
                            i5++;
                        }
                        this.dhtSlices.forward(fArr2, 0);
                        this.dhtSlices.forward(fArr2, this.slices);
                        int i7 = 0;
                        while (true) {
                            int i8 = this.slices;
                            if (i7 < i8) {
                                float[] fArr4 = fArr[i7][i4];
                                fArr4[0] = fArr2[i7];
                                fArr4[1] = fArr2[i8 + i7];
                                i7++;
                            }
                        }
                    }
                    return;
                }
                return;
            }
            for (int i9 = 0; i9 < this.rows; i9++) {
                for (int i10 = 0; i10 < this.columns; i10 += 4) {
                    int i11 = 0;
                    while (true) {
                        int i12 = this.slices;
                        if (i11 >= i12) {
                            break;
                        }
                        int i13 = i12 + i11;
                        float[] fArr5 = fArr[i11][i9];
                        fArr2[i11] = fArr5[i10];
                        fArr2[i13] = fArr5[i10 + 1];
                        fArr2[i13 + i12] = fArr5[i10 + 2];
                        fArr2[i13 + (i12 * 2)] = fArr5[i10 + 3];
                        i11++;
                    }
                    this.dhtSlices.forward(fArr2, 0);
                    this.dhtSlices.forward(fArr2, this.slices);
                    this.dhtSlices.forward(fArr2, this.slices * 2);
                    this.dhtSlices.forward(fArr2, this.slices * 3);
                    int i14 = 0;
                    while (true) {
                        int i15 = this.slices;
                        if (i14 < i15) {
                            int i16 = i15 + i14;
                            float[] fArr6 = fArr[i14][i9];
                            fArr6[i10] = fArr2[i14];
                            fArr6[i10 + 1] = fArr2[i16];
                            fArr6[i10 + 2] = fArr2[i16 + i15];
                            fArr6[i10 + 3] = fArr2[i16 + (i15 * 2)];
                            i14++;
                        }
                    }
                }
            }
            return;
        }
        if (i3 <= 2) {
            if (i3 == 2) {
                for (int i17 = 0; i17 < this.rows; i17++) {
                    int i18 = 0;
                    while (true) {
                        int i19 = this.slices;
                        if (i18 >= i19) {
                            break;
                        }
                        float[] fArr7 = fArr[i18][i17];
                        fArr2[i18] = fArr7[0];
                        fArr2[i19 + i18] = fArr7[1];
                        i18++;
                    }
                    this.dhtSlices.inverse(fArr2, 0, z);
                    this.dhtSlices.inverse(fArr2, this.slices, z);
                    int i20 = 0;
                    while (true) {
                        int i21 = this.slices;
                        if (i20 < i21) {
                            float[] fArr8 = fArr[i20][i17];
                            fArr8[0] = fArr2[i20];
                            fArr8[1] = fArr2[i21 + i20];
                            i20++;
                        }
                    }
                }
                return;
            }
            return;
        }
        for (int i22 = 0; i22 < this.rows; i22++) {
            for (int i23 = 0; i23 < this.columns; i23 += 4) {
                int i24 = 0;
                while (true) {
                    int i25 = this.slices;
                    if (i24 >= i25) {
                        break;
                    }
                    int i26 = i25 + i24;
                    float[] fArr9 = fArr[i24][i22];
                    fArr2[i24] = fArr9[i23];
                    fArr2[i26] = fArr9[i23 + 1];
                    fArr2[i26 + i25] = fArr9[i23 + 2];
                    fArr2[i26 + (i25 * 2)] = fArr9[i23 + 3];
                    i24++;
                }
                this.dhtSlices.inverse(fArr2, 0, z);
                this.dhtSlices.inverse(fArr2, this.slices, z);
                this.dhtSlices.inverse(fArr2, this.slices * 2, z);
                this.dhtSlices.inverse(fArr2, this.slices * 3, z);
                int i27 = 0;
                while (true) {
                    int i28 = this.slices;
                    if (i27 < i28) {
                        int i29 = i28 + i27;
                        float[] fArr10 = fArr[i27][i22];
                        fArr10[i23] = fArr2[i27];
                        fArr10[i23 + 1] = fArr2[i29];
                        fArr10[i23 + 2] = fArr2[i29 + i28];
                        fArr10[i23 + 3] = fArr2[i29 + (i28 * 2)];
                        i27++;
                    }
                }
            }
        }
    }

    private void ddxt3da_subth(final int i, final float[] fArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.slices;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = numberOfThreads2;
        int i3 = this.rows * 4;
        if (this.columns == 2) {
            i3 >>= 1;
        }
        final int i4 = i3;
        Future[] futureArr = new Future[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.19
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i4];
                    if (i == -1) {
                        int i7 = i6;
                        while (i7 < FloatDHT_3D.this.slices) {
                            int i8 = FloatDHT_3D.this.sliceStride * i7;
                            for (int i9 = 0; i9 < FloatDHT_3D.this.rows; i9++) {
                                FloatDHT_3D.this.dhtColumns.forward(fArr, (FloatDHT_3D.this.rowStride * i9) + i8);
                            }
                            if (FloatDHT_3D.this.columns > 2) {
                                for (int i10 = 0; i10 < FloatDHT_3D.this.columns; i10 += 4) {
                                    for (int i11 = 0; i11 < FloatDHT_3D.this.rows; i11++) {
                                        int i12 = (FloatDHT_3D.this.rowStride * i11) + i8 + i10;
                                        int i13 = FloatDHT_3D.this.rows + i11;
                                        float[] fArr3 = fArr;
                                        fArr2[i11] = fArr3[i12];
                                        fArr2[i13] = fArr3[i12 + 1];
                                        fArr2[FloatDHT_3D.this.rows + i13] = fArr[i12 + 2];
                                        fArr2[i13 + (FloatDHT_3D.this.rows * 2)] = fArr[i12 + 3];
                                    }
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, 0);
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows);
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows * 2);
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows * 3);
                                    for (int i14 = 0; i14 < FloatDHT_3D.this.rows; i14++) {
                                        int i15 = (FloatDHT_3D.this.rowStride * i14) + i8 + i10;
                                        int i16 = FloatDHT_3D.this.rows + i14;
                                        float[] fArr4 = fArr;
                                        fArr4[i15] = fArr2[i14];
                                        fArr4[i15 + 1] = fArr2[i16];
                                        fArr4[i15 + 2] = fArr2[FloatDHT_3D.this.rows + i16];
                                        fArr[i15 + 3] = fArr2[i16 + (FloatDHT_3D.this.rows * 2)];
                                    }
                                }
                            } else if (FloatDHT_3D.this.columns == 2) {
                                for (int i17 = 0; i17 < FloatDHT_3D.this.rows; i17++) {
                                    int i18 = (FloatDHT_3D.this.rowStride * i17) + i8;
                                    fArr2[i17] = fArr[i18];
                                    fArr2[FloatDHT_3D.this.rows + i17] = fArr[i18 + 1];
                                }
                                FloatDHT_3D.this.dhtRows.forward(fArr2, 0);
                                FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows);
                                for (int i19 = 0; i19 < FloatDHT_3D.this.rows; i19++) {
                                    int i20 = (FloatDHT_3D.this.rowStride * i19) + i8;
                                    float[] fArr5 = fArr;
                                    fArr5[i20] = fArr2[i19];
                                    fArr5[i20 + 1] = fArr2[FloatDHT_3D.this.rows + i19];
                                }
                            }
                            i7 += i2;
                        }
                        return;
                    }
                    int i21 = i6;
                    while (i21 < FloatDHT_3D.this.slices) {
                        int i22 = FloatDHT_3D.this.sliceStride * i21;
                        for (int i23 = 0; i23 < FloatDHT_3D.this.rows; i23++) {
                            FloatDHT_3D.this.dhtColumns.inverse(fArr, (FloatDHT_3D.this.rowStride * i23) + i22, z);
                        }
                        if (FloatDHT_3D.this.columns > 2) {
                            for (int i24 = 0; i24 < FloatDHT_3D.this.columns; i24 += 4) {
                                for (int i25 = 0; i25 < FloatDHT_3D.this.rows; i25++) {
                                    int i26 = (FloatDHT_3D.this.rowStride * i25) + i22 + i24;
                                    int i27 = FloatDHT_3D.this.rows + i25;
                                    float[] fArr6 = fArr;
                                    fArr2[i25] = fArr6[i26];
                                    fArr2[i27] = fArr6[i26 + 1];
                                    fArr2[FloatDHT_3D.this.rows + i27] = fArr[i26 + 2];
                                    fArr2[i27 + (FloatDHT_3D.this.rows * 2)] = fArr[i26 + 3];
                                }
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, 0, z);
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows, z);
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows * 2, z);
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows * 3, z);
                                for (int i28 = 0; i28 < FloatDHT_3D.this.rows; i28++) {
                                    int i29 = (FloatDHT_3D.this.rowStride * i28) + i22 + i24;
                                    int i30 = FloatDHT_3D.this.rows + i28;
                                    float[] fArr7 = fArr;
                                    fArr7[i29] = fArr2[i28];
                                    fArr7[i29 + 1] = fArr2[i30];
                                    fArr7[i29 + 2] = fArr2[FloatDHT_3D.this.rows + i30];
                                    fArr[i29 + 3] = fArr2[i30 + (FloatDHT_3D.this.rows * 2)];
                                }
                            }
                        } else if (FloatDHT_3D.this.columns == 2) {
                            for (int i31 = 0; i31 < FloatDHT_3D.this.rows; i31++) {
                                int i32 = (FloatDHT_3D.this.rowStride * i31) + i22;
                                fArr2[i31] = fArr[i32];
                                fArr2[FloatDHT_3D.this.rows + i31] = fArr[i32 + 1];
                            }
                            FloatDHT_3D.this.dhtRows.inverse(fArr2, 0, z);
                            FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows, z);
                            for (int i33 = 0; i33 < FloatDHT_3D.this.rows; i33++) {
                                int i34 = (FloatDHT_3D.this.rowStride * i33) + i22;
                                float[] fArr8 = fArr;
                                fArr8[i34] = fArr2[i33];
                                fArr8[i34 + 1] = fArr2[FloatDHT_3D.this.rows + i33];
                            }
                        }
                        i21 += i2;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3da_subth(final int i, final FloatLargeArray floatLargeArray, final boolean z) {
        long numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long numberOfThreads2 = this.slicesl;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = (int) numberOfThreads2;
        long j = this.rowsl * 4;
        if (this.columnsl == 2) {
            j >>= 1;
        }
        final long j2 = j;
        Future[] futureArr = new Future[i2];
        int i3 = 0;
        while (i3 < i2) {
            final long j3 = i3;
            int i4 = i3;
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.20
                @Override // java.lang.Runnable
                public void run() {
                    long j4;
                    long j5;
                    FloatLargeArray floatLargeArray2 = new FloatLargeArray(j2);
                    long j6 = 2;
                    long j7 = 1;
                    if (i != -1) {
                        long j8 = j3;
                        while (j8 < FloatDHT_3D.this.slicesl) {
                            long j9 = FloatDHT_3D.this.sliceStride * j8;
                            for (long j10 = 0; j10 < FloatDHT_3D.this.rowsl; j10++) {
                                FloatDHT_3D.this.dhtColumns.inverse(floatLargeArray, (FloatDHT_3D.this.rowStride * j10) + j9, z);
                            }
                            if (FloatDHT_3D.this.columnsl > 2) {
                                long j11 = 0;
                                while (j11 < FloatDHT_3D.this.columnsl) {
                                    long j12 = 0;
                                    while (j12 < FloatDHT_3D.this.rowsl) {
                                        long j13 = (FloatDHT_3D.this.rowStride * j12) + j9 + j11;
                                        long j14 = FloatDHT_3D.this.rowsl + j12;
                                        floatLargeArray2.setFloat(j12, floatLargeArray.getFloat(j13));
                                        floatLargeArray2.setFloat(j14, floatLargeArray.getFloat(j13 + 1));
                                        floatLargeArray2.setFloat(FloatDHT_3D.this.rowsl + j14, floatLargeArray.getFloat(j13 + 2));
                                        floatLargeArray2.setFloat(j14 + (FloatDHT_3D.this.rowsl * 2), floatLargeArray.getFloat(j13 + 3));
                                        j12++;
                                        j8 = j8;
                                        j11 = j11;
                                    }
                                    long j15 = j8;
                                    long j16 = j11;
                                    FloatDHT_3D.this.dhtRows.inverse(floatLargeArray2, 0L, z);
                                    FloatDHT_3D.this.dhtRows.inverse(floatLargeArray2, FloatDHT_3D.this.rowsl, z);
                                    FloatDHT_3D.this.dhtRows.inverse(floatLargeArray2, FloatDHT_3D.this.rowsl * 2, z);
                                    FloatDHT_3D.this.dhtRows.inverse(floatLargeArray2, FloatDHT_3D.this.rowsl * 3, z);
                                    for (long j17 = 0; j17 < FloatDHT_3D.this.rowsl; j17++) {
                                        long j18 = (FloatDHT_3D.this.rowStride * j17) + j9 + j16;
                                        long j19 = FloatDHT_3D.this.rowsl + j17;
                                        floatLargeArray.setFloat(j18, floatLargeArray2.getFloat(j17));
                                        floatLargeArray.setFloat(j18 + 1, floatLargeArray2.getFloat(j19));
                                        floatLargeArray.setFloat(j18 + 2, floatLargeArray2.getFloat(j19 + FloatDHT_3D.this.rowsl));
                                        floatLargeArray.setFloat(j18 + 3, floatLargeArray2.getFloat(j19 + (FloatDHT_3D.this.rowsl * 2)));
                                    }
                                    j11 = j16 + 4;
                                    j8 = j15;
                                }
                                j4 = j8;
                            } else {
                                j4 = j8;
                                if (FloatDHT_3D.this.columnsl == 2) {
                                    for (long j20 = 0; j20 < FloatDHT_3D.this.rowsl; j20++) {
                                        long j21 = (FloatDHT_3D.this.rowStride * j20) + j9;
                                        floatLargeArray2.setFloat(j20, floatLargeArray.getFloat(j21));
                                        floatLargeArray2.setFloat(FloatDHT_3D.this.rowsl + j20, floatLargeArray.getFloat(j21 + 1));
                                    }
                                    FloatDHT_3D.this.dhtRows.inverse(floatLargeArray2, 0L, z);
                                    FloatDHT_3D.this.dhtRows.inverse(floatLargeArray2, FloatDHT_3D.this.rowsl, z);
                                    for (long j22 = 0; j22 < FloatDHT_3D.this.rowsl; j22++) {
                                        long j23 = (FloatDHT_3D.this.rowStride * j22) + j9;
                                        floatLargeArray.setFloat(j23, floatLargeArray2.getFloat(j22));
                                        floatLargeArray.setFloat(j23 + 1, floatLargeArray2.getFloat(FloatDHT_3D.this.rowsl + j22));
                                    }
                                }
                            }
                            j8 = j4 + i2;
                        }
                        return;
                    }
                    long j24 = j3;
                    while (j24 < FloatDHT_3D.this.slicesl) {
                        long j25 = FloatDHT_3D.this.sliceStride * j24;
                        for (long j26 = 0; j26 < FloatDHT_3D.this.rowsl; j26 += j7) {
                            FloatDHT_3D.this.dhtColumns.forward(floatLargeArray, (FloatDHT_3D.this.rowStride * j26) + j25);
                        }
                        if (FloatDHT_3D.this.columnsl > j6) {
                            long j27 = 0;
                            while (j27 < FloatDHT_3D.this.columnsl) {
                                long j28 = 0;
                                while (j28 < FloatDHT_3D.this.rowsl) {
                                    long j29 = (FloatDHT_3D.this.rowStride * j28) + j25 + j27;
                                    long j30 = FloatDHT_3D.this.rowsl + j28;
                                    floatLargeArray2.setFloat(j28, floatLargeArray.getFloat(j29));
                                    floatLargeArray2.setFloat(j30, floatLargeArray.getFloat(j29 + 1));
                                    floatLargeArray2.setFloat(FloatDHT_3D.this.rowsl + j30, floatLargeArray.getFloat(j29 + 2));
                                    floatLargeArray2.setFloat(j30 + (FloatDHT_3D.this.rowsl * 2), floatLargeArray.getFloat(j29 + 3));
                                    j28++;
                                    j24 = j24;
                                    j27 = j27;
                                }
                                long j31 = j24;
                                long j32 = j27;
                                FloatDHT_3D.this.dhtRows.forward(floatLargeArray2, 0L);
                                FloatDHT_3D.this.dhtRows.forward(floatLargeArray2, FloatDHT_3D.this.rowsl);
                                FloatDHT_3D.this.dhtRows.forward(floatLargeArray2, FloatDHT_3D.this.rowsl * 2);
                                FloatDHT_3D.this.dhtRows.forward(floatLargeArray2, FloatDHT_3D.this.rowsl * 3);
                                for (long j33 = 0; j33 < FloatDHT_3D.this.rowsl; j33++) {
                                    long j34 = (FloatDHT_3D.this.rowStride * j33) + j25 + j32;
                                    long j35 = FloatDHT_3D.this.rowsl + j33;
                                    floatLargeArray.setFloat(j34, floatLargeArray2.getFloat(j33));
                                    floatLargeArray.setFloat(j34 + 1, floatLargeArray2.getFloat(j35));
                                    floatLargeArray.setFloat(j34 + 2, floatLargeArray2.getFloat(j35 + FloatDHT_3D.this.rowsl));
                                    floatLargeArray.setFloat(j34 + 3, floatLargeArray2.getFloat(j35 + (FloatDHT_3D.this.rowsl * 2)));
                                }
                                j27 = j32 + 4;
                                j24 = j31;
                            }
                            j5 = j24;
                        } else {
                            j5 = j24;
                            if (FloatDHT_3D.this.columnsl == j6) {
                                for (long j36 = 0; j36 < FloatDHT_3D.this.rowsl; j36++) {
                                    long j37 = (FloatDHT_3D.this.rowStride * j36) + j25;
                                    floatLargeArray2.setFloat(j36, floatLargeArray.getFloat(j37));
                                    floatLargeArray2.setFloat(FloatDHT_3D.this.rowsl + j36, floatLargeArray.getFloat(j37 + 1));
                                }
                                FloatDHT_3D.this.dhtRows.forward(floatLargeArray2, 0L);
                                FloatDHT_3D.this.dhtRows.forward(floatLargeArray2, FloatDHT_3D.this.rowsl);
                                for (long j38 = 0; j38 < FloatDHT_3D.this.rowsl; j38++) {
                                    long j39 = (FloatDHT_3D.this.rowStride * j38) + j25;
                                    floatLargeArray.setFloat(j39, floatLargeArray2.getFloat(j38));
                                    floatLargeArray.setFloat(j39 + 1, floatLargeArray2.getFloat(FloatDHT_3D.this.rowsl + j38));
                                }
                            }
                        }
                        j24 = j5 + i2;
                        j6 = 2;
                        j7 = 1;
                    }
                }
            });
            i3 = i4 + 1;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3da_subth(final int i, final float[][][] fArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.slices;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = numberOfThreads2;
        int i3 = this.rows * 4;
        if (this.columns == 2) {
            i3 >>= 1;
        }
        final int i4 = i3;
        Future[] futureArr = new Future[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.21
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i4];
                    if (i == -1) {
                        int i7 = i6;
                        while (i7 < FloatDHT_3D.this.slices) {
                            for (int i8 = 0; i8 < FloatDHT_3D.this.rows; i8++) {
                                FloatDHT_3D.this.dhtColumns.forward(fArr[i7][i8]);
                            }
                            if (FloatDHT_3D.this.columns > 2) {
                                for (int i9 = 0; i9 < FloatDHT_3D.this.columns; i9 += 4) {
                                    for (int i10 = 0; i10 < FloatDHT_3D.this.rows; i10++) {
                                        int i11 = FloatDHT_3D.this.rows + i10;
                                        float[] fArr3 = fArr[i7][i10];
                                        fArr2[i10] = fArr3[i9];
                                        fArr2[i11] = fArr3[i9 + 1];
                                        fArr2[FloatDHT_3D.this.rows + i11] = fArr[i7][i10][i9 + 2];
                                        fArr2[i11 + (FloatDHT_3D.this.rows * 2)] = fArr[i7][i10][i9 + 3];
                                    }
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, 0);
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows);
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows * 2);
                                    FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows * 3);
                                    for (int i12 = 0; i12 < FloatDHT_3D.this.rows; i12++) {
                                        int i13 = FloatDHT_3D.this.rows + i12;
                                        float[] fArr4 = fArr[i7][i12];
                                        fArr4[i9] = fArr2[i12];
                                        fArr4[i9 + 1] = fArr2[i13];
                                        fArr4[i9 + 2] = fArr2[FloatDHT_3D.this.rows + i13];
                                        fArr[i7][i12][i9 + 3] = fArr2[i13 + (FloatDHT_3D.this.rows * 2)];
                                    }
                                }
                            } else if (FloatDHT_3D.this.columns == 2) {
                                for (int i14 = 0; i14 < FloatDHT_3D.this.rows; i14++) {
                                    fArr2[i14] = fArr[i7][i14][0];
                                    fArr2[FloatDHT_3D.this.rows + i14] = fArr[i7][i14][1];
                                }
                                FloatDHT_3D.this.dhtRows.forward(fArr2, 0);
                                FloatDHT_3D.this.dhtRows.forward(fArr2, FloatDHT_3D.this.rows);
                                for (int i15 = 0; i15 < FloatDHT_3D.this.rows; i15++) {
                                    float[] fArr5 = fArr[i7][i15];
                                    fArr5[0] = fArr2[i15];
                                    fArr5[1] = fArr2[FloatDHT_3D.this.rows + i15];
                                }
                            }
                            i7 += i2;
                        }
                        return;
                    }
                    int i16 = i6;
                    while (i16 < FloatDHT_3D.this.slices) {
                        for (int i17 = 0; i17 < FloatDHT_3D.this.rows; i17++) {
                            FloatDHT_3D.this.dhtColumns.inverse(fArr[i16][i17], z);
                        }
                        if (FloatDHT_3D.this.columns > 2) {
                            for (int i18 = 0; i18 < FloatDHT_3D.this.columns; i18 += 4) {
                                for (int i19 = 0; i19 < FloatDHT_3D.this.rows; i19++) {
                                    int i20 = FloatDHT_3D.this.rows + i19;
                                    float[] fArr6 = fArr[i16][i19];
                                    fArr2[i19] = fArr6[i18];
                                    fArr2[i20] = fArr6[i18 + 1];
                                    fArr2[FloatDHT_3D.this.rows + i20] = fArr[i16][i19][i18 + 2];
                                    fArr2[i20 + (FloatDHT_3D.this.rows * 2)] = fArr[i16][i19][i18 + 3];
                                }
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, 0, z);
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows, z);
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows * 2, z);
                                FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows * 3, z);
                                for (int i21 = 0; i21 < FloatDHT_3D.this.rows; i21++) {
                                    int i22 = FloatDHT_3D.this.rows + i21;
                                    float[] fArr7 = fArr[i16][i21];
                                    fArr7[i18] = fArr2[i21];
                                    fArr7[i18 + 1] = fArr2[i22];
                                    fArr7[i18 + 2] = fArr2[FloatDHT_3D.this.rows + i22];
                                    fArr[i16][i21][i18 + 3] = fArr2[i22 + (FloatDHT_3D.this.rows * 2)];
                                }
                            }
                        } else if (FloatDHT_3D.this.columns == 2) {
                            for (int i23 = 0; i23 < FloatDHT_3D.this.rows; i23++) {
                                fArr2[i23] = fArr[i16][i23][0];
                                fArr2[FloatDHT_3D.this.rows + i23] = fArr[i16][i23][1];
                            }
                            FloatDHT_3D.this.dhtRows.inverse(fArr2, 0, z);
                            FloatDHT_3D.this.dhtRows.inverse(fArr2, FloatDHT_3D.this.rows, z);
                            for (int i24 = 0; i24 < FloatDHT_3D.this.rows; i24++) {
                                float[] fArr8 = fArr[i16][i24];
                                fArr8[0] = fArr2[i24];
                                fArr8[1] = fArr2[FloatDHT_3D.this.rows + i24];
                            }
                        }
                        i16 += i2;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3db_subth(final int i, final float[] fArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = numberOfThreads2;
        int i3 = this.slices * 4;
        if (this.columns == 2) {
            i3 >>= 1;
        }
        final int i4 = i3;
        Future[] futureArr = new Future[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.22
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i4];
                    if (i == -1) {
                        if (FloatDHT_3D.this.columns > 2) {
                            int i7 = i6;
                            while (i7 < FloatDHT_3D.this.rows) {
                                int i8 = FloatDHT_3D.this.rowStride * i7;
                                for (int i9 = 0; i9 < FloatDHT_3D.this.columns; i9 += 4) {
                                    for (int i10 = 0; i10 < FloatDHT_3D.this.slices; i10++) {
                                        int i11 = (FloatDHT_3D.this.sliceStride * i10) + i8 + i9;
                                        int i12 = FloatDHT_3D.this.slices + i10;
                                        float[] fArr3 = fArr;
                                        fArr2[i10] = fArr3[i11];
                                        fArr2[i12] = fArr3[i11 + 1];
                                        fArr2[FloatDHT_3D.this.slices + i12] = fArr[i11 + 2];
                                        fArr2[i12 + (FloatDHT_3D.this.slices * 2)] = fArr[i11 + 3];
                                    }
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, 0);
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices);
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices * 2);
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices * 3);
                                    for (int i13 = 0; i13 < FloatDHT_3D.this.slices; i13++) {
                                        int i14 = (FloatDHT_3D.this.sliceStride * i13) + i8 + i9;
                                        int i15 = FloatDHT_3D.this.slices + i13;
                                        float[] fArr4 = fArr;
                                        fArr4[i14] = fArr2[i13];
                                        fArr4[i14 + 1] = fArr2[i15];
                                        fArr4[i14 + 2] = fArr2[FloatDHT_3D.this.slices + i15];
                                        fArr[i14 + 3] = fArr2[i15 + (FloatDHT_3D.this.slices * 2)];
                                    }
                                }
                                i7 += i2;
                            }
                            return;
                        }
                        if (FloatDHT_3D.this.columns == 2) {
                            int i16 = i6;
                            while (i16 < FloatDHT_3D.this.rows) {
                                int i17 = FloatDHT_3D.this.rowStride * i16;
                                for (int i18 = 0; i18 < FloatDHT_3D.this.slices; i18++) {
                                    int i19 = (FloatDHT_3D.this.sliceStride * i18) + i17;
                                    fArr2[i18] = fArr[i19];
                                    fArr2[FloatDHT_3D.this.slices + i18] = fArr[i19 + 1];
                                }
                                FloatDHT_3D.this.dhtSlices.forward(fArr2, 0);
                                FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices);
                                for (int i20 = 0; i20 < FloatDHT_3D.this.slices; i20++) {
                                    int i21 = (FloatDHT_3D.this.sliceStride * i20) + i17;
                                    float[] fArr5 = fArr;
                                    fArr5[i21] = fArr2[i20];
                                    fArr5[i21 + 1] = fArr2[FloatDHT_3D.this.slices + i20];
                                }
                                i16 += i2;
                            }
                            return;
                        }
                        return;
                    }
                    if (FloatDHT_3D.this.columns > 2) {
                        int i22 = i6;
                        while (i22 < FloatDHT_3D.this.rows) {
                            int i23 = FloatDHT_3D.this.rowStride * i22;
                            for (int i24 = 0; i24 < FloatDHT_3D.this.columns; i24 += 4) {
                                for (int i25 = 0; i25 < FloatDHT_3D.this.slices; i25++) {
                                    int i26 = (FloatDHT_3D.this.sliceStride * i25) + i23 + i24;
                                    int i27 = FloatDHT_3D.this.slices + i25;
                                    float[] fArr6 = fArr;
                                    fArr2[i25] = fArr6[i26];
                                    fArr2[i27] = fArr6[i26 + 1];
                                    fArr2[FloatDHT_3D.this.slices + i27] = fArr[i26 + 2];
                                    fArr2[i27 + (FloatDHT_3D.this.slices * 2)] = fArr[i26 + 3];
                                }
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, 0, z);
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices, z);
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices * 2, z);
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices * 3, z);
                                for (int i28 = 0; i28 < FloatDHT_3D.this.slices; i28++) {
                                    int i29 = (FloatDHT_3D.this.sliceStride * i28) + i23 + i24;
                                    int i30 = FloatDHT_3D.this.slices + i28;
                                    float[] fArr7 = fArr;
                                    fArr7[i29] = fArr2[i28];
                                    fArr7[i29 + 1] = fArr2[i30];
                                    fArr7[i29 + 2] = fArr2[FloatDHT_3D.this.slices + i30];
                                    fArr[i29 + 3] = fArr2[i30 + (FloatDHT_3D.this.slices * 2)];
                                }
                            }
                            i22 += i2;
                        }
                        return;
                    }
                    if (FloatDHT_3D.this.columns == 2) {
                        int i31 = i6;
                        while (i31 < FloatDHT_3D.this.rows) {
                            int i32 = FloatDHT_3D.this.rowStride * i31;
                            for (int i33 = 0; i33 < FloatDHT_3D.this.slices; i33++) {
                                int i34 = (FloatDHT_3D.this.sliceStride * i33) + i32;
                                fArr2[i33] = fArr[i34];
                                fArr2[FloatDHT_3D.this.slices + i33] = fArr[i34 + 1];
                            }
                            FloatDHT_3D.this.dhtSlices.inverse(fArr2, 0, z);
                            FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices, z);
                            for (int i35 = 0; i35 < FloatDHT_3D.this.slices; i35++) {
                                int i36 = (FloatDHT_3D.this.sliceStride * i35) + i32;
                                float[] fArr8 = fArr;
                                fArr8[i36] = fArr2[i35];
                                fArr8[i36 + 1] = fArr2[FloatDHT_3D.this.slices + i35];
                            }
                            i31 += i2;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3db_subth(final int i, final FloatLargeArray floatLargeArray, final boolean z) {
        long numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long numberOfThreads2 = this.rowsl;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = (int) numberOfThreads2;
        long j = this.slicesl * 4;
        if (this.columnsl == 2) {
            j >>= 1;
        }
        final long j2 = j;
        Future[] futureArr = new Future[i2];
        int i3 = 0;
        while (i3 < i2) {
            final long j3 = i3;
            int i4 = i3;
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.23
                @Override // java.lang.Runnable
                public void run() {
                    FloatLargeArray floatLargeArray2 = new FloatLargeArray(j2);
                    long j4 = 1;
                    if (i == -1) {
                        if (FloatDHT_3D.this.columnsl <= 2) {
                            if (FloatDHT_3D.this.columnsl == 2) {
                                long j5 = j3;
                                while (j5 < FloatDHT_3D.this.rowsl) {
                                    long j6 = FloatDHT_3D.this.rowStridel * j5;
                                    for (long j7 = 0; j7 < FloatDHT_3D.this.slicesl; j7++) {
                                        long j8 = (FloatDHT_3D.this.sliceStridel * j7) + j6;
                                        floatLargeArray2.setFloat(j7, floatLargeArray.getFloat(j8));
                                        floatLargeArray2.setFloat(FloatDHT_3D.this.slicesl + j7, floatLargeArray.getFloat(j8 + 1));
                                    }
                                    FloatDHT_3D.this.dhtSlices.forward(floatLargeArray2, 0L);
                                    FloatDHT_3D.this.dhtSlices.forward(floatLargeArray2, FloatDHT_3D.this.slicesl);
                                    for (long j9 = 0; j9 < FloatDHT_3D.this.slicesl; j9++) {
                                        long j10 = (FloatDHT_3D.this.sliceStridel * j9) + j6;
                                        floatLargeArray.setFloat(j10, floatLargeArray2.getFloat(j9));
                                        floatLargeArray.setFloat(j10 + 1, floatLargeArray2.getFloat(FloatDHT_3D.this.slicesl + j9));
                                    }
                                    j5 += i2;
                                }
                                return;
                            }
                            return;
                        }
                        long j11 = j3;
                        while (j11 < FloatDHT_3D.this.rowsl) {
                            long j12 = FloatDHT_3D.this.rowStridel * j11;
                            long j13 = 0;
                            while (j13 < FloatDHT_3D.this.columnsl) {
                                long j14 = 0;
                                while (j14 < FloatDHT_3D.this.slicesl) {
                                    long j15 = (FloatDHT_3D.this.sliceStridel * j14) + j12 + j13;
                                    long j16 = FloatDHT_3D.this.slicesl + j14;
                                    floatLargeArray2.setFloat(j14, floatLargeArray.getFloat(j15));
                                    floatLargeArray2.setFloat(j16, floatLargeArray.getFloat(j15 + j4));
                                    floatLargeArray2.setFloat(FloatDHT_3D.this.slicesl + j16, floatLargeArray.getFloat(j15 + 2));
                                    floatLargeArray2.setFloat(j16 + (FloatDHT_3D.this.slicesl * 2), floatLargeArray.getFloat(j15 + 3));
                                    j14++;
                                    j4 = 1;
                                    j11 = j11;
                                }
                                long j17 = j11;
                                FloatDHT_3D.this.dhtSlices.forward(floatLargeArray2, 0L);
                                FloatDHT_3D.this.dhtSlices.forward(floatLargeArray2, FloatDHT_3D.this.slicesl);
                                FloatDHT_3D.this.dhtSlices.forward(floatLargeArray2, FloatDHT_3D.this.slicesl * 2);
                                FloatDHT_3D.this.dhtSlices.forward(floatLargeArray2, FloatDHT_3D.this.slicesl * 3);
                                for (long j18 = 0; j18 < FloatDHT_3D.this.slicesl; j18++) {
                                    long j19 = (FloatDHT_3D.this.sliceStridel * j18) + j12 + j13;
                                    long j20 = FloatDHT_3D.this.slicesl + j18;
                                    floatLargeArray.setFloat(j19, floatLargeArray2.getFloat(j18));
                                    floatLargeArray.setFloat(j19 + 1, floatLargeArray2.getFloat(j20));
                                    floatLargeArray.setFloat(j19 + 2, floatLargeArray2.getFloat(j20 + FloatDHT_3D.this.slicesl));
                                    floatLargeArray.setFloat(j19 + 3, floatLargeArray2.getFloat(j20 + (FloatDHT_3D.this.slicesl * 2)));
                                }
                                j13 += 4;
                                j11 = j17;
                                j4 = 1;
                            }
                            j11 += i2;
                            j4 = 1;
                        }
                        return;
                    }
                    if (FloatDHT_3D.this.columnsl <= 2) {
                        if (FloatDHT_3D.this.columnsl == 2) {
                            long j21 = j3;
                            while (j21 < FloatDHT_3D.this.rowsl) {
                                long j22 = FloatDHT_3D.this.rowStridel * j21;
                                for (long j23 = 0; j23 < FloatDHT_3D.this.slicesl; j23++) {
                                    long j24 = (FloatDHT_3D.this.sliceStridel * j23) + j22;
                                    floatLargeArray2.setFloat(j23, floatLargeArray.getFloat(j24));
                                    floatLargeArray2.setFloat(FloatDHT_3D.this.slicesl + j23, floatLargeArray.getFloat(j24 + 1));
                                }
                                FloatDHT_3D.this.dhtSlices.inverse(floatLargeArray2, 0L, z);
                                FloatDHT_3D.this.dhtSlices.inverse(floatLargeArray2, FloatDHT_3D.this.slicesl, z);
                                for (long j25 = 0; j25 < FloatDHT_3D.this.slicesl; j25++) {
                                    long j26 = (FloatDHT_3D.this.sliceStridel * j25) + j22;
                                    floatLargeArray.setFloat(j26, floatLargeArray2.getFloat(j25));
                                    floatLargeArray.setFloat(j26 + 1, floatLargeArray2.getFloat(FloatDHT_3D.this.slicesl + j25));
                                }
                                j21 += i2;
                            }
                            return;
                        }
                        return;
                    }
                    long j27 = j3;
                    while (j27 < FloatDHT_3D.this.rowsl) {
                        long j28 = FloatDHT_3D.this.rowStridel * j27;
                        long j29 = 0;
                        while (j29 < FloatDHT_3D.this.columnsl) {
                            long j30 = 0;
                            while (j30 < FloatDHT_3D.this.slicesl) {
                                long j31 = (FloatDHT_3D.this.sliceStridel * j30) + j28 + j29;
                                long j32 = FloatDHT_3D.this.slicesl + j30;
                                floatLargeArray2.setFloat(j30, floatLargeArray.getFloat(j31));
                                floatLargeArray2.setFloat(j32, floatLargeArray.getFloat(j31 + 1));
                                floatLargeArray2.setFloat(FloatDHT_3D.this.slicesl + j32, floatLargeArray.getFloat(j31 + 2));
                                floatLargeArray2.setFloat(j32 + (FloatDHT_3D.this.slicesl * 2), floatLargeArray.getFloat(j31 + 3));
                                j30++;
                                j27 = j27;
                                j29 = j29;
                            }
                            long j33 = j27;
                            long j34 = j29;
                            FloatDHT_3D.this.dhtSlices.inverse(floatLargeArray2, 0L, z);
                            FloatDHT_3D.this.dhtSlices.inverse(floatLargeArray2, FloatDHT_3D.this.slicesl, z);
                            FloatDHT_3D.this.dhtSlices.inverse(floatLargeArray2, FloatDHT_3D.this.slicesl * 2, z);
                            FloatDHT_3D.this.dhtSlices.inverse(floatLargeArray2, FloatDHT_3D.this.slicesl * 3, z);
                            for (long j35 = 0; j35 < FloatDHT_3D.this.slicesl; j35++) {
                                long j36 = (FloatDHT_3D.this.sliceStridel * j35) + j28 + j34;
                                long j37 = FloatDHT_3D.this.slicesl + j35;
                                floatLargeArray.setFloat(j36, floatLargeArray2.getFloat(j35));
                                floatLargeArray.setFloat(j36 + 1, floatLargeArray2.getFloat(j37));
                                floatLargeArray.setFloat(j36 + 2, floatLargeArray2.getFloat(j37 + FloatDHT_3D.this.slicesl));
                                floatLargeArray.setFloat(j36 + 3, floatLargeArray2.getFloat(j37 + (FloatDHT_3D.this.slicesl * 2)));
                            }
                            j29 = j34 + 4;
                            j27 = j33;
                        }
                        j27 += i2;
                    }
                }
            });
            i3 = i4 + 1;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3db_subth(final int i, final float[][][] fArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = numberOfThreads2;
        int i3 = this.slices * 4;
        if (this.columns == 2) {
            i3 >>= 1;
        }
        final int i4 = i3;
        Future[] futureArr = new Future[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.FloatDHT_3D.24
                @Override // java.lang.Runnable
                public void run() {
                    float[] fArr2 = new float[i4];
                    if (i == -1) {
                        if (FloatDHT_3D.this.columns > 2) {
                            int i7 = i6;
                            while (i7 < FloatDHT_3D.this.rows) {
                                for (int i8 = 0; i8 < FloatDHT_3D.this.columns; i8 += 4) {
                                    for (int i9 = 0; i9 < FloatDHT_3D.this.slices; i9++) {
                                        int i10 = FloatDHT_3D.this.slices + i9;
                                        float[] fArr3 = fArr[i9][i7];
                                        fArr2[i9] = fArr3[i8];
                                        fArr2[i10] = fArr3[i8 + 1];
                                        fArr2[FloatDHT_3D.this.slices + i10] = fArr[i9][i7][i8 + 2];
                                        fArr2[i10 + (FloatDHT_3D.this.slices * 2)] = fArr[i9][i7][i8 + 3];
                                    }
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, 0);
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices);
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices * 2);
                                    FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices * 3);
                                    for (int i11 = 0; i11 < FloatDHT_3D.this.slices; i11++) {
                                        int i12 = FloatDHT_3D.this.slices + i11;
                                        float[] fArr4 = fArr[i11][i7];
                                        fArr4[i8] = fArr2[i11];
                                        fArr4[i8 + 1] = fArr2[i12];
                                        fArr4[i8 + 2] = fArr2[FloatDHT_3D.this.slices + i12];
                                        fArr[i11][i7][i8 + 3] = fArr2[i12 + (FloatDHT_3D.this.slices * 2)];
                                    }
                                }
                                i7 += i2;
                            }
                            return;
                        }
                        if (FloatDHT_3D.this.columns == 2) {
                            int i13 = i6;
                            while (i13 < FloatDHT_3D.this.rows) {
                                for (int i14 = 0; i14 < FloatDHT_3D.this.slices; i14++) {
                                    fArr2[i14] = fArr[i14][i13][0];
                                    fArr2[FloatDHT_3D.this.slices + i14] = fArr[i14][i13][1];
                                }
                                FloatDHT_3D.this.dhtSlices.forward(fArr2, 0);
                                FloatDHT_3D.this.dhtSlices.forward(fArr2, FloatDHT_3D.this.slices);
                                for (int i15 = 0; i15 < FloatDHT_3D.this.slices; i15++) {
                                    float[] fArr5 = fArr[i15][i13];
                                    fArr5[0] = fArr2[i15];
                                    fArr5[1] = fArr2[FloatDHT_3D.this.slices + i15];
                                }
                                i13 += i2;
                            }
                            return;
                        }
                        return;
                    }
                    if (FloatDHT_3D.this.columns > 2) {
                        int i16 = i6;
                        while (i16 < FloatDHT_3D.this.rows) {
                            for (int i17 = 0; i17 < FloatDHT_3D.this.columns; i17 += 4) {
                                for (int i18 = 0; i18 < FloatDHT_3D.this.slices; i18++) {
                                    int i19 = FloatDHT_3D.this.slices + i18;
                                    float[] fArr6 = fArr[i18][i16];
                                    fArr2[i18] = fArr6[i17];
                                    fArr2[i19] = fArr6[i17 + 1];
                                    fArr2[FloatDHT_3D.this.slices + i19] = fArr[i18][i16][i17 + 2];
                                    fArr2[i19 + (FloatDHT_3D.this.slices * 2)] = fArr[i18][i16][i17 + 3];
                                }
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, 0, z);
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices, z);
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices * 2, z);
                                FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices * 3, z);
                                for (int i20 = 0; i20 < FloatDHT_3D.this.slices; i20++) {
                                    int i21 = FloatDHT_3D.this.slices + i20;
                                    float[] fArr7 = fArr[i20][i16];
                                    fArr7[i17] = fArr2[i20];
                                    fArr7[i17 + 1] = fArr2[i21];
                                    fArr7[i17 + 2] = fArr2[FloatDHT_3D.this.slices + i21];
                                    fArr[i20][i16][i17 + 3] = fArr2[i21 + (FloatDHT_3D.this.slices * 2)];
                                }
                            }
                            i16 += i2;
                        }
                        return;
                    }
                    if (FloatDHT_3D.this.columns == 2) {
                        int i22 = i6;
                        while (i22 < FloatDHT_3D.this.rows) {
                            for (int i23 = 0; i23 < FloatDHT_3D.this.slices; i23++) {
                                fArr2[i23] = fArr[i23][i22][0];
                                fArr2[FloatDHT_3D.this.slices + i23] = fArr[i23][i22][1];
                            }
                            FloatDHT_3D.this.dhtSlices.inverse(fArr2, 0, z);
                            FloatDHT_3D.this.dhtSlices.inverse(fArr2, FloatDHT_3D.this.slices, z);
                            for (int i24 = 0; i24 < FloatDHT_3D.this.slices; i24++) {
                                float[] fArr8 = fArr[i24][i22];
                                fArr8[0] = fArr2[i24];
                                fArr8[1] = fArr2[FloatDHT_3D.this.slices + i24];
                            }
                            i22 += i2;
                        }
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void yTransform(float[] fArr) {
        int i = 0;
        while (true) {
            int i2 = this.slices;
            if (i > i2 / 2) {
                return;
            }
            int i3 = (i2 - i) % i2;
            int i4 = this.sliceStride;
            int i5 = i * i4;
            int i6 = i3 * i4;
            int i7 = 0;
            while (true) {
                int i8 = this.rows;
                if (i7 <= i8 / 2) {
                    int i9 = (i8 - i7) % i8;
                    int i10 = this.rowStride;
                    int i11 = i7 * i10;
                    int i12 = i9 * i10;
                    int i13 = 0;
                    while (true) {
                        int i14 = this.columns;
                        if (i13 <= i14 / 2) {
                            int i15 = (i14 - i13) % i14;
                            int i16 = i5 + i12;
                            int i17 = i16 + i13;
                            int i18 = i5 + i11;
                            int i19 = i18 + i15;
                            int i20 = i6 + i11;
                            int i21 = i20 + i13;
                            int i22 = i6 + i12;
                            int i23 = i22 + i15;
                            int i24 = i22 + i13;
                            int i25 = i20 + i15;
                            int i26 = i18 + i13;
                            int i27 = i16 + i15;
                            float f = fArr[i17];
                            float f2 = fArr[i19];
                            float f3 = fArr[i21];
                            float f4 = fArr[i23];
                            float f5 = fArr[i24];
                            float f6 = fArr[i25];
                            float f7 = fArr[i26];
                            float f8 = fArr[i27];
                            float f9 = f + f2;
                            fArr[i26] = ((f9 + f3) - f4) / 2.0f;
                            float f10 = f5 + f6;
                            fArr[i21] = ((f10 + f7) - f8) / 2.0f;
                            float f11 = f7 + f8;
                            fArr[i17] = ((f11 + f5) - f6) / 2.0f;
                            float f12 = f3 + f4;
                            fArr[i24] = ((f12 + f) - f2) / 2.0f;
                            fArr[i19] = ((f11 + f6) - f5) / 2.0f;
                            fArr[i25] = ((f12 + f2) - f) / 2.0f;
                            fArr[i27] = ((f9 + f4) - f3) / 2.0f;
                            fArr[i23] = ((f10 + f8) - f7) / 2.0f;
                            i13++;
                        }
                    }
                    i7++;
                }
            }
            i++;
        }
    }

    private void yTransform(FloatLargeArray floatLargeArray) {
        FloatDHT_3D floatDHT_3D = this;
        long j = 0;
        while (true) {
            long j2 = floatDHT_3D.slicesl;
            long j3 = 2;
            if (j > j2 / 2) {
                return;
            }
            long j4 = (j2 - j) % j2;
            long j5 = floatDHT_3D.sliceStridel;
            long j6 = j * j5;
            long j7 = j4 * j5;
            long j8 = 0;
            while (true) {
                long j9 = floatDHT_3D.rowsl;
                if (j8 <= j9 / j3) {
                    long j10 = (j9 - j8) % j9;
                    long j11 = floatDHT_3D.rowStridel;
                    long j12 = j8 * j11;
                    long j13 = j10 * j11;
                    long j14 = 0;
                    while (true) {
                        long j15 = floatDHT_3D.columnsl;
                        if (j14 <= j15 / j3) {
                            long j16 = (j15 - j14) % j15;
                            long j17 = j6 + j13;
                            long j18 = j17 + j14;
                            long j19 = j6 + j12;
                            long j20 = j6;
                            long j21 = j19 + j16;
                            long j22 = j7 + j12;
                            long j23 = j;
                            long j24 = j22 + j14;
                            long j25 = j7 + j13;
                            long j26 = j7;
                            long j27 = j25 + j16;
                            long j28 = j8;
                            long j29 = j25 + j14;
                            long j30 = j22 + j16;
                            long j31 = j19 + j14;
                            long j32 = j17 + j16;
                            float f = floatLargeArray.getFloat(j18);
                            float f2 = floatLargeArray.getFloat(j21);
                            float f3 = floatLargeArray.getFloat(j24);
                            float f4 = floatLargeArray.getFloat(j27);
                            float f5 = floatLargeArray.getFloat(j29);
                            float f6 = floatLargeArray.getFloat(j30);
                            float f7 = floatLargeArray.getFloat(j31);
                            float f8 = floatLargeArray.getFloat(j32);
                            float f9 = f + f2;
                            floatLargeArray.setFloat(j31, ((f9 + f3) - f4) / 2.0f);
                            float f10 = f5 + f6;
                            floatLargeArray.setFloat(j24, ((f10 + f7) - f8) / 2.0f);
                            float f11 = f7 + f8;
                            floatLargeArray.setFloat(j18, ((f11 + f5) - f6) / 2.0f);
                            float f12 = f3 + f4;
                            floatLargeArray.setFloat(j29, ((f12 + f) - f2) / 2.0f);
                            floatLargeArray.setFloat(j21, ((f11 + f6) - f5) / 2.0f);
                            floatLargeArray.setFloat(j30, ((f12 + f2) - f) / 2.0f);
                            floatLargeArray.setFloat(j32, ((f9 + f4) - f3) / 2.0f);
                            floatLargeArray.setFloat(j27, ((f10 + f8) - f7) / 2.0f);
                            j14++;
                            floatDHT_3D = this;
                            j6 = j20;
                            j = j23;
                            j7 = j26;
                            j8 = j28;
                            j3 = 2;
                        }
                    }
                    j8++;
                    floatDHT_3D = this;
                    j3 = 2;
                }
            }
            j++;
            floatDHT_3D = this;
        }
    }

    private void yTransform(float[][][] fArr) {
        int i = 0;
        while (true) {
            int i2 = this.slices;
            if (i > i2 / 2) {
                return;
            }
            int i3 = (i2 - i) % i2;
            int i4 = 0;
            while (true) {
                int i5 = this.rows;
                if (i4 <= i5 / 2) {
                    int i6 = (i5 - i4) % i5;
                    int i7 = 0;
                    while (true) {
                        int i8 = this.columns;
                        if (i7 <= i8 / 2) {
                            int i9 = (i8 - i7) % i8;
                            float[][] fArr2 = fArr[i];
                            float[] fArr3 = fArr2[i6];
                            float f = fArr3[i7];
                            float[] fArr4 = fArr2[i4];
                            float f2 = fArr4[i9];
                            float[][] fArr5 = fArr[i3];
                            float[] fArr6 = fArr5[i4];
                            float f3 = fArr6[i7];
                            float[] fArr7 = fArr5[i6];
                            float f4 = fArr7[i9];
                            float f5 = fArr7[i7];
                            float f6 = fArr6[i9];
                            float f7 = fArr4[i7];
                            float f8 = fArr3[i9];
                            float f9 = f + f2;
                            fArr4[i7] = ((f9 + f3) - f4) / 2.0f;
                            float f10 = f5 + f6;
                            fArr6[i7] = ((f10 + f7) - f8) / 2.0f;
                            float f11 = f7 + f8;
                            fArr3[i7] = ((f11 + f5) - f6) / 2.0f;
                            float f12 = f3 + f4;
                            fArr7[i7] = ((f12 + f) - f2) / 2.0f;
                            fArr4[i9] = ((f11 + f6) - f5) / 2.0f;
                            fArr6[i9] = ((f12 + f2) - f) / 2.0f;
                            fArr3[i9] = ((f9 + f4) - f3) / 2.0f;
                            fArr7[i9] = ((f10 + f8) - f7) / 2.0f;
                            i7++;
                        }
                    }
                    i4++;
                }
            }
            i++;
        }
    }
}
