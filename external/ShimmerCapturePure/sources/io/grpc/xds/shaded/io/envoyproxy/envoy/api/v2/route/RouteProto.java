package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Status;

/* loaded from: classes2.dex */
public final class RouteProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001eenvoy/api/v2/route/route.proto\u0012\u0012envoy.api.v2.route\u001a\u001dudpa/annotations/status.proto\u001a)envoy/api/v2/route/route_components.protoB0\n io.envoyproxy.envoy.api.v2.routeB\nRouteProtoP\u0001P\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), RouteComponentsProto.getDescriptor()});

    static {
        Status.getDescriptor();
        RouteComponentsProto.getDescriptor();
    }

    private RouteProto() {
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
