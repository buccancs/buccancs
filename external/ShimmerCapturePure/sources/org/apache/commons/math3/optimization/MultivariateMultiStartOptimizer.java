package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
/* loaded from: classes5.dex */
public class MultivariateMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    public MultivariateMultiStartOptimizer(MultivariateOptimizer multivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        super(multivariateOptimizer, i, randomVectorGenerator);
    }
}
