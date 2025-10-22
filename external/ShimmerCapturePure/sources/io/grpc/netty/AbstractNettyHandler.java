package io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.netty.ListeningEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2LocalFlowController;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.codec.http2.Http2Stream;

import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
abstract class AbstractNettyHandler extends GrpcHttp2ConnectionHandler {
    private static final long BDP_MEASUREMENT_PING = 1234;
    private static final long GRACEFUL_SHUTDOWN_NO_TIMEOUT = -1;
    private static final int MAX_ALLOWED_PING = 2;
    private final FlowControlPinger flowControlPing;
    private final int initialConnectionWindow;
    private final PingCountingListener pingCountingListener;
    private boolean autoTuneFlowControlOn;
    private ChannelHandlerContext ctx;
    private boolean initialWindowSent;

    AbstractNettyHandler(ChannelPromise channelPromise, Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, boolean z) {
        super(channelPromise, http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        PingCountingListener pingCountingListener = new PingCountingListener();
        this.pingCountingListener = pingCountingListener;
        this.flowControlPing = new FlowControlPinger(2);
        this.initialWindowSent = false;
        gracefulShutdownTimeoutMillis(-1L);
        this.initialConnectionWindow = http2Settings.initialWindowSize() == null ? -1 : http2Settings.initialWindowSize().intValue();
        this.autoTuneFlowControlOn = z;
        if (http2ConnectionEncoder instanceof ListeningEncoder) {
            ((ListeningEncoder) http2ConnectionEncoder).setListener(pingCountingListener);
        }
    }

    protected final ChannelHandlerContext ctx() {
        return this.ctx;
    }

    FlowControlPinger flowControlPing() {
        return this.flowControlPing;
    }

    void setAutoTuneFlowControl(boolean z) {
        this.autoTuneFlowControlOn = z;
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        super.handlerAdded(channelHandlerContext);
        sendInitialConnectionWindow();
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelActive(channelHandlerContext);
        sendInitialConnectionWindow();
    }

    public final void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (Http2CodecUtil.getEmbeddedHttp2Exception(th) == null) {
            onError(channelHandlerContext, false, th);
        } else {
            super.exceptionCaught(channelHandlerContext, th);
        }
    }

    private void sendInitialConnectionWindow() throws Http2Exception {
        if (this.initialWindowSent || !this.ctx.channel().isActive()) {
            return;
        }
        Http2Stream http2StreamConnectionStream = connection().connectionStream();
        decoder().flowController().incrementWindowSize(http2StreamConnectionStream, this.initialConnectionWindow - connection().local().flowController().windowSize(http2StreamConnectionStream));
        this.initialWindowSent = true;
        this.ctx.flush();
    }

    private static class PingCountingListener extends ListeningEncoder.Http2OutboundFrameListener {
        int pingCount;

        private PingCountingListener() {
            this.pingCount = 0;
        }

        @Override // io.grpc.netty.ListeningEncoder.Http2OutboundFrameListener
        public void onWindowUpdate(int i, int i2) {
            this.pingCount = 0;
            super.onWindowUpdate(i, i2);
        }

        @Override // io.grpc.netty.ListeningEncoder.Http2OutboundFrameListener
        public void onPing(boolean z, long j) {
            if (!z) {
                this.pingCount++;
            }
            super.onPing(z, j);
        }

        @Override // io.grpc.netty.ListeningEncoder.Http2OutboundFrameListener
        public void onData(int i, ByteBuf byteBuf, int i2, boolean z) {
            this.pingCount = 0;
            super.onData(i, byteBuf, i2, z);
        }
    }

    final class FlowControlPinger {
        private static final int MAX_WINDOW_SIZE = 8388608;
        private final int maxAllowedPing;
        private int dataSizeSincePing;
        private float lastBandwidth;
        private long lastPingTime;
        private int pingCount;
        private int pingReturn;
        private boolean pinging;

        public FlowControlPinger(int i) {
            Preconditions.checkArgument(i > 0, "maxAllowedPing must be positive");
            this.maxAllowedPing = i;
        }

        private boolean isPinging() {
            return this.pinging;
        }

        private void setPinging(boolean z) {
            this.pinging = z;
        }

        private void setDataSizeSincePing(int i) {
            this.dataSizeSincePing = i;
        }

        int getDataSincePing() {
            return this.dataSizeSincePing;
        }

        int getPingCount() {
            return this.pingCount;
        }

        int getPingReturn() {
            return this.pingReturn;
        }

        public int maxWindow() {
            return 8388608;
        }

        public long payload() {
            return AbstractNettyHandler.BDP_MEASUREMENT_PING;
        }

        public void onDataRead(int i, int i2) {
            if (AbstractNettyHandler.this.autoTuneFlowControlOn) {
                if (!isPinging() && AbstractNettyHandler.this.pingCountingListener.pingCount < this.maxAllowedPing) {
                    setPinging(true);
                    sendPing(AbstractNettyHandler.this.ctx());
                }
                incrementDataSincePing(i + i2);
            }
        }

        public void updateWindow() throws Http2Exception {
            if (AbstractNettyHandler.this.autoTuneFlowControlOn) {
                this.pingReturn++;
                long jNanoTime = System.nanoTime() - this.lastPingTime;
                if (jNanoTime == 0) {
                    jNanoTime = 1;
                }
                long dataSincePing = (getDataSincePing() * TimeUnit.SECONDS.toNanos(1L)) / jNanoTime;
                Http2LocalFlowController http2LocalFlowControllerFlowController = AbstractNettyHandler.this.decoder().flowController();
                int iMin = Math.min(getDataSincePing() * 2, 8388608);
                setPinging(false);
                int iInitialWindowSize = http2LocalFlowControllerFlowController.initialWindowSize(AbstractNettyHandler.this.connection().connectionStream());
                if (iMin > iInitialWindowSize) {
                    float f = dataSincePing;
                    if (f > this.lastBandwidth) {
                        this.lastBandwidth = f;
                        http2LocalFlowControllerFlowController.incrementWindowSize(AbstractNettyHandler.this.connection().connectionStream(), iMin - iInitialWindowSize);
                        http2LocalFlowControllerFlowController.initialWindowSize(iMin);
                        Http2Settings http2Settings = new Http2Settings();
                        http2Settings.initialWindowSize(iMin);
                        AbstractNettyHandler.this.frameWriter().writeSettings(AbstractNettyHandler.this.ctx(), http2Settings, AbstractNettyHandler.this.ctx().newPromise());
                    }
                }
            }
        }

        private void sendPing(ChannelHandlerContext channelHandlerContext) {
            setDataSizeSincePing(0);
            this.lastPingTime = System.nanoTime();
            AbstractNettyHandler.this.encoder().writePing(channelHandlerContext, false, AbstractNettyHandler.BDP_MEASUREMENT_PING, channelHandlerContext.newPromise());
            this.pingCount++;
        }

        private void incrementDataSincePing(int i) {
            setDataSizeSincePing(getDataSincePing() + i);
        }

        void setDataSizeAndSincePing(int i) {
            setDataSizeSincePing(i);
            this.lastPingTime = System.nanoTime() - TimeUnit.SECONDS.toNanos(1L);
        }
    }
}
