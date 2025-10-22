package org.apache.commons.math.stat.inference;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/inference/ChiSquareTest.class */
public interface ChiSquareTest {
    double chiSquare(double[] dArr, long[] jArr) throws IllegalArgumentException;

    double chiSquareTest(double[] dArr, long[] jArr) throws MathException, IllegalArgumentException;

    boolean chiSquareTest(double[] dArr, long[] jArr, double d) throws MathException, IllegalArgumentException;

    double chiSquare(long[][] jArr) throws IllegalArgumentException;

    double chiSquareTest(long[][] jArr) throws MathException, IllegalArgumentException;

    boolean chiSquareTest(long[][] jArr, double d) throws MathException, IllegalArgumentException;
}
