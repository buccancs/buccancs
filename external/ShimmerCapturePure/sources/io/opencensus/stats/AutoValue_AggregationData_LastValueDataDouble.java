package io.opencensus.stats;

import io.opencensus.stats.AggregationData;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_AggregationData_LastValueDataDouble extends AggregationData.LastValueDataDouble {
    private final double lastValue;

    AutoValue_AggregationData_LastValueDataDouble(double d) {
        this.lastValue = d;
    }

    @Override // io.opencensus.stats.AggregationData.LastValueDataDouble
    public double getLastValue() {
        return this.lastValue;
    }

    public String toString() {
        return "LastValueDataDouble{lastValue=" + this.lastValue + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AggregationData.LastValueDataDouble) && Double.doubleToLongBits(this.lastValue) == Double.doubleToLongBits(((AggregationData.LastValueDataDouble) obj).getLastValue());
    }

    public int hashCode() {
        return (int) (1000003 ^ ((Double.doubleToLongBits(this.lastValue) >>> 32) ^ Double.doubleToLongBits(this.lastValue)));
    }
}
