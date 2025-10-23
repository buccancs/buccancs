package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.OptimizationProblem;

/* loaded from: classes5.dex */
public interface LeastSquaresProblem extends OptimizationProblem<Evaluation> {

    Evaluation evaluate(RealVector realVector);

    int getObservationSize();

    int getParameterSize();

    RealVector getStart();

    public interface Evaluation {
        double getCost();

        RealMatrix getCovariances(double d);

        RealMatrix getJacobian();

        RealVector getPoint();

        double getRMS();

        RealVector getResiduals();

        RealVector getSigma(double d);
    }
}
