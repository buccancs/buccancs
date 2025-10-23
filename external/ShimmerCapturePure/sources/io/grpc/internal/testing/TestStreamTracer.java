package io.grpc.internal.testing;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.grpc.Status;
import io.grpc.StreamTracer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public interface TestStreamTracer {
    void await() throws InterruptedException;

    boolean await(long j, TimeUnit timeUnit) throws InterruptedException;

    long getInboundUncompressedSize();

    long getInboundWireSize();

    long getOutboundUncompressedSize();

    long getOutboundWireSize();

    Status getStatus();

    String nextInboundEvent();

    @Nullable
    String nextOutboundEvent();

    void setFailDuplicateCallbacks(boolean z);

    public static class TestBaseStreamTracer extends StreamTracer implements TestStreamTracer {
        protected final AtomicLong outboundWireSize = new AtomicLong();
        protected final AtomicLong inboundWireSize = new AtomicLong();
        protected final AtomicLong outboundUncompressedSize = new AtomicLong();
        protected final AtomicLong inboundUncompressedSize = new AtomicLong();
        protected final LinkedBlockingQueue<String> outboundEvents = new LinkedBlockingQueue<>();
        protected final LinkedBlockingQueue<String> inboundEvents = new LinkedBlockingQueue<>();
        protected final AtomicReference<Status> streamClosedStatus = new AtomicReference<>();
        protected final AtomicReference<Throwable> streamClosedStack = new AtomicReference<>();
        protected final CountDownLatch streamClosed = new CountDownLatch(1);
        protected final AtomicBoolean failDuplicateCallbacks = new AtomicBoolean(true);

        @Override // io.grpc.internal.testing.TestStreamTracer
        public void await() throws InterruptedException {
            this.streamClosed.await();
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.streamClosed.await(j, timeUnit);
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public Status getStatus() {
            return this.streamClosedStatus.get();
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public long getInboundWireSize() {
            return this.inboundWireSize.get();
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public long getInboundUncompressedSize() {
            return this.inboundUncompressedSize.get();
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public long getOutboundWireSize() {
            return this.outboundWireSize.get();
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public long getOutboundUncompressedSize() {
            return this.outboundUncompressedSize.get();
        }

        @Override // io.grpc.StreamTracer
        public void outboundWireSize(long j) {
            this.outboundWireSize.addAndGet(j);
        }

        @Override // io.grpc.StreamTracer
        public void inboundWireSize(long j) {
            this.inboundWireSize.addAndGet(j);
        }

        @Override // io.grpc.StreamTracer
        public void outboundUncompressedSize(long j) {
            this.outboundUncompressedSize.addAndGet(j);
        }

        @Override // io.grpc.StreamTracer
        public void inboundUncompressedSize(long j) {
            this.inboundUncompressedSize.addAndGet(j);
        }

        @Override // io.grpc.StreamTracer
        public void streamClosed(Status status) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.streamClosedStack, null, new Throwable("first call"));
            if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.streamClosedStatus, null, status)) {
                if (this.failDuplicateCallbacks.get()) {
                    throw new AssertionError("streamClosed called more than once", this.streamClosedStack.get());
                }
            } else {
                this.streamClosed.countDown();
            }
        }

        @Override // io.grpc.StreamTracer
        public void inboundMessage(int i) {
            this.inboundEvents.add("inboundMessage(" + i + ")");
        }

        @Override // io.grpc.StreamTracer
        public void outboundMessage(int i) {
            this.outboundEvents.add("outboundMessage(" + i + ")");
        }

        @Override // io.grpc.StreamTracer
        public void outboundMessageSent(int i, long j, long j2) {
            this.outboundEvents.add(String.format("outboundMessageSent(%d, %d, %d)", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2)));
        }

        @Override // io.grpc.StreamTracer
        public void inboundMessageRead(int i, long j, long j2) {
            this.inboundEvents.add(String.format("inboundMessageRead(%d, %d, %d)", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2)));
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public void setFailDuplicateCallbacks(boolean z) {
            this.failDuplicateCallbacks.set(z);
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public String nextOutboundEvent() {
            return this.outboundEvents.poll();
        }

        @Override // io.grpc.internal.testing.TestStreamTracer
        public String nextInboundEvent() {
            return this.inboundEvents.poll();
        }
    }
}
