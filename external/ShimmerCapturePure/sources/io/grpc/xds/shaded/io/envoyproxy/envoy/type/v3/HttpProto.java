package io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class HttpProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018envoy/type/v3/http.proto\u0012\renvoy.type.v3\u001a\u001dudpa/annotations/status.proto*2\n\u000fCodecClientType\u0012\t\n\u0005HTTP1\u0010\u0000\u0012\t\n\u0005HTTP2\u0010\u0001\u0012\t\n\u0005HTTP3\u0010\u0002B2\n\u001bio.envoyproxy.envoy.type.v3B\tHttpProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor()});

    static {
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
    }

    private HttpProto() {
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
