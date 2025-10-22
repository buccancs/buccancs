package io.grpc.testing;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import io.grpc.ManagedChannel;
import io.grpc.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

/* loaded from: classes3.dex */
public final class GrpcCleanupRule implements TestRule {
    private final List<Resource> resources = new ArrayList();
    private Throwable firstException;
    private long timeoutNanos = TimeUnit.SECONDS.toNanos(10);
    private Stopwatch stopwatch = Stopwatch.createUnstarted();

    public GrpcCleanupRule setTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "timeout should be positive");
        this.timeoutNanos = timeUnit.toNanos(j);
        return this;
    }

    GrpcCleanupRule setTicker(Ticker ticker) {
        this.stopwatch = Stopwatch.createUnstarted(ticker);
        return this;
    }

    public <T extends ManagedChannel> T register(@Nonnull T t) {
        Preconditions.checkNotNull(t, "channel");
        register(new ManagedChannelResource(t));
        return t;
    }

    public <T extends Server> T register(@Nonnull T t) {
        Preconditions.checkNotNull(t, "server");
        register(new ServerResource(t));
        return t;
    }

    void register(Resource resource) {
        this.resources.add(resource);
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement statement, Description description) {
        return new Statement() { // from class: io.grpc.testing.GrpcCleanupRule.1
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                    GrpcCleanupRule.this.teardown();
                    if (GrpcCleanupRule.this.firstException != null) {
                        throw GrpcCleanupRule.this.firstException;
                    }
                } catch (Throwable th) {
                    GrpcCleanupRule.this.firstException = th;
                    try {
                        GrpcCleanupRule.this.teardown();
                        throw th;
                    } catch (Throwable th2) {
                        throw new MultipleFailureException(Arrays.asList(th, th2));
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void teardown() {
        this.stopwatch.start();
        if (this.firstException == null) {
            for (int size = this.resources.size() - 1; size >= 0; size--) {
                this.resources.get(size).cleanUp();
            }
        }
        for (int size2 = this.resources.size() - 1; size2 >= 0; size2--) {
            if (this.firstException != null) {
                this.resources.get(size2).forceCleanUp();
            } else {
                try {
                    if (!this.resources.get(size2).awaitReleased(this.timeoutNanos - this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS)) {
                        this.firstException = new AssertionError("Resource " + this.resources.get(size2) + " can not be released in time at the end of test");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    this.firstException = e;
                }
                if (this.firstException != null) {
                    this.resources.get(size2).forceCleanUp();
                }
            }
        }
        this.resources.clear();
    }

    interface Resource {
        boolean awaitReleased(long j, TimeUnit timeUnit) throws InterruptedException;

        void cleanUp();

        void forceCleanUp();
    }

    private static final class ManagedChannelResource implements Resource {
        final ManagedChannel channel;

        ManagedChannelResource(ManagedChannel managedChannel) {
            this.channel = managedChannel;
        }

        @Override // io.grpc.testing.GrpcCleanupRule.Resource
        public void cleanUp() {
            this.channel.shutdown();
        }

        @Override // io.grpc.testing.GrpcCleanupRule.Resource
        public void forceCleanUp() {
            this.channel.shutdownNow();
        }

        @Override // io.grpc.testing.GrpcCleanupRule.Resource
        public boolean awaitReleased(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.channel.awaitTermination(j, timeUnit);
        }

        public String toString() {
            return this.channel.toString();
        }
    }

    private static final class ServerResource implements Resource {
        final Server server;

        ServerResource(Server server) {
            this.server = server;
        }

        @Override // io.grpc.testing.GrpcCleanupRule.Resource
        public void cleanUp() {
            this.server.shutdown();
        }

        @Override // io.grpc.testing.GrpcCleanupRule.Resource
        public void forceCleanUp() {
            this.server.shutdownNow();
        }

        @Override // io.grpc.testing.GrpcCleanupRule.Resource
        public boolean awaitReleased(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.server.awaitTermination(j, timeUnit);
        }

        public String toString() {
            return this.server.toString();
        }
    }
}
