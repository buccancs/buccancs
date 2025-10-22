package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

/* compiled from: MpscArrayQueue.java */
/* loaded from: classes3.dex */
abstract class MpscArrayQueueProducerLimitField<E> extends MpscArrayQueueMidPad<E> {
    private static final long P_LIMIT_OFFSET = UnsafeAccess.fieldOffset(MpscArrayQueueProducerLimitField.class, "producerLimit");
    private volatile long producerLimit;

    MpscArrayQueueProducerLimitField(int i) {
        super(i);
        this.producerLimit = i;
    }

    final long lvProducerLimit() {
        return this.producerLimit;
    }

    final void soProducerLimit(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_LIMIT_OFFSET, j);
    }
}
