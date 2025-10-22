package org.jtransforms.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

/* loaded from: classes2.dex */
public class IOUtils {
    private static final String FF = "%.4f";

    private IOUtils() {
    }

    public static double computeRMSE(float f, float f2) {
        double d = f - f2;
        return FastMath.sqrt(d * d);
    }

    public static double computeRMSE(float[] fArr, float[] fArr2) {
        if (fArr.length != fArr2.length) {
            throw new IllegalArgumentException("Arrays are not the same size");
        }
        double d = 0.0d;
        for (int i = 0; i < fArr.length; i++) {
            double d2 = fArr[i] - fArr2[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d / fArr.length);
    }

    public static double computeRMSE(FloatLargeArray floatLargeArray, FloatLargeArray floatLargeArray2) {
        if (floatLargeArray.length() != floatLargeArray2.length()) {
            throw new IllegalArgumentException("Arrays are not the same size.");
        }
        double d = 0.0d;
        for (long j = 0; j < floatLargeArray.length(); j++) {
            double d2 = floatLargeArray.getFloat(j) - floatLargeArray2.getFloat(j);
            d += d2 * d2;
        }
        return FastMath.sqrt(d / floatLargeArray.length());
    }

    public static double computeRMSE(float[][] fArr, float[][] fArr2) {
        if (fArr.length != fArr2.length || fArr[0].length != fArr2[0].length) {
            throw new IllegalArgumentException("Arrays are not the same size");
        }
        double d = 0.0d;
        for (int i = 0; i < fArr.length; i++) {
            for (int i2 = 0; i2 < fArr[0].length; i2++) {
                double d2 = fArr[i][i2] - fArr2[i][i2];
                d += d2 * d2;
            }
        }
        return FastMath.sqrt(d / (fArr.length * fArr[0].length));
    }

    public static double computeRMSE(float[][][] fArr, float[][][] fArr2) {
        if (fArr.length == fArr2.length) {
            float[][] fArr3 = fArr[0];
            int length = fArr3.length;
            float[][] fArr4 = fArr2[0];
            if (length == fArr4.length && fArr3[0].length == fArr4[0].length) {
                double d = 0.0d;
                for (int i = 0; i < fArr.length; i++) {
                    for (int i2 = 0; i2 < fArr[0].length; i2++) {
                        for (int i3 = 0; i3 < fArr[0][0].length; i3++) {
                            double d2 = fArr[i][i2][i3] - fArr2[i][i2][i3];
                            d += d2 * d2;
                        }
                    }
                }
                int length2 = fArr.length;
                float[][] fArr5 = fArr[0];
                return FastMath.sqrt(d / ((length2 * fArr5.length) * fArr5[0].length));
            }
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static double computeRMSE(double d, double d2) {
        double d3 = d - d2;
        return FastMath.sqrt(d3 * d3);
    }

    public static double computeRMSE(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new IllegalArgumentException("Arrays are not the same size");
        }
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i] - dArr2[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d / dArr.length);
    }

    public static double computeRMSE(DoubleLargeArray doubleLargeArray, DoubleLargeArray doubleLargeArray2) {
        if (doubleLargeArray.length() != doubleLargeArray2.length()) {
            throw new IllegalArgumentException("Arrays are not the same size.");
        }
        double d = 0.0d;
        for (long j = 0; j < doubleLargeArray.length(); j++) {
            double d2 = doubleLargeArray.getDouble(j) - doubleLargeArray2.getDouble(j);
            d += d2 * d2;
        }
        return FastMath.sqrt(d / doubleLargeArray.length());
    }

    public static double computeRMSE(double[][] dArr, double[][] dArr2) {
        if (dArr.length != dArr2.length || dArr[0].length != dArr2[0].length) {
            throw new IllegalArgumentException("Arrays are not the same size");
        }
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            for (int i2 = 0; i2 < dArr[0].length; i2++) {
                double d2 = dArr[i][i2] - dArr2[i][i2];
                d += d2 * d2;
            }
        }
        return FastMath.sqrt(d / (dArr.length * dArr[0].length));
    }

