package io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.ReplayingDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksVersion;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.List;

/* loaded from: classes3.dex */
public class Socks5CommandResponseDecoder extends ReplayingDecoder<State> {
    private final Socks5AddressDecoder addressDecoder;

    public Socks5CommandResponseDecoder() {
        this(Socks5AddressDecoder.DEFAULT);
    }

    public Socks5CommandResponseDecoder(Socks5AddressDecoder socks5AddressDecoder) {
        super(State.INIT);
        this.addressDecoder = (Socks5AddressDecoder) ObjectUtil.checkNotNull(socks5AddressDecoder, "addressDecoder");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        try {
            int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$socksx$v5$Socks5CommandResponseDecoder$State[state().ordinal()];
            if (i == 1) {
                byte b = byteBuf.readByte();
                if (b != SocksVersion.SOCKS5.byteValue()) {
                    throw new DecoderException("unsupported version: " + ((int) b) + " (expected: " + ((int) SocksVersion.SOCKS5.byteValue()) + ')');
                }
                Socks5CommandStatus socks5CommandStatusValueOf = Socks5CommandStatus.valueOf(byteBuf.readByte());
                byteBuf.skipBytes(1);
                Socks5AddressType socks5AddressTypeValueOf = Socks5AddressType.valueOf(byteBuf.readByte());
                list.add(new DefaultSocks5CommandResponse(socks5CommandStatusValueOf, socks5AddressTypeValueOf, this.addressDecoder.decodeAddress(socks5AddressTypeValueOf, byteBuf), byteBuf.readUnsignedShort()));
                checkpoint(State.SUCCESS);
            } else if (i != 2) {
                if (i != 3) {
                    return;
                }
                byteBuf.skipBytes(actualReadableBytes());
                return;
            }
            int iActualReadableBytes = actualReadableBytes();
            if (iActualReadableBytes > 0) {
                list.add(byteBuf.readRetainedSlice(iActualReadableBytes));
            }
        } catch (Exception e) {
            fail(list, e);
        }
    }

    private void fail(List<Object> list, Exception exc) {
        if (!(exc instanceof DecoderException)) {
            exc = new DecoderException(exc);
        }
        checkpoint(State.FAILURE);
        DefaultSocks5CommandResponse defaultSocks5CommandResponse = new DefaultSocks5CommandResponse(Socks5CommandStatus.FAILURE, Socks5AddressType.IPv4, null, 0);
        defaultSocks5CommandResponse.setDecoderResult(DecoderResult.failure(exc));
        list.add(defaultSocks5CommandResponse);
    }

    enum State {
        INIT,
        SUCCESS,
        FAILURE
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5CommandResponseDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$socksx$v5$Socks5CommandResponseDecoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$socksx$v5$Socks5CommandResponseDecoder$State = iArr;
            try {
                iArr[State.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$v5$Socks5CommandResponseDecoder$State[State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$v5$Socks5CommandResponseDecoder$State[State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
