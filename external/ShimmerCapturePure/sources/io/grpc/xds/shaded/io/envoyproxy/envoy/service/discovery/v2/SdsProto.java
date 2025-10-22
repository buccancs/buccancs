package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import envoy.annotations.Resource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryProto;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class SdsProto {
    static final Descriptors.Descriptor internal_static_envoy_service_discovery_v2_SdsDummy_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_service_discovery_v2_SdsDummy_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$envoy/service/discovery/v2/sds.proto\u0012\u001aenvoy.service.discovery.v2\u001a\u001cenvoy/api/v2/discovery.proto\u001a\u001cgoogle/api/annotations.proto\u001a envoy/annotations/resource.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\"\n\n\bSdsDummy2ì\u0002\n\u0016SecretDiscoveryService\u0012_\n\fDeltaSecrets\u0012#.envoy.api.v2.DeltaDiscoveryRequest\u001a$.envoy.api.v2.DeltaDiscoveryResponse\"\u0000(\u00010\u0001\u0012V\n\rStreamSecrets\u0012\u001e.envoy.api.v2.DiscoveryRequest\u001a\u001f.envoy.api.v2.DiscoveryResponse\"\u0000(\u00010\u0001\u0012w\n\fFetchSecrets\u0012\u001e.envoy.api.v2.DiscoveryRequest\u001a\u001f.envoy.api.v2.DiscoveryResponse\"&\u0082Óä\u0093\u0002\u0017\"\u0015/v2/discovery:secrets\u0082Óä\u0093\u0002\u0003:\u0001*\u001a \u008a¤\u0096ó\u0007\u001a\n\u0018envoy.api.v2.auth.SecretB`\n(io.envoyproxy.envoy.service.discovery.v2B\bSdsProtoP\u0001\u0088\u0001\u0001ò\u0098þ\u008f\u0005\u0019\u0012\u0017envoy.service.secret.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{DiscoveryProto.getDescriptor(), AnnotationsProto.getDescriptor(), Resource.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_service_discovery_v2_SdsDummy_descriptor = descriptor2;
        internal_static_envoy_service_discovery_v2_SdsDummy_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[0]);
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Resource.resource);
        extensionRegistryNewInstance.add(AnnotationsProto.http);
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        DiscoveryProto.getDescriptor();
        AnnotationsProto.getDescriptor();
        Resource.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
    }

    private SdsProto() {
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
