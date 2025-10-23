package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class IterativeLegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {
    private static final GaussIntegratorFactory FACTORY = new GaussIntegratorFactory();
    private final int numberOfPoints;

    public IterativeLegendreGaussIntegrator(int i, double d, double d2, int i2, int i3) throws NumberIsTooSmallException {
        super(d, d2, i2, i3);
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(i));
        }
        this.numberOfPoints = i;
    }

    public IterativeLegendreGaussIntegrator(int i, double d, double d2) throws NotStrictlyPositiveException {
        this(i, d, d2, 3, Integer.MAX_VALUE);
    }

    public IterativeLegendreGaussIntegrator(int i, int i2, int i3) throws NumberIsTooSmallException {
        this(i, 1.0E-6d, 1.0E-15d, i2, i3);
    }

    @Override // org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator
    protected double doIntegrate() throws MaxCountExceededException, MathIllegalArgumentException {
        double dStage = stage(1);
        int iMax = 2;
        while (true) {
            double dStage2 = stage(iMax);
            double dAbs = FastMath.abs(dStage2 - dStage);
            double dMax = FastMath.max(getAbsoluteAccuracy(), getRelativeAccuracy() * (FastMath.abs(dStage) + FastMath.abs(dStage2)) * 0.5d);
            if (getIterations() + 1 >= getMinimalIterationCount() && dAbs <= dMax) {
                return dStage2;
            }
            iMax = FastMath.max((int) (FastMath.min(4.0d, FastMath.pow(dAbs / dMax, 0.5d / this.numberOfPoints)) * iMax), iMax + 1);
            incrementCount();
            dStage = dStage2;
        }
    }

    private double stage(int i) throws TooManyEvaluationsException {
        UnivariateFunction univariateFunction = new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.1
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double d) throws TooManyEvaluationsException, MathIllegalArgumentException {
                return IterativeLegendreGaussIntegrator.this.computeObjectiveValue(d);
            }
        };
        double min = getMin();
        double max = (getMax() - min) / i;
        double dIntegrate = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            double d = min + (i2 * max);
            dIntegrate += FACTORY.legendreHighPrecision(this.numberOfPoints, d, d + max).integrate(univariateFunction);
        }
        return dIntegrate;
    }
}
