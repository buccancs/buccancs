package org.apache.commons.math.util;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/util/DoubleArray.class */
public interface DoubleArray {
    int getNumElements();

    double getElement(int i);

    void setElement(int i, double d);

    void addElement(double d);

    double addElementRolling(double d);

    double[] getElements();

    void clear();
}
