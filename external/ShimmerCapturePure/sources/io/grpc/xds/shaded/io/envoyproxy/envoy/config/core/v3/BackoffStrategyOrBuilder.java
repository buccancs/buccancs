package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes6.dex */
public interface BackoffStrategyOrBuilder extends MessageOrBuilder {
    Duration getBaseInterval();

    DurationOrBuilder getBaseIntervalOrBuilder();

    Duration getMaxInterval();

    DurationOrBuilder getMaxIntervalOrBuilder();

    boolean hasBaseInterval();

    boolean hasMaxInterval();
}
