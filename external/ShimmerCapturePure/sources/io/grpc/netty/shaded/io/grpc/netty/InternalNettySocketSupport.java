package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.InternalChannelz;
import io.grpc.netty.shaded.io.grpc.netty.NettySocketSupport;
import io.grpc.netty.shaded.io.netty.channel.Channel;

import java.util.Map;

/* loaded from: classes2.dex */
public final class InternalNettySocketSupport {

    private InternalNettySocketSupport() {
    }

    public static void setHelper(InternalHelper internalHelper) {
        NettySocketSupport.setHelper(internalHelper);
    }

    public interface InternalHelper extends NettySocketSupport.Helper {
        @Override
            // io.grpc.netty.shaded.io.grpc.netty.NettySocketSupport.Helper
        InternalNativeSocketOptions getNativeSocketOptions(Channel channel);
    }

    public static final class InternalNativeSocketOptions extends NettySocketSupport.NativeSocketOptions {
        public InternalNativeSocketOptions(InternalChannelz.TcpInfo tcpInfo, Map<String, String> map) {
            super(tcpInfo, map);
        }
    }
}
