package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/* loaded from: classes3.dex */
public final class UniformStreamByteDistributor implements StreamByteDistributor {
    private final Http2Connection.PropertyKey stateKey;
    private final Deque<State> queue = new ArrayDeque(4);
    private long totalStreamableBytes;
    private int minAllocationChunk = 1024;

    public UniformStreamByteDistributor(Http2Connection http2Connection) {
        Http2Connection.PropertyKey propertyKeyNewKey = http2Connection.newKey();
        this.stateKey = propertyKeyNewKey;
        Http2Stream http2StreamConnectionStream = http2Connection.connectionStream();
        http2StreamConnectionStream.setProperty(propertyKeyNewKey, new State(http2StreamConnectionStream));
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor.1
            @Override
            // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamAdded(Http2Stream http2Stream) {
                http2Stream.setProperty(UniformStreamByteDistributor.this.stateKey, UniformStreamByteDistributor.this.new State(http2Stream));
            }

            @Override
            // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                UniformStreamByteDistributor.this.state(http2Stream).close();
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor
    public void updateDependencyTree(int i, int i2, short s, boolean z) {
    }

    public void minAllocationChunk(int i) {
        ObjectUtil.checkPositive(i, "minAllocationChunk");
        this.minAllocationChunk = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor
    public void updateStreamableBytes(StreamByteDistributor.StreamState streamState) {
        state(streamState.stream()).updateStreamableBytes(Http2CodecUtil.streamableBytes(streamState), streamState.hasFrame(), streamState.windowSize());
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0052 A[EDGE_INSN: B:25:0x0052->B:20:0x0052 BREAK  A[LOOP:0: B:10:0x0025->B:27:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[LOOP:0: B:10:0x0025->B:27:?, LOOP_END, SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean distribute(int r8, io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor.Writer r9) throws io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception {
        /*
            r7 = this;
            java.util.Deque<io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor$State> r0 = r7.queue
            int r0 = r0.size()
            r1 = 0
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L15
            long r8 = r7.totalStreamableBytes
            int r0 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r0 <= 0) goto L13
            goto L14
        L13:
            r3 = 0
        L14:
            return r3
        L15:
            int r5 = r7.minAllocationChunk
            int r0 = r8 / r0
            int r0 = java.lang.Math.max(r5, r0)
            java.util.Deque<io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor$State> r5 = r7.queue
            java.lang.Object r5 = r5.pollFirst()
            io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor$State r5 = (io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor.State) r5
        L25:
            r5.enqueued = r4
            boolean r6 = r5.windowNegative
            if (r6 == 0) goto L2c
            goto L48
        L2c:
            if (r8 != 0) goto L3a
            int r6 = r5.streamableBytes
            if (r6 <= 0) goto L3a
            java.util.Deque<io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor$State> r8 = r7.queue
            r8.addFirst(r5)
            r5.enqueued = r3
            goto L52
        L3a:
            int r6 = r5.streamableBytes
            int r6 = java.lang.Math.min(r8, r6)
            int r6 = java.lang.Math.min(r0, r6)
            int r8 = r8 - r6
            r5.write(r6, r9)
        L48:
            java.util.Deque<io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor$State> r5 = r7.queue
            java.lang.Object r5 = r5.pollFirst()
            io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor$State r5 = (io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor.State) r5
            if (r5 != 0) goto L25
        L52:
            long r8 = r7.totalStreamableBytes
            int r0 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r0 <= 0) goto L59
            goto L5a
        L59:
            r3 = 0
        L5a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor.distribute(int, io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor$Writer):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public State state(Http2Stream http2Stream) {
        return (State) ((Http2Stream) ObjectUtil.checkNotNull(http2Stream, "stream")).getProperty(this.stateKey);
    }

    private final class State {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final Http2Stream stream;
        boolean enqueued;
        int streamableBytes;
        boolean windowNegative;
        boolean writing;

        State(Http2Stream http2Stream) {
            this.stream = http2Stream;
        }

        void updateStreamableBytes(int i, boolean z, int i2) {
            int i3 = i - this.streamableBytes;
            if (i3 != 0) {
                this.streamableBytes = i;
                UniformStreamByteDistributor.this.totalStreamableBytes += i3;
            }
            this.windowNegative = i2 < 0;
            if (z) {
                if (i2 > 0 || (i2 == 0 && !this.writing)) {
                    addToQueue();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void write(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
            this.writing = true;
            try {
                writer.write(this.stream, i);
            } finally {
            }
        }

        void addToQueue() {
            if (this.enqueued) {
                return;
            }
            this.enqueued = true;
            UniformStreamByteDistributor.this.queue.addLast(this);
        }

        void removeFromQueue() {
            if (this.enqueued) {
                this.enqueued = false;
                UniformStreamByteDistributor.this.queue.remove(this);
            }
        }

        void close() {
            removeFromQueue();
            updateStreamableBytes(0, false, 0);
        }
    }
}
