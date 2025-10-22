package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_Value_ValueLong extends Value.ValueLong {
    private final long value;

    AutoValue_Value_ValueLong(long j) {
        this.value = j;
    }

    @Override
        // io.opencensus.metrics.export.Value.ValueLong
    long getValue() {
        return this.value;
    }

    public int hashCode() {
        long j = this.value;
        return (int) (1000003 ^ (j ^ (j >>> 32)));
    }

    public String toString() {
        return "ValueLong{value=" + this.value + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Value.ValueLong) && this.value == ((Value.ValueLong) obj).getValue();
    }
}
