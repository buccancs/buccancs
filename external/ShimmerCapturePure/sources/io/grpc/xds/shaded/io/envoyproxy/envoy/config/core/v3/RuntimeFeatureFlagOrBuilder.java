package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes6.dex */
public interface RuntimeFeatureFlagOrBuilder extends MessageOrBuilder {
    BoolValue getDefaultValue();

    BoolValueOrBuilder getDefaultValueOrBuilder();

    String getRuntimeKey();

    ByteString getRuntimeKeyBytes();

    boolean hasDefaultValue();
}
