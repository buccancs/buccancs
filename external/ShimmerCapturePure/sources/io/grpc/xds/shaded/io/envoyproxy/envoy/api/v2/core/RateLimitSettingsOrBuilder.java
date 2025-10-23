package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.DoubleValue;
import com.google.protobuf.DoubleValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes3.dex */
public interface RateLimitSettingsOrBuilder extends MessageOrBuilder {
    DoubleValue getFillRate();

    DoubleValueOrBuilder getFillRateOrBuilder();

    UInt32Value getMaxTokens();

    UInt32ValueOrBuilder getMaxTokensOrBuilder();

    boolean hasFillRate();

    boolean hasMaxTokens();
}
