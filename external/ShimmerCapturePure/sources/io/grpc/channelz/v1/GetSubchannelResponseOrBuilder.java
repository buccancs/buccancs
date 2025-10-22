package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface GetSubchannelResponseOrBuilder extends MessageOrBuilder {
    Subchannel getSubchannel();

    SubchannelOrBuilder getSubchannelOrBuilder();

    boolean hasSubchannel();
}
