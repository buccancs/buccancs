package io.grpc.netty.shaded.io.netty.util.concurrent;

/* loaded from: classes3.dex */
public interface EventExecutorChooserFactory {

    EventExecutorChooser newChooser(EventExecutor[] eventExecutorArr);

    public interface EventExecutorChooser {
        EventExecutor next();
    }
}
