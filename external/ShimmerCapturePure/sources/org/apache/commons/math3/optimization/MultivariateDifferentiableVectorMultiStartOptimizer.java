package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
/* loaded from: classes5.dex */
public class MultivariateDifferentiableVectorMultiStartOptimizer extends BaseMultivariateVectorMultiStartOptimizer<MultivariateDifferentiableVectorFunction> implements MultivariateDifferentiableVectorOptimizer {
    public MultivariateDifferentiableVectorMultiStartOptimizer(MultivariateDifferentiableVectorOptimizer multivariateDifferentiableVectorOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        super(multivariateDifferentiableVectorOptimizer, i, randomVectorGenerator);
    }
}
