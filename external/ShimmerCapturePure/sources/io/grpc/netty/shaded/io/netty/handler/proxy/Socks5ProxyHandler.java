package io.grpc.netty.shaded.io.netty.handler.proxy;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.DefaultSocks5CommandRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.DefaultSocks5InitialRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.DefaultSocks5PasswordAuthRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5AddressType;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5AuthMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5ClientEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5CommandResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5CommandResponseDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5CommandStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5CommandType;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5InitialRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5InitialResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5InitialResponseDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5PasswordAuthResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5PasswordAuthResponseDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5PasswordAuthStatus;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Collections;

/* loaded from: classes3.dex */
public final class Socks5ProxyHandler extends ProxyHandler {
    private static final String AUTH_PASSWORD = "password";
    private static final Socks5InitialRequest INIT_REQUEST_NO_AUTH = new DefaultSocks5InitialRequest(Collections.singletonList(Socks5AuthMethod.NO_AUTH));
    private static final Socks5InitialRequest INIT_REQUEST_PASSWORD = new DefaultSocks5InitialRequest(Arrays.asList(Socks5AuthMethod.NO_AUTH, Socks5AuthMethod.PASSWORD));
    private static final String PROTOCOL = "socks5";
    private final String password;
    private final String username;
    private String decoderName;
    private String encoderName;

    public Socks5ProxyHandler(SocketAddress socketAddress) {
        this(socketAddress, null, null);
    }

    public Socks5ProxyHandler(SocketAddress socketAddress, String str, String str2) {
        super(socketAddress);
        if (str != null && str.isEmpty()) {
            str = null;
        }
        if (str2 != null && str2.isEmpty()) {
            str2 = null;
        }
        this.username = str;
        this.password = str2;
    }

    public String password() {
        return this.password;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String protocol() {
        return PROTOCOL;
    }

    public String username() {
        return this.username;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String authScheme() {
        return socksAuthMethod() == Socks5AuthMethod.PASSWORD ? AUTH_PASSWORD : "none";
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void addCodec(ChannelHandlerContext channelHandlerContext) throws Exception {
        ChannelPipeline channelPipelinePipeline = channelHandlerContext.pipeline();
        String strName = channelHandlerContext.name();
        Socks5InitialResponseDecoder socks5InitialResponseDecoder = new Socks5InitialResponseDecoder();
        channelPipelinePipeline.addBefore(strName, null, socks5InitialResponseDecoder);
        this.decoderName = channelPipelinePipeline.context(socks5InitialResponseDecoder).name();
        String str = this.decoderName + ".encoder";
        this.encoderName = str;
        channelPipelinePipeline.addBefore(strName, str, Socks5ClientEncoder.DEFAULT);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeEncoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().remove(this.encoderName);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeDecoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        ChannelPipeline channelPipelinePipeline = channelHandlerContext.pipeline();
        if (channelPipelinePipeline.context(this.decoderName) != null) {
            channelPipelinePipeline.remove(this.decoderName);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected Object newInitialMessage(ChannelHandlerContext channelHandlerContext) throws Exception {
        return socksAuthMethod() == Socks5AuthMethod.PASSWORD ? INIT_REQUEST_PASSWORD : INIT_REQUEST_NO_AUTH;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected boolean handleResponse(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof Socks5InitialResponse) {
            Socks5InitialResponse socks5InitialResponse = (Socks5InitialResponse) obj;
            Socks5AuthMethod socks5AuthMethodSocksAuthMethod = socksAuthMethod();
            if (socks5InitialResponse.authMethod() != Socks5AuthMethod.NO_AUTH && socks5InitialResponse.authMethod() != socks5AuthMethodSocksAuthMethod) {
                throw new ProxyConnectException(exceptionMessage("unexpected authMethod: " + socks5InitialResponse.authMethod()));
            }
            if (socks5AuthMethodSocksAuthMethod == Socks5AuthMethod.NO_AUTH) {
                sendConnectCommand(channelHandlerContext);
            } else if (socks5AuthMethodSocksAuthMethod == Socks5AuthMethod.PASSWORD) {
                ChannelPipeline channelPipelinePipeline = channelHandlerContext.pipeline();
                String str = this.decoderName;
                channelPipelinePipeline.replace(str, str, new Socks5PasswordAuthResponseDecoder());
                String str2 = this.username;
                if (str2 == null) {
                    str2 = "";
                }
                String str3 = this.password;
                sendToProxyServer(new DefaultSocks5PasswordAuthRequest(str2, str3 != null ? str3 : ""));
            } else {
                throw new Error();
            }
            return false;
        }
        if (obj instanceof Socks5PasswordAuthResponse) {
            Socks5PasswordAuthResponse socks5PasswordAuthResponse = (Socks5PasswordAuthResponse) obj;
            if (socks5PasswordAuthResponse.status() != Socks5PasswordAuthStatus.SUCCESS) {
                throw new ProxyConnectException(exceptionMessage("authStatus: " + socks5PasswordAuthResponse.status()));
            }
            sendConnectCommand(channelHandlerContext);
            return false;
        }
        Socks5CommandResponse socks5CommandResponse = (Socks5CommandResponse) obj;
        if (socks5CommandResponse.status() == Socks5CommandStatus.SUCCESS) {
            return true;
        }
        throw new ProxyConnectException(exceptionMessage("status: " + socks5CommandResponse.status()));
    }

    private Socks5AuthMethod socksAuthMethod() {
        if (this.username == null && this.password == null) {
            return Socks5AuthMethod.NO_AUTH;
        }
        return Socks5AuthMethod.PASSWORD;
    }

    private void sendConnectCommand(ChannelHandlerContext channelHandlerContext) throws Exception {
        String hostAddress;
        Socks5AddressType socks5AddressType;
        InetSocketAddress inetSocketAddress = (InetSocketAddress) destinationAddress();
        if (inetSocketAddress.isUnresolved()) {
            socks5AddressType = Socks5AddressType.DOMAIN;
            hostAddress = inetSocketAddress.getHostString();
        } else {
            hostAddress = inetSocketAddress.getAddress().getHostAddress();
            if (NetUtil.isValidIpV4Address(hostAddress)) {
                socks5AddressType = Socks5AddressType.IPv4;
            } else if (NetUtil.isValidIpV6Address(hostAddress)) {
                socks5AddressType = Socks5AddressType.IPv6;
            } else {
                throw new ProxyConnectException(exceptionMessage("unknown address type: " + StringUtil.simpleClassName(hostAddress)));
            }
        }
        ChannelPipeline channelPipelinePipeline = channelHandlerContext.pipeline();
        String str = this.decoderName;
        channelPipelinePipeline.replace(str, str, new Socks5CommandResponseDecoder());
        sendToProxyServer(new DefaultSocks5CommandRequest(Socks5CommandType.CONNECT, socks5AddressType, hostAddress, inetSocketAddress.getPort()));
    }
}
