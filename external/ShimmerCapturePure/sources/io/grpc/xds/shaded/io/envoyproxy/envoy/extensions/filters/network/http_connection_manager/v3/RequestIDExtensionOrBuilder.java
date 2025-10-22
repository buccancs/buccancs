package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface RequestIDExtensionOrBuilder extends MessageOrBuilder {
    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    boolean hasTypedConfig();
}
