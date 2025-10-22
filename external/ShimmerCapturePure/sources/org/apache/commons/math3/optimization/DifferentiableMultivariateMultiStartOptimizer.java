package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.DifferentiableMultivariateFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
/* loaded from: classes5.dex */
public class DifferentiableMultivariateMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer<DifferentiableMultivariateFunction> implements DifferentiableMultivariateOptimizer {
    public DifferentiableMultivariateMultiStartOptimizer(DifferentiableMultivariateOptimizer differentiableMultivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        super(differentiableMultivariateOptimizer, i, randomVectorGenerator);
    }
}
