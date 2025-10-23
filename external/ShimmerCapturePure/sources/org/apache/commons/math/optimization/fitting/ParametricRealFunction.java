package org.apache.commons.math.optimization.fitting;

import org.apache.commons.math.FunctionEvaluationException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/ParametricRealFunction.class */
public interface ParametricRealFunction {
    double value(double d, double[] dArr) throws FunctionEvaluationException;

    double[] gradient(double d, double[] dArr) throws FunctionEvaluationException;
}
