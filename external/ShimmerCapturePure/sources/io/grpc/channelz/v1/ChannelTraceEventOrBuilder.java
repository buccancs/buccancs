package io.grpc.channelz.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import io.grpc.channelz.v1.ChannelTraceEvent;

/* loaded from: classes2.dex */
public interface ChannelTraceEventOrBuilder extends MessageOrBuilder {
    ChannelRef getChannelRef();

    ChannelRefOrBuilder getChannelRefOrBuilder();

    ChannelTraceEvent.ChildRefCase getChildRefCase();

    String getDescription();

    ByteString getDescriptionBytes();

    ChannelTraceEvent.Severity getSeverity();

    int getSeverityValue();

    SubchannelRef getSubchannelRef();

    SubchannelRefOrBuilder getSubchannelRefOrBuilder();

    Timestamp getTimestamp();

    TimestampOrBuilder getTimestampOrBuilder();

    boolean hasChannelRef();

    boolean hasSubchannelRef();

    boolean hasTimestamp();
}
