package org.apache.commons.math;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ConvergingAlgorithm.class */
public interface ConvergingAlgorithm {
    int getMaximalIterationCount();

    void setMaximalIterationCount(int i);

    void resetMaximalIterationCount();

    double getAbsoluteAccuracy();

    void setAbsoluteAccuracy(double d);

    void resetAbsoluteAccuracy();

    double getRelativeAccuracy();

    void setRelativeAccuracy(double d);

    void resetRelativeAccuracy();

    int getIterationCount();
}
