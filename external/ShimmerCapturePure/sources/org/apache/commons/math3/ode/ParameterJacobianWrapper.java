package org.apache.commons.math3.ode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
class ParameterJacobianWrapper implements ParameterJacobianProvider {
    private final FirstOrderDifferentialEquations fode;
    private final Map<String, Double> hParam = new HashMap();
    private final ParameterizedODE pode;

    ParameterJacobianWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations, ParameterizedODE parameterizedODE, ParameterConfiguration[] parameterConfigurationArr) {
        this.fode = firstOrderDifferentialEquations;
        this.pode = parameterizedODE;
        for (ParameterConfiguration parameterConfiguration : parameterConfigurationArr) {
            String parameterName = parameterConfiguration.getParameterName();
            if (parameterizedODE.isSupported(parameterName)) {
                this.hParam.put(parameterName, Double.valueOf(parameterConfiguration.getHP()));
            }
        }
    }

    @Override // org.apache.commons.math3.ode.Parameterizable
    public Collection<String> getParametersNames() {
        return this.pode.getParametersNames();
    }

    @Override // org.apache.commons.math3.ode.Parameterizable
    public boolean isSupported(String str) {
        return this.pode.isSupported(str);
    }

    @Override // org.apache.commons.math3.ode.ParameterJacobianProvider
    public void computeParameterJacobian(double d, double[] dArr, double[] dArr2, String str, double[] dArr3) throws UnknownParameterException, MaxCountExceededException, DimensionMismatchException {
        int dimension = this.fode.getDimension();
        if (this.pode.isSupported(str)) {
            double[] dArr4 = new double[dimension];
            double parameter = this.pode.getParameter(str);
            double dDoubleValue = this.hParam.get(str).doubleValue();
            this.pode.setParameter(str, parameter + dDoubleValue);
            this.fode.computeDerivatives(d, dArr, dArr4);
            for (int i = 0; i < dimension; i++) {
                dArr3[i] = (dArr4[i] - dArr2[i]) / dDoubleValue;
            }
            this.pode.setParameter(str, parameter);
            return;
        }
        Arrays.fill(dArr3, 0, dimension, 0.0d);
    }
}
