package io.grpc.netty.shaded.io.netty.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes3.dex */
public final class DefaultEventExecutor extends SingleThreadEventExecutor {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultEventExecutor() {
        this((EventExecutorGroup) null);
    }

    public DefaultEventExecutor(ThreadFactory threadFactory) {
        this((EventExecutorGroup) null, threadFactory);
    }

    public DefaultEventExecutor(Executor executor) {
        this((EventExecutorGroup) null, executor);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup) {
        this(eventExecutorGroup, new DefaultThreadFactory((Class<?>) DefaultEventExecutor.class));
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory) {
        super(eventExecutorGroup, threadFactory, true);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor) {
        super(eventExecutorGroup, executor, true);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup, threadFactory, true, i, rejectedExecutionHandler);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup, executor, true, i, rejectedExecutionHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor
    protected void run() {
        do {
            Runnable runnableTakeTask = takeTask();
            if (runnableTakeTask != null) {
                runnableTakeTask.run();
                updateLastExecutionTime();
            }
        } while (!confirmShutdown());
    }
}
