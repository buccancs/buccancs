package io.grpc.netty.shaded.io.netty.util.internal;

import java.util.concurrent.atomic.LongAdder;

/* loaded from: classes3.dex */
final class LongAdderCounter extends LongAdder implements LongCounter {
    LongAdderCounter() {
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.LongCounter
    public long value() {
        return longValue();
    }
}
