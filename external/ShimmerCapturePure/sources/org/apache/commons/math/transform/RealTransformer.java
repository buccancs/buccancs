package org.apache.commons.math.transform;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/transform/RealTransformer.class */
public interface RealTransformer {
    double[] transform(double[] dArr) throws IllegalArgumentException;

    double[] transform(UnivariateRealFunction univariateRealFunction, double d, double d2, int i) throws FunctionEvaluationException, IllegalArgumentException;

    double[] inversetransform(double[] dArr) throws IllegalArgumentException;

    double[] inversetransform(UnivariateRealFunction univariateRealFunction, double d, double d2, int i) throws FunctionEvaluationException, IllegalArgumentException;
}
