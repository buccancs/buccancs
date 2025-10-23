package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Status;

/* loaded from: classes5.dex */
public final class ListenerProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n$envoy/api/v2/listener/listener.proto\u0012\u0015envoy.api.v2.listener\u001a\u001dudpa/annotations/status.proto\u001a/envoy/api/v2/listener/listener_components.protoBj\n#io.envoyproxy.envoy.api.v2.listenerB\rListenerProtoP\u0001ª\u0002\u0017Envoy.Api.V2.ListenerNSê\u0002\u0017Envoy.Api.V2.ListenerNSP\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), ListenerComponentsProto.getDescriptor()});

    static {
        Status.getDescriptor();
        ListenerComponentsProto.getDescriptor();
    }

    private ListenerProto() {
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
