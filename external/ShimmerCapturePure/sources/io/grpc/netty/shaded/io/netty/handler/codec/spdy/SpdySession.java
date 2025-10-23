package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
final class SpdySession {
    private final AtomicInteger receiveWindowSize;
    private final AtomicInteger sendWindowSize;
    private final AtomicInteger activeLocalStreams = new AtomicInteger();
    private final AtomicInteger activeRemoteStreams = new AtomicInteger();
    private final Map<Integer, StreamState> activeStreams = PlatformDependent.newConcurrentHashMap();
    private final StreamComparator streamComparator = new StreamComparator();

    SpdySession(int i, int i2) {
        this.sendWindowSize = new AtomicInteger(i);
        this.receiveWindowSize = new AtomicInteger(i2);
    }

    int numActiveStreams(boolean z) {
        if (z) {
            return this.activeRemoteStreams.get();
        }
        return this.activeLocalStreams.get();
    }

    boolean noActiveStreams() {
        return this.activeStreams.isEmpty();
    }

    boolean isActiveStream(int i) {
        return this.activeStreams.containsKey(Integer.valueOf(i));
    }

    Map<Integer, StreamState> activeStreams() {
        TreeMap treeMap = new TreeMap(this.streamComparator);
        treeMap.putAll(this.activeStreams);
        return treeMap;
    }

    void acceptStream(int i, byte b, boolean z, boolean z2, int i2, int i3, boolean z3) {
        if (!(z && z2) && this.activeStreams.put(Integer.valueOf(i), new StreamState(b, z, z2, i2, i3)) == null) {
            if (z3) {
                this.activeRemoteStreams.incrementAndGet();
            } else {
                this.activeLocalStreams.incrementAndGet();
            }
        }
    }

    private StreamState removeActiveStream(int i, boolean z) {
        StreamState streamStateRemove = this.activeStreams.remove(Integer.valueOf(i));
        if (streamStateRemove != null) {
            if (z) {
                this.activeRemoteStreams.decrementAndGet();
            } else {
                this.activeLocalStreams.decrementAndGet();
            }
        }
        return streamStateRemove;
    }

    void removeStream(int i, Throwable th, boolean z) {
        StreamState streamStateRemoveActiveStream = removeActiveStream(i, z);
        if (streamStateRemoveActiveStream != null) {
            streamStateRemoveActiveStream.clearPendingWrites(th);
        }
    }

    boolean isRemoteSideClosed(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState == null || streamState.isRemoteSideClosed();
    }

