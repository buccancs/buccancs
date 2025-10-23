package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;

import java.util.Map;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_RunningSpanStore_Summary extends RunningSpanStore.Summary {
    private final Map<String, RunningSpanStore.PerSpanNameSummary> perSpanNameSummary;

    AutoValue_RunningSpanStore_Summary(Map<String, RunningSpanStore.PerSpanNameSummary> map) {
        if (map == null) {
            throw new NullPointerException("Null perSpanNameSummary");
        }
        this.perSpanNameSummary = map;
    }

    @Override // io.opencensus.trace.export.RunningSpanStore.Summary
    public Map<String, RunningSpanStore.PerSpanNameSummary> getPerSpanNameSummary() {
        return this.perSpanNameSummary;
    }

    public String toString() {
        return "Summary{perSpanNameSummary=" + this.perSpanNameSummary + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RunningSpanStore.Summary) {
            return this.perSpanNameSummary.equals(((RunningSpanStore.Summary) obj).getPerSpanNameSummary());
        }
        return false;
    }

    public int hashCode() {
        return this.perSpanNameSummary.hashCode() ^ 1000003;
    }
}
