package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;

import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;

/* loaded from: classes2.dex */
class KeepAliveEnforcer {
    static final long IMPLICIT_PERMIT_TIME_NANOS = TimeUnit.HOURS.toNanos(2);
    static final int MAX_PING_STRIKES = 2;
    private final long epoch;
    private final long minTimeNanos;
    private final boolean permitWithoutCalls;
    private final Ticker ticker;
    private boolean hasOutstandingCalls;
    private long lastValidPingTime;
    private int pingStrikes;

    public KeepAliveEnforcer(boolean z, long j, TimeUnit timeUnit) {
        this(z, j, timeUnit, SystemTicker.INSTANCE);
    }

    KeepAliveEnforcer(boolean z, long j, TimeUnit timeUnit, Ticker ticker) {
        Preconditions.checkArgument(j >= 0, "minTime must be non-negative: %s", j);
        this.permitWithoutCalls = z;
        this.minTimeNanos = Math.min(timeUnit.toNanos(j), IMPLICIT_PERMIT_TIME_NANOS);
        this.ticker = ticker;
        long jNanoTime = ticker.nanoTime();
        this.epoch = jNanoTime;
        this.lastValidPingTime = jNanoTime;
    }

    private static long compareNanos(long j, long j2) {
        return j - j2;
    }

    public void onTransportActive() {
        this.hasOutstandingCalls = true;
    }

    public void onTransportIdle() {
        this.hasOutstandingCalls = false;
    }

    public void resetCounters() {
        this.lastValidPingTime = this.epoch;
        this.pingStrikes = 0;
    }

    @CheckReturnValue
    public boolean pingAcceptable() {
        long jNanoTime = this.ticker.nanoTime();
        if (this.hasOutstandingCalls || this.permitWithoutCalls ? compareNanos(this.lastValidPingTime + this.minTimeNanos, jNanoTime) <= 0 : compareNanos(this.lastValidPingTime + IMPLICIT_PERMIT_TIME_NANOS, jNanoTime) <= 0) {
            this.lastValidPingTime = jNanoTime;
            return true;
        }
        int i = this.pingStrikes + 1;
        this.pingStrikes = i;
        return i <= 2;
    }

    interface Ticker {
        long nanoTime();
    }

    static class SystemTicker implements Ticker {
        public static final SystemTicker INSTANCE = new SystemTicker();

        SystemTicker() {
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.KeepAliveEnforcer.Ticker
        public long nanoTime() {
            return System.nanoTime();
        }
    }
}
