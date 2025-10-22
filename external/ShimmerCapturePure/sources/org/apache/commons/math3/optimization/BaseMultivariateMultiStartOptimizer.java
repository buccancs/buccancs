package org.apache.commons.math3.optimization;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
/* loaded from: classes5.dex */
public class BaseMultivariateMultiStartOptimizer<FUNC extends MultivariateFunction> implements BaseMultivariateOptimizer<FUNC> {
    private final BaseMultivariateOptimizer<FUNC> optimizer;
    private RandomVectorGenerator generator;
    private int maxEvaluations;
    private PointValuePair[] optima;
    private int starts;
    private int totalEvaluations;

    protected BaseMultivariateMultiStartOptimizer(BaseMultivariateOptimizer<FUNC> baseMultivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        if (baseMultivariateOptimizer == null || randomVectorGenerator == null) {
            throw new NullArgumentException();
        }
        if (i < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.optimizer = baseMultivariateOptimizer;
        this.starts = i;
        this.generator = randomVectorGenerator;
    }

    @Override // org.apache.commons.math3.optimization.BaseOptimizer
    public int getEvaluations() {
        return this.totalEvaluations;
    }

    @Override // org.apache.commons.math3.optimization.BaseOptimizer
    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    public PointValuePair[] getOptima() {
        PointValuePair[] pointValuePairArr = this.optima;
        if (pointValuePairArr == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
        }
        return (PointValuePair[]) pointValuePairArr.clone();
    }

    @Override // org.apache.commons.math3.optimization.BaseOptimizer
    public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    @Override // org.apache.commons.math3.optimization.BaseMultivariateOptimizer
    public PointValuePair optimize(int i, FUNC func, GoalType goalType, double[] dArr) {
        this.maxEvaluations = i;
        this.optima = new PointValuePair[this.starts];
        this.totalEvaluations = 0;
        RuntimeException e = null;
        int i2 = 0;
        while (i2 < this.starts) {
            try {
                this.optima[i2] = this.optimizer.optimize(i - this.totalEvaluations, func, goalType, i2 == 0 ? dArr : this.generator.nextVector());
            } catch (RuntimeException e2) {
                e = e2;
                this.optima[i2] = null;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
            i2++;
        }
        sortPairs(goalType);
        PointValuePair pointValuePair = this.optima[0];
        if (pointValuePair != null) {
            return pointValuePair;
        }
        throw e;
    }

    private void sortPairs(final GoalType goalType) {
        Arrays.sort(this.optima, new Comparator<PointValuePair>() { // from class: org.apache.commons.math3.optimization.BaseMultivariateMultiStartOptimizer.1
            @Override // java.util.Comparator
            public int compare(PointValuePair pointValuePair, PointValuePair pointValuePair2) {
                if (pointValuePair == null) {
                    return pointValuePair2 == null ? 0 : 1;
                }
                if (pointValuePair2 == null) {
                    return -1;
                }
                double dDoubleValue = pointValuePair.getValue().doubleValue();
                double dDoubleValue2 = pointValuePair2.getValue().doubleValue();
                return goalType == GoalType.MINIMIZE ? Double.compare(dDoubleValue, dDoubleValue2) : Double.compare(dDoubleValue2, dDoubleValue);
            }
        });
    }
}
