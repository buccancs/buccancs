package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_AttributeValue_AttributeValueString extends AttributeValue.AttributeValueString {
    private final String stringValue;

    AutoValue_AttributeValue_AttributeValueString(String str) {
        if (str == null) {
            throw new NullPointerException("Null stringValue");
        }
        this.stringValue = str;
    }

    @Override
        // io.opencensus.trace.AttributeValue.AttributeValueString
    String getStringValue() {
        return this.stringValue;
    }

    public String toString() {
        return "AttributeValueString{stringValue=" + this.stringValue + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueString) {
            return this.stringValue.equals(((AttributeValue.AttributeValueString) obj).getStringValue());
        }
        return false;
    }

    public int hashCode() {
        return this.stringValue.hashCode() ^ 1000003;
    }
}
