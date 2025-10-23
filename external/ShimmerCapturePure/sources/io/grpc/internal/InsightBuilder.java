package io.grpc.internal;

import java.util.ArrayList;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public final class InsightBuilder {
    private final ArrayList<String> buffer = new ArrayList<>();

    public InsightBuilder append(@Nullable Object obj) {
        this.buffer.add(String.valueOf(obj));
        return this;
    }

    public InsightBuilder appendKeyValue(String str, @Nullable Object obj) {
        this.buffer.add(str + "=" + obj);
        return this;
    }

    public String toString() {
        return this.buffer.toString();
    }
}
