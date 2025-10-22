package org.apache.commons.math3.optimization.fitting;

import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes5.dex */
public class HarmonicFitter extends CurveFitter<HarmonicOscillator.Parametric> {
    public HarmonicFitter(DifferentiableMultivariateVectorOptimizer differentiableMultivariateVectorOptimizer) {
        super(differentiableMultivariateVectorOptimizer);
    }

    public double[] fit(double[] dArr) {
        return fit(new HarmonicOscillator.Parametric(), dArr);
    }

    public double[] fit() {
        return fit(new ParameterGuesser(getObservations()).guess());
    }

    public static class ParameterGuesser {
        private final double a;
        private final double omega;
        private final double phi;

        public ParameterGuesser(WeightedObservedPoint[] weightedObservedPointArr) {
            if (weightedObservedPointArr.length < 4) {
                throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(weightedObservedPointArr.length), 4, true);
            }
            WeightedObservedPoint[] weightedObservedPointArrSortObservations = sortObservations(weightedObservedPointArr);
            double[] dArrGuessAOmega = guessAOmega(weightedObservedPointArrSortObservations);
            this.a = dArrGuessAOmega[0];
            this.omega = dArrGuessAOmega[1];
            this.phi = guessPhi(weightedObservedPointArrSortObservations);
        }

        public double[] guess() {
            return new double[]{this.a, this.omega, this.phi};
        }

        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] weightedObservedPointArr) {
            WeightedObservedPoint[] weightedObservedPointArr2 = (WeightedObservedPoint[]) weightedObservedPointArr.clone();
            WeightedObservedPoint weightedObservedPoint = weightedObservedPointArr2[0];
            for (int i = 1; i < weightedObservedPointArr2.length; i++) {
                WeightedObservedPoint weightedObservedPoint2 = weightedObservedPointArr2[i];
                if (weightedObservedPoint2.getX() < weightedObservedPoint.getX()) {
                    int i2 = i - 1;
                    WeightedObservedPoint weightedObservedPoint3 = weightedObservedPointArr2[i2];
                    while (i2 >= 0 && weightedObservedPoint2.getX() < weightedObservedPoint3.getX()) {
                        weightedObservedPointArr2[i2 + 1] = weightedObservedPoint3;
                        int i3 = i2 - 1;
                        if (i2 != 0) {
                            weightedObservedPoint3 = weightedObservedPointArr2[i3];
                        }
                        i2 = i3;
                    }
                    weightedObservedPointArr2[i2 + 1] = weightedObservedPoint2;
                    weightedObservedPoint = weightedObservedPointArr2[i];
                } else {
                    weightedObservedPoint = weightedObservedPoint2;
                }
            }
            return weightedObservedPointArr2;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x00a2  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private double[] guessAOmega(org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[] r36) {
            /*
                Method dump skipped, instructions count: 231
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.fitting.HarmonicFitter.ParameterGuesser.guessAOmega(org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[]):double[]");
        }

        private double guessPhi(WeightedObservedPoint[] weightedObservedPointArr) {
            double x = weightedObservedPointArr[0].getX();
            double y = weightedObservedPointArr[0].getY();
            double d = 0.0d;
            int i = 1;
            double d2 = 0.0d;
            while (i < weightedObservedPointArr.length) {
                double x2 = weightedObservedPointArr[i].getX();
                double y2 = weightedObservedPointArr[i].getY();
                double d3 = (y2 - y) / (x2 - x);
                double d4 = this.omega * x2;
                double dCos = FastMath.cos(d4);
                double dSin = FastMath.sin(d4);
                double d5 = this.omega;
                d2 += ((d5 * y2) * dCos) - (d3 * dSin);
                d += (d5 * y2 * dSin) + (d3 * dCos);
                i++;
                y = y2;
                x = x2;
            }
            return FastMath.atan2(-d, d2);
        }
    }
}
