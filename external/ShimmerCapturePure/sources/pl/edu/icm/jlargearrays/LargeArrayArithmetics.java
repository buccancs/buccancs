package pl.edu.icm.jlargearrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes2.dex */
public class LargeArrayArithmetics {
    private LargeArrayArithmetics() {
    }

    public static float[] complexSin(float[] fArr) {
        return new float[]{(float) (FastMath.sin(fArr[0]) * FastMath.cosh(fArr[1])), (float) (FastMath.cos(fArr[0]) * FastMath.sinh(fArr[1]))};
    }

    public static double[] complexSin(double[] dArr) {
        return new double[]{FastMath.sin(dArr[0]) * FastMath.cosh(dArr[1]), FastMath.cos(dArr[0]) * FastMath.sinh(dArr[1])};
    }

    public static float[] complexCos(float[] fArr) {
        return new float[]{(float) (FastMath.cos(fArr[0]) * FastMath.cosh(fArr[1])), (float) ((-FastMath.sin(fArr[0])) * FastMath.sinh(fArr[1]))};
    }

    public static double[] complexCos(double[] dArr) {
        return new double[]{FastMath.cos(dArr[0]) * FastMath.cosh(dArr[1]), (-FastMath.sin(dArr[0])) * FastMath.sinh(dArr[1])};
    }

    public static float[] complexTan(float[] fArr) {
        return complexDiv(complexSin(fArr), complexCos(fArr));
    }

    public static double[] complexTan(double[] dArr) {
        return complexDiv(complexSin(dArr), complexCos(dArr));
    }

    public static float[] complexMult(float[] fArr, float[] fArr2) {
        float f = fArr[0] * fArr2[0];
        float f2 = fArr[1];
        float f3 = fArr2[1];
        return new float[]{f - (f2 * f3), (f2 * fArr2[0]) + (fArr[0] * f3)};
    }

    public static double[] complexMult(double[] dArr, double[] dArr2) {
        double d = dArr[0] * dArr2[0];
        double d2 = dArr[1];
        double d3 = dArr2[1];
        return new double[]{d - (d2 * d3), (d2 * dArr2[0]) + (dArr[0] * d3)};
    }

    public static float[] complexDiv(float[] fArr, float[] fArr2) {
        float f = fArr2[0];
        float f2 = fArr2[1];
        float f3 = (f * f) + (f2 * f2);
        float f4 = fArr[0] * f;
        float f5 = fArr[1];
        return new float[]{(f4 + (f5 * f2)) / f3, ((f5 * fArr2[0]) - (fArr[0] * f2)) / f3};
    }

    public static double[] complexDiv(double[] dArr, double[] dArr2) {
        double d = dArr2[0];
        double d2 = dArr2[1];
        double d3 = (d * d) + (d2 * d2);
        double d4 = dArr[0] * d;
        double d5 = dArr[1];
        return new double[]{(d4 + (d5 * d2)) / d3, ((d5 * dArr2[0]) - (dArr[0] * d2)) / d3};
    }

    public static float[] complexPow(float[] fArr, double d) {
        float f = fArr[0];
        float f2 = fArr[1];
        double dPow = FastMath.pow(FastMath.sqrt((f * f) + (f2 * f2)), d);
        double dAtan2 = d * FastMath.atan2(fArr[1], fArr[0]);
        return new float[]{(float) (FastMath.cos(dAtan2) * dPow), (float) (dPow * FastMath.sin(dAtan2))};
    }

    public static double[] complexPow(double[] dArr, double d) {
        double d2 = dArr[0];
        double d3 = dArr[1];
        double dPow = FastMath.pow(FastMath.sqrt((d2 * d2) + (d3 * d3)), d);
        double dAtan2 = d * FastMath.atan2(dArr[1], dArr[0]);
        return new double[]{FastMath.cos(dAtan2) * dPow, dPow * FastMath.sin(dAtan2)};
    }

    public static float[] complexSqrt(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        double dSqrt = FastMath.sqrt((f * f) + (f2 * f2));
        return new float[]{(float) FastMath.sqrt((fArr[0] + dSqrt) / 2.0d), (float) (FastMath.signum(fArr[1]) * FastMath.sqrt(((-fArr[0]) + dSqrt) / 2.0d))};
    }

    public static double[] complexSqrt(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double dSqrt = FastMath.sqrt((d * d) + (d2 * d2));
        return new double[]{FastMath.sqrt((dArr[0] + dSqrt) / 2.0d), FastMath.signum(dArr[1]) * FastMath.sqrt(((-dArr[0]) + dSqrt) / 2.0d)};
    }

    public static float complexAbs(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        return (float) FastMath.sqrt((f * f) + (f2 * f2));
    }

    public static double complexAbs(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        return FastMath.sqrt((d * d) + (d2 * d2));
    }

    public static float[] complexLog(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        return new float[]{(float) FastMath.log(FastMath.sqrt((f * f) + (f2 * f2))), (float) FastMath.atan2(fArr[1], fArr[0])};
    }

    public static double[] complexLog(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        return new double[]{FastMath.log(FastMath.sqrt((d * d) + (d2 * d2))), FastMath.atan2(dArr[1], dArr[0])};
    }

    public static float[] complexLog10(float[] fArr) {
        double dLog = FastMath.log(10.0d);
        float f = fArr[0];
        float f2 = fArr[1];
        return new float[]{(float) (FastMath.log(FastMath.sqrt((f * f) + (f2 * f2))) / dLog), (float) (FastMath.atan2(fArr[1], fArr[0]) / dLog)};
    }

    public static double[] complexLog10(double[] dArr) {
        double dLog = FastMath.log(10.0d);
        double d = dArr[0];
        double d2 = dArr[1];
        return new double[]{FastMath.log(FastMath.sqrt((d * d) + (d2 * d2))) / dLog, FastMath.atan2(dArr[1], dArr[0]) / dLog};
    }

    public static float[] complexExp(float[] fArr) {
        return new float[]{(float) (FastMath.exp(fArr[0]) * FastMath.cos(fArr[1])), (float) (FastMath.exp(fArr[0]) * FastMath.sin(fArr[1]))};
    }

    public static double[] complexExp(double[] dArr) {
        return new double[]{FastMath.exp(dArr[0]) * FastMath.cos(dArr[1]), FastMath.exp(dArr[0]) * FastMath.sin(dArr[1])};
    }

    public static float[] complexAsin(float[] fArr) {
        float[] fArrComplexMult = complexMult(fArr, fArr);
        fArrComplexMult[0] = 1.0f - fArrComplexMult[0];
        fArrComplexMult[1] = 1.0f - fArrComplexMult[1];
        float[] fArrComplexLog = complexLog(fArrComplexMult);
        float[] fArrComplexMult2 = complexMult(new float[]{0.0f, 1.0f}, fArr);
        fArrComplexLog[0] = fArrComplexLog[0] + fArrComplexMult2[0];
        fArrComplexLog[1] = fArrComplexLog[1] + fArrComplexMult2[1];
        return complexMult(new float[]{0.0f, -1.0f}, fArrComplexLog);
    }

    public static double[] complexAsin(double[] dArr) {
        double[] dArrComplexMult = complexMult(dArr, dArr);
        dArrComplexMult[0] = 1.0d - dArrComplexMult[0];
        dArrComplexMult[1] = 1.0d - dArrComplexMult[1];
        double[] dArrComplexLog = complexLog(dArrComplexMult);
        double[] dArrComplexMult2 = complexMult(new double[]{0.0d, 1.0d}, dArr);
        dArrComplexLog[0] = dArrComplexLog[0] + dArrComplexMult2[0];
        dArrComplexLog[1] = dArrComplexLog[1] + dArrComplexMult2[1];
        return complexMult(new double[]{0.0d, -1.0d}, dArrComplexLog);
    }

    public static float[] complexAcos(float[] fArr) {
        float[] fArrComplexMult = complexMult(fArr, fArr);
        fArrComplexMult[0] = 1.0f - fArrComplexMult[0];
        fArrComplexMult[1] = 1.0f - fArrComplexMult[1];
        float[] fArrComplexMult2 = complexMult(new float[]{0.0f, 1.0f}, fArrComplexMult);
        fArrComplexMult2[0] = fArrComplexMult2[0] + fArr[0];
        fArrComplexMult2[1] = fArrComplexMult2[1] + fArr[1];
        return complexMult(new float[]{0.0f, -1.0f}, complexLog(fArrComplexMult2));
    }

    public static double[] complexAcos(double[] dArr) {
        double[] dArrComplexMult = complexMult(dArr, dArr);
        dArrComplexMult[0] = 1.0d - dArrComplexMult[0];
        dArrComplexMult[1] = 1.0d - dArrComplexMult[1];
        double[] dArrComplexMult2 = complexMult(new double[]{0.0d, 1.0d}, dArrComplexMult);
        dArrComplexMult2[0] = dArrComplexMult2[0] + dArr[0];
        dArrComplexMult2[1] = dArrComplexMult2[1] + dArr[1];
        return complexMult(new double[]{0.0d, -1.0d}, complexLog(dArrComplexMult2));
    }

    public static float[] complexAtan(float[] fArr) {
        float[] fArr2 = {0.0f, 1.0f};
        float[] fArrComplexLog = complexLog(complexDiv(new float[]{fArr2[0] + fArr[0], fArr2[1] + fArr[1]}, new float[]{fArr2[0] - fArr[0], fArr2[1] - fArr[1]}));
        fArr2[1] = (float) (fArr2[1] / 2.0d);
        return complexMult(fArr2, fArrComplexLog);
    }

    public static double[] complexAtan(double[] dArr) {
        double[] dArr2 = {0.0d, 1.0d};
        double[] dArrComplexLog = complexLog(complexDiv(new double[]{dArr2[0] + dArr[0], dArr2[1] + dArr[1]}, new double[]{dArr2[0] - dArr[0], dArr2[1] - dArr[1]}));
        dArr2[1] = dArr2[1] / 2.0d;
        return complexMult(dArr2, dArrComplexLog);
    }

