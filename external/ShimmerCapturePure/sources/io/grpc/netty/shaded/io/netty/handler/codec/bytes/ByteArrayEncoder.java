package io.grpc.netty.shaded.io.netty.handler.codec.bytes;

import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

@ChannelHandler.Sharable
/* loaded from: classes3.dex */
public class ByteArrayEncoder extends MessageToMessageEncoder<byte[]> {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    protected /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, byte[] bArr, List list) throws Exception {
        encode2(channelHandlerContext, bArr, (List<Object>) list);
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, byte[] bArr, List<Object> list) throws Exception {
        list.add(Unpooled.wrappedBuffer(bArr));
    }
}
