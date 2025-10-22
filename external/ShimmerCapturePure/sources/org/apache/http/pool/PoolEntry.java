package org.apache.http.pool;

import java.util.concurrent.TimeUnit;

import org.apache.http.util.Args;

/* loaded from: classes5.dex */
public abstract class PoolEntry<T, C> {
    private final C conn;
    private final long created;
    private final String id;
    private final T route;
    private final long validityDeadline;
    private long expiry;
    private volatile Object state;
    private long updated;

    public PoolEntry(String str, T t, C c, long j, TimeUnit timeUnit) {
        Args.notNull(t, "Route");
        Args.notNull(c, "Connection");
        Args.notNull(timeUnit, "Time unit");
        this.id = str;
        this.route = t;
        this.conn = c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.created = jCurrentTimeMillis;
        this.updated = jCurrentTimeMillis;
        if (j > 0) {
            long millis = jCurrentTimeMillis + timeUnit.toMillis(j);
            this.validityDeadline = millis > 0 ? millis : Long.MAX_VALUE;
        } else {
            this.validityDeadline = Long.MAX_VALUE;
        }
        this.expiry = this.validityDeadline;
    }

    public PoolEntry(String str, T t, C c) {
        this(str, t, c, 0L, TimeUnit.MILLISECONDS);
    }

    public abstract void close();

    public C getConnection() {
        return this.conn;
    }

    public long getCreated() {
        return this.created;
    }

    public String getId() {
        return this.id;
    }

    public T getRoute() {
        return this.route;
    }

    public Object getState() {
        return this.state;
    }

    public void setState(Object obj) {
        this.state = obj;
    }

    @Deprecated
    public long getValidUnit() {
        return this.validityDeadline;
    }

    public long getValidityDeadline() {
        return this.validityDeadline;
    }

    public abstract boolean isClosed();

    public synchronized long getUpdated() {
        return this.updated;
    }

    public synchronized long getExpiry() {
        return this.expiry;
    }

    public synchronized void updateExpiry(long j, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.updated = jCurrentTimeMillis;
        this.expiry = Math.min(j > 0 ? jCurrentTimeMillis + timeUnit.toMillis(j) : Long.MAX_VALUE, this.validityDeadline);
    }

    public synchronized boolean isExpired(long j) {
        return j >= this.expiry;
    }

    public String toString() {
        return "[id:" + this.id + "][route:" + this.route + "][state:" + this.state + "]";
    }
}
