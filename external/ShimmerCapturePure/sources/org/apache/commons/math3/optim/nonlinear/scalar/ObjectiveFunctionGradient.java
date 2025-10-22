package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.OptimizationData;

/* loaded from: classes5.dex */
public class ObjectiveFunctionGradient implements OptimizationData {
    private final MultivariateVectorFunction gradient;

    public ObjectiveFunctionGradient(MultivariateVectorFunction multivariateVectorFunction) {
        this.gradient = multivariateVectorFunction;
    }

    public MultivariateVectorFunction getObjectiveFunctionGradient() {
        return this.gradient;
    }
}
