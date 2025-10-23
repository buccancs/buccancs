package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class EndpointProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$envoy/api/v2/endpoint/endpoint.proto\u0012\u0015envoy.api.v2.endpoint\u001a\u001dudpa/annotations/status.proto\u001a/envoy/api/v2/endpoint/endpoint_components.protoB6\n#io.envoyproxy.envoy.api.v2.endpointB\rEndpointProtoP\u0001P\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), EndpointComponentsProto.getDescriptor()});

    static {
        Status.getDescriptor();
        EndpointComponentsProto.getDescriptor();
    }

    private EndpointProto() {
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
