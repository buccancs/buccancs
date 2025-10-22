package io.grpc.channelz.v1;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface SocketOptionTimeoutOrBuilder extends MessageOrBuilder {
    Duration getDuration();

    DurationOrBuilder getDurationOrBuilder();

    boolean hasDuration();
}
