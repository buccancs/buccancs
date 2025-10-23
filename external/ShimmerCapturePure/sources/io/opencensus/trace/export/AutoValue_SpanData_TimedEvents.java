package io.opencensus.trace.export;

import io.opencensus.trace.export.SpanData;

import java.util.List;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_SpanData_TimedEvents<T> extends SpanData.TimedEvents<T> {
    private final int droppedEventsCount;
    private final List<SpanData.TimedEvent<T>> events;

    AutoValue_SpanData_TimedEvents(List<SpanData.TimedEvent<T>> list, int i) {
        if (list == null) {
            throw new NullPointerException("Null events");
        }
        this.events = list;
        this.droppedEventsCount = i;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvents
    public int getDroppedEventsCount() {
        return this.droppedEventsCount;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvents
    public List<SpanData.TimedEvent<T>> getEvents() {
        return this.events;
    }

    public String toString() {
        return "TimedEvents{events=" + this.events + ", droppedEventsCount=" + this.droppedEventsCount + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanData.TimedEvents)) {
            return false;
        }
        SpanData.TimedEvents timedEvents = (SpanData.TimedEvents) obj;
        return this.events.equals(timedEvents.getEvents()) && this.droppedEventsCount == timedEvents.getDroppedEventsCount();
    }

    public int hashCode() {
        return ((this.events.hashCode() ^ 1000003) * 1000003) ^ this.droppedEventsCount;
    }
}
