package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;

import java.util.Iterator;

/* loaded from: classes3.dex */
abstract class BaseLinkedQueue<E> extends BaseLinkedQueuePad2<E> {
    BaseLinkedQueue() {
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int capacity() {
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return getClass().getName();
    }

    protected final LinkedQueueNode<E> newNode() {
        return new LinkedQueueNode<>();
    }

    protected final LinkedQueueNode<E> newNode(E e) {
        return new LinkedQueueNode<>(e);
    }

    @Override
    // java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public final int size() {
        LinkedQueueNode<E> linkedQueueNodeLvConsumerNode = lvConsumerNode();
        LinkedQueueNode<E> linkedQueueNodeLvProducerNode = lvProducerNode();
        int i = 0;
        while (linkedQueueNodeLvConsumerNode != linkedQueueNodeLvProducerNode && linkedQueueNodeLvConsumerNode != null && i < Integer.MAX_VALUE) {
            LinkedQueueNode<E> linkedQueueNodeLvNext = linkedQueueNodeLvConsumerNode.lvNext();
            if (linkedQueueNodeLvNext == linkedQueueNodeLvConsumerNode) {
                return i;
            }
            i++;
            linkedQueueNodeLvConsumerNode = linkedQueueNodeLvNext;
        }
        return i;
    }

    @Override
    // java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean isEmpty() {
        return lvConsumerNode() == lvProducerNode();
    }

    protected E getSingleConsumerNodeValue(LinkedQueueNode<E> linkedQueueNode, LinkedQueueNode<E> linkedQueueNode2) {
        E andNullValue = linkedQueueNode2.getAndNullValue();
        linkedQueueNode.soNext(linkedQueueNode);
        spConsumerNode(linkedQueueNode2);
        return andNullValue;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        LinkedQueueNode<E> linkedQueueNodeLpConsumerNode = lpConsumerNode();
        LinkedQueueNode<E> linkedQueueNodeLvNext = linkedQueueNodeLpConsumerNode.lvNext();
        if (linkedQueueNodeLvNext != null) {
            return getSingleConsumerNodeValue(linkedQueueNodeLpConsumerNode, linkedQueueNodeLvNext);
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        LinkedQueueNode<E> linkedQueueNodeLvNext = lpConsumerNode().lvNext();
        if (linkedQueueNodeLvNext != null) {
            return linkedQueueNodeLvNext.lpValue();
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer, int i) {
        if (consumer == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("limit is negative: " + i);
        }
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        LinkedQueueNode<E> linkedQueueNodeLpConsumerNode = lpConsumerNode();
        while (i2 < i) {
            LinkedQueueNode<E> linkedQueueNodeLvNext = linkedQueueNodeLpConsumerNode.lvNext();
            if (linkedQueueNodeLvNext == null) {
                return i2;
            }
            consumer.accept(getSingleConsumerNodeValue(linkedQueueNodeLpConsumerNode, linkedQueueNodeLvNext));
            i2++;
            linkedQueueNodeLpConsumerNode = linkedQueueNodeLvNext;
        }
        return i;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return MessagePassingQueueUtil.drain(this, consumer);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        MessagePassingQueueUtil.drain(this, consumer, waitStrategy, exitCondition);
    }
}
