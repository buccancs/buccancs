package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes6.dex */
public final class EventServiceConfigProto {
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_EventServiceConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_EventServiceConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n/envoy/config/core/v3/event_service_config.proto\u0012\u0014envoy.config.core.v3\u001a'envoy/config/core/v3/grpc_service.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"\u009c\u0001\n\u0012EventServiceConfig\u00129\n\fgrpc_service\u0018\u0001 \u0001(\u000b2!.envoy.config.core.v3.GrpcServiceH\u0000:+\u009aÅ\u0088\u001e&\n$envoy.api.v2.core.EventServiceConfigB\u001e\n\u0017config_source_specifier\u0012\u0003øB\u0001BG\n\"io.envoyproxy.envoy.config.core.v3B\u0017EventServiceConfigProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{GrpcServiceProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_core_v3_EventServiceConfig_descriptor = descriptor2;
        internal_static_envoy_config_core_v3_EventServiceConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"GrpcService", "ConfigSourceSpecifier"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.required);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        GrpcServiceProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private EventServiceConfigProto() {
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
