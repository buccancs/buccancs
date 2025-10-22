package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.KthSelector;

/* loaded from: classes5.dex */
public class Median extends Percentile implements Serializable {
    private static final double FIXED_QUANTILE_50 = 50.0d;
    private static final long serialVersionUID = -3961477041290915687L;

    public Median() {
        super(FIXED_QUANTILE_50);
    }

    public Median(Median median) throws NullArgumentException {
        super(median);
    }

    private Median(Percentile.EstimationType estimationType, NaNStrategy naNStrategy, KthSelector kthSelector) throws MathIllegalArgumentException {
        super(FIXED_QUANTILE_50, estimationType, naNStrategy, kthSelector);
    }

    @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile
    public Median withEstimationType(Percentile.EstimationType estimationType) {
        return new Median(estimationType, getNaNStrategy(), getKthSelector());
    }

    @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile
    public Median withNaNStrategy(NaNStrategy naNStrategy) {
        return new Median(getEstimationType(), naNStrategy, getKthSelector());
    }

    @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile
    public Median withKthSelector(KthSelector kthSelector) {
        return new Median(getEstimationType(), getNaNStrategy(), kthSelector);
    }
}
