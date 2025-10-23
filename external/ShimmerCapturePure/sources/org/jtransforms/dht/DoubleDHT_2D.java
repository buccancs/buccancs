package org.jtransforms.dht;

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
public class DoubleDHT_2D {
    private int columns;
    private long columnsl;
    private DoubleDHT_1D dhtColumns;
    private DoubleDHT_1D dhtRows;
    private boolean isPowerOfTwo;
    private int rows;
    private long rowsl;
    private boolean useThreads;

    public DoubleDHT_2D(long j, long j2) {
        this.isPowerOfTwo = false;
        this.useThreads = false;
        if (j <= 1 || j2 <= 1) {
            throw new IllegalArgumentException("rows and columns must be greater than 1");
        }
        this.rows = (int) j;
        this.columns = (int) j2;
        this.rowsl = j;
        this.columnsl = j2;
        long j3 = j * j2;
        if (j3 >= CommonUtils.getThreadsBeginN_2D()) {
            this.useThreads = true;
        }
        if (CommonUtils.isPowerOf2(j) && CommonUtils.isPowerOf2(j2)) {
            this.isPowerOfTwo = true;
        }
        CommonUtils.setUseLargeArrays(j3 > ((long) LargeArray.getMaxSizeOf32bitArray()));
        DoubleDHT_1D doubleDHT_1D = new DoubleDHT_1D(j);
        this.dhtRows = doubleDHT_1D;
        if (j == j2) {
            this.dhtColumns = doubleDHT_1D;
        } else {
            this.dhtColumns = new DoubleDHT_1D(j2);
        }
    }

