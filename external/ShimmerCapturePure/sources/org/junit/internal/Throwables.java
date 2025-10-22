package org.junit.internal;

/* loaded from: classes2.dex */
public final class Throwables {
    private Throwables() {
    }

    public static Exception rethrowAsException(Throwable th) throws Exception {
        rethrow(th);
        return null;
    }

    private static <T extends Throwable> void rethrow(Throwable th) throws Throwable {
        throw th;
    }
}
