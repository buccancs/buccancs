package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class NeighbourhoodSizeFunctionFactory {
    private NeighbourhoodSizeFunctionFactory() {
    }

    public static NeighbourhoodSizeFunction exponentialDecay(double d, double d2, long j) {
        return new NeighbourhoodSizeFunction(d, d2, j) { // from class: org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunctionFactory.1
            final /* synthetic */ double val$initValue;
            final /* synthetic */ long val$numCall;
            final /* synthetic */ double val$valueAtNumCall;
            private final ExponentialDecayFunction decay;

            {
                this.val$initValue = d;
                this.val$valueAtNumCall = d2;
                this.val$numCall = j;
                this.decay = new ExponentialDecayFunction(d, d2, j);
            }

            @Override // org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction
            public int value(long j2) {
                return (int) FastMath.rint(this.decay.value(j2));
            }
        };
    }

    public static NeighbourhoodSizeFunction quasiSigmoidDecay(double d, double d2, long j) {
        return new NeighbourhoodSizeFunction(d, d2, j) { // from class: org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunctionFactory.2
            final /* synthetic */ double val$initValue;
            final /* synthetic */ long val$numCall;
            final /* synthetic */ double val$slope;
            private final QuasiSigmoidDecayFunction decay;

            {
                this.val$initValue = d;
                this.val$slope = d2;
                this.val$numCall = j;
                this.decay = new QuasiSigmoidDecayFunction(d, d2, j);
            }

            @Override // org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction
            public int value(long j2) {
                return (int) FastMath.rint(this.decay.value(j2));
            }
        };
    }
}
