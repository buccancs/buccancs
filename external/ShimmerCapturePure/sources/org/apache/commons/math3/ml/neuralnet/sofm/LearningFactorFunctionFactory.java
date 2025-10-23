package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;

/* loaded from: classes5.dex */
public class LearningFactorFunctionFactory {
    private LearningFactorFunctionFactory() {
    }

    public static LearningFactorFunction exponentialDecay(double d, double d2, long j) {
        if (d <= 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        }
        return new LearningFactorFunction(d, d2, j) { // from class: org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunctionFactory.1
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

            @Override // org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction
            public double value(long j2) {
                return this.decay.value(j2);
            }
        };
    }

    public static LearningFactorFunction quasiSigmoidDecay(double d, double d2, long j) {
        if (d <= 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        }
        return new LearningFactorFunction(d, d2, j) { // from class: org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunctionFactory.2
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

            @Override // org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction
            public double value(long j2) {
                return this.decay.value(j2);
            }
        };
    }
}
