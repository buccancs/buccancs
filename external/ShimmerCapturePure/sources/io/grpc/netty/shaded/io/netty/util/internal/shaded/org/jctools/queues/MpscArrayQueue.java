package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

import java.util.Iterator;

/* loaded from: classes3.dex */
public class MpscArrayQueue<E> extends MpscArrayQueueL3Pad<E> {
    public MpscArrayQueue(int i) {
        super(i);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int capacity() {
        return super.capacity();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public /* bridge */ /* synthetic */ long currentConsumerIndex() {
        return super.currentConsumerIndex();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public /* bridge */ /* synthetic */ long currentProducerIndex() {
        return super.currentProducerIndex();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.ConcurrentCircularArrayQueue, java.util.AbstractCollection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public boolean offerIfBelowThreshold(E e, int i) {
        long jLvProducerIndex;
        if (e == null) {
            throw null;
        }
        long j = this.mask;
        long j2 = j + 1;
        long jLvProducerLimit = lvProducerLimit();
        do {
            jLvProducerIndex = lvProducerIndex();
            long j3 = i;
            if (j2 - (jLvProducerLimit - jLvProducerIndex) >= j3) {
                long jLvConsumerIndex = lvConsumerIndex();
                if (jLvProducerIndex - jLvConsumerIndex >= j3) {
                    return false;
                }
                jLvProducerLimit = jLvConsumerIndex + j2;
                soProducerLimit(jLvProducerLimit);
            }
        } while (!casProducerIndex(jLvProducerIndex, jLvProducerIndex + 1));
        UnsafeRefArrayAccess.soRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(jLvProducerIndex, j), e);
        return true;
    }

    @Override
    // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        long jLvProducerIndex;
        if (e == null) {
            throw null;
        }
        long j = this.mask;
        long jLvProducerLimit = lvProducerLimit();
        do {
            jLvProducerIndex = lvProducerIndex();
            if (jLvProducerIndex >= jLvProducerLimit) {
                jLvProducerLimit = lvConsumerIndex() + j + 1;
                if (jLvProducerIndex >= jLvProducerLimit) {
                    return false;
                }
                soProducerLimit(jLvProducerLimit);
            }
        } while (!casProducerIndex(jLvProducerIndex, 1 + jLvProducerIndex));
        UnsafeRefArrayAccess.soRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(jLvProducerIndex, j), e);
        return true;
    }

    public final int failFastOffer(E e) {
        if (e == null) {
            throw null;
        }
        long j = this.mask;
        long j2 = j + 1;
        long jLvProducerIndex = lvProducerIndex();
        if (jLvProducerIndex >= lvProducerLimit()) {
            long jLvConsumerIndex = lvConsumerIndex() + j2;
            if (jLvProducerIndex >= jLvConsumerIndex) {
                return 1;
            }
            soProducerLimit(jLvConsumerIndex);
        }
        if (!casProducerIndex(jLvProducerIndex, 1 + jLvProducerIndex)) {
            return -1;
        }
        UnsafeRefArrayAccess.soRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(jLvProducerIndex, j), e);
        return 0;
    }

    @Override
    // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        long jLpConsumerIndex = lpConsumerIndex();
        long jCalcCircularRefElementOffset = UnsafeRefArrayAccess.calcCircularRefElementOffset(jLpConsumerIndex, this.mask);
        E[] eArr = this.buffer;
        E e = (E) UnsafeRefArrayAccess.lvRefElement(eArr, jCalcCircularRefElementOffset);
        if (e == null) {
            if (jLpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) UnsafeRefArrayAccess.lvRefElement(eArr, jCalcCircularRefElementOffset);
            } while (e == null);
        }
        UnsafeRefArrayAccess.soRefElement(eArr, jCalcCircularRefElementOffset, null);
        soConsumerIndex(jLpConsumerIndex + 1);
        return e;
    }

    @Override
    // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        E[] eArr = this.buffer;
        long jLpConsumerIndex = lpConsumerIndex();
        long jCalcCircularRefElementOffset = UnsafeRefArrayAccess.calcCircularRefElementOffset(jLpConsumerIndex, this.mask);
        E e = (E) UnsafeRefArrayAccess.lvRefElement(eArr, jCalcCircularRefElementOffset);
        if (e == null) {
            if (jLpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) UnsafeRefArrayAccess.lvRefElement(eArr, jCalcCircularRefElementOffset);
            } while (e == null);
        }
        return e;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        E[] eArr = this.buffer;
        long jLpConsumerIndex = lpConsumerIndex();
        long jCalcCircularRefElementOffset = UnsafeRefArrayAccess.calcCircularRefElementOffset(jLpConsumerIndex, this.mask);
        E e = (E) UnsafeRefArrayAccess.lvRefElement(eArr, jCalcCircularRefElementOffset);
        if (e == null) {
            return null;
        }
        UnsafeRefArrayAccess.soRefElement(eArr, jCalcCircularRefElementOffset, null);
        soConsumerIndex(jLpConsumerIndex + 1);
        return e;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        return (E) UnsafeRefArrayAccess.lvRefElement(this.buffer, UnsafeRefArrayAccess.calcCircularRefElementOffset(lpConsumerIndex(), this.mask));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer, int i) {
        if (consumer == 0) {
            throw new IllegalArgumentException("c is null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("limit is negative: " + i);
        }
        if (i == 0) {
            return 0;
        }
        E[] eArr = this.buffer;
        long j = this.mask;
        long jLpConsumerIndex = lpConsumerIndex();
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = i2 + jLpConsumerIndex;
            long jCalcCircularRefElementOffset = UnsafeRefArrayAccess.calcCircularRefElementOffset(j2, j);
            Object objLvRefElement = UnsafeRefArrayAccess.lvRefElement(eArr, jCalcCircularRefElementOffset);
            if (objLvRefElement == null) {
                return i2;
            }
            UnsafeRefArrayAccess.soRefElement(eArr, jCalcCircularRefElementOffset, null);
            soConsumerIndex(j2 + 1);
            consumer.accept(objLvRefElement);
        }
        return i;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier, int i) {
        long jLvProducerIndex;
        int iMin;
        if (supplier == null) {
            throw new IllegalArgumentException("supplier is null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("limit is negative:" + i);
        }
        if (i == 0) {
            return 0;
        }
        long j = this.mask;
        long j2 = 1 + j;
        long jLvProducerLimit = lvProducerLimit();
        do {
            jLvProducerIndex = lvProducerIndex();
            long j3 = jLvProducerLimit - jLvProducerIndex;
            if (j3 <= 0) {
                jLvProducerLimit = lvConsumerIndex() + j2;
                j3 = jLvProducerLimit - jLvProducerIndex;
                if (j3 <= 0) {
                    return 0;
                }
                soProducerLimit(jLvProducerLimit);
            }
            iMin = Math.min((int) j3, i);
        } while (!casProducerIndex(jLvProducerIndex, iMin + jLvProducerIndex));
        E[] eArr = this.buffer;
        for (int i2 = 0; i2 < iMin; i2++) {
            UnsafeRefArrayAccess.soRefElement(eArr, UnsafeRefArrayAccess.calcCircularRefElementOffset(i2 + jLvProducerIndex, j), supplier.get());
        }
        return iMin;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return drain(consumer, capacity());
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier) {
        return MessagePassingQueueUtil.fillBounded(this, supplier);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        MessagePassingQueueUtil.drain(this, consumer, waitStrategy, exitCondition);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        MessagePassingQueueUtil.fill(this, supplier, waitStrategy, exitCondition);
    }
}
