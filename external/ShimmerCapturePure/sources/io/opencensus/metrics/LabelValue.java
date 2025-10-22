package io.opencensus.metrics;

import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public abstract class LabelValue {
    LabelValue() {
    }

    public static LabelValue create(@Nullable String str) {
        return new AutoValue_LabelValue(str);
    }

    @Nullable
    public abstract String getValue();
}
