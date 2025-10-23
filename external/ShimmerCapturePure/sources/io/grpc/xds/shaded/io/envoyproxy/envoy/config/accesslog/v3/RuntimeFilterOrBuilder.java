package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercentOrBuilder;

/* loaded from: classes2.dex */
public interface RuntimeFilterOrBuilder extends MessageOrBuilder {
    FractionalPercent getPercentSampled();

    FractionalPercentOrBuilder getPercentSampledOrBuilder();

    String getRuntimeKey();

    ByteString getRuntimeKeyBytes();

    boolean getUseIndependentRandomness();

    boolean hasPercentSampled();
}
