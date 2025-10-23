package org.apache.commons.math3.genetics;

import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

/* loaded from: classes5.dex */
public class FixedElapsedTime implements StoppingCondition {
    private final long maxTimePeriod;
    private long endTime;

    public FixedElapsedTime(long j) throws NumberIsTooSmallException {
        this(j, TimeUnit.SECONDS);
    }

    public FixedElapsedTime(long j, TimeUnit timeUnit) throws NumberIsTooSmallException {
        this.endTime = -1L;
        if (j < 0) {
            throw new NumberIsTooSmallException(Long.valueOf(j), 0, true);
        }
        this.maxTimePeriod = timeUnit.toNanos(j);
    }

    @Override // org.apache.commons.math3.genetics.StoppingCondition
    public boolean isSatisfied(Population population) {
        if (this.endTime < 0) {
            this.endTime = System.nanoTime() + this.maxTimePeriod;
        }
        return System.nanoTime() >= this.endTime;
    }
}
