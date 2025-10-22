package org.apache.commons.math.stat.inference;

import java.util.Collection;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/inference/OneWayAnova.class */
public interface OneWayAnova {
    double anovaFValue(Collection<double[]> collection) throws MathException, IllegalArgumentException;

    double anovaPValue(Collection<double[]> collection) throws MathException, IllegalArgumentException;

    boolean anovaTest(Collection<double[]> collection, double d) throws MathException, IllegalArgumentException;
}
