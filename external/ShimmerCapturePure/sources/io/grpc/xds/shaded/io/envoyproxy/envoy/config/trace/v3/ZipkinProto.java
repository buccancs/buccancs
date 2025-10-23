package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import envoy.annotations.Deprecation;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class ZipkinProto {
    static final Descriptors.Descriptor internal_static_envoy_config_trace_v3_ZipkinConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_trace_v3_ZipkinConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\"envoy/config/trace/v3/zipkin.proto\u0012\u0015envoy.config.trace.v3\u001a\u001egoogle/protobuf/wrappers.proto\u001a#envoy/annotations/deprecation.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"°\u0003\n\fZipkinConfig\u0012\"\n\u0011collector_cluster\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012#\n\u0012collector_endpoint\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u0017\n\u000ftrace_id_128bit\u0018\u0003 \u0001(\b\u00127\n\u0013shared_span_context\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012`\n\u001acollector_endpoint_version\u0018\u0005 \u0001(\u000e2<.envoy.config.trace.v3.ZipkinConfig.CollectorEndpointVersion\"x\n\u0018CollectorEndpointVersion\u00123\n%DEPRECATED_AND_UNAVAILABLE_DO_NOT_USE\u0010\u0000\u001a\b\b\u0001¨÷´\u008b\u0002\u0001\u0012\r\n\tHTTP_JSON\u0010\u0001\u0012\u000e\n\nHTTP_PROTO\u0010\u0002\u0012\b\n\u0004GRPC\u0010\u0003:)\u009aÅ\u0088\u001e$\n\"envoy.config.trace.v2.ZipkinConfigBk\n#io.envoyproxy.envoy.config.trace.v3B\u000bZipkinProtoP\u0001ò\u0098þ\u008f\u0005)\u0012'envoy.extensions.tracers.zipkin.v4alphaº\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{WrappersProto.getDescriptor(), Deprecation.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_trace_v3_ZipkinConfig_descriptor = descriptor2;
        internal_static_envoy_config_trace_v3_ZipkinConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"CollectorCluster", "CollectorEndpoint", "TraceId128Bit", "SharedSpanContext", "CollectorEndpointVersion"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Deprecation.disallowedByDefaultEnum);
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        WrappersProto.getDescriptor();
        Deprecation.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private ZipkinProto() {
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
