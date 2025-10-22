package io.grpc.netty.shaded.io.netty.handler.codec.marshalling;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import org.jboss.marshalling.Marshaller;

/* loaded from: classes3.dex */
public interface MarshallerProvider {
    Marshaller getMarshaller(ChannelHandlerContext channelHandlerContext) throws Exception;
}
