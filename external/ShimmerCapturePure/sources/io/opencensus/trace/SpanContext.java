package io.opencensus.trace;

import java.util.Arrays;
import javax.annotation.Nullable;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
public final class SpanContext {
    public static final SpanContext INVALID;
    private static final Tracestate TRACESTATE_DEFAULT;

    static {
        Tracestate tracestateBuild = Tracestate.builder().build();
        TRACESTATE_DEFAULT = tracestateBuild;
        INVALID = new SpanContext(TraceId.INVALID, SpanId.INVALID, TraceOptions.DEFAULT, tracestateBuild);
    }

    private final SpanId spanId;
    private final TraceId traceId;
    private final TraceOptions traceOptions;
    private final Tracestate tracestate;

    private SpanContext(TraceId traceId, SpanId spanId, TraceOptions traceOptions, Tracestate tracestate) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.traceOptions = traceOptions;
        this.tracestate = tracestate;
    }

    @Deprecated
    public static SpanContext create(TraceId traceId, SpanId spanId, TraceOptions traceOptions) {
        return create(traceId, spanId, traceOptions, TRACESTATE_DEFAULT);
    }

    public static SpanContext create(TraceId traceId, SpanId spanId, TraceOptions traceOptions, Tracestate tracestate) {
        return new SpanContext(traceId, spanId, traceOptions, tracestate);
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public TraceId getTraceId() {
        return this.traceId;
    }

    public TraceOptions getTraceOptions() {
        return this.traceOptions;
    }

    public Tracestate getTracestate() {
        return this.tracestate;
    }

    public boolean isValid() {
        return this.traceId.isValid() && this.spanId.isValid();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanContext)) {
            return false;
        }
        SpanContext spanContext = (SpanContext) obj;
        return this.traceId.equals(spanContext.traceId) && this.spanId.equals(spanContext.spanId) && this.traceOptions.equals(spanContext.traceOptions);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.traceId, this.spanId, this.traceOptions});
    }

    public String toString() {
        return "SpanContext{traceId=" + this.traceId + ", spanId=" + this.spanId + ", traceOptions=" + this.traceOptions + VectorFormat.DEFAULT_SUFFIX;
    }
}
