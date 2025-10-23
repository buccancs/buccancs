package io.opencensus.metrics;

import io.opencensus.internal.Utils;

import java.util.List;

/* loaded from: classes4.dex */
public abstract class LongCumulative {

    static LongCumulative newNoopLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopLongCumulative.create(str, str2, str3, list);
    }

    public abstract void clear();

    public abstract LongPoint getDefaultTimeSeries();

    public abstract LongPoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);

    public static abstract class LongPoint {
        public abstract void add(long j);
    }

    private static final class NoopLongCumulative extends LongCumulative {
        private final int labelKeysSize;

        NoopLongCumulative(String str, String str2, String str3, List<LabelKey> list) {
            this.labelKeysSize = list.size();
        }

        static NoopLongCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopLongCumulative(str, str2, str3, list);
        }

        @Override // io.opencensus.metrics.LongCumulative
        public void clear() {
        }

        @Override // io.opencensus.metrics.LongCumulative
        public /* bridge */ /* synthetic */ LongPoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        @Override // io.opencensus.metrics.LongCumulative
        public NoopLongPoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopLongPoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.LongCumulative
        public NoopLongPoint getDefaultTimeSeries() {
            return NoopLongPoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.LongCumulative
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }

        private static final class NoopLongPoint extends LongPoint {
            private static final NoopLongPoint INSTANCE = new NoopLongPoint();

            private NoopLongPoint() {
            }

            @Override // io.opencensus.metrics.LongCumulative.LongPoint
            public void add(long j) {
            }
        }
    }
}
