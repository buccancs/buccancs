package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedMessageTypeException;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

import java.net.SocketAddress;
import java.util.List;

/* loaded from: classes3.dex */
public class SpdyFrameCodec extends ByteToMessageDecoder implements SpdyFrameDecoderDelegate, ChannelOutboundHandler {
    private static final SpdyProtocolException INVALID_FRAME = new SpdyProtocolException("Received invalid frame");
    private final SpdyFrameDecoder spdyFrameDecoder;
    private final SpdyFrameEncoder spdyFrameEncoder;
    private final SpdyHeaderBlockDecoder spdyHeaderBlockDecoder;
    private final SpdyHeaderBlockEncoder spdyHeaderBlockEncoder;
    private final boolean validateHeaders;
    private ChannelHandlerContext ctx;
    private boolean read;
    private SpdyHeadersFrame spdyHeadersFrame;
    private SpdySettingsFrame spdySettingsFrame;

    public SpdyFrameCodec(SpdyVersion spdyVersion) {
        this(spdyVersion, true);
    }

    public SpdyFrameCodec(SpdyVersion spdyVersion, boolean z) {
        this(spdyVersion, 8192, 16384, 6, 15, 8, z);
    }

    public SpdyFrameCodec(SpdyVersion spdyVersion, int i, int i2, int i3, int i4, int i5) {
        this(spdyVersion, i, i2, i3, i4, i5, true);
    }

    public SpdyFrameCodec(SpdyVersion spdyVersion, int i, int i2, int i3, int i4, int i5, boolean z) {
        this(spdyVersion, i, SpdyHeaderBlockDecoder.newInstance(spdyVersion, i2), SpdyHeaderBlockEncoder.newInstance(spdyVersion, i3, i4, i5), z);
    }

