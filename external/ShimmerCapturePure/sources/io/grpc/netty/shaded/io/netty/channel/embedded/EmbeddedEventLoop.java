package io.grpc.netty.shaded.io.netty.channel.embedded;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.util.concurrent.AbstractScheduledEventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
final class EmbeddedEventLoop extends AbstractScheduledEventExecutor implements EventLoop {
    private final Queue<Runnable> tasks = new ArrayDeque(2);

    EmbeddedEventLoop() {
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return false;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor
    public boolean inEventLoop() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor
    public boolean inEventLoop(Thread thread) {
        return true;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor, io.grpc.netty.shaded.io.netty.channel.EventLoop
    public EventLoopGroup parent() {
        return (EventLoopGroup) super.parent();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public EventLoop next() {
        return (EventLoop) super.next();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.tasks.add(ObjectUtil.checkNotNull(runnable, "command"));
    }

    void runTasks() {
        while (true) {
            Runnable runnablePoll = this.tasks.poll();
            if (runnablePoll == null) {
                return;
            } else {
                runnablePoll.run();
            }
        }
    }

    long runScheduledTasks() {
        long jNanoTime = AbstractScheduledEventExecutor.nanoTime();
        while (true) {
            Runnable runnablePollScheduledTask = pollScheduledTask(jNanoTime);
            if (runnablePollScheduledTask == null) {
                return nextScheduledTaskNano();
            }
            runnablePollScheduledTask.run();
        }
    }

    long nextScheduledTask() {
        return nextScheduledTaskNano();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractScheduledEventExecutor
    protected void cancelScheduledTasks() {
        super.cancelScheduledTasks();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        throw new UnsupportedOperationException();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ExecutorService, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    @Deprecated
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public ChannelFuture register(Channel channel) {
        return register(new DefaultChannelPromise(channel, this));
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public ChannelFuture register(ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channelPromise, "promise");
        channelPromise.channel().unsafe().register(this, channelPromise);
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    @Deprecated
    public ChannelFuture register(Channel channel, ChannelPromise channelPromise) {
        channel.unsafe().register(this, channelPromise);
        return channelPromise;
    }
}
