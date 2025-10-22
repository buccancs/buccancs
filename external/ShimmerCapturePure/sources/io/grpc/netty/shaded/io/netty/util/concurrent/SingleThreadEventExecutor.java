package io.grpc.netty.shaded.io.netty.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadExecutorMap;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: classes3.dex */
public abstract class SingleThreadEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.eventexecutor.maxPendingTasks", Integer.MAX_VALUE));
    private static final int ST_NOT_STARTED = 1;
    private static final int ST_SHUTDOWN = 4;
    private static final int ST_SHUTTING_DOWN = 3;
    private static final int ST_STARTED = 2;
    private static final int ST_TERMINATED = 5;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SingleThreadEventExecutor.class);
    private static final Runnable NOOP_TASK = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };
    private static final AtomicIntegerFieldUpdater<SingleThreadEventExecutor> STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(SingleThreadEventExecutor.class, "state");
    private static final AtomicReferenceFieldUpdater<SingleThreadEventExecutor, ThreadProperties> PROPERTIES_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SingleThreadEventExecutor.class, ThreadProperties.class, "threadProperties");
    private static final long SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1);
    private final boolean addTaskWakesUp;
    private final Executor executor;
    private final int maxPendingTasks;
    private final RejectedExecutionHandler rejectedExecutionHandler;
    private final Set<Runnable> shutdownHooks;
    private final Queue<Runnable> taskQueue;
    private final Promise<?> terminationFuture;
    private final CountDownLatch threadLock;
    private volatile long gracefulShutdownQuietPeriod;
    private long gracefulShutdownStartTime;
    private volatile long gracefulShutdownTimeout;
    private volatile boolean interrupted;
    private long lastExecutionTime;
    private volatile int state;
    private volatile Thread thread;
    private volatile ThreadProperties threadProperties;

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, boolean z) {
        this(eventExecutorGroup, new ThreadPerTaskExecutor(threadFactory), z);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, boolean z, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        this(eventExecutorGroup, new ThreadPerTaskExecutor(threadFactory), z, i, rejectedExecutionHandler);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, boolean z) {
        this(eventExecutorGroup, executor, z, DEFAULT_MAX_PENDING_EXECUTOR_TASKS, RejectedExecutionHandlers.reject());
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, boolean z, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        int iMax = Math.max(16, i);
        this.maxPendingTasks = iMax;
        this.executor = ThreadExecutorMap.apply(executor, this);
        this.taskQueue = newTaskQueue(iMax);
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedExecutionHandler, "rejectedHandler");
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, boolean z, Queue<Runnable> queue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        this.maxPendingTasks = DEFAULT_MAX_PENDING_EXECUTOR_TASKS;
        this.executor = ThreadExecutorMap.apply(executor, this);
        this.taskQueue = (Queue) ObjectUtil.checkNotNull(queue, "taskQueue");
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedExecutionHandler, "rejectedHandler");
    }

    protected static Runnable pollTaskFrom(Queue<Runnable> queue) {
        Runnable runnablePoll;
        do {
            runnablePoll = queue.poll();
        } while (runnablePoll == WAKEUP_TASK);
        return runnablePoll;
    }

    protected static void reject() {
        throw new RejectedExecutionException("event executor terminated");
    }

    protected void afterRunningAllTasks() {
    }

    protected void cleanup() {
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor
    public boolean inEventLoop(Thread thread) {
        return thread == this.thread;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.state >= 4;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        return this.state >= 3;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.state == 5;
    }

    protected abstract void run();

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    protected boolean wakesUpForTask(Runnable runnable) {
        return true;
    }

    @Deprecated
    protected Queue<Runnable> newTaskQueue() {
        return newTaskQueue(this.maxPendingTasks);
    }

    protected Queue<Runnable> newTaskQueue(int i) {
        return new LinkedBlockingQueue(i);
    }

    protected void interruptThread() {
        Thread thread = this.thread;
        if (thread == null) {
            this.interrupted = true;
        } else {
            thread.interrupt();
        }
    }

    protected Runnable pollTask() {
        return pollTaskFrom(this.taskQueue);
    }

    protected Runnable takeTask() {
        Runnable runnable;
        Queue<Runnable> queue = this.taskQueue;
        if (!(queue instanceof BlockingQueue)) {
            throw new UnsupportedOperationException();
        }
        BlockingQueue blockingQueue = (BlockingQueue) queue;
        do {
            ScheduledFutureTask<?> scheduledFutureTaskPeekScheduledTask = peekScheduledTask();
            runnable = null;
            if (scheduledFutureTaskPeekScheduledTask == null) {
                try {
                    Runnable runnable2 = (Runnable) blockingQueue.take();
                    try {
                        if (runnable2 == WAKEUP_TASK) {
                            return null;
                        }
                    } catch (InterruptedException unused) {
                    }
                    return runnable2;
                } catch (InterruptedException unused2) {
                    return null;
                }
            }
            long jDelayNanos = scheduledFutureTaskPeekScheduledTask.delayNanos();
            if (jDelayNanos > 0) {
                try {
                    runnable = (Runnable) blockingQueue.poll(jDelayNanos, TimeUnit.NANOSECONDS);
                } catch (InterruptedException unused3) {
                    return null;
                }
            }
            if (runnable == null) {
                fetchFromScheduledTaskQueue();
                runnable = (Runnable) blockingQueue.poll();
            }
        } while (runnable == null);
        return runnable;
    }

    private boolean fetchFromScheduledTaskQueue() {
        Runnable runnablePollScheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty()) {
            return true;
        }
        long jNanoTime = AbstractScheduledEventExecutor.nanoTime();
        do {
            runnablePollScheduledTask = pollScheduledTask(jNanoTime);
            if (runnablePollScheduledTask == null) {
                return true;
            }
        } while (this.taskQueue.offer(runnablePollScheduledTask));
        this.scheduledTaskQueue.add((ScheduledFutureTask) runnablePollScheduledTask);
        return false;
    }

    private boolean executeExpiredScheduledTasks() {
        long jNanoTime;
        Runnable runnablePollScheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty() || (runnablePollScheduledTask = pollScheduledTask((jNanoTime = AbstractScheduledEventExecutor.nanoTime()))) == null) {
            return false;
        }
        do {
            safeExecute(runnablePollScheduledTask);
            runnablePollScheduledTask = pollScheduledTask(jNanoTime);
        } while (runnablePollScheduledTask != null);
        return true;
    }

    protected Runnable peekTask() {
        return this.taskQueue.peek();
    }

    protected boolean hasTasks() {
        return !this.taskQueue.isEmpty();
    }

    public int pendingTasks() {
        return this.taskQueue.size();
    }

    protected void addTask(Runnable runnable) {
        ObjectUtil.checkNotNull(runnable, "task");
        if (offerTask(runnable)) {
            return;
        }
        reject(runnable);
    }

    final boolean offerTask(Runnable runnable) {
        if (isShutdown()) {
            reject();
        }
        return this.taskQueue.offer(runnable);
    }

    protected boolean removeTask(Runnable runnable) {
        return this.taskQueue.remove(ObjectUtil.checkNotNull(runnable, "task"));
    }

    protected boolean runAllTasks() {
        boolean zFetchFromScheduledTaskQueue;
        boolean z = false;
        do {
            zFetchFromScheduledTaskQueue = fetchFromScheduledTaskQueue();
            if (runAllTasksFrom(this.taskQueue)) {
                z = true;
            }
        } while (!zFetchFromScheduledTaskQueue);
        if (z) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        afterRunningAllTasks();
        return z;
    }

    protected final boolean runScheduledAndExecutorTasks(int i) {
        int i2 = 0;
        while ((runExistingTasksFrom(this.taskQueue) | executeExpiredScheduledTasks()) && (i2 = i2 + 1) < i) {
        }
        if (i2 > 0) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        afterRunningAllTasks();
        return i2 > 0;
    }

    protected final boolean runAllTasksFrom(Queue<Runnable> queue) {
        Runnable runnablePollTaskFrom = pollTaskFrom(queue);
        if (runnablePollTaskFrom == null) {
            return false;
        }
        do {
            safeExecute(runnablePollTaskFrom);
            runnablePollTaskFrom = pollTaskFrom(queue);
        } while (runnablePollTaskFrom != null);
        return true;
    }

    private boolean runExistingTasksFrom(Queue<Runnable> queue) {
        Runnable runnablePoll;
        Runnable runnablePollTaskFrom = pollTaskFrom(queue);
        if (runnablePollTaskFrom == null) {
            return false;
        }
        int iMin = Math.min(this.maxPendingTasks, queue.size());
        safeExecute(runnablePollTaskFrom);
        while (true) {
            int i = iMin - 1;
            if (iMin <= 0 || (runnablePoll = queue.poll()) == null) {
                return true;
            }
            safeExecute(runnablePoll);
            iMin = i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean runAllTasks(long r8) {
        /*
            r7 = this;
            r7.fetchFromScheduledTaskQueue()
            java.lang.Runnable r0 = r7.pollTask()
            if (r0 != 0) goto Le
            r7.afterRunningAllTasks()
            r8 = 0
            return r8
        Le:
            r1 = 0
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 <= 0) goto L1a
            long r3 = io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
            long r3 = r3 + r8
            goto L1b
        L1a:
            r3 = r1
        L1b:
            r8 = r1
        L1c:
            safeExecute(r0)
            r5 = 1
            long r8 = r8 + r5
            r5 = 63
            long r5 = r5 & r8
            int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r0 != 0) goto L32
            long r5 = io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 < 0) goto L32
            goto L3c
        L32:
            java.lang.Runnable r0 = r7.pollTask()
            if (r0 != 0) goto L1c
            long r5 = io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFutureTask.nanoTime()
        L3c:
            r7.afterRunningAllTasks()
            r7.lastExecutionTime = r5
            r8 = 1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(long):boolean");
    }

    protected long delayNanos(long j) {
        ScheduledFutureTask<?> scheduledFutureTaskPeekScheduledTask = peekScheduledTask();
        return scheduledFutureTaskPeekScheduledTask == null ? SCHEDULE_PURGE_INTERVAL : scheduledFutureTaskPeekScheduledTask.delayNanos(j);
    }

    protected long deadlineNanos() {
        ScheduledFutureTask<?> scheduledFutureTaskPeekScheduledTask = peekScheduledTask();
        if (scheduledFutureTaskPeekScheduledTask == null) {
            return nanoTime() + SCHEDULE_PURGE_INTERVAL;
        }
        return scheduledFutureTaskPeekScheduledTask.deadlineNanos();
    }

    protected void updateLastExecutionTime() {
        this.lastExecutionTime = ScheduledFutureTask.nanoTime();
    }

    protected void wakeup(boolean z) {
        if (z) {
            return;
        }
        this.taskQueue.offer(WAKEUP_TASK);
    }

    public void addShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.add(runnable);
        } else {
            execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.2
                @Override // java.lang.Runnable
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.add(runnable);
                }
            });
        }
    }

    public void removeShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.remove(runnable);
        } else {
            execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.3
                @Override // java.lang.Runnable
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.remove(runnable);
                }
            });
        }
    }

    private boolean runShutdownHooks() {
        boolean z = false;
        while (!this.shutdownHooks.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.shutdownHooks);
            this.shutdownHooks.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                try {
                    ((Runnable) it2.next()).run();
                } catch (Throwable th) {
                    logger.warn("Shutdown hook raised an exception.", th);
                }
                z = true;
            }
        }
        if (z) {
            this.lastExecutionTime = ScheduledFutureTask.nanoTime();
        }
        return z;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        ObjectUtil.checkPositiveOrZero(j, "quietPeriod");
        if (j2 < j) {
            throw new IllegalArgumentException("timeout: " + j2 + " (expected >= quietPeriod (" + j + "))");
        }
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (isShuttingDown()) {
            return terminationFuture();
        }
        boolean zInEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            int i = this.state;
            int i2 = 3;
            boolean z = true;
            if (!zInEventLoop && i != 1 && i != 2) {
                z = false;
                i2 = i;
            }
            if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                this.gracefulShutdownQuietPeriod = timeUnit.toNanos(j);
                this.gracefulShutdownTimeout = timeUnit.toNanos(j2);
                if (ensureThreadStarted(i)) {
                    return this.terminationFuture;
                }
                if (z) {
                    this.taskQueue.offer(WAKEUP_TASK);
                    if (!this.addTaskWakesUp) {
                        wakeup(zInEventLoop);
                    }
                }
                return terminationFuture();
            }
        }
        return terminationFuture();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ExecutorService, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    @Deprecated
    public void shutdown() {
        if (isShutdown()) {
            return;
        }
        boolean zInEventLoop = inEventLoop();
        while (!isShuttingDown()) {
            int i = this.state;
            int i2 = 4;
            boolean z = true;
            if (!zInEventLoop && i != 1 && i != 2 && i != 3) {
                z = false;
                i2 = i;
            }
            if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                if (!ensureThreadStarted(i) && z) {
                    this.taskQueue.offer(WAKEUP_TASK);
                    if (this.addTaskWakesUp) {
                        return;
                    }
                    wakeup(zInEventLoop);
                    return;
                }
                return;
            }
        }
    }

    protected boolean confirmShutdown() throws InterruptedException {
        if (!isShuttingDown()) {
            return false;
        }
        if (!inEventLoop()) {
            throw new IllegalStateException("must be invoked from an event loop");
        }
        cancelScheduledTasks();
        if (this.gracefulShutdownStartTime == 0) {
            this.gracefulShutdownStartTime = ScheduledFutureTask.nanoTime();
        }
        if (runAllTasks() || runShutdownHooks()) {
            if (isShutdown() || this.gracefulShutdownQuietPeriod == 0) {
                return true;
            }
            this.taskQueue.offer(WAKEUP_TASK);
            return false;
        }
        long jNanoTime = ScheduledFutureTask.nanoTime();
        if (isShutdown() || jNanoTime - this.gracefulShutdownStartTime > this.gracefulShutdownTimeout || jNanoTime - this.lastExecutionTime > this.gracefulShutdownQuietPeriod) {
            return true;
        }
        this.taskQueue.offer(WAKEUP_TASK);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException unused) {
        }
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (inEventLoop()) {
            throw new IllegalStateException("cannot await termination of the current thread");
        }
        this.threadLock.await(j, timeUnit);
        return isTerminated();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        ObjectUtil.checkNotNull(runnable, "task");
        execute(runnable, !(runnable instanceof AbstractEventExecutor.LazyRunnable) && wakesUpForTask(runnable));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor
    public void lazyExecute(Runnable runnable) {
        execute((Runnable) ObjectUtil.checkNotNull(runnable, "task"), false);
    }

    private void execute(Runnable runnable, boolean z) {
        boolean zInEventLoop = inEventLoop();
        addTask(runnable);
        if (!zInEventLoop) {
            startThread();
            if (isShutdown()) {
                try {
                    if (removeTask(runnable)) {
                        reject();
                    }
                } catch (UnsupportedOperationException unused) {
                }
            }
        }
        if (this.addTaskWakesUp || !z) {
            return;
        }
        wakeup(zInEventLoop);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        throwIfInEventLoop("invokeAny");
        return (T) super.invokeAny(collection);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        throwIfInEventLoop("invokeAny");
        return (T) super.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(collection);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(collection, j, timeUnit);
    }

    private void throwIfInEventLoop(String str) {
        if (inEventLoop()) {
            throw new RejectedExecutionException("Calling " + str + " from within the EventLoop is not allowed");
        }
    }

    public final ThreadProperties threadProperties() {
        ThreadProperties threadProperties = this.threadProperties;
        if (threadProperties != null) {
            return threadProperties;
        }
        Thread thread = this.thread;
        if (thread == null) {
            submit(NOOP_TASK).syncUninterruptibly();
            thread = this.thread;
        }
        DefaultThreadProperties defaultThreadProperties = new DefaultThreadProperties(thread);
        return !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(PROPERTIES_UPDATER, this, null, defaultThreadProperties) ? this.threadProperties : defaultThreadProperties;
    }

    protected final void reject(Runnable runnable) {
        this.rejectedExecutionHandler.rejected(runnable, this);
    }

    private void startThread() {
        if (this.state == 1 && STATE_UPDATER.compareAndSet(this, 1, 2)) {
            try {
                doStartThread();
            } catch (Throwable th) {
                STATE_UPDATER.compareAndSet(this, 2, 1);
                throw th;
            }
        }
    }

    private boolean ensureThreadStarted(int i) throws Throwable {
        if (i != 1) {
            return false;
        }
        try {
            doStartThread();
            return false;
        } catch (Throwable th) {
            STATE_UPDATER.set(this, 5);
            this.terminationFuture.tryFailure(th);
            if (!(th instanceof Exception)) {
                PlatformDependent.throwException(th);
            }
            return true;
        }
    }

    private void doStartThread() {
        this.executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.4
            @Override // java.lang.Runnable
            public void run() {
                int i;
                int i2;
                int i3;
                int i4;
                int iDrainTasks;
                InternalLogger internalLogger;
                StringBuilder sb;
                int i5;
                int i6;
                SingleThreadEventExecutor.this.thread = Thread.currentThread();
                if (SingleThreadEventExecutor.this.interrupted) {
                    SingleThreadEventExecutor.this.thread.interrupt();
                }
                SingleThreadEventExecutor.this.updateLastExecutionTime();
                try {
                    SingleThreadEventExecutor.this.run();
                    do {
                        i5 = SingleThreadEventExecutor.this.state;
                        if (i5 >= 3) {
                            break;
                        }
                    } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i5, 3));
                    if (SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0 && SingleThreadEventExecutor.logger.isErrorEnabled()) {
                        SingleThreadEventExecutor.logger.error("Buggy EventExecutor implementation; SingleThreadEventExecutor.confirmShutdown() must be called before run() implementation terminates.");
                    }
                    do {
                        try {
                        } catch (Throwable th) {
                            try {
                                SingleThreadEventExecutor.this.cleanup();
                                throw th;
                            } finally {
                                FastThreadLocal.removeAll();
                                SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                SingleThreadEventExecutor.this.threadLock.countDown();
                                int iDrainTasks2 = SingleThreadEventExecutor.this.drainTasks();
                                if (iDrainTasks2 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                    SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks2 + ')');
                                }
                                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            }
                        }
                    } while (!SingleThreadEventExecutor.this.confirmShutdown());
                    do {
                        i6 = SingleThreadEventExecutor.this.state;
                        if (i6 >= 4) {
                            break;
                        }
                    } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i6, 4));
                    SingleThreadEventExecutor.this.confirmShutdown();
                    try {
                        SingleThreadEventExecutor.this.cleanup();
                        FastThreadLocal.removeAll();
                        SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                        SingleThreadEventExecutor.this.threadLock.countDown();
                        iDrainTasks = SingleThreadEventExecutor.this.drainTasks();
                    } finally {
                    }
                } catch (Throwable th2) {
                    try {
                        SingleThreadEventExecutor.logger.warn("Unexpected exception from an event executor: ", th2);
                        do {
                            i3 = SingleThreadEventExecutor.this.state;
                            if (i3 < 3) {
                            }
                            break;
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i3, 3));
                        break;
                        do {
                            try {
                            } catch (Throwable th3) {
                                try {
                                    SingleThreadEventExecutor.this.cleanup();
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int iDrainTasks3 = SingleThreadEventExecutor.this.drainTasks();
                                    if (iDrainTasks3 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks3 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                    throw th3;
                                } finally {
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int iDrainTasks4 = SingleThreadEventExecutor.this.drainTasks();
                                    if (iDrainTasks4 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks4 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                }
                            }
                        } while (!SingleThreadEventExecutor.this.confirmShutdown());
                        do {
                            i4 = SingleThreadEventExecutor.this.state;
                            if (i4 >= 4) {
                                break;
                            }
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i4, 4));
                        SingleThreadEventExecutor.this.confirmShutdown();
                        try {
                            SingleThreadEventExecutor.this.cleanup();
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            iDrainTasks = SingleThreadEventExecutor.this.drainTasks();
                            if (iDrainTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                internalLogger = SingleThreadEventExecutor.logger;
                                sb = new StringBuilder("An event executor terminated with non-empty task queue (");
                            }
                        } finally {
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            int iDrainTasks5 = SingleThreadEventExecutor.this.drainTasks();
                            if (iDrainTasks5 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks5 + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        }
                    } catch (Throwable th4) {
                        do {
                            i = SingleThreadEventExecutor.this.state;
                            if (i < 3) {
                            }
                            break;
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i, 3));
                        break;
                        do {
                            try {
                            } catch (Throwable th5) {
                                try {
                                    SingleThreadEventExecutor.this.cleanup();
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int iDrainTasks6 = SingleThreadEventExecutor.this.drainTasks();
                                    if (iDrainTasks6 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks6 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                    throw th5;
                                } finally {
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int iDrainTasks7 = SingleThreadEventExecutor.this.drainTasks();
                                    if (iDrainTasks7 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks7 + ')');
                                    }
                                    SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                                }
                            }
                        } while (!SingleThreadEventExecutor.this.confirmShutdown());
                        do {
                            i2 = SingleThreadEventExecutor.this.state;
                            if (i2 >= 4) {
                                break;
                            }
                        } while (!SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, i2, 4));
                        SingleThreadEventExecutor.this.confirmShutdown();
                        try {
                            SingleThreadEventExecutor.this.cleanup();
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            int iDrainTasks8 = SingleThreadEventExecutor.this.drainTasks();
                            if (iDrainTasks8 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks8 + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                            throw th4;
                        } finally {
                            FastThreadLocal.removeAll();
                            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                            SingleThreadEventExecutor.this.threadLock.countDown();
                            int iDrainTasks9 = SingleThreadEventExecutor.this.drainTasks();
                            if (iDrainTasks9 > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + iDrainTasks9 + ')');
                            }
                            SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        }
                    }
                }
                if (iDrainTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                    internalLogger = SingleThreadEventExecutor.logger;
                    sb = new StringBuilder("An event executor terminated with non-empty task queue (");
                    sb.append(iDrainTasks);
                    sb.append(')');
                    internalLogger.warn(sb.toString());
                }
                SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
            }
        });
    }

    final int drainTasks() {
        int i = 0;
        while (true) {
            Runnable runnablePoll = this.taskQueue.poll();
            if (runnablePoll == null) {
                return i;
            }
            if (WAKEUP_TASK != runnablePoll) {
                i++;
            }
        }
    }

    @Deprecated
    protected interface NonWakeupRunnable extends AbstractEventExecutor.LazyRunnable {
    }

    private static final class DefaultThreadProperties implements ThreadProperties {
        private final Thread t;

        DefaultThreadProperties(Thread thread) {
            this.t = thread;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public Thread.State state() {
            return this.t.getState();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public int priority() {
            return this.t.getPriority();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public boolean isInterrupted() {
            return this.t.isInterrupted();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public boolean isDaemon() {
            return this.t.isDaemon();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public String name() {
            return this.t.getName();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public long id() {
            return this.t.getId();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public StackTraceElement[] stackTrace() {
            return this.t.getStackTrace();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.ThreadProperties
        public boolean isAlive() {
            return this.t.isAlive();
        }
    }
}
