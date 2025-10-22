package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes2.dex */
final class MigratingThreadDeframer implements ThreadOptimizedDeframer {
    private final ApplicationThreadDeframerListener appListener;
    private final MessageDeframer deframer;
    private final MigratingDeframerListener migratingListener;
    private final ApplicationThreadDeframerListener.TransportExecutor transportExecutor;
    private final MessageDeframer.Listener transportListener;
    private final DeframeMessageProducer messageProducer = new DeframeMessageProducer();
    private final Object lock = new Object();
    private final Queue<Op> opQueue = new ArrayDeque();
    private boolean deframerOnTransportThread;
    private boolean messageProducerEnqueued;

    public MigratingThreadDeframer(MessageDeframer.Listener listener, ApplicationThreadDeframerListener.TransportExecutor transportExecutor, MessageDeframer messageDeframer) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, "listener"));
        this.transportListener = squelchLateMessagesAvailableDeframerListener;
        this.transportExecutor = (ApplicationThreadDeframerListener.TransportExecutor) Preconditions.checkNotNull(transportExecutor, "transportExecutor");
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor);
        this.appListener = applicationThreadDeframerListener;
        MigratingDeframerListener migratingDeframerListener = new MigratingDeframerListener(applicationThreadDeframerListener);
        this.migratingListener = migratingDeframerListener;
        messageDeframer.setListener(migratingDeframerListener);
        this.deframer = messageDeframer;
    }

    @Override // io.grpc.internal.Deframer
    public void setMaxInboundMessageSize(int i) {
        this.deframer.setMaxInboundMessageSize(i);
    }

    @Override // io.grpc.internal.Deframer
    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    @Override // io.grpc.internal.Deframer
    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
    }

    private boolean runWhereAppropriate(Op op) {
        return runWhereAppropriate(op, true);
    }

    private boolean runWhereAppropriate(Op op, boolean z) {
        boolean z2;
        boolean z3;
        synchronized (this.lock) {
            z2 = this.deframerOnTransportThread;
            z3 = this.messageProducerEnqueued;
            if (!z2) {
                this.opQueue.offer(op);
                this.messageProducerEnqueued = true;
            }
        }
        if (z2) {
            op.run(true);
            return true;
        }
        if (z3) {
            return false;
        }
        if (z) {
            PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
            try {
                this.transportListener.messagesAvailable(this.messageProducer);
                return false;
            } finally {
                PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
            }
        }
        final Link linkLinkOut = PerfMark.linkOut();
        this.transportExecutor.runOnTransportThread(new Runnable() { // from class: io.grpc.internal.MigratingThreadDeframer.1
            @Override // java.lang.Runnable
            public void run() {
                PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                PerfMark.linkIn(linkLinkOut);
                try {
                    MigratingThreadDeframer.this.transportListener.messagesAvailable(MigratingThreadDeframer.this.messageProducer);
                } finally {
                    PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                }
            }
        });
        return false;
    }

    @Override // io.grpc.internal.ThreadOptimizedDeframer, io.grpc.internal.Deframer
    public void request(final int i) {
        runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean z) {
                if (z) {
                    final Link linkLinkOut = PerfMark.linkOut();
                    MigratingThreadDeframer.this.transportExecutor.runOnTransportThread(new Runnable() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestOp.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PerfMark.startTask("MigratingThreadDeframer.request");
                            PerfMark.linkIn(linkLinkOut);
                            try {
                                MigratingThreadDeframer.this.requestFromTransportThread(i);
                            } finally {
                                PerfMark.stopTask("MigratingThreadDeframer.request");
                            }
                        }
                    });
                } else {
                    PerfMark.startTask("MigratingThreadDeframer.request");
                    try {
                        MigratingThreadDeframer.this.deframer.request(i);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFromTransportThread(final int i) {
        runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestAgainOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean z) {
                if (z) {
                    try {
                        MigratingThreadDeframer.this.deframer.request(i);
                    } catch (Throwable th) {
                        MigratingThreadDeframer.this.appListener.deframeFailed(th);
                        MigratingThreadDeframer.this.deframer.close();
                    }
                    if (MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                        return;
                    }
                    synchronized (MigratingThreadDeframer.this.lock) {
                        PerfMark.event("MigratingThreadDeframer.deframerOnApplicationThread");
                        MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.appListener);
                        MigratingThreadDeframer.this.deframerOnTransportThread = false;
                    }
                    return;
                }
                MigratingThreadDeframer.this.request(i);
            }
        });
    }

    @Override // io.grpc.internal.Deframer
    public void deframe(ReadableBuffer readableBuffer) {
        runWhereAppropriate(new C1DeframeOp(readableBuffer));
    }

    @Override // io.grpc.internal.Deframer
    public void closeWhenComplete() {
        runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1CloseWhenCompleteOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean z) {
                MigratingThreadDeframer.this.deframer.closeWhenComplete();
            }
        });
    }

    @Override // io.grpc.internal.Deframer
    public void close() {
        if (runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1CloseOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean z) {
                MigratingThreadDeframer.this.deframer.close();
            }
        })) {
            return;
        }
        this.deframer.stopDelivery();
    }

    private interface Op {
        void run(boolean z);
    }

    static class MigratingDeframerListener extends ForwardingDeframerListener {
        private MessageDeframer.Listener delegate;

        public MigratingDeframerListener(MessageDeframer.Listener listener) {
            setDelegate(listener);
        }

        @Override // io.grpc.internal.ForwardingDeframerListener
        protected MessageDeframer.Listener delegate() {
            return this.delegate;
        }

        public void setDelegate(MessageDeframer.Listener listener) {
            this.delegate = (MessageDeframer.Listener) Preconditions.checkNotNull(listener, "delegate");
        }
    }

    /* renamed from: io.grpc.internal.MigratingThreadDeframer$1DeframeOp, reason: invalid class name */
    class C1DeframeOp implements Op, Closeable {
        final /* synthetic */ ReadableBuffer val$data;

        C1DeframeOp(ReadableBuffer readableBuffer) {
            this.val$data = readableBuffer;
        }

        @Override // io.grpc.internal.MigratingThreadDeframer.Op
        public void run(boolean z) {
            PerfMark.startTask("MigratingThreadDeframer.deframe");
            try {
                if (z) {
                    MigratingThreadDeframer.this.deframer.deframe(this.val$data);
                    return;
                }
                try {
                    MigratingThreadDeframer.this.deframer.deframe(this.val$data);
                } catch (Throwable th) {
                    MigratingThreadDeframer.this.appListener.deframeFailed(th);
                    MigratingThreadDeframer.this.deframer.close();
                }
            } finally {
                PerfMark.stopTask("MigratingThreadDeframer.deframe");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.val$data.close();
        }
    }

    class DeframeMessageProducer implements StreamListener.MessageProducer, Closeable {
        DeframeMessageProducer() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x002d, code lost:

            if (r4.this$0.deframer.hasPendingDeliveries() == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:

            io.perfmark.PerfMark.event("MigratingThreadDeframer.deframerOnTransportThread");
            r4.this$0.migratingListener.setDelegate(r4.this$0.transportListener);
            r4.this$0.deframerOnTransportThread = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0049, code lost:

            r4.this$0.messageProducerEnqueued = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x004f, code lost:

            return null;
         */
        @Override // io.grpc.internal.StreamListener.MessageProducer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.io.InputStream next() {
            /*
                r4 = this;
            L0:
                io.grpc.internal.MigratingThreadDeframer r0 = io.grpc.internal.MigratingThreadDeframer.this
                io.grpc.internal.ApplicationThreadDeframerListener r0 = io.grpc.internal.MigratingThreadDeframer.access$500(r0)
                java.io.InputStream r0 = r0.messageReadQueuePoll()
                if (r0 == 0) goto Ld
                return r0
            Ld:
                io.grpc.internal.MigratingThreadDeframer r0 = io.grpc.internal.MigratingThreadDeframer.this
                java.lang.Object r0 = io.grpc.internal.MigratingThreadDeframer.access$600(r0)
                monitor-enter(r0)
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L56
                java.util.Queue r1 = io.grpc.internal.MigratingThreadDeframer.access$900(r1)     // Catch: java.lang.Throwable -> L56
                java.lang.Object r1 = r1.poll()     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MigratingThreadDeframer$Op r1 = (io.grpc.internal.MigratingThreadDeframer.Op) r1     // Catch: java.lang.Throwable -> L56
                r2 = 0
                if (r1 != 0) goto L51
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MessageDeframer r1 = io.grpc.internal.MigratingThreadDeframer.access$400(r1)     // Catch: java.lang.Throwable -> L56
                boolean r1 = r1.hasPendingDeliveries()     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L49
                java.lang.String r1 = "MigratingThreadDeframer.deframerOnTransportThread"
                io.perfmark.PerfMark.event(r1)     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MigratingThreadDeframer$MigratingDeframerListener r1 = io.grpc.internal.MigratingThreadDeframer.access$700(r1)     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MigratingThreadDeframer r3 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MessageDeframer$Listener r3 = io.grpc.internal.MigratingThreadDeframer.access$100(r3)     // Catch: java.lang.Throwable -> L56
                r1.setDelegate(r3)     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L56
                r3 = 1
                io.grpc.internal.MigratingThreadDeframer.access$802(r1, r3)     // Catch: java.lang.Throwable -> L56
            L49:
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L56
                io.grpc.internal.MigratingThreadDeframer.access$1002(r1, r2)     // Catch: java.lang.Throwable -> L56
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                r0 = 0
                return r0
            L51:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                r1.run(r2)
                goto L0
            L56:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.MigratingThreadDeframer.DeframeMessageProducer.next():java.io.InputStream");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Op op;
            while (true) {
                synchronized (MigratingThreadDeframer.this.lock) {
                    do {
                        op = (Op) MigratingThreadDeframer.this.opQueue.poll();
                        if (op == null) {
                            break;
                        }
                    } while (!(op instanceof Closeable));
                    if (op == null) {
                        MigratingThreadDeframer.this.messageProducerEnqueued = false;
                        return;
                    }
                }
                GrpcUtil.closeQuietly((Closeable) op);
            }
        }
    }
}