    void closeRemoteSide(int i, boolean z) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            streamState.closeRemoteSide();
            if (streamState.isLocalSideClosed()) {
                removeActiveStream(i, z);
            }
        }
    }

    boolean isLocalSideClosed(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState == null || streamState.isLocalSideClosed();
    }

    void closeLocalSide(int i, boolean z) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            streamState.closeLocalSide();
            if (streamState.isRemoteSideClosed()) {
                removeActiveStream(i, z);
            }
        }
    }

    boolean hasReceivedReply(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState != null && streamState.hasReceivedReply();
    }

    void receivedReply(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            streamState.receivedReply();
        }
    }

    int getSendWindowSize(int i) {
        if (i == 0) {
            return this.sendWindowSize.get();
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.getSendWindowSize();
        }
        return -1;
    }

    int updateSendWindowSize(int i, int i2) {
        if (i == 0) {
            return this.sendWindowSize.addAndGet(i2);
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.updateSendWindowSize(i2);
        }
        return -1;
    }

    int updateReceiveWindowSize(int i, int i2) {
        if (i == 0) {
            return this.receiveWindowSize.addAndGet(i2);
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState == null) {
            return -1;
        }
        if (i2 > 0) {
            streamState.setReceiveWindowSizeLowerBound(0);
        }
        return streamState.updateReceiveWindowSize(i2);
    }

    int getReceiveWindowSizeLowerBound(int i) {
        StreamState streamState;
        if (i == 0 || (streamState = this.activeStreams.get(Integer.valueOf(i))) == null) {
            return 0;
        }
        return streamState.getReceiveWindowSizeLowerBound();
    }

    void updateAllSendWindowSizes(int i) {
        Iterator<StreamState> it2 = this.activeStreams.values().iterator();
        while (it2.hasNext()) {
            it2.next().updateSendWindowSize(i);
        }
    }

    void updateAllReceiveWindowSizes(int i) {
        for (StreamState streamState : this.activeStreams.values()) {
            streamState.updateReceiveWindowSize(i);
            if (i < 0) {
                streamState.setReceiveWindowSizeLowerBound(i);
            }
        }
    }

    boolean putPendingWrite(int i, PendingWrite pendingWrite) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState != null && streamState.putPendingWrite(pendingWrite);
    }

    PendingWrite getPendingWrite(int i) {
        PendingWrite pendingWrite;
        if (i == 0) {
            Iterator<Map.Entry<Integer, StreamState>> it2 = activeStreams().entrySet().iterator();
            while (it2.hasNext()) {
                StreamState value = it2.next().getValue();
                if (value.getSendWindowSize() > 0 && (pendingWrite = value.getPendingWrite()) != null) {
                    return pendingWrite;
                }
            }
            return null;
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.getPendingWrite();
        }
        return null;
    }

    PendingWrite removePendingWrite(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.removePendingWrite();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class StreamState {
        private final Queue<PendingWrite> pendingWriteQueue = new ConcurrentLinkedQueue();
        private final byte priority;
        private final AtomicInteger receiveWindowSize;
        private final AtomicInteger sendWindowSize;
        private boolean localSideClosed;
        private int receiveWindowSizeLowerBound;
        private boolean receivedReply;
        private boolean remoteSideClosed;

        StreamState(byte b, boolean z, boolean z2, int i, int i2) {
            this.priority = b;
            this.remoteSideClosed = z;
            this.localSideClosed = z2;
            this.sendWindowSize = new AtomicInteger(i);
            this.receiveWindowSize = new AtomicInteger(i2);
        }

        void closeLocalSide() {
            this.localSideClosed = true;
        }

        void closeRemoteSide() {
            this.remoteSideClosed = true;
        }

        byte getPriority() {
            return this.priority;
        }

        int getReceiveWindowSizeLowerBound() {
            return this.receiveWindowSizeLowerBound;
        }

        void setReceiveWindowSizeLowerBound(int i) {
            this.receiveWindowSizeLowerBound = i;
        }

        boolean hasReceivedReply() {
            return this.receivedReply;
        }

        boolean isLocalSideClosed() {
            return this.localSideClosed;
        }

        boolean isRemoteSideClosed() {
            return this.remoteSideClosed;
        }

        void receivedReply() {
            this.receivedReply = true;
        }

        int getSendWindowSize() {
            return this.sendWindowSize.get();
        }

        int updateSendWindowSize(int i) {
            return this.sendWindowSize.addAndGet(i);
        }

        int updateReceiveWindowSize(int i) {
            return this.receiveWindowSize.addAndGet(i);
        }

        boolean putPendingWrite(PendingWrite pendingWrite) {
            return this.pendingWriteQueue.offer(pendingWrite);
        }

        PendingWrite getPendingWrite() {
            return this.pendingWriteQueue.peek();
        }

        PendingWrite removePendingWrite() {
            return this.pendingWriteQueue.poll();
        }

        void clearPendingWrites(Throwable th) {
            while (true) {
                PendingWrite pendingWritePoll = this.pendingWriteQueue.poll();
                if (pendingWritePoll == null) {
                    return;
                } else {
                    pendingWritePoll.fail(th);
                }
            }
        }
    }

    public static final class PendingWrite {
        final ChannelPromise promise;
        final SpdyDataFrame spdyDataFrame;

        PendingWrite(SpdyDataFrame spdyDataFrame, ChannelPromise channelPromise) {
            this.spdyDataFrame = spdyDataFrame;
            this.promise = channelPromise;
        }

        void fail(Throwable th) {
            this.spdyDataFrame.release();
            this.promise.setFailure(th);
        }
    }

    private final class StreamComparator implements Comparator<Integer> {
        StreamComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Integer num, Integer num2) {
            int priority = ((StreamState) SpdySession.this.activeStreams.get(num)).getPriority() - ((StreamState) SpdySession.this.activeStreams.get(num2)).getPriority();
            return priority != 0 ? priority : num.intValue() - num2.intValue();
        }
    }
}
