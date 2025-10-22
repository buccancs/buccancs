package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

/* compiled from: BaseMpscLinkedArrayQueue.java */
/* loaded from: classes3.dex */
abstract class BaseMpscLinkedArrayQueueColdProducerFields<E> extends BaseMpscLinkedArrayQueuePad3<E> {
    private static final long P_LIMIT_OFFSET = UnsafeAccess.fieldOffset(BaseMpscLinkedArrayQueueColdProducerFields.class, "producerLimit");
    protected E[] producerBuffer;
    protected long producerMask;
    private volatile long producerLimit;

    BaseMpscLinkedArrayQueueColdProducerFields() {
    }

    final long lvProducerLimit() {
        return this.producerLimit;
    }

    final boolean casProducerLimit(long j, long j2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, P_LIMIT_OFFSET, j, j2);
    }

    final void soProducerLimit(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_LIMIT_OFFSET, j);
    }
}
