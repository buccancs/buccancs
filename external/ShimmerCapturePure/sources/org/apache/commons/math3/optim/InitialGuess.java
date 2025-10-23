package org.apache.commons.math3.optim;

/* loaded from: classes5.dex */
public class InitialGuess implements OptimizationData {
    private final double[] init;

    public InitialGuess(double[] dArr) {
        this.init = (double[]) dArr.clone();
    }

    public double[] getInitialGuess() {
        return (double[]) this.init.clone();
    }
}
