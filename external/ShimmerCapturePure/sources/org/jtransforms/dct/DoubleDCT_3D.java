package org.jtransforms.dct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

/* loaded from: classes2.dex */
public class DoubleDCT_3D {
    private int columns;
    private long columnsl;
    private DoubleDCT_1D dctColumns;
    private DoubleDCT_1D dctRows;
    private DoubleDCT_1D dctSlices;
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

    public DoubleDCT_3D(long j, long j2, long j3) {
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
        DoubleDCT_1D doubleDCT_1D = new DoubleDCT_1D(j);
        this.dctSlices = doubleDCT_1D;
        if (j == j2) {
            this.dctRows = doubleDCT_1D;
        } else {
            this.dctRows = new DoubleDCT_1D(j2);
        }
        if (j == j3) {
            this.dctColumns = this.dctSlices;
        } else if (j2 == j3) {
            this.dctColumns = this.dctRows;
        } else {
            this.dctColumns = new DoubleDCT_1D(j3);
        }
    }

    public void forward(final double[] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(-1, dArr, z);
                ddxt3db_subth(-1, dArr, z);
                return;
            } else {
                ddxt3da_sub(-1, dArr, z);
                ddxt3db_sub(-1, dArr, z);
                return;
            }
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            int i9 = DoubleDCT_3D.this.sliceStride * i8;
                            for (int i10 = 0; i10 < DoubleDCT_3D.this.rows; i10++) {
                                DoubleDCT_3D.this.dctColumns.forward(dArr, (DoubleDCT_3D.this.rowStride * i10) + i9, z);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            int i12 = DoubleDCT_3D.this.sliceStride * i11;
                            for (int i13 = 0; i13 < DoubleDCT_3D.this.columns; i13++) {
                                for (int i14 = 0; i14 < DoubleDCT_3D.this.rows; i14++) {
                                    dArr2[i14] = dArr[(DoubleDCT_3D.this.rowStride * i14) + i12 + i13];
                                }
                                DoubleDCT_3D.this.dctRows.forward(dArr2, z);
                                for (int i15 = 0; i15 < DoubleDCT_3D.this.rows; i15++) {
                                    dArr[(DoubleDCT_3D.this.rowStride * i15) + i12 + i13] = dArr2[i15];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.3
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            int i15 = DoubleDCT_3D.this.rowStride * i14;
                            for (int i16 = 0; i16 < DoubleDCT_3D.this.columns; i16++) {
                                for (int i17 = 0; i17 < DoubleDCT_3D.this.slices; i17++) {
                                    dArr2[i17] = dArr[(DoubleDCT_3D.this.sliceStride * i17) + i15 + i16];
                                }
                                DoubleDCT_3D.this.dctSlices.forward(dArr2, z);
                                for (int i18 = 0; i18 < DoubleDCT_3D.this.slices; i18++) {
                                    dArr[(DoubleDCT_3D.this.sliceStride * i18) + i15 + i16] = dArr2[i18];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i14 = 0; i14 < this.slices; i14++) {
            int i15 = this.sliceStride * i14;
            for (int i16 = 0; i16 < this.rows; i16++) {
                this.dctColumns.forward(dArr, (this.rowStride * i16) + i15, z);
            }
        }
        double[] dArr2 = new double[this.rows];
        int i17 = 0;
        while (true) {
            i = this.slices;
            if (i17 >= i) {
                break;
            }
            int i18 = this.sliceStride * i17;
            for (int i19 = 0; i19 < this.columns; i19++) {
                for (int i20 = 0; i20 < this.rows; i20++) {
                    dArr2[i20] = dArr[(this.rowStride * i20) + i18 + i19];
                }
                this.dctRows.forward(dArr2, z);
                for (int i21 = 0; i21 < this.rows; i21++) {
                    dArr[(this.rowStride * i21) + i18 + i19] = dArr2[i21];
                }
            }
            i17++;
        }
        double[] dArr3 = new double[i];
        for (int i22 = 0; i22 < this.rows; i22++) {
            int i23 = this.rowStride * i22;
            for (int i24 = 0; i24 < this.columns; i24++) {
                for (int i25 = 0; i25 < this.slices; i25++) {
                    dArr3[i25] = dArr[(this.sliceStride * i25) + i23 + i24];
                }
                this.dctSlices.forward(dArr3, z);
                for (int i26 = 0; i26 < this.slices; i26++) {
                    dArr[(this.sliceStride * i26) + i23 + i24] = dArr3[i26];
                }
            }
        }
    }

    public void forward(final DoubleLargeArray doubleLargeArray, final boolean z) {
        long j;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(-1, doubleLargeArray, z);
                ddxt3db_subth(-1, doubleLargeArray, z);
                return;
            } else {
                ddxt3da_sub(-1, doubleLargeArray, z);
                ddxt3db_sub(-1, doubleLargeArray, z);
                return;
            }
        }
        if (numberOfThreads > 1 && this.useThreads) {
            long j2 = this.slicesl;
            long j3 = numberOfThreads;
            if (j2 >= j3 && this.rowsl >= j3 && this.columnsl >= j3) {
                Future[] futureArr = new Future[numberOfThreads];
                long j4 = j2 / j3;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j5 = i * j4;
                    final long j6 = i == numberOfThreads + (-1) ? this.slicesl : j5 + j4;
                    futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.4
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j7 = j5; j7 < j6; j7++) {
                                long j8 = DoubleDCT_3D.this.sliceStridel * j7;
                                for (long j9 = 0; j9 < DoubleDCT_3D.this.rowsl; j9++) {
                                    DoubleDCT_3D.this.dctColumns.forward(doubleLargeArray, (DoubleDCT_3D.this.rowStridel * j9) + j8, z);
                                }
                            }
                        }
                    });
                    i++;
                    j3 = j3;
                }
                long j7 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j8 = i2 * j4;
                    final long j9 = i2 == numberOfThreads + (-1) ? this.slicesl : j8 + j4;
                    futureArr[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.5
                        @Override // java.lang.Runnable
                        public void run() {
                            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(DoubleDCT_3D.this.rowsl, false);
                            for (long j10 = j8; j10 < j9; j10++) {
                                long j11 = DoubleDCT_3D.this.sliceStridel * j10;
                                for (long j12 = 0; j12 < DoubleDCT_3D.this.columnsl; j12++) {
                                    for (long j13 = 0; j13 < DoubleDCT_3D.this.rowsl; j13++) {
                                        doubleLargeArray2.setDouble(j13, doubleLargeArray.getDouble((DoubleDCT_3D.this.rowStridel * j13) + j11 + j12));
                                    }
                                    DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, z);
                                    long j14 = 0;
                                    while (j14 < DoubleDCT_3D.this.rowsl) {
                                        doubleLargeArray.setDouble((DoubleDCT_3D.this.rowStridel * j14) + j11 + j12, doubleLargeArray2.getDouble(j14));
                                        j14++;
                                        j11 = j11;
                                    }
                                }
                            }
                        }
                    });
                    i2++;
                    str = str;
                }
                String str2 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e3) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                }
                long j10 = this.rowsl / j7;
                int i3 = 0;
                while (i3 < numberOfThreads) {
                    final long j11 = i3 * j10;
                    final long j12 = i3 == numberOfThreads + (-1) ? this.rowsl : j11 + j10;
                    futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.6
                        @Override // java.lang.Runnable
                        public void run() {
                            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(DoubleDCT_3D.this.slicesl, false);
                            for (long j13 = j11; j13 < j12; j13++) {
                                long j14 = DoubleDCT_3D.this.rowStridel * j13;
                                for (long j15 = 0; j15 < DoubleDCT_3D.this.columnsl; j15++) {
                                    for (long j16 = 0; j16 < DoubleDCT_3D.this.slicesl; j16++) {
                                        doubleLargeArray2.setDouble(j16, doubleLargeArray.getDouble((DoubleDCT_3D.this.sliceStridel * j16) + j14 + j15));
                                    }
                                    DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, z);
                                    long j17 = 0;
                                    while (j17 < DoubleDCT_3D.this.slicesl) {
                                        doubleLargeArray.setDouble((DoubleDCT_3D.this.sliceStridel * j17) + j14 + j15, doubleLargeArray2.getDouble(j17));
                                        j17++;
                                        j14 = j14;
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
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                    return;
                } catch (ExecutionException e6) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                    return;
                }
            }
        }
        long j13 = 0;
        while (true) {
            j = 1;
            if (j13 >= this.slicesl) {
                break;
            }
            long j14 = this.sliceStridel * j13;
            for (long j15 = 0; j15 < this.rowsl; j15++) {
                this.dctColumns.forward(doubleLargeArray, (this.rowStridel * j15) + j14, z);
            }
            j13++;
        }
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.rowsl, false);
        long j16 = 0;
        while (j16 < this.slicesl) {
            long j17 = this.sliceStridel * j16;
            long j18 = 0;
            while (j18 < this.columnsl) {
                for (long j19 = 0; j19 < this.rowsl; j19++) {
                    doubleLargeArray2.setDouble(j19, doubleLargeArray.getDouble((this.rowStridel * j19) + j17 + j18));
                }
                this.dctRows.forward(doubleLargeArray2, z);
                for (long j20 = 0; j20 < this.rowsl; j20++) {
                    doubleLargeArray.setDouble((this.rowStridel * j20) + j17 + j18, doubleLargeArray2.getDouble(j20));
                }
                j18++;
                j = 1;
            }
            long j21 = j;
            j16 += j21;
            j = j21;
        }
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.slicesl, false);
        for (long j22 = 0; j22 < this.rowsl; j22++) {
            long j23 = this.rowStridel * j22;
            for (long j24 = 0; j24 < this.columnsl; j24++) {
                for (long j25 = 0; j25 < this.slicesl; j25++) {
                    doubleLargeArray3.setDouble(j25, doubleLargeArray.getDouble((this.sliceStridel * j25) + j23 + j24));
                }
                this.dctSlices.forward(doubleLargeArray3, z);
                for (long j26 = 0; j26 < this.slicesl; j26++) {
                    doubleLargeArray.setDouble((this.sliceStridel * j26) + j23 + j24, doubleLargeArray3.getDouble(j26));
                }
            }
        }
    }

    public void forward(final double[][][] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(-1, dArr, z);
                ddxt3db_subth(-1, dArr, z);
                return;
            } else {
                ddxt3da_sub(-1, dArr, z);
                ddxt3db_sub(-1, dArr, z);
                return;
            }
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            for (int i9 = 0; i9 < DoubleDCT_3D.this.rows; i9++) {
                                DoubleDCT_3D.this.dctColumns.forward(dArr[i8][i9], z);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < DoubleDCT_3D.this.columns; i12++) {
                                for (int i13 = 0; i13 < DoubleDCT_3D.this.rows; i13++) {
                                    dArr2[i13] = dArr[i11][i13][i12];
                                }
                                DoubleDCT_3D.this.dctRows.forward(dArr2, z);
                                for (int i14 = 0; i14 < DoubleDCT_3D.this.rows; i14++) {
                                    dArr[i11][i14][i12] = dArr2[i14];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.9
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            for (int i15 = 0; i15 < DoubleDCT_3D.this.columns; i15++) {
                                for (int i16 = 0; i16 < DoubleDCT_3D.this.slices; i16++) {
                                    dArr2[i16] = dArr[i16][i14][i15];
                                }
                                DoubleDCT_3D.this.dctSlices.forward(dArr2, z);
                                for (int i17 = 0; i17 < DoubleDCT_3D.this.slices; i17++) {
                                    dArr[i17][i14][i15] = dArr2[i17];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i14 = 0; i14 < this.slices; i14++) {
            for (int i15 = 0; i15 < this.rows; i15++) {
                this.dctColumns.forward(dArr[i14][i15], z);
            }
        }
        double[] dArr2 = new double[this.rows];
        int i16 = 0;
        while (true) {
            i = this.slices;
            if (i16 >= i) {
                break;
            }
            for (int i17 = 0; i17 < this.columns; i17++) {
                for (int i18 = 0; i18 < this.rows; i18++) {
                    dArr2[i18] = dArr[i16][i18][i17];
                }
                this.dctRows.forward(dArr2, z);
                for (int i19 = 0; i19 < this.rows; i19++) {
                    dArr[i16][i19][i17] = dArr2[i19];
                }
            }
            i16++;
        }
        double[] dArr3 = new double[i];
        for (int i20 = 0; i20 < this.rows; i20++) {
            for (int i21 = 0; i21 < this.columns; i21++) {
                for (int i22 = 0; i22 < this.slices; i22++) {
                    dArr3[i22] = dArr[i22][i20][i21];
                }
                this.dctSlices.forward(dArr3, z);
                for (int i23 = 0; i23 < this.slices; i23++) {
                    dArr[i23][i20][i21] = dArr3[i23];
                }
            }
        }
    }

    public void inverse(final double[] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(1, dArr, z);
                ddxt3db_subth(1, dArr, z);
                return;
            } else {
                ddxt3da_sub(1, dArr, z);
                ddxt3db_sub(1, dArr, z);
                return;
            }
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.10
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            int i9 = DoubleDCT_3D.this.sliceStride * i8;
                            for (int i10 = 0; i10 < DoubleDCT_3D.this.rows; i10++) {
                                DoubleDCT_3D.this.dctColumns.inverse(dArr, (DoubleDCT_3D.this.rowStride * i10) + i9, z);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.11
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            int i12 = DoubleDCT_3D.this.sliceStride * i11;
                            for (int i13 = 0; i13 < DoubleDCT_3D.this.columns; i13++) {
                                for (int i14 = 0; i14 < DoubleDCT_3D.this.rows; i14++) {
                                    dArr2[i14] = dArr[(DoubleDCT_3D.this.rowStride * i14) + i12 + i13];
                                }
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, z);
                                for (int i15 = 0; i15 < DoubleDCT_3D.this.rows; i15++) {
                                    dArr[(DoubleDCT_3D.this.rowStride * i15) + i12 + i13] = dArr2[i15];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.12
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            int i15 = DoubleDCT_3D.this.rowStride * i14;
                            for (int i16 = 0; i16 < DoubleDCT_3D.this.columns; i16++) {
                                for (int i17 = 0; i17 < DoubleDCT_3D.this.slices; i17++) {
                                    dArr2[i17] = dArr[(DoubleDCT_3D.this.sliceStride * i17) + i15 + i16];
                                }
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, z);
                                for (int i18 = 0; i18 < DoubleDCT_3D.this.slices; i18++) {
                                    dArr[(DoubleDCT_3D.this.sliceStride * i18) + i15 + i16] = dArr2[i18];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i14 = 0; i14 < this.slices; i14++) {
            int i15 = this.sliceStride * i14;
            for (int i16 = 0; i16 < this.rows; i16++) {
                this.dctColumns.inverse(dArr, (this.rowStride * i16) + i15, z);
            }
        }
        double[] dArr2 = new double[this.rows];
        int i17 = 0;
        while (true) {
            i = this.slices;
            if (i17 >= i) {
                break;
            }
            int i18 = this.sliceStride * i17;
            for (int i19 = 0; i19 < this.columns; i19++) {
                for (int i20 = 0; i20 < this.rows; i20++) {
                    dArr2[i20] = dArr[(this.rowStride * i20) + i18 + i19];
                }
                this.dctRows.inverse(dArr2, z);
                for (int i21 = 0; i21 < this.rows; i21++) {
                    dArr[(this.rowStride * i21) + i18 + i19] = dArr2[i21];
                }
            }
            i17++;
        }
        double[] dArr3 = new double[i];
        for (int i22 = 0; i22 < this.rows; i22++) {
            int i23 = this.rowStride * i22;
            for (int i24 = 0; i24 < this.columns; i24++) {
                for (int i25 = 0; i25 < this.slices; i25++) {
                    dArr3[i25] = dArr[(this.sliceStride * i25) + i23 + i24];
                }
                this.dctSlices.inverse(dArr3, z);
                for (int i26 = 0; i26 < this.slices; i26++) {
                    dArr[(this.sliceStride * i26) + i23 + i24] = dArr3[i26];
                }
            }
        }
    }

    public void inverse(final DoubleLargeArray doubleLargeArray, final boolean z) {
        long j;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(1, doubleLargeArray, z);
                ddxt3db_subth(1, doubleLargeArray, z);
                return;
            } else {
                ddxt3da_sub(1, doubleLargeArray, z);
                ddxt3db_sub(1, doubleLargeArray, z);
                return;
            }
        }
        if (numberOfThreads > 1 && this.useThreads) {
            long j2 = this.slicesl;
            long j3 = numberOfThreads;
            if (j2 >= j3 && this.rowsl >= j3 && this.columnsl >= j3) {
                Future[] futureArr = new Future[numberOfThreads];
                long j4 = j2 / j3;
                int i = 0;
                while (i < numberOfThreads) {
                    final long j5 = i * j4;
                    final long j6 = i == numberOfThreads + (-1) ? this.slicesl : j5 + j4;
                    futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.13
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j7 = j5; j7 < j6; j7++) {
                                long j8 = DoubleDCT_3D.this.sliceStridel * j7;
                                for (long j9 = 0; j9 < DoubleDCT_3D.this.rowsl; j9++) {
                                    DoubleDCT_3D.this.dctColumns.inverse(doubleLargeArray, (DoubleDCT_3D.this.rowStridel * j9) + j8, z);
                                }
                            }
                        }
                    });
                    i++;
                    j3 = j3;
                }
                long j7 = j3;
                String str = null;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
                int i2 = 0;
                while (i2 < numberOfThreads) {
                    final long j8 = i2 * j4;
                    final long j9 = i2 == numberOfThreads + (-1) ? this.slicesl : j8 + j4;
                    futureArr[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.14
                        @Override // java.lang.Runnable
                        public void run() {
                            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(DoubleDCT_3D.this.rowsl, false);
                            for (long j10 = j8; j10 < j9; j10++) {
                                long j11 = DoubleDCT_3D.this.sliceStridel * j10;
                                for (long j12 = 0; j12 < DoubleDCT_3D.this.columnsl; j12++) {
                                    for (long j13 = 0; j13 < DoubleDCT_3D.this.rowsl; j13++) {
                                        doubleLargeArray2.setDouble(j13, doubleLargeArray.getDouble((DoubleDCT_3D.this.rowStridel * j13) + j11 + j12));
                                    }
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, z);
                                    long j14 = 0;
                                    while (j14 < DoubleDCT_3D.this.rowsl) {
                                        doubleLargeArray.setDouble((DoubleDCT_3D.this.rowStridel * j14) + j11 + j12, doubleLargeArray2.getDouble(j14));
                                        j14++;
                                        j11 = j11;
                                    }
                                }
                            }
                        }
                    });
                    i2++;
                    str = str;
                }
                String str2 = str;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e3) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e3);
                } catch (ExecutionException e4) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e4);
                }
                long j10 = this.rowsl / j7;
                int i3 = 0;
                while (i3 < numberOfThreads) {
                    final long j11 = i3 * j10;
                    final long j12 = i3 == numberOfThreads + (-1) ? this.rowsl : j11 + j10;
                    futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.15
                        @Override // java.lang.Runnable
                        public void run() {
                            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(DoubleDCT_3D.this.slicesl, false);
                            for (long j13 = j11; j13 < j12; j13++) {
                                long j14 = DoubleDCT_3D.this.rowStridel * j13;
                                for (long j15 = 0; j15 < DoubleDCT_3D.this.columnsl; j15++) {
                                    for (long j16 = 0; j16 < DoubleDCT_3D.this.slicesl; j16++) {
                                        doubleLargeArray2.setDouble(j16, doubleLargeArray.getDouble((DoubleDCT_3D.this.sliceStridel * j16) + j14 + j15));
                                    }
                                    DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, z);
                                    long j17 = 0;
                                    while (j17 < DoubleDCT_3D.this.slicesl) {
                                        doubleLargeArray.setDouble((DoubleDCT_3D.this.sliceStridel * j17) + j14 + j15, doubleLargeArray2.getDouble(j17));
                                        j17++;
                                        j14 = j14;
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
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                    return;
                } catch (ExecutionException e6) {
                    Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                    return;
                }
            }
        }
        long j13 = 0;
        while (true) {
            j = 1;
            if (j13 >= this.slicesl) {
                break;
            }
            long j14 = this.sliceStridel * j13;
            for (long j15 = 0; j15 < this.rowsl; j15++) {
                this.dctColumns.inverse(doubleLargeArray, (this.rowStridel * j15) + j14, z);
            }
            j13++;
        }
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.rowsl, false);
        long j16 = 0;
        while (j16 < this.slicesl) {
            long j17 = this.sliceStridel * j16;
            long j18 = 0;
            while (j18 < this.columnsl) {
                for (long j19 = 0; j19 < this.rowsl; j19++) {
                    doubleLargeArray2.setDouble(j19, doubleLargeArray.getDouble((this.rowStridel * j19) + j17 + j18));
                }
                this.dctRows.inverse(doubleLargeArray2, z);
                for (long j20 = 0; j20 < this.rowsl; j20++) {
                    doubleLargeArray.setDouble((this.rowStridel * j20) + j17 + j18, doubleLargeArray2.getDouble(j20));
                }
                j18++;
                j = 1;
            }
            long j21 = j;
            j16 += j21;
            j = j21;
        }
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.slicesl, false);
        for (long j22 = 0; j22 < this.rowsl; j22++) {
            long j23 = this.rowStridel * j22;
            for (long j24 = 0; j24 < this.columnsl; j24++) {
                for (long j25 = 0; j25 < this.slicesl; j25++) {
                    doubleLargeArray3.setDouble(j25, doubleLargeArray.getDouble((this.sliceStridel * j25) + j23 + j24));
                }
                this.dctSlices.inverse(doubleLargeArray3, z);
                for (long j26 = 0; j26 < this.slicesl; j26++) {
                    doubleLargeArray.setDouble((this.sliceStridel * j26) + j23 + j24, doubleLargeArray3.getDouble(j26));
                }
            }
        }
    }

    public void inverse(final double[][][] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt3da_subth(1, dArr, z);
                ddxt3db_subth(1, dArr, z);
                return;
            } else {
                ddxt3da_sub(1, dArr, z);
                ddxt3db_sub(1, dArr, z);
                return;
            }
        }
        int i3 = 0;
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.slices) >= numberOfThreads && this.rows >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.slices : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.16
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            for (int i9 = 0; i9 < DoubleDCT_3D.this.rows; i9++) {
                                DoubleDCT_3D.this.dctColumns.inverse(dArr[i8][i9], z);
                            }
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = 0;
            while (i8 < numberOfThreads) {
                final int i9 = i8 * i4;
                final int i10 = i8 == numberOfThreads + (-1) ? this.slices : i9 + i4;
                futureArr[i8] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.17
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < DoubleDCT_3D.this.columns; i12++) {
                                for (int i13 = 0; i13 < DoubleDCT_3D.this.rows; i13++) {
                                    dArr2[i13] = dArr[i11][i13][i12];
                                }
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, z);
                                for (int i14 = 0; i14 < DoubleDCT_3D.this.rows; i14++) {
                                    dArr[i11][i14][i12] = dArr2[i14];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            int i11 = this.rows / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i12 = i3 * i11;
                final int i13 = i3 == numberOfThreads + (-1) ? this.rows : i12 + i11;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.18
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDCT_3D.this.slices];
                        for (int i14 = i12; i14 < i13; i14++) {
                            for (int i15 = 0; i15 < DoubleDCT_3D.this.columns; i15++) {
                                for (int i16 = 0; i16 < DoubleDCT_3D.this.slices; i16++) {
                                    dArr2[i16] = dArr[i16][i14][i15];
                                }
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, z);
                                for (int i17 = 0; i17 < DoubleDCT_3D.this.slices; i17++) {
                                    dArr[i17][i14][i15] = dArr2[i17];
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
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e6);
                return;
            }
        }
        for (int i14 = 0; i14 < this.slices; i14++) {
            for (int i15 = 0; i15 < this.rows; i15++) {
                this.dctColumns.inverse(dArr[i14][i15], z);
            }
        }
        double[] dArr2 = new double[this.rows];
        int i16 = 0;
        while (true) {
            i = this.slices;
            if (i16 >= i) {
                break;
            }
            for (int i17 = 0; i17 < this.columns; i17++) {
                for (int i18 = 0; i18 < this.rows; i18++) {
                    dArr2[i18] = dArr[i16][i18][i17];
                }
                this.dctRows.inverse(dArr2, z);
                for (int i19 = 0; i19 < this.rows; i19++) {
                    dArr[i16][i19][i17] = dArr2[i19];
                }
            }
            i16++;
        }
        double[] dArr3 = new double[i];
        for (int i20 = 0; i20 < this.rows; i20++) {
            for (int i21 = 0; i21 < this.columns; i21++) {
                for (int i22 = 0; i22 < this.slices; i22++) {
                    dArr3[i22] = dArr[i22][i20][i21];
                }
                this.dctSlices.inverse(dArr3, z);
                for (int i23 = 0; i23 < this.slices; i23++) {
                    dArr[i23][i20][i21] = dArr3[i23];
                }
            }
        }
    }

    private void ddxt3da_sub(int i, double[] dArr, boolean z) {
        int i2 = this.rows * 4;
        if (this.columns == 2) {
            i2 >>= 1;
        }
        double[] dArr2 = new double[i2];
        if (i == -1) {
            for (int i3 = 0; i3 < this.slices; i3++) {
                int i4 = this.sliceStride * i3;
                for (int i5 = 0; i5 < this.rows; i5++) {
                    this.dctColumns.forward(dArr, (this.rowStride * i5) + i4, z);
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
                            dArr2[i8] = dArr[i10];
                            dArr2[i11] = dArr[i10 + 1];
                            dArr2[i11 + i9] = dArr[i10 + 2];
                            dArr2[i11 + (i9 * 2)] = dArr[i10 + 3];
                            i8++;
                        }
                        this.dctRows.forward(dArr2, 0, z);
                        this.dctRows.forward(dArr2, this.rows, z);
                        this.dctRows.forward(dArr2, this.rows * 2, z);
                        this.dctRows.forward(dArr2, this.rows * 3, z);
                        int i12 = 0;
                        while (true) {
                            int i13 = this.rows;
                            if (i12 < i13) {
                                int i14 = (this.rowStride * i12) + i4 + i7;
                                int i15 = i13 + i12;
                                dArr[i14] = dArr2[i12];
                                dArr[i14 + 1] = dArr2[i15];
                                dArr[i14 + 2] = dArr2[i15 + i13];
                                dArr[i14 + 3] = dArr2[i15 + (i13 * 2)];
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
                        dArr2[i16] = dArr[i18];
                        dArr2[i17 + i16] = dArr[i18 + 1];
                        i16++;
                    }
                    this.dctRows.forward(dArr2, 0, z);
                    this.dctRows.forward(dArr2, this.rows, z);
                    int i19 = 0;
                    while (true) {
                        int i20 = this.rows;
                        if (i19 < i20) {
                            int i21 = (this.rowStride * i19) + i4;
                            dArr[i21] = dArr2[i19];
                            dArr[i21 + 1] = dArr2[i20 + i19];
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
                this.dctColumns.inverse(dArr, (this.rowStride * i24) + i23, z);
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
                        dArr2[i27] = dArr[i29];
                        dArr2[i30] = dArr[i29 + 1];
                        dArr2[i30 + i28] = dArr[i29 + 2];
                        dArr2[i30 + (i28 * 2)] = dArr[i29 + 3];
                        i27++;
                    }
                    this.dctRows.inverse(dArr2, 0, z);
                    this.dctRows.inverse(dArr2, this.rows, z);
                    this.dctRows.inverse(dArr2, this.rows * 2, z);
                    this.dctRows.inverse(dArr2, this.rows * 3, z);
                    int i31 = 0;
                    while (true) {
                        int i32 = this.rows;
                        if (i31 < i32) {
                            int i33 = (this.rowStride * i31) + i23 + i26;
                            int i34 = i32 + i31;
                            dArr[i33] = dArr2[i31];
                            dArr[i33 + 1] = dArr2[i34];
                            dArr[i33 + 2] = dArr2[i34 + i32];
                            dArr[i33 + 3] = dArr2[i34 + (i32 * 2)];
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
                    dArr2[i35] = dArr[i37];
                    dArr2[i36 + i35] = dArr[i37 + 1];
                    i35++;
                }
                this.dctRows.inverse(dArr2, 0, z);
                this.dctRows.inverse(dArr2, this.rows, z);
                int i38 = 0;
                while (true) {
                    int i39 = this.rows;
                    if (i38 < i39) {
                        int i40 = (this.rowStride * i38) + i23;
                        dArr[i40] = dArr2[i38];
                        dArr[i40 + 1] = dArr2[i39 + i38];
                        i38++;
                    }
                }
            }
        }
    }

    private void ddxt3da_sub(int i, DoubleLargeArray doubleLargeArray, boolean z) {
        long j;
        boolean z2;
        long j2;
        boolean z3 = z;
        long j3 = this.rowsl * 4;
        long j4 = 2;
        if (this.columnsl == 2) {
            j3 >>= 1;
        }
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j3);
        if (i == -1) {
            long j5 = 0;
            while (j5 < this.slicesl) {
                long j6 = this.sliceStridel * j5;
                for (long j7 = 0; j7 < this.rowsl; j7++) {
                    this.dctColumns.forward(doubleLargeArray, (this.rowStridel * j7) + j6, z3);
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
                            doubleLargeArray2.setDouble(j10, doubleLargeArray.getDouble(j13));
                            doubleLargeArray2.setDouble(j14, doubleLargeArray.getDouble(j13 + 1));
                            doubleLargeArray2.setDouble(this.rowsl + j14, doubleLargeArray.getDouble(j13 + 2));
                            doubleLargeArray2.setDouble(j14 + (this.rowsl * 2), doubleLargeArray.getDouble(j13 + 3));
                            j10++;
                            j5 = j12;
                            j9 = j9;
                            j6 = j6;
                        }
                        long j15 = j5;
                        long j16 = j6;
                        long j17 = j9;
                        this.dctRows.forward(doubleLargeArray2, 0L, z3);
                        this.dctRows.forward(doubleLargeArray2, this.rowsl, z3);
                        this.dctRows.forward(doubleLargeArray2, this.rowsl * 2, z3);
                        this.dctRows.forward(doubleLargeArray2, this.rowsl * 3, z3);
                        long j18 = 0;
                        while (true) {
                            long j19 = this.rowsl;
                            if (j18 < j19) {
                                long j20 = j16 + (this.rowStridel * j18) + j17;
                                long j21 = j19 + j18;
                                doubleLargeArray.setDouble(j20, doubleLargeArray2.getDouble(j18));
                                doubleLargeArray.setDouble(j20 + 1, doubleLargeArray2.getDouble(j21));
                                doubleLargeArray.setDouble(j20 + 2, doubleLargeArray2.getDouble(this.rowsl + j21));
                                doubleLargeArray.setDouble(j20 + 3, doubleLargeArray2.getDouble(j21 + (this.rowsl * 2)));
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
                            doubleLargeArray2.setDouble(j22, doubleLargeArray.getDouble(j23));
                            doubleLargeArray2.setDouble(this.rowsl + j22, doubleLargeArray.getDouble(j23 + 1));
                        }
                        this.dctRows.forward(doubleLargeArray2, 0L, z3);
                        this.dctRows.forward(doubleLargeArray2, this.rowsl, z3);
                        for (long j24 = 0; j24 < this.rowsl; j24++) {
                            long j25 = j6 + (this.rowStridel * j24);
                            doubleLargeArray.setDouble(j25, doubleLargeArray2.getDouble(j24));
                            doubleLargeArray.setDouble(j25 + 1, doubleLargeArray2.getDouble(this.rowsl + j24));
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
                this.dctColumns.inverse(doubleLargeArray, (this.rowStridel * j28) + j27, z3);
            }
            long j29 = this.columnsl;
            if (j29 > 2) {
                long j30 = 0;
                while (true) {
                    j = j26;
                    if (j30 >= this.columnsl) {
                        break;
                    }
                    long j31 = 0;
                    while (true) {
                        long j32 = this.rowsl;
                        if (j31 >= j32) {
                            break;
                        }
                        long j33 = (this.rowStridel * j31) + j27 + j30;
                        long j34 = j32 + j31;
                        doubleLargeArray2.setDouble(j31, doubleLargeArray.getDouble(j33));
                        doubleLargeArray2.setDouble(j34, doubleLargeArray.getDouble(j33 + 1));
                        doubleLargeArray2.setDouble(this.rowsl + j34, doubleLargeArray.getDouble(j33 + 2));
                        doubleLargeArray2.setDouble(j34 + (this.rowsl * 2), doubleLargeArray.getDouble(j33 + 3));
                        j31++;
                        j30 = j30;
                        j27 = j27;
                    }
                    long j35 = j27;
                    long j36 = j30;
                    this.dctRows.inverse(doubleLargeArray2, 0L, z3);
                    this.dctRows.inverse(doubleLargeArray2, this.rowsl, z3);
                    this.dctRows.inverse(doubleLargeArray2, this.rowsl * 2, z3);
                    this.dctRows.inverse(doubleLargeArray2, this.rowsl * 3, z3);
                    long j37 = 0;
                    while (true) {
                        long j38 = this.rowsl;
                        if (j37 < j38) {
                            long j39 = j35 + (this.rowStridel * j37) + j36;
                            long j40 = j38 + j37;
                            doubleLargeArray.setDouble(j39, doubleLargeArray2.getDouble(j37));
                            doubleLargeArray.setDouble(j39 + 1, doubleLargeArray2.getDouble(j40));
                            doubleLargeArray.setDouble(j39 + 2, doubleLargeArray2.getDouble(this.rowsl + j40));
                            doubleLargeArray.setDouble(j39 + 3, doubleLargeArray2.getDouble(j40 + (this.rowsl * 2)));
                            j37++;
                        }
                    }
                    j30 = j36 + 4;
                    z3 = z;
                    j26 = j;
                    j27 = j35;
                }
            } else {
                j = j26;
                if (j29 == 2) {
                    for (long j41 = 0; j41 < this.rowsl; j41++) {
                        long j42 = j27 + (this.rowStridel * j41);
                        doubleLargeArray2.setDouble(j41, doubleLargeArray.getDouble(j42));
                        doubleLargeArray2.setDouble(this.rowsl + j41, doubleLargeArray.getDouble(j42 + 1));
                    }
                    z2 = z;
                    this.dctRows.inverse(doubleLargeArray2, 0L, z2);
                    this.dctRows.inverse(doubleLargeArray2, this.rowsl, z2);
                    for (long j43 = 0; j43 < this.rowsl; j43++) {
                        long j44 = j27 + (this.rowStridel * j43);
                        doubleLargeArray.setDouble(j44, doubleLargeArray2.getDouble(j43));
                        doubleLargeArray.setDouble(j44 + 1, doubleLargeArray2.getDouble(this.rowsl + j43));
                    }
                }
                j26 = j + 1;
                z3 = z2;
            }
            z2 = z;
            j26 = j + 1;
            z3 = z2;
        }
    }

    private void ddxt3da_sub(int i, double[][][] dArr, boolean z) {
        int i2 = this.rows * 4;
        if (this.columns == 2) {
            i2 >>= 1;
        }
        double[] dArr2 = new double[i2];
        if (i == -1) {
            for (int i3 = 0; i3 < this.slices; i3++) {
                for (int i4 = 0; i4 < this.rows; i4++) {
                    this.dctColumns.forward(dArr[i3][i4], z);
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
                            double[] dArr3 = dArr[i3][i7];
                            dArr2[i7] = dArr3[i6];
                            dArr2[i9] = dArr3[i6 + 1];
                            dArr2[i9 + i8] = dArr3[i6 + 2];
                            dArr2[i9 + (i8 * 2)] = dArr3[i6 + 3];
                            i7++;
                        }
                        this.dctRows.forward(dArr2, 0, z);
                        this.dctRows.forward(dArr2, this.rows, z);
                        this.dctRows.forward(dArr2, this.rows * 2, z);
                        this.dctRows.forward(dArr2, this.rows * 3, z);
                        int i10 = 0;
                        while (true) {
                            int i11 = this.rows;
                            if (i10 < i11) {
                                int i12 = i11 + i10;
                                double[] dArr4 = dArr[i3][i10];
                                dArr4[i6] = dArr2[i10];
                                dArr4[i6 + 1] = dArr2[i12];
                                dArr4[i6 + 2] = dArr2[i12 + i11];
                                dArr4[i6 + 3] = dArr2[i12 + (i11 * 2)];
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
                        double[] dArr5 = dArr[i3][i13];
                        dArr2[i13] = dArr5[0];
                        dArr2[i14 + i13] = dArr5[1];
                        i13++;
                    }
                    this.dctRows.forward(dArr2, 0, z);
                    this.dctRows.forward(dArr2, this.rows, z);
                    int i15 = 0;
                    while (true) {
                        int i16 = this.rows;
                        if (i15 < i16) {
                            double[] dArr6 = dArr[i3][i15];
                            dArr6[0] = dArr2[i15];
                            dArr6[1] = dArr2[i16 + i15];
                            i15++;
                        }
                    }
                }
            }
            return;
        }
        for (int i17 = 0; i17 < this.slices; i17++) {
            for (int i18 = 0; i18 < this.rows; i18++) {
                this.dctColumns.inverse(dArr[i17][i18], z);
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
                        double[] dArr7 = dArr[i17][i21];
                        dArr2[i21] = dArr7[i20];
                        dArr2[i23] = dArr7[i20 + 1];
                        dArr2[i23 + i22] = dArr7[i20 + 2];
                        dArr2[i23 + (i22 * 2)] = dArr7[i20 + 3];
                        i21++;
                    }
                    this.dctRows.inverse(dArr2, 0, z);
                    this.dctRows.inverse(dArr2, this.rows, z);
                    this.dctRows.inverse(dArr2, this.rows * 2, z);
                    this.dctRows.inverse(dArr2, this.rows * 3, z);
                    int i24 = 0;
                    while (true) {
                        int i25 = this.rows;
                        if (i24 < i25) {
                            int i26 = i25 + i24;
                            double[] dArr8 = dArr[i17][i24];
                            dArr8[i20] = dArr2[i24];
                            dArr8[i20 + 1] = dArr2[i26];
                            dArr8[i20 + 2] = dArr2[i26 + i25];
                            dArr8[i20 + 3] = dArr2[i26 + (i25 * 2)];
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
                    double[] dArr9 = dArr[i17][i27];
                    dArr2[i27] = dArr9[0];
                    dArr2[i28 + i27] = dArr9[1];
                    i27++;
                }
                this.dctRows.inverse(dArr2, 0, z);
                this.dctRows.inverse(dArr2, this.rows, z);
                int i29 = 0;
                while (true) {
                    int i30 = this.rows;
                    if (i29 < i30) {
                        double[] dArr10 = dArr[i17][i29];
                        dArr10[0] = dArr2[i29];
                        dArr10[1] = dArr2[i30 + i29];
                        i29++;
                    }
                }
            }
        }
    }

    private void ddxt3db_sub(int i, double[] dArr, boolean z) {
        int i2 = this.slices * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        }
        double[] dArr2 = new double[i2];
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
                            dArr2[i6] = dArr[i8];
                            dArr2[i7 + i6] = dArr[i8 + 1];
                            i6++;
                        }
                        this.dctSlices.forward(dArr2, 0, z);
                        this.dctSlices.forward(dArr2, this.slices, z);
                        int i9 = 0;
                        while (true) {
                            int i10 = this.slices;
                            if (i9 < i10) {
                                int i11 = (this.sliceStride * i9) + i5;
                                dArr[i11] = dArr2[i9];
                                dArr[i11 + 1] = dArr2[i10 + i9];
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
                        dArr2[i15] = dArr[i17];
                        dArr2[i18] = dArr[i17 + 1];
                        dArr2[i18 + i16] = dArr[i17 + 2];
                        dArr2[i18 + (i16 * 2)] = dArr[i17 + 3];
                        i15++;
                    }
                    this.dctSlices.forward(dArr2, 0, z);
                    this.dctSlices.forward(dArr2, this.slices, z);
                    this.dctSlices.forward(dArr2, this.slices * 2, z);
                    this.dctSlices.forward(dArr2, this.slices * 3, z);
                    int i19 = 0;
                    while (true) {
                        int i20 = this.slices;
                        if (i19 < i20) {
                            int i21 = (this.sliceStride * i19) + i13 + i14;
                            int i22 = i20 + i19;
                            dArr[i21] = dArr2[i19];
                            dArr[i21 + 1] = dArr2[i22];
                            dArr[i21 + 2] = dArr2[i22 + i20];
                            dArr[i21 + 3] = dArr2[i22 + (i20 * 2)];
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
                        dArr2[i25] = dArr[i27];
                        dArr2[i26 + i25] = dArr[i27 + 1];
                        i25++;
                    }
                    this.dctSlices.inverse(dArr2, 0, z);
                    this.dctSlices.inverse(dArr2, this.slices, z);
                    int i28 = 0;
                    while (true) {
                        int i29 = this.slices;
                        if (i28 < i29) {
                            int i30 = (this.sliceStride * i28) + i24;
                            dArr[i30] = dArr2[i28];
                            dArr[i30 + 1] = dArr2[i29 + i28];
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
                    dArr2[i34] = dArr[i36];
                    dArr2[i37] = dArr[i36 + 1];
                    dArr2[i37 + i35] = dArr[i36 + 2];
                    dArr2[i37 + (i35 * 2)] = dArr[i36 + 3];
                    i34++;
                }
                this.dctSlices.inverse(dArr2, 0, z);
                this.dctSlices.inverse(dArr2, this.slices, z);
                this.dctSlices.inverse(dArr2, this.slices * 2, z);
                this.dctSlices.inverse(dArr2, this.slices * 3, z);
                int i38 = 0;
                while (true) {
                    int i39 = this.slices;
                    if (i38 < i39) {
                        int i40 = (this.sliceStride * i38) + i32 + i33;
                        int i41 = i39 + i38;
                        dArr[i40] = dArr2[i38];
                        dArr[i40 + 1] = dArr2[i41];
                        dArr[i40 + 2] = dArr2[i41 + i39];
                        dArr[i40 + 3] = dArr2[i41 + (i39 * 2)];
                        i38++;
                    }
                }
            }
        }
    }

    private void ddxt3db_sub(int i, DoubleLargeArray doubleLargeArray, boolean z) {
        boolean z2 = z;
        long j = this.slicesl * 4;
        long j2 = 2;
        if (this.columnsl == 2) {
            j >>= 1;
        }
        DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j);
        if (i == -1) {
            long j3 = this.columnsl;
            if (j3 <= 2) {
                if (j3 == 2) {
                    for (long j4 = 0; j4 < this.rowsl; j4++) {
                        long j5 = this.rowStridel * j4;
                        for (long j6 = 0; j6 < this.slicesl; j6++) {
                            long j7 = (this.sliceStridel * j6) + j5;
                            doubleLargeArray2.setDouble(j6, doubleLargeArray.getDouble(j7));
                            doubleLargeArray2.setDouble(this.slicesl + j6, doubleLargeArray.getDouble(j7 + 1));
                        }
                        this.dctSlices.forward(doubleLargeArray2, 0L, z2);
                        this.dctSlices.forward(doubleLargeArray2, this.slicesl, z2);
                        for (long j8 = 0; j8 < this.slicesl; j8++) {
                            long j9 = (this.sliceStridel * j8) + j5;
                            doubleLargeArray.setDouble(j9, doubleLargeArray2.getDouble(j8));
                            doubleLargeArray.setDouble(j9 + 1, doubleLargeArray2.getDouble(this.slicesl + j8));
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
                        doubleLargeArray2.setDouble(j13, doubleLargeArray.getDouble(j15));
                        doubleLargeArray2.setDouble(j16, doubleLargeArray.getDouble(j15 + 1));
                        doubleLargeArray2.setDouble(this.slicesl + j16, doubleLargeArray.getDouble(j15 + 2));
                        doubleLargeArray2.setDouble(j16 + (this.slicesl * 2), doubleLargeArray.getDouble(j15 + 3));
                        j13++;
                        j10 = j10;
                        j11 = j11;
                    }
                    long j17 = j10;
                    long j18 = j11;
                    this.dctSlices.forward(doubleLargeArray2, 0L, z2);
                    this.dctSlices.forward(doubleLargeArray2, this.slicesl, z2);
                    this.dctSlices.forward(doubleLargeArray2, this.slicesl * 2, z2);
                    this.dctSlices.forward(doubleLargeArray2, this.slicesl * 3, z2);
                    long j19 = 0;
                    while (true) {
                        long j20 = this.slicesl;
                        if (j19 < j20) {
                            long j21 = (this.sliceStridel * j19) + j18 + j12;
                            long j22 = j20 + j19;
                            doubleLargeArray.setDouble(j21, doubleLargeArray2.getDouble(j19));
                            doubleLargeArray.setDouble(j21 + 1, doubleLargeArray2.getDouble(j22));
                            doubleLargeArray.setDouble(j21 + 2, doubleLargeArray2.getDouble(this.slicesl + j22));
                            doubleLargeArray.setDouble(j21 + 3, doubleLargeArray2.getDouble(j22 + (this.slicesl * 2)));
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
                        doubleLargeArray2.setDouble(j26, doubleLargeArray.getDouble(j27));
                        doubleLargeArray2.setDouble(this.slicesl + j26, doubleLargeArray.getDouble(j27 + 1));
                    }
                    this.dctSlices.inverse(doubleLargeArray2, 0L, z);
                    this.dctSlices.inverse(doubleLargeArray2, this.slicesl, z);
                    for (long j28 = 0; j28 < this.slicesl; j28++) {
                        long j29 = (this.sliceStridel * j28) + j25;
                        doubleLargeArray.setDouble(j29, doubleLargeArray2.getDouble(j28));
                        doubleLargeArray.setDouble(j29 + 1, doubleLargeArray2.getDouble(this.slicesl + j28));
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
                long j33 = j30;
                long j34 = 0;
                while (true) {
                    long j35 = this.slicesl;
                    if (j34 >= j35) {
                        break;
                    }
                    long j36 = (this.sliceStridel * j34) + j31 + j32;
                    long j37 = j35 + j34;
                    doubleLargeArray2.setDouble(j34, doubleLargeArray.getDouble(j36));
                    doubleLargeArray2.setDouble(j37, doubleLargeArray.getDouble(j36 + 1));
                    doubleLargeArray2.setDouble(this.slicesl + j37, doubleLargeArray.getDouble(j36 + 2));
                    doubleLargeArray2.setDouble(j37 + (this.slicesl * 2), doubleLargeArray.getDouble(j36 + 3));
                    j34++;
                    j32 = j32;
                    j31 = j31;
                }
                long j38 = j31;
                long j39 = j32;
                this.dctSlices.inverse(doubleLargeArray2, 0L, z2);
                this.dctSlices.inverse(doubleLargeArray2, this.slicesl, z2);
                this.dctSlices.inverse(doubleLargeArray2, this.slicesl * 2, z2);
                this.dctSlices.inverse(doubleLargeArray2, this.slicesl * 3, z2);
                long j40 = 0;
                while (true) {
                    long j41 = this.slicesl;
                    if (j40 < j41) {
                        long j42 = (this.sliceStridel * j40) + j38 + j39;
                        long j43 = j41 + j40;
                        doubleLargeArray.setDouble(j42, doubleLargeArray2.getDouble(j40));
                        doubleLargeArray.setDouble(j42 + 1, doubleLargeArray2.getDouble(j43));
                        doubleLargeArray.setDouble(j42 + 2, doubleLargeArray2.getDouble(this.slicesl + j43));
                        doubleLargeArray.setDouble(j42 + 3, doubleLargeArray2.getDouble(j43 + (this.slicesl * 2)));
                        j40++;
                    }
                }
                j32 = j39 + 4;
                z2 = z;
                j30 = j33;
                j31 = j38;
            }
            z2 = z;
            j30++;
        }
    }

    private void ddxt3db_sub(int i, double[][][] dArr, boolean z) {
        int i2 = this.slices * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        }
        double[] dArr2 = new double[i2];
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
                            double[] dArr3 = dArr[i5][i4];
                            dArr2[i5] = dArr3[0];
                            dArr2[i6 + i5] = dArr3[1];
                            i5++;
                        }
                        this.dctSlices.forward(dArr2, 0, z);
                        this.dctSlices.forward(dArr2, this.slices, z);
                        int i7 = 0;
                        while (true) {
                            int i8 = this.slices;
                            if (i7 < i8) {
                                double[] dArr4 = dArr[i7][i4];
                                dArr4[0] = dArr2[i7];
                                dArr4[1] = dArr2[i8 + i7];
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
                        double[] dArr5 = dArr[i11][i9];
                        dArr2[i11] = dArr5[i10];
                        dArr2[i13] = dArr5[i10 + 1];
                        dArr2[i13 + i12] = dArr5[i10 + 2];
                        dArr2[i13 + (i12 * 2)] = dArr5[i10 + 3];
                        i11++;
                    }
                    this.dctSlices.forward(dArr2, 0, z);
                    this.dctSlices.forward(dArr2, this.slices, z);
                    this.dctSlices.forward(dArr2, this.slices * 2, z);
                    this.dctSlices.forward(dArr2, this.slices * 3, z);
                    int i14 = 0;
                    while (true) {
                        int i15 = this.slices;
                        if (i14 < i15) {
                            int i16 = i15 + i14;
                            double[] dArr6 = dArr[i14][i9];
                            dArr6[i10] = dArr2[i14];
                            dArr6[i10 + 1] = dArr2[i16];
                            dArr6[i10 + 2] = dArr2[i16 + i15];
                            dArr6[i10 + 3] = dArr2[i16 + (i15 * 2)];
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
                        double[] dArr7 = dArr[i18][i17];
                        dArr2[i18] = dArr7[0];
                        dArr2[i19 + i18] = dArr7[1];
                        i18++;
                    }
                    this.dctSlices.inverse(dArr2, 0, z);
                    this.dctSlices.inverse(dArr2, this.slices, z);
                    int i20 = 0;
                    while (true) {
                        int i21 = this.slices;
                        if (i20 < i21) {
                            double[] dArr8 = dArr[i20][i17];
                            dArr8[0] = dArr2[i20];
                            dArr8[1] = dArr2[i21 + i20];
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
                    double[] dArr9 = dArr[i24][i22];
                    dArr2[i24] = dArr9[i23];
                    dArr2[i26] = dArr9[i23 + 1];
                    dArr2[i26 + i25] = dArr9[i23 + 2];
                    dArr2[i26 + (i25 * 2)] = dArr9[i23 + 3];
                    i24++;
                }
                this.dctSlices.inverse(dArr2, 0, z);
                this.dctSlices.inverse(dArr2, this.slices, z);
                this.dctSlices.inverse(dArr2, this.slices * 2, z);
                this.dctSlices.inverse(dArr2, this.slices * 3, z);
                int i27 = 0;
                while (true) {
                    int i28 = this.slices;
                    if (i27 < i28) {
                        int i29 = i28 + i27;
                        double[] dArr10 = dArr[i27][i22];
                        dArr10[i23] = dArr2[i27];
                        dArr10[i23 + 1] = dArr2[i29];
                        dArr10[i23 + 2] = dArr2[i29 + i28];
                        dArr10[i23 + 3] = dArr2[i29 + (i28 * 2)];
                        i27++;
                    }
                }
            }
        }
    }

    private void ddxt3da_subth(final int i, final double[] dArr, final boolean z) {
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
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.19
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    if (i == -1) {
                        int i7 = i6;
                        while (i7 < DoubleDCT_3D.this.slices) {
                            int i8 = DoubleDCT_3D.this.sliceStride * i7;
                            for (int i9 = 0; i9 < DoubleDCT_3D.this.rows; i9++) {
                                DoubleDCT_3D.this.dctColumns.forward(dArr, (DoubleDCT_3D.this.rowStride * i9) + i8, z);
                            }
                            if (DoubleDCT_3D.this.columns > 2) {
                                for (int i10 = 0; i10 < DoubleDCT_3D.this.columns; i10 += 4) {
                                    for (int i11 = 0; i11 < DoubleDCT_3D.this.rows; i11++) {
                                        int i12 = (DoubleDCT_3D.this.rowStride * i11) + i8 + i10;
                                        int i13 = DoubleDCT_3D.this.rows + i11;
                                        double[] dArr3 = dArr;
                                        dArr2[i11] = dArr3[i12];
                                        dArr2[i13] = dArr3[i12 + 1];
                                        dArr2[DoubleDCT_3D.this.rows + i13] = dArr[i12 + 2];
                                        dArr2[i13 + (DoubleDCT_3D.this.rows * 2)] = dArr[i12 + 3];
                                    }
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, 0, z);
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows, z);
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows * 2, z);
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows * 3, z);
                                    for (int i14 = 0; i14 < DoubleDCT_3D.this.rows; i14++) {
                                        int i15 = (DoubleDCT_3D.this.rowStride * i14) + i8 + i10;
                                        int i16 = DoubleDCT_3D.this.rows + i14;
                                        double[] dArr4 = dArr;
                                        dArr4[i15] = dArr2[i14];
                                        dArr4[i15 + 1] = dArr2[i16];
                                        dArr4[i15 + 2] = dArr2[DoubleDCT_3D.this.rows + i16];
                                        dArr[i15 + 3] = dArr2[i16 + (DoubleDCT_3D.this.rows * 2)];
                                    }
                                }
                            } else if (DoubleDCT_3D.this.columns == 2) {
                                for (int i17 = 0; i17 < DoubleDCT_3D.this.rows; i17++) {
                                    int i18 = (DoubleDCT_3D.this.rowStride * i17) + i8;
                                    dArr2[i17] = dArr[i18];
                                    dArr2[DoubleDCT_3D.this.rows + i17] = dArr[i18 + 1];
                                }
                                DoubleDCT_3D.this.dctRows.forward(dArr2, 0, z);
                                DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows, z);
                                for (int i19 = 0; i19 < DoubleDCT_3D.this.rows; i19++) {
                                    int i20 = (DoubleDCT_3D.this.rowStride * i19) + i8;
                                    double[] dArr5 = dArr;
                                    dArr5[i20] = dArr2[i19];
                                    dArr5[i20 + 1] = dArr2[DoubleDCT_3D.this.rows + i19];
                                }
                            }
                            i7 += i2;
                        }
                        return;
                    }
                    int i21 = i6;
                    while (i21 < DoubleDCT_3D.this.slices) {
                        int i22 = DoubleDCT_3D.this.sliceStride * i21;
                        for (int i23 = 0; i23 < DoubleDCT_3D.this.rows; i23++) {
                            DoubleDCT_3D.this.dctColumns.inverse(dArr, (DoubleDCT_3D.this.rowStride * i23) + i22, z);
                        }
                        if (DoubleDCT_3D.this.columns > 2) {
                            for (int i24 = 0; i24 < DoubleDCT_3D.this.columns; i24 += 4) {
                                for (int i25 = 0; i25 < DoubleDCT_3D.this.rows; i25++) {
                                    int i26 = (DoubleDCT_3D.this.rowStride * i25) + i22 + i24;
                                    int i27 = DoubleDCT_3D.this.rows + i25;
                                    double[] dArr6 = dArr;
                                    dArr2[i25] = dArr6[i26];
                                    dArr2[i27] = dArr6[i26 + 1];
                                    dArr2[DoubleDCT_3D.this.rows + i27] = dArr[i26 + 2];
                                    dArr2[i27 + (DoubleDCT_3D.this.rows * 2)] = dArr[i26 + 3];
                                }
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, 0, z);
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows, z);
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows * 2, z);
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows * 3, z);
                                for (int i28 = 0; i28 < DoubleDCT_3D.this.rows; i28++) {
                                    int i29 = (DoubleDCT_3D.this.rowStride * i28) + i22 + i24;
                                    int i30 = DoubleDCT_3D.this.rows + i28;
                                    double[] dArr7 = dArr;
                                    dArr7[i29] = dArr2[i28];
                                    dArr7[i29 + 1] = dArr2[i30];
                                    dArr7[i29 + 2] = dArr2[DoubleDCT_3D.this.rows + i30];
                                    dArr[i29 + 3] = dArr2[i30 + (DoubleDCT_3D.this.rows * 2)];
                                }
                            }
                        } else if (DoubleDCT_3D.this.columns == 2) {
                            for (int i31 = 0; i31 < DoubleDCT_3D.this.rows; i31++) {
                                int i32 = (DoubleDCT_3D.this.rowStride * i31) + i22;
                                dArr2[i31] = dArr[i32];
                                dArr2[DoubleDCT_3D.this.rows + i31] = dArr[i32 + 1];
                            }
                            DoubleDCT_3D.this.dctRows.inverse(dArr2, 0, z);
                            DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows, z);
                            for (int i33 = 0; i33 < DoubleDCT_3D.this.rows; i33++) {
                                int i34 = (DoubleDCT_3D.this.rowStride * i33) + i22;
                                double[] dArr8 = dArr;
                                dArr8[i34] = dArr2[i33];
                                dArr8[i34 + 1] = dArr2[DoubleDCT_3D.this.rows + i33];
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
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3da_subth(final int i, final DoubleLargeArray doubleLargeArray, final boolean z) {
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
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.20
                @Override // java.lang.Runnable
                public void run() {
                    long j4;
                    long j5;
                    DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j2);
                    long j6 = 2;
                    long j7 = 1;
                    if (i != -1) {
                        long j8 = j3;
                        while (j8 < DoubleDCT_3D.this.slicesl) {
                            long j9 = DoubleDCT_3D.this.sliceStridel * j8;
                            for (long j10 = 0; j10 < DoubleDCT_3D.this.rowsl; j10++) {
                                DoubleDCT_3D.this.dctColumns.inverse(doubleLargeArray, (DoubleDCT_3D.this.rowStridel * j10) + j9, z);
                            }
                            if (DoubleDCT_3D.this.columnsl > 2) {
                                long j11 = 0;
                                while (j11 < DoubleDCT_3D.this.columnsl) {
                                    long j12 = 0;
                                    while (j12 < DoubleDCT_3D.this.rowsl) {
                                        long j13 = (DoubleDCT_3D.this.rowStridel * j12) + j9 + j11;
                                        long j14 = DoubleDCT_3D.this.rowsl + j12;
                                        doubleLargeArray2.setDouble(j12, doubleLargeArray.getDouble(j13));
                                        doubleLargeArray2.setDouble(j14, doubleLargeArray.getDouble(j13 + 1));
                                        doubleLargeArray2.setDouble(DoubleDCT_3D.this.rowsl + j14, doubleLargeArray.getDouble(j13 + 2));
                                        doubleLargeArray2.setDouble(j14 + (DoubleDCT_3D.this.rowsl * 2), doubleLargeArray.getDouble(j13 + 3));
                                        j12++;
                                        j8 = j8;
                                        j11 = j11;
                                    }
                                    long j15 = j8;
                                    long j16 = j11;
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, 0L, z);
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, DoubleDCT_3D.this.rowsl, z);
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, DoubleDCT_3D.this.rowsl * 2, z);
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, DoubleDCT_3D.this.rowsl * 3, z);
                                    for (long j17 = 0; j17 < DoubleDCT_3D.this.rowsl; j17++) {
                                        long j18 = (DoubleDCT_3D.this.rowStridel * j17) + j9 + j16;
                                        long j19 = DoubleDCT_3D.this.rowsl + j17;
                                        doubleLargeArray.setDouble(j18, doubleLargeArray2.getDouble(j17));
                                        doubleLargeArray.setDouble(j18 + 1, doubleLargeArray2.getDouble(j19));
                                        doubleLargeArray.setDouble(j18 + 2, doubleLargeArray2.getDouble(j19 + DoubleDCT_3D.this.rowsl));
                                        doubleLargeArray.setDouble(j18 + 3, doubleLargeArray2.getDouble(j19 + (DoubleDCT_3D.this.rowsl * 2)));
                                    }
                                    j11 = j16 + 4;
                                    j8 = j15;
                                }
                                j4 = j8;
                            } else {
                                j4 = j8;
                                if (DoubleDCT_3D.this.columnsl == 2) {
                                    for (long j20 = 0; j20 < DoubleDCT_3D.this.rowsl; j20++) {
                                        long j21 = (DoubleDCT_3D.this.rowStridel * j20) + j9;
                                        doubleLargeArray2.setDouble(j20, doubleLargeArray.getDouble(j21));
                                        doubleLargeArray2.setDouble(DoubleDCT_3D.this.rowsl + j20, doubleLargeArray.getDouble(j21 + 1));
                                    }
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, 0L, z);
                                    DoubleDCT_3D.this.dctRows.inverse(doubleLargeArray2, DoubleDCT_3D.this.rowsl, z);
                                    for (long j22 = 0; j22 < DoubleDCT_3D.this.rowsl; j22++) {
                                        long j23 = (DoubleDCT_3D.this.rowStridel * j22) + j9;
                                        doubleLargeArray.setDouble(j23, doubleLargeArray2.getDouble(j22));
                                        doubleLargeArray.setDouble(j23 + 1, doubleLargeArray2.getDouble(DoubleDCT_3D.this.rowsl + j22));
                                    }
                                }
                            }
                            j8 = j4 + i2;
                        }
                        return;
                    }
                    long j24 = j3;
                    while (j24 < DoubleDCT_3D.this.slicesl) {
                        long j25 = DoubleDCT_3D.this.sliceStridel * j24;
                        for (long j26 = 0; j26 < DoubleDCT_3D.this.rowsl; j26 += j7) {
                            DoubleDCT_3D.this.dctColumns.forward(doubleLargeArray, (DoubleDCT_3D.this.rowStridel * j26) + j25, z);
                        }
                        if (DoubleDCT_3D.this.columnsl > j6) {
                            long j27 = 0;
                            while (j27 < DoubleDCT_3D.this.columnsl) {
                                long j28 = 0;
                                while (j28 < DoubleDCT_3D.this.rowsl) {
                                    long j29 = (DoubleDCT_3D.this.rowStridel * j28) + j25 + j27;
                                    long j30 = DoubleDCT_3D.this.rowsl + j28;
                                    doubleLargeArray2.setDouble(j28, doubleLargeArray.getDouble(j29));
                                    doubleLargeArray2.setDouble(j30, doubleLargeArray.getDouble(j29 + 1));
                                    doubleLargeArray2.setDouble(DoubleDCT_3D.this.rowsl + j30, doubleLargeArray.getDouble(j29 + 2));
                                    doubleLargeArray2.setDouble(j30 + (DoubleDCT_3D.this.rowsl * 2), doubleLargeArray.getDouble(j29 + 3));
                                    j28++;
                                    j24 = j24;
                                    j27 = j27;
                                }
                                long j31 = j24;
                                long j32 = j27;
                                DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, 0L, z);
                                DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, DoubleDCT_3D.this.rowsl, z);
                                DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, DoubleDCT_3D.this.rowsl * 2, z);
                                DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, DoubleDCT_3D.this.rowsl * 3, z);
                                for (long j33 = 0; j33 < DoubleDCT_3D.this.rowsl; j33++) {
                                    long j34 = (DoubleDCT_3D.this.rowStridel * j33) + j25 + j32;
                                    long j35 = DoubleDCT_3D.this.rowsl + j33;
                                    doubleLargeArray.setDouble(j34, doubleLargeArray2.getDouble(j33));
                                    doubleLargeArray.setDouble(j34 + 1, doubleLargeArray2.getDouble(j35));
                                    doubleLargeArray.setDouble(j34 + 2, doubleLargeArray2.getDouble(j35 + DoubleDCT_3D.this.rowsl));
                                    doubleLargeArray.setDouble(j34 + 3, doubleLargeArray2.getDouble(j35 + (DoubleDCT_3D.this.rowsl * 2)));
                                }
                                j27 = j32 + 4;
                                j24 = j31;
                            }
                            j5 = j24;
                        } else {
                            j5 = j24;
                            if (DoubleDCT_3D.this.columnsl == j6) {
                                for (long j36 = 0; j36 < DoubleDCT_3D.this.rowsl; j36++) {
                                    long j37 = (DoubleDCT_3D.this.rowStridel * j36) + j25;
                                    doubleLargeArray2.setDouble(j36, doubleLargeArray.getDouble(j37));
                                    doubleLargeArray2.setDouble(DoubleDCT_3D.this.rowsl + j36, doubleLargeArray.getDouble(j37 + 1));
                                }
                                DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, 0L, z);
                                DoubleDCT_3D.this.dctRows.forward(doubleLargeArray2, DoubleDCT_3D.this.rowsl, z);
                                for (long j38 = 0; j38 < DoubleDCT_3D.this.rowsl; j38++) {
                                    long j39 = (DoubleDCT_3D.this.rowStridel * j38) + j25;
                                    doubleLargeArray.setDouble(j39, doubleLargeArray2.getDouble(j38));
                                    doubleLargeArray.setDouble(j39 + 1, doubleLargeArray2.getDouble(DoubleDCT_3D.this.rowsl + j38));
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
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3da_subth(final int i, final double[][][] dArr, final boolean z) {
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
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.21
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    if (i == -1) {
                        int i7 = i6;
                        while (i7 < DoubleDCT_3D.this.slices) {
                            for (int i8 = 0; i8 < DoubleDCT_3D.this.rows; i8++) {
                                DoubleDCT_3D.this.dctColumns.forward(dArr[i7][i8], z);
                            }
                            if (DoubleDCT_3D.this.columns > 2) {
                                for (int i9 = 0; i9 < DoubleDCT_3D.this.columns; i9 += 4) {
                                    for (int i10 = 0; i10 < DoubleDCT_3D.this.rows; i10++) {
                                        int i11 = DoubleDCT_3D.this.rows + i10;
                                        double[] dArr3 = dArr[i7][i10];
                                        dArr2[i10] = dArr3[i9];
                                        dArr2[i11] = dArr3[i9 + 1];
                                        dArr2[DoubleDCT_3D.this.rows + i11] = dArr[i7][i10][i9 + 2];
                                        dArr2[i11 + (DoubleDCT_3D.this.rows * 2)] = dArr[i7][i10][i9 + 3];
                                    }
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, 0, z);
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows, z);
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows * 2, z);
                                    DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows * 3, z);
                                    for (int i12 = 0; i12 < DoubleDCT_3D.this.rows; i12++) {
                                        int i13 = DoubleDCT_3D.this.rows + i12;
                                        double[] dArr4 = dArr[i7][i12];
                                        dArr4[i9] = dArr2[i12];
                                        dArr4[i9 + 1] = dArr2[i13];
                                        dArr4[i9 + 2] = dArr2[DoubleDCT_3D.this.rows + i13];
                                        dArr[i7][i12][i9 + 3] = dArr2[i13 + (DoubleDCT_3D.this.rows * 2)];
                                    }
                                }
                            } else if (DoubleDCT_3D.this.columns == 2) {
                                for (int i14 = 0; i14 < DoubleDCT_3D.this.rows; i14++) {
                                    dArr2[i14] = dArr[i7][i14][0];
                                    dArr2[DoubleDCT_3D.this.rows + i14] = dArr[i7][i14][1];
                                }
                                DoubleDCT_3D.this.dctRows.forward(dArr2, 0, z);
                                DoubleDCT_3D.this.dctRows.forward(dArr2, DoubleDCT_3D.this.rows, z);
                                for (int i15 = 0; i15 < DoubleDCT_3D.this.rows; i15++) {
                                    double[] dArr5 = dArr[i7][i15];
                                    dArr5[0] = dArr2[i15];
                                    dArr5[1] = dArr2[DoubleDCT_3D.this.rows + i15];
                                }
                            }
                            i7 += i2;
                        }
                        return;
                    }
                    int i16 = i6;
                    while (i16 < DoubleDCT_3D.this.slices) {
                        for (int i17 = 0; i17 < DoubleDCT_3D.this.rows; i17++) {
                            DoubleDCT_3D.this.dctColumns.inverse(dArr[i16][i17], z);
                        }
                        if (DoubleDCT_3D.this.columns > 2) {
                            for (int i18 = 0; i18 < DoubleDCT_3D.this.columns; i18 += 4) {
                                for (int i19 = 0; i19 < DoubleDCT_3D.this.rows; i19++) {
                                    int i20 = DoubleDCT_3D.this.rows + i19;
                                    double[] dArr6 = dArr[i16][i19];
                                    dArr2[i19] = dArr6[i18];
                                    dArr2[i20] = dArr6[i18 + 1];
                                    dArr2[DoubleDCT_3D.this.rows + i20] = dArr[i16][i19][i18 + 2];
                                    dArr2[i20 + (DoubleDCT_3D.this.rows * 2)] = dArr[i16][i19][i18 + 3];
                                }
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, 0, z);
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows, z);
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows * 2, z);
                                DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows * 3, z);
                                for (int i21 = 0; i21 < DoubleDCT_3D.this.rows; i21++) {
                                    int i22 = DoubleDCT_3D.this.rows + i21;
                                    double[] dArr7 = dArr[i16][i21];
                                    dArr7[i18] = dArr2[i21];
                                    dArr7[i18 + 1] = dArr2[i22];
                                    dArr7[i18 + 2] = dArr2[DoubleDCT_3D.this.rows + i22];
                                    dArr[i16][i21][i18 + 3] = dArr2[i22 + (DoubleDCT_3D.this.rows * 2)];
                                }
                            }
                        } else if (DoubleDCT_3D.this.columns == 2) {
                            for (int i23 = 0; i23 < DoubleDCT_3D.this.rows; i23++) {
                                dArr2[i23] = dArr[i16][i23][0];
                                dArr2[DoubleDCT_3D.this.rows + i23] = dArr[i16][i23][1];
                            }
                            DoubleDCT_3D.this.dctRows.inverse(dArr2, 0, z);
                            DoubleDCT_3D.this.dctRows.inverse(dArr2, DoubleDCT_3D.this.rows, z);
                            for (int i24 = 0; i24 < DoubleDCT_3D.this.rows; i24++) {
                                double[] dArr8 = dArr[i16][i24];
                                dArr8[0] = dArr2[i24];
                                dArr8[1] = dArr2[DoubleDCT_3D.this.rows + i24];
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
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3db_subth(final int i, final double[] dArr, final boolean z) {
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
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.22
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    if (i == -1) {
                        if (DoubleDCT_3D.this.columns > 2) {
                            int i7 = i6;
                            while (i7 < DoubleDCT_3D.this.rows) {
                                int i8 = DoubleDCT_3D.this.rowStride * i7;
                                for (int i9 = 0; i9 < DoubleDCT_3D.this.columns; i9 += 4) {
                                    for (int i10 = 0; i10 < DoubleDCT_3D.this.slices; i10++) {
                                        int i11 = (DoubleDCT_3D.this.sliceStride * i10) + i8 + i9;
                                        int i12 = DoubleDCT_3D.this.slices + i10;
                                        double[] dArr3 = dArr;
                                        dArr2[i10] = dArr3[i11];
                                        dArr2[i12] = dArr3[i11 + 1];
                                        dArr2[DoubleDCT_3D.this.slices + i12] = dArr[i11 + 2];
                                        dArr2[i12 + (DoubleDCT_3D.this.slices * 2)] = dArr[i11 + 3];
                                    }
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, 0, z);
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices, z);
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices * 2, z);
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices * 3, z);
                                    for (int i13 = 0; i13 < DoubleDCT_3D.this.slices; i13++) {
                                        int i14 = (DoubleDCT_3D.this.sliceStride * i13) + i8 + i9;
                                        int i15 = DoubleDCT_3D.this.slices + i13;
                                        double[] dArr4 = dArr;
                                        dArr4[i14] = dArr2[i13];
                                        dArr4[i14 + 1] = dArr2[i15];
                                        dArr4[i14 + 2] = dArr2[DoubleDCT_3D.this.slices + i15];
                                        dArr[i14 + 3] = dArr2[i15 + (DoubleDCT_3D.this.slices * 2)];
                                    }
                                }
                                i7 += i2;
                            }
                            return;
                        }
                        if (DoubleDCT_3D.this.columns == 2) {
                            int i16 = i6;
                            while (i16 < DoubleDCT_3D.this.rows) {
                                int i17 = DoubleDCT_3D.this.rowStride * i16;
                                for (int i18 = 0; i18 < DoubleDCT_3D.this.slices; i18++) {
                                    int i19 = (DoubleDCT_3D.this.sliceStride * i18) + i17;
                                    dArr2[i18] = dArr[i19];
                                    dArr2[DoubleDCT_3D.this.slices + i18] = dArr[i19 + 1];
                                }
                                DoubleDCT_3D.this.dctSlices.forward(dArr2, 0, z);
                                DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices, z);
                                for (int i20 = 0; i20 < DoubleDCT_3D.this.slices; i20++) {
                                    int i21 = (DoubleDCT_3D.this.sliceStride * i20) + i17;
                                    double[] dArr5 = dArr;
                                    dArr5[i21] = dArr2[i20];
                                    dArr5[i21 + 1] = dArr2[DoubleDCT_3D.this.slices + i20];
                                }
                                i16 += i2;
                            }
                            return;
                        }
                        return;
                    }
                    if (DoubleDCT_3D.this.columns > 2) {
                        int i22 = i6;
                        while (i22 < DoubleDCT_3D.this.rows) {
                            int i23 = DoubleDCT_3D.this.rowStride * i22;
                            for (int i24 = 0; i24 < DoubleDCT_3D.this.columns; i24 += 4) {
                                for (int i25 = 0; i25 < DoubleDCT_3D.this.slices; i25++) {
                                    int i26 = (DoubleDCT_3D.this.sliceStride * i25) + i23 + i24;
                                    int i27 = DoubleDCT_3D.this.slices + i25;
                                    double[] dArr6 = dArr;
                                    dArr2[i25] = dArr6[i26];
                                    dArr2[i27] = dArr6[i26 + 1];
                                    dArr2[DoubleDCT_3D.this.slices + i27] = dArr[i26 + 2];
                                    dArr2[i27 + (DoubleDCT_3D.this.slices * 2)] = dArr[i26 + 3];
                                }
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, 0, z);
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices, z);
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices * 2, z);
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices * 3, z);
                                for (int i28 = 0; i28 < DoubleDCT_3D.this.slices; i28++) {
                                    int i29 = (DoubleDCT_3D.this.sliceStride * i28) + i23 + i24;
                                    int i30 = DoubleDCT_3D.this.slices + i28;
                                    double[] dArr7 = dArr;
                                    dArr7[i29] = dArr2[i28];
                                    dArr7[i29 + 1] = dArr2[i30];
                                    dArr7[i29 + 2] = dArr2[DoubleDCT_3D.this.slices + i30];
                                    dArr[i29 + 3] = dArr2[i30 + (DoubleDCT_3D.this.slices * 2)];
                                }
                            }
                            i22 += i2;
                        }
                        return;
                    }
                    if (DoubleDCT_3D.this.columns == 2) {
                        int i31 = i6;
                        while (i31 < DoubleDCT_3D.this.rows) {
                            int i32 = DoubleDCT_3D.this.rowStride * i31;
                            for (int i33 = 0; i33 < DoubleDCT_3D.this.slices; i33++) {
                                int i34 = (DoubleDCT_3D.this.sliceStride * i33) + i32;
                                dArr2[i33] = dArr[i34];
                                dArr2[DoubleDCT_3D.this.slices + i33] = dArr[i34 + 1];
                            }
                            DoubleDCT_3D.this.dctSlices.inverse(dArr2, 0, z);
                            DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices, z);
                            for (int i35 = 0; i35 < DoubleDCT_3D.this.slices; i35++) {
                                int i36 = (DoubleDCT_3D.this.sliceStride * i35) + i32;
                                double[] dArr8 = dArr;
                                dArr8[i36] = dArr2[i35];
                                dArr8[i36 + 1] = dArr2[DoubleDCT_3D.this.slices + i35];
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
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3db_subth(final int i, final DoubleLargeArray doubleLargeArray, final boolean z) {
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
            futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.23
                @Override // java.lang.Runnable
                public void run() {
                    DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j2);
                    long j4 = 1;
                    if (i == -1) {
                        if (DoubleDCT_3D.this.columnsl <= 2) {
                            if (DoubleDCT_3D.this.columnsl == 2) {
                                long j5 = j3;
                                while (j5 < DoubleDCT_3D.this.rowsl) {
                                    long j6 = DoubleDCT_3D.this.rowStridel * j5;
                                    for (long j7 = 0; j7 < DoubleDCT_3D.this.slicesl; j7++) {
                                        long j8 = (DoubleDCT_3D.this.sliceStridel * j7) + j6;
                                        doubleLargeArray2.setDouble(j7, doubleLargeArray.getDouble(j8));
                                        doubleLargeArray2.setDouble(DoubleDCT_3D.this.slicesl + j7, doubleLargeArray.getDouble(j8 + 1));
                                    }
                                    DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, 0L, z);
                                    DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, DoubleDCT_3D.this.slicesl, z);
                                    for (long j9 = 0; j9 < DoubleDCT_3D.this.slicesl; j9++) {
                                        long j10 = (DoubleDCT_3D.this.sliceStridel * j9) + j6;
                                        doubleLargeArray.setDouble(j10, doubleLargeArray2.getDouble(j9));
                                        doubleLargeArray.setDouble(j10 + 1, doubleLargeArray2.getDouble(DoubleDCT_3D.this.slicesl + j9));
                                    }
                                    j5 += i2;
                                }
                                return;
                            }
                            return;
                        }
                        long j11 = j3;
                        while (j11 < DoubleDCT_3D.this.rowsl) {
                            long j12 = DoubleDCT_3D.this.rowStridel * j11;
                            long j13 = 0;
                            while (j13 < DoubleDCT_3D.this.columnsl) {
                                long j14 = 0;
                                while (j14 < DoubleDCT_3D.this.slicesl) {
                                    long j15 = (DoubleDCT_3D.this.sliceStridel * j14) + j12 + j13;
                                    long j16 = DoubleDCT_3D.this.slicesl + j14;
                                    doubleLargeArray2.setDouble(j14, doubleLargeArray.getDouble(j15));
                                    doubleLargeArray2.setDouble(j16, doubleLargeArray.getDouble(j15 + j4));
                                    doubleLargeArray2.setDouble(DoubleDCT_3D.this.slicesl + j16, doubleLargeArray.getDouble(j15 + 2));
                                    doubleLargeArray2.setDouble(j16 + (DoubleDCT_3D.this.slicesl * 2), doubleLargeArray.getDouble(j15 + 3));
                                    j14++;
                                    j4 = 1;
                                    j11 = j11;
                                }
                                long j17 = j11;
                                DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, 0L, z);
                                DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, DoubleDCT_3D.this.slicesl, z);
                                DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, DoubleDCT_3D.this.slicesl * 2, z);
                                DoubleDCT_3D.this.dctSlices.forward(doubleLargeArray2, DoubleDCT_3D.this.slicesl * 3, z);
                                for (long j18 = 0; j18 < DoubleDCT_3D.this.slicesl; j18++) {
                                    long j19 = (DoubleDCT_3D.this.sliceStridel * j18) + j12 + j13;
                                    long j20 = DoubleDCT_3D.this.slicesl + j18;
                                    doubleLargeArray.setDouble(j19, doubleLargeArray2.getDouble(j18));
                                    doubleLargeArray.setDouble(j19 + 1, doubleLargeArray2.getDouble(j20));
                                    doubleLargeArray.setDouble(j19 + 2, doubleLargeArray2.getDouble(j20 + DoubleDCT_3D.this.slicesl));
                                    doubleLargeArray.setDouble(j19 + 3, doubleLargeArray2.getDouble(j20 + (DoubleDCT_3D.this.slicesl * 2)));
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
                    if (DoubleDCT_3D.this.columnsl <= 2) {
                        if (DoubleDCT_3D.this.columnsl == 2) {
                            long j21 = j3;
                            while (j21 < DoubleDCT_3D.this.rowsl) {
                                long j22 = DoubleDCT_3D.this.rowStridel * j21;
                                for (long j23 = 0; j23 < DoubleDCT_3D.this.slicesl; j23++) {
                                    long j24 = (DoubleDCT_3D.this.sliceStridel * j23) + j22;
                                    doubleLargeArray2.setDouble(j23, doubleLargeArray.getDouble(j24));
                                    doubleLargeArray2.setDouble(DoubleDCT_3D.this.slicesl + j23, doubleLargeArray.getDouble(j24 + 1));
                                }
                                DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, 0L, z);
                                DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, DoubleDCT_3D.this.slicesl, z);
                                for (long j25 = 0; j25 < DoubleDCT_3D.this.slicesl; j25++) {
                                    long j26 = (DoubleDCT_3D.this.sliceStridel * j25) + j22;
                                    doubleLargeArray.setDouble(j26, doubleLargeArray2.getDouble(j25));
                                    doubleLargeArray.setDouble(j26 + 1, doubleLargeArray2.getDouble(DoubleDCT_3D.this.slicesl + j25));
                                }
                                j21 += i2;
                            }
                            return;
                        }
                        return;
                    }
                    long j27 = j3;
                    while (j27 < DoubleDCT_3D.this.rowsl) {
                        long j28 = DoubleDCT_3D.this.rowStridel * j27;
                        long j29 = 0;
                        while (j29 < DoubleDCT_3D.this.columnsl) {
                            long j30 = 0;
                            while (j30 < DoubleDCT_3D.this.slicesl) {
                                long j31 = (DoubleDCT_3D.this.sliceStridel * j30) + j28 + j29;
                                long j32 = DoubleDCT_3D.this.slicesl + j30;
                                doubleLargeArray2.setDouble(j30, doubleLargeArray.getDouble(j31));
                                doubleLargeArray2.setDouble(j32, doubleLargeArray.getDouble(j31 + 1));
                                doubleLargeArray2.setDouble(DoubleDCT_3D.this.slicesl + j32, doubleLargeArray.getDouble(j31 + 2));
                                doubleLargeArray2.setDouble(j32 + (DoubleDCT_3D.this.slicesl * 2), doubleLargeArray.getDouble(j31 + 3));
                                j30++;
                                j27 = j27;
                                j29 = j29;
                            }
                            long j33 = j27;
                            long j34 = j29;
                            DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, 0L, z);
                            DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, DoubleDCT_3D.this.slicesl, z);
                            DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, DoubleDCT_3D.this.slicesl * 2, z);
                            DoubleDCT_3D.this.dctSlices.inverse(doubleLargeArray2, DoubleDCT_3D.this.slicesl * 3, z);
                            for (long j35 = 0; j35 < DoubleDCT_3D.this.slicesl; j35++) {
                                long j36 = (DoubleDCT_3D.this.sliceStridel * j35) + j28 + j34;
                                long j37 = DoubleDCT_3D.this.slicesl + j35;
                                doubleLargeArray.setDouble(j36, doubleLargeArray2.getDouble(j35));
                                doubleLargeArray.setDouble(j36 + 1, doubleLargeArray2.getDouble(j37));
                                doubleLargeArray.setDouble(j36 + 2, doubleLargeArray2.getDouble(j37 + DoubleDCT_3D.this.slicesl));
                                doubleLargeArray.setDouble(j36 + 3, doubleLargeArray2.getDouble(j37 + (DoubleDCT_3D.this.slicesl * 2)));
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
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt3db_subth(final int i, final double[][][] dArr, final boolean z) {
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
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.DoubleDCT_3D.24
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    if (i == -1) {
                        if (DoubleDCT_3D.this.columns > 2) {
                            int i7 = i6;
                            while (i7 < DoubleDCT_3D.this.rows) {
                                for (int i8 = 0; i8 < DoubleDCT_3D.this.columns; i8 += 4) {
                                    for (int i9 = 0; i9 < DoubleDCT_3D.this.slices; i9++) {
                                        int i10 = DoubleDCT_3D.this.slices + i9;
                                        double[] dArr3 = dArr[i9][i7];
                                        dArr2[i9] = dArr3[i8];
                                        dArr2[i10] = dArr3[i8 + 1];
                                        dArr2[DoubleDCT_3D.this.slices + i10] = dArr[i9][i7][i8 + 2];
                                        dArr2[i10 + (DoubleDCT_3D.this.slices * 2)] = dArr[i9][i7][i8 + 3];
                                    }
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, 0, z);
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices, z);
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices * 2, z);
                                    DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices * 3, z);
                                    for (int i11 = 0; i11 < DoubleDCT_3D.this.slices; i11++) {
                                        int i12 = DoubleDCT_3D.this.slices + i11;
                                        double[] dArr4 = dArr[i11][i7];
                                        dArr4[i8] = dArr2[i11];
                                        dArr4[i8 + 1] = dArr2[i12];
                                        dArr4[i8 + 2] = dArr2[DoubleDCT_3D.this.slices + i12];
                                        dArr[i11][i7][i8 + 3] = dArr2[i12 + (DoubleDCT_3D.this.slices * 2)];
                                    }
                                }
                                i7 += i2;
                            }
                            return;
                        }
                        if (DoubleDCT_3D.this.columns == 2) {
                            int i13 = i6;
                            while (i13 < DoubleDCT_3D.this.rows) {
                                for (int i14 = 0; i14 < DoubleDCT_3D.this.slices; i14++) {
                                    dArr2[i14] = dArr[i14][i13][0];
                                    dArr2[DoubleDCT_3D.this.slices + i14] = dArr[i14][i13][1];
                                }
                                DoubleDCT_3D.this.dctSlices.forward(dArr2, 0, z);
                                DoubleDCT_3D.this.dctSlices.forward(dArr2, DoubleDCT_3D.this.slices, z);
                                for (int i15 = 0; i15 < DoubleDCT_3D.this.slices; i15++) {
                                    double[] dArr5 = dArr[i15][i13];
                                    dArr5[0] = dArr2[i15];
                                    dArr5[1] = dArr2[DoubleDCT_3D.this.slices + i15];
                                }
                                i13 += i2;
                            }
                            return;
                        }
                        return;
                    }
                    if (DoubleDCT_3D.this.columns > 2) {
                        int i16 = i6;
                        while (i16 < DoubleDCT_3D.this.rows) {
                            for (int i17 = 0; i17 < DoubleDCT_3D.this.columns; i17 += 4) {
                                for (int i18 = 0; i18 < DoubleDCT_3D.this.slices; i18++) {
                                    int i19 = DoubleDCT_3D.this.slices + i18;
                                    double[] dArr6 = dArr[i18][i16];
                                    dArr2[i18] = dArr6[i17];
                                    dArr2[i19] = dArr6[i17 + 1];
                                    dArr2[DoubleDCT_3D.this.slices + i19] = dArr[i18][i16][i17 + 2];
                                    dArr2[i19 + (DoubleDCT_3D.this.slices * 2)] = dArr[i18][i16][i17 + 3];
                                }
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, 0, z);
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices, z);
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices * 2, z);
                                DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices * 3, z);
                                for (int i20 = 0; i20 < DoubleDCT_3D.this.slices; i20++) {
                                    int i21 = DoubleDCT_3D.this.slices + i20;
                                    double[] dArr7 = dArr[i20][i16];
                                    dArr7[i17] = dArr2[i20];
                                    dArr7[i17 + 1] = dArr2[i21];
                                    dArr7[i17 + 2] = dArr2[DoubleDCT_3D.this.slices + i21];
                                    dArr[i20][i16][i17 + 3] = dArr2[i21 + (DoubleDCT_3D.this.slices * 2)];
                                }
                            }
                            i16 += i2;
                        }
                        return;
                    }
                    if (DoubleDCT_3D.this.columns == 2) {
                        int i22 = i6;
                        while (i22 < DoubleDCT_3D.this.rows) {
                            for (int i23 = 0; i23 < DoubleDCT_3D.this.slices; i23++) {
                                dArr2[i23] = dArr[i23][i22][0];
                                dArr2[DoubleDCT_3D.this.slices + i23] = dArr[i23][i22][1];
                            }
                            DoubleDCT_3D.this.dctSlices.inverse(dArr2, 0, z);
                            DoubleDCT_3D.this.dctSlices.inverse(dArr2, DoubleDCT_3D.this.slices, z);
                            for (int i24 = 0; i24 < DoubleDCT_3D.this.slices; i24++) {
                                double[] dArr8 = dArr[i24][i22];
                                dArr8[0] = dArr2[i24];
                                dArr8[1] = dArr2[DoubleDCT_3D.this.slices + i24];
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
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDCT_3D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }
}
