package io.opencensus.tags;

/* loaded from: classes4.dex */
public abstract class TagMetadata {
    TagMetadata() {
    }

    public static TagMetadata create(TagTtl tagTtl) {
        return new AutoValue_TagMetadata(tagTtl);
    }

    public abstract TagTtl getTagTtl();

    public enum TagTtl {
        NO_PROPAGATION(0),
        UNLIMITED_PROPAGATION(-1);

        private final int hops;

        TagTtl(int i) {
            this.hops = i;
        }
    }
}