    public static double computeRMSE(double[][][] dArr, double[][][] dArr2) {
        if (dArr.length == dArr2.length) {
            double[][] dArr3 = dArr[0];
            int length = dArr3.length;
            double[][] dArr4 = dArr2[0];
            if (length == dArr4.length && dArr3[0].length == dArr4[0].length) {
                double d = 0.0d;
                for (int i = 0; i < dArr.length; i++) {
                    for (int i2 = 0; i2 < dArr[0].length; i2++) {
                        for (int i3 = 0; i3 < dArr[0][0].length; i3++) {
                            double d2 = dArr[i][i2][i3] - dArr2[i][i2][i3];
                            d += d2 * d2;
                        }
                    }
                }
                int length2 = dArr.length;
                double[][] dArr5 = dArr[0];
                return FastMath.sqrt(d / ((length2 * dArr5.length) * dArr5[0].length));
            }
        }
        throw new IllegalArgumentException("Arrays are not the same size");
    }

    public static void fillMatrix_1D(long j, double[] dArr) {
        Random random = new Random(2L);
        for (int i = 0; i < j; i++) {
            dArr[i] = random.nextDouble();
        }
    }

    public static void fillMatrix_1D(long j, DoubleLargeArray doubleLargeArray) {
        Random random = new Random(2L);
        for (long j2 = 0; j2 < j; j2++) {
            doubleLargeArray.setDouble(j2, random.nextDouble());
        }
    }

    public static void fillMatrix_1D(long j, FloatLargeArray floatLargeArray) {
        Random random = new Random(2L);
        for (long j2 = 0; j2 < j; j2++) {
            floatLargeArray.setDouble(j2, random.nextFloat());
        }
    }

    public static void fillMatrix_1D(long j, float[] fArr) {
        Random random = new Random(2L);
        for (int i = 0; i < j; i++) {
            fArr[i] = random.nextFloat();
        }
    }

