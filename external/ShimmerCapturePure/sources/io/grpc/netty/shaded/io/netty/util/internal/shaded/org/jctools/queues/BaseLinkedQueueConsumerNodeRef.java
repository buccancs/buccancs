package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

/* compiled from: BaseLinkedQueue.java */
/* loaded from: classes3.dex */
abstract class BaseLinkedQueueConsumerNodeRef<E> extends BaseLinkedQueuePad1<E> {
    private static final long C_NODE_OFFSET = UnsafeAccess.fieldOffset(BaseLinkedQueueConsumerNodeRef.class, "consumerNode");
    private LinkedQueueNode<E> consumerNode;

    BaseLinkedQueueConsumerNodeRef() {
    }

    final LinkedQueueNode<E> lpConsumerNode() {
        return this.consumerNode;
    }

    final void spConsumerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.consumerNode = linkedQueueNode;
    }

    final LinkedQueueNode<E> lvConsumerNode() {
        return (LinkedQueueNode) UnsafeAccess.UNSAFE.getObjectVolatile(this, C_NODE_OFFSET);
    }
}
