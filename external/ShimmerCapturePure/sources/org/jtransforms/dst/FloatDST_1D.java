package org.jtransforms.dst;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jtransforms.dct.FloatDCT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

/* loaded from: classes2.dex */
public class FloatDST_1D {
    private final FloatDCT_1D dct;
    private final int n;
    private final long nl;
    private final boolean useLargeArrays;

    public FloatDST_1D(long j) {
        this.n = (int) j;
        this.nl = j;
        this.useLargeArrays = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.dct = new FloatDCT_1D(j);
    }

    public void forward(float[] fArr, boolean z) {
        forward(fArr, 0, z);
    }

    public void forward(FloatLargeArray floatLargeArray, boolean z) {
        forward(floatLargeArray, 0L, z);
    }

    public void forward(final float[] fArr, final int i, boolean z) {
        int i2 = this.n;
        if (i2 == 1) {
            return;
        }
        if (this.useLargeArrays) {
            forward(new FloatLargeArray(fArr), i, z);
            return;
        }
        int i3 = i2 / 2;
        int i4 = i2 + i;
        for (int i5 = i + 1; i5 < i4; i5 += 2) {
            fArr[i5] = -fArr[i5];
        }
        this.dct.forward(fArr, i, z);
        if (ConcurrencyUtils.getNumberOfThreads() <= 1 || i3 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            int i6 = (this.n + i) - 1;
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = i + i7;
                float f = fArr[i8];
                int i9 = i6 - i7;
                fArr[i8] = fArr[i9];
                fArr[i9] = f;
            }
            return;
        }
        int i10 = i3 / 2;
        Future[] futureArr = new Future[2];
        int i11 = 0;
        while (i11 < 2) {
            final int i12 = i11 * i10;
            final int i13 = i11 == 1 ? i3 : i12 + i10;
            futureArr[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dst.FloatDST_1D.1
                @Override // java.lang.Runnable
                public void run() {
                    int i14 = (i + FloatDST_1D.this.n) - 1;
                    for (int i15 = i12; i15 < i13; i15++) {
                        int i16 = i + i15;
                        float[] fArr2 = fArr;
                        float f2 = fArr2[i16];
                        int i17 = i14 - i15;
                        fArr2[i16] = fArr2[i17];
                        fArr2[i17] = f2;
                    }
                }
            });
            i11++;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    public void forward(final FloatLargeArray floatLargeArray, final long j, boolean z) {
        long j2 = this.nl;
        if (j2 == 1) {
            return;
        }
        if (!this.useLargeArrays) {
            if (!floatLargeArray.isLarge() && !floatLargeArray.isConstant() && j < 2147483647L) {
                forward(floatLargeArray.getData(), (int) j, z);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        long j3 = j2 / 2;
        long j4 = j2 + j;
        for (long j5 = j + 1; j5 < j4; j5 += 2) {
            floatLargeArray.setFloat(j5, -floatLargeArray.getFloat(j5));
        }
        this.dct.forward(floatLargeArray, j, z);
        int i = 1;
        if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j3 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            long j6 = (this.nl + j) - 1;
            for (long j7 = 0; j7 < j3; j7++) {
                long j8 = j + j7;
                float f = floatLargeArray.getFloat(j8);
                long j9 = j6 - j7;
                floatLargeArray.setFloat(j8, floatLargeArray.getFloat(j9));
                floatLargeArray.setFloat(j9, f);
            }
            return;
        }
        int i2 = 2;
        long j10 = j3 / 2;
        Future[] futureArr = new Future[2];
        int i3 = 0;
        while (i3 < i2) {
            final long j11 = i3 * j10;
            long j12 = i3 == i ? j3 : j11 + j10;
            int i4 = i3;
            Future[] futureArr2 = futureArr;
            final long j13 = j12;
            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dst.FloatDST_1D.2
                @Override // java.lang.Runnable
                public void run() {
                    long j14 = (j + FloatDST_1D.this.nl) - 1;
                    for (long j15 = j11; j15 < j13; j15++) {
                        long j16 = j + j15;
                        float f2 = floatLargeArray.getFloat(j16);
                        long j17 = j14 - j15;
                        FloatLargeArray floatLargeArray2 = floatLargeArray;
                        floatLargeArray2.setFloat(j16, floatLargeArray2.getFloat(j17));
                        floatLargeArray.setFloat(j17, f2);
                    }
                }
            });
            i3 = i4 + 1;
            futureArr = futureArr2;
            i2 = 2;
            i = 1;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException e) {
            Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
        } catch (ExecutionException e2) {
            Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
        }
    }

    public void inverse(float[] fArr, boolean z) {
        inverse(fArr, 0, z);
    }

    public void inverse(FloatLargeArray floatLargeArray, boolean z) {
        inverse(floatLargeArray, 0L, z);
    }

    public void inverse(final float[] fArr, final int i, boolean z) {
        int i2 = this.n;
        if (i2 == 1) {
            return;
        }
        if (this.useLargeArrays) {
            inverse(new FloatLargeArray(fArr), i, z);
            return;
        }
        int i3 = i2 / 2;
        if (ConcurrencyUtils.getNumberOfThreads() <= 1 || i3 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            int i4 = (this.n + i) - 1;
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = i + i5;
                float f = fArr[i6];
                int i7 = i4 - i5;
                fArr[i6] = fArr[i7];
                fArr[i7] = f;
            }
        } else {
            int i8 = i3 / 2;
            Future[] futureArr = new Future[2];
            int i9 = 0;
            while (i9 < 2) {
                final int i10 = i9 * i8;
                final int i11 = i9 == 1 ? i3 : i10 + i8;
                int i12 = i9;
                futureArr[i12] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dst.FloatDST_1D.3
                    @Override // java.lang.Runnable
                    public void run() {
                        int i13 = (i + FloatDST_1D.this.n) - 1;
                        for (int i14 = i10; i14 < i11; i14++) {
                            int i15 = i + i14;
                            float[] fArr2 = fArr;
                            float f2 = fArr2[i15];
                            int i16 = i13 - i14;
                            fArr2[i15] = fArr2[i16];
                            fArr2[i16] = f2;
                        }
                    }
                });
                i9 = i12 + 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        this.dct.inverse(fArr, i, z);
        int i13 = this.n + i;
        for (int i14 = i + 1; i14 < i13; i14 += 2) {
            fArr[i14] = -fArr[i14];
        }
    }

    public void inverse(final FloatLargeArray floatLargeArray, final long j, boolean z) {
        long j2 = this.nl;
        if (j2 == 1) {
            return;
        }
        if (!this.useLargeArrays) {
            if (!floatLargeArray.isLarge() && !floatLargeArray.isConstant() && j < 2147483647L) {
                inverse(floatLargeArray.getData(), (int) j, z);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        long j3 = j2 / 2;
        int i = 1;
        if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j3 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            long j4 = (this.nl + j) - 1;
            for (long j5 = 0; j5 < j3; j5++) {
                long j6 = j + j5;
                float f = floatLargeArray.getFloat(j6);
                long j7 = j4 - j5;
                floatLargeArray.setFloat(j6, floatLargeArray.getFloat(j7));
                floatLargeArray.setFloat(j7, f);
            }
        } else {
            long j8 = j3 / 2;
            Future[] futureArr = new Future[2];
            int i2 = 0;
            while (i2 < 2) {
                final long j9 = i2 * j8;
                Future[] futureArr2 = futureArr;
                int i3 = i2;
                final long j10 = i2 == i ? j3 : j9 + j8;
                futureArr2[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dst.FloatDST_1D.4
                    @Override // java.lang.Runnable
                    public void run() {
                        long j11 = (j + FloatDST_1D.this.nl) - 1;
                        for (long j12 = j9; j12 < j10; j12++) {
                            long j13 = j + j12;
                            float f2 = floatLargeArray.getFloat(j13);
                            long j14 = j11 - j12;
                            FloatLargeArray floatLargeArray2 = floatLargeArray;
                            floatLargeArray2.setFloat(j13, floatLargeArray2.getFloat(j14));
                            floatLargeArray.setFloat(j14, f2);
                        }
                    }
                });
                i2 = i3 + 1;
                futureArr = futureArr2;
                i = 1;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        this.dct.inverse(floatLargeArray, j, z);
        long j11 = this.nl + j;
        for (long j12 = j + 1; j12 < j11; j12 += 2) {
            floatLargeArray.setFloat(j12, -floatLargeArray.getFloat(j12));
        }
    }
}