    public static void fillMatrix_2D(long j, long j2, double[] dArr) {
        Random random = new Random(2L);
        int i = 0;
        while (true) {
            long j3 = i;
            if (j3 >= j) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j4 = i2;
                if (j4 < j2) {
                    dArr[(int) ((j3 * j2) + j4)] = random.nextDouble();
                    i2++;
                }
            }
            i++;
        }
    }

    public static void fillMatrix_2D(long j, long j2, FloatLargeArray floatLargeArray) {
        Random random = new Random(2L);
        for (long j3 = 0; j3 < j; j3++) {
            for (long j4 = 0; j4 < j2; j4++) {
                floatLargeArray.setFloat((j3 * j2) + j4, random.nextFloat());
            }
        }
    }

    public static void fillMatrix_2D(long j, long j2, DoubleLargeArray doubleLargeArray) {
        Random random = new Random(2L);
        for (long j3 = 0; j3 < j; j3++) {
            for (long j4 = 0; j4 < j2; j4++) {
                doubleLargeArray.setDouble((j3 * j2) + j4, random.nextDouble());
            }
        }
    }

    public static void fillMatrix_2D(long j, long j2, float[] fArr) {
        Random random = new Random(2L);
        int i = 0;
        while (true) {
            long j3 = i;
            if (j3 >= j) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j4 = i2;
                if (j4 < j2) {
                    fArr[(int) ((j3 * j2) + j4)] = random.nextFloat();
                    i2++;
                }
            }
            i++;
        }
    }

    public static void fillMatrix_2D(long j, long j2, double[][] dArr) {
        Random random = new Random(2L);
        for (int i = 0; i < j; i++) {
            for (int i2 = 0; i2 < j2; i2++) {
                dArr[i][i2] = random.nextDouble();
            }
        }
    }

    public static void fillMatrix_2D(long j, long j2, float[][] fArr) {
        Random random = new Random(2L);
        for (int i = 0; i < j; i++) {
            for (int i2 = 0; i2 < j2; i2++) {
                fArr[i][i2] = random.nextFloat();
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, double[] dArr) {
        Random random = new Random(2L);
        long j4 = j2 * j3;
        int i = 0;
        while (true) {
            long j5 = i;
            if (j5 >= j) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j6 = i2;
                if (j6 < j2) {
                    int i3 = 0;
                    while (true) {
                        long j7 = i3;
                        if (j7 < j3) {
                            dArr[(int) ((j5 * j4) + (j6 * j3) + j7)] = random.nextDouble();
                            i3++;
                        }
                    }
                    i2++;
                }
            }
            i++;
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, DoubleLargeArray doubleLargeArray) {
        Random random = new Random(2L);
        long j4 = j2 * j3;
        for (long j5 = 0; j5 < j; j5++) {
            for (long j6 = 0; j6 < j2; j6++) {
                for (long j7 = 0; j7 < j3; j7++) {
                    doubleLargeArray.setDouble((j5 * j4) + (j6 * j3) + j7, random.nextDouble());
                }
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, FloatLargeArray floatLargeArray) {
        Random random = new Random(2L);
        long j4 = j2 * j3;
        for (long j5 = 0; j5 < j; j5++) {
            for (long j6 = 0; j6 < j2; j6++) {
                for (long j7 = 0; j7 < j3; j7++) {
                    floatLargeArray.setDouble((j5 * j4) + (j6 * j3) + j7, random.nextFloat());
                }
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, float[] fArr) {
        Random random = new Random(2L);
        long j4 = j2 * j3;
        int i = 0;
        while (true) {
            long j5 = i;
            if (j5 >= j) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j6 = i2;
                if (j6 < j2) {
                    int i3 = 0;
                    while (true) {
                        long j7 = i3;
                        if (j7 < j3) {
                            fArr[(int) ((j5 * j4) + (j6 * j3) + j7)] = random.nextFloat();
                            i3++;
                        }
                    }
                    i2++;
                }
            }
            i++;
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, double[][][] dArr) {
        Random random = new Random(2L);
        for (int i = 0; i < j; i++) {
            for (int i2 = 0; i2 < j2; i2++) {
                for (int i3 = 0; i3 < j3; i3++) {
                    dArr[i][i2][i3] = random.nextDouble();
                }
            }
        }
    }

    public static void fillMatrix_3D(long j, long j2, long j3, float[][][] fArr) {
        Random random = new Random(2L);
        for (int i = 0; i < j; i++) {
            for (int i2 = 0; i2 < j2; i2++) {
                for (int i3 = 0; i3 < j3; i3++) {
                    fArr[i][i2][i3] = random.nextFloat();
                }
            }
        }
    }

    public static void showComplex_1D(double[] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        for (int i = 0; i < dArr.length; i += 2) {
            int i2 = i + 1;
            double d = dArr[i2];
            if (d == 0.0d) {
                System.out.println(String.format(FF, Double.valueOf(dArr[i])));
            } else if (dArr[i] == 0.0d) {
                System.out.println(String.format(FF, Double.valueOf(dArr[i2])) + "i");
            } else if (d < 0.0d) {
                System.out.println(String.format(FF, Double.valueOf(dArr[i])) + " - " + String.format(FF, Double.valueOf(-dArr[i2])) + "i");
            } else {
                System.out.println(String.format(FF, Double.valueOf(dArr[i])) + " + " + String.format(FF, Double.valueOf(dArr[i2])) + "i");
            }
        }
        System.out.println();
    }

    public static void showComplex_2D(int i, int i2, double[] dArr, String str) {
        StringBuilder sb = new StringBuilder(String.format(str + ": complex array 2D: %d rows, %d columns\n\n", Integer.valueOf(i), Integer.valueOf(i2)));
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2 * 2; i4 += 2) {
                int i5 = (i3 * 2 * i2) + i4;
                int i6 = i5 + 1;
                double d = dArr[i6];
                if (d == 0.0d) {
                    sb.append(String.format("%.4f\t", Double.valueOf(dArr[i5])));
                } else {
                    double d2 = dArr[i5];
                    if (d2 == 0.0d) {
                        sb.append(String.format("%.4fi\t", Double.valueOf(d)));
                    } else if (d < 0.0d) {
                        sb.append(String.format("%.4f - %.4fi\t", Double.valueOf(d2), Double.valueOf(-dArr[i6])));
                    } else {
                        sb.append(String.format("%.4f + %.4fi\t", Double.valueOf(d2), Double.valueOf(dArr[i6])));
                    }
                }
            }
            sb.append(StringUtils.LF);
        }
        System.out.println(sb.toString());
    }

    public static void showComplex_2D(double[][] dArr, String str) {
        int length = dArr.length;
        int length2 = dArr[0].length;
        StringBuilder sb = new StringBuilder(String.format(str + ": complex array 2D: %d rows, %d columns\n\n", Integer.valueOf(length), Integer.valueOf(length2)));
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2 += 2) {
                double[] dArr2 = dArr[i];
                int i3 = i2 + 1;
                double d = dArr2[i3];
                if (d == 0.0d) {
                    sb.append(String.format("%.4f\t", Double.valueOf(dArr2[i2])));
                } else {
                    double d2 = dArr2[i2];
                    if (d2 == 0.0d) {
                        sb.append(String.format("%.4fi\t", Double.valueOf(d)));
                    } else if (d < 0.0d) {
                        sb.append(String.format("%.4f - %.4fi\t", Double.valueOf(d2), Double.valueOf(-dArr[i][i3])));
                    } else {
                        sb.append(String.format("%.4f + %.4fi\t", Double.valueOf(d2), Double.valueOf(dArr[i][i3])));
                    }
                }
            }
            sb.append(StringUtils.LF);
        }
        System.out.println(sb.toString());
    }

    public static void showComplex_3D(int i, int i2, int i3, double[] dArr, String str) {
        int i4 = i2 * 2 * i3;
        int i5 = i3 * 2;
        System.out.println(str);
        System.out.println("-------------------");
        for (int i6 = 0; i6 < i5; i6 += 2) {
            System.out.println("(:,:," + (i6 / 2) + ")=\n");
            for (int i7 = 0; i7 < i; i7++) {
                for (int i8 = 0; i8 < i2; i8++) {
                    int i9 = (i7 * i4) + (i8 * i5) + i6;
                    int i10 = i9 + 1;
                    double d = dArr[i10];
                    if (d == 0.0d) {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i9])) + "\t");
                    } else if (dArr[i9] == 0.0d) {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i10])) + "i\t");
                    } else if (d < 0.0d) {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i9])) + " - " + String.format(FF, Double.valueOf(-dArr[i10])) + "i\t");
                    } else {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i9])) + " + " + String.format(FF, Double.valueOf(dArr[i10])) + "i\t");
                    }
                }
                System.out.println("");
            }
        }
        System.out.println("");
    }

    public static void showComplex_3D(double[][][] dArr, String str) {
        int i;
        int i2;
        System.out.println(str);
        System.out.println("-------------------");
        int length = dArr.length;
        double[][] dArr2 = dArr[0];
        int length2 = dArr2.length;
        int length3 = dArr2[0].length;
        for (int i3 = 0; i3 < length3; i3 += 2) {
            System.out.println("(:,:," + (i3 / 2) + ")=\n");
            for (int i4 = 0; i4 < length; i4++) {
                int i5 = 0;
                while (i5 < length2) {
                    double[] dArr3 = dArr[i4][i5];
                    int i6 = i3 + 1;
                    double d = dArr3[i6];
                    if (d == 0.0d) {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i4][i5][i3])) + "\t");
                    } else if (dArr3[i3] == 0.0d) {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i4][i5][i6])) + "i\t");
                    } else if (d < 0.0d) {
                        PrintStream printStream = System.out;
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format(FF, Double.valueOf(dArr[i4][i5][i3])));
                        sb.append(" - ");
                        i = length3;
                        i2 = length2;
                        sb.append(String.format(FF, Double.valueOf(-dArr[i4][i5][i6])));
                        sb.append("i\t");
                        printStream.print(sb.toString());
                        i5++;
                        length2 = i2;
                        length3 = i;
                    } else {
                        i = length3;
                        i2 = length2;
                        System.out.print(String.format(FF, Double.valueOf(dArr[i4][i5][i3])) + " + " + String.format(FF, Double.valueOf(dArr[i4][i5][i6])) + "i\t");
                        i5++;
                        length2 = i2;
                        length3 = i;
                    }
                    i = length3;
                    i2 = length2;
                    i5++;
                    length2 = i2;
                    length3 = i;
                }
                System.out.println("");
            }
        }
        System.out.println("");
    }

    public static void showComplex_3D(int i, int i2, int i3, float[] fArr, String str) {
        int i4 = i2 * 2 * i3;
        int i5 = i3 * 2;
        System.out.println(str);
        System.out.println("-------------------");
        char c = 0;
        int i6 = 0;
        while (i6 < i5) {
            System.out.println("(:,:," + (i6 / 2) + ")=\n");
            int i7 = 0;
            while (i7 < i) {
                int i8 = 0;
                while (i8 < i2) {
                    int i9 = (i7 * i4) + (i8 * i5) + i6;
                    int i10 = i9 + 1;
                    float f = fArr[i10];
                    if (f == 0.0f) {
                        PrintStream printStream = System.out;
                        StringBuilder sb = new StringBuilder();
                        Object[] objArr = new Object[1];
                        objArr[c] = Float.valueOf(fArr[i9]);
                        sb.append(String.format(FF, objArr));
                        sb.append("\t");
                        printStream.print(sb.toString());
                    } else if (fArr[i9] == 0.0f) {
                        System.out.print(String.format(FF, Float.valueOf(fArr[i10])) + "i\t");
                    } else if (f < 0.0f) {
                        System.out.print(String.format(FF, Float.valueOf(fArr[i9])) + " - " + String.format(FF, Float.valueOf(-fArr[i10])) + "i\t");
                    } else {
                        System.out.print(String.format(FF, Float.valueOf(fArr[i9])) + " + " + String.format(FF, Float.valueOf(fArr[i10])) + "i\t");
                        i8++;
                        c = 0;
                    }
                    i8++;
                    c = 0;
                }
                System.out.println("");
                i7++;
                c = 0;
            }
            i6 += 2;
            c = 0;
        }
        System.out.println("");
    }

    public static void showReal_1D(double[] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        for (double d : dArr) {
            System.out.println(String.format(FF, Double.valueOf(d)));
        }
        System.out.println();
    }

    public static void showReal_2D(int i, int i2, double[] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = (i3 * i2) + i4;
                if (FastMath.abs(dArr[i5]) < 5.0E-5d) {
                    System.out.print("0\t");
                } else {
                    System.out.print(String.format(FF, Double.valueOf(dArr[i5])) + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showReal_3D(int i, int i2, int i3, double[] dArr, String str) {
        int i4 = i2 * i3;
        System.out.println(str);
        System.out.println("-------------------");
        for (int i5 = 0; i5 < i3; i5++) {
            System.out.println();
            System.out.println("(:,:," + i5 + ")=\n");
            for (int i6 = 0; i6 < i; i6++) {
                for (int i7 = 0; i7 < i2; i7++) {
                    int i8 = (i6 * i4) + (i7 * i3) + i5;
                    if (FastMath.abs(dArr[i8]) <= 5.0E-5d) {
                        System.out.print("0\t");
                    } else {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i8])) + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void showReal_3D(double[][][] dArr, String str) {
        System.out.println(str);
        System.out.println("-------------------");
        int length = dArr.length;
        double[][] dArr2 = dArr[0];
        int length2 = dArr2.length;
        int length3 = dArr2[0].length;
        for (int i = 0; i < length3; i++) {
            System.out.println();
            System.out.println("(:,:," + i + ")=\n");
            for (int i2 = 0; i2 < length; i2++) {
                for (int i3 = 0; i3 < length2; i3++) {
                    if (FastMath.abs(dArr[i2][i3][i]) <= 5.0E-5d) {
                        System.out.print("0\t");
                    } else {
                        System.out.print(String.format(FF, Double.valueOf(dArr[i2][i3][i])) + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void writeToFileComplex_1D(double[] dArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < dArr.length; i += 2) {
                int i2 = i + 1;
                double d = dArr[i2];
                if (d == 0.0d) {
                    bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i])));
                    bufferedWriter.newLine();
                } else if (dArr[i] == 0.0d) {
                    bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i2])) + "i");
                    bufferedWriter.newLine();
                } else if (d < 0.0d) {
                    bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i])) + " - " + String.format(FF, Double.valueOf(-dArr[i2])) + "i");
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i])) + " + " + String.format(FF, Double.valueOf(dArr[i2])) + "i");
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_1D(float[] fArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < fArr.length; i += 2) {
                int i2 = i + 1;
                float f = fArr[i2];
                if (f == 0.0f) {
                    bufferedWriter.write(String.format(FF, Float.valueOf(fArr[i])));
                    bufferedWriter.newLine();
                } else if (fArr[i] == 0.0f) {
                    bufferedWriter.write(String.format(FF, Float.valueOf(fArr[i2])) + "i");
                    bufferedWriter.newLine();
                } else if (f < 0.0f) {
                    bufferedWriter.write(String.format(FF, Float.valueOf(fArr[i])) + " - " + String.format(FF, Float.valueOf(-fArr[i2])) + "i");
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write(String.format(FF, Float.valueOf(fArr[i])) + " + " + String.format(FF, Float.valueOf(fArr[i2])) + "i");
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d A[Catch: IOException -> 0x0190, TRY_LEAVE, TryCatch #0 {IOException -> 0x0190, blocks: (B:2:0x0000, B:5:0x0013, B:7:0x0017, B:9:0x002d, B:11:0x0039, B:13:0x003f, B:33:0x017e, B:14:0x0046, B:15:0x004d, B:18:0x005c, B:20:0x0062, B:21:0x0086, B:22:0x00aa, B:25:0x00b6, B:27:0x00bc, B:28:0x00e3, B:29:0x010a, B:31:0x0110, B:32:0x0148, B:34:0x0182, B:35:0x0189), top: B:40:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void writeToFileComplex_2D(int r17, int r18, double[] r19, java.lang.String r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.utils.IOUtils.writeToFileComplex_2D(int, int, double[], java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0050 A[Catch: IOException -> 0x0198, TRY_LEAVE, TryCatch #0 {IOException -> 0x0198, blocks: (B:2:0x0000, B:5:0x0013, B:7:0x0017, B:9:0x002e, B:11:0x003b, B:13:0x0042, B:33:0x0186, B:14:0x0049, B:15:0x0050, B:18:0x0060, B:20:0x0067, B:21:0x008b, B:22:0x00af, B:25:0x00bc, B:27:0x00c3, B:28:0x00ea, B:29:0x0111, B:31:0x0118, B:32:0x0150, B:34:0x018a, B:35:0x0191), top: B:40:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void writeToFileComplex_2D(int r17, int r18, float[] r19, java.lang.String r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.utils.IOUtils.writeToFileComplex_2D(int, int, float[], java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0052 A[Catch: IOException -> 0x01b8, TRY_LEAVE, TryCatch #0 {IOException -> 0x01b8, blocks: (B:3:0x0007, B:6:0x0017, B:8:0x001b, B:10:0x002e, B:12:0x003c, B:14:0x0044, B:35:0x01a3, B:15:0x004a, B:17:0x0052, B:20:0x0063, B:22:0x006b, B:23:0x0090, B:24:0x00b5, B:27:0x00c3, B:29:0x00cb, B:30:0x00f5, B:31:0x0120, B:33:0x0128, B:34:0x0167, B:36:0x01a8, B:37:0x01b1), top: B:42:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void writeToFileComplex_2D(double[][] r18, java.lang.String r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 445
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jtransforms.utils.IOUtils.writeToFileComplex_2D(double[][], java.lang.String):void");
    }

    public static void writeToFileComplex_3D(int i, int i2, int i3, double[] dArr, String str) throws IOException {
        int i4 = i2 * i3 * 2;
        int i5 = i3 * 2;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i6 = 0; i6 < i5; i6 += 2) {
                bufferedWriter.newLine();
                bufferedWriter.write("(:,:," + (i6 / 2) + ")=");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                for (int i7 = 0; i7 < i; i7++) {
                    for (int i8 = 0; i8 < i2; i8++) {
                        int i9 = (i7 * i4) + (i8 * i5) + i6;
                        int i10 = i9 + 1;
                        double d = dArr[i10];
                        if (d == 0.0d) {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i9])) + "\t");
                        } else if (dArr[i9] == 0.0d) {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i10])) + "i\t");
                        } else if (d < 0.0d) {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i9])) + " - " + String.format(FF, Double.valueOf(-dArr[i10])) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i9])) + " + " + String.format(FF, Double.valueOf(dArr[i10])) + "i\t");
                        }
                    }
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileComplex_3D(double[][][] dArr, String str) throws IOException {
        int length = dArr.length;
        double[][] dArr2 = dArr[0];
        int length2 = dArr2.length;
        int length3 = dArr2[0].length;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i = 0; i < length3 * 2; i += 2) {
                bufferedWriter.newLine();
                bufferedWriter.write("(:,:," + (i / 2) + ")=");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                for (int i2 = 0; i2 < length; i2++) {
                    for (int i3 = 0; i3 < length2; i3++) {
                        double[] dArr3 = dArr[i2][i3];
                        int i4 = i + 1;
                        double d = dArr3[i4];
                        if (d == 0.0d) {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i2][i3][i])) + "\t");
                        } else if (dArr3[i] == 0.0d) {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i2][i3][i4])) + "i\t");
                        } else if (d < 0.0d) {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i2][i3][i])) + " - " + String.format(FF, Double.valueOf(-dArr[i2][i3][i4])) + "i\t");
                        } else {
                            bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i2][i3][i])) + " + " + String.format(FF, Double.valueOf(dArr[i2][i3][i4])) + "i\t");
                        }
                    }
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_1D(double[] dArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (double d : dArr) {
                bufferedWriter.write(String.format(FF, Double.valueOf(d)));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_1D(float[] fArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (float f : fArr) {
                bufferedWriter.write(String.format(FF, Float.valueOf(f)));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_2D(int i, int i2, double[] dArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = (i3 * i2) + i4;
                    if (FastMath.abs(dArr[i5]) < 5.0E-5d) {
                        bufferedWriter.write("0\t");
                    } else {
                        bufferedWriter.write(String.format(FF, Double.valueOf(dArr[i5])) + "\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_2D(int i, int i2, float[] fArr, String str) throws IOException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = (i3 * i2) + i4;
                    if (FastMath.abs(fArr[i5]) < 5.0E-5d) {
                        bufferedWriter.write("0\t");
                    } else {
                        bufferedWriter.write(String.format(FF, Float.valueOf(fArr[i5])) + "\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFileReal_3D(int i, int i2, int i3, double[] dArr, String str) throws IOException {
        int i4 = i2 * i3;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            for (int i5 = 0; i5 < i3; i5++) {
                bufferedWriter.newLine();
                bufferedWriter.write("(:,:," + i5 + ")=");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                for (int i6 = 0; i6 < i; i6++) {
                    for (int i7 = 0; i7 < i2; i7++) {
                        bufferedWriter.write(String.format(FF, Double.valueOf(dArr[(i6 * i4) + (i7 * i3) + i5])) + "\t");
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFFTBenchmarkResultsToFile(String str, int i, int i2, boolean z, boolean z2, long[] jArr, double[] dArr, double[] dArr2) throws IOException {
        String[] strArr = {"os.name", "os.version", "os.arch", "java.vendor", "java.version"};
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str, false));
            bufferedWriter.write(new Date().toString());
            bufferedWriter.newLine();
            bufferedWriter.write("System properties:");
            bufferedWriter.newLine();
            bufferedWriter.write("\tos.name = " + System.getProperty(strArr[0]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tos.version = " + System.getProperty(strArr[1]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tos.arch = " + System.getProperty(strArr[2]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tjava.vendor = " + System.getProperty(strArr[3]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tjava.version = " + System.getProperty(strArr[4]));
            bufferedWriter.newLine();
            bufferedWriter.write("\tavailable processors = " + Runtime.getRuntime().availableProcessors());
            bufferedWriter.newLine();
            bufferedWriter.write("Settings:");
            bufferedWriter.newLine();
            bufferedWriter.write("\tused processors = " + i);
            bufferedWriter.newLine();
            bufferedWriter.write("\tTHREADS_BEGIN_N_2D = " + CommonUtils.getThreadsBeginN_2D());
            bufferedWriter.newLine();
            bufferedWriter.write("\tTHREADS_BEGIN_N_3D = " + CommonUtils.getThreadsBeginN_3D());
            bufferedWriter.newLine();
            bufferedWriter.write("\tnumber of iterations = " + i2);
            bufferedWriter.newLine();
            bufferedWriter.write("\twarm-up performed = " + z);
            bufferedWriter.newLine();
            bufferedWriter.write("\tscaling performed = " + z2);
            bufferedWriter.newLine();
            bufferedWriter.write("--------------------------------------------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write("sizes=[");
            for (int i3 = 0; i3 < jArr.length; i3++) {
                bufferedWriter.write(Long.toString(jArr[i3]));
                if (i3 < jArr.length - 1) {
                    bufferedWriter.write(", ");
                } else {
                    bufferedWriter.write("]");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("times without constructor(in msec)=[");
            for (int i4 = 0; i4 < dArr.length; i4++) {
                bufferedWriter.write(String.format("%.2f", Double.valueOf(dArr[i4])));
                if (i4 < dArr.length - 1) {
                    bufferedWriter.write(", ");
                } else {
                    bufferedWriter.write("]");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("times with constructor(in msec)=[");
            for (int i5 = 0; i5 < dArr.length; i5++) {
                bufferedWriter.write(String.format("%.2f", Double.valueOf(dArr2[i5])));
                if (i5 < dArr2.length - 1) {
                    bufferedWriter.write(", ");
                } else {
                    bufferedWriter.write("]");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
