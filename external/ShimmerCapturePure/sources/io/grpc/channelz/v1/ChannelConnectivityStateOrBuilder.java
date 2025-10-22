package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.channelz.v1.ChannelConnectivityState;

/* loaded from: classes2.dex */
public interface ChannelConnectivityStateOrBuilder extends MessageOrBuilder {
    ChannelConnectivityState.State getState();

    int getStateValue();
}
