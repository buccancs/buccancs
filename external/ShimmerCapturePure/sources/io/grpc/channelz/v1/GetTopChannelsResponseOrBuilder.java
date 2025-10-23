package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface GetTopChannelsResponseOrBuilder extends MessageOrBuilder {
    Channel getChannel(int i);

    int getChannelCount();

    List<Channel> getChannelList();

    ChannelOrBuilder getChannelOrBuilder(int i);

    List<? extends ChannelOrBuilder> getChannelOrBuilderList();

    boolean getEnd();
}
