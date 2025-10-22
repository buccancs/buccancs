package io.opencensus.metrics;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_LabelKey extends LabelKey {
    private final String description;
    private final String key;

    AutoValue_LabelKey(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Null key");
        }
        this.key = str;
        if (str2 == null) {
            throw new NullPointerException("Null description");
        }
        this.description = str2;
    }

    @Override // io.opencensus.metrics.LabelKey
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.metrics.LabelKey
    public String getKey() {
        return this.key;
    }

    public String toString() {
        return "LabelKey{key=" + this.key + ", description=" + this.description + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LabelKey)) {
            return false;
        }
        LabelKey labelKey = (LabelKey) obj;
        return this.key.equals(labelKey.getKey()) && this.description.equals(labelKey.getDescription());
    }

    public int hashCode() {
        return ((this.key.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode();
    }
}
