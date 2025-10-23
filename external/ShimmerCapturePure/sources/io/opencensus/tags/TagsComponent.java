package io.opencensus.tags;

import io.opencensus.tags.propagation.TagPropagationComponent;

/* loaded from: classes4.dex */
public abstract class TagsComponent {
    public abstract TaggingState getState();

    @Deprecated
    public abstract void setState(TaggingState taggingState);

    public abstract TagPropagationComponent getTagPropagationComponent();

    public abstract Tagger getTagger();
}
