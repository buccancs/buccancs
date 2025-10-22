package io.opencensus.stats;

import io.opencensus.common.Duration;
import io.opencensus.common.Function;
import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;
import io.opencensus.tags.TagKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class View {
    static final int NAME_MAX_LENGTH = 255;
    private static final Comparator<TagKey> TAG_KEY_COMPARATOR = new Comparator<TagKey>() { // from class: io.opencensus.stats.View.1
        @Override // java.util.Comparator
        public int compare(TagKey tagKey, TagKey tagKey2) {
            return tagKey.getName().compareToIgnoreCase(tagKey2.getName());
        }
    };

    View() {
    }

    @Deprecated
    public static View create(Name name, String str, Measure measure, Aggregation aggregation, List<TagKey> list, AggregationWindow aggregationWindow) {
        Utils.checkArgument(new HashSet(list).size() == list.size(), "Columns have duplicate.");
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, TAG_KEY_COMPARATOR);
        return new AutoValue_View(name, str, measure, aggregation, Collections.unmodifiableList(arrayList), aggregationWindow);
    }

    public static View create(Name name, String str, Measure measure, Aggregation aggregation, List<TagKey> list) {
        Utils.checkArgument(new HashSet(list).size() == list.size(), "Columns have duplicate.");
        return create(name, str, measure, aggregation, list, AggregationWindow.Cumulative.create());
    }

    public abstract Aggregation getAggregation();

    public abstract List<TagKey> getColumns();

    public abstract String getDescription();

    public abstract Measure getMeasure();

    public abstract Name getName();

    @Deprecated
    public abstract AggregationWindow getWindow();

    public static abstract class Name {
        Name() {
        }

        public static Name create(String str) {
            Utils.checkArgument(StringUtils.isPrintableString(str) && str.length() <= 255, "Name should be a ASCII string with a length no greater than 255 characters.");
            return new AutoValue_View_Name(str);
        }

        public abstract String asString();
    }

    @Deprecated
    public static abstract class AggregationWindow {
        private AggregationWindow() {
        }

        public abstract <T> T match(Function<? super Cumulative, T> function, Function<? super Interval, T> function2, Function<? super AggregationWindow, T> function3);

        @Deprecated
        public static abstract class Cumulative extends AggregationWindow {
            private static final Cumulative CUMULATIVE = new AutoValue_View_AggregationWindow_Cumulative();

            Cumulative() {
                super();
            }

            public static Cumulative create() {
                return CUMULATIVE;
            }

            @Override // io.opencensus.stats.View.AggregationWindow
            public final <T> T match(Function<? super Cumulative, T> function, Function<? super Interval, T> function2, Function<? super AggregationWindow, T> function3) {
                return function.apply(this);
            }
        }

        @Deprecated
        public static abstract class Interval extends AggregationWindow {
            private static final Duration ZERO = Duration.create(0, 0);

            Interval() {
                super();
            }

            public static Interval create(Duration duration) {
                Utils.checkArgument(duration.compareTo(ZERO) > 0, "Duration must be positive");
                return new AutoValue_View_AggregationWindow_Interval(duration);
            }

            public abstract Duration getDuration();

            @Override // io.opencensus.stats.View.AggregationWindow
            public final <T> T match(Function<? super Cumulative, T> function, Function<? super Interval, T> function2, Function<? super AggregationWindow, T> function3) {
                return function2.apply(this);
            }
        }
    }
}
