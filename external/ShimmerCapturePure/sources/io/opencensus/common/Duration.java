package io.opencensus.common;

import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public abstract class Duration implements Comparable<Duration> {
    Duration() {
    }

    public static Duration create(long j, int i) {
        if (j < -315576000000L) {
            throw new IllegalArgumentException("'seconds' is less than minimum (-315576000000): " + j);
        }
        if (j > 315576000000L) {
            throw new IllegalArgumentException("'seconds' is greater than maximum (315576000000): " + j);
        }
        if (i < -999999999) {
            throw new IllegalArgumentException("'nanos' is less than minimum (-999999999): " + i);
        }
        if (i > 999999999) {
            throw new IllegalArgumentException("'nanos' is greater than maximum (999999999): " + i);
        }
        if ((j < 0 && i > 0) || (j > 0 && i < 0)) {
            throw new IllegalArgumentException("'seconds' and 'nanos' have inconsistent sign: seconds=" + j + ", nanos=" + i);
        }
        return new AutoValue_Duration(j, i);
    }

    public static Duration fromMillis(long j) {
        return create(j / 1000, (int) ((j % 1000) * 1000000));
    }

    public abstract int getNanos();

    public abstract long getSeconds();

    public long toMillis() {
        return TimeUnit.SECONDS.toMillis(getSeconds()) + TimeUnit.NANOSECONDS.toMillis(getNanos());
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        int iCompareLongs = TimeUtils.compareLongs(getSeconds(), duration.getSeconds());
        return iCompareLongs != 0 ? iCompareLongs : TimeUtils.compareLongs(getNanos(), duration.getNanos());
    }
}
