package org.apache.commons.math3.ml.neuralnet;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Constant;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.RandomGenerator;

/* loaded from: classes5.dex */
public class FeatureInitializerFactory {
    private FeatureInitializerFactory() {
    }

    public static FeatureInitializer uniform(RandomGenerator randomGenerator, double d, double d2) {
        return randomize(new UniformRealDistribution(randomGenerator, d, d2), function(new Constant(0.0d), 0.0d, 0.0d));
    }

    public static FeatureInitializer uniform(double d, double d2) {
        return randomize(new UniformRealDistribution(d, d2), function(new Constant(0.0d), 0.0d, 0.0d));
    }

    public static FeatureInitializer function(UnivariateFunction univariateFunction, double d, double d2) {
        return new FeatureInitializer(d, univariateFunction, d2) { // from class: org.apache.commons.math3.ml.neuralnet.FeatureInitializerFactory.1
            final /* synthetic */ UnivariateFunction val$f;
            final /* synthetic */ double val$inc;
            final /* synthetic */ double val$init;
            private double arg;

            {
                this.val$init = d;
                this.val$f = univariateFunction;
                this.val$inc = d2;
                this.arg = d;
            }

            @Override // org.apache.commons.math3.ml.neuralnet.FeatureInitializer
            public double value() {
                double dValue = this.val$f.value(this.arg);
                this.arg += this.val$inc;
                return dValue;
            }
        };
    }

    public static FeatureInitializer randomize(final RealDistribution realDistribution, final FeatureInitializer featureInitializer) {
        return new FeatureInitializer() { // from class: org.apache.commons.math3.ml.neuralnet.FeatureInitializerFactory.2
            @Override // org.apache.commons.math3.ml.neuralnet.FeatureInitializer
            public double value() {
                return featureInitializer.value() + realDistribution.sample();
            }
        };
    }
}
