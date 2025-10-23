package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultRateLimit;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

/* loaded from: classes6.dex */
public interface FaultRateLimitOrBuilder extends MessageOrBuilder {
    FaultRateLimit.FixedLimit getFixedLimit();

    FaultRateLimit.FixedLimitOrBuilder getFixedLimitOrBuilder();

    FaultRateLimit.HeaderLimit getHeaderLimit();

    FaultRateLimit.HeaderLimitOrBuilder getHeaderLimitOrBuilder();

    FaultRateLimit.LimitTypeCase getLimitTypeCase();

    FractionalPercent getPercentage();

    FractionalPercentOrBuilder getPercentageOrBuilder();

    boolean hasFixedLimit();

    boolean hasHeaderLimit();

    boolean hasPercentage();
}
