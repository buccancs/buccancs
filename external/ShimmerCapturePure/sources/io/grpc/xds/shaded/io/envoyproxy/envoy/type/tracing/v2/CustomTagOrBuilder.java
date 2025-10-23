package io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2.CustomTag;

/* loaded from: classes4.dex */
public interface CustomTagOrBuilder extends MessageOrBuilder {
    CustomTag.Environment getEnvironment();

    CustomTag.EnvironmentOrBuilder getEnvironmentOrBuilder();

    CustomTag.Literal getLiteral();

    CustomTag.LiteralOrBuilder getLiteralOrBuilder();

    CustomTag.Metadata getMetadata();

    CustomTag.MetadataOrBuilder getMetadataOrBuilder();

    CustomTag.Header getRequestHeader();

    CustomTag.HeaderOrBuilder getRequestHeaderOrBuilder();

    String getTag();

    ByteString getTagBytes();

    CustomTag.TypeCase getTypeCase();

    boolean hasEnvironment();

    boolean hasLiteral();

    boolean hasMetadata();

    boolean hasRequestHeader();
}
