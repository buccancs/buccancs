package io.grpc.internal;

import java.util.HashSet;

/* loaded from: classes2.dex */
public abstract class InUseStateAggregator<T> {
    private final HashSet<T> inUseObjects = new HashSet<>();

    protected abstract void handleInUse();

    protected abstract void handleNotInUse();

    public final void updateObjectInUse(T t, boolean z) {
        int size = this.inUseObjects.size();
        if (z) {
            this.inUseObjects.add(t);
            if (size == 0) {
                handleInUse();
                return;
            }
            return;
        }
        if (this.inUseObjects.remove(t) && size == 1) {
            handleNotInUse();
        }
    }

    public final boolean isInUse() {
        return !this.inUseObjects.isEmpty();
    }
}
