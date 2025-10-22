package io.opencensus.trace.export;

import io.opencensus.trace.export.SampledSpanStore;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_SampledSpanStore_LatencyFilter extends SampledSpanStore.LatencyFilter {
    private final long latencyLowerNs;
    private final long latencyUpperNs;
    private final int maxSpansToReturn;
    private final String spanName;

    AutoValue_SampledSpanStore_LatencyFilter(String str, long j, long j2, int i) {
        if (str == null) {
            throw new NullPointerException("Null spanName");
        }
        this.spanName = str;
        this.latencyLowerNs = j;
        this.latencyUpperNs = j2;
        this.maxSpansToReturn = i;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public long getLatencyLowerNs() {
        return this.latencyLowerNs;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public long getLatencyUpperNs() {
        return this.latencyUpperNs;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public int getMaxSpansToReturn() {
        return this.maxSpansToReturn;
    }

    @Override // io.opencensus.trace.export.SampledSpanStore.LatencyFilter
    public String getSpanName() {
        return this.spanName;
    }

    public String toString() {
        return "LatencyFilter{spanName=" + this.spanName + ", latencyLowerNs=" + this.latencyLowerNs + ", latencyUpperNs=" + this.latencyUpperNs + ", maxSpansToReturn=" + this.maxSpansToReturn + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SampledSpanStore.LatencyFilter)) {
            return false;
        }
        SampledSpanStore.LatencyFilter latencyFilter = (SampledSpanStore.LatencyFilter) obj;
        return this.spanName.equals(latencyFilter.getSpanName()) && this.latencyLowerNs == latencyFilter.getLatencyLowerNs() && this.latencyUpperNs == latencyFilter.getLatencyUpperNs() && this.maxSpansToReturn == latencyFilter.getMaxSpansToReturn();
    }

    public int hashCode() {
        long jHashCode = (this.spanName.hashCode() ^ 1000003) * 1000003;
        long j = this.latencyLowerNs;
        long j2 = ((int) (jHashCode ^ (j ^ (j >>> 32)))) * 1000003;
        long j3 = this.latencyUpperNs;
        return (((int) (j2 ^ (j3 ^ (j3 >>> 32)))) * 1000003) ^ this.maxSpansToReturn;
    }
}
