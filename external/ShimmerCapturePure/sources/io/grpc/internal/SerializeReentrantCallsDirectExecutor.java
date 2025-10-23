package io.grpc.internal;

import com.google.common.base.Preconditions;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
class SerializeReentrantCallsDirectExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializeReentrantCallsDirectExecutor.class.getName());
    private boolean executing;
    private ArrayDeque<Runnable> taskQueue;

    SerializeReentrantCallsDirectExecutor() {
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        Preconditions.checkNotNull(runnable, "'task' must not be null.");
        if (!this.executing) {
            this.executing = true;
            try {
                runnable.run();
            } catch (Throwable th) {
                try {
                    log.log(Level.SEVERE, "Exception while executing runnable " + runnable, th);
                    if (this.taskQueue != null) {
                    }
                } finally {
                    if (this.taskQueue != null) {
                        completeQueuedTasks();
                    }
                    this.executing = false;
                }
            }
            return;
        }
        enqueue(runnable);
    }

    private void completeQueuedTasks() {
        while (true) {
            Runnable runnablePoll = this.taskQueue.poll();
            if (runnablePoll == null) {
                return;
            }
            try {
                runnablePoll.run();
            } catch (Throwable th) {
                log.log(Level.SEVERE, "Exception while executing runnable " + runnablePoll, th);
            }
        }
    }

    private void enqueue(Runnable runnable) {
        if (this.taskQueue == null) {
            this.taskQueue = new ArrayDeque<>(4);
        }
        this.taskQueue.add(runnable);
    }
}
