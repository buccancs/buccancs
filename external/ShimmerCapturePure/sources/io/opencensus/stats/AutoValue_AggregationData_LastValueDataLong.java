package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_AggregationData_LastValueDataLong extends AggregationData.LastValueDataLong {
    private final long lastValue;

    AutoValue_AggregationData_LastValueDataLong(long j) {
        this.lastValue = j;
    }

    @Override // io.opencensus.stats.AggregationData.LastValueDataLong
    public long getLastValue() {
        return this.lastValue;
    }

    public int hashCode() {
        long j = this.lastValue;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }

    public String toString() {
        return "LastValueDataLong{lastValue=" + this.lastValue + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.LastValueDataLong) && this.lastValue == ((AggregationData.LastValueDataLong) obj).getLastValue();
    }
}
