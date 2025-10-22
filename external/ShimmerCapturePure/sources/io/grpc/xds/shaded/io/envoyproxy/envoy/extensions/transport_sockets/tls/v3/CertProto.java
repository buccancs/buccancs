package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class CertProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n4envoy/extensions/transport_sockets/tls/v3/cert.proto\u0012)envoy.extensions.transport_sockets.tls.v3\u001a\u001dudpa/annotations/status.proto\u001a6envoy/extensions/transport_sockets/tls/v3/common.proto\u001a6envoy/extensions/transport_sockets/tls/v3/secret.proto\u001a3envoy/extensions/transport_sockets/tls/v3/tls.protoBF\n7io.envoyproxy.envoy.extensions.transport_sockets.tls.v3B\tCertProtoP\u0001P\u0001P\u0002P\u0003b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), CommonProto.getDescriptor(), SecretProto.getDescriptor(), TlsProto.getDescriptor()});

    static {
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
