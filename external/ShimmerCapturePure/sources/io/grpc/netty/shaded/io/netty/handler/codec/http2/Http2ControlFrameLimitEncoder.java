package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

/* loaded from: classes3.dex */
final class Http2ControlFrameLimitEncoder extends DecoratingHttp2ConnectionEncoder {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Http2ControlFrameLimitEncoder.class);
    private final int maxOutstandingControlFrames;
    private final ChannelFutureListener outstandingControlFramesListener;
    private Http2LifecycleManager lifecycleManager;
    private boolean limitReached;
    private int outstandingControlFrames;

    Http2ControlFrameLimitEncoder(Http2ConnectionEncoder http2ConnectionEncoder, int i) {
        super(http2ConnectionEncoder);
        this.outstandingControlFramesListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ControlFrameLimitEncoder.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                Http2ControlFrameLimitEncoder.access$010(Http2ControlFrameLimitEncoder.this);
            }
        };
        this.maxOutstandingControlFrames = ObjectUtil.checkPositive(i, "maxOutstandingControlFrames");
    }

    static /* synthetic */ int access$010(Http2ControlFrameLimitEncoder http2ControlFrameLimitEncoder) {
        int i = http2ControlFrameLimitEncoder.outstandingControlFrames;
        http2ControlFrameLimitEncoder.outstandingControlFrames = i - 1;
        return i;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2ConnectionEncoder, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.lifecycleManager = http2LifecycleManager;
        super.lifecycleManager(http2LifecycleManager);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        ChannelPromise channelPromiseHandleOutstandingControlFrames = handleOutstandingControlFrames(channelHandlerContext, channelPromise);
        return channelPromiseHandleOutstandingControlFrames == null ? channelPromise : super.writeSettingsAck(channelHandlerContext, channelPromiseHandleOutstandingControlFrames);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        if (z) {
            ChannelPromise channelPromiseHandleOutstandingControlFrames = handleOutstandingControlFrames(channelHandlerContext, channelPromise);
            return channelPromiseHandleOutstandingControlFrames == null ? channelPromise : super.writePing(channelHandlerContext, z, j, channelPromiseHandleOutstandingControlFrames);
        }
        return super.writePing(channelHandlerContext, z, j, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        ChannelPromise channelPromiseHandleOutstandingControlFrames = handleOutstandingControlFrames(channelHandlerContext, channelPromise);
        return channelPromiseHandleOutstandingControlFrames == null ? channelPromise : super.writeRstStream(channelHandlerContext, i, j, channelPromiseHandleOutstandingControlFrames);
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
    private ChannelPromise handleOutstandingControlFrames(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        if (this.limitReached) {
            return channelPromise;
        }
        if (this.outstandingControlFrames == this.maxOutstandingControlFrames) {
            channelHandlerContext.flush();
        }
        if (this.outstandingControlFrames == this.maxOutstandingControlFrames) {
            this.limitReached = true;
            Http2Exception http2ExceptionConnectionError = Http2Exception.connectionError(Http2Error.ENHANCE_YOUR_CALM, "Maximum number %d of outstanding control frames reached", Integer.valueOf(this.maxOutstandingControlFrames));
            logger.info("Maximum number {} of outstanding control frames reached. Closing channel {}", Integer.valueOf(this.maxOutstandingControlFrames), channelHandlerContext.channel(), http2ExceptionConnectionError);
            this.lifecycleManager.onError(channelHandlerContext, true, http2ExceptionConnectionError);
            channelHandlerContext.close();
        }
        this.outstandingControlFrames++;
        return channelPromise.unvoid().addListener((GenericFutureListener<? extends Future<? super Void>>) this.outstandingControlFramesListener);
    }
}
