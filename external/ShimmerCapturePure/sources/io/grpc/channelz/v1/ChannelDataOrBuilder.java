package io.grpc.channelz.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

/* loaded from: classes2.dex */
public interface ChannelDataOrBuilder extends MessageOrBuilder {
    long getCallsFailed();

    long getCallsStarted();

    long getCallsSucceeded();

    Timestamp getLastCallStartedTimestamp();

    TimestampOrBuilder getLastCallStartedTimestampOrBuilder();

    ChannelConnectivityState getState();

    ChannelConnectivityStateOrBuilder getStateOrBuilder();

    String getTarget();

    ByteString getTargetBytes();

    ChannelTrace getTrace();

    ChannelTraceOrBuilder getTraceOrBuilder();

    boolean hasLastCallStartedTimestamp();

    boolean hasState();

    boolean hasTrace();
}
