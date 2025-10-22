package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface ChannelTraceOrBuilder extends MessageOrBuilder {
    Timestamp getCreationTimestamp();

    TimestampOrBuilder getCreationTimestampOrBuilder();

    ChannelTraceEvent getEvents(int i);

    int getEventsCount();

    List<ChannelTraceEvent> getEventsList();

    ChannelTraceEventOrBuilder getEventsOrBuilder(int i);

    List<? extends ChannelTraceEventOrBuilder> getEventsOrBuilderList();

    long getNumEventsLogged();

    boolean hasCreationTimestamp();
}
