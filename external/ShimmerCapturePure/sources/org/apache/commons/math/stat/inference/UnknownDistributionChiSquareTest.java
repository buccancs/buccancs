package org.apache.commons.math.stat.inference;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/inference/UnknownDistributionChiSquareTest.class */
public interface UnknownDistributionChiSquareTest extends ChiSquareTest {
    double chiSquareDataSetsComparison(long[] jArr, long[] jArr2) throws IllegalArgumentException;

    double chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2) throws MathException, IllegalArgumentException;

    boolean chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws MathException, IllegalArgumentException;
}
