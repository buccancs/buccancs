package com.google.common.util.concurrent;

import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    double stableIntervalMicros;
    double storedPermits;
    private long nextFreeTicketMicros;

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }

    abstract double coolDownIntervalMicros();

    abstract void doSetRate(double d, double d2);

    @Override // com.google.common.util.concurrent.RateLimiter
    final long queryEarliestAvailable(long j) {
        return this.nextFreeTicketMicros;
    }

    abstract long storedPermitsToWaitTime(double d, double d2);

    @Override // com.google.common.util.concurrent.RateLimiter
    final void doSetRate(double d, long j) {
        resync(j);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d;
        this.stableIntervalMicros = micros;
        doSetRate(d, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final double doGetRate() {
        return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final long reserveEarliestAvailable(int i, long j) {
        resync(j);
        long j2 = this.nextFreeTicketMicros;
        double d = i;
        double dMin = Math.min(d, this.storedPermits);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, dMin) + ((long) ((d - dMin) * this.stableIntervalMicros)));
        this.storedPermits -= dMin;
        return j2;
    }

    void resync(long j) {
        if (j > this.nextFreeTicketMicros) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((j - r0) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = j;
        }
    }

    static final class SmoothWarmingUp extends SmoothRateLimiter {
        private final long warmupPeriodMicros;
        private double coldFactor;
        private double slope;
        private double thresholdPermits;

        SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j, TimeUnit timeUnit, double d) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j);
            this.coldFactor = d;
        }

        @Override
            // com.google.common.util.concurrent.SmoothRateLimiter
        void doSetRate(double d, double d2) {
            double d3 = this.maxPermits;
            double d4 = this.coldFactor * d2;
            long j = this.warmupPeriodMicros;
            double d5 = (j * 0.5d) / d2;
            this.thresholdPermits = d5;
            this.maxPermits = d5 + ((j * 2.0d) / (d2 + d4));
            this.slope = (d4 - d2) / (this.maxPermits - this.thresholdPermits);
            if (d3 == Double.POSITIVE_INFINITY) {
                this.storedPermits = 0.0d;
            } else {
                this.storedPermits = d3 == 0.0d ? this.maxPermits : (this.storedPermits * this.maxPermits) / d3;
            }
        }

        @Override
            // com.google.common.util.concurrent.SmoothRateLimiter
        long storedPermitsToWaitTime(double d, double d2) {
            long jPermitsToTime;
            double d3 = d - this.thresholdPermits;
            if (d3 > 0.0d) {
                double dMin = Math.min(d3, d2);
                jPermitsToTime = (long) (((permitsToTime(d3) + permitsToTime(d3 - dMin)) * dMin) / 2.0d);
                d2 -= dMin;
            } else {
                jPermitsToTime = 0;
            }
            return jPermitsToTime + ((long) (this.stableIntervalMicros * d2));
        }

        private double permitsToTime(double d) {
            return this.stableIntervalMicros + (d * this.slope);
        }

        @Override
            // com.google.common.util.concurrent.SmoothRateLimiter
        double coolDownIntervalMicros() {
            return this.warmupPeriodMicros / this.maxPermits;
        }
    }

    static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d) {
            super(sleepingStopwatch);
            this.maxBurstSeconds = d;
        }

        @Override
            // com.google.common.util.concurrent.SmoothRateLimiter
        long storedPermitsToWaitTime(double d, double d2) {
            return 0L;
        }

        @Override
            // com.google.common.util.concurrent.SmoothRateLimiter
        void doSetRate(double d, double d2) {
            double d3 = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * d;
            if (d3 == Double.POSITIVE_INFINITY) {
                this.storedPermits = this.maxPermits;
            } else {
                this.storedPermits = d3 != 0.0d ? (this.storedPermits * this.maxPermits) / d3 : 0.0d;
            }
        }

        @Override
            // com.google.common.util.concurrent.SmoothRateLimiter
        double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }
    }
}
