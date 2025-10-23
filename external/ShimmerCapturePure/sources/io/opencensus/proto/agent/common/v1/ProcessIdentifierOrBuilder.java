package io.opencensus.proto.agent.common.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

/* loaded from: classes4.dex */
public interface ProcessIdentifierOrBuilder extends MessageOrBuilder {
    String getHostName();

    ByteString getHostNameBytes();

    int getPid();

    Timestamp getStartTimestamp();

    TimestampOrBuilder getStartTimestampOrBuilder();

    boolean hasStartTimestamp();
}
