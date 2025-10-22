package io.opencensus.metrics.export;

import io.opencensus.metrics.export.Value;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_Value_ValueDistribution extends Value.ValueDistribution {
    private final Distribution value;

    AutoValue_Value_ValueDistribution(Distribution distribution) {
        if (distribution == null) {
            throw new NullPointerException("Null value");
        }
        this.value = distribution;
    }

    @Override
        // io.opencensus.metrics.export.Value.ValueDistribution
    Distribution getValue() {
        return this.value;
    }

    public String toString() {
        return "ValueDistribution{value=" + this.value + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Value.ValueDistribution) {
            return this.value.equals(((Value.ValueDistribution) obj).getValue());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }
}
