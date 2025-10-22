package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class LightstepProto {
    static final Descriptors.Descriptor internal_static_envoy_config_trace_v2_LightstepConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_trace_v2_LightstepConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%envoy/config/trace/v2/lightstep.proto\u0012\u0015envoy.config.trace.v2\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"\u0083\u0002\n\u000fLightstepConfig\u0012\"\n\u0011collector_cluster\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\"\n\u0011access_token_file\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012`\n\u0011propagation_modes\u0018\u0003 \u0003(\u000e26.envoy.config.trace.v2.LightstepConfig.PropagationModeB\rúB\n\u0092\u0001\u0007\"\u0005\u0082\u0001\u0002\u0010\u0001\"F\n\u000fPropagationMode\u0012\t\n\u0005ENVOY\u0010\u0000\u0012\r\n\tLIGHTSTEP\u0010\u0001\u0012\u0006\n\u0002B3\u0010\u0002\u0012\u0011\n\rTRACE_CONTEXT\u0010\u0003B?\n#io.envoyproxy.envoy.config.trace.v2B\u000eLightstepProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_trace_v2_LightstepConfig_descriptor = descriptor2;
        internal_static_envoy_config_trace_v2_LightstepConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"CollectorCluster", "AccessTokenFile", "PropagationModes"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private LightstepProto() {
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
