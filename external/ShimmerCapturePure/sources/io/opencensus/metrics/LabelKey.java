package io.opencensus.metrics;

/* loaded from: classes4.dex */
public abstract class LabelKey {
    LabelKey() {
    }

    public static LabelKey create(String str, String str2) {
        return new AutoValue_LabelKey(str, str2);
    }

    public abstract String getDescription();

    public abstract String getKey();
}
