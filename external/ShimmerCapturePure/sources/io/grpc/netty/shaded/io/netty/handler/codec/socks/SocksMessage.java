package io.grpc.netty.shaded.io.netty.handler.codec.socks;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public abstract class SocksMessage {
    private final SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;
    private final SocksMessageType type;

    protected SocksMessage(SocksMessageType socksMessageType) {
        this.type = (SocksMessageType) ObjectUtil.checkNotNull(socksMessageType, "type");
    }

    @Deprecated
    public abstract void encodeAsByteBuf(ByteBuf byteBuf);

    public SocksProtocolVersion protocolVersion() {
        return this.protocolVersion;
    }

    public SocksMessageType type() {
        return this.type;
    }
}
