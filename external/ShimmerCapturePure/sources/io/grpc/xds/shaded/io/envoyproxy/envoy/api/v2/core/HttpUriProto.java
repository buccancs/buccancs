package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import org.apache.http.HttpHeaders;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class HttpUriProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_HttpUri_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_HttpUri_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n envoy/api/v2/core/http_uri.proto\u0012\u0011envoy.api.v2.core\u001a\u001egoogle/protobuf/duration.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"\u008e\u0001\n\u0007HttpUri\u0012\u0014\n\u0003uri\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u001a\n\u0007cluster\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000\u00126\n\u0007timeout\u0018\u0003 \u0001(\u000b2\u0019.google.protobuf.DurationB\núB\u0007ª\u0001\u0004\b\u00012\u0000B\u0019\n\u0012http_upstream_type\u0012\u0003øB\u0001BU\n\u001fio.envoyproxy.envoy.api.v2.coreB\fHttpUriProtoP\u0001ò\u0098þ\u008f\u0005\u0016\u0012\u0014envoy.config.core.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_core_HttpUri_descriptor = descriptor2;
        internal_static_envoy_api_v2_core_HttpUri_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Uri", "Cluster", HttpHeaders.TIMEOUT, "HttpUpstreamType"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        DurationProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private HttpUriProto() {
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
