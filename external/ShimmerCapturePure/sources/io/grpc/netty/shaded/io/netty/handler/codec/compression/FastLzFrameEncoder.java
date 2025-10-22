package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

/* loaded from: classes3.dex */
public class FastLzFrameEncoder extends MessageToByteEncoder<ByteBuf> {
    private final Checksum checksum;
    private final int level;

    public FastLzFrameEncoder() {
        this(0, null);
    }

    public FastLzFrameEncoder(int i) {
        this(i, null);
    }

    public FastLzFrameEncoder(boolean z) {
        this(0, z ? new Adler32() : null);
    }

    public FastLzFrameEncoder(int i, Checksum checksum) {
        super(false);
        if (i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException(String.format("level: %d (expected: %d or %d or %d)", Integer.valueOf(i), 0, 1, 2));
        }
        this.level = i;
        this.checksum = checksum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e1  */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void encode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r18, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r19, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r20) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.compression.FastLzFrameEncoder.encode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf):void");
    }
}
