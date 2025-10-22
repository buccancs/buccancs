package io.grpc.internal;

/* loaded from: classes2.dex */
final class LongCounterFactory {
    LongCounterFactory() {
    }

    public static LongCounter create() {
        if (ReflectionLongAdderCounter.isAvailable()) {
            return new ReflectionLongAdderCounter();
        }
        return new AtomicLongCounter();
    }
}
