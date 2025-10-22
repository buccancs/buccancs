package io.grpc.netty.shaded.io.netty.channel.group;

import io.grpc.netty.shaded.io.netty.channel.Channel;

/* loaded from: classes3.dex */
public interface ChannelMatcher {
    boolean matches(Channel channel);
}
