package io.grpc.netty;

import io.grpc.netty.WriteQueue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
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

    @Override // io.grpc.netty.WriteQueue.QueuedCommand
    public Link getLink() {
        return this.link;
    }

    @Override // io.grpc.netty.WriteQueue.QueuedCommand
    public ChannelPromise promise() {
        return this.promise;
    }

    @Override // io.grpc.netty.WriteQueue.QueuedCommand
    public void promise(ChannelPromise channelPromise) {
        this.promise = channelPromise;
    }

    StreamIdHolder stream() {
        return this.stream;
    }

    public ByteBufHolder copy() {
        return new SendGrpcFrameCommand(this.stream, content().copy(), this.endStream);
    }

    public ByteBufHolder duplicate() {
        return new SendGrpcFrameCommand(this.stream, content().duplicate(), this.endStream);
    }

    /* renamed from: retain, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SendGrpcFrameCommand m9612retain() {
        super.retain();
        return this;
    }

    /* renamed from: retain, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SendGrpcFrameCommand m9613retain(int i) {
        super.retain(i);
        return this;
    }

    /* renamed from: touch, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SendGrpcFrameCommand m9616touch() {
        super.touch();
        return this;
    }

    /* renamed from: touch, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SendGrpcFrameCommand m9617touch(Object obj) {
        super.touch(obj);
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(SendGrpcFrameCommand.class)) {
            return false;
        }
        SendGrpcFrameCommand sendGrpcFrameCommand = (SendGrpcFrameCommand) obj;
        return sendGrpcFrameCommand.stream.equals(this.stream) && sendGrpcFrameCommand.endStream == this.endStream && sendGrpcFrameCommand.content().equals(content());
    }

    public String toString() {
        return getClass().getSimpleName() + "(streamId=" + this.stream.id() + ", endStream=" + this.endStream + ", content=" + content() + ")";
    }

    public int hashCode() {
        int iHashCode = (content().hashCode() * 31) + this.stream.hashCode();
        return this.endStream ? -iHashCode : iHashCode;
    }

    @Override // io.grpc.netty.WriteQueue.QueuedCommand
    public final void run(Channel channel) {
        channel.write(this, this.promise);
    }
}
