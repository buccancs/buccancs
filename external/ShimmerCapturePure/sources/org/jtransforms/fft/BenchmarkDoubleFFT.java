package org.jtransforms.fft;

import com.shimmerresearch.verisense.communication.VerisenseMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import org.jtransforms.utils.IOUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;

/* loaded from: classes2.dex */
public class BenchmarkDoubleFFT {
    private static boolean doWarmup = true;
    private static int niter = 100;
    private static int nsize = 8;
    private static int nthread = 16;
    private static int threadsBegin2D = 65536;
    private static int threadsBegin3D = 65536;
    private static long[] sizes1D = {262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 10368, 27000, 75600, 165375, 362880, 1562500, 3211264, 6250000};
    private static long[] sizes2D = {256, 512, 1024, 2048, 4096, Http2CodecUtil.DEFAULT_HEADER_LIST_SIZE, DefaultHttpDataFactory.MINSIZE, 32768, 260, 520, 1050, 1458, 1960, 2916, 4116, 5832};
    private static long[] sizes3D = {16, 32, 64, 128, 256, 512, 1024, 2048, 5, 17, 30, 95, 180, 270, 324, 420};
    private static boolean doScaling = false;

    private BenchmarkDoubleFFT() {
    }

    public static void parseArguments(String[] strArr) throws NumberFormatException {
        if (strArr.length > 0) {
            int i = 0;
            nthread = Integer.parseInt(strArr[0]);
            threadsBegin2D = Integer.parseInt(strArr[1]);
            threadsBegin3D = Integer.parseInt(strArr[2]);
            niter = Integer.parseInt(strArr[3]);
            doWarmup = Boolean.parseBoolean(strArr[4]);
            doScaling = Boolean.parseBoolean(strArr[5]);
            int i2 = Integer.parseInt(strArr[6]);
            nsize = i2;
            sizes1D = new long[i2];
            sizes2D = new long[i2];
            sizes3D = new long[i2];
            for (int i3 = 0; i3 < nsize; i3++) {
                sizes1D[i3] = Integer.parseInt(strArr[i3 + 7]);
            }
            int i4 = 0;
            while (true) {
                if (i4 >= nsize) {
                    break;
                }
                sizes2D[i4] = Integer.parseInt(strArr[r2 + 7 + i4]);
                i4++;
            }
            while (true) {
                if (i >= nsize) {
                    break;
                }
                sizes3D[i] = Integer.parseInt(strArr[r1 + 7 + r1 + i]);
                i++;
            }
        } else {
            System.out.println("Default settings are used.");
        }
        ConcurrencyUtils.setNumberOfThreads(nthread);
        CommonUtils.setThreadsBeginN_2D(threadsBegin2D);
        CommonUtils.setThreadsBeginN_3D(threadsBegin3D);
        System.out.println("nthred = " + nthread);
        System.out.println("threadsBegin2D = " + threadsBegin2D);
        System.out.println("threadsBegin3D = " + threadsBegin3D);
        System.out.println("niter = " + niter);
        System.out.println("doWarmup = " + doWarmup);
        System.out.println("doScaling = " + doScaling);
        System.out.println("nsize = " + nsize);
        System.out.println("sizes1D[] = " + Arrays.toString(sizes1D));
        System.out.println("sizes2D[] = " + Arrays.toString(sizes2D));
        System.out.println("sizes3D[] = " + Arrays.toString(sizes3D));
    }

