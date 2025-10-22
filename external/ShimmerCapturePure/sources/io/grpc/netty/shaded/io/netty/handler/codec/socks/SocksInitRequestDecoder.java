package io.grpc.netty.shaded.io.netty.handler.codec.socks;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ReplayingDecoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class SocksInitRequestDecoder extends ReplayingDecoder<State> {

    public SocksInitRequestDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        List listEmptyList;
        int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$socks$SocksInitRequestDecoder$State[state().ordinal()];
        if (i == 1) {
            if (byteBuf.readByte() != SocksProtocolVersion.SOCKS5.byteValue()) {
                list.add(SocksCommonUtils.UNKNOWN_SOCKS_REQUEST);
                channelHandlerContext.pipeline().remove(this);
            }
            checkpoint(State.READ_AUTH_SCHEMES);
        } else if (i != 2) {
            throw new Error();
        }
        byte b = byteBuf.readByte();
        if (b > 0) {
            listEmptyList = new ArrayList(b);
            for (int i2 = 0; i2 < b; i2++) {
                listEmptyList.add(SocksAuthScheme.valueOf(byteBuf.readByte()));
            }
        } else {
            listEmptyList = Collections.emptyList();
        }
        list.add(new SocksInitRequest(listEmptyList));
        channelHandlerContext.pipeline().remove(this);
    }

    enum State {
        CHECK_PROTOCOL_VERSION,
        READ_AUTH_SCHEMES
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.socks.SocksInitRequestDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$socks$SocksInitRequestDecoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$socks$SocksInitRequestDecoder$State = iArr;
            try {
                iArr[State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socks$SocksInitRequestDecoder$State[State.READ_AUTH_SCHEMES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
