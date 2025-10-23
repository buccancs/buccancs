package org.apache.commons.math.estimation;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/estimation/Estimator.class */
public interface Estimator {
    void estimate(EstimationProblem estimationProblem) throws EstimationException;

    double getRMS(EstimationProblem estimationProblem);

    double[][] getCovariances(EstimationProblem estimationProblem) throws EstimationException;

    double[] guessParametersErrors(EstimationProblem estimationProblem) throws EstimationException;
}
