package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.EndpointComponentsProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class EndpointProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_ClusterLoadAssignment_NamedEndpointsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_ClusterLoadAssignment_NamedEndpointsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_ClusterLoadAssignment_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_ClusterLoadAssignment_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001benvoy/api/v2/endpoint.proto\u0012\fenvoy.api.v2\u001a/envoy/api/v2/endpoint/endpoint_components.proto\u001a\u0018envoy/type/percent.proto\u001a\u001cgoogle/api/annotations.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"Ñ\u0005\n\u0015ClusterLoadAssignment\u0012\u001d\n\fcluster_name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012=\n\tendpoints\u0018\u0002 \u0003(\u000b2*.envoy.api.v2.endpoint.LocalityLbEndpoints\u0012P\n\u000fnamed_endpoints\u0018\u0005 \u0003(\u000b27.envoy.api.v2.ClusterLoadAssignment.NamedEndpointsEntry\u0012:\n\u0006policy\u0018\u0004 \u0001(\u000b2*.envoy.api.v2.ClusterLoadAssignment.Policy\u001aó\u0002\n\u0006Policy\u0012O\n\u000edrop_overloads\u0018\u0002 \u0003(\u000b27.envoy.api.v2.ClusterLoadAssignment.Policy.DropOverload\u0012F\n\u0017overprovisioning_factor\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002 \u0000\u0012A\n\u0014endpoint_stale_after\u0018\u0004 \u0001(\u000b2\u0019.google.protobuf.DurationB\búB\u0005ª\u0001\u0002*\u0000\u0012$\n\u0018disable_overprovisioning\u0018\u0005 \u0001(\bB\u0002\u0018\u0001\u001aa\n\fDropOverload\u0012\u0019\n\bcategory\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u00126\n\u000fdrop_percentage\u0018\u0002 \u0001(\u000b2\u001d.envoy.type.FractionalPercentJ\u0004\b\u0001\u0010\u0002\u001aV\n\u0013NamedEndpointsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012.\n\u0005value\u0018\u0002 \u0001(\u000b2\u001f.envoy.api.v2.endpoint.Endpoint:\u00028\u0001BU\n\u001aio.envoyproxy.envoy.api.v2B\rEndpointProtoP\u0001ò\u0098þ\u008f\u0005\u001a\u0012\u0018envoy.config.endpoint.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{EndpointComponentsProto.getDescriptor(), PercentProto.getDescriptor(), AnnotationsProto.getDescriptor(), DurationProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_ClusterLoadAssignment_descriptor = descriptor2;
        internal_static_envoy_api_v2_ClusterLoadAssignment_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ClusterName", "Endpoints", "NamedEndpoints", "Policy"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_descriptor = descriptor3;
        internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"DropOverloads", "OverprovisioningFactor", "EndpointStaleAfter", "DisableOverprovisioning"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor3.getNestedTypes().get(0);
        internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_descriptor = descriptor4;
        internal_static_envoy_api_v2_ClusterLoadAssignment_Policy_DropOverload_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Category", "DropPercentage"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(1);
        internal_static_envoy_api_v2_ClusterLoadAssignment_NamedEndpointsEntry_descriptor = descriptor5;
        internal_static_envoy_api_v2_ClusterLoadAssignment_NamedEndpointsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Key", "Value"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        EndpointComponentsProto.getDescriptor();
        PercentProto.getDescriptor();
        AnnotationsProto.getDescriptor();
        DurationProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private EndpointProto() {
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
