package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.DoubleValue;
import com.google.protobuf.DoubleValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes6.dex */
public interface RateLimitSettingsOrBuilder extends MessageOrBuilder {
    DoubleValue getFillRate();

    DoubleValueOrBuilder getFillRateOrBuilder();

    UInt32Value getMaxTokens();

    UInt32ValueOrBuilder getMaxTokensOrBuilder();

    boolean hasFillRate();

    boolean hasMaxTokens();
}
