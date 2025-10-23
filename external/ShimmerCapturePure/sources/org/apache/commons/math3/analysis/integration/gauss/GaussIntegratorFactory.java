package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes5.dex */
public class GaussIntegratorFactory {
    private final BaseRuleFactory<Double> legendre = new LegendreRuleFactory();
    private final BaseRuleFactory<BigDecimal> legendreHighPrecision = new LegendreHighPrecisionRuleFactory();
    private final BaseRuleFactory<Double> hermite = new HermiteRuleFactory();

    private static Pair<double[], double[]> getRule(BaseRuleFactory<? extends Number> baseRuleFactory, int i) throws NotStrictlyPositiveException, DimensionMismatchException {
        return baseRuleFactory.getRule(i);
    }

    private static Pair<double[], double[]> transform(Pair<double[], double[]> pair, double d, double d2) {
        double[] first = pair.getFirst();
        double[] second = pair.getSecond();
        double d3 = (d2 - d) / 2.0d;
        double d4 = d + d3;
        for (int i = 0; i < first.length; i++) {
            first[i] = (first[i] * d3) + d4;
            second[i] = second[i] * d3;
        }
        return new Pair<>(first, second);
    }

    public GaussIntegrator legendre(int i) {
        return new GaussIntegrator(getRule(this.legendre, i));
    }

    public GaussIntegrator legendre(int i, double d, double d2) throws NotStrictlyPositiveException {
        return new GaussIntegrator(transform(getRule(this.legendre, i), d, d2));
    }

    public GaussIntegrator legendreHighPrecision(int i) throws NotStrictlyPositiveException {
        return new GaussIntegrator(getRule(this.legendreHighPrecision, i));
    }

    public GaussIntegrator legendreHighPrecision(int i, double d, double d2) throws NotStrictlyPositiveException {
        return new GaussIntegrator(transform(getRule(this.legendreHighPrecision, i), d, d2));
    }

    public SymmetricGaussIntegrator hermite(int i) {
        return new SymmetricGaussIntegrator(getRule(this.hermite, i));
    }
}
