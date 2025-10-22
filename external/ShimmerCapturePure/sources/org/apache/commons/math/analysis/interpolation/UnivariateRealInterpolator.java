package org.apache.commons.math.analysis.interpolation;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/UnivariateRealInterpolator.class */
public interface UnivariateRealInterpolator {
    UnivariateRealFunction interpolate(double[] dArr, double[] dArr2) throws MathException;
}
