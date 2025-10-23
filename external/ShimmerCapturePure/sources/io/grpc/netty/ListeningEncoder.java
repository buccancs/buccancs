package io.grpc.netty;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2FrameWriter;
import io.netty.handler.codec.http2.StreamBufferingEncoder;

/* loaded from: classes2.dex */
interface ListeningEncoder {

    void setListener(Http2OutboundFrameListener http2OutboundFrameListener);

    public static class Http2OutboundFrameListener {
        public void onData(int i, ByteBuf byteBuf, int i2, boolean z) {
        }

        public void onPing(boolean z, long j) {
        }

        public void onWindowUpdate(int i, int i2) {
        }
    }

    public static final class ListeningStreamBufferingEncoder extends StreamBufferingEncoder implements ListeningEncoder {
        private Http2OutboundFrameListener listener;

        public ListeningStreamBufferingEncoder(Http2ConnectionEncoder http2ConnectionEncoder) {
            super(http2ConnectionEncoder);
            this.listener = new Http2OutboundFrameListener();
        }

        @Override // io.grpc.netty.ListeningEncoder
        public void setListener(Http2OutboundFrameListener http2OutboundFrameListener) {
            this.listener = (Http2OutboundFrameListener) Preconditions.checkNotNull(http2OutboundFrameListener, "listener");
        }

        public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
            this.listener.onPing(z, j);
            return super.writePing(channelHandlerContext, z, j, channelPromise);
        }

        public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
            this.listener.onWindowUpdate(i, i2);
            return super.writeWindowUpdate(channelHandlerContext, i, i2, channelPromise);
        }

        public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
            this.listener.onData(i, byteBuf, i2, z);
            return super.writeData(channelHandlerContext, i, byteBuf, i2, z, channelPromise);
        }
    }

    public static final class ListeningDefaultHttp2ConnectionEncoder extends DefaultHttp2ConnectionEncoder implements ListeningEncoder {
        private Http2OutboundFrameListener listener;

        public ListeningDefaultHttp2ConnectionEncoder(Http2Connection http2Connection, Http2FrameWriter http2FrameWriter) {
            super(http2Connection, http2FrameWriter);
            this.listener = new Http2OutboundFrameListener();
        }

        @Override // io.grpc.netty.ListeningEncoder
        public void setListener(Http2OutboundFrameListener http2OutboundFrameListener) {
            this.listener = (Http2OutboundFrameListener) Preconditions.checkNotNull(http2OutboundFrameListener, "listener");
        }

        public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
            this.listener.onPing(z, j);
            return super.writePing(channelHandlerContext, z, j, channelPromise);
        }

        public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
            this.listener.onWindowUpdate(i, i2);
            return super.writeWindowUpdate(channelHandlerContext, i, i2, channelPromise);
        }

        public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
            this.listener.onData(i, byteBuf, i2, z);
            return super.writeData(channelHandlerContext, i, byteBuf, i2, z, channelPromise);
        }
    }
}
