package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercentOrBuilder;

/* loaded from: classes4.dex */
public interface HedgePolicyOrBuilder extends MessageOrBuilder {
    FractionalPercent getAdditionalRequestChance();

    FractionalPercentOrBuilder getAdditionalRequestChanceOrBuilder();

    boolean getHedgeOnPerTryTimeout();

    UInt32Value getInitialRequests();

    UInt32ValueOrBuilder getInitialRequestsOrBuilder();

    boolean hasAdditionalRequestChance();

    boolean hasInitialRequests();
}
