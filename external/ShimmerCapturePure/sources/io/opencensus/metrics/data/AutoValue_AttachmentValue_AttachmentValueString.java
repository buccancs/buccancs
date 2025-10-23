package io.opencensus.metrics.data;

import io.opencensus.metrics.data.AttachmentValue;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_AttachmentValue_AttachmentValueString extends AttachmentValue.AttachmentValueString {
    private final String value;

    AutoValue_AttachmentValue_AttachmentValueString(String str) {
        if (str == null) {
            throw new NullPointerException("Null value");
        }
        this.value = str;
    }

    @Override // io.opencensus.metrics.data.AttachmentValue
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "AttachmentValueString{value=" + this.value + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttachmentValue.AttachmentValueString) {
            return this.value.equals(((AttachmentValue.AttachmentValueString) obj).getValue());
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }
}
