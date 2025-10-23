package io.opencensus.common;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_Duration extends Duration {
    private final int nanos;
    private final long seconds;

    AutoValue_Duration(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    @Override // io.opencensus.common.Duration
    public int getNanos() {
        return this.nanos;
    }

    @Override // io.opencensus.common.Duration
    public long getSeconds() {
        return this.seconds;
    }

    public int hashCode() {
        long j = this.seconds;
        return this.nanos ^ (((int) (1000003 ^ (j ^ (j >>> 32)))) * 1000003);
    }

    public String toString() {
        return "Duration{seconds=" + this.seconds + ", nanos=" + this.nanos + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) obj;
        return this.seconds == duration.getSeconds() && this.nanos == duration.getNanos();
    }
}
