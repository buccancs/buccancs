package io.grpc.internal;

import androidx.core.app.NotificationManagerCompat;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.HedgingPolicy;
import io.grpc.internal.RetryPolicy;
import io.grpc.internal.StreamListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* loaded from: classes2.dex */
abstract class RetriableStream<ReqT> implements ClientStream {
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    private static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    private static Random random = new Random();
    private final Executor callExecutor;
    private final long channelBufferLimit;
    private final ChannelBufferMeter channelBufferUsed;
    private final Metadata headers;
    private final HedgingPolicy.Provider hedgingPolicyProvider;
    private final MethodDescriptor<ReqT, ?> method;
    private final long perRpcBufferLimit;
    private final RetryPolicy.Provider retryPolicyProvider;
    private final ScheduledExecutorService scheduledExecutorService;
    @Nullable
    private final Throttle throttle;
    private final Object lock = new Object();
    private final InsightBuilder closedSubstreamsInsight = new InsightBuilder();
    private final AtomicBoolean noMoreTransparentRetry = new AtomicBoolean();
    private HedgingPolicy hedgingPolicy;
    private boolean isHedging;
    private ClientStreamListener masterListener;
    private long nextBackoffIntervalNanos;
    private long perRpcBufferUsed;
    private RetryPolicy retryPolicy;
    private FutureCanceller scheduledHedging;
    private FutureCanceller scheduledRetry;
    private volatile State state = new State(new ArrayList(8), Collections.emptyList(), null, null, false, false, false, 0);

    RetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, Metadata metadata, ChannelBufferMeter channelBufferMeter, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService, RetryPolicy.Provider provider, HedgingPolicy.Provider provider2, @Nullable Throttle throttle) {
        this.method = methodDescriptor;
        this.channelBufferUsed = channelBufferMeter;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService;
        this.headers = metadata;
        this.retryPolicyProvider = (RetryPolicy.Provider) Preconditions.checkNotNull(provider, "retryPolicyProvider");
        this.hedgingPolicyProvider = (HedgingPolicy.Provider) Preconditions.checkNotNull(provider2, "hedgingPolicyProvider");
        this.throttle = throttle;
    }

    static void setRandom(Random random2) {
        random = random2;
    }

    abstract ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata);

    abstract void postCommit();

    @CheckReturnValue
    @Nullable
    abstract Status prestart();

    /* JADX INFO: Access modifiers changed from: private */
    @CheckReturnValue
    @Nullable
    public Runnable commit(final Substream substream) {
        final Future<?> future;
        final Future<?> future2;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> collection = this.state.drainedSubstreams;
            this.state = this.state.committed(substream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                Future<?> futureMarkCancelled = futureCanceller.markCancelled();
                this.scheduledRetry = null;
                future = futureMarkCancelled;
            } else {
                future = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                Future<?> futureMarkCancelled2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
                future2 = futureMarkCancelled2;
            } else {
                future2 = null;
            }
            return new Runnable() { // from class: io.grpc.internal.RetriableStream.1CommitTask
                @Override // java.lang.Runnable
                public void run() {
                    for (Substream substream2 : collection) {
                        if (substream2 != substream) {
                            substream2.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    Future future3 = future;
                    if (future3 != null) {
                        future3.cancel(false);
                    }
                    Future future4 = future2;
                    if (future4 != null) {
                        future4.cancel(false);
                    }
                    RetriableStream.this.postCommit();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitAndRun(Substream substream) {
        Runnable runnableCommit = commit(substream);
        if (runnableCommit != null) {
            runnableCommit.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Substream createSubstream(int i) {
        Substream substream = new Substream(i);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(substream);
        substream.stream = newSubstream(new ClientStreamTracer.Factory() { // from class: io.grpc.internal.RetriableStream.1
            @Override // io.grpc.ClientStreamTracer.Factory
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                return bufferSizeTracer;
            }
        }, updateHeaders(this.headers, i));
        return substream;
    }

    final Metadata updateHeaders(Metadata metadata, int i) {
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        return metadata2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drain(Substream substream) {
        ArrayList<BufferEntry> arrayList = null;
        int i = 0;
        while (true) {
            synchronized (this.lock) {
                State state = this.state;
                if (state.winningSubstream == null || state.winningSubstream == substream) {
                    if (i == state.buffer.size()) {
                        this.state = state.substreamDrained(substream);
                        return;
                    }
                    if (substream.closed) {
                        return;
                    }
                    int iMin = Math.min(i + 128, state.buffer.size());
                    if (arrayList == null) {
                        arrayList = new ArrayList(state.buffer.subList(i, iMin));
                    } else {
                        arrayList.clear();
                        arrayList.addAll(state.buffer.subList(i, iMin));
                    }
                    for (BufferEntry bufferEntry : arrayList) {
                        State state2 = this.state;
                        if (state2.winningSubstream == null || state2.winningSubstream == substream) {
                            if (state2.cancelled) {
                                Preconditions.checkState(state2.winningSubstream == substream, "substream should be CANCELLED_BECAUSE_COMMITTED already");
                                return;
                            }
                            bufferEntry.runWith(substream);
                        }
                    }
                    i = iMin;
                } else {
                    substream.stream.cancel(CANCELLED_BECAUSE_COMMITTED);
                    return;
                }
            }
        }
    }

    @Override // io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        FutureCanceller futureCanceller;
        Throttle throttle;
        this.masterListener = clientStreamListener;
        Status statusPrestart = prestart();
        if (statusPrestart != null) {
            cancel(statusPrestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1StartEntry
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.start(new Sublistener(substream));
                }
            });
        }
        Substream substreamCreateSubstream = createSubstream(0);
        Preconditions.checkState(this.hedgingPolicy == null, "hedgingPolicy has been initialized unexpectedly");
        this.hedgingPolicy = this.hedgingPolicyProvider.get();
        if (!HedgingPolicy.DEFAULT.equals(this.hedgingPolicy)) {
            this.isHedging = true;
            this.retryPolicy = RetryPolicy.DEFAULT;
            synchronized (this.lock) {
                this.state = this.state.addActiveHedge(substreamCreateSubstream);
                if (hasPotentialHedging(this.state) && ((throttle = this.throttle) == null || throttle.isAboveThreshold())) {
                    futureCanceller = new FutureCanceller(this.lock);
                    this.scheduledHedging = futureCanceller;
                } else {
                    futureCanceller = null;
                }
            }
            if (futureCanceller != null) {
                futureCanceller.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
            }
        }
        drain(substreamCreateSubstream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushbackHedging(@Nullable Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 0) {
            freezeHedging();
            return;
        }
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            if (futureCanceller == null) {
                return;
            }
            Future<?> futureMarkCancelled = futureCanceller.markCancelled();
            FutureCanceller futureCanceller2 = new FutureCanceller(this.lock);
            this.scheduledHedging = futureCanceller2;
            if (futureMarkCancelled != null) {
                futureMarkCancelled.cancel(false);
            }
            futureCanceller2.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller2), num.intValue(), TimeUnit.MILLISECONDS));
        }
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
        Substream substream = new Substream(0);
        substream.stream = new NoopClientStream();
        Runnable runnableCommit = commit(substream);
        if (runnableCommit != null) {
            this.masterListener.closed(status, new Metadata());
            runnableCommit.run();
        } else {
            this.state.winningSubstream.stream.cancel(status);
            synchronized (this.lock) {
                this.state = this.state.cancelled();
            }
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        Iterator<Substream> it2 = collection.iterator();
        while (it2.hasNext()) {
            bufferEntry.runWith(it2.next());
        }
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    final void sendMessage(final ReqT reqt) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.writeMessage(this.method.streamRequest(reqt));
        } else {
            delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1SendMessageEntry
                /* JADX WARN: Multi-variable type inference failed */
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(RetriableStream.this.method.streamRequest(reqt));
                }
            });
        }
    }

    @Override // io.grpc.internal.Stream
    public final void request(final int i) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1RequestEntry
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.request(i);
                }
            });
        }
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1FlushEntry
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    @Override // io.grpc.internal.Stream
    public final boolean isReady() {
        Iterator<Substream> it2 = this.state.drainedSubstreams.iterator();
        while (it2.hasNext()) {
            if (it2.next().stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    @Override // io.grpc.internal.Stream
    public void optimizeForDirectExecutor() {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1OptimizeDirectEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.optimizeForDirectExecutor();
            }
        });
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1CompressorEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setFullStreamDecompression(final boolean z) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1FullStreamDecompressionEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(z);
            }
        });
    }

    @Override // io.grpc.internal.Stream
    public final void setMessageCompression(final boolean z) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1MessageCompressionEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(z);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1HalfCloseEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setAuthority(final String str) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1AuthorityEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setAuthority(str);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1DecompressorRegistryEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1MaxInboundMessageSizeEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(i);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1MaxOutboundMessageSizeEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(i);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1DeadlineEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    @Override // io.grpc.internal.ClientStream
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        State state;
        synchronized (this.lock) {
            insightBuilder.appendKeyValue("closed", this.closedSubstreamsInsight);
            state = this.state;
        }
        if (state.winningSubstream != null) {
            InsightBuilder insightBuilder2 = new InsightBuilder();
            state.winningSubstream.stream.appendTimeoutInsight(insightBuilder2);
            insightBuilder.appendKeyValue("committed", insightBuilder2);
            return;
        }
        InsightBuilder insightBuilder3 = new InsightBuilder();
        for (Substream substream : state.drainedSubstreams) {
            InsightBuilder insightBuilder4 = new InsightBuilder();
            substream.stream.appendTimeoutInsight(insightBuilder4);
            insightBuilder3.append(insightBuilder4);
        }
        insightBuilder.appendKeyValue(AbstractCircuitBreaker.PROPERTY_NAME, insightBuilder3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasPotentialHedging(State state) {
        return state.winningSubstream == null && state.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state.hedgingFrozen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freezeHedging() {
        Future<?> future;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            future = null;
            if (futureCanceller != null) {
                Future<?> futureMarkCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
                future = futureMarkCancelled;
            }
            this.state = this.state.freezeHedging();
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    private interface BufferEntry {
        void runWith(Substream substream);
    }

    private static final class State {
        final Collection<Substream> activeHedges;

        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;

        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> list, Collection<Substream> collection, Collection<Substream> collection2, @Nullable Substream substream, boolean z, boolean z2, boolean z3, int i) {
            this.buffer = list;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.winningSubstream = substream;
            this.activeHedges = collection2;
            this.cancelled = z;
            this.passThrough = z2;
            this.hedgingFrozen = z3;
            this.hedgingAttemptCount = i;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState((z2 && substream == null) ? false : true, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(substream)) || (collection.size() == 0 && substream.closed), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((z && substream == null) ? false : true, "cancelled should imply committed");
        }

        @CheckReturnValue
        State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, true, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State substreamDrained(Substream substream) {
            Collection collectionUnmodifiableCollection;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                collectionUnmodifiableCollection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                collectionUnmodifiableCollection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                collectionUnmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            Collection collection = collectionUnmodifiableCollection;
            Substream substream2 = this.winningSubstream;
            boolean z = substream2 != null;
            List<BufferEntry> list = this.buffer;
            if (z) {
                Preconditions.checkState(substream2 == substream, "Another RPC attempt has already committed");
                list = null;
            }
            return new State(list, collection, this.activeHedges, this.winningSubstream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State substreamClosed(Substream substream) {
            substream.closed = true;
            if (!this.drainedSubstreams.contains(substream)) {
                return this;
            }
            ArrayList arrayList = new ArrayList(this.drainedSubstreams);
            arrayList.remove(substream);
            return new State(this.buffer, Collections.unmodifiableCollection(arrayList), this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State committed(Substream substream) {
            List<BufferEntry> list;
            Collection collectionEmptyList;
            boolean z;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            List<BufferEntry> list2 = this.buffer;
            if (this.drainedSubstreams.contains(substream)) {
                collectionEmptyList = Collections.singleton(substream);
                list = null;
                z = true;
            } else {
                list = list2;
                collectionEmptyList = Collections.emptyList();
                z = false;
            }
            return new State(list, collectionEmptyList, this.activeHedges, substream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State freezeHedging() {
            return this.hedgingFrozen ? this : new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State addActiveHedge(Substream substream) {
            Collection collectionUnmodifiableCollection;
            Preconditions.checkState(!this.hedgingFrozen, "hedging frozen");
            Preconditions.checkState(this.winningSubstream == null, "already committed");
            if (this.activeHedges == null) {
                collectionUnmodifiableCollection = Collections.singleton(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.activeHedges);
                arrayList.add(substream);
                collectionUnmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            return new State(this.buffer, this.drainedSubstreams, collectionUnmodifiableCollection, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount + 1);
        }

        @CheckReturnValue
        State removeActiveHedge(Substream substream) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State replaceActiveHedge(Substream substream, Substream substream2) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            arrayList.add(substream2);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    private static final class Substream {
        final int previousAttemptCount;
        boolean bufferLimitExceeded;
        boolean closed;
        ClientStream stream;

        Substream(int i) {
            this.previousAttemptCount = i;
        }
    }

    static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter() {
        }

        long addAndGet(long j) {
            return this.bufferUsed.addAndGet(j);
        }
    }

    static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        Throttle(float f, float f2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (f2 * 1000.0f);
            int i = (int) (f * 1000.0f);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                if (i == 0) {
                    return false;
                }
                i2 = i + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            return i2 > this.threshold;
        }

        void onSuccess() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                i2 = this.maxTokens;
                if (i == i2) {
                    return;
                }
            } while (!this.tokenCount.compareAndSet(i, Math.min(this.tokenRatio + i, i2)));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Throttle)) {
                return false;
            }
            Throttle throttle = (Throttle) obj;
            return this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    private static final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        RetryPlan(boolean z, long j) {
            this.shouldRetry = z;
            this.backoffNanos = j;
        }
    }

    private static final class HedgingPlan {

        @Nullable
        final Integer hedgingPushbackMillis;
        final boolean isHedgeable;

        public HedgingPlan(boolean z, @Nullable Integer num) {
            this.isHedgeable = z;
            this.hedgingPushbackMillis = num;
        }
    }

    private static final class FutureCanceller {
        final Object lock;
        boolean cancelled;
        Future<?> future;

        FutureCanceller(Object obj) {
            this.lock = obj;
        }

        boolean isCancelled() {
            return this.cancelled;
        }

        @CheckForNull
        Future<?> markCancelled() {
            this.cancelled = true;
            return this.future;
        }

        void setFuture(Future<?> future) {
            synchronized (this.lock) {
                if (!this.cancelled) {
                    this.future = future;
                }
            }
        }
    }

    private final class HedgingRunnable implements Runnable {
        final FutureCanceller scheduledHedgingRef;

        HedgingRunnable(FutureCanceller futureCanceller) {
            this.scheduledHedgingRef = futureCanceller;
        }

        @Override // java.lang.Runnable
        public void run() {
            RetriableStream.this.callExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.HedgingRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    FutureCanceller futureCanceller;
                    boolean z;
                    Substream substreamCreateSubstream = RetriableStream.this.createSubstream(RetriableStream.this.state.hedgingAttemptCount);
                    synchronized (RetriableStream.this.lock) {
                        futureCanceller = null;
                        if (HedgingRunnable.this.scheduledHedgingRef.isCancelled()) {
                            z = true;
                        } else {
                            RetriableStream.this.state = RetriableStream.this.state.addActiveHedge(substreamCreateSubstream);
                            if (RetriableStream.this.hasPotentialHedging(RetriableStream.this.state) && (RetriableStream.this.throttle == null || RetriableStream.this.throttle.isAboveThreshold())) {
                                RetriableStream retriableStream = RetriableStream.this;
                                futureCanceller = new FutureCanceller(RetriableStream.this.lock);
                                retriableStream.scheduledHedging = futureCanceller;
                            } else {
                                RetriableStream.this.state = RetriableStream.this.state.freezeHedging();
                                RetriableStream.this.scheduledHedging = null;
                            }
                            z = false;
                        }
                    }
                    if (!z) {
                        if (futureCanceller != null) {
                            futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), RetriableStream.this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                        }
                        RetriableStream.this.drain(substreamCreateSubstream);
                        return;
                    }
                    substreamCreateSubstream.stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                }
            });
        }
    }

    private final class Sublistener implements ClientStreamListener {
        final Substream substream;

        Sublistener(Substream substream) {
            this.substream = substream;
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void headersRead(Metadata metadata) {
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.headersRead(metadata);
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
            }
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void closed(Status status, Metadata metadata) {
            closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            FutureCanceller futureCanceller;
            synchronized (RetriableStream.this.lock) {
                RetriableStream retriableStream = RetriableStream.this;
                retriableStream.state = retriableStream.state.substreamClosed(this.substream);
                RetriableStream.this.closedSubstreamsInsight.append(status.getCode());
            }
            if (this.substream.bufferLimitExceeded) {
                RetriableStream.this.commitAndRun(this.substream);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.masterListener.closed(status, metadata);
                    return;
                }
                return;
            }
            if (RetriableStream.this.state.winningSubstream == null) {
                boolean z = true;
                if (rpcProgress == ClientStreamListener.RpcProgress.REFUSED && RetriableStream.this.noMoreTransparentRetry.compareAndSet(false, true)) {
                    final Substream substreamCreateSubstream = RetriableStream.this.createSubstream(this.substream.previousAttemptCount);
                    if (RetriableStream.this.isHedging) {
                        synchronized (RetriableStream.this.lock) {
                            RetriableStream retriableStream2 = RetriableStream.this;
                            retriableStream2.state = retriableStream2.state.replaceActiveHedge(this.substream, substreamCreateSubstream);
                            RetriableStream retriableStream3 = RetriableStream.this;
                            if (retriableStream3.hasPotentialHedging(retriableStream3.state) || RetriableStream.this.state.activeHedges.size() != 1) {
                                z = false;
                            }
                        }
                        if (z) {
                            RetriableStream.this.commitAndRun(substreamCreateSubstream);
                        }
                    } else {
                        if (RetriableStream.this.retryPolicy == null) {
                            RetriableStream retriableStream4 = RetriableStream.this;
                            retriableStream4.retryPolicy = retriableStream4.retryPolicyProvider.get();
                        }
                        if (RetriableStream.this.retryPolicy.maxAttempts == 1) {
                            RetriableStream.this.commitAndRun(substreamCreateSubstream);
                        }
                    }
                    RetriableStream.this.callExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RetriableStream.this.drain(substreamCreateSubstream);
                        }
                    });
                    return;
                }
                if (rpcProgress == ClientStreamListener.RpcProgress.DROPPED) {
                    if (RetriableStream.this.isHedging) {
                        RetriableStream.this.freezeHedging();
                    }
                } else {
                    RetriableStream.this.noMoreTransparentRetry.set(true);
                    if (RetriableStream.this.retryPolicy == null) {
                        RetriableStream retriableStream5 = RetriableStream.this;
                        retriableStream5.retryPolicy = retriableStream5.retryPolicyProvider.get();
                        RetriableStream retriableStream6 = RetriableStream.this;
                        retriableStream6.nextBackoffIntervalNanos = retriableStream6.retryPolicy.initialBackoffNanos;
                    }
                    if (RetriableStream.this.isHedging) {
                        HedgingPlan hedgingPlanMakeHedgingDecision = makeHedgingDecision(status, metadata);
                        if (hedgingPlanMakeHedgingDecision.isHedgeable) {
                            RetriableStream.this.pushbackHedging(hedgingPlanMakeHedgingDecision.hedgingPushbackMillis);
                        }
                        synchronized (RetriableStream.this.lock) {
                            RetriableStream retriableStream7 = RetriableStream.this;
                            retriableStream7.state = retriableStream7.state.removeActiveHedge(this.substream);
                            if (hedgingPlanMakeHedgingDecision.isHedgeable) {
                                RetriableStream retriableStream8 = RetriableStream.this;
                                if (retriableStream8.hasPotentialHedging(retriableStream8.state) || !RetriableStream.this.state.activeHedges.isEmpty()) {
                                    return;
                                }
                            }
                        }
                    } else {
                        RetryPlan retryPlanMakeRetryDecision = makeRetryDecision(status, metadata);
                        if (retryPlanMakeRetryDecision.shouldRetry) {
                            synchronized (RetriableStream.this.lock) {
                                RetriableStream retriableStream9 = RetriableStream.this;
                                futureCanceller = new FutureCanceller(retriableStream9.lock);
                                retriableStream9.scheduledRetry = futureCanceller;
                            }
                            futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    RetriableStream.this.callExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.2.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            RetriableStream.this.drain(RetriableStream.this.createSubstream(Sublistener.this.substream.previousAttemptCount + 1));
                                        }
                                    });
                                }
                            }, retryPlanMakeRetryDecision.backoffNanos, TimeUnit.NANOSECONDS));
                            return;
                        }
                    }
                }
            }
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.closed(status, metadata);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x009b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private io.grpc.internal.RetriableStream.RetryPlan makeRetryDecision(io.grpc.Status r7, io.grpc.Metadata r8) {
            /*
                r6 = this;
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r0 = io.grpc.internal.RetriableStream.access$1700(r0)
                java.util.Set<io.grpc.Status$Code> r0 = r0.retryableStatusCodes
                io.grpc.Status$Code r7 = r7.getCode()
                boolean r7 = r0.contains(r7)
                java.lang.Integer r8 = r6.getPushbackMills(r8)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Throttle r0 = io.grpc.internal.RetriableStream.access$500(r0)
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L34
                if (r7 != 0) goto L28
                if (r8 == 0) goto L34
                int r0 = r8.intValue()
                if (r0 >= 0) goto L34
            L28:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Throttle r0 = io.grpc.internal.RetriableStream.access$500(r0)
                boolean r0 = r0.onQualifiedFailureThenCheckIsAboveThreshold()
                r0 = r0 ^ r1
                goto L35
            L34:
                r0 = 0
            L35:
                io.grpc.internal.RetriableStream r3 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r3 = io.grpc.internal.RetriableStream.access$1700(r3)
                int r3 = r3.maxAttempts
                io.grpc.internal.RetriableStream$Substream r4 = r6.substream
                int r4 = r4.previousAttemptCount
                int r4 = r4 + r1
                if (r3 <= r4) goto L9b
                if (r0 != 0) goto L9b
                if (r8 != 0) goto L7e
                if (r7 == 0) goto L9b
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this
                long r7 = io.grpc.internal.RetriableStream.access$2000(r7)
                double r7 = (double) r7
                java.util.Random r0 = io.grpc.internal.RetriableStream.access$2300()
                double r2 = r0.nextDouble()
                double r7 = r7 * r2
                long r7 = (long) r7
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                long r2 = io.grpc.internal.RetriableStream.access$2000(r0)
                double r2 = (double) r2
                io.grpc.internal.RetriableStream r4 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r4 = io.grpc.internal.RetriableStream.access$1700(r4)
                double r4 = r4.backoffMultiplier
                double r2 = r2 * r4
                long r2 = (long) r2
                io.grpc.internal.RetriableStream r4 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r4 = io.grpc.internal.RetriableStream.access$1700(r4)
                long r4 = r4.maxBackoffNanos
                long r2 = java.lang.Math.min(r2, r4)
                io.grpc.internal.RetriableStream.access$2002(r0, r2)
                goto L9e
            L7e:
                int r7 = r8.intValue()
                if (r7 < 0) goto L9b
                java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS
                int r8 = r8.intValue()
                long r2 = (long) r8
                long r7 = r7.toNanos(r2)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r2 = io.grpc.internal.RetriableStream.access$1700(r0)
                long r2 = r2.initialBackoffNanos
                io.grpc.internal.RetriableStream.access$2002(r0, r2)
                goto L9e
            L9b:
                r7 = 0
                r1 = 0
            L9e:
                io.grpc.internal.RetriableStream$RetryPlan r0 = new io.grpc.internal.RetriableStream$RetryPlan
                r0.<init>(r1, r7)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.Sublistener.makeRetryDecision(io.grpc.Status, io.grpc.Metadata):io.grpc.internal.RetriableStream$RetryPlan");
        }

        private HedgingPlan makeHedgingDecision(Status status, Metadata metadata) {
            Integer pushbackMills = getPushbackMills(metadata);
            boolean z = !RetriableStream.this.hedgingPolicy.nonFatalStatusCodes.contains(status.getCode());
            return new HedgingPlan((z || ((RetriableStream.this.throttle == null || (z && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold() ^ true)) ? false : true, pushbackMills);
        }

        @Nullable
        private Integer getPushbackMills(Metadata metadata) {
            String str = (String) metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (str == null) {
                return null;
            }
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            State state = RetriableStream.this.state;
            Preconditions.checkState(state.winningSubstream != null, "Headers should be received prior to messages.");
            if (state.winningSubstream != this.substream) {
                return;
            }
            RetriableStream.this.masterListener.messagesAvailable(messageProducer);
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            RetriableStream.this.masterListener.onReady();
        }
    }

    class BufferSizeTracer extends ClientStreamTracer {
        private final Substream substream;
        long bufferNeeded;

        BufferSizeTracer(Substream substream) {
            this.substream = substream;
        }

        @Override // io.grpc.StreamTracer
        public void outboundWireSize(long j) {
            if (RetriableStream.this.state.winningSubstream != null) {
                return;
            }
            synchronized (RetriableStream.this.lock) {
                if (RetriableStream.this.state.winningSubstream == null && !this.substream.closed) {
                    long j2 = this.bufferNeeded + j;
                    this.bufferNeeded = j2;
                    if (j2 <= RetriableStream.this.perRpcBufferUsed) {
                        return;
                    }
                    if (this.bufferNeeded <= RetriableStream.this.perRpcBufferLimit) {
                        long jAddAndGet = RetriableStream.this.channelBufferUsed.addAndGet(this.bufferNeeded - RetriableStream.this.perRpcBufferUsed);
                        RetriableStream.this.perRpcBufferUsed = this.bufferNeeded;
                        if (jAddAndGet > RetriableStream.this.channelBufferLimit) {
                            this.substream.bufferLimitExceeded = true;
                        }
                    } else {
                        this.substream.bufferLimitExceeded = true;
                    }
                    Runnable runnableCommit = this.substream.bufferLimitExceeded ? RetriableStream.this.commit(this.substream) : null;
                    if (runnableCommit != null) {
                        runnableCommit.run();
                    }
                }
            }
        }
    }
}
