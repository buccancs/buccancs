package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

/* loaded from: classes5.dex */
public interface HedgePolicyOrBuilder extends MessageOrBuilder {
    FractionalPercent getAdditionalRequestChance();

    FractionalPercentOrBuilder getAdditionalRequestChanceOrBuilder();

    boolean getHedgeOnPerTryTimeout();

    UInt32Value getInitialRequests();

    UInt32ValueOrBuilder getInitialRequestsOrBuilder();

    boolean hasAdditionalRequestChance();

    boolean hasInitialRequests();
}
