package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

/* loaded from: classes3.dex */
public final class IndexedQueueSizeUtil {

    public static int size(IndexedQueue indexedQueue) {
        long jLvProducerIndex;
        long jLvConsumerIndex;
        long jLvConsumerIndex2 = indexedQueue.lvConsumerIndex();
        while (true) {
            jLvProducerIndex = indexedQueue.lvProducerIndex();
            jLvConsumerIndex = indexedQueue.lvConsumerIndex();
            if (jLvConsumerIndex2 == jLvConsumerIndex) {
                break;
            }
            jLvConsumerIndex2 = jLvConsumerIndex;
        }
        long j = jLvProducerIndex - jLvConsumerIndex;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    public static boolean isEmpty(IndexedQueue indexedQueue) {
        return indexedQueue.lvConsumerIndex() == indexedQueue.lvProducerIndex();
    }

    public interface IndexedQueue {
        long lvConsumerIndex();

        long lvProducerIndex();
    }
}
