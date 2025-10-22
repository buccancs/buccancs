package org.apache.commons.math3.analysis.integration.gauss;

import java.lang.Number;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes5.dex */
public abstract class BaseRuleFactory<T extends Number> {
    private final Map<Integer, Pair<T[], T[]>> pointsAndWeights = new TreeMap();
    private final Map<Integer, Pair<double[], double[]>> pointsAndWeightsDouble = new TreeMap();

    private static <T extends Number> Pair<double[], double[]> convertToDouble(Pair<T[], T[]> pair) {
        T[] first = pair.getFirst();
        T[] second = pair.getSecond();
        int length = first.length;
        double[] dArr = new double[length];
        double[] dArr2 = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = first[i].doubleValue();
            dArr2[i] = second[i].doubleValue();
        }
        return new Pair<>(dArr, dArr2);
    }

    protected abstract Pair<T[], T[]> computeRule(int i) throws DimensionMismatchException;

    public Pair<double[], double[]> getRule(int i) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(i));
        }
        Pair<double[], double[]> pairConvertToDouble = this.pointsAndWeightsDouble.get(Integer.valueOf(i));
        if (pairConvertToDouble == null) {
            pairConvertToDouble = convertToDouble(getRuleInternal(i));
            this.pointsAndWeightsDouble.put(Integer.valueOf(i), pairConvertToDouble);
        }
        return new Pair<>(pairConvertToDouble.getFirst().clone(), pairConvertToDouble.getSecond().clone());
    }

    protected synchronized Pair<T[], T[]> getRuleInternal(int i) throws DimensionMismatchException {
        Pair<T[], T[]> pair = this.pointsAndWeights.get(Integer.valueOf(i));
        if (pair != null) {
            return pair;
        }
        addRule(computeRule(i));
        return getRuleInternal(i);
    }

    protected void addRule(Pair<T[], T[]> pair) throws DimensionMismatchException {
        if (pair.getFirst().length != pair.getSecond().length) {
            throw new DimensionMismatchException(pair.getFirst().length, pair.getSecond().length);
        }
        this.pointsAndWeights.put(Integer.valueOf(pair.getFirst().length), pair);
    }
}
