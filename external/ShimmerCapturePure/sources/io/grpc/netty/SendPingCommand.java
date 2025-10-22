package io.grpc.netty;

import io.grpc.internal.ClientTransport;
import io.grpc.netty.WriteQueue;

import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
class SendPingCommand extends WriteQueue.AbstractQueuedCommand {
    private final ClientTransport.PingCallback callback;
    private final Executor executor;

    SendPingCommand(ClientTransport.PingCallback pingCallback, Executor executor) {
        this.callback = pingCallback;
        this.executor = executor;
    }

    ClientTransport.PingCallback callback() {
        return this.callback;
    }

    Executor executor() {
        return this.executor;
    }
}
