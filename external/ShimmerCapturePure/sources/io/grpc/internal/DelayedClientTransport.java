package io.grpc.internal;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ManagedClientTransport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
final class DelayedClientTransport implements ManagedClientTransport {
    private final Executor defaultAppExecutor;
    private final SynchronizationContext syncContext;
    private final InternalLogId logId = InternalLogId.allocate((Class<?>) DelayedClientTransport.class, (String) null);
    private final Object lock = new Object();
    @Nullable
    private LoadBalancer.SubchannelPicker lastPicker;
    private long lastPickerVersion;
    private ManagedClientTransport.Listener listener;
    private Runnable reportTransportInUse;
    private Runnable reportTransportNotInUse;
    private Runnable reportTransportTerminated;
    private Status shutdownStatus;
    @Nonnull
    private Collection<PendingStream> pendingStreams = new LinkedHashSet();

    DelayedClientTransport(Executor executor, SynchronizationContext synchronizationContext) {
        this.defaultAppExecutor = executor;
        this.syncContext = synchronizationContext;
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final Runnable start(final ManagedClientTransport.Listener listener) {
        this.listener = listener;
        this.reportTransportInUse = new Runnable() { // from class: io.grpc.internal.DelayedClientTransport.1
            @Override // java.lang.Runnable
            public void run() {
                listener.transportInUse(true);
            }
        };
        this.reportTransportNotInUse = new Runnable() { // from class: io.grpc.internal.DelayedClientTransport.2
            @Override // java.lang.Runnable
            public void run() {
                listener.transportInUse(false);
            }
        };
        this.reportTransportTerminated = new Runnable() { // from class: io.grpc.internal.DelayedClientTransport.3
            @Override // java.lang.Runnable
            public void run() {
                listener.transportTerminated();
            }
        };
        return null;
    }

    @Override // io.grpc.internal.ClientTransport
    public final ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        ClientStream failingClientStream;
        try {
            PickSubchannelArgsImpl pickSubchannelArgsImpl = new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions);
            LoadBalancer.SubchannelPicker subchannelPicker = null;
            long j = -1;
            while (true) {
                synchronized (this.lock) {
                    if (this.shutdownStatus == null) {
                        LoadBalancer.SubchannelPicker subchannelPicker2 = this.lastPicker;
                        if (subchannelPicker2 != null) {
                            if (subchannelPicker != null && j == this.lastPickerVersion) {
                                failingClientStream = createPendingStream(pickSubchannelArgsImpl);
                                break;
                            }
                            j = this.lastPickerVersion;
                            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(subchannelPicker2.pickSubchannel(pickSubchannelArgsImpl), callOptions.isWaitForReady());
                            if (transportFromPickResult != null) {
                                failingClientStream = transportFromPickResult.newStream(pickSubchannelArgsImpl.getMethodDescriptor(), pickSubchannelArgsImpl.getHeaders(), pickSubchannelArgsImpl.getCallOptions());
                                break;
                            }
                            subchannelPicker = subchannelPicker2;
                        } else {
                            failingClientStream = createPendingStream(pickSubchannelArgsImpl);
                            break;
                        }
                    } else {
                        failingClientStream = new FailingClientStream(this.shutdownStatus);
                        break;
                    }
                }
            }
            return failingClientStream;
        } finally {
            this.syncContext.drain();
        }
    }

    private PendingStream createPendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
        PendingStream pendingStream = new PendingStream(pickSubchannelArgs);
        this.pendingStreams.add(pendingStream);
        if (getPendingStreamsCount() == 1) {
            this.syncContext.executeLater(this.reportTransportInUse);
        }
        return pendingStream;
    }

    @Override // io.grpc.internal.ClientTransport
    public final void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
        throw new UnsupportedOperationException("This method is not expected to be called");
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture settableFutureCreate = SettableFuture.create();
        settableFutureCreate.set(null);
        return settableFutureCreate;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdown(final Status status) {
        Runnable runnable;
        synchronized (this.lock) {
            if (this.shutdownStatus != null) {
                return;
            }
            this.shutdownStatus = status;
            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.internal.DelayedClientTransport.4
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientTransport.this.listener.transportShutdown(status);
                }
            });
            if (!hasPendingStreams() && (runnable = this.reportTransportTerminated) != null) {
                this.syncContext.executeLater(runnable);
                this.reportTransportTerminated = null;
            }
            this.syncContext.drain();
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdownNow(Status status) {
        Collection<PendingStream> collection;
        Runnable runnable;
        shutdown(status);
        synchronized (this.lock) {
            collection = this.pendingStreams;
            runnable = this.reportTransportTerminated;
            this.reportTransportTerminated = null;
            if (!collection.isEmpty()) {
                this.pendingStreams = Collections.emptyList();
            }
        }
        if (runnable != null) {
            Iterator<PendingStream> it2 = collection.iterator();
            while (it2.hasNext()) {
                it2.next().cancel(status);
            }
            this.syncContext.execute(runnable);
        }
    }

    public final boolean hasPendingStreams() {
        boolean z;
        synchronized (this.lock) {
            z = !this.pendingStreams.isEmpty();
        }
        return z;
    }

    final int getPendingStreamsCount() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    final void reprocess(@Nullable LoadBalancer.SubchannelPicker subchannelPicker) {
        Runnable runnable;
        synchronized (this.lock) {
            this.lastPicker = subchannelPicker;
            this.lastPickerVersion++;
            if (subchannelPicker != null && hasPendingStreams()) {
                ArrayList arrayList = new ArrayList(this.pendingStreams);
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    final PendingStream pendingStream = (PendingStream) it2.next();
                    LoadBalancer.PickResult pickResultPickSubchannel = subchannelPicker.pickSubchannel(pendingStream.args);
                    CallOptions callOptions = pendingStream.args.getCallOptions();
                    final ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(pickResultPickSubchannel, callOptions.isWaitForReady());
                    if (transportFromPickResult != null) {
                        Executor executor = this.defaultAppExecutor;
                        if (callOptions.getExecutor() != null) {
                            executor = callOptions.getExecutor();
                        }
                        executor.execute(new Runnable() { // from class: io.grpc.internal.DelayedClientTransport.5
                            @Override // java.lang.Runnable
                            public void run() {
                                pendingStream.createRealStream(transportFromPickResult);
                            }
                        });
                        arrayList2.add(pendingStream);
                    }
                }
                synchronized (this.lock) {
                    if (hasPendingStreams()) {
                        this.pendingStreams.removeAll(arrayList2);
                        if (this.pendingStreams.isEmpty()) {
                            this.pendingStreams = new LinkedHashSet();
                        }
                        if (!hasPendingStreams()) {
                            this.syncContext.executeLater(this.reportTransportNotInUse);
                            if (this.shutdownStatus != null && (runnable = this.reportTransportTerminated) != null) {
                                this.syncContext.executeLater(runnable);
                                this.reportTransportTerminated = null;
                            }
                        }
                        this.syncContext.drain();
                    }
                }
            }
        }
    }

    private class PendingStream extends DelayedStream {
        private final LoadBalancer.PickSubchannelArgs args;
        private final Context context;

        private PendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            this.context = Context.current();
            this.args = pickSubchannelArgs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void createRealStream(ClientTransport clientTransport) {
            Context contextAttach = this.context.attach();
            try {
                ClientStream clientStreamNewStream = clientTransport.newStream(this.args.getMethodDescriptor(), this.args.getHeaders(), this.args.getCallOptions());
                this.context.detach(contextAttach);
                setStream(clientStreamNewStream);
            } catch (Throwable th) {
                this.context.detach(contextAttach);
                throw th;
            }
        }

        @Override // io.grpc.internal.DelayedStream, io.grpc.internal.ClientStream
        public void cancel(Status status) {
            super.cancel(status);
            synchronized (DelayedClientTransport.this.lock) {
                if (DelayedClientTransport.this.reportTransportTerminated != null) {
                    boolean zRemove = DelayedClientTransport.this.pendingStreams.remove(this);
                    if (!DelayedClientTransport.this.hasPendingStreams() && zRemove) {
                        DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportNotInUse);
                        if (DelayedClientTransport.this.shutdownStatus != null) {
                            DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportTerminated);
                            DelayedClientTransport.this.reportTransportTerminated = null;
                        }
                    }
                }
            }
            DelayedClientTransport.this.syncContext.drain();
        }
    }
}
