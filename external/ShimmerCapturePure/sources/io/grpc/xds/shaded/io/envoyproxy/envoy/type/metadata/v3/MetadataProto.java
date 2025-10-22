package io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class MetadataProto {
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKey_PathSegment_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKey_PathSegment_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKey_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKey_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKind_Cluster_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKind_Cluster_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKind_Host_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKind_Host_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKind_Request_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKind_Request_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKind_Route_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKind_Route_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_metadata_v3_MetadataKind_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_metadata_v3_MetadataKind_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%envoy/type/metadata/v3/metadata.proto\u0012\u0016envoy.type.metadata.v3\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"\u0085\u0002\n\u000bMetadataKey\u0012\u0014\n\u0003key\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012G\n\u0004path\u0018\u0002 \u0003(\u000b2/.envoy.type.metadata.v3.MetadataKey.PathSegmentB\búB\u0005\u0092\u0001\u0002\b\u0001\u001al\n\u000bPathSegment\u0012\u0016\n\u0003key\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000:5\u009aÅ\u0088\u001e0\n.envoy.type.metadata.v2.MetadataKey.PathSegmentB\u000e\n\u0007segment\u0012\u0003øB\u0001:)\u009aÅ\u0088\u001e$\n\"envoy.type.metadata.v2.MetadataKey\"³\u0004\n\fMetadataKind\u0012?\n\u0007request\u0018\u0001 \u0001(\u000b2,.envoy.type.metadata.v3.MetadataKind.RequestH\u0000\u0012;\n\u0005route\u0018\u0002 \u0001(\u000b2*.envoy.type.metadata.v3.MetadataKind.RouteH\u0000\u0012?\n\u0007cluster\u0018\u0003 \u0001(\u000b2,.envoy.type.metadata.v3.MetadataKind.ClusterH\u0000\u00129\n\u0004host\u0018\u0004 \u0001(\u000b2).envoy.type.metadata.v3.MetadataKind.HostH\u0000\u001a=\n\u0007Request:2\u009aÅ\u0088\u001e-\n+envoy.type.metadata.v2.MetadataKind.Request\u001a9\n\u0005Route:0\u009aÅ\u0088\u001e+\n)envoy.type.metadata.v2.MetadataKind.Route\u001a=\n\u0007Cluster:2\u009aÅ\u0088\u001e-\n+envoy.type.metadata.v2.MetadataKind.Cluster\u001a7\n\u0004Host:/\u009aÅ\u0088\u001e*\n(envoy.type.metadata.v2.MetadataKind.Host:*\u009aÅ\u0088\u001e%\n#envoy.type.metadata.v2.MetadataKindB\u000b\n\u0004kind\u0012\u0003øB\u0001B?\n$io.envoyproxy.envoy.type.metadata.v3B\rMetadataProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_metadata_v3_MetadataKey_descriptor = descriptor2;
        internal_static_envoy_type_metadata_v3_MetadataKey_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Key", CookieHeaderNames.PATH});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_type_metadata_v3_MetadataKey_PathSegment_descriptor = descriptor3;
        internal_static_envoy_type_metadata_v3_MetadataKey_PathSegment_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Key", "Segment"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_type_metadata_v3_MetadataKind_descriptor = descriptor4;
        internal_static_envoy_type_metadata_v3_MetadataKind_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Request", "Route", "Cluster", "Host", "Kind"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(0);
        internal_static_envoy_type_metadata_v3_MetadataKind_Request_descriptor = descriptor5;
        internal_static_envoy_type_metadata_v3_MetadataKind_Request_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[0]);
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(1);
        internal_static_envoy_type_metadata_v3_MetadataKind_Route_descriptor = descriptor6;
        internal_static_envoy_type_metadata_v3_MetadataKind_Route_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[0]);
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(2);
        internal_static_envoy_type_metadata_v3_MetadataKind_Cluster_descriptor = descriptor7;
        internal_static_envoy_type_metadata_v3_MetadataKind_Cluster_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[0]);
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(3);
        internal_static_envoy_type_metadata_v3_MetadataKind_Host_descriptor = descriptor8;
        internal_static_envoy_type_metadata_v3_MetadataKind_Host_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[0]);
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
        Versioning.getDescriptor();
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
