package io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.ReplayingDecoder;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;

import java.util.List;

/* loaded from: classes3.dex */
public class Socks4ServerDecoder extends ReplayingDecoder<State> {
    private static final int MAX_FIELD_LENGTH = 255;
    private String dstAddr;
    private int dstPort;
    private Socks4CommandType type;
    private String userId;

    public Socks4ServerDecoder() {
        super(State.START);
        setSingleDecode(true);
    }

    private static String readString(String str, ByteBuf byteBuf) {
        int iBytesBefore = byteBuf.bytesBefore(256, (byte) 0);
        if (iBytesBefore < 0) {
            throw new DecoderException("field '" + str + "' longer than 255 chars");
        }
        String string = byteBuf.readSlice(iBytesBefore).toString(CharsetUtil.US_ASCII);
        byteBuf.skipBytes(1);
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0099 A[Catch: Exception -> 0x00b3, TryCatch #0 {Exception -> 0x00b3, blocks: (B:3:0x0002, B:14:0x0021, B:25:0x0093, B:27:0x0099, B:19:0x0062, B:21:0x006c, B:23:0x0076, B:24:0x007e, B:18:0x0055, B:15:0x002a, B:17:0x0036, B:28:0x00a1, B:29:0x00b2), top: B:33:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r5, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r6, java.util.List<java.lang.Object> r7) throws java.lang.Exception {
        /*
            r4 = this;
            java.lang.String r5 = "unsupported protocol version: "
            int[] r0 = io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State     // Catch: java.lang.Exception -> Lb3
            java.lang.Object r1 = r4.state()     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder$State r1 = (io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder.State) r1     // Catch: java.lang.Exception -> Lb3
            int r1 = r1.ordinal()     // Catch: java.lang.Exception -> Lb3
            r0 = r0[r1]     // Catch: java.lang.Exception -> Lb3
            r1 = 1
            if (r0 == r1) goto L2a
            r5 = 2
            if (r0 == r5) goto L55
            r5 = 3
            if (r0 == r5) goto L62
            r5 = 4
            if (r0 == r5) goto L93
            r5 = 5
            if (r0 == r5) goto L21
            goto Lb7
        L21:
            int r5 = r4.actualReadableBytes()     // Catch: java.lang.Exception -> Lb3
            r6.skipBytes(r5)     // Catch: java.lang.Exception -> Lb3
            goto Lb7
        L2a:
            short r0 = r6.readUnsignedByte()     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksVersion r1 = io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksVersion.SOCKS4a     // Catch: java.lang.Exception -> Lb3
            byte r1 = r1.byteValue()     // Catch: java.lang.Exception -> Lb3
            if (r0 != r1) goto La1
            byte r5 = r6.readByte()     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4CommandType r5 = io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4CommandType.valueOf(r5)     // Catch: java.lang.Exception -> Lb3
            r4.type = r5     // Catch: java.lang.Exception -> Lb3
            int r5 = r6.readUnsignedShort()     // Catch: java.lang.Exception -> Lb3
            r4.dstPort = r5     // Catch: java.lang.Exception -> Lb3
            int r5 = r6.readInt()     // Catch: java.lang.Exception -> Lb3
            java.lang.String r5 = io.grpc.netty.shaded.io.netty.util.NetUtil.intToIpAddress(r5)     // Catch: java.lang.Exception -> Lb3
            r4.dstAddr = r5     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder$State r5 = io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder.State.READ_USERID     // Catch: java.lang.Exception -> Lb3
            r4.checkpoint(r5)     // Catch: java.lang.Exception -> Lb3
        L55:
            java.lang.String r5 = "userid"
            java.lang.String r5 = readString(r5, r6)     // Catch: java.lang.Exception -> Lb3
            r4.userId = r5     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder$State r5 = io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder.State.READ_DOMAIN     // Catch: java.lang.Exception -> Lb3
            r4.checkpoint(r5)     // Catch: java.lang.Exception -> Lb3
        L62:
            java.lang.String r5 = "0.0.0.0"
            java.lang.String r0 = r4.dstAddr     // Catch: java.lang.Exception -> Lb3
            boolean r5 = r5.equals(r0)     // Catch: java.lang.Exception -> Lb3
            if (r5 != 0) goto L7e
            java.lang.String r5 = r4.dstAddr     // Catch: java.lang.Exception -> Lb3
            java.lang.String r0 = "0.0.0."
            boolean r5 = r5.startsWith(r0)     // Catch: java.lang.Exception -> Lb3
            if (r5 == 0) goto L7e
            java.lang.String r5 = "dstAddr"
            java.lang.String r5 = readString(r5, r6)     // Catch: java.lang.Exception -> Lb3
            r4.dstAddr = r5     // Catch: java.lang.Exception -> Lb3
        L7e:
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.DefaultSocks4CommandRequest r5 = new io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.DefaultSocks4CommandRequest     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4CommandType r0 = r4.type     // Catch: java.lang.Exception -> Lb3
            java.lang.String r1 = r4.dstAddr     // Catch: java.lang.Exception -> Lb3
            int r2 = r4.dstPort     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = r4.userId     // Catch: java.lang.Exception -> Lb3
            r5.<init>(r0, r1, r2, r3)     // Catch: java.lang.Exception -> Lb3
            r7.add(r5)     // Catch: java.lang.Exception -> Lb3
            io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder$State r5 = io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder.State.SUCCESS     // Catch: java.lang.Exception -> Lb3
            r4.checkpoint(r5)     // Catch: java.lang.Exception -> Lb3
        L93:
            int r5 = r4.actualReadableBytes()     // Catch: java.lang.Exception -> Lb3
            if (r5 <= 0) goto Lb7
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r5 = r6.readRetainedSlice(r5)     // Catch: java.lang.Exception -> Lb3
            r7.add(r5)     // Catch: java.lang.Exception -> Lb3
            goto Lb7
        La1:
            io.grpc.netty.shaded.io.netty.handler.codec.DecoderException r6 = new io.grpc.netty.shaded.io.netty.handler.codec.DecoderException     // Catch: java.lang.Exception -> Lb3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb3
            r1.<init>(r5)     // Catch: java.lang.Exception -> Lb3
            r1.append(r0)     // Catch: java.lang.Exception -> Lb3
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Exception -> Lb3
            r6.<init>(r5)     // Catch: java.lang.Exception -> Lb3
            throw r6     // Catch: java.lang.Exception -> Lb3
        Lb3:
            r5 = move-exception
            r4.fail(r7, r5)
        Lb7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder.decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.util.List):void");
    }

    private void fail(List<Object> list, Exception exc) {
        if (!(exc instanceof DecoderException)) {
            exc = new DecoderException(exc);
        }
        Socks4CommandType socks4CommandType = this.type;
        if (socks4CommandType == null) {
            socks4CommandType = Socks4CommandType.CONNECT;
        }
        String str = this.dstAddr;
        if (str == null) {
            str = "";
        }
        int i = this.dstPort;
        if (i == 0) {
            i = 65535;
        }
        String str2 = this.userId;
        DefaultSocks4CommandRequest defaultSocks4CommandRequest = new DefaultSocks4CommandRequest(socks4CommandType, str, i, str2 != null ? str2 : "");
        defaultSocks4CommandRequest.setDecoderResult(DecoderResult.failure(exc));
        list.add(defaultSocks4CommandRequest);
        checkpoint(State.FAILURE);
    }

    enum State {
        START,
        READ_USERID,
        READ_DOMAIN,
        SUCCESS,
        FAILURE
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ServerDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State = iArr;
            try {
                iArr[State.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State[State.READ_USERID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State[State.READ_DOMAIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State[State.SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State[State.FAILURE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
