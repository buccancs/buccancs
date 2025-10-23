package org.apache.commons.math.distribution;

import java.io.Serializable;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/distribution/ZipfDistributionImpl.class */
public class ZipfDistributionImpl extends AbstractIntegerDistribution implements ZipfDistribution, Serializable {
    private static final long serialVersionUID = -140627372283420404L;
    private int numberOfElements;
    private double exponent;

    public ZipfDistributionImpl(int numberOfElements, double exponent) throws IllegalArgumentException {
        setNumberOfElementsInternal(numberOfElements);
        setExponentInternal(exponent);
    }

    @Override // org.apache.commons.math.distribution.ZipfDistribution
    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    @Override // org.apache.commons.math.distribution.ZipfDistribution
    @Deprecated
    public void setNumberOfElements(int n) throws IllegalArgumentException {
        setNumberOfElementsInternal(n);
    }

    private void setNumberOfElementsInternal(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(n), 0);
        }
        this.numberOfElements = n;
    }

    @Override // org.apache.commons.math.distribution.ZipfDistribution
    public double getExponent() {
        return this.exponent;
    }

    @Override // org.apache.commons.math.distribution.ZipfDistribution
    @Deprecated
    public void setExponent(double s) throws IllegalArgumentException {
        setExponentInternal(s);
    }

    private void setExponentInternal(double s) throws IllegalArgumentException {
        if (s <= 0.0d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_POSITIVE_EXPONENT, Double.valueOf(s));
        }
        this.exponent = s;
    }

    @Override // org.apache.commons.math.distribution.IntegerDistribution
    public double probability(int x) {
        if (x <= 0 || x > this.numberOfElements) {
            return 0.0d;
        }
        return (1.0d / FastMath.pow(x, this.exponent)) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    @Override
    // org.apache.commons.math.distribution.AbstractIntegerDistribution, org.apache.commons.math.distribution.IntegerDistribution
    public double cumulativeProbability(int x) {
        if (x <= 0) {
            return 0.0d;
        }
        if (x >= this.numberOfElements) {
            return 1.0d;
        }
        return generalizedHarmonic(x, this.exponent) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    @Override // org.apache.commons.math.distribution.AbstractIntegerDistribution
    protected int getDomainLowerBound(double p) {
        return 0;
    }

    @Override // org.apache.commons.math.distribution.AbstractIntegerDistribution
    protected int getDomainUpperBound(double p) {
        return this.numberOfElements;
    }

    private double generalizedHarmonic(int n, double m) {
        double value = 0.0d;
        for (int k = n; k > 0; k--) {
            value += 1.0d / FastMath.pow(k, m);
        }
        return value;
    }

    public int getSupportLowerBound() {
        return 1;
    }

    public int getSupportUpperBound() {
        return getNumberOfElements();
    }

    protected double getNumericalMean() {
        int N = getNumberOfElements();
        double s = getExponent();
        double Hs1 = generalizedHarmonic(N, s - 1.0d);
        double Hs = generalizedHarmonic(N, s);
        return Hs1 / Hs;
    }

    protected double getNumericalVariance() {
        int N = getNumberOfElements();
        double s = getExponent();
        double Hs2 = generalizedHarmonic(N, s - 2.0d);
        double Hs1 = generalizedHarmonic(N, s - 1.0d);
        double Hs = generalizedHarmonic(N, s);
        return (Hs2 / Hs) - ((Hs1 * Hs1) / (Hs * Hs));
    }
}
