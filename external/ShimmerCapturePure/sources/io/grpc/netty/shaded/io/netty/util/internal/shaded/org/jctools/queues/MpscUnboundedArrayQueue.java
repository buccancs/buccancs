package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;

import java.util.Iterator;

/* loaded from: classes3.dex */
public class MpscUnboundedArrayQueue<E> extends BaseMpscLinkedArrayQueue<E> {
    long p0;
    long p1;
    long p10;
    long p11;
    long p12;
    long p13;
    long p14;
    long p15;
    long p16;
    long p17;
    long p2;
    long p3;
    long p4;
    long p5;
    long p6;
    long p7;

    public MpscUnboundedArrayQueue(int i) {
        super(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue
    protected long availableInQueue(long j, long j2) {
        return 2147483647L;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int capacity() {
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue
    protected long getCurrentBufferCapacity(long j) {
        return j;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public /* bridge */ /* synthetic */ long currentConsumerIndex() {
        return super.currentConsumerIndex();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public /* bridge */ /* synthetic */ long currentProducerIndex() {
        return super.currentProducerIndex();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int drain(MessagePassingQueue.Consumer consumer, int i) {
        return super.drain(consumer, i);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ void drain(MessagePassingQueue.Consumer consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        super.drain(consumer, waitStrategy, exitCondition);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int fill(MessagePassingQueue.Supplier supplier, int i) {
        return super.fill(supplier, i);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ void fill(MessagePassingQueue.Supplier supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        super.fill(supplier, waitStrategy, exitCondition);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ boolean offer(Object obj) {
        return super.offer(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ Object peek() {
        return super.peek();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ Object poll() {
        return super.poll();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ boolean relaxedOffer(Object obj) {
        return super.relaxedOffer(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ Object relaxedPeek() {
        return super.relaxedPeek();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ Object relaxedPoll() {
        return super.relaxedPoll();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, java.util.AbstractCollection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return drain(consumer, 4096);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier) {
        return MessagePassingQueueUtil.fillUnbounded(this, supplier);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue
    protected int getNextBufferSize(E[] eArr) {
        return LinkedArrayQueueUtil.length(eArr);
    }
}
