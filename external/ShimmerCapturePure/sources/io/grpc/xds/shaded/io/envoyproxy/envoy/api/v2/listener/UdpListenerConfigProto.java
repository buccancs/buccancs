package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes5.dex */
public final class UdpListenerConfigProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_listener_ActiveRawUdpListenerConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_listener_ActiveRawUdpListenerConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_listener_UdpListenerConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_listener_UdpListenerConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n/envoy/api/v2/listener/udp_listener_config.proto\u0012\u0015envoy.api.v2.listener\u001a\u0019google/protobuf/any.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\"\u009a\u0001\n\u0011UdpListenerConfig\u0012\u0019\n\u0011udp_listener_name\u0018\u0001 \u0001(\t\u0012-\n\u0006config\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.StructB\u0002\u0018\u0001H\u0000\u0012,\n\ftyped_config\u0018\u0003 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000B\r\n\u000bconfig_type\"\u001c\n\u001aActiveRawUdpListenerConfigB\u009b\u0001\n#io.envoyproxy.envoy.api.v2.listenerB\u0016UdpListenerConfigProtoP\u0001ª\u0002\u0017Envoy.Api.V2.ListenerNSê\u0002\u0017Envoy.Api.V2.ListenerNSò\u0098þ\u008f\u0005\u001a\u0012\u0018envoy.config.listener.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor(), StructProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_listener_UdpListenerConfig_descriptor = descriptor2;
        internal_static_envoy_api_v2_listener_UdpListenerConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"UdpListenerName", "Config", "TypedConfig", "ConfigType"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_listener_ActiveRawUdpListenerConfig_descriptor = descriptor3;
        internal_static_envoy_api_v2_listener_ActiveRawUdpListenerConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[0]);
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AnyProto.getDescriptor();
        StructProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
    }

    private UdpListenerConfigProto() {
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
