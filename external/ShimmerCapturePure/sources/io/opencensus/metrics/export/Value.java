package io.opencensus.metrics.export;

import io.opencensus.common.Function;

/* loaded from: classes4.dex */
public abstract class Value {
    Value() {
    }

    public static Value doubleValue(double d) {
        return ValueDouble.create(d);
    }

    public static Value longValue(long j) {
        return ValueLong.create(j);
    }

    public static Value distributionValue(Distribution distribution) {
        return ValueDistribution.create(distribution);
    }

    public static Value summaryValue(Summary summary) {
        return ValueSummary.create(summary);
    }

    public abstract <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5);

    static abstract class ValueDouble extends Value {
        ValueDouble() {
        }

        static ValueDouble create(double d) {
            return new AutoValue_Value_ValueDouble(d);
        }

        abstract double getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function.apply(Double.valueOf(getValue()));
        }
    }

    static abstract class ValueLong extends Value {
        ValueLong() {
        }

        static ValueLong create(long j) {
            return new AutoValue_Value_ValueLong(j);
        }

        abstract long getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function2.apply(Long.valueOf(getValue()));
        }
    }

    static abstract class ValueDistribution extends Value {
        ValueDistribution() {
        }

        static ValueDistribution create(Distribution distribution) {
            return new AutoValue_Value_ValueDistribution(distribution);
        }

        abstract Distribution getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function3.apply(getValue());
        }
    }

    static abstract class ValueSummary extends Value {
        ValueSummary() {
        }

        static ValueSummary create(Summary summary) {
            return new AutoValue_Value_ValueSummary(summary);
        }

        abstract Summary getValue();

        @Override // io.opencensus.metrics.export.Value
        public final <T> T match(Function<? super Double, T> function, Function<? super Long, T> function2, Function<? super Distribution, T> function3, Function<? super Summary, T> function4, Function<? super Value, T> function5) {
            return function4.apply(getValue());
        }
    }
}
