package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

/* loaded from: classes3.dex */
final class LinkedQueueNode<E> {
    private static final long NEXT_OFFSET = UnsafeAccess.fieldOffset(LinkedQueueNode.class, "next");
    private volatile LinkedQueueNode<E> next;
    private E value;

    LinkedQueueNode() {
        this(null);
    }

    LinkedQueueNode(E e) {
        spValue(e);
    }

    public E lpValue() {
        return this.value;
    }

    public LinkedQueueNode<E> lvNext() {
        return this.next;
    }

    public void spValue(E e) {
        this.value = e;
    }

    public E getAndNullValue() {
        E eLpValue = lpValue();
        spValue(null);
        return eLpValue;
    }

    public void soNext(LinkedQueueNode<E> linkedQueueNode) {
        UnsafeAccess.UNSAFE.putOrderedObject(this, NEXT_OFFSET, linkedQueueNode);
    }
}
