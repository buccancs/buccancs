package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.export.SpanData;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_SpanData_TimedEvent<T> extends SpanData.TimedEvent<T> {
    private final T event;
    private final Timestamp timestamp;

    AutoValue_SpanData_TimedEvent(Timestamp timestamp, T t) {
        if (timestamp == null) {
            throw new NullPointerException("Null timestamp");
        }
        this.timestamp = timestamp;
        if (t == null) {
            throw new NullPointerException("Null event");
        }
        this.event = t;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvent
    public T getEvent() {
        return this.event;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvent
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public String toString() {
        return "TimedEvent{timestamp=" + this.timestamp + ", event=" + this.event + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanData.TimedEvent)) {
            return false;
        }
        SpanData.TimedEvent timedEvent = (SpanData.TimedEvent) obj;
        return this.timestamp.equals(timedEvent.getTimestamp()) && this.event.equals(timedEvent.getEvent());
    }

    public int hashCode() {
        return ((this.timestamp.hashCode() ^ 1000003) * 1000003) ^ this.event.hashCode();
    }
}
