package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
/* loaded from: classes5.dex */
public class MultivariateDifferentiableMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer<MultivariateDifferentiableFunction> implements MultivariateDifferentiableOptimizer {
    public MultivariateDifferentiableMultiStartOptimizer(MultivariateDifferentiableOptimizer multivariateDifferentiableOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        super(multivariateDifferentiableOptimizer, i, randomVectorGenerator);
    }
}
