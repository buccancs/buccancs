package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.SemanticVersion;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.SemanticVersionOrBuilder;

/* loaded from: classes3.dex */
public interface BuildVersionOrBuilder extends MessageOrBuilder {
    Struct getMetadata();

    StructOrBuilder getMetadataOrBuilder();

    SemanticVersion getVersion();

    SemanticVersionOrBuilder getVersionOrBuilder();

    boolean hasMetadata();

    boolean hasVersion();
}
