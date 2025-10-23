package org.apache.commons.math.analysis.interpolation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math.DimensionMismatchException;
import org.apache.commons.math.analysis.MultivariateRealFunction;
import org.apache.commons.math.exception.NoDataException;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/MicrosphereInterpolatingFunction.class */
public class MicrosphereInterpolatingFunction implements MultivariateRealFunction {
    private final int dimension;
    private final List<MicrosphereSurfaceElement> microsphere;
    private final double brightnessExponent;
    private final Map<RealVector, Double> samples;

    public MicrosphereInterpolatingFunction(double[][] xval, double[] yval, int brightnessExponent, int microsphereElements, UnitSphereRandomVectorGenerator rand) throws NoDataException, DimensionMismatchException {
        if (xval.length == 0 || xval[0] == null) {
            throw new NoDataException();
        }
        if (xval.length != yval.length) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        this.dimension = xval[0].length;
        this.brightnessExponent = brightnessExponent;
        this.samples = new HashMap(yval.length);
        for (int i = 0; i < xval.length; i++) {
            double[] xvalI = xval[i];
            if (xvalI.length != this.dimension) {
                throw new DimensionMismatchException(xvalI.length, this.dimension);
            }
            this.samples.put(new ArrayRealVector(xvalI), Double.valueOf(yval[i]));
        }
        this.microsphere = new ArrayList(microsphereElements);
        for (int i2 = 0; i2 < microsphereElements; i2++) {
            this.microsphere.add(new MicrosphereSurfaceElement(rand.nextVector()));
        }
    }

    @Override // org.apache.commons.math.analysis.MultivariateRealFunction
    public double value(double[] point) {
        RealVector p = new ArrayRealVector(point);
        Iterator i$ = this.microsphere.iterator();
        while (i$.hasNext()) {
            i$.next().reset();
        }
        for (Map.Entry<RealVector, Double> sd : this.samples.entrySet()) {
            RealVector diff = sd.getKey().subtract(p);
            double diffNorm = diff.getNorm();
            if (FastMath.abs(diffNorm) < FastMath.ulp(1.0d)) {
                return sd.getValue().doubleValue();
            }
            for (MicrosphereSurfaceElement md : this.microsphere) {
                double w = FastMath.pow(diffNorm, -this.brightnessExponent);
                md.store(cosAngle(diff, md.normal()) * w, sd);
            }
        }
        double value = 0.0d;
        double totalWeight = 0.0d;
        for (MicrosphereSurfaceElement md2 : this.microsphere) {
            double iV = md2.illumination();
            Map.Entry<RealVector, Double> sd2 = md2.sample();
            if (sd2 != null) {
                value += iV * sd2.getValue().doubleValue();
                totalWeight += iV;
            }
        }
        return value / totalWeight;
    }

    private double cosAngle(RealVector v, RealVector w) {
        return v.dotProduct(w) / (v.getNorm() * w.getNorm());
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/MicrosphereInterpolatingFunction$MicrosphereSurfaceElement.class */
    private static class MicrosphereSurfaceElement {
        private final RealVector normal;
        private double brightestIllumination;
        private Map.Entry<RealVector, Double> brightestSample;

        MicrosphereSurfaceElement(double[] n) {
            this.normal = new ArrayRealVector(n);
        }

        RealVector normal() {
            return this.normal;
        }

        void reset() {
            this.brightestIllumination = 0.0d;
            this.brightestSample = null;
        }

        void store(double illuminationFromSample, Map.Entry<RealVector, Double> sample) {
            if (illuminationFromSample > this.brightestIllumination) {
                this.brightestIllumination = illuminationFromSample;
                this.brightestSample = sample;
            }
        }

        double illumination() {
            return this.brightestIllumination;
        }

        Map.Entry<RealVector, Double> sample() {
            return this.brightestSample;
        }
    }
}
