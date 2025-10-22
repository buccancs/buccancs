package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class MannWhitneyUTest {
    private NaturalRanking naturalRanking;

    public MannWhitneyUTest() {
        this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    public MannWhitneyUTest(NaNStrategy naNStrategy, TiesStrategy tiesStrategy) {
        this.naturalRanking = new NaturalRanking(naNStrategy, tiesStrategy);
    }

    private void ensureDataConformance(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        }
        if (dArr.length == 0 || dArr2.length == 0) {
            throw new NoDataException();
        }
    }

    private double[] concatenateSamples(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[dArr.length + dArr2.length];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr3, dArr.length, dArr2.length);
        return dArr3;
    }

    public double mannWhitneyU(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException {
        ensureDataConformance(dArr, dArr2);
        double[] dArrRank = this.naturalRanking.rank(concatenateSamples(dArr, dArr2));
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d += dArrRank[i];
        }
        double length = d - ((dArr.length * (dArr.length + 1)) / 2);
        return FastMath.max(length, (dArr.length * dArr2.length) - length);
    }

    private double calculateAsymptoticPValue(double d, int i, int i2) throws ConvergenceException, MaxCountExceededException {
        return new NormalDistribution((RandomGenerator) null, 0.0d, 1.0d).cumulativeProbability((d - ((i * i2) / 2.0d)) / FastMath.sqrt((r2 * ((i + i2) + 1)) / 12.0d)) * 2.0d;
    }

    public double mannWhitneyUTest(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, ConvergenceException, MaxCountExceededException {
        ensureDataConformance(dArr, dArr2);
        return calculateAsymptoticPValue((dArr.length * dArr2.length) - mannWhitneyU(dArr, dArr2), dArr.length, dArr2.length);
    }
}
