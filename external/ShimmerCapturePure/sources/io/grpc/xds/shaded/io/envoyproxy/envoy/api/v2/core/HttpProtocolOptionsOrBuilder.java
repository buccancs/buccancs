package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HttpProtocolOptions;

/* loaded from: classes3.dex */
public interface HttpProtocolOptionsOrBuilder extends MessageOrBuilder {
    HttpProtocolOptions.HeadersWithUnderscoresAction getHeadersWithUnderscoresAction();

    int getHeadersWithUnderscoresActionValue();

    Duration getIdleTimeout();

    DurationOrBuilder getIdleTimeoutOrBuilder();

    Duration getMaxConnectionDuration();

    DurationOrBuilder getMaxConnectionDurationOrBuilder();

    UInt32Value getMaxHeadersCount();

    UInt32ValueOrBuilder getMaxHeadersCountOrBuilder();

    Duration getMaxStreamDuration();

    DurationOrBuilder getMaxStreamDurationOrBuilder();

    boolean hasIdleTimeout();

    boolean hasMaxConnectionDuration();

    boolean hasMaxHeadersCount();

    boolean hasMaxStreamDuration();
}
