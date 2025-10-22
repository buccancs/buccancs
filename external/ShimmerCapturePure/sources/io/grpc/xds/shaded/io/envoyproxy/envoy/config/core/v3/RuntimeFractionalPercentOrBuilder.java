package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercentOrBuilder;

/* loaded from: classes6.dex */
public interface RuntimeFractionalPercentOrBuilder extends MessageOrBuilder {
    FractionalPercent getDefaultValue();

    FractionalPercentOrBuilder getDefaultValueOrBuilder();

    String getRuntimeKey();

    ByteString getRuntimeKeyBytes();

    boolean hasDefaultValue();
}
