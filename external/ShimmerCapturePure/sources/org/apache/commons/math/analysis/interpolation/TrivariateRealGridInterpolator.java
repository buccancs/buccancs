package org.apache.commons.math.analysis.interpolation;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.TrivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/TrivariateRealGridInterpolator.class */
public interface TrivariateRealGridInterpolator {
    TrivariateRealFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws MathException;
}
