package org.apache.commons.math3.ode.sampling;

import java.io.Externalizable;

import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
public interface StepInterpolator extends Externalizable {
    StepInterpolator copy() throws MaxCountExceededException;

    double getCurrentTime();

    double[] getInterpolatedDerivatives() throws MaxCountExceededException;

    double[] getInterpolatedSecondaryDerivatives(int i) throws MaxCountExceededException;

    double[] getInterpolatedSecondaryState(int i) throws MaxCountExceededException;

    double[] getInterpolatedState() throws MaxCountExceededException;

    double getInterpolatedTime();

    void setInterpolatedTime(double d);

    double getPreviousTime();

    boolean isForward();
}