    public void forward(final double[] dArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt2d_subth(-1, dArr, true);
                ddxt2d0_subth(-1, dArr, true);
            } else {
                ddxt2d_sub(-1, dArr, true);
                while (i3 < this.rows) {
                    this.dhtColumns.forward(dArr, this.columns * i3);
                    i3++;
                }
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.rows : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr, DoubleDHT_2D.this.columns * i8);
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i9 = i3 * i8;
                final int i10 = i3 == numberOfThreads + (-1) ? this.columns : i9 + i8;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDHT_2D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < DoubleDHT_2D.this.rows; i12++) {
                                dArr2[i12] = dArr[(DoubleDHT_2D.this.columns * i12) + i11];
                            }
                            DoubleDHT_2D.this.dhtRows.forward(dArr2);
                            for (int i13 = 0; i13 < DoubleDHT_2D.this.rows; i13++) {
                                dArr[(DoubleDHT_2D.this.columns * i13) + i11] = dArr2[i13];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
        } else {
            int i11 = 0;
            while (true) {
                i = this.rows;
                if (i11 >= i) {
                    break;
                }
                this.dhtColumns.forward(dArr, this.columns * i11);
                i11++;
            }
            double[] dArr2 = new double[i];
            for (int i12 = 0; i12 < this.columns; i12++) {
                for (int i13 = 0; i13 < this.rows; i13++) {
                    dArr2[i13] = dArr[(this.columns * i13) + i12];
                }
                this.dhtRows.forward(dArr2);
                for (int i14 = 0; i14 < this.rows; i14++) {
                    dArr[(this.columns * i14) + i12] = dArr2[i14];
                }
            }
        }
        yTransform(dArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void forward(final pl.edu.icm.jlargearrays.DoubleLargeArray r20) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.DoubleDHT_2D.forward(pl.edu.icm.jlargearrays.DoubleLargeArray):void");
    }

    public void forward(final double[][] dArr) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt2d_subth(-1, dArr, true);
                ddxt2d0_subth(-1, dArr, true);
            } else {
                ddxt2d_sub(-1, dArr, true);
                while (i3 < this.rows) {
                    this.dhtColumns.forward(dArr[i3]);
                    i3++;
                }
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.rows : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.5
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr[i8]);
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i9 = i3 * i8;
                final int i10 = i3 == numberOfThreads + (-1) ? this.columns : i9 + i8;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.6
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDHT_2D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < DoubleDHT_2D.this.rows; i12++) {
                                dArr2[i12] = dArr[i12][i11];
                            }
                            DoubleDHT_2D.this.dhtRows.forward(dArr2);
                            for (int i13 = 0; i13 < DoubleDHT_2D.this.rows; i13++) {
                                dArr[i13][i11] = dArr2[i13];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
        } else {
            int i11 = 0;
            while (true) {
                i = this.rows;
                if (i11 >= i) {
                    break;
                }
                this.dhtColumns.forward(dArr[i11]);
                i11++;
            }
            double[] dArr2 = new double[i];
            for (int i12 = 0; i12 < this.columns; i12++) {
                for (int i13 = 0; i13 < this.rows; i13++) {
                    dArr2[i13] = dArr[i13][i12];
                }
                this.dhtRows.forward(dArr2);
                for (int i14 = 0; i14 < this.rows; i14++) {
                    dArr[i14][i12] = dArr2[i14];
                }
            }
        }
        yTransform(dArr);
    }

    public void inverse(final double[] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt2d_subth(1, dArr, z);
                ddxt2d0_subth(1, dArr, z);
            } else {
                ddxt2d_sub(1, dArr, z);
                while (i3 < this.rows) {
                    this.dhtColumns.inverse(dArr, this.columns * i3, z);
                    i3++;
                }
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.rows : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            DoubleDHT_2D.this.dhtColumns.inverse(dArr, DoubleDHT_2D.this.columns * i8, z);
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i9 = i3 * i8;
                final int i10 = i3 == numberOfThreads + (-1) ? this.columns : i9 + i8;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDHT_2D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < DoubleDHT_2D.this.rows; i12++) {
                                dArr2[i12] = dArr[(DoubleDHT_2D.this.columns * i12) + i11];
                            }
                            DoubleDHT_2D.this.dhtRows.inverse(dArr2, z);
                            for (int i13 = 0; i13 < DoubleDHT_2D.this.rows; i13++) {
                                dArr[(DoubleDHT_2D.this.columns * i13) + i11] = dArr2[i13];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
        } else {
            int i11 = 0;
            while (true) {
                i = this.rows;
                if (i11 >= i) {
                    break;
                }
                this.dhtColumns.inverse(dArr, this.columns * i11, z);
                i11++;
            }
            double[] dArr2 = new double[i];
            for (int i12 = 0; i12 < this.columns; i12++) {
                for (int i13 = 0; i13 < this.rows; i13++) {
                    dArr2[i13] = dArr[(this.columns * i13) + i12];
                }
                this.dhtRows.inverse(dArr2, z);
                for (int i14 = 0; i14 < this.rows; i14++) {
                    dArr[(this.columns * i14) + i12] = dArr2[i14];
                }
            }
        }
        yTransform(dArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void inverse(final pl.edu.icm.jlargearrays.DoubleLargeArray r22, final boolean r23) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.DoubleDHT_2D.inverse(pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    public void inverse(final double[][] dArr, final boolean z) {
        int i;
        int i2;
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (this.isPowerOfTwo) {
            if (numberOfThreads > 1 && this.useThreads) {
                ddxt2d_subth(1, dArr, z);
                ddxt2d0_subth(1, dArr, z);
            } else {
                ddxt2d_sub(1, dArr, z);
                while (i3 < this.rows) {
                    this.dhtColumns.inverse(dArr[i3], z);
                    i3++;
                }
            }
            yTransform(dArr);
            return;
        }
        if (numberOfThreads > 1 && this.useThreads && (i2 = this.rows) >= numberOfThreads && this.columns >= numberOfThreads) {
            Future[] futureArr = new Future[numberOfThreads];
            int i4 = i2 / numberOfThreads;
            int i5 = 0;
            while (i5 < numberOfThreads) {
                final int i6 = i5 * i4;
                final int i7 = i5 == numberOfThreads + (-1) ? this.rows : i6 + i4;
                futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.11
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i8 = i6; i8 < i7; i8++) {
                            DoubleDHT_2D.this.dhtColumns.inverse(dArr[i8], z);
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            int i8 = this.columns / numberOfThreads;
            while (i3 < numberOfThreads) {
                final int i9 = i3 * i8;
                final int i10 = i3 == numberOfThreads + (-1) ? this.columns : i9 + i8;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.12
                    @Override // java.lang.Runnable
                    public void run() {
                        double[] dArr2 = new double[DoubleDHT_2D.this.rows];
                        for (int i11 = i9; i11 < i10; i11++) {
                            for (int i12 = 0; i12 < DoubleDHT_2D.this.rows; i12++) {
                                dArr2[i12] = dArr[i12][i11];
                            }
                            DoubleDHT_2D.this.dhtRows.inverse(dArr2, z);
                            for (int i13 = 0; i13 < DoubleDHT_2D.this.rows; i13++) {
                                dArr[i13][i11] = dArr2[i13];
                            }
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
        } else {
            int i11 = 0;
            while (true) {
                i = this.rows;
                if (i11 >= i) {
                    break;
                }
                this.dhtColumns.inverse(dArr[i11], z);
                i11++;
            }
            double[] dArr2 = new double[i];
            for (int i12 = 0; i12 < this.columns; i12++) {
                for (int i13 = 0; i13 < this.rows; i13++) {
                    dArr2[i13] = dArr[i13][i12];
                }
                this.dhtRows.inverse(dArr2, z);
                for (int i14 = 0; i14 < this.rows; i14++) {
                    dArr[i14][i12] = dArr2[i14];
                }
            }
        }
        yTransform(dArr);
    }

    private void ddxt2d_subth(final int i, final double[] dArr, final boolean z) {
        final int iMin = FastMath.min(this.columns, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        final int i4 = i2;
        Future[] futureArr = new Future[iMin];
        for (int i5 = 0; i5 < iMin; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.13
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    if (DoubleDHT_2D.this.columns <= 2) {
                        if (DoubleDHT_2D.this.columns == 2) {
                            for (int i7 = 0; i7 < DoubleDHT_2D.this.rows; i7++) {
                                int i8 = (DoubleDHT_2D.this.columns * i7) + (i6 * 2);
                                dArr2[i7] = dArr[i8];
                                dArr2[DoubleDHT_2D.this.rows + i7] = dArr[i8 + 1];
                            }
                            if (i == -1) {
                                DoubleDHT_2D.this.dhtRows.forward(dArr2, 0);
                                DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows);
                            } else {
                                DoubleDHT_2D.this.dhtRows.inverse(dArr2, 0, z);
                                DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows, z);
                            }
                            for (int i9 = 0; i9 < DoubleDHT_2D.this.rows; i9++) {
                                int i10 = (DoubleDHT_2D.this.columns * i9) + (i6 * 2);
                                double[] dArr3 = dArr;
                                dArr3[i10] = dArr2[i9];
                                dArr3[i10 + 1] = dArr2[DoubleDHT_2D.this.rows + i9];
                            }
                            return;
                        }
                        return;
                    }
                    if (i == -1) {
                        int i11 = i6 * 4;
                        while (i11 < DoubleDHT_2D.this.columns) {
                            for (int i12 = 0; i12 < DoubleDHT_2D.this.rows; i12++) {
                                int i13 = (DoubleDHT_2D.this.columns * i12) + i11;
                                int i14 = DoubleDHT_2D.this.rows + i12;
                                double[] dArr4 = dArr;
                                dArr2[i12] = dArr4[i13];
                                dArr2[i14] = dArr4[i13 + 1];
                                dArr2[DoubleDHT_2D.this.rows + i14] = dArr[i13 + 2];
                                dArr2[i14 + (DoubleDHT_2D.this.rows * 2)] = dArr[i13 + 3];
                            }
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, 0);
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows);
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows * 2);
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows * 3);
                            for (int i15 = 0; i15 < DoubleDHT_2D.this.rows; i15++) {
                                int i16 = (DoubleDHT_2D.this.columns * i15) + i11;
                                int i17 = DoubleDHT_2D.this.rows + i15;
                                double[] dArr5 = dArr;
                                dArr5[i16] = dArr2[i15];
                                dArr5[i16 + 1] = dArr2[i17];
                                dArr5[i16 + 2] = dArr2[DoubleDHT_2D.this.rows + i17];
                                dArr[i16 + 3] = dArr2[i17 + (DoubleDHT_2D.this.rows * 2)];
                            }
                            i11 += iMin * 4;
                        }
                        return;
                    }
                    int i18 = i6 * 4;
                    while (i18 < DoubleDHT_2D.this.columns) {
                        for (int i19 = 0; i19 < DoubleDHT_2D.this.rows; i19++) {
                            int i20 = (DoubleDHT_2D.this.columns * i19) + i18;
                            int i21 = DoubleDHT_2D.this.rows + i19;
                            double[] dArr6 = dArr;
                            dArr2[i19] = dArr6[i20];
                            dArr2[i21] = dArr6[i20 + 1];
                            dArr2[DoubleDHT_2D.this.rows + i21] = dArr[i20 + 2];
                            dArr2[i21 + (DoubleDHT_2D.this.rows * 2)] = dArr[i20 + 3];
                        }
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, 0, z);
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows, z);
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows * 2, z);
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows * 3, z);
                        for (int i22 = 0; i22 < DoubleDHT_2D.this.rows; i22++) {
                            int i23 = (DoubleDHT_2D.this.columns * i22) + i18;
                            int i24 = DoubleDHT_2D.this.rows + i22;
                            double[] dArr7 = dArr;
                            dArr7[i23] = dArr2[i22];
                            dArr7[i23 + 1] = dArr2[i24];
                            dArr7[i23 + 2] = dArr2[DoubleDHT_2D.this.rows + i24];
                            dArr[i23 + 3] = dArr2[i24 + (DoubleDHT_2D.this.rows * 2)];
                        }
                        i18 += iMin * 4;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e A[LOOP:0: B:10:0x002c->B:11:0x002e, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void ddxt2d_subth(final int r19, final pl.edu.icm.jlargearrays.DoubleLargeArray r20, final boolean r21) {
        /*
            r18 = this;
            r11 = r18
            java.lang.Class<org.jtransforms.dht.DoubleDHT_2D> r12 = org.jtransforms.dht.DoubleDHT_2D.class
            long r0 = r11.columnsl
            int r2 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r2 = (long) r2
            long r0 = org.apache.commons.math3.util.FastMath.min(r0, r2)
            int r13 = (int) r0
            r0 = 4
            long r2 = r11.rowsl
            long r2 = r2 * r0
            long r0 = r11.columnsl
            r4 = 2
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 != 0) goto L21
            r0 = 1
        L1f:
            long r2 = r2 >> r0
            goto L27
        L21:
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 >= 0) goto L27
            r0 = 2
            goto L1f
        L27:
            r14 = r2
            java.util.concurrent.Future[] r0 = new java.util.concurrent.Future[r13]
            r1 = 0
            r10 = 0
        L2c:
            if (r10 >= r13) goto L4b
            long r6 = (long) r10
            org.jtransforms.dht.DoubleDHT_2D$14 r16 = new org.jtransforms.dht.DoubleDHT_2D$14
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
            goto L2c
        L4b:
            r1 = 0
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r0)     // Catch: java.util.concurrent.ExecutionException -> L50 java.lang.InterruptedException -> L60
            goto L6f
        L50:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
            goto L6f
        L60:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r12.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            r0.log(r3, r1, r2)
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.DoubleDHT_2D.ddxt2d_subth(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void ddxt2d_subth(final int i, final double[][] dArr, final boolean z) {
        final int iMin = FastMath.min(this.columns, ConcurrencyUtils.getNumberOfThreads());
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        final int i4 = i2;
        Future[] futureArr = new Future[iMin];
        for (int i5 = 0; i5 < iMin; i5++) {
            final int i6 = i5;
            futureArr[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.15
                @Override // java.lang.Runnable
                public void run() {
                    double[] dArr2 = new double[i4];
                    if (DoubleDHT_2D.this.columns <= 2) {
                        if (DoubleDHT_2D.this.columns == 2) {
                            for (int i7 = 0; i7 < DoubleDHT_2D.this.rows; i7++) {
                                dArr2[i7] = dArr[i7][i6 * 2];
                                dArr2[DoubleDHT_2D.this.rows + i7] = dArr[i7][(i6 * 2) + 1];
                            }
                            if (i == -1) {
                                DoubleDHT_2D.this.dhtRows.forward(dArr2, 0);
                                DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows);
                            } else {
                                DoubleDHT_2D.this.dhtRows.inverse(dArr2, 0, z);
                                DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows, z);
                            }
                            for (int i8 = 0; i8 < DoubleDHT_2D.this.rows; i8++) {
                                double[] dArr3 = dArr[i8];
                                int i9 = i6;
                                dArr3[i9 * 2] = dArr2[i8];
                                dArr3[(i9 * 2) + 1] = dArr2[DoubleDHT_2D.this.rows + i8];
                            }
                            return;
                        }
                        return;
                    }
                    if (i == -1) {
                        int i10 = i6 * 4;
                        while (i10 < DoubleDHT_2D.this.columns) {
                            for (int i11 = 0; i11 < DoubleDHT_2D.this.rows; i11++) {
                                int i12 = DoubleDHT_2D.this.rows + i11;
                                double[] dArr4 = dArr[i11];
                                dArr2[i11] = dArr4[i10];
                                dArr2[i12] = dArr4[i10 + 1];
                                dArr2[DoubleDHT_2D.this.rows + i12] = dArr[i11][i10 + 2];
                                dArr2[i12 + (DoubleDHT_2D.this.rows * 2)] = dArr[i11][i10 + 3];
                            }
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, 0);
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows);
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows * 2);
                            DoubleDHT_2D.this.dhtRows.forward(dArr2, DoubleDHT_2D.this.rows * 3);
                            for (int i13 = 0; i13 < DoubleDHT_2D.this.rows; i13++) {
                                int i14 = DoubleDHT_2D.this.rows + i13;
                                double[] dArr5 = dArr[i13];
                                dArr5[i10] = dArr2[i13];
                                dArr5[i10 + 1] = dArr2[i14];
                                dArr5[i10 + 2] = dArr2[DoubleDHT_2D.this.rows + i14];
                                dArr[i13][i10 + 3] = dArr2[i14 + (DoubleDHT_2D.this.rows * 2)];
                            }
                            i10 += iMin * 4;
                        }
                        return;
                    }
                    int i15 = i6 * 4;
                    while (i15 < DoubleDHT_2D.this.columns) {
                        for (int i16 = 0; i16 < DoubleDHT_2D.this.rows; i16++) {
                            int i17 = DoubleDHT_2D.this.rows + i16;
                            double[] dArr6 = dArr[i16];
                            dArr2[i16] = dArr6[i15];
                            dArr2[i17] = dArr6[i15 + 1];
                            dArr2[DoubleDHT_2D.this.rows + i17] = dArr[i16][i15 + 2];
                            dArr2[i17 + (DoubleDHT_2D.this.rows * 2)] = dArr[i16][i15 + 3];
                        }
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, 0, z);
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows, z);
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows * 2, z);
                        DoubleDHT_2D.this.dhtRows.inverse(dArr2, DoubleDHT_2D.this.rows * 3, z);
                        for (int i18 = 0; i18 < DoubleDHT_2D.this.rows; i18++) {
                            int i19 = DoubleDHT_2D.this.rows + i18;
                            double[] dArr7 = dArr[i18];
                            dArr7[i15] = dArr2[i18];
                            dArr7[i15 + 1] = dArr2[i19];
                            dArr7[i15 + 2] = dArr2[DoubleDHT_2D.this.rows + i19];
                            dArr[i18][i15 + 3] = dArr2[i19 + (DoubleDHT_2D.this.rows * 2)];
                        }
                        i15 += iMin * 4;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt2d0_subth(final int i, final double[] dArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        Future[] futureArr = new Future[numberOfThreads2];
        for (int i2 = 0; i2 < numberOfThreads2; i2++) {
            final int i3 = i2;
            final int i4 = numberOfThreads2;
            futureArr[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.16
                @Override // java.lang.Runnable
                public void run() {
                    if (i == -1) {
                        int i5 = i3;
                        while (i5 < DoubleDHT_2D.this.rows) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr, DoubleDHT_2D.this.columns * i5);
                            i5 += i4;
                        }
                        return;
                    }
                    int i6 = i3;
                    while (i6 < DoubleDHT_2D.this.rows) {
                        DoubleDHT_2D.this.dhtColumns.inverse(dArr, DoubleDHT_2D.this.columns * i6, z);
                        i6 += i4;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt2d0_subth(final int i, final DoubleLargeArray doubleLargeArray, final boolean z) {
        long numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        long numberOfThreads2 = this.rowsl;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        final int i2 = (int) numberOfThreads2;
        Future[] futureArr = new Future[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final long j = i3;
            futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.17
                @Override // java.lang.Runnable
                public void run() {
                    if (i == -1) {
                        long j2 = j;
                        while (j2 < DoubleDHT_2D.this.rowsl) {
                            DoubleDHT_2D.this.dhtColumns.forward(doubleLargeArray, DoubleDHT_2D.this.columnsl * j2);
                            j2 += i2;
                        }
                        return;
                    }
                    long j3 = j;
                    while (j3 < DoubleDHT_2D.this.rowsl) {
                        DoubleDHT_2D.this.dhtColumns.inverse(doubleLargeArray, DoubleDHT_2D.this.columnsl * j3, z);
                        j3 += i2;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt2d0_subth(final int i, final double[][] dArr, final boolean z) {
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int numberOfThreads2 = this.rows;
        if (numberOfThreads <= numberOfThreads2) {
            numberOfThreads2 = ConcurrencyUtils.getNumberOfThreads();
        }
        Future[] futureArr = new Future[numberOfThreads2];
        for (int i2 = 0; i2 < numberOfThreads2; i2++) {
            final int i3 = i2;
            final int i4 = numberOfThreads2;
            futureArr[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_2D.18
                @Override // java.lang.Runnable
                public void run() {
                    if (i == -1) {
                        int i5 = i3;
                        while (i5 < DoubleDHT_2D.this.rows) {
                            DoubleDHT_2D.this.dhtColumns.forward(dArr[i5]);
                            i5 += i4;
                        }
                        return;
                    }
                    int i6 = i3;
                    while (i6 < DoubleDHT_2D.this.rows) {
                        DoubleDHT_2D.this.dhtColumns.inverse(dArr[i6], z);
                        i6 += i4;
                    }
                }
            });
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(DoubleDHT_2D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    private void ddxt2d_sub(int i, double[] dArr, boolean z) {
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        int i4 = 0;
        if (i3 > 2) {
            if (i == -1) {
                for (int i5 = 0; i5 < this.columns; i5 += 4) {
                    int i6 = 0;
                    while (true) {
                        int i7 = this.rows;
                        if (i6 >= i7) {
                            break;
                        }
                        int i8 = (this.columns * i6) + i5;
                        int i9 = i7 + i6;
                        dArr2[i6] = dArr[i8];
                        dArr2[i9] = dArr[i8 + 1];
                        dArr2[i9 + i7] = dArr[i8 + 2];
                        dArr2[i9 + (i7 * 2)] = dArr[i8 + 3];
                        i6++;
                    }
                    this.dhtRows.forward(dArr2, 0);
                    this.dhtRows.forward(dArr2, this.rows);
                    this.dhtRows.forward(dArr2, this.rows * 2);
                    this.dhtRows.forward(dArr2, this.rows * 3);
                    int i10 = 0;
                    while (true) {
                        int i11 = this.rows;
                        if (i10 < i11) {
                            int i12 = (this.columns * i10) + i5;
                            int i13 = i11 + i10;
                            dArr[i12] = dArr2[i10];
                            dArr[i12 + 1] = dArr2[i13];
                            dArr[i12 + 2] = dArr2[i13 + i11];
                            dArr[i12 + 3] = dArr2[i13 + (i11 * 2)];
                            i10++;
                        }
                    }
                }
                return;
            }
            for (int i14 = 0; i14 < this.columns; i14 += 4) {
                int i15 = 0;
                while (true) {
                    int i16 = this.rows;
                    if (i15 >= i16) {
                        break;
                    }
                    int i17 = (this.columns * i15) + i14;
                    int i18 = i16 + i15;
                    dArr2[i15] = dArr[i17];
                    dArr2[i18] = dArr[i17 + 1];
                    dArr2[i18 + i16] = dArr[i17 + 2];
                    dArr2[i18 + (i16 * 2)] = dArr[i17 + 3];
                    i15++;
                }
                this.dhtRows.inverse(dArr2, 0, z);
                this.dhtRows.inverse(dArr2, this.rows, z);
                this.dhtRows.inverse(dArr2, this.rows * 2, z);
                this.dhtRows.inverse(dArr2, this.rows * 3, z);
                int i19 = 0;
                while (true) {
                    int i20 = this.rows;
                    if (i19 < i20) {
                        int i21 = (this.columns * i19) + i14;
                        int i22 = i20 + i19;
                        dArr[i21] = dArr2[i19];
                        dArr[i21 + 1] = dArr2[i22];
                        dArr[i21 + 2] = dArr2[i22 + i20];
                        dArr[i21 + 3] = dArr2[i22 + (i20 * 2)];
                        i19++;
                    }
                }
            }
            return;
        }
        if (i3 != 2) {
            return;
        }
        int i23 = 0;
        while (true) {
            int i24 = this.rows;
            if (i23 >= i24) {
                break;
            }
            int i25 = this.columns * i23;
            dArr2[i23] = dArr[i25];
            dArr2[i24 + i23] = dArr[i25 + 1];
            i23++;
        }
        if (i == -1) {
            this.dhtRows.forward(dArr2, 0);
            this.dhtRows.forward(dArr2, this.rows);
        } else {
            this.dhtRows.inverse(dArr2, 0, z);
            this.dhtRows.inverse(dArr2, this.rows, z);
        }
        while (true) {
            int i26 = this.rows;
            if (i4 >= i26) {
                return;
            }
            int i27 = this.columns * i4;
            dArr[i27] = dArr2[i4];
            dArr[i27 + 1] = dArr2[i26 + i4];
            i4++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void ddxt2d_sub(int r25, pl.edu.icm.jlargearrays.DoubleLargeArray r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.dht.DoubleDHT_2D.ddxt2d_sub(int, pl.edu.icm.jlargearrays.DoubleLargeArray, boolean):void");
    }

    private void ddxt2d_sub(int i, double[][] dArr, boolean z) {
        int i2 = this.rows * 4;
        int i3 = this.columns;
        if (i3 == 2) {
            i2 >>= 1;
        } else if (i3 < 2) {
            i2 >>= 2;
        }
        double[] dArr2 = new double[i2];
        if (i3 > 2) {
            if (i == -1) {
                for (int i4 = 0; i4 < this.columns; i4 += 4) {
                    int i5 = 0;
                    while (true) {
                        int i6 = this.rows;
                        if (i5 >= i6) {
                            break;
                        }
                        int i7 = i6 + i5;
                        double[] dArr3 = dArr[i5];
                        dArr2[i5] = dArr3[i4];
                        dArr2[i7] = dArr3[i4 + 1];
                        dArr2[i7 + i6] = dArr3[i4 + 2];
                        dArr2[i7 + (i6 * 2)] = dArr3[i4 + 3];
                        i5++;
                    }
                    this.dhtRows.forward(dArr2, 0);
                    this.dhtRows.forward(dArr2, this.rows);
                    this.dhtRows.forward(dArr2, this.rows * 2);
                    this.dhtRows.forward(dArr2, this.rows * 3);
                    int i8 = 0;
                    while (true) {
                        int i9 = this.rows;
                        if (i8 < i9) {
                            int i10 = i9 + i8;
                            double[] dArr4 = dArr[i8];
                            dArr4[i4] = dArr2[i8];
                            dArr4[i4 + 1] = dArr2[i10];
                            dArr4[i4 + 2] = dArr2[i10 + i9];
                            dArr4[i4 + 3] = dArr2[i10 + (i9 * 2)];
                            i8++;
                        }
                    }
                }
                return;
            }
            for (int i11 = 0; i11 < this.columns; i11 += 4) {
                int i12 = 0;
                while (true) {
                    int i13 = this.rows;
                    if (i12 >= i13) {
                        break;
                    }
                    int i14 = i13 + i12;
                    double[] dArr5 = dArr[i12];
                    dArr2[i12] = dArr5[i11];
                    dArr2[i14] = dArr5[i11 + 1];
                    dArr2[i14 + i13] = dArr5[i11 + 2];
                    dArr2[i14 + (i13 * 2)] = dArr5[i11 + 3];
                    i12++;
                }
                this.dhtRows.inverse(dArr2, 0, z);
                this.dhtRows.inverse(dArr2, this.rows, z);
                this.dhtRows.inverse(dArr2, this.rows * 2, z);
                this.dhtRows.inverse(dArr2, this.rows * 3, z);
                int i15 = 0;
                while (true) {
                    int i16 = this.rows;
                    if (i15 < i16) {
                        int i17 = i16 + i15;
                        double[] dArr6 = dArr[i15];
                        dArr6[i11] = dArr2[i15];
                        dArr6[i11 + 1] = dArr2[i17];
                        dArr6[i11 + 2] = dArr2[i17 + i16];
                        dArr6[i11 + 3] = dArr2[i17 + (i16 * 2)];
                        i15++;
                    }
                }
            }
            return;
        }
        if (i3 != 2) {
            return;
        }
        int i18 = 0;
        while (true) {
            int i19 = this.rows;
            if (i18 >= i19) {
                break;
            }
            double[] dArr7 = dArr[i18];
            dArr2[i18] = dArr7[0];
            dArr2[i19 + i18] = dArr7[1];
            i18++;
        }
        if (i == -1) {
            this.dhtRows.forward(dArr2, 0);
            this.dhtRows.forward(dArr2, this.rows);
        } else {
            this.dhtRows.inverse(dArr2, 0, z);
            this.dhtRows.inverse(dArr2, this.rows, z);
        }
        int i20 = 0;
        while (true) {
            int i21 = this.rows;
            if (i20 >= i21) {
                return;
            }
            double[] dArr8 = dArr[i20];
            dArr8[0] = dArr2[i20];
            dArr8[1] = dArr2[i21 + i20];
            i20++;
        }
    }

    private void yTransform(double[] dArr) {
        int i = 0;
        while (true) {
            int i2 = this.rows;
            if (i > i2 / 2) {
                return;
            }
            int i3 = (i2 - i) % i2;
            int i4 = this.columns;
            int i5 = i * i4;
            int i6 = i3 * i4;
            int i7 = 0;
            while (true) {
                int i8 = this.columns;
                if (i7 <= i8 / 2) {
                    int i9 = (i8 - i7) % i8;
                    int i10 = i5 + i7;
                    double d = dArr[i10];
                    int i11 = i6 + i7;
                    double d2 = dArr[i11];
                    int i12 = i5 + i9;
                    double d3 = dArr[i12];
                    int i13 = i9 + i6;
                    double d4 = dArr[i13];
                    double d5 = ((d + d4) - (d2 + d3)) / 2.0d;
                    dArr[i10] = d - d5;
                    dArr[i11] = d2 + d5;
                    dArr[i12] = d3 + d5;
                    dArr[i13] = d4 - d5;
                    i7++;
                }
            }
            i++;
        }
    }

    private void yTransform(DoubleLargeArray doubleLargeArray) {
        long j = 0;
        while (true) {
            long j2 = this.rowsl;
            long j3 = 2;
            if (j > j2 / 2) {
                return;
            }
            long j4 = (j2 - j) % j2;
            long j5 = this.columnsl;
            long j6 = j * j5;
            long j7 = j4 * j5;
            long j8 = 0;
            while (true) {
                long j9 = this.columnsl;
                if (j8 <= j9 / j3) {
                    long j10 = (j9 - j8) % j9;
                    long j11 = j6 + j8;
                    double d = doubleLargeArray.getDouble(j11);
                    long j12 = j7 + j8;
                    double d2 = doubleLargeArray.getDouble(j12);
                    long j13 = j6 + j10;
                    double d3 = doubleLargeArray.getDouble(j13);
                    long j14 = j6;
                    long j15 = j7 + j10;
                    double d4 = doubleLargeArray.getDouble(j15);
                    double d5 = ((d + d4) - (d2 + d3)) / 2.0d;
                    doubleLargeArray.setDouble(j11, d - d5);
                    doubleLargeArray.setDouble(j12, d2 + d5);
                    doubleLargeArray.setDouble(j13, d3 + d5);
                    doubleLargeArray.setDouble(j15, d4 - d5);
                    j8++;
                    j6 = j14;
                    j7 = j7;
                    j3 = 2;
                }
            }
            j++;
        }
    }

    private void yTransform(double[][] dArr) {
        int i = 0;
        while (true) {
            int i2 = this.rows;
            if (i > i2 / 2) {
                return;
            }
            int i3 = (i2 - i) % i2;
            int i4 = 0;
            while (true) {
                int i5 = this.columns;
                if (i4 <= i5 / 2) {
                    int i6 = (i5 - i4) % i5;
                    double[] dArr2 = dArr[i];
                    double d = dArr2[i4];
                    double[] dArr3 = dArr[i3];
                    double d2 = dArr3[i4];
                    double d3 = dArr2[i6];
                    double d4 = dArr3[i6];
                    double d5 = ((d + d4) - (d2 + d3)) / 2.0d;
                    dArr2[i4] = d - d5;
                    dArr3[i4] = d2 + d5;
                    dArr2[i6] = d3 + d5;
                    dArr3[i6] = d4 - d5;
                    i4++;
                }
            }
            i++;
        }
    }
}
