package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class CertProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cenvoy/api/v2/auth/cert.proto\u0012\u0011envoy.api.v2.auth\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u001eenvoy/api/v2/auth/common.proto\u001a\u001eenvoy/api/v2/auth/secret.proto\u001a\u001benvoy/api/v2/auth/tls.protoB_\n\u001fio.envoyproxy.envoy.api.v2.authB\tCertProtoP\u0001ò\u0098þ\u008f\u0005+\u0012)envoy.extensions.transport_sockets.tls.v3P\u0002P\u0003P\u0004b\u0006proto3"}, new Descriptors.FileDescriptor[]{Migrate.getDescriptor(), Status.getDescriptor(), CommonProto.getDescriptor(), SecretProto.getDescriptor(), TlsProto.getDescriptor()});

    static {
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Migrate.getDescriptor();
        Status.getDescriptor();
        CommonProto.getDescriptor();
        SecretProto.getDescriptor();
        TlsProto.getDescriptor();
    }

    private CertProto() {
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
