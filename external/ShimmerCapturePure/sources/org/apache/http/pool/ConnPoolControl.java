package org.apache.http.pool;

/* loaded from: classes5.dex */
public interface ConnPoolControl<T> {
    int getDefaultMaxPerRoute();

    void setDefaultMaxPerRoute(int i);

    int getMaxPerRoute(T t);

    int getMaxTotal();

    void setMaxTotal(int i);

    PoolStats getStats(T t);

    PoolStats getTotalStats();

    void setMaxPerRoute(T t, int i);
}
