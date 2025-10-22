package io.opencensus.trace.export;

import io.opencensus.internal.Utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class RunningSpanStore {
    private static final RunningSpanStore NOOP_RUNNING_SPAN_STORE = new NoopRunningSpanStore();

    protected RunningSpanStore() {
    }

    static RunningSpanStore getNoopRunningSpanStore() {
        return NOOP_RUNNING_SPAN_STORE;
    }

    public abstract Collection<SpanData> getRunningSpans(Filter filter);

    public abstract Summary getSummary();

    public abstract void setMaxNumberOfSpans(int i);

    public static abstract class Summary {
        Summary() {
        }

        public static Summary create(Map<String, PerSpanNameSummary> map) {
            return new AutoValue_RunningSpanStore_Summary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "perSpanNameSummary"))));
        }

        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();
    }

    public static abstract class PerSpanNameSummary {
        PerSpanNameSummary() {
        }

        public static PerSpanNameSummary create(int i) {
            Utils.checkArgument(i >= 0, "Negative numRunningSpans.");
            return new AutoValue_RunningSpanStore_PerSpanNameSummary(i);
        }

        public abstract int getNumRunningSpans();
    }

    public static abstract class Filter {
        Filter() {
        }

        public static Filter create(String str, int i) {
            Utils.checkArgument(i >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_RunningSpanStore_Filter(str, i);
        }

        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();
    }

    private static final class NoopRunningSpanStore extends RunningSpanStore {
        private static final Summary EMPTY_SUMMARY = Summary.create(Collections.emptyMap());

        private NoopRunningSpanStore() {
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public Summary getSummary() {
            return EMPTY_SUMMARY;
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public Collection<SpanData> getRunningSpans(Filter filter) {
            Utils.checkNotNull(filter, "filter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public void setMaxNumberOfSpans(int i) {
            Utils.checkArgument(i >= 0, "Invalid negative maxNumberOfElements");
        }
    }
}
