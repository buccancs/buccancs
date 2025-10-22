package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientStream;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
class CancelClientStreamCommand extends WriteQueue.AbstractQueuedCommand {

    @Nullable
    private final Status reason;
    private final NettyClientStream.TransportState stream;

    CancelClientStreamCommand(NettyClientStream.TransportState transportState, Status status) {
        this.stream = (NettyClientStream.TransportState) Preconditions.checkNotNull(transportState, "stream");
        Preconditions.checkArgument(status == null || !status.isOk(), "Should not cancel with OK status");
        this.reason = status;
    }

    @Nullable
    Status reason() {
        return this.reason;
    }

    NettyClientStream.TransportState stream() {
        return this.stream;
    }
}
