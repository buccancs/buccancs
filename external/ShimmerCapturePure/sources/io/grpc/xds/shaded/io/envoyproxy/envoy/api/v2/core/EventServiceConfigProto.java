package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class EventServiceConfigProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_EventServiceConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_EventServiceConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n,envoy/api/v2/core/event_service_config.proto\u0012\u0011envoy.api.v2.core\u001a$envoy/api/v2/core/grpc_service.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"l\n\u0012EventServiceConfig\u00126\n\fgrpc_service\u0018\u0001 \u0001(\u000b2\u001e.envoy.api.v2.core.GrpcServiceH\u0000B\u001e\n\u0017config_source_specifier\u0012\u0003øB\u0001B`\n\u001fio.envoyproxy.envoy.api.v2.coreB\u0017EventServiceConfigProtoP\u0001ò\u0098þ\u008f\u0005\u0016\u0012\u0014envoy.config.core.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{GrpcServiceProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_core_EventServiceConfig_descriptor = descriptor2;
        internal_static_envoy_api_v2_core_EventServiceConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"GrpcService", "ConfigSourceSpecifier"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        GrpcServiceProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
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
