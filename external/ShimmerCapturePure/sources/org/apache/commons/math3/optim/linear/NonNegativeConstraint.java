package org.apache.commons.math3.optim.linear;

import org.apache.commons.math3.optim.OptimizationData;

/* loaded from: classes5.dex */
public class NonNegativeConstraint implements OptimizationData {
    private final boolean isRestricted;

    public NonNegativeConstraint(boolean z) {
        this.isRestricted = z;
    }

    public boolean isRestrictedToNonNegative() {
        return this.isRestricted;
    }
}
