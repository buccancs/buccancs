package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class MetadataProto {
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_MetadataMatcher_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_MetadataMatcher_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!envoy/type/matcher/metadata.proto\u0012\u0012envoy.type.matcher\u001a\u001eenvoy/type/matcher/value.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"å\u0001\n\u000fMetadataMatcher\u0012\u0017\n\u0006filter\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012G\n\u0004path\u0018\u0002 \u0003(\u000b2/.envoy.type.matcher.MetadataMatcher.PathSegmentB\búB\u0005\u0092\u0001\u0002\b\u0001\u00129\n\u0005value\u0018\u0003 \u0001(\u000b2 .envoy.type.matcher.ValueMatcherB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u001a5\n\u000bPathSegment\u0012\u0016\n\u0003key\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000B\u000e\n\u0007segment\u0012\u0003øB\u0001B;\n io.envoyproxy.envoy.type.matcherB\rMetadataProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{ValueProto.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_matcher_MetadataMatcher_descriptor = descriptor2;
        internal_static_envoy_type_matcher_MetadataMatcher_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Filter", CookieHeaderNames.PATH, "Value"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_descriptor = descriptor3;
        internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Key", "Segment"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        ValueProto.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private MetadataProto() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }
}