    protected SpdyFrameCodec(SpdyVersion spdyVersion, int i, SpdyHeaderBlockDecoder spdyHeaderBlockDecoder, SpdyHeaderBlockEncoder spdyHeaderBlockEncoder, boolean z) {
        this.spdyFrameDecoder = new SpdyFrameDecoder(spdyVersion, this, i);
        this.spdyFrameEncoder = new SpdyFrameEncoder(spdyVersion);
        this.spdyHeaderBlockDecoder = spdyHeaderBlockDecoder;
        this.spdyHeaderBlockEncoder = spdyHeaderBlockEncoder;
        this.validateHeaders = z;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerAdded(channelHandlerContext);
        this.ctx = channelHandlerContext;
        channelHandlerContext.channel().closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameCodec.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                SpdyFrameCodec.this.spdyHeaderBlockDecoder.end();
                SpdyFrameCodec.this.spdyHeaderBlockEncoder.end();
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        this.spdyFrameDecoder.decode(byteBuf);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.read && !channelHandlerContext.channel().config().isAutoRead()) {
            channelHandlerContext.read();
        }
        this.read = false;
        super.channelReadComplete(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.read();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.flush();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        ByteBuf byteBufEncode;
        if (obj instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) obj;
            ByteBuf byteBufEncodeDataFrame = this.spdyFrameEncoder.encodeDataFrame(channelHandlerContext.alloc(), spdyDataFrame.streamId(), spdyDataFrame.isLast(), spdyDataFrame.content());
            spdyDataFrame.release();
            channelHandlerContext.write(byteBufEncodeDataFrame, channelPromise);
            return;
        }
        if (obj instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) obj;
            byteBufEncode = this.spdyHeaderBlockEncoder.encode(channelHandlerContext.alloc(), spdySynStreamFrame);
            try {
                ByteBuf byteBufEncodeSynStreamFrame = this.spdyFrameEncoder.encodeSynStreamFrame(channelHandlerContext.alloc(), spdySynStreamFrame.streamId(), spdySynStreamFrame.associatedStreamId(), spdySynStreamFrame.priority(), spdySynStreamFrame.isLast(), spdySynStreamFrame.isUnidirectional(), byteBufEncode);
                byteBufEncode.release();
                channelHandlerContext.write(byteBufEncodeSynStreamFrame, channelPromise);
                return;
            } finally {
            }
        }
        if (obj instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) obj;
            byteBufEncode = this.spdyHeaderBlockEncoder.encode(channelHandlerContext.alloc(), spdySynReplyFrame);
            try {
                ByteBuf byteBufEncodeSynReplyFrame = this.spdyFrameEncoder.encodeSynReplyFrame(channelHandlerContext.alloc(), spdySynReplyFrame.streamId(), spdySynReplyFrame.isLast(), byteBufEncode);
                byteBufEncode.release();
                channelHandlerContext.write(byteBufEncodeSynReplyFrame, channelPromise);
                return;
            } finally {
            }
        }
        if (obj instanceof SpdyRstStreamFrame) {
            SpdyRstStreamFrame spdyRstStreamFrame = (SpdyRstStreamFrame) obj;
            channelHandlerContext.write(this.spdyFrameEncoder.encodeRstStreamFrame(channelHandlerContext.alloc(), spdyRstStreamFrame.streamId(), spdyRstStreamFrame.status().code()), channelPromise);
            return;
        }
        if (obj instanceof SpdySettingsFrame) {
            channelHandlerContext.write(this.spdyFrameEncoder.encodeSettingsFrame(channelHandlerContext.alloc(), (SpdySettingsFrame) obj), channelPromise);
            return;
        }
        if (obj instanceof SpdyPingFrame) {
            channelHandlerContext.write(this.spdyFrameEncoder.encodePingFrame(channelHandlerContext.alloc(), ((SpdyPingFrame) obj).id()), channelPromise);
            return;
        }
        if (obj instanceof SpdyGoAwayFrame) {
            SpdyGoAwayFrame spdyGoAwayFrame = (SpdyGoAwayFrame) obj;
            channelHandlerContext.write(this.spdyFrameEncoder.encodeGoAwayFrame(channelHandlerContext.alloc(), spdyGoAwayFrame.lastGoodStreamId(), spdyGoAwayFrame.status().code()), channelPromise);
            return;
        }
        if (obj instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) obj;
            byteBufEncode = this.spdyHeaderBlockEncoder.encode(channelHandlerContext.alloc(), spdyHeadersFrame);
            try {
                ByteBuf byteBufEncodeHeadersFrame = this.spdyFrameEncoder.encodeHeadersFrame(channelHandlerContext.alloc(), spdyHeadersFrame.streamId(), spdyHeadersFrame.isLast(), byteBufEncode);
                byteBufEncode.release();
                channelHandlerContext.write(byteBufEncodeHeadersFrame, channelPromise);
                return;
            } finally {
            }
        }
        if (obj instanceof SpdyWindowUpdateFrame) {
            SpdyWindowUpdateFrame spdyWindowUpdateFrame = (SpdyWindowUpdateFrame) obj;
            channelHandlerContext.write(this.spdyFrameEncoder.encodeWindowUpdateFrame(channelHandlerContext.alloc(), spdyWindowUpdateFrame.streamId(), spdyWindowUpdateFrame.deltaWindowSize()), channelPromise);
            return;
        }
        throw new UnsupportedMessageTypeException(obj, (Class<?>[]) new Class[0]);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readDataFrame(int i, boolean z, ByteBuf byteBuf) {
        this.read = true;
        DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(i, byteBuf);
        defaultSpdyDataFrame.setLast(z);
        this.ctx.fireChannelRead((Object) defaultSpdyDataFrame);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readSynStreamFrame(int i, int i2, byte b, boolean z, boolean z2) {
        DefaultSpdySynStreamFrame defaultSpdySynStreamFrame = new DefaultSpdySynStreamFrame(i, i2, b, this.validateHeaders);
        defaultSpdySynStreamFrame.setLast(z);
        defaultSpdySynStreamFrame.setUnidirectional(z2);
        this.spdyHeadersFrame = defaultSpdySynStreamFrame;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readSynReplyFrame(int i, boolean z) {
        DefaultSpdySynReplyFrame defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(i, this.validateHeaders);
        defaultSpdySynReplyFrame.setLast(z);
        this.spdyHeadersFrame = defaultSpdySynReplyFrame;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readRstStreamFrame(int i, int i2) {
        this.read = true;
        this.ctx.fireChannelRead((Object) new DefaultSpdyRstStreamFrame(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readSettingsFrame(boolean z) {
        this.read = true;
        DefaultSpdySettingsFrame defaultSpdySettingsFrame = new DefaultSpdySettingsFrame();
        this.spdySettingsFrame = defaultSpdySettingsFrame;
        defaultSpdySettingsFrame.setClearPreviouslyPersistedSettings(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readSetting(int i, int i2, boolean z, boolean z2) {
        this.spdySettingsFrame.setValue(i, i2, z, z2);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readSettingsEnd() {
        this.read = true;
        SpdySettingsFrame spdySettingsFrame = this.spdySettingsFrame;
        this.spdySettingsFrame = null;
        this.ctx.fireChannelRead((Object) spdySettingsFrame);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readPingFrame(int i) {
        this.read = true;
        this.ctx.fireChannelRead((Object) new DefaultSpdyPingFrame(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readGoAwayFrame(int i, int i2) {
        this.read = true;
        this.ctx.fireChannelRead((Object) new DefaultSpdyGoAwayFrame(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readHeadersFrame(int i, boolean z) {
        DefaultSpdyHeadersFrame defaultSpdyHeadersFrame = new DefaultSpdyHeadersFrame(i, this.validateHeaders);
        this.spdyHeadersFrame = defaultSpdyHeadersFrame;
        defaultSpdyHeadersFrame.setLast(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readWindowUpdateFrame(int i, int i2) {
        this.read = true;
        this.ctx.fireChannelRead((Object) new DefaultSpdyWindowUpdateFrame(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readHeaderBlock(ByteBuf byteBuf) {
        try {
            try {
                this.spdyHeaderBlockDecoder.decode(this.ctx.alloc(), byteBuf, this.spdyHeadersFrame);
            } catch (Exception e) {
                this.ctx.fireExceptionCaught((Throwable) e);
            }
        } finally {
            byteBuf.release();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void readHeaderBlockEnd() {
        /*
            r4 = this;
            r0 = 0
            io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder r1 = r4.spdyHeaderBlockDecoder     // Catch: java.lang.Exception -> Lf
            io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame r2 = r4.spdyHeadersFrame     // Catch: java.lang.Exception -> Lf
            r1.endHeaderBlock(r2)     // Catch: java.lang.Exception -> Lf
            io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeadersFrame r1 = r4.spdyHeadersFrame     // Catch: java.lang.Exception -> Lf
            r4.spdyHeadersFrame = r0     // Catch: java.lang.Exception -> Ld
            goto L18
        Ld:
            r0 = move-exception
            goto L13
        Lf:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L13:
            io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r2 = r4.ctx
            r2.fireExceptionCaught(r0)
        L18:
            if (r1 == 0) goto L22
            r0 = 1
            r4.read = r0
            io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r0 = r4.ctx
            r0.fireChannelRead(r1)
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameCodec.readHeaderBlockEnd():void");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyFrameDecoderDelegate
    public void readFrameError(String str) {
        this.ctx.fireExceptionCaught((Throwable) INVALID_FRAME);
    }
}
