package io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataKey;

import java.util.List;

/* loaded from: classes4.dex */
public interface MetadataKeyOrBuilder extends MessageOrBuilder {
    String getKey();

    ByteString getKeyBytes();

    MetadataKey.PathSegment getPath(int i);

    int getPathCount();

    List<MetadataKey.PathSegment> getPathList();

    MetadataKey.PathSegmentOrBuilder getPathOrBuilder(int i);

    List<? extends MetadataKey.PathSegmentOrBuilder> getPathOrBuilderList();
}
