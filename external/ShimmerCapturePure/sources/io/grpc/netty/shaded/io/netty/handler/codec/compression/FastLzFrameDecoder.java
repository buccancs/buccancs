package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

/* loaded from: classes3.dex */
public class FastLzFrameDecoder extends ByteToMessageDecoder {
    private final Checksum checksum;
    private int chunkLength;
    private int currentChecksum;
    private State currentState;
    private boolean hasChecksum;
    private boolean isCompressed;
    private int originalLength;

    public FastLzFrameDecoder() {
        this(false);
    }

    public FastLzFrameDecoder(boolean z) {
        this(z ? new Adler32() : null);
    }

    public FastLzFrameDecoder(Checksum checksum) {
        this.currentState = State.INIT_BLOCK;
        this.checksum = checksum;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0095 A[Catch: Exception -> 0x015b, TryCatch #0 {Exception -> 0x015b, blocks: (B:3:0x0004, B:9:0x001b, B:10:0x0024, B:11:0x0029, B:44:0x008b, B:47:0x0095, B:49:0x009d, B:78:0x014f, B:79:0x0152, B:50:0x00b6, B:26:0x0056, B:30:0x0061, B:34:0x0068, B:38:0x006f, B:40:0x0075, B:42:0x0081, B:43:0x0085, B:12:0x002a, B:15:0x0032, B:17:0x003b, B:21:0x0046, B:25:0x0050, B:80:0x0153, B:81:0x015a, B:52:0x00bc, B:54:0x00c0, B:56:0x00c6, B:58:0x00d6, B:64:0x0101, B:67:0x0109, B:70:0x0119, B:71:0x0134, B:73:0x0137, B:74:0x0144, B:61:0x00e3, B:62:0x00fc, B:57:0x00d0, B:63:0x00fd), top: B:85:0x0004, inners: #1 }] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r17, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r18, java.util.List<java.lang.Object> r19) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.compression.FastLzFrameDecoder.decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.util.List):void");
    }

    private enum State {
        INIT_BLOCK,
        INIT_BLOCK_PARAMS,
        DECOMPRESS_DATA,
        CORRUPTED
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.FastLzFrameDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State = iArr;
            try {
                iArr[State.INIT_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State[State.INIT_BLOCK_PARAMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State[State.DECOMPRESS_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State[State.CORRUPTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
