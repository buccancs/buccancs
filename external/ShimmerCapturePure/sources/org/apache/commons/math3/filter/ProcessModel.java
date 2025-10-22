package org.apache.commons.math3.filter;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/* loaded from: classes5.dex */
public interface ProcessModel {
    RealMatrix getControlMatrix();

    RealMatrix getInitialErrorCovariance();

    RealVector getInitialStateEstimate();

    RealMatrix getProcessNoise();

    RealMatrix getStateTransitionMatrix();
}
