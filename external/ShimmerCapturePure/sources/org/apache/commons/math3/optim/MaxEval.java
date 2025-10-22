package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;

/* loaded from: classes5.dex */
public class MaxEval implements OptimizationData {
    private final int maxEval;

    public MaxEval(int i) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.maxEval = i;
    }

    public static MaxEval unlimited() {
        return new MaxEval(Integer.MAX_VALUE);
    }

    public int getMaxEval() {
        return this.maxEval;
    }
}
