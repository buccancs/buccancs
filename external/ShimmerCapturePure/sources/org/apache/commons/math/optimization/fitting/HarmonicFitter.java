package org.apache.commons.math.optimization.fitting;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/HarmonicFitter.class */
public class HarmonicFitter {
    private final CurveFitter fitter;
    private double[] parameters;

    public HarmonicFitter(DifferentiableMultivariateVectorialOptimizer optimizer) {
        this.fitter = new CurveFitter(optimizer);
        this.parameters = null;
    }

    public HarmonicFitter(DifferentiableMultivariateVectorialOptimizer optimizer, double[] initialGuess) {
        this.fitter = new CurveFitter(optimizer);
        this.parameters = (double[]) initialGuess.clone();
    }

    public void addObservedPoint(double weight, double x, double y) {
        this.fitter.addObservedPoint(weight, x, y);
    }

    public HarmonicFunction fit() throws OptimizationException, IllegalArgumentException {
        if (this.parameters == null) {
            WeightedObservedPoint[] observations = this.fitter.getObservations();
            if (observations.length < 4) {
                throw new OptimizationException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(observations.length), 4);
            }
            HarmonicCoefficientsGuesser guesser = new HarmonicCoefficientsGuesser(observations);
            guesser.guess();
            this.parameters = new double[]{guesser.getGuessedAmplitude(), guesser.getGuessedPulsation(), guesser.getGuessedPhase()};
        }
        try {
            double[] fitted = this.fitter.fit(new ParametricHarmonicFunction(), this.parameters);
            return new HarmonicFunction(fitted[0], fitted[1], fitted[2]);
        } catch (FunctionEvaluationException fee) {
            throw new RuntimeException(fee);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/HarmonicFitter$ParametricHarmonicFunction.class */
    private static class ParametricHarmonicFunction implements ParametricRealFunction {
        private ParametricHarmonicFunction() {
        }

        @Override // org.apache.commons.math.optimization.fitting.ParametricRealFunction
        public double value(double x, double[] parameters) {
            double a = parameters[0];
            double omega = parameters[1];
            double phi = parameters[2];
            return a * FastMath.cos((omega * x) + phi);
        }

        @Override // org.apache.commons.math.optimization.fitting.ParametricRealFunction
        public double[] gradient(double x, double[] parameters) {
            double a = parameters[0];
            double omega = parameters[1];
            double phi = parameters[2];
            double alpha = (omega * x) + phi;
            double cosAlpha = FastMath.cos(alpha);
            double sinAlpha = FastMath.sin(alpha);
            return new double[]{cosAlpha, (-a) * x * sinAlpha, (-a) * sinAlpha};
        }
    }
}
