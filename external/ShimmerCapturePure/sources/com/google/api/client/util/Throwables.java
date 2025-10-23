package com.google.api.client.util;

/* loaded from: classes.dex */
public final class Throwables {
    private Throwables() {
    }

    public static RuntimeException propagate(Throwable th) {
        return com.google.common.base.Throwables.propagate(th);
    }

    public static void propagateIfPossible(Throwable th) {
        if (th != null) {
            com.google.common.base.Throwables.throwIfUnchecked(th);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(Throwable th, Class<X> cls) throws Throwable {
        com.google.common.base.Throwables.propagateIfPossible(th, cls);
    }
}
