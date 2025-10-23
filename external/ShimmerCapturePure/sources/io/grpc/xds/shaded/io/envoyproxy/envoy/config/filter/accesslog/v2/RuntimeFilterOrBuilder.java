package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

/* loaded from: classes6.dex */
public interface RuntimeFilterOrBuilder extends MessageOrBuilder {
    FractionalPercent getPercentSampled();

    FractionalPercentOrBuilder getPercentSampledOrBuilder();

    String getRuntimeKey();

    ByteString getRuntimeKeyBytes();

    boolean getUseIndependentRandomness();

    boolean hasPercentSampled();
}
