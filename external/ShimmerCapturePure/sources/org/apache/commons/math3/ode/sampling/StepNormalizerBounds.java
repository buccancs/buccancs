package org.apache.commons.math3.ode.sampling;

/* loaded from: classes5.dex */
public enum StepNormalizerBounds {
    NEITHER(false, false),
    FIRST(true, false),
    LAST(false, true),
    BOTH(true, true);

    private final boolean first;
    private final boolean last;

    StepNormalizerBounds(boolean z, boolean z2) {
        this.first = z;
        this.last = z2;
    }

    public boolean firstIncluded() {
        return this.first;
    }

    public boolean lastIncluded() {
        return this.last;
    }
}
