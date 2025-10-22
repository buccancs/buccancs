package io.grpc.netty.shaded.io.netty.handler.codec.protobuf;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.List;

@ChannelHandler.Sharable
/* loaded from: classes3.dex */
public class ProtobufDecoder extends MessageToMessageDecoder<ByteBuf> {
    private static final boolean HAS_PARSER;

    static {
        boolean z = false;
        try {
            MessageLite.class.getDeclaredMethod("getParserForType", new Class[0]);
            z = true;
        } catch (Throwable unused) {
        }
        HAS_PARSER = z;
    }

    private final ExtensionRegistryLite extensionRegistry;
    private final MessageLite prototype;

    public ProtobufDecoder(MessageLite messageLite) {
        this(messageLite, (ExtensionRegistry) null);
    }

    public ProtobufDecoder(MessageLite messageLite, ExtensionRegistry extensionRegistry) {
        this(messageLite, (ExtensionRegistryLite) extensionRegistry);
    }

    public ProtobufDecoder(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        this.prototype = ((MessageLite) ObjectUtil.checkNotNull(messageLite, "prototype")).getDefaultInstanceForType();
        this.extensionRegistry = extensionRegistryLite;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        decode2(channelHandlerContext, byteBuf, (List<Object>) list);
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int iArrayOffset;
        byte[] bytes;
        int i = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            bytes = byteBuf.array();
            iArrayOffset = byteBuf.arrayOffset() + byteBuf.readerIndex();
        } else {
            iArrayOffset = 0;
            bytes = ByteBufUtil.getBytes(byteBuf, byteBuf.readerIndex(), i, false);
        }
        if (this.extensionRegistry == null) {
            if (HAS_PARSER) {
                list.add(this.prototype.getParserForType().parseFrom(bytes, iArrayOffset, i));
                return;
            } else {
                list.add(this.prototype.newBuilderForType().mergeFrom(bytes, iArrayOffset, i).build());
                return;
            }
        }
        if (HAS_PARSER) {
            list.add(this.prototype.getParserForType().parseFrom(bytes, iArrayOffset, i, this.extensionRegistry));
        } else {
            list.add(this.prototype.newBuilderForType().mergeFrom(bytes, iArrayOffset, i, this.extensionRegistry).build());
        }
    }
}
