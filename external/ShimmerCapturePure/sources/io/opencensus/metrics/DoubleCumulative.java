package io.opencensus.metrics;

import io.opencensus.internal.Utils;

import java.util.List;

/* loaded from: classes4.dex */
public abstract class DoubleCumulative {

    static DoubleCumulative newNoopDoubleCumulative(String str, String str2, String str3, List<LabelKey> list) {
        return NoopDoubleCumulative.create(str, str2, str3, list);
    }

    public abstract void clear();

    public abstract DoublePoint getDefaultTimeSeries();

    public abstract DoublePoint getOrCreateTimeSeries(List<LabelValue> list);

    public abstract void removeTimeSeries(List<LabelValue> list);

    public static abstract class DoublePoint {
        public abstract void add(double d);
    }

    private static final class NoopDoubleCumulative extends DoubleCumulative {
        private final int labelKeysSize;

        NoopDoubleCumulative(String str, String str2, String str3, List<LabelKey> list) {
            Utils.checkNotNull(str, "name");
            Utils.checkNotNull(str2, "description");
            Utils.checkNotNull(str3, "unit");
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
            this.labelKeysSize = list.size();
        }

        static NoopDoubleCumulative create(String str, String str2, String str3, List<LabelKey> list) {
            return new NoopDoubleCumulative(str, str2, str3, list);
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public void clear() {
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public /* bridge */ /* synthetic */ DoublePoint getOrCreateTimeSeries(List list) {
            return getOrCreateTimeSeries((List<LabelValue>) list);
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public NoopDoublePoint getOrCreateTimeSeries(List<LabelValue> list) {
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
            Utils.checkArgument(this.labelKeysSize == list.size(), "Label Keys and Label Values don't have same size.");
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public NoopDoublePoint getDefaultTimeSeries() {
            return NoopDoublePoint.INSTANCE;
        }

        @Override // io.opencensus.metrics.DoubleCumulative
        public void removeTimeSeries(List<LabelValue> list) {
            Utils.checkNotNull(list, "labelValues");
        }

        private static final class NoopDoublePoint extends DoublePoint {
            private static final NoopDoublePoint INSTANCE = new NoopDoublePoint();

            private NoopDoublePoint() {
            }

            @Override // io.opencensus.metrics.DoubleCumulative.DoublePoint
            public void add(double d) {
            }
        }
    }
}
