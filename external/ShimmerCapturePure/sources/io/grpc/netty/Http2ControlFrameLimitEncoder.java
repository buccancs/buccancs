package io.grpc.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.DecoratingHttp2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2Error;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2LifecycleManager;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* loaded from: classes2.dex */
final class Http2ControlFrameLimitEncoder extends DecoratingHttp2ConnectionEncoder {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(Http2ControlFrameLimitEncoder.class);
    private final int maxOutstandingControlFrames;
    private final ChannelFutureListener outstandingControlFramesListener;
    private Http2LifecycleManager lifecycleManager;
    private boolean limitReached;
    private int outstandingControlFrames;

    Http2ControlFrameLimitEncoder(Http2ConnectionEncoder http2ConnectionEncoder, int i) {
        super(http2ConnectionEncoder);
        this.outstandingControlFramesListener = new ChannelFutureListener() { // from class: io.grpc.netty.Http2ControlFrameLimitEncoder.1
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

    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.lifecycleManager = http2LifecycleManager;
        super.lifecycleManager(http2LifecycleManager);
    }

    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        ChannelPromise channelPromiseHandleOutstandingControlFrames = handleOutstandingControlFrames(channelHandlerContext, channelPromise);
        return channelPromiseHandleOutstandingControlFrames == null ? channelPromise : super.writeSettingsAck(channelHandlerContext, channelPromiseHandleOutstandingControlFrames);
    }

    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        if (z) {
            ChannelPromise channelPromiseHandleOutstandingControlFrames = handleOutstandingControlFrames(channelHandlerContext, channelPromise);
            return channelPromiseHandleOutstandingControlFrames == null ? channelPromise : super.writePing(channelHandlerContext, z, j, channelPromiseHandleOutstandingControlFrames);
        }
        return super.writePing(channelHandlerContext, z, j, channelPromise);
    }

    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        ChannelPromise channelPromiseHandleOutstandingControlFrames = handleOutstandingControlFrames(channelHandlerContext, channelPromise);
        return channelPromiseHandleOutstandingControlFrames == null ? channelPromise : super.writeRstStream(channelHandlerContext, i, j, channelPromiseHandleOutstandingControlFrames);
    }

    private ChannelPromise handleOutstandingControlFrames(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        if (this.limitReached) {
            return channelPromise;
        }
        if (this.outstandingControlFrames == this.maxOutstandingControlFrames) {
            channelHandlerContext.flush();
        }
        if (this.outstandingControlFrames == this.maxOutstandingControlFrames) {
            this.limitReached = true;
            Http2Exception http2ExceptionConnectionError = Http2Exception.connectionError(Http2Error.ENHANCE_YOUR_CALM, "Maximum number %d of outstanding control frames reached", new Object[]{Integer.valueOf(this.maxOutstandingControlFrames)});
            logger.info("Maximum number {} of outstanding control frames reached. Closing channel {}", new Object[]{Integer.valueOf(this.maxOutstandingControlFrames), channelHandlerContext.channel(), http2ExceptionConnectionError});
            this.lifecycleManager.onError(channelHandlerContext, true, http2ExceptionConnectionError);
            channelHandlerContext.close();
        }
        this.outstandingControlFrames++;
        return channelPromise.unvoid().addListener(this.outstandingControlFramesListener);
    }
}
