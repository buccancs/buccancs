package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;

/* loaded from: classes3.dex */
public class SpscLinkedQueue<E> extends BaseLinkedQueue<E> {
    public SpscLinkedQueue() {
        LinkedQueueNode<E> linkedQueueNodeNewNode = newNode();
        spProducerNode(linkedQueueNodeNewNode);
        spConsumerNode(linkedQueueNodeNewNode);
        linkedQueueNodeNewNode.soNext(null);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int capacity() {
        return super.capacity();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int drain(MessagePassingQueue.Consumer consumer) {
        return super.drain(consumer);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int drain(MessagePassingQueue.Consumer consumer, int i) {
        return super.drain(consumer, i);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ void drain(MessagePassingQueue.Consumer consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        super.drain(consumer, waitStrategy, exitCondition);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ boolean relaxedOffer(Object obj) {
        return super.relaxedOffer(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ Object relaxedPeek() {
        return super.relaxedPeek();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ Object relaxedPoll() {
        return super.relaxedPoll();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseLinkedQueue, java.util.AbstractCollection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override
    // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw null;
        }
        LinkedQueueNode<E> linkedQueueNodeNewNode = newNode(e);
        lpProducerNode().soNext(linkedQueueNodeNewNode);
        spProducerNode(linkedQueueNodeNewNode);
        return true;
    }

    @Override
    // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        return (E) relaxedPoll();
    }

    @Override
    // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        return (E) relaxedPeek();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier) {
        return MessagePassingQueueUtil.fillUnbounded(this, supplier);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier, int i) {
        if (supplier == null) {
            throw new IllegalArgumentException("supplier is null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("limit is negative:" + i);
        }
        if (i == 0) {
            return 0;
        }
        LinkedQueueNode<E> linkedQueueNodeNewNode = newNode(supplier.get());
        int i2 = 1;
        LinkedQueueNode<E> linkedQueueNode = linkedQueueNodeNewNode;
        while (i2 < i) {
            LinkedQueueNode<E> linkedQueueNodeNewNode2 = newNode(supplier.get());
            linkedQueueNode.soNext(linkedQueueNodeNewNode2);
            i2++;
            linkedQueueNode = linkedQueueNodeNewNode2;
        }
        lpProducerNode().soNext(linkedQueueNodeNewNode);
        spProducerNode(linkedQueueNode);
        return i;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        if (waitStrategy == null) {
            throw new IllegalArgumentException("waiter is null");
        }
        if (exitCondition == null) {
            throw new IllegalArgumentException("exit condition is null");
        }
        if (supplier == null) {
            throw new IllegalArgumentException("supplier is null");
        }
        LinkedQueueNode<E> linkedQueueNodeLpProducerNode = lpProducerNode();
        while (exitCondition.keepRunning()) {
            int i = 0;
            while (i < 4096) {
                LinkedQueueNode<E> linkedQueueNodeNewNode = newNode(supplier.get());
                linkedQueueNodeLpProducerNode.soNext(linkedQueueNodeNewNode);
                spProducerNode(linkedQueueNodeNewNode);
                i++;
                linkedQueueNodeLpProducerNode = linkedQueueNodeNewNode;
            }
        }
    }
}