    public static void benchmarkComplexForward_1D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Complex forward FFT 1D of size " + sizes1D[i2]);
            if (doWarmup) {
                DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(sizes1D[i2]);
                long j = sizes1D[i2];
                double[] dArr3 = new double[(int) (j * 2)];
                IOUtils.fillMatrix_1D(j * 2, dArr3);
                doubleFFT_1D.complexForward(dArr3);
                IOUtils.fillMatrix_1D(sizes1D[i2] * 2, dArr3);
                doubleFFT_1D.complexForward(dArr3);
            }
            long jNanoTime = System.nanoTime();
            DoubleFFT_1D doubleFFT_1D2 = new DoubleFFT_1D(sizes1D[i2]);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            double[] dArr4 = new double[(int) (sizes1D[i2] * 2)];
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                IOUtils.fillMatrix_1D(sizes1D[i2] * 2, dArr4);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_1D2.complexForward(dArr4);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleComplexForwardFFT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, dArr, dArr2);
    }

    public static void benchmarkRealForward_1D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 1D of size " + sizes1D[i2]);
            if (doWarmup) {
                DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(sizes1D[i2]);
                long j = sizes1D[i2];
                double[] dArr3 = new double[(int) (j * 2)];
                IOUtils.fillMatrix_1D(j, dArr3);
                doubleFFT_1D.realForwardFull(dArr3);
                IOUtils.fillMatrix_1D(sizes1D[i2], dArr3);
                doubleFFT_1D.realForwardFull(dArr3);
            }
            long jNanoTime = System.nanoTime();
            DoubleFFT_1D doubleFFT_1D2 = new DoubleFFT_1D(sizes1D[i2]);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            double[] dArr4 = new double[(int) (sizes1D[i2] * 2)];
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                IOUtils.fillMatrix_1D(sizes1D[i2], dArr4);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_1D2.realForwardFull(dArr4);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleRealForwardFFT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_2D_input_1D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        boolean z = false;
        int i2 = 0;
        while (i2 < nsize) {
            System.out.println("Complex forward FFT 2D (input 1D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                DoubleFFT_2D doubleFFT_2D = new DoubleFFT_2D(j, j);
                long j2 = sizes2D[i2];
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j2 * 2 * j2, z);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3 * 2, doubleLargeArray);
                doubleFFT_2D.complexForward(doubleLargeArray);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4 * 2, doubleLargeArray);
                doubleFFT_2D.complexForward(doubleLargeArray);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            DoubleFFT_2D doubleFFT_2D2 = new DoubleFFT_2D(j5, j5);
            double d = 1000000.0d;
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes2D[i2];
            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j6 * 2 * j6, z);
            int iMax = niter;
            if (sizes2D[i2] >= Http2CodecUtil.DEFAULT_HEADER_LIST_SIZE) {
                iMax = FastMath.max(1, iMax / 10);
            }
            double d2 = Double.MAX_VALUE;
            int i3 = 0;
            while (i3 < iMax) {
                long j7 = sizes2D[i2];
                int i4 = i2;
                IOUtils.fillMatrix_2D(j7, j7 * 2, doubleLargeArray2);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_2D2.complexForward(doubleLargeArray2);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d2) {
                    d2 = dNanoTime;
                }
                i3++;
                i2 = i4;
                d = 1000000.0d;
            }
            int i5 = i2;
            double d3 = d2 / d;
            dArr[i5] = d3;
            dArr2[i5] = dArr2[i5] + d3;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i5])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i5])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
            i2 = i5 + 1;
            z = false;
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleComplexForwardFFT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_2D_input_2D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        int i2 = 0;
        while (i2 < nsize) {
            System.out.println("Complex forward FFT 2D (input 2D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                DoubleFFT_2D doubleFFT_2D = new DoubleFFT_2D(j, j);
                long j2 = sizes2D[i2];
                double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, (int) j2, ((int) j2) * 2);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3 * 2, dArr3);
                doubleFFT_2D.complexForward(dArr3);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4 * 2, dArr3);
                doubleFFT_2D.complexForward(dArr3);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            DoubleFFT_2D doubleFFT_2D2 = new DoubleFFT_2D(j5, j5);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes2D[i2];
            double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, (int) j6, ((int) j6) * 2);
            double d = Double.MAX_VALUE;
            int i3 = 0;
            while (i3 < niter) {
                long j7 = sizes2D[i2];
                int i4 = i2;
                IOUtils.fillMatrix_2D(j7, j7 * 2, dArr4);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_2D2.complexForward(dArr4);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
                i3++;
                i2 = i4;
            }
            int i5 = i2;
            double d2 = d / 1000000.0d;
            dArr[i5] = d2;
            dArr2[i5] = dArr2[i5] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i5])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i5])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
            i2 = i5 + 1;
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleComplexForwardFFT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkRealForward_2D_input_1D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 2D (input 1D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                DoubleFFT_2D doubleFFT_2D = new DoubleFFT_2D(j, j);
                long j2 = sizes2D[i2];
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j2 * 2 * j2, false);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3, doubleLargeArray);
                doubleFFT_2D.realForwardFull(doubleLargeArray);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4, doubleLargeArray);
                doubleFFT_2D.realForwardFull(doubleLargeArray);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            DoubleFFT_2D doubleFFT_2D2 = new DoubleFFT_2D(j5, j5);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes2D[i2];
            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(2 * j6 * j6, false);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j7, j7, doubleLargeArray2);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_2D2.realForwardFull(doubleLargeArray2);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleRealForwardFFT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkRealForward_2D_input_2D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 2D (input 2D) of size " + sizes2D[i2] + " x " + sizes2D[i2]);
            if (doWarmup) {
                long j = sizes2D[i2];
                DoubleFFT_2D doubleFFT_2D = new DoubleFFT_2D(j, j);
                long j2 = sizes2D[i2];
                double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, (int) j2, ((int) j2) * 2);
                long j3 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j3, j3, dArr3);
                doubleFFT_2D.realForwardFull(dArr3);
                long j4 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j4, j4, dArr3);
                doubleFFT_2D.realForwardFull(dArr3);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes2D[i2];
            DoubleFFT_2D doubleFFT_2D2 = new DoubleFFT_2D(j5, j5);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes2D[i2];
            double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, (int) j6, ((int) j6) * 2);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes2D[i2];
                IOUtils.fillMatrix_2D(j7, j7, dArr4);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_2D2.realForwardFull(dArr4);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleRealForwardFFT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_3D_input_1D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Complex forward FFT 3D (input 1D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            long j = 2;
            if (doWarmup) {
                long j2 = sizes3D[i2];
                DoubleFFT_3D doubleFFT_3D = new DoubleFFT_3D(j2, j2, j2);
                long j3 = sizes3D[i2];
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j3 * j3 * 2 * j3, false);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4 * 2, doubleLargeArray);
                doubleFFT_3D.complexForward(doubleLargeArray);
                long j5 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j5, j5, j5 * 2, doubleLargeArray);
                doubleFFT_3D.complexForward(doubleLargeArray);
            }
            long jNanoTime = System.nanoTime();
            long j6 = sizes3D[i2];
            DoubleFFT_3D doubleFFT_3D2 = new DoubleFFT_3D(j6, j6, j6);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j7 = sizes3D[i2];
            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j7 * j7 * 2 * j7, false);
            int iMax = niter;
            if (sizes3D[i2] >= 1024) {
                iMax = FastMath.max(1, iMax / 10);
            }
            double d = Double.MAX_VALUE;
            int i3 = 0;
            while (i3 < iMax) {
                long j8 = sizes3D[i2];
                int i4 = i3;
                IOUtils.fillMatrix_3D(j8, j8, j8 * j, doubleLargeArray2);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_3D2.complexForward(doubleLargeArray2);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
                i3 = i4 + 1;
                j = 2;
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleComplexForwardFFT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkComplexForward_3D_input_3D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Complex forward FFT 3D (input 3D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                DoubleFFT_3D doubleFFT_3D = new DoubleFFT_3D(j, j, j);
                long j2 = sizes3D[i2];
                double[][][] dArr3 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, (int) j2, (int) j2, ((int) j2) * 2);
                long j3 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j3, j3, j3 * 2, dArr3);
                doubleFFT_3D.complexForward(dArr3);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4 * 2, dArr3);
                doubleFFT_3D.complexForward(dArr3);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            DoubleFFT_3D doubleFFT_3D2 = new DoubleFFT_3D(j5, j5, j5);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes3D[i2];
            double[][][] dArr4 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, (int) j6, (int) j6, ((int) j6) * 2);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7 * 2, dArr4);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_3D2.complexForward(dArr4);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleComplexForwardFFT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkRealForward_3D_input_1D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 3D (input 1D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                DoubleFFT_3D doubleFFT_3D = new DoubleFFT_3D(j, j, j);
                long j2 = sizes3D[i2];
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(j2 * j2 * 2 * j2, false);
                long j3 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j3, j3, j3, doubleLargeArray);
                doubleFFT_3D.realForwardFull(doubleLargeArray);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4, doubleLargeArray);
                doubleFFT_3D.realForwardFull(doubleLargeArray);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            DoubleFFT_3D doubleFFT_3D2 = new DoubleFFT_3D(j5, j5, j5);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes3D[i2];
            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(j6 * j6 * 2 * j6, false);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7, doubleLargeArray2);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_3D2.realForwardFull(doubleLargeArray2);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleRealForwardFFT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void benchmarkRealForward_3D_input_3D() throws InterruptedException, IOException {
        int i = nsize;
        double[] dArr = new double[i];
        double[] dArr2 = new double[i];
        for (int i2 = 0; i2 < nsize; i2++) {
            System.out.println("Real forward FFT 3D (input 3D) of size " + sizes3D[i2] + " x " + sizes3D[i2] + " x " + sizes3D[i2]);
            if (doWarmup) {
                long j = sizes3D[i2];
                DoubleFFT_3D doubleFFT_3D = new DoubleFFT_3D(j, j, j);
                long j2 = sizes3D[i2];
                double[][][] dArr3 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, (int) j2, (int) j2, ((int) j2) * 2);
                long j3 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j3, j3, j3, dArr3);
                doubleFFT_3D.realForwardFull(dArr3);
                long j4 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j4, j4, j4, dArr3);
                doubleFFT_3D.realForwardFull(dArr3);
            }
            long jNanoTime = System.nanoTime();
            long j5 = sizes3D[i2];
            DoubleFFT_3D doubleFFT_3D2 = new DoubleFFT_3D(j5, j5, j5);
            dArr2[i2] = (System.nanoTime() - jNanoTime) / 1000000.0d;
            long j6 = sizes3D[i2];
            double[][][] dArr4 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, (int) j6, (int) j6, ((int) j6) * 2);
            double d = Double.MAX_VALUE;
            for (int i3 = 0; i3 < niter; i3++) {
                long j7 = sizes3D[i2];
                IOUtils.fillMatrix_3D(j7, j7, j7, dArr4);
                long jNanoTime2 = System.nanoTime();
                doubleFFT_3D2.realForwardFull(dArr4);
                double dNanoTime = System.nanoTime() - jNanoTime2;
                if (dNanoTime < d) {
                    d = dNanoTime;
                }
            }
            double d2 = d / 1000000.0d;
            dArr[i2] = d2;
            dArr2[i2] = dArr2[i2] + d2;
            System.out.println("\tBest execution time without constructor: " + String.format("%.2f", Double.valueOf(dArr[i2])) + " msec");
            System.out.println("\tBest execution time with constructor: " + String.format("%.2f", Double.valueOf(dArr2[i2])) + " msec");
            System.gc();
            CommonUtils.sleep(VerisenseMessage.TIMEOUT_MS.ALL_TEST_TIMEOUT);
        }
        IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleRealForwardFFT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, dArr, dArr2);
    }

    public static void main(String[] strArr) throws InterruptedException, NumberFormatException, IOException {
        parseArguments(strArr);
        benchmarkComplexForward_1D();
        benchmarkRealForward_1D();
        benchmarkComplexForward_2D_input_1D();
        benchmarkComplexForward_2D_input_2D();
        benchmarkRealForward_2D_input_1D();
        benchmarkRealForward_2D_input_2D();
        benchmarkComplexForward_3D_input_1D();
        benchmarkComplexForward_3D_input_3D();
        benchmarkRealForward_3D_input_1D();
        benchmarkRealForward_3D_input_3D();
        System.exit(0);
    }
}
