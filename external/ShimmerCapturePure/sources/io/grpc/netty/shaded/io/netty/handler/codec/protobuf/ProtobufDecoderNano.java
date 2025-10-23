package io.grpc.netty.shaded.io.netty.handler.codec.protobuf;

import com.google.protobuf.nano.MessageNano;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.List;

@ChannelHandler.Sharable
/* loaded from: classes3.dex */
public class ProtobufDecoderNano extends MessageToMessageDecoder<ByteBuf> {
    private final Class<? extends MessageNano> clazz;

    public ProtobufDecoderNano(Class<? extends MessageNano> cls) {
        this.clazz = (Class) ObjectUtil.checkNotNull(cls, "You must provide a Class");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        decode2(channelHandlerContext, byteBuf, (List<Object>) list);
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] bytes;
        int iArrayOffset;
        int i = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            bytes = byteBuf.array();
            iArrayOffset = byteBuf.arrayOffset() + byteBuf.readerIndex();
        } else {
            bytes = ByteBufUtil.getBytes(byteBuf, byteBuf.readerIndex(), i, false);
            iArrayOffset = 0;
        }
        list.add(MessageNano.mergeFrom(this.clazz.getConstructor(new Class[0]).newInstance(new Object[0]), bytes, iArrayOffset, i));
    }
}
