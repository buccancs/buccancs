package io.grpc.netty.shaded.io.netty.channel.oio;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: classes3.dex */
public abstract class AbstractOioMessageChannel extends AbstractOioChannel {
    private final List<Object> readBuf;

    protected AbstractOioMessageChannel(Channel channel) {
        super(channel);
        this.readBuf = new ArrayList();
    }

    protected abstract int doReadMessages(List<Object> list) throws Exception;

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioChannel
    protected void doRead() {
        Throwable th;
        boolean z;
        if (this.readPending) {
            boolean z2 = false;
            this.readPending = false;
            ChannelConfig channelConfigConfig = config();
            ChannelPipeline channelPipelinePipeline = pipeline();
            RecvByteBufAllocator.Handle handleRecvBufAllocHandle = unsafe().recvBufAllocHandle();
            handleRecvBufAllocHandle.reset(channelConfigConfig);
            do {
                try {
                    int iDoReadMessages = doReadMessages(this.readBuf);
                    if (iDoReadMessages == 0) {
                        break;
                    }
                    if (iDoReadMessages < 0) {
                        z = true;
                        break;
                    }
                    handleRecvBufAllocHandle.incMessagesRead(iDoReadMessages);
                } catch (Throwable th2) {
                    th = th2;
                    z = false;
                }
            } while (handleRecvBufAllocHandle.continueReading());
            z = false;
            th = null;
            int size = this.readBuf.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    this.readPending = false;
                    channelPipelinePipeline.fireChannelRead(this.readBuf.get(i));
                }
                this.readBuf.clear();
                handleRecvBufAllocHandle.readComplete();
                channelPipelinePipeline.fireChannelReadComplete();
                z2 = true;
            }
            if (th != null) {
                boolean z3 = th instanceof IOException ? true : z;
                channelPipelinePipeline.fireExceptionCaught(th);
                z = z3;
            }
            if (z) {
                if (isOpen()) {
                    unsafe().close(unsafe().voidPromise());
                }
            } else if (this.readPending || channelConfigConfig.isAutoRead() || (!z2 && isActive())) {
                read();
            }
        }
    }
}
