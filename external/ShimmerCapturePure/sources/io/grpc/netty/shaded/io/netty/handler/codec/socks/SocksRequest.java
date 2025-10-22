package io.grpc.netty.shaded.io.netty.handler.codec.socks;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public abstract class SocksRequest extends SocksMessage {
    private final SocksRequestType requestType;

    protected SocksRequest(SocksRequestType socksRequestType) {
        super(SocksMessageType.REQUEST);
        this.requestType = (SocksRequestType) ObjectUtil.checkNotNull(socksRequestType, "requestType");
    }

    public SocksRequestType requestType() {
        return this.requestType;
    }
}
