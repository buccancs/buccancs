package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_AggregationData_SumDataLong extends AggregationData.SumDataLong {
    private final long sum;

    AutoValue_AggregationData_SumDataLong(long j) {
        this.sum = j;
    }

    @Override // io.opencensus.stats.AggregationData.SumDataLong
    public long getSum() {
        return this.sum;
    }

    public int hashCode() {
        long j = this.sum;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }

    public String toString() {
        return "SumDataLong{sum=" + this.sum + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.SumDataLong) && this.sum == ((AggregationData.SumDataLong) obj).getSum();
    }
}
