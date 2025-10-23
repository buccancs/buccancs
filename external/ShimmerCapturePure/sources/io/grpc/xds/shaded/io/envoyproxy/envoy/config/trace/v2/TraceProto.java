package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class TraceProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!envoy/config/trace/v2/trace.proto\u0012\u0015envoy.config.trace.v2\u001a\u001dudpa/annotations/status.proto\u001a#envoy/config/trace/v2/datadog.proto\u001a&envoy/config/trace/v2/dynamic_ot.proto\u001a'envoy/config/trace/v2/http_tracer.proto\u001a%envoy/config/trace/v2/lightstep.proto\u001a&envoy/config/trace/v2/opencensus.proto\u001a#envoy/config/trace/v2/service.proto\u001a\"envoy/config/trace/v2/zipkin.protoB3\n#io.envoyproxy.envoy.config.trace.v2B\nTraceProtoP\u0001P\u0001P\u0002P\u0003P\u0004P\u0005P\u0006P\u0007b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), DatadogProto.getDescriptor(), DynamicOtProto.getDescriptor(), HttpTracerProto.getDescriptor(), LightstepProto.getDescriptor(), OpencensusProto.getDescriptor(), ServiceProto.getDescriptor(), ZipkinProto.getDescriptor()});

    static {
        Status.getDescriptor();
        DatadogProto.getDescriptor();
        DynamicOtProto.getDescriptor();
        HttpTracerProto.getDescriptor();
        LightstepProto.getDescriptor();
        OpencensusProto.getDescriptor();
        ServiceProto.getDescriptor();
        ZipkinProto.getDescriptor();
    }

    private TraceProto() {
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
