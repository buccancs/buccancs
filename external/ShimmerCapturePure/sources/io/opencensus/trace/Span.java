package io.opencensus.trace;

import androidx.core.app.NotificationCompat;
import io.opencensus.internal.Utils;
import io.opencensus.trace.internal.BaseMessageEventUtils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public abstract class Span {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();
    private static final Set<Options> DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(Options.class));
    private final SpanContext context;
    private final Set<Options> options;

    protected Span(SpanContext spanContext, @Nullable EnumSet<Options> enumSet) {
        this.context = (SpanContext) Utils.checkNotNull(spanContext, "context");
        Set<Options> setUnmodifiableSet = enumSet == null ? DEFAULT_OPTIONS : Collections.unmodifiableSet(EnumSet.copyOf((EnumSet) enumSet));
        this.options = setUnmodifiableSet;
        Utils.checkArgument(!spanContext.getTraceOptions().isSampled() || setUnmodifiableSet.contains(Options.RECORD_EVENTS), "Span is sampled, but does not have RECORD_EVENTS set.");
    }

    public abstract void addAnnotation(Annotation annotation);

    public abstract void addAnnotation(String str, Map<String, AttributeValue> map);

    public abstract void addLink(Link link);

    public abstract void end(EndSpanOptions endSpanOptions);

    public final SpanContext getContext() {
        return this.context;
    }

    public final Set<Options> getOptions() {
        return this.options;
    }

    public void putAttribute(String str, AttributeValue attributeValue) {
        Utils.checkNotNull(str, "key");
        Utils.checkNotNull(attributeValue, "value");
        putAttributes(Collections.singletonMap(str, attributeValue));
    }

    public void putAttributes(Map<String, AttributeValue> map) {
        Utils.checkNotNull(map, "attributes");
        addAttributes(map);
    }

    @Deprecated
    public void addAttributes(Map<String, AttributeValue> map) {
        putAttributes(map);
    }

    public final void addAnnotation(String str) {
        Utils.checkNotNull(str, "description");
        addAnnotation(str, EMPTY_ATTRIBUTES);
    }

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
        addMessageEvent(BaseMessageEventUtils.asMessageEvent(networkEvent));
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
        addNetworkEvent(BaseMessageEventUtils.asNetworkEvent(messageEvent));
    }

    public void setStatus(Status status) {
        Utils.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
    }

    public final void end() {
        end(EndSpanOptions.DEFAULT);
    }

    public enum Kind {
        SERVER,
        CLIENT
    }

    public enum Options {
        RECORD_EVENTS
    }
}
