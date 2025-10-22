package org.jtransforms.dct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.util.FastMath;
import org.jtransforms.fft.FloatFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

/* loaded from: classes2.dex */
public class FloatDCT_1D {
    private static final float PI = 3.1415927f;
    private FloatFFT_1D fft;
    private int[] ip;
    private LongLargeArray ipl;
    private boolean isPowerOfTwo;
    private int n;
    private int nc;
    private long ncl;
    private long nl;
    private int nw;
    private long nwl;
    private boolean useLargeArrays;
    private float[] w;
    private FloatLargeArray wl;

    public FloatDCT_1D(long j) {
        this.isPowerOfTwo = false;
        if (j < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        boolean z = CommonUtils.isUseLargeArrays() || j > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.useLargeArrays = z;
        this.n = (int) j;
        this.nl = j;
        if (z) {
            if (CommonUtils.isPowerOf2(j)) {
                this.isPowerOfTwo = true;
                this.ipl = new LongLargeArray((long) FastMath.ceil((1 << ((int) (((long) (FastMath.log((j / 2) + 0.5d) / FastMath.log(2.0d))) / 2))) + 2));
                this.wl = new FloatLargeArray((this.nl * 5) / 4);
                long j2 = this.ipl.getLong(0L);
                this.nwl = j2;
                if (j > (j2 << 2)) {
                    long j3 = this.nl >> 2;
                    this.nwl = j3;
                    CommonUtils.makewt(j3, this.ipl, this.wl);
                }
                long j4 = this.ipl.getLong(1L);
                this.ncl = j4;
                if (j > j4) {
                    long j5 = this.nl;
                    this.ncl = j5;
                    CommonUtils.makect(j5, this.wl, this.nwl, this.ipl);
                    return;
                }
                return;
            }
            this.wl = makect(j);
            this.fft = new FloatFFT_1D(j * 2);
            return;
        }
        if (j > 268435456) {
            throw new IllegalArgumentException("n must be smaller or equal to 268435456 when useLargeArrays argument is set to false");
        }
        if (CommonUtils.isPowerOf2(j)) {
            this.isPowerOfTwo = true;
            int[] iArr = new int[(int) FastMath.ceil((1 << (((int) (FastMath.log((j / 2) + 0.5d) / FastMath.log(2.0d))) / 2)) + 2)];
            this.ip = iArr;
            int i = this.n;
            float[] fArr = new float[(i * 5) / 4];
            this.w = fArr;
            this.nw = iArr[0];
            if (j > (r0 << 2)) {
                int i2 = i >> 2;
                this.nw = i2;
                CommonUtils.makewt(i2, iArr, fArr);
            }
            int[] iArr2 = this.ip;
            int i3 = iArr2[1];
            this.nc = i3;
            if (j > i3) {
                int i4 = this.n;
                this.nc = i4;
                CommonUtils.makect(i4, this.w, this.nw, iArr2);
                return;
            }
            return;
        }
        this.w = makect(this.n);
        this.fft = new FloatFFT_1D(j * 2);
    }

    private static void rftfsub(int i, float[] fArr, int i2, int i3, float[] fArr2, int i4) {
        int i5 = i >> 1;
        int i6 = (i3 * 2) / i5;
        int i7 = 0;
        for (int i8 = 2; i8 < i5; i8 += 2) {
            i7 += i6;
            float f = 0.5f - fArr2[(i4 + i3) - i7];
            float f2 = fArr2[i4 + i7];
            int i9 = i2 + i8;
            int i10 = i2 + (i - i8);
            float f3 = fArr[i9];
            float f4 = f3 - fArr[i10];
            int i11 = i9 + 1;
            int i12 = i10 + 1;
            float f5 = fArr[i11] + fArr[i12];
            float f6 = (f * f4) - (f2 * f5);
            float f7 = (f * f5) + (f2 * f4);
            fArr[i9] = f3 - f6;
            fArr[i11] = fArr[i11] - f7;
            fArr[i10] = fArr[i10] + f6;
            fArr[i12] = fArr[i12] - f7;
        }
    }

    private static void rftfsub(long j, FloatLargeArray floatLargeArray, long j2, long j3, FloatLargeArray floatLargeArray2, long j4) {
        FloatLargeArray floatLargeArray3 = floatLargeArray2;
        long j5 = j >> 1;
        long j6 = (j3 * 2) / j5;
        long j7 = 0;
        long j8 = 2;
        while (j8 < j5) {
            long j9 = j7 + j6;
            float f = 0.5f - floatLargeArray3.getFloat((j4 + j3) - j9);
            float f2 = floatLargeArray3.getFloat(j4 + j9);
            long j10 = j2 + j8;
            long j11 = j2 + (j - j8);
            float f3 = floatLargeArray.getFloat(j10) - floatLargeArray.getFloat(j11);
            long j12 = j10 + 1;
            long j13 = j11 + 1;
            float f4 = floatLargeArray.getFloat(j12) + floatLargeArray.getFloat(j13);
            float f5 = (f * f3) - (f2 * f4);
            float f6 = (f * f4) + (f2 * f3);
            floatLargeArray.setFloat(j10, floatLargeArray.getFloat(j10) - f5);
            floatLargeArray.setFloat(j12, floatLargeArray.getFloat(j12) - f6);
            floatLargeArray.setFloat(j11, floatLargeArray.getFloat(j11) + f5);
            floatLargeArray.setFloat(j13, floatLargeArray.getFloat(j13) - f6);
            j8 += 2;
            j5 = j5;
            j6 = j6;
            j7 = j9;
            floatLargeArray3 = floatLargeArray2;
        }
    }

    private static void rftbsub(int i, float[] fArr, int i2, int i3, float[] fArr2, int i4) {
        int i5 = i >> 1;
        int i6 = (i3 * 2) / i5;
        int i7 = 0;
        for (int i8 = 2; i8 < i5; i8 += 2) {
            i7 += i6;
            float f = 0.5f - fArr2[(i4 + i3) - i7];
            float f2 = fArr2[i4 + i7];
            int i9 = i2 + i8;
            int i10 = i2 + (i - i8);
            float f3 = fArr[i9];
            float f4 = f3 - fArr[i10];
            int i11 = i9 + 1;
            int i12 = i10 + 1;
            float f5 = fArr[i11] + fArr[i12];
            float f6 = (f * f4) + (f2 * f5);
            float f7 = (f * f5) - (f2 * f4);
            fArr[i9] = f3 - f6;
            fArr[i11] = fArr[i11] - f7;
            fArr[i10] = fArr[i10] + f6;
            fArr[i12] = fArr[i12] - f7;
        }
    }

    private static void rftbsub(long j, FloatLargeArray floatLargeArray, long j2, long j3, FloatLargeArray floatLargeArray2, long j4) {
        FloatLargeArray floatLargeArray3 = floatLargeArray2;
        long j5 = j >> 1;
        long j6 = (j3 * 2) / j5;
        long j7 = 0;
        long j8 = 2;
        while (j8 < j5) {
            long j9 = j7 + j6;
            float f = 0.5f - floatLargeArray3.getFloat((j4 + j3) - j9);
            float f2 = floatLargeArray3.getFloat(j4 + j9);
            long j10 = j2 + j8;
            long j11 = j2 + (j - j8);
            float f3 = floatLargeArray.getFloat(j10) - floatLargeArray.getFloat(j11);
            long j12 = j10 + 1;
            long j13 = j11 + 1;
            float f4 = floatLargeArray.getFloat(j12) + floatLargeArray.getFloat(j13);
            float f5 = (f * f3) + (f2 * f4);
            float f6 = (f * f4) - (f2 * f3);
            floatLargeArray.setFloat(j10, floatLargeArray.getFloat(j10) - f5);
            floatLargeArray.setFloat(j12, floatLargeArray.getFloat(j12) - f6);
            floatLargeArray.setFloat(j11, floatLargeArray.getFloat(j11) + f5);
            floatLargeArray.setFloat(j13, floatLargeArray.getFloat(j13) - f6);
            j8 += 2;
            j5 = j5;
            j6 = j6;
            j7 = j9;
            floatLargeArray3 = floatLargeArray2;
        }
    }

    public void forward(float[] fArr, boolean z) {
        forward(fArr, 0, z);
    }

    public void forward(FloatLargeArray floatLargeArray, boolean z) {
        forward(floatLargeArray, 0L, z);
    }

    public void forward(final float[] fArr, final int i, boolean z) {
        int i2;
        boolean z2;
        int i3 = this.n;
        if (i3 == 1) {
            return;
        }
        if (this.useLargeArrays) {
            forward(new FloatLargeArray(fArr), i, z);
            return;
        }
        if (this.isPowerOfTwo) {
            float f = fArr[(i + i3) - 1];
            for (int i4 = i3 - 2; i4 >= 2; i4 -= 2) {
                int i5 = i + i4;
                int i6 = i5 - 1;
                fArr[i5 + 1] = fArr[i5] - fArr[i6];
                fArr[i5] = fArr[i5] + fArr[i6];
            }
            fArr[i + 1] = fArr[i] - f;
            fArr[i] = fArr[i] + f;
            int i7 = this.n;
            if (i7 > 4) {
                rftbsub(i7, fArr, i, this.nc, this.w, this.nw);
                CommonUtils.cftbsub(this.n, fArr, i, this.ip, this.nw, this.w);
            } else if (i7 == 4) {
                CommonUtils.cftbsub(i7, fArr, i, this.ip, this.nw, this.w);
            }
            CommonUtils.dctsub(this.n, fArr, i, this.nc, this.w, this.nw);
            if (z) {
                int i8 = this.n;
                CommonUtils.scale(i8, (float) FastMath.sqrt(2.0d / i8), fArr, i, false);
                fArr[i] = fArr[i] / ((float) FastMath.sqrt(2.0d));
                return;
            }
            return;
        }
        int i9 = i3 * 2;
        float[] fArr2 = new float[i9];
        System.arraycopy(fArr, i, fArr2, 0, i3);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        for (int i10 = this.n; i10 < i9; i10++) {
            fArr2[i10] = fArr2[(i9 - i10) - 1];
        }
        this.fft.realForward(fArr2);
        if (numberOfThreads <= 1 || this.n <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            i2 = i9;
            z2 = false;
            for (int i11 = 0; i11 < this.n; i11++) {
                int i12 = i11 * 2;
                float[] fArr3 = this.w;
                float f2 = fArr3[i12] * fArr2[i12];
                int i13 = i12 + 1;
                fArr[i + i11] = f2 - (fArr3[i13] * fArr2[i13]);
            }
        } else {
            int i14 = this.n / 2;
            Future[] futureArr = new Future[2];
            int i15 = 0;
            for (int i16 = 2; i15 < i16; i16 = 2) {
                final int i17 = i15 * i14;
                final int i18 = i15 == 1 ? this.n : i17 + i14;
                int i19 = i15;
                Future[] futureArr2 = futureArr;
                final float[] fArr4 = fArr2;
                futureArr2[i19] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.FloatDCT_1D.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i20 = i17; i20 < i18; i20++) {
                            int i21 = i20 * 2;
                            int i22 = i + i20;
                            float[] fArr5 = fArr;
                            float f3 = FloatDCT_1D.this.w[i21] * fArr4[i21];
                            int i23 = i21 + 1;
                            fArr5[i22] = f3 - (FloatDCT_1D.this.w[i23] * fArr4[i23]);
                        }
                    }
                });
                i15 = i19 + 1;
                i9 = i9;
                fArr2 = fArr4;
                futureArr = futureArr2;
            }
            i2 = i9;
            z2 = false;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        if (z) {
            CommonUtils.scale(this.n, 1.0f / ((float) FastMath.sqrt(i2)), fArr, i, z2);
            fArr[i] = fArr[i] / ((float) FastMath.sqrt(2.0d));
        }
    }

    public void forward(final FloatLargeArray floatLargeArray, final long j, boolean z) {
        long j2;
        long j3 = j;
        long j4 = this.nl;
        if (j4 == 1) {
            return;
        }
        if (!this.useLargeArrays) {
            if (!floatLargeArray.isLarge() && !floatLargeArray.isConstant() && j3 < 2147483647L) {
                forward(floatLargeArray.getData(), (int) j3, z);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        if (this.isPowerOfTwo) {
            float f = floatLargeArray.getFloat((j4 + j3) - 1);
            long j5 = this.nl - 2;
            while (j5 >= 2) {
                long j6 = j3 + j5;
                long j7 = j6 - 1;
                floatLargeArray.setFloat(j6 + 1, floatLargeArray.getFloat(j6) - floatLargeArray.getFloat(j7));
                floatLargeArray.setFloat(j6, floatLargeArray.getFloat(j6) + floatLargeArray.getFloat(j7));
                j5 -= 2;
                j3 = j;
            }
            floatLargeArray.setFloat(j3 + 1, floatLargeArray.getFloat(j) - f);
            floatLargeArray.setFloat(j3, floatLargeArray.getFloat(j) + f);
            long j8 = this.nl;
            if (j8 > 4) {
                rftbsub(j8, floatLargeArray, j, this.ncl, this.wl, this.nwl);
                CommonUtils.cftbsub(this.nl, floatLargeArray, j, this.ipl, this.nwl, this.wl);
            } else if (j8 == 4) {
                CommonUtils.cftbsub(j8, floatLargeArray, j, this.ipl, this.nwl, this.wl);
            }
            CommonUtils.dctsub(this.nl, floatLargeArray, j, this.ncl, this.wl, this.nwl);
            if (z) {
                long j9 = this.nl;
                CommonUtils.scale(j9, (float) FastMath.sqrt(2.0d / j9), floatLargeArray, j, false);
                floatLargeArray.setFloat(j3, floatLargeArray.getFloat(j) / ((float) FastMath.sqrt(2.0d)));
                return;
            }
            return;
        }
        long j10 = j4 * 2;
        final FloatLargeArray floatLargeArray2 = new FloatLargeArray(j10);
        long j11 = j10;
        LargeArrayUtils.arraycopy(floatLargeArray, j, floatLargeArray2, 0L, this.nl);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        for (long j12 = this.nl; j12 < j11; j12++) {
            floatLargeArray2.setFloat(j12, floatLargeArray2.getFloat((j11 - j12) - 1));
        }
        this.fft.realForward(floatLargeArray2);
        int i = 1;
        if (numberOfThreads <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            j2 = j11;
            for (long j13 = 0; j13 < this.nl; j13++) {
                long j14 = j13 * 2;
                float f2 = this.wl.getFloat(j14) * floatLargeArray2.getFloat(j14);
                long j15 = j14 + 1;
                floatLargeArray.setFloat(j3 + j13, f2 - (this.wl.getFloat(j15) * floatLargeArray2.getFloat(j15)));
            }
        } else {
            long j16 = this.nl / 2;
            Future[] futureArr = new Future[2];
            int i2 = 0;
            for (int i3 = 2; i2 < i3; i3 = 2) {
                final long j17 = i2 * j16;
                final long j18 = i2 == i ? this.nl : j17 + j16;
                Future[] futureArr2 = futureArr;
                int i4 = i2;
                futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.FloatDCT_1D.2
                    @Override // java.lang.Runnable
                    public void run() {
                        for (long j19 = j17; j19 < j18; j19++) {
                            long j20 = 2 * j19;
                            long j21 = j + j19;
                            FloatLargeArray floatLargeArray3 = floatLargeArray;
                            float f3 = FloatDCT_1D.this.wl.getFloat(j20) * floatLargeArray2.getFloat(j20);
                            long j22 = j20 + 1;
                            floatLargeArray3.setFloat(j21, f3 - (FloatDCT_1D.this.wl.getFloat(j22) * floatLargeArray2.getFloat(j22)));
                        }
                    }
                });
                i2 = i4 + 1;
                futureArr = futureArr2;
                j11 = j11;
                i = 1;
            }
            j2 = j11;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
        }
        if (z) {
            CommonUtils.scale(this.nl, 1.0f / ((float) FastMath.sqrt(j2)), floatLargeArray, j, false);
            floatLargeArray.setFloat(j3, floatLargeArray.getFloat(j) / ((float) FastMath.sqrt(2.0d)));
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
        int i3 = 2;
        if (this.isPowerOfTwo) {
            if (z) {
                CommonUtils.scale(i2, (float) FastMath.sqrt(2.0d / i2), fArr, i, false);
                fArr[i] = fArr[i] / ((float) FastMath.sqrt(2.0d));
            }
            CommonUtils.dctsub(this.n, fArr, i, this.nc, this.w, this.nw);
            int i4 = this.n;
            if (i4 > 4) {
                CommonUtils.cftfsub(i4, fArr, i, this.ip, this.nw, this.w);
                rftfsub(this.n, fArr, i, this.nc, this.w, this.nw);
            } else if (i4 == 4) {
                CommonUtils.cftfsub(i4, fArr, i, this.ip, this.nw, this.w);
            }
            float f = fArr[i];
            float f2 = fArr[i + 1];
            float f3 = f - f2;
            fArr[i] = f + f2;
            while (true) {
                int i5 = this.n;
                if (i3 < i5) {
                    int i6 = i + i3;
                    int i7 = i6 + 1;
                    fArr[i6 - 1] = fArr[i6] - fArr[i7];
                    fArr[i6] = fArr[i6] + fArr[i7];
                    i3 += 2;
                } else {
                    fArr[(i5 + i) - 1] = f3;
                    return;
                }
            }
        } else {
            int i8 = i2 * 2;
            if (z) {
                CommonUtils.scale(i2, (float) FastMath.sqrt(i8), fArr, i, false);
                fArr[i] = fArr[i] * ((float) FastMath.sqrt(2.0d));
            }
            final float[] fArr2 = new float[i8];
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || this.n <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                for (int i9 = 0; i9 < this.n; i9++) {
                    int i10 = i9 * 2;
                    float f4 = fArr[i + i9];
                    float[] fArr3 = this.w;
                    fArr2[i10] = fArr3[i10] * f4;
                    int i11 = i10 + 1;
                    fArr2[i11] = (-fArr3[i11]) * f4;
                }
            } else {
                int i12 = this.n / 2;
                Future[] futureArr = new Future[2];
                int i13 = 0;
                while (i13 < 2) {
                    final int i14 = i13 * i12;
                    final int i15 = i13 == 1 ? this.n : i14 + i12;
                    int i16 = i13;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i16] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.FloatDCT_1D.3
                        @Override // java.lang.Runnable
                        public void run() {
                            for (int i17 = i14; i17 < i15; i17++) {
                                int i18 = i17 * 2;
                                float f5 = fArr[i + i17];
                                fArr2[i18] = FloatDCT_1D.this.w[i18] * f5;
                                int i19 = i18 + 1;
                                fArr2[i19] = (-FloatDCT_1D.this.w[i19]) * f5;
                            }
                        }
                    });
                    i13 = i16 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
            }
            this.fft.realInverse(fArr2, true);
            System.arraycopy(fArr2, 0, fArr, i, this.n);
        }
    }

    public void inverse(final FloatLargeArray floatLargeArray, final long j, boolean z) {
        long j2;
        FloatLargeArray floatLargeArray2;
        long j3 = this.nl;
        if (j3 == 1) {
            return;
        }
        if (!this.useLargeArrays) {
            if (!floatLargeArray.isLarge() && !floatLargeArray.isConstant() && j < 2147483647L) {
                inverse(floatLargeArray.getData(), (int) j, z);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        if (this.isPowerOfTwo) {
            if (z) {
                CommonUtils.scale(j3, (float) FastMath.sqrt(2.0d / j3), floatLargeArray, j, false);
                floatLargeArray.setFloat(j, floatLargeArray.getFloat(j) / ((float) FastMath.sqrt(2.0d)));
            }
            CommonUtils.dctsub(this.nl, floatLargeArray, j, this.ncl, this.wl, this.nwl);
            long j4 = this.nl;
            if (j4 > 4) {
                CommonUtils.cftfsub(j4, floatLargeArray, j, this.ipl, this.nwl, this.wl);
                rftfsub(this.nl, floatLargeArray, j, this.ncl, this.wl, this.nwl);
            } else if (j4 == 4) {
                CommonUtils.cftfsub(j4, floatLargeArray, j, this.ipl, this.nwl, this.wl);
            }
            long j5 = j + 1;
            float f = floatLargeArray.getFloat(j) - floatLargeArray.getFloat(j5);
            floatLargeArray.setFloat(j, floatLargeArray.getFloat(j) + floatLargeArray.getFloat(j5));
            long j6 = 2;
            while (true) {
                long j7 = this.nl;
                if (j6 < j7) {
                    long j8 = j + j6;
                    long j9 = j8 + 1;
                    floatLargeArray.setFloat(j8 - 1, floatLargeArray.getFloat(j8) - floatLargeArray.getFloat(j9));
                    floatLargeArray.setFloat(j8, floatLargeArray.getFloat(j8) + floatLargeArray.getFloat(j9));
                    j6 += 2;
                } else {
                    floatLargeArray.setFloat((j + j7) - 1, f);
                    return;
                }
            }
        } else {
            long j10 = j3 * 2;
            if (z) {
                j2 = j10;
                CommonUtils.scale(j3, (float) FastMath.sqrt(j10), floatLargeArray, j, false);
                floatLargeArray.setFloat(j, floatLargeArray.getFloat(j) * ((float) FastMath.sqrt(2.0d)));
            } else {
                j2 = j10;
            }
            final FloatLargeArray floatLargeArray3 = new FloatLargeArray(j2);
            int i = 1;
            if (ConcurrencyUtils.getNumberOfThreads() <= 1 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                floatLargeArray2 = floatLargeArray3;
                for (long j11 = 0; j11 < this.nl; j11++) {
                    long j12 = j11 * 2;
                    float f2 = floatLargeArray.getFloat(j + j11);
                    floatLargeArray2.setFloat(j12, this.wl.getFloat(j12) * f2);
                    long j13 = j12 + 1;
                    floatLargeArray2.setFloat(j13, (-this.wl.getFloat(j13)) * f2);
                }
            } else {
                long j14 = this.nl / 2;
                Future[] futureArr = new Future[2];
                int i2 = 0;
                for (int i3 = 2; i2 < i3; i3 = 2) {
                    final long j15 = i2 * j14;
                    final long j16 = i2 == i ? this.nl : j15 + j14;
                    int i4 = i2;
                    futureArr[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.dct.FloatDCT_1D.4
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j17 = j15; j17 < j16; j17++) {
                                long j18 = 2 * j17;
                                float f3 = floatLargeArray.getFloat(j + j17);
                                floatLargeArray3.setFloat(j18, FloatDCT_1D.this.wl.getFloat(j18) * f3);
                                long j19 = j18 + 1;
                                floatLargeArray3.setFloat(j19, (-FloatDCT_1D.this.wl.getFloat(j19)) * f3);
                            }
                        }
                    });
                    i2 = i4 + 1;
                    i = 1;
                }
                floatLargeArray2 = floatLargeArray3;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException e) {
                    Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                } catch (ExecutionException e2) {
                    Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
                }
            }
            FloatLargeArray floatLargeArray4 = floatLargeArray2;
            this.fft.realInverse(floatLargeArray4, true);
            LargeArrayUtils.arraycopy(floatLargeArray4, 0L, floatLargeArray, j, this.nl);
        }
    }

    private float[] makect(int i) {
        int i2 = i * 2;
        float f = PI / i2;
        float[] fArr = new float[i2];
        fArr[0] = 1.0f;
        for (int i3 = 1; i3 < i; i3++) {
            int i4 = i3 * 2;
            double d = i3 * f;
            fArr[i4] = (float) FastMath.cos(d);
            fArr[i4 + 1] = -((float) FastMath.sin(d));
        }
        return fArr;
    }

    private FloatLargeArray makect(long j) {
        long j2 = j * 2;
        float f = PI / j2;
        FloatLargeArray floatLargeArray = new FloatLargeArray(j2);
        floatLargeArray.setFloat(0L, 1.0f);
        for (long j3 = 1; j3 < j; j3++) {
            long j4 = j3 * 2;
            double d = j3 * f;
            floatLargeArray.setFloat(j4, (float) FastMath.cos(d));
            floatLargeArray.setFloat(j4 + 1, -((float) FastMath.sin(d)));
        }
        return floatLargeArray;
    }
}
