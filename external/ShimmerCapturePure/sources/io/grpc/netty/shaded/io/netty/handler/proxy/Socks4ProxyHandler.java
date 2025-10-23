package io.grpc.netty.shaded.io.netty.handler.proxy;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.DefaultSocks4CommandRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ClientDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ClientEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4CommandResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4CommandStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4CommandType;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* loaded from: classes3.dex */
public final class Socks4ProxyHandler extends ProxyHandler {
    private static final String AUTH_USERNAME = "username";
    private static final String PROTOCOL = "socks4";
    private final String username;
    private String decoderName;
    private String encoderName;

    public Socks4ProxyHandler(SocketAddress socketAddress) {
        this(socketAddress, null);
    }

    public Socks4ProxyHandler(SocketAddress socketAddress, String str) {
        super(socketAddress);
        if (str != null && str.isEmpty()) {
            str = null;
        }
        this.username = str;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String authScheme() {
        return this.username != null ? AUTH_USERNAME : "none";
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String protocol() {
        return PROTOCOL;
    }

    public String username() {
        return this.username;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void addCodec(ChannelHandlerContext channelHandlerContext) throws Exception {
        ChannelPipeline channelPipelinePipeline = channelHandlerContext.pipeline();
        String strName = channelHandlerContext.name();
        Socks4ClientDecoder socks4ClientDecoder = new Socks4ClientDecoder();
        channelPipelinePipeline.addBefore(strName, null, socks4ClientDecoder);
        this.decoderName = channelPipelinePipeline.context(socks4ClientDecoder).name();
        String str = this.decoderName + ".encoder";
        this.encoderName = str;
        channelPipelinePipeline.addBefore(strName, str, Socks4ClientEncoder.INSTANCE);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeEncoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().remove(this.encoderName);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeDecoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().remove(this.decoderName);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected Object newInitialMessage(ChannelHandlerContext channelHandlerContext) throws Exception {
        String hostAddress;
        InetSocketAddress inetSocketAddress = (InetSocketAddress) destinationAddress();
        if (inetSocketAddress.isUnresolved()) {
            hostAddress = inetSocketAddress.getHostString();
        } else {
            hostAddress = inetSocketAddress.getAddress().getHostAddress();
        }
        Socks4CommandType socks4CommandType = Socks4CommandType.CONNECT;
        int port = inetSocketAddress.getPort();
        String str = this.username;
        if (str == null) {
            str = "";
        }
        return new DefaultSocks4CommandRequest(socks4CommandType, hostAddress, port, str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected boolean handleResponse(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        Socks4CommandStatus socks4CommandStatusStatus = ((Socks4CommandResponse) obj).status();
        if (socks4CommandStatusStatus == Socks4CommandStatus.SUCCESS) {
            return true;
        }
        throw new ProxyConnectException(exceptionMessage("status: " + socks4CommandStatusStatus));
    }
}
