package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

/* loaded from: classes3.dex */
public interface RuntimeFractionalPercentOrBuilder extends MessageOrBuilder {
    FractionalPercent getDefaultValue();

    FractionalPercentOrBuilder getDefaultValueOrBuilder();

    String getRuntimeKey();

    ByteString getRuntimeKeyBytes();

    boolean hasDefaultValue();
}
