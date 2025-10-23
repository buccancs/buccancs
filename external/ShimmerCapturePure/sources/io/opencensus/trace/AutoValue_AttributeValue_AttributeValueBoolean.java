package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_AttributeValue_AttributeValueBoolean extends AttributeValue.AttributeValueBoolean {
    private final Boolean booleanValue;

    AutoValue_AttributeValue_AttributeValueBoolean(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("Null booleanValue");
        }
        this.booleanValue = bool;
    }

    @Override
        // io.opencensus.trace.AttributeValue.AttributeValueBoolean
    Boolean getBooleanValue() {
        return this.booleanValue;
    }

    public String toString() {
        return "AttributeValueBoolean{booleanValue=" + this.booleanValue + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueBoolean) {
            return this.booleanValue.equals(((AttributeValue.AttributeValueBoolean) obj).getBooleanValue());
        }
        return false;
    }

    public int hashCode() {
        return this.booleanValue.hashCode() ^ 1000003;
    }
}
