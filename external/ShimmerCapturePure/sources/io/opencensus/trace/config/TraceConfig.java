package io.opencensus.trace.config;

/* loaded from: classes4.dex */
public abstract class TraceConfig {
    private static final NoopTraceConfig NOOP_TRACE_CONFIG = new NoopTraceConfig();

    public static TraceConfig getNoopTraceConfig() {
        return NOOP_TRACE_CONFIG;
    }

    public abstract TraceParams getActiveTraceParams();

    public abstract void updateActiveTraceParams(TraceParams traceParams);

    private static final class NoopTraceConfig extends TraceConfig {
        private NoopTraceConfig() {
        }

        @Override // io.opencensus.trace.config.TraceConfig
        public void updateActiveTraceParams(TraceParams traceParams) {
        }

        @Override // io.opencensus.trace.config.TraceConfig
        public TraceParams getActiveTraceParams() {
            return TraceParams.DEFAULT;
        }
    }
}
