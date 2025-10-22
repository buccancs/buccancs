package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;

/* loaded from: classes2.dex */
public class DoubleDHT_1D {
    private final DoubleFFT_1D fft;
    private final int n;
    private final long nl;
    private final boolean useLargeArrays;

    public DoubleDHT_1D(long j) {
        this.n = (int) j;
        this.nl = j;
        this.useLargeArrays = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.fft = new DoubleFFT_1D(j);
    }

    public void forward(double[] dArr) {
        forward(dArr, 0);
    }

    public void forward(DoubleLargeArray doubleLargeArray) {
        forward(doubleLargeArray, 0L);
    }

    public void forward(final double[] dArr, final int i) {
        if (this.n == 1) {
            return;
        }
        if (this.useLargeArrays) {
            forward(new DoubleLargeArray(dArr), i);
            return;
        }
        this.fft.realForward(dArr, i);
        int i2 = this.n;
        final double[] dArr2 = new double[i2];
        System.arraycopy(dArr, i, dArr2, 0, i2);
        int i3 = this.n / 2;
        if (ConcurrencyUtils.getNumberOfThreads() <= 1 || i3 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            for (int i4 = 1; i4 < i3; i4++) {
                int i5 = i4 * 2;
                int i6 = i5 + 1;
                dArr[i + i4] = dArr2[i5] - dArr2[i6];
                dArr[(this.n + i) - i4] = dArr2[i5] + dArr2[i6];
            }
        } else {
            int i7 = i3 / 2;
            Future[] futureArr = new Future[2];
            int i8 = 0;
            while (i8 < 2) {
                final int i9 = (i8 * i7) + 1;
                final int i10 = i8 == 1 ? i3 : i9 + i7;
                int i11 = i8;
                Future[] futureArr2 = futureArr;
                futureArr2[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_1D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i12 = i9; i12 < i10; i12++) {
                            int i13 = i12 * 2;
                            int i14 = i13 + 1;
                            double[] dArr3 = dArr;
                            int i15 = i;
                            double[] dArr4 = dArr2;
                            dArr3[i15 + i12] = dArr4[i13] - dArr4[i14];
                            int i16 = (i15 + DoubleDHT_1D.this.n) - i12;
                            double[] dArr5 = dArr2;
                            dArr3[i16] = dArr5[i13] + dArr5[i14];
                        }
                    }
                });
                i8 = i11 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDHT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDHT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        int i12 = this.n;
        if (i12 % 2 == 0) {
            dArr[i + i3] = dArr2[1];
            return;
        }
        int i13 = i + i3;
        dArr[i13] = dArr2[i12 - 1] - dArr2[1];
        dArr[i13 + 1] = dArr2[i12 - 1] + dArr2[1];
    }

    public void forward(final DoubleLargeArray doubleLargeArray, final long j) {
        long j2;
        long j3 = 1;
        if (this.nl == 1) {
            return;
        }
        if (!this.useLargeArrays) {
            if (!doubleLargeArray.isLarge() && !doubleLargeArray.isConstant() && j < 2147483647L) {
                forward(doubleLargeArray.getData(), (int) j);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        this.fft.realForward(doubleLargeArray, j);
        final DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.nl, false);
        int i = 0;
        LargeArrayUtils.arraycopy(doubleLargeArray, j, doubleLargeArray2, 0L, this.nl);
        long j4 = this.nl / 2;
        int i2 = 1;
        if (ConcurrencyUtils.getNumberOfThreads() <= 1 || j4 <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            j2 = 1;
            long j5 = 1;
            while (j5 < j4) {
                long j6 = j5 * 2;
                long j7 = j6 + j2;
                doubleLargeArray.setDouble(j + j5, doubleLargeArray2.getDouble(j6) - doubleLargeArray2.getDouble(j7));
                doubleLargeArray.setDouble((this.nl + j) - j5, doubleLargeArray2.getDouble(j6) + doubleLargeArray2.getDouble(j7));
                j5++;
                j2 = 1;
            }
        } else {
            int i3 = 2;
            long j8 = j4 / 2;
            Future[] futureArr = new Future[2];
            while (i < i3) {
                final long j9 = (i * j8) + j3;
                Future[] futureArr2 = futureArr;
                final long j10 = i == i2 ? j4 : j9 + j8;
                futureArr2[i] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dht.DoubleDHT_1D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        for (long j11 = j9; j11 < j10; j11++) {
                            long j12 = 2 * j11;
                            long j13 = j12 + 1;
                            doubleLargeArray.setDouble(j + j11, doubleLargeArray2.getDouble(j12) - doubleLargeArray2.getDouble(j13));
                            doubleLargeArray.setDouble((j + DoubleDHT_1D.this.nl) - j11, doubleLargeArray2.getDouble(j12) + doubleLargeArray2.getDouble(j13));
                        }
                    }
                });
                i++;
                j3 = j3;
                futureArr = futureArr2;
                i3 = 2;
                i2 = 1;
            }
            j2 = j3;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleDHT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleDHT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        long j11 = j2;
        long j12 = this.nl;
        if (j12 % 2 == 0) {
            doubleLargeArray.setDouble(j + j4, doubleLargeArray2.getDouble(j11));
            return;
        }
        long j13 = j + j4;
        doubleLargeArray.setDouble(j13, doubleLargeArray2.getDouble(j12 - j11) - doubleLargeArray2.getDouble(j11));
        doubleLargeArray.setDouble(j13 + j11, doubleLargeArray2.getDouble(this.nl - j11) + doubleLargeArray2.getDouble(j11));
    }

    public void inverse(double[] dArr, boolean z) {
        inverse(dArr, 0, z);
    }

    public void inverse(DoubleLargeArray doubleLargeArray, boolean z) {
        inverse(doubleLargeArray, 0L, z);
    }

    public void inverse(double[] dArr, int i, boolean z) {
        if (this.n == 1) {
            return;
        }
        if (this.useLargeArrays) {
            inverse(new DoubleLargeArray(dArr), i, z);
            return;
        }
        forward(dArr, i);
        if (z) {
            int i2 = this.n;
            CommonUtils.scale(i2, 1.0d / i2, dArr, i, false);
        }
    }

    public void inverse(DoubleLargeArray doubleLargeArray, long j, boolean z) {
        if (this.n == 1) {
            return;
        }
        if (!this.useLargeArrays) {
            if (!doubleLargeArray.isLarge() && !doubleLargeArray.isConstant() && j < 2147483647L) {
                inverse(doubleLargeArray.getData(), (int) j, z);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        forward(doubleLargeArray, j);
        if (z) {
            int i = this.n;
            CommonUtils.scale(i, 1.0d / i, doubleLargeArray, j, false);
        }
    }
}
