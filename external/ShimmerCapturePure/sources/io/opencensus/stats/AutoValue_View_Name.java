package io.opencensus.stats;

import io.opencensus.stats.View;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_View_Name extends View.Name {
    private final String asString;

    AutoValue_View_Name(String str) {
        if (str == null) {
            throw new NullPointerException("Null asString");
        }
        this.asString = str;
    }

    @Override // io.opencensus.stats.View.Name
    public String asString() {
        return this.asString;
    }

    public String toString() {
        return "Name{asString=" + this.asString + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof View.Name) {
            return this.asString.equals(((View.Name) obj).asString());
        }
        return false;
    }

    public int hashCode() {
        return this.asString.hashCode() ^ 1000003;
    }
}
