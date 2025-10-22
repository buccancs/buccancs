package io.grpc.netty.shaded.io.netty.handler.codec.string;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

@ChannelHandler.Sharable
/* loaded from: classes3.dex */
public class StringEncoder extends MessageToMessageEncoder<CharSequence> {
    private final Charset charset;

    public StringEncoder() {
        this(Charset.defaultCharset());
    }

    public StringEncoder(Charset charset) {
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    protected /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, CharSequence charSequence, List list) throws Exception {
        encode2(channelHandlerContext, charSequence, (List<Object>) list);
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, CharSequence charSequence, List<Object> list) throws Exception {
        if (charSequence.length() == 0) {
            return;
        }
        list.add(ByteBufUtil.encodeString(channelHandlerContext.alloc(), CharBuffer.wrap(charSequence), this.charset));
    }
}
