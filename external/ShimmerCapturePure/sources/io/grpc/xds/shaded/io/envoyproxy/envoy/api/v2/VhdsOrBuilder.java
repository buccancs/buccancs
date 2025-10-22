package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder;

/* loaded from: classes3.dex */
public interface VhdsOrBuilder extends MessageOrBuilder {
    ConfigSource getConfigSource();

    ConfigSourceOrBuilder getConfigSourceOrBuilder();

    boolean hasConfigSource();
}
