package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;

import java.util.Map;

/* loaded from: classes6.dex */
public interface MetadataOrBuilder extends MessageOrBuilder {
    boolean containsFilterMetadata(String str);

    @Deprecated
    Map<String, Struct> getFilterMetadata();

    int getFilterMetadataCount();

    Map<String, Struct> getFilterMetadataMap();

    Struct getFilterMetadataOrDefault(String str, Struct struct);

    Struct getFilterMetadataOrThrow(String str);
}
