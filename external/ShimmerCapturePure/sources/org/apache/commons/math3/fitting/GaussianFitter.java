package org.apache.commons.math3.fitting;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes5.dex */
public class GaussianFitter extends CurveFitter<Gaussian.Parametric> {
    public GaussianFitter(MultivariateVectorOptimizer multivariateVectorOptimizer) {
        super(multivariateVectorOptimizer);
    }

    public double[] fit(double[] dArr) {
        return fit(new Gaussian.Parametric() { // from class: org.apache.commons.math3.fitting.GaussianFitter.1
            @Override
            // org.apache.commons.math3.analysis.function.Gaussian.Parametric, org.apache.commons.math3.analysis.ParametricUnivariateFunction
            public double value(double d, double... dArr2) {
                try {
                    return super.value(d, dArr2);
                } catch (NotStrictlyPositiveException unused) {
                    return Double.POSITIVE_INFINITY;
                }
            }

            @Override
            // org.apache.commons.math3.analysis.function.Gaussian.Parametric, org.apache.commons.math3.analysis.ParametricUnivariateFunction
            public double[] gradient(double d, double... dArr2) {
                double[] dArr3 = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                try {
                    return super.gradient(d, dArr2);
                } catch (NotStrictlyPositiveException unused) {
                    return dArr3;
                }
            }
        }, dArr);
    }

    public double[] fit() {
        return fit(new ParameterGuesser(getObservations()).guess());
    }

    public static class ParameterGuesser {
        private final double mean;
        private final double norm;
        private final double sigma;

        public ParameterGuesser(WeightedObservedPoint[] weightedObservedPointArr) {
            if (weightedObservedPointArr == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
            }
            if (weightedObservedPointArr.length < 3) {
                throw new NumberIsTooSmallException(Integer.valueOf(weightedObservedPointArr.length), 3, true);
            }
            double[] dArrBasicGuess = basicGuess(sortObservations(weightedObservedPointArr));
            this.norm = dArrBasicGuess[0];
            this.mean = dArrBasicGuess[1];
            this.sigma = dArrBasicGuess[2];
        }

        private boolean isBetween(double d, double d2, double d3) {
            return (d >= d2 && d <= d3) || (d >= d3 && d <= d2);
        }

        public double[] guess() {
            return new double[]{this.norm, this.mean, this.sigma};
        }

        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] weightedObservedPointArr) {
            WeightedObservedPoint[] weightedObservedPointArr2 = (WeightedObservedPoint[]) weightedObservedPointArr.clone();
            Arrays.sort(weightedObservedPointArr2, new Comparator<WeightedObservedPoint>() { // from class: org.apache.commons.math3.fitting.GaussianFitter.ParameterGuesser.1
                @Override // java.util.Comparator
                public int compare(WeightedObservedPoint weightedObservedPoint, WeightedObservedPoint weightedObservedPoint2) {
                    if (weightedObservedPoint == null && weightedObservedPoint2 == null) {
                        return 0;
                    }
                    if (weightedObservedPoint == null) {
                        return -1;
                    }
                    if (weightedObservedPoint2 == null) {
                        return 1;
                    }
                    int iCompare = Double.compare(weightedObservedPoint.getX(), weightedObservedPoint2.getX());
                    if (iCompare < 0) {
                        return -1;
                    }
                    if (iCompare > 0) {
                        return 1;
                    }
                    int iCompare2 = Double.compare(weightedObservedPoint.getY(), weightedObservedPoint2.getY());
                    if (iCompare2 < 0) {
                        return -1;
                    }
                    if (iCompare2 > 0) {
                        return 1;
                    }
                    int iCompare3 = Double.compare(weightedObservedPoint.getWeight(), weightedObservedPoint2.getWeight());
                    if (iCompare3 < 0) {
                        return -1;
                    }
                    return iCompare3 > 0 ? 1 : 0;
                }
            });
            return weightedObservedPointArr2;
        }

        private double[] basicGuess(WeightedObservedPoint[] weightedObservedPointArr) {
            double x;
            int iFindMaxY = findMaxY(weightedObservedPointArr);
            double y = weightedObservedPointArr[iFindMaxY].getY();
            double x2 = weightedObservedPointArr[iFindMaxY].getX();
            double d = y + ((x2 - y) / 2.0d);
            try {
                x = interpolateXAtY(weightedObservedPointArr, iFindMaxY, 1, d) - interpolateXAtY(weightedObservedPointArr, iFindMaxY, -1, d);
            } catch (OutOfRangeException unused) {
                x = weightedObservedPointArr[weightedObservedPointArr.length - 1].getX() - weightedObservedPointArr[0].getX();
            }
            return new double[]{y, x2, x / (FastMath.sqrt(FastMath.log(2.0d) * 2.0d) * 2.0d)};
        }

        private int findMaxY(WeightedObservedPoint[] weightedObservedPointArr) {
            int i = 0;
            for (int i2 = 1; i2 < weightedObservedPointArr.length; i2++) {
                if (weightedObservedPointArr[i2].getY() > weightedObservedPointArr[i].getY()) {
                    i = i2;
                }
            }
            return i;
        }

        private double interpolateXAtY(WeightedObservedPoint[] weightedObservedPointArr, int i, int i2, double d) throws OutOfRangeException {
            if (i2 == 0) {
                throw new ZeroException();
            }
            WeightedObservedPoint[] interpolationPointsForY = getInterpolationPointsForY(weightedObservedPointArr, i, i2, d);
            WeightedObservedPoint weightedObservedPoint = interpolationPointsForY[0];
            WeightedObservedPoint weightedObservedPoint2 = interpolationPointsForY[1];
            if (weightedObservedPoint.getY() == d) {
                return weightedObservedPoint.getX();
            }
            if (weightedObservedPoint2.getY() == d) {
                return weightedObservedPoint2.getX();
            }
            return weightedObservedPoint.getX() + (((d - weightedObservedPoint.getY()) * (weightedObservedPoint2.getX() - weightedObservedPoint.getX())) / (weightedObservedPoint2.getY() - weightedObservedPoint.getY()));
        }

        private WeightedObservedPoint[] getInterpolationPointsForY(WeightedObservedPoint[] weightedObservedPointArr, int i, int i2, double d) throws OutOfRangeException {
            WeightedObservedPoint weightedObservedPoint;
            WeightedObservedPoint weightedObservedPoint2;
            if (i2 == 0) {
                throw new ZeroException();
            }
            do {
                int i3 = i + i2;
                if (i2 >= 0) {
                    if (i3 >= weightedObservedPointArr.length) {
                        throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(Double.NEGATIVE_INFINITY), Double.valueOf(Double.POSITIVE_INFINITY));
                    }
                    weightedObservedPoint = weightedObservedPointArr[i];
                    i += i2;
                    weightedObservedPoint2 = weightedObservedPointArr[i];
                } else {
                    if (i3 < 0) {
                        throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(Double.NEGATIVE_INFINITY), Double.valueOf(Double.POSITIVE_INFINITY));
                    }
                    weightedObservedPoint = weightedObservedPointArr[i];
                    i += i2;
                    weightedObservedPoint2 = weightedObservedPointArr[i];
                }
            } while (!isBetween(d, weightedObservedPoint.getY(), weightedObservedPoint2.getY()));
            return i2 < 0 ? new WeightedObservedPoint[]{weightedObservedPoint2, weightedObservedPoint} : new WeightedObservedPoint[]{weightedObservedPoint, weightedObservedPoint2};
        }
    }
}
