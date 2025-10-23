package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_RunningSpanStore_PerSpanNameSummary extends RunningSpanStore.PerSpanNameSummary {
    private final int numRunningSpans;

    AutoValue_RunningSpanStore_PerSpanNameSummary(int i) {
        this.numRunningSpans = i;
    }

    @Override // io.opencensus.trace.export.RunningSpanStore.PerSpanNameSummary
    public int getNumRunningSpans() {
        return this.numRunningSpans;
    }

    public int hashCode() {
        return 1000003 ^ this.numRunningSpans;
    }

    public String toString() {
        return "PerSpanNameSummary{numRunningSpans=" + this.numRunningSpans + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RunningSpanStore.PerSpanNameSummary) && this.numRunningSpans == ((RunningSpanStore.PerSpanNameSummary) obj).getNumRunningSpans();
    }
}
