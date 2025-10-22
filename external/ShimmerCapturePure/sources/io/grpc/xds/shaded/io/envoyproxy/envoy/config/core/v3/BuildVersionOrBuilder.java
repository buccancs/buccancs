package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.SemanticVersion;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.SemanticVersionOrBuilder;

/* loaded from: classes6.dex */
public interface BuildVersionOrBuilder extends MessageOrBuilder {
    Struct getMetadata();

    StructOrBuilder getMetadataOrBuilder();

    SemanticVersion getVersion();

    SemanticVersionOrBuilder getVersionOrBuilder();

    boolean hasMetadata();

    boolean hasVersion();
}
