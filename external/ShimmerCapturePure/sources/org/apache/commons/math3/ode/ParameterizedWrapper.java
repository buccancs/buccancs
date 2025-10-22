package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
class ParameterizedWrapper implements ParameterizedODE {
    private final FirstOrderDifferentialEquations fode;

    ParameterizedWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations) {
        this.fode = firstOrderDifferentialEquations;
    }

    @Override // org.apache.commons.math3.ode.Parameterizable
    public boolean isSupported(String str) {
        return false;
    }

    @Override // org.apache.commons.math3.ode.ParameterizedODE
    public void setParameter(String str, double d) {
    }

    public int getDimension() {
        return this.fode.getDimension();
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
        this.fode.computeDerivatives(d, dArr, dArr2);
    }

    @Override // org.apache.commons.math3.ode.Parameterizable
    public Collection<String> getParametersNames() {
        return new ArrayList();
    }

    @Override // org.apache.commons.math3.ode.ParameterizedODE
    public double getParameter(String str) throws UnknownParameterException {
        if (isSupported(str)) {
            return Double.NaN;
        }
        throw new UnknownParameterException(str);
    }
}
