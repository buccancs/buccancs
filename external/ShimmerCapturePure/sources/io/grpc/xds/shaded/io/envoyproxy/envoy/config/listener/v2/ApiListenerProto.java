package io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v2;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes6.dex */
public final class ApiListenerProto {
    static final Descriptors.Descriptor internal_static_envoy_config_listener_v2_ApiListener_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_listener_v2_ApiListener_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n+envoy/config/listener/v2/api_listener.proto\u0012\u0018envoy.config.listener.v2\u001a\u0019google/protobuf/any.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\"9\n\u000bApiListener\u0012*\n\fapi_listener\u0018\u0001 \u0001(\u000b2\u0014.google.protobuf.AnyBd\n&io.envoyproxy.envoy.config.listener.v2B\u0010ApiListenerProtoP\u0001ò\u0098þ\u008f\u0005\u001a\u0012\u0018envoy.config.listener.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_listener_v2_ApiListener_descriptor = descriptor2;
        internal_static_envoy_config_listener_v2_ApiListener_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ApiListener"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AnyProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
    }

    private ApiListenerProto() {
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
