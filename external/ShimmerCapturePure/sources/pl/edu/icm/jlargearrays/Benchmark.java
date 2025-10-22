package pl.edu.icm.jlargearrays;

import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes2.dex */
public class Benchmark {
    private static void writeToFile(long[] jArr, int[] iArr, double[][] dArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            bufferedWriter.write(System.getProperty("os.name") + StringUtils.SPACE + System.getProperty("os.arch") + StringUtils.SPACE + System.getProperty("os.version"));
            bufferedWriter.newLine();
            StringBuilder sb = new StringBuilder();
            sb.append(System.getProperty("java.vendor"));
            sb.append(StringUtils.SPACE);
            sb.append(System.getProperty("java.version"));
            bufferedWriter.write(sb.toString());
            bufferedWriter.newLine();
            bufferedWriter.write("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
            bufferedWriter.newLine();
            bufferedWriter.write("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
            bufferedWriter.newLine();
            bufferedWriter.write("Number of threads: {");
            for (int i = 0; i < iArr.length; i++) {
                if (i < iArr.length - 1) {
                    bufferedWriter.write(iArr[i] + UtilVerisenseDriver.CSV_DELIMITER);
                } else {
                    bufferedWriter.write(iArr[iArr.length - 1] + VectorFormat.DEFAULT_SUFFIX);
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("Sizes: {");
            for (int i2 = 0; i2 < jArr.length; i2++) {
                if (i2 < jArr.length - 1) {
                    bufferedWriter.write(jArr[i2] + UtilVerisenseDriver.CSV_DELIMITER);
                } else {
                    bufferedWriter.write(jArr[jArr.length - 1] + VectorFormat.DEFAULT_SUFFIX);
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("Timings: {");
            for (int i3 = 0; i3 < iArr.length; i3++) {
                bufferedWriter.write(VectorFormat.DEFAULT_PREFIX);
                if (i3 < iArr.length - 1) {
                    for (int i4 = 0; i4 < jArr.length; i4++) {
                        if (i4 < jArr.length - 1) {
                            bufferedWriter.write(dArr[i3][i4] + UtilVerisenseDriver.CSV_DELIMITER);
                        } else {
                            bufferedWriter.write(dArr[i3][i4] + "},");
                        }
                    }
                    bufferedWriter.newLine();
                } else {
                    for (int i5 = 0; i5 < jArr.length; i5++) {
                        if (i5 < jArr.length - 1) {
                            bufferedWriter.write(dArr[i3][i5] + UtilVerisenseDriver.CSV_DELIMITER);
                        } else {
                            bufferedWriter.write(dArr[i3][i5] + "}}");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[][] benchmarkJavaArraysByteSequential(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        Object obj;
        int i2;
        long j;
        int i3 = 0;
        while (true) {
            Object obj2 = null;
            if (i3 >= jArr.length) {
                double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
                System.out.println("Benchmarking java arrays (bytes, sequentual)");
                int i4 = 0;
                while (i4 < iArr.length) {
                    int i5 = iArr[i4];
                    Thread[] threadArr = new Thread[i5];
                    System.out.println("\tNumber of threads = " + i5);
                    int i6 = 0;
                    while (i6 < jArr.length) {
                        System.out.print("\tSize = " + jArr[i6]);
                        final byte[] bArr = new byte[(int) jArr[i6]];
                        double dNanoTime = (double) System.nanoTime();
                        int i7 = 0;
                        while (i7 < i) {
                            double[][] dArr2 = dArr;
                            long j2 = jArr[i6] / i5;
                            int i8 = 0;
                            while (i8 < i5) {
                                final int i9 = (int) (i8 * j2);
                                if (i8 == i5 - 1) {
                                    i2 = i4;
                                    j = jArr[i6];
                                } else {
                                    i2 = i4;
                                    j = i9 + j2;
                                }
                                final int i10 = (int) j;
                                Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        for (int i11 = i9; i11 < i10; i11++) {
                                            byte[] bArr2 = bArr;
                                            bArr2[i11] = 1;
                                            bArr2[i11] = (byte) (1 + 1);
                                        }
                                    }
                                });
                                threadArr[i8] = thread;
                                thread.start();
                                i8++;
                                i4 = i2;
                            }
                            int i11 = i4;
                            for (int i12 = 0; i12 < i5; i12++) {
                                try {
                                    threadArr[i12].join();
                                    obj = null;
                                } catch (Exception e) {
                                    e = e;
                                    obj = null;
                                }
                                try {
                                    threadArr[i12] = null;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i7++;
                                    obj2 = obj;
                                    dArr = dArr2;
                                    i4 = i11;
                                }
                            }
                            obj = null;
                            i7++;
                            obj2 = obj;
                            dArr = dArr2;
                            i4 = i11;
                        }
                        double[][] dArr3 = dArr;
                        int i13 = i4;
                        dArr3[i13][i6] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                        System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr3[i13][i6])));
                        i6++;
                        obj2 = obj2;
                        dArr = dArr3;
                        i4 = i13;
                    }
                    i4++;
                }
                double[][] dArr4 = dArr;
                writeToFile(jArr, iArr, dArr4, str);
                return dArr4;
            }
            if (jArr[i3] > 2147483643) {
                return null;
            }
            i3++;
        }
    }

    public static double[][] benchmarkJavaArraysDoubleSequential(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        Object obj;
        int i2;
        long j;
        int i3 = 0;
        while (true) {
            Object obj2 = null;
            if (i3 >= jArr.length) {
                double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
                System.out.println("Benchmarking java arrays (doubles, sequentual)");
                int i4 = 0;
                while (i4 < iArr.length) {
                    int i5 = iArr[i4];
                    Thread[] threadArr = new Thread[i5];
                    System.out.println("\tNumber of threads = " + i5);
                    int i6 = 0;
                    while (i6 < jArr.length) {
                        System.out.print("\tSize = " + jArr[i6]);
                        final double[] dArr2 = new double[(int) jArr[i6]];
                        double dNanoTime = (double) System.nanoTime();
                        int i7 = 0;
                        while (i7 < i) {
                            double[][] dArr3 = dArr;
                            long j2 = jArr[i6] / i5;
                            int i8 = 0;
                            while (i8 < i5) {
                                final int i9 = (int) (i8 * j2);
                                if (i8 == i5 - 1) {
                                    i2 = i4;
                                    j = jArr[i6];
                                } else {
                                    i2 = i4;
                                    j = i9 + j2;
                                }
                                final int i10 = (int) j;
                                Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        for (int i11 = i9; i11 < i10; i11++) {
                                            double[] dArr4 = dArr2;
                                            dArr4[i11] = 1.0d;
                                            dArr4[i11] = 1.0d + 1.0d;
                                        }
                                    }
                                });
                                threadArr[i8] = thread;
                                thread.start();
                                i8++;
                                i4 = i2;
                            }
                            int i11 = i4;
                            for (int i12 = 0; i12 < i5; i12++) {
                                try {
                                    threadArr[i12].join();
                                    obj = null;
                                } catch (Exception e) {
                                    e = e;
                                    obj = null;
                                }
                                try {
                                    threadArr[i12] = null;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i7++;
                                    obj2 = obj;
                                    dArr = dArr3;
                                    i4 = i11;
                                }
                            }
                            obj = null;
                            i7++;
                            obj2 = obj;
                            dArr = dArr3;
                            i4 = i11;
                        }
                        double[][] dArr4 = dArr;
                        int i13 = i4;
                        dArr4[i13][i6] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                        System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr4[i13][i6])));
                        i6++;
                        obj2 = obj2;
                        dArr = dArr4;
                        i4 = i13;
                    }
                    i4++;
                }
                double[][] dArr5 = dArr;
                writeToFile(jArr, iArr, dArr5, str);
                return dArr5;
            }
            if (jArr[i3] > 2147483643) {
                return null;
            }
            i3++;
        }
    }

    public static double[][] benchmarkJavaArraysByteRandom(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        byte[] bArr;
        int i2;
        long j;
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i3 = 0;
        while (true) {
            Object obj = null;
            if (i3 >= jArr2.length) {
                int[] iArr3 = new int[(int) jArr2[jArr2.length - 1]];
                double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr2.length, jArr2.length);
                Random random = new Random(0L);
                System.out.println("generating random indices.");
                int i4 = (int) jArr2[jArr2.length - 1];
                for (int i5 = 0; i5 < i4; i5++) {
                    iArr3[i5] = (int) (random.nextDouble() * (i4 - 1));
                }
                System.out.println("Benchmarking java arrays (bytes, random)");
                int i6 = 0;
                while (i6 < iArr2.length) {
                    int i7 = iArr2[i6];
                    Thread[] threadArr = new Thread[i7];
                    System.out.println("\tNumber of threads = " + i7);
                    int i8 = 0;
                    while (i8 < jArr2.length) {
                        System.out.print("\tSize = " + jArr2[i8]);
                        long j2 = jArr2[i8];
                        byte[] bArr2 = new byte[(int) j2];
                        double dNanoTime = System.nanoTime();
                        int i9 = 0;
                        while (i9 < i) {
                            final long j3 = j2;
                            byte[] bArr3 = bArr2;
                            long j4 = jArr2[i8] / i7;
                            int i10 = 0;
                            while (i10 < i7) {
                                final int i11 = (int) (i10 * j4);
                                if (i10 == i7 - 1) {
                                    bArr = bArr3;
                                    i2 = i8;
                                    j = j3;
                                } else {
                                    bArr = bArr3;
                                    i2 = i8;
                                    j = i11 + j4;
                                }
                                final int i12 = (int) j;
                                final byte[] bArr4 = bArr;
                                final int[] iArr4 = iArr3;
                                Thread[] threadArr2 = threadArr;
                                Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        for (int i13 = i11; i13 < i12; i13++) {
                                            int i14 = (int) (iArr4[i13] % j3);
                                            byte[] bArr5 = bArr4;
                                            bArr5[i14] = 1;
                                            bArr5[i14] = (byte) (1 + 1);
                                        }
                                    }
                                });
                                threadArr2[i10] = thread;
                                thread.start();
                                i10++;
                                i7 = i7;
                                i8 = i2;
                                bArr3 = bArr4;
                                iArr3 = iArr3;
                                threadArr = threadArr2;
                                i6 = i6;
                            }
                            byte[] bArr5 = bArr3;
                            int i13 = i8;
                            Thread[] threadArr3 = threadArr;
                            int i14 = i6;
                            int[] iArr5 = iArr3;
                            int i15 = i7;
                            for (int i16 = 0; i16 < i15; i16++) {
                                try {
                                    threadArr3[i16].join();
                                } catch (Exception e) {
                                    e = e;
                                }
                                try {
                                    threadArr3[i16] = null;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i9++;
                                    jArr2 = jArr;
                                    i7 = i15;
                                    j2 = j3;
                                    i8 = i13;
                                    bArr2 = bArr5;
                                    iArr3 = iArr5;
                                    threadArr = threadArr3;
                                    i6 = i14;
                                }
                            }
                            i9++;
                            jArr2 = jArr;
                            i7 = i15;
                            j2 = j3;
                            i8 = i13;
                            bArr2 = bArr5;
                            iArr3 = iArr5;
                            threadArr = threadArr3;
                            i6 = i14;
                        }
                        int i17 = i8;
                        int i18 = i6;
                        dArr[i18][i17] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                        System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr[i18][i17])));
                        i8 = i17 + 1;
                        obj = null;
                        iArr3 = iArr3;
                        jArr2 = jArr;
                    }
                    i6++;
                    iArr2 = iArr;
                    jArr2 = jArr;
                }
                writeToFile(jArr2, iArr2, dArr, str);
                return dArr;
            }
            if (jArr2[i3] > 2147483643) {
                return null;
            }
            i3++;
        }
    }

    public static double[][] benchmarkJavaArraysDoubleRandom(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        double[] dArr;
        int i2;
        long j;
        long[] jArr2 = jArr;
        int[] iArr2 = iArr;
        int i3 = 0;
        while (true) {
            Object obj = null;
            if (i3 >= jArr2.length) {
                int[] iArr3 = new int[(int) jArr2[jArr2.length - 1]];
                double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr2.length, jArr2.length);
                Random random = new Random(0L);
                System.out.println("generating random indices.");
                int i4 = (int) jArr2[jArr2.length - 1];
                for (int i5 = 0; i5 < i4; i5++) {
                    iArr3[i5] = (int) (random.nextDouble() * (i4 - 1));
                }
                System.out.println("Benchmarking java arrays (double, random)");
                int i6 = 0;
                while (i6 < iArr2.length) {
                    int i7 = iArr2[i6];
                    Thread[] threadArr = new Thread[i7];
                    System.out.println("\tNumber of threads = " + i7);
                    int i8 = 0;
                    while (i8 < jArr2.length) {
                        System.out.print("\tSize = " + jArr2[i8]);
                        long j2 = jArr2[i8];
                        double[] dArr3 = new double[(int) j2];
                        double dNanoTime = System.nanoTime();
                        int i9 = 0;
                        while (i9 < i) {
                            final long j3 = j2;
                            double[] dArr4 = dArr3;
                            long j4 = jArr2[i8] / i7;
                            int i10 = 0;
                            while (i10 < i7) {
                                final int i11 = (int) (i10 * j4);
                                if (i10 == i7 - 1) {
                                    dArr = dArr4;
                                    i2 = i8;
                                    j = j3;
                                } else {
                                    dArr = dArr4;
                                    i2 = i8;
                                    j = i11 + j4;
                                }
                                final int i12 = (int) j;
                                final double[] dArr5 = dArr;
                                final int[] iArr4 = iArr3;
                                Thread[] threadArr2 = threadArr;
                                Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.4
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        for (int i13 = i11; i13 < i12; i13++) {
                                            int i14 = (int) (iArr4[i13] % j3);
                                            double[] dArr6 = dArr5;
                                            dArr6[i14] = 1.0d;
                                            dArr6[i14] = 1.0d + 1.0d;
                                        }
                                    }
                                });
                                threadArr2[i10] = thread;
                                thread.start();
                                i10++;
                                i7 = i7;
                                i8 = i2;
                                dArr4 = dArr5;
                                iArr3 = iArr3;
                                threadArr = threadArr2;
                                i6 = i6;
                            }
                            double[] dArr6 = dArr4;
                            int i13 = i8;
                            Thread[] threadArr3 = threadArr;
                            int i14 = i6;
                            int[] iArr5 = iArr3;
                            int i15 = i7;
                            for (int i16 = 0; i16 < i15; i16++) {
                                try {
                                    threadArr3[i16].join();
                                } catch (Exception e) {
                                    e = e;
                                }
                                try {
                                    threadArr3[i16] = null;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    i9++;
                                    jArr2 = jArr;
                                    i7 = i15;
                                    j2 = j3;
                                    i8 = i13;
                                    dArr3 = dArr6;
                                    iArr3 = iArr5;
                                    threadArr = threadArr3;
                                    i6 = i14;
                                }
                            }
                            i9++;
                            jArr2 = jArr;
                            i7 = i15;
                            j2 = j3;
                            i8 = i13;
                            dArr3 = dArr6;
                            iArr3 = iArr5;
                            threadArr = threadArr3;
                            i6 = i14;
                        }
                        int i17 = i8;
                        int i18 = i6;
                        dArr2[i18][i17] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                        System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr2[i18][i17])));
                        i8 = i17 + 1;
                        obj = null;
                        iArr3 = iArr3;
                        jArr2 = jArr;
                    }
                    i6++;
                    iArr2 = iArr;
                    jArr2 = jArr;
                }
                writeToFile(jArr2, iArr2, dArr2, str);
                return dArr2;
            }
            if (jArr2[i3] > 2147483643) {
                return null;
            }
            i3++;
        }
    }

    public static double[][] benchmarkJLargeArraysByteSequentual(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        System.out.println("Benchmarking JLargeArrays (bytes, sequentual)");
        int i2 = 0;
        while (i2 < iArr.length) {
            int i3 = iArr[i2];
            Thread[] threadArr = new Thread[i3];
            System.out.println("\tNumber of threads = " + i3);
            int i4 = 0;
            while (i4 < jArr.length) {
                System.out.print("\tSize = " + jArr[i4]);
                final ByteLargeArray byteLargeArray = new ByteLargeArray(jArr[i4]);
                double dNanoTime = (double) System.nanoTime();
                int i5 = 0;
                while (i5 < i) {
                    int i6 = i2;
                    long j = jArr[i4] / i3;
                    int i7 = 0;
                    while (i7 < i3) {
                        int i8 = i5;
                        final long j2 = i7 * j;
                        final long j3 = i7 == i3 + (-1) ? jArr[i4] : j2 + j;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.5
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j4 = j2; j4 < j3; j4++) {
                                    byteLargeArray.setByte(j4, (byte) 1);
                                    ByteLargeArray byteLargeArray2 = byteLargeArray;
                                    byteLargeArray2.setByte(j4, (byte) (byteLargeArray2.getByte(j4) + 1));
                                }
                            }
                        });
                        threadArr[i7] = thread;
                        thread.start();
                        i7++;
                        i5 = i8;
                        j = j;
                        dNanoTime = dNanoTime;
                    }
                    int i9 = i5;
                    double d = dNanoTime;
                    for (int i10 = 0; i10 < i3; i10++) {
                        try {
                            threadArr[i10].join();
                            threadArr[i10] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i5 = i9 + 1;
                    i2 = i6;
                    dNanoTime = d;
                }
                int i11 = i2;
                dArr[i11][i4] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr[i11][i4])));
                i4++;
                i2 = i11;
            }
            i2++;
        }
        writeToFile(jArr, iArr, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysByteSequentualNative(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        LargeArray.setMaxSizeOf32bitArray(1);
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        System.out.println("Benchmarking JLargeArrays (bytes, sequentual, native)");
        int i2 = 0;
        while (i2 < iArr.length) {
            int i3 = iArr[i2];
            Thread[] threadArr = new Thread[i3];
            System.out.println("\tNumber of threads = " + i3);
            int i4 = 0;
            while (i4 < jArr.length) {
                System.out.print("\tSize = " + jArr[i4]);
                final ByteLargeArray byteLargeArray = new ByteLargeArray(jArr[i4]);
                double dNanoTime = (double) System.nanoTime();
                int i5 = 0;
                while (i5 < i) {
                    double[][] dArr2 = dArr;
                    long j = jArr[i4] / i3;
                    int i6 = 0;
                    while (i6 < i3) {
                        int i7 = i2;
                        final long j2 = i6 * j;
                        long j3 = i6 == i3 + (-1) ? jArr[i4] : j2 + j;
                        int i8 = i5;
                        final long j4 = j3;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.6
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j5 = j2; j5 < j4; j5++) {
                                    byteLargeArray.setToNative(j5, (byte) 1);
                                    ByteLargeArray byteLargeArray2 = byteLargeArray;
                                    byteLargeArray2.setToNative(j5, Byte.valueOf((byte) (byteLargeArray2.getFromNative(j5).byteValue() + 1)));
                                }
                            }
                        });
                        threadArr[i6] = thread;
                        thread.start();
                        i6++;
                        i5 = i8;
                        i2 = i7;
                        j = j;
                        dNanoTime = dNanoTime;
                    }
                    int i9 = i2;
                    double d = dNanoTime;
                    int i10 = i5;
                    for (int i11 = 0; i11 < i3; i11++) {
                        try {
                            threadArr[i11].join();
                            threadArr[i11] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i5 = i10 + 1;
                    dArr = dArr2;
                    i2 = i9;
                    dNanoTime = d;
                }
                double[][] dArr3 = dArr;
                int i12 = i2;
                dArr3[i12][i4] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr3[i12][i4])));
                i4++;
                dArr = dArr3;
                i2 = i12;
            }
            i2++;
            dArr = dArr;
        }
        double[][] dArr4 = dArr;
        writeToFile(jArr, iArr, dArr4, str);
        return dArr4;
    }

    public static double[][] benchmarkJLargeArraysByteSequentual_safe(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        System.out.println("Benchmarking JLargeArrays (bytes, sequentual, with bounds checking)");
        int i2 = 0;
        while (i2 < iArr.length) {
            int i3 = iArr[i2];
            Thread[] threadArr = new Thread[i3];
            System.out.println("\tNumber of threads = " + i3);
            int i4 = 0;
            while (i4 < jArr.length) {
                System.out.print("\tSize = " + jArr[i4]);
                final ByteLargeArray byteLargeArray = new ByteLargeArray(jArr[i4]);
                double dNanoTime = (double) System.nanoTime();
                int i5 = 0;
                while (i5 < i) {
                    int i6 = i2;
                    long j = jArr[i4] / i3;
                    int i7 = 0;
                    while (i7 < i3) {
                        int i8 = i5;
                        final long j2 = i7 * j;
                        final long j3 = i7 == i3 + (-1) ? jArr[i4] : j2 + j;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.7
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j4 = j2; j4 < j3; j4++) {
                                    byteLargeArray.setByte_safe(j4, (byte) 1);
                                    ByteLargeArray byteLargeArray2 = byteLargeArray;
                                    byteLargeArray2.setByte_safe(j4, (byte) (byteLargeArray2.getByte_safe(j4) + 1));
                                }
                            }
                        });
                        threadArr[i7] = thread;
                        thread.start();
                        i7++;
                        i5 = i8;
                        j = j;
                        dNanoTime = dNanoTime;
                    }
                    int i9 = i5;
                    double d = dNanoTime;
                    for (int i10 = 0; i10 < i3; i10++) {
                        try {
                            threadArr[i10].join();
                            threadArr[i10] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i5 = i9 + 1;
                    i2 = i6;
                    dNanoTime = d;
                }
                int i11 = i2;
                dArr[i11][i4] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr[i11][i4])));
                i4++;
                i2 = i11;
            }
            i2++;
        }
        writeToFile(jArr, iArr, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysDoubleSequentual(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        System.out.println("Benchmarking JLargeArrays (doubles, sequentual)");
        int i2 = 0;
        while (i2 < iArr.length) {
            int i3 = iArr[i2];
            Thread[] threadArr = new Thread[i3];
            System.out.println("\tNumber of threads = " + i3);
            int i4 = 0;
            while (i4 < jArr.length) {
                System.out.print("\tSize = " + jArr[i4]);
                final DoubleLargeArray doubleLargeArray = new DoubleLargeArray(jArr[i4]);
                double dNanoTime = (double) System.nanoTime();
                int i5 = 0;
                while (i5 < i) {
                    int i6 = i2;
                    long j = jArr[i4] / i3;
                    int i7 = 0;
                    while (i7 < i3) {
                        int i8 = i5;
                        final long j2 = i7 * j;
                        final long j3 = i7 == i3 + (-1) ? jArr[i4] : j2 + j;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.8
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j4 = j2; j4 < j3; j4++) {
                                    doubleLargeArray.setDouble(j4, 1.0d);
                                    DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                                    doubleLargeArray2.setDouble(j4, doubleLargeArray2.getDouble(j4) + 1.0d);
                                }
                            }
                        });
                        threadArr[i7] = thread;
                        thread.start();
                        i7++;
                        i5 = i8;
                        j = j;
                        dNanoTime = dNanoTime;
                    }
                    int i9 = i5;
                    double d = dNanoTime;
                    for (int i10 = 0; i10 < i3; i10++) {
                        try {
                            threadArr[i10].join();
                            threadArr[i10] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i5 = i9 + 1;
                    i2 = i6;
                    dNanoTime = d;
                }
                int i11 = i2;
                dArr[i11][i4] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr[i11][i4])));
                i4++;
                i2 = i11;
            }
            i2++;
        }
        writeToFile(jArr, iArr, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysDoubleSequentualNative(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        LargeArray.setMaxSizeOf32bitArray(1);
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        System.out.println("Benchmarking JLargeArrays (doubles, sequentual, native)");
        int i2 = 0;
        while (i2 < iArr.length) {
            int i3 = iArr[i2];
            Thread[] threadArr = new Thread[i3];
            System.out.println("\tNumber of threads = " + i3);
            int i4 = 0;
            while (i4 < jArr.length) {
                System.out.print("\tSize = " + jArr[i4]);
                final DoubleLargeArray doubleLargeArray = new DoubleLargeArray(jArr[i4]);
                double dNanoTime = (double) System.nanoTime();
                int i5 = 0;
                while (i5 < i) {
                    double[][] dArr2 = dArr;
                    long j = jArr[i4] / i3;
                    int i6 = 0;
                    while (i6 < i3) {
                        int i7 = i2;
                        final long j2 = i6 * j;
                        long j3 = i6 == i3 + (-1) ? jArr[i4] : j2 + j;
                        int i8 = i5;
                        final long j4 = j3;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.9
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j5 = j2; j5 < j4; j5++) {
                                    doubleLargeArray.setToNative(j5, Double.valueOf(1.0d));
                                    DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                                    doubleLargeArray2.setToNative(j5, Double.valueOf(doubleLargeArray2.getFromNative(j5).doubleValue() + 1.0d));
                                }
                            }
                        });
                        threadArr[i6] = thread;
                        thread.start();
                        i6++;
                        i5 = i8;
                        i2 = i7;
                        j = j;
                        dNanoTime = dNanoTime;
                    }
                    int i9 = i2;
                    double d = dNanoTime;
                    int i10 = i5;
                    for (int i11 = 0; i11 < i3; i11++) {
                        try {
                            threadArr[i11].join();
                            threadArr[i11] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i5 = i10 + 1;
                    dArr = dArr2;
                    i2 = i9;
                    dNanoTime = d;
                }
                double[][] dArr3 = dArr;
                int i12 = i2;
                dArr3[i12][i4] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr3[i12][i4])));
                i4++;
                dArr = dArr3;
                i2 = i12;
            }
            i2++;
            dArr = dArr;
        }
        double[][] dArr4 = dArr;
        writeToFile(jArr, iArr, dArr4, str);
        return dArr4;
    }

    public static double[][] benchmarkJLargeArraysByteRandom(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        final int[] iArr2 = new int[(int) jArr[jArr.length - 1]];
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        Random random = new Random(0L);
        System.out.println("generating random indices.");
        int i2 = (int) jArr[jArr.length - 1];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr2[i3] = (int) (random.nextDouble() * (i2 - 1));
        }
        System.out.println("Benchmarking JLargeArrays (bytes, random)");
        int i4 = 0;
        while (i4 < iArr.length) {
            int i5 = iArr[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = 0;
            while (i6 < jArr.length) {
                System.out.print("\tSize = " + jArr[i6]);
                ByteLargeArray byteLargeArray = new ByteLargeArray(jArr[i6]);
                int i7 = (int) jArr[i6];
                double dNanoTime = System.nanoTime();
                int i8 = 0;
                while (i8 < i) {
                    double d = dNanoTime;
                    long j = jArr[i6] / i5;
                    int i9 = 0;
                    while (i9 < i5) {
                        final long j2 = i9 * j;
                        final long j3 = i9 == i5 + (-1) ? jArr[i6] : j2 + j;
                        final int i10 = i7;
                        final ByteLargeArray byteLargeArray2 = byteLargeArray;
                        Thread[] threadArr2 = threadArr;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.10
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j4 = j2; j4 < j3; j4++) {
                                    long j5 = iArr2[(int) j4] % i10;
                                    byteLargeArray2.setByte(j5, (byte) 1);
                                    ByteLargeArray byteLargeArray3 = byteLargeArray2;
                                    byteLargeArray3.setByte(j5, (byte) (byteLargeArray3.getByte(j5) + 1));
                                }
                            }
                        });
                        threadArr2[i9] = thread;
                        thread.start();
                        i9++;
                        i5 = i5;
                        i6 = i6;
                        byteLargeArray = byteLargeArray2;
                        threadArr = threadArr2;
                        i7 = i10;
                        i8 = i8;
                        i4 = i4;
                    }
                    int i11 = i7;
                    int i12 = i8;
                    int i13 = i6;
                    ByteLargeArray byteLargeArray3 = byteLargeArray;
                    Thread[] threadArr3 = threadArr;
                    int i14 = i5;
                    int i15 = i4;
                    for (int i16 = 0; i16 < i14; i16++) {
                        try {
                            threadArr3[i16].join();
                            threadArr3[i16] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i8 = i12 + 1;
                    i5 = i14;
                    dNanoTime = d;
                    i6 = i13;
                    byteLargeArray = byteLargeArray3;
                    threadArr = threadArr3;
                    i7 = i11;
                    i4 = i15;
                }
                int i17 = i6;
                int i18 = i4;
                dArr[i18][i17] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr[i18][i17])));
                i6 = i17 + 1;
            }
            i4++;
        }
        writeToFile(jArr, iArr, dArr, str);
        return dArr;
    }

    public static double[][] benchmarkJLargeArraysDoubleRandom(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        final int[] iArr2 = new int[(int) jArr[jArr.length - 1]];
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr.length, jArr.length);
        Random random = new Random(0L);
        System.out.println("generating random indices.");
        int i2 = (int) jArr[jArr.length - 1];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr2[i3] = (int) (random.nextDouble() * (i2 - 1));
        }
        System.out.println("Benchmarking JLargeArrays (doubles, random)");
        int i4 = 0;
        while (i4 < iArr.length) {
            int i5 = iArr[i4];
            Thread[] threadArr = new Thread[i5];
            System.out.println("\tNumber of threads = " + i5);
            int i6 = 0;
            while (i6 < jArr.length) {
                System.out.print("\tSize = " + jArr[i6]);
                DoubleLargeArray doubleLargeArray = new DoubleLargeArray(jArr[i6]);
                int i7 = (int) jArr[i6];
                double dNanoTime = System.nanoTime();
                int i8 = 0;
                while (i8 < i) {
                    double d = dNanoTime;
                    long j = jArr[i6] / i5;
                    int i9 = 0;
                    while (i9 < i5) {
                        final long j2 = i9 * j;
                        final long j3 = i9 == i5 + (-1) ? jArr[i6] : j2 + j;
                        final int i10 = i7;
                        final DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
                        Thread[] threadArr2 = threadArr;
                        Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.11
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j4 = j2; j4 < j3; j4++) {
                                    long j5 = iArr2[(int) j4] % i10;
                                    doubleLargeArray2.setDouble(j5, 1.0d);
                                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray2;
                                    doubleLargeArray3.setDouble(j5, doubleLargeArray3.getDouble(j5) + 1.0d);
                                }
                            }
                        });
                        threadArr2[i9] = thread;
                        thread.start();
                        i9++;
                        i5 = i5;
                        i6 = i6;
                        doubleLargeArray = doubleLargeArray2;
                        threadArr = threadArr2;
                        i7 = i10;
                        i8 = i8;
                        i4 = i4;
                    }
                    int i11 = i7;
                    int i12 = i8;
                    int i13 = i6;
                    DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                    Thread[] threadArr3 = threadArr;
                    int i14 = i5;
                    int i15 = i4;
                    for (int i16 = 0; i16 < i14; i16++) {
                        try {
                            threadArr3[i16].join();
                            threadArr3[i16] = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i8 = i12 + 1;
                    i5 = i14;
                    dNanoTime = d;
                    i6 = i13;
                    doubleLargeArray = doubleLargeArray3;
                    threadArr = threadArr3;
                    i7 = i11;
                    i4 = i15;
                }
                int i17 = i6;
                int i18 = i4;
                dArr[i18][i17] = ((System.nanoTime() - dNanoTime) / 1.0E9d) / i;
                System.out.println(" : " + String.format("%.7f sec", Double.valueOf(dArr[i18][i17])));
                i6 = i17 + 1;
            }
            i4++;
        }
        writeToFile(jArr, iArr, dArr, str);
        return dArr;
    }

    public static void benchmarkByteSequential(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        benchmarkJavaArraysDoubleSequential(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_byte_sequential.txt");
        System.gc();
        benchmarkJLargeArraysByteSequentual(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_byte_sequentual.txt");
    }

    public static void benchmarkDoubleSequential(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        benchmarkJavaArraysDoubleSequential(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_double_sequential.txt");
        System.gc();
        benchmarkJLargeArraysDoubleSequentual(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_double_sequentual.txt");
    }

    public static void benchmarkByteRandom(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        benchmarkJavaArraysByteRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_byte_random.txt");
        System.gc();
        benchmarkJLargeArraysByteRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_byte_random.txt");
    }

    public static void benchmarkDoubleRandom(long[] jArr, int[] iArr, int i, String str) throws InterruptedException, IOException {
        benchmarkJavaArraysDoubleRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "java_arrays_double_random.txt");
        System.gc();
        benchmarkJLargeArraysDoubleRandom(jArr, iArr, i, str + System.getProperty("file.separator") + "jlargearrays_double_random.txt");
    }

    public static void benchmarkByteLargeArray() {
        System.out.println("Benchmarking ByteLargeArray.");
        long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        ByteLargeArray byteLargeArray = new ByteLargeArray(jPow);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long jNanoTime2 = System.nanoTime();
            for (long j = 0; j < jPow; j++) {
                byteLargeArray.getByte(j);
                byteLargeArray.setByte(j, (byte) 1);
                byteLargeArray.setByte(j, (byte) (byteLargeArray.getByte(j) + 1));
            }
            System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + "sec");
        }
    }

    public static void benchmarkByteLargeArrayInANewThread() throws InterruptedException {
        System.out.println("Benchmarking ByteLargeArray in a new thread.");
        final long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        final ByteLargeArray byteLargeArray = new ByteLargeArray(jPow);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long jNanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.12
                @Override // java.lang.Runnable
                public void run() {
                    for (long j = 0; j < jPow; j++) {
                        byteLargeArray.setByte(j, (byte) 1);
                        ByteLargeArray byteLargeArray2 = byteLargeArray;
                        byteLargeArray2.setByte(j, (byte) (byteLargeArray2.getByte(j) + 1));
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkFloatLargeArray() {
        System.out.println("Benchmarking FloatLargeArray.");
        long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        FloatLargeArray floatLargeArray = new FloatLargeArray(jPow);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long jNanoTime2 = System.nanoTime();
            for (long j = 0; j < jPow; j++) {
                floatLargeArray.getFloat(j);
                floatLargeArray.setFloat(j, 1.0f);
                floatLargeArray.setFloat(j, floatLargeArray.getFloat(j) + 1.0f);
            }
            System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + "sec");
        }
    }

    public static void benchmarkFloatLargeArrayInANewThread() throws InterruptedException {
        System.out.println("Benchmarking FloatLargeArray in a new thread.");
        final long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        final FloatLargeArray floatLargeArray = new FloatLargeArray(jPow);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long jNanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.13
                @Override // java.lang.Runnable
                public void run() {
                    for (long j = 0; j < jPow; j++) {
                        floatLargeArray.setFloat(j, 1.0f);
                        FloatLargeArray floatLargeArray2 = floatLargeArray;
                        floatLargeArray2.setFloat(j, floatLargeArray2.getFloat(j) + 1.0f);
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkByteLargeArrayNative() {
        System.out.println("Benchmarking ByteLargeArray native.");
        long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        ByteLargeArray byteLargeArray = new ByteLargeArray(jPow, false);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        if (byteLargeArray.isLarge()) {
            for (int i = 0; i < 5; i++) {
                long jNanoTime2 = System.nanoTime();
                for (long j = 0; j < jPow; j++) {
                    byteLargeArray.getFromNative(j);
                    byteLargeArray.setToNative(j, (byte) 1);
                    byteLargeArray.setToNative(j, Byte.valueOf((byte) (byteLargeArray.getFromNative(j).byteValue() + 1)));
                }
                System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
            }
        }
    }

    public static void benchmarkByteLargeArrayNativeInANewThread() throws InterruptedException {
        System.out.println("Benchmarking ByteLargeArray native in a new thread.");
        final long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        final ByteLargeArray byteLargeArray = new ByteLargeArray(jPow);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long jNanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.14
                @Override // java.lang.Runnable
                public void run() {
                    for (long j = 0; j < jPow; j++) {
                        byteLargeArray.setToNative(j, (byte) 1);
                        ByteLargeArray byteLargeArray2 = byteLargeArray;
                        byteLargeArray2.setToNative(j, Byte.valueOf((byte) (byteLargeArray2.getFromNative(j).byteValue() + 1)));
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkFloatLargeArrayNative() {
        System.out.println("Benchmarking FloatLargeArray native.");
        long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        FloatLargeArray floatLargeArray = new FloatLargeArray(jPow, false);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        if (floatLargeArray.isLarge()) {
            for (int i = 0; i < 5; i++) {
                long jNanoTime2 = System.nanoTime();
                for (long j = 0; j < jPow; j++) {
                    floatLargeArray.getFromNative(j);
                    floatLargeArray.setToNative(j, Float.valueOf(1.0f));
                    floatLargeArray.setToNative(j, Float.valueOf(floatLargeArray.getFromNative(j).floatValue() + 1.0f));
                }
                System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
            }
        }
    }

    public static void benchmarkFloatLargeArrayNativeInANewThread() throws InterruptedException {
        System.out.println("Benchmarking FloatLargeArray native in a new thread.");
        final long jPow = (long) Math.pow(2.0d, 32.0d);
        long jNanoTime = System.nanoTime();
        final FloatLargeArray floatLargeArray = new FloatLargeArray(jPow);
        System.out.println("Constructor time: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
        for (int i = 0; i < 5; i++) {
            long jNanoTime2 = System.nanoTime();
            Thread thread = new Thread(new Runnable() { // from class: pl.edu.icm.jlargearrays.Benchmark.15
                @Override // java.lang.Runnable
                public void run() {
                    for (long j = 0; j < jPow; j++) {
                        floatLargeArray.setToNative(j, Float.valueOf(1.0f));
                        FloatLargeArray floatLargeArray2 = floatLargeArray;
                        floatLargeArray2.setToNative(j, Float.valueOf(floatLargeArray2.getFromNative(j).floatValue() + 1.0f));
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Computation time: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkArithmeticAdd() {
        System.out.println("Benchmarking addition of two ByteLargeArrays.");
        LargeArray.setMaxSizeOf32bitArray(1);
        long jPow = (long) Math.pow(2.0d, 27.0d);
        LargeArray largeArrayGenerateRandom = LargeArrayUtils.generateRandom(LargeArrayType.BYTE, jPow);
        LargeArray largeArrayGenerateRandom2 = LargeArrayUtils.generateRandom(LargeArrayType.BYTE, jPow);
        LargeArray largeArrayConvert = LargeArrayUtils.convert(largeArrayGenerateRandom, LargeArrayType.LONG);
        LargeArray largeArrayConvert2 = LargeArrayUtils.convert(largeArrayGenerateRandom2, LargeArrayType.LONG);
        int i = 1;
        while (true) {
            if (i > 16) {
                break;
            }
            ConcurrencyUtils.setNumberOfThreads(i);
            LargeArrayArithmetics.add(largeArrayGenerateRandom, largeArrayGenerateRandom2);
            LargeArrayArithmetics.add(largeArrayGenerateRandom, largeArrayGenerateRandom2);
            long jNanoTime = System.nanoTime();
            for (int i2 = 0; i2 < 5; i2++) {
                LargeArrayArithmetics.add(largeArrayGenerateRandom, largeArrayGenerateRandom2);
            }
            System.out.println("Average computation time using " + i + " threads: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
            i += 2;
        }
        System.out.println("Benchmarking addition of two LongLargeArrays.");
        for (int i3 = 1; i3 <= 16; i3 += 2) {
            ConcurrencyUtils.setNumberOfThreads(i3);
            LargeArrayArithmetics.add(largeArrayConvert, largeArrayConvert2);
            LargeArrayArithmetics.add(largeArrayConvert, largeArrayConvert2);
            long jNanoTime2 = System.nanoTime();
            for (int i4 = 0; i4 < 5; i4++) {
                LargeArrayArithmetics.add(largeArrayConvert, largeArrayConvert2);
            }
            System.out.println("Average computation time using " + i3 + " threads: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
        }
    }

    public static void benchmarkStatisticsAvg() {
        System.out.println("Benchmarking avgKahan (DoubleLargeArray of length = 2^28).");
        LargeArray.setMaxSizeOf32bitArray(1);
        LargeArray largeArrayGenerateRandom = LargeArrayUtils.generateRandom(LargeArrayType.DOUBLE, (long) Math.pow(2.0d, 28.0d));
        int i = 1;
        while (true) {
            if (i > 16) {
                break;
            }
            ConcurrencyUtils.setNumberOfThreads(i);
            LargeArrayStatistics.avgKahan(largeArrayGenerateRandom);
            LargeArrayStatistics.avgKahan(largeArrayGenerateRandom);
            long jNanoTime = System.nanoTime();
            for (int i2 = 0; i2 < 5; i2++) {
                LargeArrayStatistics.avgKahan(largeArrayGenerateRandom);
            }
            System.out.println("Average computation time using " + i + " threads: " + ((System.nanoTime() - jNanoTime) / 1.0E9d) + " sec");
            i++;
        }
        System.out.println("Benchmarking avg (DoubleLargeArray of length = 2^28).");
        LargeArray.setMaxSizeOf32bitArray(1);
        for (int i3 = 1; i3 <= 16; i3++) {
            ConcurrencyUtils.setNumberOfThreads(i3);
            LargeArrayStatistics.avg(largeArrayGenerateRandom);
            LargeArrayStatistics.avg(largeArrayGenerateRandom);
            long jNanoTime2 = System.nanoTime();
            for (int i4 = 0; i4 < 5; i4++) {
                LargeArrayStatistics.avg(largeArrayGenerateRandom);
            }
            System.out.println("Average computation time using " + i3 + " threads: " + ((System.nanoTime() - jNanoTime2) / 1.0E9d) + " sec");
        }
    }

    public static void main(String[] strArr) throws InterruptedException, IOException {
        long[] jArr = new long[6];
        for (int i = 0; i < 6; i++) {
            int i2 = 27 + i;
            if (i2 == 31) {
                jArr[i] = ((long) Math.pow(2.0d, 31.0d)) - 4;
            } else {
                jArr[i] = (long) Math.pow(2.0d, i2);
            }
        }
        long[] jArr2 = new long[4];
        for (int i3 = 0; i3 < 4; i3++) {
            jArr2[i3] = (long) Math.pow(2.0d, 32 + i3);
        }
        int[] iArr = {1, 2, 4, 8, 16};
        LargeArray.setMaxSizeOf32bitArray(1);
        benchmarkByteSequential(jArr, iArr, 10, "/tmp/");
        benchmarkDoubleSequential(jArr, iArr, 10, "/tmp/");
        benchmarkByteRandom(jArr, iArr, 10, "/tmp/");
        benchmarkDoubleRandom(jArr, iArr, 10, "/tmp/");
        System.exit(0);
    }
}
