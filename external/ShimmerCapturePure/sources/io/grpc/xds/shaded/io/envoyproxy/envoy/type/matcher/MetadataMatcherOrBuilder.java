package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher;

import java.util.List;

/* loaded from: classes4.dex */
public interface MetadataMatcherOrBuilder extends MessageOrBuilder {
    String getFilter();

    ByteString getFilterBytes();

    MetadataMatcher.PathSegment getPath(int i);

    int getPathCount();

    List<MetadataMatcher.PathSegment> getPathList();

    MetadataMatcher.PathSegmentOrBuilder getPathOrBuilder(int i);

    List<? extends MetadataMatcher.PathSegmentOrBuilder> getPathOrBuilderList();

    ValueMatcher getValue();

    ValueMatcherOrBuilder getValueOrBuilder();

    boolean hasValue();
}
