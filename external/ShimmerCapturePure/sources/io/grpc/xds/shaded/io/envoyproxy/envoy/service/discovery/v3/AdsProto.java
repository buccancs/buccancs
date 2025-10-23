package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class AdsProto {
    static final Descriptors.Descriptor internal_static_envoy_service_discovery_v3_AdsDummy_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_service_discovery_v3_AdsDummy_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$envoy/service/discovery/v3/ads.proto\u0012\u001aenvoy.service.discovery.v3\u001a*envoy/service/discovery/v3/discovery.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\"6\n\bAdsDummy:*\u009aÅ\u0088\u001e%\n#envoy.service.discovery.v2.AdsDummy2¦\u0002\n\u001aAggregatedDiscoveryService\u0012~\n\u0019StreamAggregatedResources\u0012,.envoy.service.discovery.v3.DiscoveryRequest\u001a-.envoy.service.discovery.v3.DiscoveryResponse\"\u0000(\u00010\u0001\u0012\u0087\u0001\n\u0018DeltaAggregatedResources\u00121.envoy.service.discovery.v3.DeltaDiscoveryRequest\u001a2.envoy.service.discovery.v3.DeltaDiscoveryResponse\"\u0000(\u00010\u0001BA\n(io.envoyproxy.envoy.service.discovery.v3B\bAdsProtoP\u0001\u0088\u0001\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{DiscoveryProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_service_discovery_v3_AdsDummy_descriptor = descriptor2;
        internal_static_envoy_service_discovery_v3_AdsDummy_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[0]);
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        DiscoveryProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
    }

    private AdsProto() {
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
