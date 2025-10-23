package org.apache.commons.math.stat.inference;

import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.descriptive.StatisticalSummary;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/inference/TTest.class */
public interface TTest {
    double pairedT(double[] dArr, double[] dArr2) throws MathException, IllegalArgumentException;

    double pairedTTest(double[] dArr, double[] dArr2) throws MathException, IllegalArgumentException;

    boolean pairedTTest(double[] dArr, double[] dArr2, double d) throws MathException, IllegalArgumentException;

    double t(double d, double[] dArr) throws IllegalArgumentException;

    double t(double d, StatisticalSummary statisticalSummary) throws IllegalArgumentException;

    double homoscedasticT(double[] dArr, double[] dArr2) throws IllegalArgumentException;

    double t(double[] dArr, double[] dArr2) throws IllegalArgumentException;

    double t(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws IllegalArgumentException;

    double homoscedasticT(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws IllegalArgumentException;

    double tTest(double d, double[] dArr) throws MathException, IllegalArgumentException;

    boolean tTest(double d, double[] dArr, double d2) throws MathException, IllegalArgumentException;

    double tTest(double d, StatisticalSummary statisticalSummary) throws MathException, IllegalArgumentException;

    boolean tTest(double d, StatisticalSummary statisticalSummary, double d2) throws MathException, IllegalArgumentException;

    double tTest(double[] dArr, double[] dArr2) throws MathException, IllegalArgumentException;

    double homoscedasticTTest(double[] dArr, double[] dArr2) throws MathException, IllegalArgumentException;

    boolean tTest(double[] dArr, double[] dArr2, double d) throws MathException, IllegalArgumentException;

    boolean homoscedasticTTest(double[] dArr, double[] dArr2, double d) throws MathException, IllegalArgumentException;

    double tTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws MathException, IllegalArgumentException;

    double homoscedasticTTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws MathException, IllegalArgumentException;

    boolean tTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2, double d) throws MathException, IllegalArgumentException;
}
