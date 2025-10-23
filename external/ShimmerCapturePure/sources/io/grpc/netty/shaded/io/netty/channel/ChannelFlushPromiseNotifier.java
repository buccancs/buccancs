package io.grpc.netty.shaded.io.netty.channel;

import com.shimmerresearch.driver.ShimmerObject;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes3.dex */
public final class ChannelFlushPromiseNotifier {
    private final Queue<FlushCheckpoint> flushCheckpoints;
    private final boolean tryNotify;
    private long writeCounter;

    public ChannelFlushPromiseNotifier(boolean z) {
        this.flushCheckpoints = new ArrayDeque();
        this.tryNotify = z;
    }

    public ChannelFlushPromiseNotifier() {
        this(false);
    }

    public long writeCounter() {
        return this.writeCounter;
    }

    @Deprecated
    public ChannelFlushPromiseNotifier add(ChannelPromise channelPromise, int i) {
        return add(channelPromise, i);
    }

    public ChannelFlushPromiseNotifier add(ChannelPromise channelPromise, long j) {
        ObjectUtil.checkNotNull(channelPromise, "promise");
        ObjectUtil.checkPositiveOrZero(j, "pendingDataSize");
        long j2 = this.writeCounter + j;
        if (channelPromise instanceof FlushCheckpoint) {
            FlushCheckpoint flushCheckpoint = (FlushCheckpoint) channelPromise;
            flushCheckpoint.flushCheckpoint(j2);
            this.flushCheckpoints.add(flushCheckpoint);
        } else {
            this.flushCheckpoints.add(new DefaultFlushCheckpoint(j2, channelPromise));
        }
        return this;
    }

    public ChannelFlushPromiseNotifier increaseWriteCounter(long j) {
        ObjectUtil.checkPositiveOrZero(j, "delta");
        this.writeCounter += j;
        return this;
    }

    public ChannelFlushPromiseNotifier notifyPromises() {
        notifyPromises0(null);
        return this;
    }

    @Deprecated
    public ChannelFlushPromiseNotifier notifyFlushFutures() {
        return notifyPromises();
    }

    public ChannelFlushPromiseNotifier notifyPromises(Throwable th) {
        notifyPromises();
        while (true) {
            FlushCheckpoint flushCheckpointPoll = this.flushCheckpoints.poll();
            if (flushCheckpointPoll == null) {
                return this;
            }
            if (this.tryNotify) {
                flushCheckpointPoll.promise().tryFailure(th);
            } else {
                flushCheckpointPoll.promise().setFailure(th);
            }
        }
    }

    @Deprecated
    public ChannelFlushPromiseNotifier notifyFlushFutures(Throwable th) {
        return notifyPromises(th);
    }

    public ChannelFlushPromiseNotifier notifyPromises(Throwable th, Throwable th2) {
        notifyPromises0(th);
        while (true) {
            FlushCheckpoint flushCheckpointPoll = this.flushCheckpoints.poll();
            if (flushCheckpointPoll == null) {
                return this;
            }
            if (this.tryNotify) {
                flushCheckpointPoll.promise().tryFailure(th2);
            } else {
                flushCheckpointPoll.promise().setFailure(th2);
            }
        }
    }

    @Deprecated
    public ChannelFlushPromiseNotifier notifyFlushFutures(Throwable th, Throwable th2) {
        return notifyPromises(th, th2);
    }

    private void notifyPromises0(Throwable th) {
        if (this.flushCheckpoints.isEmpty()) {
            this.writeCounter = 0L;
            return;
        }
        long j = this.writeCounter;
        while (true) {
            FlushCheckpoint flushCheckpointPeek = this.flushCheckpoints.peek();
            if (flushCheckpointPeek == null) {
                this.writeCounter = 0L;
                break;
            }
            if (flushCheckpointPeek.flushCheckpoint() > j) {
                if (j > 0 && this.flushCheckpoints.size() == 1) {
                    this.writeCounter = 0L;
                    flushCheckpointPeek.flushCheckpoint(flushCheckpointPeek.flushCheckpoint() - j);
                }
            } else {
                this.flushCheckpoints.remove();
                ChannelPromise channelPromisePromise = flushCheckpointPeek.promise();
                if (th == null) {
                    if (this.tryNotify) {
                        channelPromisePromise.trySuccess();
                    } else {
                        channelPromisePromise.setSuccess();
                    }
                } else if (this.tryNotify) {
                    channelPromisePromise.tryFailure(th);
                } else {
                    channelPromisePromise.setFailure(th);
                }
            }
        }
        long j2 = this.writeCounter;
        if (j2 >= ShimmerObject.SDLogHeader.GYRO_MPU_MPL) {
            this.writeCounter = 0L;
            for (FlushCheckpoint flushCheckpoint : this.flushCheckpoints) {
                flushCheckpoint.flushCheckpoint(flushCheckpoint.flushCheckpoint() - j2);
            }
        }
    }

    interface FlushCheckpoint {
        long flushCheckpoint();

        void flushCheckpoint(long j);

        ChannelPromise promise();
    }

    private static class DefaultFlushCheckpoint implements FlushCheckpoint {
        private final ChannelPromise future;
        private long checkpoint;

        DefaultFlushCheckpoint(long j, ChannelPromise channelPromise) {
            this.checkpoint = j;
            this.future = channelPromise;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
        public long flushCheckpoint() {
            return this.checkpoint;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
        public void flushCheckpoint(long j) {
            this.checkpoint = j;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
        public ChannelPromise promise() {
            return this.future;
        }
    }
}
