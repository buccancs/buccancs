package org.apache.commons.math3.analysis;

@Deprecated
/* loaded from: classes5.dex */
public interface DifferentiableMultivariateFunction extends MultivariateFunction {
    MultivariateVectorFunction gradient();

    MultivariateFunction partialDerivative(int i);
}
