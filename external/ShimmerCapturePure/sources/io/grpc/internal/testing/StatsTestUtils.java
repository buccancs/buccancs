package io.grpc.internal.testing;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.Context;
import io.opencensus.common.Scope;
import io.opencensus.stats.Measure;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.Tag;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagMetadata;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagContextDeserializationException;
import io.opencensus.tags.unsafe.ContextUtils;
import io.opencensus.trace.Annotation;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.Link;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import io.opencensus.trace.TraceOptions;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class StatsTestUtils {
    public static final TagKey EXTRA_TAG = TagKey.create("/rpc/test/extratag");
    private static final String EXTRA_TAG_HEADER_VALUE_PREFIX = "extratag:";

    private StatsTestUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImmutableMap<TagKey, TagValue> getTags(TagContext tagContext) {
        if (tagContext instanceof FakeTagContext) {
            return ((FakeTagContext) tagContext).getTags();
        }
        return ImmutableMap.of();
    }

    public static class MetricsRecord {
        public final ImmutableMap<Measure, Number> metrics;
        public final ImmutableMap<TagKey, TagValue> tags;

        private MetricsRecord(ImmutableMap<TagKey, TagValue> immutableMap, ImmutableMap<Measure, Number> immutableMap2) {
            this.tags = immutableMap;
            this.metrics = immutableMap2;
        }

        @Nullable
        public Double getMetric(Measure measure) {
            UnmodifiableIterator<Map.Entry<Measure, Number>> it2 = this.metrics.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<Measure, Number> next = it2.next();
                if (next.getKey().equals(measure)) {
                    Number value = next.getValue();
                    if (value instanceof Double) {
                        return (Double) value;
                    }
                    if (value instanceof Long) {
                        return Double.valueOf(((Long) value).longValue());
                    }
                    throw new AssertionError("Unexpected measure value type: " + value.getClass().getName());
                }
            }
            return null;
        }

        public long getMetricAsLongOrFail(Measure measure) {
            Double metric = getMetric(measure);
            Preconditions.checkNotNull(metric, "Measure not found: %s", measure.getName());
            long jAbs = (long) (Math.abs(metric.doubleValue()) + 1.0E-4d);
            return metric.doubleValue() < 0.0d ? -jAbs : jAbs;
        }

        public String toString() {
            return "[tags=" + this.tags + ", metrics=" + this.metrics + "]";
        }
    }

    public static final class FakeStatsRecorder extends StatsRecorder {
        private BlockingQueue<MetricsRecord> records;

        public FakeStatsRecorder() {
            rolloverRecords();
        }

        @Override // io.opencensus.stats.StatsRecorder
        public MeasureMap newMeasureMap() {
            return new FakeStatsRecord(this);
        }

        public MetricsRecord pollRecord() {
            return getCurrentRecordSink().poll();
        }

        public MetricsRecord pollRecord(long j, TimeUnit timeUnit) throws InterruptedException {
            return getCurrentRecordSink().poll(j, timeUnit);
        }

        public synchronized void rolloverRecords() {
            this.records = new LinkedBlockingQueue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized BlockingQueue<MetricsRecord> getCurrentRecordSink() {
            return this.records;
        }
    }

    public static final class FakeTagger extends Tagger {
        @Override // io.opencensus.tags.Tagger
        public FakeTagContext empty() {
            return FakeTagContext.EMPTY;
        }

        @Override // io.opencensus.tags.Tagger
        public TagContext getCurrentTagContext() {
            return ContextUtils.getValue(Context.current());
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder emptyBuilder() {
            return new FakeTagContextBuilder(ImmutableMap.of());
        }

        @Override // io.opencensus.tags.Tagger
        public FakeTagContextBuilder toBuilder(TagContext tagContext) {
            return new FakeTagContextBuilder(StatsTestUtils.getTags(tagContext));
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder currentBuilder() {
            throw new UnsupportedOperationException();
        }

        @Override // io.opencensus.tags.Tagger
        public Scope withTagContext(TagContext tagContext) {
            throw new UnsupportedOperationException();
        }
    }

    public static final class FakeTagContextBinarySerializer extends TagContextBinarySerializer {
        private final FakeTagger tagger = new FakeTagger();

        @Override // io.opencensus.tags.propagation.TagContextBinarySerializer
        public TagContext fromByteArray(byte[] bArr) throws TagContextDeserializationException {
            String str = new String(bArr, Charsets.UTF_8);
            if (str.startsWith(StatsTestUtils.EXTRA_TAG_HEADER_VALUE_PREFIX)) {
                return this.tagger.emptyBuilder().putLocal(StatsTestUtils.EXTRA_TAG, TagValue.create(str.substring(9))).build();
            }
            throw new TagContextDeserializationException("Malformed value");
        }

        @Override // io.opencensus.tags.propagation.TagContextBinarySerializer
        public byte[] toByteArray(TagContext tagContext) {
            TagValue tagValue = (TagValue) StatsTestUtils.getTags(tagContext).get(StatsTestUtils.EXTRA_TAG);
            if (tagValue == null) {
                throw new UnsupportedOperationException("TagContext must contain EXTRA_TAG");
            }
            return (StatsTestUtils.EXTRA_TAG_HEADER_VALUE_PREFIX + tagValue.asString()).getBytes(Charsets.UTF_8);
        }
    }

    public static final class FakeStatsRecord extends MeasureMap {
        public final Map<Measure, Number> metrics;
        private final BlockingQueue<MetricsRecord> recordSink;

        private FakeStatsRecord(FakeStatsRecorder fakeStatsRecorder) {
            this.metrics = Maps.newHashMap();
            this.recordSink = fakeStatsRecorder.getCurrentRecordSink();
        }

        @Override // io.opencensus.stats.MeasureMap
        public MeasureMap put(Measure.MeasureDouble measureDouble, double d) {
            this.metrics.put(measureDouble, Double.valueOf(d));
            return this;
        }

        @Override // io.opencensus.stats.MeasureMap
        public MeasureMap put(Measure.MeasureLong measureLong, long j) {
            this.metrics.put(measureLong, Long.valueOf(j));
            return this;
        }

        @Override // io.opencensus.stats.MeasureMap
        public void record(TagContext tagContext) {
            this.recordSink.add(new MetricsRecord(StatsTestUtils.getTags(tagContext), ImmutableMap.copyOf((Map) this.metrics)));
        }

        @Override // io.opencensus.stats.MeasureMap
        public void record() {
            throw new UnsupportedOperationException();
        }
    }

    public static final class FakeTagContext extends TagContext {
        private static final FakeTagContext EMPTY = new FakeTagContext(ImmutableMap.of());
        private static final TagMetadata METADATA_PROPAGATING = TagMetadata.create(TagMetadata.TagTtl.UNLIMITED_PROPAGATION);
        private final ImmutableMap<TagKey, TagValue> tags;

        private FakeTagContext(ImmutableMap<TagKey, TagValue> immutableMap) {
            this.tags = immutableMap;
        }

        public ImmutableMap<TagKey, TagValue> getTags() {
            return this.tags;
        }

        @Override // io.opencensus.tags.TagContext
        public String toString() {
            return "[tags=" + this.tags + "]";
        }

        @Override // io.opencensus.tags.TagContext
        protected Iterator<Tag> getIterator() {
            return Iterators.transform(this.tags.entrySet().iterator(), new Function<Map.Entry<TagKey, TagValue>, Tag>() { // from class: io.grpc.internal.testing.StatsTestUtils.FakeTagContext.1
                @Override // com.google.common.base.Function
                public Tag apply(@Nullable Map.Entry<TagKey, TagValue> entry) {
                    return Tag.create(entry.getKey(), entry.getValue(), FakeTagContext.METADATA_PROPAGATING);
                }
            });
        }
    }

    public static class FakeTagContextBuilder extends TagContextBuilder {
        private final Map<TagKey, TagValue> tagsBuilder;

        private FakeTagContextBuilder(Map<TagKey, TagValue> map) {
            HashMap mapNewHashMap = Maps.newHashMap();
            this.tagsBuilder = mapNewHashMap;
            mapNewHashMap.putAll(map);
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder put(TagKey tagKey, TagValue tagValue) {
            this.tagsBuilder.put(tagKey, tagValue);
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder remove(TagKey tagKey) {
            this.tagsBuilder.remove(tagKey);
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContext build() {
            return new FakeTagContext(ImmutableMap.copyOf((Map) this.tagsBuilder));
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public Scope buildScoped() {
            throw new UnsupportedOperationException();
        }
    }

    public static class MockableSpan extends Span {

        private MockableSpan(SpanContext spanContext, @Nullable EnumSet<Span.Options> enumSet) {
            super(spanContext, enumSet);
        }

        public static MockableSpan generateRandomSpan(Random random) {
            return new MockableSpan(SpanContext.create(TraceId.generateRandomId(random), SpanId.generateRandomId(random), TraceOptions.DEFAULT), null);
        }

        @Override // io.opencensus.trace.Span
        public void addAnnotation(Annotation annotation) {
        }

        @Override // io.opencensus.trace.Span
        public void addAnnotation(String str, Map<String, AttributeValue> map) {
        }

        @Override // io.opencensus.trace.Span
        public void addLink(Link link) {
        }

        @Override // io.opencensus.trace.Span
        public void addMessageEvent(MessageEvent messageEvent) {
        }

        @Override // io.opencensus.trace.Span
        public void end(EndSpanOptions endSpanOptions) {
        }

        @Override // io.opencensus.trace.Span
        public void putAttributes(Map<String, AttributeValue> map) {
        }

        public static class Builder extends SpanBuilder {
            @Override // io.opencensus.trace.SpanBuilder
            public SpanBuilder setParentLinks(List<Span> list) {
                return this;
            }

            @Override // io.opencensus.trace.SpanBuilder
            public SpanBuilder setRecordEvents(boolean z) {
                return this;
            }

            @Override // io.opencensus.trace.SpanBuilder
            public SpanBuilder setSampler(Sampler sampler) {
                return this;
            }

            @Override // io.opencensus.trace.SpanBuilder
            public Span startSpan() {
                return null;
            }
        }
    }
}
