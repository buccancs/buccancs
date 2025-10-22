package io.opencensus.trace.export;

import java.util.Collection;

/* loaded from: classes4.dex */
public abstract class SpanExporter {
    private static final SpanExporter NOOP_SPAN_EXPORTER = new NoopSpanExporter();

    public static SpanExporter getNoopSpanExporter() {
        return NOOP_SPAN_EXPORTER;
    }

    public abstract void registerHandler(String str, Handler handler);

    public abstract void unregisterHandler(String str);

    public static abstract class Handler {
        public abstract void export(Collection<SpanData> collection);
    }

    private static final class NoopSpanExporter extends SpanExporter {
        private NoopSpanExporter() {
        }

        @Override // io.opencensus.trace.export.SpanExporter
        public void registerHandler(String str, Handler handler) {
        }

        @Override // io.opencensus.trace.export.SpanExporter
        public void unregisterHandler(String str) {
        }
    }
}
