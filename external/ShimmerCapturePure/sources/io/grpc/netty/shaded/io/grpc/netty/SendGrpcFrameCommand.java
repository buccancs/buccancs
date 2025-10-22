package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;
import io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.perfmark.Link;
import io.perfmark.PerfMark;

/* loaded from: classes2.dex */
final class SendGrpcFrameCommand extends DefaultByteBufHolder implements WriteQueue.QueuedCommand {
    private final boolean endStream;
    private final Link link;
    private final StreamIdHolder stream;
    private ChannelPromise promise;

    SendGrpcFrameCommand(StreamIdHolder streamIdHolder, ByteBuf byteBuf, boolean z) {
        super(byteBuf);
        this.stream = streamIdHolder;
        this.endStream = z;
        this.link = PerfMark.linkOut();
    }

    boolean endStream() {
        return this.endStream;
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
    public Link getLink() {
        return this.link;
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
    public ChannelPromise promise() {
        return this.promise;
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
    public void promise(ChannelPromise channelPromise) {
        this.promise = channelPromise;
    }

    StreamIdHolder stream() {
        return this.stream;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBufHolder copy() {
        return new SendGrpcFrameCommand(this.stream, content().copy(), this.endStream);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public ByteBufHolder duplicate() {
        return new SendGrpcFrameCommand(this.stream, content().duplicate(), this.endStream);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SendGrpcFrameCommand retain() {
        super.retain();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SendGrpcFrameCommand retain(int i) {
        super.retain(i);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SendGrpcFrameCommand touch() {
        super.touch();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public SendGrpcFrameCommand touch(Object obj) {
        super.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(SendGrpcFrameCommand.class)) {
            return false;
        }
        SendGrpcFrameCommand sendGrpcFrameCommand = (SendGrpcFrameCommand) obj;
        return sendGrpcFrameCommand.stream.equals(this.stream) && sendGrpcFrameCommand.endStream == this.endStream && sendGrpcFrameCommand.content().equals(content());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public String toString() {
        return getClass().getSimpleName() + "(streamId=" + this.stream.id() + ", endStream=" + this.endStream + ", content=" + content() + ")";
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.DefaultByteBufHolder
    public int hashCode() {
        int iHashCode = (content().hashCode() * 31) + this.stream.hashCode();
        return this.endStream ? -iHashCode : iHashCode;
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
    public final void run(Channel channel) {
        channel.write(this, this.promise);
    }
}
