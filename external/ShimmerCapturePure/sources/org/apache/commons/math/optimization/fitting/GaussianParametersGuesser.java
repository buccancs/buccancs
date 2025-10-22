package org.apache.commons.math.optimization.fitting;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math.exception.NullArgumentException;
import org.apache.commons.math.exception.NumberIsTooSmallException;
import org.apache.commons.math.exception.OutOfRangeException;
import org.apache.commons.math.exception.ZeroException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/GaussianParametersGuesser.class */
public class GaussianParametersGuesser {
    private final WeightedObservedPoint[] observations;
    private double[] parameters;

    public GaussianParametersGuesser(WeightedObservedPoint[] observations) {
        if (observations == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        if (observations.length < 3) {
            throw new NumberIsTooSmallException(Integer.valueOf(observations.length), 3, true);
        }
        this.observations = (WeightedObservedPoint[]) observations.clone();
    }

    public double[] guess() {
        if (this.parameters == null) {
            this.parameters = basicGuess(this.observations);
        }
        return (double[]) this.parameters.clone();
    }

    private double[] basicGuess(WeightedObservedPoint[] points) {
        double fwhmApprox;
        Arrays.sort(points, createWeightedObservedPointComparator());
        double[] params = new double[4];
        int minYIdx = findMinY(points);
        params[0] = points[minYIdx].getY();
        int maxYIdx = findMaxY(points);
        params[1] = points[maxYIdx].getY();
        params[2] = points[maxYIdx].getX();
        try {
            double halfY = params[0] + ((params[1] - params[0]) / 2.0d);
            double fwhmX1 = interpolateXAtY(points, maxYIdx, -1, halfY);
            double fwhmX2 = interpolateXAtY(points, maxYIdx, 1, halfY);
            fwhmApprox = fwhmX2 - fwhmX1;
        } catch (OutOfRangeException e) {
            fwhmApprox = points[points.length - 1].getX() - points[0].getX();
        }
        params[3] = fwhmApprox / (2.0d * Math.sqrt(2.0d * Math.log(2.0d)));
        return params;
    }

    private int findMinY(WeightedObservedPoint[] points) {
        int minYIdx = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getY() < points[minYIdx].getY()) {
                minYIdx = i;
            }
        }
        return minYIdx;
    }

    private int findMaxY(WeightedObservedPoint[] points) {
        int maxYIdx = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].getY() > points[maxYIdx].getY()) {
                maxYIdx = i;
            }
        }
        return maxYIdx;
    }

    private double interpolateXAtY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
        if (idxStep == 0) {
            throw new ZeroException();
        }
        WeightedObservedPoint[] twoPoints = getInterpolationPointsForY(points, startIdx, idxStep, y);
        WeightedObservedPoint pointA = twoPoints[0];
        WeightedObservedPoint pointB = twoPoints[1];
        if (pointA.getY() == y) {
            return pointA.getX();
        }
        if (pointB.getY() == y) {
            return pointB.getX();
        }
        return pointA.getX() + (((y - pointA.getY()) * (pointB.getX() - pointA.getX())) / (pointB.getY() - pointA.getY()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0078, code lost:
    
        r15 = Double.POSITIVE_INFINITY;
        r17 = Double.NEGATIVE_INFINITY;
        r0 = r10.length;
        r21 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0091, code lost:
    
        if (r21 >= r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0094, code lost:
    
        r0 = r10[r21];
        r15 = java.lang.Math.min(r15, r0.getY());
        r17 = java.lang.Math.max(r17, r0.getY());
        r21 = r21 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cf, code lost:
    
        throw new org.apache.commons.math.exception.OutOfRangeException(java.lang.Double.valueOf(r13), java.lang.Double.valueOf(r15), java.lang.Double.valueOf(r17));
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006f A[LOOP:0: B:7:0x000f->B:21:0x006f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.commons.math.optimization.fitting.WeightedObservedPoint[] getInterpolationPointsForY(org.apache.commons.math.optimization.fitting.WeightedObservedPoint[] r10, int r11, int r12, double r13) throws org.apache.commons.math.exception.OutOfRangeException {
        /*
            Method dump skipped, instructions count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.optimization.fitting.GaussianParametersGuesser.getInterpolationPointsForY(org.apache.commons.math.optimization.fitting.WeightedObservedPoint[], int, int, double):org.apache.commons.math.optimization.fitting.WeightedObservedPoint[]");
    }

    private boolean isBetween(double value, double boundary1, double boundary2) {
        return (value >= boundary1 && value <= boundary2) || (value >= boundary2 && value <= boundary1);
    }

    private Comparator<WeightedObservedPoint> createWeightedObservedPointComparator() {
        return new Comparator<WeightedObservedPoint>() { // from class: org.apache.commons.math.optimization.fitting.GaussianParametersGuesser.1
            @Override // java.util.Comparator
            public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
                if (p1 == null && p2 == null) {
                    return 0;
                }
                if (p1 == null) {
                    return -1;
                }
                if (p2 == null) {
                    return 1;
                }
                if (p1.getX() < p2.getX()) {
                    return -1;
                }
                if (p1.getX() > p2.getX()) {
                    return 1;
                }
                if (p1.getY() < p2.getY()) {
                    return -1;
                }
                if (p1.getY() > p2.getY()) {
                    return 1;
                }
                if (p1.getWeight() < p2.getWeight()) {
                    return -1;
                }
                if (p1.getWeight() > p2.getWeight()) {
                    return 1;
                }
                return 0;
            }
        };
    }
}
