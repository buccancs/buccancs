package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.optim.OptimizationData;

@Deprecated
/* loaded from: classes5.dex */
public class ModelFunctionJacobian implements OptimizationData {
    private final MultivariateMatrixFunction jacobian;

    public ModelFunctionJacobian(MultivariateMatrixFunction multivariateMatrixFunction) {
        this.jacobian = multivariateMatrixFunction;
    }

    public MultivariateMatrixFunction getModelFunctionJacobian() {
        return this.jacobian;
    }
}
