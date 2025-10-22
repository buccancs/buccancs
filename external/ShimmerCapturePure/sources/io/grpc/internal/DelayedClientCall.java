package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientCall;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
class DelayedClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = Logger.getLogger(DelayedClientCall.class.getName());
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() { // from class: io.grpc.internal.DelayedClientCall.7
        @Override // io.grpc.ClientCall
        public void cancel(String str, Throwable th) {
        }

        @Override // io.grpc.ClientCall
        public void halfClose() {
        }

        @Override // io.grpc.ClientCall
        public boolean isReady() {
            return false;
        }

        @Override // io.grpc.ClientCall
        public void request(int i) {
        }

        @Override // io.grpc.ClientCall
        public void sendMessage(Object obj) {
        }

        @Override // io.grpc.ClientCall
        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };
    private final Executor callExecutor;
    private final Context context;
    @Nullable
    private final ScheduledFuture<?> initialDeadlineMonitor;
    private DelayedListener<RespT> delayedListener;
    private Status error;
    private ClientCall.Listener<RespT> listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingRunnables = new ArrayList();
    private ClientCall<ReqT, RespT> realCall;

    DelayedClientCall(Executor executor, ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        this.callExecutor = (Executor) Preconditions.checkNotNull(executor, "callExecutor");
        Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.context = Context.current();
        this.initialDeadlineMonitor = scheduleDeadlineIfNeeded(scheduledExecutorService, deadline);
    }

    protected void callCancelled() {
    }

    final ClientCall<ReqT, RespT> getRealCall() {
        return this.realCall;
    }

    private void setRealCall(ClientCall<ReqT, RespT> clientCall) {
        ClientCall<ReqT, RespT> clientCall2 = this.realCall;
        Preconditions.checkState(clientCall2 == null, "realCall already set to %s", clientCall2);
        ScheduledFuture<?> scheduledFuture = this.initialDeadlineMonitor;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.realCall = clientCall;
    }

    @Nullable
    private ScheduledFuture<?> scheduleDeadlineIfNeeded(ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        Deadline deadline2 = this.context.getDeadline();
        if (deadline == null && deadline2 == null) {
            return null;
        }
        long jMin = deadline != null ? Math.min(Long.MAX_VALUE, deadline.timeRemaining(TimeUnit.NANOSECONDS)) : Long.MAX_VALUE;
        if (deadline2 != null && deadline2.timeRemaining(TimeUnit.NANOSECONDS) < jMin) {
            jMin = deadline2.timeRemaining(TimeUnit.NANOSECONDS);
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(jMin)));
                if (deadline == null) {
                    sb.append(" Explicit call timeout was not set.");
                } else {
                    sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(deadline.timeRemaining(TimeUnit.NANOSECONDS))));
                }
                logger2.fine(sb.toString());
            }
        }
        long jAbs = Math.abs(jMin) / TimeUnit.SECONDS.toNanos(1L);
        long jAbs2 = Math.abs(jMin) % TimeUnit.SECONDS.toNanos(1L);
        final StringBuilder sb2 = new StringBuilder();
        if (jMin < 0) {
            sb2.append("ClientCall started after deadline exceeded. Deadline exceeded after -");
        } else {
            sb2.append("Deadline exceeded after ");
        }
        sb2.append(jAbs);
        sb2.append(String.format(".%09d", Long.valueOf(jAbs2)));
        sb2.append("s. ");
        return scheduledExecutorService.schedule(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.1DeadlineExceededRunnable
            @Override // java.lang.Runnable
            public void run() {
                DelayedClientCall.this.cancel(Status.DEADLINE_EXCEEDED.withDescription(sb2.toString()), true);
            }
        }, jMin, TimeUnit.NANOSECONDS);
    }

    final void setCall(ClientCall<ReqT, RespT> clientCall) {
        synchronized (this) {
            if (this.realCall != null) {
                return;
            }
            setRealCall((ClientCall) Preconditions.checkNotNull(clientCall, NotificationCompat.CATEGORY_CALL));
            drainPendingCalls();
        }
    }

    @Override // io.grpc.ClientCall
    public final void start(final ClientCall.Listener<RespT> listener, final Metadata metadata) {
        Status status;
        boolean z;
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            this.listener = (ClientCall.Listener) Preconditions.checkNotNull(listener, "listener");
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedListener<RespT> delayedListener = new DelayedListener<>(listener);
                this.delayedListener = delayedListener;
                listener = delayedListener;
            }
        }
        if (status != null) {
            this.callExecutor.execute(new CloseListenerRunnable(listener, status));
        } else if (z) {
            this.realCall.start(listener, metadata);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.1
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.start(listener, metadata);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void cancel(@Nullable String str, @Nullable Throwable th) {
        Status statusWithDescription;
        Status status = Status.CANCELLED;
        if (str != null) {
            statusWithDescription = status.withDescription(str);
        } else {
            statusWithDescription = status.withDescription("Call cancelled without message");
        }
        if (th != null) {
            statusWithDescription = statusWithDescription.withCause(th);
        }
        cancel(statusWithDescription, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void cancel(final Status status, boolean z) {
        boolean z2;
        ClientCall.Listener<RespT> listener;
        synchronized (this) {
            if (this.realCall == null) {
                setRealCall(NOOP_CALL);
                listener = this.listener;
                this.error = status;
                z2 = false;
            } else {
                if (z) {
                    return;
                }
                z2 = true;
                listener = null;
            }
            if (z2) {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedClientCall.this.realCall.cancel(status.getDescription(), status.getCause());
                    }
                });
            } else {
                if (listener != null) {
                    this.callExecutor.execute(new CloseListenerRunnable(listener, status));
                }
                drainPendingCalls();
            }
            callCancelled();
        }
    }

    private void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:

        r0 = r1.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:

        if (r0.hasNext() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:

        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drainPendingCalls() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L5:
            monitor-enter(r3)
            java.util.List<java.lang.Runnable> r1 = r3.pendingRunnables     // Catch: java.lang.Throwable -> L42
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L24
            r0 = 0
            r3.pendingRunnables = r0     // Catch: java.lang.Throwable -> L42
            r0 = 1
            r3.passThrough = r0     // Catch: java.lang.Throwable -> L42
            io.grpc.internal.DelayedClientCall$DelayedListener<RespT> r0 = r3.delayedListener     // Catch: java.lang.Throwable -> L42
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L23
            java.util.concurrent.Executor r1 = r3.callExecutor
            io.grpc.internal.DelayedClientCall$1DrainListenerRunnable r2 = new io.grpc.internal.DelayedClientCall$1DrainListenerRunnable
            r2.<init>()
            r1.execute(r2)
        L23:
            return
        L24:
            java.util.List<java.lang.Runnable> r1 = r3.pendingRunnables     // Catch: java.lang.Throwable -> L42
            r3.pendingRunnables = r0     // Catch: java.lang.Throwable -> L42
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            java.util.Iterator r0 = r1.iterator()
        L2d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L3d
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L2d
        L3d:
            r1.clear()
            r0 = r1
            goto L5
        L42:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientCall.drainPendingCalls():void");
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(final ReqT reqt) {
        if (this.passThrough) {
            this.realCall.sendMessage(reqt);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.sendMessage(reqt);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void setMessageCompression(final boolean z) {
        if (this.passThrough) {
            this.realCall.setMessageCompression(z);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.4
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.setMessageCompression(z);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void request(final int i) {
        if (this.passThrough) {
            this.realCall.request(i);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.5
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.request(i);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
        delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.6
            @Override // java.lang.Runnable
            public void run() {
                DelayedClientCall.this.realCall.halfClose();
            }
        });
    }

    @Override // io.grpc.ClientCall
    public final boolean isReady() {
        if (this.passThrough) {
            return this.realCall.isReady();
        }
        return false;
    }

    @Override // io.grpc.ClientCall
    public final Attributes getAttributes() {
        ClientCall<ReqT, RespT> clientCall;
        synchronized (this) {
            clientCall = this.realCall;
        }
        if (clientCall != null) {
            return clientCall.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("realCall", this.realCall).toString();
    }

    private static final class DelayedListener<RespT> extends ClientCall.Listener<RespT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ClientCall.Listener<RespT> realListener;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();

        public DelayedListener(ClientCall.Listener<RespT> listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onHeaders(final Metadata metadata) {
            if (this.passThrough) {
                this.realListener.onHeaders(metadata);
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onHeaders(metadata);
                    }
                });
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onMessage(final RespT respt) {
            if (this.passThrough) {
                this.realListener.onMessage(respt);
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onMessage(respt);
                    }
                });
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onClose(final Status status, final Metadata metadata) {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.3
                @Override // java.lang.Runnable
                public void run() {
                    DelayedListener.this.realListener.onClose(status, metadata);
                }
            });
        }

        @Override // io.grpc.ClientCall.Listener
        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onReady();
                    }
                });
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drainPendingCallbacks() {
            List list;
            List arrayList = new ArrayList();
            while (true) {
                synchronized (this) {
                    if (this.pendingCallbacks.isEmpty()) {
                        this.pendingCallbacks = null;
                        this.passThrough = true;
                        return;
                    } else {
                        list = this.pendingCallbacks;
                        this.pendingCallbacks = arrayList;
                    }
                }
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ((Runnable) it2.next()).run();
                }
                list.clear();
                arrayList = list;
            }
        }
    }

    private final class CloseListenerRunnable extends ContextRunnable {
        final ClientCall.Listener<RespT> listener;
        final Status status;

        CloseListenerRunnable(ClientCall.Listener<RespT> listener, Status status) {
            super(DelayedClientCall.this.context);
            this.listener = listener;
            this.status = status;
        }

        @Override // io.grpc.internal.ContextRunnable
        public void runInContext() {
            this.listener.onClose(this.status, new Metadata());
        }
    }
}
