package io.opencensus.stats;

import io.opencensus.common.Timestamp;
import io.opencensus.stats.ViewData;
import org.apache.commons.math3.geometry.VectorFormat;

@Deprecated
/* loaded from: classes4.dex */
final class AutoValue_ViewData_AggregationWindowData_CumulativeData extends ViewData.AggregationWindowData.CumulativeData {
    private final Timestamp end;
    private final Timestamp start;

    AutoValue_ViewData_AggregationWindowData_CumulativeData(Timestamp timestamp, Timestamp timestamp2) {
        if (timestamp == null) {
            throw new NullPointerException("Null start");
        }
        this.start = timestamp;
        if (timestamp2 == null) {
            throw new NullPointerException("Null end");
        }
        this.end = timestamp2;
    }

    @Override // io.opencensus.stats.ViewData.AggregationWindowData.CumulativeData
    public Timestamp getEnd() {
        return this.end;
    }

    @Override // io.opencensus.stats.ViewData.AggregationWindowData.CumulativeData
    public Timestamp getStart() {
        return this.start;
    }

    public String toString() {
        return "CumulativeData{start=" + this.start + ", end=" + this.end + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewData.AggregationWindowData.CumulativeData)) {
            return false;
        }
        ViewData.AggregationWindowData.CumulativeData cumulativeData = (ViewData.AggregationWindowData.CumulativeData) obj;
        return this.start.equals(cumulativeData.getStart()) && this.end.equals(cumulativeData.getEnd());
    }

    public int hashCode() {
        return ((this.start.hashCode() ^ 1000003) * 1000003) ^ this.end.hashCode();
    }
}
