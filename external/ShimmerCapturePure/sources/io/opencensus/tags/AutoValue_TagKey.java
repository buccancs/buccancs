package io.opencensus.tags;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_TagKey extends TagKey {
    private final String name;

    AutoValue_TagKey(String str) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.name = str;
    }

    @Override // io.opencensus.tags.TagKey
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "TagKey{name=" + this.name + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TagKey) {
            return this.name.equals(((TagKey) obj).getName());
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }
}
