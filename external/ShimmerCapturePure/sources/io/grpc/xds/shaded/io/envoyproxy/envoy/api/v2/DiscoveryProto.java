package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.rpc.StatusProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BaseProto;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class DiscoveryProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_DeltaDiscoveryRequest_InitialResourceVersionsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_DeltaDiscoveryRequest_InitialResourceVersionsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_DeltaDiscoveryRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_DeltaDiscoveryRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_DeltaDiscoveryResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_DeltaDiscoveryResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_DiscoveryRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_DiscoveryRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_DiscoveryResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_DiscoveryResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_Resource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_Resource_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cenvoy/api/v2/discovery.proto\u0012\fenvoy.api.v2\u001a\u001cenvoy/api/v2/core/base.proto\u001a\u0019google/protobuf/any.proto\u001a\u0017google/rpc/status.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\"»\u0001\n\u0010DiscoveryRequest\u0012\u0014\n\fversion_info\u0018\u0001 \u0001(\t\u0012%\n\u0004node\u0018\u0002 \u0001(\u000b2\u0017.envoy.api.v2.core.Node\u0012\u0016\n\u000eresource_names\u0018\u0003 \u0003(\t\u0012\u0010\n\btype_url\u0018\u0004 \u0001(\t\u0012\u0016\n\u000eresponse_nonce\u0018\u0005 \u0001(\t\u0012(\n\ferror_detail\u0018\u0006 \u0001(\u000b2\u0012.google.rpc.Status\"»\u0001\n\u0011DiscoveryResponse\u0012\u0014\n\fversion_info\u0018\u0001 \u0001(\t\u0012'\n\tresources\u0018\u0002 \u0003(\u000b2\u0014.google.protobuf.Any\u0012\u000e\n\u0006canary\u0018\u0003 \u0001(\b\u0012\u0010\n\btype_url\u0018\u0004 \u0001(\t\u0012\r\n\u0005nonce\u0018\u0005 \u0001(\t\u00126\n\rcontrol_plane\u0018\u0006 \u0001(\u000b2\u001f.envoy.api.v2.core.ControlPlane\"ý\u0002\n\u0015DeltaDiscoveryRequest\u0012%\n\u0004node\u0018\u0001 \u0001(\u000b2\u0017.envoy.api.v2.core.Node\u0012\u0010\n\btype_url\u0018\u0002 \u0001(\t\u0012 \n\u0018resource_names_subscribe\u0018\u0003 \u0003(\t\u0012\"\n\u001aresource_names_unsubscribe\u0018\u0004 \u0003(\t\u0012c\n\u0019initial_resource_versions\u0018\u0005 \u0003(\u000b2@.envoy.api.v2.DeltaDiscoveryRequest.InitialResourceVersionsEntry\u0012\u0016\n\u000eresponse_nonce\u0018\u0006 \u0001(\t\u0012(\n\ferror_detail\u0018\u0007 \u0001(\u000b2\u0012.google.rpc.Status\u001a>\n\u001cInitialResourceVersionsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"\u009c\u0001\n\u0016DeltaDiscoveryResponse\u0012\u001b\n\u0013system_version_info\u0018\u0001 \u0001(\t\u0012)\n\tresources\u0018\u0002 \u0003(\u000b2\u0016.envoy.api.v2.Resource\u0012\u0010\n\btype_url\u0018\u0004 \u0001(\t\u0012\u0019\n\u0011removed_resources\u0018\u0006 \u0003(\t\u0012\r\n\u0005nonce\u0018\u0005 \u0001(\t\"b\n\bResource\u0012\f\n\u0004name\u0018\u0003 \u0001(\t\u0012\u000f\n\u0007aliases\u0018\u0004 \u0003(\t\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\t\u0012&\n\bresource\u0018\u0002 \u0001(\u000b2\u0014.google.protobuf.AnyBX\n\u001aio.envoyproxy.envoy.api.v2B\u000eDiscoveryProtoP\u0001ò\u0098þ\u008f\u0005\u001c\u0012\u001aenvoy.service.discovery.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), AnyProto.getDescriptor(), StatusProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_DiscoveryRequest_descriptor = descriptor2;
        internal_static_envoy_api_v2_DiscoveryRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"VersionInfo", "Node", "ResourceNames", "TypeUrl", "ResponseNonce", "ErrorDetail"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_DiscoveryResponse_descriptor = descriptor3;
        internal_static_envoy_api_v2_DiscoveryResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"VersionInfo", "Resources", "Canary", "TypeUrl", "Nonce", "ControlPlane"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_api_v2_DeltaDiscoveryRequest_descriptor = descriptor4;
        internal_static_envoy_api_v2_DeltaDiscoveryRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Node", "TypeUrl", "ResourceNamesSubscribe", "ResourceNamesUnsubscribe", "InitialResourceVersions", "ResponseNonce", "ErrorDetail"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(0);
        internal_static_envoy_api_v2_DeltaDiscoveryRequest_InitialResourceVersionsEntry_descriptor = descriptor5;
        internal_static_envoy_api_v2_DeltaDiscoveryRequest_InitialResourceVersionsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_api_v2_DeltaDiscoveryResponse_descriptor = descriptor6;
        internal_static_envoy_api_v2_DeltaDiscoveryResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"SystemVersionInfo", "Resources", "TypeUrl", "RemovedResources", "Nonce"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_api_v2_Resource_descriptor = descriptor7;
        internal_static_envoy_api_v2_Resource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Name", "Aliases", "Version", "Resource"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        AnyProto.getDescriptor();
        StatusProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
    }

    private DiscoveryProto() {
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
