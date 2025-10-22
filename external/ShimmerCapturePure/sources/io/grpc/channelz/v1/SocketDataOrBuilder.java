package io.grpc.channelz.v1;

import com.google.protobuf.Int64Value;
import com.google.protobuf.Int64ValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface SocketDataOrBuilder extends MessageOrBuilder {
    long getKeepAlivesSent();

    Timestamp getLastLocalStreamCreatedTimestamp();

    TimestampOrBuilder getLastLocalStreamCreatedTimestampOrBuilder();

    Timestamp getLastMessageReceivedTimestamp();

    TimestampOrBuilder getLastMessageReceivedTimestampOrBuilder();

    Timestamp getLastMessageSentTimestamp();

    TimestampOrBuilder getLastMessageSentTimestampOrBuilder();

    Timestamp getLastRemoteStreamCreatedTimestamp();

    TimestampOrBuilder getLastRemoteStreamCreatedTimestampOrBuilder();

    Int64Value getLocalFlowControlWindow();

    Int64ValueOrBuilder getLocalFlowControlWindowOrBuilder();

    long getMessagesReceived();

    long getMessagesSent();

    SocketOption getOption(int i);

    int getOptionCount();

    List<SocketOption> getOptionList();

    SocketOptionOrBuilder getOptionOrBuilder(int i);

    List<? extends SocketOptionOrBuilder> getOptionOrBuilderList();

    Int64Value getRemoteFlowControlWindow();

    Int64ValueOrBuilder getRemoteFlowControlWindowOrBuilder();

    long getStreamsFailed();

    long getStreamsStarted();

    long getStreamsSucceeded();

    boolean hasLastLocalStreamCreatedTimestamp();

    boolean hasLastMessageReceivedTimestamp();

    boolean hasLastMessageSentTimestamp();

    boolean hasLastRemoteStreamCreatedTimestamp();

    boolean hasLocalFlowControlWindow();

    boolean hasRemoteFlowControlWindow();
}
