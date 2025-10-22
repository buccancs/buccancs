package org.apache.commons.math.distribution;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/Distribution.class */
public interface Distribution {
    double cumulativeProbability(double d) throws MathException;

    double cumulativeProbability(double d, double d2) throws MathException;
}
