package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface RuntimeFeatureFlagOrBuilder extends MessageOrBuilder {
    BoolValue getDefaultValue();

    BoolValueOrBuilder getDefaultValueOrBuilder();

    String getRuntimeKey();

    ByteString getRuntimeKeyBytes();

    boolean hasDefaultValue();
}