    public static LargeArray add(LargeArray largeArray, LargeArray largeArray2) {
        return add(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARN: Type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    public static LargeArray add(final LargeArray largeArray, final LargeArray largeArray2, LargeArrayType largeArrayType) {
        ComplexDoubleLargeArray complexDoubleLargeArrayCreate;
        ComplexFloatLargeArray complexFloatLargeArrayCreate;
        final LargeArray largeArrayCreate;
        if (largeArray == null || largeArray2 == null || largeArray.length() != largeArray2.length() || !largeArray.isNumeric() || !largeArray2.isNumeric()) {
            throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        int i = 0;
        if (largeArray.isConstant() && largeArray2.isConstant()) {
            if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Long.valueOf(largeArray.getLong(0L) + largeArray2.getLong(0L)));
            }
            if (largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(largeArray.getDouble(0L) + largeArray2.getDouble(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                float[] complexFloat = ((ComplexFloatLargeArray) largeArray).getComplexFloat(0L);
                float[] complexFloat2 = ((ComplexFloatLargeArray) largeArray2).getComplexFloat(0L);
                return LargeArrayUtils.createConstant(largeArrayType, length, new float[]{complexFloat[0] + complexFloat2[0], complexFloat[1] + complexFloat2[1]});
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                double[] complexDouble = ((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L);
                double[] complexDouble2 = ((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0L);
                return LargeArrayUtils.createConstant(largeArrayType, length, new double[]{complexDouble[0] + complexDouble2[0], complexDouble[1] + complexDouble2[1]});
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType()) {
            LargeArray largeArrayCreate2 = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                largeArrayCreate = largeArrayCreate2;
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setLong(j, largeArray.getLong(j) + largeArray2.getLong(j));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    int i2 = i;
                    final LargeArray largeArray3 = largeArrayCreate2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.1
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArray3.setLong(j5, largeArray.getLong(j5) + largeArray2.getLong(j5));
                            }
                        }
                    });
                    i = i2 + 1;
                    largeArrayCreate2 = largeArrayCreate2;
                    futureArr = futureArr2;
                }
                largeArrayCreate = largeArrayCreate2;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setLong(j5, largeArray.getLong(j5) + largeArray2.getLong(j5));
                    }
                }
            }
        } else if (largeArrayType.isRealNumericType()) {
            largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j6 = 0; j6 < length; j6++) {
                    largeArrayCreate.setDouble(j6, largeArray.getDouble(j6) + largeArray2.getDouble(j6));
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j8 = i3 * j7;
                    final long j9 = i3 == iMin + (-1) ? length : j8 + j7;
                    int i4 = i3;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.2
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j10 = j8; j10 < j9; j10++) {
                                largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) + largeArray2.getDouble(j10));
                            }
                        }
                    });
                    i3 = i4 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    for (long j10 = 0; j10 < length; j10++) {
                        largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) + largeArray2.getDouble(j10));
                    }
                }
            }
        } else {
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexFloatLargeArray.getType() == largeArray.getType() && complexFloatLargeArray2.getType() == largeArray2.getType()) {
                    complexFloatLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexFloatLargeArrayCreate = complexFloatLargeArray.getType() != largeArray.getType() ? complexFloatLargeArray : complexFloatLargeArray2;
                }
                final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    float[] fArr = new float[2];
                    for (long j11 = 0; j11 < length; j11++) {
                        float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j11);
                        float[] complexFloat4 = complexFloatLargeArray2.getComplexFloat(j11);
                        fArr[0] = complexFloat3[0] + complexFloat4[0];
                        fArr[1] = complexFloat3[1] + complexFloat4[1];
                        complexFloatLargeArray3.setComplexFloat(j11, fArr);
                    }
                    return complexFloatLargeArrayCreate;
                }
                long j12 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                int i5 = 0;
                while (i5 < iMin) {
                    final long j13 = i5 * j12;
                    final long j14 = i5 == iMin + (-1) ? length : j13 + j12;
                    futureArr4[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.3
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                float[] complexFloat5 = complexFloatLargeArray.getComplexFloat(j15);
                                float[] complexFloat6 = complexFloatLargeArray2.getComplexFloat(j15);
                                fArr2[0] = complexFloat5[0] + complexFloat6[0];
                                fArr2[1] = complexFloat5[1] + complexFloat6[1];
                                complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                            }
                        }
                    });
                    i5++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                    return complexFloatLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused3) {
                    float[] fArr2 = new float[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        float[] complexFloat5 = complexFloatLargeArray.getComplexFloat(j15);
                        float[] complexFloat6 = complexFloatLargeArray2.getComplexFloat(j15);
                        fArr2[0] = complexFloat5[0] + complexFloat6[0];
                        fArr2[1] = complexFloat5[1] + complexFloat6[1];
                        complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                    }
                    return complexFloatLargeArrayCreate;
                }
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexDoubleLargeArray.getType() == largeArray.getType() && complexDoubleLargeArray2.getType() == largeArray2.getType()) {
                    complexDoubleLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexDoubleLargeArrayCreate = complexDoubleLargeArray.getType() != largeArray.getType() ? complexDoubleLargeArray : complexDoubleLargeArray2;
                }
                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    double[] dArr = new double[2];
                    for (long j16 = 0; j16 < length; j16++) {
                        double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j16);
                        double[] complexDouble4 = complexDoubleLargeArray2.getComplexDouble(j16);
                        dArr[0] = complexDouble3[0] + complexDouble4[0];
                        dArr[1] = complexDouble3[1] + complexDouble4[1];
                        complexDoubleLargeArray3.setComplexDouble(j16, dArr);
                    }
                    return complexDoubleLargeArrayCreate;
                }
                long j17 = length / iMin;
                Future[] futureArr5 = new Future[iMin];
                int i6 = 0;
                while (i6 < iMin) {
                    int i7 = iMin;
                    final long j18 = i6 * j17;
                    final long j19 = i6 == i7 + (-1) ? length : j18 + j17;
                    futureArr5[i6] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.4
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j20 = j18; j20 < j19; j20++) {
                                double[] complexDouble5 = complexDoubleLargeArray.getComplexDouble(j20);
                                double[] complexDouble6 = complexDoubleLargeArray2.getComplexDouble(j20);
                                dArr2[0] = complexDouble5[0] + complexDouble6[0];
                                dArr2[1] = complexDouble5[1] + complexDouble6[1];
                                complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                            }
                        }
                    });
                    i6++;
                    iMin = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr5);
                    return complexDoubleLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused4) {
                    double[] dArr2 = new double[2];
                    for (long j20 = 0; j20 < length; j20++) {
                        double[] complexDouble5 = complexDoubleLargeArray.getComplexDouble(j20);
                        double[] complexDouble6 = complexDoubleLargeArray2.getComplexDouble(j20);
                        dArr2[0] = complexDouble5[0] + complexDouble6[0];
                        dArr2[1] = complexDouble5[1] + complexDouble6[1];
                        complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                    }
                    return complexDoubleLargeArrayCreate;
                }
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray diff(LargeArray largeArray, LargeArray largeArray2) {
        return diff(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARN: Type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    public static LargeArray diff(final LargeArray largeArray, final LargeArray largeArray2, LargeArrayType largeArrayType) {
        ComplexDoubleLargeArray complexDoubleLargeArrayCreate;
        ComplexFloatLargeArray complexFloatLargeArrayCreate;
        final LargeArray largeArrayCreate;
        if (largeArray == null || largeArray2 == null || largeArray.length() != largeArray2.length() || !largeArray.isNumeric() || !largeArray2.isNumeric()) {
            throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        int i = 0;
        if (largeArray.isConstant() && largeArray2.isConstant()) {
            if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Long.valueOf(largeArray.getLong(0L) - largeArray2.getLong(0L)));
            }
            if (largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(largeArray.getDouble(0L) - largeArray2.getDouble(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                float[] complexFloat = ((ComplexFloatLargeArray) largeArray).getComplexFloat(0L);
                float[] complexFloat2 = ((ComplexFloatLargeArray) largeArray2).getComplexFloat(0L);
                return LargeArrayUtils.createConstant(largeArrayType, length, new float[]{complexFloat[0] - complexFloat2[0], complexFloat[1] - complexFloat2[1]});
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                double[] complexDouble = ((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L);
                double[] complexDouble2 = ((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0L);
                return LargeArrayUtils.createConstant(largeArrayType, length, new double[]{complexDouble[0] - complexDouble2[0], complexDouble[1] - complexDouble2[1]});
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType()) {
            LargeArray largeArrayCreate2 = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                largeArrayCreate = largeArrayCreate2;
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setLong(j, largeArray.getLong(j) - largeArray2.getLong(j));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    int i2 = i;
                    final LargeArray largeArray3 = largeArrayCreate2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.5
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArray3.setLong(j5, largeArray.getLong(j5) - largeArray2.getLong(j5));
                            }
                        }
                    });
                    i = i2 + 1;
                    largeArrayCreate2 = largeArrayCreate2;
                    futureArr = futureArr2;
                }
                largeArrayCreate = largeArrayCreate2;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setLong(j5, largeArray.getLong(j5) - largeArray2.getLong(j5));
                    }
                }
            }
        } else if (largeArrayType.isRealNumericType()) {
            largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j6 = 0; j6 < length; j6++) {
                    largeArrayCreate.setDouble(j6, largeArray.getDouble(j6) - largeArray2.getDouble(j6));
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j8 = i3 * j7;
                    final long j9 = i3 == iMin + (-1) ? length : j8 + j7;
                    int i4 = i3;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.6
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j10 = j8; j10 < j9; j10++) {
                                largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) - largeArray2.getDouble(j10));
                            }
                        }
                    });
                    i3 = i4 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    for (long j10 = 0; j10 < length; j10++) {
                        largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) - largeArray2.getDouble(j10));
                    }
                }
            }
        } else {
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexFloatLargeArray.getType() == largeArray.getType() && complexFloatLargeArray2.getType() == largeArray2.getType()) {
                    complexFloatLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexFloatLargeArrayCreate = complexFloatLargeArray.getType() != largeArray.getType() ? complexFloatLargeArray : complexFloatLargeArray2;
                }
                final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    float[] fArr = new float[2];
                    for (long j11 = 0; j11 < length; j11++) {
                        float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j11);
                        float[] complexFloat4 = complexFloatLargeArray2.getComplexFloat(j11);
                        fArr[0] = complexFloat3[0] - complexFloat4[0];
                        fArr[1] = complexFloat3[1] - complexFloat4[1];
                        complexFloatLargeArray3.setComplexFloat(j11, fArr);
                    }
                    return complexFloatLargeArrayCreate;
                }
                long j12 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                int i5 = 0;
                while (i5 < iMin) {
                    final long j13 = i5 * j12;
                    final long j14 = i5 == iMin + (-1) ? length : j13 + j12;
                    futureArr4[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.7
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                float[] complexFloat5 = complexFloatLargeArray.getComplexFloat(j15);
                                float[] complexFloat6 = complexFloatLargeArray2.getComplexFloat(j15);
                                fArr2[0] = complexFloat5[0] - complexFloat6[0];
                                fArr2[1] = complexFloat5[1] - complexFloat6[1];
                                complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                            }
                        }
                    });
                    i5++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                    return complexFloatLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused3) {
                    float[] fArr2 = new float[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        float[] complexFloat5 = complexFloatLargeArray.getComplexFloat(j15);
                        float[] complexFloat6 = complexFloatLargeArray2.getComplexFloat(j15);
                        fArr2[0] = complexFloat5[0] - complexFloat6[0];
                        fArr2[1] = complexFloat5[1] - complexFloat6[1];
                        complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                    }
                    return complexFloatLargeArrayCreate;
                }
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexDoubleLargeArray.getType() == largeArray.getType() && complexDoubleLargeArray2.getType() == largeArray2.getType()) {
                    complexDoubleLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexDoubleLargeArrayCreate = complexDoubleLargeArray.getType() != largeArray.getType() ? complexDoubleLargeArray : complexDoubleLargeArray2;
                }
                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    double[] dArr = new double[2];
                    for (long j16 = 0; j16 < length; j16++) {
                        double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j16);
                        double[] complexDouble4 = complexDoubleLargeArray2.getComplexDouble(j16);
                        dArr[0] = complexDouble3[0] - complexDouble4[0];
                        dArr[1] = complexDouble3[1] - complexDouble4[1];
                        complexDoubleLargeArray3.setComplexDouble(j16, dArr);
                    }
                    return complexDoubleLargeArrayCreate;
                }
                long j17 = length / iMin;
                Future[] futureArr5 = new Future[iMin];
                int i6 = 0;
                while (i6 < iMin) {
                    int i7 = iMin;
                    final long j18 = i6 * j17;
                    final long j19 = i6 == i7 + (-1) ? length : j18 + j17;
                    futureArr5[i6] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.8
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j20 = j18; j20 < j19; j20++) {
                                double[] complexDouble5 = complexDoubleLargeArray.getComplexDouble(j20);
                                double[] complexDouble6 = complexDoubleLargeArray2.getComplexDouble(j20);
                                dArr2[0] = complexDouble5[0] - complexDouble6[0];
                                dArr2[1] = complexDouble5[1] - complexDouble6[1];
                                complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                            }
                        }
                    });
                    i6++;
                    iMin = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr5);
                    return complexDoubleLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused4) {
                    double[] dArr2 = new double[2];
                    for (long j20 = 0; j20 < length; j20++) {
                        double[] complexDouble5 = complexDoubleLargeArray.getComplexDouble(j20);
                        double[] complexDouble6 = complexDoubleLargeArray2.getComplexDouble(j20);
                        dArr2[0] = complexDouble5[0] - complexDouble6[0];
                        dArr2[1] = complexDouble5[1] - complexDouble6[1];
                        complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                    }
                    return complexDoubleLargeArrayCreate;
                }
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray mult(LargeArray largeArray, LargeArray largeArray2) {
        return mult(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARN: Type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    public static LargeArray mult(final LargeArray largeArray, final LargeArray largeArray2, LargeArrayType largeArrayType) {
        ComplexDoubleLargeArray complexDoubleLargeArrayCreate;
        ComplexFloatLargeArray complexFloatLargeArrayCreate;
        final LargeArray largeArrayCreate;
        if (largeArray == null || largeArray2 == null || largeArray.length() != largeArray2.length() || !largeArray.isNumeric() || !largeArray2.isNumeric()) {
            throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant() && largeArray2.isConstant()) {
            if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Long.valueOf(largeArray.getLong(0L) * largeArray2.getLong(0L)));
            }
            if (largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(largeArray.getDouble(0L) * largeArray2.getDouble(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexMult(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L), ((ComplexFloatLargeArray) largeArray2).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexMult(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L), ((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType()) {
            LargeArray largeArrayCreate2 = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                largeArrayCreate = largeArrayCreate2;
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setLong(j, largeArray.getLong(j) * largeArray2.getLong(j));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    int i2 = i;
                    final LargeArray largeArray3 = largeArrayCreate2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.9
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArray3.setLong(j5, largeArray.getLong(j5) * largeArray2.getLong(j5));
                            }
                        }
                    });
                    i = i2 + 1;
                    largeArrayCreate2 = largeArrayCreate2;
                    futureArr = futureArr2;
                }
                largeArrayCreate = largeArrayCreate2;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setLong(j5, largeArray.getLong(j5) * largeArray2.getLong(j5));
                    }
                }
            }
        } else if (largeArrayType.isRealNumericType()) {
            largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j6 = 0; j6 < length; j6++) {
                    largeArrayCreate.setDouble(j6, largeArray.getDouble(j6) * largeArray2.getDouble(j6));
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j8 = i3 * j7;
                    final long j9 = i3 == iMin + (-1) ? length : j8 + j7;
                    int i4 = i3;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.10
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j10 = j8; j10 < j9; j10++) {
                                largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) * largeArray2.getDouble(j10));
                            }
                        }
                    });
                    i3 = i4 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    for (long j10 = 0; j10 < length; j10++) {
                        largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) * largeArray2.getDouble(j10));
                    }
                }
            }
        } else {
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexFloatLargeArray.getType() == largeArray.getType() && complexFloatLargeArray2.getType() == largeArray2.getType()) {
                    complexFloatLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexFloatLargeArrayCreate = complexFloatLargeArray.getType() != largeArray.getType() ? complexFloatLargeArray : complexFloatLargeArray2;
                }
                final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    float[] fArr = new float[2];
                    for (long j11 = 0; j11 < length; j11++) {
                        float[] complexFloat = complexFloatLargeArray.getComplexFloat(j11);
                        float[] complexFloat2 = complexFloatLargeArray2.getComplexFloat(j11);
                        float f = complexFloat[0] * complexFloat2[0];
                        float f2 = complexFloat[1];
                        float f3 = complexFloat2[1];
                        fArr[0] = f - (f2 * f3);
                        fArr[1] = (complexFloat2[0] * f2) + (complexFloat[0] * f3);
                        complexFloatLargeArray3.setComplexFloat(j11, fArr);
                    }
                    return complexFloatLargeArrayCreate;
                }
                long j12 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                int i5 = 0;
                while (i5 < iMin) {
                    final long j13 = i5 * j12;
                    final long j14 = i5 == iMin + (-1) ? length : j13 + j12;
                    futureArr4[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.11
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j15);
                                float[] complexFloat4 = complexFloatLargeArray2.getComplexFloat(j15);
                                float f4 = complexFloat3[0] * complexFloat4[0];
                                float f5 = complexFloat3[1];
                                float f6 = complexFloat4[1];
                                fArr2[0] = f4 - (f5 * f6);
                                fArr2[1] = (f5 * complexFloat4[0]) + (complexFloat3[0] * f6);
                                complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                            }
                        }
                    });
                    i5++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                    return complexFloatLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused3) {
                    float[] fArr2 = new float[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j15);
                        float[] complexFloat4 = complexFloatLargeArray2.getComplexFloat(j15);
                        float f4 = complexFloat3[0] * complexFloat4[0];
                        float f5 = complexFloat3[1];
                        float f6 = complexFloat4[1];
                        fArr2[0] = f4 - (f5 * f6);
                        fArr2[1] = (complexFloat4[0] * f5) + (complexFloat3[0] * f6);
                        complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                    }
                    return complexFloatLargeArrayCreate;
                }
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexDoubleLargeArray.getType() == largeArray.getType() && complexDoubleLargeArray2.getType() == largeArray2.getType()) {
                    complexDoubleLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexDoubleLargeArrayCreate = complexDoubleLargeArray.getType() != largeArray.getType() ? complexDoubleLargeArray : complexDoubleLargeArray2;
                }
                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    double[] dArr = new double[2];
                    for (long j16 = 0; j16 < length; j16++) {
                        double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j16);
                        double[] complexDouble2 = complexDoubleLargeArray2.getComplexDouble(j16);
                        double d = complexDouble[0] * complexDouble2[0];
                        double d2 = complexDouble[1];
                        double d3 = complexDouble2[1];
                        dArr[0] = d - (d2 * d3);
                        dArr[1] = (d2 * complexDouble2[0]) + (complexDouble[0] * d3);
                        complexDoubleLargeArray3.setComplexDouble(j16, dArr);
                    }
                    return complexDoubleLargeArrayCreate;
                }
                long j17 = length / iMin;
                Future[] futureArr5 = new Future[iMin];
                int i6 = 0;
                while (i6 < iMin) {
                    int i7 = iMin;
                    final long j18 = i6 * j17;
                    final long j19 = i6 == i7 + (-1) ? length : j18 + j17;
                    futureArr5[i6] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.12
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j20 = j18; j20 < j19; j20++) {
                                double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j20);
                                double[] complexDouble4 = complexDoubleLargeArray2.getComplexDouble(j20);
                                double d4 = complexDouble3[0] * complexDouble4[0];
                                double d5 = complexDouble3[1];
                                double d6 = complexDouble4[1];
                                dArr2[0] = d4 - (d5 * d6);
                                dArr2[1] = (d5 * complexDouble4[0]) + (complexDouble3[0] * d6);
                                complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                            }
                        }
                    });
                    i6++;
                    iMin = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr5);
                    return complexDoubleLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused4) {
                    double[] dArr2 = new double[2];
                    for (long j20 = 0; j20 < length; j20++) {
                        double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j20);
                        double[] complexDouble4 = complexDoubleLargeArray2.getComplexDouble(j20);
                        double d4 = complexDouble3[0] * complexDouble4[0];
                        double d5 = complexDouble3[1];
                        double d6 = complexDouble4[1];
                        dArr2[0] = d4 - (d5 * d6);
                        dArr2[1] = (d5 * complexDouble4[0]) + (complexDouble3[0] * d6);
                        complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                    }
                    return complexDoubleLargeArrayCreate;
                }
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray div(LargeArray largeArray, LargeArray largeArray2) {
        return div(largeArray, largeArray2, largeArray.getType().compareTo(largeArray2.getType()) >= 0 ? largeArray.getType() : largeArray2.getType());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [pl.edu.icm.jlargearrays.LargeArray] */
    /* JADX WARN: Type inference failed for: r0v7, types: [pl.edu.icm.jlargearrays.LargeArray] */
    public static LargeArray div(final LargeArray largeArray, final LargeArray largeArray2, LargeArrayType largeArrayType) {
        ComplexDoubleLargeArray complexDoubleLargeArrayCreate;
        ComplexFloatLargeArray complexFloatLargeArrayCreate;
        final LargeArray largeArrayCreate;
        if (largeArray == null || largeArray2 == null || largeArray.length() != largeArray2.length() || !largeArray.isNumeric() || !largeArray2.isNumeric()) {
            throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant() && largeArray2.isConstant()) {
            if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Long.valueOf(largeArray.getLong(0L) / largeArray2.getLong(0L)));
            }
            if (largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(largeArray.getDouble(0L) / largeArray2.getDouble(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexDiv(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L), ((ComplexFloatLargeArray) largeArray2).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexDiv(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L), ((ComplexDoubleLargeArray) largeArray2).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType()) {
            LargeArray largeArrayCreate2 = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                largeArrayCreate = largeArrayCreate2;
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setLong(j, largeArray.getLong(j) / largeArray2.getLong(j));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    int i2 = i;
                    final LargeArray largeArray3 = largeArrayCreate2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.13
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArray3.setLong(j5, largeArray.getLong(j5) / largeArray2.getLong(j5));
                            }
                        }
                    });
                    i = i2 + 1;
                    largeArrayCreate2 = largeArrayCreate2;
                    futureArr = futureArr2;
                }
                largeArrayCreate = largeArrayCreate2;
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setLong(j5, largeArray.getLong(j5) / largeArray2.getLong(j5));
                    }
                }
            }
        } else if (largeArrayType.isRealNumericType()) {
            largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j6 = 0; j6 < length; j6++) {
                    largeArrayCreate.setDouble(j6, largeArray.getDouble(j6) / largeArray2.getDouble(j6));
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j8 = i3 * j7;
                    final long j9 = i3 == iMin + (-1) ? length : j8 + j7;
                    int i4 = i3;
                    futureArr3[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.14
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j10 = j8; j10 < j9; j10++) {
                                largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) / largeArray2.getDouble(j10));
                            }
                        }
                    });
                    i3 = i4 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    for (long j10 = 0; j10 < length; j10++) {
                        largeArrayCreate.setDouble(j10, largeArray.getDouble(j10) / largeArray2.getDouble(j10));
                    }
                }
            }
        } else {
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexFloatLargeArray.getType() == largeArray.getType() && complexFloatLargeArray2.getType() == largeArray2.getType()) {
                    complexFloatLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexFloatLargeArrayCreate = complexFloatLargeArray.getType() != largeArray.getType() ? complexFloatLargeArray : complexFloatLargeArray2;
                }
                final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    float[] fArr = new float[2];
                    for (long j11 = 0; j11 < length; j11++) {
                        float[] complexFloat = complexFloatLargeArray.getComplexFloat(j11);
                        float[] complexFloat2 = complexFloatLargeArray2.getComplexFloat(j11);
                        float f = complexFloat2[0];
                        float f2 = complexFloat2[1];
                        float f3 = (f * f) + (f2 * f2);
                        float f4 = complexFloat[0] * f;
                        float f5 = complexFloat[1];
                        fArr[0] = (f4 + (f5 * f2)) / f3;
                        fArr[1] = ((f5 * complexFloat2[0]) - (complexFloat[0] * f2)) / f3;
                        complexFloatLargeArray3.setComplexFloat(j11, fArr);
                    }
                    return complexFloatLargeArrayCreate;
                }
                long j12 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                int i5 = 0;
                while (i5 < iMin) {
                    final long j13 = i5 * j12;
                    final long j14 = i5 == iMin + (-1) ? length : j13 + j12;
                    futureArr4[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.15
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j15);
                                float[] complexFloat4 = complexFloatLargeArray2.getComplexFloat(j15);
                                float f6 = complexFloat4[0];
                                float f7 = complexFloat4[1];
                                float f8 = (f6 * f6) + (f7 * f7);
                                float f9 = complexFloat3[0] * f6;
                                float f10 = complexFloat3[1];
                                fArr2[0] = (f9 + (f10 * f7)) / f8;
                                fArr2[1] = ((f10 * complexFloat4[0]) - (complexFloat3[0] * f7)) / f8;
                                complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                            }
                        }
                    });
                    i5++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                    return complexFloatLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused3) {
                    float[] fArr2 = new float[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j15);
                        float[] complexFloat4 = complexFloatLargeArray2.getComplexFloat(j15);
                        float f6 = complexFloat4[0];
                        float f7 = complexFloat4[1];
                        float f8 = (f6 * f6) + (f7 * f7);
                        float f9 = complexFloat3[0] * f6;
                        float f10 = complexFloat3[1];
                        fArr2[0] = (f9 + (f10 * f7)) / f8;
                        fArr2[1] = ((f10 * complexFloat4[0]) - (complexFloat3[0] * f7)) / f8;
                        complexFloatLargeArray3.setComplexFloat(j15, fArr2);
                    }
                    return complexFloatLargeArrayCreate;
                }
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray, largeArrayType);
                final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) LargeArrayUtils.convert(largeArray2, largeArrayType);
                if (complexDoubleLargeArray.getType() == largeArray.getType() && complexDoubleLargeArray2.getType() == largeArray2.getType()) {
                    complexDoubleLargeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
                } else {
                    complexDoubleLargeArrayCreate = complexDoubleLargeArray.getType() != largeArray.getType() ? complexDoubleLargeArray : complexDoubleLargeArray2;
                }
                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    double[] dArr = new double[2];
                    for (long j16 = 0; j16 < length; j16++) {
                        double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j16);
                        double[] complexDouble2 = complexDoubleLargeArray2.getComplexDouble(j16);
                        double d = complexDouble2[0];
                        double d2 = complexDouble2[1];
                        double d3 = (d * d) + (d2 * d2);
                        double d4 = complexDouble[0] * d;
                        double d5 = complexDouble[1];
                        dArr[0] = (d4 + (d5 * d2)) / d3;
                        dArr[1] = ((d5 * complexDouble2[0]) - (complexDouble[0] * d2)) / d3;
                        complexDoubleLargeArray3.setComplexDouble(j16, dArr);
                    }
                    return complexDoubleLargeArrayCreate;
                }
                long j17 = length / iMin;
                Future[] futureArr5 = new Future[iMin];
                int i6 = 0;
                while (i6 < iMin) {
                    int i7 = iMin;
                    final long j18 = i6 * j17;
                    final long j19 = i6 == i7 + (-1) ? length : j18 + j17;
                    futureArr5[i6] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.16
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j20 = j18; j20 < j19; j20++) {
                                double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j20);
                                double[] complexDouble4 = complexDoubleLargeArray2.getComplexDouble(j20);
                                double d6 = complexDouble4[0];
                                double d7 = complexDouble4[1];
                                double d8 = (d6 * d6) + (d7 * d7);
                                double d9 = complexDouble3[0] * d6;
                                double d10 = complexDouble3[1];
                                dArr2[0] = (d9 + (d10 * d7)) / d8;
                                dArr2[1] = ((d10 * complexDouble4[0]) - (complexDouble3[0] * d7)) / d8;
                                complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                            }
                        }
                    });
                    i6++;
                    iMin = i7;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr5);
                    return complexDoubleLargeArrayCreate;
                } catch (InterruptedException | ExecutionException unused4) {
                    double[] dArr2 = new double[2];
                    for (long j20 = 0; j20 < length; j20++) {
                        double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j20);
                        double[] complexDouble4 = complexDoubleLargeArray2.getComplexDouble(j20);
                        double d6 = complexDouble4[0];
                        double d7 = complexDouble4[1];
                        double d8 = (d6 * d6) + (d7 * d7);
                        double d9 = complexDouble3[0] * d6;
                        double d10 = complexDouble3[1];
                        dArr2[0] = (d9 + (d10 * d7)) / d8;
                        dArr2[1] = ((complexDouble4[0] * d10) - (complexDouble3[0] * d7)) / d8;
                        complexDoubleLargeArray3.setComplexDouble(j20, dArr2);
                    }
                    return complexDoubleLargeArrayCreate;
                }
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray pow(LargeArray largeArray, double d) {
        return pow(largeArray, d, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray pow(final LargeArray largeArray, final double d, LargeArrayType largeArrayType) {
        LargeArray largeArray2;
        int i;
        int i2;
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.pow(largeArray.getDouble(0L), d)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexPow(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L), d));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexPow(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L), d));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType()) {
            largeArray2 = largeArrayCreate;
            i = 2;
            i2 = iMin;
        } else {
            if (!largeArrayType.isRealNumericType()) {
                if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                    final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
                    ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
                    if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        largeArray2 = largeArrayCreate;
                        float[] fArr = new float[2];
                        for (long j = 0; j < length; j++) {
                            float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                            float f = complexFloat[0];
                            float f2 = complexFloat[1];
                            double dPow = FastMath.pow(FastMath.sqrt((f * f) + (f2 * f2)), d);
                            double dAtan2 = FastMath.atan2(complexFloat[1], complexFloat[0]) * d;
                            fArr[0] = (float) (FastMath.cos(dAtan2) * dPow);
                            fArr[1] = (float) (dPow * FastMath.sin(dAtan2));
                            complexFloatLargeArray2.setComplexFloat(j, fArr);
                        }
                    } else {
                        long j2 = length / iMin;
                        Future[] futureArr = new Future[iMin];
                        int i3 = 0;
                        while (i3 < iMin) {
                            LargeArray largeArray3 = largeArrayCreate;
                            final long j3 = i3 * j2;
                            int i4 = i3;
                            final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            Future[] futureArr2 = futureArr;
                            final long j4 = i3 == iMin + (-1) ? length : j3 + j2;
                            futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.18
                                @Override // java.lang.Runnable
                                public void run() {
                                    float[] fArr2 = new float[2];
                                    for (long j5 = j3; j5 < j4; j5++) {
                                        float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                        float f3 = complexFloat2[0];
                                        float f4 = complexFloat2[1];
                                        double dPow2 = FastMath.pow(FastMath.sqrt((f3 * f3) + (f4 * f4)), d);
                                        double dAtan22 = FastMath.atan2(complexFloat2[1], complexFloat2[0]);
                                        fArr2[0] = (float) (FastMath.cos(d * dAtan22) * dPow2);
                                        fArr2[1] = (float) (dPow2 * FastMath.sin(d * dAtan22));
                                        complexFloatLargeArray3.setComplexFloat(j5, fArr2);
                                    }
                                }
                            });
                            i3 = i4 + 1;
                            complexFloatLargeArray2 = complexFloatLargeArray3;
                            largeArrayCreate = largeArray3;
                            futureArr = futureArr2;
                            iMin = iMin;
                        }
                        ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                        LargeArray largeArray4 = largeArrayCreate;
                        try {
                            ConcurrencyUtils.waitForCompletion(futureArr);
                        } catch (InterruptedException | ExecutionException unused) {
                            float[] fArr2 = new float[2];
                            long j5 = 0;
                            while (j5 < length) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                float f3 = complexFloat2[0];
                                float f4 = complexFloat2[1];
                                double dPow2 = FastMath.pow(FastMath.sqrt((f3 * f3) + (f4 * f4)), d);
                                double dAtan22 = FastMath.atan2(complexFloat2[1], complexFloat2[0]) * d;
                                fArr2[0] = (float) (FastMath.cos(dAtan22) * dPow2);
                                fArr2[1] = (float) (dPow2 * FastMath.sin(dAtan22));
                                complexFloatLargeArray4.setComplexFloat(j5, fArr2);
                                j5++;
                                largeArray4 = largeArray4;
                            }
                        }
                        largeArray2 = largeArray4;
                    }
                } else {
                    largeArray2 = largeArrayCreate;
                    if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                        final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
                        final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArray2;
                        int i5 = iMin;
                        if (i5 < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                            double[] dArr = new double[2];
                            for (long j6 = 0; j6 < length; j6++) {
                                double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                                double d2 = complexDouble[0];
                                double d3 = complexDouble[1];
                                double dPow3 = FastMath.pow(FastMath.sqrt((d2 * d2) + (d3 * d3)), d);
                                double dAtan23 = FastMath.atan2(complexDouble[1], complexDouble[0]) * d;
                                dArr[0] = FastMath.cos(dAtan23) * dPow3;
                                dArr[1] = dPow3 * FastMath.sin(dAtan23);
                                complexDoubleLargeArray2.setComplexDouble(j6, dArr);
                            }
                        } else {
                            long j7 = length / i5;
                            Future[] futureArr3 = new Future[i5];
                            int i6 = 0;
                            while (i6 < i5) {
                                final long j8 = i6 * j7;
                                final long j9 = i6 == i5 + (-1) ? length : j8 + j7;
                                Future[] futureArr4 = futureArr3;
                                int i7 = i6;
                                futureArr4[i7] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.19
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        double[] dArr2 = new double[2];
                                        for (long j10 = j8; j10 < j9; j10++) {
                                            double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                            double d4 = complexDouble2[0];
                                            double d5 = complexDouble2[1];
                                            double dPow4 = FastMath.pow(FastMath.sqrt((d4 * d4) + (d5 * d5)), d);
                                            double dAtan24 = FastMath.atan2(complexDouble2[1], complexDouble2[0]);
                                            dArr2[0] = FastMath.cos(d * dAtan24) * dPow4;
                                            dArr2[1] = dPow4 * FastMath.sin(d * dAtan24);
                                            complexDoubleLargeArray2.setComplexDouble(j10, dArr2);
                                        }
                                    }
                                });
                                i6 = i7 + 1;
                                futureArr3 = futureArr4;
                                i5 = i5;
                            }
                            try {
                                ConcurrencyUtils.waitForCompletion(futureArr3);
                            } catch (InterruptedException | ExecutionException unused2) {
                                double[] dArr2 = new double[2];
                                long j10 = 0;
                                while (j10 < length) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    double d4 = complexDouble2[0];
                                    double d5 = complexDouble2[1];
                                    double dPow4 = FastMath.pow(FastMath.sqrt((d4 * d4) + (d5 * d5)), d);
                                    double dAtan24 = FastMath.atan2(complexDouble2[1], complexDouble2[0]) * d;
                                    dArr2[0] = FastMath.cos(dAtan24) * dPow4;
                                    dArr2[1] = dPow4 * FastMath.sin(dAtan24);
                                    complexDoubleLargeArray2.setComplexDouble(j10, dArr2);
                                    j10++;
                                    complexDoubleLargeArray = complexDoubleLargeArray;
                                }
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid array type.");
                    }
                }
                return largeArray2;
            }
            largeArray2 = largeArrayCreate;
            i2 = iMin;
            i = 2;
        }
        if (i2 < i || length < ConcurrencyUtils.getConcurrentThreshold()) {
            LargeArray largeArray5 = largeArray2;
            for (long j11 = 0; j11 < length; j11++) {
                largeArray5.setDouble(j11, FastMath.pow(largeArray.getDouble(j11), d));
            }
            return largeArray5;
        }
        long j12 = length / i2;
        Future[] futureArr5 = new Future[i2];
        int i8 = 0;
        while (i8 < i2) {
            final long j13 = i8 * j12;
            final long j14 = i8 == i2 + (-1) ? length : j13 + j12;
            final LargeArray largeArray6 = largeArray2;
            int i9 = i8;
            futureArr5[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.17
                @Override // java.lang.Runnable
                public void run() {
                    for (long j15 = j13; j15 < j14; j15++) {
                        largeArray6.setDouble(j15, FastMath.pow(largeArray.getDouble(j15), d));
                    }
                }
            });
            i8 = i9 + 1;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr5);
        } catch (InterruptedException | ExecutionException unused3) {
            for (long j15 = 0; j15 < length; j15++) {
                largeArray2.setDouble(j15, FastMath.pow(largeArray.getDouble(j15), d));
            }
        }
        return largeArray2;
    }

    public static LargeArray neg(LargeArray largeArray) {
        return neg(largeArray, largeArray.getType());
    }

    public static LargeArray neg(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Long.valueOf(-largeArray.getLong(0L)));
            }
            if (largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(-largeArray.getDouble(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                float[] complexFloat = ((ComplexFloatLargeArray) largeArray).getComplexFloat(0L);
                return LargeArrayUtils.createConstant(largeArrayType, length, new float[]{-complexFloat[0], -complexFloat[1]});
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                double[] complexDouble = ((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L);
                return LargeArrayUtils.createConstant(largeArrayType, length, new double[]{-complexDouble[0], -complexDouble[1]});
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setLong(j, -largeArray.getLong(j));
                    j++;
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    int i2 = i;
                    futureArr[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.20
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setLong(j5, -largeArray.getLong(j5));
                            }
                        }
                    });
                    i = i2 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    while (j < length) {
                        largeArrayCreate.setLong(j, -largeArray.getLong(j));
                        j++;
                    }
                }
            }
        } else if (largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, -largeArray.getDouble(j));
                    j++;
                }
            } else {
                long j5 = length / iMin;
                Future[] futureArr2 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j6 = i3 * j5;
                    final long j7 = i3 == iMin + (-1) ? length : j6 + j5;
                    int i4 = i3;
                    futureArr2[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.21
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j8 = j6; j8 < j7; j8++) {
                                largeArrayCreate.setDouble(j8, -largeArray.getDouble(j8));
                            }
                        }
                    });
                    i3 = i4 + 1;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr2);
                } catch (InterruptedException | ExecutionException unused2) {
                    while (j < length) {
                        largeArrayCreate.setDouble(j, -largeArray.getDouble(j));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                float[] fArr = new float[2];
                for (long j8 = 0; j8 < length; j8++) {
                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j8);
                    fArr[0] = -complexFloat2[0];
                    fArr[1] = -complexFloat2[1];
                    complexFloatLargeArray2.setComplexFloat(j8, fArr);
                }
            } else {
                long j9 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i5 = 0;
                while (i5 < iMin) {
                    final long j10 = i5 * j9;
                    final long j11 = i5 == iMin + (-1) ? length : j10 + j9;
                    futureArr3[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.22
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j12 = j10; j12 < j11; j12++) {
                                float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j12);
                                fArr2[0] = -complexFloat3[0];
                                fArr2[1] = -complexFloat3[1];
                                complexFloatLargeArray2.setComplexFloat(j12, fArr2);
                            }
                        }
                    });
                    i5++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    float[] fArr2 = new float[2];
                    for (long j12 = 0; j12 < length; j12++) {
                        float[] complexFloat3 = complexFloatLargeArray.getComplexFloat(j12);
                        fArr2[0] = -complexFloat3[0];
                        fArr2[1] = -complexFloat3[1];
                        complexFloatLargeArray2.setComplexFloat(j12, fArr2);
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                double[] dArr = new double[2];
                for (long j13 = 0; j13 < length; j13++) {
                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j13);
                    dArr[0] = -complexDouble2[0];
                    dArr[1] = -complexDouble2[1];
                    complexDoubleLargeArray2.setComplexDouble(j13, dArr);
                }
            } else {
                long j14 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                int i6 = 0;
                while (i6 < iMin) {
                    final long j15 = i6 * j14;
                    final long j16 = i6 == iMin + (-1) ? length : j15 + j14;
                    futureArr4[i6] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.23
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j17 = j15; j17 < j16; j17++) {
                                double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j17);
                                dArr2[0] = -complexDouble3[0];
                                dArr2[1] = -complexDouble3[1];
                                complexDoubleLargeArray2.setComplexDouble(j17, dArr2);
                            }
                        }
                    });
                    i6++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                } catch (InterruptedException | ExecutionException unused4) {
                    double[] dArr2 = new double[2];
                    for (long j17 = 0; j17 < length; j17++) {
                        double[] complexDouble3 = complexDoubleLargeArray.getComplexDouble(j17);
                        dArr2[0] = -complexDouble3[0];
                        dArr2[1] = -complexDouble3[1];
                        complexDoubleLargeArray2.setComplexDouble(j17, dArr2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray sqrt(LargeArray largeArray) {
        return sqrt(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray sqrt(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.sqrt(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexSqrt(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexSqrt(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        char c = 0;
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
            double d = 2.0d;
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
                final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    float[] fArr = new float[2];
                    for (long j = 0; j < length; j++) {
                        float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                        float f = complexFloat[0];
                        float f2 = complexFloat[1];
                        double dSqrt = FastMath.sqrt((f * f) + (f2 * f2));
                        fArr[0] = (float) FastMath.sqrt((complexFloat[0] + dSqrt) / 2.0d);
                        fArr[1] = (float) (FastMath.signum(complexFloat[1]) * FastMath.sqrt(((-complexFloat[0]) + dSqrt) / 2.0d));
                        complexFloatLargeArray2.setComplexFloat(j, fArr);
                    }
                } else {
                    long j2 = length / iMin;
                    Future[] futureArr = new Future[iMin];
                    int i = 0;
                    while (i < iMin) {
                        final long j3 = i * j2;
                        final long j4 = i == iMin + (-1) ? length : j3 + j2;
                        futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.25
                            @Override // java.lang.Runnable
                            public void run() {
                                float[] fArr2 = new float[2];
                                for (long j5 = j3; j5 < j4; j5++) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    float f3 = complexFloat2[0];
                                    float f4 = complexFloat2[1];
                                    double dSqrt2 = FastMath.sqrt((f3 * f3) + (f4 * f4));
                                    fArr2[0] = (float) FastMath.sqrt((complexFloat2[0] + dSqrt2) / 2.0d);
                                    fArr2[1] = (float) (FastMath.signum(complexFloat2[1]) * FastMath.sqrt(((-complexFloat2[0]) + dSqrt2) / 2.0d));
                                    complexFloatLargeArray2.setComplexFloat(j5, fArr2);
                                }
                            }
                        });
                        i++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                    } catch (InterruptedException | ExecutionException unused) {
                        float[] fArr2 = new float[2];
                        long j5 = 0;
                        while (j5 < length) {
                            float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                            float f3 = complexFloat2[c];
                            float f4 = complexFloat2[1];
                            double dSqrt2 = FastMath.sqrt((f3 * f3) + (f4 * f4));
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            fArr2[0] = (float) FastMath.sqrt((complexFloat2[c] + dSqrt2) / d);
                            fArr2[1] = (float) (FastMath.signum(complexFloat2[1]) * FastMath.sqrt(((-complexFloat2[0]) + dSqrt2) / 2.0d));
                            complexFloatLargeArray3.setComplexFloat(j5, fArr2);
                            j5++;
                            complexFloatLargeArray2 = complexFloatLargeArray3;
                            c = 0;
                            d = 2.0d;
                        }
                    }
                }
            } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
                final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    double[] dArr = new double[2];
                    for (long j6 = 0; j6 < length; j6++) {
                        double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                        double d2 = complexDouble[0];
                        double d3 = complexDouble[1];
                        double dSqrt3 = FastMath.sqrt((d2 * d2) + (d3 * d3));
                        dArr[0] = FastMath.sqrt((complexDouble[0] + dSqrt3) / 2.0d);
                        dArr[1] = FastMath.signum(complexDouble[1]) * FastMath.sqrt(((-complexDouble[0]) + dSqrt3) / 2.0d);
                        complexDoubleLargeArray2.setComplexDouble(j6, dArr);
                    }
                } else {
                    long j7 = length / iMin;
                    Future[] futureArr2 = new Future[iMin];
                    int i2 = 0;
                    while (i2 < iMin) {
                        final long j8 = i2 * j7;
                        final long j9 = i2 == iMin + (-1) ? length : j8 + j7;
                        futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.26
                            @Override // java.lang.Runnable
                            public void run() {
                                double[] dArr2 = new double[2];
                                for (long j10 = j8; j10 < j9; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    double d4 = complexDouble2[0];
                                    double d5 = complexDouble2[1];
                                    double dSqrt4 = FastMath.sqrt((d4 * d4) + (d5 * d5));
                                    dArr2[0] = FastMath.sqrt((complexDouble2[0] + dSqrt4) / 2.0d);
                                    dArr2[1] = FastMath.signum(complexDouble2[1]) * FastMath.sqrt(((-complexDouble2[0]) + dSqrt4) / 2.0d);
                                    complexDoubleLargeArray2.setComplexDouble(j10, dArr2);
                                }
                            }
                        });
                        i2++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr2);
                    } catch (InterruptedException | ExecutionException unused2) {
                        double[] dArr2 = new double[2];
                        long j10 = 0;
                        while (j10 < length) {
                            double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                            double d4 = complexDouble2[0];
                            double d5 = complexDouble2[1];
                            double dSqrt4 = FastMath.sqrt((d4 * d4) + (d5 * d5));
                            dArr2[0] = FastMath.sqrt((complexDouble2[0] + dSqrt4) / 2.0d);
                            dArr2[1] = FastMath.signum(complexDouble2[1]) * FastMath.sqrt(((-complexDouble2[0]) + dSqrt4) / 2.0d);
                            complexDoubleLargeArray2.setComplexDouble(j10, dArr2);
                            j10++;
                            length = length;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
            for (long j11 = 0; j11 < length; j11++) {
                largeArrayCreate.setDouble(j11, FastMath.sqrt(largeArray.getDouble(j11)));
            }
        } else {
            long j12 = length / iMin;
            Future[] futureArr3 = new Future[iMin];
            int i3 = 0;
            while (i3 < iMin) {
                final long j13 = i3 * j12;
                final long j14 = i3 == iMin + (-1) ? length : j13 + j12;
                futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.24
                    @Override // java.lang.Runnable
                    public void run() {
                        for (long j15 = j13; j15 < j14; j15++) {
                            largeArrayCreate.setDouble(j15, FastMath.sqrt(largeArray.getDouble(j15)));
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException | ExecutionException unused3) {
                for (long j15 = 0; j15 < length; j15++) {
                    largeArrayCreate.setDouble(j15, FastMath.sqrt(largeArray.getDouble(j15)));
                }
            }
        }
        return largeArrayCreate;
    }

    public static LargeArray log(LargeArray largeArray) {
        return log(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray log(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.log(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexLog(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexLog(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        char c = 0;
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (!largeArrayType.isIntegerNumericType() && !largeArrayType.isRealNumericType()) {
            char c2 = 1;
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
                final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    float[] fArr = new float[2];
                    long j = 0;
                    while (j < length) {
                        float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                        float f = complexFloat[0];
                        float f2 = complexFloat[c2];
                        double dSqrt = FastMath.sqrt((f * f) + (f2 * f2));
                        double dAtan2 = FastMath.atan2(complexFloat[c2], complexFloat[0]);
                        fArr[0] = (float) FastMath.log(dSqrt);
                        fArr[1] = (float) dAtan2;
                        complexFloatLargeArray2.setComplexFloat(j, fArr);
                        j++;
                        c2 = 1;
                    }
                } else {
                    long j2 = length / iMin;
                    Future[] futureArr = new Future[iMin];
                    int i = 0;
                    while (i < iMin) {
                        final long j3 = i * j2;
                        final long j4 = i == iMin + (-1) ? length : j3 + j2;
                        futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.28
                            @Override // java.lang.Runnable
                            public void run() {
                                float[] fArr2 = new float[2];
                                for (long j5 = j3; j5 < j4; j5++) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                                    float f3 = complexFloat2[0];
                                    float f4 = complexFloat2[1];
                                    double dSqrt2 = FastMath.sqrt((f3 * f3) + (f4 * f4));
                                    double dAtan22 = FastMath.atan2(complexFloat2[1], complexFloat2[0]);
                                    fArr2[0] = (float) FastMath.log(dSqrt2);
                                    fArr2[1] = (float) dAtan22;
                                    complexFloatLargeArray2.setComplexFloat(j5, fArr2);
                                }
                            }
                        });
                        i++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                    } catch (InterruptedException | ExecutionException unused) {
                        float[] fArr2 = new float[2];
                        long j5 = 0;
                        while (j5 < length) {
                            float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j5);
                            float f3 = complexFloat2[c];
                            float f4 = complexFloat2[1];
                            double dSqrt2 = FastMath.sqrt((f3 * f3) + (f4 * f4));
                            ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                            double dAtan22 = FastMath.atan2(complexFloat2[1], complexFloat2[c]);
                            fArr2[0] = (float) FastMath.log(dSqrt2);
                            fArr2[1] = (float) dAtan22;
                            complexFloatLargeArray3.setComplexFloat(j5, fArr2);
                            j5++;
                            complexFloatLargeArray2 = complexFloatLargeArray3;
                            c = 0;
                        }
                    }
                }
            } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
                final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
                if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                    double[] dArr = new double[2];
                    for (long j6 = 0; j6 < length; j6++) {
                        double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j6);
                        double d = complexDouble[0];
                        double d2 = complexDouble[1];
                        double dSqrt3 = FastMath.sqrt((d * d) + (d2 * d2));
                        double dAtan23 = FastMath.atan2(complexDouble[1], complexDouble[0]);
                        dArr[0] = FastMath.log(dSqrt3);
                        dArr[1] = dAtan23;
                        complexDoubleLargeArray2.setComplexDouble(j6, dArr);
                    }
                } else {
                    long j7 = length / iMin;
                    Future[] futureArr2 = new Future[iMin];
                    int i2 = 0;
                    while (i2 < iMin) {
                        final long j8 = i2 * j7;
                        final long j9 = i2 == iMin + (-1) ? length : j8 + j7;
                        futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.29
                            @Override // java.lang.Runnable
                            public void run() {
                                double[] dArr2 = new double[2];
                                for (long j10 = j8; j10 < j9; j10++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                                    double d3 = complexDouble2[0];
                                    double d4 = complexDouble2[1];
                                    double dSqrt4 = FastMath.sqrt((d3 * d3) + (d4 * d4));
                                    double dAtan24 = FastMath.atan2(complexDouble2[1], complexDouble2[0]);
                                    dArr2[0] = FastMath.log(dSqrt4);
                                    dArr2[1] = dAtan24;
                                    complexDoubleLargeArray2.setComplexDouble(j10, dArr2);
                                }
                            }
                        });
                        i2++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr2);
                    } catch (InterruptedException | ExecutionException unused2) {
                        double[] dArr2 = new double[2];
                        long j10 = 0;
                        while (j10 < length) {
                            double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j10);
                            double d3 = complexDouble2[0];
                            double d4 = complexDouble2[1];
                            double dSqrt4 = FastMath.sqrt((d3 * d3) + (d4 * d4));
                            double dAtan24 = FastMath.atan2(complexDouble2[1], complexDouble2[0]);
                            dArr2[0] = FastMath.log(dSqrt4);
                            dArr2[1] = dAtan24;
                            complexDoubleLargeArray2.setComplexDouble(j10, dArr2);
                            j10++;
                            length = length;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid array type.");
            }
        } else if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
            for (long j11 = 0; j11 < length; j11++) {
                largeArrayCreate.setDouble(j11, FastMath.log(largeArray.getDouble(j11)));
            }
        } else {
            long j12 = length / iMin;
            Future[] futureArr3 = new Future[iMin];
            int i3 = 0;
            while (i3 < iMin) {
                final long j13 = i3 * j12;
                final long j14 = i3 == iMin + (-1) ? length : j13 + j12;
                futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.27
                    @Override // java.lang.Runnable
                    public void run() {
                        for (long j15 = j13; j15 < j14; j15++) {
                            largeArrayCreate.setDouble(j15, FastMath.log(largeArray.getDouble(j15)));
                        }
                    }
                });
                i3++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException | ExecutionException unused3) {
                for (long j15 = 0; j15 < length; j15++) {
                    largeArrayCreate.setDouble(j15, FastMath.log(largeArray.getDouble(j15)));
                }
            }
        }
        return largeArrayCreate;
    }

    public static LargeArray log10(LargeArray largeArray) {
        return log10(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray log10(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.log10(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexLog10(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexLog10(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setDouble(j, FastMath.log10(largeArray.getDouble(j)));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.30
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.log10(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setDouble(j5, FastMath.log10(largeArray.getDouble(j5)));
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final double dLog = FastMath.log(10.0d);
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                float[] fArr = new float[2];
                for (long j6 = 0; j6 < length; j6++) {
                    float[] complexFloat = complexFloatLargeArray.getComplexFloat(j6);
                    float f = complexFloat[0];
                    float f2 = complexFloat[1];
                    double dSqrt = FastMath.sqrt((f * f) + (f2 * f2));
                    ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray2;
                    double dAtan2 = FastMath.atan2(complexFloat[1], complexFloat[0]) / dLog;
                    fArr[0] = (float) (FastMath.log(dSqrt) / dLog);
                    fArr[1] = (float) dAtan2;
                    complexFloatLargeArray2 = complexFloatLargeArray3;
                    complexFloatLargeArray2.setComplexFloat(j6, fArr);
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr2 = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j8 = i2 * j7;
                    final long j9 = i2 == iMin + (-1) ? length : j8 + j7;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.31
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j10 = j8; j10 < j9; j10++) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                                float f3 = complexFloat2[0];
                                float f4 = complexFloat2[1];
                                double dSqrt2 = FastMath.sqrt((f3 * f3) + (f4 * f4));
                                double dAtan22 = FastMath.atan2(complexFloat2[1], complexFloat2[0]) / dLog;
                                fArr2[0] = (float) (FastMath.log(dSqrt2) / dLog);
                                fArr2[1] = (float) dAtan22;
                                complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                            }
                        }
                    });
                    i2++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr2);
                } catch (InterruptedException | ExecutionException unused2) {
                    float[] fArr2 = new float[2];
                    long j10 = 0;
                    while (j10 < length) {
                        float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                        float f3 = complexFloat2[0];
                        float f4 = complexFloat2[1];
                        double dSqrt2 = FastMath.sqrt((f3 * f3) + (f4 * f4));
                        ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
                        double dAtan22 = FastMath.atan2(complexFloat2[1], complexFloat2[0]) / dLog;
                        fArr2[0] = (float) (FastMath.log(dSqrt2) / dLog);
                        fArr2[1] = (float) dAtan22;
                        complexFloatLargeArray2 = complexFloatLargeArray4;
                        complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                        j10++;
                        length = length;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final double dLog2 = FastMath.log(10.0d);
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                double[] dArr = new double[2];
                for (long j11 = 0; j11 < length; j11++) {
                    double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j11);
                    double d = complexDouble[0];
                    double d2 = complexDouble[1];
                    double dSqrt3 = FastMath.sqrt((d * d) + (d2 * d2));
                    ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray2;
                    double dAtan23 = FastMath.atan2(complexDouble[1], complexDouble[0]) / dLog2;
                    dArr[0] = FastMath.log(dSqrt3) / dLog2;
                    dArr[1] = dAtan23;
                    complexDoubleLargeArray2 = complexDoubleLargeArray3;
                    complexDoubleLargeArray2.setComplexDouble(j11, dArr);
                }
            } else {
                long j12 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j13 = i3 * j12;
                    final long j14 = i3 == iMin + (-1) ? length : j13 + j12;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.32
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                                double d3 = complexDouble2[0];
                                double d4 = complexDouble2[1];
                                double dSqrt4 = FastMath.sqrt((d3 * d3) + (d4 * d4));
                                double dAtan24 = FastMath.atan2(complexDouble2[1], complexDouble2[0]) / dLog2;
                                dArr2[0] = FastMath.log(dSqrt4) / dLog2;
                                dArr2[1] = dAtan24;
                                complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    double[] dArr2 = new double[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                        double d3 = complexDouble2[0];
                        double d4 = complexDouble2[1];
                        double dSqrt4 = FastMath.sqrt((d3 * d3) + (d4 * d4));
                        ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
                        double dAtan24 = FastMath.atan2(complexDouble2[1], complexDouble2[0]) / dLog2;
                        dArr2[0] = FastMath.log(dSqrt4) / dLog2;
                        dArr2[1] = dAtan24;
                        complexDoubleLargeArray2 = complexDoubleLargeArray4;
                        complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray exp(LargeArray largeArray) {
        return exp(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray exp(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.exp(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexExp(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexExp(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setDouble(j, FastMath.exp(largeArray.getDouble(j)));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.33
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.exp(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setDouble(j5, FastMath.exp(largeArray.getDouble(j5)));
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                float[] fArr = new float[2];
                for (long j6 = 0; j6 < length; j6++) {
                    float[] complexFloat = complexFloatLargeArray.getComplexFloat(j6);
                    fArr[0] = (float) (FastMath.exp(complexFloat[0]) * FastMath.cos(complexFloat[1]));
                    fArr[1] = (float) (FastMath.exp(complexFloat[0]) * FastMath.sin(complexFloat[1]));
                    complexFloatLargeArray2.setComplexFloat(j6, fArr);
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr2 = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j8 = i2 * j7;
                    final long j9 = i2 == iMin + (-1) ? length : j8 + j7;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.34
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j10 = j8; j10 < j9; j10++) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                                fArr2[0] = (float) (FastMath.exp(complexFloat2[0]) * FastMath.cos(complexFloat2[1]));
                                fArr2[1] = (float) (FastMath.exp(complexFloat2[0]) * FastMath.sin(complexFloat2[1]));
                                complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                            }
                        }
                    });
                    i2++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr2);
                } catch (InterruptedException | ExecutionException unused2) {
                    float[] fArr2 = new float[2];
                    for (long j10 = 0; j10 < length; j10++) {
                        float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                        fArr2[0] = (float) (FastMath.exp(complexFloat2[0]) * FastMath.cos(complexFloat2[1]));
                        fArr2[1] = (float) (FastMath.exp(complexFloat2[0]) * FastMath.sin(complexFloat2[1]));
                        complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                double[] dArr = new double[2];
                for (long j11 = 0; j11 < length; j11++) {
                    double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j11);
                    dArr[0] = FastMath.exp(complexDouble[0]) * FastMath.cos(complexDouble[1]);
                    dArr[1] = FastMath.exp(complexDouble[0]) * FastMath.sin(complexDouble[1]);
                    complexDoubleLargeArray2.setComplexDouble(j11, dArr);
                }
            } else {
                long j12 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j13 = i3 * j12;
                    final long j14 = i3 == iMin + (-1) ? length : j13 + j12;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.35
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                                dArr2[0] = FastMath.exp(complexDouble2[0]) * FastMath.cos(complexDouble2[1]);
                                dArr2[1] = FastMath.exp(complexDouble2[0]) * FastMath.sin(complexDouble2[1]);
                                complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    double[] dArr2 = new double[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                        dArr2[0] = FastMath.exp(complexDouble2[0]) * FastMath.cos(complexDouble2[1]);
                        dArr2[1] = FastMath.exp(complexDouble2[0]) * FastMath.sin(complexDouble2[1]);
                        complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray abs(LargeArray largeArray) {
        return abs(largeArray, largeArray.getType() == LargeArrayType.COMPLEX_FLOAT ? LargeArrayType.FLOAT : largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE ? LargeArrayType.DOUBLE : largeArray.getType());
    }

    public static LargeArray abs(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Long.valueOf(FastMath.abs(largeArray.getLong(0L))));
            }
            if (largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.abs(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Float.valueOf(complexAbs(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(complexAbs(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L))));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (!largeArray.getType().isIntegerNumericType()) {
            if (!largeArray.getType().isRealNumericType()) {
                if (largeArray.getType() == LargeArrayType.COMPLEX_FLOAT) {
                    final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
                    if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for (long j2 = 0; j2 < length; j2++) {
                            float[] complexFloat = complexFloatLargeArray.getComplexFloat(j2);
                            float f = complexFloat[0];
                            float f2 = complexFloat[1];
                            largeArrayCreate.setFloat(j2, (float) FastMath.sqrt((f * f) + (f2 * f2)));
                        }
                        return largeArrayCreate;
                    }
                    long j3 = length / iMin;
                    Future[] futureArr = new Future[iMin];
                    int i = 0;
                    while (i < iMin) {
                        final long j4 = i * j3;
                        long j5 = i == iMin + (-1) ? length : j4 + j3;
                        final LargeArray largeArray2 = largeArrayCreate;
                        final long j6 = j5;
                        futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.38
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j7 = j4; j7 < j6; j7++) {
                                    float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j7);
                                    LargeArray largeArray3 = largeArray2;
                                    float f3 = complexFloat2[0];
                                    float f4 = complexFloat2[1];
                                    largeArray3.setFloat(j7, (float) FastMath.sqrt((f3 * f3) + (f4 * f4)));
                                }
                            }
                        });
                        i++;
                        largeArrayCreate = largeArray2;
                        iMin = iMin;
                    }
                    LargeArray largeArray3 = largeArrayCreate;
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr);
                        return largeArray3;
                    } catch (InterruptedException | ExecutionException unused) {
                        for (long j7 = 0; j7 < length; j7++) {
                            float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j7);
                            float f3 = complexFloat2[0];
                            float f4 = complexFloat2[1];
                            largeArray3.setFloat(j7, (float) FastMath.sqrt((f3 * f3) + (f4 * f4)));
                        }
                        return largeArray3;
                    }
                }
                if (largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                    final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
                    if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for (long j8 = 0; j8 < length; j8++) {
                            double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j8);
                            double d = complexDouble[0];
                            double d2 = complexDouble[1];
                            largeArrayCreate.setDouble(j8, FastMath.sqrt((d * d) + (d2 * d2)));
                        }
                        return largeArrayCreate;
                    }
                    long j9 = length / iMin;
                    Future[] futureArr2 = new Future[iMin];
                    int i2 = 0;
                    while (i2 < iMin) {
                        final long j10 = i2 * j9;
                        final long j11 = i2 == iMin + (-1) ? length : j10 + j9;
                        futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.39
                            @Override // java.lang.Runnable
                            public void run() {
                                for (long j12 = j10; j12 < j11; j12++) {
                                    double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j12);
                                    LargeArray largeArray4 = largeArrayCreate;
                                    double d3 = complexDouble2[0];
                                    double d4 = complexDouble2[1];
                                    largeArray4.setDouble(j12, FastMath.sqrt((d3 * d3) + (d4 * d4)));
                                }
                            }
                        });
                        i2++;
                    }
                    try {
                        ConcurrencyUtils.waitForCompletion(futureArr2);
                        return largeArrayCreate;
                    } catch (InterruptedException | ExecutionException unused2) {
                        for (long j12 = 0; j12 < length; j12++) {
                            double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j12);
                            double d3 = complexDouble2[0];
                            double d4 = complexDouble2[1];
                            largeArrayCreate.setDouble(j12, FastMath.sqrt((d3 * d3) + (d4 * d4)));
                        }
                        return largeArrayCreate;
                    }
                }
                throw new IllegalArgumentException("Invalid array type.");
            }
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, FastMath.abs(largeArray.getDouble(j)));
                    j++;
                }
            } else {
                long j13 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j14 = i3 * j13;
                    final long j15 = i3 == iMin + (-1) ? length : j14 + j13;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.37
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j16 = j14; j16 < j15; j16++) {
                                largeArrayCreate.setDouble(j16, FastMath.abs(largeArray.getDouble(j16)));
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    while (j < length) {
                        largeArrayCreate.setDouble(j, FastMath.abs(largeArray.getDouble(j)));
                        j++;
                    }
                }
            }
        } else if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < length) {
                largeArrayCreate.setDouble(j, FastMath.abs(largeArray.getDouble(j)));
                j++;
            }
        } else {
            long j16 = length / iMin;
            Future[] futureArr4 = new Future[iMin];
            int i4 = 0;
            while (i4 < iMin) {
                final long j17 = i4 * j16;
                final long j18 = i4 == iMin + (-1) ? length : j17 + j16;
                futureArr4[i4] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.36
                    @Override // java.lang.Runnable
                    public void run() {
                        for (long j19 = j17; j19 < j18; j19++) {
                            largeArrayCreate.setLong(j19, FastMath.abs(largeArray.getLong(j19)));
                        }
                    }
                });
                i4++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr4);
            } catch (InterruptedException | ExecutionException unused4) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, FastMath.abs(largeArray.getDouble(j)));
                    j++;
                }
            }
        }
        return largeArrayCreate;
    }

    public static LargeArray sin(LargeArray largeArray) {
        return sin(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray sin(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.sin(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexSin(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexSin(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setDouble(j, FastMath.sin(largeArray.getDouble(j)));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.40
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.sin(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setDouble(j5, FastMath.sin(largeArray.getDouble(j5)));
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                float[] fArr = new float[2];
                for (long j6 = 0; j6 < length; j6++) {
                    float[] complexFloat = complexFloatLargeArray.getComplexFloat(j6);
                    fArr[0] = (float) (FastMath.sin(complexFloat[0]) * FastMath.cosh(complexFloat[1]));
                    fArr[1] = (float) (FastMath.cos(complexFloat[0]) * FastMath.sinh(complexFloat[1]));
                    complexFloatLargeArray2.setComplexFloat(j6, fArr);
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr2 = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j8 = i2 * j7;
                    final long j9 = i2 == iMin + (-1) ? length : j8 + j7;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.41
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j10 = j8; j10 < j9; j10++) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                                fArr2[0] = (float) (FastMath.sin(complexFloat2[0]) * FastMath.cosh(complexFloat2[1]));
                                fArr2[1] = (float) (FastMath.cos(complexFloat2[0]) * FastMath.sinh(complexFloat2[1]));
                                complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                            }
                        }
                    });
                    i2++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr2);
                } catch (InterruptedException | ExecutionException unused2) {
                    float[] fArr2 = new float[2];
                    for (long j10 = 0; j10 < length; j10++) {
                        float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                        fArr2[0] = (float) (FastMath.sin(complexFloat2[0]) * FastMath.cosh(complexFloat2[1]));
                        fArr2[1] = (float) (FastMath.cos(complexFloat2[0]) * FastMath.sinh(complexFloat2[1]));
                        complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                double[] dArr = new double[2];
                for (long j11 = 0; j11 < length; j11++) {
                    double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j11);
                    dArr[0] = FastMath.sin(complexDouble[0]) * FastMath.cosh(complexDouble[1]);
                    dArr[1] = FastMath.cos(complexDouble[0]) * FastMath.sinh(complexDouble[1]);
                    complexDoubleLargeArray2.setComplexDouble(j11, dArr);
                }
            } else {
                long j12 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j13 = i3 * j12;
                    final long j14 = i3 == iMin + (-1) ? length : j13 + j12;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.42
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                                dArr2[0] = FastMath.sin(complexDouble2[0]) * FastMath.cosh(complexDouble2[1]);
                                dArr2[1] = FastMath.cos(complexDouble2[0]) * FastMath.sinh(complexDouble2[1]);
                                complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    double[] dArr2 = new double[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                        dArr2[0] = FastMath.sin(complexDouble2[0]) * FastMath.cosh(complexDouble2[1]);
                        dArr2[1] = FastMath.cos(complexDouble2[0]) * FastMath.sinh(complexDouble2[1]);
                        complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray cos(LargeArray largeArray) {
        return cos(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray cos(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.cos(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexCos(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexCos(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j = 0; j < length; j++) {
                    largeArrayCreate.setDouble(j, FastMath.cos(largeArray.getDouble(j)));
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i = 0;
                while (i < iMin) {
                    final long j3 = i * j2;
                    final long j4 = i == iMin + (-1) ? length : j3 + j2;
                    futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.43
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.cos(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    for (long j5 = 0; j5 < length; j5++) {
                        largeArrayCreate.setDouble(j5, FastMath.cos(largeArray.getDouble(j5)));
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                float[] fArr = new float[2];
                for (long j6 = 0; j6 < length; j6++) {
                    float[] complexFloat = complexFloatLargeArray.getComplexFloat(j6);
                    fArr[0] = (float) (FastMath.cos(complexFloat[0]) * FastMath.cosh(complexFloat[1]));
                    fArr[1] = (float) ((-FastMath.sin(complexFloat[0])) * FastMath.sinh(complexFloat[1]));
                    complexFloatLargeArray2.setComplexFloat(j6, fArr);
                }
            } else {
                long j7 = length / iMin;
                Future[] futureArr2 = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j8 = i2 * j7;
                    final long j9 = i2 == iMin + (-1) ? length : j8 + j7;
                    futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.44
                        @Override // java.lang.Runnable
                        public void run() {
                            float[] fArr2 = new float[2];
                            for (long j10 = j8; j10 < j9; j10++) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                                fArr2[0] = (float) (FastMath.cos(complexFloat2[0]) * FastMath.cosh(complexFloat2[1]));
                                fArr2[1] = (float) ((-FastMath.sin(complexFloat2[0])) * FastMath.sinh(complexFloat2[1]));
                                complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                            }
                        }
                    });
                    i2++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr2);
                } catch (InterruptedException | ExecutionException unused2) {
                    float[] fArr2 = new float[2];
                    for (long j10 = 0; j10 < length; j10++) {
                        float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j10);
                        fArr2[0] = (float) (FastMath.cos(complexFloat2[0]) * FastMath.cosh(complexFloat2[1]));
                        fArr2[1] = (float) ((-FastMath.sin(complexFloat2[0])) * FastMath.sinh(complexFloat2[1]));
                        complexFloatLargeArray2.setComplexFloat(j10, fArr2);
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                double[] dArr = new double[2];
                for (long j11 = 0; j11 < length; j11++) {
                    double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j11);
                    dArr[0] = FastMath.cos(complexDouble[0]) * FastMath.cosh(complexDouble[1]);
                    dArr[1] = (-FastMath.sin(complexDouble[0])) * FastMath.sinh(complexDouble[1]);
                    complexDoubleLargeArray2.setComplexDouble(j11, dArr);
                }
            } else {
                long j12 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                int i3 = 0;
                while (i3 < iMin) {
                    final long j13 = i3 * j12;
                    final long j14 = i3 == iMin + (-1) ? length : j13 + j12;
                    futureArr3[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.45
                        @Override // java.lang.Runnable
                        public void run() {
                            double[] dArr2 = new double[2];
                            for (long j15 = j13; j15 < j14; j15++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                                dArr2[0] = FastMath.cos(complexDouble2[0]) * FastMath.cosh(complexDouble2[1]);
                                dArr2[1] = (-FastMath.sin(complexDouble2[0])) * FastMath.sinh(complexDouble2[1]);
                                complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                            }
                        }
                    });
                    i3++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused3) {
                    double[] dArr2 = new double[2];
                    for (long j15 = 0; j15 < length; j15++) {
                        double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j15);
                        dArr2[0] = FastMath.cos(complexDouble2[0]) * FastMath.cosh(complexDouble2[1]);
                        dArr2[1] = (-FastMath.sin(complexDouble2[0])) * FastMath.sinh(complexDouble2[1]);
                        complexDoubleLargeArray2.setComplexDouble(j15, dArr2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray tan(LargeArray largeArray) {
        return tan(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray tan(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.tan(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexTan(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexTan(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int i = 0;
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, FastMath.tan(largeArray.getDouble(j)));
                    j++;
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j3 = i2 * j2;
                    final long j4 = i2 == iMin + (-1) ? length : j3 + j2;
                    int i3 = i2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.46
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.tan(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i2 = i3 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    while (j < length) {
                        largeArrayCreate.setDouble(j, FastMath.tan(largeArray.getDouble(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    float[] complexFloat = complexFloatLargeArray.getComplexFloat(j);
                    complexFloatLargeArray2.setComplexFloat(j, complexDiv(complexSin(complexFloat), complexCos(complexFloat)));
                    j++;
                }
            } else {
                long j5 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                while (i < iMin) {
                    final long j6 = i * j5;
                    final long j7 = i == iMin + (-1) ? length : j6 + j5;
                    futureArr3[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.47
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j8 = j6; j8 < j7; j8++) {
                                float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j8);
                                complexFloatLargeArray2.setComplexFloat(j8, LargeArrayArithmetics.complexDiv(LargeArrayArithmetics.complexSin(complexFloat2), LargeArrayArithmetics.complexCos(complexFloat2)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    while (j < length) {
                        float[] complexFloat2 = complexFloatLargeArray.getComplexFloat(j);
                        complexFloatLargeArray2.setComplexFloat(j, complexDiv(complexSin(complexFloat2), complexCos(complexFloat2)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    double[] complexDouble = complexDoubleLargeArray.getComplexDouble(j);
                    complexDoubleLargeArray2.setComplexDouble(j, complexDiv(complexSin(complexDouble), complexCos(complexDouble)));
                    j++;
                }
            } else {
                long j8 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                while (i < iMin) {
                    final long j9 = i * j8;
                    final long j10 = i == iMin + (-1) ? length : j9 + j8;
                    futureArr4[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.48
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j11 = j9; j11 < j10; j11++) {
                                double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j11);
                                complexDoubleLargeArray2.setComplexDouble(j11, LargeArrayArithmetics.complexDiv(LargeArrayArithmetics.complexSin(complexDouble2), LargeArrayArithmetics.complexCos(complexDouble2)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                } catch (InterruptedException | ExecutionException unused3) {
                    while (j < length) {
                        double[] complexDouble2 = complexDoubleLargeArray.getComplexDouble(j);
                        complexDoubleLargeArray2.setComplexDouble(j, complexDiv(complexSin(complexDouble2), complexCos(complexDouble2)));
                        j++;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray asin(LargeArray largeArray) {
        return asin(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray asin(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.asin(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexAsin(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexAsin(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int i = 0;
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, FastMath.asin(largeArray.getDouble(j)));
                    j++;
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j3 = i2 * j2;
                    final long j4 = i2 == iMin + (-1) ? length : j3 + j2;
                    int i3 = i2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.49
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.asin(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i2 = i3 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    while (j < length) {
                        largeArrayCreate.setDouble(j, FastMath.asin(largeArray.getDouble(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    complexFloatLargeArray2.setComplexFloat(j, complexAsin(complexFloatLargeArray.getComplexFloat(j)));
                    j++;
                }
            } else {
                long j5 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                while (i < iMin) {
                    final long j6 = i * j5;
                    final long j7 = i == iMin + (-1) ? length : j6 + j5;
                    futureArr3[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.50
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j8 = j6; j8 < j7; j8++) {
                                complexFloatLargeArray2.setComplexFloat(j8, LargeArrayArithmetics.complexAsin(complexFloatLargeArray.getComplexFloat(j8)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    while (j < length) {
                        complexFloatLargeArray2.setComplexFloat(j, complexAsin(complexFloatLargeArray.getComplexFloat(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    complexDoubleLargeArray2.setComplexDouble(j, complexAsin(complexDoubleLargeArray.getComplexDouble(j)));
                    j++;
                }
            } else {
                long j8 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                while (i < iMin) {
                    final long j9 = i * j8;
                    final long j10 = i == iMin + (-1) ? length : j9 + j8;
                    futureArr4[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.51
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j11 = j9; j11 < j10; j11++) {
                                complexDoubleLargeArray2.setComplexDouble(j11, LargeArrayArithmetics.complexAsin(complexDoubleLargeArray.getComplexDouble(j11)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                } catch (InterruptedException | ExecutionException unused3) {
                    while (j < length) {
                        complexDoubleLargeArray2.setComplexDouble(j, complexAsin(complexDoubleLargeArray.getComplexDouble(j)));
                        j++;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray acos(LargeArray largeArray) {
        return acos(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray acos(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.acos(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexAcos(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexAcos(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int i = 0;
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, FastMath.acos(largeArray.getDouble(j)));
                    j++;
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j3 = i2 * j2;
                    final long j4 = i2 == iMin + (-1) ? length : j3 + j2;
                    int i3 = i2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.52
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.acos(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i2 = i3 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    while (j < length) {
                        largeArrayCreate.setDouble(j, FastMath.acos(largeArray.getDouble(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    complexFloatLargeArray2.setComplexFloat(j, complexAcos(complexFloatLargeArray.getComplexFloat(j)));
                    j++;
                }
            } else {
                long j5 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                while (i < iMin) {
                    final long j6 = i * j5;
                    final long j7 = i == iMin + (-1) ? length : j6 + j5;
                    futureArr3[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.53
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j8 = j6; j8 < j7; j8++) {
                                complexFloatLargeArray2.setComplexFloat(j8, LargeArrayArithmetics.complexAcos(complexFloatLargeArray.getComplexFloat(j8)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    while (j < length) {
                        complexFloatLargeArray2.setComplexFloat(j, complexAcos(complexFloatLargeArray.getComplexFloat(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    complexDoubleLargeArray2.setComplexDouble(j, complexAcos(complexDoubleLargeArray.getComplexDouble(j)));
                    j++;
                }
            } else {
                long j8 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                while (i < iMin) {
                    final long j9 = i * j8;
                    final long j10 = i == iMin + (-1) ? length : j9 + j8;
                    futureArr4[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.54
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j11 = j9; j11 < j10; j11++) {
                                complexDoubleLargeArray2.setComplexDouble(j11, LargeArrayArithmetics.complexAcos(complexDoubleLargeArray.getComplexDouble(j11)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                } catch (InterruptedException | ExecutionException unused3) {
                    while (j < length) {
                        complexDoubleLargeArray2.setComplexDouble(j, complexAcos(complexDoubleLargeArray.getComplexDouble(j)));
                        j++;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray atan(LargeArray largeArray) {
        return atan(largeArray, largeArray.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : largeArray.getType());
    }

    public static LargeArray atan(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric()) {
            throw new IllegalArgumentException("a == null || !a.isNumeric()");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
                return LargeArrayUtils.createConstant(largeArrayType, length, Double.valueOf(FastMath.atan(largeArray.getDouble(0L))));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexAtan(((ComplexFloatLargeArray) largeArray).getComplexFloat(0L)));
            }
            if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
                return LargeArrayUtils.createConstant(largeArrayType, length, complexAtan(((ComplexDoubleLargeArray) largeArray).getComplexDouble(0L)));
            }
            throw new IllegalArgumentException("Invalid array type.");
        }
        int i = 0;
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (largeArrayType.isIntegerNumericType() || largeArrayType.isRealNumericType()) {
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    largeArrayCreate.setDouble(j, FastMath.atan(largeArray.getDouble(j)));
                    j++;
                }
            } else {
                long j2 = length / iMin;
                Future[] futureArr = new Future[iMin];
                int i2 = 0;
                while (i2 < iMin) {
                    final long j3 = i2 * j2;
                    final long j4 = i2 == iMin + (-1) ? length : j3 + j2;
                    int i3 = i2;
                    Future[] futureArr2 = futureArr;
                    futureArr2[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.55
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j5 = j3; j5 < j4; j5++) {
                                largeArrayCreate.setDouble(j5, FastMath.atan(largeArray.getDouble(j5)));
                            }
                        }
                    });
                    i2 = i3 + 1;
                    futureArr = futureArr2;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr);
                } catch (InterruptedException | ExecutionException unused) {
                    while (j < length) {
                        largeArrayCreate.setDouble(j, FastMath.atan(largeArray.getDouble(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_FLOAT) {
            final ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) largeArray;
            final ComplexFloatLargeArray complexFloatLargeArray2 = (ComplexFloatLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    complexFloatLargeArray2.setComplexFloat(j, complexAtan(complexFloatLargeArray.getComplexFloat(j)));
                    j++;
                }
            } else {
                long j5 = length / iMin;
                Future[] futureArr3 = new Future[iMin];
                while (i < iMin) {
                    final long j6 = i * j5;
                    final long j7 = i == iMin + (-1) ? length : j6 + j5;
                    futureArr3[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.56
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j8 = j6; j8 < j7; j8++) {
                                complexFloatLargeArray2.setComplexFloat(j8, LargeArrayArithmetics.complexAtan(complexFloatLargeArray.getComplexFloat(j8)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr3);
                } catch (InterruptedException | ExecutionException unused2) {
                    while (j < length) {
                        complexFloatLargeArray2.setComplexFloat(j, complexAtan(complexFloatLargeArray.getComplexFloat(j)));
                        j++;
                    }
                }
            }
        } else if (largeArrayType == LargeArrayType.COMPLEX_DOUBLE) {
            final ComplexDoubleLargeArray complexDoubleLargeArray = (ComplexDoubleLargeArray) largeArray;
            final ComplexDoubleLargeArray complexDoubleLargeArray2 = (ComplexDoubleLargeArray) largeArrayCreate;
            if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                while (j < length) {
                    complexDoubleLargeArray2.setComplexDouble(j, complexAtan(complexDoubleLargeArray.getComplexDouble(j)));
                    j++;
                }
            } else {
                long j8 = length / iMin;
                Future[] futureArr4 = new Future[iMin];
                while (i < iMin) {
                    final long j9 = i * j8;
                    final long j10 = i == iMin + (-1) ? length : j9 + j8;
                    futureArr4[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.57
                        @Override // java.lang.Runnable
                        public void run() {
                            for (long j11 = j9; j11 < j10; j11++) {
                                complexDoubleLargeArray2.setComplexDouble(j11, LargeArrayArithmetics.complexAtan(complexDoubleLargeArray.getComplexDouble(j11)));
                            }
                        }
                    });
                    i++;
                }
                try {
                    ConcurrencyUtils.waitForCompletion(futureArr4);
                } catch (InterruptedException | ExecutionException unused3) {
                    while (j < length) {
                        complexDoubleLargeArray2.setComplexDouble(j, complexAtan(complexDoubleLargeArray.getComplexDouble(j)));
                        j++;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid array type.");
        }
        return largeArrayCreate;
    }

    public static LargeArray signum(LargeArray largeArray) {
        return signum(largeArray, LargeArrayType.BYTE);
    }

    public static LargeArray signum(final LargeArray largeArray, LargeArrayType largeArrayType) {
        if (largeArray == null || !largeArray.isNumeric() || largeArray.getType() == LargeArrayType.COMPLEX_FLOAT || largeArray.getType() == LargeArrayType.COMPLEX_DOUBLE) {
            throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
        }
        if (!largeArrayType.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
        }
        long length = largeArray.length();
        long j = 0;
        if (largeArray.isConstant()) {
            return LargeArrayUtils.createConstant(largeArrayType, length, Byte.valueOf((byte) FastMath.signum(largeArray.getDouble(0L))));
        }
        final LargeArray largeArrayCreate = LargeArrayUtils.create(largeArrayType, length, false);
        int iMin = (int) FastMath.min(length, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < length) {
                largeArrayCreate.setByte(j, (byte) FastMath.signum(largeArray.getDouble(j)));
                j++;
            }
        } else {
            long j2 = length / iMin;
            Future[] futureArr = new Future[iMin];
            int i = 0;
            while (i < iMin) {
                final long j3 = i * j2;
                final long j4 = i == iMin + (-1) ? length : j3 + j2;
                int i2 = i;
                Future[] futureArr2 = futureArr;
                futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayArithmetics.58
                    @Override // java.lang.Runnable
                    public void run() {
                        for (long j5 = j3; j5 < j4; j5++) {
                            largeArrayCreate.setByte(j5, (byte) FastMath.signum(largeArray.getDouble(j5)));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException | ExecutionException unused) {
                while (j < length) {
                    largeArrayCreate.setByte(j, (byte) FastMath.signum(largeArray.getDouble(j)));
                    j++;
                }
            }
        }
        return largeArrayCreate;
    }
}
