package org.apache.commons.math.analysis.integration;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.ConvergingAlgorithm;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/integration/UnivariateRealIntegrator.class */
public interface UnivariateRealIntegrator extends ConvergingAlgorithm {
    int getMinimalIterationCount();

    void setMinimalIterationCount(int i);

    void resetMinimalIterationCount();

    @Deprecated
    double integrate(double d, double d2) throws FunctionEvaluationException, IllegalArgumentException, ConvergenceException;

    double integrate(UnivariateRealFunction univariateRealFunction, double d, double d2) throws FunctionEvaluationException, IllegalArgumentException, ConvergenceException;

    double getResult() throws IllegalStateException;
}
