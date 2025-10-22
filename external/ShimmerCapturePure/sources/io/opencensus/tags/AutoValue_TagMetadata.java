package io.opencensus.tags;

import io.opencensus.tags.TagMetadata;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_TagMetadata extends TagMetadata {
    private final TagMetadata.TagTtl tagTtl;

    AutoValue_TagMetadata(TagMetadata.TagTtl tagTtl) {
        if (tagTtl == null) {
            throw new NullPointerException("Null tagTtl");
        }
        this.tagTtl = tagTtl;
    }

    @Override // io.opencensus.tags.TagMetadata
    public TagMetadata.TagTtl getTagTtl() {
        return this.tagTtl;
    }

    public String toString() {
        return "TagMetadata{tagTtl=" + this.tagTtl + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TagMetadata) {
            return this.tagTtl.equals(((TagMetadata) obj).getTagTtl());
        }
        return false;
    }

    public int hashCode() {
        return this.tagTtl.hashCode() ^ 1000003;
    }
}
