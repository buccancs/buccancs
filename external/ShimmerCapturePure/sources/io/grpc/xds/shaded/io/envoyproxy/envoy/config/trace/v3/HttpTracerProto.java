package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class HttpTracerProto {
    static final Descriptors.Descriptor internal_static_envoy_config_trace_v3_Tracing_Http_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_trace_v3_Tracing_Http_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_trace_v3_Tracing_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_trace_v3_Tracing_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n'envoy/config/trace/v3/http_tracer.proto\u0012\u0015envoy.config.trace.v3\u001a\u0019google/protobuf/any.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"ø\u0001\n\u0007Tracing\u00121\n\u0004http\u0018\u0001 \u0001(\u000b2#.envoy.config.trace.v3.Tracing.Http\u001a\u0093\u0001\n\u0004Http\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012,\n\ftyped_config\u0018\u0003 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000:)\u009aÅ\u0088\u001e$\n\"envoy.config.trace.v2.Tracing.HttpB\r\n\u000bconfig_typeJ\u0004\b\u0002\u0010\u0003R\u0006config:$\u009aÅ\u0088\u001e\u001f\n\u001denvoy.config.trace.v2.TracingB@\n#io.envoyproxy.envoy.config.trace.v3B\u000fHttpTracerProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor(), StructProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_trace_v3_Tracing_descriptor = descriptor2;
        internal_static_envoy_config_trace_v3_Tracing_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Http"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_config_trace_v3_Tracing_Http_descriptor = descriptor3;
        internal_static_envoy_config_trace_v3_Tracing_Http_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "TypedConfig", "ConfigType"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AnyProto.getDescriptor();
        StructProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private HttpTracerProto() {
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
